package com.ry.core.dao;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ry.core.entity.Actionlog;
import com.ry.core.form.CustomReportForm;
import com.ry.util.page.PageResults;

public interface ActionlogDao {
	
	void addActionlog(Actionlog actionlog);		
	
	Actionlog getActionlog(Actionlog actionlog) throws ParseException;
	
	/**
	 * 根据用户主键获取该用户活跃天数[及开始结束时间]
	 * @author WKX
	 * @param start
	 * @param end
	 * @param memberId
	 * @since 2016年1月26日 下午3:22:18
	 */
	public BigInteger countActionlogByStartAndEndAndMemberId(Date start,Date end,Integer memberId);
	
	/**
	 * 根据用户主键查询各个主键在某段时间内的登录次数(一天只统计一次)
	 * @param paramList 用户主键列表
	 * @author BKY
	 */
	public List<Map<String, Object>> countMemberLogin(List<Object> paramList, Date startTime, Date endTime);
	
	public List<Actionlog> getListByIdAndTime(List<Object> paramList, Date startTime, Date endTime);
	
	/**
	 * 根据条件获取这一天的功能统计
	 * @author WKX
	 * @param code 编号
	 * @param memberId 用户主键
	 * @param day 天
	 * @param from 来源
	 * @since 2016年7月4日 下午4:29:47
	 */
	public List<Map<String, Object>> getByCodeAndMemberIdAndDayAndFrom(String code,Integer memberId,String day,String from);
	
	/**
	 * 分页查询登陆机构是否报价
	 * @author MH
	 * @param from
	 * @return
	 */
	public PageResults<Map<String, Object>> orgloginreport(CustomReportForm from);
	
	
	/**
	 * 分页查询登陆企业是否贴现
	 * @author MH
	 * @param from
	 * @return
	 */
	public PageResults<Map<String, Object>> bnsloginreport(CustomReportForm from);
	
	/**
	 * 根据时间和来源，获取时间内访问总量和ip访问总数
	 * @author MH
	 * @param start 开始时间
	 * @param end 截止时间
	 * @param source 来源
	 * @return
	 */
	public List<Actionlog> getListCountByDay(Date start, Date end, String source);
}