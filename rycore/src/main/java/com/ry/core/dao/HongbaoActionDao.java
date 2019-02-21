package com.ry.core.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ry.core.entity.HongbaoAction;

public interface HongbaoActionDao {
	
	void addHongbaoAction(HongbaoAction hongbaoAction);
	
	List<Object[]> countHongbaoPrice(Integer hid);
	
	List<HongbaoAction> getHongbaoActionList(Integer hid, String phone, Integer sid);
	
	void updateHongbaoAction(HongbaoAction hongbaoAction);
	
	public BigInteger countHongbaoAction(Integer hid);
	
	/**
	 * 统计红包动作的每天的次数（推荐注册系统双向发送红包）
	 * @author WKX
	 * @param start 开始时间
	 * @param end 结束时间
	 * @since 2016年2月15日 下午3:51:52
	 */
	public List<Map<String,Object>> getCountByStartAndEnd(Date start,Date end);
}