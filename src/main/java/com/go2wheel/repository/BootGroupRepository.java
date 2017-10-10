package com.go2wheel.repository;

import com.go2wheel.domain.BootGroup;

public interface BootGroupRepository extends RepositoryBase<BootGroup> {
    BootGroup findByName(String rn);

}
