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
public enum InvoiceState {

	SENT(0,"已发送"),
	UNSENT(1,"未发送"),
    ;
	private InvoiceState(Integer code, String desc) {
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
		  InvoiceState[] values =  values();
	        for (InvoiceState item : values) {
	            map.put(item.code+"", item.desc);
	        }
	        return map;
	}
	  
}
