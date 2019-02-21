package com.ry.core.dao.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.AccountrecordDao;
import com.ry.core.dao.BaseDao;
import com.ry.core.entity.Accountrecord;
import com.ry.core.form.AccountrecordForm;
import com.ry.util.DateUtil;
import com.ry.util.page.PageResults;

@Repository
public class AccountrecordDaoImpl extends BaseDao<Accountrecord, Integer> implements AccountrecordDao {

	@Override
	public List<Accountrecord> getList(Integer memberId, String day) {
		List paramList = new ArrayList();
		paramList.add(memberId);
		List<Accountrecord> accountrecord = (List<Accountrecord>) getListByHQL("from accountrecord where memberid like ? and (statue = 1 OR statue IS NULL) and publishtime like '%"+day+"%'", paramList.toArray());
		return accountrecord;
	}

	@Override
	public Integer addAccountrecord(Accountrecord accountrecord) {
		accountrecord.setStatue("1");
		return save(accountrecord);
		
	}

	@Override
	public List<Double> allprice(String memberid, String publishtime) {
		
		String sql = "SELECT ROUND((sum(allprice)/(sum(allprice)+sum(tiexianlixi))) ,2)  as p from accountrecord where memberid = ? and publishtime > ? and (statue = 1 OR statue IS NULL)";
		List<Double> list = getSession().createSQLQuery(sql)			
				.setParameter(0, memberid)
				.setParameter(1, publishtime).list();
		
		return list;
	}

	@Override
	public List<Double> tiexianlixi(String memberid, String publishtime) {
		String hql = "SELECT ROUND((sum(tiexianlixi)/(sum(allprice)+sum(tiexianlixi))) ,2)  as p from accountrecord where memberid = ? and publishtime > ? and (statue = 1 OR statue IS NULL)";
		List<Double> list = getSession().createSQLQuery(hql)				
				.setParameter(0, memberid)
				.setParameter(1, publishtime).list();		
		
		return list;
	}

	@Override
	public Double sallprice(String memberid, String publishtime, List paramList) {
							
		String hql = "select IFNULL((SELECT SUM(allprice) from accountrecord where memberid = ? and (statue = 1 OR statue IS NULL) and publishtime > ? and type1 in (:typelist)),0) ";
		List dlist = getSession().createSQLQuery(hql)				
				.setParameter(0, memberid)
				.setParameter(1, publishtime)				
				.setParameterList("typelist", paramList).list();
		
		if (dlist != null && dlist.size() >0) {
			return Double.parseDouble(dlist.get(0).toString());
		}
		
		return 0.0;
		
	}

	@Override
	public Accountrecord getAccountrecord(Integer id) {
		List<Accountrecord> accs = getListByHQL("from accountrecord where (statue = 1 OR statue IS NULL) and id = ?", new Object[]{id});
		if(accs != null && accs.size() > 0){
			return accs.get(0);
		}
		return null;
		//return get(id);
	}

	@Override
	public void delete(Accountrecord accountrecord) {
		
		delete(accountrecord.getId());
	}
	
	@Override
	public void updateAccountrecord(Accountrecord accountrecord) {
		
		update(accountrecord);
	}

	@Override
	public BigDecimal countPriceByTypeAndMonth(String type) {
		String hql = "select sum(allprice) from accountrecord where date_format(tiexiandate,'%Y-%m')=date_format(now(),'%Y-%m')  and (statue = 1 OR statue IS NULL) and isdiscount"+"="+type;
		BigDecimal count =  (BigDecimal)getSession().createSQLQuery(hql).uniqueResult();
		return count;
	}
	@Override
	public BigDecimal countPriceByTypeAndWeek(String type) {
		String hql = "select sum(allprice) from accountrecord where YEARWEEK(date_format(tiexiandate,'%Y-%m-%d')) = YEARWEEK(now()) and (statue = 1 OR statue IS NULL) and isdiscount"+"="+type;
		BigDecimal count =  (BigDecimal)getSession().createSQLQuery(hql).uniqueResult();
		return count;
	}
	@Override
	public BigDecimal countPriceByTypeAday(String type) {
		String hql = "select sum(allprice) from accountrecord where date_format(tiexiandate,'%Y-%m-%d')=date_format(now(),'%Y-%m-%d') and (statue = 1 OR statue IS NULL) and isdiscount"+"="+type;
		BigDecimal count =  (BigDecimal)getSession().createSQLQuery(hql).uniqueResult();
		return count;
	}

