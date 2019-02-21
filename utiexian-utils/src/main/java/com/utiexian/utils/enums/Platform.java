package com.utiexian.utils.enums;

/**
 * 平台：IOS、ANDROID、PC、WAP
 * @author WKX
 */
public enum Platform{
	
	IOS(1,"IOS"),ANDROID(2,"ANDROID"),PC(3,"PC"),WAP(4,"WAP");
	
	private Integer key;
	private String value;
	
	Platform(Integer key,String value){
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