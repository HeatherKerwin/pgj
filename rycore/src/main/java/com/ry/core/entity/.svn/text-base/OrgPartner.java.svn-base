package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 合作机构
 * @author WKX
 */
@Entity(name = "org_partner")
public class OrgPartner extends BaseModel {

	private static final long serialVersionUID = 1L;

	private String name;//名称
	private String address;//地址
	private Integer orgId;//机构主键
	
	private Date createTime;// 添加新的城市的时间

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name="org_id")
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}