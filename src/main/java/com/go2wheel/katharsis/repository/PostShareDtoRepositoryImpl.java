package com.go2wheel.katharsis.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.PostShare;
import com.go2wheel.facade.PostShareFacadeRepository;
import com.go2wheel.katharsis.dto.PostShareDto;
import com.go2wheel.katharsis.dto.converter.PostShareDtoConverter;
import com.go2wheel.katharsis.repository.PostShareDtoRepository.PostShareDtoList;
import com.go2wheel.util.QuerySpecUtil.RelationQuery;

import io.katharsis.queryspec.QuerySpec;

@Component
public class PostShareDtoRepositoryImpl  extends DtoRepositoryBase<PostShareDto, PostShareDtoList, PostShare, PostShareFacadeRepository> implements PostShareDtoRepository {
	
	@Autowired
	public PostShareDtoRepositoryImpl(PostShareFacadeRepository repository, PostShareDtoConverter converter) {
		super(PostShareDto.class, PostShareDtoList.class, PostShare.class, repository, converter);
	}

	@Override
	protected PostShareDtoList findAllWithQuerySpec(QuerySpec querySpec) {
		return null;
	}

	@Override
	protected List<String> checkAllSortableFieldAllowed(QuerySpec querySpec) {
		return null;
	}

	@Override
	protected PostShareDtoList findWithRelationAndSpec(RelationQuery rq, QuerySpec querySpec) {
		return null;
	}
}
