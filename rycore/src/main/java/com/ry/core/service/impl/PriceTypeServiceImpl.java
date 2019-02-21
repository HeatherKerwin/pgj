package com.ry.core.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.PriceTypeDao;
import com.ry.core.entity.PriceType;
import com.ry.core.service.PriceTypeService;

@Service
public class PriceTypeServiceImpl implements PriceTypeService {
	
	@Resource
	PriceTypeDao priceTypeDao;

	/* (non-Javadoc)
	 * @see com.ry.core.service.PriceTypeService#getPriceType()
	 */
	public List<PriceType> getPriceType() {
		return priceTypeDao.getPriceType();
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.PriceTypeService#getById(java.lang.Integer)
	 */
	public PriceType getById(Integer id) {
		return priceTypeDao.getById(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.PriceTypeService#getByPriceType(com.ry.core.entity.PriceType)
	 */
	public List<PriceType> getByPriceType(PriceType pt) {
		return priceTypeDao.getByPriceType(pt);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.PriceTypeService#getByIdAndTimeLimit(java.lang.Integer, java.lang.Integer)
	 */
	public List<Map<String, Object>> getByIdAndTimeLimit(Integer priceTypeId, Integer timeLimit) {
		return priceTypeDao.getByIdAndTimeLimit(priceTypeId, timeLimit);
	}
}
