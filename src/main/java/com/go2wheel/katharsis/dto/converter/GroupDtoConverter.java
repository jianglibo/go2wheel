package com.go2wheel.katharsis.dto.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.BootGroup;
import com.go2wheel.katharsis.dto.GroupDto;

@Component
public class GroupDtoConverter implements DtoConverter<BootGroup, GroupDto> {

	@Override
	public GroupDto entity2Dto(BootGroup entity, Scenario scenario) {
		GroupDto dto = new GroupDto();
		BeanUtils.copyProperties(entity, dto, "members", "creator");
		return dto;
	}

}