	public List<Map<String,Object>> countPriceByDayAndMonth(Integer memberid,Integer belong) {
		String hql = "SELECT new Map ( a.isTiexian AS tiexian, sum(allprice) AS monthNum, sum( CASE WHEN DATE_FORMAT( IFNULL(a.createTime, a.tiexiandate), '%Y-%m-%d' ) = DATE_FORMAT(now(), '%Y-%m-%d') THEN allprice ELSE 0 END ) AS dayNum ) FROM accountrecord a WHERE a.memberid = " + memberid + "AND belong="+belong+" AND (statue = 1 OR statue IS NULL) AND YEAR ( IFNULL(a.createTime, a.tiexiandate)) = YEAR (NOW()) AND MONTH ( IFNULL(a.createTime, a.tiexiandate)) = MONTH (now()) GROUP BY a.isTiexian";
		return getSession().createQuery(hql).list();
	}

	@Override
	public List<Map<String,Object>> countPriceByWeek(Integer memberid) {
		String hql = "select new Map(a.isTiexian as tiexian,sum(allprice) as weekNum) from accountrecord a where  a.memberid = "+memberid+" and (statue = 1 OR statue IS NULL) and YEARWEEK(date_format(tiexiandate,'%Y-%m-%d')) = YEARWEEK(now()) group by a.isTiexian";
		return getSession().createQuery(hql).list();
	}

	@Override
	public List<Map<String,Object>> countPrice(Integer memberid,Integer belong, Date begin, Date end){
		String hql = "select a.isTiexian as tiexian,sum(allprice) as weekNum from accountrecord a where a.memberid = ? AND a.belong=? and (statue = 1 OR statue IS NULL) and date_format(IFNULL(createTime, tiexiandate),'%Y-%m-%d') between date_format(?,'%Y-%m-%d') and date_format(?,'%Y-%m-%d') group by a.isTiexian";
		return getListMapBySQL(hql, new Object[]{memberid,belong,begin,end});
	}
	
	@Override
	public List<Accountrecord> findDiscountAday(Integer memberid,String day) {
		StringBuilder hql = new StringBuilder("from accountrecord a where 1=1 and (statue = 1 OR statue IS NULL) ");
		List paramList = new ArrayList();
		if(day !=null){
			hql.append("and DATE_FORMAT(IFNULL(a.createTime, a.tiexiandate),'%Y-%m-%d')=DATE_FORMAT(?,'%Y-%m-%d')");
			paramList.add(day);
		}
		if(memberid !=null){
			hql.append("and memberid = ? ");
			paramList.add(memberid);
		}
		return getListByHQL(hql.toString(), paramList.toArray());
	}
	@Override
	public List<Accountrecord> findDiscountByWeek(String type) {
		String hql = "select * from accountrecord a where DATE_FORMAT(a.tiexiandate,'%Y-%m-%d')=DATE_FORMAT(now(),'%Y-%m-%d') and (statue = 1 OR statue IS NULL)";
		return getSession().createQuery(hql).list();
	}
	@Override
	public List<Accountrecord> findDiscountByMonth(String type) {
		String hql = "select * from accountrecord a where DATE_FORMAT(a.tiexiandate,'%Y-%m-%d')=DATE_FORMAT(now(),'%Y-%m-%d') and (statue = 1 OR statue IS NULL)";
		return getSession().createQuery(hql).list();
	}


	@Override
	public List<Accountrecord> findByTwoDates(Integer memberid,String start,String end) {
		StringBuilder hql = new StringBuilder("from accountrecord a where 1=1 and (statue = 1 OR statue IS NULL) ");
		List paramList = new ArrayList();
		if((start !=null && end !=null )){
			hql.append("and DATE_FORMAT(a.tiexiandate,'%Y-%m-%d') BETWEEN ? AND ?");
			paramList.add(start);
			paramList.add(end);
		}
		if(memberid !=null){
			hql.append("and memberid = ? ");
			paramList.add(memberid);
		}
		return getListByHQL(hql.toString(), paramList.toArray());
	}

