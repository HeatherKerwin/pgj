package com.utiexian.utils.enums;

/**
 * 公告类型（显示位置）：INDEX首页、TIEXIAN贴现、INQUIRY查询查复、JIEDAN接单
 * @author WKX
 */
public enum AnnouncementType{
	
	INDEX(1,"首页"),TIEXIAN(2,"贴现"),INQUIRY(3,"查询查复"),JIEDAN(4,"接单");
	
	private Integer key;
	private String value;
	
	AnnouncementType(Integer key,String value){
		this.key = key;
		this.value = value;
	}
	
	public Integer getKey() {
		return key;
	}
	
	public void setKey(Integer key) {
		this.key = key;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
}