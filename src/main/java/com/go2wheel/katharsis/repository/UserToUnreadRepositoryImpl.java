package com.go2wheel.katharsis.repository;

import org.springframework.stereotype.Component;

import com.go2wheel.katharsis.dto.UnreadDto;
import com.go2wheel.katharsis.dto.UserDto;

@Component
public class UserToUnreadRepositoryImpl extends RelationshipRepositoryBaseMine<UserDto, UnreadDto> {
	
	protected UserToUnreadRepositoryImpl() {
		super(UserDto.class, UnreadDto.class);
	}

}
