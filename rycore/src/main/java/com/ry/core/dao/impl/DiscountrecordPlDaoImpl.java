package com.ry.core.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.DiscountrecordPlDao;
import com.ry.core.entity.DiscountrecordPl;
import com.ry.core.entity.Systeminfo.ReadState;
import com.ry.core.form.DiscountrecordPlForm;
import com.ry.core.form.MemOrder.MemOrderPageRequest;
import com.ry.core.util.Utility;
import com.ry.util.page.PageResults;

@Repository
public class DiscountrecordPlDaoImpl extends BaseDao<DiscountrecordPl, Integer> implements DiscountrecordPlDao {

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordPlDao#getById(java.lang.Integer)
	 */
	public DiscountrecordPl getById(Integer id) {
		DiscountrecordPl discountrecordPl=get(id);
		return DiscountrecordPl.deDiscountrecordPl(discountrecordPl);//@ZY EDIT:2016-09-23手机号转码
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordPlDao#getByOrdernumber(java.lang.String)
	 */
	public DiscountrecordPl getByOrdernumber(String ordernumber){
		StringBuffer hql = new StringBuffer("from discountrecord_pl where no = ? ");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(ordernumber);
		List<DiscountrecordPl> list = getListByHQL(hql.toString(), paramList.toArray());	
		return DiscountrecordPl.deDiscountrecordPl(list.get(0));//@WKX EDIT2016-08-19 手机号转码;
		
	}
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordPlDao#saveModel(com.ry.core.entity.DiscountrecordPl)
	 */
	public void saveModel(DiscountrecordPl discountrecordPl) {
		save(DiscountrecordPl.enDiscountrecordPl(discountrecordPl));//@ZY EDIT:2016-09-23手机号转码
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordPlDao#updateModel(com.ry.core.entity.DiscountrecordPl)
	 */
	public void updateModel(DiscountrecordPl discountrecordPl) {
		update(DiscountrecordPl.enDiscountrecordPl(discountrecordPl));//@ZY EDIT:2016-09-23手机号转码
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordPlDao#getPageDisRecordPl(com.ry.core.form.MemOrder.MemOrderPageRequest)
	 */
	public PageResults<Map<String,Object>> getPageDisRecordPl(MemOrderPageRequest mem) {
		StringBuffer hql = new StringBuffer(" from discountrecord_pl disc LEFT JOIN member m ON disc.member_id = m.id where (is_valid = 'T' OR is_valid IS NULL) ");
		StringBuffer hqlcount = new StringBuffer("select count(*) from discountrecord_pl where (is_valid = 'T' OR is_valid IS NULL)");
		List<Object> params = new ArrayList<Object>();
		if(StringUtils.hasText(mem.getTel())){
			hql.append(" AND m.mobile = ?");
			params.add(Utility.encodeMobile(mem.getTel()));
		}
		if(StringUtils.hasText(mem.getOrderNumber())){
			hql.append(" AND disc.ordernumber = ?");
			params.add(mem.getOrderNumber());
		}
		if(StringUtils.hasText(mem.getStartDate())){
			hql.append(" and create_time >= ?");
			hqlcount.append(" and create_time >= ?");
			params.add(mem.getStartDate() + " 00:00:00");
		}
		if(StringUtils.hasText(mem.getEndDate())){
			hql.append(" and create_time <= ?");
			hqlcount.append(" and create_time >= ?");
			params.add(mem.getEndDate() + " 23:59:59");
		}
		if(mem.getState() != null){
			if(mem.getState()==0){
				hql.append(" and orderflag in (0,-2)");//超时，和手动取消订单的无效状态
				hqlcount.append(" and orderflag in (0,-2)");
			}else{
				hql.append(" and orderflag = ?");
				hqlcount.append(" and orderflag = ?");
				params.add(mem.getState());
			}
		}
		hql.append(" order by create_time desc");
		String str = new String(" SELECT disc.*,m.mobile " + hql.toString());
		PageResults<Map<String,Object>> pageResult = findPageMapByFetchedSql(str, hqlcount.toString(), mem.currentPage().intValue(), mem.getLength().intValue(), params.toArray());
		
		return Utility.decodeMobile(pageResult);//@ZY EDIT:2016-09-23手机号转码
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordPlDao#getInfoById(java.lang.Integer)
	 */
	public Map<String, Object> getInfoById(Integer id) {
		StringBuffer sql = new StringBuffer("SELECT r2.company,disc.*,mem.username AS username,mem.mobile AS memmobile,dist.take,dist.no as jgno,dist.cancel_cause AS cancelCause,dist.state,dist.longitude lon,dist.latitude lat FROM discountrecord_pl disc LEFT JOIN member mem ON mem.id = disc.member_id LEFT JOIN distribute_order_pl dist ON dist.dcrd_pl_id = disc.id LEFT JOIN (SELECT res.member_id,res.company FROM (SELECT * FROM org_info info WHERE info.type_= 0 ORDER BY info.id DESC)res GROUP BY res.member_id)r2 ON disc.member_id=r2.member_id WHERE disc.id = ?");
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		List<Map<String, Object>> list = getListMapBySQL(sql.toString(), params.toArray());
		if(list!=null && list.size()>0){
			return Utility.decodeMobile(list.get(0));//@ZY EDIT:2016-09-23手机号转码
		}else{
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordPlDao#getOrgList(com.ry.core.entity.DiscountrecordPl)
	 */
	public List<Map<String, Object>> getOrgListByPrice(DiscountrecordPl discountrecordPl) {
		StringBuffer sql = new StringBuffer(" SELECT mit.org_id as orgId ,(mit.price-mit.used_price) AS price , info.company as company from org_limit mit INNER JOIN (SELECT res.org_id id,res.company FROM (SELECT o.id org_id,i.id org_info_id,i.company,i.type_ FROM org o LEFT JOIN org_info i ON o.id=i.org_id WHERE (i.type_=1 OR (i.type_ IS NULL AND o.type_=1)) ORDER BY i.id DESC)res GROUP BY res.org_id) info ON mit.org_id = info.id where 1 = 1 "); 
		List<Object> paramList = new ArrayList<Object>();
		Integer orderMoney = (int)(discountrecordPl.getAllmoney()-0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sql.append(" and mit.day = ?");
		paramList.add(sdf.format(new Date()));
		if(orderMoney != null){
			sql.append(" and mit.price >= ?");
			paramList.add(orderMoney);
		}
		sql.append(" GROUP BY orgId");
		List<Map<String,Object>> list = getListMapBySQL(sql.toString(), paramList.toArray());
		return list;
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordPlDao#getOrgs(java.lang.Integer, com.ry.core.entity.DiscountrecordPl)
	 */
	public List<Map<String, Object>> getOrgListByType(DiscountrecordPl discountrecordPl) {
		StringBuffer sql = new StringBuffer("SELECT p.org_id,pt.type2 FROM price p INNER JOIN price_type pt ON p.price_type_id = pt.id  WHERE pt.type2+1 = ? GROUP BY p.org_id");
		List<Object> params = new ArrayList<Object>();
		params.add(discountrecordPl.getType1());
		return getListMapBySQL(sql.toString(), params.toArray());
	}
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordPlDao#getUnReadByMemberId(java.lang.Integer, java.lang.Integer)
	 */
	public List<Map<String, Object>> getUnReadByMemberId(Integer memberId, Integer orderflag) {
		StringBuffer sql = new StringBuffer("SELECT res.* FROM(");
		List<Object> params = new ArrayList<Object>();
		
		sql.append("SELECT task.* FROM discountrecord_pl dis,discountrecord_task task WHERE dis.id=task.discountrecordId AND task.read_state=?");
		params.add(ReadState.UNREAD.getValue());//原生SQL不能比较对象
		if(memberId!=null){
			sql.append(" AND dis.member_id=?");
			params.add(memberId);
		}
		if(orderflag!=null){
			sql.append(" AND dis.orderflag=?");
			params.add(orderflag);
		}
		sql.append(" ORDER BY task.id DESC)res GROUP BY res.discountrecordId");
		return getListMapBySQL(sql.toString(),params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordPlDao#getPageList(java.lang.Integer)
	 */
	public PageResults<Map<String,Object>> getPageList(Integer pageIndex,Integer pageSize,DiscountrecordPlForm form) {
		StringBuffer sql = new StringBuffer("FROM ");
		List<Object> params = new ArrayList<Object>();
		
		sql.append("(SELECT result.*,c.id commentId FROM (SELECT d.remarks,d.id,d.type1,d.type2,d.create_time,d.endtime,d.no,d.amount,d.member_id,d.orderflag,d.allmoney,dis.org_id,dis.id dtboId FROM discountrecord_pl d LEFT JOIN distribute_order_pl dis ON d.id=dis.dcrd_pl_id AND dis.state NOT IN(0,-2) WHERE d.member_id=? AND (d.is_valid<>'F' OR d.is_valid IS NULL)");
		params.add(form.getMemberId());
		if(form!=null && form.getOrderflag()!=null){
			if(form.getOrderflag() !=  null){
				sql.append(" AND d.orderflag=?");
				params.add(form.getOrderflag());
			}
		}
		sql.append(")result LEFT JOIN comments c ON result.id=c.dcrd_id AND c.type_=2");
		sql.append(")r1 LEFT JOIN(SELECT res.org_id AS id,res.company,res.phone FROM(SELECT * FROM org_info info WHERE info.type_=1 ORDER BY info.id DESC)res GROUP BY res.org_id)r2");
		sql.append(" ON r1.org_id=r2.id");
 		if(form!=null && form.getComment()!=null){
			if(form.getComment()==0){
				sql.append(" where r1.commentId is not null ");
			}else if(form.getComment()==1){
				sql.append(" where r1.commentId is null ");
			}
		}
		sql.append(" ORDER BY r1.create_time DESC");
		String count = "select count(*) "+ sql.toString();
		return findPageMapByFetchedSql("SELECT r1.*,r2.company,r2.phone "+sql.toString(),count,pageIndex,pageSize,params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordPlDao#getPageList(java.lang.Integer)
	 */
	public PageResults<Map<String,Object>> getPcPageList(Integer pageIndex,Integer pageSize,DiscountrecordPlForm form) {
		StringBuffer sql = new StringBuffer("FROM ");
		List<Object> params = new ArrayList<Object>();
		
		sql.append("(SELECT result.*,c.id commentId,avg(c.speed)*2 dksd,avg(c.service)*2 fwtd,avg(c.price)*2 sjjg,AVG(isToDoor)*100 sml FROM (SELECT d.address,d.member_mobile,d.member_name,d.need_todoor,d.flaw_ticket,d.remarks,d.id,d.type1,d.type2,d.min_expiry_day,d.max_expiry_day,d.create_time,d.endtime,d.no,d.amount,d.member_id,d.orderflag,d.allmoney,d.min_money,d.max_money,dis.org_id,dis.id dtboId FROM discountrecord_pl d LEFT JOIN distribute_order_pl dis ON d.id=dis.dcrd_pl_id AND dis.state NOT IN(0,-2) WHERE d.member_id=? AND (d.is_valid<>'F' OR d.is_valid IS NULL)");
		params.add(form.getMemberId());
		if(form!=null && form.getOrderflag()!=null){
			if(form.getOrderflag() !=  null){
				sql.append(" AND d.orderflag=?");
				params.add(form.getOrderflag());
			}
		}else if(form!=null && form.getState()!=null){//对应多个状态一起查询
			String tag = "";
			for(Integer of:form.getState()){
				if(!"".equals(tag)) tag += ",";
				tag += "?";
				params.add(of);
			}
			sql.append(" and d.orderflag in("+tag+")");
		}
		if(form!=null && form.getCreateTime()!=null){//开始时间
			sql.append(" AND d.endtime >= ?");
			params.add(form.getCreateTime());
		}
		if(form!=null && form.getEndtime()!=null){//结束时间
			sql.append(" AND d.endtime <= ?");
			params.add(form.getEndtime());
		}
		if(form!=null && form.getMinallmoney()!=null){//最小金额
			sql.append(" AND d.allmoney >= ?");
			params.add(form.getMinallmoney());
		}
		if(form!=null && form.getMaxallmoney()!=null){//最大金额
			sql.append(" AND d.allmoney <= ?");
			params.add(form.getMaxallmoney());
		}
		if(form!=null && form.getNo()!=null){//根据订单号获取
			sql.append(" AND d.no = ?");
			params.add(form.getNo());
		}
		sql.append(")result LEFT JOIN comments c ON result.id=c.dcrd_id AND c.type_=2 group by result.id");
		sql.append(")r1 LEFT JOIN(SELECT res.org_id AS id,res.company,res.phone ,res.name FROM(SELECT * FROM org_info info WHERE info.type_=1 ORDER BY info.id DESC)res GROUP BY res.org_id)r2");
		sql.append(" ON r1.org_id=r2.id");
 		if(form!=null && form.getComment()!=null){
			if(form.getComment()==0){
				sql.append(" where r1.commentId is not null ");
			}else if(form.getComment()==1){
				sql.append(" where r1.commentId is null ");
			}
		}
		sql.append(" ORDER BY r1.create_time DESC");
		String count = "select count(*) "+ sql.toString();
		return Utility.decodeMobile(findPageMapByFetchedSql("SELECT r1.*,r2.company,r2.phone ,r2.name "+sql.toString(),count,pageIndex,pageSize,params.toArray()));
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordPlDao#updateModel(com.ry.core.entity.DiscountrecordPl)
	 */
	public PageResults<Map<String,Object>> getPageList(Integer pageIndex,Integer pageSize,Integer id) {
		StringBuffer sql = new StringBuffer("select result.* from( select c.content,c.create_time,c.dtbo_id,d.address,d.member_mobile from comments c left join discountrecord_pl d on c.dcrd_id=d.id ) result left join distribute_order_pl dist on result.dtbo_id = dist.id where dist.org_id = ? ");
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		String count = " select count(*) FROM  ("  + sql.toString() + " ) bt";
		PageResults<Map<String,Object>> page= findPageMapByFetchedSql(sql.toString(),count,pageIndex,pageSize,params.toArray());
		return Utility.decodeMobile(page);//@ZY EDIT:2016-09-23手机号转码
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordPlDao#updateModel(com.ry.core.entity.DiscountrecordPl)
	 */
	public List<Map<String,Object>> getInfoAndOrderById(Integer id){
		StringBuffer sql = new StringBuffer("select result.*,c.id commentId From (SELECT d.*,dis.state,dis.dcrd_pl_id,dis.id dtboId,dis.org_id FROM discountrecord_pl d LEFT JOIN distribute_order_pl dis ON d.id=dis.dcrd_pl_id AND dis.state NOT IN(0,-1,-2) WHERE d.id=? ) result left join comments c on result.id = c.dcrd_id where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		List<Map<String,Object>> list=getListMapBySQL(sql.toString(), params.toArray());
		return Utility.decodeMobile(list);//@ZY EDIT:2016-09-23手机号转码
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordPlDao#getNeedPaidan()
	 */
	public List<Map<String, Object>> getNeedPaidan() {
		StringBuffer sql = new StringBuffer("SELECT disc.id,disc.orderflag,dist.id dtboId,dist.state,dist.take FROM discountrecord_pl disc LEFT JOIN distribute_order_pl dist ON disc.id=dist.dcrd_pl_id AND (dist.state NOT IN(-2,-1,0) OR dist.take<>1)");
		List<Object> params = new ArrayList<Object>();
		sql.append(" WHERE disc.orderflag=1  AND disc.id NOT IN(");
		sql.append(" SELECT disc.id FROM discountrecord_pl disc LEFT JOIN distribute_order_pl dist ON disc.id=dist.dcrd_pl_id");
		sql.append(" WHERE disc.orderflag=1  AND (dist.state NOT IN(-2,-1,0) OR dist.take<>1))");
		return getListMapBySQL(sql.toString(),params.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordPlDao#getByEndTime(java.util.Date)
	 */
	public List<DiscountrecordPl> getByEndTime(Date date) {
		StringBuffer hql = new StringBuffer(" FROM discountrecord_pl WHERE orderflag=1 AND endtime < ?");
		List<Object> params = new ArrayList<Object>();
		params.add(date);
		List<DiscountrecordPl> list=getListByHQL(hql.toString(), params.toArray());
	    return DiscountrecordPl.deDiscountrecordPl(list);//@ZY EDIT:2016-09-23手机号转码
	}
	
	@Override
	public List<DiscountrecordPl> getList(String id) {
		String hql = "from discountrecord_pl where 1=1 ";
		List<Object> paramList = new ArrayList<Object>();		
		if (id != null && !"".equals(id)) {
			hql += " and id like ? ";
			paramList.add(Integer.parseInt(id));
		}
		List<DiscountrecordPl> list = getListByHQL(hql, paramList.toArray());
		return DiscountrecordPl.deDiscountrecordPl(list);//@ZY EDIT:2016-09-23手机号转码
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordPlDao#getModelById(java.lang.Integer)
	 */
	public DiscountrecordPl getModelById(Integer id) {
		return get(id);
	}
	
	public List<DiscountrecordPl> getByDiscountrecordPl(DiscountrecordPl dis) {
		StringBuffer hql = new StringBuffer(" FROM discountrecord_pl WHERE 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if(dis.getMemberId()!=null){
			hql.append(" and member_id = ? ");
			params.add(dis.getMemberId());
		}
		List<DiscountrecordPl> list=getListByHQL(hql.toString(), params.toArray());
	    return DiscountrecordPl.deDiscountrecordPl(list);//@ZY EDIT:2016-09-23手机号转码
	}

	@Override
	public List<DiscountrecordPl> getByObj(MemOrderPageRequest mem) {
		StringBuffer hql = new StringBuffer("from discountrecord_pl where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if(mem!=null){
			if(StringUtils.hasText(mem.getStartDate())){
				hql.append(" and create_time >= ?");
				params.add(mem.getStartDate() + " 00:00:00");
			}
			if(StringUtils.hasText(mem.getEndDate())){
				hql.append(" and create_time <= ?");
				params.add(mem.getEndDate() + " 23:59:59");
			}
			if(mem.getState() != null){
				if(mem.getState()==0){
					hql.append(" and orderflag in (0,-2)");//超时，和手动取消订单的无效状态
				}else{
					hql.append(" and orderflag = ?");
					params.add(mem.getState());
				}
			}
		}
		List<DiscountrecordPl> list = getListByHQL(hql.toString(), params.toArray());	
		return DiscountrecordPl.deDiscountrecordPl(list);//@WKX EDIT2016-08-19 手机号转码
	}
}