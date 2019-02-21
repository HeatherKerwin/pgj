package com.utiexian.utils.enums;

/**
 * 状态（待接单1、接单2、取消3、超时4）
 * @author WKX
 */
public enum DispatchState{
	
	WAITING(1,"待接单"),ACCEPT(2,"已接单"),CANCEL(3,"取消"),OVERTIME(4,"超时");
	
	private Integer key;
	private String value;
	
	DispatchState(Integer key,String value){
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
	
	public static DispatchState valueOf(int falg){
		switch (falg) {
		case 1:
			return WAITING;
		case 2:
			return ACCEPT;
		case 3:
			return CANCEL;
		case 4:
			return OVERTIME;
		}
		return null;
	}
}