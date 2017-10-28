package com.go2wheel.facade.jpa;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.go2wheel.constant.PreAuthorizeExpression;
import com.go2wheel.domain.Medium;
import com.go2wheel.facade.MediumFacadeRepository;
import com.go2wheel.katharsis.dto.MediumDto;
import com.go2wheel.repository.MediumRepository;

/**
 * @author jianglibo@gmail.com
 *
 */
@Component
public class MediumFacadeRepositoryImpl extends FacadeRepositoryBaseImpl<Medium,MediumDto, MediumRepository> implements MediumFacadeRepository {
	
	public MediumFacadeRepositoryImpl(MediumRepository jpaRepo) {
		super(jpaRepo);
	}
	
	@Override
	@PreAuthorize(PreAuthorizeExpression.IS_FULLY_AUTHENTICATED)
	public Medium save(Medium entity, MediumDto dto) {
		return super.save(entity, dto);
	}

	@Override
	public Medium newByDto(MediumDto dto) {
		Medium entity = new Medium();
		return entity;
	}
}
