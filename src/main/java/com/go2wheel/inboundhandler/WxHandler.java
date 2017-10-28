package com.go2wheel.inboundhandler;

import org.springframework.beans.factory.annotation.Autowired;

import com.go2wheel.message.WxBodyUtil;

public class WxHandler {
	
	@Autowired
	protected WxBodyUtil wxUtil;
}
