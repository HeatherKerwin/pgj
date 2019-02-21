package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.ReviewOrderDao;
import com.ry.core.dto.ReviewOrderDto;
import com.ry.core.form.org.ReviewOrderRequest;
import com.ry.util.page.PageResults;

@Repository
public class ReviewOrderDaoImpl extends BaseDao<ReviewOrderDto, Integer> implements ReviewOrderDao {
	
	
	
	public PageResults<ReviewOrderDto> getPageReviewOrder(ReviewOrderRequest req){
		

		StringBuffer hql = new StringBuffer("select ord.image_path,rec.ordernumber as memberNo, ord.dcrd_id as dcrdId, ord.id as id, ord.org_id as orgId,ord.deposit_state,"+
											"ord.state as state, ord.`no` as orgNo, ord.create_time as createTime, rec.ordertime as memTime, rec.allmoney as orderMoney from"+
											" distribute_order ord ,discountrecord rec where ord.dcrd_id = rec.id ");
		StringBuffer hqlcount = new StringBuffer("select count(*) from distribute_order ord ,discountrecord rec where ord.dcrd_id = rec.id ");
		List<Object> paramList = new ArrayList<Object>();
		
		if(req.getStartDate() != null){
			    hql.append(" and ord.create_time >= ?");
			hqlcount.append("and ord.create_time >= ?");
			paramList.add(req.getStartDate()+"00:00:00");
		}
		
		if(req.getEndDate() != null){
			hql.append(" and ord.create_time <= ?");
			hqlcount.append(" and ord.create_time <= ?");
			paramList.add(req.getEndDate()+"23:59:59");
		}
		if(req.getState() != null){
			hql.append(" and ord.state = ?");
			hqlcount.append(" and ord.state = ?");
			paramList.add(req.getState());
		}
		hql.append(" order by ord.create_time desc ");
		return findPageByFetchedSql(hql.toString(), hqlcount.toString(), req.currentPage().intValue(), req.getLength().intValue(), paramList.toArray());		
	}
	//获取验票失败订单列表
	@Override
	public PageResults<ReviewOrderDto> getPageFailedReviewOrder(ReviewOrderRequest req) {
		List<Object> paramList = new ArrayList<Object>();
		StringBuffer hqlcount = new StringBuffer("select count(*) from distribute_order ord ,discountrecord rec where ord.dcrd_id = rec.id and ord.state = -1 ");
		StringBuffer hql = new StringBuffer("SELECT o.*,info.company FROM (SELECT ord.id id , ord.dcrd_id dcrdId, ord.no orgNo, rec.ordertime orderTime, ord.org_id orgId, ord.create_time createTime FROM discountrecord rec, distribute_order ord WHERE ord.dcrd_id = rec.id AND ord.state = -1 ");
		if(StringUtils.hasText(req.getStartDate())){
			    hql.append(" and ord.create_time >= ?");
			hqlcount.append("and ord.create_time >= ?");
			paramList.add(req.getStartDate()+" 00:00:00");
		}
		if(StringUtils.hasText(req.getEndDate())){
			hql.append(" and ord.create_time <= ?");
			hqlcount.append(" and ord.create_time <= ?");
			paramList.add(req.getEndDate()+" 23:59:59");
		}
		if(StringUtils.hasText(req.getNo())){
			hql.append(" and ord.no = ?");
			hqlcount.append(" and ord.no = ?");
			paramList.add(req.getNo());
		}
		hql.append(") o  LEFT JOIN (SELECT * FROM (	SELECT i_.* FROM org o_ LEFT JOIN org_info i_ ON o_.id=i_.org_id WHERE i_.type_=1 ORDER BY i_.id DESC)result GROUP BY result.org_id) info  ON o.orgId=info.org_id");
		hql.append(" order by createTime desc ");
		return findPageByFetchedSql(hql.toString(), hqlcount.toString(), req.currentPage().intValue(), req.getLength().intValue(), paramList.toArray());		
	}
}