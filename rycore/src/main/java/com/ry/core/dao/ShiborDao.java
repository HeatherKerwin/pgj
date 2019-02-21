package com.ry.core.dao;

import java.util.List;

import com.ry.core.entity.Shibor;

public interface ShiborDao {
	Shibor getShibor(int id);
	
	Shibor getShibor(String day);
	
	void insertShibor(Shibor shibor);
	
	List<Shibor> getList(Integer start, Integer end);
}
