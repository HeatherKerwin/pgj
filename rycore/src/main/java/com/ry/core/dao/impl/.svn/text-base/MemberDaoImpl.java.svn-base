package com.ry.core.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.MemberDao;
import com.ry.core.entity.Member;
import com.ry.core.form.MemberForm;
import com.ry.core.util.Utility;
import com.ry.util.page.PageResults;
@Repository
public class MemberDaoImpl extends BaseDao<Member,Integer > implements MemberDao{

	/* (non-Javadoc)
	 * @see com.ry.core.dao.MemberDao#countMember(com.ry.core.entity.Member)
	 */
	@Override
	public Long countMember(MemberForm m) {
		StringBuffer hql = new StringBuffer(" select count(*) from member where 1 = 1"); 
		List<Object> paramList = new ArrayList<Object>();
		if(m!=null){
			if(StringUtils.hasText(m.getRecommendpeople())){
				hql.append(" and recommendpeople = ?");
				paramList.add(m.getRecommendpeople());
			}
			if(m.getBeginRegDate()!=null){
				hql.append(" and regtime >= ?");
				paramList.add(m.getBeginRegDate());
			}
			if(m.getEndRegDate()!=null){
				hql.append(" and regtime < ?");
				paramList.add(m.getEndRegDate());
			}
			if (m.getQudao() != null) {
				hql.append(" and qudao = ? ");
				paramList.add(m.getQudao());
			}
			if (m.getHezuo() != null) {
				hql.append(" and hezuo = ? ");
				paramList.add(m.getHezuo());
			}
		}
		return countByHql(hql.toString(), paramList.toArray());
	}

	@Override
	public List<Member> getList(String id) {
		StringBuffer hql = new StringBuffer(" from member where 1=1 "); 
		List<Object> paramList = new ArrayList<Object>();
		if(id!=null&&!"".equals(id)){
			hql.append(" and id = ?");
			paramList.add(Integer.parseInt(id));
		}
		List<Member> list = getListByHQL(hql.toString(), paramList.toArray());
		return Member.deMember(list);//@WKX EDIT2016-08-19 手机号转码
	}

	@Override
	public void updateMember(Member member) {
		update(Member.enMember(member));//@WKX EDIT2016-08-19 手机号转码
	}

	@Override
	public List<Member> getList(Integer id, String username) {
		String hql = "from member where 1=1 ";
		List<Object> paramList = new ArrayList<Object>();
		List<Member> memberList;
		if (username != null) {
			hql += " and username like ? ";						
			paramList.add(username);
		} 
		if (id != null) {
			hql += " and id like ? ";						
			paramList.add(id);
		}		
		memberList = getListByHQL(hql, paramList.toArray());
		return Member.deMember(memberList);//@WKX EDIT2016-08-19 手机号转码
	}

	@Override
	public Member getMember(Integer id, String username, String password) {
		String hql = "from member where 1=1 ";
		List<Object> paramList = new ArrayList<Object>();
		if (id != null) {
			hql += " and id like ? ";
			paramList.add(id);
		}
		if (username != null) {
			hql += " and username like ? ";
			paramList.add(username);
		}
		if (password != null) {
			hql += " and password like ? ";
			paramList.add(password);
		}		
		Member member = (Member)getByHQL(hql,paramList.toArray());		
		return Member.deMember(member);//@WKX EDIT2016-08-19 手机号转码
	}

	@Override
	public List<Member> getList(Date begintimed, Date endtimed, String recommendpeople) {
		StringBuilder hql = new StringBuilder("from member where 1=1 ");
		List<Object> paramList = new ArrayList<Object>();
		
		if(StringUtils.hasText(recommendpeople)){
			hql.append("and recommendpeople = ?");
			paramList.add(recommendpeople);
		}
		
		if(begintimed !=null){
			hql.append(" and regtime >= ?");
			paramList.add(begintimed);
		}	
		
		if(endtimed != null){
			hql.append(" and regtime < ?");
			paramList.add(endtimed);
		}		
		List<Member> list = queryByHql(hql.toString(), paramList.toArray());
		return Member.deMember(list);//@WKX EDIT2016-08-19 手机号转码
	}

