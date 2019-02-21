package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 推荐用户信息
 * @author BKY
 */
@Entity(name = "rmuserinfo")
public class RmUserInfo extends BaseModel {
	private static final long serialVersionUID = 8353572331303648173L;
	/* 创建时间 */
	private Date createTime;
	/* 我的邀请码 */
	private String myCode;
	/* 我的推荐人的推荐码 */
	private String code;
	/* 用户姓名*/
	private String memberName;
	/* 用户id*/
	private String memberId;
	/* 查询查复次数 */
	private String chaxunchafu;
	/* 下级用户的实际活跃天数 */
	private String userNumber;
	/* 新增用户个数*/
	private String addNumber;
	/* 成交单数 */
	private String dingdan;
	/* 额外奖励 */
	private String extraMoney;
	/* 总提成 */
	private String money;
	
	@Column(name="createtime")
	public Date getCreateTime() {
		return createTime;
	}

	@Column(name="mycode")
	public String getMyCode() {
		return myCode;
	}

	@Column(name="code")
	public String getCode() {
		return code;
	}

	@Column(name="addnumber")
	public String getAddNumber() {
		return addNumber;
	}

	@Column(name="membername")
	public String getMemberName() {
		return memberName;
	}

	@Column(name="memberid")
	public String getMemberId() {
		return memberId;
	}
	
	@Column(name="chaxunchafu")
	public String getChaxunchafu() {
		return chaxunchafu;
	}

	@Column(name="usernumber")
	public String getUserNumber() {
		return userNumber;
	}

	@Column(name="dingdan")
	public String getDingdan() {
		return dingdan;
	}

	@Column(name="extramoney")
	public String getExtraMoney() {
		return extraMoney;
	}

	@Column(name="money")
	public String getMoney() {
		return money;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setMyCode(String myCode) {
		this.myCode = myCode;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public void setAddNumber(String addNumber) {
		this.addNumber = addNumber;
	}

	public void setChaxunchafu(String chaxunchafu) {
		this.chaxunchafu = chaxunchafu;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public void setDingdan(String dingdan) {
		this.dingdan = dingdan;
	}

	public void setExtraMoney(String extraMoney) {
		this.extraMoney = extraMoney;
	}

	public void setMoney(String money) {
		this.money = money;
	}
	
	
}
