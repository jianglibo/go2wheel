package com.go2wheel.message.event;

import com.go2wheel.message.WxInEvent;

public class ClickEvent extends WxInEvent {
	
	public ClickEvent() {
		super();
		setEvent(WxEventType.CLICK);
	}
}
