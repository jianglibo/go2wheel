package com.go2wheel.fixturegen;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.go2wheel.JsonApiPostBodyWrapper;
import com.go2wheel.JsonApiPostBodyWrapper.CreateOneBody;
import com.go2wheel.JsonApiPostBodyWrapperBuilder;
import com.go2wheel.JsonApiPostBodyWrapperBuilder.OneObjectWapperBuilder;
import com.go2wheel.KatharsisBase;
import com.go2wheel.config.JsonApiResourceNames;

public class TestApproveFixture extends KatharsisBase {

	@Test
	public void t() throws JsonProcessingException {
		OneObjectWapperBuilder builder = JsonApiPostBodyWrapperBuilder.getOneBuilder(JsonApiResourceNames.APPROVE);
		
		JsonApiPostBodyWrapper<CreateOneBody> body = builder.addOneRelation("requester", JsonApiResourceNames.BOOT_USER, 10L)
				.addOneRelation("receiver", JsonApiResourceNames.BOOT_USER, 20L).build();
		String s = objectMapper.writeValueAsString(body);
		printme(s);
	}

	@Override
	protected String getResourceName() {
		return null;
	}
}
