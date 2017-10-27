package com.go2wheel.katharsis.rest.mtmodel;

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
import com.go2wheel.JsonApiPostBodyWrapperBuilder;
import com.go2wheel.KatharsisBase;
import com.go2wheel.JsonApiPostBodyWrapper.CreateOneBody;
import com.go2wheel.config.JsonApiResourceNames;
import com.go2wheel.constant.Market;
import com.go2wheel.constant.EnginType.CoolType;
import com.go2wheel.constant.EnginType.CylinderHead;
import com.go2wheel.constant.EnginType.EnginePosition;
import com.go2wheel.constant.EnginType.FinalDriveType;
import com.go2wheel.constant.EnginType.IgnitionType;
import com.go2wheel.domain.MtModel;
import com.go2wheel.domain.MtSeries;
import com.go2wheel.katharsis.dto.MtModelDto;
import com.go2wheel.katharsis.rest.mtseries.MtSeriesTUtil;
import com.go2wheel.util.MyJsonApiUrlBuilder;

public class TestMtModelApi extends KatharsisBase {
	
	@Autowired
	private MtSeriesTUtil msUtil;
	
	@Before
	public void b() throws JsonParseException, JsonMappingException, IOException {
		initTestUser();
		deleteAllMtModels();
	}
	
	@Test
	public void tAddOneByRest() throws JsonParseException, JsonMappingException, IOException {
//		private static String[] initProperties = new String[] {"name", "description"};
		MtSeries ms = msUtil.createOne("versys");
		JsonApiPostBodyWrapper<CreateOneBody> jbw = JsonApiPostBodyWrapperBuilder.getOneBuilder(getResourceName())
				.addAttributePair("name", "versys-650")
				.addAttributePair("description", "very good.")
				.addAttributePair("market", Market.CHINA)
				.addAttributePair("finalDriveType", FinalDriveType.CHAIN)
				.addAttributePair("coolType", CoolType.LIQUID)
				.addAttributePair("cylinderHead", CylinderHead.DOHC)
				.addAttributePair("enginPosition", EnginePosition.INLINE)
				.addAttributePair("ignitionType", IgnitionType.FULL_TRANSISTORIZED)
				.addOneRelation("mtSeries", JsonApiResourceNames.MT_SERIES, ms.getId())
				.build();
		
		String s = objectMapper.writeValueAsString(jbw);
		writeDto(s, getResourceName(), "postcontent");
		
		response = postItemWithContent(s, jwt1);
		writeDto(response, getResourceName(), ActionNames.POST_RESULT);
		
		MtModelDto newMtModel = getOne(response.getBody(), MtModelDto.class);
		assertThat(newMtModel.getName(), equalTo("versys-650"));
		
		MtModel m = mtModelRepo.findOne(newMtModel.getId());
		
		assertThat(m.getName(), equalTo("versys-650"));
		
		MyJsonApiUrlBuilder b = new MyJsonApiUrlBuilder("?");
		b.filters("name", "versys-650");
		String url = getBaseURI() +  b.build();
		
		response = requestForBody(null, url);
		writeDto(response, getResourceName(), ActionNames.GET_LIST);
		List<MtModelDto> newMtModels = getList(response, MtModelDto.class);
		assertThat(newMtModels.size(), equalTo(1));
	}

	@Override
	protected String getResourceName() {
		return JsonApiResourceNames.MT_MODEL;
	}

}
