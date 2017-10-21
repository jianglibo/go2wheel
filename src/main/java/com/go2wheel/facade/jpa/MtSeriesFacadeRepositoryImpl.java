package com.go2wheel.facade.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.go2wheel.constant.PreAuthorizeExpression;
import com.go2wheel.domain.Manufacturer;
import com.go2wheel.domain.MtSeries;
import com.go2wheel.facade.Page;
import com.go2wheel.facade.PageFacade;
import com.go2wheel.facade.MtSeriesFacadeRepository;
import com.go2wheel.katharsis.dto.MtSeriesDto;
import com.go2wheel.repository.ManufacturerRepository;
import com.go2wheel.repository.MtSeriesRepository;
import com.go2wheel.util.PropertyCopyUtil;

/**
 * @author jianglibo@gmail.com
 *
 */
@Component
public class MtSeriesFacadeRepositoryImpl extends FacadeRepositoryBaseImpl<MtSeries,MtSeriesDto, MtSeriesRepository> implements MtSeriesFacadeRepository {
	
	@Autowired
	private ManufacturerRepository manufacturerRepository;
	
	public MtSeriesFacadeRepositoryImpl(MtSeriesRepository jpaRepo) {
		super(jpaRepo);
	}
	
	@Override
	@PreAuthorize(PreAuthorizeExpression.IS_FULLY_AUTHENTICATED)
	public MtSeries save(MtSeries entity, MtSeriesDto dto) {
		return super.save(entity, dto);
	}
	
	@Override
	public MtSeries newByDto(MtSeriesDto dto) {
		MtSeries entity = new MtSeries();
		PropertyCopyUtil.copyPropertyWhenCreate(entity, dto);
		if (dto.getManufacturer() != null) {
			entity.setManufacturer(manufacturerRepository.findOne(dto.getManufacturer().getId()));
		}
		return entity;
	}

	@Override
	public Page<MtSeries> findByNameContaining(String name, PageFacade pageFacade) {
		org.springframework.data.domain.Page<MtSeries> opage = getRepository().findByNameContaining(name, new SimplePageable(pageFacade));
		return new Page<>(opage.getTotalElements(), opage.getContent());
	}

	@Override
	public Page<MtSeries> findByManufacturer(Manufacturer mf, PageFacade pf) {
		org.springframework.data.domain.Page<MtSeries> opage = getRepository().findByManufacturer(mf, new SimplePageable(pf));
		return new Page<>(opage.getTotalElements(), opage.getContent());
	}
}
