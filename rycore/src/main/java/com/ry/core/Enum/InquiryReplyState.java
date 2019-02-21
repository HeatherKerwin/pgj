package com.ry.core.Enum;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: InquiryReplyState 
 * @Description: 验票状态：0待验票、1验票成功、2验票失败
 * @author li.xiaofei
 * @date 2016.3.5
 */
public enum InquiryReplyState {

	WAIT(0,"待验票"),
	SUCCESSED(1,"验票成功"),
	FILTERED(2,"验票失败");
	private InquiryReplyState(Integer code, String desc) {
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
		  InquiryReplyState[] values =  values();
	        for (InquiryReplyState item : values) {
	            map.put(item.code+"", item.desc);
	        }
	        return map;
	}
	
	public static InquiryReplyState getByCode(Integer code){
		InquiryReplyState[] values =  values();
        for (InquiryReplyState item : values){
            if(item.getCode().equals(code)){
                return item;
            }
        }
        return null;
    }
}