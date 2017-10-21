package com.go2wheel.facade;


import com.go2wheel.domain.MtModel;
import com.go2wheel.katharsis.dto.MtModelDto;


public interface MtModelFacadeRepository extends FacadeRepositoryBase<MtModel, MtModelDto> {
	
	Page<MtModel> findByNameContaining(String name, PageFacade pf);
}
