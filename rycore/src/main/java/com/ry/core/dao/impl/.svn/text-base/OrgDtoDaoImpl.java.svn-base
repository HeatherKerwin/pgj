package com.ry.core.dao.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.OrgDtoDao;
import com.ry.core.dto.OrgListDto;
import com.ry.core.form.org.ListRequest;
import com.ry.core.util.Utility;
import com.ry.util.DateUtil;
import com.ry.util.page.PageResults;

/**
 * 名称: OrgDaoImpl.java<br>
 * 描述: <br>
 * 类型: JAVA<br>
 * 最近修改时间:2016年3月2日 下午7:04:32<br>
 * @since  2016年3月2日
 * @author li.xiaofei 
 */
@Repository
public class OrgDtoDaoImpl extends BaseDao<OrgListDto, Integer> implements OrgDtoDao{
	
	private static Logger logger = Logger.getLogger(OrgDtoDaoImpl.class);
	
	@Override
	public PageResults<Map<String,Object>> getPageList(ListRequest list) {
		StringBuffer hql = new StringBuffer("select org.id orgId, org.member_id memId, org.type_ orgType, m.username userName, m.mobile,oi.company, oi.state,oi.create_time createTime from org_info oi inner join org org on org.id = oi.org_id inner join member m on org.member_id = m.id where 1=1 ");
		List<Object> paramList = new ArrayList<Object>();
		if(list.getStartDate() != null){
			hql.append(" and oi.create_time > ?");
			paramList.add(list.getStartDate()+"00:00:00");
		}
		if(list.getEndDate() != null){
			hql.append(" and oi.create_time <= ?");
			paramList.add(list.getEndDate()+"23:59:59");
		}
		if(list.getState() != null){
			hql.append(" and oi.state = ?");
			paramList.add(list.getState());
		}
		if(list.getType() != null){
			hql.append(" and oi.type_ = ?");
			paramList.add(list.getType());
		}
		hql.append(" order by oi.create_time desc ");
		String count = "select count(*) from ( "+ hql.toString() +" ) c";
		PageResults<Map<String,Object>> page = findPageMapByFetchedSql(hql.toString(), count, list.currentPage().intValue(), list.getLength().intValue(), paramList.toArray());	
		return Utility.decodeMobile(page);//@WKX EDIT2016-08-17 手机号转码
	}
	
/**
 * @APP2.2  老版本的用法：因org的调整， 被上面的给替代了
	@Override
	public PageResults<Map<String,Object>> getPageList(ListRequest list) {
		StringBuffer hql = new StringBuffer("select mo.id AS orgId, mo.meId AS memId, mo.orgType AS orgType, mo.username AS userName, info.company AS company,info.state AS state, mo.mobile AS mobile, info.create_time AS createTime from org_info as info , (SELECT org.id AS id, org.member_id AS meId, org.type_ AS orgType, mem.mobile,mem.username AS username FROM  member AS mem , org AS org where org.member_id = mem.id) as mo WHERE mo.id = info.org_id ");
		StringBuffer hqlcount = new StringBuffer("select count(*) from org_info as info , (SELECT org.id AS id, org.member_id AS meId, org.type_ AS orgType, mem.mobile,mem.username AS username FROM  member AS mem , org AS org where org.member_id = mem.id) as mo WHERE mo.id = info.org_id ");
		List<Object> paramList = new ArrayList<Object>();
		if(list.getStartDate() != null){
			hql.append(" and info.create_time > ?");
			hqlcount.append(" and info.create_time > ?");
			paramList.add(list.getStartDate()+"00:00:00");
		}
		
		if(list.getEndDate() != null){
			hql.append(" and info.create_time <= ?");
			hqlcount.append(" and info.create_time <= ?");
			paramList.add(list.getEndDate()+"23:59:59");
		}
		if(list.getState() != null){
			hql.append(" and info.state = ?");
			hqlcount.append(" and info.state = ?");
			paramList.add(list.getState());
		}
		if(list.getType() != null){
			hql.append(" and info.type_ = ?");
			hqlcount.append(" and info.type_ = ?");
			paramList.add(list.getType());
		}
		
		hql.append(" order by info.create_time desc ");
		return findPageMapByFetchedSql(hql.toString(), hqlcount.toString(), list.currentPage().intValue(), list.getLength().intValue(), paramList.toArray());		
	}
*/
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrgDao#getById(java.lang.Integer)
	 */
	public List<Map<String,Object>> getInfoById(Integer id){
		StringBuffer sql = new StringBuffer("SELECT o.id orgId,o.type_ orgType,info.* FROM org o LEFT JOIN org_info info ON o.id=info.org_id AND info.state=2 AND o.type_=info.type_ WHERE o.id=?");
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		return getListMapBySQL(sql.toString(), params.toArray());
	}

