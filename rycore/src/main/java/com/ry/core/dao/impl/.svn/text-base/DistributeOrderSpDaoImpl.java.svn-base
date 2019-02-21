package com.ry.core.dao.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.DistributeOrderSpDao;
import com.ry.core.entity.DistributeOrderSp;
import com.ry.core.form.DistributeOrderSpForm;
import com.ry.core.form.org.ReviewOrderRequest;
import com.ry.core.util.Utility;
import com.ry.util.DateUtil;
import com.ry.util.page.PageResults;

@Repository
public class DistributeOrderSpDaoImpl extends BaseDao<DistributeOrderSp, Integer> implements DistributeOrderSpDao {

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderSpDao#getById(java.lang.Integer)
	 */
	public DistributeOrderSp getById(Integer id) {
		return get(id);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderSpDao#saveModel(com.ry.core.entity.DistributeOrderSp)
	 */
	public void saveModel(DistributeOrderSp distributeOrderSp) {
		save(distributeOrderSp);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderSpDao#updateModel(com.ry.core.entity.DistributeOrderSp)
	 */
	@Override
	public void updateModel(DistributeOrderSp distributeOrderSp) {
		update(distributeOrderSp);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderSpDao#getAllByDcrdSpId(java.lang.Integer)
	 */
	public List<Map<String,Object>> getAllByDcrdSpId(Integer dcrdSpId){
		StringBuffer sql = new StringBuffer("SELECT dist.*,org.company,org.phone,org.name FROM distribute_order_sp dist LEFT JOIN(SELECT res.org_id AS id,res.company,res.phone,res.name FROM(SELECT * FROM org_info info WHERE info.type_=1 ORDER BY info.id DESC)res GROUP BY res.org_id)org");
		sql.append(" ON dist.org_id=org.id WHERE dist.dcrd_sp_id=? AND dist.state<>-2 AND dist.way IS NOT NULL ORDER BY dist.price");
		List<Object> params = new ArrayList<Object>();
		params.add(dcrdSpId);
		List<Map<String,Object>> list = getListMapBySQL(sql.toString(),params.toArray());
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderSpDao#getPcAllByDcrdSpId(java.lang.Integer)
	 */
	public List<Map<String,Object>> getPcAllByDcrdSpId(Integer dcrdSpId){
		StringBuffer sql = new StringBuffer("SELECT dist.*,org.company,org.phone,org.name FROM distribute_order_sp dist LEFT JOIN(SELECT res.org_id AS id,res.company,res.phone,res.name FROM(SELECT * FROM org_info info WHERE info.type_=1 ORDER BY info.id DESC)res GROUP BY res.org_id)org");
		sql.append(" ON dist.org_id=org.id WHERE dist.dcrd_sp_id=? AND dist.state<>-2 AND dist.way IS NOT NULL ORDER BY dist.price");
		List<Object> params = new ArrayList<Object>();
		params.add(dcrdSpId);
		List<Map<String,Object>> list = getListMapBySQL(sql.toString(),params.toArray());
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderSpDao#getInfoById(java.lang.Integer)
	 */
	public Map<String,Object> getInfoById(Integer dtboId){
		StringBuffer sql = new StringBuffer("SELECT dist.*,org.company,org.phone,org.name FROM distribute_order_sp dist LEFT JOIN(SELECT res.org_id AS id,res.company,res.phone,res.name FROM(SELECT * FROM org_info info WHERE info.type_=1 ORDER BY info.id DESC)res GROUP BY res.org_id)org");
		sql.append(" ON dist.org_id=org.id WHERE dist.id=?");
		List<Object> params = new ArrayList<Object>();
		params.add(dtboId);
		List<Map<String,Object>> list = getListMapBySQL(sql.toString(),params.toArray());
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderSpDao#getCurrentSelectById(java.lang.Integer)
	 */
	public Map<String,Object> getCurrentSelectById(Integer dcrdId){
		StringBuffer sql = new StringBuffer("SELECT dist.*,org.company,org.phone FROM distribute_order_sp dist LEFT JOIN(SELECT res.org_id AS id,res.company,res.phone FROM(SELECT * FROM org_info info WHERE info.type_=1 ORDER BY info.id DESC)res GROUP BY res.org_id)org");
		List<Object> params = new ArrayList<Object>();
		sql.append(" ON dist.org_id=org.id WHERE dist.state NOT IN(-2,0) AND dist.is_select=1 AND dist.dcrd_sp_id=?");
		params.add(dcrdId);
		List<Map<String,Object>> list = getListMapBySQL(sql.toString(),params.toArray());
		if(list!=null){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderSpDao#getByOrgIdAndWay(java.lang.Integer, java.lang.Integer)
	 */
	public List<Map<String,Object>> getWaitByOrgId(Integer orgId){
		StringBuffer sql = new StringBuffer("SELECT dist.*,disc.type1,disc.begintime,disc.printtime,disc.endtime,disc.allmoney,disc.bank,disc.endorse,disc.remarks,disc.no as qyno FROM distribute_order_sp dist LEFT JOIN discountrecord_sp disc ON disc.id=dist.dcrd_sp_id WHERE dist.org_id=? AND state NOT IN(0,-2) AND way IS NULL");
		List<Object> params = new ArrayList<Object>();
		params.add(orgId);
		List<Map<String,Object>> list = getListMapBySQL(sql.toString(),params.toArray());
		return list;
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderSpDao#getByDcrdId(java.lang.Integer)
	 */
	public List<DistributeOrderSp> getByDcrdId(Integer dcrdId) {
		StringBuffer hql = new StringBuffer("from distribute_order_sp where state NOT IN(0,-1) AND dcrdSpId=?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(dcrdId);
		return queryByHql(hql.toString(), paramList.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderSpDao#getByOrgId(java.lang.Integer)
	 */
	public List<DistributeOrderSp> getByOrgId(Integer orgId) {
		StringBuffer hql = new StringBuffer("from distribute_order_sp WHERE orgId = ?");
		List<Object> params = new ArrayList<Object>();
		params.add(orgId);
		return queryByHql(hql.toString(), params.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderSpDao#getList()
	 */
	public List<Map<String, Object>> getList(Integer memberId) {
		StringBuffer sql = new StringBuffer(" SELECT dist.* FROM distribute_order_sp dist LEFT JOIN  discountrecord_sp disc ON disc.id = dist.dcrd_sp_id WHERE disc.orderflag = 1 AND dist.state = 1 AND dist.way IS NOT NULL AND disc.member_id=? ORDER BY dist.create_time desc");
		List<Object> params = new ArrayList<Object>();
		params.add(memberId);
		return getListMapBySQL(sql.toString(), params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderSpDao#getInfoByDtboId(java.lang.Integer)
	 */
	public Map<String,Object> getInfoByDtboId(Integer id){
		StringBuffer sql = new StringBuffer("SELECT disc.*,dist.need_stuff,dist.no distNo,dist.state,dist.price,dist.price1,dist.price2,dist.way,dist.create_time createTime,dist.longitude lon,dist.latitude lat,dist.todoor_price,dist.todoor_time FROM distribute_order_sp dist LEFT JOIN discountrecord_sp disc ON dist.dcrd_sp_id = disc.id WHERE dist.id=?");
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		List<Map<String,Object>> list = getListMapBySQL(sql.toString(),params.toArray());
		if(list!=null && list.size()>0){
			return Utility.decodeMobile(list.get(0));//@ZY EDIT:2016-09-23手机号转码
		}else{
			return null;
		}
	}
	
	@Override
	public PageResults<Map<String,Object>> getPageList(Integer pageIndex,Integer pageSize,DistributeOrderSpForm form) {
		StringBuffer sql = new StringBuffer(" select result.id dtboId,dis.type1,dis.endtime,result.state,result.no jgno,result.create_time,dis.allmoney,dis.bank,dis.endorse,dis.member_mobile FROM distribute_order_sp result left join discountrecord_sp dis on result.dcrd_sp_id = dis.id where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if( form.getOrgId() != null ){
			sql.append(" and result.org_id = ? ");
			params.add(form.getOrgId());
		}
		if( form.getState() != null ){
			sql.append(" AND result.state = ? ");
			params.add(form.getState());
		}
		if(form.getDcrdSpId()!=null){
			sql.append(" AND result.dcrd_sp_id = ? ");
			params.add(form.getDcrdSpId());
		}
		sql.append(" ORDER BY result.create_time DESC");
		String count = "select count(*) from ( " + sql.toString() + " ) bt" ;
		PageResults<Map<String,Object>> page=findPageMapByFetchedSql(sql.toString(),count,pageIndex,pageSize,params.toArray());
		return Utility.decodeMobile(page);//@ZY EDIT:2016-09-23手机号转码
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderSpDao#getList(com.ry.core.form.DistributeOrderSpForm)
	 */
	public List<DistributeOrderSp> getList(DistributeOrderSpForm form) {
		StringBuffer hql = new StringBuffer("from distribute_order_sp where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if(form!=null){
			if(form.getId()!=null){
				hql.append(" AND id=?");
				params.add(form.getId());
			}
			if(form.getDcrdSpId()!=null){
				hql.append(" AND dcrdSpId=?");
				params.add(form.getDcrdSpId());
			}
			if(form.getIsSelect()!=null){//企业端选择机构的标示
				hql.append(" AND isSelect=?");
				params.add(form.getIsSelect());
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
	 * @see com.ry.core.dao.DistributeOrderSpDao#getListByDiscIdAndOrgId(java.lang.Integer, java.lang.Integer)
	 */
	public List<DistributeOrderSp> getListByDiscIdAndOrgId(Integer discId, Integer orgId) {
		StringBuffer hql = new StringBuffer("from distribute_order_sp WHERE orgId = ? and dcrdSpId =?");
		List<Object> params = new ArrayList<Object>();
		params.add(orgId);
		params.add(discId);
		return queryByHql(hql.toString(), params.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderSpDao#getOrderPageList(java.lang.Integer, java.lang.Integer, com.ry.core.form.DistributeOrderSpForm)
	 */
	public PageResults<DistributeOrderSp> getOrderPageList(ReviewOrderRequest req) {
		StringBuffer sql = new StringBuffer("SELECT * FROM distribute_order_sp WHERE 1=1");
		StringBuffer count = new StringBuffer("SELECT COUNT(*) FROM distribute_order_sp WHERE 1=1");
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
	 * @see com.ry.core.dao.DistributeOrderSpDao#getOrderPageList(java.lang.Integer, java.lang.Integer, com.ry.core.form.DistributeOrderSpForm)
	 */
	public PageResults<Map<String,Object>> getOrderMemberPageList(ReviewOrderRequest req) {
		StringBuffer sql = new StringBuffer("SELECT r1.* ,m.mobile from (SELECT diso.* ,o.member_id  as memberid from (SELECT * FROM distribute_order_sp  order by create_time desc)diso LEFT JOIN org o ON diso.org_id = o.id)r1  LEFT JOIN member m ON r1.memberid = m.id WHERE 1=1 ");
		StringBuffer count = new StringBuffer("SELECT COUNT(r1.id) from (SELECT diso.* ,o.member_id  as memberid from (SELECT * FROM distribute_order_sp  order by create_time desc)diso LEFT JOIN org o ON diso.org_id = o.id)r1  LEFT JOIN member m ON r1.memberid = m.id WHERE 1=1 ");
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
	 * @see com.ry.core.dao.DistributeOrderSpDao#getOverrunByCreateTime(java.util.Date)
	 */
	public List<DistributeOrderSp> getOverrunByCreateTime(Date date) {
		StringBuffer hql = new StringBuffer("FROM distribute_order_sp WHERE state=1 AND (is_select<>1 or is_select is null) AND create_time<?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(date);
		return queryByHql(hql.toString(), paramList.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderSpDao#getOrderByCreateTime(java.lang.Integer)
	 */
	public List<DistributeOrderSp> getOrderByCreateTime(Integer dcrdSpId) {
		StringBuffer hql = new StringBuffer("FROM distribute_order_sp dist WHERE dist.dcrdSpId=? ORDER BY dist.createTime DESC");
		List<Object> params = new ArrayList<Object>();
		params.add(dcrdSpId);
		return queryByHql(hql.toString(), params.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderSpDao#getPageListPC(java.lang.Integer, java.lang.Integer, com.ry.core.form.DistributeOrderSpForm)
	 */
	public PageResults<Map<String, Object>> getPageListPC(Integer pageIndex, Integer pageSize,DistributeOrderSpForm form) throws Exception{
		StringBuffer sql = new StringBuffer("SELECT r1.*,r2.company FROM (SELECT disc.allmoney,disc.remarks,disc.member_id,disc.member_mobile,disc.member_name,disc.type1,disc.bank,disc.need_todoor,disc.endorse,disc.printtime,disc.begintime,disc.endtime,dist.* FROM distribute_order_sp dist LEFT JOIN discountrecord_sp disc ON dist.dcrd_sp_id = disc.id WHERE 1=1 AND dist.way is not null AND dist.org_id=?");
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
				sql.append(" AND disc.create_time >=?");
				params.add(DateUtil.parser(form.getStart()+" 00:00:00", DateUtil.FORMART));
			}
			if(StringUtils.hasText(form.getEnd())){
				sql.append(" AND disc.create_time <=?");
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
			if(form.getBank()!=null){
				sql.append(" AND disc.bank like ?");
				params.add("%"+form.getBank()+"%");
			}
		}
		sql.append(" )r1 LEFT JOIN (SELECT res.org_id AS id,res.member_id,res.company FROM (SELECT * FROM org_info info WHERE info.type_= 0 ORDER BY info.id DESC)res GROUP BY res.member_id)r2 ON r1.member_id=r2.member_id");
		String count = "SELECT COUNT(*) FROM("+sql.toString()+")res";
		PageResults<Map<String,Object>> page = findPageMapByFetchedSql(sql.toString(),count,pageIndex,pageSize,params.toArray());
		return Utility.decodeMobile(page);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderSpDao#getByNo(java.lang.String)
	 */
	public DistributeOrderSp getByNo(String no) throws Exception{
		StringBuffer sql = new StringBuffer("from distribute_order_sp where no =?");
		List<Object> params = new ArrayList<Object>();
		params.add(no);
		List<DistributeOrderSp> list = queryByHql(sql.toString(), params.toArray());
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
}