package com.ry.core.service;

import java.util.List;
import java.util.Map;

import com.ry.core.entity.OrdersGoods;

public interface OrdersGoodsService {

	/**
	 * 根据主键获取订单的商品
	 * @author MH
	 * @param id 主键
	 * @return
	 */
	public OrdersGoods getById(Integer id);
	
	/**
	 * 保存订单的商品
	 * @author MH
	 * @param ordersGoods
	 */
	public void saveModel(OrdersGoods ordersGoods);
	
	/**
	 * 根据订单Id 获取订单商品表信息
	 * @author MH
	 * @param ordersId
	 * @return
	 */
	public List<OrdersGoods> getByOrderId(Integer ordersId);
	
	/**
	 * 根据订单Id 获取该订单的已购商品信息
	 * @author MH
	 * @param ordersId
	 * @return
	 */
	public List<Map<String,Object>> getByGoodsOrderId(Integer ordersId);
}
