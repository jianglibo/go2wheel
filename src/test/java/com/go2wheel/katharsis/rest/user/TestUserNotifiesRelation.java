package com.go2wheel.katharsis.rest.user;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.go2wheel.KatharsisBase;
import com.go2wheel.config.JsonApiResourceNames;
import com.go2wheel.domain.BootUser;
import com.go2wheel.domain.MessageNotify;
import com.go2wheel.domain.Post;
import com.go2wheel.katharsis.dto.MessageNotifyDto;
import com.go2wheel.repository.MessageNotifyRepository;

public class TestUserNotifiesRelation  extends KatharsisBase {
	
	@Autowired
	private MessageNotifyRepository mnRepo;
	
	@Before
	public void b() throws JsonParseException, JsonMappingException, IOException {
		initTestUser();
	}
	
	@Test
	public void t() throws Exception {
		BootUser follower = user1;
		MessageNotify mn = new MessageNotify();
		mn.setBootUser(follower);
		mn.setNtype(Post.class.getSimpleName());
		mn = mnRepo.save(mn);
		response = requestForBody(jwt1, getItemUrl(follower.getId()) + "/notifies");
		writeDto(response.getBody(), getResourceName(), "notifiesrelation");
		List<MessageNotifyDto> notifies = getList(response, MessageNotifyDto.class);
		assertThat(notifies.size(), equalTo(1));
		assertThat(notifies.get(0).getNtype(), equalTo(Post.class.getSimpleName()));
		assertThat(notifies.get(0).getNumber(), equalTo(0));
	}
	

	@Override
	protected String getResourceName() {
		return JsonApiResourceNames.BOOT_USER;
	}

}
