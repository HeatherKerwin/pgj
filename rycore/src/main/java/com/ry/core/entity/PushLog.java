package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * APP推送消息记录（日志）
 * @author WKX
 */
@Entity(name="push_log")
public class PushLog extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	
	private Integer type;//类型0全部、1认证机构、2认证企业
	
	private String content;//内容
	
	private Integer amount;//推送总人数
	
	private Integer successAmount;//成功人数
	
	private Date createTime;//推送日期
	
	private String memberIds;//推送失败用户主键
	
	private String mobiles;//推送失败的手机号码
	
	private Date startDate;//开始日期（推送消息时，过滤条件，注册日期）
	
	private Date endDate;//结束日期（推送消息时，过滤条件，注册日期）
	
	private Integer cityId;//推送范围，城市

	/**
	 * 类型
	 * 0全部、1认证机构、2认证企业
	 */
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 总推送群体（人数）
	 */
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	/**
	 * 成功推送群体（人数）
	 */
	@Column(name="success_amount")
	public Integer getSuccessAmount() {
		return successAmount;
	}

	public void setSuccessAmount(Integer successAmount) {
		this.successAmount = successAmount;
	}

	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 推送失败用户主键
	 */
	@Column(name="member_ids",columnDefinition="text")
	public String getMemberIds() {
		return memberIds;
	}

	public void setMemberIds(String memberIds) {
		this.memberIds = memberIds;
	}

	/**
	 * 推送失败的手机号码
	 */
	@Column(name="mobiles",columnDefinition="text")
	public String getMobiles() {
		return mobiles;
	}

	public void setMobiles(String mobiles) {
		this.mobiles = mobiles;
	}

	/**
	 * 开始日期（推送消息时，过滤条件，注册日期）
	 */
	@Column(name="start_date")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * 结束日期（推送消息时，过滤条件，注册日期）
	 */
	@Column(name="end_date")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * 推送范围，城市
	 */
	@Column(name="city_id")
	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
}