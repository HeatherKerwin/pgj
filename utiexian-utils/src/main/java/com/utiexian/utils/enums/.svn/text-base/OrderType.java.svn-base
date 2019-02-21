package com.utiexian.utils.enums;

/**
 * 订单类型（1银票订单、2商票订单、3批量订单）
 * @author WKX
 */
public enum OrderType{
	
	DISCOUNTRECORD(1,"贴现订单（银）"),DISCOUNTRECORDSP(2,"贴现订单（商）"),DISCOUNTRECORDPL(3,"贴现订单（批量）"),
	DISTRIBUTEORDER(4,"接单订单（银）"),DISTRIBUTEORDERSP(5,"接单订单（商）"),DISTRIBUTEORDERPL(6,"接单订单（批量）"),
	INQUIRYREPLY(7,"查询查复"),
	DISCOUNTRECORDUNION(8,"贴现订单（兴）"),DISTRIBUTEORDERUNION(9,"接单订单（兴）");
	
	private Integer key;
	private String value;
	
	OrderType(Integer key,String value){
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
	
	public static OrderType valueOf(int falg){
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