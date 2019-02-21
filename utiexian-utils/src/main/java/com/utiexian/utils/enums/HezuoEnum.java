package com.utiexian.utils.enums;

/**
 * 用户合作来源
 */
public enum HezuoEnum{
	
	BAIDU("baidu","百度"),YONYOU("yonyou","用友"),SINA("sina","新浪"),QIHOO360("360","奇虎360"),SOGOU("sougou","搜狗"),
	HONGYANTU("hongyantu","红眼兔");
	
	private String key;
	private String value;
	
	HezuoEnum(String key,String value){
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
}