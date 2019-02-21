package com.ry.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.GoodsDao;
import com.ry.core.entity.Goods;
import com.ry.core.form.integral.GoodsRequest;
import com.ry.core.service.GoodsService;
import com.ry.util.page.PageResults;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Resource
	private GoodsDao goodsDao;
	@Override
	public PageResults<Goods> getPage(GoodsRequest goodsRes) {
		return goodsDao.getPage(goodsRes);
	}
	

	@Override
	public Integer addGoods(Goods goods) {
		return goodsDao.addGoods(goods);
	}

	@Override
	public void updateGoods(Goods goods) {
		goodsDao.updateGoods(goods);
	}
	/* (non-Javadoc)
	 * @see com.ry.core.service.GoodsService#getlist()
	 */
	@Override
	public List<Goods> getlist(Goods goods) {
		return goodsDao.getList(goods);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.GoodsService#getById(java.lang.Integer)
	 */
	@Override
	public Goods getModelById(Integer id) {
		return goodsDao.getModelById(id);
	}



	@Override
	public PageResults<Goods> getPageList(Integer pageIndex, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void putaway(Integer id) {
		List<Goods> goods = goodsDao.getUnPutawayById(id);
		for (Goods good : goods) {
			good.setState(1);
			goodsDao.updateGoods(good);
		}
	}
}
