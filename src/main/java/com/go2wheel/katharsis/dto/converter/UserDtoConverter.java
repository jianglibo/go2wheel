package com.go2wheel.katharsis.dto.converter;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.BootUser;
import com.go2wheel.katharsis.dto.UserDto;

@Component
public class UserDtoConverter extends DtoConverterBase<BootUser, UserDto> {
	
	@Autowired
	private RoleDtoConverter roleConverter;

	@Override
	protected UserDto afterPropertyCopy(BootUser entity, UserDto dto, Scenario scenario) {
		dto.setRoles(entity.getRoles().stream().map(r -> roleConverter.entity2Dto(r, scenario)).collect(Collectors.toList()));
		return dto;
	}

}
