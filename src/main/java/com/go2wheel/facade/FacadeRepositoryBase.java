package com.go2wheel.facade;

import com.go2wheel.domain.BaseEntity;
import com.go2wheel.katharsis.dto.Dto;

public interface FacadeRepositoryBase<E extends BaseEntity, D extends Dto> {
	
	Page<E> findAll(PageFacade pf);
	
	long count();
	
	E save(E entity, D dto);
	
	E save(E entity);
	
	void delete(E entity);
	
	E newByDto(D dto);

	E findOne(Long id, boolean internalCall);

}