	@Override
	public PageResults<Map<String, Object>> getMemberPageList(ListRequest list) {
		StringBuffer hql = new StringBuffer("");
		List<Object> paramList = new ArrayList<Object>();
		if(list.getType()==1){//机构
			if(list.getImagecheck()!=null  && list.getImagecheck()==2){//机构全部认证通过
				hql.append(" SELECT * FROM (SELECT * FROM (SELECT oi.id infoId,oi.org_id orgId,oi.name,oi.member_id memId, oi.type_ orgType,m.username userName, m.mobile ,m.invitationCode code,m.regtime,oi.company,oi.state,oi.create_time createTime FROM member m LEFT JOIN org_info oi ON oi.member_id = m.id AND oi.org_id IN (SELECT img.org_id FROM org_image img WHERE img.state=2) WHERE 1=1");
			}else{//机构票据审核未通过
				hql.append(" SELECT * FROM (SELECT * FROM (SELECT oi.id infoId,oi.org_id orgId,oi.name,oi.member_id memId, oi.type_ orgType,m.username userName, m.mobile ,m.invitationCode code,m.regtime,oi.company,oi.state,oi.create_time createTime FROM member m LEFT JOIN org_info oi ON oi.member_id = m.id LEFT JOIN org org ON org.member_id = oi.`member_id`  LEFT JOIN org_image orgimg ON orgimg.org_id = oi.org_id WHERE 1=1");
			}
		}else if(list.getType()==0){//企业
			hql.append(" SELECT oi.id infoId,oi.org_id orgId,oi.member_id memId, oi.type_ orgType,oi.name,oi.create_time createTime,m.username userName, m.mobile,m.invitationCode code,m.regtime,oi.company,oi.state FROM member m LEFT JOIN org_info oi ON oi.member_id = m.id WHERE 1=1 ");
		}
		if(list.getStartDate() != null && list.getStartDate() != ""){
			try {
				hql.append(" and oi.create_time > ?");
				paramList.add(DateUtil.parser(list.getStartDate(), DateUtil.FORMART3));
			} catch (ParseException e) {
				logger.info(e.getMessage());
			}
		}
		if(list.getEndDate() != null && list.getEndDate() != ""){
			try {
				hql.append(" and oi.create_time < ?");
				paramList.add(DateUtil.addDays(DateUtil.parser(list.getEndDate(), DateUtil.FORMART3), 1));
			} catch (ParseException e) {
				logger.info(e.getMessage());
			}
		}
		if(list.getStartRegDate() != null && list.getStartRegDate() != ""){
			try {
				hql.append(" and m.regtime > ?");
				paramList.add(DateUtil.addDays(DateUtil.parser(list.getStartRegDate(), DateUtil.FORMART3), 1));
			} catch (ParseException e) {
				logger.info(e.getMessage());
			}
		}
		if(list.getEndRegDate() != null && list.getEndRegDate() != ""){
			try {
				hql.append(" and m.regtime < ?");
				paramList.add(DateUtil.addDays(DateUtil.parser(list.getEndRegDate(), DateUtil.FORMART3), 1));
			} catch (ParseException e) {
				logger.info(e.getMessage());
			}
		}
		if(list.getType()==0 && list.getState()==-1){//企业
			hql.append(" and oi.state in (0,1,2,3)");
		}else if(list.getType()==1 && list.getState()==-1){//机构
			hql.append(" AND (org.state IN (0,1,2,3) OR oi.state IN (0,1,2,3) OR orgimg.state IN (0,1,2,3))");
		}
		if(list.getState()==0){//(0.未认证，1.审核中，2.已认证，3.未通过)
			hql.append(" AND oi.state = 0");
		}else if(list.getState()==1){//(0.未认证，1.审核中，2.已认证，3.未通过)
			hql.append(" AND oi.state = 1");
		}else if(list.getState()==2){//(0.未认证，1.审核中，2.已认证，3.未通过)
			hql.append(" AND oi.state = 2");
		}else if(list.getState()==3){//(0.未认证，1.审核中，2.已认证，3.未通过)
			hql.append(" AND oi.state = 3");
		}	
		if(list.getType() != null){
			hql.append(" and oi.type_ = ?");
			paramList.add(list.getType());
		}
		if(list.getPhone() != null && list.getPhone() != ""){
			hql.append(" and m.mobile = ?");
			paramList.add(Utility.encodeMobile(list.getPhone()));
		}
		if(list.getName() != null && list.getName() != ""){
			hql.append(" and oi.name = ?");
			paramList.add(list.getName());
		}
		if(list.getCompanyName() != null && list.getCompanyName() != ""){
			hql.append(" and oi.company like ?");
			paramList.add("%"+list.getCompanyName()+"%");
		}
		if(list.getType() ==1){
			hql.append(" )as cc order by cc.infoId desc) as m GROUP BY m.orgId ORDER BY m.infoId DESC");
		}else{
			hql.append(" order by oi.create_time desc");
		}
		
		
		String count = "select count(*) from ( "+ hql.toString() +" ) c";
		PageResults<Map<String, Object>> page = findPageMapByFetchedSql(hql.toString(), count, list.currentPage().intValue(), list.getLength().intValue(), paramList.toArray());		
		return Utility.decodeMobile(page);//@WKX EDIT2016-08-17 手机号转码
	}

	@Override
	public List<Map<String, Object>> getByObj(ListRequest list) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		StringBuffer sql = new StringBuffer("select org.id orgId, org.member_id memId, org.type_ orgType, m.username userName, m.mobile,oi.company, oi.state,oi.create_time createTime from org_info oi inner join org org on org.id = oi.org_id inner join member m on org.member_id = m.id where 1=1 ");
		List<Object> paramList = new ArrayList<Object>();
		if(list.getStartDate() != null){
			sql.append(" and oi.create_time > ?");
			paramList.add(list.getStartDate()+"00:00:00");
		}
		if(list.getEndDate() != null){
			sql.append(" and oi.create_time <= ?");
			paramList.add(list.getEndDate()+"23:59:59");
		}
		if(list.getState() != null){
			sql.append(" and oi.state = ?");
			paramList.add(list.getState());
		}
		if(list.getType() != null){
			sql.append(" and oi.type_ = ?");
			paramList.add(list.getType());
		}
		sql.append(" order by oi.create_time desc ");
		result = getListMapBySQL(sql.toString(),paramList.toArray());
		return Utility.decodeMobile(result);//@ZWD EDIT2016-08-17 手机号转码
	}
}