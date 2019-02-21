package com.ry.core.Enum;

import java.util.ArrayList;
import java.util.List;

public enum InquiryReplyHandleStateEnum {
	PENDING(0,"待处理"),
	IN_HAND(1,"处理中"),
	INVALID_ORDERS(2,"无效订单"),
	IN_ORDER(3,"订单进行中"),
	ORDER_COMPLETION(4,"订单完成");
	
	private final Integer value;
	private final String inquiryReplyHandleState;
	private static List<InquiryReplyHandleStateEnum> list;
	
	public static String getInquiryReplyHandleState(Integer value){
		initList();
		for(InquiryReplyHandleStateEnum inquiryReplyHandleStateEnum : list){
			if(inquiryReplyHandleStateEnum.value == value){
				return inquiryReplyHandleStateEnum.inquiryReplyHandleState;
			}
		}
		return null;
	}
	
	public static void initList(){
		if(list == null){
			list = new ArrayList<InquiryReplyHandleStateEnum>();
			list.add(PENDING);
			list.add(IN_HAND);
			list.add(INVALID_ORDERS);
			list.add(IN_ORDER);
			list.add(ORDER_COMPLETION);
		}
	}
	InquiryReplyHandleStateEnum (Integer value,String inquiryReplyHandleState){
		this.value = value;
		this.inquiryReplyHandleState = inquiryReplyHandleState;
	}
	public Integer getValue() { return value; }

	public String getInquiryReplyHandleState() {
		return inquiryReplyHandleState;
	}
	
	
	
}
