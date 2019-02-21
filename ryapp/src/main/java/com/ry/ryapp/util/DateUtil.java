package com.ry.ryapp.util;

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
	 * 日期格式 yyyy-MM-dd</br> 如：2011-03-03
	 */
	public static final String FORMART2 = "yyyy-MM-dd HH:mm";
	/**
	 * 日期格式HH:mm:ss</br> 如：12:30:12
	 */
	public static final String FORMART3 = "yyyy-MM-dd";
	/**
	 * 日期格式MM-dd</br> 如：12:30:12
	 */
	public static final String FORMART4 = "MM-dd";
	/**
	 * 日期格式MMdd</br> 如：12:30:12
	 */
	public static final String FORMART5 = "MMdd";
	
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
	
	/**
	 * Long转Date[String]
	 * @author WKX
	 * @param dateFormat
	 * @param millSec
	 * @since 2016年1月11日 下午3:21:20
	 */
	public static String transferLongToDate(String dateFormat, Long millSec) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = new Date(millSec);
		return sdf.format(date);
	}
	
	/**
	 * 获取本周一
	 * @author WKX
	 * @since 2016年1月25日 上午9:42:20
	 */
	public static Date getThisWeekMonday(){
		Calendar cal =Calendar.getInstance();
        if(Calendar.SUNDAY==cal.get(Calendar.DAY_OF_WEEK)){
        	cal.add(Calendar.WEEK_OF_YEAR, -1);
        }
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //获取本周一的日期
        return cal.getTime();
	}
	
	/**
	 * 获取本周日
	 * @author WKX
	 * @since 2016年1月25日 上午9:43:13
	 */
	public static Date getThisWeekSunday(){
		Calendar cal =Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        if(Calendar.SUNDAY!=cal.get(Calendar.DAY_OF_WEEK)){
        	cal.add(Calendar.WEEK_OF_YEAR, 1);
        }
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY); //获取本周日的日期
        return cal.getTime();
	}
	
	public static void main(String[] args) {
		getThisWeekMonday();
		getThisWeekSunday();
	}
	
	
	
	/**
	 * 获取小时
	 * @param date
	 * @return
	 * @date 2016年1月25日
	 * @author lvyanqin
	 */
	public static int getHour(Date date) {
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    return calendar.get(Calendar.HOUR_OF_DAY);
	}
	 
	/**
	 * 获取分钟
	 * @param date
	 * @return
	 * @date 2016年1月25日
	 * @author lvyanqin
	 */
	public static int getMinute(Date date) {
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    return calendar.get(Calendar.MINUTE);
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
}