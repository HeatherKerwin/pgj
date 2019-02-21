package com.ry.core.dao.appmanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ry.core.entity.da.RemainWeekByQudao;

public interface RemainWeekByQudaoDao {

	/**
	 * 统计周留存,如果数据库中已存在数据，则一天一天的更新然后加上今天的统计数据
	 * @date 2016年1月8日
	 * @author lvyanqin
	 */
	void countReaminWeekByQudao(Date executeDate, Date endDate);

	/**
	 * 根据渠道和时间获取周留存信息
	 * @param qudao
	 * @param beginDate
	 * @param endDate
	 * @return
	 * @date 2016年1月12日
	 * @author lvyanqin
	 */
	List<Map<String, Object>> showWeekRemain(String qudao, Date beginDate,
			Date endDate);
	
	/**
	 * 周留存信息入库的公用方法
	 * @param sql
	 * @date 2016年1月11日
	 * @author lvyanqin
	 */
	public void countRemainWeekMethod(List<Object[]> lists);
	
	/**
	 * 求两个时间差
	 * @param date
	 * @param date1
	 * @return
	 * @date 2016年1月11日
	 * @author lvyanqin
	 */
	public  Integer getBetweenDays(Date date, Date date1);
	
	/**
	 * 求记录中的用户注册时间和行为访问记录的时间差
	 * @param lists
	 * @param index,列表下标
	 * @param simpleDateFormate，日期格式化
	 * @param remain，留存对象
	 * @date 2016年1月11日
	 * @author lvyanqin
	 */
	public  Map<String, Object> getBetweenDays(List<Object[]> lists, int index);
	
	public  Date dateFormat(String strDate);
	
}
