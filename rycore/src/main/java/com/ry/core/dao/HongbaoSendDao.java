package com.ry.core.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ry.core.entity.HongbaoSend;

public interface HongbaoSendDao {
	
	HongbaoSend getHongbaoSend(Integer id);
	
	List<HongbaoSend> getList(Integer memberid);
	
	List<HongbaoSend> getList(Integer memberid, Integer hid);
	
	void addHongbaoSend(HongbaoSend hongbaoSend);
	
	/**
	 * 根据时间区间统计发送红包数（一个用户一天内活跃数）
	 * @author WKX
	 * @param hid 红包活动主键
	 * @param start 开始日期
	 * @param end 截止日期
	 * @since 2016年2月15日 上午10:48:36
	 */
	public List<Map<String,Object>> getCountByStartAndEnd(Integer hid,Date start,Date end);
}