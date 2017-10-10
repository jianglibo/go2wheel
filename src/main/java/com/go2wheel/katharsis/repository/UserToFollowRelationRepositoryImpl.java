package com.go2wheel.katharsis.repository;

import org.springframework.stereotype.Component;

import com.go2wheel.katharsis.dto.FollowRelationDto;
import com.go2wheel.katharsis.dto.UserDto;

@Component
public class UserToFollowRelationRepositoryImpl extends RelationshipRepositoryBaseMine<UserDto, FollowRelationDto> {

	protected UserToFollowRelationRepositoryImpl() {
		super(UserDto.class, FollowRelationDto.class);
	}

}
