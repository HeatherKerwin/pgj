package com.ry.core.service;

import java.util.List;

import com.ry.core.entity.IntegraltradingDetail;
import com.ry.util.page.PageResults;

public interface IntegraltradingDetailService {

	/**
	 * 根据主键获取对象
	 * @author MH
	 * @param id 主键
	 * @return
	 */
	public IntegraltradingDetail getById(Integer id);
	
	/**
	 * 根据对象查询，获取队形列表
	 * @author MH
	 * @param integraltradingDetail 对象
	 * @return
	 */
	public List<IntegraltradingDetail> getList(IntegraltradingDetail integraltradingDetail);
	
	/**
	 * 分页查询积分收支明细
	 * @author MH
	 * @param pageIndex 页
	 * @param pageSize 个数
	 * @param memberId 用户id
	 * @return
	 */
	public PageResults<IntegraltradingDetail> getPageList(Integer pageIndex,Integer pageSize,Integer memberId);
}
