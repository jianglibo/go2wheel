package com.go2wheel.repository;

import org.springframework.data.domain.Pageable;

import com.go2wheel.domain.Post;

public interface PostRepository extends RepositoryBase<Post> {

	org.springframework.data.domain.Page<Post> findAllByToAll(boolean toAll, Pageable pageable);

}
