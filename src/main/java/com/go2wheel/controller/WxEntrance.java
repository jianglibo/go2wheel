package com.go2wheel.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.go2wheel.inboundhandler.InEventHandler;
import com.go2wheel.inboundhandler.InMessageHandler;
import com.go2wheel.message.WxBody;
import com.go2wheel.message.WxBody.WxMessageType;
import com.go2wheel.message.WxBodyUtil;
import com.go2wheel.message.WxInEvent;
import com.go2wheel.message.WxInMessage;

@Controller
public class WxEntrance {
	
	@Autowired
	private WxBodyUtil wxBodyUtil;
	
	@Autowired
	private InEventHandler inEventHandler;
	
	@Autowired
	private InMessageHandler inMessageHandler;

	@RequestMapping(path = "/wxentrance")
	@ResponseBody
	public String dispatch(@RequestBody String body) {
		try {
			WxBody wxbody = wxBodyUtil.deserialize(body);
			WxMessageType wmt = wxbody.getMsgType();
			switch (wmt) {
			case event:
				return inEventHandler.handle((WxInEvent) wxbody);
			default:
				return inMessageHandler.handle((WxInMessage) wxbody);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
}
