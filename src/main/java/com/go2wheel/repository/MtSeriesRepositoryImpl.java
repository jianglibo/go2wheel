package com.go2wheel.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.go2wheel.domain.MtSeries;

/**
 * To handle some conditions, not all conditions.
 * @author jianglibo@gmail.com
 *
 */
public class MtSeriesRepositoryImpl extends SimpleJpaRepositoryBase<MtSeries> implements RepositoryBase<MtSeries> {

	@Autowired
	public MtSeriesRepositoryImpl(EntityManager entityManager) {
		super(MtSeries.class, entityManager);
	}

}
