package com.ry.core.service;

import java.util.List;
import java.util.Map;

import com.ry.core.entity.OrgPartner;

public interface OrgPartnerService {
	
	/**
	 * 根据主键获取对象
	 * @author WKX
	 * @param id 主键
	 * @since 2017年1月11日 上午10:48:15
	 */
	public OrgPartner getById(Integer id);
	
	/**
	 * 保存对象
	 * @author WKX
	 * @param orgPartner
	 * @since 2017年1月11日 上午10:48:33
	 */
	public void saveModel(OrgPartner orgPartner);
	
	/**
	 * 根据主键删除对象
	 * @author WKX
	 * @param id
	 * @since 2017年1月11日 上午10:48:41
	 */
	public void deleteById(Integer id);
	
	/**
	 * 更新对象
	 * @author WKX
	 * @param orgPartner
	 * @since 2017年1月11日 上午10:48:57
	 */
	public void updateModel(OrgPartner orgPartner);
	
	/**
	 * 根据机构主键获取合作机构集合
	 * @author WKX
	 * @param orgId 机构主键
	 * @since 2017年1月11日 上午10:49:09
	 */
	public List<OrgPartner> getByOrgId(Integer orgId);
	
	/**
	 * 根据用户主键获取合作机构
	 * @author WKX
	 * @param memberId 用户主键
	 * @since 2017年1月12日 上午10:43:17
	 */
	public List<Map<String,Object>> getByMemberId(Integer memberId);
}