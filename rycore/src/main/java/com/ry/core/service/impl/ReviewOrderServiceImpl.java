package com.ry.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.ReviewOrderDao;
import com.ry.core.dto.ReviewOrderDto;
import com.ry.core.form.org.ReviewOrderRequest;
import com.ry.core.service.ReviewOrderService;
import com.ry.util.page.PageResults;

@Service
public class ReviewOrderServiceImpl implements ReviewOrderService {

	@Resource
	ReviewOrderDao reviewOrderDao;
	
	public  PageResults<ReviewOrderDto> getPageReviewOrder(ReviewOrderRequest req){
		return reviewOrderDao.getPageReviewOrder(req);
	}

	@Override
	public PageResults<ReviewOrderDto> getPageFailedReviewOrder(ReviewOrderRequest req) {
		return reviewOrderDao.getPageFailedReviewOrder(req);
	}
}