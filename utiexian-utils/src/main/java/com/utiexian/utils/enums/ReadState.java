package com.utiexian.utils.enums;

/**
 * 消息是否读过[枚举]
 * @author WKX
 */
public enum ReadState{
	
	UNREAD("未读"),READ("已读");
	public String name;
	private String value;
	
	ReadState(String name){
		this.name = name;
		this.value = this.name();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}