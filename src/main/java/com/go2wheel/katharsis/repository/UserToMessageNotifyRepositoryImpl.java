package com.go2wheel.katharsis.repository;

import org.springframework.stereotype.Component;

import com.go2wheel.katharsis.dto.MessageNotifyDto;
import com.go2wheel.katharsis.dto.UserDto;

@Component
public class UserToMessageNotifyRepositoryImpl extends RelationshipRepositoryBaseMine<UserDto, MessageNotifyDto> {
	
	protected UserToMessageNotifyRepositoryImpl() {
		super(UserDto.class, MessageNotifyDto.class);
	}
}
