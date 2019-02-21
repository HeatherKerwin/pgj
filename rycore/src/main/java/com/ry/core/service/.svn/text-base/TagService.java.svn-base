package com.ry.core.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.entity.Tag;

/**
 * 字典表
 * @author WKX
 */
@Repository
public interface TagService {

	/**
	 * 根据编号获取对象[更多用于大类查询]
	 * @author WKX
	 * @param code
	 * @since 2016年1月15日 上午11:16:24
	 */
	public Tag getByCode(String code);
	
	/**
	 * 根据父类主键获取的小类
	 * @author WKX
	 * @param parentId
	 * @since 2016年1月15日 上午11:16:55
	 */
	public List<Tag> getByParentId(Integer parentId);
	
	/**
	 * 根据父类编好获取子类对象
	 * @author WKX
	 * @param code
	 * @since 2016年1月15日 下午3:30:59
	 */
	public List<Tag> getByParentCode(String code);
	
	/**
	 * 通过主键id查找tag对象
	 * @author ZY
	 * @param id
	 * @since 2016年9月22日 上午9:48:17
	 */
	public Tag getById(Integer id);
}