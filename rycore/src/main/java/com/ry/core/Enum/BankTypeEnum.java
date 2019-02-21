package com.ry.core.Enum;

import java.util.ArrayList;
import java.util.List;

/**
 * 承兑行类型枚举
 * @author ZWD
 *
 */
public enum BankTypeEnum {
	
	GG(1,"国股"),
	XS(2,"小商"),
	WZ(3,"外资"),
	NS(4,"农商"),
	NH(5,"农合"),
	NX(6,"农信"),
	CZ(7,"村镇"),
	DS(8,"大商");
	
	private final Integer value;
	
	private final String bankType;
	
	private static List<BankTypeEnum> list;
	
	public static String GetBankType(Integer value){
		initList();
		for(BankTypeEnum bankTypeEnum : list){
			if(bankTypeEnum.value == value){
				return bankTypeEnum.bankType;
			}
		}
		return null;
	}
	
	BankTypeEnum(Integer value,String bankType){
		this.value = value;
		this.bankType = bankType;
	}
	
	public static void initList(){
		if(list == null){
			list = new ArrayList<BankTypeEnum>();
			list.add(GG);
			list.add(XS);
			list.add(WZ);
			list.add(NS);
			list.add(NH);
			list.add(NX);
			list.add(CZ);
			list.add(DS);
		}
	}

	public Integer getValue() { return value; }

	public String getBankType() { return bankType; }
	
}
