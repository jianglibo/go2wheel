package com.go2wheel.facade.jpa;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.go2wheel.constant.PreAuthorizeExpression;
import com.go2wheel.domain.Role;
import com.go2wheel.facade.RoleFacadeRepository;
import com.go2wheel.katharsis.dto.RoleDto;
import com.go2wheel.repository.RoleRepository;

/**
 * @author jianglibo@gmail.com
 *
 */
@Component
public class RoleFacadeRepositoryImpl extends FacadeRepositoryBaseImpl<Role,RoleDto, RoleRepository> implements RoleFacadeRepository {
	
	public RoleFacadeRepositoryImpl(RoleRepository jpaRepo) {
		super(jpaRepo);
	}
	
	@Override
	@PreAuthorize(PreAuthorizeExpression.HAS_ADMINISTRATOR_ROLE)
	public Role save(Role entity, RoleDto dto) {
		return super.save(entity, dto);
	}
	

	@Override
	public Role findByName(String rn) {
		if (!(rn.startsWith("role_") || rn.startsWith("ROLE_"))) {
			rn = ("role_" + rn).toUpperCase();
		}
		return getRepository().findByName(rn);
	}

	@Override
	public Role initSave(Role entity) {
		return super.save(entity, null);
	}

	@Override
	public List<Role> findAll() {
		return getRepository().findAll();
	}

	@Override
	public Role newByDto(RoleDto dto) {
		Role entity = new Role(dto.getName());
		return entity;
	}
}
