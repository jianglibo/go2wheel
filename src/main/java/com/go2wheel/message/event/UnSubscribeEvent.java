package com.go2wheel.message.event;

import com.go2wheel.message.WxInEvent;

public class UnSubscribeEvent extends WxInEvent {
	
	public UnSubscribeEvent() {
		super();
		setEvent(WxEventType.unsubscribe);
	}
}
