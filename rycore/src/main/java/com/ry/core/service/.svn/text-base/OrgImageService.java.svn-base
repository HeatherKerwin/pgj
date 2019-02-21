package com.ry.core.service;

import java.util.List;
import java.util.Map;

import com.ry.core.entity.Image;
import com.ry.core.entity.OrgImage;

public interface OrgImageService {
	
	/**
	 * 根据主键获取对象
	 * @author WKX
	 * @param id
	 */
	public OrgImage getById(Integer id);
	
	/**
	 * 根据认证主键获取 最新的承诺函
	 * @param orgId
	 * @author WKX
	 */
	public Map<String,Object> getByOrgId(Integer orgId);
	
	/**
	 * 保存承诺函（含更新）
	 * @author WKX
	 * @param orgInfo
	 */
	public void saveModel(OrgImage orgImage);
	
	/**
	 * 保存承诺函（及图片）
	 * @param orgImage
	 * @param images
	 * @author WKX
	 */
	public void saveModelAndImage(OrgImage orgImage,List<Image> images);
}