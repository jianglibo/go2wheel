package com.go2wheel.katharsis.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.BootUser;
import com.go2wheel.domain.MessageNotify;
import com.go2wheel.facade.BootUserFacadeRepository;
import com.go2wheel.facade.MessageNotifyFacadeRepository;
import com.go2wheel.facade.Page;
import com.go2wheel.katharsis.dto.MessageNotifyDto;
import com.go2wheel.katharsis.dto.UserDto;
import com.go2wheel.katharsis.dto.converter.DtoConverter.Scenario;
import com.go2wheel.katharsis.dto.converter.MessageNotifyDtoConverter;
import com.go2wheel.katharsis.repository.MessageNotifyDtoRepository.MessageNotifyDtoList;
import com.go2wheel.util.QuerySpecUtil.RelationQuery;

import io.katharsis.queryspec.QuerySpec;

@Component
public class MessageNotifyDtoRepositoryImpl  extends DtoRepositoryBase<MessageNotifyDto, MessageNotifyDtoList, MessageNotify, MessageNotifyFacadeRepository> implements MessageNotifyDtoRepository {
	
	@Autowired
	private BootUserFacadeRepository userRepo;
	
	@Autowired
	private MessageNotifyFacadeRepository mnRepo;
	
	@Autowired
	public MessageNotifyDtoRepositoryImpl(MessageNotifyFacadeRepository repository, MessageNotifyDtoConverter converter) {
		super(MessageNotifyDto.class, MessageNotifyDtoList.class, MessageNotify.class, repository, converter);
	}

	@Override
	protected MessageNotifyDtoList findAllWithQuerySpec(QuerySpec querySpec) {
		return null;
	}

	@Override
	protected List<String> checkAllSortableFieldAllowed(QuerySpec querySpec) {
		return null;
	}

	@Override
	protected MessageNotifyDtoList findWithRelationAndSpec(RelationQuery rq, QuerySpec querySpec) {
		if ("user".equals(rq.getRelationName())) {
			UserDto udto = new UserDto(rq.getRelationIds().get(0));
			BootUser bu = userRepo.findOne(udto.getId(), true);
			Page<MessageNotify> mns = mnRepo.findByBootUser(bu);
			MessageNotifyDtoList mnl = convertToResourceList(mns, Scenario.FIND_LIST);
			mnl.forEach(mn -> mn.setUser(udto));
			return mnl;
		}
		throw getUnsupportRelationException("messagenotify", rq.getRelationName());
	}
}
