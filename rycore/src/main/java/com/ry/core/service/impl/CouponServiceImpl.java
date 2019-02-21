package com.ry.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.CouponDao;
import com.ry.core.entity.Coupon;
import com.ry.core.service.CouponService;

@Service
public class CouponServiceImpl implements CouponService {

	@Resource
	CouponDao couponDao;
	
	@Override
	public void saveModel(Coupon coupon) {
		couponDao.saveModel(coupon);
	}
}
