package com.ry.core.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ry.core.entity.Member;
import com.ry.core.form.MemberForm;
import com.ry.util.page.PageResults;


public interface MemberDao {
	/**
	 * 
	* @Title: countMember
	* @Description: 统计用户
	* @param @param m
	* @param @return    参数
	* @return Long    返回类型
	* @throws
	 */
	public Long countMember(MemberForm m);	
	/**
	 * 
	* @Title: countMemberBySpecialSale
	* @Description: 销售人员不走hezuo=2渠道的客户注册数量
	* @param @param saleNums
	* @param @return    参数
	* @return List    返回类型
	* @throws
	 */
	public List<?> countMemberBySpecialSale(String saleNums);	
	public List<Member> getList(String id);
	List<Member> getList(Integer id,String username);
	List<Member> getList(Date begintimed,Date endtimed,String recommendpeople);
	Member getMember(Integer id,String username, String password);
	PageResults<Member> getPageResults(Date begintimed,Date endtimed,String recommendpeople, int pageNo, int pageSize);
//	void deleteMember(Integer id);
	void addMember(Member member)throws Exception;
//	void uncheckedMember(Integer id);
	Member yonghu(String mobile);	
	List<Member> getList(String mobile, String password);
	List<Member> getListByMobile(String mobile);
	void updateMember(Member member);
	void updatepwdMember(Member member);
	Member getMember(String mobile);
	
	/**
	 * 根据我的邀请码获取用户[在生成用户邀请码之前验证是否已存在]
	 * @author WKX
	 * @param myInvitationCode
	 * @since 2016年1月12日 下午2:07:37
	 */
	public List<Member> getByMyInvitationCode(String myInvitationCode);
	
	/**
	 * @author MH
	 *        获取最近两个月的推荐信息
	 * @param begin 61天前
	 * @param end   1天前
	 * @return
	 */
	public List<Map<String, Object>> getGroomList(String begin,String end,String operate);
	
	/**
	 * 根据邀请码获取邀请过多少用户
	 * @author WKX
	 * @param myInvitationCode
	 * @since 2016年1月12日 下午2:08:12
	 */
	public Long getByInvitationCode(String invitationCode);
	
	/**
	 * 根据主键获取对象
	 * @author WKX
	 * @param id
	 * @since 2016年1月12日 下午2:45:55
	 */
	public Member getById(Integer id);
	
	/**
	 * 查询所有没有 我的推荐码 的用户
	 * @author WKX
	 * @since 2016年1月21日 上午11:34:07
	 */
	public List<Member> getByMyInvitationCodeISNULL();
	
	/**
	 * 获取该时段（由领取红包来的新增用户）
	 * @author WKX
	 * @param hid
	 * @param start
	 * @param end
	 * @since 2016年2月16日 下午4:55:10
	 */
	public List<Map<String,Object>> getFromHongbaoCountByStartAndEnd(Integer hid,Date start,Date end);
	
	/**
	 * 获取时段内新增的用户
	 * @author WKX
	 * @param start 开始日期
	 * @param end 结束日期
	 * @since 2016年2月17日 上午11:29:43
	 */
	public List<Map<String,Object>> getCountByStartAndEnd(Date start,Date end);
	
	/**
	 * 根据主键获取用户相关信息（含认证信息）
	 * @author WKX
	 * @param memberId
	 * @since 2016年3月9日 下午2:40:05
	 */
	public List<Map<String,Object>> getMemberAndOrgInfoByMemberId(Integer memberId);
	
	/**
	 * 根据主键获取用户相关信息（含推荐总数）
	 * @author WKX
	 * @param memberId
	 */
	public List<Map<String,Object>> getMemberAndNum(Integer memberId);
	
	/**
	 * 根据认证主键获取用户信息
	 * @author WKX
	 * @param orgId
	 * @since 2016年3月23日 下午2:29:22
	 */
	public List<Map<String,Object>> getInfoByOrgId(Integer orgId);
	
	/**
	 * 查询所有销售人员某个月的新增用户的信息(二级)
	 */
	public List<Map<String, Object>> getListByRegTime2(Date startRegTime, Date endRegTime);
	
	/**
	 * 根据我的邀请码查
	 */
	public List<Member> getByMyInvitationCode(String myInvitationCode, Date startRegTime, Date endRegTime);
	
	/**
	 * 根据我的推荐人查询某段时间内的数据
	 */
	public List<Member> getByRecommendpeople(String servicenumber, Date startRegTime, Date endRegTime);
	
	/**
	 * 查询所有销售人员某个月的新增用户的所有邀请人的id(三级)
	 */
	public List<Map<String, Object>> getListByRegTime3(Date startRegTime, Date endRegTime);
	
	/**
	* 通过QQ信息查询用户信息
	* @param  openId
	* @return Long   
	* @author GXW
	* @since 2016年5月21日
	 */
	public List<Map<String, Object>> getByThird(String openId,Integer type);
	
	/**
	 * 根据主键获取对象（不解密，最原始对象）
	 * @author KHC
	 * @param id
	 */
	public Member getModelById(Integer id);
	
	/**
	 * 根据手机号修改登陆密码
	 * @author MH
	 * @param pwd 登陆密码
	 * @param mobile 手机号
	 */
	public void updateModelPwd(String pwd ,String mobile);
	
	/**
	 * 汇总用户时间段内，活跃天数（含两张统计表的数据联合）
	 * @author WKX
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param memberId 用户主键
	 * @since 2017年8月16日 上午9:38:05
	 */
	public BigInteger countByStartAndEndAndMemberId(Date start,Date end,Integer memberId);
	
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
	 * @param hezuo 用户注册渠道
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