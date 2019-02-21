package com.ry.core.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.AppversionDao;
import com.ry.core.dao.BaseDao;
import com.ry.core.entity.Appversion;

@Repository
public class AppversionDaoImpl extends BaseDao<Appversion,Integer > implements AppversionDao{

	@Override
	public Appversion getAppversion() {
		Appversion appversion = (Appversion)getListByHQL(" from appversion ",new String[]{}).get(0);		
		return appversion;		
	}

	@Override
	public void updateAppersion(Appversion appversion) {
		update(appversion);
	}

	@Override
	public List<Appversion> getList() {
		
		return getListByHQL("from appversion", null);
	}

}
