package com.ry.core.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.entity.Noticerecord;

@Repository
public interface NoticerecordDao {
	
	List<Noticerecord> getList(Integer memberid, Date noticetime);

	void addNoticerecord(Noticerecord noticerecord);
	
	/**
	 * 根据外键获取对象（查看票据管理是否设置提醒）
	 * @author WKX
	 * @param fkId
	 * @since 2016年3月31日 下午2:25:09
	 */
	public List<Noticerecord> getByFkId(Integer fkId);
	
	/**
	 * 根据主键获取对象
	 * @author WKX
	 * @param id
	 * @since 2016年4月1日 下午1:01:27
	 */
	public Noticerecord getById(Integer id);
	
	/**
	 * 根据主键删除对象
	 * @author WKX
	 * @param id
	 * @since 2016年4月1日 下午2:08:05
	 */
	public void delById(Integer id);
	
	/**
	 * 查询noticerecord表某个时间段的数据
	 * @param noticetime1 提醒开始时间
	 * @param noticetime2 提醒结束时间
	 * @author BKY
	 */
	public List<Map<String, Object>> getForRemindExpire(Date noticetime1, Date noticetime2);
	
	/**
	 * 更新
	 * @author cx
	 * @param 
	 * @since 2016年5月30日 
	 */
	public void updateNoticerecord(Noticerecord n);
}