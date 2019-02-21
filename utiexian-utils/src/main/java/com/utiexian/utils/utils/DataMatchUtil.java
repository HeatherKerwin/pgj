package com.utiexian.utils.utils;

/**
 * 票据数据转换
 * [承兑行、票据]
 * @author WKX
 */
public class DataMatchUtil {
	
	/**
	 * 承兑行类型转  中文  展示[新APP接口用]
	 * @author WKX
	 * @param type 承兑行类型[数字]
	 * @since 2016年1月22日 下午3:11:06
	 */
	public static String getCDHByTypeFromNew(Integer type) {
		String cdh = "";
		if(type!=null){
			if(type==1){//国股
				cdh = "国股";
			}else if(type==2){//城商（小商）
				cdh = "小商";
			}else if(type==3){//外资
				cdh = "外资";
			}else if(type==4){//农商
				cdh = "农商";
			}else if(type==5){//农合
				cdh = "农合";
			}else if(type==6){//农信
				cdh = "农信";
			}else if(type==7){//村镇
				cdh = "村镇";
			}else if(type==8){//城商（大商）
				cdh = "大商";
			}
		}
		return cdh;
	}
	
	/**
	 * 根据承兑行枚举获取其拼音
	 * @author WKX
	 * @param data
	 * @since 2016年3月15日 下午5:54:15
	 */
	public static String getNameByStateCDH(Integer data) {
		String value = "";
		if(data!=null){
			if(data==1){
				value = "guogu";
			}else if(data==2){
				value = "chengshang";//小商（城商）
			}else if(data==3){
				value = "waizi";
			}else if(data==4){
				value = "nongshang";
			}else if(data==5){
				value = "nonghe";
			}else if(data==6){
				value = "nongxin";
			}else if(data==7){
				value = "cunzhen";
			}else if(data==8){//大商（城商）APP2.2新添加
				value = "dashang";
			}
		}
		return value;
	}
}