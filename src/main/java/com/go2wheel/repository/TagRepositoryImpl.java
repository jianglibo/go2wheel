package com.go2wheel.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.go2wheel.domain.Tag;

/**
 * To handle some conditions, not all conditions.
 * @author jianglibo@gmail.com
 *
 */
public class TagRepositoryImpl extends SimpleJpaRepositoryBase<Tag> implements RepositoryBase<Tag> {

	@Autowired
	public TagRepositoryImpl(EntityManager entityManager) {
		super(Tag.class, entityManager);
	}

}
