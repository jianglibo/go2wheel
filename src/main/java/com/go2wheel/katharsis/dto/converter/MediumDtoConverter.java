﻿package com.go2wheel.katharsis.dto.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.Medium;
import com.go2wheel.katharsis.dto.MediumDto;
import com.go2wheel.katharsis.dto.converter.DtoConverter.Scenario;

@Component
public class MediumDtoConverter implements DtoConverter<Medium, MediumDto> {

//	@Override
//	public Medium dto2Entity(MediumDto dto) {
//		Medium entity = new Medium();
//		BeanUtils.copyProperties(dto, entity);
//		return entity;
//	}

	@Override
	public MediumDto entity2Dto(Medium entity, Scenario scenario) {
		MediumDto dto = new MediumDto();
		BeanUtils.copyProperties(entity, dto, "creator", "post");
		return dto;
	}

}
