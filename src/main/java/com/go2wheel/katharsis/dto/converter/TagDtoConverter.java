package com.go2wheel.katharsis.dto.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.Tag;
import com.go2wheel.katharsis.dto.TagDto;

@Component
public class TagDtoConverter implements DtoConverter<Tag, TagDto> {

	@Override
	public TagDto entity2Dto(Tag entity, Scenario scenario) {
		TagDto dto = new TagDto();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}

}
