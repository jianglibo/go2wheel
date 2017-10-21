package com.go2wheel.katharsis.repository;

import com.go2wheel.katharsis.dto.MtModelDto;

import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryV2;
import io.katharsis.resource.list.ResourceListBase;

public interface MtModelDtoRepository extends ResourceRepositoryV2<MtModelDto, Long> {


	public class MtModelDtoList extends ResourceListBase<MtModelDto, DtoListMeta, DtoListLinks> {

	}

	@Override
	public MtModelDtoList findAll(QuerySpec querySpec);
}

