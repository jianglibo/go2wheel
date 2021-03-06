package com.go2wheel.katharsis.rest.medium;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.go2wheel.KatharsisBase;
import com.go2wheel.config.ApplicationConfig;
import com.go2wheel.config.JsonApiResourceNames;
import com.go2wheel.katharsis.dto.MediumDto;

public class TestUpload extends KatharsisBase {
	
	@Autowired
	private ApplicationConfig appConfig;
	
	@Before
	public void b() throws JsonParseException, JsonMappingException, IOException {
		deleteAllMedia();
		initTestUser();
	}
	
	@Test
	public void t() throws ClientProtocolException, IOException {
		assertTrue("outUrlBase end with /", appConfig.getOutUrlBase().endsWith("/"));
		assertFalse("should not contain {", appConfig.getOutUrlBase().contains("{"));
		assertFalse("should not contain {", appConfig.getUploadLinkBase().contains("{"));
		HttpResponse apacheResponse = uploadFile(jwt1, Paths.get("fixturesingit", "v.js"), Paths.get("fixturesingit", "th.jpg"));
		String url = apacheResponse.getFirstHeader("location").getValue();
		// url: http://localhost/jsonapi/media?filter[id]=524288&filter[id]=524289
		response = requestForBody(jwt1, url);
		writeDto(response, getResourceName(), "getByIds");
		List<MediumDto> media = getList(response, MediumDto.class);
		assertThat(media.size(), equalTo(2));
		
	}
	
	@Test
	public void t1() throws ClientProtocolException, IOException {
		HttpResponse apacheResponse = uploadFile(jwt1, Paths.get("fixturesingit", "th.jpg"));
		String url = apacheResponse.getFirstHeader("location").getValue();
		response = requestForBody(jwt1, url);
		List<MediumDto> media = getList(response, MediumDto.class);
		MediumDto m = media.get(0);
		assertThat(m.getContentType(), equalTo("application/octet-stream"));
		assertTrue("should end with .jpg", m.getOriginName().endsWith(".jpg"));
		assertTrue("should end with .jpg", m.getUrl().endsWith(".jpg"));
	}

	@Override
	protected String getResourceName() {
		return JsonApiResourceNames.MEDIUM;

	}

}
