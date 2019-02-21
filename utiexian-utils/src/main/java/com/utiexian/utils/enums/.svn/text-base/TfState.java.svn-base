package com.utiexian.utils.enums;

/**
 * 公用状态（是否）
 * @author WKX
 */
public enum TfState{
	
	F(0,"否"),T(1,"是");
	
	private Integer key;
	private String value;
	
	TfState(Integer key,String value){
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
	
	public static TfState valueOf(int falg){
		switch (falg) {
		case 0:
			return F;
		case 1:
			return T;
		}
		return null;
	}
}