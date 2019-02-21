package com.ry.core.service;

import java.util.List;

import com.ry.core.entity.Goods;
import com.ry.core.form.integral.GoodsRequest;
import com.ry.util.page.PageResults;

public interface GoodsService {
	/**
	 * 分页动态查询商品
	 * @author ZWD
	 * @return
	 */
	public PageResults<Goods> getPage(GoodsRequest goodsRes);
	
	/**
	 * 新增商品
	 * @author ZWD
	 * @return
	 */
	public Integer addGoods(Goods goods);
	
	/**
	 * 获取热门商品
	 * @author MH
	 * @return
	 */
	public void updateGoods(Goods goods);
	
	/**
	 * 获取热门商品
	 * @author MH
	 * @return
	 */
	public List<Goods> getlist(Goods goods);
	
	/**
	 * 根据id获取商品对象
	 * @author MH
	 * @param id
	 * @return
	 */
	public Goods getModelById(Integer id);
	
	/**
	 * 分页查询所有的商品
	 * @author MH
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PageResults<Goods> getPageList(Integer pageIndex, Integer pageSize);
	
	public void putaway(Integer id);

}
