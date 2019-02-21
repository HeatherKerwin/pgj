package com.ry.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.entity.Tag;

@Repository
public interface TagDao {

	/**
	 * 根据编号获取对象[更多用于获取大类]
	 * @author WKX
	 * @param code
	 * @since 2016年1月15日 上午11:12:41
	 */
	public List<Tag> getByCode(String code);
	
	/**
	 * 根据父类主键获取子类
	 * @author WKX
	 * @param parentId
	 * @since 2016年1月15日 上午11:12:01
	 */
	public List<Tag> getByParentId(Integer parentId);
	
	/**
	 * 根据父类编好获取子类
	 * @author WKX
	 * @param code
	 * @since 2016年1月15日 下午3:29:11
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