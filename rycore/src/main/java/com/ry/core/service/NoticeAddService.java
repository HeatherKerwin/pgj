package com.ry.core.service;

import java.util.Date;
import java.util.List;

import com.ry.core.entity.NoticeAdd;

public interface NoticeAddService {

	/**
	 * 根据主键获取对象
	 * @author WKX
	 * @param id 主键
	 * @since 2017年5月12日 下午4:27:29
	 */
	public NoticeAdd getById(Integer id);
	
	/**
	 * 保存对象（补班）
	 * @author WKX
	 * @param noticeAdd 补班
	 * @since 2017年5月12日 下午4:27:51
	 */
	public void saveModel(NoticeAdd noticeAdd);
	
	/**
	 * 更新补班
	 * @author WKX
	 * @param noticeAdd 补班
	 * @since 2017年5月12日 下午4:28:07
	 */
	public void updateModel(NoticeAdd noticeAdd);
	
	/**
	 * 根据主键删除对象
	 * @author WKX
	 * @param id 主键
	 * @since 2017年5月12日 下午4:28:27
	 */
	public void deleteById(Integer id);
	
	/**
	 * 根据节假日主键获取所有补班
	 * @author WKX
	 * @param noticeId 节假日主键
	 * @since 2017年5月12日 下午4:31:16
	 */
	public List<NoticeAdd> getByNoticeId(Integer noticeId);
	
	/**
	 * 判断该日期是否是补班
	 * @author WKX
	 * @param nowTime 日期
	 * @since 2017年5月15日 下午8:36:41
	 */
	public List<NoticeAdd> getByNowTime(Date nowTime);
}