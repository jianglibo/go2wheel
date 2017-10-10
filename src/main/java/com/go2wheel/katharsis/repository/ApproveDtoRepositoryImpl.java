package com.go2wheel.katharsis.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.Approve;
import com.go2wheel.domain.BootGroup;
import com.go2wheel.domain.BootUser;
import com.go2wheel.domain.GroupUserRelation;
import com.go2wheel.facade.ApproveFacadeRepository;
import com.go2wheel.facade.BootGroupFacadeRepository;
import com.go2wheel.facade.BootUserFacadeRepository;
import com.go2wheel.facade.GroupUserRelationFacadeRepository;
import com.go2wheel.facade.Page;
import com.go2wheel.katharsis.dto.ApproveDto;
import com.go2wheel.katharsis.dto.UserDto;
import com.go2wheel.katharsis.dto.converter.ApproveDtoConverter;
import com.go2wheel.katharsis.dto.converter.DtoConverter.Scenario;
import com.go2wheel.katharsis.exception.UnsupportedRequestException;
import com.go2wheel.katharsis.repository.ApproveDtoRepository.ApproveDtoList;
import com.go2wheel.util.PropertyCopyUtil;
import com.go2wheel.util.QuerySpecUtil;
import com.go2wheel.util.QuerySpecUtil.RelationQuery;
import com.go2wheel.util.SecurityUtil;

import io.katharsis.queryspec.QuerySpec;

@Component
public class ApproveDtoRepositoryImpl  extends DtoRepositoryBase<ApproveDto, ApproveDtoList, Approve, ApproveFacadeRepository> implements ApproveDtoRepository {
	
	@Autowired
	private BootUserFacadeRepository userRepo;
	
	@Autowired
	private BootGroupFacadeRepository groupRepo;
	
	@Autowired
	private GroupUserRelationFacadeRepository guRepo;
	
	@Autowired
	public ApproveDtoRepositoryImpl(ApproveFacadeRepository repository, ApproveDtoConverter converter) {
		super(ApproveDto.class, ApproveDtoList.class, Approve.class, repository, converter);
	}

	@Override
	protected ApproveDtoList findAllWithQuerySpec(QuerySpec querySpec) {
		return null;
	}

	@Override
	protected List<String> checkAllSortableFieldAllowed(QuerySpec querySpec) {
		return null;
	}
	
	@Override
	public ApproveDto createNew(ApproveDto dto) {
		throw new UnsupportedRequestException("Approve object direct creation is forbiden.");
	}
	
	@Override
	public ApproveDto modify(ApproveDto dto) {
		Approve entity = getRepository().findOne(dto.getId(), false);
		PropertyCopyUtil.applyPatch(entity,dto);
		
		if (BootGroup.class.getName().equals(entity.getTargetType())) {
			BootGroup group = groupRepo.findOne(entity.getTargetId(), true);
			if (group.getCreator().getId().equals(SecurityUtil.getLoginUserId())) { // if group's creator isn't the current user, reject it.
				switch (entity.getState()) {
				case APPROVED:
					GroupUserRelation gur = new GroupUserRelation(group, entity.getRequester());
					guRepo.save(gur, null);
					break;
				case REJECT:
					break;
				default:
					break;
				}
			} else {
				throw new AccessDeniedException("group is not created by you.");
			}
		}
		return getConverter().entity2Dto(saveToBackendRepo(dto, entity), Scenario.MODIFY);
	}

	@Override
	protected ApproveDtoList findWithRelationAndSpec(RelationQuery rq, QuerySpec querySpec) {
		if ("receiver".equals(rq.getRelationName())) {
			UserDto userDto = new UserDto(rq.getRelationIds().get(0));
			BootUser bu = userRepo.findOne(userDto.getId(), true);
			Page<Approve> approves =  getRepository().findReceived(bu, QuerySpecUtil.getPageFacade(querySpec));
			ApproveDtoList adl = convertToResourceList(approves, Scenario.RELATION_LIST);
			adl.forEach(a -> a.setReceiver(userDto));
			return adl;
		} else if ("requester".equals(rq.getRelationName())) {
			UserDto userDto = new UserDto(rq.getRelationIds().get(0));
			BootUser bu = userRepo.findOne(userDto.getId(), true);
			Page<Approve> approves =  getRepository().findSent(bu, QuerySpecUtil.getPageFacade(querySpec));
			ApproveDtoList adl = convertToResourceList(approves, Scenario.RELATION_LIST);
			adl.forEach(a -> a.setRequester(userDto));
			return adl;
		}
		return null;
	}
}
