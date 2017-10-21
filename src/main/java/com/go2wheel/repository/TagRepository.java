package com.go2wheel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.go2wheel.domain.Tag;

public interface TagRepository extends RepositoryBase<Tag> {

    Tag findByName(String rn);

	Page<Tag> findByNameContaining(String name, Pageable pageable);

}
