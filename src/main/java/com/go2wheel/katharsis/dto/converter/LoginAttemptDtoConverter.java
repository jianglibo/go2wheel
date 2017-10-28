package com.go2wheel.katharsis.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.LoginAttempt;
import com.go2wheel.jwt.JwtUtil;
import com.go2wheel.katharsis.dto.LoginAttemptDto;
import com.go2wheel.vo.BootUserPrincipal;

@Component
public class LoginAttemptDtoConverter extends DtoConverterBase<LoginAttempt, LoginAttemptDto> {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	public LoginAttemptDto newDto(LoginAttemptDto dto, BootUserPrincipal pricipal, LoginAttempt loginAttemp) {
		dto.setId(loginAttemp.getId());
		dto.setSuccess(true);
		dto.setPassword("");
		dto.setJwtToken(jwtUtil.issuePrincipalToken(pricipal));
		dto.setUser(pricipal.getId());
		return dto;
	}

	@Override
	protected LoginAttemptDto afterPropertyCopy(LoginAttempt entity, LoginAttemptDto dto, Scenario scenario) {
		return null;
	}
}
