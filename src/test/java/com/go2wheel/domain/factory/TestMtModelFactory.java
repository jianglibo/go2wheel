package com.go2wheel.domain.factory;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.regex.Matcher;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.go2wheel.KatharsisBase;
import com.go2wheel.api.util.SizeConverter;
import com.go2wheel.domain.MtModel;
import com.go2wheel.katharsis.exception.MissingRequiredFieldException;
import com.go2wheel.katharsis.rest.mtseries.MtSeriesTUtil;

public class TestMtModelFactory extends KatharsisBase {
	
	@Autowired
	private MtModelFactory mmFactory;
	
	@Autowired
	private MtSeriesTUtil msUtil;
	
	@Before
	public void b() throws JsonParseException, JsonMappingException, IOException {
		initTestUser();
		deleteAllMtSerieses();
	}
	
	@Test(expected=MissingRequiredFieldException.class)
	public void t() throws IOException {
		MtModel mm = mmFactory.fromPropertyFile(readModelFixtureStream("versys650"));
		assertThat(mm.getBore(), equalTo(830));
	}
	
	@Test
	public void tt() throws IOException {
		msUtil.createOne("versys");
		MtModel mm = mmFactory.fromPropertyFile(readModelFixtureStream("versys650"));
		assertThat(mm.getBore(), equalTo(830));
		mm = mtModelRepo.save(mm);
	}
	
	@Test
	public void tPtn() {
		Matcher m = SizeConverter.sizePtn.matcher("1.33in");
		assertTrue(m.matches());
		assertThat(m.group(1), equalTo("1.33"));
		assertThat(m.group(2), equalTo("in"));
		
		
		m = SizeConverter.sizePtn.matcher(" 1.33in ");
		assertTrue(m.matches());
		assertThat(m.group(1), equalTo("1.33"));
		assertThat(m.group(2), equalTo("in"));
		
		m = SizeConverter.sizePtn.matcher(" 1.33 in ");
		assertTrue(m.matches());
		assertThat(m.group(1), equalTo("1.33"));
		assertThat(m.group(2), equalTo("in"));
	}
	
	@Test
	public void tConverter() {
		int i = SizeConverter.toOneTenth("1.33in");
		assertThat(i, equalTo(338));
		
		float f = SizeConverter.toInch(337, 2);
		assertThat(f, equalTo(1.33F));
		
		i = SizeConverter.toOneTenth("5.5gal");
		assertThat(i, equalTo(208));
		
		i = SizeConverter.toOneTenth("476.3lb");
		assertThat(i, equalTo(2160));
		
		f = SizeConverter.toPound(i, 1);
		assertThat(f, equalTo(476.2F));
	}

	@Override
	protected String getResourceName() {
		return null;
	}
}
