package com.ry.core.Enum;

import java.util.ArrayList;
import java.util.List;

/**
 * 导入导出状态
 * @author ZWD
 *
 */
public enum ImportStateEnum {
	NOT_EXPORT(1,"未导出"),
	EXPORT(1,"已导出"),
	IMPORT(2,"已导入");
	
	private final Integer value;
	
	private final String importState;
	
	private static List<ImportStateEnum> list;
	
	public static String GetiImportState(Integer value){
		initList();
		for(ImportStateEnum importStateEnum : list){
			if(importStateEnum.value == value){
				return importStateEnum.importState;
			}
		}
		return null;
	}
	
	ImportStateEnum(Integer value,String importState){
		this.value = value;
		this.importState = importState;
	}
	
	public static void initList(){
		if(list == null){
			list = new ArrayList<ImportStateEnum>();
			list.add(NOT_EXPORT);
			list.add(EXPORT);
			list.add(IMPORT);
		}
	}

	public Integer getValue() { return value; }

	public String getImportState() {
		return importState;
	}

	
}
