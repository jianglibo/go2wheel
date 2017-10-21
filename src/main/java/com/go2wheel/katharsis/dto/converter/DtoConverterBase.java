package com.go2wheel.katharsis.dto.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.go2wheel.config.DtoEntityMapper;
import com.go2wheel.domain.BaseEntity;
import com.go2wheel.katharsis.dto.DtoBase;

public abstract class DtoConverterBase<E extends BaseEntity, D extends DtoBase> implements DtoConverter<E, D> {
	
	@Autowired
	private DtoEntityMapper dtoEntityMapper;

	@SuppressWarnings("unchecked")
	@Override
	public D entity2Dto(E entity, Scenario scenario) {
		Class<?> dtoClass = dtoEntityMapper.getEntityToDto().get(entity.getClass());
		D d = null;
		try {
			d = (D) dtoClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		BeanUtils.copyProperties(entity, d, dtoEntityMapper.getEntityToDtoIgnors().get(entity.getClass()));
		D newd = afterPropertyCopy(entity, d, scenario);
		if (newd == null) {
			newd = d;
		}
		return newd;
	}
	
	protected abstract D afterPropertyCopy(E entity, D dto, Scenario scenario);

}
