package com.ry.core.dao;
import java.util.List;

import com.ry.core.entity.RequirementSp;


public interface RequirementSpDao {
	
	/**
	 * 通过id得到RequirementSp对象
	 * @author ZY
	 * @param id
	 * @since 2016年7月28日 下午3:50:28
	 */
	public RequirementSp getById(Integer id);
	
	/**
	 * 保存RequirementSp对象
	 * @author ZY
	 * @param requirementsp
	 * @since 2016年7月28日 下午3:51:03
	 */
	public void saveModel(RequirementSp requirementsp);
	
	/**
	 * 更新RequirementSp对象
	 * @author ZY
	 * @param requirementsp
	 * @since 2016年7月28日 下午3:51:07
	 */
	public void updateModel (RequirementSp requirementsp);
	
	/**
	 * 根据orgId，create_time，type获取该RequirementSp
	 * @author ZY
	 * @param orgId 
	 * @param type 
	 * @param time
	 * @since 2016年8月26日 下午2:16:00
	 */
	public RequirementSp getByAll(Integer orgId,Integer type,String time);
	
	/**
	 * 保存或修改商票需求
	 * @author ZY
	 * @param requirementSp
	 * @since 2016年8月26日 下午3:06:34
	 */
	public void saveOrUpdate(RequirementSp requirementSp);
	
	/**
	 * 根据orgId获取RequirementSp的纸票和电票
	 * @author ZY
	 * @param orgId 
	 * @since 2016年8月26日 下午3:41:01
	 */
	public List<RequirementSp> getByOrgId(Integer orgId,String time);
}
