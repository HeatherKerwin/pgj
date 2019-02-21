package com.ry.core.dao;
import java.util.List;
import java.util.Map;

import com.ry.core.entity.Address;
import com.ry.core.form.AddressForm;
import com.ry.util.page.PageResults;


public interface AddressDao {
	
	/**
	 * 编辑地图时初始化地图的省市
	 * @since 2016年9月5日 
	 */
	public List<Map<String, Object>> getAddressInfoById(Integer cityid);
	
	/**
	 * 根据主键获取对象
	 * @author ZY
	 * @param id
	 * @since 2016年7月28日 下午3:44:51
	 */
	public Address getById(Integer id);
	
	/**
	 * 保存对象
	 * @author ZY
	 * @param address
	 * @since 2016年7月28日 下午3:44:34
	 */
	public void saveModel(Address address);
	
	/**
	 * 更新对象
	 * @author ZY
	 * @param address
	 * @since 2016年7月28日 下午3:44:44
	 */
	public void updateModel (Address address);
	
	/**
	 * 分页显示贴现地址
	 * @param pageIndex
	 * @param pageSize
	 * @param form
	 */
	public PageResults<Address> getPageList(Integer pageIndex,Integer pageSize,AddressForm form);
	
	/**
	 * 设置默认贴现地址
	 * @author WKX
	 * @param id
	 * @param memberId
	 * @since 2016年8月9日 下午7:29:21
	 */
	public void updateDefaultByMemberId(Integer id,Integer memberId);
	
	/**
	 * 根据主键删除对象
	 * @author WKX
	 * @param id
	 * @since 2016年8月9日 下午7:47:56
	 */
	public void deleteById(Integer id);
	
	/**
	 * 获取默认交易城市
	 * @author WKX
	 * @param memberId
	 * @since 2016年8月12日 下午1:35:15
	 */
	public List<Address> getDefaultByMemberId(Integer memberId);
	
	/**
	 * 获取用户所有交易城市
	 * @author WKX
	 * @param memberId 用户主键
	 * @since 2017年5月8日 上午9:40:53
	 */
	public List<Address> getByMemberId(Integer memberId);
}