package com.go2wheel.katharsis.rest.manufacturer;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

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
import com.go2wheel.katharsis.dto.ManufacturerDto;
import com.go2wheel.katharsis.dto.MtSeriesDto;
import com.go2wheel.katharsis.rest.mtseries.MtSeriesTUtil;
import com.go2wheel.util.MyJsonApiUrlBuilder;

public class TestMenufacturerApi  extends KatharsisBase {
	
	@Autowired
	private ManufacturerTUtil mftu;
	
	@Autowired
	private MtSeriesTUtil mtSeriesUtil;
	
	@Before
	public void b() throws JsonParseException, JsonMappingException, IOException {
		initTestUser();
		deleteAllManufacturers();
	}
	
	@Test
	public void tAddOneByRest() throws JsonParseException, JsonMappingException, IOException {

		JsonApiPostBodyWrapper<CreateOneBody> jbw = JsonApiPostBodyWrapperBuilder.getOneBuilder(getResourceName())
				.addAttributePair("name", "kawasaki")
				.addAttributeMapPair("websites", "zh", "http://www.kawasaki-motors.cn")
				.addAttributeMapPair("websites", "en", "https://www.kawasaki.com")
				.addAttributePair("founder", "")
				.addAttributePair("nationality", "Japan")
				.addAttributePair("logo", "abc/aa.gif")
				.addAttributePair("legend", "hello. world.")
				.build();
		
		String s = objectMapper.writeValueAsString(jbw);
		writeDto(s, getResourceName(), "postcontent");
		
		response = postItemWithContent(s, jwt1);
		writeDto(response, getResourceName(), ActionNames.POST_RESULT);
		
		ManufacturerDto newPost = getOne(response.getBody(), ManufacturerDto.class);
		assertThat(newPost.getName(), equalTo("kawasaki"));
		
		Manufacturer p = manufacturerRepo.findOne(newPost.getId());
		
		assertThat(p.getName(), equalTo("kawasaki"));
		
		MyJsonApiUrlBuilder b = new MyJsonApiUrlBuilder("?");
		b.filters("name", "kawasaki");
		String url = getBaseURI() +  b.build();
		
		response = requestForBody(null, url);
//		writeDto(response, getResourceName(), ActionNames.GET_LIST);
		List<ManufacturerDto> newPosts = getList(response, ManufacturerDto.class);
		assertThat(newPosts.size(), equalTo(1));
	}
	
	@Test
	public void getlist() throws IOException {
		IntStream.range(0, 12).forEach(i -> {
			mftu.createOne("kawasaki" + i);
		});
		response = requestForBody(null, getBaseURI());
		writeDto(response, getResourceName(), ActionNames.GET_LIST);
		List<ManufacturerDto> newPosts = getList(response, ManufacturerDto.class);
		assertThat(newPosts.size(), equalTo(12));
	}
	
	
	@Test
	public void testMtSeriesRelations() throws IOException {
		Manufacturer mf = mftu.createOne();
		@SuppressWarnings("unused")
		List<MtSeries> mss = mtSeriesUtil.createMany(mf, 3);
		
		String url = buildRelationUrl(mf, "mtSerieses");
		response = requestForBody(null, url);
		writeDto(response, JsonApiResourceNames.MANUFACTURER, "relation-mtSerieses");
		List<MtSeriesDto> mtSeries = getList(response, MtSeriesDto.class);
		assertThat(mtSeries.size(), equalTo(3));
		
		
		url = buildRelationUrl(mf, "mtSerieses") + new MyJsonApiUrlBuilder("?").page(2, 0).build();
		response = requestForBody(null, url);
		writeDto(response, JsonApiResourceNames.MANUFACTURER, "relation-mtSerieses");
		mtSeries = getList(response, MtSeriesDto.class);
		assertThat(mtSeries.size(), equalTo(2));
	}


	@Override
	protected String getResourceName() {
		return JsonApiResourceNames.MANUFACTURER;
	}
}
