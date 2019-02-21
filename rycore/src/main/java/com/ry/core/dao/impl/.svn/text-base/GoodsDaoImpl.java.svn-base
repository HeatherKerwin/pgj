package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.GoodsDao;
import com.ry.core.entity.Goods;
import com.ry.core.form.integral.GoodsRequest;
import com.ry.util.page.PageResults;

@Repository
public class GoodsDaoImpl extends BaseDao<Goods,Integer> implements GoodsDao{

	@Override
	public PageResults<Goods> getPage(GoodsRequest goodsRes) {
		StringBuffer hql = new StringBuffer("from goods where 1=1");
		List<Object> paras = new ArrayList<Object>();
		if(StringUtils.hasText(goodsRes.getGoodsName())){
			hql.append(" and goods_name like ?");
			paras.add("%"+goodsRes.getGoodsName()+"%");
		}
		if(StringUtils.hasText(goodsRes.getGoodsNo())){
			hql.append(" and no = ?");
			paras.add(goodsRes.getGoodsNo());
		}
		if(goodsRes.getState() != null){
			hql.append(" and state = ?");
			paras.add(goodsRes.getState());
		}
		if(goodsRes.getMinPrice() != null){
			hql.append(" and integral >= ?");
			paras.add(goodsRes.getMinPrice());
		}
		if(goodsRes.getMaxPrice() != null){
			hql.append(" and integral <= ?");
			paras.add(goodsRes.getMaxPrice());
		}
		if(goodsRes.getMinStock() != null){
			hql.append(" and stock >= ?");
			paras.add(goodsRes.getMinStock());
		}
		if(goodsRes.getMaxStock() != null){
			hql.append(" and stock <= ?");
			paras.add(goodsRes.getMaxStock());
		}
		String count = "select count(*) " + hql.toString();
		hql.append(" order by sort");
		PageResults<Goods> list = findPageByFetchedHql(hql.toString(),
				count, goodsRes.currentPage().intValue(), goodsRes.getLength().intValue(), paras.toArray());
		return list;
	}

	@Override
	public Integer addGoods(Goods goods) {
		return save(goods);
	}

	@Override
	public void updateGoods(Goods goods) {
		update(goods);
	}

	@Override
	public Goods getGoods(Integer id) {
		return get(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.GoodsDao#getList()
	 */
	@Override
	public List<Goods> getList(Goods goods) {
		StringBuffer hql = new StringBuffer("FROM goods where 1=1");
		List<Object> paramList = new ArrayList<Object>();
		if(goods != null){
			if(goods.getHotGoods() == 0){//查询热门商品
				hql.append(" and hot_goods = ?");
				paramList.add(0);
			}
			if(goods.getState() != null && goods.getState() == 1){//已上架的商品
				hql.append(" and state = ?");
				paramList.add(1);
			}
		}
		hql.append(" ORDER BY sort ASC");
		return getListByHQL(hql.toString(), paramList.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.GoodsDao#getModelById(java.lang.Integer)
	 */
	@Override
	public Goods getModelById(Integer id) {
		return get(id);
	}

	@Override
	public List<Goods> getUnPutawayById(Integer id) {
		String hql = "from goods where state = 0";
		List<Object> paras = new ArrayList<Object>();
		if(id!=null){
			hql += " and id = ?";
			paras.add(id);
		}
		return getListByHQL(hql, paras.toArray());
	}
	
}
