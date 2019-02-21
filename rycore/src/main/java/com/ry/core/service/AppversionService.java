package com.ry.core.service;

import java.util.List;

import com.ry.core.entity.Appversion;

public interface AppversionService {
	
	Appversion getAppversion();
	
	void updateAppversion(Appversion appversion);
	
	List<Appversion> getList();
		
}
