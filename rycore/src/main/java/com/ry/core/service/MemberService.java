package com.ry.core.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ry.core.entity.Member;
import com.ry.core.form.MemberForm;
import com.ry.util.page.PageResults;

public interface MemberService {
	Member shouji( String mobile);//暂无使用
	List<Member> getMemberList(String id);
	void updateMember(Member member);	
//	void updateAdmin(Member member); 
	void updatepwdMember(Member member); 
	List<Member> getMemberList(Integer id, String username);//暂无使用
	Member login(Integer id,String username, String password);//暂无使用
	Long count(Date regtime1, Date regtime2, String recommendpeople);
	Long count(MemberForm memberForm);
	List<Member> getMemberList(Date begintimed, Date endtimed, String recommendpeople);//暂无使用
	PageResults<Member> getPageResults(Date begintimed,Date endtimed,String recommendpeople, int pageNo, int pageSize) throws Exception;
//	void modifyMerber(Member s);
	void addMember(Member member) throws Exception;
//	void deleteMember();
//	void uncheckedMember();
	List<Member> getList(String mobile, String password);
	public List<Member> getListByMobile(String mobile);
	
	/**
	 * @author MH
	 *        获取最近两个月的推荐信息
	 * @param begin 61天前
	 * @param end   1天前
	 * @return
	 */
	public List<Map<String, Object>> getGroomList(String begin,String end,String operate);
	
	/**
	 * 根据邀请码获取[推荐了多少人]
	 * @author WKX
	 * @param invitationCode
	 * @since 2016年1月12日 下午2:44:02
	 */
	public Long getByInvitationCode(String invitationCode);
	
	/**
	 * 根据主键获取对象
	 * @author WKX
	 * @param id
	 * @since 2016年1月12日 下午2:46:51
	 */
	public Member getById(Integer id);
	
	/**
	 * 根据我的邀请码获取用户[适合用于判断邀请码是否存在等..]
	 * @author WKX
	 * @param myInvitationCode
	 * @since 2016年1月12日 下午4:18:03
	 */
	public Member getByMyInvitationCode(String myInvitationCode);
	
	/**
	 * 获取没有 我的推荐码的 用户
	 * @author WKX
	 * @since 2016年1月21日 上午11:35:26
	 */
	public List<Member> getByMyInvitationCodeISNULL();
	
	/**
	 * 更新用户 的推荐码 [给没有推荐码的赋值]
	 * @author WKX
	 * @param member
	 * @throws Exception
	 * @since 2016年1月21日 上午11:38:16
	 */
	public void updateMemberByMyInvitationCodeISNULL(Member member) throws Exception;
	
	/**
	 * 根据主键获取用户相关信息（含认证信息）
	 * @author WKX
	 * @param memberId
	 * @since 2016年3月9日 下午2:42:59
	 */
	public Map<String,Object> getMemberAndOrgInfoByMemberId(Integer memberId);
	
	/**
	 * 根据主键获取用户相关信息（含推荐的总数）
	 * @author WKX
	 * @param memberId
	 */
	public Map<String,Object> getMemberAndNum(Integer memberId);
		
	/**
	 * 根据认证主键获取用户信息
	 * @author WKX
	 * @param orgId
	 * @since 2016年3月23日 下午2:29:22
	 */
	public Map<String,Object> getInfoByOrgId(Integer orgId);
	
	/**
	 * 查询所有销售人员某个月的新增用户的信息(二级)
	 * @author BKY
	 */
	public List<Map<String, Object>> getListByRegTime2(Date startRegTime, Date endRegTime);
	
	/**
	 * 根据我的邀请码查
	 */
	public List<Member> getByMyInvitationCode(String myInvitationCode, Date startRegTime, Date endRegTime);
	
	/**
	 * 根据我的推荐人查
	 */
	public List<Member> getByRecommendpeople(String servicenumber, Date startRegTime, Date endRegTime);
	
	/**
	 * 查询所有销售人员某个月的新增用户的所有邀请人的id(三级)
	 * @author BKY
	 */
	public List<Map<String, Object>> getListByRegTime3(Date startRegTime, Date endRegTime);
	
	/**
	* 通过QQ信息查询用户信息
	* @param  openId
	* @return Long   
	* @author GXW
	* @since 2016年5月21日
	 */
	public List<Map<String, Object>> getByThird(String openId, Integer type);
	
	/**
	 * 根据手机号修改登陆密码
	 * @author MH
	 * @param pwd 登陆密码
	 * @param mobile 手机号
	 */
	public void updateModelPwd(String pwd ,String mobile);
	
	/**
	 * 根据主键获取对象（不解密，最原始对象）
	 * @author KHC
	 * @param id
	 */
	public Member getModelById(Integer id);
	
	/**
	 * 分页获取销售的用户
	 * @author MH
	 * @param pageIndex
	 * @param pageSize
	 * @param begTime 开始时间
	 * @param endTime 结束时间
	 * @param mobile 手机号
	 * @param isAccreditation 是否认证
	 * @return
	 */
	public PageResults<Map<String, Object>> getPageSalesMember(Integer pageIndex,Integer pageSize,String begTime,String endTime,String mobile,Integer isAccreditation,Integer admin);
	
	/**
	 * 获取当天时间没有分配的注册用户
	 * @param date 当天时间
	 * @param hezuo 用户注册的渠道
	 * @author MH
	 */
	public List<Map<String,Object>> getNoDistributionMember(String date,String hezuo);
	
	/**
	 * 获取最后分配的客服
	 * @author MH
	 * @param type 客服类型(FRONT("前端客服")，AFTER("后端客服"))
	 * @return
	 */
	public List<Map<String,Object>> getLastDistributionMember(String type);
	
	/**
	 * 获取认证未分配的用户
	 * @author MH
	 * @param type 客服类型(FRONT("前端客服")，AFTER("后端客服"))
	 * @return
	 */
	public List<Map<String,Object>> getAuthenticationNoDistributionMember(String type);
}