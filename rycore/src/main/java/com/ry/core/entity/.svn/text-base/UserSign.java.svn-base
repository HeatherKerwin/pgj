package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户签到
 * @author MH
 */
@Entity(name="user_sign")
public class UserSign extends BaseModel{

	private static final long serialVersionUID = 1L;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date signTime;//签到的时间
	private Integer memberId;//用户的Id
	
	@Column(name="sign_time")
	public Date getSignTime() {
		return signTime;
	}
	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}
	
	@Column(length=10)
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	
	 
}
