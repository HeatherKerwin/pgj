package com.ry.core.Enum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 企业订单
 * @ClassName: ValidType 
 * @Description: 生效状态 ： 生效  0 失效 1
 * @author li.xiaofei
 * @date 2016.3.5
 */
public enum OrderState {

	INVALID(0,"无效订单"),
	BECONFIRM(1,"待确认"),
	BETICKET(2,"待验票"),
	COMPLETE(3,"已完成"),
	BERECEIVABLES(4,"待收款"),
	FAIL(-1,"订单失败"),
	NOAUDIT(-2,"取消订单（待复核）");
	private OrderState(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	private Integer code;
	private String desc;
	private static List<OrderState> list;
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public static Object getEnumMap() {
		  Map<String,String> map = new HashMap<String,String>();
		  OrderState[] values =  values();
	        for (OrderState item : values) {
	            map.put(item.code+"", item.desc);
	        }
	        return map;
	}
	
	public static String getOrderState(Integer code){
		initList();
		for(OrderState orderState : list){
			if(orderState.code == code){
				return orderState.desc;
			}
		}
		return null;
	}
	
	public static void initList(){
		if(list == null){
			list = new ArrayList<OrderState>();
			list.add(INVALID);
			list.add(BECONFIRM);
			list.add(BETICKET);
			list.add(COMPLETE);
			list.add(BERECEIVABLES);
			list.add(FAIL);
			list.add(NOAUDIT);
		}
	}
	
}