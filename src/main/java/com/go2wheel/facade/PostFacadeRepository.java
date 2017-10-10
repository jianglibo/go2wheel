package com.go2wheel.facade;


import com.go2wheel.domain.BootUser;
import com.go2wheel.domain.Post;
import com.go2wheel.katharsis.dto.PostDto;


public interface PostFacadeRepository extends FacadeRepositoryBase<Post, PostDto> {
	Page<Post> findMine(BootUser user, PageFacade pf);
	void saveSharePost(final Post fe, BootUser user);
	Page<Post> findAllByToAll(boolean toAll, PageFacade pageFacade);
}
