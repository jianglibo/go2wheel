package com.go2wheel.katharsis.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.MtModel;
import com.go2wheel.domain.MtSeries;
import com.go2wheel.facade.Page;
import com.go2wheel.facade.MtModelFacadeRepository;
import com.go2wheel.facade.MtSeriesFacadeRepository;
import com.go2wheel.katharsis.dto.MtModelDto;
import com.go2wheel.katharsis.dto.converter.DtoConverter.Scenario;
import com.go2wheel.katharsis.dto.converter.MtModelDtoConverter;
import com.go2wheel.katharsis.repository.MtModelDtoRepository.MtModelDtoList;
import com.go2wheel.util.QuerySpecUtil;
import com.go2wheel.util.QuerySpecUtil.RelationQuery;

import io.katharsis.queryspec.QuerySpec;

@Component
public class MtModelDtoRepositoryImpl  extends DtoRepositoryBase<MtModelDto, MtModelDtoList, MtModel, MtModelFacadeRepository> implements MtModelDtoRepository {
	
	@Autowired
	private MtSeriesFacadeRepository mtSeriesRepo;
	
	@Autowired
	public MtModelDtoRepositoryImpl(MtModelFacadeRepository repository, MtModelDtoConverter converter) {
		super(MtModelDto.class, MtModelDtoList.class, MtModel.class, repository, converter);
	}

	@Override
	protected MtModelDtoList findAllWithQuerySpec(QuerySpec querySpec) {
		String name = (String) QuerySpecUtil.getFilterSingleValue(querySpec, "name").orElse("");
		Page<MtModel> MtModels = getRepository().findByNameContaining(name, QuerySpecUtil.getPageFacade(querySpec));
		return convertToResourceList(MtModels, Scenario.FIND_LIST);
	}

	@Override
	protected List<String> checkAllSortableFieldAllowed(QuerySpec querySpec) {
		return null;
	}

	@Override
	protected MtModelDtoList findWithRelationAndSpec(RelationQuery rq, QuerySpec querySpec) {
		if ("mtSeries".equals(rq.getRelationName())) {
			MtSeries ms = mtSeriesRepo.findOne(rq.getRelationIds().get(0), false);
			Page<MtModel> mtModels = getRepository().findByMtSeries(ms, QuerySpecUtil.getPageFacade(querySpec));
			return convertToResourceList(mtModels, Scenario.FIND_LIST);
		}

		return null;
	}
}
