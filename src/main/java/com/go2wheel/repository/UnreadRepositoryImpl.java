package com.go2wheel.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.go2wheel.domain.Unread;

/**
 * To handle some conditions, not all conditions.
 * @author jianglibo@gmail.com
 *
 */
public class UnreadRepositoryImpl extends SimpleJpaRepositoryBase<Unread> implements RepositoryBase<Unread> {

	@Autowired
	public UnreadRepositoryImpl(EntityManager entityManager) {
		super(Unread.class, entityManager);
	}

}
