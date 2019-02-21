package com.ry.core.service;

import java.util.List;
import java.util.Map;

import com.ry.core.entity.IntegralOrders;
import com.ry.core.form.integral.OrdersRequest;
import com.ry.util.page.PageResults;

public interface IntegralOrdersService {
	/**
	 * 
	 * @param ordersReq
	 * @param oIds
	 * @return
	 * @throws Exception
	 */
	public PageResults<Map<String, Object>> getPage(OrdersRequest ordersReq) throws Exception;
	
	public Integer addOrders(IntegralOrders orders);
	
	public void updateOrders(IntegralOrders orders);
	
	public Map<String , Object> detail(Integer id);
	
	public IntegralOrders getById(Integer Id);
	
	public List<Map<String,Object>> getOrdersGoodsById(Integer id);
	
	
	/**
	 * 保存订单 ，同时修改积分明细表，商品表，订单商品表
	 * @author MH
	 * @param IntegralOrders
	 * @param goodsId
	 * @param integral
	 * @param count
	 */
	public void saveModel(IntegralOrders IntegralOrders,Integer goodsId ,Integer integral,Integer count) throws Exception;
	
	/**
	 * 根据订单编号获取对象
	 * @author MH
	 * @param no 订单编号
	 * @return
	 */
	public IntegralOrders getByNo(String no);
	
	/**
	 * 分页获取用户的订单
	 * @author MH
	 * @param memberId 用户Id
	 * @return
	 * @throws Exception
	 */
	public PageResults<Map<String, Object>> getPageList(Integer indexPage,Integer pageSize,Integer memberId,Integer state) throws Exception;
}