	@Override
	public PageResults<Member> getPageResults(Date begintimed, Date endtimed, String recommendpeople, int pageNo, int pageSize) {
		
		StringBuilder hql = new StringBuilder("from member where 1=1 ");
		
		StringBuilder hqlcount = new StringBuilder(" select count(*) from member where 1 = 1 "); 		
		
		List<Object> paramList = new ArrayList<Object>();
		
		if(StringUtils.hasText(recommendpeople)){
			hql.append(" and recommendpeople = ?");
			hqlcount.append(" and recommendpeople = ?");
			paramList.add(recommendpeople);
		}
		
		if(begintimed !=null){
			hql.append(" and regtime >= ?");
			hqlcount.append(" and regtime >= ?");
			paramList.add(begintimed);
		}	
		
		if(endtimed != null){
			hql.append(" and regtime < ?");
			hqlcount.append(" and regtime < ?");
			paramList.add(endtimed);
		}		
		hql.append(" order by id desc ");
		PageResults<Member> pageResults = findPageByFetchedHql(hql.toString(), hqlcount.toString(), pageNo, pageSize, paramList.toArray()); 
		
		return Member.deMember(pageResults);//@WKX EDIT2016-08-19 手机号转码
	}

	@Override
	public void addMember(Member member)throws Exception{
		save(Member.enMember(member));//@WKX EDIT2016-08-19 手机号转码
	}

	@Override
	public Member yonghu(String mobile) {
		mobile = Utility.encodeMobile(mobile);//@WKX EDIT2016-08-17 手机号转码
		StringBuffer hql = new StringBuffer(" from member where 1=1 "); 
		List<Object> paramList = new ArrayList<Object>();
		if(StringUtils.hasText(mobile)){
			hql.append(" and mobile = ?");
			paramList.add(mobile);
		}
		List<Member> list = getListByHQL(hql.toString(), paramList.toArray());
		if(list!=null && list.size()>0){
			return Member.deMember(list.get(0));
		}else{
			return null;
		}
	}

	@Override
	public List<Member> getList(String mobile, String password) {
		mobile = Utility.encodeMobile(mobile);//@WKX EDIT2016-08-17 手机号转码
		StringBuilder hql = new StringBuilder("from member where 1=1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (mobile != null) {
			hql.append(" and mobile like ? ");
			paramList.add(mobile);
		}
		if (password != null) {
			hql.append(" and pwd like ? ");
			paramList.add(password);
		}		
		List<Member> members = getListByHQL(hql.toString(), paramList.toArray());
		return Member.deMember(members);//@WKX EDIT2016-08-19 手机号转码
	}

	@Override
	public List<Member> getListByMobile(String mobile) {
		mobile = Utility.encodeMobile(mobile);//@WKX EDIT2016-08-17  手机号转码
		StringBuffer hql = new StringBuffer(" from member where 1=1 "); 
		List<Object> paramList = new ArrayList<Object>();
		if(mobile!=null&&!"".equals(mobile)){
			hql.append(" and mobile = ?");
			paramList.add(mobile);
		}
		List<Member> list = getListByHQL(hql.toString(), paramList.toArray());
		return Member.deMember(list);//@WKX EDIT2016-08-19 手机号转码
	}

	@Override
	public void updatepwdMember(Member member) {
		update(Member.enMember(member));//@WKX EDIT2016-08-19 手机号转码
	}
	
