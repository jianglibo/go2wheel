package com.go2wheel.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.go2wheel.domain.Role;

/**
 * To handle some conditions, not all conditions.
 * @author jianglibo@gmail.com
 *
 */
public class RoleRepositoryImpl extends SimpleJpaRepositoryBase<Role> implements RepositoryBase<Role> {

	@Autowired
	public RoleRepositoryImpl(EntityManager entityManager) {
		super(Role.class, entityManager);
	}

}
