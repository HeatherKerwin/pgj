package com.ry.core.Enum;

/**
 * 红包状态（UNUSED未使用、USED已使用、INVALID无效）
 * @author MH
 */
public enum CouponState{
	
	UNUSED(0,"未使用"),USED(1,"已使用"),INVALID(2,"无效");
	
	private Integer key;
	private String value;
	
	CouponState(Integer key,String value){
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