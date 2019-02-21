package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 预警机构
 * 
 * @author ZY
 */
@Entity(name = "org_warn")
public class OrgWarn extends BaseModel {

	private static final long serialVersionUID = 1L;

	private Date day;// 具体哪一天
	private Integer orgId;// 机构端，机构主键
	private Integer state;// 状态（0初始状态 1恶意报价 2非恶意报价）
	private Integer amount;// 出现 超出标准的 次数

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	@Column(name = "org_id")
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(length = 1)
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}