package com.go2wheel.katharsis.repository;

import org.springframework.stereotype.Component;

import com.go2wheel.katharsis.dto.ApproveDto;
import com.go2wheel.katharsis.dto.UserDto;

@Component
public class UserToApproveRepositoryImpl extends RelationshipRepositoryBaseMine<UserDto, ApproveDto> {
	
	protected UserToApproveRepositoryImpl() {
		super(UserDto.class, ApproveDto.class);
	}

}
