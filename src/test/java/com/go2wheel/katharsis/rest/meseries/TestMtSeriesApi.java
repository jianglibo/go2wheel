package com.go2wheel.katharsis.rest.meseries;

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
import com.go2wheel.domain.Manufacturer;
import com.go2wheel.domain.MtSeries;
import com.go2wheel.katharsis.dto.MtSeriesDto;
import com.go2wheel.katharsis.rest.manufacturer.ManufacturerTUtil;
import com.go2wheel.util.MyJsonApiUrlBuilder;

public class TestMtSeriesApi  extends KatharsisBase {
	
	@Autowired
	private ManufacturerTUtil mftu;
	
	@Before
	public void b() throws JsonParseException, JsonMappingException, IOException {
		initTestUser();
		deleteAllMtSerieses();
	}
	
	@Test
	public void tAddOneByRest() throws JsonParseException, JsonMappingException, IOException {
//		private static String[] initProperties = new String[] {"name", "description"};
		Manufacturer mf = mftu.createOne();
		JsonApiPostBodyWrapper<CreateOneBody> jbw = JsonApiPostBodyWrapperBuilder.getOneBuilder(getResourceName())
				.addAttributePair("name", "versys")
				.addAttributePair("description", "very good.")
				.addOneRelation("manufacturer", JsonApiResourceNames.MANUFACTURER, mf.getId())
				.build();
		
		String s = objectMapper.writeValueAsString(jbw);
		writeDto(s, getResourceName(), "postcontent");
		
		response = postItemWithContent(s, jwt1);
		writeDto(response, getResourceName(), ActionNames.POST_RESULT);
		
		MtSeriesDto newPost = getOne(response.getBody(), MtSeriesDto.class);
		assertThat(newPost.getName(), equalTo("versys"));
		
		MtSeries p = mtSeriesRepo.findOne(newPost.getId());
		
		assertThat(p.getName(), equalTo("versys"));
		
		MyJsonApiUrlBuilder b = new MyJsonApiUrlBuilder("?");
		b.filters("name", "versys");
		String url = getBaseURI() +  b.build();
		
		response = requestForBody(null, url);
		writeDto(response, getResourceName(), ActionNames.GET_LIST);
		List<MtSeriesDto> newPosts = getList(response, MtSeriesDto.class);
		assertThat(newPosts.size(), equalTo(1));
	}


	@Override
	protected String getResourceName() {
		return JsonApiResourceNames.MT_SERIES;
	}
}
