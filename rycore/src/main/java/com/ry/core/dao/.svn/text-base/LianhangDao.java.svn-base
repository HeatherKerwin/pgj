package com.ry.core.dao;

import java.util.List;

import com.ry.core.entity.Lianhang;
import com.ry.util.page.PageResults;

public interface LianhangDao {
	
	void addLianhang(Lianhang lianhang);
	
	List<Lianhang> getList(Lianhang lianhang);
	
	public PageResults<Lianhang> getPageList(Lianhang lianhang, Integer currentPage, Integer pageSize);
	
	/**
	 * pc联行号查询，yinhangdesc是3个以内的关键字
	 * @author ZY
	 * @param lianhang
	 * @return
	 * 2016年11月15日下午2:09:05
	 */
	public PageResults<Lianhang> getListBylianhang(Lianhang lianhang,Integer pageIndex,Integer pageSize);
}
