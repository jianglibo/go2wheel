package com.go2wheel.katharsis.repository;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.BootGroup;
import com.go2wheel.domain.BootUser;
import com.go2wheel.domain.GroupUserRelation;
import com.go2wheel.facade.BootGroupFacadeRepository;
import com.go2wheel.facade.BootUserFacadeRepository;
import com.go2wheel.facade.GroupUserRelationFacadeRepository;
import com.go2wheel.facade.Page;
import com.go2wheel.katharsis.dto.GroupDto;
import com.go2wheel.katharsis.dto.UserDto;
import com.go2wheel.katharsis.dto.converter.DtoConverter.Scenario;
import com.go2wheel.katharsis.dto.converter.GroupDtoConverter;
import com.go2wheel.katharsis.repository.GroupDtoRepository.GroupDtoList;
import com.go2wheel.util.QuerySpecUtil;
import com.go2wheel.util.QuerySpecUtil.RelationQuery;

import io.katharsis.queryspec.QuerySpec;

@Component
public class GroupDtoRepositoryImpl  extends DtoRepositoryBase<GroupDto, GroupDtoList, BootGroup, BootGroupFacadeRepository> implements GroupDtoRepository {
	
	@Autowired
	private BootUserFacadeRepository userRepo;
	
	@Autowired
	private GroupUserRelationFacadeRepository guRepo;
	
	@Autowired
	public GroupDtoRepositoryImpl(BootGroupFacadeRepository repository, GroupDtoConverter converter) {
		super(GroupDto.class, GroupDtoList.class, BootGroup.class, repository, converter);
	}

	@Override
	protected GroupDtoList findAllWithQuerySpec(QuerySpec querySpec) {
		Page<BootGroup> groups = getRepository().findAll(QuerySpecUtil.getPageFacade(querySpec)); 
		return convertToResourceList(groups, Scenario.FIND_LIST);
	}

	@Override
	protected List<String> checkAllSortableFieldAllowed(QuerySpec querySpec) {
		return null;
	}

	@Override
	protected GroupDtoList findWithRelationAndSpec(RelationQuery rq, QuerySpec querySpec) {
		if ("members".equals(rq.getRelationName())) {
			UserDto udo = new UserDto(rq.getRelationIds().get(0));
			BootUser bu = userRepo.findOne(udo.getId(), true);
			Page<GroupUserRelation> gurs = guRepo.findByBootUser(bu, QuerySpecUtil.getPageFacade(querySpec));
			List<BootGroup> gps = gurs.getContent().stream().map(gur -> gur.getBootGroup()).collect(Collectors.toList());
			GroupDtoList gl = convertToResourceList(gps, gurs.getTotalResourceCount(), Scenario.RELATION_LIST);
			gl.forEach(gdto -> gdto.getMembers().add(udo));
			return gl;
		}
		return null;
	}
}
