package com.go2wheel.katharsis.dto.converter;

import org.springframework.stereotype.Component;

import com.go2wheel.domain.Medium;
import com.go2wheel.katharsis.dto.MediumDto;

@Component
public class MediumDtoConverter extends DtoConverterBase<Medium, MediumDto> {

	@Override
	protected MediumDto afterPropertyCopy(Medium entity, MediumDto dto, Scenario scenario) {
		return null;
	}

}
