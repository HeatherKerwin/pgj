package com.utiexian.utils.enums;

/**
 * 公用状态（是否）
 * @author WKX
 */
public enum YnState{
	
	N(0,"否"),Y(1,"是");
	
	private Integer key;
	private String value;
	
	YnState(Integer key,String value){
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
	
	public static YnState valueOf(int falg){
		switch (falg) {
		case 0:
			return N;
		case 1:
			return Y;
		}
		return null;
	}
}