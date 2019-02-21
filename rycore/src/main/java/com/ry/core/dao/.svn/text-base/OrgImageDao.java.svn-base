package com.ry.core.dao;

import java.util.List;
import java.util.Map;

import com.ry.core.entity.OrgImage;

public interface OrgImageDao {
	
	/**
	 * 根据主键获取对象
	 * @param id
	 * @author WKX
	 */
	public OrgImage getById(Integer id);

	/**
	 * 根据认证主键获取承诺函（获取最新的）
	 * @param orgId
	 * @author WKX
	 */
	public List<Map<String,Object>> getByOrgId(Integer orgId);
	
	/**
	 * 保存承诺函
	 * @param orgInfo
	 * @author WKX
	 */
	public void saveModel(OrgImage orgInfo);
}