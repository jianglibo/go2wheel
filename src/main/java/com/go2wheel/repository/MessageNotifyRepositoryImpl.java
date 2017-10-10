﻿package com.go2wheel.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.go2wheel.domain.MessageNotify;

/**
 * To handle some conditions, not all conditions.
 * @author jianglibo@gmail.com
 *
 */
public class MessageNotifyRepositoryImpl extends SimpleJpaRepositoryBase<MessageNotify> implements RepositoryBase<MessageNotify> {

	@Autowired
	public MessageNotifyRepositoryImpl(EntityManager entityManager) {
		super(MessageNotify.class, entityManager);
	}

}
