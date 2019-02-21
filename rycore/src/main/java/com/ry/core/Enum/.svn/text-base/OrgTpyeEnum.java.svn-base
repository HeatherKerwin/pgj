package com.ry.core.Enum;

import java.util.ArrayList;
import java.util.List;

public enum OrgTpyeEnum {
	COMPANY(0,"企业"),
	ORG(1,"机构");
	private final Integer value;
	private final String type;
	private static List<OrgTpyeEnum> list;
	
	public static String getOrgType(Integer value){
		initList();
		for(OrgTpyeEnum orgTpyeEnum:list){
			if(orgTpyeEnum.value==value){
				return orgTpyeEnum.type;
			}
		}
		return null;
	}
	
	public static void initList(){
		if(list == null){
			list = new ArrayList<OrgTpyeEnum> ();
			list.add(COMPANY);
			list.add(ORG);
		}
	}
	
	OrgTpyeEnum (Integer value,String type){
		this.value = value;
		this.type = type;
	}

	public Integer getValue() {
		return value;
	}

	public String getType() {
		return type;
	}
	
	
}
