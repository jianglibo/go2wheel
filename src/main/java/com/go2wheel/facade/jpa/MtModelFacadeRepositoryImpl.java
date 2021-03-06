package com.go2wheel.facade.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.go2wheel.constant.PreAuthorizeExpression;
import com.go2wheel.domain.MtModel;
import com.go2wheel.domain.MtSeries;
import com.go2wheel.facade.Page;
import com.go2wheel.facade.PageFacade;
import com.go2wheel.facade.MtModelFacadeRepository;
import com.go2wheel.facade.MtSeriesFacadeRepository;
import com.go2wheel.katharsis.dto.MtModelDto;
import com.go2wheel.repository.MtModelRepository;

/**
 * @author jianglibo@gmail.com
 *
 */
@Component
public class MtModelFacadeRepositoryImpl extends FacadeRepositoryBaseImpl<MtModel,MtModelDto, MtModelRepository> implements MtModelFacadeRepository {
	
	@Autowired
	private MtSeriesFacadeRepository mtSeriesRepository;
	
	public MtModelFacadeRepositoryImpl(MtModelRepository jpaRepo) {
		super(jpaRepo);
	}
	
	@Override
	@PreAuthorize(PreAuthorizeExpression.IS_FULLY_AUTHENTICATED)
	public MtModel save(MtModel entity, MtModelDto dto) {
		return super.save(entity, dto);
	}
	
	@Override
	public MtModel newByDto(MtModelDto dto) {
		MtModel entity = new MtModel();
		getPropertyCopyUtil().copyPropertyWhenCreate(entity, dto);
		if (dto.getMtSeries() != null) {
			entity.setMtSeries(mtSeriesRepository.findOne(dto.getMtSeries().getId(), true));
		}
		return entity;
	}

	@Override
	public Page<MtModel> findByNameContaining(String name, PageFacade pageFacade) {
		org.springframework.data.domain.Page<MtModel> opage = getRepository().findByNameContaining(name, new SimplePageable(pageFacade));
		return new Page<>(opage.getTotalElements(), opage.getContent());
	}

	@Override
	public Page<MtModel> findByMtSeries(MtSeries ms, PageFacade pageFacade) {
		org.springframework.data.domain.Page<MtModel> opage = getRepository().findByMtSeries(ms, new SimplePageable(pageFacade));
		return new Page<>(opage.getTotalElements(), opage.getContent());
	}
}
