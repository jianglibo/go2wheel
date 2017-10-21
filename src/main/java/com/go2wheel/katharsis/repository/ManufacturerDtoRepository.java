package com.go2wheel.katharsis.repository;

import com.go2wheel.katharsis.dto.ManufacturerDto;

import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryV2;
import io.katharsis.resource.list.ResourceListBase;

public interface ManufacturerDtoRepository extends ResourceRepositoryV2<ManufacturerDto, Long> {


	public class ManufacturerDtoList extends ResourceListBase<ManufacturerDto, DtoListMeta, DtoListLinks> {

	}

	@Override
	public ManufacturerDtoList findAll(QuerySpec querySpec);
}

