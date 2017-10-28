package com.go2wheel.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.go2wheel.domain.PostShare;

/**
 * To handle some conditions, not all conditions.
 * @author jianglibo@gmail.com
 *
 */
public class PostShareRepositoryImpl extends SimpleJpaRepositoryBase<PostShare> implements RepositoryBase<PostShare> {

	@Autowired
	public PostShareRepositoryImpl(EntityManager entityManager) {
		super(PostShare.class, entityManager);
	}

}
