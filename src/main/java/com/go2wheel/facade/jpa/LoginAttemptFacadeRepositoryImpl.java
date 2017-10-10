package com.go2wheel.facade.jpa;

import org.springframework.stereotype.Component;

import com.go2wheel.domain.LoginAttempt;
import com.go2wheel.facade.LoginAttemptFacadeRepository;
import com.go2wheel.katharsis.dto.LoginAttemptDto;
import com.go2wheel.repository.LoginAttemptRepository;

@Component
public class LoginAttemptFacadeRepositoryImpl extends FacadeRepositoryBaseImpl<LoginAttempt, LoginAttemptDto, LoginAttemptRepository> implements  LoginAttemptFacadeRepository {

	public LoginAttemptFacadeRepositoryImpl(LoginAttemptRepository jpaRepo) {
		super(jpaRepo);
	}

	@Override
	public LoginAttempt newByDto(LoginAttemptDto dto) {
		return null;
	}

}
