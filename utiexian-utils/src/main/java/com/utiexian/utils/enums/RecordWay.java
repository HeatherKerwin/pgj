package com.utiexian.utils.enums;

/**
 * 记账方式（SYSTEM系统、MEMBER用户）
 * @author WKX
 */
public enum RecordWay{
	
	SYSTEM(0,"系统"),MEMBER(1,"用户");
	
	private Integer key;
	private String value;
	
	RecordWay(Integer key,String value){
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