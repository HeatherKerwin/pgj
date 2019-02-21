package com.ry.core.dao.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.Enum.OrderState;
import com.ry.core.dao.BaseDao;
import com.ry.core.dao.DiscountrecordDao;
import com.ry.core.entity.Discountrecord;
import com.ry.core.entity.DiscountrecordTask.Operator;
import com.ry.core.entity.Systeminfo.ReadState;
import com.ry.core.form.DiscountrecordForm;
import com.ry.core.form.MemOrder.MemOrderPageRequest;
import com.ry.core.util.Constant;
import com.ry.core.util.Utility;
import com.ry.util.DateUtil;
import com.ry.util.page.PageResults;

@Repository
public class DiscountrecordDaoImpl extends BaseDao<Discountrecord, Integer> implements DiscountrecordDao {
	
	String valid_sql = " (is_valid IS NULL OR is_valid='T') ";
	String valid_hql = " (isValid IS NULL OR isValid='T') ";
	
	String valid_and_sql = " (is_valid IS NULL OR is_valid='T') AND ";
	String valid_and_hql = " (isValid IS NULL OR isValid='T') AND ";
	
	@Override
	public void delete(Discountrecord discountrecord) {		
		deleteObject(discountrecord);
	}

	@Override
	public List<Discountrecord> getList(String id) {
		
		String hql = "from discountrecord where" + valid_hql;
		List<Object> paramList = new ArrayList<Object>();		
		if (id != null && !"".equals(id)) {
			hql += " and id like ? ";
			paramList.add(Integer.parseInt(id));
		}
		List<Discountrecord> list = getListByHQL(hql, paramList.toArray());
		return Discountrecord.deDiscountrecord(list);//@WKX EDIT2016-08-19 手机号转码
	}

	@Override
	public void updateDiscountrecord(Discountrecord discountrecord) {
		update(Discountrecord.enDiscountrecord(discountrecord));//@WKX EDIT2016-08-19 手机号转码
	}
	
	@Override
	public void mergeDiscountrecord(Discountrecord discountrecord) {
		merge(Discountrecord.enDiscountrecord(discountrecord));//@WKX EDIT2016-08-19 手机号转码
	}

	@Override
	public Long countbyOrderflag(Integer orderflag1, Integer orderflag2) {
		String hql = "select count(*) from discountrecord WHERE" + valid_hql;
		List<Object> paramList = new ArrayList<Object>();	
		if (orderflag1 != null) {
			hql += " and orderflag like ? ";
			paramList.add(orderflag1);
		}
		if (orderflag2 != null) {
			hql += " or orderflag like ? ";
			paramList.add(orderflag2);
		}
		Long count = countByHql(hql, paramList.toArray());
		return count;
	}

	@Override
	public Long countbyOrdertime(Date begintimed, Date endtimed, Integer orderflag) {
		String hql = "select count(*) from discountrecord WHERE" + valid_and_hql + "ordertime >= ? and ordertime <= ? and orderflag like ? ";		
		Long count = countByHql(hql, new Object[]{begintimed,endtimed,orderflag});
		return count;
	}

	@Override
	public List<Discountrecord> getList(Date begintimed, Date endtimed) {
		String hql = "from discountrecord where" + valid_and_hql + "ordertime >= ? and ordertime <= ? order by id desc ";
		List<Discountrecord> list = getListByHQL(hql, new Object[]{begintimed,endtimed});
		return Discountrecord.deDiscountrecord(list);//@WKX EDIT2016-08-19 手机号转码
	}
	
	@Override
	public PageResults<Discountrecord> findPageModel(String hql, String hqlcount, int pageNo, int pageSize, Object[] values) {		
		
		PageResults<Discountrecord> pageResult = findPageByFetchedHql(hql, hqlcount, pageNo, pageSize, values);
		
		return Discountrecord.deDiscountrecord(pageResult);//@WKX EDIT2016-08-19 手机号转码
	}

	@Override
	public Long countbyMemberId(Integer memberId) {		
		return countByHql("select count(*) from discountrecord where" + valid_and_hql + "memberid like ?", new Object[]{memberId});
	}

	@Override
	public Double allmoneybyMemberId(Integer memberId) {				
		String sql =  "select sum(allmoney) from discountrecord where" + valid_and_sql + "memberid like ?";
		Object obj;
		List<Object> list = getListBySQL(sql, new Object[]{memberId});
		if (list != null && list.size() > 0) {
			obj = list.get(0);																	
			if (obj != null) {		
				return Double.valueOf(obj.toString());
			}
		}
		return 0.0;
	}

	@Override
	public List<Discountrecord> getList(Integer orderflag1, Integer orderflag2,Integer memberid) {
		String hql = "from discountrecord where" + valid_hql;
		List<Object> paramList = new ArrayList<Object>();
		
		if (memberid != null) {
			hql += " and memberid like ? ";
			paramList.add(memberid);
		}
		if (orderflag2 != null) {
			hql += " and (orderflag like ? or orderflag like ?) order by ordertime desc";
			paramList.add(orderflag1);
			paramList.add(orderflag2);
		} else {
			hql += " and orderflag like ? order by ordertime desc";
			paramList.add(orderflag1);
		}		
		List<Discountrecord> list = getListByHQL(hql, paramList.toArray());		
		return Discountrecord.deDiscountrecord(list);//@WKX EDIT2016-08-19 手机号转码
	}

	@Override
	public List<Discountrecord> getList(Integer orderflag, Integer memberid) {
		String hql = "from discountrecord where" + valid_and_hql + "orderflag like ? and memberid like ? order by ordertime desc";
		List<Discountrecord> discountrecords = (List<Discountrecord>) getListByHQL(hql, new Object[]{orderflag, memberid});
		return Discountrecord.deDiscountrecord(discountrecords);//@WKX EDIT2016-08-19 手机号转码
	}
	
	@Override
	public List<Discountrecord> getList1(String id) {
		String hql = "from discountrecord where" + valid_hql;
		List<Object> paramList = new ArrayList<Object>();		
		if (id != null && !"".equals(id)) {
			hql += " and id = " + id  ;
			paramList.add(Integer.parseInt(id));
		}
		List<Discountrecord> list = getListByHQL(hql, paramList.toArray());
		return Discountrecord.deDiscountrecord(list);//@WKX EDIT2016-08-19 手机号转码
	}
	
	@Override
	public Double allmoney( Date begintimed, Date endtimed) {		
	//	return countByHql("select sum(allmoney) from discountrecord where ordertime >= ? and ordertime <= ? and orderflag!=-1 ", new Object[]{begintimed, endtimed});		
		String sql = "select sum(allmoney) from discountrecord where" + valid_and_sql + "ordertime >= ? and ordertime <= ? and orderflag!=-1";
		Object obj;
		List<Object> list = getListBySQL(sql, new Object[]{begintimed, endtimed});
		if (list != null && list.size() > 0) {
			obj = list.get(0);																	
			if (obj != null) {		
				return Double.valueOf(obj.toString());
			}
		}
		return 0.0;
	}

	@Override
	public void addDiscountrecord(Discountrecord discountrecord) {
		save(Discountrecord.enDiscountrecord(discountrecord));//@WKX EDIT2016-08-19 手机号转码
	}

