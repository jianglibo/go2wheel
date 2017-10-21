package com.go2wheel.katharsis.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.go2wheel.facade.ManufacturerFacadeRepository;
import com.go2wheel.facade.MtSeriesFacadeRepository;
import com.go2wheel.katharsis.dto.ManufacturerDto;
import com.go2wheel.katharsis.dto.MtSeriesDto;

@Component
public class MtSeriesToManufacturerRepositoryImpl extends RelationshipRepositoryBaseMine<MtSeriesDto, ManufacturerDto> {
	
	@Autowired
	private MtSeriesFacadeRepository mtSeriesRepo;
	
	@Autowired
	private ManufacturerFacadeRepository manufacturerRepo;

	protected MtSeriesToManufacturerRepositoryImpl() {
		super(MtSeriesDto.class, ManufacturerDto.class);
	}

}
