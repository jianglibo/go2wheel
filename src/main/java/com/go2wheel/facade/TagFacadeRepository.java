package com.go2wheel.facade;


import com.go2wheel.domain.Tag;
import com.go2wheel.katharsis.dto.TagDto;


public interface TagFacadeRepository extends FacadeRepositoryBase<Tag, TagDto> {
	
	Page<Tag> findByNameContaining(String name, PageFacade pf);
}
