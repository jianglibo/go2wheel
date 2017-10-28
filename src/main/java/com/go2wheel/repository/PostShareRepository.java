package com.go2wheel.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.go2wheel.domain.BootUser;
import com.go2wheel.domain.Post;
import com.go2wheel.domain.PostShare;

public interface PostShareRepository extends RepositoryBase<PostShare> {
	Page<PostShare> findAllByBootUser(BootUser user, Pageable pageable);
	
	Page<PostShare> findAllByPost(Post post, Pageable pageable);
	
	PostShare findByPostAndBootUser(Post post, BootUser user);
	
	List<PostShare> findAllByBootUser(BootUser user);

}
