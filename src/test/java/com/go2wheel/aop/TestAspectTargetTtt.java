package com.go2wheel.aop;

import org.junit.Test;

import com.go2wheel.Tbase;

public class TestAspectTargetTtt extends Tbase {
	
	@Test
	public void t() {
		AspectTargetTtt att = context.getBean(AspectTargetTtt.class);
		att.beAdvised("abc");
	}

}
