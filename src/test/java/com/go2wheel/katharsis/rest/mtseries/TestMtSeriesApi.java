package com.go2wheel.katharsis.rest.mtseries;

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
import com.go2wheel.domain.MtModel;
import com.go2wheel.domain.MtSeries;
import com.go2wheel.katharsis.dto.MtModelDto;
import com.go2wheel.katharsis.dto.MtSeriesDto;
import com.go2wheel.katharsis.rest.manufacturer.ManufacturerTUtil;
import com.go2wheel.katharsis.rest.mtmodel.MtModelTUtil;
import com.go2wheel.util.MyJsonApiUrlBuilder;

public class TestMtSeriesApi  extends KatharsisBase {
	
	@Autowired
	private ManufacturerTUtil mftu;
	
	@Autowired
	private MtSeriesTUtil msUtil;
	
	@Autowired
	private MtModelTUtil mmUtil;
	
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
	
	@Test
	public void testMtModelsRelations() throws IOException {
		MtSeries ms = msUtil.createOne("versys");
		@SuppressWarnings("unused")
		List<MtModel> mms = mmUtil.createMany(ms, 3);
		String url = buildRelationUrl(ms, "models");
		response = requestForBody(null, url);
		writeDto(response, getResourceName(), "relation-models");
		List<MtModelDto> mtmodels = getList(response, MtModelDto.class);
		assertThat(mtmodels.size(), equalTo(3));
		
		url = buildRelationUrl(ms, "models") + new MyJsonApiUrlBuilder("?").page(2, 0).build();
		response = requestForBody(null, url);
		writeDto(response, getResourceName(), "relation-mtSerieses");
		mtmodels = getList(response, MtModelDto.class);
		assertThat(mtmodels.size(), equalTo(2));
	}



	@Override
	protected String getResourceName() {
		return JsonApiResourceNames.MT_SERIES;
	}
}
