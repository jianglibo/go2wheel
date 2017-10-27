package com.go2wheel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@RequestMapping(path = "/")
	@ResponseBody
	public String dispatch() {
		return "Hello!";
	}
	
	
	@RequestMapping(path = "/adminarea/importmtmodel")
	@ResponseBody
	public String importMtModel() {
		return "Hello!";
	}

}
