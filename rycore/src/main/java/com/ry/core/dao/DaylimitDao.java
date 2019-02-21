package com.ry.core.dao;

import java.util.List;

import com.ry.core.entity.Daylimit;

public interface DaylimitDao {
	
	List<Daylimit> getList(String day, String id);
	
	Double countAllmoney(Long endordertime,Long beginordertime);
	
	void add(Daylimit daylimit);
	
	void updateDaylimit(Daylimit daylimit);
}
