package com.ry.core.dao;

import java.util.Date;
import java.util.List;

import com.ry.core.entity.Notice;
import com.ry.util.page.PageResults;

public interface TiexianNoticeDao {

	void add(Notice notice);
	
	Notice findNoticeById(Integer id);

	void updateNotice(Notice notice);
	
	List<Notice> findAll();
	
	/**
	 * 根据 年份 获取提醒
	 * @author WKX
	 * @param yearToken
	 * @since 2016年4月11日 下午6:41:41
	 */
	public List<Notice> findByYearToken(String yearToken);

	void deleteById(Integer id);

	/**
	 * 根据编号获取提示
	 * @author WKX
	 * @param code
	 * @since 2016年1月27日 下午1:42:11
	 */
	public Notice findByCode(String code);
	
	/**
	 * 获取当前时间[在节日内的提示]
	 * @author WKX
	 * @param nowTime
	 * @param yearToken 年份
	 * @since 2016年4月11日 下午6:34:21
	 */
	public List<Notice> findByNowTime(Date nowTime,String yearToken);
	
	/**
	 * 获取无报价时的提示]
	 * @author WKX
	 * @param yearToken 年份
	 */
	public List<Notice> findByContent(String yearToken);
	
	/**
	 * 分页显示节假日
	 * @author WKX
	 * @param index 第几页
	 * @param size 一页多少条
	 * @param notice （含类型 [OFFDAY]非工作日[FESTIVAL]节日、及名称搜索）
	 * @since 2017年5月11日 下午3:56:29
	 */
	public PageResults<Notice> getPageList(Integer index,Integer size,Notice notice);
	
	/**
	 * 获取当前时间属于什么节日
	 * @param nowTime 时间
	 * @since 2017年5月16日 下午4:01:05
	 */
	public List<Notice> getFestivalByNowTime(Date nowTime);
}