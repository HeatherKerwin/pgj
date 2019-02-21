package com.ry.core.Enum;

import java.util.HashMap;
import java.util.Map;

/**
 * 
* @ClassName: ValidType 
* @Description: 生效状态 ： 生效  0 失效 1
* @author li.xiaofei
* @date 2016.3.5
*
 */
public enum ValidType {

	EFFECTIVE("T","有效"),
	INVALID("F","无效"),
    ;
	private ValidType(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	  private String code;
	  private String desc;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
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
		  ValidType[] values =  values();
	        for (ValidType item : values) {
	            map.put(item.code, item.desc);
	        }
	        return map;
	}
	  
}
