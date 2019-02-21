package com.ry.core.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.CityDao;
import com.ry.core.entity.City;
import com.ry.core.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	@Resource
	CityDao cityDao;

	@Override
	public List<City> getList() {		
		return cityDao.getList();
	}

	@Override
	public List<Map<String, Object>> getOrgCity(Integer orgId) {
		return cityDao.getOrgCity(orgId);
	}

	
}
