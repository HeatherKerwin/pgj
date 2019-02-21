package com.ry.core.dao.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.InquiryReplyDao;
import com.ry.core.entity.InquiryReply;
import com.ry.core.form.InquiryReplyForm;
import com.ry.core.form.company.InquiryReplyRequest;
import com.ry.core.util.Utility;
import com.ry.util.DateUtil;
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
public class InquiryReplyDaoImpl extends BaseDao<InquiryReply, Integer> implements InquiryReplyDao{
	
	public List<InquiryReply> getByObj(InquiryReplyRequest req){
		StringBuffer hql = new StringBuffer(" from  inquiry_reply where 1=1");
		List<Object> params = new ArrayList<Object>();
		if(req.getId() != null){
			hql.append(" and id = ?");
			params.add(req.getId());
		}
		if(req.getQueryNo() != null){
			hql.append(" and no = ?");
			params.add(req.getQueryNo());
		}
		if(req.getIdStr() != null ){
			hql.append(" and id in (:id)");
			return getListByHQLList(hql.toString(), req.getIdStr());
		}
		if(req.getOrderNo() != null && req.getOrderNo() != ""){
			hql.append(" and no like ?");
			params.add(req.getOrderNo());
		}
		if(req.getStartDate() != null && req.getStartDate() != ""){
			hql.append(" and create_time >= ?");
			params.add(req.getStartDate()+"00:00:00");
		}
		if(req.getEndDate() != null && req.getEndDate() != ""){
			hql.append(" and create_time <= ?");
			params.add(req.getEndDate()+"23:59:59");
		}
		if(req.getOrderState() != null){
			hql.append(" and pay_state = ?");
			params.add(req.getOrderState());
		}
/*		if(req.getInvState() != null){
			hql.append(" and inv.state = ?");
			params.add(req.getInvState() );
		}*/
		if(req.getCheckState() != null){
			hql.append(" and state = ?");
			params.add(req.getCheckState());
		}
		if(req.getImportState()!=null){//导入导出状态
			hql.append(" and importState = ?");
			params.add(req.getImportState());
		}
		return getListByHQL(hql.toString(), params.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.InquiryReplyDtDao#updateInquiryReply(com.ry.core.entity.InquiryReply)
	 */
	@Override
	public void updateInquiryReply(InquiryReply reply) {
		 update(reply);
		
	}
	
    /**
     * in(ids) 获取选的数据
     * @param hqlString
     * @param values
     * @return
     */
    public List<InquiryReply> getListByHQLList(String hqlString, Object[] values) {
        Query query = this.getSession().createQuery(hqlString);
        	query.setParameterList("id", values);  
        	return query.list();
    }
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.InquiryReplyDao#getByNo(java.lang.String)
	 */
	public List<InquiryReply> getByNo(String no){
		StringBuffer hql = new StringBuffer("from inquiry_reply where no=?");
		List<Object> params = new ArrayList<Object>();
		params.add(no);
		return getListByHQL(hql.toString(), params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.InquiryReplyDao#saveModel(com.ry.core.entity.InquiryReply)
	 */
	public void saveModel(InquiryReply reply) {
		 if(reply!=null && reply.getId()!=null){
			 update(reply);
		 }else{
			 save(reply);
		 }
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.InquiryReplyDao#getById(java.lang.Integer)
	 */
	public InquiryReply getById(Integer id){
		return get(id);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.InquiryReplyDao#getInfoById(java.lang.Integer)
	 */
	@Override
	public List<Map<String,Object>> getInfoById(Integer id) {
		StringBuffer sql = new StringBuffer("select inc.id,inc.state incState,ir.visit_state,ir.result,ir.state,ir.org_type,ir.import_state,ir.need_invoice,ir.no,ir.pay_state,inc.send_way,inc.address,ir.draft_no,ir.money,inc.title,inc.invoice_type,inc.content,inc.name,inc.phone,inc.express_way,inc.express_no,m.username,m.mobile,ir.edit_state from inquiry_reply ir LEFT JOIN invoice inc on (ir.id=inc.fk_id and inc.fk_type=2) LEFT JOIN member m ON ir.member_id=m.id where ir.id=?;"); 
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		List<Map<String,Object>> list = getListMapBySQL(sql.toString(), params.toArray());
		return Utility.decodeMobile(list);//@KHC EDIT2016-10-12  手机号转码
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.InquiryReplyDao#getPageList(com.ry.core.entity.InquiryReply, java.lang.Integer, java.lang.Integer)
	 */
	public PageResults<InquiryReply> getPageList(InquiryReply inq,Integer pageIndex,Integer pageSize) {
		StringBuffer hql=new StringBuffer("from inquiry_reply where 1=1 ");
		List<Object> paramList = new ArrayList<Object>();
		if(inq!=null){
			if(inq.getOrgId()!=null){
				hql.append(" AND org_id=?"); 
				paramList.add(inq.getOrgId());
			}
			if(inq.getMemberId()!=null){
				hql.append(" AND member_id=?"); 
				paramList.add(inq.getMemberId());
			}
			if(inq.getOrgType()!=null){
				hql.append(" AND org_type=?"); 
				paramList.add(inq.getOrgType());
			}
		}
		hql.append(" order by create_time desc");
		String countHql = "select count(*) "+hql.toString();
		return findPageByFetchedHql(hql.toString(), countHql, pageIndex, pageSize, paramList.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.InquiryReplyDao#updateTransaction(java.lang.Integer, java.util.Date, java.lang.String)
	 */
	public List<InquiryReply> updateTransaction(Integer payState, Date createTime, Date createTime2) {
		StringBuffer sql = new StringBuffer("from inquiry_reply ir where 1 = 1 and ir.payState = ? and ir.createTime between ? and ? and ir.jyh is null");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(payState);
		paramList.add(createTime);
		paramList.add(createTime2);
		return getListByHQL(sql.toString(), paramList.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.InquiryReplyDao#updateJyh(com.ry.core.entity.InquiryReply)
	 */
	public void updateJyh(InquiryReply inquiryRepl) {
		update(inquiryRepl);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.InquiryReplyDao#getByOrgId(java.lang.Integer)
	 */
	public List<InquiryReply> getByOrgId(Integer orgId){
		StringBuffer hql = new StringBuffer("from inquiry_reply where orgId=?");
		List<Object> params = new ArrayList<Object>();
		params.add(orgId);
		return getListByHQL(hql.toString(), params.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.InquiryReplyDao#getCountByArray(java.util.List, java.lang.Integer, java.util.Date, java.util.Date)
	 */
	public List<Map<String, Object>> getCountByArray(List<Object> paramList, Integer payState, Date startTime, Date endTime) {
		StringBuffer sql = new StringBuffer("select o.id orgId, o.member_id memberId,ir.no from inquiry_reply ir inner join org o on ir.org_id = o.id where 1=1");
		List<Object> params = new ArrayList<Object>();
		if (payState != null) {
			sql.append(" and ir.pay_state = ? ");
			params.add(payState);
		}
		if (startTime != null && endTime != null) {
			sql.append("and ir.create_time between ? and ? ");
			params.add(startTime);
			params.add(endTime);
		}
		if (paramList != null && paramList.size() > 0) {
			sql.append(" and o.member_id in (");
			for (int i = 0; i < paramList.size(); i++) {
				if ((i + 1) == paramList.size()) {
					sql.append(" ? ");
				} else {
					sql.append(" ?, ");
				}
			}
			sql.append(")");
			params.addAll(paramList);
		}
		return getListMapBySQL(sql.toString(), params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.InquiryReplyDao#getByMemberId(java.lang.Integer)
	 */
	public List<InquiryReply> getByMemberId(Integer memberId){
		StringBuffer hql = new StringBuffer("from inquiry_reply where memberId=?");
		List<Object> params = new ArrayList<Object>();
		params.add(memberId);
		return getListByHQL(hql.toString(), params.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.InquiryReplyDao#getPageList(com.ry.core.form.InquiryReplyForm, java.lang.Integer, java.lang.Integer)
	 */
	public PageResults<Map<String, Object>> getPageList(InquiryReplyForm form, Integer pageIndex, Integer pageSize) throws Exception{
		StringBuffer sql = new StringBuffer("SELECT reply.*,invoice.fk_id,invoice.title_type,invoice.invoice_type,invoice.content,invoice.send_way,invoice.prov,invoice.city,invoice.dist,invoice.address FROM inquiry_reply reply LEFT JOIN invoice invoice ON reply.id = invoice.fk_id WHERE reply.member_id = ? ");//暂时按机构查询
		List<Object> params = new ArrayList<Object>();
		params.add(form.getMemberId());
		if(form != null){
			if(StringUtils.hasText(form.getStart())){
				sql.append(" AND reply.create_time >=?");
				params.add(DateUtil.parser(form.getStart()+" 00:00:00", DateUtil.FORMART));
			}
			if(StringUtils.hasText(form.getEnd())){
				sql.append(" AND reply.create_time <=?");
				params.add(DateUtil.parser(form.getEnd()+" 59:59:59", DateUtil.FORMART));
			}
			if(form.getMinMoney()!=null){
				sql.append(" AND reply.money >=?");
				params.add(form.getMinMoney());
			}
			if(form.getMaxMoney()!=null){
				sql.append(" AND reply.money <=?");
				params.add(form.getMaxMoney());
			}
			if(StringUtils.hasText(form.getDraftNo())){
				sql.append(" AND reply.draft_no =?");
				params.add(form.getDraftNo());
			}
			if(form.getNum()!=null){
				if(form.getNum()==1){//待支付
					sql.append(" AND reply.pay_state = 0");
				}else if(form.getNum()==2){//反馈中
					sql.append(" AND reply.pay_state = 1 AND reply.result is null");
				}else if(form.getNum()==3){//已完成
					sql.append(" AND reply.pay_state = 1 AND reply.result is not null");
				}
			}
		}
		sql.append(" GROUP BY reply.id ");
		String count = "SELECT COUNT(*) FROM("+sql.toString()+")res";
		return findPageMapByFetchedSql(sql.toString(), count, pageIndex, pageSize, params.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.InquiryReplyDao#getInfoByNo(java.lang.String)
	 */
	public Map<String, Object> getInfoByNo(String no) {
		StringBuffer sql = new StringBuffer("SELECT reply.*,invoice.title_type,invoice.invoice_type,invoice.content,invoice.send_way,invoice.express_way,invoice.express_no,invoice.address,invoice.prov,invoice.city,invoice.dist FROM inquiry_reply reply LEFT JOIN invoice invoice ON reply.id = invoice.fk_id WHERE reply.no = ?");
		List<Object> params = new ArrayList<Object>();
		params.add(no);
		List<Map<String, Object>> list = getListMapBySQL(sql.toString(), params.toArray());
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	public PageResults<Map<String,Object>> listByInquiryReplyRequest(Integer pageIndex,Integer pageSize,InquiryReplyRequest req){
		
		StringBuffer sql = new StringBuffer(" SELECT i.*,m.mobile,inv.state invState,inv.id invoiceId FROM inquiry_reply i LEFT JOIN member m ON i.member_id=m.id  left join invoice inv on ( i.id = inv.fk_id and inv.fk_type=2 ) WHERE  1=1");
		List<Object> params = new ArrayList<Object>();
		if (StringUtils.hasText(req.getOrderNo())){//票号
			sql.append(" and i.draft_no = ?");
			params.add(req.getOrderNo());
		}
		
		if (StringUtils.hasText(req.getQueryNo())){//订单查询查复号
			sql.append(" and i.no = ?");
			params.add(req.getQueryNo());
		}
		
		if (StringUtils.hasText(req.getStartDate())){//起始日期
			sql.append(" and i.create_time >= ?");
			params.add(req.getStartDate() + " 00:00:00");
		}
		
		try {
			if(StringUtils.hasText(req.getEndDate())){//结束日期
				sql.append(" and i.create_time < ?");
				params.add(DateUtil.addDays(DateUtil.parser(req.getEndDate(), DateUtil.FORMART3),1));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (StringUtils.hasText(req.getMinMoney())){//最小订单金额
			sql.append(" and i.money >= ?");
			params.add(req.getMinMoney());
		}
		if (StringUtils.hasText(req.getMaxMoney())){//最大订单金额
			sql.append(" and i.money <= ?");
			params.add(req.getMaxMoney());
		}
		
		if (StringUtils.hasText(req.getPhone())){//手机号
			sql.append(" and m.mobile = ?");
			params.add(Utility.encodeMobile(req.getPhone()));
		}
		
		if (req.getVisitState()!=null){//回访状态
			sql.append(" and i.visit_state = ?");
			params.add(req.getVisitState());
		}
		
		if (req.getOrderState() != null){//后台处理状态
			sql.append(" and i.state = ? ");
			params.add(req.getOrderState());
		}
		
		if (req.getPayState() != null){//支付状态
			sql.append(" and i.pay_state = ?");
			params.add(req.getPayState());
		}
		
		if (req.getVisit() != null && req.getVisit() == 0){//0待回访还是1全部
			sql.append(" and i.visit_state  is not  null");
		}
		
		sql.append(" order by i.create_time desc ");		
		String count = "SELECT COUNT(*) FROM ("+ sql.toString() + ") des ";
		return Utility.decodeMobile(findPageMapByFetchedSql(sql.toString(), count, pageIndex, pageSize, params.toArray()));
	}
	
	public Long countByImportAndTime(Integer importState,Integer time){
		StringBuffer hql = new StringBuffer("select count(*) from inquiry_reply inq where inq.payState != 2 ");
		List<Object> params = new ArrayList<Object>();
		if(importState != null){
			hql.append(" and inq.importState = ? ");
			params.add(importState);
		}
		if(time != null && time==5){
			hql.append(" and TIMEDIFF(SYSDATE(),inq.createTime) >= '00:05:00' ");
		}else if(time != null && time==360){
			hql.append(" and TIMEDIFF(SYSDATE(),inq.createTime) >= '06:00:00' ");
		}
		return countByHql(hql.toString(), params.toArray());
	}

	@Override
	public List<Map<String, Object>> getExcelExport(InquiryReplyRequest req) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		StringBuffer sql = new StringBuffer(" SELECT i.*,m.mobile,o.name,inv.state invState,inv.id invoiceId FROM inquiry_reply i LEFT JOIN org_info o ON i.org_id=o.id LEFT JOIN member m ON i.member_id=m.id  left join invoice inv on ( i.id = inv.fk_id and inv.fk_type=2 ) WHERE  1=1");
		List<Object> params = new ArrayList<Object>();
		if (StringUtils.hasText(req.getOrderNo())){//票号
			sql.append(" and i.draft_no = ?");
			params.add(req.getOrderNo());
		}
		
		if (StringUtils.hasText(req.getQueryNo())){//订单查询查复号
			sql.append(" and i.no = ?");
			params.add(req.getQueryNo());
		}
		
		if (StringUtils.hasText(req.getStartDate())){//起始日期
			sql.append(" and i.create_time >= ?");
			params.add(req.getStartDate() + " 00:00:00");
		}
		
		try {
			if(StringUtils.hasText(req.getEndDate())){//结束日期
				sql.append(" and i.create_time < ?");
				params.add(DateUtil.addDays(DateUtil.parser(req.getEndDate(), DateUtil.FORMART3),1));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (StringUtils.hasText(req.getMinMoney())){//最小订单金额
			sql.append(" and i.money >= ?");
			params.add(req.getMinMoney());
		}
		if (StringUtils.hasText(req.getMaxMoney())){//最大订单金额
			sql.append(" and i.money <= ?");
			params.add(req.getMaxMoney());
		}
		
		if (StringUtils.hasText(req.getPhone())){//手机号
			sql.append(" and m.mobile = ?");
			params.add(Utility.encodeMobile(req.getPhone()));
		}
		
		if (req.getVisitState()!=null){//回访状态
			sql.append(" and i.visit_state = ?");
			params.add(req.getVisitState());
		}
		
		if (req.getOrderState() != null){//后台处理状态
			sql.append(" and i.state = ? ");
			params.add(req.getOrderState());
		}
		
		if (req.getPayState() != null){//支付状态
			sql.append(" and i.pay_state = ?");
			params.add(req.getPayState());
		}
		
		if (req.getVisit() != null && req.getVisit() == 0){//0待回访还是1全部
			sql.append(" and i.visit_state  is not  null");
		}
		sql.append(" order by i.create_time desc ");	
		result = getListMapBySQL(sql.toString() , params.toArray());
		return Utility.decodeMobile(result);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.InquiryReplyDao#listReportByInquiryReplyRequest(java.lang.Integer, java.lang.Integer, com.ry.core.form.company.InquiryReplyRequest)
	 */
	@Override
	public PageResults<Map<String, Object>> listReportByInquiryReplyRequest(Integer currentPage, Integer pageSize,InquiryReplyRequest req) {
			StringBuffer sql = new StringBuffer(" FROM( SELECT i.*,m.mobile,inv.state invState,inv.id invoiceId FROM inquiry_reply i LEFT JOIN member m ON i.member_id=m.id  left join invoice inv on ( i.id = inv.fk_id and inv.fk_type=2 ) order by i.create_time desc )r1 LEFT JOIN ("
											+ "  SELECT r.paper_demand,r.location,r.create_time AS rtime,r.fk_id FROM (SELECT rr.* FROM remarks rr ORDER BY rr.id DESC )r GROUP BY r.fk_type ,r.fk_id ORDER BY r.id DESC)r2 ON r1.id = r2.fk_id where 1=1 ");
			List<Object> params = new ArrayList<Object>();
			if (StringUtils.hasText(req.getPhone())){//手机号
				sql.append(" and r1.mobile = ?");
				params.add(Utility.encodeMobile(req.getPhone()));
			}
			
			if (StringUtils.hasText(req.getQueryNo())){//订单查询查复号
				sql.append(" and r1.no = ?");
				params.add(req.getQueryNo());
			}
			
			if (StringUtils.hasText(req.getLocation())){//订单查询查复号
				sql.append(" and r2.location like ?");
				params.add("%"+req.getLocation()+"%");
			}
			
			try {
				if (StringUtils.hasText(req.getStartDate())){//起始日期
					sql.append(" and r1.create_time >= ?");
					params.add(req.getStartDate() + " 00:00:00");
				}
				if(StringUtils.hasText(req.getEndDate())){//结束日期
					sql.append(" and r1.create_time < ?");
					params.add(DateUtil.addDays(DateUtil.parser(req.getEndDate(), DateUtil.FORMART3),1));
				}
				if (StringUtils.hasText(req.getBegremarksTime())){//起始日期
					sql.append(" and r2.rtime >= ?");
					params.add(req.getBegremarksTime() + " 00:00:00");
				}
				if(StringUtils.hasText(req.getEndremarksTime())){//结束日期
					sql.append(" and r2.rtime < ?");
					params.add(DateUtil.addDays(DateUtil.parser(req.getEndremarksTime(), DateUtil.FORMART3),1));
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
	
			String str = new String("SELECT r1.*,r2.paper_demand,r2.location,r2.rtime  " + sql.toString());
			String count = "SELECT COUNT(*) "+ sql.toString();
			return Utility.decodeMobile(findPageMapByFetchedSql(str, count, currentPage, pageSize, params.toArray()));
	}

}