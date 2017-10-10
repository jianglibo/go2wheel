package com.go2wheel.katharsis.rest.user;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.go2wheel.KatharsisBase;
import com.go2wheel.config.JsonApiResourceNames;
import com.go2wheel.domain.Post;
import com.go2wheel.katharsis.dto.PostDto;

public class TestUserMyPostRelation  extends KatharsisBase {
	
	@Before
	public void b() throws JsonParseException, JsonMappingException, IOException {
		initTestUser();
	}
	
	@Test
	public void tAddOne() throws JsonParseException, JsonMappingException, IOException {
		Post post1 = new Post();
		post1.setTitle("title");
		post1.setContent("content");
		post1.setCreator(user1);
		post1 = postRepo.save(post1);
		
		Post post2 = new Post();
		post2.setTitle("title1");
		post2.setContent("content1");
		post2.setCreator(user1);
		post2 = postRepo.save(post2);
		
		response = requestForBody(jwt1, getItemUrl(user1.getId()) + "/posts");
		writeDto(response.getBody(), getResourceName(), "getpostsrelation");
		List<PostDto> posts = getList(response, PostDto.class);
		assertThat(posts.size(), equalTo(2));
		
		response = requestForBody(jwt2, getItemUrl(user1.getId()) + "/posts");
		assertAccessDenied(response);
		
	}

	@Override
	protected String getResourceName() {
		return JsonApiResourceNames.BOOT_USER;
	}

}
