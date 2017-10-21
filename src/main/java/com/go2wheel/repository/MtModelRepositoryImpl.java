package com.go2wheel.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.go2wheel.domain.MtModel;

/**
 * To handle some conditions, not all conditions.
 * @author jianglibo@gmail.com
 *
 */
public class MtModelRepositoryImpl extends SimpleJpaRepositoryBase<MtModel> implements RepositoryBase<MtModel> {

	@Autowired
	public MtModelRepositoryImpl(EntityManager entityManager) {
		super(MtModel.class, entityManager);
	}

}
