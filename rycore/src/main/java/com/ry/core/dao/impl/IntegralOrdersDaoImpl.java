package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.IntegralOrdersDao;
import com.ry.core.entity.IntegralOrders;
import com.ry.core.form.integral.OrdersRequest;
import com.ry.util.DateUtil;
import com.ry.util.page.PageResults;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

@Repository
public class IntegralOrdersDaoImpl extends BaseDao<IntegralOrders, Integer> implements IntegralOrdersDao {

	public PageResults<Map<String, Object>> getPage(OrdersRequest ordersReq) throws Exception {
		StringBuffer sql = new StringBuffer("from integral_orders where 1=1");
		List<Object> paras = new ArrayList<Object>();
		Calendar c = Calendar.getInstance();
		if(StringUtils.hasText(ordersReq.getExpressNo())){
			sql.append(" and express_no = ?");
			paras.add(ordersReq.getExpressNo());
		}
		if(StringUtils.hasText(ordersReq.getOrdersNo())){
			sql.append(" and no = ?");
			paras.add(ordersReq.getOrdersNo());
		}
		if(ordersReq.getMinSumIntegral() != null){
			sql.append(" and sum_price >= ?");
			paras.add(ordersReq.getMinSumIntegral());
		}
		if(ordersReq.getMaxSumIntegral() != null){
			sql.append(" and sum_price <= ?");
			paras.add(ordersReq.getMaxSumIntegral());
		}
		if(ordersReq.getState() != null){
			sql.append(" and state = ?");
			paras.add(ordersReq.getState());
		}
		if(StringUtils.hasText(ordersReq.getStartTime())){
			sql.append(" and create_time > ?");
			paras.add(ordersReq.getStartTime());
		}
		if(StringUtils.hasText(ordersReq.getEndTime())){
			sql.append(" and create_time < ?");
			try {
				c.setTime(DateUtil.parser(ordersReq.getEndTime(), DateUtil.FORMART3));
				c.add(Calendar.DATE, +1);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			paras.add(DateUtil.formart(c.getTime(), DateUtil.FORMART));
		}
		String count = "select count(*)" + sql.toString();
		PageResults<Map<String, Object>> list = findPageMapByFetchedSql("SELECT * " + sql.toString(),
				count, ordersReq.currentPage().intValue(), ordersReq.getLength().intValue(), paras.toArray());
		return list;
	}

	public Integer addOrders(IntegralOrders orders) {
		return save(orders);
	}

	public void updateOrders(IntegralOrders orders) {
		update(orders);
	}

	@Override
	public Map<String,Object> detail(Integer id) {
		String sql = "SELECT s.consignee,s.tel,s.address,s.detail_address,o.id,o.no,o.sum_price,o.state,o.express_company,o.express_no,o.create_time,o.remark from integral_orders o left join orders_goods g ON g.orders_id = o.id left join shipping_address s on s.id=o.shiping_address_id left join goods g1 on g1.id = g.goods_id where o.id = ?";
		List<Object> paras = new ArrayList<Object>();
		paras.add(id);
		List<Map<String,Object>> map = getListMapBySQL(sql, paras.toArray());
		return map.get(0);
	}
	
	public IntegralOrders getById(Integer Id){
		return get(Id);
	}

	@Override
	public List<Map<String, Object>> getOrdersGoodsList(Integer id) {
		String sql = "from orders_goods g left join goods g1 on g.goods_id = g1.id where orders_id=?";
		List<Object> paras = new ArrayList<Object>();
		paras.add(id);
		List<Map<String,Object>> map = getListMapBySQL("select g.count,g1.goods_name,g.integral "+sql, paras.toArray());
		return map;
	}
	/* (non-Javadoc)
	 * @see com.ry.core.dao.IntegralOrdersDao#saveModel(com.ry.core.entity.IntegralOrders)
	 */
	@Override
	public void saveModel(IntegralOrders IntegralOrders) {
		save(IntegralOrders);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.IntegralOrdersDao#getByNo(java.lang.String)
	 */
	@Override
	public List<IntegralOrders> getByNo(String no) {
		StringBuffer hql = new StringBuffer("FROM integral_orders WHERE no=?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(no);
		return getListByHQL(hql.toString(), paramList.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.IntegralOrdersDao#getPageList(java.lang.Integer)
	 */
	@Override
	public PageResults<Map<String, Object>> getPageList(Integer indexPage,Integer pageSize,Integer memberId,Integer state) throws Exception {
		StringBuffer sql = new StringBuffer("  SELECT integral_orders.no,integral_orders.id,integral_orders.state FROM integral_orders WHERE 1=1 ");
		StringBuffer count = new StringBuffer(" SELECT COUNT(*) FROM integral_orders WHERE 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if(memberId != null ){
			sql.append(" and memberId=? ");
			count.append(" and memberId=? ");
			params.add(memberId);
		}
		if(state != null){
			if(state == 2){//去除无效的订单
				sql.append(" AND integral_orders.state NOT IN(2) ");
				count.append(" AND integral_orders.state NOT IN(2) ");
			}
		}
		sql.append(" ORDER BY integral_orders.id DESC");
		return findPageMapByFetchedSql(sql.toString(), count.toString(), indexPage, pageSize, params.toArray());
	}

}
