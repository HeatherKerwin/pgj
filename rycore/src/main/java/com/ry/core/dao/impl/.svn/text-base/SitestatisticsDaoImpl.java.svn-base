package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.SitestatisticsDao;
import com.ry.core.entity.Sitestatistics;
import com.ry.util.DateUtil;


@Repository
public class SitestatisticsDaoImpl extends BaseDao<Sitestatistics,Integer > implements SitestatisticsDao{

	@Override
	public List getListCountByDay(Date date) { 
		Date endDate = date==null?new Date():date;
		endDate = DateUtil.getStartDate(endDate);
		Date beginDate = DateUtil.addDays(endDate, -1);
		List paramList = new ArrayList();
		paramList.add(beginDate);
		paramList.add(endDate);
		String sql = "select count(*) as countvisit ,count(distinct ip) countip  from sitestatistics where invitedate >= ? and invitedate < ? and type='PC' ";
		return getListBySQL(sql, paramList.toArray());
	}
	
	@Override
	public List<Map<String,Object>> getPinyouCountByDay(String date) { 
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(date);
		String sql = "select count(*) as countvisit ,count(distinct ip) countip  from sitestatistics where date_format(invitedate,'%Y-%m-%d') = ? and type='PC' and  hezuo ='pinyou' ";
		return getListMapBySQL(sql, paramList.toArray());
	}

	@Override
	public List getListCountByDayAndCooperation(Date date,String cooStr) {
		Date endDate = date==null?new Date():date;
		endDate = DateUtil.getStartDate(endDate);
		Date beginDate = DateUtil.addDays(endDate, -1);
		return getListCountByDateAndCooperation(beginDate, endDate,cooStr);
	}
	@Override
	public List getListCountByDateAndCooperation(Date beginDate,Date endDate,String cooStr) {
		List paramList = new ArrayList();
		paramList.add(beginDate);
		paramList.add(endDate);
		paramList.add(cooStr);
		String sql = "SELECT s.hezuo,count(*) as countvisit,count(DISTINCT s.ip) as countip from sitestatistics s where  s.invitedate >= ? and s.invitedate < ? GROUP BY s.hezuo HAVING s.hezuo in(?);";
		return getListBySQL(sql, paramList.toArray());
	}
	@Override
	public void addSitestatistics(Sitestatistics sitestatistics) {
		
		save(sitestatistics);
		
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.SitestatisticsDao#getCountByStartAndEnd(java.lang.String, java.util.Date, java.util.Date)
	 */
	public List<Map<String,Object>> getCountByStartAndEnd(String type,Date start,Date end){
		StringBuffer sql = new StringBuffer("SELECT invitedate AS createtime,COUNT(id)AS amount FROM (SELECT * FROM (SELECT *,CONCAT(ip,DATE_FORMAT(invitedate,'%Y-%m-%d')) AS _f FROM sitestatistics WHERE 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if(StringUtils.hasText(type)){
			sql.append(" and type=?");
			params.add(type);
		}
		if(start!=null && end!=null){
			sql.append(" and invitedate BETWEEN ? and ?");
			params.add(start);
			params.add(end);
		}
		sql.append(") _t GROUP BY _f)result");
		return getListMapBySQL(sql.toString(), params.toArray());
	}
}