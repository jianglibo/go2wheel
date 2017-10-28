package com.go2wheel.facade.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.BootUser;
import com.go2wheel.domain.MessageNotify;
import com.go2wheel.facade.MessageNotifyFacadeRepository;
import com.go2wheel.facade.Page;
import com.go2wheel.katharsis.dto.MessageNotifyDto;
import com.go2wheel.repository.MessageNotifyRepository;

@Component
public class MessageNotifyFacadeRepositoryImpl extends FacadeRepositoryBaseImpl<MessageNotify,MessageNotifyDto, MessageNotifyRepository> implements MessageNotifyFacadeRepository {

	@Autowired
	public MessageNotifyFacadeRepositoryImpl(MessageNotifyRepository jpaRepo) {
		super(jpaRepo);
	}

	@Override
	public MessageNotify newByDto(MessageNotifyDto dto) {
		return null;
	}

	@Override
	public MessageNotify findByBootUserAndNtype(BootUser user, String ntype) {
		return getRepository().findByBootUserAndNtype(user, ntype);
	}

	@Override
	public Page<MessageNotify> findByBootUser(BootUser user) {
		List<MessageNotify> mns = getRepository().findByBootUser(user);
		return new Page<>(mns.size(), mns);
	}

}
