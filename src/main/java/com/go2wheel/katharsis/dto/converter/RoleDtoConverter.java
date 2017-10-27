package com.go2wheel.katharsis.dto.converter;

import org.springframework.stereotype.Component;

import com.go2wheel.domain.Role;
import com.go2wheel.katharsis.dto.RoleDto;

@Component
public class RoleDtoConverter extends DtoConverterBase<Role, RoleDto> {

	@Override
	protected RoleDto afterPropertyCopy(Role entity, RoleDto dto, Scenario scenario) {
		return null;
	}

}
