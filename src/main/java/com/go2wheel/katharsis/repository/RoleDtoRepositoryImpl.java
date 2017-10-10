package com.go2wheel.katharsis.repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.BootUser;
import com.go2wheel.domain.Role;
import com.go2wheel.facade.BootUserFacadeRepository;
import com.go2wheel.facade.RoleFacadeRepository;
import com.go2wheel.katharsis.dto.RoleDto;
import com.go2wheel.katharsis.dto.converter.DtoConverter.Scenario;
import com.go2wheel.katharsis.dto.converter.RoleDtoConverter;
import com.go2wheel.katharsis.repository.RoleDtoRepository.RoleDtoList;
import com.go2wheel.util.QuerySpecUtil.RelationQuery;

import io.katharsis.queryspec.QuerySpec;

@Component
public class RoleDtoRepositoryImpl  extends DtoRepositoryBase<RoleDto, RoleDtoList, Role, RoleFacadeRepository> implements RoleDtoRepository {
	
	@Autowired
	private BootUserFacadeRepository userRepo;
	
	@Autowired
	public RoleDtoRepositoryImpl(RoleFacadeRepository repository, RoleDtoConverter converter) {
		super(RoleDto.class, RoleDtoList.class, Role.class, repository, converter);
	}

	@Override
	protected RoleDtoList findAllWithQuerySpec(QuerySpec querySpec) {
		return null;
	}

	@Override
	protected List<String> checkAllSortableFieldAllowed(QuerySpec querySpec) {
		return null;
	}

	@Override
	protected RoleDtoList findWithRelationAndSpec(RelationQuery rq, QuerySpec querySpec) {
		if ("users".equals(rq.getRelationName())) {
			BootUser bu = userRepo.findOne(rq.getRelationIds().get(0), true);
			Set<Role> roles = bu.getRoles();
			return convertToResourceList(new ArrayList<>(roles), roles.size(), Scenario.FIND_LIST);
		}
		return null;
	}
}
