package com.go2wheel.facade;


import com.go2wheel.domain.Manufacturer;
import com.go2wheel.domain.MtSeries;
import com.go2wheel.katharsis.dto.MtSeriesDto;


public interface MtSeriesFacadeRepository extends FacadeRepositoryBase<MtSeries, MtSeriesDto> {
	Page<MtSeries> findByNameContaining(String name, PageFacade pf);
	
	Page<MtSeries> findByManufacturer(Manufacturer mf, PageFacade pf);
}
