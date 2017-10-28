package com.go2wheel.katharsis.rest.user;


import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.go2wheel.KatharsisBase;
import com.go2wheel.config.JsonApiResourceNames;
import com.go2wheel.domain.Post;
import com.go2wheel.domain.Unread;
import com.go2wheel.katharsis.dto.UnreadDto;
import com.go2wheel.repository.UnreadRepository;
import com.go2wheel.util.MyJsonApiUrlBuilder;

public class TestUserUnreadRelation  extends KatharsisBase {
	
	@Autowired
	private UnreadRepository unreadRepo;
	
	@Before
	public void b() throws JsonParseException, JsonMappingException, IOException {
		initTestUser();
	}
	
	@Test
	public void tAddOne() throws JsonParseException, JsonMappingException, IOException {
		Unread ur = new Unread();
		ur.setObid(55L);
		ur.setType(Post.class.getSimpleName());
		ur.setBootUser(user1);
		unreadRepo.save(ur);
		
		response = requestForBody(jwt1, getItemUrl(user1.getId()) + "/unreads");
		writeDto(response.getBody(), getResourceName(), "unreadrelation");
		assertItemNumber(response, UnreadDto.class, 0);
		
		String filterUrl = new MyJsonApiUrlBuilder("?").filters("type", Post.class.getSimpleName()).build();
		response = requestForBody(jwt1, getItemUrl(user1.getId()) + "/unreads" + filterUrl);
		writeDto(response.getBody(), getResourceName(), "unreadrelation");
		assertItemNumber(response, UnreadDto.class, 1);
	}


	@Override
	protected String getResourceName() {
		return JsonApiResourceNames.BOOT_USER;
	}

}
