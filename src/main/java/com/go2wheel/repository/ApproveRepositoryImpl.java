package com.go2wheel.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.go2wheel.domain.Approve;

/**
 * To handle some conditions, not all conditions.
 * @author jianglibo@gmail.com
 *
 */
public class ApproveRepositoryImpl extends SimpleJpaRepositoryBase<Approve> implements RepositoryBase<Approve> {

	@Autowired
	public ApproveRepositoryImpl(EntityManager entityManager) {
		super(Approve.class, entityManager);
	}

}
