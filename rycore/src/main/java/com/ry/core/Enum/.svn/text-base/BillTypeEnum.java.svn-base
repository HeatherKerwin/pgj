package com.ry.core.Enum;

import java.util.ArrayList;
import java.util.List;
/**
 * 票据类型
 * @author ZWD
 *
 */
public enum BillTypeEnum {
	ZHI(1,"纸票"),
	DIAN(2,"电票");
	
	private final Integer value;
	private final String billType;
	private static List<BillTypeEnum> list;
	
	public static String getBillType(Integer value){
		initList();
		for(BillTypeEnum billTypeEnum : list){
			if(billTypeEnum.value == value){
				return billTypeEnum.billType;
			}
		}
		return null;
	}
	
	public static void initList(){
		if(list == null){
			list = new ArrayList<BillTypeEnum>();
			list.add(ZHI);
			list.add(DIAN);
		}
	}
	
	BillTypeEnum(Integer value , String billType){
		this.value = value;
		this.billType = billType;
	}
	
	public Integer getValue() { return value; }
	
	public String getBillType() { return billType; }
	
}
