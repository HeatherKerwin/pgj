package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.ServicememberDao;
import com.ry.core.entity.Servicemember;
import com.ry.util.page.PageResults;
@Repository
public class ServicememberDaoImpl extends BaseDao<Servicemember,Integer > implements ServicememberDao{

	public Integer saveServicemember(Servicemember s){
		return save(s);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.ServicememberDao#pageList(com.ry.core.entity.Servicemember, int, int)
	 */
	@Override
	public PageResults<Servicemember> pageList(Servicemember sm, int pageNo, int pageSize) {
		StringBuffer hql = new StringBuffer("from servicemember where 1=1 and flag=0 ");
		List paramList = new ArrayList();
		if(sm!=null){
			
		}
		hql.append("order by id desc");
		return findPageByFetchedHql(hql.toString(), null, pageNo, pageSize, paramList.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.ServicememberDao#deleteServicemembe(java.lang.Long)
	 */
	@Override
	public void deleteServicemembe(Integer id) {
		delete(id);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.ServicememberDao#updateServicemember(com.ry.core.entity.Servicemember)
	 */
	@Override
	public void updateServicemember(Servicemember s) {
		 update(s);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.ServicememberDao#getById(java.lang.Integer)
	 */
	@Override
	public Servicemember getById(Integer id) {
		return get(id);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.ServicememberDao#saleCountData()
	 */
	@Override
	public List saleCountData(String nums) {
		if(!StringUtils.hasText(nums)){
			return null;
		}
		String sql = "SELECT sm1.servicemember,sm1.servicenumber,m2.total,m2.monthNum,m2.dayNum from (SELECT sm.servicemember,sm.servicenumber from servicemember sm	where sm.servicenumber in (" + nums + ")) sm1 "
				+" LEFT JOIN (SELECT m1.recommendpeople,count(*) as total,sum(case when DATE_FORMAT(m1.date,'%Y-%m') = DATE_FORMAT(now(),'%Y-%m') then 1 else 0 end) monthNum, sum(case when DATE_FORMAT(date,'%Y-%m-%d') = DATE_FORMAT(now(),'%Y-%m-%d') then 1 else 0 end) dayNum" 
						+" FROM	(SELECT m.recommendpeople,  FROM_UNIXTIME(m.regtime/1000,'%Y-%m-%d %H:%i:%s') date FROM member m) m1 GROUP BY	m1.recommendpeople) m2 on sm1.servicenumber = m2.recommendpeople";
		return getListBySQL(sql, null);
	}

	@Override
	public Servicemember getServicemember(String servicenumber) {
		List<Servicemember> list =getListByHQL("from servicemember where servicenumber = ? and flag = 0 ", new Object[]{servicenumber});
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.ServicememberDao#saleLivenessData(java.lang.String)
	 */
	@Override
	public List saleLivenessData(String type) {
		StringBuffer sql = new StringBuffer("SELECT sm.servicemember,sm.servicenumber,count(DISTINCT aa.memberId) total,sm.id FROM servicemember sm LEFT JOIN member m ON m.recommendpeople = sm.servicenumber LEFT JOIN "
	+"(SELECT *	FROM actionlog a WHERE");
		if("monthCount".equals(type)){
			sql.append( " MONTH(a.actiontime) =MONTH(now()) ");
		}else if("lastMonthCount".equals(type)){
			sql.append( " MONTH(a.actiontime) =MONTH(now())-1 ");
		}else if("weekCount".equals(type)){
			sql.append( " WEEKOFYEAR(a.actiontime) =WEEKOFYEAR(now()) ");
		}else if("lastWeekCount".equals(type)){
			sql.append( " WEEKOFYEAR(a.actiontime) =WEEKOFYEAR(now())-1 ");
		}		
		sql.append("AND a.memberId IS NOT NULL 	) as aa on m.id = aa.memberId GROUP BY sm.servicenumber,sm.servicemember ORDER BY total desc");
		return getListBySQL(sql.toString(), null);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.ServicememberDao#saleCustomCount(java.lang.String)
	 */
	@Override
	public List saleCustomCount(String type) {
		StringBuffer sql = new StringBuffer("SELECT sm.servicemember,sm.servicenumber,count(DISTINCT m1.id) total from servicemember sm LEFT JOIN (SELECT m.id,m.recommendpeople,FROM_UNIXTIME(m.regtime/1000,'%Y-%m-%d %H:%i:%s') date FROM	member m ) m1 ON m1.recommendpeople = sm.servicenumber and ");
		if("monthCount".equals(type)){
			sql.append("Month(m1.date ) = MONTH(now())");
		}else if("weekCount".equals(type)){
			sql.append(" WEEKOFYEAR(m1.date ) =  WEEKOFYEAR(now())");
		}
		sql.append(" GROUP BY sm.servicemember,sm.servicenumber ORDER BY total desc");
		return getListBySQL(sql.toString(), null);
	}
	
	public List<Servicemember> getList(Servicemember sm) {
		StringBuffer sql = new StringBuffer("from servicemember sm where 1=1");
		List<Object> params = new ArrayList<Object>();
		if (sm != null && sm.getFlag() != null) {
			sql.append(" and sm.flag=?");
			params.add(sm.getFlag());
		}
		if (sm != null && sm.getServicenumber() != null && !"".equals(sm.getServicenumber().trim())) {
			sql.append(" and sm.servicenumber=?");
			params.add(sm.getServicenumber());
		}
		return getListByHQL(sql.toString(), params.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.ServicememberDao#recommendEmail(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<Map<String,Object>> recommendEmail(String date1, String date2, String num, String weekbegtime,String weekendtime, int year, int month) {
		if(!StringUtils.hasText(num)){
			return null;
		}
		StringBuffer sql = new StringBuffer("select COUNT(*) AS num, mobile,recommendpeople  from member where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		//日统计
		if(date1!=null && date1!=""){
			sql.append(" and regtime >= ? ");
			params.add(date1);
		}
		if(date2!=null && date2!=""){
			sql.append(" and regtime <= ? ");
			params.add(date2);
		}
		//周统计
		if(weekbegtime!=null && weekbegtime!=""){
			sql.append(" and regtime >= ? ");
			params.add(weekbegtime);
		}
		if(weekendtime!=null && weekendtime!=""){
			sql.append(" and regtime < ? ");
			params.add(weekendtime);
		}
		//月统计
		if(year != 0){
			sql.append(" and YEAR(regtime)= ? ");
			params.add(year);
		}
		if(month != 0){
			sql.append(" and MONTH(regtime)= ? ");
			params.add(month);
		}
		
		if(num!=null && num!=""){
			sql.append(" and recommendpeople IN ("+num+") ");
		}
		sql.append("  GROUP BY recommendpeople ");
		List<Map<String,Object>> list = getListMapBySQL(sql.toString(), params.toArray());
		return list;
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.ServicememberDao#YearWeekDayemailSaleDate(int, int, int, int)
	 */
	@Override
	public List<Map<String, Object>> YearWeekDayemailSaleDate(String weekendtime, String weekbegtime, int year, int month,String num) {
		if(!StringUtils.hasText(num)){
			return null;
		}
		StringBuffer sql = new StringBuffer("select COUNT(*) AS num, mobile,invitationCode  from member where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		
		if(weekbegtime!=null && weekbegtime!=""){
			sql.append(" and regtime >= ? ");
			params.add(weekbegtime);
		}
		if(weekendtime!=null && weekendtime!=""){
			sql.append(" and regtime <= ? ");
			params.add(weekendtime);
		}
		
		if(year != 0){
			sql.append(" and YEAR(regtime)= ? ");
			params.add(year);
		}
		if(month != 0){
			sql.append(" and MONTH(regtime)= ? ");
			params.add(month);
		}
		
		if(num!=null && num!=""){
			sql.append(" and invitationCode IN ("+num+") ");
		}
		sql.append("  GROUP BY invitationCode ");
		List<Map<String,Object>> list = getListMapBySQL(sql.toString(), params.toArray());
		return list;
	}
}
