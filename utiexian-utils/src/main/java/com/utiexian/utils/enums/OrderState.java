package com.utiexian.utils.enums;

/**
 * 状态（无效订单0、待确认1、背书[确认已背书]2、待付款4、已完成3、已付款5、待复核6、7确认背书[确认签收].....）
 * @author WKX
 */
public enum OrderState{
	
	INVALID(0,"无效订单"),UNCONFIRM(1,"待确认（派单中）"),CONFIRM(2,"验票中（背书中）确认已背书"),FINISH(3,"已完成"),WAITPAY(4,"待付款（预打款）"),PAY(5,"已付款（收款中）"),EXAMINE(6,"待复核"),CONFIRMENDORSE(7,"确认背书（确认已签收）");
	
	private Integer key;
	private String value;
	
	OrderState(Integer key,String value){
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
	
	public static OrderState valueOf(int falg){
		switch (falg) {
		case 0:
			return INVALID;
		case 1:
			return UNCONFIRM;
		case 2:
			return CONFIRM;
		case 3:
			return FINISH;
		case 4:
			return WAITPAY;
		case 5:
			return PAY;
		case 6:
			return EXAMINE;
		}
		return null;
	}
	
	/**
	 * 测试：两种方式都可以取值
	 * @param args
	 */
	public static void main(String[] args) {
		System.err.println(OrderState.valueOf(0));
		System.err.println(OrderState.valueOf("INVALID"));
		System.err.println(OrderState.CONFIRM.getKey());
	}
}