package com.go2wheel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.go2wheel.domain.MtModel;
import com.go2wheel.domain.MtSeries;

public interface MtModelRepository extends RepositoryBase<MtModel> {

    MtModel findByName(String rn);

	Page<MtModel> findByNameContaining(String name, Pageable pageable);

	Page<MtModel> findByMtSeries(MtSeries ms, Pageable pageable);

}
