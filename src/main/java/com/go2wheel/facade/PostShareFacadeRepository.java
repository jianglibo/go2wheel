package com.go2wheel.facade;

import com.go2wheel.domain.BootUser;
import com.go2wheel.domain.Post;
import com.go2wheel.domain.PostShare;
import com.go2wheel.katharsis.dto.PostShareDto;


public interface PostShareFacadeRepository extends FacadeRepositoryBase<PostShare, PostShareDto> {
	
	
	/**
	 * find by follower mean the follower is me. So the result is all I followed.
	 */
	Page<PostShare> findByBootUser(BootUser user, PageFacade pf);
	
	Page<PostShare> findByPost(Post post, PageFacade pf);

	PostShare findByPostAndBootUser(Post post, BootUser findOne);
	
}
