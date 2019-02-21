package com.ry.core.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.ReturnVisitRecordDao;
import com.ry.core.entity.ReturnVisitRecord;
import com.ry.core.form.MemOrder.MemOrderPageRequest;
import com.ry.core.util.Utility;
import com.ry.util.DateUtil;
import com.ry.util.page.PageResults;

@Repository
public class ReturnVisitRecordDaoImpl extends BaseDao<ReturnVisitRecord , Integer>implements ReturnVisitRecordDao{
	@Override
	public PageResults<ReturnVisitRecord> getPageReturn(MemOrderPageRequest mem) {
		Object[] values = new Object[0];
		String hql = "select * from return_visit_record";
		String counthql = "select count(*) from (select * from return_visit_record)r1";
		PageResults<ReturnVisitRecord> pageResults= findPageByFetchedSql(hql,counthql,mem.currentPage().intValue(),mem.getLength().intValue(),values);
		return pageResults;	
	} 
	
	@Override
	public PageResults<Map<String,Object>> getPageSearch(Integer month,Integer state,Long telphone,Integer start,Integer length){
		StringBuffer hql = new StringBuffer("select return_visit_record.*,member.mobile as mobilephone,member.regtime as reg,member.username as name"
				+ " from return_visit_record INNER JOIN member "
				+ " ON return_visit_record.member_id = member.id where 1=1");
		//StringBuffer counthql = new StringBuffer("select count(*) from return_visit_record where 1=1");
		List<Object> paramList = new ArrayList<Object>();
		
		if(month !=null){
			SimpleDateFormat sf = new SimpleDateFormat("yyyy");
			String year = sf.format(new Date());
			String date1 = null;
			String date2 = null;
			if(month<10){
				date1 = year+"-0"+month+"-01";
			}else{
				date1 = year+"-"+month+"-01";
			}
			if(month+1<10){
				date2 = year+"-0"+(month+1)+"-01";
			}else{
				date2 = year+"-"+(month+1)+"-01";
			}
			hql.append(" and create_time between ? and ? ");
			//counthql.append(" and create_time between ? and ? ");
			paramList.add(date1);
			paramList.add(date2);
		}
		if(state != null){
			hql.append(" and return_visit_record.state = ? ");
			//counthql.append(" and state = ? ");
			paramList.add(state);
		}
		if(telphone != null){
			hql.append(" and mobile = ? ");
			//counthql.append(" and mobilephone = ? ");
			String telphone1 = Utility.encodeMobile(telphone.toString());//@WKX EDIT2016-08-17  手机号转码
			paramList.add(telphone1);
		}
		String counthql = "select count(*) from (" + hql+")a";
		PageResults<Map<String,Object>> page = findPageMapByFetchedSql(hql.toString(),counthql.toString(),start,length,paramList.toArray());
		return Utility.decodeMobile(page);//@WKX EDIT2016-08-23 手机号转码
	}
	
	@Override
	public List<ReturnVisitRecord> getList(Integer memberid){
		String hql = "from return_visit_record where memberId = ?";
		List<Object> paras = new ArrayList<Object>();
		paras.add(memberid);
		return getListByHQL(hql, paras.toArray());
	}

	@Override
	public ReturnVisitRecord getById(Integer id) {
		return get(id);
	}
	
	@Override
	public void updateReturnVisitRecord (ReturnVisitRecord returnVisitRecord){
		update(returnVisitRecord);
	}
	
	@Override
	public void saveReturnVisitRecord (ReturnVisitRecord returnVisitRecord){
		save(returnVisitRecord);
	}
	
