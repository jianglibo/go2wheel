package com.go2wheel.facade.jpa;

import org.springframework.beans.factory.annotation.Autowired;

import com.go2wheel.domain.BaseEntity;
import com.go2wheel.facade.FacadeRepositoryBase;
import com.go2wheel.facade.Page;
import com.go2wheel.facade.PageFacade;
import com.go2wheel.facade.SortBroker;
import com.go2wheel.katharsis.dto.Dto;
import com.go2wheel.repository.RepositoryBase;
import com.go2wheel.util.PropertyCopyUtil;

public abstract class FacadeRepositoryBaseImpl<T extends BaseEntity, D extends Dto, R extends RepositoryBase<T>> implements FacadeRepositoryBase<T, D> {

	private final R jpaRepo;
	
	@Autowired
	private PropertyCopyUtil propertyCopyUtil;
	
	public FacadeRepositoryBaseImpl(R jpaRepo) {
		this.jpaRepo = jpaRepo;
	}
	
	@Override
	public Page<T> findAll(PageFacade pf) {
		org.springframework.data.domain.Page<T> opage = jpaRepo.findAll(new SimplePageable(pf));
		return new Page<>(opage.getTotalElements(), opage.getContent());
	}
	
	protected SimplePageable getSimplePageable(long offset, long limit,SortBroker...sortFields) {
		return new SimplePageable(offset, limit, sortFields);
	}
	
	@Override
	public long count() {
		return jpaRepo.count();
	}

	@Override
	public T save(T entity, D dto) {
		return jpaRepo.save(entity);
	}

	@Override
	public void delete(T entity) {
		jpaRepo.delete(entity);
	}

	@Override
	public T findOne(Long id, boolean internalCall) {
		return jpaRepo.findOne(id);
	}
	
	R getRepository() {
		return jpaRepo;
	}

	public PropertyCopyUtil getPropertyCopyUtil() {
		return propertyCopyUtil;
	}

}
