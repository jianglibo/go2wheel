package com.go2wheel.katharsis.dto.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.Medium;
import com.go2wheel.katharsis.dto.MediumDto;

@Component
public class MediumDtoConverter implements DtoConverter<Medium, MediumDto> {

	@Override
	public MediumDto entity2Dto(Medium entity, Scenario scenario) {
		MediumDto dto = new MediumDto();
		BeanUtils.copyProperties(entity, dto, "creator", "post");
		return dto;
	}

}
