package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 机构承诺函（审核主表）
 * @author WKX
 */
@Entity(name="org_image")
public class OrgImage extends BaseModel{

	private static final long serialVersionUID = 1L;
	
	private Integer orgId; //认证主键

	private Date createTime;//创建时间（申请时间）
	private Date authTime;//认证时间（处理时间）
	private Integer state = 1;//认证状态（审核中1、已认证2、未通过3）
	private String reason;//审核原因
	
	/**
	 * 认证主键
	 */
	@Column(name="org_id")
	public Integer getOrgId() {
		return orgId;
	}
	
	/**
	 * 用户主键
	 */
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
	/**
	 * 创建时间（申请时间）
	 */
	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * 认证时间（处理时间）
	 */
	@Column(name="auth_time")
	public Date getAuthTime() {
		return authTime;
	}
	
	public void setAuthTime(Date authTime) {
		this.authTime = authTime;
	}
	
	/**
	 * 认证状态（未认证0、审核中1、已认证2、未通过3）
	 */
	public Integer getState() {
		return state;
	}
	
	public void setState(Integer state) {
		this.state = state;
	}
	
	/**
	 * 审核原因
	 */
	public String getReason() {
		return reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
}