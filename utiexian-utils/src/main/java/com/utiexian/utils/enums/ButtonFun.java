package com.utiexian.utils.enums;

/**
 * 按钮功能：NONE无（不能关闭）、BACK返回、CLOSE关闭
 * @author WKX
 */
public enum ButtonFun{
	
	NONE(1,"无（不能关闭）"),BACK(2,"返回"),CLOSE(3,"关闭");
	
	private Integer key;
	private String value;
	
	ButtonFun(Integer key,String value){
		this.key = key;
		this.value = value;
	}
	
	public Integer getKey() {
		return key;
	}
	
	public void setKey(Integer key) {
		this.key = key;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
}