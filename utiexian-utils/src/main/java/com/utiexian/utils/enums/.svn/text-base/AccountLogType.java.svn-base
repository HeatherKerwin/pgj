package com.utiexian.utils.enums;

/**
 * 账户交易类型（账户资金记录表用：充值、提现、保证金、查询查复、服务费、购买会员）
 * @author WKX
 */
public enum AccountLogType{
	
	IN(1,"充值"),OUT(2,"提现"),
	ORDERIN(3,"保证金（退还）"),ORDEROUT(4,"保证金（支出）"),
	INQUIRYREPLYOUT(5,"查询查复（支出）"),INQUIRYREPLYBACK(6,"查询查复（退还）"),
	SERVER_IN(7,"服务费（退还）"),SERVER_OUT(8,"服务费（支出）"),VIP(9,"购买会员"),
	ECONTRACT_IN(10,"电子签章费（退还）"),ECONTRACT_OUT(10,"电子签章费（支出）");
	
	private Integer key;
	private String value;
	
	AccountLogType(Integer key,String value){
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
	
	public static AccountLogType valueOf(int falg){
		switch (falg) {
		case 1:
			return IN;
		case 2:
			return OUT;
		case 3:
			return ORDERIN;
		case 4:
			return ORDEROUT;
		case 5:
			return INQUIRYREPLYOUT;
		case 6:
			return INQUIRYREPLYBACK;
		}
		return null;
	}
}