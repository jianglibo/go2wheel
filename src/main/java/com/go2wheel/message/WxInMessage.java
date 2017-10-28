package com.go2wheel.message;

public abstract class WxInMessage extends WxBody {

	private long msgId;
	
	public long getMsgId() {
		return msgId;
	}

	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}
}
