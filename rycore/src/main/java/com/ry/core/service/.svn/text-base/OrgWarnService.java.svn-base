package com.ry.core.service;
import java.util.Map;

import com.ry.core.entity.OrgWarn;
import com.ry.util.page.PageResults;


public interface OrgWarnService {
	
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
	 * 获取预警机构的列表
	 * @author ZY
	 * @param currentPage
	 * @param pageSize
	 * @return
	 * @since 2016年8月10日 下午4:30:43
	 */
	public PageResults<Map<String,Object>> getPageList(Integer currentPage, Integer pageSize,String name) ;
}