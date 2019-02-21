package com.bbs.kit;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import blade.kit.StringKit;

/**
 * 昵称起名
 */
public class NameKit {
	
	/**
	 * 随机起名
	 * @author WKX
	 * @param phone 注册手机号
	 */
	public static String randomName(String phone){
		String fix = randomChar(5);
		
		String end = "_";
		if(StringKit.isNotBlank(phone) && phone.length()>=4){
			end += phone.substring(phone.length()-4, phone.length());
		}else{
			SimpleDateFormat simple = new SimpleDateFormat("HHmm");
			end += simple.format(new Date());
		}
		return fix + end;
	}
	
	/**
	 * 随机字母
	 * @author WKX
	 * @param size 个数
	 * @since 2016年11月11日 下午3:00:07
	 */
	public static String randomChar(Integer size) {
	    String str = "";
		Random random = new Random();
		if(size==null)size = 5;
	    for( int i = 0; i < size; i ++) {
	        int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;//取得大写还是小写
	        str += (char)(choice + random.nextInt(26));
	    }
	    return str;
	}
	
	/**
	 * 获取手机后六位作为默认密码
	 * @author WKX
	 * @param phone 手机号
	 * @param default_
	 * @since 2016年11月18日 下午7:49:59
	 */
	public static String end4Phone(String phone,String default_){
		if(StringKit.isBlank(phone)){
			if(StringKit.isBlank(default_))default_ = "123456";
			phone = default_;
		}else if(StringKit.isNotBlank(phone) && phone.length()>=6){
			phone = phone.substring(phone.length()-6, phone.length());
		}
		return phone;
	}
	
	public static void main(String[] args) {
		System.err.println(randomName("13818994981"));
		System.err.println(end4Phone("13818994981","123456"));
	}
}