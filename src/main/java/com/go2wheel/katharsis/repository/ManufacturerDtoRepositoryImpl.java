package com.go2wheel.katharsis.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.Manufacturer;
import com.go2wheel.facade.ManufacturerFacadeRepository;
import com.go2wheel.facade.Page;
import com.go2wheel.katharsis.dto.ManufacturerDto;
import com.go2wheel.katharsis.dto.converter.DtoConverter.Scenario;
import com.go2wheel.katharsis.dto.converter.ManufacturerDtoConverter;
import com.go2wheel.katharsis.repository.ManufacturerDtoRepository.ManufacturerDtoList;
import com.go2wheel.util.QuerySpecUtil;
import com.go2wheel.util.QuerySpecUtil.RelationQuery;

import io.katharsis.queryspec.QuerySpec;

@Component
public class ManufacturerDtoRepositoryImpl  extends DtoRepositoryBase<ManufacturerDto, ManufacturerDtoList, Manufacturer, ManufacturerFacadeRepository> implements ManufacturerDtoRepository {
	
	
	@Autowired
	public ManufacturerDtoRepositoryImpl(ManufacturerFacadeRepository repository, ManufacturerDtoConverter converter) {
		super(ManufacturerDto.class, ManufacturerDtoList.class, Manufacturer.class, repository, converter);
	}

	@Override
	protected ManufacturerDtoList findAllWithQuerySpec(QuerySpec querySpec) {
		String name = (String) QuerySpecUtil.getFilterSingleValue(querySpec, "name").orElse("");
		Page<Manufacturer> Manufacturers = getRepository().findByNameContaining(name, QuerySpecUtil.getPageFacade(querySpec));
		return convertToResourceList(Manufacturers, Scenario.FIND_LIST);
	}

	@Override
	protected List<String> checkAllSortableFieldAllowed(QuerySpec querySpec) {
		return null;
	}

	@Override
	protected ManufacturerDtoList findWithRelationAndSpec(RelationQuery rq, QuerySpec querySpec) {
		if ("mtSerieses".equals(rq.getRelationName())) {
			Manufacturer muf = getRepository().findOne(rq.getRelationIds().get(0), true);
			return convertToResourceList(muf, Scenario.RELATION_LIST);
		} else {
			return null;
		}
	}
}
