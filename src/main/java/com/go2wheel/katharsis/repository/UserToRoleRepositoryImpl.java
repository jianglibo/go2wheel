package com.go2wheel.katharsis.repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.BootUser;
import com.go2wheel.domain.Role;
import com.go2wheel.facade.BootUserFacadeRepository;
import com.go2wheel.facade.RoleFacadeRepository;
import com.go2wheel.katharsis.dto.RoleDto;
import com.go2wheel.katharsis.dto.UserDto;

@Component
public class UserToRoleRepositoryImpl extends RelationshipRepositoryBaseMine<UserDto, RoleDto> {
	
	@Autowired
	private BootUserFacadeRepository userRepo;
	
	@Autowired
	private RoleFacadeRepository roleRepo;
	
	protected UserToRoleRepositoryImpl() {
		super(UserDto.class, RoleDto.class);
	}
	
	@Override
	public void addRelations(UserDto source, Iterable<Long> targetIds, String fieldName) {
		BootUser bu = userRepo.findOne(source.getId(), true);
		if ("roles".equals(fieldName)) {
			Set<Role> roles = new HashSet<>(bu.getRoles());
			for(Long id : targetIds) {
				roles.add(roleRepo.findOne(id, true));
			}
			bu.setRoles(roles);
			userRepo.save(bu, null);
		}
	}
	
	@Override
	public void removeRelations(UserDto source, Iterable<Long> targetIds, String fieldName) {
		BootUser bu = userRepo.findOne(source.getId(), true);
		if ("roles".equals(fieldName)) {
			Set<Role> roles = new HashSet<>(bu.getRoles());
			for(Long id : targetIds) {
				Optional<Role> ro = roles.stream().filter(r -> r.getId().equals(id)).findAny();
				if (ro.isPresent()) {
					roles.remove(ro.get());
				}
			}
			bu.setRoles(roles);
			userRepo.save(bu, null);
		}
	}

}
