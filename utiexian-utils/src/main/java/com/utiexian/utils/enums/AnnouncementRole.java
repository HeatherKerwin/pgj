package com.utiexian.utils.enums;

/**
 * 公告范围：ALL全部、ORG机构、BNS企业、ORG_RZ机构认证、BNS_RZ企业认证、NONE未认证
 * @author WKX
 */
public enum AnnouncementRole{
	
	ALL(1,"全部"),ORG(2,"机构"),BNS(3,"企业"),ORG_RZ(4,"机构认证"),BNS_RZ(5,"企业认证"),NONE(6,"未认证");
	
	private Integer key;
	private String value;
	
	AnnouncementRole(Integer key,String value){
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