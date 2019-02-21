package com.ry.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 第三方登录授权表 APP2.2
 * @author WKX
 */
@Entity(name="third_auth")
public class ThirdAuth extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	
	private Integer memberId;//用户主键
	
	private String qqId;//QQ 登录UID
	private String qqToken;//access_token
	
	private String wechatId;//微信 登录UID
	private String wechatToken;//access_token
	
	private String sinaId;//新浪 登录UID
	private String sinaToken;//access_token
	
	private String wxappletId;//微信小程序 登录UID 
	
	/**
	 * 用户主键
	 * @author WKX
	 * @since 2016年5月11日 下午3:06:53
	 */
	@Column(name="member_id")
	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	/**
	 * QQ登录 
	 * @author WKX
	 * @since 2016年5月11日 下午3:07:21
	 */
	@Column(name="qq_id")
	public String getQqId() {
		return qqId;
	}

	public void setQqId(String qqId) {
		this.qqId = qqId;
	}

	/**
	 * QQ登录 access_token
	 * @author WKX
	 * @since 2016年5月11日 下午3:07:21
	 */
	@Column(name="qq_token")
	public String getQqToken() {
		return qqToken;
	}

	public void setQqToken(String qqToken) {
		this.qqToken = qqToken;
	}

	/**
	 * 微信登录
	 * @author WKX
	 * @since 2016年5月11日 下午3:07:21
	 */
	@Column(name="wechat_id")
	public String getWechatId() {
		return wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	/**
	 * 微信登录 access_token
	 * @author WKX
	 * @since 2016年5月11日 下午3:07:21
	 */
	@Column(name="wechat_token")
	public String getWechatToken() {
		return wechatToken;
	}

	public void setWechatToken(String wechatToken) {
		this.wechatToken = wechatToken;
	}

	/**
	 * 新浪登录
	 * @author WKX
	 * @since 2016年5月11日 下午3:07:21
	 */
	@Column(name="sina_id")
	public String getSinaId() {
		return sinaId;
	}

	public void setSinaId(String sinaId) {
		this.sinaId = sinaId;
	}

	/**
	 * 新浪登录 access_token
	 * @author WKX
	 * @since 2016年5月11日 下午3:07:21
	 */
	@Column(name="sina_token")
	public String getSinaToken() {
		return sinaToken;
	}

	public void setSinaToken(String sinaToken) {
		this.sinaToken = sinaToken;
	}

	/**
	 * 微信小程序登录
	 * @author ZWD
	 * @since 2017年6月14日09:47:26
	 */
	@Column(name="wxapplet_id")
	public String getWxappletId() {
		return wxappletId;
	}

	public void setWxappletId(String wxappletId) {
		this.wxappletId = wxappletId;
	}
}