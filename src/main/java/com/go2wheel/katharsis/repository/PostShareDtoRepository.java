package com.go2wheel.katharsis.repository;

import com.go2wheel.katharsis.dto.PostShareDto;

import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryV2;
import io.katharsis.resource.list.ResourceListBase;

public interface PostShareDtoRepository extends ResourceRepositoryV2<PostShareDto, Long> {


	public class PostShareDtoList extends ResourceListBase<PostShareDto, DtoListMeta, DtoListLinks> {

	}

	@Override
	public PostShareDtoList findAll(QuerySpec querySpec);
}

