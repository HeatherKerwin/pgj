package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.CityDao;
import com.ry.core.entity.City;

@Repository
public class CityDaoImpl extends BaseDao<City,Integer > implements CityDao{

	@Override
	public List<City> getList() {
		List<City> cities = getListByHQL("from city ", null);
		return cities;
	}

	@Override
	public List<Map<String, Object>> getOrgCity(Integer orgId) {
		StringBuffer sql = new StringBuffer("select ct.id, ct.name from  city ct ,org_city oc where oc.city_id = ct.id  "); 
		List<Object> paramList = new ArrayList<Object>();
		if(orgId!=null && !"".equals(orgId)){
			sql.append(" and oc.org_id = ?");
			paramList.add(orgId);
		}
		List<Map<String, Object>> lists = getListMapBySQL(sql.toString(), paramList.toArray());
		return lists;
	}

}
