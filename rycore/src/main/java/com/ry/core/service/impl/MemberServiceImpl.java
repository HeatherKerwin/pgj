package com.ry.core.service.impl;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ry.core.dao.ActionlogDao;
import com.ry.core.dao.ActivecountDao;
import com.ry.core.dao.AppVisitLogDao;
import com.ry.core.dao.DiscountrecordDao;
import com.ry.core.dao.MemberDao;
import com.ry.core.entity.Member;
import com.ry.core.form.MemberForm;
import com.ry.core.service.MemberService;
import com.ry.util.page.PageResults;

@Service
public class MemberServiceImpl implements MemberService {

	@Resource
	MemberDao memberDao;
	
	@Resource
	ActivecountDao activecountDao;
	
	@Resource
	DiscountrecordDao discountrecordDao;
	
	@Resource
	ActionlogDao actionlogDao;
	
	@Resource
	private AppVisitLogDao appVisitLogDao;
	
	@Override
	public List<Member> getMemberList(Integer id,String username) {
		List<Member> memberList = memberDao.getList(id,username);
		return memberList;
	}
	
	@Override
	public List<Member> getMemberList(String id) {
		List<Member> list = memberDao.getList(id);
		return list;
	}
	@Override
	public void updateMember(Member member) {
		memberDao.updateMember(member);
		
	}
	@Override
	public Member login(Integer id,String username, String password) {
		Member member = memberDao.getMember(id,username, password);
		return member;
	}

	@Override
	public Long count(Date regtime1, Date regtime2, String recommendpeople) {
		MemberForm m = new MemberForm();	
		if (StringUtils.hasText(recommendpeople)) {
			m.setRecommendpeople(recommendpeople);
		}	
		if (regtime1 != null) {
			m.setBeginRegDate(regtime1);
		}
		if (regtime2 != null) {
			m.setEndRegDate(regtime2);
		}
		
		return memberDao.countMember(m);
	}

	@Override
	public List<Member> getMemberList(Date begintimed, Date endtimed, String recommendpeople) {		
		return memberDao.getList(begintimed, endtimed, recommendpeople);
	}

	@Override
	public PageResults<Member> getPageResults(Date begintimed, Date endtimed,String recommendpeople, int pageNo, int pageSize) throws Exception {
		
		PageResults<Member> pageResults = memberDao.getPageResults(begintimed, endtimed, recommendpeople, pageNo, pageSize);
		List<Member> memberList = pageResults.getResults();		
		List<Member> memberList1 = null;
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM");
		Long nowtime = System.currentTimeMillis();
		Date start = dateFormat2.parse(dateFormat3.format(new Date(nowtime))+"-01 00:00:00");
		Date end = dateFormat2.parse(dateFormat1.format(new Date(nowtime))+" 23:59:59");
		if(!(memberList == null || memberList.size() == 0)){
			memberList1 = new ArrayList<Member>();
			for(Member member : memberList){				
				member.setRegtimeshow(member.getRegtime()==null?"":new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(member.getRegtime()));
				Long c1 = discountrecordDao.countbyMemberId(member.getId());
				if (c1 != null) {
					member.setOrderallcount(Integer.parseInt(c1.toString()));
				}		
				Double c2 = discountrecordDao.allmoneybyMemberId(member.getId());
				if (c2 != null) {
					member.setOrderallprice(c2);
				} else {
					member.setOrderallprice(0.0);
				}
				//@WKX 根据用户主键获取访问天数（共计活跃度）
				
				BigInteger c3 = memberDao.countByStartAndEndAndMemberId(null,null,member.getId());
				if (c3 != null) {
					member.setActivecount(Integer.parseInt(c3.toString()));
				}
				
				//@WKX 根据用户主键获取访问天数（本月活跃度）
				BigInteger c5 = memberDao.countByStartAndEndAndMemberId(start,end,member.getId());
				if (c5 != null) {
					member.setMonthactivecount(Integer.parseInt(c5.toString()));
				}
				memberList1.add(member);
			}
			pageResults.setResults(memberList1);
		}
		
		return pageResults;
	}

	@Override
	public void addMember(Member member) throws Exception {
		//生成邀请码
		String myInvitationCode = random();
		member.setMyInvitationCode(myInvitationCode);
		memberDao.addMember(member);
	}
//
//	@Override
//	public void uncheckedMember() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deleteMember() {
//		// TODO Auto-generated method stub
//		
//	}	

	@Override
	public Member shouji(String mobile) {		
		return memberDao.yonghu(mobile);
	}

	@Override
	public List<Member> getList(String mobile, String password) {
		return memberDao.getList(mobile, password);
	}

	@Override
	public List<Member> getListByMobile(String mobile) {
		
		return memberDao.getListByMobile(mobile);
	}

	@Override
	public void updatepwdMember(Member member) {
	
		memberDao.updatepwdMember(member);
	}

	@Override
	public Long count(MemberForm memberForm) {
		return memberDao.countMember(memberForm);
	}

