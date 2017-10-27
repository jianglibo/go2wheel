package com.go2wheel.katharsis.dto.converter;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.Post;
import com.go2wheel.facade.MediumFacadeRepository;
import com.go2wheel.katharsis.dto.PostDto;

@Component
public class PostDtoConverter extends DtoConverterBase<Post, PostDto> {
	
	@Autowired
	private UserDtoConverter userConverter;
	
	@SuppressWarnings("unused")
	@Autowired
	private MediumFacadeRepository mediumRepo;
	
	@Autowired
	private MediumDtoConverter mediumConverter;

	@Override
	protected PostDto afterPropertyCopy(Post entity, PostDto dto, Scenario scenario) {
		dto.setCreator(userConverter.entity2Dto(entity.getCreator(), scenario));
		if (entity.getMedia() != null) {
			dto.setMedia(entity.getMedia().stream().map(en -> mediumConverter.entity2Dto(en, Scenario.RELATION_LIST)).collect(Collectors.toList()));
		}
		if (scenario == Scenario.FIND_ONE) {
			dto.setRead(true);
		}
		return dto;
	}

}
