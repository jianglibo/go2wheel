package com.go2wheel.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.go2wheel.facade.RoleFacadeRepository;
import com.go2wheel.katharsis.dto.UserDto;

@Component
public class BootUserFactory {
	
	@Autowired
	private RoleFacadeRepository roleRepository;
	
	public BootUserBuilder getBootUserBuilder(String name, String email, String mobile, String openId) {
		return new BootUserBuilder(roleRepository, name, email, mobile, openId);
	}
	
	public BootUserBuilder getBootUserBuilder(UserDto userDto) {
		return new BootUserBuilder(roleRepository, userDto);
	}

}
