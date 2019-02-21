/**
 * 
 */
package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.InquiryReplyDtoDao;
import com.ry.core.dto.InquiryReplyDto;
import com.ry.core.form.company.InquiryReplyRequest;
import com.ry.util.page.PageResults;

/**
 * 名称: OrgDaoImpl.java<br>
 * 描述: <br>
 * 类型: JAVA<br>
 * 最近修改时间:2016年3月2日 下午7:04:32<br>
 * @since  2016年3月2日
 * @author li.xiaofei 
 */
@Repository
public class InquiryReplyDtoDaoImpl extends BaseDao<InquiryReplyDto, Integer> implements InquiryReplyDtoDao{

	@Override
	public PageResults<InquiryReplyDto> getPageList(InquiryReplyRequest req) {
		
		StringBuffer hql = new StringBuffer("SELECT rep.id as id, rep.org_id as orgId , rep.draft_no as draftNo,"+
" rep.money as money,rep.result as result, rep.drawer as drawer, rep.payee as payee, rep.bank as bank, rep.bank_no as bankNo, rep.start_date as startDate,"+
" rep.end_date as endDate, rep.`no` as `no` , rep.create_time as createTime, rep.pay_state as payState, rep.pay_money as payMoney,"+
" rep.pay_way as payWay,  rep.need_invoice as needInvice,rep.org_type orgType,rep.import_state importState, rep.state as state, inv.fk_id as fkId,inv.fk_type as fkType, inv.state as invState "+ 
" from invoice inv RIGHT JOIN inquiry_reply rep ON rep.id = inv.fk_id  where 1=1");
		StringBuffer hqlcount = new StringBuffer("select count(*)  from invoice inv RIGHT JOIN inquiry_reply rep ON rep.id = inv.fk_id  where 1=1");
		List<Object> params = new ArrayList<Object>();
		if(req.getOrderNo() != null){
			hql.append(" and rep.'no' like ?");
			hqlcount.append(" and rep.'no' like ?");
			params.add(req.getOrderNo());
		}
		
		if(req.getStartDate() != null){
			hql.append(" and rep.create_time >= ?");
			hqlcount.append(" and rep.create_time >= ?");
			params.add(req.getStartDate()+"00:00:00");
		}
		
		if(req.getEndDate() != null){
			hql.append(" and rep.create_time <= ?");
			hqlcount.append(" and rep.create_time <= ?");
			params.add(req.getEndDate()+"23:59:59");
		}

		if(req.getOrderState() != null){
			hql.append(" and rep.pay_state = ?");
			hqlcount.append(" and rep.pay_state = ?");
			params.add(req.getOrderState());
		}
		if(req.getInvState() != null){
			hql.append(" and inv.state = ?");
			hqlcount.append(" and inv.state= ?");
			params.add(req.getInvState() );
		}
		if(req.getCheckState() != null){
			hql.append(" and rep.state = ?");
			hqlcount.append(" and rep.state= ?");
			params.add(req.getCheckState());
		}
		if(req.getNeedInvoice()!=null){
			hql.append(" and rep.need_invoice = ?");
			hqlcount.append(" and rep.need_invoice = ?");
			params.add(req.getNeedInvoice());
		}
		hql.append(" order by rep.create_time desc");
		return findPageByFetchedSql(hql.toString(), hqlcount.toString(), req.currentPage().intValue(), req.getLength().intValue(), params.toArray());
	} 
	
	
	
	/*public PageResults<OrderInvoiceDto> getPageList(InquiryReplyRequest list) {
		
		StringBuffer hql = new StringBuffer("select vo.id,vo.state as invoiceState , vo.create_time as createTime, dm.* from (select ord.id as orgId, ord.`no` as orgNo, ord.create_time as orgTime, ord.state as orgState,"+ 
												"rec.orderflag as memState, rec.allmoney as orderMoney, rec.ordernumber as memberNo,rec.ordertime as memTime from "+
												" distribute_order ord , discountrecord rec where  rec.id = ord.dcrd_id and rec.is_valid='T' ) dm ,"+
												"invoice vo  where dm.orgId = vo.fk_id and vo.fk_type=1 ");
		StringBuffer hqlcount = new StringBuffer("select count(*) from (select ord.id as orgId from distribute_order ord , discountrecord rec where  rec.id = ord.dcrd_id and rec.is_valid='T' ) dm , invoice vo  where dm.orgId = vo.fk_id and vo.fk_type=1 ");
		List<Object> paramList = new ArrayList<Object>();
		if(list.getStartDate() != null){
			hql.append(" and info.create_time > ?");
			hqlcount.append(" and info.create_time > ?");
			paramList.add(list.getStartDate()+"00:00:00");
		}
		
		if(list.getEndDate() != null){
			hql.append(" and info.create_time <= ?");
			hqlcount.append(" and info.create_time <= ?");
			paramList.add(list.getEndDate()+"23:59:59");
		}
		if(list.getState() != null){
			hql.append(" and info.state = ?");
			hqlcount.append(" and info.state = ?");
			paramList.add(list.getState());
		}
		
		hql.append(" order by vo.create_time desc ");
		return findPageByFetchedSql(hql.toString(), hqlcount.toString(), list.currentPage().intValue(), list.getLength().intValue(), paramList.toArray());		
	}*/

}
