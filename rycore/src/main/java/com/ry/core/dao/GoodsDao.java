package com.ry.core.dao;

import java.util.List;
import com.ry.core.entity.Goods;
import com.ry.core.form.integral.GoodsRequest;
import com.ry.util.page.PageResults;

/**
 * 
 * @author zwd
 * 关于商品的一些操作
 */
public interface GoodsDao{
	
	public PageResults<Goods> getPage(GoodsRequest goodsRes);
	
	public Integer addGoods(Goods goods);
	
	public void updateGoods(Goods goods);
	
	public Goods getGoods(Integer id);
	
	/**
	 * 获取热门商品
	 * @author MH
	 * @return
	 */
	public List<Goods> getList(Goods goods);
	
	/**
	 * 根据id获取商品对象
	 * @author MH
	 * @param id
	 * @return
	 */
	public Goods getModelById(Integer id);
	
	/**
	 * 获取未上架商品
	 * @author ZWD
	 * @param id
	 * @return
	 */
	public List<Goods> getUnPutawayById(Integer id);
	
}
