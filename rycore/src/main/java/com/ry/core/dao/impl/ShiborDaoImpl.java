package com.ry.core.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.ShiborDao;
import com.ry.core.entity.News;
import com.ry.core.entity.Shibor;
@Repository
public class ShiborDaoImpl extends BaseDao<Shibor,Integer > implements ShiborDao{

	@Override
	public Shibor getShibor(int id) {
		return get(id);
	}

	@Override
	public void insertShibor(Shibor shibor) {
		save(shibor);
	}

	@Override
	public List<Shibor> getList(Integer start, Integer end) {
		List<Shibor> shibors = (List<Shibor>) getSession().createQuery("from shibor order by day desc ").setFirstResult(start).setMaxResults(end).list();
		return shibors;		
	}

	@Override
	public Shibor getShibor(String day) {
		return getByHQL("from shibor where day like ? ", new Object[]{day});
	}


}
