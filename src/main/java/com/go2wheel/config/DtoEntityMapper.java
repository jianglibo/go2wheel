package com.go2wheel.config;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.go2wheel.annotation.DtoToEntity;
import com.go2wheel.annotation.EntityToDtoIgnore;
import com.go2wheel.domain.BaseEntity;
import com.go2wheel.katharsis.dto.Dto;
import com.go2wheel.util.ClassScanner;

@Component
public class DtoEntityMapper implements InitializingBean {
	
	private final Map<Class<? extends Dto>, Class<? extends BaseEntity>>  dtoToEntity = new HashMap<>();
	
	private final Map<Class<? extends BaseEntity>, Class<? extends Dto>>  entityToDto = new HashMap<>();
	
	private final Map<Class<? extends BaseEntity>, String[]>  entityToDtoIgnores = new HashMap<>();
	
	private final Map<Class<? extends BaseEntity>, String[]>  dtoToEntityIgnores = new HashMap<>();
	
	@Override
	public void afterPropertiesSet() throws Exception {
		List<Class<?>> dtoClasses = ClassScanner.findAnnotatedBy("com.go2wheel", DtoToEntity.class);
		
		DtoToEntity de;
		Class<? extends BaseEntity> entityClass;
		for(Class<?> dtoClass : dtoClasses) {
			de = dtoClass.getAnnotation(DtoToEntity.class);
			entityClass = de.entityClass();
			
			Field[] fields = entityClass.getDeclaredFields();
			String[] entityToDtoIgnoreNames = Stream.of(fields).map(f -> {
				EntityToDtoIgnore tdi = f.getAnnotation(EntityToDtoIgnore.class);
				if (tdi != null) {
					return tdi.value().isEmpty() ? f.getName() : tdi.value();
				} else {
					return null;
				}
			}).filter(s -> s != null).toArray(length -> new String[length]);
			
			entityToDtoIgnores.put(entityClass, entityToDtoIgnoreNames);
			
			String[] dtoToEntityIgnoreNames = Stream.of(fields).map(f -> {
				EntityToDtoIgnore tdi = f.getAnnotation(EntityToDtoIgnore.class);
				if (tdi != null) {
					return tdi.value().isEmpty() ? f.getName() : tdi.value();
				} else {
					return null;
				}
			}).filter(s -> s != null).toArray(length -> new String[length]);
			
			dtoToEntityIgnores.put(entityClass, dtoToEntityIgnoreNames);
			
			@SuppressWarnings("unchecked")
			Class<? extends Dto> dtotrue = (Class<? extends Dto>) dtoClass;
			dtoToEntity.put(dtotrue, entityClass);
			entityToDto.put(entityClass, dtotrue);
		}
	}

	public Map<Class<? extends Dto>, Class<? extends BaseEntity>> getDtoToEntity() {
		return dtoToEntity;
	}

	public Map<Class<? extends BaseEntity>, Class<? extends Dto>> getEntityToDto() {
		return entityToDto;
	}

	public Map<Class<? extends BaseEntity>, String[]> getEntityToDtoIgnores() {
		return entityToDtoIgnores;
	}

	public Map<Class<? extends BaseEntity>, String[]> getDtoToEntityIgnores() {
		return dtoToEntityIgnores;
	}
	
	
	
}
