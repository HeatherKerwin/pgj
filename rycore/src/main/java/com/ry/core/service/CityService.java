package com.ry.core.service;

import java.util.List;
import java.util.Map;

import com.ry.core.entity.City;

public interface CityService {
	
	public List<City> getList();
	
	List<Map<String, Object>> getOrgCity(Integer orgId);
}
