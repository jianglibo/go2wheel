package com.go2wheel.katharsis.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.Manufacturer;
import com.go2wheel.domain.MtSeries;
import com.go2wheel.facade.Page;
import com.go2wheel.facade.ManufacturerFacadeRepository;
import com.go2wheel.facade.MtSeriesFacadeRepository;
import com.go2wheel.katharsis.dto.MtSeriesDto;
import com.go2wheel.katharsis.dto.converter.DtoConverter.Scenario;
import com.go2wheel.katharsis.dto.converter.MtSeriesDtoConverter;
import com.go2wheel.katharsis.repository.MtSeriesDtoRepository.MtSeriesDtoList;
import com.go2wheel.util.QuerySpecUtil;
import com.go2wheel.util.QuerySpecUtil.RelationQuery;

import io.katharsis.queryspec.QuerySpec;

@Component
public class MtSeriesDtoRepositoryImpl  extends DtoRepositoryBase<MtSeriesDto, MtSeriesDtoList, MtSeries, MtSeriesFacadeRepository> implements MtSeriesDtoRepository {
	
	@Autowired
	private ManufacturerFacadeRepository manufacturerRepo;
	
	@Autowired
	public MtSeriesDtoRepositoryImpl(MtSeriesFacadeRepository repository, MtSeriesDtoConverter converter) {
		super(MtSeriesDto.class, MtSeriesDtoList.class, MtSeries.class, repository, converter);
	}

	@Override
	protected MtSeriesDtoList findAllWithQuerySpec(QuerySpec querySpec) {
		String name = (String) QuerySpecUtil.getFilterSingleValue(querySpec, "name").orElse("");
		Page<MtSeries> MtSeriess = getRepository().findByNameContaining(name, QuerySpecUtil.getPageFacade(querySpec));
		return convertToResourceList(MtSeriess, Scenario.FIND_LIST);
	}

	@Override
	protected List<String> checkAllSortableFieldAllowed(QuerySpec querySpec) {
		return null;
	}

	@Override
	protected MtSeriesDtoList findWithRelationAndSpec(RelationQuery rq, QuerySpec querySpec) {
		if ("manufacturer".equals(rq.getRelationName())) {
			Manufacturer mf = manufacturerRepo.findOne(rq.getRelationIds().get(0), false);
			Page<MtSeries> mtSerieses = getRepository().findByManufacturer(mf, QuerySpecUtil.getPageFacade(querySpec));
			return convertToResourceList(mtSerieses, Scenario.FIND_LIST);
		}
		return null;
	}
}
