package com.ry.core.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.OrdersGoodsDao;
import com.ry.core.entity.OrdersGoods;
import com.ry.core.service.OrdersGoodsService;

@Service
public class OrdersGoodsServiceImpl implements OrdersGoodsService {
	
	@Resource
	OrdersGoodsDao ordersGoodsDao;

	@Override
	public OrdersGoods getById(Integer id) {
		return ordersGoodsDao.getById(id);
	}

	@Override
	public void saveModel(OrdersGoods ordersGoods) {
		ordersGoodsDao.saveModel(ordersGoods);
	}

	@Override
	public List<OrdersGoods> getByOrderId(Integer ordersId) {
		return ordersGoodsDao.getByOrderId(ordersId);
	}

	@Override
	public List<Map<String, Object>> getByGoodsOrderId(Integer ordersId) {
		return ordersGoodsDao.getByGoodsOrderId(ordersId);
	}

}
