package com.ry.core.dao;

import java.util.List;
import java.util.Map;

import com.ry.core.entity.Org;

public interface OrgDao {
	
	/**
	 * 保存对象
	 * @author WKX
	 * @param org
	 * @since 2016年3月22日 下午4:28:09
	 */
	public void saveModel(Org org);
	
	/**
	 * 根据用户主键获取认证
	 * @author WKX
	 * @param memberId
	 * @throws Exception
	 * @since 2016年3月22日 下午4:54:33
	 */
	public Org getByMemberId(Integer memberId) throws Exception;
	
	/**
	 * 根据主键获取对象
	 * @author WKX
	 * @param id
	 * @throws Exception
	 * @since 2016年3月23日 下午1:54:59
	 */
	public Org getById(Integer id) throws Exception;
	
	/**
	 * 根据类型获取认证的机构（或企业）
	 * @author WKX
	 * @param type
	 * @since 2016年3月29日 下午3:55:06
	 */
	public List<Org> getByType(Integer type);
	
	/**
	 * 根据类型获取认证机构或企业 (含名称)
	 * @param type
	 * @return
	 */
	public List<Map<String, Object>> getOrgAndNameByType(Integer type);
	
	/**
	 * 为上面的方法暂时修改，要改	TODO:	2.2版本要改掉
	 * @param type
	 */
	public List<Map<String, Object>> getOrgAndNameByType1(Integer type);
	
	public List<Map<String, Object>> searchCityByOrgId(Integer orgId) throws Exception;
	
	/**
	 * 根据主键获取交易城市（含详细信息）
	 * @author WKX
	 * @param orgCityId
	 * @throws Exception
	 * @since 2016年5月20日 下午3:08:23
	 */
	public List<Map<String, Object>> getOrgCityInfoById(Integer orgCityId) throws Exception;
	
	/**
	 * 根据公司名获取member列表
	 * @author GXW
	 * @param orgId 
	 * @param company 公司名
	 * @since 2016年6月18日
	 */
	List<Map<String, Object>> getMemberListByCompany(String company);
	
	/**
	 * 根据memberId获取对应唯一机构的交易城市列表
	 * @author GXW
	 * @param memberId 
	 * @since 2016年6月18日
	 */
	List<Map<String, Object>> getCityListByMemId(Integer memberId);
	
	/**
	 * 修改对象
	 * @author KHC
	 * @param org 
	 * @since 2016年10月9日
	 */
	public void updateModel(Org org);
	
	/**
	 * 通过认证状态和类型获取 用户信息
	 * @author WKX
	 * @param type 类型（企业0、机构1）
	 * @param state 认证状态（未认证0、审核中1、已认证2、未通过3）
	 * @since 2017年6月1日 下午4:38:06
	 */
	public List<Map<String,Object>> getOrgByTypeState(Integer type,Integer state);
}