package com.ry.core.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.beans.BeanUtils;

import com.ry.core.util.Utility;
import com.ry.util.page.PageResults;

@Entity(name="member")
public class Member implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String mobile;//用户手机号
	private String username;//用户名
	private String pwd;//用户密码
	private String recommendpeople;//推荐人
	private Date regtime;//注册时间
	
	private String qq;//qq
	private String email;//邮箱
	private String weixin;//微信
	private String zuoji;//座机
	private String gongsi;//公司
	private String zhiwu;//职位
	private String province;//@WKX 省
	private String city;//@WKX 市
	private String place;//详细地址
	private String headpic;//头像
	private String qudao;//渠道：PC,APP
	private String hezuo;//合作单位名称
	private String salt;
	private String encodestr;
	
	private String invitationCode;//@WKX 邀请码
	private String myInvitationCode;//@WKX 我的邀请码
	private String ip;//@WKX APP2.1 注册地址
	private String uuid;//@WKX 对应ClickCount 用户是百度推广而来
	
	private String regtimeshow;
	private Integer orderallcount;
	private Double orderallprice;
	private Integer activecount;
	private Integer dayactivecount;
	private Integer monthactivecount;
	
	private Integer state = 0;//@APP2.2 认证状态（未认证0、审核中1、已认证2、未通过3）
	private Integer draftExchangeBankId ;//大小票互换银行主键（说明这是银行人员）
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="mobile")
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Column(name="username")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name="pwd")
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	@Column(name="recommendpeople")
	public String getRecommendpeople() {
		return recommendpeople;
	}
	public void setRecommendpeople(String recommendpeople) {
		this.recommendpeople = recommendpeople;
	}
	
	@Column(name="regtime")
	public Date getRegtime() {
		return regtime;
	}
	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}
	
	@Column(name="qq")
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	
	@Column(name="email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="weixin")
	public String getWeixin() {
		return weixin;
	}
	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}
	
	@Column(name="zuoji")
	public String getZuoji() {
		return zuoji;
	}
	public void setZuoji(String zuoji) {
		this.zuoji = zuoji;
	}
	
	@Column(name="gongsi")
	public String getGongsi() {
		return gongsi;
	}
	public void setGongsi(String gongsi) {
		this.gongsi = gongsi;
	}
	
	@Column(name="zhiwu")
	public String getZhiwu() {
		return zhiwu;
	}
	public void setZhiwu(String zhiwu) {
		this.zhiwu = zhiwu;
	}
	
	/**
	 * 所在省
	 * @author WKX
	 * @since 2016年1月11日 上午10:05:19
	 */
	@Column(name="province")
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	
	/**
	 * 所在市
	 * @author WKX
	 * @since 2016年1月11日 上午10:05:19
	 */
	@Column(name="city")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Column(name="place")
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	
	@Column(name="headpic")
	public String getHeadpic() {
		return headpic;
	}
	public void setHeadpic(String headpic) {
		this.headpic = headpic;
	}		
	
	@Column(name="qudao")
	public String getQudao() {
		return qudao;
	}
	public void setQudao(String qudao) {
		this.qudao = qudao;
	}
	
	@Column(name="hezuo")
	public String getHezuo() {
		return hezuo;
	}
	public void setHezuo(String hezuo) {
		this.hezuo = hezuo;
	}
	
	@Transient
	public String getRegtimeshow() {
		return regtimeshow;
	}
	public void setRegtimeshow(String regtimeshow) {
		this.regtimeshow = regtimeshow;
	}
	
	@Transient
	public Integer getOrderallcount() {
		return orderallcount;
	}
	public void setOrderallcount(Integer orderallcount) {
		this.orderallcount = orderallcount;
	}
	
	@Transient
	public Double getOrderallprice() {
		return orderallprice;
	}
	public void setOrderallprice(Double orderallprice) {
		this.orderallprice = orderallprice;
	}
	
	@Transient
	public Integer getActivecount() {
		return activecount;
	}
	public void setActivecount(Integer activecount) {
		this.activecount = activecount;
	}
	
	@Transient
	public Integer getDayactivecount() {
		return dayactivecount;
	}
	public void setDayactivecount(Integer dayactivecount) {
		this.dayactivecount = dayactivecount;
	}
	
	@Transient
	public Integer getMonthactivecount() {
		return monthactivecount;
	}
	public void setMonthactivecount(Integer monthactivecount) {
		this.monthactivecount = monthactivecount;
	}
	/**
	 * 新添加盐值加密的两个字段
	 * @author GJJ
	 * @return
	 */
	@Column(name="salt")
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	@Column(name="encodestr")
	public String getEncodestr() {
		return encodestr;
	}
	
	public void setEncodestr(String encodestr) {
		this.encodestr = encodestr;
	}
	
	/**
	 * 邀请码[输入别人推荐给你时对方的邀请码]
	 * @author WKX
	 * @since 2016年1月12日 下午1:28:45
	 */
	@Column(length=8)
	public String getInvitationCode() {
		return invitationCode;
	}
	
	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}
	
	/**
	 * 我的邀请码[唯一]推荐给别人时
	 * @author WKX
	 * @since 2016年1月12日 下午1:28:56
	 */
	@Column(unique=true,length=8)
	public String getMyInvitationCode() {
		return myInvitationCode;
	}
	
	public void setMyInvitationCode(String myInvitationCode) {
		this.myInvitationCode = myInvitationCode;
	}
	
	/**
	 * 获取注册时IP
	 * @author WKX
	 * @since 2016年4月5日 下午5:07:12
	 */
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	/**
	 * 对应ClickCount 用户是百度推广而来
	 * @author WKX
	 * @since 2016年5月4日 下午2:49:25
	 */
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	@Column(length=8)
	public Integer getState() {
		return state;
	}
	
	public void setState(Integer state) {
		this.state = state;
	}
	
	@Column(name="draft_exchange_bank_id")
	public Integer getDraftExchangeBankId() {
		return draftExchangeBankId;
	}
	public void setDraftExchangeBankId(Integer draftExchangeBankId) {
		this.draftExchangeBankId = draftExchangeBankId;
	}
	/**
	 * 解密
	 * @author WKX
	 * @param member
	 * @since 2016年8月19日 上午10:47:02
	 */
	public static Member deMember(Member member){
		if(member==null)return member;
		Member temp = new Member();
		BeanUtils.copyProperties(member, temp);
		if(temp!=null){
			temp.setMobile(Utility.decodeMobile(temp.getMobile()));
		}
		return temp;
	}
	
	/**
	 * 解密
	 * @author WKX
	 * @param list
	 * @since 2016年8月19日 上午10:47:13
	 */
	public static List<Member> deMember(List<Member> list){
		List<Member> temp = new ArrayList<Member>();
		if(list!=null){
			for(Member member:list){
				temp.add(deMember(member));
			}
		}
		return temp;
	}
	
	/**
	 * 解密
	 * @author WKX
	 * @param list
	 * @since 2016年8月19日 上午10:47:13
	 */
	public static PageResults<Member> deMember(PageResults<Member> page){
		if(page!=null){
			List<Member> list = page.getResults();
			page.setResults(deMember(list));
		}
		return page;
	}
	
	/**
	 * 加密
	 * @author WKX
	 * @param member
	 * @since 2016年8月19日 上午10:47:02
	 */
	public static Member enMember(Member member){
		if(member!=null){
			member.setMobile(Utility.encodeMobile(member.getMobile()));
		}
		return member;
	}
	
	/**
	 * 加密
	 * @author WKX
	 * @param list
	 * @since 2016年8月19日 上午10:47:13
	 */
	public static List<Member> enMember(List<Member> list){
		if(list!=null){
			for(Member member:list){
				member = enMember(member);
			}
		}
		return list;
	}
}