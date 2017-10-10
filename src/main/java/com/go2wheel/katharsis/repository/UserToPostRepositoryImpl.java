package com.go2wheel.katharsis.repository;

import org.springframework.stereotype.Component;

import com.go2wheel.katharsis.dto.PostDto;
import com.go2wheel.katharsis.dto.UserDto;

@Component
public class UserToPostRepositoryImpl extends RelationshipRepositoryBaseMine<UserDto, PostDto> {

	protected UserToPostRepositoryImpl() {
		super(UserDto.class, PostDto.class);
	}

}
