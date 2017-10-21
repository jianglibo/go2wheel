﻿package com.go2wheel.util;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;

import com.go2wheel.domain.BaseEntity;
import com.go2wheel.katharsis.dto.Dto;

public class PropertyCopyUtil {
	
	private static Map<Class<?>, Set<String>> cmap = new ConcurrentHashMap<>();

	public static <E extends BaseEntity, D extends Dto> void applyPatch(E entity, D dto) {
		if (dto.getDtoApplyTo() != null && !dto.getDtoApplyTo().trim().isEmpty()) {
			Set<String> ppnames = cmap.computeIfAbsent(entity.getClass(), clazz -> getClassPropertyNames(clazz));
			Set<String> ss = dto.calDtoApplyToSet();
			String[] excludes = ppnames.stream().filter(s -> !ss.contains(s)).toArray(size -> new String[size]);
			BeanUtils.copyProperties(dto, entity, excludes);
		}
	}
	
	public static <E extends BaseEntity, D extends Dto> void copyPropertyWhenCreate(E entity, D dto) {
		Set<String> ppnames = cmap.computeIfAbsent(entity.getClass(), clazz -> getClassPropertyNames(clazz));
		Set<String> ss = new HashSet<>(Arrays.asList(entity.propertiesOnCreating()));
		String[] excludes = ppnames.stream().filter(s -> !ss.contains(s)).toArray(size -> new String[size]);
		BeanUtils.copyProperties(dto, entity, excludes);
	}
	
	private static Set<String> getClassPropertyNames(Class<?> clazz) {
		PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(clazz);		
		return Stream.of(pds).map(pd -> pd.getName()).collect(Collectors.toSet());
	}

}
