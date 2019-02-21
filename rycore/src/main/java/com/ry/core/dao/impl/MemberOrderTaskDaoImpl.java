package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.MemberOrderTaskDao;
import com.ry.core.dto.OrderHistoryDto;
import com.ry.core.form.orgOrder.OrgOrderTaskRequest;
import com.ry.util.page.PageResults;

@Repository
public class MemberOrderTaskDaoImpl extends BaseDao<OrderHistoryDto, Integer> implements MemberOrderTaskDao {
	
	@Override
	public PageResults<OrderHistoryDto> getPageList(OrgOrderTaskRequest req) {	
		
		StringBuffer hql = new StringBuffer("SELECT rec.no as no, task.create_time as createTime, task.remarks as remarks,task.state as state from distribute_order_task task RIGHT JOIN distribute_order rec ON rec.id = task.dtbo_id where rec.state in (0,2)  ");
		StringBuffer hqlcount = new StringBuffer("SELECT count(*) from distribute_order_task task RIGHT JOIN distribute_order rec ON rec.id = task.dtbo_id where rec.state in (0,2)  ");
		List<Object> params = new ArrayList<Object>();
		
		if(req.getMemId() != null){
			hql.append(" and rec.dcrd_id = ?");
			hqlcount.append(" and rec.dcrd_id = ?");
			params.add(req.getMemId());
		}
		hql.append(" order by task.create_time desc");
		PageResults<OrderHistoryDto> pageResult = findPageByFetchedSql(hql.toString(), hqlcount.toString(), req.currentPage().intValue(), req.getLength().intValue(), params.toArray());
		return pageResult;
	}

	@Override
	public PageResults<OrderHistoryDto> getPageSpHisList(OrgOrderTaskRequest req) {
		StringBuffer hql = new StringBuffer("SELECT rec.no as no, task.create_time as createTime, task.remarks as remarks,task.state as state from distribute_order_task task RIGHT JOIN distribute_order_sp rec ON rec.id = task.dtbo_id where rec.state in (0,1,2) ");
		StringBuffer hqlcount = new StringBuffer("SELECT count(*) from distribute_order_task task RIGHT JOIN distribute_order_sp rec ON rec.id = task.dtbo_id where rec.state in (0,1,2) ");
		List<Object> params = new ArrayList<Object>();
		
		if(req.getMemId() != null){
			hql.append(" and rec.dcrd_sp_id  = ?");
			hqlcount.append(" and rec.dcrd_sp_id  = ?");
			params.add(req.getMemId());
		}
		hql.append(" order by task.create_time desc");
		PageResults<OrderHistoryDto> pageResult = findPageByFetchedSql(hql.toString(), hqlcount.toString(), req.currentPage().intValue(), req.getLength().intValue(), params.toArray());
		return pageResult;
	}

	@Override
	public PageResults<OrderHistoryDto> getPagePlHisList(OrgOrderTaskRequest req) {
		StringBuffer hql = new StringBuffer("SELECT rec.no as no, task.create_time as createTime, task.remarks as remarks,task.state as state from distribute_order_task task RIGHT JOIN distribute_order_pl rec ON rec.id = task.dtbo_id where rec.state in (0,1,2) ");
		StringBuffer hqlcount = new StringBuffer("SELECT count(*) from distribute_order_task task RIGHT JOIN distribute_order_pl rec ON rec.id = task.dtbo_id where rec.state in (0,1,2) ");
		List<Object> params = new ArrayList<Object>();
		
		if(req.getMemId() != null){
			hql.append(" and rec.dcrd_pl_id  = ?");
			hqlcount.append(" and rec.dcrd_pl_id  = ?");
			params.add(req.getMemId());
		}
		hql.append(" order by task.create_time desc");
		PageResults<OrderHistoryDto> pageResult = findPageByFetchedSql(hql.toString(), hqlcount.toString(), req.currentPage().intValue(), req.getLength().intValue(), params.toArray());
		return pageResult;
	} 
}