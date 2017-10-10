package com.go2wheel.katharsis.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.BootGroup;
import com.go2wheel.domain.GroupUserRelation;
import com.go2wheel.domain.Post;
import com.go2wheel.domain.PostShare;
import com.go2wheel.facade.BootGroupFacadeRepository;
import com.go2wheel.facade.GroupUserRelationFacadeRepository;
import com.go2wheel.facade.Page;
import com.go2wheel.facade.PageFacade;
import com.go2wheel.facade.PostFacadeRepository;
import com.go2wheel.facade.PostShareFacadeRepository;
import com.go2wheel.katharsis.dto.GroupDto;
import com.go2wheel.katharsis.dto.PostDto;
import com.go2wheel.katharsis.exception.UnsupportedRequestException;

@Component
public class PostToGroupRepositoryImpl extends RelationshipRepositoryBaseMine<PostDto, GroupDto> {
	
	@Autowired
	private PostFacadeRepository postRepo;
	
	@Autowired
	private BootGroupFacadeRepository groupRepo;
	
	@Autowired
	private GroupUserRelationFacadeRepository guRepo;
	
	@Autowired
	private PostShareFacadeRepository psRepo;

	protected PostToGroupRepositoryImpl() {
		super(PostDto.class, GroupDto.class);
	}
	
	@Override
	public void addRelations(PostDto source, Iterable<Long> targetIds, String fieldName) {
		Post p = postRepo.findOne(source.getId(), true);
		if ("sharedGroups".equals(fieldName)) {
			for(Long id : targetIds) {
				BootGroup bg = groupRepo.findOne(id, true);
				Page<GroupUserRelation> gurs =  guRepo.findByBootGroup(bg, new PageFacade(10000L));
				gurs.getContent().stream().map(gur -> gur.getBootUser()).forEach(user -> {
					PostShare ps = new PostShare(p, user);
					psRepo.save(ps, null);
				});
			}
		}
	}
	
	@Override
	public void removeRelations(PostDto source, Iterable<Long> targetIds, String fieldName) {
		throw new UnsupportedRequestException(String.format("post doesn't support remove relation: %s", fieldName));
	}

}
