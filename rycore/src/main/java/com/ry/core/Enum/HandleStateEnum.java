package com.ry.core.Enum;

import java.util.ArrayList;
import java.util.List;

/**
 * 贴现订单处理状态
 * @author ZWD
 *
 */
public enum HandleStateEnum {
	PENDING(0,"待处理"),
	IN_HAND(1,"处理中"),
	CANCEL_QUERY(2,"取消查询"),
	TO_BANK(3,"已发送银行"),
	QUERY_COMPLETION(4,"查询完成");
	
	private final Integer value;
	private final String handleState;
	private static List<HandleStateEnum> list;
	
	public static String getHandleState(Integer value){
		initList();
		for(HandleStateEnum handleStateEnum : list){
			if(handleStateEnum.value == value){
				return handleStateEnum.handleState;
			}
		}
		return null;
	}
	
	public static void initList(){
		if(list == null){
			list = new ArrayList<HandleStateEnum>();
			list.add(PENDING);
			list.add(IN_HAND);
			list.add(CANCEL_QUERY);
			list.add(TO_BANK);
			list.add(QUERY_COMPLETION);
		}
	}
	HandleStateEnum (Integer value,String handleState){
		this.value = value;
		this.handleState = handleState;
	}
	public Integer getValue() { return value; }
	public String getHandleState() {
		return handleState;
	}
	
	
}
