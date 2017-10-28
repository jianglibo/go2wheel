package com.go2wheel.message.event;

import com.go2wheel.message.WxInEvent;

public class ViewEvent extends WxInEvent {
	
	private String menuId;
	
	public ViewEvent() {
		super();
		setEvent(WxEventType.VIEW);
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
}
