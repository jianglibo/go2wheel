package com.go2wheel.katharsis.repository;

import com.go2wheel.domain.BootUser;
import com.go2wheel.domain.GroupUserRelation;
import com.go2wheel.facade.Page;
import com.go2wheel.facade.PageFacade;
import com.go2wheel.katharsis.dto.GroupUserRelationDto;

import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryV2;
import io.katharsis.resource.list.ResourceListBase;

public interface GroupUserRelationDtoRepository extends ResourceRepositoryV2<GroupUserRelationDto, Long> {


	public class GroupUserRelationDtoList extends ResourceListBase<GroupUserRelationDto, DtoListMeta, DtoListLinks> {
	}

	@Override
	public GroupUserRelationDtoList findAll(QuerySpec querySpec);

	public Page<GroupUserRelation> findByUser(BootUser bu, PageFacade pf);

}

