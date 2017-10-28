package com.go2wheel.facade;

import com.go2wheel.domain.BootGroup;
import com.go2wheel.domain.BootUser;
import com.go2wheel.katharsis.dto.UserDto;

public interface BootUserFacadeRepository extends FacadeRepositoryBase<BootUser, UserDto> {

	BootUser findByEmail(String emailOrMobile);

	BootUser findByMobile(String emailOrMobile);

	BootUser findByName(String emailOrMobile);
	
	BootUser updatePassword(BootUser user, String encodedPassword);
	
	BootUser patch(BootUser user, UserDto userDto);
	
	BootUser findByOpenId(String openId);
	
	Page<BootUser> findAllByGroup(BootGroup group, PageFacade pf);

}
