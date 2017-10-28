package com.go2wheel.katharsis.rest;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.go2wheel.JsonApiPostBodyWrapper;
import com.go2wheel.JsonApiPostBodyWrapper.CreateOneBody;
import com.go2wheel.JsonApiPostBodyWrapperBuilder;
import com.go2wheel.KatharsisBase;
import com.go2wheel.config.JsonApiResourceNames;
import com.go2wheel.constant.AppErrorCodes;
import com.go2wheel.domain.BootGroup;
import com.go2wheel.domain.BootUser;
import com.go2wheel.repository.ApproveRepository;
import com.go2wheel.repository.BootGroupRepository;

import io.katharsis.errorhandling.ErrorData;

public class TestApproveAdd extends KatharsisBase {
	
	@Autowired
	private BootGroupRepository groupRepo;
	
	@Autowired
	private ApproveRepository approveRepo;
	
	@Before
	public void b() throws JsonParseException, JsonMappingException, IOException {
		initTestUser();
		approveRepo.deleteAll();
		groupRepo.deleteAll();
	}
	
	@Test
	public void t() throws IOException {
		BootUser requester = tutil.createBootUser("b1", "123");
		BootUser receiver = tutil.createBootUser("b2", "123");
		BootGroup bg = new BootGroup("agroup");
		bg = groupRepo.save(bg);
		
		JsonApiPostBodyWrapper<CreateOneBody> jpw = JsonApiPostBodyWrapperBuilder.getOneBuilder(getResourceName()).addAttributePair("targetType", BootGroup.class.getName())
				.addAttributePair("targetId", bg.getId())
				.addOneRelation("requester", JsonApiResourceNames.BOOT_USER, requester.getId())
				.addOneRelation("receiver", JsonApiResourceNames.BOOT_USER, receiver.getId()).build();
		
		String s = indentOm.writeValueAsString(jpw);
		response = postItemWithContent(s, jwt1);
		List<ErrorData> eds = getErrors(response);
		assertThat(eds.size(), equalTo(1));
		assertThat(eds.get(0).getCode(), equalTo(AppErrorCodes.UNSUPPORTED_REQUEST));
	}

	@Override
	protected String getResourceName() {
		return JsonApiResourceNames.APPROVE;
	}

}
