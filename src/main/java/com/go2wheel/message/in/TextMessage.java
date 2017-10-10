package com.go2wheel.message.in;

import com.go2wheel.message.WxInMessage;

public class TextMessage extends WxInMessage {

	private String content;
	
	public TextMessage() {
		super();
		setMsgType(WxMessageType.text);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
