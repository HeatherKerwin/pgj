package com.utiexian.utils.enums;

/**
 * 支付方式（充值、提现）
 * @author WKX
 */
public enum PayWay{
	
	CZ_ALIPAY(0,"支付宝（充值）"),
	CZ_BAOFOO(1,"宝付（充值）"),CZ_BILL99(2,"块钱（充值）"),
	TX_BAOFOO(3,"宝付（提现）"),TX_BILL99(4,"块钱（提现）"),TX_OFFLINE(5,"提现（线下）");
	
	private Integer key;
	private String value;
	
	PayWay(Integer key,String value){
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
	
	public static PayWay valueOf(int falg){
		switch (falg) {
		case 1:
			return CZ_BAOFOO;
		case 2:
			return CZ_BILL99;
		case 3:
			return TX_BAOFOO;
		case 4:
			return TX_BILL99;
		case 5:
			return TX_OFFLINE;
		}
		return null;
	}
}