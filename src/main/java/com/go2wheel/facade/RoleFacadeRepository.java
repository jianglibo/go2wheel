package com.go2wheel.facade;

import java.util.List;

import com.go2wheel.domain.Role;
import com.go2wheel.katharsis.dto.RoleDto;


public interface RoleFacadeRepository extends FacadeRepositoryBase<Role, RoleDto> {
	
    Role findByName(String rn);
    Role initSave(Role entity);
    List<Role> findAll();
    
}
