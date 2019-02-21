package com.ry.core.dao;

import java.util.List;
import java.util.Map;

import com.ry.core.entity.OrgCity;

/**
 * 机构交易城市
 * @author WKX
 */
public interface OrgCityDao {
	
	/**
	 * 判断添加的地址是否存在
	 * @param orgCity 存储的对象
	 */
	public OrgCity getOrgCity(OrgCity orgCity);
	
	/**
	 * 根据主键获取对象
	 * @param id
	 * @author WKX
	 */
	public OrgCity getById(Integer id);
	
	/**
	 * 根据机构主键获取交易城市
	 * @param orgId
	 * @author WKX
	 */
	public List<Map<String,Object>> getRegionByOrgId(Integer orgId);
	
	/**
	 * 查找机构中符合该名称的城市
	 * @param orgIds
	 * @param cityName
	 * @author WKX
	 */
	public List<Map<String,Object>> getInOrgIdAndCityName(List<Integer> orgIds,String cityName);
	
	/**
	 * 查找机构中符合该 贴现地址 的城市
	 * @author WKX
	 * @param orgIds 多个机构
	 * @param cityId 贴现城市主键
	 * @since 2016年6月24日 下午2:49:12
	 */
	public List<Map<String,Object>> getInOrgIdAndCityId(List<Integer> orgIds,Integer cityId);
	
	/**
	 * 删除认证的 交易城市
	 * @author WKX
	 * @param orgId
	 * @throws Exception
	 * @since 2016年3月24日 下午2:29:32
	 */
	public void deleteByOrgId(Integer orgId) throws Exception;
	
	/**
	 * 保存对象
	 * @author WKX
	 * @param orgCity
	 * @since 2016年3月24日 下午2:43:58
	 */
	public void saveModel(OrgCity orgCity);
	
	/**
	 * 根据主键删除对象
	 * @param id
	 * @author WKX
	 */
	public void deleteById(Integer id);
	
	/**
	 * 更新对象
	 * @author WKX
	 * @param orgCity
	 * @since 2016年5月16日 下午1:36:16
	 */
	public void updateModel(OrgCity orgCity);
	
	/**
	 * 根据机构主键获取 设置的交易城市
	 * @author WKX
	 * @param orgId
	 * @since 2016年5月24日 上午10:24:57
	 */
	public List<OrgCity> getByOrgId(Integer orgId);
	
	/**
	 * 根据机构主键获取 设置的交易城市(根据城市名称分组)
	 * @author BKY
	 * @param orgId 机构主键
	 */
	public List<Map<String, Object>> getCityByOrgId(Integer orgId);
	
	/**
	 * 获取org_city中的所有城市 （去重）
	 * @author GXW
	 * @since 2016年6月27日
	 */
	public List<Map<String, Object>> getOrgCityList();
	
	/**
	 * 根据机构主键获取交易城市（含已报价城市）
	 * @author WKX
	 * @param orgId 机构主键
	 * @param day 哪一天
	 * @since 2016年6月29日 上午10:31:41
	 */
	public List<Map<String,Object>> getAndHasPriceByOrgId(Integer orgId,String day);
}