	@Override
	public Double allmoney(Integer salepriceId) {
		List list = getListBySQL("select sum(allmoney) from discountrecord where" + valid_and_sql + "salepriceid like ? ", new Object[]{salepriceId});
		return (Double)list.get(0);
	}

	@Override
	public List<Discountrecord> getList(String id, String memberid) {
		String hql = "from discountrecord where" + valid_hql;
		List<Object> paramList = new ArrayList<Object>();
		if (StringUtils.hasText(id)) {
			hql += " and id like ? " ;
			paramList.add(Integer.parseInt(id));
		}
		if (StringUtils.hasText(memberid)) {
			hql += " and memberid like ? ";
			paramList.add(Integer.parseInt(memberid));
		}
		hql += " order by ordertime desc ";
		
		List<Discountrecord> list = getListByHQL(hql, paramList.toArray());
		return Discountrecord.deDiscountrecord(list);//@WKX EDIT2016-08-19 手机号转码
	}

	@Override
	public List<Discountrecord> getListFlag(String id, String memberid,Integer orderflag) {
		String hql = "from discountrecord where" + valid_hql;
		List<Object> paramList = new ArrayList<Object>();
		if (StringUtils.hasText(id)) {
			hql += " and id = ? " ;
			paramList.add(Integer.parseInt(id));
		}
		if (StringUtils.hasText(memberid)) {
			hql += " and memberid = ? ";
			paramList.add(Integer.parseInt(memberid));
		}
		if (orderflag != null) {
			hql += " and orderflag = ? ";
			paramList.add(orderflag);
		}
		hql += " order by ordertime desc ";
		List<Discountrecord> list = getListByHQL(hql, paramList.toArray());
		return Discountrecord.deDiscountrecord(list);//@WKX EDIT2016-08-19 手机号转码
	}

	@Override
	public List<Discountrecord> getTopDiscountrecode(int i) {
		String hql = "from discountrecord where" + valid_and_hql + "orderflag = 3 order by ordertime desc";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(i);
		List<Discountrecord> dis = query.list();
		return Discountrecord.deDiscountrecord(dis);//@WKX EDIT2016-08-19 手机号转码
	}

	@Override
	public Double countByWeek(Integer memberid) {
		List list = getListBySQL("SELECT sum(allmoney) from discountrecord where" + valid_and_sql + "YEARWEEK(FROM_UNIXTIME(ordertime/1000,'%Y-%m-%d'))=YEARWEEK(now())",new Object[]{});
		return (Double)list.get(0);
		/*Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(1);
		Long amounts = (Long) query.uniqueResult();
		return amounts;*/
	}
	
	
	@Override
	public Double countByWeek1(Integer memberid) {
		List<Object> list = getListBySQL("SELECT sum(allmoney) from discountrecord where" + valid_and_sql + "YEARWEEK(FROM_UNIXTIME(ordertime/1000,'%Y-%m-%d'))=YEARWEEK(now()) and orderFlag = 3 and memberid = ?",new Object[]{memberid});
		return (Double)list.get(0);
	}

