package com.ry.core.dao.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.OrgInfoDao;
import com.ry.core.entity.OrgInfo;
import com.ry.core.form.OrgInfoForm;
import com.ry.core.form.OrginfoReportForm;
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
public class OrgInfoDaoImpl extends BaseDao<OrgInfo, Integer> implements OrgInfoDao{
	
	@Override
	public List<OrgInfo> getOrgInfoById(Integer orgId,Integer state) {
		StringBuffer hql = new StringBuffer(" from org_info where 1=1 "); 
		List<Object> paramList = new ArrayList<Object>();
		if(orgId!=null && !"".equals(orgId)){
			hql.append(" and orgId = ?");
			paramList.add(orgId);
		}
		
		if(state!=null && !"".equals(state)){
			hql.append(" and state = ?");
			paramList.add(state);
		}
		List<OrgInfo> list = getListByHQL(hql.toString(), paramList.toArray());
		return list;
	}
	
	@Override
	public void updateOrgInfoById(OrgInfo orgInfo) {
		 update(orgInfo);
	}
	
	@Override
	public OrgInfo getById(Integer id) {
		return get(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrgInfoDao#getCurrentTypeInfoById(java.lang.Integer)
	 */
	public List<Map<String,Object>> getCurrentTypeInfoById(Integer orgId){
		StringBuffer hql = new StringBuffer("SELECT info.* FROM org_info info LEFT JOIN org o ON info.org_id=o.id WHERE info.type_=o.type_ AND o.id=? ORDER BY info.create_time DESC"); 
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(orgId);
		return getListMapBySQL(hql.toString(), paramList.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrgInfoDao#getByOrgIdAndType(java.lang.Integer, java.lang.Integer)
	 */
	public List<Map<String, Object>> getByOrgIdAndType(Integer orgId, Integer type) {
		StringBuffer sql = new StringBuffer("select info.* from org_info info where 1=1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (type != null) {
			sql.append(" and info.type_ = ?");
			paramList.add(type);
			if (type == 0) {
				sql.append(" and info.member_id = ?");
				paramList.add(orgId);
			}else if (type == 1) {
				sql.append(" and info.org_id = ?");
				paramList.add(orgId);
			}
		}
		sql.append(" order by info.id desc");
		return getListMapBySQL(sql.toString(), paramList.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrgInfoDao#saveModel(com.ry.core.entity.OrgInfo)
	 */
	public void saveModel(OrgInfo orgInfo) {
		save(orgInfo);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrgInfoDao#getByOrgId(java.lang.Integer, java.lang.Integer)
	 */
	public List<Map<String,Object>> getByOrgId(Integer orgId,Integer type){
		StringBuffer hql = new StringBuffer("SELECT * FROM org_info WHERE id= (SELECT MAX(id) FROM org_info WHERE org_id=? AND type_=?)"); 
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(orgId);
		paramList.add(type);
		return getListMapBySQL(hql.toString(), paramList.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrgInfoDao#getByMemberIdAndType(java.lang.Integer, java.lang.Integer)
	 */
	public List<Map<String,Object>> getByMemberIdAndType(Integer memberId,Integer type){
		StringBuffer hql = new StringBuffer("SELECT * FROM org_info WHERE id= (SELECT MAX(id) FROM org_info WHERE member_id=? AND type_=?)"); 
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(memberId);
		paramList.add(type);
		return getListMapBySQL(hql.toString(), paramList.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrgInfoDao#getAllOrgName(java.lang.Integer)
	 */
	public List<Map<String, Object>> getAllOrgName(Integer type) {
		StringBuffer sql = new StringBuffer("select oi.org_id orgid, oi.company from org_info oi where 1=1");
		List<Object> paramList = new ArrayList<Object>();
		if (type != null) {
			sql.append(" and oi.type_ = ?");
			paramList.add(type);
		}
		return getListMapBySQL(sql.toString(), paramList.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrgInfoDao#getOrgNameAndLimit(java.lang.Integer, java.lang.String)
	 */
	public List<Map<String, Object>> getOrgNameAndLimit(Integer type, String createTime) {
		StringBuffer sql = new StringBuffer("select oi.phone phone,oi.org_id orgid, oi.company,ol.price from org_info oi "
				+ "left join org_limit ol on oi.org_id = ol.org_id ");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtils.isEmpty(createTime)) {
			sql.append(" and date_format(ol.create_time, '%Y-%m-%d') = ?");
			paramList.add(createTime);
		} else {
			sql.append(" and date_format(ol.create_time, '%Y-%m-%d') = curdate()");			
		}
		if (type != null) {
			sql.append(" where oi.type_ = ?");
			paramList.add(type);
		}
		return getListMapBySQL(sql.toString(), paramList.toArray());
	}
	
	@Override
	public PageResults<Map<String,Object>> getPageList(Integer currentPage, Integer pageSize,OrgInfoForm orgInfoForm) throws ParseException  {
		StringBuilder sql = new StringBuilder();
		StringBuilder sql1 = new StringBuilder();
		StringBuilder sql2 = new StringBuilder();
		StringBuilder sql3 = new StringBuilder();
		StringBuilder price = new StringBuilder();//银票报价记录的条件
		StringBuilder requirementSp = new StringBuilder();//商票报价记录的条件
		StringBuilder orgInfo = new StringBuilder();//银票报价记录的条件
		StringBuilder member = new StringBuilder();//用户条件
		StringBuilder orgExtendInfo = new StringBuilder();//银票报价记录的条件
		StringBuilder str = new StringBuilder();//总条件
		List<Object> params = new ArrayList<Object>();
		List<Object> paramList = new ArrayList<Object>();
		List<Object> paramList1 = new ArrayList<Object>();
		List<Object> paramList2 = new ArrayList<Object>();
		String str1 = "";
		//银票报价时间
		if(StringUtils.hasText(orgInfoForm.getBeginDate())){
			str1 = " AND A.orgId IS NOT NULL";
			price.append(" and create_time >= ?");
			requirementSp.append(" and create_time >= ?");
			paramList.add(DateUtil.parser(orgInfoForm.getBeginDate(),DateUtil.FORMART3));
			paramList1.add(DateUtil.parser(orgInfoForm.getBeginDate(),DateUtil.FORMART3));
		}
		if(StringUtils.hasText(orgInfoForm.getEndDate())){
			str1 = " AND A.orgId IS NOT NULL";
			price.append(" and create_time <= ?");
			requirementSp.append(" and create_time <= ?");
			paramList.add(DateUtil.addDays(DateUtil.parser(orgInfoForm.getEndDate(),DateUtil.FORMART3),1));
			paramList1.add(DateUtil.addDays(DateUtil.parser(orgInfoForm.getEndDate(),DateUtil.FORMART3),1));
		}
		//公司名称
		if(orgInfoForm.getCompany()!= null && !"".equals(orgInfoForm.getCompany().trim())){
			orgInfo.append(" and company like ? ");
			paramList.add("%"+ orgInfoForm.getCompany() +"%");
			paramList1.add("%"+ orgInfoForm.getCompany() +"%");
		}
		//认证时间
		if(StringUtils.hasText(orgInfoForm.getAuthBeginDate())){
			orgInfo.append(" and create_time >= ?");
			paramList.add(DateUtil.parser(orgInfoForm.getAuthBeginDate(),DateUtil.FORMART3));
			paramList1.add(DateUtil.parser(orgInfoForm.getAuthBeginDate(),DateUtil.FORMART3));
		}
		if(StringUtils.hasText(orgInfoForm.getAuthEndDate())){
			orgInfo.append(" and create_time <= ?");
			paramList.add(DateUtil.addDays(DateUtil.parser(orgInfoForm.getAuthEndDate(),DateUtil.FORMART3),1));
			paramList1.add(DateUtil.addDays(DateUtil.parser(orgInfoForm.getAuthEndDate(),DateUtil.FORMART3),1));
		}
		//注册手机号
		if(StringUtils.hasText(orgInfoForm.getMobile())){
			member.append(" and mobile = ?");
			paramList.add(Utility.encodeMobile(orgInfoForm.getMobile()));
			paramList1.add(Utility.encodeMobile(orgInfoForm.getMobile()));
		}
		//注册时间
		if(StringUtils.hasText(orgInfoForm.getRegBeginDate())){
			member.append(" and regtime >= ?");
			paramList.add(DateUtil.parser(orgInfoForm.getRegBeginDate(),DateUtil.FORMART3));
			paramList1.add(DateUtil.parser(orgInfoForm.getRegBeginDate(),DateUtil.FORMART3));
		}
		if(StringUtils.hasText(orgInfoForm.getRegEndDate())){
			member.append(" and regtime <= ?");
			paramList.add(DateUtil.addDays(DateUtil.parser(orgInfoForm.getRegEndDate(),DateUtil.FORMART3),1));
			paramList1.add(DateUtil.addDays(DateUtil.parser(orgInfoForm.getRegEndDate(),DateUtil.FORMART3),1));
		}
		//当前的登录的管理员
		if(orgInfoForm.getAdminId()!=null){
			str.append(" AND A.admin_id=?");
			paramList2.add(orgInfoForm.getAdminId());
		}else{
			//业务员id
			if(orgInfoForm.getServiceMemberId()!=null){
				orgExtendInfo.append(" AND ascription_person=?");
				str.append(" and A.ascription_person IS NOT NULL");
				paramList.add(orgInfoForm.getServiceMemberId());
				paramList1.add(orgInfoForm.getServiceMemberId());
			}
		}
		
		
		//归属状态
		if(orgInfoForm.getAscriptionState()!=null){
			orgExtendInfo.append(" and ascription_state = ?");
			str.append(" and A.ascription_state IS NOT NULL");
			paramList.add(orgInfoForm.getAscriptionState());
			paramList1.add(orgInfoForm.getAscriptionState());
			
		}
		if(orgInfoForm.getPriceType()!=null){
			switch(orgInfoForm.getPriceType()) {  
			    case 1:{//大电银
			    	price.append(" and price_type_id in(2,21)");
			    	break;
			    }
			    case 2:{//小电银
			    	price.append(" and price_type_id in(12,13,14,15,16,17,18,19,20)");
			    	break;
			    }
			    case 3:{//纸银
			    	price.append(" and price_type_id in(5,8,11)");
			    	break;
			    }
			    case 4:{//电商
			    	requirementSp.append(" and type = 2");
			    	break;
			    }
			    case 5:{//纸商
			    	requirementSp.append(" and type = 1");
			    	break;
			    }
			    case 6:{
			    	price.append(" and guogu IS NOT NULL");
			    	break;
			    }
			    case 7:{
			    	price.append(" and chengshang IS NOT NULL");
			    	break;
			    }
			    case 8:{
			    	price.append(" and (nonghe IS NOT NULL OR nongxin IS NOT NULL OR nongshang IS NOT NULL)");
			    	break;
			    }
			    case 9:{
			    	price.append(" and waizi IS NOT NULL");
			    	break;
			    }
			    case 10:{
			    	price.append(" and cunzhen IS NOT NULL");
			    	break;
			    }
			}
			str1 = " and A.orgId IS NOT NULL";
		}
		
		sql.append(" SELECT * FROM (");
		sql1.append(" SELECT p.org_id AS orgId,oi.company AS org,oi.NAME AS `name`,oi.member_id,oi.phone,p.maxt,oi.create_time AS authTime,m.regtime,oe.ascription_state,oe.permit_time,");
		sql1.append(" oe.ascription_person,UNIX_TIMESTAMP(oe.conversion_time) c_time,sm.admin_id,sm.servicemember FROM");
		sql1.append(" (SELECT id,org_id,price_type_id,guogu,nongshang,chengshang,nonghe,nongxin,cunzhen,waizi,MAX(create_time) AS maxt FROM price"); 
		sql1.append(" WHERE 1=1");
		sql1.append(price);
		sql1.append(" GROUP BY org_id) p");
		sql1.append(" RIGHT JOIN");
		sql1.append(" (SELECT * FROM org_info WHERE type_=1 AND state=2");
		sql1.append(orgInfo);
		sql1.append(" ) oi");
		sql1.append(" ON p.org_id=oi.org_id LEFT JOIN"); 
		sql1.append(" (SELECT * FROM member WHERE 1=1");
		sql1.append(member);
		sql1.append(" AND id IN ( SELECT DISTINCT(org.member_id) FROM org_info org WHERE org.type_ =1 AND state =2 )");
		sql1.append(" ) m ON m.id=oi.member_id");
		sql1.append(" LEFT JOIN(SELECT * FROM org_extend_info WHERE 1=1");
		sql1.append(orgExtendInfo);
		sql1.append(" ) oe ON oe.org_info_id=oi.id");
		sql1.append(" LEFT JOIN (select servicenumber,servicemember,id,admin_id FROM servicemember) sm ON oe.ascription_person=sm.id");
		sql2.append(" UNION ALL");
		sql3.append(" SELECT rs.org_id AS orgId,oi.oi.company AS org,`name` AS `name`,oi.member_id,oi.phone,rs.maxt,oi.create_time AS authTime,m.regtime,oe.ascription_state,oe.permit_time,");
		sql3.append(" UNIX_TIMESTAMP(oe.conversion_time) c_time,oe.ascription_person,sm.admin_id,sm.servicemember FROM"); 
		sql3.append(" (SELECT id,`type`,org_id,MAX(create_time) maxt FROM requirement_sp");
		sql3.append(" WHERE 1=1");
		sql3.append(requirementSp);
		sql3.append(" GROUP BY org_id) rs");
		sql3.append(" RIGHT JOIN");
		sql3.append(" (SELECT * FROM org_info WHERE type_=1 AND state=2");
		sql3.append(orgInfo);
		sql3.append(" ) oi");
		sql3.append(" ON rs.org_id=oi.org_id LEFT JOIN"); 
		sql3.append(" (SELECT * FROM member WHERE 1=1 ");
		sql3.append(member);
		sql3.append(" AND id IN ( SELECT DISTINCT(org.member_id) FROM org_info org WHERE org.type_ =1 AND state =2 )");
		sql3.append(" ) m ON m.id=oi.member_id");
		sql3.append(" LEFT JOIN(SELECT * FROM org_extend_info WHERE 1=1");
		sql3.append(orgExtendInfo);
		sql3.append(" ) oe ON oe.org_info_id=oi.id");
		sql3.append(" LEFT JOIN (select servicenumber,servicemember,id,admin_id FROM servicemember) sm ON oe.ascription_person=sm.id");
		if(orgInfoForm.getYin()!=null){
			sql.append(sql1);
			if(paramList.size()>0){
				for(int i=0;i<paramList.size();i++){
					params.add(paramList.get(i));
				}
			}
			if(orgInfoForm.getYin()==0){//银票有报价
				str1 = " and A.orgId IS NOT NULL";
			}else if(orgInfoForm.getYin()==1){//银票无报价
				str1 = " and A.orgId IS NULL";
				if(orgInfoForm.getPriceType()!=null){
					str1 = " and A.orgId IS NULL AND A.orgId IS NOT NULL";
				}
			}
		}else if(orgInfoForm.getShang()!=null){
			sql.append(sql3);
			if(paramList1.size()>0){
				for(int i=0;i<paramList1.size();i++){
					params.add(paramList1.get(i));
				}
			}
			if(orgInfoForm.getShang()==0){//商票有报价
				str1 = " and A.orgId IS NOT NULL";
			}else if(orgInfoForm.getShang()==1){//商票无报价
				str1 = " and A.orgId IS NULL";
				if(orgInfoForm.getPriceType()!=null){
					str1 = " and A.orgId IS NULL AND A.orgId IS NOT NULL";
				}
			}
		}else{
			if(paramList.size()>0){
				for(int i=0;i<paramList.size();i++){
					params.add(paramList.get(i));
				}
			}
			if(paramList1.size()>0){
				for(int i=0;i<paramList1.size();i++){
					params.add(paramList1.get(i));
				}
			}
			sql.append(sql1);
			sql.append(sql2);
			sql.append(sql3);
		}
		sql.append(" )A WHERE A.regtime IS NOT NULL"+ str1+"");
		sql.append(str);
		if(paramList2.size()>0){
			for(int i=0;i<paramList2.size();i++){
				params.add(paramList2.get(i));
			}
		}
		String count1 = "SELECT COUNT(*) FROM ( " + sql.toString() + " ) bt";
		return findPageMapByFetchedSql(sql.toString(),count1,currentPage,pageSize, params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.PriceDao#getbyphone(java.lang.String)
	 */
	public OrgInfo getByPhone(String phone){
		StringBuffer hql = new StringBuffer("from org_info where phone = ? ");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(phone);
		return getByHQL(hql.toString(), paramList.toArray());
	}
	
	public List<Map<String,Object>> getOrginfoId(Integer orginfoId){
		StringBuffer hql = new StringBuffer("SELECT * FROM org_info WHERE  id=? "); 
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(orginfoId);
		return getListMapBySQL(hql.toString(), paramList.toArray());
	}

	@Override
	public List<Map<String, Object>> getByObj(OrgInfoForm orgInfoForm) throws Exception {
		StringBuilder sql = new StringBuilder();
		StringBuilder sql1 = new StringBuilder();
		StringBuilder sql2 = new StringBuilder();
		StringBuilder sql3 = new StringBuilder();
		StringBuilder price = new StringBuilder();//银票报价记录的条件
		StringBuilder requirementSp = new StringBuilder();//商票报价记录的条件
		StringBuilder orgInfo = new StringBuilder();//银票报价记录的条件
		StringBuilder member = new StringBuilder();//用户条件
		StringBuilder orgExtendInfo = new StringBuilder();//银票报价记录的条件
		StringBuilder str = new StringBuilder();//总条件
		List<Object> params = new ArrayList<Object>();
		List<Object> paramList = new ArrayList<Object>();
		List<Object> paramList1 = new ArrayList<Object>();
		List<Object> paramList2 = new ArrayList<Object>();
		String str1 = "";
		//银票报价时间
				if(StringUtils.hasText(orgInfoForm.getBeginDate())){
					str1 = " AND A.orgId IS NOT NULL";
					price.append(" and create_time >= ?");
					requirementSp.append(" and create_time >= ?");
					paramList.add(DateUtil.parser(orgInfoForm.getBeginDate(),DateUtil.FORMART3));
					paramList1.add(DateUtil.parser(orgInfoForm.getBeginDate(),DateUtil.FORMART3));
				}
				if(StringUtils.hasText(orgInfoForm.getEndDate())){
					str1 = " AND A.orgId IS NOT NULL";
					price.append(" and create_time <= ?");
					requirementSp.append(" and create_time <= ?");
					paramList.add(DateUtil.addDays(DateUtil.parser(orgInfoForm.getEndDate(),DateUtil.FORMART3),1));
					paramList1.add(DateUtil.addDays(DateUtil.parser(orgInfoForm.getEndDate(),DateUtil.FORMART3),1));
				}
				//公司名称
				if(orgInfoForm.getCompany()!= null && !"".equals(orgInfoForm.getCompany().trim())){
					orgInfo.append(" and company like ? ");
					paramList.add("%"+ orgInfoForm.getCompany() +"%");
					paramList1.add("%"+ orgInfoForm.getCompany() +"%");
				}
				//认证时间
				if(StringUtils.hasText(orgInfoForm.getAuthBeginDate())){
					orgInfo.append(" and create_time >= ?");
					paramList.add(DateUtil.parser(orgInfoForm.getAuthBeginDate(),DateUtil.FORMART3));
					paramList1.add(DateUtil.parser(orgInfoForm.getAuthBeginDate(),DateUtil.FORMART3));
				}
				if(StringUtils.hasText(orgInfoForm.getAuthEndDate())){
					orgInfo.append(" and create_time <= ?");
					paramList.add(DateUtil.addDays(DateUtil.parser(orgInfoForm.getAuthEndDate(),DateUtil.FORMART3),1));
					paramList1.add(DateUtil.addDays(DateUtil.parser(orgInfoForm.getAuthEndDate(),DateUtil.FORMART3),1));
				}
				//注册手机号
				if(StringUtils.hasText(orgInfoForm.getMobile())){
					member.append(" and mobile = ?");
					paramList.add(Utility.encodeMobile(orgInfoForm.getMobile()));
					paramList1.add(Utility.encodeMobile(orgInfoForm.getMobile()));
				}
				//注册时间
				if(StringUtils.hasText(orgInfoForm.getRegBeginDate())){
					member.append(" and regtime >= ?");
					paramList.add(DateUtil.parser(orgInfoForm.getRegBeginDate(),DateUtil.FORMART3));
					paramList1.add(DateUtil.parser(orgInfoForm.getRegBeginDate(),DateUtil.FORMART3));
				}
				if(StringUtils.hasText(orgInfoForm.getRegEndDate())){
					member.append(" and regtime <= ?");
					paramList.add(DateUtil.addDays(DateUtil.parser(orgInfoForm.getRegEndDate(),DateUtil.FORMART3),1));
					paramList1.add(DateUtil.addDays(DateUtil.parser(orgInfoForm.getRegEndDate(),DateUtil.FORMART3),1));
				}
				//当前的登录的管理员
				if(orgInfoForm.getAdminId()!=null){
					str.append(" AND A.admin_id=?");
					paramList2.add(orgInfoForm.getAdminId());
				}else{
					//业务员id
					if(orgInfoForm.getServiceMemberId()!=null){
						orgExtendInfo.append(" AND ascription_person=?");
						str.append(" and A.ascription_person IS NOT NULL");
						paramList.add(orgInfoForm.getServiceMemberId());
						paramList1.add(orgInfoForm.getServiceMemberId());
					}
				}
				
				
				//归属状态
				if(orgInfoForm.getAscriptionState()!=null){
					orgExtendInfo.append(" and ascription_state = ?");
					str.append(" and A.ascription_state IS NOT NULL");
					paramList.add(orgInfoForm.getAscriptionState());
					paramList1.add(orgInfoForm.getAscriptionState());
					
				}
				if(orgInfoForm.getPriceType()!=null){
					switch(orgInfoForm.getPriceType()) {  
					    case 1:{//大电银
					    	price.append(" and price_type_id in(2,21)");
					    	break;
					    }
					    case 2:{//小电银
					    	price.append(" and price_type_id in(12,13,14,15,16,17,18,19,20)");
					    	break;
					    }
					    case 3:{//纸银
					    	price.append(" and price_type_id in(5,8,11)");
					    	break;
					    }
					    case 4:{//电商
					    	requirementSp.append(" and type = 2");
					    	break;
					    }
					    case 5:{//纸商
					    	requirementSp.append(" and type = 1");
					    	break;
					    }
					    case 6:{
					    	price.append(" and guogu IS NOT NULL");
					    	break;
					    }
					    case 7:{
					    	price.append(" and chengshang IS NOT NULL");
					    	break;
					    }
					    case 8:{
					    	price.append(" and nonghe IS NOT NULL OR nongxin IS NOT NULL OR nongshang IS NOT NULL");
					    	break;
					    }
					    case 9:{
					    	price.append(" and waizi IS NOT NULL");
					    	break;
					    }
					    case 10:{
					    	price.append(" and cunzhen IS NOT NULL");
					    	break;
					    }
					}
					str1 = " and A.orgId IS NOT NULL";
				}
				
				sql.append(" SELECT * FROM (");
				sql1.append(" SELECT p.org_id AS orgId,oi.company AS org,oi.NAME AS `name`,oi.member_id,oi.phone,p.maxt,oi.create_time AS authTime,m.regtime,oe.ascription_state,oe.permit_time,");
				sql1.append(" oe.ascription_person,UNIX_TIMESTAMP(oe.conversion_time) c_time,sm.admin_id,sm.servicemember FROM");
				sql1.append(" (SELECT id,org_id,price_type_id,guogu,nongshang,chengshang,nonghe,nongxin,cunzhen,waizi,MAX(create_time) AS maxt FROM price"); 
				sql1.append(" WHERE 1=1");
				sql1.append(price);
				sql1.append(" GROUP BY org_id) p");
				sql1.append(" RIGHT JOIN");
				sql1.append(" (SELECT * FROM org_info WHERE type_=1 AND state=2");
				sql1.append(orgInfo);
				sql1.append(" ) oi");
				sql1.append(" ON p.org_id=oi.org_id LEFT JOIN"); 
				sql1.append(" (SELECT * FROM member WHERE 1=1");
				sql1.append(member);
				sql1.append(" AND id IN ( SELECT DISTINCT(org.member_id) FROM org_info org WHERE org.type_ =1 AND state =2 )");
				sql1.append(" ) m ON m.id=oi.member_id");
				sql1.append(" LEFT JOIN(SELECT * FROM org_extend_info WHERE 1=1");
				sql1.append(orgExtendInfo);
				sql1.append(" ) oe ON oe.org_info_id=oi.id");
				sql1.append(" LEFT JOIN (select servicenumber,servicemember,id,admin_id FROM servicemember) sm ON oe.ascription_person=sm.id");
				sql2.append(" UNION ALL");
				sql3.append(" SELECT rs.org_id AS orgId,oi.oi.company AS org,`name` AS `name`,oi.member_id,oi.phone,rs.maxt,oi.create_time AS authTime,m.regtime,oe.ascription_state,oe.permit_time,");
				sql3.append(" UNIX_TIMESTAMP(oe.conversion_time) c_time,oe.ascription_person,sm.admin_id,sm.servicemember FROM"); 
				sql3.append(" (SELECT id,`type`,org_id,MAX(create_time) maxt FROM requirement_sp");
				sql3.append(" WHERE 1=1");
				sql3.append(requirementSp);
				sql3.append(" GROUP BY org_id) rs");
				sql3.append(" RIGHT JOIN");
				sql3.append(" (SELECT * FROM org_info WHERE type_=1 AND state=2");
				sql3.append(orgInfo);
				sql3.append(" ) oi");
				sql3.append(" ON rs.org_id=oi.org_id LEFT JOIN"); 
				sql3.append(" (SELECT * FROM member WHERE 1=1 ");
				sql3.append(member);
				sql3.append(" AND id IN ( SELECT DISTINCT(org.member_id) FROM org_info org WHERE org.type_ =1 AND state =2 )");
				sql3.append(" ) m ON m.id=oi.member_id");
				sql3.append(" LEFT JOIN(SELECT * FROM org_extend_info WHERE 1=1");
				sql3.append(orgExtendInfo);
				sql3.append(" ) oe ON oe.org_info_id=oi.id");
				sql3.append(" LEFT JOIN (select servicenumber,servicemember,id,admin_id FROM servicemember) sm ON oe.ascription_person=sm.id");
				if(orgInfoForm.getYin()!=null){
					sql.append(sql1);
					if(paramList.size()>0){
						for(int i=0;i<paramList.size();i++){
							params.add(paramList.get(i));
						}
					}
					if(orgInfoForm.getYin()==0){//银票有报价
						str1 = " and A.orgId IS NOT NULL";
					}else if(orgInfoForm.getYin()==1){//银票无报价
						str1 = " and A.orgId IS NULL";
						if(orgInfoForm.getPriceType()!=null){
							str1 = " and A.orgId IS NULL AND A.orgId IS NOT NULL";
						}
					}
				}else if(orgInfoForm.getShang()!=null){
					sql.append(sql3);
					if(paramList1.size()>0){
						for(int i=0;i<paramList1.size();i++){
							params.add(paramList1.get(i));
						}
					}
					if(orgInfoForm.getShang()==0){//商票有报价
						str1 = " and A.orgId IS NOT NULL";
					}else if(orgInfoForm.getShang()==1){//商票无报价
						str1 = " and A.orgId IS NULL";
						if(orgInfoForm.getPriceType()!=null){
							str1 = " and A.orgId IS NULL AND A.orgId IS NOT NULL";
						}
					}
				}else{
					if(paramList.size()>0){
						for(int i=0;i<paramList.size();i++){
							params.add(paramList.get(i));
						}
					}
					if(paramList1.size()>0){
						for(int i=0;i<paramList1.size();i++){
							params.add(paramList1.get(i));
						}
					}
					sql.append(sql1);
					sql.append(sql2);
					sql.append(sql3);
				}
				sql.append(" )A WHERE A.regtime IS NOT NULL"+ str1+"");
				sql.append(str);
				if(paramList2.size()>0){
					for(int i=0;i<paramList2.size();i++){
						params.add(paramList2.get(i));
					}
				}
		return getListMapBySQL(sql.toString(), params.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrgInfoDao#getOrginfoReport(com.ry.core.form.OrginfoReportForm)
	 */
	@Override
	public PageResults<Map<String, Object>> getOrginfoReport(OrginfoReportForm form) throws ParseException {
		List<Object> paramList = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer("FROM (SELECT r3.*,oefo.* FROM (SELECT r2.* ,discount.cnt_discount FROM (SELECT r1.*,pp.cnt_price FROM "
				+ "(SELECT o.company,o.create_time,o.state,o.type_,o.name,m.`mobile`,m.`qq`,m.`email`,m.`zhiwu`,m.`weixin`,o.`bl_number`,o.member_id,o.address,"
				+ "o.`id` AS oid ,o.`org_id` ,m.`invitationCode` FROM org_info o LEFT JOIN member m  ON o.member_id = m.id  WHERE o.state = 2 AND o.type_ =1 and 1=1"); 
		if(StringUtils.hasText(form.getOrgcompany())){
			hql.append(" and o.company like ?");
			paramList.add("%"+form.getOrgcompany()+"%");
		}
		if(StringUtils.hasText(form.getName())){
			hql.append(" and o.name = ?");
			paramList.add(form.getName());
		}
		if(StringUtils.hasText(form.getPhone())){
			hql.append(" and m.mobile = ?");
			paramList.add(Utility.encodeMobile(form.getPhone()));
		}
		if(StringUtils.hasText(form.getStartDate())){
			hql.append(" and o.create_time >= ?");
			paramList.add(DateUtil.parser(form.getStartDate(),DateUtil.FORMART3));
		}
		if(StringUtils.hasText(form.getEndDate())){
			hql.append(" AND o.create_time < ?");
			paramList.add(DateUtil.addDays(DateUtil.parser(form.getEndDate(),DateUtil.FORMART3),1));
		}
		hql.append(")r1 LEFT JOIN (SELECT DISTINCT COUNT(DATE_FORMAT(p.create_time,'%Y-%m-%d'))AS cnt_price ,p.org_id FROM  price p GROUP BY org_id)");
		hql.append(" pp ON r1.org_id = pp.org_id)r2 LEFT JOIN (SELECT SUM(cnt) AS cnt_discount ,member_id FROM ( SELECT COUNT(id) AS cnt, memberId AS member_id ");
		hql.append(" FROM discountrecord disc WHERE disc.orderflag= 3 UNION ALL SELECT COUNT(id) AS cnt, member_id AS member_id FROM discountrecord_sp disc1 WHERE disc1.orderflag= 3");
		hql.append(" ) AS t GROUP BY member_id) discount ON r2.member_id = discount.member_id)r3 LEFT JOIN org_extend_info oefo");
		hql.append(" ON r3.oid = oefo.`org_info_id`)r4 LEFT JOIN servicemember s ON r4.ascription_person = s.`id` WHERE 1=1 ");
		if(form.getDa_dian_yin() != null && form.getDa_dian_yin()>0){
			hql.append(" and  r4.da_dian_yin = ?");
			paramList.add(form.getDa_dian_yin());
		}
		if(form.getDian_shang() != null && form.getDian_shang()>0){
			hql.append(" and  r4.dian_shang = ?");
			paramList.add(form.getDian_shang());
		}
		if(form.getXiao_zhi_yin() != null){
			if(form.getXiao_zhi_yin() == 1){
				hql.append(" and r4.xiao_zhi_yin = ?");
				paramList.add(form.getXiao_zhi_yin());
			}else if(form.getXiao_zhi_yin() == 2){
				hql.append(" and  r4.xiao_zhi_yin = ?");
				paramList.add(form.getXiao_zhi_yin());
			}
		}
		if(form.getXiao_dian_yin() != null && form.getXiao_dian_yin()>0){
			hql.append(" and  r4.xiao_dian_yin = ?");
			paramList.add(form.getXiao_dian_yin());
		}
		if(form.getZhi_shang() != null && form.getZhi_shang()>0){
			hql.append(" and r4.zhi_shang = ?");
			paramList.add(form.getZhi_shang());
		}
		if(form.getAscription_person() != null && form.getAscription_person()>0){
			hql.append(" and r4.ascription_person = ?");
			paramList.add(form.getAscription_person());
		}
		if(form.getAscription_state() != null && form.getAscription_state()>=0){
			hql.append(" and r4.ascription_state = ?");
			paramList.add(form.getAscription_state());
		}
		if(StringUtils.hasText(form.getOverlay_area())){
			hql.append(" and r4.overlay_area like ?");
			paramList.add("%"+form.getOverlay_area()+"%");
		}
		hql.append(" ORDER BY r4.create_time DESC");
		String sql = new String ("SELECT r4.*,s.`servicemember` "+hql.toString());
		String count = new String("select count(*) "+hql.toString());
		return Utility.decodeMobile(findPageMapByFetchedSql(sql, count, form.currentPage().intValue(), form.getLength().intValue(), paramList.toArray()));
	}

	@Override
	public List<Map<String, Object>> getOrgInfoReportById(Integer Id) {
		List<Object> paramList = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer("FROM (SELECT r3.*,oefo.*,oefo.id extendId FROM (SELECT r2.* ,discount.cnt_discount FROM (SELECT r1.*,pp.cnt_price FROM "
				+ "(SELECT o.company,o.create_time,o.state,o.type_,o.name,m.`mobile`,m.`qq`,m.`email`,m.`zhiwu`,m.`weixin`,o.`bl_number`,o.member_id,o.address,"
				+ "o.`id` AS oid ,o.`org_id` ,m.`invitationCode` FROM org_info o LEFT JOIN member m  ON o.member_id = m.id  WHERE o.state = 2 AND o.type_ =1 and 1=1"); 
		if(Id != null){
			hql.append(" AND o.id = ?");
			paramList.add(Id);
		}
		hql.append(")r1 LEFT JOIN (SELECT DISTINCT COUNT(DATE_FORMAT(p.create_time,'%Y-%m-%d'))AS cnt_price ,p.org_id FROM  price p GROUP BY org_id)");
		hql.append(" pp ON r1.org_id = pp.org_id)r2 LEFT JOIN (SELECT SUM(cnt) AS cnt_discount ,member_id FROM ( SELECT COUNT(id) AS cnt, memberId AS member_id ");
		hql.append(" FROM discountrecord disc WHERE disc.orderflag= 3 UNION ALL SELECT COUNT(id) AS cnt, member_id AS member_id FROM discountrecord_sp disc1 WHERE disc1.orderflag= 3");
		hql.append(" ) AS t GROUP BY member_id) discount ON r2.member_id = discount.member_id)r3 LEFT JOIN org_extend_info oefo");
		hql.append(" ON r3.oid = oefo.`org_info_id`)r4 LEFT JOIN servicemember s ON r4.ascription_person = s.`id` WHERE 1=1 ");
		hql.append(" ORDER BY r4.create_time DESC");
		String sql = new String ("SELECT r4.*,s.`servicemember` "+hql.toString());
		return Utility.decodeMobile(getListMapBySQL(sql,paramList.toArray()));
	}
}