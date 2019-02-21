package com.ry.core.Enum;

import java.util.ArrayList;
import java.util.List;

/**
 * 客服人员前后端
 * @author MH
 */
public enum ServicememberEnum {
	FRONT(0,"前端客服"),
	AFTER(1,"后端客服");
	
private final Integer value;
	
	private final String payState;
	
	private static List<ServicememberEnum> list;
	
	public static String GetPayState(Integer value){
		initList();
		for(ServicememberEnum servicememberEnum : list){
			if(servicememberEnum.value == value){
				return servicememberEnum.payState;
			}
		}
		return null;
	}
	
	ServicememberEnum(Integer value,String importState){
		this.value = value;
		this.payState = importState;
	}
	
	public static void initList(){
		if(list == null){
			list = new ArrayList<ServicememberEnum>();
			list.add(AFTER);
			list.add(FRONT);
		}
	}

	public Integer getValue() { return value; }
}
