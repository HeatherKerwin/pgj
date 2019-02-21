package com.ry.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.SalepriceDao;
import com.ry.core.entity.Saleprice;
import com.ry.core.service.SalepriceService;

@Service
public class SalepriceServiceImpl extends BaseDao<Saleprice, Integer> implements
		SalepriceService {

	@Resource
	SalepriceDao salepriceDao;

	@Override
	public List<Saleprice> getList(Integer id, String begintime, String endtime) {
		return salepriceDao.getList(id, begintime, endtime);
	};
}
