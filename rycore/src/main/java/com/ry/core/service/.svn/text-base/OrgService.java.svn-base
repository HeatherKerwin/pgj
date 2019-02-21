/**
 * 
 */
package com.ry.core.service;

import java.util.List;
import java.util.Map;

import com.ry.core.entity.Org;
import com.ry.core.form.org.ListRequest;
import com.ry.util.excel.ViewExcel;
import com.ry.util.page.PageResults;

/**
 * 名称: OrgDao.java<br>
 * 描述: <br>
 * 类型: JAVA<br>
 * 最近修改时间:2016年3月2日 下午6:55:04<br>
 * @since  2016年3月2日
 * @author li.xiaofei 
 */
public interface OrgService {
	
	/**
	 * 分页查询认证信息列表
	 * @param list
	 * @return
	 */
	PageResults<Map<String,Object>> getPageList(ListRequest list);
	
	/**
	 * 根据主键获取认证信息（已认证）
	 * @author WKX
	 * @param id
	 * @since 2016年3月9日 下午4:22:47
	 */
	public Map<String,Object> getInfoById(Integer id);
	
	/**
	 * 根据用户主键获取其 认证
	 * @author WKX
	 * @param memberId 用户主键
	 * @since 2016年3月22日 下午4:20:52
	 */
	public Org getByMemberId(Integer memberId) throws Exception;
	
	/**
	 * 保存对象（并返回对象）
	 * @author WKX
	 * @param org
	 * @since 2016年3月22日 下午4:29:29
	 */
	public Org saveModel(Org org);
	
	/**
	 * 根据主键获取对象
	 * @author WKX
	 * @param id
	 * @throws Exception
	 * @since 2016年3月23日 下午2:23:13
	 */
	public Org getById(Integer id) throws Exception;
	
	/**
	 * 根据类型获取认证的机构（或企业）
	 */
	public List<Org> getByType(Integer type) throws Exception;
	
	/**
	 * 根据类型获取认证的机构（或企业）含名称
	 */
	public List<Map<String, Object>> getOrgAndNameByType(Integer type) throws Exception;
	
	/**
	 * 为上面的方法修改：cms获取机构的info信息 来查数据	TODO:2.2版本要改
	 */
	public List<Map<String, Object>> getOrgAndNameByType1(Integer type) throws Exception;
	
	/**
	 * 根据机构Id查询该机构的所有交易城市
	 * @param orgId
	 * @throws Exception
	 */
	public List<Map<String, Object>> searchCityByOrgId(Integer orgId) throws Exception;
	
	/**
	 * 根据主键获取交易城市
	 * @author WKX
	 * @param orgCityId
	 * @throws Exception
	 * @since 2016年5月20日 下午3:11:52
	 */
	public Map<String, Object> getOrgCityInfoById(Integer orgCityId) throws Exception;
	
	/**
	 * 分页查询企业认证信息列表
	 * @author GXW
	 * @param list 查询条件
	 * @since 2016年6月15日
	 */
	PageResults<Map<String,Object>> getMemberPageList(ListRequest list);
	
	/**
	 * 根据公司名获取member列表
	 * @author GXW
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
	 * 汇总机构评价（评分）
	 * @author WKX
	 * @param org
	 * @param orgId
	 * @throws Exception
	 * @since 2016年8月18日 下午7:06:55
	 */
	public Map<String,Object> getComment(Map<String,Object> org,Integer orgId) throws Exception;
	
	/**
	 * 修改对象
	 * @author KHC
	 * @param org 
	 * @since 2016年10月9日
	 */
	public void updateModel(Org org);
	
	/**
	 * 获取VeiwExcel对象
	 */
	ViewExcel getByExcelData(ListRequest req, String orderType);

}