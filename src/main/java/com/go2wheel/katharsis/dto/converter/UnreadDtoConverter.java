package com.go2wheel.katharsis.dto.converter;

import org.springframework.stereotype.Component;

import com.go2wheel.domain.Unread;
import com.go2wheel.katharsis.dto.UnreadDto;

@Component
public class UnreadDtoConverter extends DtoConverterBase<Unread, UnreadDto> {

	@Override
	protected UnreadDto afterPropertyCopy(Unread entity, UnreadDto dto, Scenario scenario) {
		return null;
	}

}
