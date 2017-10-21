package com.go2wheel.facade;


import com.go2wheel.domain.Manufacturer;
import com.go2wheel.katharsis.dto.ManufacturerDto;


public interface ManufacturerFacadeRepository extends FacadeRepositoryBase<Manufacturer, ManufacturerDto> {
	Page<Manufacturer> findByNameContaining(String name, PageFacade pf);
}
