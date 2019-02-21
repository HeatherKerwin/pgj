package com.ry.core.dao;

import java.util.List;

import com.ry.core.entity.Dimension;

public interface DimensionDao {
	
	/**
	 * 根据orgId,年月查询记录
	 * @author GXW
	 * @param orgId,yearMonth
	 * @since 2016年5月21日
	 */
	public List<Dimension> getByOrgIdAndMonth(Integer orgId,String yearMonth);
	
	/**
	 * 增加或更新记录
	 * @author GXW
	 * @param d
	 * @since 2016年5月21日
	 */
	public void saveOrUpdate(Dimension d);
	
	/**
     * 新增数据
     * @author cx
     * @since 2016年5月12日 
     */
	public void saveDimension(Dimension dimension);
	
	/**
     * 根据org_id和时间查找类
     * @author cx
     * @since 2016年5月12日 
     */
	public List<Dimension> findDimension(String month,Integer org_id);
	
	/**
     * 更新数据
     * @author cx
     * @since 2016年5月12日 
     */
	public void updateDimension(Dimension dimension);
}