package com.ry.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;


public class DateUtil {
	
	/**
	 * 日期格式 yyyy-MM-dd HH:mm:ss </br> 如：2011-03-03 12:30:12
	 */
	public static final String FORMART = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 日期格式 yyyy-MM-dd HH:mm</br> 如：2011-03-03 12:12
	 */
	public static final String FORMART2 = "yyyy-MM-dd HH:mm";
	/**
	 * 日期格式 yyyy-MM-dd</br> 如：2011-03-03
	 */
	public static final String FORMART3 = "yyyy-MM-dd";
	/**
	 * 日期格式 yyyy-MM</br> 如：2011-03
	 */
	public static final String FORMART4 = "yyyy-MM";
	
	/**
	 * <p class="detail">
	 * 将日期按照{@link Formart} 格式返回 字符串
	 * </p>
	 * 
	 * @author <a href="mailto:dengqinglong@wokejia.com ">邓庆龙</a> 2014-5-28
	 *         上午10:29:44
	 * @param date
	 *            日期
	 * @param formart
	 *            日期格式
	 * @return String 格式化之后的日期字符串
	 */
	public static String formart(Date date, String format) {
		SimpleDateFormat simple = new SimpleDateFormat(format);
		return simple.format(date);
	}
	
	
	/**
	 * <p class="detail">
	 * 将字符串按照{@link Formart} 格式返回 @see java.util.Date
	 * </p>
	 * 
	 * @author <a href="mailto:dengqinglong@wokejia.com ">邓庆龙</a> 2014-5-28
	 *         上午10:29:44
	 * @param date
	 *            日期字符串
	 * @param formart
	 *            日期格式
	 * @return Date 格式化之后的日期对象
	 * @throws ParseException
	 *             传入的 date字符串，与formart格式不一致
	 */
	public static Date parser(String date, String formart) throws ParseException {
		SimpleDateFormat simple = new SimpleDateFormat(formart);
		return simple.parse(date);
	}
	
	/**
	 * 增加日期
	 * 
	 * @param date
	 * @param amount
	 *            正数往后，负数往前推
	 * @return
	 */
	public static Date addDays(Date date, int amount) {
		Date d = DateUtils.addDays(date, amount);
		d = DateUtils.truncate(d, Calendar.DAY_OF_MONTH);
		return d;
	}

	public static Date addDays(int amount) {
		return addDays(new Date(), amount);
	}
	/**
	 * 获取日期的初始时间
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date getStartDate(Date date) {
		
		try {
			return parser(formart(date, FORMART3), FORMART3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取日期的初始时间
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date getMonthStartDate(Date date) {
		
		try {
			return parser(formart(date, FORMART4), FORMART4);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(Date smdate,Date bdate) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    } 
    
    /**  
     * 计算两个日期之间相差的分钟数
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @author lvyanqin
     * @date 2016-3-6
     * @throws ParseException  
     */    
    public static int minusBetween(Date smdate,Date bdate) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*60);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    } 
    
    /**
     * 
     * 计算两个日期之间相差的月数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差月数 
     * @throws ParseException
     * @date 2016年2月17日
     * @author lvyanqin
     */
    public static int monthsBetween(Date smdate,Date bdate) throws ParseException{
    	int result = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(smdate);
        c2.setTime(bdate);
        int month2 = c2.get(Calendar.MONTH);
        int month1 = c1.get(Calendar.MONTH);
        int months = 0;
        int years = 0;
        int year1 = c1.get(Calendar.YEAR);
		int year2 = c2.get(Calendar.YEAR);
        if(month2 - month1 < 0){
        	months = 12 - month1 + month2;
        	years = year2 - year1 - 1;
        }else {
        	months = month2 - month1;
        	years = year2 - year1;
        }
        result = 12* years + months;
        return result;
	}
    
    /**
	 * 获取上月第一天
	 * @author WKX
	 * @since 2016年1月21日 下午4:24:01
	 */
	public static Date getPreviousMonthFirst() {
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(5, 1);
		lastDate.add(2, -1);
		return lastDate.getTime();
	}
	
	/**
	 * 获取上月最后一天
	 * @author WKX
	 * @since 2016年1月21日 下午4:24:19
	 */
	public static Date getPreviousMonthEnd() {
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(2, -1);
		lastDate.set(5, 1);
		lastDate.roll(5, -1);
		return lastDate.getTime();
	}
	
	public static void main(String[] args) {
		try {
			System.err.println(parser("2015-12-5", "yyyy-MM-dd").getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 日期修改月份
	 * @param date
	 * @param addMonths
	 * @return
	 * @date 2016年1月13日
	 * @author lvyanqin
	 */
	public static Date addMonth(Date date, int addMonths){
		Calendar cal = Calendar.getInstance();  
		cal.setTime(date);
		cal.add(Calendar.MONTH,addMonths);
		date = cal.getTime();
		return date;
	}
	
	
	/**
	 * String类型日期转换为Long 类型
	 * @param date 
	 * @param DateType
	 * @author li.xiafei
	 * @return
	 */
	public static Long dataTurntoInt(String date, String DateType) {

		Calendar c = Calendar.getInstance();
		try {
			c.setTime(new SimpleDateFormat(DateType).parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return c.getTimeInMillis();
	}
	
	/**
	 * 长整型转时间（字符串）
	 * @author WKX
	 * @param dateFormat
	 * @param millSec
	 * @since 2016年3月16日 下午2:42:11
	 */
	public static String transferLongToString(String dateFormat, Long millSec) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = new Date(millSec);
		return sdf.format(date);
	}
	
	
	/**
	 * 获取星期数
	 * @param dt
	 * @return
	 * @date 2016年1月25日
	 * @author lvyanqin
	 */
	public static int getWeekOfDate(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return w;
    }
	
	/**  
     * 计算两个日期之间相差的分钟数
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @author cx
     * @date 2016-5-25
     * @throws ParseException  
     */    
    public static int calMinus(Date smdate,Date bdate) throws ParseException {    
        long time1 = smdate.getTime();               
        long time2 = bdate.getTime();         
        long between_days=(time2-time1)/(1000*60);  
        return Integer.parseInt(String.valueOf(between_days));           
    } 
    
    /**
     * 获取 相差的 秒数
     * @author WKX
     * @param smdate 小的时间
     * @param bdate 大的时间
     * @throws ParseException
     * @since 2016年6月16日 下午8:15:49
     */
    public static int calSeconds(Date smdate,Date bdate) throws ParseException{
    	long time1 = smdate.getTime();               
        long time2 = bdate.getTime();         
        long between_days=(time2-time1)/(1000);
        return Integer.parseInt(String.valueOf(between_days));
    }
}