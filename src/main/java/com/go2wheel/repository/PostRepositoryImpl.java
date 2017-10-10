package com.go2wheel.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.go2wheel.domain.Post;

/**
 * To handle some conditions, not all conditions.
 * @author jianglibo@gmail.com
 *
 */
public class PostRepositoryImpl extends SimpleJpaRepositoryBase<Post> implements RepositoryBase<Post> {

	@Autowired
	public PostRepositoryImpl(EntityManager entityManager) {
		super(Post.class, entityManager);
	}

}
