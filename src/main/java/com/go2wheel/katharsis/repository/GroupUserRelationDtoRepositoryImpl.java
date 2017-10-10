package com.go2wheel.katharsis.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.BootUser;
import com.go2wheel.domain.GroupUserRelation;
import com.go2wheel.facade.GroupUserRelationFacadeRepository;
import com.go2wheel.facade.Page;
import com.go2wheel.facade.PageFacade;
import com.go2wheel.katharsis.dto.GroupUserRelationDto;
import com.go2wheel.katharsis.dto.converter.GroupUserRelationDtoConverter;
import com.go2wheel.katharsis.repository.GroupUserRelationDtoRepository.GroupUserRelationDtoList;
import com.go2wheel.util.QuerySpecUtil.RelationQuery;

import io.katharsis.queryspec.QuerySpec;

@Component
public class GroupUserRelationDtoRepositoryImpl  extends DtoRepositoryBase<GroupUserRelationDto, GroupUserRelationDtoList, GroupUserRelation, GroupUserRelationFacadeRepository> implements GroupUserRelationDtoRepository {
	
	@Autowired
	private GroupUserRelationFacadeRepository gurRepo;
	
	@Autowired
	public GroupUserRelationDtoRepositoryImpl(GroupUserRelationFacadeRepository repository, GroupUserRelationDtoConverter converter) {
		super(GroupUserRelationDto.class, GroupUserRelationDtoList.class, GroupUserRelation.class, repository, converter);
	}

	@Override
	protected GroupUserRelationDtoList findAllWithQuerySpec(QuerySpec querySpec) {
		return null;
	}

	@Override
	protected List<String> checkAllSortableFieldAllowed(QuerySpec querySpec) {
		return null;
	}

	@Override
	protected GroupUserRelationDtoList findWithRelationAndSpec(RelationQuery rq, QuerySpec querySpec) {
		return null;
	}

	@Override
	public Page<GroupUserRelation> findByUser(BootUser bu, PageFacade pf) {
		return gurRepo.findByBootUser(bu, pf);
	}
}
