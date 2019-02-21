package com.ry.core.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.entity.PreferentialInfo;
import com.ry.util.page.PageResults;

@Repository
public interface PreferentialInfoDao {

	/**
	 * 优惠信息分页
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
	 * @return
	 */
	public PreferentialInfo getById(Integer id);
	
	/**
	 * 保存对象
	 * @author WKX
	 * @param info
	 */
	public void saveInfo(PreferentialInfo info)throws Exception;
	
	/**
	 * 获取时间段内的优惠消息
	 * @author WKX
	 * @param start
	 * @param end
	 * @since 2016年1月8日 下午12:51:50
	 */
	public List<PreferentialInfo> getBetweenCreateTime(Date start,Date end);
}