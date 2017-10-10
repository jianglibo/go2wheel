package com.go2wheel.inboundhandler;

import org.springframework.stereotype.Component;

import com.go2wheel.message.WxInEvent;
import com.go2wheel.message.passivereply.MusicOutMessage.MusicOutMessageBuilder;

@Component
public class InEventHandler {

	public String handle(WxInEvent event) {
		switch (event.getEvent()) {
		case CLICK:
			break;
		case LOCATION:
			break;
		case SCAN:
			break;
		case subscribe:
			return subscribe(event);
		case unsubscribe:
			break;
		default:
			break;
		}
		return null;
	}

	/**
	 * reply with a composed message.
	 * @param event
	 * @return
	 */
	private String subscribe(WxInEvent event) {
		new MusicOutMessageBuilder(event.getFromUserName(), event.getToUserName());
		return null;
	}
}
