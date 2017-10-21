package com.go2wheel.katharsis.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.go2wheel.katharsis.dto.UserDto;
import com.go2wheel.domain.BootGroup;
import com.go2wheel.facade.BootGroupFacadeRepository;
import com.go2wheel.facade.BootUserFacadeRepository;
import com.go2wheel.facade.GroupUserRelationFacadeRepository;
import com.go2wheel.katharsis.dto.GroupDto;

@Component
public class GroupToUserRepositoryImpl extends RelationshipRepositoryBaseMine<GroupDto, UserDto> {

	@Autowired
	private BootUserFacadeRepository userRepo;
	
	@Autowired
	private BootGroupFacadeRepository groupRepo;
	
	@Autowired
	private GroupUserRelationFacadeRepository gurRepo;
	
	protected GroupToUserRepositoryImpl() {
		super(GroupDto.class, UserDto.class);
	}
	
	@Override
	public void addRelations(GroupDto source, Iterable<Long> targetIds, String fieldName) {
		BootGroup gp = groupRepo.findOne(source.getId(), true);
		for (Long target : targetIds) {
			gurRepo.addRelation(gp, userRepo.findOne(target, true));
		}
	}
	
	@Override
	public void removeRelations(GroupDto source, Iterable<Long> targetIds, String fieldName) {
		BootGroup gp = groupRepo.findOne(source.getId(), true);
		for (Long target : targetIds) {
			gurRepo.deleteByBootGroupAndBootUser(gp, userRepo.findOne(target, true));
		}
	}

}
