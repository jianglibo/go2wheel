package com.go2wheel.katharsis.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.MtModel;
import com.go2wheel.katharsis.dto.MtModelDto;

@Component
public class MtModelDtoConverter extends DtoConverterBase<MtModel, MtModelDto> {
	
	@Autowired
	private MtSeriesDtoConverter mtSeriesDtoConverter;

	@Override
	protected MtModelDto afterPropertyCopy(MtModel entity, MtModelDto dto, Scenario scenario) {
		dto.setMtSeries(mtSeriesDtoConverter.entity2Dto(entity.getMtSeries(), Scenario.FIND_ONE));
		return dto;
	}

}
