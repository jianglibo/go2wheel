package com.go2wheel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.go2wheel.domain.MtModel;

public interface MtModelRepository extends RepositoryBase<MtModel> {

    MtModel findByName(String rn);

	Page<MtModel> findByNameContaining(String name, Pageable pageable);

}
