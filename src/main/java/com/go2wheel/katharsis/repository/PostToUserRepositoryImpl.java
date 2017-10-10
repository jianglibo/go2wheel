package com.go2wheel.katharsis.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.go2wheel.domain.Post;
import com.go2wheel.domain.PostShare;
import com.go2wheel.facade.BootUserFacadeRepository;
import com.go2wheel.facade.PageFacade;
import com.go2wheel.facade.PostFacadeRepository;
import com.go2wheel.facade.PostShareFacadeRepository;
import com.go2wheel.katharsis.dto.PostDto;
import com.go2wheel.katharsis.dto.UserDto;

@Component
public class PostToUserRepositoryImpl extends RelationshipRepositoryBaseMine<PostDto, UserDto> {
	
	@Autowired
	private PostFacadeRepository postRepo;
	
	@Autowired
	private BootUserFacadeRepository userRepo;
	
	@Autowired
	private PostShareFacadeRepository psRepo;

	protected PostToUserRepositoryImpl() {
		super(PostDto.class, UserDto.class);
	}
	
	@Override
	public void addRelations(PostDto source, Iterable<Long> targetIds, String fieldName) {
		Post p = postRepo.findOne(source.getId(), true);
		if ("sharedUsers".equals(fieldName)) {
			for(Long id : targetIds) {
				PostShare ps = new PostShare(p, userRepo.findOne(id, true));
				psRepo.save(ps, null);
			}
		}
	}
	
	@Override
	public void removeRelations(PostDto source, Iterable<Long> targetIds, String fieldName) {
		Post p = postRepo.findOne(source.getId(), true);
		if ("sharedUsers".equals(fieldName)) {
			for(Long id : targetIds) {
				PostShare ps = psRepo.findByPostAndBootUser(p, userRepo.findOne(id, true));
				psRepo.delete(ps);
			}
		}
	}
	
	@Override
	public void setRelations(PostDto source, Iterable<Long> targetIds, String fieldName) {
		Post p = postRepo.findOne(source.getId(), true);
		if ("sharedUsers".equals(fieldName)) {
			psRepo.findByPost(p, new PageFacade(10000L)).getContent().forEach(ps -> psRepo.delete(ps)); 
			for(Long id : targetIds) {
				PostShare ps = new PostShare(p, userRepo.findOne(id, true));
				psRepo.save(ps, null);
			}
		}
	}
}
