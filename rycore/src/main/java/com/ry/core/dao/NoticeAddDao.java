package com.ry.core.dao;

import java.util.Date;
import java.util.List;

import com.ry.core.entity.NoticeAdd;

public interface NoticeAddDao {

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
	 * 根据节假日主键[获取]所有补班
	 * @author WKX
	 * @param noticeId 节假日主键
	 * @since 2017年5月12日 下午4:31:16
	 */
	public List<NoticeAdd> getByNoticeId(Integer noticeId);
	
	/**
	 * 根据节假日主键[删除]所有补班
	 * @author WKX
	 * @param noticeId 节假日主键
	 * @since 2017年5月12日 下午7:02:11
	 */
	public void deleteByNoticeId(Integer noticeId);
	
	/**
	 * 查看该日期是属于补班
	 * @author WKX
	 * @param nowTime
	 * @since 2017年5月15日 下午8:32:36
	 */
	public List<NoticeAdd> getByNowTime(Date nowTime);
}