	@Override
	public Double countAll(Integer memberid) {
		List<Object> list = getListBySQL("SELECT sum(allmoney) from discountrecord where" + valid_and_sql + "memberid = ?",new Object[]{memberid});
		return (Double)list.get(0);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordDao#getById(java.lang.Integer)
	 */
	public Discountrecord getById(Integer id) {
		Discountrecord disc = get(id);
		return Discountrecord.deDiscountrecord(disc);//@WKX EDIT2016-08-19 手机号转码
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordDao#getByOrdernumber(java.lang.String)
	 */
	public Discountrecord getByOrdernumber(String ordernumber){
		StringBuffer hql = new StringBuffer("from discountrecord where ordernumber = ? ");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(ordernumber);
		List<Discountrecord> list = getListByHQL(hql.toString(), paramList.toArray());	
		return Discountrecord.deDiscountrecord(list.get(0));//@WKX EDIT2016-08-19 手机号转码;
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordDao#getByBnsNoDiscount(java.lang.String)
	 */
	public Discountrecord getByBnsNoDiscount(String bnsno){
		StringBuffer hql = new StringBuffer(" from discountrecord where" + valid_and_hql + "bns_no = ? ");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(bnsno);
		List<Discountrecord> list = getListByHQL(hql.toString(), paramList.toArray());	
		if(list!=null && list.size()>0){
			return Discountrecord.deDiscountrecord(list.get(0));//@WKX EDIT2016-08-19 手机号转码;
		}else{
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordDao#getModelByBnsNoDiscount(java.lang.String)
	 */
	public Discountrecord getModelByBnsNoDiscount(String bnsno){
		StringBuffer hql = new StringBuffer(" from discountrecord where bns_no = ? ");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(bnsno);
		List<Discountrecord> list = getListByHQL(hql.toString(), paramList.toArray());	
		if(list!=null && list.size()>0){
			return list.get(0);//@WKX EDIT2016-08-19 手机号转码;
		}else{
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordDao#getPageList(com.ry.core.form.DiscountrecordForm, java.lang.Integer, java.lang.Integer)
	 */
	public PageResults<Discountrecord> getPageList(DiscountrecordForm df,Integer pageIndex,Integer pageSize) {
		StringBuffer hql = new StringBuffer("from discountrecord where" + valid_hql);
		StringBuffer count = new StringBuffer("select count(*) from discountrecord where" + valid_hql);
		
		List<Object> paramList = new ArrayList<Object>();
		if(df!=null){
			if(df.getStart()!=null){//下单时间
				hql.append(" and ordertime >= ?");
				count.append(" and ordertime >= ?");
				paramList.add(df.getStart());	
			}
			if(df.getEnd()!=null){
				hql.append(" and ordertime <?");
				count.append(" and ordertime <?");
				paramList.add(df.getEnd());
			}
			if(df.getType()!=null){//承兑行类型
				hql.append(" and type2=?");
				count.append(" and type2=?");
				paramList.add(df.getType());
			}
			if(df.getMemberid()!=null && df.getMemberid()>0){//用户主键
				hql.append(" and memberid=?");
				count.append(" and memberid=?");
				paramList.add(df.getMemberid());
			}
			if(df.getOrderflag()!=null){//订单状态[订单状态存在-1、0]
				hql.append(" and orderflag=?");
				count.append(" and orderflag=?");
				paramList.add(df.getOrderflag());
			}else if(df.getState()!=null && df.getState().length>0){//订单状态[可能同时查询多个状态]
				String tag = "";
				for(Integer of:df.getState()){
					if(!"".equals(tag)) tag += ",";
					tag += "?";
					paramList.add(of);
				}
				hql.append(" and orderflag in("+tag+")");
				count.append(" and orderflag in("+tag+")");
			}
		}
		hql.append(" order by ordertime desc");
		PageResults<Discountrecord> page = findPageByFetchedHql(hql.toString(),count.toString(),pageIndex,pageSize,paramList.toArray());
		return Discountrecord.deDiscountrecord(page);//@WKX EDIT2016-08-19 手机号转码
	}

	@Override
	public Double countFinish(Integer memberid) {
		List<Object> list = getListBySQL("SELECT sum(allmoney) from discountrecord where" + valid_and_sql + "orderFlag = 3 and memberid = ?",new Object[]{memberid});
		return (Double)list.get(0);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordDao#getMoneyLastMonth()
	 */
	public Double getMoneyLastMonth() {
		StringBuffer url = new StringBuffer("SELECT sum(allmoney) from discountrecord where" + valid_and_sql + "orderflag = 3");
		List<Object> paramList = new ArrayList<Object>();
		url.append(" and ordertime between ? and ?");
		paramList.add(DateUtil.getPreviousMonthFirst());
		paramList.add(DateUtil.getPreviousMonthEnd());
		List<Object> list = getListBySQL(url.toString(),paramList.toArray());
		if(list!=null && list.size()>0){
			return (Double)list.get(0);
		}else{
			return 0D;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordDao#getMoneyAllFinish()
	 */
	public Double getMoneyAllFinish() {
		List<Object> list = getListBySQL("SELECT sum(allmoney) from discountrecord where" + valid_and_sql + "orderFlag = 3",null);
		if(list!=null && list.size()>0){
			return (Double)list.get(0);
		}else{
			return 0D;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordDao#getList(com.ry.core.form.DiscountrecordForm)
	 */
	public List<Discountrecord> getList(DiscountrecordForm df) {
		StringBuffer hql = new StringBuffer("from discountrecord where" + valid_hql);
		List<Object> paramList = new ArrayList<Object>();
		
		if(df!=null){
			if(df.getStart()!=null){//下单时间
				hql.append(" and ordertime >= ?");
				paramList.add(df.getStart());	
			}
			if(df.getEnd()!=null){
				hql.append(" and ordertime <?");
				paramList.add(df.getEnd());
			}
			if(df.getType()!=null){//承兑行类型
				hql.append(" and type2=?");
				paramList.add(df.getType());
			}
			if(df.getMemberid()!=null && df.getMemberid()>0){//用户主键
				hql.append(" and memberid=?");
				paramList.add(df.getMemberid());
			}
			if(df.getOrderflag()!=null){//订单状态[订单状态存在-1、0]
				hql.append(" and orderflag=?");
				paramList.add(df.getOrderflag());
			}else if(df.getState()!=null && df.getState().length>0){//订单状态[可能同时查询多个状态]
				String tag = "";
				for(Integer of:df.getState()){
					if(!"".equals(tag)) tag += ",";
					tag += "?";
					paramList.add(of);
				}
				hql.append(" and orderflag in("+tag+")");
			}
			if(df.getDepositState()!=null){//押金状态
				hql.append(" and deposit_state=?");
				paramList.add(df.getDepositState());
			}
		}	
		List<Discountrecord> list = getListByHQL(hql.toString(), paramList.toArray());	
		return Discountrecord.deDiscountrecord(list);//@WKX EDIT2016-08-19 手机号转码
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordDao#getFinishByStartAndEnd(java.util.Date, java.util.Date)
	 */
	public List<Map<String,Object>> getFinishByStartAndEnd(Date start,Date end) {
		StringBuffer sql = new StringBuffer("SELECT result.id,result.memberid,result.createTime as createtime,COUNT(id)AS amount FROM (SELECT * FROM (");
		List<Object> params = new ArrayList<Object>();
		sql.append("SELECT dis.id,dis.memberid,task.createTime,CONCAT(memberid,DATE_FORMAT(createTime,'%Y-%m-%d')) AS _f FROM discountrecord_task AS task LEFT JOIN discountrecord AS dis ON dis.id=task.discountrecordId WHERE" + valid_and_sql + "orderflag=3 AND task.operator=?");
		params.add(Operator.FINISH.getValue());
		if(start!=null && end!=null){
			sql.append(" and task.createTime BETWEEN ? and ?");
			params.add(start);
			params.add(end);
		}
		sql.append(")_t  GROUP BY _f)result");
		return getListMapBySQL(sql.toString(), params.toArray());
	}
	
	@Override
	public PageResults<Discountrecord> getPageDisRecord(MemOrderPageRequest mem) {	
		StringBuffer hql = new StringBuffer("from discountrecord where (isValid = 'T' OR isValid IS NULL) ");
		StringBuffer hqlcount = new StringBuffer("select count(*) from discountrecord where (isValid = 'T' OR isValid IS NULL)");
		List<Object> params = new ArrayList<Object>();
		
		if(mem.getDepositState()!=null){
			hql.append(" AND (deposit_state IS NULL OR deposit_state=?)");
			hqlcount.append(" AND (deposit_state IS NULL OR deposit_state=?)");
			params.add(mem.getDepositState());
		}
		if(mem.getStartDate() != null){
			try {
				hql.append(" and ordertime >= ?");
				hqlcount.append(" and ordertime >= ?");
				params.add(DateUtil.parser(mem.getStartDate(), DateUtil.FORMART3));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(mem.getEndDate() != null){
			try {
				hql.append(" and ordertime <= ?");
				hqlcount.append(" and ordertime <= ?");
				params.add(DateUtil.parser(mem.getEndDate(), DateUtil.FORMART3));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(mem.getState() != null){
			if(mem.getState()==0){//无效订单
				hql.append(" and orderflag in (0,-1)");
				hqlcount.append(" and orderflag in (0,-1)");
			}else if(mem.getState()==1){//待支付：订单状态为1，保证金为0
				hql.append(" and orderflag = 1 AND (deposit_state IS NULL OR deposit_state=0)");
				hqlcount.append(" and orderflag = 1 AND (deposit_state IS NULL OR deposit_state=0)");
			}else if(mem.getState()==2){//交易中：订单状态为2,4(交易中：订单状态为1，保证金不为为0)
				hql.append(" and orderflag in (1,2,4) and (deposit_state IS NULL OR deposit_state<>0)");
				hqlcount.append(" and orderflag in (1,2,4) and (deposit_state IS NULL OR deposit_state<>0)");
			}else{
				hql.append(" and orderflag = ?");
				hqlcount.append(" and orderflag = ?");
				params.add(mem.getState());
			}
		}
		hql.append(" order by ordertime desc");
		PageResults<Discountrecord> pageResult = findPageByFetchedHql(hql.toString(), hqlcount.toString(), mem.currentPage().intValue(), mem.getLength().intValue(), params.toArray());
		return Discountrecord.deDiscountrecord(pageResult);//@WKX EDIT2016-08-19 手机号转码
	} 
	
	@Override
	public Discountrecord querybyId(Integer id) {
		Discountrecord disc = get(id);
		return Discountrecord.deDiscountrecord(disc);//@WKX EDIT2016-08-19 手机号转码
	}
	
	public List<Map<String,Object>> getInfoById(Integer id){
		StringBuffer sql = new StringBuffer(" select record.accept_time,record.flaw_ticket,record.trade_model,record.need_todoor,record.memberother,record.ordernumber as ordernumber, mem.username as username, mem.mobile as mobilenum, record.type1 as type1, record.type2 as type2, record.begintime as begintime, record.endtime as endtime, record.place as place, record.allmoney as allmoney, record.membermobile as membermobile, record.membername as membername, record.picpath as picpath, record.orderflag, record.reasondesc, record.city_id,record.cancel_cause,record.source,dist.todoor_price,dist.todoor_time,dist.lostCause from discountrecord record LEFT JOIN member mem on record.memberid = mem.id LEFT JOIN distribute_order dist ON dist.dcrd_id = record.id  ");
		List<Object> params = new ArrayList<Object>();
		if(id != null){
			sql.append(" where record.id = ?");
			params.add(id);
		}
		List<Map<String,Object>> list = getListMapBySQL(sql.toString(), params.toArray());
		return Utility.decodeMobile(list);//@WKX EDIT2016-08-17  手机号转码
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordDao#getPageList(java.lang.Integer, java.lang.Integer, com.ry.core.form.DiscountrecordForm)
	 */
	public PageResults<Map<String,Object>> getPageList(Integer pageIndex,Integer pageSize,DiscountrecordForm form) {
		StringBuffer sql = new StringBuffer("FROM ");
		List<Object> params = new ArrayList<Object>();
		
		sql.append("(SELECT result.*,c.id commentId FROM (SELECT d.bank,d.id,d.type1,d.type2,d.ordertime,d.endtime,d.ordernumber,d.memberid,d.orderflag,d.allmoney,d.deposit,d.deposit_state,d.endorse,d.memberother,dis.org_id,dis.price rate,dis.price1 rate1,dis.price2 rate2,dis.way FROM discountrecord d LEFT JOIN distribute_order dis ON d.id=dis.dcrd_id AND dis.state NOT IN(0,-1,-2) WHERE d.memberid=? AND (d.is_valid<>'F' OR d.is_valid IS NULL)");
		params.add(form.getMemberid());
		if(form!=null && form.getOrderflag()!=null){
			sql.append(" AND d.orderflag=?");
			params.add(form.getOrderflag());
		}else if(form!=null && form.getState()!=null){//APP2.3对应多个状态一起查询
			String tag = "";
			for(Integer of:form.getState()){
				if(!"".equals(tag)) tag += ",";
				tag += "?";
				params.add(of);
			}
			sql.append(" and d.orderflag in("+tag+")");
		}
		if(form!=null && form.getDepositState()!=null){//押金状态
			sql.append(" AND d.deposit_state=? ");
			params.add(form.getDepositState());
		}
		sql.append(")result LEFT JOIN comments c ON result.id=c.dcrd_id AND (c.type_ IS NULL OR c.type_=0)");
		
		//@WKX 2016-05-11 调整过 链接查询结构的 信息（匹配APP2.2角色结构）
		sql.append(")r1 LEFT JOIN(SELECT res.org_id AS id,res.company,res.phone FROM(SELECT * FROM org_info info WHERE info.type_=1 ORDER BY info.id DESC)res GROUP BY res.org_id)r2");
		sql.append(" ON r1.org_id=r2.id");
		
 		if(form!=null && form.getComment()!=null){//APP2.2 新增-评价标示（0已评价,1未评价）
			if(form.getComment()==0){
				sql.append(" where r1.commentId is not null ");
			}else if(form.getComment()==1){
				sql.append(" where r1.commentId is null AND r1.deposit IS NOT NULL");//APP2.2之前的订单不计算待评价（之前没有保证金）
			}
		}
		sql.append(" ORDER BY r1.ordertime DESC");
		String count = "select count(*) "+ sql.toString();
		return findPageMapByFetchedSql("SELECT r1.*,r2.company,r2.phone "+sql.toString(),count,pageIndex,pageSize,params.toArray());
	}
	
	
	/* (non-Javadoc)
	 *  用户根据企业端的订单 左查询 机构端，在左链接查询评价表，左链接org认证表
	 * @see com.ry.core.dao.DiscountrecordDao#getPcPageList(java.lang.Integer, java.lang.Integer, com.ry.core.form.DiscountrecordForm)
	 */
	public PageResults<Map<String,Object>> getPcPageList(Integer pageIndex,Integer pageSize,DiscountrecordForm form) {
		StringBuffer sql = new StringBuffer("FROM ");
		List<Object> params = new ArrayList<Object>();
		
		sql.append("(SELECT result.*,c.id commentId  FROM (SELECT d.need_todoor,d.flaw_ticket,d.has_ticket,d.bank,d.address,d.bns_no,d.membername as dname,d.membermobile,d.picpath,d.id,d.type1,d.type2,d.begintime,d.ordertime,d.latitude lat_a, d.longitude lon_a,d.endtime,d.ordernumber,d.memberid,d.orderflag,d.allmoney,d.deposit,d.deposit_state,d.endorse,d.memberother,dis.org_id,dis.price rate,dis.price1 rate1,dis.jxts,dis.txlx,dis.price2 rate2,dis.latitude lat_b, dis.longitude lon_b,dis.todoor_price,dis.todoor_time,dis.way FROM discountrecord d LEFT JOIN distribute_order dis ON d.id=dis.dcrd_id  AND dis.state NOT IN(0,-1,-2) WHERE d.memberid=?  AND (d.is_valid<>'F' OR d.is_valid IS NULL)");
		params.add(form.getMemberid());
		if(form!=null && form.getOrderflag()!=null){
			sql.append(" AND d.orderflag=?");
			params.add(form.getOrderflag());
		}else if(form!=null && form.getState()!=null){//APP2.3对应多个状态一起查询
			for(Integer of:form.getState()){
				if(of==1){//企业付款了，机构没有接单，交易中。
					sql.append(" AND ( (d.orderflag=? and  d.deposit_state=1)");
					params.add(of);
				}
				if(of==4){//机构接单了，企业有付款和没付款两种。交易中。
					sql.append(" or ( d.orderflag=? AND d.deposit_state=0 ) OR (d.orderflag=4 and d.deposit_state=1) )");
					params.add(of);
				}
			}
		}
		if(form!=null && form.getType2()!=null){//选择类型
			sql.append(" AND d.type2=?");
			params.add(form.getType2());
		}
		if(form!=null && form.getBegintime()!=null){//开始时间
			sql.append(" AND d.begintime >= ?");
			params.add(form.getBegintime());
		}
		if(form!=null && form.getEnd()!=null){//结束时间
			sql.append(" AND d.begintime <= ?");
			params.add(form.getEnd());
		}
		if(form!=null && form.getMinallmoney()!=null){//最小金额
			sql.append(" AND d.allmoney >= ?");
			params.add(form.getMinallmoney());
		}
		if(form!=null && form.getMaxallmoney()!=null){//最大金额
			sql.append(" AND d.allmoney <= ?");
			params.add(form.getMaxallmoney());
		}
		if(form!=null && form.getOrdernumber()!=null){//根据id获取
			sql.append(" AND d.ordernumber = ?");
			params.add(form.getOrdernumber());
		}
		if(form!=null && form.getDepositState()!=null){//押金状态
			sql.append(" AND d.deposit_state=?");
			params.add(form.getDepositState());
		}
		sql.append(")result LEFT JOIN comments c ON result.id=c.dcrd_id AND (c.type_ IS NULL OR c.type_=0)");
		
		//@WKX 2016-05-11 调整过 链接查询结构的 信息（匹配APP2.2角色结构）
		sql.append(")r1 LEFT JOIN(SELECT res.org_id AS id,res.company,res.phone,res.name FROM(SELECT * FROM org_info info WHERE info.type_=1 ORDER BY info.id DESC)res GROUP BY res.org_id)r2");
		sql.append(" ON r1.org_id=r2.id");
		
 		if(form!=null && form.getComment()!=null){//APP2.2 新增-评价标示（0已评价,1未评价）
			if(form.getComment()==0){
				sql.append(" where r1.commentId is not null ");
			}else if(form.getComment()==1){
				sql.append(" where r1.commentId is null AND r1.deposit IS NOT NULL");//APP2.2之前的订单不计算待评价（之前没有保证金）
			}
		}
		sql.append(" ORDER BY r1.ordertime DESC");
		String count = "select count(*) "+ sql.toString();
		return Utility.decodeMobile(findPageMapByFetchedSql("SELECT r1.*,r2.company,r2.phone,r2.name "+sql.toString(),count,pageIndex,pageSize,params.toArray()));
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordDao#getInfoAndOrderById(java.lang.String)
	 */
	public List<Map<String,Object>> getInfoAndOrderById(Integer id){//APP2.2无效订单有三种
		StringBuffer sql = new StringBuffer("SELECT d.*,dis.state,dis.price rate,dis.price1 rate1,dis.price2 rate2,dis.way,dis.dcrd_id,dis.id dtboId,dis.org_id,dis.tzts,dis.txlx,dis.txje FROM discountrecord d LEFT JOIN distribute_order dis ON d.id=dis.dcrd_id AND dis.state NOT IN(0,-1,-2) WHERE d.id=?");
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		List<Map<String,Object>> list = getListMapBySQL(sql.toString(), params.toArray());
		return Utility.decodeMobile(list);//@WKX EDIT2016-08-17  手机号转码
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordDao#getTaskAndInfoById(java.lang.Integer)
	 */
	public List<Map<String,Object>> getTaskAndInfoById(Integer id){
		StringBuffer sql = new StringBuffer("SELECT task.*,dis.ordernumber FROM discountrecord_task task LEFT JOIN discountrecord dis ON task.discountrecordId=dis.id WHERE task.discountrecordId=? ORDER BY task.createTime DESC");
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		return getListMapBySQL(sql.toString(),params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordDao#getInfoAndImgById(java.lang.Integer)
	 */
	public List<Map<String,Object>> getInfoAndImgById(Integer id){//APP2.2无效订单有三种
		StringBuffer sql = new StringBuffer("SELECT d.*,dis.image_path FROM discountrecord d LEFT JOIN distribute_order dis ON d.id=dis.dcrd_id AND dis.state NOT IN(0,-1,-2) WHERE d.id=?");
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		List<Map<String,Object>> list = getListMapBySQL(sql.toString(), params.toArray());
		return Utility.decodeMobile(list);//@WKX EDIT2016-08-17  手机号转码
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordDao#disImg(java.lang.String, java.lang.Long, java.lang.Long)
	 */
	public List<Object> disImg(String orderflag, Date start,Date end) {
		StringBuffer sql = new StringBuffer("select dr.picpath from discountrecord dr where" + valid_sql);
		List<Object> params = new ArrayList<Object>();
		if (!"".equals(orderflag.trim())) {
			sql.append(" and dr.orderflag = ? ");
			params.add(orderflag);
		}
		if(start != null && end != null){
			sql.append(" and dr.ordertime >= ? and dr.ordertime <= ?");
			params.add(start);
			params.add(end);
		}
		List<Object> lists = getListBySQL(sql.toString(), params.toArray());
		return lists;
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordDao#getMoneyByIdAndTime(java.lang.Integer, java.lang.Long, java.lang.Long)
	 */
	public Double getMoneyByIdAndTime(Integer memberId, Date start, Date end) {
		StringBuffer sql = new StringBuffer("select sum(dr.allmoney) as total from distribute_order dbo right join discountrecord dr on dbo.dcrd_id = dr.id where" + valid_sql);
		List<Object> paramsList = new ArrayList<Object>();
		if (memberId != null) {
			sql.append(" and dr.memberid = ?");
			paramsList.add(memberId);
		}
		if (start != null && end != null) {
			sql.append(" and dr.ordertime between ? and ?");
			paramsList.add(start);
			paramsList.add(end);
		}
		sql.append(" and dr.orderflag = 3");
		List<Object> list = getListBySQL(sql.toString(), paramsList.toArray());
		if (list != null && list.size() > 0 && list.get(0) != null) {
			return (Double) list.get(0);
		} else {			
			return 0D;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordDao#getUnReadByMemberId(java.lang.Integer, java.lang.Integer)
	 */
	public List<Map<String,Object>> getUnReadByMemberId(Integer memberId,Integer orderflag) {
		StringBuffer sql = new StringBuffer("SELECT res.* FROM(");
		List<Object> params = new ArrayList<Object>();
		
		sql.append("SELECT dis.deposit,dis.deposit_state,task.* FROM discountrecord dis,discountrecord_task task WHERE dis.id=task.discountrecordId AND" + valid_and_sql + "task.read_state=?");
		params.add(ReadState.UNREAD.getValue());//原生SQL不能比较对象
		if(memberId!=null){
			sql.append(" AND dis.memberid=?");
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
	 * @see com.ry.core.dao.DiscountrecordDao#getByMemberId(java.lang.Integer)
	 */
	public List<Discountrecord> getByMemberId(Integer memberId) {
		StringBuffer hql = new StringBuffer("from discountrecord where memberid=? AND orderflag<>? AND (isValid<>? OR isValid IS NULL)");
		List<Object> params = new ArrayList<Object>();
		
		params.add(memberId);
		params.add(Constant.ORDER_FAILED);//无效订单
		params.add("F");//删除状态
		List<Discountrecord> list = getListByHQL(hql.toString(), params.toArray());
		return Discountrecord.deDiscountrecord(list);//@WKX EDIT2016-08-19 手机号转码
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordDao#getCountRecord(java.util.List, java.lang.Integer, java.lang.Long, java.lang.Long)
	 */
	public List<Map<String, Object>> getCountRecord(List<Object> paramList,Integer orderflag, Date startTime, Date endTime) {
		StringBuffer sql = new StringBuffer("select dr.id,dr.memberid,dr.ordernumber from discountrecord dr inner join member m on dr.memberid = m.id where" + valid_sql);
		List<Object> params = new ArrayList<Object>();
		if (orderflag != null) {
			sql.append(" and dr.orderflag = ? ");
			params.add(orderflag);
		}
		if (startTime != null && endTime != null) {
			sql.append(" and dr.ordertime between ? and ? ");
			params.add(startTime);
			params.add(endTime);
		}
		if (paramList != null && paramList.size() > 0) {
			sql.append("and dr.memberid in (");
			for (int i = 0; i < paramList.size(); i++) {
				if ((i + 1) == paramList.size()) {
					sql.append(" ? ");
				} else {
					sql.append(" ?, ");
				}
			}
			params.addAll(paramList);
			sql.append(")");
		}
		return getListMapBySQL(sql.toString(), params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordDao#getCountAmount(java.lang.Long, java.lang.Integer)
	 */
	public List<Map<String, Object>> getCountAmount(Long time, Integer amount, List<Object> params) {
		StringBuffer sql = new StringBuffer("SELECT m.id,COUNT(dis.id) amount FROM discountrecord dis LEFT JOIN member m ON dis.memberid=m.id WHERE" + valid_sql);
		List<Object> paramList = new ArrayList<Object>();
		
		if (time != null) {
			sql.append(" and dis.ordertime BETWEEN m.regtime AND m.regtime+? ");
			paramList.add(time);
		}
		if (params != null && params.size() > 0) {
			sql.append("and dis.memberid in (");
			for (int i = 0; i < params.size(); i++) {
				if ((i + 1) == params.size()) {
					sql.append(" ? ");
				} else {
					sql.append(" ?, ");
				}
			}
			paramList.addAll(params);
			sql.append(")");
		}
		if (amount != null) {
			sql.append("GROUP BY m.id HAVING amount >= ?");
			paramList.add(amount);
		}
		return getListMapBySQL(sql.toString(), paramList.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordDao#getByNo(java.lang.String)
	 */
	public List<Discountrecord> getByBnsNo(String bnsNo) {
		StringBuffer hql = new StringBuffer("from discountrecord where bnsNo=?");
		List<Object> params = new ArrayList<Object>();
		params.add(bnsNo);
		List<Discountrecord> list = getListByHQL(hql.toString(), params.toArray());
		return Discountrecord.deDiscountrecord(list);//@WKX EDIT2016-08-19 手机号转码
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordDao#getUnCommentsByMemberId(java.lang.Integer)
	 */
	public List<Map<String,Object>> getUnCommentsByMemberId(Integer memberId){
		StringBuffer sql = new StringBuffer("SELECT d.id,c.id commentsId FROM discountrecord d LEFT JOIN comments c ON (d.id=c.dcrd_id and c.type_ = 0 ) WHERE d.orderflag=? AND d.memberid=? AND c.id IS NULL AND d.deposit IS NOT NULL");
		List<Object> params = new ArrayList<Object>();
		params.add(OrderState.COMPLETE.getCode());
		params.add(memberId);
		return getListMapBySQL(sql.toString(),params.toArray());
	}
	
	public List<Map<String, Object>> getAllList(){
		String sql ="select dis.id,dis.type1,dis.type2,dis.ordertime,dis.allmoney from discountrecord as dis WHERE" + valid_sql;
		List<Object> paras = new ArrayList<Object>();
		List<Map<String,Object>> list = getListMapBySQL(sql,paras.toArray());
		return list;
	}
	
	public List<Map<String,Object>> getDeposit(String memberId){
		String sql = "select dis.id,dis.memberid,dis.deposit as qydeposit,dit.txlx,dit.no,dis.allmoney,dit.org_id,dit.deposit as jgdeposit from "
				+ "discountrecord as dis LEFT JOIN distribute_order as dit on dis.id=dit.dcrd_id where dis.memberid=?  and dis.orderflag NOT IN(-2,-1,0,3)";
		List<Object> paras = new ArrayList<Object>();
		paras.add(memberId);
		List<Map<String, Object>> list= getListMapBySQL(sql,paras.toArray());
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordDao#getPcBnsDeposit(java.lang.String)
	 */
	public PageResults<Map<String,Object>> getPcBnsDeposit(Integer pageIndex,Integer pageSize,String memberId){
		StringBuffer sql = new StringBuffer(" from discountrecord as dis LEFT JOIN distribute_order as dit on dis.id=dit.dcrd_id where dis.memberid=?  and dis.orderflag NOT IN(-2,-1,0,3)");
//		String sql = "select dis.id,dis.memberid,dis.deposit as qydeposit,dit.txlx,dit.no,dis.allmoney,dis.endorse,dis.bank,dit.org_id,dit.deposit as jgdeposit from "
//				+ "discountrecord as dis LEFT JOIN distribute_order as dit on dis.id=dit.dcrd_id where dis.memberid=?  and dis.orderflag NOT IN(-2,-1,0,3)";
		List<Object> paras = new ArrayList<Object>();
		paras.add(memberId);
		String count = "select count(*)"+sql.toString();
		PageResults<Map<String,Object>> list= findPageMapByFetchedSql("select dis.id,dis.memberid,dis.deposit as qydeposit,dit.txlx,dis.ordernumber as no,dis.allmoney,dis.endorse,dis.bank,dit.org_id,dit.deposit as jgdeposit "+sql.toString(),count, pageIndex, pageSize,paras.toArray());
		return list;
	}
	
	public List<Map<String,Object>> baoJinListJG(Integer orgId){
		String sql = "select dit.org_id,dit.no,dit.txlx,org.company,org.state as orgstate from distribute_order as dit LEFT JOIN org_info as org "
				+ " on dit.org_id = org.org_id where dit.dcrd_id=?";
		List<Object> paras = new ArrayList<Object>();
		paras.add(orgId);
		List<Map<String, Object>> list= getListMapBySQL(sql,paras.toArray());
		return list;
	}
	
	public List<Map<String,Object>> ifComplete(Date datetime1,Date datetime2,Integer memberid){
		String sql = "select * from discountrecord as dis where dis.orderflag = 3 and dis.ordertime >  ? and dis.ordertime < ?  and dis.memberid = ? ";
		List<Object> paras = new ArrayList<Object>();
		paras.add(datetime1);
		paras.add(datetime2);
		paras.add(memberid);
		List<Map<String,Object>> list = getListMapBySQL(sql.toString(), paras.toArray());
		return Utility.decodeMobile(list);//@WKX EDIT2016-08-17  手机号转码
	}
	
	public List<Map<String,Object>> ifRefused(Date datetime1,Date datetime2,Integer memberid){
		String sql = "select dit.id from discountrecord as dis LEFT JOIN distribute_order as dit on dis.id = dit.dcrd_id "
				+ " where (dis.is_valid IS NULL OR dis.is_valid='T') AND dis.ordertime > ? and dis.ordertime < ? and dit.refusedSign = 2 and dis.memberid = ?";
		List<Object> paras = new ArrayList<Object>();
		paras.add(datetime1);
		paras.add(datetime2);
		paras.add(memberid);
		List<Map<String, Object>> list= getListMapBySQL(sql,paras.toArray());
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordDao#getNeedPaidan()
	 */
	public List<Map<String,Object>> getNeedPaidan(){
		StringBuffer sql = new StringBuffer("SELECT disc.id,disc.orderflag,dist.id dtboId,dist.state FROM discountrecord disc LEFT JOIN distribute_order dist ON disc.id=dist.dcrd_id AND dist.state NOT IN(-2,-1,0)");
		List<Object> params = new ArrayList<Object>();
		sql.append(" WHERE disc.orderflag=1 AND ((disc.deposit IS NULL)OR(disc.deposit_state=1))AND disc.id NOT IN(");
		sql.append(" SELECT disc.id FROM discountrecord disc LEFT JOIN distribute_order dist ON disc.id=dist.dcrd_id");
		sql.append(" WHERE disc.orderflag=1 AND ((disc.deposit IS NULL)OR(disc.deposit_state=1)) AND dist.state NOT IN(-2,-1,0))");
		return getListMapBySQL(sql.toString(),params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordDao#getModelById(java.lang.Integer)
	 */
	public Discountrecord getModelById(Integer id) {
		return get(id);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordDao#getPageMapList(com.ry.core.form.DiscountrecordForm, java.lang.Integer, java.lang.Integer)
	 */
	public PageResults<Map<String, Object>> getPageMapList(MemOrderPageRequest mem, Integer pageNo, Integer pageSize) {
		StringBuffer sql = new StringBuffer(" FROM discountrecord disc LEFT JOIN member m ON disc.memberid = m.id WHERE disc.is_valid ='T' OR disc.is_valid IS NULL");
		List<Object> params = new ArrayList<Object>();
		if(mem!=null){
			if(StringUtils.hasText(mem.getOrderNumber())){
				sql.append(" AND disc.ordernumber = ?");
				params.add(mem.getOrderNumber());
			}
			if(StringUtils.hasText(mem.getTel())){
				sql.append(" AND m.mobile = ?");
				params.add(Utility.encodeMobile(mem.getTel()));
			}
			if(mem.getState() != null){
				if(mem.getState()==0){//无效订单
					sql.append(" and disc.orderflag in (0,-1)");
				}else if(mem.getState()==1){//待支付：订单状态为1，保证金为0
					sql.append(" and disc.orderflag = 1 AND (disc.deposit_state IS NULL OR disc.deposit_state=0)");
				}else if(mem.getState()==2){//交易中：订单状态为2,4(交易中：订单状态为1，保证金不为为0)
					sql.append(" and disc.orderflag in (1,2,4) and (disc.deposit_state IS NULL OR disc.deposit_state<>0)");
				}else{
					sql.append(" and disc.orderflag = ?");
					params.add(mem.getState());
				}
			}
			if(StringUtils.hasText(mem.getHandleState())){
				sql.append(" AND disc.handle_state = ?");
				params.add(mem.getHandleState());
			}
			if(mem.getMaxMoney()!=null){
				sql.append(" AND disc.allmoney <= ?");
				params.add(mem.getMaxMoney());
			}
			if(mem.getMinMoney()!=null){
				sql.append(" AND disc.allmoney >= ?");
				params.add(mem.getMinMoney());
			}
			try {
				if(StringUtils.hasText(mem.getStartDate())){
					sql.append(" and disc.ordertime > ?");
					params.add(DateUtil.parser(mem.getStartDate(), DateUtil.FORMART3));
				}
				if(StringUtils.hasText(mem.getEndDate())){
					sql.append(" and disc.ordertime < ?");
					params.add(DateUtil.addDays(DateUtil.parser(mem.getEndDate(), DateUtil.FORMART3), 1));
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		sql.append(" order by disc.ordertime desc");
		String str = new String(" SELECT disc.*,m.mobile " + sql.toString());
		String count = new String(" SELECT COUNT(*)" + sql.toString());
		PageResults<Map<String, Object>> pageResult = findPageMapByFetchedSql(str, count, pageNo, pageSize, params.toArray());
		return Utility.decodeMobile(pageResult);//@WKX EDIT2016-08-19 手机号转码
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordDao#getPageList(com.ry.core.form.DiscountrecordForm, java.lang.Integer, java.lang.Integer)
	 */
	public PageResults<Map<String, Object>> getPageList(MemOrderPageRequest mem, Integer pageNo, Integer pageSize) {
		StringBuffer sql = new StringBuffer(" FROM discountrecord disc LEFT JOIN member m ON disc.memberid = m.id LEFT JOIN distribute_order dist on (dist.dcrd_id = disc.id and dist.state not in(0,-1,-2) )WHERE disc.is_valid ='T' OR disc.is_valid IS NULL");
		List<Object> params = new ArrayList<Object>();
		if(mem!=null){
			if(StringUtils.hasText(mem.getOrderNumber())){
				sql.append(" AND disc.ordernumber = ?");
				params.add(mem.getOrderNumber());
			}
			if(StringUtils.hasText(mem.getTel())){
				sql.append(" AND m.mobile = ?");
				params.add(Utility.encodeMobile(mem.getTel()));
			}
			if(mem.getState() != null){
				if(mem.getState()==0){//无效订单
					sql.append(" and disc.orderflag in (0,-1)");
				}else if(mem.getState()==1){//待支付：订单状态为1，保证金为0
					sql.append(" and disc.orderflag = 1 AND (disc.deposit_state IS NULL OR disc.deposit_state=0)");
				}else if(mem.getState()==2){//交易中：订单状态为2,4(交易中：订单状态为1，保证金不为为0)
					sql.append(" and disc.orderflag in (1,2,4) and (disc.deposit_state IS NULL OR disc.deposit_state<>0)");
				}else{
					sql.append(" and disc.orderflag = ?");
					params.add(mem.getState());
				}
			}
			if(StringUtils.hasText(mem.getHandleState())){
				sql.append(" AND disc.handle_state = ?");
				params.add(mem.getHandleState());
			}
			if(mem.getMaxMoney()!=null){
				sql.append(" AND disc.allmoney <= ?");
				params.add(mem.getMaxMoney());
			}
			if(mem.getMinMoney()!=null){
				sql.append(" AND disc.allmoney >= ?");
				params.add(mem.getMinMoney());
			}
			try {
				if(StringUtils.hasText(mem.getStartDate())){
					sql.append(" and disc.ordertime > ?");
					params.add(DateUtil.parser(mem.getStartDate(), DateUtil.FORMART3));
				}
				if(StringUtils.hasText(mem.getEndDate())){
					sql.append(" and disc.ordertime < ?");
					params.add(DateUtil.addDays(DateUtil.parser(mem.getEndDate(), DateUtil.FORMART3), 1));
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		sql.append(" and disc.orderflag in (1,2,4) and dist.id is null ");
		sql.append(" order by disc.ordertime desc ");
		String str = new String(" SELECT disc.*,m.mobile" + sql.toString());
		String count = new String(" SELECT COUNT(*)" + sql.toString());
		PageResults<Map<String, Object>> pageResult = findPageMapByFetchedSql(str, count, pageNo, pageSize, params.toArray());
		return Utility.decodeMobile(pageResult);//@WKX EDIT2016-08-19 手机号转码
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordDao#getPageMapListVisit(com.ry.core.form.MemOrder.MemOrderPageRequest, java.lang.Integer, java.lang.Integer)
	 */
	public PageResults<Map<String, Object>> getPageMapListVisit(MemOrderPageRequest mem, Integer pageNo,Integer pageSize) {
		StringBuffer sql = new StringBuffer(" FROM discountrecord disc LEFT JOIN member m ON disc.memberid = m.id WHERE disc.is_valid ='T' OR disc.is_valid IS NULL");
		List<Object> params = new ArrayList<Object>();
		if(mem!=null){
			if(StringUtils.hasText(mem.getOrderNumber())){
				sql.append(" AND disc.ordernumber = ?");
				params.add(mem.getOrderNumber());
			}
			if(StringUtils.hasText(mem.getTel())){
				sql.append(" AND m.mobile = ?");
				params.add(Utility.encodeMobile(mem.getTel()));
			}
			if(mem.getState() != null){
				if(mem.getState()==0){//无效订单
					sql.append(" and disc.orderflag in (0,-1)");
				}else if(mem.getState()==1){//待支付：订单状态为1，保证金为0
					sql.append(" and disc.orderflag = 1 AND (disc.deposit_state IS NULL OR disc.deposit_state=0)");
				}else if(mem.getState()==2){//交易中：订单状态为2,4(交易中：订单状态为1，保证金不为为0)
					sql.append(" and disc.orderflag in (1,2,4) and (disc.deposit_state IS NULL OR disc.deposit_state<>0)");
				}else{
					sql.append(" and disc.orderflag = ?");
					params.add(mem.getState());
				}
			}
			if(StringUtils.hasText(mem.getVisitState())){
				sql.append(" AND disc.visit_state = ?");
				params.add(mem.getVisitState());
			}
			if(mem.getMaxMoney()!=null){
				sql.append(" AND disc.allmoney <= ?");
				params.add(mem.getMaxMoney());
			}
			if(mem.getMinMoney()!=null){
				sql.append(" AND disc.allmoney >= ?");
				params.add(mem.getMinMoney());
			}
			try {
				if(StringUtils.hasText(mem.getStartDate())){
					sql.append(" and disc.ordertime > ?");
					params.add(DateUtil.parser(mem.getStartDate(), DateUtil.FORMART3));
				}
				if(StringUtils.hasText(mem.getEndDate())){
					sql.append(" and disc.ordertime < ?");
					params.add(DateUtil.addDays(DateUtil.parser(mem.getEndDate(), DateUtil.FORMART3), 1));
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		sql.append(" and disc.orderflag in (0,-1,3) order by disc.ordertime desc");
		String str = new String(" SELECT disc.*,m.mobile" + sql.toString());
		String count = new String(" SELECT COUNT(*)" + sql.toString());
		PageResults<Map<String, Object>> pageResult = findPageMapByFetchedSql(str, count, pageNo, pageSize, params.toArray());
		return Utility.decodeMobile(pageResult);//@WKX EDIT2016-08-19 手机号转码
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordDao#getListByHandleState(com.ry.core.form.DiscountrecordForm)
	 */
	public List<Discountrecord> getListByHandleState(DiscountrecordForm form) {
		StringBuffer hql = new StringBuffer("FROM discountrecord WHERE" + valid_and_hql + "handle_state = 0 ");
		List<Object> params = new ArrayList<Object>();
		if(form!=null){
			if(form.getStart()!=null){
				hql.append(" and ordertime > ?");
				params.add(form.getStart());
			}
			if(form.getEnd()!=null){
				hql.append(" and ordertime < ?");
				params.add(form.getEnd());
			}
		}
		return getListByHQL(hql.toString(), params.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordDao#mergeModel(com.ry.core.entity.Discountrecord)
	 */
	public void mergeModel(Discountrecord discountrecord) {
		merge(Discountrecord.enDiscountrecord(discountrecord));//@KHC EDIT2017-01-23 手机号转码
	} 
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordDao#getByRefundState(java.lang.Integer)
	 */
	public List<Discountrecord> getByRefundState(Integer refundState) {
		StringBuffer hql = new StringBuffer("FROM discountrecord WHERE" + valid_and_hql + "refundState=?");
		List<Object> params = new ArrayList<Object>();
		params.add(refundState);
		List<Discountrecord> list = getListByHQL(hql.toString(), params.toArray());
		return Discountrecord.deDiscountrecord(list);
	}

	@Override
	public List<Discountrecord> getByObj(MemOrderPageRequest mem) {
		StringBuffer sql = new StringBuffer("from discountrecord where" + valid_hql);
		List<Object> params = new ArrayList<Object>();
		
		if(mem!=null){
			if(StringUtils.hasText(mem.getOrderNumber())){
				sql.append(" AND ordernumber = ?");
				params.add(mem.getOrderNumber());
			}
			if(StringUtils.hasText(mem.getTel())){
				sql.append(" AND membermobile = ?");
				params.add(Utility.encodeMobile(mem.getTel()));
			}
			if(mem.getState() != null){
				if(mem.getState()==0){//无效订单
					sql.append(" and orderflag in (0,-1)");
				}else if(mem.getState()==1){//待支付：订单状态为1，保证金为0
					sql.append(" and orderflag = 1 AND (deposit_state IS NULL OR deposit_state=0)");
				}else if(mem.getState()==2){//交易中：订单状态为2,4(交易中：订单状态为1，保证金不为为0)
					sql.append(" and orderflag in (1,2,4) and (deposit_state IS NULL OR deposit_state<>0)");
				}else{
					sql.append(" and orderflag = ?");
					params.add(mem.getState());
				}
			}
			if(StringUtils.hasText(mem.getHandleState())){
				sql.append(" AND handle_state = ?");
				params.add(mem.getHandleState());
			}
			if(mem.getMaxMoney()!=null){
				sql.append(" AND allmoney <= ?");
				params.add(mem.getMaxMoney());
			}
			if(mem.getMinMoney()!=null){
				sql.append(" AND allmoney >= ?");
				params.add(mem.getMinMoney());
			}
			try {
				if(StringUtils.hasText(mem.getStartDate())){
					sql.append(" and ordertime > ?");
					params.add(DateUtil.parser(mem.getStartDate(), DateUtil.FORMART3));
				}
				if(StringUtils.hasText(mem.getEndDate())){
					sql.append(" and ordertime < ?");
					params.add(DateUtil.addDays(DateUtil.parser(mem.getEndDate(), DateUtil.FORMART3), 1));
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		List<Discountrecord> list = getListByHQL(sql.toString(), params.toArray());	
		return Discountrecord.deDiscountrecord(list);//@WKX EDIT2016-08-19 手机号转码
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscountrecordDao#getPageMapList(com.ry.core.form.DiscountrecordForm, java.lang.Integer, java.lang.Integer)
	 */
	public PageResults<Map<String, Object>> getReportPageMapList(MemOrderPageRequest mem, Integer pageNo, Integer pageSize) {
		StringBuffer sql = new StringBuffer(" FROM (SELECT disc.*,m.mobile,m.username FROM discountrecord disc LEFT JOIN member m ON disc.memberid = m.id WHERE disc.is_valid ='T' OR disc.is_valid IS NULL ORDER BY disc.ordertime DESC)r1 LEFT JOIN (SELECT r.paper_info, r.org_price,r.location,r.fk_id FROM (SELECT rr.* FROM remarks rr ORDER BY rr.id DESC )r GROUP BY r.fk_type ,r.fk_id ORDER BY r.id DESC)r2 ON r1.id = r2.fk_id where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if(mem!=null){
			if(StringUtils.hasText(mem.getOrderNumber())){
				sql.append(" AND r1.ordernumber = ?");
				params.add(mem.getOrderNumber());
			}
			if(StringUtils.hasText(mem.getTel())){
				sql.append(" AND r1.mobile = ?");
				params.add(Utility.encodeMobile(mem.getTel()));
			}
			if(StringUtils.hasText(mem.getCity())){
				sql.append(" AND r2.location like ?");
				params.add("%"+mem.getCity()+"%");
			}
			if(StringUtils.hasText(mem.getUsername())){
				sql.append(" AND r1.username like ?");
				params.add("%"+mem.getUsername()+"%");
			}
			try {
				if(StringUtils.hasText(mem.getStartDate())){
					sql.append(" and r1.ordertime > ?");
					params.add(DateUtil.parser(mem.getStartDate(), DateUtil.FORMART3));
				}
				if(StringUtils.hasText(mem.getEndDate())){
					sql.append(" and r1.ordertime < ?");
					params.add(DateUtil.addDays(DateUtil.parser(mem.getEndDate(), DateUtil.FORMART3), 1));
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		String str = new String(" SELECT r1.*,r2.location,r2.org_price,r2.paper_info " + sql.toString());
		String count = new String(" SELECT COUNT(*) " + sql.toString());
		PageResults<Map<String, Object>> pageResult = findPageMapByFetchedSql(str, count, pageNo, pageSize, params.toArray());
		return Utility.decodeMobile(pageResult);//@WKX EDIT2016-08-19 手机号转码
	}
}