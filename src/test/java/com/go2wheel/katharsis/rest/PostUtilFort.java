package com.go2wheel.katharsis.rest;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.go2wheel.JsonApiPostBodyWrapper;
import com.go2wheel.JsonApiPostBodyWrapperBuilder;
import com.go2wheel.Tutil;
import com.go2wheel.JsonApiPostBodyWrapper.CreateListBody;
import com.go2wheel.JsonApiPostBodyWrapperBuilder.ListWapperBuilder;
import com.go2wheel.config.JsonApiResourceNames;
import com.go2wheel.domain.BootGroup;
import com.go2wheel.domain.BootUser;

@Component
public class PostUtilFort {
	
	@Autowired
	private Tutil tutil;

	public String createPostPostBody(BootGroup group, BootUser...users) throws JsonProcessingException {
		Long[] ids = Stream.of(users).map(u -> u.getId()).toArray(size -> new Long[size]);
		ListWapperBuilder lb = JsonApiPostBodyWrapperBuilder.getListBuilder(JsonApiResourceNames.POST)
				.addAttributePair("title", "title")
				.addAttributePair("content", "content")
				.addRelation("sharedUsers", JsonApiResourceNames.BOOT_USER, ids);
		if (group != null) {
			lb.addRelation("sharedGroups", JsonApiResourceNames.BOOT_GROUP, group.getId());
		}
				
		JsonApiPostBodyWrapper<CreateListBody> jbw = lb.build();
		return tutil.getObjectMapper().writeValueAsString(jbw);
	}
}
