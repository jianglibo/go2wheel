package com.go2wheel.message.in;

import com.go2wheel.message.WxInMessage;

public class LinkMessage extends WxInMessage {

	private String title;
	private String description;
	private String url;
	
	public LinkMessage() {
		super();
		setMsgType(WxMessageType.link);
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
