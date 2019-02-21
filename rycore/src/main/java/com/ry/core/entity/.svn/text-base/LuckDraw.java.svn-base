package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 抽奖
 * @author MH
 */
@Entity(name="luck_draw")
public class LuckDraw extends BaseModel{

	private static final long serialVersionUID = 0L;

	private Integer memberId;//用户主键（属于谁）
	private String address;//收货地址
	private Integer type;//类型（0虚拟，1实物，2已发送）
	private String goods;//物品
	private String mobile;//手机号
	private String name;//收件人
	private Date createTime;//创建日期（获取日期）
	
	@Column(name="member_id")
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getGoods() {
		return goods;
	}
	public void setGoods(String goods) {
		this.goods = goods;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}