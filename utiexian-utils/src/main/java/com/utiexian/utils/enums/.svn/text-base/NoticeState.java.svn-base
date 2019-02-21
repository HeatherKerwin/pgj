package com.utiexian.utils.enums;

/**
 * 公用状态（是否）
 * @author WKX
 */
public enum NoticeState{
	
	FESTIVAL(1,"节日"),OFFDAY(2,"非工作日"),ERROR(3,"额度受限");
	
	private Integer key;
	private String value;
	
	NoticeState(Integer key,String value){
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
	
	public static NoticeState valueOf(int falg){
		switch (falg) {
		case 1:
			return FESTIVAL;
		case 2:
			return OFFDAY;
		case 3:
			return ERROR;
		}
		return null;
	}
}