package com.utiexian.utils.enums;

/**
 * 备注类型（对订单，对用户）
 * @author WKX
 */
public enum NoteType{
	
	DISCOUNTRECORD(1,"银票订单"),DISCOUNTRECORDSP(2,"商票订单"),DISCOUNTRECORDPL(3,"批量订单"),
	DISTRIBUTEORDER(4,"银票接单订单"),DISTRIBUTEORDERSP(5,"商票接单订单"),DISTRIBUTEORDERPL(6,"批量接单订单"),
	MEMBER(7,"用户");
	
	private Integer key;
	private String value;
	
	NoteType(Integer key,String value){
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
	
	public static NoteType valueOf(int falg){
		switch (falg) {
		case 1:
			return DISCOUNTRECORD;
		case 2:
			return DISCOUNTRECORDSP;
		case 3:
			return DISCOUNTRECORDPL;
		case 4:
			return DISTRIBUTEORDER;
		case 5:
			return DISTRIBUTEORDERSP;
		case 6:
			return DISTRIBUTEORDERPL;
		}
		return null;
	}
}