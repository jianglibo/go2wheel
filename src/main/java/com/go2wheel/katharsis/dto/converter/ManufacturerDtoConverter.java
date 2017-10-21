package com.go2wheel.katharsis.dto.converter;

import org.springframework.stereotype.Component;

import com.go2wheel.domain.Manufacturer;
import com.go2wheel.katharsis.dto.ManufacturerDto;

@Component
public class ManufacturerDtoConverter extends DtoConverterBase<Manufacturer, ManufacturerDto> {

	@Override
	protected ManufacturerDto afterPropertyCopy(Manufacturer entity, ManufacturerDto dto, Scenario scenario) {
		return null;
	}

}
