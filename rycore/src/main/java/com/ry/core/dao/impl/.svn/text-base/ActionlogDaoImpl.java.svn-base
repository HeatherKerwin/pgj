package com.ry.core.dao.impl;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.ActionlogDao;
import com.ry.core.dao.BaseDao;
import com.ry.core.entity.Actionlog;
import com.ry.core.form.CustomReportForm;
import com.ry.util.DateUtil;
import com.ry.util.page.PageResults;

@Repository
public class ActionlogDaoImpl extends BaseDao<Actionlog, Integer> implements ActionlogDao {

	@Override
	public void addActionlog(Actionlog actionlog) {
		if ("action7".equals(actionlog.getCode()) || "action8".equals(actionlog.getCode())) {
			if ("APP".equals(actionlog.getFrom())) {
				save(actionlog);
				return;
			}			
		}
		if (getActionlog(actionlog) != null) {
			return;
		}
		save(actionlog);
	}

	//当天该用户是否已记录
	@Override
	public Actionlog getActionlog(Actionlog actionlog) {
		Date nowdDate = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String beforeDate = sf.format(nowdDate) + " 00:00:00";
		String endDate = sf.format(nowdDate) + " 23:59:59";

		try {
			Date bDate = sd.parse(beforeDate);
			Date eDate = sd.parse(endDate);
			String hql = "from actionlog where 1=1";
			List paramList = new ArrayList();
			if (actionlog.getMemberId()!=null && actionlog.getMemberId()>0) {
				hql += " and memberid=?";
				paramList.add(actionlog.getMemberId());
			}		
			hql += " and actiontime >? and actiontime <?";
			paramList.add(bDate);
			paramList.add(eDate);
			if (actionlog.getCode() != null) {
				hql += " and code=? ";
				paramList.add(actionlog.getCode());
			}
			
			if (!"APP".equals(actionlog.getFrom()) &&actionlog.getIpadr() != null) {
				hql += " and ipadr=? ";
				paramList.add(actionlog.getIpadr());
			}
			if (actionlog.getFrom() != null) {
				hql += " and source=? ";
				paramList.add(actionlog.getFrom());
			}
			List<Actionlog> list =getListByHQL(hql, paramList.toArray());
			if (list!=null && list.size()>0) {
				return list.get(0);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.ActionlogDao#countActionlogByStartAndEndAndMemberId(java.util.Date, java.util.Date, java.lang.Integer)
	 */
	public BigInteger countActionlogByStartAndEndAndMemberId(Date start,Date end,Integer memberId) {
		List<Object> paramList = new ArrayList<Object>();
		StringBuilder hql = new StringBuilder("SELECT COUNT(*) FROM (SELECT * from actionlog where memberId=?");
		paramList.add(memberId);
		if(start !=null && end !=null){
			hql.append(" and actiontime between ? and ?");
			paramList.add(start);
			paramList.add(end);
		}
		hql.append(" GROUP BY DATE_FORMAT(actiontime,'%Y-%m-%d'))AS result");
		return countBySql(hql.toString(), paramList.toArray());
	}
	
	public List<Map<String, Object>> countMemberLogin(List<Object> paramList, Date startTime, Date endTime) {
		StringBuffer sql = new StringBuffer("select tab1.memberId, count(1) number from (select distinct al.memberId, DATE_FORMAT(al.actiontime, '%Y-%m-%d') actiontime,count(1) total from actionlog al where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if (paramList != null && paramList.size() > 0) {
			sql.append("and al.memberId in (");
			for (int i = 0; i < paramList.size(); i++) {
				if ((i + 1) == paramList.size()) {
					sql.append(" ?");
				} else {
					sql.append(" ?,");
				}
			}
			sql.append(") ");
		}
		params.addAll(paramList);
		if (startTime != null && endTime != null) {
			sql.append("and al.actiontime between ? and ? ");
			params.add(startTime);
			params.add(endTime);
		}
		sql.append(" group by al.memberId,al.actiontime ) tab1 group by tab1.memberId");
		return getListMapBySQL(sql.toString(), params.toArray());
	}
	
	public List<Actionlog> getListByIdAndTime(List<Object> paramList, Date startTime, Date endTime) {
		StringBuffer hql = new StringBuffer("from actionlog where 1=1");
		List<Object> params = new ArrayList<Object>();
		if (startTime != null && endTime != null) {
			hql.append(" and actiontime between ? and ? ");
			params.add(startTime);
			params.add(endTime);
		}
		if (paramList != null && paramList.size() > 0) {
			hql.append(" and memberId in (");
			for (int i = 0; i < paramList.size(); i++) {
				if ((i + 1) == paramList.size()) {
					hql.append(" ?");
				} else {
					hql.append(" ?,");
				}
			}
			hql.append(") ");
			params.addAll(paramList);
		}
		return getListByHQL(hql.toString(), params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.ActionlogDao#getByCodeAndMemberIdAndDayAndFrom(java.lang.String, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	public List<Map<String, Object>> getByCodeAndMemberIdAndDayAndFrom(String code,Integer memberId,String day,String from) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder("select * FROM actionlog where code=? and memberId=? and DATE_FORMAT(actiontime,'%Y-%m-%d')=? and source=?");
		params.add(code);
		params.add(memberId);
		params.add(day);
		params.add(from);
		return getListMapBySQL(sql.toString(), params.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.ActionlogDao#orgloginreport(com.ry.core.form.CustomReportForm)
	 */
	@Override
	public PageResults<Map<String, Object>> orgloginreport(CustomReportForm from) {
		StringBuilder sql = new StringBuilder("select r1.* ,p.id FROM (SELECT rowdata.actiontime,org_info.`org_id` AS orgid,org_info.`company` FROM (SELECT memberId,DATE_FORMAT(actiontime,'%Y-%m-%d') AS actiontime FROM actionlog WHERE CODE='action1' AND 1=1");
		List<Object> params = new ArrayList<Object>();
		String count = null;
		try {
			if(StringUtils.hasText(from.getStartDate())){
				params.add(DateUtil.parser(from.getStartDate(), DateUtil.FORMART3));
				sql.append(" and actiontime >= ? ");
			}
			if(StringUtils.hasText(from.getEndDate())){
				sql.append(" and actiontime < ?");
				params.add(DateUtil.addDays(DateUtil.parser(from.getEndDate(), DateUtil.FORMART3), 1));
			}
			sql.append(")rowdata LEFT JOIN org_info ON rowdata.memberId = org_info.`member_id` WHERE org_info.state =2 AND org_info.type_=1)r1");
			sql.append(" LEFT JOIN price p ON p.org_id = r1.orgid AND r1.actiontime = DATE_FORMAT(p.create_time,'%Y-%m-%d') where 1=1");
			if(from.getPrice() != null && from.getPrice() == 1){//查询未报价的
				sql.append(" and p.id IS NULL");
			}else if(from.getPrice() != null && from.getPrice() == 2){//查询报价的
				sql.append(" and p.id IS NOT NULL");
			}
			if(StringUtils.hasText(from.getOrgcompany())){
				sql.append(" and r1.company = ?");
				params.add(from.getOrgcompany());
			}
			sql.append(" GROUP BY actiontime ,orgid  ORDER BY r1.actiontime desc ");
			count = new String(" SELECT COUNT(*) FROM (" + sql.toString()+")r2");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return findPageMapByFetchedSql(sql.toString(), count, from.currentPage().intValue(),from.getLength().intValue(), params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.ActionlogDao#bnsloginreport(com.ry.core.form.CustomReportForm)
	 */
	@Override
	public PageResults<Map<String, Object>> bnsloginreport(CustomReportForm from) {
		StringBuilder sql = new StringBuilder("SELECT r1.actiontime,r1.memberId AS member_id,r1.company, r2.create_time FROM (SELECT rowdata.actiontime,rowdata.memberId AS memberId,org_info.`company` AS company FROM (SELECT memberId,DATE_FORMAT(actiontime,'%Y-%m-%d') AS actiontime FROM actionlog WHERE CODE='action1' AND 1=1");
		List<Object> params = new ArrayList<Object>();
		String count = null;
		try {
			if(StringUtils.hasText(from.getStartDate())){
				params.add(DateUtil.parser(from.getStartDate(), DateUtil.FORMART3));
				sql.append(" and actiontime >= ? ");
			}
			if(StringUtils.hasText(from.getEndDate())){
				sql.append(" and actiontime < ?");
				params.add(DateUtil.addDays(DateUtil.parser(from.getEndDate(), DateUtil.FORMART3), 1));
			}
			sql.append(")rowdata LEFT JOIN org_info ON rowdata.memberId = org_info.`member_id` WHERE org_info.state =2 AND org_info.type_=0 GROUP BY rowdata.actiontime ,org_info.org_id)r1");
			sql.append(" LEFT JOIN (SELECT DISTINCT create_time,memberId FROM ( SELECT DATE_FORMAT(ordertime,'%Y-%m-%d') create_time,memberid AS memberId FROM discountrecord disc where 1=1 ");
			if(StringUtils.hasText(from.getStartDate())){
				params.add(DateUtil.parser(from.getStartDate(), DateUtil.FORMART3));
				sql.append(" and DATE_FORMAT(disc.ordertime,'%Y-%m-%d') >= ? ");
			}
			if(StringUtils.hasText(from.getEndDate())){
				sql.append(" and DATE_FORMAT(disc.ordertime,'%Y-%m-%d') < ?");
				params.add(DateUtil.addDays(DateUtil.parser(from.getEndDate(), DateUtil.FORMART3), 1));
			}
			sql.append(" UNION ALL SELECT DATE_FORMAT(create_time,'%Y-%m-%d') create_time,member_id AS memberId FROM discountrecord_sp disc1 where 1=1 ");
			if(StringUtils.hasText(from.getStartDate())){
				params.add(DateUtil.parser(from.getStartDate(), DateUtil.FORMART3));
				sql.append(" and disc1.create_time >= ? ");
			}
			if(StringUtils.hasText(from.getEndDate())){
				sql.append(" and disc1.create_time < ?");
				params.add(DateUtil.addDays(DateUtil.parser(from.getEndDate(), DateUtil.FORMART3), 1));
			}
			sql.append(")AS t)r2 ON r1.actiontime = create_time AND r1.memberId=r2.memberId where 1=1 ");
			if(from.getDiscount() != null && from.getDiscount() == 1){//查询为贴现的
				sql.append(" and r2.create_time IS NULL");
			}else if(from.getDiscount() != null && from.getDiscount() == 2){//查询贴现的
				sql.append(" and r2.create_time IS NOT NULL");
			}
			if(StringUtils.hasText(from.getBnscompany())){
				sql.append(" and r1.company = ?");
				params.add(from.getBnscompany());
			}
			sql.append(" ORDER BY r1.actiontime desc ");
			count = new String(" SELECT COUNT(*) FROM (" + sql.toString()+")r3");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return findPageMapByFetchedSql(sql.toString(), count, from.currentPage().intValue(),from.getLength().intValue(), params.toArray());
	}

	@Override
	public List<Actionlog> getListCountByDay(Date start, Date end, String source) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) AS countvisitA ,COUNT(DISTINCT ipadr) AS countipA FROM actionlog WHERE 1=1 ");
		if(start != null){
			sql.append(" AND actiontime >= ?");
			params.add(start);
		}
		if(end != null){
			sql.append(" AND actiontime < ?");
			params.add(end);
		}
		if(StringUtils.hasText(source)){
			sql.append(" AND source=?");
			params.add(source);
		}
		return getListByHQL(sql.toString(), params.toArray());
	}
}