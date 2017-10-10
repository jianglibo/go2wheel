package com.go2wheel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.go2wheel.domain.BootGroup;
import com.go2wheel.domain.BootUser;
import com.go2wheel.domain.GroupUserRelation;

public interface GroupUserRelationRepository extends RepositoryBase<GroupUserRelation> {

	Page<GroupUserRelation> findAllByBootUser(BootUser user, Pageable pageable);
	
	Page<GroupUserRelation> findAllByBootGroup(BootGroup group, Pageable pageable);
	
	GroupUserRelation findByBootGroupAndBootUser(BootGroup group, BootUser user);

}
