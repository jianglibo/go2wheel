package com.go2wheel.facade;

import com.go2wheel.domain.BootGroup;
import com.go2wheel.katharsis.dto.GroupDto;


public interface BootGroupFacadeRepository extends FacadeRepositoryBase<BootGroup, GroupDto> {
    BootGroup findByName(String rn);
    BootGroup initSave(BootGroup entity);
}
