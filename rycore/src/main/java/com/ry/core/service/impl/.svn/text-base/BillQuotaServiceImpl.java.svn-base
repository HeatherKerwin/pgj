package com.ry.core.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.BillQuotaDao;
import com.ry.core.entity.BillQuota;
import com.ry.core.service.BillQuotaService;
@Service
public class BillQuotaServiceImpl implements BillQuotaService {

	@Resource
	private BillQuotaDao billQuotaDao;
	@Override
	public List<Map<String,Object>> getList(Integer length ,String date, Integer type) {
		
		return billQuotaDao.getList(length,date,type);
	}

	@Override
	public List<Map<String,Object>> getByDay(String day,Integer type) {
		return billQuotaDao.getByDay(day,type);
	}

	@Override
	public void add(BillQuota billquota) {
		billQuotaDao.add(billquota);
	}

}