	@Override
	public List<Map<String,Object>> countPiaoju(String begin, String end, String type,Integer memberid) {
		StringBuilder hql = new StringBuilder("select count(*) as total,type1 as type from accountrecord a where 1=1 and (statue = 1 OR statue IS NULL) ");
		List paramList = new ArrayList();
		if(memberid != null){
			hql.append("and memberid = ?");
			paramList.add(memberid);
		}
		if(begin!=null && end != null){
			hql.append(" and tiexiandate between ? and ?");
			try {
				paramList.add(DateUtil.parser(begin, DateUtil.FORMART3));
				paramList.add(DateUtil.parser(end, DateUtil.FORMART3));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(type != null){
			hql.append(" and istiexian = ?");
			paramList.add(type);
		}
		hql.append(" GROUP BY type1"); 
		return getListMapBySQL(hql.toString(), paramList.toArray());
	}
	
	

	@Override
	public List<Accountrecord> findAccountrecordByDaoqidate() {
		Date date = new Date();
		String hql = "from accountrecord where (statue = 1 OR statue IS NULL) and istiexian = 0 and DATE_FORMAT(daoqidate,'%Y-%m-%d') =  DATE_FORMAT(?,'%Y-%m-%d')";
		return getListByHQL(hql.toString(), new Object[]{date});
	}

	@Override
	public List<Accountrecord> findByCustomizedParam(AccountrecordForm form) {
		StringBuilder hql = new StringBuilder("from accountrecord a where 1=1 and (statue = 1 OR statue IS NULL) ");
		List paramList = new ArrayList();
		if(form != null){
			if(form.getDate() != null){
				hql.append("and DATE_FORMAT(a.tiexiandate,'%Y-%m-%d') = "+form.getDate());
				paramList.add(form.getDate());
			}else if(form.getType() != null){
				hql.append("and a.istiexian = "+form.getType());
				paramList.add(form.getType());
			}
		}
		return getSession().createQuery(hql.toString()).list();
	}

	@Override
	public List<Accountrecord> getDiscount(Integer memberId, String type,
			String start, String end) {
		StringBuilder hql = new StringBuilder("from accountrecord a where 1=1 and (statue = 1 OR statue IS NULL) ");
		List<Object> paramList = new ArrayList<Object>();
		if(memberId != null){
			hql.append("and memberid = ?");
			paramList.add(memberId);
		}
		if(start!=null && end != null){
			hql.append(" and DATE_FORMAT( IFNULL(createTime, tiexiandate), '%Y-%m-%d' ) between DATE_FORMAT(?,'%Y-%m-%d') and DATE_FORMAT(?,'%Y-%m-%d')");
			try {
				paramList.add(DateUtil.parser(start, DateUtil.FORMART3));
				paramList.add(DateUtil.parser(end, DateUtil.FORMART3));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(type != null){
			hql.append(" and istiexian = ?");
			paramList.add(type);
		}
		return getListByHQL(hql.toString(), paramList.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.AccountrecordDao#getByForm(com.ry.core.form.AccountrecordForm)
	 */
	public List<Accountrecord> getByForm(AccountrecordForm form) throws Exception {
		StringBuilder hql = new StringBuilder("from accountrecord a where 1=1 and (statue = 1 OR statue IS NULL) ");
		List<Object> paramList = new ArrayList<Object>();
		if(form!=null){
			if(StringUtils.hasText(form.getStart()) && StringUtils.hasText(form.getEnd())){
				hql.append(" and tiexiandate between ? and ?");
				paramList.add(DateUtil.parser(form.getStart(), DateUtil.FORMART3));
				paramList.add(DateUtil.parser(form.getEnd(), DateUtil.FORMART3));
			}
			if(form.getMemberid()!=null){
				hql.append(" and memberid = ?");
				paramList.add(form.getMemberid());
			}
			if (form.getBelong() != null) {
				hql.append(" and belong = ?");
				paramList.add(form.getBelong());
			}
			if(StringUtils.hasText(form.getIsTiexian())){
				String temp = form.getIsTiexian();
				String[] str = temp.split(",");
				String tag = "";
				for(String s:str){
					if(tag!=null && tag!="")tag += ",";
					tag += "?";
					paramList.add(s);
				}
				hql.append(" and istiexian in("+tag+")");
			}
			if(form.getType()!=null){
				
			}
		}
		return getListByHQL(hql.toString(), paramList.toArray());
	}

	@Override
	public List<Map<String, Object>> sumLixi(String begin, String end,
			String type, Integer memberid) {
		StringBuilder hql = new StringBuilder("select sum(tiexianlixi) as total,type1 as type from accountrecord a where 1=1 and (statue = 1 OR statue IS NULL) ");
		List paramList = new ArrayList();
		if(memberid != null){
			hql.append("and memberid = ?");
			paramList.add(memberid);
		}
		if(begin!=null && end != null){
			hql.append(" and tiexiandate between ? and ?");
			try {
				paramList.add(DateUtil.parser(begin, DateUtil.FORMART3));
				paramList.add(DateUtil.parser(end, DateUtil.FORMART3));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(type != null){
			hql.append(" and istiexian = ?");
			paramList.add(type);
		}
		hql.append(" GROUP BY type1"); 
		return getListMapBySQL(hql.toString(), paramList.toArray());
	}

	@Override
	public Accountrecord getAccountrecordByDiscountrecordId(Integer id) {
		List<Accountrecord> accs = getListByHQL("from accountrecord where (statue = 1 OR statue IS NULL) and discountrecordId = ?", new Object[]{id});
		if(accs != null && accs.size() > 0){
			return accs.get(0);
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.AccountrecordDao#getSumByMemberId(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	public List<Map<String, Object>> getSumByMemberId(Integer memberId,Integer belong,String start,String end) throws Exception {
		StringBuffer sql = new StringBuffer("SELECT type1,SUM(tiexianlixi) tiexianlixi,SUM(allprice) allprice FROM accountrecord WHERE istiexian=1 AND (statue = 1 OR statue IS NULL) AND memberid=? AND belong=?");
		List<Object> param = new ArrayList<Object>();
		param.add(memberId);
		param.add(belong);
		if(StringUtils.hasText(start) && StringUtils.hasText(end)){
			sql.append(" and tiexiandate between ? and ?");
			param.add(DateUtil.parser(start+" 00:00:00", DateUtil.FORMART3));
			param.add(DateUtil.parser(end+" 59:59:59", DateUtil.FORMART3));
		}
		sql.append(" GROUP BY type1");
		return getListMapBySQL(sql.toString(), param.toArray());
	}
	
	@Override
	public List<Accountrecord> alltiexian(Integer memberid,String istiexian){
		StringBuilder hql=new StringBuilder("from accountrecord where 1=1 ");
		List<Object> paramList = new ArrayList<Object>();
		if(memberid != null){
			hql.append("and memberid = ?");
			paramList.add(memberid);
		}
		if(istiexian != null){
			hql.append("and istiexian = ?");
			paramList.add(istiexian);
		}
		System.out.println(hql);
		return getListByHQL(hql.toString(), paramList.toArray());
	}
	
	@Override
	public List<Accountrecord> customcount(Integer memberId, String type,String end,String start,Float allprice,Integer type1){
		StringBuilder hql = new StringBuilder("from accountrecord a where 1=1 and (statue = 1 OR statue IS NULL) ");
		List<Object> paramList = new ArrayList<Object>();
		if(memberId != null){
			hql.append("and memberid = ?");
			paramList.add(memberId);
		}
		if(start!=null && end != null){
			hql.append(" and DATE_FORMAT( IFNULL(createTime, tiexiandate), '%Y-%m-%d' ) between DATE_FORMAT(?,'%Y-%m-%d') and DATE_FORMAT(?,'%Y-%m-%d')");
			try {
				paramList.add(DateUtil.parser(start, DateUtil.FORMART3));
				paramList.add(DateUtil.parser(end, DateUtil.FORMART3));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(type != null){
			hql.append(" and istiexian = ?");
			paramList.add(type);
		}
		if(allprice !=null){
			hql.append("and allprice = ?");
			paramList.add(allprice);
		}
		if(type1 !=null){
			hql.append("and type1 = ?");
			paramList.add(type1);
		}
		return getListByHQL(hql.toString(), paramList.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.AccountrecordDao#noticeConnect(java.lang.Integer, java.lang.String, java.util.Date, java.util.Date, java.lang.Float, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<Map<String, Object>> noticeConnect(Integer memberId, String istiexian,Date end,Date start,Float allprice,Integer type1,Integer belong,String type,Integer limit){
		StringBuilder hql = new StringBuilder("select acc.*,n.fkId noticeId from accountrecord acc LEFT JOIN noticerecord n on acc.id=n.fkId where (statue = 1 OR statue IS NULL) and acc.memberid = ? ");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(memberId);
		if(start!=null && end!=null){
			hql.append(" and acc.createTime between ? and ?");
			paramList.add(start);
			paramList.add(end);
		}
		if(istiexian != null){
			hql.append(" and acc.istiexian = ?");
			paramList.add(istiexian);
		}
		if(allprice !=null){
			hql.append(" and acc.allprice = ?");
			paramList.add(allprice);
		}
		if(type1 !=null){
			hql.append(" and acc.type1 = ?");
			paramList.add(type1);
		}
		if(belong !=null){
			hql.append(" and acc.belong = ?");
			paramList.add(belong);
		}
		if(type !=null){//电纸票
			hql.append(" and acc.piaojushuxing = ?");
			paramList.add(type);
		}
		if(limit !=null){//电票期限
			hql.append(" and acc.accept_time = ?");
			paramList.add(limit);
		}
		return getListMapBySQL(hql.toString(), paramList.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.AccountrecordDao#getForRemindExpire(java.util.Date, java.util.Date, java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<Map<String, Object>> getForRemindExpire(Date beforeDaoqishijian, Date daoqishijian, String statue, String tiexianType, String istiexian) {
		StringBuilder sql = new StringBuilder("select ar.id, ar.memberid, ar.daoqidate, ar.allprice from accountrecord ar where 1 = 1");
		List<Object> paramList = new ArrayList<Object>();
		if (beforeDaoqishijian != null && daoqishijian != null) {
			sql.append(" and ar.daoqidate between ? and ?");
			paramList.add(beforeDaoqishijian);
			paramList.add(daoqishijian);
		}
		if (statue != null) {
			sql.append(" and ar.statue != ?");
			paramList.add(statue);
		}
		if (!"".equals(tiexianType)) {
			sql.append(" and ar.tiexianType = ?");
			paramList.add(tiexianType);
		}
		if (!"".equals(istiexian)) {
			sql.append(" and ar.istiexian = ?");
			paramList.add(istiexian);
		}
		return getListMapBySQL(sql.toString(), paramList.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.AccountrecordDao#getByDcrdIdAndBelong(java.lang.Integer, java.lang.Integer)
	 */
	public Accountrecord getByDcrdIdAndBelong(Integer dcrdId,Integer belong) {
		StringBuffer hql = new StringBuffer("from accountrecord where (statue = 1 OR statue IS NULL) and discountrecordId = ? AND belong=?");
		List<Object> paras = new ArrayList<Object>();
		paras.add(dcrdId);
		paras.add(belong);
		
		List<Accountrecord> accs = getListByHQL(hql.toString(),paras.toArray());
		if(accs != null && accs.size() > 0){
			return accs.get(0);
		}else{
			return null;
		}
	}
	
	public List<Map<String,Object>> ifExistTiXin(Integer memberid,Integer belong,String date1,String date2){
		String sql = "select acc.*,n.id noticeId from accountrecord acc LEFT JOIN noticerecord n on acc.id=n.fkId where acc.memberid = ?"
				+ " and acc.belong =? and (n.fkId != null or n.fkId != '') and n.noticetime between ? and ?";
		List<Object> paras = new ArrayList<Object>();
		paras.add(memberid);
		paras.add(belong);
		paras.add(date1);
		paras.add(date2);
		List<Map<String, Object>> list= getListMapBySQL(sql,paras.toArray());
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.AccountrecordDao#getListByBelong(java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	public List<Accountrecord> getListByBelong(Integer memberId,Integer belong, String day) {
		StringBuffer hql = new StringBuffer("from accountrecord where memberid = ? and (statue = 1 OR statue IS NULL)and belong=? and DATE_FORMAT(createTime,'%Y-%m-%d') =?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(memberId);
		paramList.add(belong);
		paramList.add(day);
		return getListByHQL(hql.toString(), paramList.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.AccountrecordDao#getPageList(com.ry.core.form.AccountrecordForm, java.lang.Integer, java.lang.Integer)
	 */
	public PageResults<Map<String, Object>> getPageList(AccountrecordForm form, Integer pageIndex, Integer pageSize,Integer memberId) {
		StringBuffer sql = new StringBuffer(" SELECT ac.*,nc.noticeaddtime FROM accountrecord ac LEFT JOIN noticerecord nc ON ac.id = nc.fkId WHERE ac.memberid = ? AND (ac.statue = 1 OR ac.statue IS NULL) AND ac.belong = 0");
		List<Object> params = new ArrayList<Object>();
		params.add(memberId);
		if(form!=null){
			if(form.getShuxing()!=null){
				if(form.getShuxing()==1){
					sql.append(" and piaojushuxing = '纸票'");
				}else if(form.getShuxing()==2){
					sql.append(" and piaojushuxing = '电票'");
				}
			}
			if(form.getType()!=null && form.getType().trim()!=""){
				sql.append(" and istiexian = ?");
				params.add(form.getType());
			}
			if(form.getBelong()!=null){
				sql.append(" and belong = ?");
				params.add(form.getBelong());
			}
			if(form.getNotice()!=null){
				if(form.getNotice() == 1){//设置票据提醒
					sql.append(" AND nc.noticeaddtime IS NOT NULL");
				}else if(form.getNotice() == 0){
					sql.append(" AND nc.noticeaddtime IS NULL");
				}
			}
		}
		sql.append(" GROUP BY ac.id ORDER BY ac.createTime DESC");
		String count = " SELECT COUNT(*) FROM (" + sql.toString() +")res";
		return findPageMapByFetchedSql(sql.toString(), count, pageIndex, pageSize, params.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.AccountrecordDao#getList(com.ry.core.form.AccountrecordForm, java.lang.Integer)
	 */
	public List<Map<String, Object>> getList(AccountrecordForm form, Integer memberId) {
		StringBuffer sql = new StringBuffer(" SELECT ac.*,nc.noticeaddtime FROM accountrecord ac LEFT JOIN noticerecord nc ON ac.id = nc.fkId WHERE ac.memberid = ? AND (ac.statue = 1 OR ac.statue IS NULL) AND ac.belong = 0");
		List<Object> params = new ArrayList<Object>();
		params.add(memberId);
		if(form!=null){
			if(form.getShuxing()!=null){
				if(form.getShuxing()==1){
					sql.append(" and piaojushuxing = '纸票'");
				}else if(form.getShuxing()==2){
					sql.append(" and piaojushuxing = '电票'");
				}
			}
			if(form.getType()!=null && form.getType().trim()!=""){
				sql.append(" and istiexian = ?");
				params.add(form.getType());
			}
			if(form.getBelong()!=null){
				sql.append(" and belong = ?");
				params.add(form.getBelong());
			}
			if(form.getNotice()!=null){
				if(form.getNotice() == 1){//设置票据提醒
					sql.append(" AND nc.noticeaddtime IS NOT NULL");
				}else if(form.getNotice() == 0){
					sql.append(" AND nc.noticeaddtime IS NULL");
				}
			}
		}
		sql.append(" GROUP BY ac.id ORDER BY ac.createTime DESC");
		return getListMapBySQL(sql.toString(), params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.AccountrecordDao#getPageList(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PageResults<Map<String, Object>> getPageList(Integer orgId,Integer memberId,Integer pageIndex,Integer pageSize) throws Exception {
		StringBuffer sql = new StringBuffer("FROM (SELECT * FROM accountrecord WHERE 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if(memberId!=null){
			sql.append(" AND (memberid = ? AND belong=0) ");
			params.add(memberId);
			if(orgId!=null){
				sql.append(" OR(memberid = ? AND belong=1) ");
				params.add(orgId);
			}
			sql.append(" )acc ");
		}else{
			throw new Exception("网络异常");
		}
		sql.append(" LEFT JOIN noticerecord n ON acc.id=n.fkId WHERE (n.fkId IS NOT NULL OR n.fkId != '')");
		sql.append(" ORDER BY n.noticetime DESC");
		return findPageMapByFetchedSql("SELECT acc.id,acc.allprice,acc.tiexianType,n.id noticeId,n.noticedesc,n.expiredate,n.noticetime "+sql.toString(), "SELECT COUNT(*) "+sql.toString(), pageIndex, pageSize, params.toArray());
	}
}