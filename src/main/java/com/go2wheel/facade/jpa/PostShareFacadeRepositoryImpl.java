package com.go2wheel.facade.jpa;

import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.go2wheel.constant.PreAuthorizeExpression;
import com.go2wheel.domain.BootUser;
import com.go2wheel.domain.Post;
import com.go2wheel.domain.PostShare;
import com.go2wheel.facade.Page;
import com.go2wheel.facade.PageFacade;
import com.go2wheel.facade.PostShareFacadeRepository;
import com.go2wheel.katharsis.dto.PostShareDto;
import com.go2wheel.repository.PostShareRepository;

/**
 * @author jianglibo@gmail.com
 *
 */
@Component
public class PostShareFacadeRepositoryImpl extends FacadeRepositoryBaseImpl<PostShare,PostShareDto, PostShareRepository> implements PostShareFacadeRepository {
	
	public PostShareFacadeRepositoryImpl(PostShareRepository jpaRepo) {
		super(jpaRepo);
	}
	
	@Override
	@PreAuthorize(PreAuthorizeExpression.IS_FULLY_AUTHENTICATED)
	public PostShare save(PostShare entity, PostShareDto dto) {
		return super.save(entity, dto);
	}

	@Override
	public PostShare newByDto(PostShareDto dto) {
		return null;
	}

	@Override
	@PreAuthorize(PreAuthorizeExpression.IS_FULLY_AUTHENTICATED + " and (#entity.id == principal.id)")
	public Page<PostShare> findByBootUser(@P("entity")BootUser user, PageFacade pf) {
		org.springframework.data.domain.Page<PostShare> opage = getRepository().findAllByBootUser(user, new SimplePageable(pf));
		return new Page<>(opage.getTotalElements(), opage.getContent());
	}

	@Override
	public Page<PostShare> findByPost(Post post, PageFacade pf) {
		org.springframework.data.domain.Page<PostShare> opage = getRepository().findAllByPost(post, new SimplePageable(pf));
		return new Page<>(opage.getTotalElements(), opage.getContent()); 
	}

	@Override
	public PostShare findByPostAndBootUser(Post post, BootUser user) {
		return getRepository().findByPostAndBootUser(post, user);
	}
}
