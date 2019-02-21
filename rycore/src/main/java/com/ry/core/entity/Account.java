package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 保证金账户
 * @author WKX
 * @date 2017-10-17
 */
@Entity(name="account")
public class Account extends BaseModel{

	private static final long serialVersionUID = 0L;

	private Integer memberId;
	private Float money;//金额[单位：元]
	
	private Date createTime;//创建日期
	private Date updateTime;//金额变动日期

	@Column(name="member_id")
	public Integer getMemberId(){
		return memberId;
	}

	public void setMemberId(Integer memberId){
		this.memberId = memberId;
	}
	
	@Column(name="money")
	public Float getMoney(){
		return money;
	}

	public void setMoney(Float money){
		this.money = money;
	}

	@Column(name="create_time")
	public Date getCreateTime(){
		return createTime;
	}

	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}

	@Column(name="update_time")
	public Date getUpdateTime(){
		return updateTime;
	}

	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
}