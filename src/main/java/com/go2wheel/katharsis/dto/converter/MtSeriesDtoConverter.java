package com.go2wheel.katharsis.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.MtSeries;
import com.go2wheel.katharsis.dto.MtSeriesDto;

@Component
public class MtSeriesDtoConverter extends DtoConverterBase<MtSeries, MtSeriesDto> {

	@Autowired
	private ManufacturerDtoConverter mfConverter;
	
	@Override
	protected MtSeriesDto afterPropertyCopy(MtSeries entity, MtSeriesDto dto, Scenario scenario) {
		dto.setManufacturer(mfConverter.entity2Dto(entity.getManufacturer(), Scenario.IN_RELATION));
		return dto;
	}

}
