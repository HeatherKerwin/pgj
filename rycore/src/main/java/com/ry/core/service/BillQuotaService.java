package com.ry.core.service;

import java.util.List;
import java.util.Map;

import com.ry.core.entity.BillQuota;

public interface BillQuotaService {
	/**
	 * 自动获取近期票据指数
	 * @author GXW
	 * @param length 数量
	 * @param date 时间下限
	 * @param type 电纸票类型
	 * @since 2016年5月13日
	 */
	public List<Map<String,Object>> getList(Integer length ,String date, Integer type);
	
	/**
	 * 自动获取近期票据指数
	 * @author GXW
	 * @since 2016年5月13日
	 */
	public List<Map<String,Object>> getByDay(String day ,Integer type);
	
	/**
	 * 存入前一天票据指数
	 * @author GXW
	 * @param billquota
	 * @since 2016年5月13日
	 */
	public void add(BillQuota billquota);
}
