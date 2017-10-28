package com.go2wheel.api;

import java.io.IOException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.go2wheel.WxBase;

public class TestAutoReplyRule extends WxBase {
	
	@Autowired
	private AutoReplyRule aru;
	
	@Test
	public void t() throws JsonParseException, JsonMappingException, IOException {
		String s = aru.getAutoReplySettingString();
		printme(s);
	}

}
