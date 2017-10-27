package com.go2wheel.katharsis.repository;

import org.springframework.stereotype.Component;

import com.go2wheel.katharsis.dto.MtModelDto;
import com.go2wheel.katharsis.dto.MtSeriesDto;

@Component
public class MtModelToMtSeriesRepositoryImpl extends RelationshipRepositoryBaseMine<MtModelDto, MtSeriesDto> {

	protected MtModelToMtSeriesRepositoryImpl() {
		super(MtModelDto.class, MtSeriesDto.class);
	}
}
