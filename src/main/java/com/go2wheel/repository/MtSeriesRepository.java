package com.go2wheel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.go2wheel.domain.Manufacturer;
import com.go2wheel.domain.MtSeries;

public interface MtSeriesRepository extends RepositoryBase<MtSeries> {

    MtSeries findByName(String rn);

	Page<MtSeries> findByNameContaining(String name, Pageable pageable);

	Page<MtSeries> findByManufacturer(Manufacturer mf, Pageable pageable);

}
