package com.go2wheel.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.go2wheel.domain.Manufacturer;

/**
 * To handle some conditions, not all conditions.
 * @author jianglibo@gmail.com
 *
 */
public class ManufacturerRepositoryImpl extends SimpleJpaRepositoryBase<Manufacturer> implements RepositoryBase<Manufacturer> {

	@Autowired
	public ManufacturerRepositoryImpl(EntityManager entityManager) {
		super(Manufacturer.class, entityManager);
	}

}
