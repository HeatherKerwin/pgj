package com.ry.core.service;

import com.ry.core.entity.Dimension;

/**
* @Package com.ry.core.service
* @Description: TODO
* @author GXW
* @date 2016年5月21日
*/
public interface DimensionService {
	
	/**
	 * 根据orgId,年月查询记录
	 * @author GXW
	 * @param orgId,yearMonth
	 * @since 2016年5月21日
	 */
	public Dimension getByOrgIdAndMonth(Integer orgId, String yearMonth);
	
	/**
	 * 增加或更新记录
	 * @author GXW
	 * @param d
	 * @since 2016年5月21日
	 */
	public void saveOrUpdate(Dimension d);
	
	public void saveDimension(Dimension dimension);
	
	public Dimension findDimension(String month,Integer org_id);
	
	public void updateDimension(Dimension dimension);
}
