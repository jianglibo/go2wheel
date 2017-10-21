package com.go2wheel.katharsis.repository;

import com.go2wheel.katharsis.dto.TagDto;

import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryV2;
import io.katharsis.resource.list.ResourceListBase;

public interface TagDtoRepository extends ResourceRepositoryV2<TagDto, Long> {


	public class TagDtoList extends ResourceListBase<TagDto, DtoListMeta, DtoListLinks> {

	}

	@Override
	public TagDtoList findAll(QuerySpec querySpec);
}

