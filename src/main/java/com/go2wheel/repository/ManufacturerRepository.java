package com.go2wheel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.go2wheel.domain.Manufacturer;

public interface ManufacturerRepository extends RepositoryBase<Manufacturer> {

    Manufacturer findByName(String rn);

	Page<Manufacturer> findByNameContaining(String name, Pageable pageable);

}
