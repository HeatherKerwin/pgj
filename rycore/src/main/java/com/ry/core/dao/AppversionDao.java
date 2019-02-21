package com.ry.core.dao;

import java.util.List;

import com.ry.core.entity.Appversion;

public interface AppversionDao {
	
	Appversion getAppversion();		
	
	void updateAppersion(Appversion appversion);
	
	List<Appversion> getList();
}