	@Override
	public Member getMember(String mobile) {
		mobile = Utility.encodeMobile(mobile);//@WKX EDIT2016-08-17  手机号转码
		String hql = "from member where 1=1 ";
		List<Object> paramList = new ArrayList<Object>();
		if (mobile != null) {
			hql += " and mobile like ? ";
			paramList.add(mobile);
		}
		Member member = getByHQL(hql,paramList.toArray());		
		return Member.deMember(member);//@WKX EDIT2016-08-19 手机号转码
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.MemberDao#countMemberBySpecialSale(java.lang.String)
	 */
	@Override
	public List<?> countMemberBySpecialSale(String saleNums) {
		String sql = "SELECT count(*) as monthNum,sum(case when DATE_FORMAT(date,'%Y-%m-%d') = DATE_FORMAT(now(),'%Y-%m-%d') then 1 else 0 end) dayNum from (SELECT *,FROM_UNIXTIME(m.regtime/1000,'%Y-%m-%d %H:%i:%s') date from member m) m1 where MONTH(m1.date)=MONTH(now()) and m1.recommendpeople in ("+saleNums+") and (m1.hezuo!='2' or m1.hezuo is null) ";
		return getListBySQL(sql, null);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.MemberDao#getByMyInvitationCode(java.lang.String)
	 */
	public List<Member> getByMyInvitationCode(String myInvitationCode) {
		StringBuilder hql = new StringBuilder("from member where myInvitationCode=?");
		List<Member> members = getListByHQL(hql.toString(), new String[]{myInvitationCode});
		return Member.deMember(members);//@WKX EDIT2016-08-19 手机号转码
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.MemberDao#getByInvitationCode(java.lang.String)
	 */
	public Long getByInvitationCode(String invitationCode) {
		StringBuilder hql = new StringBuilder("SELECT count(*) from member where invitationCode=?");
		return countByHql(hql.toString(), new String[]{invitationCode});
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.MemberDao#getById(java.lang.Integer)
	 */
	public Member getById(Integer id) {
		Member member = get(id);
		return Member.deMember(member);//@WKX EDIT2016-08-19 手机号转码
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.MemberDao#getByMyInvitationCodeISNULL()
	 */
	public List<Member> getByMyInvitationCodeISNULL() {
		StringBuffer hql = new StringBuffer("from member where myInvitationCode IS NULL"); 
		List<Member> list = getListByHQL(hql.toString(),null);
		return Member.deMember(list);//@WKX EDIT2016-08-19 手机号转码
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.MemberDao#getFromHongbaoCountByStartAndEnd(java.lang.Integer, java.util.Date, java.util.Date)
	 */
	public List<Map<String,Object>> getFromHongbaoCountByStartAndEnd(Integer hid,Date start,Date end) {
		StringBuffer sql = new StringBuffer("SELECT result.createtime,COUNT(result.id)AS amount FROM(SELECT h.createtime AS D,m.* FROM hongbaoDetail h LEFT JOIN (SELECT id,mobile,username,FROM_UNIXTIME(regtime/1000,'%Y-%m-%d')AS createtime FROM member");
		sql.append(") m ON h.memberid=m.id WHERE 1=1");
		List<Object> params = new ArrayList<Object>();
		if(hid!=null){
			sql.append(" and h.hid=?");
			params.add(hid);
		}
		if(start!=null && end!=null){
			sql.append(" and h.createtime BETWEEN ? and ?");
			params.add(start);
			params.add(end);
		}
		sql.append(" AND m.createtime=DATE_FORMAT(h.createtime,'%Y-%m-%d'))result");
		return getListMapBySQL(sql.toString(), params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.MemberDao#getCountByStartAndEnd(java.util.Date, java.util.Date)
	 */
	public List<Map<String,Object>> getCountByStartAndEnd(Date start,Date end) {
		StringBuffer sql = new StringBuffer("SELECT COUNT(id)AS amount FROM member WHERE 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if(start!=null && end!=null){
			sql.append(" and FROM_UNIXTIME(regtime/1000) BETWEEN ? AND ?");
			params.add(start);
			params.add(end);
		}
		return getListMapBySQL(sql.toString(), params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.MemberDao#getMemberAndOrgInfoByMemberId(java.lang.Integer)
	 */
	public List<Map<String,Object>> getMemberAndOrgInfoByMemberId(Integer memberId) {
		StringBuffer sql = new StringBuffer("SELECT r1.mobile,r1.username,r1.province,r1.city,r1.place,r2.* FROM member r1 LEFT JOIN (SELECT o.id orgId,info.company,o.member_id FROM org o LEFT JOIN org_info info ON o.id=info.org_id AND o.type_=info.type_ WHERE info.state=2)r2 ON r1.id=r2.member_id WHERE r1.id=?");
		List<Object> params = new ArrayList<Object>();
		params.add(memberId);
		
		List<Map<String,Object>> list = getListMapBySQL(sql.toString(), params.toArray());
		return Utility.decodeMobile(list);//@WKX EDIT2016-08-17  手机号转码
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.MemberDao#getMemberAndNum(java.lang.Integer)
	 */
	public List<Map<String,Object>> getMemberAndNum(Integer memberId){
		StringBuffer sql = new StringBuffer("SELECT m1.myInvitationCode, m1.invitationCode ,m2.num FROM (SELECT myInvitationCode ,invitationCode FROM  member WHERE  id= ?)m1 LEFT JOIN(SELECT COUNT(m.invitationCode) AS num,m.myInvitationCode AS mmy FROM member m WHERE m.invitationCode =(SELECT myInvitationCode FROM  member WHERE  id= ?))m2 ON m2.mmy = m1.invitationCode");
		List<Object> params = new ArrayList<Object>();
		params.add(memberId);
		
		List<Map<String,Object>> list = getListMapBySQL(sql.toString(), params.toArray());
		return Utility.decodeMobile(list);//@WKX EDIT2016-08-17  手机号转码
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.MemberDao#getInfoByOrgId(java.lang.Integer)
	 */
	public List<Map<String,Object>> getInfoByOrgId(Integer orgId) {
		StringBuffer sql = new StringBuffer("SELECT m.* FROM member m LEFT JOIN org o ON m.id=o.member_id WHERE o.id=?");
		List<Object> params = new ArrayList<Object>();
		params.add(orgId);
		
		List<Map<String,Object>> list = getListMapBySQL(sql.toString(), params.toArray());
		return Utility.decodeMobile(list);//@WKX EDIT2016-08-17  手机号转码
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.MemberDao#getListByRegTime(java.lang.Long, java.lang.Long)
	 */
	public List<Map<String, Object>> getListByRegTime3(Date startRegTime, Date endRegTime) {
		StringBuffer sql = new StringBuffer("select m.id,tab1.servicemember,tab1.servicenumber,m.recommendpeople,m.invitationCode,m.myInvitationCode,m.regtime from (select sm.servicemember,sm.servicenumber,m.id,m.invitationCode,m.myInvitationCode from servicemember sm inner join member m on sm.servicenumber = m.recommendpeople where m.regtime between ? and ?) tab1");
		sql.append(" inner join member m on m.invitationCode = tab1.myInvitationCode where m.regtime between ? and ? ");
		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(startRegTime);
		paramsList.add(endRegTime);
		paramsList.add(startRegTime);
		paramsList.add(endRegTime);
		return getListMapBySQL(sql.toString(), paramsList.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.MemberDao#getListBy(java.lang.Long, java.lang.Long)
	 */
	public List<Map<String, Object>> getListByRegTime2(Date startRegTime, Date endRegTime) {
		StringBuffer sql = new StringBuffer("select sm.id smid,sm.servicemember,sm.servicenumber,m.id,m.invitationCode,m.myInvitationCode,m.regtime from member m inner join servicemember sm on sm.servicenumber = m.recommendpeople where m.regtime between ? and ? ");
		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(startRegTime);
		paramsList.add(endRegTime);
		return getListMapBySQL(sql.toString(), paramsList.toArray());
	}
	
	public List<Member> getByRecommendpeople(String servicenumber, Date startRegTime, Date endRegTime) {
		StringBuilder hql = new StringBuilder("from member m where 1=1 ");
		List<Object> paramsList = new ArrayList<Object>();
		if (!"".equals(servicenumber.trim())) {
			hql.append(" and m.recommendpeople=? ");
			paramsList.add(servicenumber);
		}
		if (startRegTime != null && endRegTime != null) {
			hql.append(" and m.regtime between ? and ? ");
			paramsList.add(startRegTime);
			paramsList.add(endRegTime);
		}
		List<Member> list = getListByHQL(hql.toString(), paramsList.toArray());
		return Member.deMember(list);//@WKX EDIT2016-08-19 手机号转码
	}
	
	public List<Member> getByMyInvitationCode(String myInvitationCode, Date startRegTime, Date endRegTime) {
		StringBuilder hql = new StringBuilder("from member where 1=1 ");
		List<Object> paramsList = new ArrayList<Object>();
		if (!"".equals(myInvitationCode.trim())) {
			hql.append(" and invitationCode=? ");
			paramsList.add(myInvitationCode);
		}
		if (startRegTime != null && endRegTime != null) {
			hql.append(" and regtime between ? and ? ");
			paramsList.add(startRegTime);
			paramsList.add(endRegTime);
		}
		List<Member> list = getListByHQL(hql.toString(), paramsList.toArray());
		return Member.deMember(list);//@WKX EDIT2016-08-19 手机号转码
	}
	
	@Override
	public List<Map<String, Object>> getByThird(String openId, Integer type) {
		StringBuffer sql = new StringBuffer("SELECT m.* FROM member m, third_auth t WHERE m.id = t.member_id ");
		if(type==1){//微信方式
			sql.append(" AND t.wechat_id = ?  ");
		}else if(type==2){//QQ方式
			sql.append(" AND t.qq_id = ?  ");
		}else if(type==3){//微博方式
			sql.append(" AND t.sina_id = ? ");
		}
		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(openId);
		List<Map<String,Object>> list = getListMapBySQL(sql.toString(), paramsList.toArray());
		return Utility.decodeMobile(list);//@WKX EDIT2016-08-17  手机号转码
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.MemberDao#getModelById(java.lang.Integer)
	 */
	public Member getModelById(Integer id) {
		return get(id);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.MemberDao#getGroomList(java.util.Date, java.util.Date)
	 */
	@Override
	public List<Map<String, Object>> getGroomList(String begin, String end,String operate) {
		StringBuffer hql = new StringBuffer("SELECT aa.recommendpeople ,c.activefate ,aa.mobile FROM (SELECT * FROM member mm WHERE mm.`id` IN (SELECT memberId FROM (SELECT a.memberId ,DATE_FORMAT(a.actionDate,'%Y-%m-%d') DATE FROM appVisitLog a WHERE  a.memberId IN( " +
                 " SELECT id FROM member m WHERE  m.regtime >= '"+ begin +"' AND   m.regtime < '"+ end +"'  AND m.recommendpeople IN ("+ operate +") " +		
				" ) GROUP BY  a.memberId,DATE) temp  GROUP BY memberId) ) AS aa LEFT JOIN (SELECT memberId ,COUNT(0) AS activefate FROM (SELECT a.memberId ,DATE_FORMAT(a.actionDate,'%Y-%m-%d') DATE FROM appVisitLog a WHERE  a.memberId "+
				" IN( SELECT id FROM member m WHERE  m.regtime >= '"+ begin +"' AND   m.regtime < '"+ end +"' AND m.recommendpeople IN ("+ operate +") " +
				" ) GROUP BY  a.memberId,DATE) temp  GROUP BY memberId) AS c ON aa.id = c.memberId "); 
		List<Object> paramList = new ArrayList<Object>();
		List<Map<String,Object>> list = getListMapBySQL(hql.toString(), paramList.toArray());
		return  Utility.decodeMobile(list);//@WKX EDIT2016-08-17  手机号转码
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.MemberDao#updateModelPwd(java.lang.String, java.lang.String)
	 */
	@Override
	public void updateModelPwd(String pwd, String mobile) {
		mobile = Utility.encodeMobile(mobile);//@MH EDIT2016-08-17  手机号转码
		String hql = "update member set pwd = '"+pwd+"' where mobile = '"+mobile+"'";
		executeHql(hql,null);
	}
	
	public BigInteger countByStartAndEndAndMemberId(Date start,Date end,Integer memberId) {
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM (SELECT DISTINCT DATE_FORMAT(_t,'%Y-%m-%d')_t  FROM(");
		List<Object> params = new ArrayList<Object>();
		
		sql.append("SELECT actiontime _t FROM actionlog WHERE memberId=?");
		params.add(memberId);
		if(start !=null && end !=null){
			sql.append(" AND actiontime BETWEEN ? AND ?");
			params.add(start);
			params.add(end);
		}
		
		sql.append(" UNION ");
		
		sql.append("SELECT actionDate _t FROM appVisitLog WHERE memberId=?");
		params.add(memberId);
		if(start !=null && end !=null){
			sql.append(" AND actionDate BETWEEN ? AND ?");
			params.add(start);
			params.add(end);
		}
		sql.append(")res)temp");
		return countBySql(sql.toString(), params.toArray());
	}

	@Override
	public PageResults<Map<String, Object>> getPageSalesMember(Integer pageIndex, Integer pageSize, String begTime,
			String endTime, String mobile, Integer isAccreditation,Integer admin) {
		List<Object> paras = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder("FROM (SELECT m.regtime,m.id,m.mobile,o.name,o.member_id,o.company,o.state,o.id oId FROM member m "
				+ "LEFT JOIN  org_info o ON m.id = o.member_id WHERE 1=1");
		if(StringUtils.hasText(begTime)){
			sql.append(" and m.regtime > ?");
			paras.add(begTime);
		}
		if(StringUtils.hasText(endTime)){
			sql.append(" and m.regtime <= ?");
			paras.add(endTime);
		}
		if(StringUtils.hasText(mobile)){
			mobile = Utility.encodeMobile(mobile);
			sql.append(" AND m.mobile = ?");
			paras.add(mobile);
		}
		if(isAccreditation != null && isAccreditation ==0){//未认证
			sql.append(" AND(o.state IS NULL || o.state =1 || o.state=3)");
		}
		if(isAccreditation != null && isAccreditation ==1){//已认证
			sql.append(" AND o.state = 2");
		}
		sql.append(" ORDER BY m.regtime DESC )ct1 LEFT JOIN (SELECT oo.org_info_id,s.servicemember ,s.admin_id FROM org_extend_info oo LEFT JOIN servicemember s ON");
		sql.append(" oo.ascription_person = s.id WHERE 1=1)oe ON oe.org_info_id = oId WHERE 1=1");
		if(admin!=null){//已认证
			sql.append(" AND oe.admin_id = ?");
			paras.add(admin);
		}
		sql.append(" AND (oId IS NULL OR oId IN(");
		sql.append(" SELECT MAX(oId) FROM (SELECT m.regtime,m.id,m.mobile,o.name,o.member_id,o.company,o.state,o.id oId FROM member m ");
		sql.append(" LEFT JOIN (SELECT * FROM org_info WHERE type_ = 1)o ON m.id = o.member_id WHERE 1=1 ");
		if(StringUtils.hasText(begTime)){
			sql.append(" and m.regtime > ?");
			paras.add(begTime);
		}
		if(StringUtils.hasText(endTime)){
			sql.append(" and m.regtime <= ?");
			paras.add(endTime);
		}
		sql.append(" )ct GROUP BY id))");
		String count = "select count(*)" + sql.toString();
		PageResults<Map<String, Object>> list = findPageMapByFetchedSql("SELECT * " + sql.toString(),
				count, pageIndex, pageSize, paras.toArray());
		return Utility.decodeMobile(list);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.MemberDao#getNoDistributionMember(java.util.Date)
	 */
	@Override
	public List<Map<String, Object>> getNoDistributionMember(String date,String hezuo) {
		StringBuffer sql = new StringBuffer("SELECT m.id FROM member m WHERE DATE_FORMAT(m.regtime ,'%Y-%m-%d') = ? AND (m.recommendpeople IS NULL OR m.recommendpeople = '')");
		List<Object> params = new ArrayList<Object>();
		params.add(date);
		if(StringUtils.hasText(hezuo)){
			sql.append(" AND m.hezuo=?");
			params.add(hezuo);
		}
		sql.append(" ORDER BY m.regtime ASC");
		List<Map<String,Object>> list = getListMapBySQL(sql.toString(), params.toArray());
		return list;
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.MemberDao#getLastDistributionMember(java.lang.Integer)
	 */
	@Override
	public List<Map<String, Object>> getLastDistributionMember(String type) {
		StringBuffer sql = new StringBuffer("SELECT m.recommendpeople FROM member m WHERE id = (SELECT MAX(id)FROM member WHERE recommendpeople IN("
				+ " SELECT servicenumber FROM servicemember WHERE flag = 0 AND service_customer = ?))");
		List<Object> params = new ArrayList<Object>();
		params.add(type);
		List<Map<String,Object>> list = getListMapBySQL(sql.toString(), params.toArray());
		return list;
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.MemberDao#getAuthenticationNoDistributionMember(java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> getAuthenticationNoDistributionMember(String type) {
		StringBuffer sql = new StringBuffer("SELECT DISTINCT member_id FROM org_info o LEFT JOIN member m ON o.member_id = m.id LEFT JOIN "
				+ "(SELECT * FROM servicemember WHERE service_customer = ?)s ON m.recommendpeople = s.servicenumber "
				+ " WHERE  cib_id IS NOT NULL && cib_id !='' AND s.servicenumber IS NULL OR m.recommendpeople = '' ORDER BY m.regtime ASC");
		List<Object> params = new ArrayList<Object>();
		params.add(type);
		List<Map<String,Object>> list = getListMapBySQL(sql.toString(), params.toArray());
		return list;
	}
}