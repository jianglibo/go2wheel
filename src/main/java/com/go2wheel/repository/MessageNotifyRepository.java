package com.go2wheel.repository;

import java.util.List;

import com.go2wheel.domain.BootUser;
import com.go2wheel.domain.MessageNotify;

public interface MessageNotifyRepository extends RepositoryBase<MessageNotify> {
	MessageNotify findByBootUserAndNtype(BootUser user, String ntype);
	List<MessageNotify> findByBootUser(BootUser user);
}
