package com.go2wheel.katharsis.dto.converter;

import org.springframework.stereotype.Component;

import com.go2wheel.domain.Tag;
import com.go2wheel.katharsis.dto.TagDto;

@Component
public class TagDtoConverter extends DtoConverterBase<Tag, TagDto> {

	@Override
	protected TagDto afterPropertyCopy(Tag entity, TagDto dto, Scenario scenario) {
		return null;
	}

}
