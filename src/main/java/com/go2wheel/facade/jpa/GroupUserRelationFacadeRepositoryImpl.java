package com.go2wheel.facade.jpa;

import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.go2wheel.constant.PreAuthorizeExpression;
import com.go2wheel.domain.BootGroup;
import com.go2wheel.domain.BootUser;
import com.go2wheel.domain.GroupUserRelation;
import com.go2wheel.facade.GroupUserRelationFacadeRepository;
import com.go2wheel.facade.Page;
import com.go2wheel.facade.PageFacade;
import com.go2wheel.katharsis.dto.GroupUserRelationDto;
import com.go2wheel.repository.GroupUserRelationRepository;

/**
 * @author jianglibo@gmail.com
 *
 */
@Component
public class GroupUserRelationFacadeRepositoryImpl extends FacadeRepositoryBaseImpl<GroupUserRelation,GroupUserRelationDto, GroupUserRelationRepository> implements GroupUserRelationFacadeRepository {
	
	public GroupUserRelationFacadeRepositoryImpl(GroupUserRelationRepository jpaRepo) {
		super(jpaRepo);
	}
	
	@Override
	@PreAuthorize(PreAuthorizeExpression.IS_FULLY_AUTHENTICATED)
	public GroupUserRelation save(GroupUserRelation entity, GroupUserRelationDto dto) {
		return super.save(entity, dto);
	}

	@Override
	public GroupUserRelation newByDto(GroupUserRelationDto dto) {
		return null;
	}


	@Override
	public Page<GroupUserRelation> findByBootGroup(@P("entity") BootGroup group, PageFacade pf) {
		org.springframework.data.domain.Page<GroupUserRelation> opage = getRepository().findAllByBootGroup(group, new SimplePageable(pf)); 
		return new Page<>(opage.getTotalElements(), opage.getContent());
	}

	@Override
	@PreAuthorize("hasRole('ADMINISTRATOR') or (#entity.id == principal.id)")
	public Page<GroupUserRelation> findByBootUser(@P("entity")BootUser user, PageFacade pf) {
		org.springframework.data.domain.Page<GroupUserRelation> opage = getRepository().findAllByBootUser(user, new SimplePageable(pf)); 
		return new Page<>(opage.getTotalElements(), opage.getContent());
	}

	@Override
	public GroupUserRelation findByBootGroupAndBootUser(BootGroup group, BootUser user) {
		return getRepository().findByBootGroupAndBootUser(group, user);
	}

	@Override
	@PreAuthorize("hasRole('ADMINISTRATOR') or (#entity.creator.id == principal.id)")
	public void deleteByBootGroupAndBootUser(@P("entity")BootGroup group, BootUser user) {
		GroupUserRelation gu = findByBootGroupAndBootUser(group, user);
		delete(gu);
	}

	@Override
	@PreAuthorize("hasRole('ADMINISTRATOR') or (#entity.creator.id == principal.id)")
	public void addRelation(@P("entity")BootGroup group, BootUser user) {
		GroupUserRelation gu = new GroupUserRelation(group, user);
		save(gu, null);
	}
}
