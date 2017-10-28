package com.go2wheel.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.go2wheel.domain.GroupUserRelation;

/**
 * To handle some conditions, not all conditions.
 * @author jianglibo@gmail.com
 *
 */
public class GroupUserRelationRepositoryImpl extends SimpleJpaRepositoryBase<GroupUserRelation> implements RepositoryBase<GroupUserRelation> {

	@Autowired
	public GroupUserRelationRepositoryImpl(EntityManager entityManager) {
		super(GroupUserRelation.class, entityManager);
	}

}
