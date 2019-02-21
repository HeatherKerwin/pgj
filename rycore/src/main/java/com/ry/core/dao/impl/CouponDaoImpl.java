package com.ry.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.CouponDao;
import com.ry.core.entity.Coupon;

@Repository
public class CouponDaoImpl extends BaseDao<Coupon, Integer> implements CouponDao {

	/* (non-Javadoc)
	 * @see com.ry.core.dao.CouponDao#save(com.ry.core.entity.Coupon)
	 */
	@Override
	public void saveModel(Coupon coupon) {
		save(coupon);
	}
}