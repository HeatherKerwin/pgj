package com.ry.core.dao;
import java.util.Map;

import com.ry.core.entity.OrgWarn;
import com.ry.util.page.PageResults;


public interface OrgWarnDao {
	
	/**
	 * 通过id得到OrgWarn对象
	 * @author ZY
	 * @param id
	 * @since 2016年7月28日 下午3:48:24
	 */
	public OrgWarn getById(Integer id);
	
	/**
	 * 保存OrgWarn对象
	 * @author ZY
	 * @param orgwarn
	 * @since 2016年7月28日 下午3:48:58
	 */
	public void saveModel(OrgWarn orgWarn);
	
	/**
	 * 更新OrgWarn对象
	 * @author ZY
	 * @param orgwarn
	 * @since 2016年7月28日 下午3:49:05
	 */
	public void updateModel (OrgWarn orgWarn);
	
	/**
	 * 获取预警的列表
	 * @author ZY
	 * @param currentPage
	 * @param pageSize
	 * @since 2016年8月10日 下午4:33:27
	 */
	 public PageResults<Map<String,Object>> getPageList(Integer currentPage, Integer pageSize,String name) ;
	
	 /**
	  * 根据日期和org_id返回orgWarn对象
	  * @author ZY
	  * @param orgId
	  * @param day
	  * @since 2016年8月24日 下午1:53:25
	  */
	 public OrgWarn getByOrgId(Integer orgId,String day) ;
}