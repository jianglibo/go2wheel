package com.go2wheel.facade;

import com.go2wheel.domain.BootGroup;
import com.go2wheel.domain.BootUser;
import com.go2wheel.domain.GroupUserRelation;
import com.go2wheel.katharsis.dto.GroupUserRelationDto;


public interface GroupUserRelationFacadeRepository extends FacadeRepositoryBase<GroupUserRelation, GroupUserRelationDto> {
	
	Page<GroupUserRelation> findByBootGroup(BootGroup group, PageFacade pf);
	
	Page<GroupUserRelation> findByBootUser(BootUser user, PageFacade pf);

	GroupUserRelation findByBootGroupAndBootUser(BootGroup group, BootUser user);
	
	void deleteByBootGroupAndBootUser(BootGroup group, BootUser user);

	void addRelation(BootGroup group, BootUser user);
	
}
