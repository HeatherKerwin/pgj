package com.ry.core.Enum;

import java.util.ArrayList;
import java.util.List;

public enum NeedTodoorEnum {
	YES(1,"是"),
	NO(0,"否");
	
	private final Integer value;
	private final String needTodoor;
	private static List<NeedTodoorEnum> list;
	
	public static String getBillType(Integer value){
		initList();
		for(NeedTodoorEnum needTodoorEnum : list){
			if(needTodoorEnum.value == value){
				return needTodoorEnum.needTodoor;
			}
		}
		return null;
	}
	
	public static void initList(){
		if(list == null){
			list = new ArrayList<NeedTodoorEnum>();
			list.add(YES);
			list.add(NO);
		}
	}
	
	NeedTodoorEnum(Integer value , String needTodoor){
		this.value = value;
		this.needTodoor = needTodoor;
	}
	public Integer getValue() { return value; }

	public String getNeedTodoor() { return needTodoor; }
	
}
