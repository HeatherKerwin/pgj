package com.ry.core.dao.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.DistributeOrderDao;
import com.ry.core.entity.DistributeOrder;
import com.ry.core.entity.Systeminfo.ReadState;
import com.ry.core.form.DistributeOrderForm;
import com.ry.core.form.org.ReviewOrderRequest;
import com.ry.core.util.Utility;
import com.ry.util.DateUtil;
import com.ry.util.page.PageResults;

@Repository
public class DistributeOrderDaoImpl extends BaseDao<DistributeOrder, Integer> implements DistributeOrderDao {
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderDao#getPageList(java.lang.Integer, java.lang.Integer, com.ry.core.form.DistributeOrderForm)
	 */
	public PageResults<Map<String,Object>> getPageList(Integer pageIndex,Integer pageSize,DistributeOrderForm form) {
		StringBuffer sql = new StringBuffer("FROM");
		List<Object> params = new ArrayList<Object>();
		
		sql.append("(SELECT result.*,c.id commentId FROM (SELECT dis.id,dis.no,dis.deposit,dis.deposit_state,dis.state,dis.price,dis.txlx,di.need_todoor,di.allmoney,di.memberid,di.orderflag,di.membermobile,di.type1,di.type2,di.bank,di.endtime,di.endorse FROM distribute_order dis LEFT JOIN discountrecord di ON dis.dcrd_id=di.id WHERE 1=1 ");
		if(form!=null){
			if(form.getState()!=null){
				sql.append(" AND dis.state=?");
				params.add(form.getState());
			}else if(form.getStates()!=null && form.getStates().length>0){//订单状态[可能同时查询多个状态]
				String tag = "";
				for(Integer of:form.getStates()){
					if(!"".equals(tag)) tag += ",";
					tag += "?";
					params.add(of);
				}
				sql.append(" AND dis.state in("+tag+")");
			}
			if(form.getOrgId()!=null){
				sql.append(" AND dis.org_id=?");
				params.add(form.getOrgId());
			}
			if(form.getDepositState()!=null && form.getDepositState()==1 && form.getVersion()>=2.31F){//临时版本：交易中无保证金（包含之前的订单）
				sql.append(" AND (dis.deposit=0 OR dis.deposit_state=?)");
				params.add(form.getDepositState());
			}else if(form.getDepositState()!=null){//押金状态
				sql.append(" AND dis.deposit_state=?");
				params.add(form.getDepositState());
			}else if(form.getVersion()!=null && form.getVersion()==2.2F){//APP2.2订单不显示待支付
				sql.append(" AND (dis.deposit_state<>0 OR dis.deposit_state IS NULL)");
			}else if(form.getVersion()!=null && form.getVersion()==2.3F){//APP2.3订单列表显示已接单的
				sql.append(" AND (dis.deposit IS NOT NULL OR dis.deposit_state IS NOT NULL)");
			}
			sql.append(" AND dis.deposit IS NOT NULL");//查询所有接单的（订单列表用）
		}
		sql.append(")result LEFT JOIN comments c ON result.id=c.dtbo_id AND (c.type_ IS NULL OR c.type_=0)");
		
		//@WKX 2016-05-11 调整过 链接查询结构的 信息（匹配APP2.2角色结构）
		sql.append(")r1 LEFT JOIN (");
		sql.append("SELECT res.org_id AS id,res.member_id,res.company FROM (SELECT * FROM org_info info WHERE info.type_=0 ORDER BY info.id DESC)res GROUP BY res.member_id");
		sql.append(")r2 ON r1.memberid=r2.member_id");
		
		String count = "select count(*) "+ sql.toString();
		
		PageResults<Map<String,Object>> page = findPageMapByFetchedSql("SELECT r1.id,r1.endtime,r1.deposit,r1.deposit_state,r1.no,r1.allmoney,r1.orderflag,r1.state,r1.price,r1.membermobile,r1.txlx,r1.type1,r1.type2,r1.bank,r1.commentId,r1.endorse,r2.company "+sql.toString(),count,pageIndex,pageSize,params.toArray());
		return Utility.decodeMobile(page);//@WKX EDIT2016-08-17  手机号转码
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderDao#getUnReadByOrgId(java.lang.Integer, java.lang.Integer)
	 */
	public List<Map<String,Object>> getUnReadByOrgId(Integer orgId,Integer state) {
		StringBuffer sql = new StringBuffer("SELECT res.* FROM (");
		List<Object> params = new ArrayList<Object>();
		
		sql.append("SELECT dis.*,task.id taskId,task.create_time createTime FROM distribute_order dis,distribute_order_task task WHERE dis.id=task.dtbo_id AND dis.state=task.state");
		sql.append(" AND task.read_state=?");
		params.add(ReadState.UNREAD.getValue());//原生SQL不能比较对象
		if(orgId!=null){
			sql.append(" AND dis.org_id=?");
			params.add(orgId);
		}
		if(state!=null){
			sql.append(" AND dis.state=?");
			params.add(state);
		}
		sql.append(" ORDER BY task.id DESC)res GROUP BY res.id");
		return getListMapBySQL(sql.toString(),params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderDao#getById(java.lang.Integer)
	 */
	public DistributeOrder getById(Integer id){
		return get(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderDao#getInfoById(java.lang.Integer)
	 */
	public Map<String,Object> getInfoById(Integer id){
		StringBuffer sql = new StringBuffer("SELECT dt.bns_no bnsNo,dt.deposit dtdeposit,dt.deposit_state depositState,dt.pay_way dtpayway,dt.id dtboId,dt.no,dt.state,dt.image_path,dt.price,dt.price1,dt.price2,dt.way,dt.tzts,dt.txlx,dt.txje,dt.create_time,dt.org_id,dt.longitude lon,dt.latitude lat,dt.todoor_price,dt.todoor_time,dc.* FROM distribute_order dt LEFT JOIN discountrecord dc ON dt.dcrd_id=dc.id WHERE dt.id=?");
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		List<Map<String,Object>> list = getListMapBySQL(sql.toString(),params.toArray());
		if(list!=null && list.size()>0){
			return Utility.decodeMobile(list.iterator().next());//@WKX EDIT2016-08-17  手机号转码
		}else{
			return null;
		}
	}
	
	public void saveDistributeOrder(DistributeOrder dis){
		save(dis);
	}
	
	public void updateDistributeOrder(DistributeOrder dis){
		update(dis);
	}
	
	public List<Map<String,Object>> getTaskAndInfoById(Integer id){
		StringBuffer sql = new StringBuffer("SELECT task.*,dis.no FROM distribute_order_task task LEFT JOIN distribute_order dis ON task.dtbo_id=dis.id WHERE dtbo_id=? ORDER BY task.create_time DESC");
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		return getListMapBySQL(sql.toString(),params.toArray());
	}
	
	public List<DistributeOrder> getByDcrdId(Integer dcrdId) {
		//APP2.2 包含：拒绝订单-2（机构端造成）、验票失败-1、无效订单0（企业端造成）
		StringBuffer hql = new StringBuffer("from distribute_order where state NOT IN(0,-1,-2) AND dcrdId=?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(dcrdId);
		return queryByHql(hql.toString(), paramList.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderDao#getLimitAndMoneyForOrg(java.util.Date)
	 */
	public List<Map<String,Object>> getLimitAndMoneyForOrg(Date day) throws ParseException {
		String temp = DateUtil.formart(day, DateUtil.FORMART3);
		Date start = DateUtil.parser(temp + " 00:00:00", DateUtil.FORMART);
		Date end = DateUtil.parser(temp + " 23:59:59", DateUtil.FORMART);
		List<Object> paramList = new ArrayList<Object>();
		
		StringBuffer sql = new StringBuffer("SELECT r1.*,r2.* FROM (");
		sql.append("SELECT o.id,o.type_,l.price FROM org o LEFT JOIN org_limit l ON o.id=l.org_id AND l.day=? WHERE type_=1");
		sql.append(")r1 LEFT JOIN (");
		sql.append("SELECT dis.org_id,SUM(re.allmoney)amount FROM distribute_order dis LEFT JOIN discountrecord re ON dis.dcrd_id=re.id WHERE dis.state NOT IN(0,-1,-2) AND dis.create_time BETWEEN ? AND ? GROUP BY dis.org_id");
		sql.append(")r2 ON r1.id=r2.org_id");
		
		paramList.add(temp);
		paramList.add(start);
		paramList.add(end);
		return getListMapBySQL(sql.toString(), paramList.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderDao#getListIdAndTotal(java.lang.Integer, java.util.Date, java.util.Date)
	 */
	public List<Map<String, Object>> getListIdAndTotal(Integer state, Date startDate, Date endDate) {
		StringBuffer sql = new StringBuffer("select dto.org_id,count(1) dingdan, sum(cs.price) price,count(cs.id) pingjia from distribute_order dto left join comments cs on dto.dcrd_id = cs.dtbo_id where 1=1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (state != null) {
			sql.append(" and dto.state = ? ");
			paramList.add(state);
		}
		if (startDate != null && endDate != null) {
			sql.append(" and dto.create_time between ? and ? ");
			paramList.add(startDate);
			paramList.add(endDate);
		}
		sql.append(" group by dto.org_id");
		return getListMapBySQL(sql.toString(), paramList.toArray());
	}
	
	@Override
	public Long countbyOrdertime(Integer org_id, Long ordertime1, Long ordertime2, Integer orderflag) {
		String hql = "SELECT COUNT(*) FROM distribute_order WHERE org_id = ? AND create_time >= ? AND create_time <= ? AND state LIKE ?";		
		Long count = countByHql(hql, new Object[]{org_id,ordertime1,ordertime2,orderflag});
		return count;
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderDao#getLimitAndMoneyByOrgId(java.lang.Integer, java.util.Date)
	 */
	public List<Map<String,Object>> getLimitAndMoneyByOrgId(Integer orgId,Date day) throws ParseException {
		String temp = DateUtil.formart(day, DateUtil.FORMART3);
		Date start = DateUtil.parser(temp + " 00:00:00", DateUtil.FORMART);
		Date end = DateUtil.parser(temp + " 23:59:59", DateUtil.FORMART);
		List<Object> paramList = new ArrayList<Object>();
		
		StringBuffer sql = new StringBuffer("SELECT r1.*,r2.amount FROM (");
		sql.append("SELECT * from org_limit l WHERE l.day=? AND org_id=?");
		sql.append(")r1 LEFT JOIN (");
		sql.append("SELECT dis.org_id,SUM(re.allmoney)amount FROM distribute_order dis LEFT JOIN discountrecord re ON dis.dcrd_id=re.id WHERE dis.state NOT IN(0,-1,-2) AND dis.create_time BETWEEN ? AND ? ");
		sql.append(")r2 ON r1.org_id=r2.org_id");
		
		paramList.add(temp); 
		paramList.add(orgId);
		paramList.add(start);
		paramList.add(end);
		return getListMapBySQL(sql.toString(), paramList.toArray());
	}
	
	public List<Map<String,Object>> getSuccessList(String date1,String date2){
		String sql = "select dio.org_id,count(*) as num from distribute_order as dio where state = 3 and dio.create_time between ? and ?  GROUP BY dio.org_id";
		List<Object> paras = new ArrayList<Object>();
		paras.add(date1);
		paras.add(date2);
		List<Map<String,Object>> list = getListMapBySQL(sql,paras.toArray());
		return list;
	}
	
	public List<Map<String,Object>> getRefuseList(String date1,String date2){
		String sql = "select dio.org_id,count(*) as num from distribute_order as dio where dio.state=-2 "
				+ "	and dio.create_time between ? and ? GROUP BY dio.org_id";
		List<Object> paras = new ArrayList<Object>();
		paras.add(date1);
		paras.add(date2);
		List<Map<String,Object>> list = getListMapBySQL(sql,paras.toArray());
		return list;
	}
	
	public List<Map<String,Object>> getSuccessTimeList(String date1,String date2){
		String sql ="select dis.id,dis.org_id,count(*) from distribute_order as dis,distribute_order_task as dit where dis.id = dit.dtbo_id and dis.state = 3 "
				+ " and dis.create_time between ? and ? GROUP BY org_id";
		List<Object> paras = new ArrayList<Object>();
		paras.add(date1);
		paras.add(date2);
		List<Map<String,Object>> list = getListMapBySQL(sql,paras.toArray());
		return list;
	}
	
	public List<Map<String,Object>> getDeposit(Integer org_id){
		String sql ="select dit.org_id,dit.no,dit.txlx,dis.allmoney,dis.deposit as qydeposit,dit.deposit as jgdeposit "
				+ "from distribute_order as dit LEFT JOIN discountrecord as dis on dit.dcrd_id = dis.id where dit.org_id = ? and dit.state NOT IN(-2,0,3)";
		List<Object> paras = new ArrayList<Object>();
		paras.add(org_id);
		List<Map<String,Object>> list = getListMapBySQL(sql,paras.toArray());
		return list;
	}
	
	public PageResults<Map<String,Object>>  getPcOrgDeposit(Integer pageIndex,Integer pageSize,Integer org_id){
		StringBuffer sql = new StringBuffer(" from distribute_order as dit LEFT JOIN discountrecord as dis on dit.dcrd_id = dis.id where dit.org_id = ? and dit.state NOT IN(-2,0,3)");
		List<Object> paras = new ArrayList<Object>();
		paras.add(org_id);
		String count = "select count(*)"+sql.toString();
		PageResults<Map<String,Object>> list = findPageMapByFetchedSql("select dit.org_id,dit.no,dit.txlx,dis.allmoney,dis.bank,dis.endorse,dis.deposit as jgdeposit,dit.deposit as qydeposit "+sql.toString(),count,pageIndex,pageSize,paras.toArray());
		return list;
	}
	
	public List<Map<String,Object>> getordthism(String date1,String date2,Integer orgid){
		String sql ="SELECT dis.id,dis.org_id from distribute_order as dis where dis.state = 3 and dis.create_time between ? and ? "
				+ " and dis.org_id = ?";
		List<Object> paras = new ArrayList<Object>();
		paras.add(date1);
		paras.add(date2);
		paras.add(orgid);
		List<Map<String,Object>> list = getListMapBySQL(sql,paras.toArray());
		return list; 
	}

	@Override
	public List<Map<String, Object>> getAssignedByOrgId(Integer org_id,Float version) {
		StringBuffer sql = new StringBuffer("SELECT d.*,dcr.type1,dcr.type2,dcr.bank,dcr.allmoney,dcr.flaw_ticket,dcr.endtime,dcr.begintime,dcr.endorse,dcr.need_todoor,dcr.deposit AS deposit_qy,dcr.memberother,dcr.ordernumber FROM distribute_order d,discountrecord dcr WHERE d.state = '1' AND d.dcrd_id = dcr.id AND d.org_id = ? ");
		List<Object> paras = new ArrayList<Object>();
		paras.add(org_id);
		sql.append(" AND d.deposit IS NULL");
		sql.append(" and d.create_time BETWEEN ? and ?");

		Calendar calendar = Calendar.getInstance ();//显示待接单（10分钟内的有效）
        Date e = calendar.getTime();
        calendar.add (Calendar.SECOND, -600);
        Date s = calendar.getTime();
		
		paras.add(s);
		paras.add(e);
		List<Map<String,Object>> list = getListMapBySQL(sql.toString(),paras.toArray());
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderDao#getByDcrdIdAndOrgId(java.lang.Integer, java.lang.Integer)
	 */
	public List<DistributeOrder> getByDcrdIdAndOrgId(Integer dcrdId,Integer orgId) {
		StringBuffer hql = new StringBuffer("from distribute_order where dcrdId=? AND orgId=?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(dcrdId);
		paramList.add(orgId);
		return queryByHql(hql.toString(), paramList.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderDao#getOverrunByCreateTime(java.util.Date)
	 */
	public List<DistributeOrder> getOverrunByCreateTime(Date date) {
		StringBuffer hql = new StringBuffer("FROM distribute_order dist WHERE dist.state=1 AND ((dist.deposit IS NULL)OR(dist.depositState=0)) AND dist.createTime<?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(date);
		return queryByHql(hql.toString(), paramList.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderDao#getByOrgIdAndTime(java.lang.Integer, java.lang.String, java.lang.String, java.lang.Integer)
	 */
	public List<Map<String, Object>> getByOrgIdAndTime(Integer orgId, String startDate, String endDate, Integer state) {
		StringBuffer sql = new StringBuffer("select dit.org_id,dit.no,dit.txlx,dis.allmoney,dis.deposit qydeposit,dit.deposit jgdeposit from distribute_order dit LEFT JOIN discountrecord dis on dit.dcrd_id = dis.id where 1=1");
		List<Object> paramList = new ArrayList<Object>();
		if (orgId != null) {
			sql.append(" and dit.org_id = ?");
			paramList.add(orgId);
		}
		if (state != null) {
			sql.append(" and dit.state = ?");
			paramList.add(state);
		}
		if (!"".equals(startDate) && !"".equals(endDate)) {
			sql.append(" and dit.create_time between ? and ?");
			paramList.add(startDate);
			paramList.add(endDate);
		}
		return getListMapBySQL(sql.toString(), paramList.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderDao#getListByOrgId(java.lang.Integer)
	 */
	public List<Map<String, Object>> getListByOrgId(Integer orgId) {
		StringBuffer sql = new StringBuffer("SELECT dist.* FROM distribute_order dist LEFT JOIN discountrecord disc ON dist.dcrd_id = disc.id WHERE dist.state not IN(-2,0,3) AND dist.org_id =?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(orgId);
		sql.append(" GROUP BY dist.id");
		return getListMapBySQL(sql.toString(), paramList.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderDao#getOrderPageList(java.lang.Integer, java.lang.Integer, com.ry.core.form.DistributeOrderForm)
	 */
	public PageResults<DistributeOrder> getOrderPageList(ReviewOrderRequest req) {
		StringBuffer sql = new StringBuffer("SELECT * FROM distribute_order WHERE 1=1 ");
		StringBuffer count = new StringBuffer("SELECT COUNT(*) FROM distribute_order WHERE 1=1 ");
		List<Object> paramList = new ArrayList<Object>();
		if(req!=null){
			if(StringUtils.hasText(req.getStartDate())){
				try {
					sql.append(" and create_time >=?");
					count.append(" and create_time >=?");
					paramList.add(DateUtil.parser(req.getStartDate(), DateUtil.FORMART3));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if(StringUtils.hasText(req.getEndDate())){
				try {
					sql.append(" and create_time <=?");
					count.append(" and create_time <=?");
					paramList.add(DateUtil.parser(req.getEndDate(), DateUtil.FORMART3));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if(StringUtils.hasText(req.getState())){
				int state = Integer.parseInt(req.getState());
				if(state==0){//无效订单（-2,-1,0）
					sql.append(" and state in (-2,-1,0)");
					count.append(" and state in (-2,-1,0)");
				}else if(state==1){//待支付（订单状态为1，保证金状态为0）
					sql.append(" and state =1 AND (deposit_state IS NULL OR deposit_state=0)");
					count.append(" and state =1 AND(deposit_state IS NULL OR deposit_state=0)");
				}else if(state==2){//交易中（订单状态为1,2,4,5，保证金状态不为0）
					sql.append(" and state in (1,2,4,5) and (deposit_state IS NULL OR deposit_state<>0)");
					count.append(" and state in (1,2,4,5) and (deposit_state IS NULL OR deposit_state<>0)");
				}else{
					sql.append(" and state = ?");
					count.append(" and state = ?");
					paramList.add(state);
				}
			}
		}
		sql.append(" order by create_time desc");
		return findPageByFetchedSql(sql.toString(), count.toString(), req.currentPage().intValue(), req.getLength().intValue(), paramList.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderDao#getOrderPageList(java.lang.Integer, java.lang.Integer, com.ry.core.form.DistributeOrderForm)
	 */
	public PageResults<Map<String, Object>> getOrderMemberPageList(ReviewOrderRequest req) {
		StringBuffer sql = new StringBuffer("SELECT r1.* ,m.mobile from (SELECT diso.* ,o.member_id  as memberid from (SELECT * FROM distribute_order order by create_time desc)diso LEFT JOIN org o ON diso.org_id = o.id)r1  LEFT JOIN member m ON r1.memberid = m.id where 1=1 ");
		StringBuffer count = new StringBuffer("SELECT COUNT(r1.id) from (SELECT diso.* ,o.member_id  as memberid from (SELECT * FROM distribute_order order by create_time desc)diso LEFT JOIN org o ON diso.org_id = o.id)r1  LEFT JOIN member m ON r1.memberid = m.id WHERE 1=1 ");
		List<Object> paramList = new ArrayList<Object>();
		if(req!=null){
			if(StringUtils.hasText(req.getStartDate())){
				try {
					sql.append(" and r1.create_time >=?");
					count.append(" and r1.create_time >=?");
					paramList.add(DateUtil.parser(req.getStartDate(), DateUtil.FORMART3));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if(StringUtils.hasText(req.getEndDate())){
				try {
					sql.append(" and r1.create_time <=?");
					count.append(" and r1.create_time <=?");
					paramList.add(DateUtil.parser(req.getEndDate(), DateUtil.FORMART3));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if(StringUtils.hasText(req.getState())){
				int state = Integer.parseInt(req.getState());
				if(state==0){//无效订单（-2,-1,0）
					sql.append(" and r1.state in (-2,-1,0)");
					count.append(" and r1.state in (-2,-1,0)");
				}else if(state==1){//待支付（订单状态为1，保证金状态为0）
					sql.append(" and r1.state =1 AND (r1.deposit_state IS NULL OR r1.deposit_state=0)");
					count.append(" and r1.state =1 AND(r1.deposit_state IS NULL OR r1.deposit_state=0)");
				}else if(state==2){//交易中（订单状态为1,2,4,5，保证金状态不为0）
					sql.append(" and r1.state in (1,2,4,5) and (r1.deposit_state IS NULL OR r1.deposit_state<>0)");
					count.append(" and r1.state in (1,2,4,5) and (r1.deposit_state IS NULL OR r1.deposit_state<>0)");
				}else{
					sql.append(" and r1.state = ?");
					count.append(" and r1.state = ?");
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
	 * @see com.ry.core.dao.DistributeOrderDao#getPageList(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public PageResults<DistributeOrder> getPageList(Integer discId,Integer pageIndex,Integer pageSize) {
		StringBuffer sql = new StringBuffer("SELECT * FROM distribute_order WHERE dcrd_id=? ");
		StringBuffer count = new StringBuffer("SELECT COUNT(*) FROM distribute_order WHERE dcrd_id=? ");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(discId);
		return findPageByFetchedSql(sql.toString(), count.toString(), pageIndex, pageSize, paramList.toArray());
	}
	
	public DistributeOrder getByBnsno(String bnsNo){
		StringBuffer hql = new StringBuffer("from distribute_order where bnsNo=? ");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(bnsNo);
		List<DistributeOrder> list = queryByHql(hql.toString(), paramList.toArray());
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderDao#getPageListPC(java.lang.Integer, java.lang.Integer, com.ry.core.form.DistributeOrderForm)
	 */
	public PageResults<Map<String, Object>> getPageListPC(Integer pageIndex, Integer pageSize,DistributeOrderForm form) throws Exception {
		StringBuffer sql = new StringBuffer("SELECT r1.*,r2.company FROM (SELECT disc.allmoney,disc.memberid,disc.membermobile,disc.membername,disc.memberother,disc.type1,disc.type2,disc.bank,disc.flaw_ticket,disc.need_todoor,disc.endorse,disc.begintime,disc.endtime,dist.* FROM distribute_order dist LEFT JOIN discountrecord disc ON dist.dcrd_id = disc.id WHERE 1=1 AND (dist.deposit IS NOT NULL AND dist.deposit_state IS NOT NULL) AND dist.org_id=?");
		List<Object> params = new ArrayList<Object>();
		params.add(form.getOrgId());
		if(form!=null){
			if(form.getState()!=null){
				sql.append(" AND dist.state=?");
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
			if(form.getDepositState()!=null && form.getDepositState()==1){//押金状态
				sql.append(" AND (dist.deposit=0 OR dist.deposit_state=?)");
				params.add(form.getDepositState());
			}else if(form.getDepositState()!=null){//押金状态
				sql.append(" AND dist.deposit_state=?");
				params.add(form.getDepositState());
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
			if(form.getType()!=null){
				sql.append(" AND disc.type2 =?");
				params.add(form.getType());
			}
		}
		sql.append(" )r1 LEFT JOIN (SELECT res.org_id AS id,res.member_id,res.company FROM (SELECT * FROM org_info info WHERE info.type_= 0 ORDER BY info.id DESC)res GROUP BY res.member_id)r2 ON r1.memberid=r2.member_id");
		String count = "SELECT COUNT(*) FROM("+sql.toString()+")res";
		PageResults<Map<String,Object>> page = findPageMapByFetchedSql(sql.toString(),count,pageIndex,pageSize,params.toArray());
		return Utility.decodeMobile(page);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderDao#getByNo(java.lang.String)
	 */
	public DistributeOrder getByNo(String no) {
		StringBuffer hql = new StringBuffer("FROM distribute_order WHERE no = ?");
		List<Object> params = new ArrayList<Object>();
		params.add(no);
		List<DistributeOrder> list = queryByHql(hql.toString(), params.toArray());
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderDao#getByRefundState(java.lang.Integer)
	 */
	public List<DistributeOrder> getByRefundState(Integer refundState) {
		StringBuffer hql = new StringBuffer("from distribute_order where refundState=?");
		List<Object> params = new ArrayList<Object>();
		params.add(refundState);
		return getListByHQL(hql.toString(), params.toArray());
	}
}