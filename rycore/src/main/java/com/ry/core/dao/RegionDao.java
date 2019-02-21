package com.ry.core.dao;

import java.util.List;
import java.util.Map;

import com.ry.core.entity.Region;

/**
 * 省市区
 * @author WKX
 */
public interface RegionDao {
	
	/**
	 * 根据逐渐获取对象
	 * @param id
	 * @author WKX
	 */
	public Region getById(Integer id);
	
	/**
	 * 根据类型获取对象
	 * @param type
	 * @author WKX
	 */
	public List<Region> getByType(String type);
	
	/**
	 * 根据名称和类型查找（模糊查找）
	 * @author WKX
	 * @param name
	 * @param type
	 * @since 2016年4月6日 下午6:47:50
	 */
	public List<Region> getByNameAndType(String name,String type);
	
	/**
	 * 根据编号获取对象
	 * @author MH
	 * @param code
	 * @return
	 */
	public Region getByCode(String code);
	
	/**
	 * 根据父级Id 获取子集 集合
	 * @author MH
	 * @param parent_id
	 * @return
	 */
	public List<Region> getByParent(Integer  parent_id);
	
	/**
	 * 根据父级Id 获取子集姓名 集合
	 * @author MH
	 * @param parent_id
	 * @return
	 */
	public List<Map<String, Object>> getByName(Integer  parent_id);
}