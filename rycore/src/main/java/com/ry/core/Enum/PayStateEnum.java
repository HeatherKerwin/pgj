package com.ry.core.Enum;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询查复支付状态
 * @author ZWD
 *
 */
public enum PayStateEnum {
	WAIT_PAY(0,"待付款"),
	PAY_SUCCESS(1,"支付成功"),
	REFUNDED(2,"已退款"),
	REFUNDING(2,"退款中");
	private final Integer value;
	
	private final String payState;
	
	private static List<PayStateEnum> list;
	
	public static String GetPayState(Integer value){
		initList();
		for(PayStateEnum payStateEnum : list){
			if(payStateEnum.value == value){
				return payStateEnum.payState;
			}
		}
		return null;
	}
	
	PayStateEnum(Integer value,String importState){
		this.value = value;
		this.payState = importState;
	}
	
	public static void initList(){
		if(list == null){
			list = new ArrayList<PayStateEnum>();
			list.add(WAIT_PAY);
			list.add(PAY_SUCCESS);
			list.add(REFUNDED);
			list.add(REFUNDING);
		}
	}

	public Integer getValue() { return value; }

}
