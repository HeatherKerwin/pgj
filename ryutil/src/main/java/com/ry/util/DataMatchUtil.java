package com.ry.util;

/**
 * 新老系统数据匹配
 * [承兑行、票据]
 * @author WKX
 */
public class DataMatchUtil {
	
	/**
	 * 承兑行[贴现]老APP保存数据[以保证数据库的数据都是 新的]
	 * @author WKX
	 * @param data 老的转新的
	 * @since 2016年1月22日 下午1:14:21
	 */
	public static Integer toDBFormTX(Integer data) {
		if(data!=null){
			if(data==1){//国股
				data = 1;
			}else if(data==2){//城商
				data = 2;
			}else if(data==3){//三农
				data = 4;
			}else if(data==4){//其他
				data = 7;
			}
		}
		return data;
	}
	
	/**
	 * 承兑行[贴现]数据显示在老APP中
	 * @author WKX
	 * @param data 新的转老的
	 * @since 2016年1月22日 下午2:14:18
	 */
	public static Integer toAPPFormTX(Integer data) {
		if(data!=null){
			if(data==1){//国股
				data = 1;
			}else if(data==2){//城商
				data = 2;
			}else if(data==3||data==7){//其他[外资、村镇]
				data = 4;
			}else if(data==4||data==5||data==6){//三农[农商、农合、农信]
				data = 3;
			}
		}
		return data;
	}
	
	/**
	 * 承兑行[票据]老APP保存数据[以保证数据库的数据都是 新的]
	 * @author WKX
	 * @param data 老的转新的
	 * @since 2016年1月22日 下午1:14:21
	 */
	public static Integer toDBFormPJ(Integer data) {
		if(data!=null){
			if(data==1){//国股
				data = 1;
			}else if(data==2){//城商
				data = 2;
			}else if(data==3){//农商
				data = 4;
			}else if(data==4){//农合
				data = 5;
			}else if(data==5){//农信
				data = 6;
			}
		}
		return data;
	}
	
	/**
	 * 承兑行[票据]老APP保存数据[以保证数据库的数据都是 新的]
	 * @author WKX
	 * @param data 新的转老的
	 * @since 2016年1月22日 下午1:14:21
	 */
	public static Integer toAPPFormPJ(Integer data) {
		if(data!=null){
			if(data==1){//国股
				data = 1;
			}else if(data==2){//城商
				data = 2;
			}else if(data==4){//农商
				data = 3;
			}else if(data==5){//农合
				data = 4;
			}else if(data==6){//农信
				data = 5;
			}else if(data==3||data==7){
				data = 0;//外资、村镇[无匹配值]
			}
		}
		return data;
	}
	
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
	 * 承兑行类型转  中文  展示[老APP接口用]
	 * @author WKX
	 * @param type 承兑行类型[数字]
	 * @since 2016年1月22日 下午3:11:06
	 */
	public static String getCDHByTypeFromOld(Integer type) {
		String cdh = "";
		if(type!=null){
			if(type==1){//国股
				cdh = "国股";
			}else if(type==2){//城商
				cdh = "城商";
			}else if(type==4||type==5||type==6){//外资
				cdh = "三农";
			}else if(type==3||type==7){//村镇
				cdh = "其他";
			}
		}
		return cdh;
	}
	
	/**
	 * 老APP饼图展示[根据老的取匹配数据库新的类型]
	 * @author WKX
	 * @param data 老的转新的
	 * @since 2016年1月22日 下午4:44:26
	 */
	public static Integer toOLDAPPFormBT(Integer data) {
		if(data!=null){
			if(data==1){//国股
				data = 1;
			}else if(data==2){//城商
				data = 2;
			}else if(data==3){//农商
				data = 4;
			}else if(data==4){//农合
				data = 5;
			}else if(data==5){//农信
				data = 6;
			}
		}
		return data;
	}
	
	/**
	 * 订单状态显示中文（根据订单状态orderflag）
	 * @author WKX
	 * @param data
	 * @since 2016年1月23日 上午11:34:06
	 */
	public static String getValueByOrderFlag(Integer data) {
		String value = "未知";
		if(data!=null){
			if(data==1){
				value = "待确认";
			}else if(data==2){
				value = "待验票";
			}else if(data==3){
				value = "已完成";
			}else if(data==4){
				value = "待收款";
			}else if(data==0){
				value = "无效订单";
			}else if(data==-1){
				value = "订单失败";
			}
		}
		return value;
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