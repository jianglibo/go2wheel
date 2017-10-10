package com.go2wheel.facade;

import com.go2wheel.domain.BootUser;
import com.go2wheel.domain.FollowRelation;
import com.go2wheel.katharsis.dto.FollowRelationDto;


public interface FollowRelationFacadeRepository extends FacadeRepositoryBase<FollowRelation, FollowRelationDto> {
	
	/**
	 * find by followed mean the follow target is me. So the result is all I follower.
	 */
	Page<FollowRelation> findByFollowed(BootUser user, PageFacade pf);
	
	
	/**
	 * find by follower mean the follower is me. So the result is all I followed.
	 */
	Page<FollowRelation> findByFollower(BootUser user, PageFacade pf);
	
	FollowRelation findByFollowedAndFollower(BootUser befollowed, BootUser follower);
	
	
}
