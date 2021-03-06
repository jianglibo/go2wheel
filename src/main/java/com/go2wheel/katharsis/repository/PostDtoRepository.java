package com.go2wheel.katharsis.repository;

import com.go2wheel.katharsis.dto.PostDto;

import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryV2;
import io.katharsis.resource.list.ResourceListBase;

public interface PostDtoRepository extends ResourceRepositoryV2<PostDto, Long> {


	public class PostDtoList extends ResourceListBase<PostDto, DtoListMeta, DtoListLinks> {

	}

	@Override
	public PostDtoList findAll(QuerySpec querySpec);
}

