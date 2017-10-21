package com.go2wheel.facade.jpa;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.go2wheel.constant.PreAuthorizeExpression;
import com.go2wheel.domain.Manufacturer;
import com.go2wheel.facade.Page;
import com.go2wheel.facade.PageFacade;
import com.go2wheel.facade.ManufacturerFacadeRepository;
import com.go2wheel.katharsis.dto.ManufacturerDto;
import com.go2wheel.repository.ManufacturerRepository;
import com.go2wheel.util.PropertyCopyUtil;

/**
 * @author jianglibo@gmail.com
 *
 */
@Component
public class ManufacturerFacadeRepositoryImpl extends FacadeRepositoryBaseImpl<Manufacturer,ManufacturerDto, ManufacturerRepository> implements ManufacturerFacadeRepository {
	
	public ManufacturerFacadeRepositoryImpl(ManufacturerRepository jpaRepo) {
		super(jpaRepo);
	}
	
	@Override
	@PreAuthorize(PreAuthorizeExpression.IS_FULLY_AUTHENTICATED)
	public Manufacturer save(Manufacturer entity, ManufacturerDto dto) {
		return super.save(entity, dto);
	}
	
	@Override
	public Manufacturer newByDto(ManufacturerDto dto) {
		Manufacturer entity = new Manufacturer();
		PropertyCopyUtil.copyPropertyWhenCreate(entity, dto);
		return entity;
	}

	@Override
	public Page<Manufacturer> findByNameContaining(String name, PageFacade pageFacade) {
		org.springframework.data.domain.Page<Manufacturer> opage = getRepository().findByNameContaining(name, new SimplePageable(pageFacade));
		return new Page<>(opage.getTotalElements(), opage.getContent());
	}
}
