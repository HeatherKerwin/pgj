package com.utiexian.utils.enums;

/**
 * @author ZWD
 * OCR识别的图片类型
 */
public enum OcrGenreEnum {

	DRAFT("DRAFT","票据"),BIZLICENCE("BIZLICENCE","营业执照"),IDCARD("IDCARD","身份证");
	
	private String key;
	private String value;
	
	OcrGenreEnum(String key,String value){
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
