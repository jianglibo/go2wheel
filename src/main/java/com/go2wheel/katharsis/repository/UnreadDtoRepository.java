package com.go2wheel.katharsis.repository;

import com.go2wheel.katharsis.dto.UnreadDto;

import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryV2;
import io.katharsis.resource.list.ResourceListBase;

public interface UnreadDtoRepository extends ResourceRepositoryV2<UnreadDto, Long> {


	public class UnreadDtoList extends ResourceListBase<UnreadDto, DtoListMeta, DtoListLinks> {

	}

	@Override
	public UnreadDtoList findAll(QuerySpec querySpec);
}

