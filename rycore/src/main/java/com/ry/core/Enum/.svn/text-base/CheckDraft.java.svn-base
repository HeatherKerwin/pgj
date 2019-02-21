package com.ry.core.Enum;

import java.util.HashMap;
import java.util.Map;

/**
 * （应该是已经弃用）
* @ClassName: ValidType 
* @Description: 生效状态 ： 生效  0 失效 1
* @author li.xiaofei
* @date 2016.3.5
*
 */
public enum CheckDraft {

	SUCCESSED(0,"验票成功"),
	FILTERED(1,"验票失败"),
    ;
	private CheckDraft(Integer code, String desc) {
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
		  CheckDraft[] values =  values();
	        for (CheckDraft item : values) {
	            map.put(item.code+"", item.desc);
	        }
	        return map;
	}
	
	
    public static CheckDraft getByCode(Integer code){
    	CheckDraft[] values =  values();
        for (CheckDraft item : values)
        {
            if(item.getCode().equals(code)){
                return item;
            }
        }
        return null;
    }
	  
}
