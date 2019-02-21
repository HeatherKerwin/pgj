package com.ry.core.service.impl;

import java.util.List;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.GoodsDao;
import com.ry.core.dao.IntegralOrdersDao;
import com.ry.core.dao.IntegraltradingDetailDao;
import com.ry.core.dao.OrdersGoodsDao;
import com.ry.core.entity.Goods;
import com.ry.core.entity.IntegralOrders;
import com.ry.core.entity.IntegraltradingDetail;
import com.ry.core.entity.OrdersGoods;
import com.ry.core.form.integral.OrdersRequest;
import com.ry.core.service.IntegralOrdersService;
import com.ry.util.page.PageResults;

@Service
public class IntegralOrdersServiceImpl implements IntegralOrdersService {

	@Resource
	private IntegralOrdersDao goodsOrdersDao;
	
	@Resource
	private GoodsDao  goodsDao;
	
	@Resource
	private OrdersGoodsDao ordersGoodsDao;
	
	@Resource
	private IntegraltradingDetailDao integraltradingDetailDao; 

	public PageResults<Map<String, Object>> getPage(OrdersRequest ordersReq) throws Exception {
		return goodsOrdersDao.getPage(ordersReq);
	}

	public Integer addOrders(IntegralOrders orders) {
		return null;
	}

	public void updateOrders(IntegralOrders orders) {
		goodsOrdersDao.updateOrders(orders);
	}

	@Override
	public Map<String , Object> detail(Integer id) {
		return goodsOrdersDao.detail(id);
	}

	@Override
	public IntegralOrders getById(Integer Id) {
		return goodsOrdersDao.getById(Id);
	}

	@Override
	public List<Map<String,Object>> getOrdersGoodsById(Integer id) {
		return goodsOrdersDao.getOrdersGoodsList(id);
	}
	
//	/* (non-Javadoc)
//	 * @see com.ry.core.service.IntegralOrdersService#saveModel(com.ry.core.entity.IntegralOrders, java.lang.Integer, java.lang.Integer, java.lang.Integer)
//	 */
//	@Override
//	public void saveModel(IntegralOrders integralOrders, Integer goodsId, Integer integral, Integer count) {
//		Session session = null;
//		Transaction tx = null;
//        try {
//        	session = Hibernate4Util.getCurrentSession();
//    		tx = session.beginTransaction();    
//    		
//        	if(integralOrders != null){//保存订单
//    			goodsOrdersDao.saveModel(integralOrders);
//    		}
//    		Goods goods = null;
//    		if(goodsId != null){//修改某商品的库存
//    			goods = goodsDao.getModelById(goodsId);
//    			goods.setStock(goods.getStock()-count);
//    			goodsDao.updateGoods(goods);
//    		}
//    		List<IntegraltradingDetail> list = integraltradingDetailDao.getByMemberId(integralOrders.getMemberId());
//    		if(list.size()>0){
//    			Integer total = list.get(0).getIntegralTotal();
//    			total = total - integral;
//    			IntegraltradingDetail integraltradingDetail = new IntegraltradingDetail();
//    			integraltradingDetail.setIntegralTotal(total);
//    			integraltradingDetail.setNum(integral);
//    			integraltradingDetail.setTitle(goods.getGoodsName());
//    			integraltradingDetail.setMemberId(integralOrders.getMemberId());
//    			integraltradingDetail.setState(0);
//    			integraltradingDetail.setTradingTime(new Date());
//    			integraltradingDetailDao.saveModel(integraltradingDetail);
//    		}
//    		List<IntegralOrders> listorders = goodsOrdersDao.getByNo(integralOrders.getNo());
//    		if(listorders.size()>0 && listorders != null){
//    			OrdersGoods oedersgoods = new OrdersGoods();
//        		oedersgoods.setConut(count);
//        		oedersgoods.setGoodsId(goodsId);
//        		oedersgoods.setIntegral(integral);
//        		oedersgoods.setOrdersId(listorders.get(0).getId());
//        		ordersGoodsDao.saveModel(oedersgoods);
//    		}
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.IntegralOrdersService#saveModel(com.ry.core.entity.IntegralOrders, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void saveModel(IntegralOrders integralOrders, Integer goodsId, Integer integral, Integer count) throws Exception {
			Integer ordersId = null;
			if(integralOrders != null){//保存订单
    		     ordersId = goodsOrdersDao.addOrders(integralOrders);
    		   
    		}
        	 if(ordersId == null) throw new Exception();
    		Goods goods = null;
    		if(goodsId != null){//修改某商品的库存
    			goods = goodsDao.getModelById(goodsId);
    			if(count > goods.getStock()) throw new Exception();//库存不足，下单失败
    			if(goods.getStock()-count == 0)goods.setState(2);//已卖完
    			goods.setStock(goods.getStock()-count);
    			goodsDao.updateGoods(goods);
    		}
    		List<IntegraltradingDetail> list = integraltradingDetailDao.getByMemberId(integralOrders.getMemberId());
    		if(list.size()>0){
    			Integer total = list.get(0).getIntegralTotal();
    			total = total - integral;
    			IntegraltradingDetail integraltradingDetail = new IntegraltradingDetail();
    			integraltradingDetail.setIntegralTotal(total);
    			integraltradingDetail.setNum(integral);
    			integraltradingDetail.setTitle(goods.getGoodsName());
    			integraltradingDetail.setMemberId(integralOrders.getMemberId());
    			integraltradingDetail.setState(0);
    			integraltradingDetail.setTradingTime(new Date());
    			integraltradingDetailDao.saveModel(integraltradingDetail);
    		}
    			
    		OrdersGoods ordersgoods = new OrdersGoods();
    		ordersgoods.setCount(count);
    		ordersgoods.setGoodsId(goodsId);
    		ordersgoods.setIntegral(integral);
    		ordersgoods.setOrdersId(ordersId);
        	ordersGoodsDao.saveModel(ordersgoods);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.IntegralOrdersService#getByNo(java.lang.String)
	 */
	@Override
	public IntegralOrders getByNo(String no) {
		List<IntegralOrders> list = goodsOrdersDao.getByNo(no);
		if(list != null && list.size()>0) {
			return list.get(0);
		}else{
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.IntegralOrdersService#getPageList(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PageResults<Map<String, Object>> getPageList(Integer indexPage, Integer pageSize, Integer memberId,Integer state)
			throws Exception {
		return goodsOrdersDao.getPageList(indexPage, pageSize, memberId,state);
	}

}
