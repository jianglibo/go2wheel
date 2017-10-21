package com.go2wheel.katharsis.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.FollowRelation;
import com.go2wheel.katharsis.dto.FollowRelationDto;

@Component
public class FollowRelationDtoConverter implements DtoConverter<FollowRelation, FollowRelationDto> {

	@Autowired
	private UserDtoConverter userConverter;

	@Override
	public FollowRelationDto entity2Dto(FollowRelation entity, Scenario scenario) {
		FollowRelationDto dto = new FollowRelationDto();
		dto.setFollower(userConverter.entity2Dto(entity.getFollower(), scenario));
		dto.setBefollowed(userConverter.entity2Dto(entity.getFollowed(), scenario));
		return dto;
	}
}
