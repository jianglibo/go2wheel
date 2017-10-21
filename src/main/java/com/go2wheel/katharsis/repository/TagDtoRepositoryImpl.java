package com.go2wheel.katharsis.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.Tag;
import com.go2wheel.facade.Page;
import com.go2wheel.facade.TagFacadeRepository;
import com.go2wheel.katharsis.dto.TagDto;
import com.go2wheel.katharsis.dto.converter.DtoConverter.Scenario;
import com.go2wheel.katharsis.dto.converter.TagDtoConverter;
import com.go2wheel.katharsis.repository.TagDtoRepository.TagDtoList;
import com.go2wheel.util.QuerySpecUtil;
import com.go2wheel.util.QuerySpecUtil.RelationQuery;

import io.katharsis.queryspec.QuerySpec;

@Component
public class TagDtoRepositoryImpl  extends DtoRepositoryBase<TagDto, TagDtoList, Tag, TagFacadeRepository> implements TagDtoRepository {
	
	
	@Autowired
	public TagDtoRepositoryImpl(TagFacadeRepository repository, TagDtoConverter converter) {
		super(TagDto.class, TagDtoList.class, Tag.class, repository, converter);
	}

	@Override
	protected TagDtoList findAllWithQuerySpec(QuerySpec querySpec) {
		String name = (String) QuerySpecUtil.getFilterSingleValue(querySpec, "name").orElse("");
		Page<Tag> tags = getRepository().findByNameContaining(name, QuerySpecUtil.getPageFacade(querySpec));
		return convertToResourceList(tags, Scenario.FIND_LIST);
	}

	@Override
	protected List<String> checkAllSortableFieldAllowed(QuerySpec querySpec) {
		return null;
	}

	@Override
	protected TagDtoList findWithRelationAndSpec(RelationQuery rq, QuerySpec querySpec) {
		return null;
	}
}
