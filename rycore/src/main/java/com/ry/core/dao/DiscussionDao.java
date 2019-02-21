package com.ry.core.dao;

import com.ry.core.entity.Discussion;

public interface DiscussionDao {
	
	public void saveModel(Discussion discussion);

	public void updateModel(Discussion discussion);
	
	public Discussion getById(Integer id);
	
	/**
	 * 根据手机号获取对象（判断是否已经申请过）
	 * @author WKX
	 * @param phone
	 * @since 2016年10月16日 下午6:34:27
	 */
	public Discussion getByPhone(String phone);
}