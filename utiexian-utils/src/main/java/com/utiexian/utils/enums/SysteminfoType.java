package com.utiexian.utils.enums;

/**
 * 消息类型
 * @author WKX
 */
public enum SysteminfoType{
	
	DISCOUNTRECORD("企业订单（银票）"),DISTRIBUTEORDER("机构订单（银票）"),DISCOUNTRECORDSP("企业订单（商票）"),
	DISTRIBUTEORDERSP("机构订单（商票）"),DISCOUNTRECORDPL("企业订单（批量）"),DISTRIBUTEORDERPL("机构订单（批量）"),
	DISCOUNTRECORDUNION("企业订单（跨平台）"),DISTRIBUTEORDERUNION("机构订单（跨平台）"),
	DISPATCH("待报价订单"),INQUIRYREPLY("查询查复"),PREFERENTIALINFO("优惠消息"),SYSTEM("系统消息"),OTHER("其他");
	
	public String name;
	private String value;
	
	SysteminfoType(String name){
		this.name = name;
		this.value = this.name();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}