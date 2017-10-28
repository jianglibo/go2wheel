package com.go2wheel.facade;


import com.go2wheel.domain.BootUser;
import com.go2wheel.domain.MessageNotify;
import com.go2wheel.katharsis.dto.MessageNotifyDto;


public interface MessageNotifyFacadeRepository extends FacadeRepositoryBase<MessageNotify, MessageNotifyDto> {
	MessageNotify findByBootUserAndNtype(BootUser user, String ntype);
	Page<MessageNotify> findByBootUser(BootUser bu);
}
