package com.ry.core.service;

import com.ry.core.entity.ShippingAddress;
import com.ry.util.page.PageResults;

public interface ShippingAddressService {

	/**保存商品收货地址
	 * @author MH
	 * @param shippingAddress 对象
	 */
	public void  saveModel(ShippingAddress shippingAddress);
	
	/**
	 * 修改收货地址
	 * @author MH
	 * @param shippingAddress
	 */
	public void updateModel(ShippingAddress shippingAddress);
	
	/**
	 * 根据用户ID 获取对象
	 * @author MH
	 * @param memberId 用户ID
	 * @return
	 */
	public ShippingAddress getDefaultByMemberId(Integer memberId);
	
	/**
	 * 设置默认城市
	 * @author MH
	 * @param id 设置默认的主键
	 * @param memberId
	 * @since 2016年8月9日 下午7:30:24
	 */
	public void updateDefaultByMemberId(Integer id,Integer memberId);
	
	/**
	 * 根据商品收货地址的Id，获取对象
	 * @author MH
	 * @param id
	 * @return
	 */
	public ShippingAddress getById(Integer id);
	
	/**
	 * 根据主键删除对象
	 * @author MH
	 * @param id
	 */
	public void deleteById(Integer id);
	
	/**
	 * 分页显示贴现地址
	 * @param pageIndex 第几页
	 * @param pageSize 一页几条
	 * @param form 条件
	 */
	public PageResults<ShippingAddress> getPageList(Integer pageIndex,Integer pageSize,ShippingAddress address);
}
