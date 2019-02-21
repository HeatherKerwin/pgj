package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.BillQuotaDao;
import com.ry.core.entity.BillQuota;
@Repository
public class BillQuotaDaoImpl extends BaseDao<BillQuota,Integer > implements BillQuotaDao{

	@Override
	public List<Map<String,Object>> getList(Integer length ,String date, Integer type) {
		StringBuffer sql = new StringBuffer("SELECT DATE_FORMAT(DAY,'%m月%d') AS xValue, ROUND (VALUE) yValue FROM bill_quota WHERE 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if(type==0||type==1){
			sql.append(" AND TYPE = ? ");
			params.add(type);
		}
		if(date!=null){
			sql.append(" AND create_time > ? ");
			params.add(date);
		}
		sql.append(" ORDER BY create_time DESC ");
		if(length!=null){
			sql.append(" LIMIT ? ");
			params.add(length);
		}
		return getListMapBySQL(sql.toString(), params.toArray());
	}

	@Override
	public void add(BillQuota billquota) {
		save(billquota);
	}

	@Override
	public List<Map<String,Object>> getByDay(String day ,Integer type) {
		StringBuffer sql = new StringBuffer("SELECT * FROM bill_quota WHERE DAY = ? AND TYPE = ?");
		List<Object> params = new ArrayList<Object>();
		if(day!=null&&day!=""){
			params.add(day);
			if(type==0|type==1)
				params.add(type);
			return getListMapBySQL(sql.toString(), params.toArray());
		}
		else
			return null;
	}

}
