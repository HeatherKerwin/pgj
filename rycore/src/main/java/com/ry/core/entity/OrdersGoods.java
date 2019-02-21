package com.ry.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 订单商品表
 * @author MH
 */   
@Entity(name = "orders_goods")
public class OrdersGoods extends BaseModel{

	private static final long serialVersionUID = 1L;
	
	private Integer count;//购买的商品数量
	private Integer integral;//购买的单个商品的积分
	private Integer goodsId;//商品的Id
	private Integer ordersId;//订单表的订单编号
	
	public Integer getCount() {
		return count;
	}
	
	public void setCount(Integer count) {
		this.count = count;
	}
	
	public Integer getIntegral() {
		return integral;
	}
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
	
	@Column(name="goods_id")
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	
	@Column(name="orders_id")
	public Integer getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(Integer ordersId) {
		this.ordersId = ordersId;
	}
}
