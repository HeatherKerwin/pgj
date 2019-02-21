package com.ry.core.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.DistributeOrderPlDao;
import com.ry.core.entity.DistributeOrderPl;
import com.ry.core.form.DistributeOrderPlForm;
import com.ry.core.form.org.ReviewOrderRequest;
import com.ry.core.util.Utility;
import com.ry.util.DateUtil;
import com.ry.util.page.PageResults;

@Repository
public class DistributeOrderPlDaoImpl extends BaseDao<DistributeOrderPl, Integer> implements DistributeOrderPlDao {

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderPlDao#getById(java.lang.Integer)
	 */
	public DistributeOrderPl getById(Integer id) {
		return get(id);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderPlDao#saveModel(com.ry.core.entity.DistributeOrderPl)
	 */
	public void saveModel(DistributeOrderPl distributeOrderPl) {
		save(distributeOrderPl);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderPlDao#updateModel(com.ry.core.entity.DistributeOrderPl)
	 */
	public void updateModel(DistributeOrderPl distributeOrderPl) {
		update(distributeOrderPl);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderPlDao#deleteModel(java.lang.Integer)
	 */
	public void deleteModel(Integer id) {
		delete(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderPlDao#getWaitByOrgId(java.lang.Integer)
	 */
	public List<Map<String,Object>> getWaitByOrgId(Integer orgId){
		StringBuffer sql = new StringBuffer("SELECT dist.*,disc.no as qyno,disc.type1,disc.type2,disc.allmoney,disc.amount,disc.max_expiry_day,disc.min_expiry_day,disc.max_money,disc.min_money,disc.need_todoor,disc.flaw_ticket,disc.endtime,disc.remarks FROM distribute_order_pl dist LEFT JOIN discountrecord_pl disc ON disc.id=dist.dcrd_pl_id WHERE dist.org_id=? AND state NOT IN(0,-2) AND take IS NULL");
		List<Object> params = new ArrayList<Object>();
		params.add(orgId);
		List<Map<String,Object>> list = getListMapBySQL(sql.toString(),params.toArray());
		return list;
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderPlDao#getPageList(java.lang.Integer, java.lang.Integer, com.ry.core.form.DistributeOrderPlForm)
	 */
	public PageResults<Map<String, Object>> getPageList(Integer indexPage,Integer pageSize,DistributeOrderPlForm form) {
		StringBuffer sql = new StringBuffer(" SELECT * FROM(SELECT dist.org_id AS orgid,dist.no AS jgno,dist.state,dist.create_time AS createtime,dist.take,dist.id as dtboId,disc.* FROM distribute_order_pl dist LEFT JOIN discountrecord_pl disc ON dist.dcrd_pl_id=disc.id)res WHERE 1=1");
		StringBuffer count = new StringBuffer(" SELECT COUNT(*) FROM(SELECT dist.org_id AS orgid,dist.no AS jgno,dist.state,dist.create_time AS createtime,dist.take,dist.id as dtboId,disc.* FROM distribute_order_pl dist LEFT JOIN discountrecord_pl disc ON dist.dcrd_pl_id=disc.id)res WHERE 1=1");
		List<Object> params = new ArrayList<Object>();
		
		if(form!=null){
			if(form.getState()!=null){
				sql.append(" AND res.state=?");
				count.append(" AND res.state=?");
				params.add(form.getState());
			}
			if(form.getOrgId()!=null){
				sql.append(" AND res.orgid=?");
				count.append(" AND res.orgid=?");
				params.add(form.getOrgId());
			}
			if(form.getDcrdPlId()!=null){
				sql.append(" AND res.id = ?");
				count.append(" AND res.id = ?");
				params.add(form.getDcrdPlId());
			}
		}
		
		sql.append(" GROUP BY res.id ");
		sql.append(" ORDER BY res.createtime DESC ");
		PageResults<Map<String, Object>> page=findPageMapByFetchedSql(sql.toString(), count.toString(), indexPage, pageSize, params.toArray());
		return Utility.decodeMobile(page);//@ZY EDIT:2016-09-23手机号转码
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderPlDao#getByDcrdId(java.lang.Integer)
	 */
	public List<DistributeOrderPl> getByDcrdId(Integer dcrdId) {
		StringBuffer hql = new StringBuffer("from distribute_order_pl where state NOT IN(0,-1) AND dcrdPlId=?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(dcrdId);
		return queryByHql(hql.toString(), paramList.toArray());
	}


	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderPlDao#getListByOrgId(java.lang.Integer)
	 */
	public List<Map<String, Object>> getListByOrgId(Integer orgId) {
		StringBuffer sql = new StringBuffer("SELECT dist.* FROM distribute_order_pl dist LEFT JOIN discountrecord_pl disc ON dist.dcrd_pl_id = disc.id WHERE disc.expiry_date>=? and dist.org_id = ?");
		List<Object> params = new ArrayList<Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		params.add(sdf.format(new Date()));
		params.add(orgId);
		return getListMapBySQL(sql.toString(), params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderPlDao#getInfoById(java.lang.Integer)
	 */
	public Map<String, Object> getInfoById(Integer id) {
		StringBuffer sql = new StringBuffer("SELECT disc.*,dist.id distId,dist.no distNo,dist.state FROM discountrecord_pl disc LEFT JOIN distribute_order_pl dist ON disc.id=dist.dcrd_pl_id WHERE dist.id=?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(id);
		List<Map<String, Object>> list = getListMapBySQL(sql.toString(), paramList.toArray());
		if(list!=null && list.size()>0){
			return Utility.decodeMobile(list.get(0));//@ZY EDIT:2016-09-23手机号转码
		}else{
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderPlDao#getList(com.ry.core.form.DistributeOrderPlForm)
	 */
	public List<DistributeOrderPl> getList(DistributeOrderPlForm form) {
		StringBuffer hql = new StringBuffer("from distribute_order_pl where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if(form!=null){
			if(form.getId()!=null){
				hql.append(" AND id=?");
				params.add(form.getId());
			}
			if(form.getDcrdPlId()!=null){
				hql.append(" AND dcrdPlId=?");
				params.add(form.getDcrdPlId());
			}
			if(form.getStates()!=null && form.getStates().length>0){//订单状态[可能同时查询多个状态]
				String tag = "";
				for(Integer of:form.getStates()){
					if(!"".equals(tag)) tag += ",";
					tag += "?";
					params.add(of);
				}
				hql.append(" AND state in("+tag+")");
			}
		}
		return queryByHql(hql.toString(), params.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderPlDao#getByOrgId(java.lang.Integer)
	 */
	public List<DistributeOrderPl> getByOrgId(Integer orgId) {
		StringBuffer hql = new StringBuffer("FROM distribute_order_pl WHERE orgId = ?");
		List<Object> params = new ArrayList<Object>();
		params.add(orgId);
		return queryByHql(hql.toString(), params.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderPlDao#getListByDiscIdAndOrgId(java.lang.Integer, java.lang.Integer)
	 */
	public List<DistributeOrderPl> getListByDiscIdAndOrgId(Integer discId, Integer orgId) {
		StringBuffer hql = new StringBuffer("FROM distribute_order_pl WHERE orgId = ? and dcrdPlId = ?");
		List<Object> params = new ArrayList<Object>();
		params.add(orgId);
		params.add(discId);
		return queryByHql(hql.toString(), params.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderPlDao#getOrderPageList(java.lang.Integer, java.lang.Integer, com.ry.core.form.DistributeOrderPlForm)
	 */
	public PageResults<DistributeOrderPl> getOrderPageList(ReviewOrderRequest req) {
		StringBuffer sql = new StringBuffer("SELECT * FROM distribute_order_pl WHERE 1=1");
		StringBuffer count = new StringBuffer("SELECT COUNT(*) FROM distribute_order_pl WHERE 1=1");
		List<Object> paramList = new ArrayList<Object>();
		if(req!=null){
			if(StringUtils.hasText(req.getStartDate())){
				try {
					sql.append(" and create_time >=?");
					count.append(" and create_time >=?");
					paramList.add(DateUtil.parser(req.getStartDate(), "yyyy-MM-dd"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if(StringUtils.hasText(req.getEndDate())){
				try {
					sql.append(" and create_time <=?");
					count.append(" and create_time <=?");
					paramList.add(DateUtil.parser(req.getEndDate(), "yyyy-MM-dd"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if(StringUtils.hasText(req.getState())){
				int state = Integer.parseInt(req.getState());
				if(state==0){//无效订单（-2,0）
					sql.append(" and state in (-2,0)");
					count.append(" and state in (-2,0)");
				}else{
					sql.append(" and state =?");
					count.append(" and state =?");
					paramList.add(state);
				}
			}
		}
		sql.append(" order by create_time desc");
		return findPageByFetchedSql(sql.toString(), count.toString(), req.currentPage().intValue(), req.getLength().intValue(), paramList.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderPlDao#getOrderPageList(java.lang.Integer, java.lang.Integer, com.ry.core.form.DistributeOrderPlForm)
	 */
	public PageResults<Map<String,Object>> getOrderMemberPageList(ReviewOrderRequest req) {
		StringBuffer sql = new StringBuffer("SELECT r1.* ,m.mobile from (SELECT diso.* ,o.member_id  as memberid from (SELECT * FROM distribute_order_pl order by create_time desc)diso LEFT JOIN org o ON diso.org_id = o.id)r1  LEFT JOIN member m ON r1.memberid = m.id WHERE 1=1 ");
		StringBuffer count = new StringBuffer("SELECT COUNT(r1.id) from (SELECT diso.* ,o.member_id  as memberid from (SELECT * FROM distribute_order_pl order by create_time desc)diso LEFT JOIN org o ON diso.org_id = o.id)r1  LEFT JOIN member m ON r1.memberid = m.id WHERE 1=1 ");
		List<Object> paramList = new ArrayList<Object>();
		if(req!=null){
			if(StringUtils.hasText(req.getStartDate())){
				try {
					sql.append(" and r1.create_time >=?");
					count.append(" and r1.create_time >=?");
					paramList.add(DateUtil.parser(req.getStartDate(), "yyyy-MM-dd"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if(StringUtils.hasText(req.getEndDate())){
				try {
					sql.append(" and r1.create_time <=?");
					count.append(" and r1.create_time <=?");
					paramList.add(DateUtil.parser(req.getEndDate(), "yyyy-MM-dd"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if(StringUtils.hasText(req.getState())){
				int state = Integer.parseInt(req.getState());
				if(state==0){//无效订单（-2,0）
					sql.append(" and r1.state in (-2,0)");
					count.append(" and r1.state in (-2,0)");
				}else{
					sql.append(" and r1.state =?");
					count.append(" and r1.state =?");
					paramList.add(state);
				}
			}
			if(StringUtils.hasText(req.getMobile())){
				sql.append(" and m.mobile = ?");
				count.append(" and m.mobile = ?");
				paramList.add(Utility.encodeMobile(req.getMobile()));
			}
		}
		return Utility.decodeMobile(findPageMapByFetchedSql(sql.toString(), count.toString(), req.currentPage().intValue(), req.getLength().intValue(), paramList.toArray()));
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderPlDao#getOverrunByCreateTime(java.util.Date)
	 */
	public List<Map<String, Object>> getOverrunByCreateTime(String date) {
		StringBuffer sql = new StringBuffer("SELECT dist.* AS dtboId FROM discountrecord_pl disc LEFT JOIN distribute_order_pl dist ON disc.id = dist.dcrd_pl_id WHERE dist.state=1 AND (dist.take<>1 OR dist.take IS NULL) AND disc.endtime <?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(date);
		return getListMapBySQL(sql.toString(), paramList.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderPlDao#getByNo(java.lang.String)
	 */
	public DistributeOrderPl getByNo(String no) throws Exception{
		StringBuffer sql = new StringBuffer("FROM distribute_order_pl WHERE no = ?");
		List<Object> params = new ArrayList<Object>();
		params.add(no);
		List<DistributeOrderPl> list = queryByHql(sql.toString(), params.toArray());
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderPlDao#getPageListPC(java.lang.Integer, java.lang.Integer, com.ry.core.form.DistributeOrderPlForm)
	 */
	public PageResults<Map<String, Object>> getPageListPC(Integer pageIndex, Integer pageSize,DistributeOrderPlForm form) throws Exception {
		StringBuffer sql = new StringBuffer("SELECT r1.*,r2.company FROM (SELECT disc.allmoney,disc.amount,disc.min_money,disc.max_money,disc.max_expiry_day,disc.min_expiry_day,disc.remarks,disc.member_id,disc.member_mobile,disc.member_name,disc.type1,disc.type2,disc.need_todoor,disc.endtime,disc.flaw_ticket,dist.* FROM distribute_order_pl dist LEFT JOIN discountrecord_pl disc ON dist.dcrd_pl_id = disc.id WHERE 1=1 and dist.take=1 AND dist.org_id=?");
		List<Object> params = new ArrayList<Object>();
		params.add(form.getOrgId());
		if(form!=null){
			if( form.getState() != null ){
				sql.append(" AND dist.state = ? ");
				params.add(form.getState());
			}else if(form.getStates()!=null && form.getStates().length>0){//订单状态[可能同时查询多个状态]
				String tag = "";
				for(Integer of:form.getStates()){
					if(!"".equals(tag)) tag += ",";
					tag += "?";
					params.add(of);
				}
				sql.append(" AND dist.state in("+tag+")");
			}
			if(StringUtils.hasText(form.getStart())){
				sql.append(" AND dist.create_time >=?");
				params.add(DateUtil.parser(form.getStart()+" 00:00:00", DateUtil.FORMART));
			}
			if(StringUtils.hasText(form.getEnd())){
				sql.append(" AND dist.create_time <=?");
				params.add(DateUtil.parser(form.getEnd()+" 59:59:59", DateUtil.FORMART));
			}
			if(form.getMinMoney()!=null){
				sql.append(" AND disc.allmoney >=?");
				params.add(form.getMinMoney());
			}
			if(form.getMaxMoney()!=null){
				sql.append(" AND disc.allmoney <=?");
				params.add(form.getMaxMoney());
			}
		}
		sql.append(" )r1 LEFT JOIN (SELECT res.org_id AS id,res.member_id,res.company FROM (SELECT * FROM org_info info WHERE info.type_= 0 ORDER BY info.id DESC)res GROUP BY res.member_id)r2 ON r1.member_id=r2.member_id");
		String count = "SELECT COUNT(*) FROM("+sql.toString()+")res";
		PageResults<Map<String,Object>> page = findPageMapByFetchedSql(sql.toString(),count,pageIndex,pageSize,params.toArray());
		return Utility.decodeMobile(page);
	}
}