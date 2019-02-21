package com.ry.core.dao.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.DiscountrecordSpDao;
import com.ry.core.entity.DiscountrecordSp;
import com.ry.core.form.DiscountrecordSpForm;
import com.ry.core.form.MemOrder.MemOrderPageRequest;
import com.ry.core.util.Utility;
import com.ry.util.DateUtil;
import com.ry.util.page.PageResults;

@Repository
public class DiscountrecordSpDaoImpl extends BaseDao<DiscountrecordSp, Integer> implements DiscountrecordSpDao {

	private static Logger logger = Logger.getLogger(DiscountrecordSpDaoImpl.class);
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordSpDao#getById(java.lang.Integer)
	 */
	public DiscountrecordSp getById(Integer id) {
		DiscountrecordSp discountrecordSp=get(id);
		return DiscountrecordSp.deDiscountrecordSp(discountrecordSp);//@ZY EDIT:2016-09-23手机号转码
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordSpDao#getByOrdernumber(java.lang.String)
	 */
	public DiscountrecordSp getByOrdernumber(String ordernumber){
		StringBuffer hql = new StringBuffer("from discountrecord_sp where no = ? ");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(ordernumber);
		List<DiscountrecordSp> list = getListByHQL(hql.toString(), paramList.toArray());	
		return DiscountrecordSp.deDiscountrecordSp(list.get(0));//@WKX EDIT2016-08-19 手机号转码;
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordSpDao#saveModel(com.ry.core.entity.DiscountrecordSp)
	 */
	public void saveModel(DiscountrecordSp discountrecordSp) {
		save(DiscountrecordSp.enDiscountrecordSp(discountrecordSp));//@ZY EDIT:2016-09-23手机号转码
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordSpDao#updateModel(com.ry.core.entity.DiscountrecordSp)
	 */
	public void updateModel(DiscountrecordSp discountrecordSp) {
		update(DiscountrecordSp.enDiscountrecordSp(discountrecordSp));//@ZY EDIT:2016-09-23手机号转码
	}
	

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordSpDao#getPageList(java.lang.Integer, java.lang.Integer, com.ry.core.form.DiscountrecordSpForm)
	 */
	public PageResults<Map<String,Object>> getPageList(Integer pageIndex,Integer pageSize,DiscountrecordSpForm form) {
		StringBuffer sql = new StringBuffer("FROM ");
		List<Object> params = new ArrayList<Object>();
		
		sql.append("(SELECT result.*,c.id commentId FROM (SELECT disc.*,dist.org_id FROM discountrecord_sp disc LEFT JOIN distribute_order_sp dist ON disc.id=dist.dcrd_sp_id AND dist.state NOT IN(-2,0) AND dist.is_select=1 WHERE disc.member_id=? AND (disc.is_valid<>'F' OR disc.is_valid IS NULL)");
		params.add(form.getMemberId());
		if(form!=null && form.getOrderflag()!=null){
			sql.append(" AND disc.orderflag=?");
			params.add(form.getOrderflag());
		}else if(form!=null && form.getState()!=null){//对应多个状态一起查询
			String tag = "";
			for(Integer of:form.getState()){
				if(!"".equals(tag)) tag += ",";
				tag += "?";
				params.add(of);
			}
			sql.append(" and disc.orderflag in("+tag+")");
		}
		sql.append(")result LEFT JOIN comments c ON result.id=c.dcrd_id AND c.type_=1");
		
		sql.append(")r1 LEFT JOIN(SELECT res.org_id AS id,res.company,res.phone FROM(SELECT * FROM org_info info WHERE info.type_=1 ORDER BY info.id DESC)res GROUP BY res.org_id)r2");
		sql.append(" ON r1.org_id=r2.id");
		
 		if(form!=null && form.getComment()!=null){//评价标示（0已评价,1未评价）
			if(form.getComment()==0){
				sql.append(" where r1.commentId is not null ");
			}else if(form.getComment()==1){
				sql.append(" where r1.commentId is null");
			}
		}
		sql.append(" ORDER BY r1.create_time DESC");
		String count = "select count(*) "+ sql.toString();
		PageResults<Map<String,Object>> page=findPageMapByFetchedSql("SELECT r1.*,r2.company,r2.phone "+sql.toString(),count,pageIndex,pageSize,params.toArray());
		return Utility.decodeMobile(page);//@ZY EDIT:2016-09-23手机号转码
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordSpDao#getPcPageList(java.lang.Integer, java.lang.Integer, com.ry.core.form.DiscountrecordSpForm)
	 */
	public PageResults<Map<String,Object>> getPcPageList(Integer pageIndex,Integer pageSize,DiscountrecordSpForm form) {
		StringBuffer sql = new StringBuffer("FROM ");
		List<Object> params = new ArrayList<Object>();
		
		sql.append("(SELECT result.*,c.id commentId,avg(c.speed)*2 dksd,avg(c.service)*2 fwtd,avg(c.price)*2 sjjg,AVG(isToDoor)*100 sml FROM (SELECT disc.*,dist.id as distId, dist.org_id,dist.txlx,dist.latitude d_lat ,dist.longitude d_lon,dist.price rate,dist.price1 rate1,dist.price2 rate2 ,dist.way,dist.need_stuff,dist.todoor_time,dist.todoor_price FROM discountrecord_sp disc LEFT JOIN distribute_order_sp dist ON disc.id=dist.dcrd_sp_id AND dist.state NOT IN(-2,0) AND dist.is_select=1 WHERE disc.member_id=? AND (disc.is_valid<>'F' OR disc.is_valid IS NULL)");
		params.add(form.getMemberId());
		if(form!=null && form.getOrderflag()!=null){
			sql.append(" AND disc.orderflag=?");
			params.add(form.getOrderflag());
		}else if(form!=null && form.getState()!=null){//对应多个状态一起查询
			String tag = "";
			for(Integer of:form.getState()){
				if(!"".equals(tag)) tag += ",";
				tag += "?";
				params.add(of);
			}
			sql.append(" and disc.orderflag in("+tag+")");
		}
		if(form!=null && form.getBegintime()!=null){//开始时间
			sql.append(" AND disc.begintime >= ?");
			params.add(form.getBegintime());
		}
		if(form!=null && form.getEndtime()!=null){//结束时间
			sql.append(" AND disc.begintime <= ?");
			params.add(form.getEndtime());
		}
		if(form!=null && form.getMinallmoney()!=null){//最小金额
			sql.append(" AND disc.allmoney >= ?");
			params.add(form.getMinallmoney());
		}
		if(form!=null && form.getMaxallmoney()!=null){//最大金额
			sql.append(" AND disc.allmoney <= ?");
			params.add(form.getMaxallmoney());
		}
		if(form!=null && form.getNo()!=null){//根据订单号获取
			sql.append(" AND disc.no = ?");
			params.add(form.getNo());
		}
		if(form!=null && form.getBank()!=null){//承兑行获取
			sql.append(" AND disc.bank=?");
			params.add(form.getBank());
		}
		sql.append(")result LEFT JOIN comments c ON result.id=c.dcrd_id AND c.type_=1 group by result.id");
		
		sql.append(")r1 LEFT JOIN(SELECT res.org_id AS id,res.company,res.phone ,res.name FROM(SELECT * FROM org_info info WHERE info.type_=1 ORDER BY info.id DESC)res GROUP BY res.org_id)r2");
		sql.append(" ON r1.org_id=r2.id");
		
 		if(form!=null && form.getComment()!=null){//评价标示（0已评价,1未评价）
			if(form.getComment()==0){
				sql.append(" where r1.commentId is not null ");
			}else if(form.getComment()==1){
				sql.append(" where r1.commentId is null");
			}
		}
		sql.append(" ORDER BY r1.create_time DESC");
		String count = "select count(*) "+ sql.toString();
		PageResults<Map<String,Object>> page=findPageMapByFetchedSql("SELECT r1.*,r2.company,r2.phone,r2.name "+sql.toString(),count,pageIndex,pageSize,params.toArray());
		return Utility.decodeMobile(page);//@ZY EDIT:2016-09-23手机号转码
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordSpDao#getPageDisRecordSp(com.ry.core.form.MemOrder.MemOrderPageRequest)
	 */
	public PageResults<Map<String,Object>> getPageDisRecordSp(MemOrderPageRequest mem) {
		StringBuffer hql = new StringBuffer(" from discountrecord_sp disc LEFT JOIN member m ON disc.member_id = m.id where (disc.is_valid = 'T' OR disc.is_valid IS NULL) ");
		StringBuffer hqlcount = new StringBuffer("select count(*) from discountrecord_sp disc LEFT JOIN member m ON disc.member_id = m.id where (is_valid = 'T' OR is_valid IS NULL)");
		List<Object> params = new ArrayList<Object>();
		if(StringUtils.hasText(mem.getOrderNumber())){
			hql.append(" AND disc.ordernumber = ?");
			params.add(mem.getOrderNumber());
		}
		if(StringUtils.hasText(mem.getStartDate())){
			hql.append(" and disc.create_time >= ?");
			hqlcount.append(" and disc.create_time >= ?");
			params.add(mem.getStartDate() + " 00:00:00");
		}
		if(StringUtils.hasText(mem.getEndDate())){
			hql.append(" and disc.create_time <= ?");
			hqlcount.append(" and disc.create_time <= ?");
			params.add(mem.getEndDate() + " 23:59:59");
		}
		if(StringUtils.hasText(mem.getBank())){
			hql.append(" AND disc.bank like ?");
			params.add("%"+mem.getBank()+"%");
		}
		if(mem.getState() != null){
			hql.append(" and disc.orderflag = ?");
			hqlcount.append(" and disc.orderflag = ?");
			params.add(mem.getState());
		}
		hql.append(" order by disc.create_time desc");
		String str = new String(" SELECT disc.*,m.mobile" + hql.toString());
		PageResults<Map<String,Object>> pageResult = findPageMapByFetchedSql(str, hqlcount.toString(), mem.currentPage().intValue(), mem.getLength().intValue(), params.toArray());
		
		return Utility.decodeMobile(pageResult);//@ZY EDIT:2016-09-23手机号转码
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordSpDao#getInfoById(java.lang.Integer)
	 */
	public Map<String, Object> getInfoById(Integer id) {
		StringBuffer sql = new StringBuffer(" SELECT record.source,record.accept_time,record.trade_model,record.remarks,record.no AS ordernumber, record.type1 AS type1,record.bank AS bank,record.need_todoor, record.place AS place,record.address AS address, record.begintime AS begintime, record.endtime AS endtime, record.allmoney AS allmoney, record.member_mobile AS membermobile, record.member_name AS membername, record.picpath AS picpath,record.orderflag AS orderflag,record.cancel_cause,mem.username AS username,mem.mobile AS memmobile,ordersp.todoor_price AS todoorPrice,ordersp.todoor_time AS todoorTime,ordersp.cancel_cause AS cancelCause FROM discountrecord_sp record LEFT JOIN member mem ON mem.id = record.member_id LEFT JOIN distribute_order_sp ordersp ON ordersp.dcrd_sp_id = record.id where record.id = ?");
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
	 * @see com.ry.core.dao.DiscountrecordSpDao#getOrgList(com.ry.core.entity.DiscountrecordSp)
	 */
	public List<Map<String, Object>> getOrgList(DiscountrecordSp discountrecordSp) {
		StringBuffer hql = new StringBuffer(" SELECT * FROM (SELECT mit.org_id AS orgId ,mit.day,(IFNULL(mit.price_sp,0)-IFNULL(mit.used_price_sp,0)) AS price1, info.company AS company,reqsp.type ,reqsp.max_day,reqsp.min_day,reqsp.create_time,reqsp.max_price,reqsp.min_price FROM org_limit mit INNER JOIN (SELECT res1.org_id id,res1.company FROM (SELECT o.id org_id,i.id org_info_id,i.company,i.type_ FROM org o LEFT JOIN org_info i ON o.id=i.org_id WHERE (i.type_=1 OR (i.type_ IS NULL AND o.type_=1)) ORDER BY i.id DESC)res1 GROUP BY res1.org_id) info ON mit.org_id = info.id LEFT JOIN requirement_sp reqsp ON reqsp.org_id = info.id )res WHERE 1 = 1 ");
		Integer orderMoney = 0;
		if(discountrecordSp.getAllmoney()==null){
			orderMoney = 0;
		}else{
			orderMoney = discountrecordSp.getAllmoney().intValue();
		}
		Integer days = 0;
		try {
			days = DateUtil.daysBetween(discountrecordSp.getBegintime(), discountrecordSp.getEndtime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Object> paramList = new ArrayList<Object>();
		hql.append(" and res.day = ?");//匹配机构当天报价
		paramList.add(DateUtil.formart(new Date(), DateUtil.FORMART3));
		logger.info("匹配机构当天报价"+DateUtil.formart(new Date(), DateUtil.FORMART3));
		hql.append(" AND DATE_FORMAT(res.create_time,'%Y-%m-%d') = ?");//匹配机构商票当天报价
		paramList.add(DateUtil.formart(new Date(), DateUtil.FORMART3));
		logger.info("匹配机构商票当天报价"+DateUtil.formart(new Date(), DateUtil.FORMART3));
		if(orderMoney != null){
			hql.append(" and res.price1 >= ?");//匹配报价额度
			paramList.add(orderMoney);
			logger.info("匹配报价额度"+orderMoney);
		}
		if(discountrecordSp.getType1()!=null){
			hql.append(" and res.type = ?");//匹配报价的票据介质
			paramList.add(discountrecordSp.getType1());
			logger.info("匹配报价的票据介质"+discountrecordSp.getType1());
		}
		if(orderMoney!=null){
			hql.append(" AND res.max_price >= ?");//匹配报价的金额段
			paramList.add(orderMoney);
			logger.info("匹配报价的金额段"+orderMoney);
		}
		if(orderMoney!=null){
			hql.append(" AND res.min_price <= ?");//匹配报价的金额段
			paramList.add(orderMoney);
			logger.info("匹配报价的金额段"+orderMoney);
		}
		if(days!=null){
			hql.append(" and res.max_day >= ?");//匹配报价的最大收票天数与商票订单有效期天数
			paramList.add(days);
			logger.info("匹配报价的最大收票天数与商票订单有效期天数"+days);
		}
		if(days!=null){
			hql.append(" and res.min_day <= ?");//匹配报价的最小收票天数与商票订单有效期天数
			paramList.add(days);
			logger.info("匹配报价的最小收票天数与商票订单有效期天数"+days);
		}
		hql.append(" GROUP BY res.orgId");
		List<Map<String,Object>> list = getListMapBySQL(hql.toString(), paramList.toArray());
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordSpDao#getInfoAndOrderById(java.lang.String)
	 */
	public List<Map<String,Object>> getInfoAndOrderById(Integer id){
		List<Object> params = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT d.*,dis.state,dis.price rate,dis.price1 rate1,dis.price2 rate2,dis.dcrd_sp_id,dis.id dtboId,dis.org_id FROM discountrecord_sp d LEFT JOIN distribute_order_sp dis ON d.id=dis.dcrd_sp_id AND dis.state NOT IN(0,-1,-2) WHERE d.id=?");
		params.add(id);
		return Utility.decodeMobile(getListMapBySQL(sql.toString(), params.toArray()));//@ZY EDIT:2016-09-23手机号转码
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordSpDao#getNeedPaidan()
	 */
	public List<Map<String, Object>> getNeedPaidan() {
		StringBuffer sql = new StringBuffer("SELECT disc.id,disc.orderflag,dist.id dtboId,dist.state,dist.is_select FROM discountrecord_sp disc LEFT JOIN distribute_order_sp dist ON disc.id=dist.dcrd_sp_id AND (dist.state NOT IN(-2,-1,0) OR dist.is_select<>1)");
		List<Object> params = new ArrayList<Object>();
		sql.append(" WHERE disc.orderflag=1  AND disc.id NOT IN(");
		sql.append(" SELECT disc.id FROM discountrecord_sp disc LEFT JOIN distribute_order_sp dist ON disc.id=dist.dcrd_sp_id");
		sql.append(" WHERE disc.orderflag=1  AND (dist.state NOT IN(-2,-1,0) OR dist.is_select<>1))");
		return getListMapBySQL(sql.toString(),params.toArray());
	}
	
	@Override
	public List<DiscountrecordSp> getList(String id) {
		String hql = "from discountrecord_sp where 1=1 ";
		List<Object> paramList = new ArrayList<Object>();		
		if (id != null && !"".equals(id)) {
			hql += " and id like ? ";
			paramList.add(Integer.parseInt(id));
		}
		List<DiscountrecordSp> list = getListByHQL(hql, paramList.toArray());
		return DiscountrecordSp.deDiscountrecordSp(list);//@ZY EDIT:2016-09-23手机号转码
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordSpDao#getModelById(java.lang.Integer)
	 */
	public DiscountrecordSp getModelById(Integer id) {
		return get(id);
	}
	
	public List<DiscountrecordSp> getByMemberId(Integer memberId){
		StringBuffer hql = new StringBuffer("from discountrecord_sp where 1=1 ");
		List<Object> paramList = new ArrayList<Object>();		
		hql.append(" and member_id = ? ");
		paramList.add(memberId);
		List<DiscountrecordSp> list = getListByHQL(hql.toString(), paramList.toArray());
		return DiscountrecordSp.deDiscountrecordSp(list);//@ZY EDIT:2016-09-23手机号转码
	}
	
	public List<DiscountrecordSp> getListByHandleState(DiscountrecordSpForm form){
		StringBuffer hql = new StringBuffer("FROM discountrecord_sp WHERE handle_state = 0");
		List<Object> params = new ArrayList<Object>();
		if(form!=null){
			if(form.getBegintime()!=null){
				hql.append(" and createTime > ?");
				params.add(form.getBegintime());
			}
			if(form.getEndtime()!=null){
				hql.append(" and createTime < ?");
				params.add(form.getEndtime());
			}
		}
		
		
		return getListByHQL(hql.toString(), params.toArray());
	}

	@Override
	public List<DiscountrecordSp> getByObj(MemOrderPageRequest mem) {
		StringBuffer hql = new StringBuffer("from discountrecord_sp where (is_valid = 'T' OR is_valid IS NULL) ");
		List<Object> params = new ArrayList<Object>();
		
		if(StringUtils.hasText(mem.getStartDate())){
			hql.append(" and create_time >= ?");
			params.add(mem.getStartDate() + " 00:00:00");
		}
		if(StringUtils.hasText(mem.getEndDate())){
			hql.append(" and create_time <= ?");
			params.add(mem.getEndDate() + " 23:59:59");
		}
		if(mem.getState() != null){
			hql.append(" and orderflag = ?");
			params.add(mem.getState());
		}
		hql.append(" order by create_time desc");
		List<DiscountrecordSp> list = getListByHQL(hql.toString(), params.toArray());	
		
		return DiscountrecordSp.deDiscountrecordSp(list);//@ZWD EDIT:2017-08-01手机号转码
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordSpDao#getPageMapList(com.ry.core.form.MemOrder.MemOrderPageRequest)
	 */
	@Override
	public PageResults<Map<String, Object>> getPageMapList(MemOrderPageRequest mem) {
		StringBuffer hql = new StringBuffer(" FROM (SELECT disc.*,m.mobile,m.username FROM discountrecord_sp disc LEFT JOIN member m ON disc.member_id = m.id where (disc.is_valid = 'T' OR disc.is_valid IS NULL) order by disc.create_time desc)r1 LEFT JOIN (SELECT r.paper_info, r.org_price,r.location,r.fk_id FROM (SELECT rr.* FROM remarks rr ORDER BY rr.id DESC)r GROUP BY r.fk_type ,r.fk_id ORDER BY r.id DESC)r2 ON r1.id = r2.fk_id where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		
		if(StringUtils.hasText(mem.getOrderNumber())){
			hql.append(" AND r1.no = ?");
			params.add(mem.getOrderNumber());
		}
		if(StringUtils.hasText(mem.getTel())){
			hql.append(" AND r1.mobile = ?");
			params.add(Utility.encodeMobile(mem.getTel()));
		}
		if(StringUtils.hasText(mem.getCity())){
			hql.append(" AND r2.location like ?");
			params.add("%"+mem.getCity()+"%");
		}
		if(StringUtils.hasText(mem.getUsername())){
			hql.append(" AND r1.username like ?");
			params.add("%"+mem.getUsername()+"%");
		}
		try {
			if(StringUtils.hasText(mem.getStartDate())){
				hql.append(" and r1.create_time > ?");
				params.add(DateUtil.parser(mem.getStartDate(), DateUtil.FORMART3));
			}
			if(StringUtils.hasText(mem.getEndDate())){
				hql.append(" and r1.create_time < ?");
				params.add(DateUtil.addDays(DateUtil.parser(mem.getEndDate(), DateUtil.FORMART3), 1));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String str = new String("SELECT r1.*,r2.location,r2.org_price,r2.paper_info " + hql.toString());
		String hqlcount = new String(" SELECT COUNT(*) " + hql.toString());
		PageResults<Map<String, Object>> pageResult = findPageMapByFetchedSql(str, hqlcount, mem.currentPage().intValue(), mem.getLength().intValue(), params.toArray());
		return Utility.decodeMobile(pageResult);//@WKX EDIT2016-08-19 手机号转码
	}
}