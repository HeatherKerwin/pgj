package com.ry.core.dao;

import java.util.List;
import java.util.Map;

import com.ry.core.entity.City;

public interface CityDao {
	List<City> getList();
	
	List<Map<String, Object>> getOrgCity(Integer orgId);
}
