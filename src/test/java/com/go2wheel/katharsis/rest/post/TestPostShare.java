package com.go2wheel.katharsis.rest.post;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.go2wheel.JsonApiListBodyWrapper;
import com.go2wheel.KatharsisBase;
import com.go2wheel.config.JsonApiResourceNames;
import com.go2wheel.domain.Post;
import com.go2wheel.katharsis.dto.UserDto;
import com.go2wheel.repository.PostRepository;

public class TestPostShare  extends KatharsisBase {
	
	@Autowired
	private PostRepository postRepo;
	
	@Before
	public void b() throws JsonParseException, JsonMappingException, IOException {
		initTestUser();
	}
	
	@Test
	public void tShareByRelation() throws JsonParseException, JsonMappingException, IOException {
		Post post = new Post();
		post.setTitle("title");
		post.setContent("content");
		post.setCreator(user0);
		postRepo.save(post);
		
		JsonApiListBodyWrapper jbw = new JsonApiListBodyWrapper("users", user1.getId(), user2.getId());
		String body = indentOm.writeValueAsString(jbw);
		writeDto(body, getResourceName(), "altersharedusers");
		response = addRelationWithContent(body, "sharedUsers", post.getId(), jwt0);
		assertResponseCode(response, 204);
		
		response = requestForBody(jwt0, getItemUrl(post.getId()) + "/sharedUsers");
		assertItemNumber(response, UserDto.class, 2);
		
		jbw = new JsonApiListBodyWrapper("users", user1.getId());
		body = indentOm.writeValueAsString(jbw);
				
		response = deleteRelationWithContent(body, "sharedUsers", post.getId(), jwt0);
		response = requestForBody(jwt0, getItemUrl(post.getId()) + "/sharedUsers");
		assertItemNumber(response, UserDto.class, 1);
		
		jbw = new JsonApiListBodyWrapper("users", new ArrayList<>());
		body = indentOm.writeValueAsString(jbw);
		response = replaceRelationWithContent(body, "sharedUsers", post.getId(), jwt0);
		response = requestForBody(jwt0, getItemUrl(post.getId()) + "/sharedUsers");
		assertItemNumber(response, UserDto.class, 0);
	}

	@Override
	protected String getResourceName() {
		return JsonApiResourceNames.POST;
	}
}