	@Override
	public List<Map<String,Object>> dingShiOne(Integer n){
		Object[] values = new Object[4];
		Object[] values1 = new Object[4];
		Object[] values2 = new Object[5];
		String[] sql = new String[3];
		//A类取20个
		String sql1 = "SELECT * FROM (SELECT * FROM (SELECT * FROM (SELECT memberid,COUNT(id) amount FROM discountrecord "
				+ " WHERE orderflag=3 and discountrecord.ordertime > ? and discountrecord.ordertime < ?"
				+ " GROUP BY memberid)r1 WHERE r1.amount>2) res1 LEFT JOIN return_visit_record res2 ON res1.memberid=res2.member_id WHERE "
				+ " res1.memberid NOT IN (SELECT temp.member_id FROM return_visit_record temp WHERE temp.create_time "
				+ " BETWEEN ? AND ?) ORDER BY res2.id DESC) result GROUP BY result.memberid limit 0,20";
		//B类取20个
		String sql2 = "SELECT * FROM (SELECT * FROM (SELECT * FROM "
				+ "(SELECT memberid,SUM(allmoney)amount FROM discountrecord WHERE orderflag=3 and discountrecord.ordertime > ? and discountrecord.ordertime < ? "
				+ "GROUP BY memberid)r1"
				+ " WHERE r1.amount>500)res1 LEFT JOIN return_visit_record res2 ON res1.memberid=res2.member_id WHERE "
				+ "	res1.memberid NOT IN (SELECT temp.member_id FROM return_visit_record temp WHERE temp.create_time "
				+ "	BETWEEN ? AND ?) ORDER BY res2.id DESC)result GROUP BY result.memberid limit 0,20";
//		String sql3 = "SELECT * FROM (SELECT * FROM (SELECT m.id memberid,COUNT(dis.id)amount FROM discountrecord dis LEFT JOIN member m ON "
//				+ "dis.memberid=m.id WHERE dis.ordertime BETWEEN m.regtime AND m.regtime+604800000 GROUP BY m.id)res1 LEFT JOIN return_visit_record res2 ON "
//				+ "res1.memberid=res2.member_id WHERE res1.memberid NOT IN (SELECT temp.member_id FROM return_visit_record temp "
//				+ "WHERE temp.create_time BETWEEN ? AND ?) ORDER BY res2.id DESC)result GROUP BY result.memberid limit 0,?";
		//C类取10个当A或B无法满足条件时，都取C。以上3单和500万可以动态维护。
		String sql3 = "SELECT * FROM (SELECT * FROM (select m.regtime,m.id memberid from member m where not exists ("
				+ "	select * from ("
				+ "	select m.id from member m "
				+ "	inner join discountrecord dis ON dis.memberid=m.id and dis.ordertime BETWEEN m.regtime AND m.regtime+604800000 "
				+ "	union"
				+ "	select m.id from member m "
				+ "	inner join return_visit_record res on m.id = res.member_id and res.create_time BETWEEN ? AND ? "
				+ "	group by id)m_ where m.id=m_.id ) and m.regtime > ? and m.regtime < ?"
				+ ")res1 LEFT JOIN return_visit_record res2 ON res1.memberid=res2.member_id "
				+ "	 ORDER BY res2.id DESC)result GROUP BY result.memberid limit 0,?";
		
		sql[0] = sql1;
		sql[1] = sql2;
		sql[2] = sql3;
		
		//配置时间
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
		String date = sf.format(new Date());
		String date1 = date+"-01";
		Date date11 = null;
		try {
			date11 = sf.parse(date1);
		} catch (Exception e) {	
			e.printStackTrace();
		}
		DateUtil dateutils = new DateUtil();
		String date2 = sf.format(dateutils.addMonth(date11, -2));
		String date3 = sf.format(dateutils.addMonth(date11, -1));
		date2 +="-01";
		date3 +="-01";
		//A类时间
		values[0] = date3;
		values[1] = date1;
		values[2] = date2;
		values[3] = date1;
		//B类时间
		values1[0] = date3;
		values1[1] = date1;
		values1[2] = date2;
		values1[3] = date1;
		//C类时间
		values2[0] = date2;
		values2[1] = date1;
		values2[2] = date3;
		values2[3] = date1;
		//Map<String,Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> map= new ArrayList<Map<String,Object>>();
		if(n == 0){
			map = getListMapBySQL(sql1 ,values);
		}
		else if(n == 1){
			map = getListMapBySQL(sql2 ,values1);
		}
		else{
			values2[4] = n;
			map = getListMapBySQL(sql3 ,values2);
		}
		if(map == null){
			return null;
		}else{
			return map;
		}
	}
	
	public ReturnVisitRecord loadReturnVisitRecord(String date,Integer memberid,Integer type){
		List<Object> pramList = new ArrayList<Object>();
		String sql = "from return_visit_record as rv where rv.memberId = ? and type = ? and date_format(createTime,'%Y-%m') =?";
		pramList.add(memberid);
		pramList.add(type);
		pramList.add(date);
		List<ReturnVisitRecord> list=queryByHql(sql,pramList.toArray());
		if(list.size()==0){
			return null;
		}else{
			ReturnVisitRecord rv = list.get(0);
			return rv;
		}
	}
	
	public List<ReturnVisitRecord> getByObj(String time,String state,String memberid){
		String hql = "from return_visit_record where 1=1";
		List<Object> pramList = new ArrayList<Object>();
		if(time != null && time != ""){
			Integer month = Integer.parseInt(time);
			SimpleDateFormat sf = new SimpleDateFormat("yyyy");
			String date = sf.format(new Date());
			String date1 = null;
			if(month<10){
				date1 = date+"-0"+month;
			}else{
				date1 = date+"-"+month;
			}
			
			hql += " and date_format(createTime,'%Y-%m') =?";
			pramList.add(date1);
		}
		if(state != null && state != ""){
			Integer states = Integer.parseInt(state);
			hql += " and state =?";
			pramList.add(states);
		}
		if(memberid != null && memberid != ""){
			Integer memberids = Integer.parseInt(memberid);
			hql += " and member_id =?";
			pramList.add(memberid);
		}
		return getListByHQL(hql,pramList.toArray());
	}
	
	public List<Map<String,Object>> exist(String time,String memberid){
		String sql = "select * from return_visit_record where date_format(create_time,'%Y-%m-%d') = ? and member_id = ?";
		List<Object> pramList = new ArrayList<Object>();
		pramList.add(time);
		pramList.add(memberid);
		return getListMapBySQL(sql,pramList.toArray());
	}
}
