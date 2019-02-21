package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.SalepriceDao;
import com.ry.core.entity.Accountrecord;
import com.ry.core.entity.Saleprice;

@Repository
public class SalepriceDaoImpl extends BaseDao<Saleprice, Integer> implements SalepriceDao {

	@Override
	public List<Saleprice> getList(Integer id, String begintime, String endtime) {
		String hql = "from saleprice where 1=1 ";
		List paramList = new ArrayList();
		if (id != null && id >0) {
			hql += " and id like ? ";
			paramList.add(id);
		}
		if (StringUtils.hasText(begintime)) {
			hql += " and begintime < ? ";
			paramList.add(begintime);
		}
		if (StringUtils.hasText(endtime)) {
			hql += " and endtime > ? ";
			paramList.add(endtime);
		}		
			
		return getListByHQL(hql, paramList.toArray());

	}
	

}
