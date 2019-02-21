package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 商票报价
 * @author ZY
 */
@Entity(name = "requirement_sp")
public class RequirementSp extends BaseModel {

	private static final long serialVersionUID = 1L;

	private Integer type;//票据类型（1.纸票，2.电票）
	private Integer orgId;//机构端，机构主键
	private Float minPrice;//收票金额区间（小）
	private Float maxPrice;//收票金额区间（大）
	private Integer minDay;//收票期限（小）
	private Integer maxDay;//收票期限（大）
	private Date createTime;//创建时间

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "org_id")
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	@Column(name = "min_price")
	public Float getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Float minPrice) {
		this.minPrice = minPrice;
	}

	@Column(name = "max_price")
	public Float getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Float maxPrice) {
		this.maxPrice = maxPrice;
	}

	@Column(name = "min_day")
	public Integer getMinDay() {
		return minDay;
	}

	public void setMinDay(Integer minDay) {
		this.minDay = minDay;
	}

	@Column(name = "max_day")
	public Integer getMaxDay() {
		return maxDay;
	}

	public void setMaxDay(Integer maxDay) {
		this.maxDay = maxDay;
	}

	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}