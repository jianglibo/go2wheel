package com.go2wheel.katharsis.rest.post;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.go2wheel.JsonApiListBodyWrapper;
import com.go2wheel.JsonApiPostBodyWrapper;
import com.go2wheel.JsonApiPostBodyWrapper.CreateListBody;
import com.go2wheel.JsonApiPostBodyWrapperBuilder;
import com.go2wheel.KatharsisBase;
import com.go2wheel.config.JsonApiResourceNames;
import com.go2wheel.domain.BootGroup;
import com.go2wheel.domain.GroupUserRelation;
import com.go2wheel.domain.PostShare;
import com.go2wheel.katharsis.dto.PostDto;
import com.go2wheel.repository.GroupUserRelationRepository;
import com.go2wheel.repository.PostShareRepository;

public class TestPostGroupRelation  extends KatharsisBase {
	
	@Autowired
	private PostShareRepository psRepo;
	
	@Autowired
	private GroupUserRelationRepository guRepo;
	
	@Before
	public void b() throws JsonParseException, JsonMappingException, IOException {
		initTestUser();
	}
	
	@Test
	public void tshareToGroup() throws JsonParseException, JsonMappingException, IOException {
		BootGroup bg = new BootGroup("group");
		bg.setCreator(user1);
		groupRepo.save(bg);
		
		GroupUserRelation gur = new GroupUserRelation(bg, user1);
		guRepo.save(gur);
		
		gur = new GroupUserRelation(bg, user2);
		guRepo.save(gur);
		
		JsonApiPostBodyWrapper<CreateListBody> jbw = JsonApiPostBodyWrapperBuilder.getListBuilder(getResourceName())
				.addAttributePair("title", "title")
				.addAttributePair("content", "content")
				.build();
		String s = objectMapper.writeValueAsString(jbw);
		
		ResponseEntity<String> response = postItemWithContent(s, jwt0);
		
		PostDto newPost = getOne(response.getBody(), PostDto.class);
		
		JsonApiListBodyWrapper jb = new JsonApiListBodyWrapper(JsonApiResourceNames.BOOT_GROUP, bg.getId());
		
		// The creator of the post is user0. 
		response = addRelationWithContent(indentOm.writeValueAsString(jb), "sharedGroups", newPost.getId(), jwt0);
		List<PostShare> ps1 = psRepo.findAllByBootUser(user1);
		List<PostShare> ps2 = psRepo.findAllByBootUser(user2);
		assertThat(ps1.size(), equalTo(1));
		assertThat(ps2.size(), equalTo(1));
		
		// share to same users again.
		response = addRelationWithContent(indentOm.writeValueAsString(jb), "sharedGroups", newPost.getId(), jwt0);
		ps1 = psRepo.findAllByBootUser(user1);
		ps2 = psRepo.findAllByBootUser(user2);
		assertThat(ps1.size(), equalTo(1));
		assertThat(ps2.size(), equalTo(1));
	}


	@Override
	protected String getResourceName() {
		return JsonApiResourceNames.POST;
	}
}
