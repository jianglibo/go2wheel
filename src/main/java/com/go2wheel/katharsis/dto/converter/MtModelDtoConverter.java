package com.go2wheel.katharsis.dto.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.MtModel;
import com.go2wheel.katharsis.dto.MtModelDto;

@Component
public class MtModelDtoConverter implements DtoConverter<MtModel, MtModelDto> {

	@Override
	public MtModelDto entity2Dto(MtModel entity, Scenario scenario) {
		MtModelDto dto = new MtModelDto();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}

}
