package com.ry.core.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.GongcuiDao;
import com.ry.core.entity.Gongcui;

@Repository
public class GongcuiDaoImpl extends BaseDao<Gongcui, Integer> implements GongcuiDao {

	@Override
	public void addGongcui(Gongcui gongcui) {
		save(gongcui);
	}

	@Override
	public List<Gongcui> getList(Integer start, Integer end) {
		List<Gongcui> gongcuis = (List<Gongcui>) getSession().createQuery("from gongcui order by gongcuidate desc ").setFirstResult(start).setMaxResults(end).list();
		return gongcuis;		
	}

	@Override
	public List<Gongcui> getList(String gongcuinumber) {
		List<Gongcui> gongcuis = getListByHQL("from gongcui where gongcuinumber like ? ", new String[]{gongcuinumber});
		return gongcuis;
	}

}
