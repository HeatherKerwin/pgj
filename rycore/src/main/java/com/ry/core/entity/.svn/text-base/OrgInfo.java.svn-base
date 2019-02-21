package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;


/**
 * 认证信息表
 * @author RY
 */
@Entity(name="org_info")
public class OrgInfo extends BaseModel{

	private static final long serialVersionUID = 1L;
	
	private Integer orgId; //认证主键
	private Integer memberId;//@APP2.2 用户主键
	private Integer type;//角色：企业0、机构1
	
	private String name;//姓名
	private String blNumber;//营业执照编号（企业认证Business License）
	private String company;//企业名称（企业认证）
	private String address;//企业地址（企业认证）
	private String phone;//联系方式
	private Date createTime;//创建时间（申请时间）
	private Date authTime;//认证时间（处理时间）
	private Integer state = 1;//认证状态（未认证0、审核中1、已认证2、未通过3）
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
	 * 用户主键
	 * @author WKX
	 * @since 2016年5月10日 上午9:00:34
	 */
	@Column(name="member_id")
	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	/**
	 * 角色：企业0、机构1
	 */
	@Column(name="type_")
	public Integer getType() {
		return type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	/**
	 * 姓名
	 * @author WKX
	 */
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 营业执照编号（企业认证Business License）
	 */
	@Column(name="bl_number")
	public String getBlNumber() {
		return blNumber;
	}
	
	public void setBlNumber(String blNumber) {
		this.blNumber = blNumber;
	}
	
	/**
	 * 企业名称（企业认证）
	 */
	public String getCompany() {
		return company;
	}
	
	public void setCompany(String company) {
		this.company = company;
	}
	
	/**
	 * 企业地址（企业认证）
	 */
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 联系方式
	 * @author WKX
	 */
	@Column(length=20)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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