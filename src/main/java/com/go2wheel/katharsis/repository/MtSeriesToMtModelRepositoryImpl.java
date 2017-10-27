package com.go2wheel.katharsis.repository;

import org.springframework.stereotype.Component;

import com.go2wheel.katharsis.dto.MtModelDto;
import com.go2wheel.katharsis.dto.MtSeriesDto;

@Component
public class MtSeriesToMtModelRepositoryImpl extends RelationshipRepositoryBaseMine<MtSeriesDto, MtModelDto> {

	protected MtSeriesToMtModelRepositoryImpl() {
		super(MtSeriesDto.class, MtModelDto.class);
	}

}
