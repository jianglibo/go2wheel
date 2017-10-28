package com.go2wheel.repository;

import com.go2wheel.domain.Role;

public interface RoleRepository extends RepositoryBase<Role> {
    Role findByName(String rn);

}
