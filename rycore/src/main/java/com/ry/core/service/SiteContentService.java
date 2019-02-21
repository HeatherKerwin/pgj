package com.ry.core.service;

import org.springframework.stereotype.Repository;

import com.ry.core.entity.SiteContent;

@Repository
public interface SiteContentService {
	
	/**
	 * 根据主键获取SiteContent
	 * @author ZY
	 * @param id
	 * 2016年11月28日下午3:37:28
	 */
	public SiteContent getById(Integer id);
	
	/**
	 * 新增SiteContent
	 * @author ZY
	 * @param SiteContent
	 * 2016年11月28日下午3:39:02
	 */
	public void saveModel(SiteContent siteContent);

	/**
	 * 更新SiteContent
	 * @author ZY
	 * @param SiteContent
	 * 2016年11月28日下午3:39:22
	 */
	public void updateModel(SiteContent siteContent);
	
	/**
	 * 获取SiteContent详情
	 * @author ZY
	 * @return
	 * 2016年11月28日下午4:56:30
	 */
	public SiteContent get();
}
