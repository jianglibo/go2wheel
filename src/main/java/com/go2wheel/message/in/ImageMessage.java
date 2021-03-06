package com.go2wheel.message.in;

import com.go2wheel.message.WxInMessage;

public class ImageMessage extends WxInMessage {

	private String picUrl;
	private String mediaId;
	
	public ImageMessage() {
		super();
		setMsgType(WxMessageType.image);
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	
	
}
