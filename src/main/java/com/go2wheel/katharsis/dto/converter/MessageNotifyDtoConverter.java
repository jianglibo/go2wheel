package com.go2wheel.katharsis.dto.converter;

import org.springframework.stereotype.Component;

import com.go2wheel.domain.MessageNotify;
import com.go2wheel.katharsis.dto.MessageNotifyDto;

@Component
public class MessageNotifyDtoConverter extends DtoConverterBase<MessageNotify, MessageNotifyDto> {

	@Override
	protected MessageNotifyDto afterPropertyCopy(MessageNotify entity, MessageNotifyDto dto, Scenario scenario) {
		// TODO Auto-generated method stub
		return null;
	}

}
