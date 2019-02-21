package com.utiexian.utils.enums;

/**
 * 审核状态（待审核、通过、未通过）
 * @author WKX
 */
public enum CheckState{
	
	NONE(0,"未认证"),PENDING(1,"待审核"),PASS(2,"通过"),NOPASS(3,"未通过");
	
	private Integer key;
	private String value;
	
	CheckState(Integer key,String value){
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
	
	public static CheckState valueOf(int falg){
		switch (falg) {
		case 0:
			return NONE;
		case 1:
			return PENDING;
		case 2:
			return PASS;
		case 3:
			return NOPASS;
		}
		return null;
	}
}