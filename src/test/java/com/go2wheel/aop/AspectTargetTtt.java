package com.go2wheel.aop;

import org.springframework.stereotype.Component;

@Component
public class AspectTargetTtt {

	public String beAdvised(String s) {
		return s;
	}
}
