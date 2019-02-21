package com.ry.core.dao;


import com.ry.core.dto.OrderHistoryDto;
import com.ry.core.form.orgOrder.OrgOrderTaskRequest;
import com.ry.util.page.PageResults;

public interface MemberOrderTaskDao {
	
	/**
	 * 分页订单分发历史信息列表
	 * @param req
	 * @since 2016年3月3日 下午4:24:35
	 */
   PageResults<OrderHistoryDto> getPageList(OrgOrderTaskRequest req);
   
   /**
     * 商票派单历史
	 * @author KHC
	 * @param req
	 * @since 2016年8月16日 下午1:23:40
	 */
	PageResults<OrderHistoryDto> getPageSpHisList(OrgOrderTaskRequest req);
	
	/**
     * 批量票派单历史
	 * @author KHC
	 * @param req
	 * @since 2016年8月16日 下午2:23:40
	 */
	PageResults<OrderHistoryDto> getPagePlHisList(OrgOrderTaskRequest req);
}