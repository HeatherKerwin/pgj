package com.utiexian.utils.enums;

/**
 * 提现状态（待审核（待转账）、成功、失败、取消、待入账）
 * @author WKX
 */
public enum AccountLogState{
	
	PENDING(1,"待审核（待转账）"),SUCCESS(2,"成功（提现和充值）"),FAILED(3,"失败（提现和充值）"),CANCEL(4,"取消"),WAITIN(5,"待入账（充值）");
	
	private Integer key;
	private String value;
	
	AccountLogState(Integer key,String value){
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
	
	public static AccountLogState valueOf(int falg){
		switch (falg) {
		case 1:
			return PENDING;
		case 2:
			return SUCCESS;
		case 3:
			return FAILED;
		case 4:
			return CANCEL;
		}
		return null;
	}
}