package com.ry.core.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.entity.PreferentialInfo;
import com.ry.util.page.PageResults;

@Repository
public interface PreferentialInfoService {

	/**
	 * 优惠信息[分页]
	 * @author WKX
	 * @param currentPage
	 * @param pageSize
	 */
	public PageResults<PreferentialInfo> getPageList(Integer currentPage,Integer pageSize);
	
	/**
	 * 根据主键删除对象
	 * @author WKX
	 * @param id
	 */
	public void deleteById(Integer id);

	/**
	 * 根据主键获取对象
	 * @author WKX
	 * @param id
	 */
	public PreferentialInfo getById(Integer id);
	
	/**
	 * 保存对象
	 * @author WKX
	 * @param info
	 */
	public void saveInfo(PreferentialInfo info) throws Exception;
	
	/**
	 * 获取时间内的优惠消息
	 * @author WKX
	 * @param start
	 * @param end
	 * @since 2016年1月8日 下午12:52:49
	 */
	public List<PreferentialInfo> getBetweenCreateTime(Date start,Date end);
}