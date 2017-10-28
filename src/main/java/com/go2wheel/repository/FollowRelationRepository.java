package com.go2wheel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.go2wheel.domain.BootUser;
import com.go2wheel.domain.FollowRelation;

public interface FollowRelationRepository extends RepositoryBase<FollowRelation> {

	Page<FollowRelation> findAllByFollowed(BootUser followTarget, Pageable pageable);
	
	long countByFollowed(BootUser followTarget);
	
	Page<FollowRelation> findAllByFollower(BootUser followTarget, Pageable pageable);
	
	long countByFollower(BootUser followTarget);

	FollowRelation findByFollowedAndFollower(BootUser befollowed, BootUser follower);
}
