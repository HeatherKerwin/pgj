package com.ry.core.service;

import java.util.Date;
import java.util.List;

import com.ry.core.entity.Notice;
import com.ry.core.entity.NoticeAdd;
import com.ry.util.page.PageResults;

public interface TiexianNoticeService {

	void add(Notice notice);
	
	Notice findOne(Integer id);
	
	void update(Notice notice);
	
	List<Notice> findAll();

	void deleteById(Integer id);
	
	/**
	 * 根据 年份 获取提醒
	 * @author WKX
	 * @param yearToken
	 * @since 2016年4月11日 下午6:45:35
	 */
	public List<Notice> findByYearToken(String yearToken);

	/**
	 * 更具编号获取提示
	 * @author WKX
	 * @param code
	 * @since 2016年1月27日 下午1:51:21
	 */
	public Notice findByCode(String code);
	
	/**
	 * 查看当前日期时候是在节日内
	 * @param yearToken
	 * @since 2016年4月11日 下午6:35:30
	 */
	public Notice findByNowTime(String yearToken);
	
	/**
	 * 无报价时获取提醒
	 * @param yearToken
	 * @since 2016年4月11日 下午6:35:30
	 */
	public Notice findByContent(String yearToken);
	
	/**
	 * 查找节日（根据年份）
	 * @param date 日期
	 * @since 2016年4月11日 下午8:00:52
	 */
	public Notice findFestivalByNowTime(Date date);
	
	/**
	 * 节假日分页
	 * @author WKX
	 * @param index 第几页
	 * @param size 一页多少条
	 * @param notice（code 编号（类型）[OFFDAY]非工作日[FESTIVAL]节日、名称搜索）
	 * @since 2017年5月11日 下午4:02:16
	 */
	public PageResults<Notice> getPageList(Integer index,Integer size,Notice notice);
	
	/**
	 * 根据主键删除节假日（含补班）
	 * @author WKX
	 * @param id 节假日主键
	 * @since 2017年5月12日 下午7:11:05
	 */
	public void deleteGroupById(Integer id);
	
	/**
	 * 保存或更新节假日
	 * @author WKX
	 * @param notice 节假日
	 * @param noticeAdds 补班
	 * @since 2017年5月15日 下午4:25:04
	 */
	public void saveAndNoticeAdd(Notice notice,List<NoticeAdd> noticeAdds);
	
	/**
	 * 获取当前时间属于什么节日
	 * @param nowTime 时间
	 * @since 2017年5月16日 下午4:01:05
	 */
	public Notice getFestivalByNowTime(Date nowTime);
	
	/**
	 * 获取节假日提示（法定节日、周末（非补班））用来贴现和查询查复的提醒
	 * @author WKX
	 * @since 2017年5月18日 下午4:52:34
	 */
	public Notice getNoticeByNowTime();
}