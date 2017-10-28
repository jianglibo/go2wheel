package com.go2wheel.katharsis.dto.converter;

import org.springframework.stereotype.Component;

import com.go2wheel.domain.BootGroup;
import com.go2wheel.katharsis.dto.GroupDto;

@Component
public class GroupDtoConverter extends DtoConverterBase<BootGroup, GroupDto> {

	@Override
	protected GroupDto afterPropertyCopy(BootGroup entity, GroupDto dto, Scenario scenario) {
		return dto;
	}

}
