package com.go2wheel.katharsis.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.FollowRelation;
import com.go2wheel.katharsis.dto.FollowRelationDto;

@Component
public class FollowRelationDtoConverter extends DtoConverterBase<FollowRelation, FollowRelationDto> {

	@Autowired
	private UserDtoConverter userConverter;

	@Override
	protected FollowRelationDto afterPropertyCopy(FollowRelation entity, FollowRelationDto dto, Scenario scenario) {
		dto.setFollower(userConverter.entity2Dto(entity.getFollower(), scenario));
		dto.setBefollowed(userConverter.entity2Dto(entity.getFollowed(), scenario));
		return dto;
	}
}
