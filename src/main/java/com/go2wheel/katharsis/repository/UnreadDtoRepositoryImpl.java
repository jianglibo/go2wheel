package com.go2wheel.katharsis.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.BootUser;
import com.go2wheel.domain.Unread;
import com.go2wheel.facade.BootUserFacadeRepository;
import com.go2wheel.facade.Page;
import com.go2wheel.facade.UnreadFacadeRepository;
import com.go2wheel.katharsis.dto.UnreadDto;
import com.go2wheel.katharsis.dto.UserDto;
import com.go2wheel.katharsis.dto.converter.DtoConverter.Scenario;
import com.go2wheel.katharsis.dto.converter.UnreadDtoConverter;
import com.go2wheel.katharsis.repository.UnreadDtoRepository.UnreadDtoList;
import com.go2wheel.util.QuerySpecUtil;
import com.go2wheel.util.QuerySpecUtil.RelationQuery;

import io.katharsis.queryspec.FilterSpec;
import io.katharsis.queryspec.QuerySpec;

@Component
public class UnreadDtoRepositoryImpl  extends DtoRepositoryBase<UnreadDto, UnreadDtoList, Unread, UnreadFacadeRepository> implements UnreadDtoRepository {
	
	@Autowired
	private BootUserFacadeRepository userRepo;
	
	@Autowired
	private UnreadFacadeRepository unreadRepo;
	
	@Autowired
	public UnreadDtoRepositoryImpl(UnreadFacadeRepository repository, UnreadDtoConverter converter) {
		super(UnreadDto.class, UnreadDtoList.class, Unread.class, repository, converter);
	}

	@Override
	protected UnreadDtoList findAllWithQuerySpec(QuerySpec querySpec) {
		return null;
	}

	@Override
	protected List<String> checkAllSortableFieldAllowed(QuerySpec querySpec) {
		return null;
	}

	@Override
	protected UnreadDtoList findWithRelationAndSpec(RelationQuery rq, QuerySpec querySpec) {
		if ("user".equals(rq.getRelationName())) {
			String type = "";
			for(FilterSpec fs : querySpec.getFilters()) {
				List<String> fps = fs.getAttributePath();
				if (fps.size() == 1 && "type".equals(fps.get(0))) {
					type = fs.getValue().toString();
				}
			}
			UserDto udto = new UserDto(rq.getRelationIds().get(0));
			BootUser bu = userRepo.findOne(udto.getId(), true);
			Page<Unread> unreads = unreadRepo.findByBootUserAndType(bu, type, QuerySpecUtil.getPageFacade(querySpec));
			UnreadDtoList urdl = convertToResourceList(unreads, Scenario.FIND_LIST);
			urdl.forEach(mn -> mn.setUser(udto));
			return urdl;
		}
		throw getUnsupportRelationException("unread", rq.getRelationName());
	}
}
