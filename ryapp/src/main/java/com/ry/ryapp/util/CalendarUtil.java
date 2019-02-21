package com.ry.ryapp.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日历工具类
 * 
 * @author GJJ
 * @date 2016年1月7日
 */
public class CalendarUtil {
	public static void main(String[] args) {
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Date date = new Date();
		System.out.println(sdf.format(date));
		System.out.println(sdf.format(getLastDate(date)));*/
	}

	public static Date getLastDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -1);
		return cal.getTime();
	}

	public static String getFirstByMonth(String date) {
		String year = date.split("-")[0];
		String month = date.split("-")[1];
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		cal.set(Calendar.MONTH, Integer.parseInt(month));
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		Date lastDate = cal.getTime();

		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDate = cal.getTime();
		return dateFormat(firstDate);

	}
	
	public static String getLastByMonth(String date) {
		String year = date.split("-")[0];
		String month = date.split("-")[1];
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		cal.set(Calendar.MONTH, Integer.parseInt(month));
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		Date lastDate = cal.getTime();

		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDate = cal.getTime();
		return dateFormat(lastDate);

	}
	private static String dateFormat(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sdf.format(date);
		return format;
		
	}
}
