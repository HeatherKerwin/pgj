package com.ry.core.dao;

import java.util.List;
import java.util.Map;

import com.ry.core.entity.IntegralOrders;
import com.ry.core.form.integral.OrdersRequest;
import com.ry.util.page.PageResults;

public interface IntegralOrdersDao {
	/**
	 * 分页动态查询
	 * @param ordersReq 
	 * @author ZWD 
	 * @return 
	 * @throws Exception
	 */
	public PageResults<Map<String, Object>> getPage(OrdersRequest ordersReq) throws Exception;
	
	public Integer addOrders(IntegralOrders orders);
	
	public void updateOrders(IntegralOrders orders);
	
	public Map<String,Object> detail(Integer id);
	
	public IntegralOrders getById(Integer Id);
	
	public List<Map<String,Object>> getOrdersGoodsList(Integer id);
	
	/**
	 * 保存订单 
	 * @author MH
	 * @param IntegralOrders
	 */
	public void saveModel(IntegralOrders IntegralOrders);
	
	/**
	 * 根据订单编号获取对象
	 * @author MH
	 * @param no 订单编号
	 * @return
	 */
	public List<IntegralOrders> getByNo(String no);
	
	
	/**
	 * 分页获取用户的订单
	 * @author MH
	 * @param memberId 用户Id
	 * @return
	 * @throws Exception
	 */
	public PageResults<Map<String, Object>> getPageList(Integer indexPage,Integer pageSize,Integer memberId,Integer state) throws Exception;
}
