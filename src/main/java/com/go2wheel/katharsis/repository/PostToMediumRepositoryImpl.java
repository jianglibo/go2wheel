package com.go2wheel.katharsis.repository;

import org.springframework.stereotype.Component;

import com.go2wheel.katharsis.dto.MediumDto;
import com.go2wheel.katharsis.dto.PostDto;

@Component
public class PostToMediumRepositoryImpl extends RelationshipRepositoryBaseMine<PostDto, MediumDto> {
	
	protected PostToMediumRepositoryImpl() {
		super(PostDto.class, MediumDto.class);
	}
}
