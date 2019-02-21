package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.DaylimitDao;
import com.ry.core.entity.Daylimit;

@Repository
public class DaylimitDaoImpl extends BaseDao<Daylimit, Integer> implements DaylimitDao {

	@Override
	public List<Daylimit> getList(String day, String id) {
		String hql = "from daylimit where 1=1 ";
		List paramList = new ArrayList();
		if (day != null && !"".equals(day)) {
			hql += " and day like ? ";
			paramList.add(day);
		}
		
		if (id != null && !"".equals(id)) {			
			hql += " and id like ? ";
			paramList.add(Integer.parseInt(id));
		}
		List<Daylimit> list = getListByHQL(hql, paramList.toArray());
		return list;
	}

	@Override
	public Double countAllmoney(Long endordertime,Long beginordertime) {
		
		Double count = countByHQLDouble("select sum(allmoney) from discountrecord where ordertime <= ? and ordertime >= ? and orderflag!=-1", new Object[]{endordertime,beginordertime});
		return count;
	}

	@Override
	public void add(Daylimit daylimit) {
		save(daylimit);		
	}

	@Override
	public void updateDaylimit(Daylimit daylimit) {
		update(daylimit);
	}

}
