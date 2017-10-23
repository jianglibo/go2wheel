package com.go2wheel.facade.jpa;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.go2wheel.constant.PreAuthorizeExpression;
import com.go2wheel.domain.Tag;
import com.go2wheel.facade.Page;
import com.go2wheel.facade.PageFacade;
import com.go2wheel.facade.TagFacadeRepository;
import com.go2wheel.katharsis.dto.TagDto;
import com.go2wheel.repository.TagRepository;

/**
 * @author jianglibo@gmail.com
 *
 */
@Component
public class TagFacadeRepositoryImpl extends FacadeRepositoryBaseImpl<Tag,TagDto, TagRepository> implements TagFacadeRepository {
	
	public TagFacadeRepositoryImpl(TagRepository jpaRepo) {
		super(jpaRepo);
	}
	
	@Override
	@PreAuthorize(PreAuthorizeExpression.IS_FULLY_AUTHENTICATED)
	public Tag save(Tag entity, TagDto dto) {
		return super.save(entity, dto);
	}
	
	@Override
	public Tag newByDto(TagDto dto) {
		Tag entity = new Tag();
		getPropertyCopyUtil().copyPropertyWhenCreate(entity, dto);
		return entity;
	}

	@Override
	public Page<Tag> findByNameContaining(String name, PageFacade pageFacade) {
		org.springframework.data.domain.Page<Tag> opage = getRepository().findByNameContaining(name, new SimplePageable(pageFacade));
		return new Page<>(opage.getTotalElements(), opage.getContent());
	}
}
