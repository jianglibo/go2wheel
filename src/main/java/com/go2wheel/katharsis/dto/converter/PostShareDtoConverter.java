package com.go2wheel.katharsis.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.PostShare;
import com.go2wheel.katharsis.dto.PostShareDto;

@Component
public class PostShareDtoConverter implements DtoConverter<PostShare, PostShareDto> {

	@Autowired
	private UserDtoConverter userConverter;
	
	@Autowired
	private PostDtoConverter postConverter;


	@Override
	public PostShareDto entity2Dto(PostShare entity,Scenario scenario) {
		PostShareDto dto = new PostShareDto();
		dto.setBootUser(userConverter.entity2Dto(entity.getBootUser(), scenario));
		dto.setPost(postConverter.entity2Dto(entity.getPost(), scenario));
		return dto;
	}
}
