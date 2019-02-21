package com.ry.core.dao;

import com.ry.core.entity.UserRemarks;
import com.ry.util.datatable.BasePageRequestData;
import com.ry.util.page.PageResults;

public interface UserRemarksDao {
	
	/**
	 * 根据主键获取对象
	 * @author ZY
	 * @param id
	 * @since 2017年1月9日 下午5:47:50
	 */
	public UserRemarks getById(Integer id);
	
	/**
	 * 新增
	 * @author ZY
	 * @param userRemarks
	 * @since 2017年1月9日 下午5:49:01
	 */
	public void saveModel(UserRemarks userRemarks);
	
	/**
	 * 更新
	 * @author ZY
	 * @param userRemarks
	 * @since 2017年1月9日 下午5:48:39
	 */
	public void updateModel(UserRemarks userRemarks);
	
	/**
	 * 根据来源和用户id获取用户备注列表
	 * @author KHC
	 * @param memberId
	 * @param source
	 * @since 2017年1月9日 下午6:20:01
	 */
	public PageResults<UserRemarks> getPageList(BasePageRequestData req,UserRemarks userRemarks) throws Exception;
}