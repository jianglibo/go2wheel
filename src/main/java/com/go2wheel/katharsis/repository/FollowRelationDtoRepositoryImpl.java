package com.go2wheel.katharsis.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.BootUser;
import com.go2wheel.domain.FollowRelation;
import com.go2wheel.facade.BootUserFacadeRepository;
import com.go2wheel.facade.FollowRelationFacadeRepository;
import com.go2wheel.facade.Page;
import com.go2wheel.katharsis.dto.FollowRelationDto;
import com.go2wheel.katharsis.dto.UserDto;
import com.go2wheel.katharsis.dto.converter.DtoConverter.Scenario;
import com.go2wheel.katharsis.dto.converter.FollowRelationDtoConverter;
import com.go2wheel.katharsis.repository.FollowRelationDtoRepository.FollowRelationDtoList;
import com.go2wheel.util.QuerySpecUtil;
import com.go2wheel.util.QuerySpecUtil.RelationQuery;

import io.katharsis.queryspec.QuerySpec;

@Component
public class FollowRelationDtoRepositoryImpl  extends DtoRepositoryBase<FollowRelationDto, FollowRelationDtoList, FollowRelation, FollowRelationFacadeRepository> implements FollowRelationDtoRepository {
	
	@Autowired
	private BootUserFacadeRepository userRepo;
	
	@Autowired
	public FollowRelationDtoRepositoryImpl(FollowRelationFacadeRepository repository, FollowRelationDtoConverter converter) {
		super(FollowRelationDto.class, FollowRelationDtoList.class, FollowRelation.class, repository, converter);
	}

	@Override
	protected FollowRelationDtoList findAllWithQuerySpec(QuerySpec querySpec) {
		return null;
	}

	@Override
	protected List<String> checkAllSortableFieldAllowed(QuerySpec querySpec) {
		return null;
	}

	@Override
	protected FollowRelationDtoList findWithRelationAndSpec(RelationQuery rq, QuerySpec querySpec) {
		if ("befollowed".equals(rq.getRelationName())) {
			UserDto udto = new UserDto(rq.getRelationIds().get(0));
			BootUser user = userRepo.findOne(udto.getId(), true);
			Page<FollowRelation> follow2me = getRepository().findByFollowed(user, QuerySpecUtil.getPageFacade(querySpec));
			return convertToResourceList(follow2me.getContent(), follow2me.getTotalResourceCount(), Scenario.RELATION_LIST);
		} else if ("follower".equals(rq.getRelationName())) {
			UserDto udto = new UserDto(rq.getRelationIds().get(0));
			BootUser user = userRepo.findOne(udto.getId(), true);
			Page<FollowRelation> follow2me = getRepository().findByFollower(user, QuerySpecUtil.getPageFacade(querySpec));
			return convertToResourceList(follow2me.getContent(), follow2me.getTotalResourceCount(), Scenario.RELATION_LIST);
		}
		return null;
	}
}
