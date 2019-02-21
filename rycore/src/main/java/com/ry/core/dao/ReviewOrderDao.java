package com.ry.core.dao;

import java.util.Map;

import com.ry.core.dto.ReviewOrderDto;
import com.ry.core.form.org.ReviewOrderRequest;
import com.ry.util.page.PageResults;

public interface ReviewOrderDao {
	
	/**
	 * 分页获取机构拒单列表
	 * li.xiaofei
	 * @param req
	 * @return
	 */
	PageResults<ReviewOrderDto> getPageReviewOrder(ReviewOrderRequest req);
	/**
	 * 分页获取机构验票失败订单列表
	 * 郭晓威16-05-10
	 * @param req
	 * @return
	 */
	PageResults<ReviewOrderDto> getPageFailedReviewOrder(ReviewOrderRequest req);
	
}