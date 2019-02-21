package com.utiexian.utils.enums;

/**
 * 申诉状态（1待处理、2处理中、3已处理）
 * @author WKX
 */
public enum AppealState{
	
	PENDING(1,"待处理"),HANDLING(2,"处理中"),FINISH(3,"已处理");
	
	private Integer key;
	private String value;
	
	AppealState(Integer key,String value){
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
	
	public static AppealState valueOf(int falg){
		switch (falg) {
		case 1:
			return PENDING;
		case 2:
			return HANDLING;
		case 3:
			return FINISH;
		}
		return null;
	}
}