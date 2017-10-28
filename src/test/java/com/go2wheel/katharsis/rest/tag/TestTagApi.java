package com.go2wheel.katharsis.rest.tag;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.go2wheel.JsonApiPostBodyWrapper;
import com.go2wheel.JsonApiPostBodyWrapper.CreateOneBody;
import com.go2wheel.JsonApiPostBodyWrapperBuilder;
import com.go2wheel.KatharsisBase;
import com.go2wheel.config.JsonApiResourceNames;
import com.go2wheel.domain.Tag;
import com.go2wheel.katharsis.dto.TagDto;
import com.go2wheel.util.MyJsonApiUrlBuilder;

public class TestTagApi  extends KatharsisBase {
	
	@Before
	public void b() throws JsonParseException, JsonMappingException, IOException {
		initTestUser();
		deleteAllTags();
	}
	
	@Test
	public void tAddOneByRest() throws JsonParseException, JsonMappingException, IOException {
		
		JsonApiPostBodyWrapper<CreateOneBody> jbw = JsonApiPostBodyWrapperBuilder.getOneBuilder(getResourceName())
				.addAttributePair("name", "taga")
				.build();
		
		String s = objectMapper.writeValueAsString(jbw);
		writeDto(s, getResourceName(), "postcontent");
		
		response = postItemWithContent(s, jwt1);
		writeDto(response, getResourceName(), ActionNames.POST_RESULT);
		
		TagDto newPost = getOne(response.getBody(), TagDto.class);
		assertThat(newPost.getName(), equalTo("taga"));
		
		Tag p = tagRepo.findOne(newPost.getId());
		assertThat(p.getName(), equalTo("taga"));
		
		MyJsonApiUrlBuilder b = new MyJsonApiUrlBuilder("?");
		b.filters("name", "tag");
		String url = getBaseURI() +  b.build();
		
		response = requestForBody(null, url);
		writeDto(response, getResourceName(), ActionNames.GET_LIST);
		List<TagDto> newPosts = getList(response, TagDto.class);
		assertThat(newPosts.size(), equalTo(1));
		
	}


	@Override
	protected String getResourceName() {
		return JsonApiResourceNames.TAG;
	}
}