	/**
	 * 生成邀请码[个人]
	 * @author WKX
	 * @param charSize 英文字母位数
	 * @param numSize 数字位数
	 * @since 2016年1月12日 下午2:43:01
	 */
	private String random(){
		String str = "";
		for(int i = 0; i < 6; i++){
			str += (int)(Math.random()*10);
		}
		String code = "Q"+str;
		List<Member> list = memberDao.getByMyInvitationCode(code);
		if(list!=null && list.size()>0)return random();
		else return code;
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.MemberService#getByInvitationCode(java.lang.String)
	 */
	public Long getByInvitationCode(String invitationCode){
		return memberDao.getByInvitationCode(invitationCode);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.MemberService#getById(java.lang.Integer)
	 */
	public Member getById(Integer id){
		if(id==null)return null;
		return memberDao.getById(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.MemberService#getByMyInvitationCode(java.lang.String)
	 */
	public Member getByMyInvitationCode(String myInvitationCode){
		List<Member> list = memberDao.getByMyInvitationCode(myInvitationCode);
		if(list!=null && list.size()>0){
			return list.iterator().next();
		}else{
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.MemberService#getByMyInvitationCodeISNULL()
	 */
	public List<Member> getByMyInvitationCodeISNULL(){
		return memberDao.getByMyInvitationCodeISNULL();
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.MemberService#updateMemberByMyInvitationCodeISNULL(com.ry.core.entity.Member)
	 */
	public void updateMemberByMyInvitationCodeISNULL(Member member) throws Exception {
		String myInvitationCode = random();//生成邀请码
		member.setMyInvitationCode(myInvitationCode);
		memberDao.updateMember(member);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.MemberService#getMemberAndOrgInfoByMemberId(java.lang.Integer)
	 */
	public Map<String,Object> getMemberAndOrgInfoByMemberId(Integer memberId){
		List<Map<String,Object>> result = memberDao.getMemberAndOrgInfoByMemberId(memberId);
		if(result!=null && result.size()>0){
			return result.iterator().next();
		}else{
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.MemberService#getMemberAndNum(java.lang.Integer)
	 */
	public Map<String,Object> getMemberAndNum(Integer memberId){
		List<Map<String,Object>> result = memberDao.getMemberAndNum(memberId);
		if(result!=null && result.size()>0){
			return result.iterator().next();
		}else{
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.MemberService#getInfoByOrgId(java.lang.Integer)
	 */
	public Map<String,Object> getInfoByOrgId(Integer orgId){
		List<Map<String,Object>> list = memberDao.getInfoByOrgId(orgId);
		if(list!=null && list.size()>0){
			return list.iterator().next();
		}else{
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.MemberService#getListByRegTime2(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<Map<String, Object>> getListByRegTime2(Date startRegTime, Date endRegTime) {
		return memberDao.getListByRegTime2(startRegTime, endRegTime);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.MemberService#getByMyInvitationCode(java.lang.String, java.lang.Long, java.lang.Long)
	 */
	public List<Member> getByMyInvitationCode(String myInvitationCode, Date startRegTime, Date endRegTime) {
		return memberDao.getByMyInvitationCode(myInvitationCode, startRegTime, endRegTime);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.MemberService#getByRecommendpeople(java.lang.String, java.lang.Long, java.lang.Long)
	 */
	public List<Member> getByRecommendpeople(String servicenumber, Date startRegTime, Date endRegTime){
		return memberDao.getByRecommendpeople(servicenumber, startRegTime, endRegTime);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.MemberService#getListByRegTime3(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<Map<String, Object>> getListByRegTime3(Date startRegTime, Date endRegTime) {
		return memberDao.getListByRegTime3(startRegTime, endRegTime);
	}

	@Override
	public List<Map<String, Object>> getByThird(String openId, Integer type) {
		return memberDao.getByThird(openId,type);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.MemberService#getGroomList(java.util.Date, java.util.Date)
	 */
	@Override
	public List<Map<String, Object>> getGroomList(String begin, String end,String operate) {
		return memberDao.getGroomList(begin,end,operate);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.MemberService#updateModelPwd(java.lang.String, java.lang.String)
	 */
	@Override
	public void updateModelPwd(String pwd, String mobile) {
		memberDao.updateModelPwd(pwd,mobile);
	}

	@Override
	public PageResults<Map<String, Object>> getPageSalesMember(Integer pageIndex, Integer pageSize, String begTime,
			String endTime, String mobile, Integer isAccreditation,Integer admin) {
		return memberDao.getPageSalesMember(pageIndex, pageSize, begTime, endTime, mobile, isAccreditation,admin);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.MemberService#getNoDistributionMember(java.util.Date)
	 */
	@Override
	public List<Map<String,Object>> getNoDistributionMember(String date,String hezuo) {
		
		return memberDao.getNoDistributionMember(date,hezuo);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.MemberService#getLastDistributionMember(java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> getLastDistributionMember(String type) {
		return memberDao.getLastDistributionMember(type);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.MemberService#getModelById(java.lang.Integer)
	 */
	@Override
	public Member getModelById(Integer id) {
		return memberDao.getModelById(id);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.MemberService#getAuthenticationAuthenticationMember(java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> getAuthenticationNoDistributionMember(String type) {
		
		return memberDao.getAuthenticationNoDistributionMember(type);
	}
}