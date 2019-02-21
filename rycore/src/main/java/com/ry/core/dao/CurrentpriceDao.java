package com.ry.core.dao;

import java.util.List;

import com.ry.core.entity.Currentprice;
import com.ry.util.page.PageResults;

public interface CurrentpriceDao {
	
	PageResults<Currentprice> pageList(Currentprice cp,int pageNo, int pageSize);
	
	
	List<Currentprice> queryList(Currentprice cp);
	
	int updateAll(List<Currentprice> currentpriceList);
	
	
}
