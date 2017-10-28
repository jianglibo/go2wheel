package com.go2wheel.katharsis.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.PostShare;
import com.go2wheel.katharsis.dto.PostShareDto;

@Component
public class PostShareDtoConverter extends DtoConverterBase<PostShare, PostShareDto> {

	@Autowired
	private UserDtoConverter userConverter;
	
	@Autowired
	private PostDtoConverter postConverter;

	@Override
	protected PostShareDto afterPropertyCopy(PostShare entity, PostShareDto dto, Scenario scenario) {
		dto.setBootUser(userConverter.entity2Dto(entity.getBootUser(), scenario));
		dto.setPost(postConverter.entity2Dto(entity.getPost(), scenario));
		return dto;
	}
}
