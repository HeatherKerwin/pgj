package com.ry.core.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ry.core.entity.Sitestatistics;


public interface SitestatisticsDao {
	
	List getListCountByDay(Date date);
	
	List getListCountByDayAndCooperation(Date date,String cooStr);
	
	public void addSitestatistics(Sitestatistics sitestatistics);
	/*
	 * 
	* @Title: getListCountByDateAndCooperation
	* @Description: 根据日期间隔来查询合作的数据
	* @param @param beginDate
	* @param @param endDate
	* @param @param cooStr
	* @param @return    参数
	* @return List    返回类型
	* @throws
	 */
	public List getListCountByDateAndCooperation(Date beginDate,Date endDate,String cooStr);
	
	/**
	 * 获取该区间的操作记录
	 * @author WKX
	 * @param type 类型（PC、AC、hongbao）
	 * @param start 开始日期
	 * @param end 结束日期
	 * @since 2016年2月16日 上午10:34:17
	 */
	public List<Map<String,Object>> getCountByStartAndEnd(String type,Date start,Date end);
	
	/**
	 * 获取hezuo是pinyou的相关统计数据
	 * @author ZY
	 * @param date
	 * 2016年10月9日上午11:02:01
	 */
	public List<Map<String,Object>> getPinyouCountByDay(String date);
}
