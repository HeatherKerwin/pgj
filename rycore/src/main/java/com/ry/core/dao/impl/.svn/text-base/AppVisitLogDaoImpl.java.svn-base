package com.ry.core.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.AppVisitLogDao;
import com.ry.core.dao.BaseDao;
import com.ry.core.entity.da.AppVisitLog;

@Repository
public class AppVisitLogDaoImpl extends BaseDao<AppVisitLog, Integer> implements AppVisitLogDao {

	@Override
	public void add(AppVisitLog visit) {
		save(visit);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.AppVisitLogDao#getGroupByMemberIdAndActionDate(java.lang.String, java.lang.String)
	 */
	public List<Map<String,Object>> getGroupByMemberIdAndActionDate(String start,String end){
		StringBuffer sql = new StringBuffer("SELECT result.memberId,COUNT(result.memberId) AS amount FROM");
		sql.append(" (SELECT * FROM appVisitLog WHERE actionDate BETWEEN ? AND ? GROUP BY memberId, DATE_FORMAT(actionDate,'%Y-%m-%d')) AS result ");
		sql.append("  GROUP BY result.memberId");
		List<Object> param = new ArrayList<Object>();
		param.add(start);
		param.add(end);
		return getListMapBySQL(sql.toString(), param.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.AppVisitLogDao#getCountByStartAndEnd(java.lang.String, java.util.Date, java.util.Date)
	 */
	public List<Map<String,Object>> getCountByStartAndEnd(String code,Date start,Date end){
		StringBuffer sql = new StringBuffer("SELECT actionDate AS createtime,COUNT(id)AS amount FROM (SELECT * FROM (SELECT *, CONCAT(memberId,DATE_FORMAT(actionDate,'%Y-%m-%d')) AS _f FROM appVisitLog WHERE 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if(StringUtils.hasText(code)){
			sql.append(" and CODE=?");
			params.add(code);
		}
		if(start!=null && end!=null){
			sql.append(" and actionDate BETWEEN ? and ?");
			params.add(start);
			params.add(end);
		}
		sql.append(") _t GROUP BY _f)result");
		return getListMapBySQL(sql.toString(), params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.AppVisitLogDao#getCountFromNewByStartAndEnd(java.lang.Integer, java.util.Date, java.util.Date, java.util.Date, java.util.Date)
	 */
	public List<Map<String,Object>> getCountFromNewByStartAndEnd(Integer hid,Date mStart,Date mEnd,Date vStart,Date vEnd){
		StringBuffer sql = new StringBuffer("SELECT COUNT(*)as amount FROM appVisitLog WHERE actionDate BETWEEN ? AND ? AND memberId IN (");
		List<Object> params = new ArrayList<Object>();
		params.add(vStart);
		params.add(vEnd);
		
		sql.append("SELECT result.id FROM(SELECT h.createtime AS D,m.* FROM hongbaoDetail h LEFT JOIN (SELECT id,mobile,username,FROM_UNIXTIME(regtime/1000,'%Y-%m-%d')AS createtime FROM member) m ON h.memberid=m.id WHERE 1=1 ");
		if(hid!=null){//红包活动的类型(用户来源于红包)
			sql.append(" and h.hid=?");
			params.add(hid);
		}
		if(mStart!=null && mEnd!=null){//用户注册日期
			sql.append(" and m.createtime BETWEEN ? and ?");
			params.add(mStart);
			params.add(mEnd);
		}
		sql.append(" AND m.createtime=DATE_FORMAT(h.createtime,'%Y-%m-%d'))result)");
		return getListMapBySQL(sql.toString(), params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.AppVisitLogDao#getTotalCountFromNewByStartAndEnd(java.lang.Integer, java.util.Date, java.util.Date, java.util.Date, java.util.Date)
	 */
	public List<Map<String,Object>> getTotalCountFromNewByStartAndEnd(Integer hid,Date mStart,Date mEnd,Date vStart,Date vEnd){
		StringBuffer sql = new StringBuffer("SELECT COUNT(*)AS amount FROM (SELECT * FROM appVisitLog WHERE actionDate BETWEEN ? AND ? AND memberId IN (");
		List<Object> params = new ArrayList<Object>();
		params.add(vStart);
		params.add(vEnd);
		
		sql.append("SELECT result.id FROM(SELECT h.createtime AS D,m.* FROM hongbaoDetail h LEFT JOIN (SELECT id,mobile,username,FROM_UNIXTIME(regtime/1000,'%Y-%m-%d')AS createtime FROM member) m ON h.memberid=m.id WHERE 1=1 ");
		if(hid!=null){//红包活动的类型(用户来源于红包)
			sql.append(" and h.hid=?");
			params.add(hid);
		}
		if(mStart!=null && mEnd!=null){//用户注册日期
			sql.append(" and h.createtime BETWEEN ? and ?");
			params.add(mStart);
			params.add(mEnd);
		}
		sql.append(" AND m.createtime=DATE_FORMAT(h.createtime,'%Y-%m-%d'))result) GROUP BY memberId)fix");
		return getListMapBySQL(sql.toString(), params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.AppVisitLogDao#getByStartAndEnd(java.util.Date, java.util.Date)
	 */
	public List<Map<String, Object>> getByStartAndEnd(Date start,Date end) {
		StringBuilder sql = new StringBuilder("SELECT COUNT(*)amount,res.* FROM (");
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT a.*,m.recommendpeople,m.mobile,DATE_FORMAT(a.actionDate, '%Y-%m-%d')atime FROM member m LEFT JOIN appVisitLogTemp a ON a.memberId=m.id WHERE m.recommendpeople IS NOT NULL AND m.recommendpeople<>'' AND m.hezuo IS NULL AND a.actionDate BETWEEN ? AND ?");
		sql.append(" AND m.regtime BETWEEN ? AND ? GROUP BY CONCAT(DATE_FORMAT(a.actionDate, '%Y-%m-%d'),a.memberId)");
		sql.append(" )res GROUP BY res.memberId");
		params.add(start);
		params.add(end);
		params.add(start);
		params.add(end);
		return getListMapBySQL(sql.toString(), params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.AppVisitLogDao#getByStartAndEndInMemberIds(java.util.Date, java.util.Date, java.util.List)
	 */
	public List<Map<String, Object>> getByStartAndEndInMemberIds(Date start,Date end,List<Integer>memberIds) {
		StringBuilder sql = new StringBuilder("SELECT COUNT(*)amount,res.* FROM (");
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT a.*,m.recommendpeople,m.mobile,DATE_FORMAT(a.actionDate, '%Y-%m-%d')atime FROM member m LEFT JOIN appVisitLogTemp a  ON a.memberId=m.id WHERE m.recommendpeople IS NOT NULL AND m.recommendpeople<>'' AND m.hezuo IS NULL AND a.actionDate BETWEEN ? AND ?");
		params.add(start);
		params.add(end);
		String tag = "";
		for(Integer of:memberIds){
			if(!"".equals(tag)) tag += ",";
			tag += "?";
			params.add(of);
		}
		sql.append(" AND m.id IN("+tag+")");
		sql.append(" GROUP BY CONCAT(DATE_FORMAT(a.actionDate, '%Y-%m-%d'),a.memberId)");
		sql.append(" )res GROUP BY res.memberId");
		return getListMapBySQL(sql.toString(), params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.AppVisitLogDao#createTempByStartAndEnd(java.util.Date, java.util.Date)
	 */
	public void createTempByStartAndEnd(Date start,Date end) {
		StringBuilder sql = new StringBuilder("CREATE TABLE appVisitLogTemp SELECT a.* FROM appVisitLog a WHERE a.actionDate BETWEEN ? AND ?");
		List<Object> params = new ArrayList<Object>();
		params.add(start);
		params.add(end);
		executeSql("DROP TABLE IF EXISTS appVisitLogTemp",null);
		executeSql(sql.toString(),params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.AppVisitLogDao#getHasOrgInfoByStartAndEnd(java.util.Date, java.util.Date)
	 */
	public List<Map<String, Object>> getHasOrgInfoByStartAndEnd(Date start,Date end) {
		StringBuilder sql = new StringBuilder("SELECT COUNT(*)amount,res.* FROM (");
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT a.*,m.recommendpeople,m.mobile,DATE_FORMAT(a.actionDate, '%Y-%m-%d')atime FROM (");
		
		sql.append("SELECT m.* FROM (");
		sql.append("SELECT * FROM (SELECT * FROM org_info i WHERE i.state=2 AND i.type_=1 AND i.create_time BETWEEN ? AND ? ORDER BY i.id DESC)res GROUP BY res.member_id");
		sql.append(")info LEFT JOIN member m ON info.member_id=m.id WHERE m.id IS NOT NULL");
		
		sql.append(" ) m LEFT JOIN appVisitLogTemp a ON a.memberId=m.id WHERE m.hezuo IS NULL AND a.actionDate BETWEEN ? AND ?");
		sql.append(" GROUP BY CONCAT(DATE_FORMAT(a.actionDate, '%Y-%m-%d'),a.memberId)");
		sql.append(" )res GROUP BY res.memberId");
		params.add(start);
		params.add(end);
		params.add(start);
		params.add(end);
		return getListMapBySQL(sql.toString(), params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.AppVisitLogDao#getByStartAndEnd(java.util.Date, java.util.Date, java.lang.Integer, java.lang.Integer)
	 */
	public List<Map<String, Object>> getByStartAndEnd(Date start,Date end,Integer index,Integer size) {
		StringBuilder sql = new StringBuilder("SELECT * FROM (SELECT COUNT(*)amount,res.* FROM (");//不限制是否来自销售推荐等
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT a.*,m.recommendpeople,m.mobile,m.regtime,m.username,DATE_FORMAT(a.actionDate, '%Y-%m-%d')atime FROM member m LEFT JOIN appVisitLog a ON a.memberId=m.id WHERE a.actionDate BETWEEN ? AND ?");
		sql.append(" AND m.regtime BETWEEN ? AND ? GROUP BY CONCAT(DATE_FORMAT(a.actionDate, '%Y-%m-%d'),a.memberId)");
		sql.append(" )res GROUP BY res.memberId ORDER BY amount DESC)r WHERE r.amount>=4");
		params.add(start);
		params.add(end);
		params.add(start);
		params.add(end);
		if(size!=null && size!=null){//取前多少条数据
			sql.append(" LIMIT ?,?");
			params.add(index);
			params.add(size);
		}
		return getListMapBySQL(sql.toString(), params.toArray());
	}
	
	public List<Map<String, Object>> getByStartAndEndNotinMemberIds(Date start,Date end,Integer size,List<Integer>memberIds) {
		StringBuilder sql = new StringBuilder("SELECT COUNT(*)amount,res.* FROM (");//不限制是否来自销售推荐等
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT a.*,m.recommendpeople,m.mobile,m.regtime,m.username,DATE_FORMAT(a.actionDate, '%Y-%m-%d')atime FROM member m LEFT JOIN appVisitLog a ON a.memberId=m.id WHERE a.actionDate BETWEEN ? AND ?");
		sql.append(" AND m.regtime BETWEEN ? AND ? ");
		
		String tag = "";
		for(Integer of:memberIds){
			if(!"".equals(tag)) tag += ",";
			tag += "?";
			params.add(of);
		}
		sql.append(" AND m.id NOT IN("+tag+")");
		
		sql.append("GROUP BY CONCAT(DATE_FORMAT(a.actionDate, '%Y-%m-%d'),a.memberId))res GROUP BY res.memberId ORDER BY amount DESC");
		params.add(start);
		params.add(end);
		params.add(start);
		params.add(end);
		
		if(size!=null && size!=null){//取前多少条数据
			sql.append(" LIMIT ?");
			params.add(size);
		}
		return getListMapBySQL(sql.toString(), params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.AppVisitLogDao#getByStartAndEndInMemberIdsNoReple(java.util.Date, java.util.Date, java.util.List)
	 */
	public List<Map<String, Object>> getByStartAndEndInMemberIdsNoReple(Date start,Date end,List<Integer>memberIds) {
		StringBuilder sql = new StringBuilder("SELECT * FROM (SELECT COUNT(*)amount,res.* FROM (");
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT a.*,m.recommendpeople,m.mobile,m.regtime,m.username,DATE_FORMAT(a.actionDate, '%Y-%m-%d')atime FROM member m LEFT JOIN appVisitLog a ON a.memberId=m.id WHERE a.actionDate BETWEEN ? AND ?");
		params.add(start);
		params.add(end);
		String tag = "";
		for(Integer of:memberIds){
			if(!"".equals(tag)) tag += ",";
			tag += "?";
			params.add(of);
		}
		sql.append(" AND m.id IN("+tag+")");
		sql.append(" GROUP BY CONCAT(DATE_FORMAT(a.actionDate, '%Y-%m-%d'),a.memberId)");
		sql.append(" )res GROUP BY res.memberId ORDER BY amount DESC)r WHERE r.amount>=4");
		return getListMapBySQL(sql.toString(), params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.AppVisitLogDao#countByStartAndEndAndMemberId(java.util.Date, java.util.Date, java.lang.Integer)
	 */
	public BigInteger countByStartAndEndAndMemberId(Date start,Date end,Integer memberId) {
		List<Object> paramList = new ArrayList<Object>();
		StringBuilder hql = new StringBuilder("SELECT COUNT(*) FROM (SELECT * from appVisitLog where memberId=?");
		paramList.add(memberId);
		if(start !=null && end !=null){
			hql.append(" and actionDate between ? and ?");
			paramList.add(start);
			paramList.add(end);
		}
		hql.append(" GROUP BY DATE_FORMAT(actionDate,'%Y-%m-%d'))AS result");
		return countBySql(hql.toString(), paramList.toArray());
	}
}