package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.OrdersGoodsDao;
import com.ry.core.entity.OrdersGoods;

@Repository
public class OrdersGoodsDaoImpl extends BaseDao<OrdersGoods, Integer> implements OrdersGoodsDao {

	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrdersGoodsDao#getById(java.lang.Integer)
	 */
	@Override
	public OrdersGoods getById(Integer id) {
		return get(id);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrdersGoodsDao#saveModel(com.ry.core.entity.OrdersGoods)
	 */
	@Override
	public void saveModel(OrdersGoods ordersGoods) {
		save(ordersGoods);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrdersGoodsDao#getByOrderId(java.lang.Integer)
	 */
	@Override
	public List<OrdersGoods> getByOrderId(Integer ordersId) {
		StringBuffer hql = new StringBuffer("FROM orders_goods WHERE orders_id=?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(ordersId);
		return getListByHQL(hql.toString(), paramList.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrdersGoodsDao#getByGoodsOrderId(java.lang.Integer)
	 */
	@Override
	public List<Map<String, Object>> getByGoodsOrderId(Integer ordersId) {
		StringBuffer sql = new StringBuffer(" SELECT og.`count`,og.`integral` ,g.`banner1`,g.`goods_name`FROM orders_goods og LEFT JOIN  goods g ON og.`goods_id` = g.`id` where 1=1 and og.`orders_id`=?");
		List<Object> params = new ArrayList<Object>();
		params.add(ordersId);
		return getListMapBySQL(sql.toString(), params.toArray());
	}

}
