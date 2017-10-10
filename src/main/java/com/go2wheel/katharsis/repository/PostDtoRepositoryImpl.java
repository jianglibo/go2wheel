package com.go2wheel.katharsis.repository;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.BootUser;
import com.go2wheel.domain.Post;
import com.go2wheel.domain.PostShare;
import com.go2wheel.facade.BootUserFacadeRepository;
import com.go2wheel.facade.Page;
import com.go2wheel.facade.PostFacadeRepository;
import com.go2wheel.facade.PostShareFacadeRepository;
import com.go2wheel.facade.UnreadFacadeRepository;
import com.go2wheel.katharsis.dto.PostDto;
import com.go2wheel.katharsis.dto.UserDto;
import com.go2wheel.katharsis.dto.converter.DtoConverter.Scenario;
import com.go2wheel.katharsis.dto.converter.PostDtoConverter;
import com.go2wheel.katharsis.repository.PostDtoRepository.PostDtoList;
import com.go2wheel.util.QuerySpecUtil;
import com.go2wheel.util.QuerySpecUtil.RelationQuery;

import io.katharsis.queryspec.QuerySpec;

@Component
public class PostDtoRepositoryImpl  extends DtoRepositoryBase<PostDto, PostDtoList, Post, PostFacadeRepository> implements PostDtoRepository {
	
	@Autowired
	private PostShareFacadeRepository psRepo;
	
	@Autowired
	private BootUserFacadeRepository userRepo;
	
	@Autowired
	private UnreadFacadeRepository unreadRepo;
	
	@Autowired
	public PostDtoRepositoryImpl(PostFacadeRepository repository, PostDtoConverter converter) {
		super(PostDto.class, PostDtoList.class, Post.class, repository, converter);
	}

	@Override
	protected PostDtoList findAllWithQuerySpec(QuerySpec querySpec) {
		boolean toAll = (boolean) QuerySpecUtil.getFilterSingleValue(querySpec, "toAll").orElse(true);
		Page<Post> posts = getRepository().findAllByToAll(toAll, QuerySpecUtil.getPageFacade(querySpec));
		return convertToResourceList(posts, Scenario.FIND_LIST);
	}

	@Override
	protected List<String> checkAllSortableFieldAllowed(QuerySpec querySpec) {
		return null;
	}

	@Override
	protected PostDtoList findWithRelationAndSpec(RelationQuery rq, QuerySpec querySpec) {
		if ("creator".equals(rq.getRelationName())) {
			BootUser user = userRepo.findOne(rq.getRelationIds().get(0), true);
			Page<Post> posts = getRepository().findMine(user, QuerySpecUtil.getPageFacade(querySpec));
			return convertToResourceList(posts, Scenario.RELATION_LIST);
		} else if ("sharedUsers".equals(rq.getRelationName())) {
			BootUser user = userRepo.findOne(rq.getRelationIds().get(0), true);
			Page<PostShare> pss = psRepo.findByBootUser(user, QuerySpecUtil.getPageFacade(querySpec));
			List<Post> postlist = pss.getContent().stream().map(ps -> ps.getPost()).collect(Collectors.toList());
			Page<Post> posts = new Page<>(pss.getTotalResourceCount(), postlist);
			
			PostDtoList pdl = convertToResourceList(posts, Scenario.RELATION_LIST);
			List<UserDto> users = Arrays.asList(new UserDto(user.getId()));
			pdl.forEach(p -> {
				p.setSharedUsers(users);
				boolean hasRead = unreadRepo.userHasReadThisPost(user, p.getId());
				p.setRead(hasRead);
			});
			return pdl;
		}
		return null;
	}
}
