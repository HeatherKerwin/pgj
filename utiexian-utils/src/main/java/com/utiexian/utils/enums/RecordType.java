package com.utiexian.utils.enums;

/**
 * 类型（IN待入账、HOLD持有中、OUT已出票）
 * @author WKX
 */
public enum RecordType{
	
	IN(0,"待入账"),HOLD(1,"持有中"),OUT(2,"已出票");
	
	private Integer key;
	private String value;
	
	RecordType(Integer key,String value){
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