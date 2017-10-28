package com.go2wheel.facade;


import com.go2wheel.domain.BootUser;
import com.go2wheel.domain.Approve;
import com.go2wheel.katharsis.dto.ApproveDto;


public interface ApproveFacadeRepository extends FacadeRepositoryBase<Approve, ApproveDto> {
	
	Page<Approve> findSent(BootUser user, PageFacade pf);
	
	Page<Approve> findReceived(BootUser user, PageFacade pf);
}
