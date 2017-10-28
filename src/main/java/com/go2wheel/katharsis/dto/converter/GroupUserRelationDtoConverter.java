package com.go2wheel.katharsis.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.GroupUserRelation;
import com.go2wheel.katharsis.dto.GroupUserRelationDto;

@Component
public class GroupUserRelationDtoConverter extends DtoConverterBase<GroupUserRelation, GroupUserRelationDto> {

	@Autowired
	private UserDtoConverter userConverter;
	
	@Autowired
	private GroupDtoConverter groupConverter;

	@Override
	protected GroupUserRelationDto afterPropertyCopy(GroupUserRelation entity, GroupUserRelationDto dto,
			Scenario scenario) {
		dto.setBootUser(userConverter.entity2Dto(entity.getBootUser(), scenario));
		dto.setBootGroup(groupConverter.entity2Dto(entity.getBootGroup(), scenario));
		return dto;
	}
}
