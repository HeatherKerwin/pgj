package com.ry.core.service;

import java.util.List;

import com.ry.core.entity.Daylimit;

public interface DaylimitService {

	List<Daylimit> getDaylimitList(String day, String id);
	
	Double countAllmoney(Long endordertime,Long beginordertime);
	
	void addDaylimit(Daylimit daylimit);
	
	void updateDaylimit(Daylimit daylimit);
		
}
