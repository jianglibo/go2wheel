package com.go2wheel.katharsis.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.Approve;
import com.go2wheel.katharsis.dto.ApproveDto;

@Component
public class ApproveDtoConverter extends DtoConverterBase<Approve, ApproveDto> {

	@SuppressWarnings("unused")
	@Autowired
	private UserDtoConverter userConverter;
	
	@Override
	protected ApproveDto afterPropertyCopy(Approve entity, ApproveDto dto, Scenario scenario) {
		return dto;
	}
}
