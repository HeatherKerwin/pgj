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
public enum ExpressWay {

	MAIL(0,"平邮"),
	TOPAY(1,"到付"),
    ;
	private ExpressWay(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	  private Integer code;
	  private String desc;
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
		  ExpressWay[] values =  values();
	        for (ExpressWay item : values) {
	            map.put(item.code+"", item.desc);
	        }
	        return map;
	}
	  
}
