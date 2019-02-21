package com.ry.core.dao;

import java.util.List;

import com.ry.core.entity.Saleprice;

public interface SalepriceDao {
	public List<Saleprice> getList(Integer id, String begintime, String endtime);
		
}

