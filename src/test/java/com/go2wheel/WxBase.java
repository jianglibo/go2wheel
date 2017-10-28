package com.go2wheel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.go2wheel.message.WxBodyUtil;

public abstract class WxBase extends Tbase {
	
	@SuppressWarnings("unused")
	private static String mt = "application/vnd.api+json;charset=UTF-8";
	
	private static Path dtosPath = Paths.get("fixturesingit", "wx");
	
	@Autowired
	protected WxBodyUtil wxUtil;

	protected ResponseEntity<String> response;
	
	@Autowired
	@Qualifier("indentOm")
	protected ObjectMapper indentOm;
	
	public String getFixtureWithExplicitName(String fname) throws IOException {
		if (fname.indexOf('.') == -1) {
			fname = fname + ".xml";
		}
		return new String(Files.readAllBytes(dtosPath.resolve(fname)));
	}

}
