package com.ry.core.dao;

import java.util.List;

import com.ry.core.entity.Image;

public interface ImageDao {
	
	/**
	 * 根据主键获取对象
	 * @param id
	 * @author WKX
	 */
	public Image getById(Integer id);

	/**
	 * 保存承诺函（单张）
	 * @param image
	 * @author WKX
	 */
	public void saveModel(Image image);
	
	/**
	 * 根据外键及类型获取 多张关联图（外键类型见PO注释）
	 * @param fkId 外键
	 * @param fkType 外键类型
	 * @author WKX
	 */
	public List<Image> getByFkIdAndFkType(Integer fkId,String fkType);
}