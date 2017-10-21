package com.go2wheel.katharsis.repository;

import com.go2wheel.katharsis.dto.MtSeriesDto;

import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryV2;
import io.katharsis.resource.list.ResourceListBase;

public interface MtSeriesDtoRepository extends ResourceRepositoryV2<MtSeriesDto, Long> {


	public class MtSeriesDtoList extends ResourceListBase<MtSeriesDto, DtoListMeta, DtoListLinks> {

	}

	@Override
	public MtSeriesDtoList findAll(QuerySpec querySpec);
}

