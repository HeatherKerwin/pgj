package com.ry.core.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.ProviceDao;
import com.ry.core.entity.Provice;

@Repository
public class ProviceDaoImpl extends BaseDao<Provice, Integer> implements ProviceDao {

	@Override
	public List<Provice> getList() {
		
		return getListByHQL("from provice", null);
	}

	
}
