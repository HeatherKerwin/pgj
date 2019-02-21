package com.ry.core.service;

import java.util.List;
import java.util.Map;

import com.ry.core.entity.PriceType;

public interface PriceTypeService {

	/**
	 * 获取所有报价类型(用于报价展示)
	 * @author BKY
	 */
	public List<PriceType> getPriceType();
	
	/**
	 * 根据id获取priceType
	 * @author BKY
	 */
	public PriceType getById(Integer id);
	
	/**
	 * 根据priceType获取列表
	 * @author BKY
	 */
	public List<PriceType> getByPriceType(PriceType pt);
	
	/**
	 * 根据priceType的ID和期限获取真正对应的priceType
	 * @author BKY
	 */
	public List<Map<String, Object>> getByIdAndTimeLimit(Integer priceTypeId, Integer timeLimit);
}
