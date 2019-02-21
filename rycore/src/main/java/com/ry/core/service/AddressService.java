package com.ry.core.service;

import java.util.List;
import java.util.Map;

import com.ry.core.entity.Address;
import com.ry.core.form.AddressForm;
import com.ry.util.page.PageResults;

public interface AddressService {
	
	/**
	 * 编辑地图时初始化地图的省市
	 * @since 2016年9月5日 
	 */
	public Map<String, Object> getAddressInfoById(Integer cityid);
	
	/**
	 * 根据主键获取对象
	 * @author ZY
	 * @param id
	 * @since 2016年7月28日 下午4:18:46
	 */
	public Address getById(Integer id);
	
	/**
	 * 保存对象
	 * @author ZY
	 * @param address
	 * @since 2016年7月28日 下午4:19:51
	 */
	public void saveModel(Address address);
	
	/**
	 * 更新对象
	 * @author ZY
	 * @param address
	 * @since 2016年7月28日 下午4:20:00
	 */
	public void updateModel (Address address);
	
	/**
	 * 分页显示贴现地址
	 * @param pageIndex 第几页
	 * @param pageSize 一页几条
	 * @param form 条件
	 */
	public PageResults<Address> getPageList(Integer pageIndex,Integer pageSize,AddressForm form);
	
	/**
	 * 设置默认城市
	 * @author WKX
	 * @param id 设置默认的主键
	 * @param memberId
	 * @since 2016年8月9日 下午7:30:24
	 */
	public void updateDefaultByMemberId(Integer id,Integer memberId);
	
	/**
	 * 根据主键删除对象
	 * @author WKX
	 * @param id
	 * @since 2016年8月9日 下午7:48:50
	 */
	public void deleteById(Integer id);
	
	/**
	 * 根据用户主键获取默认贴现地址
	 * @author WKX
	 * @param memberId
	 * @since 2016年8月12日 下午1:37:32
	 */
	public Address getDefaultByMemberId(Integer memberId);
	
	/**
	 * 获取用户所有贴现地址
	 * @author WKX
	 * @param memberId 用户主键
	 * @since 2017年5月8日 上午9:42:15
	 */
	public List<Address> getByMemberId(Integer memberId);
}
