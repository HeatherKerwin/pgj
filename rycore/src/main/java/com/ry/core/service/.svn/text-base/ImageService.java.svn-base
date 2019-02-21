package com.ry.core.service;

import java.util.List;

import com.ry.core.entity.Image;

public interface ImageService {
	
	/**
	 * 根据主键获取对象
	 * @param id
	 * @author WKX
	 */
	public Image getById(Integer id);
	
	/**
	 * 根据外键及类型获取 多张关联图（外键类型见PO注释）
	 * @param fkId 外键
	 * @param fkType 外键类型
	 * @author guoxw
	 */
	public List<Image> getByFkIdAndFkType(Integer fkId,String fkType);
	
	/**
	 * 保存承诺函（单张）
	 * @param image
	 * @author GXW
	 */
	public void saveModel(Image image);
}
