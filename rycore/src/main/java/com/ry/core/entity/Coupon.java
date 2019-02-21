package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.ry.core.Enum.CouponSrc;
import com.ry.core.Enum.CouponState;
import com.ry.core.Enum.CouponType;

/**
 * 红包（抵用券）
 * @author WKX
 * @date 2018-01-24
 */
@Entity(name="coupon")
public class Coupon extends BaseModel{

	private static final long serialVersionUID = 0L;

	private Float money;//金额（单位元）
	private Integer memberId;//用户主键（属于谁）
	
	private Date startDate;//有效期（开始日期）
	private Date endDate;//有效期（截止日期）
	
	private CouponType couponType;//红包类型（GENERAL通用红包、DISC贴现红包、DIST接单红包、INQUIRYREPLY查询查复红包）
	private CouponState couponState;//红包状态（UNUSED未使用、USED已使用、INVALID无效）
	private CouponSrc couponSrc;//红包来源（LUCK手气红包、GIVE赠送、ACTIVITY活动、REGISTER新用户注册）
	
	private Date createTime;//创建日期（获取日期）
	private Date updateTime;//更新日期（使用、更新日期）

	/**
	 * 金额（单位元）
	 */
	public Float getMoney(){
		return money;
	}

	public void setMoney(Float money){
		this.money = money;
	}

	/**
	 * 用户主键（属于谁）
	 */
	@Column(name="member_id")
	public Integer getMemberId(){
		return memberId;
	}

	public void setMemberId(Integer memberId){
		this.memberId = memberId;
	}

	/**
	 * 有效期（开始日期）
	 */
	@Column(name="start_date")
	public Date getStartDate(){
		return startDate;
	}

	public void setStartDate(Date startDate){
		this.startDate = startDate;
	}

	/**
	 * 有效期（截止日期）
	 */
	@Column(name="end_date")
	public Date getEndDate(){
		return endDate;
	}

	public void setEndDate(Date endDate){
		this.endDate = endDate;
	}

	/**
	 * 红包类型（GENERAL通用红包、DISC贴现红包、DIST接单红包、INQUIRYREPLY查询查复红包）
	 */
	@Column(name="coupon_type")
	@Enumerated(EnumType.STRING)
	public CouponType getCouponType(){
		return couponType;
	}

	public void setCouponType(CouponType couponType){
		this.couponType = couponType;
	}

	/**
	 * 红包状态（UNUSED未使用、USED已使用、INVALID无效）
	 */
	@Column(name="coupon_state")
	@Enumerated(EnumType.STRING)
	public CouponState getCouponState(){
		return couponState;
	}

	public void setCouponState(CouponState couponState){
		this.couponState = couponState;
	}

	/**
	 * 红包来源（LUCK手气红包、GIVE赠送、ACTIVITY活动、REGISTER新用户注册）
	 */
	@Column(name="coupon_src")
	@Enumerated(EnumType.STRING)
	public CouponSrc getCouponSrc(){
		return couponSrc;
	}

	public void setCouponSrc(CouponSrc couponSrc){
		this.couponSrc = couponSrc;
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