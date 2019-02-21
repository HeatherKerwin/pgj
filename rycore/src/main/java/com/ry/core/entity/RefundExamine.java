package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * APP2.3转账审核（取消订单需要人工审核）
 * @author WKX
 */
@Entity(name="refund_examine")
public class RefundExamine extends BaseModel{

	private static final long serialVersionUID = 1L;
	
	private Integer dcrdId;//Discountrecord
	private Integer dtboId;//DistributeOrder
	
	private Integer cancelRole;//拒绝方（1企业拒绝，2机构拒绝）
	private String cause;//取消原因
	
	private Integer cancelResult;//拒绝结果（1退给企业，2退给机构）
	
	private Integer operatorId;//操作人主键
	private Date createTime;//创建时间
	private Date operatorTime;//操作时间
	
	/**
	 * 企业提交的  订单的主键
	 * @author WKX
	 * @since 2016年6月1日 下午4:30:12
	 */
	@Column(name="dcrd_id")
	public Integer getDcrdId() {
		return dcrdId;
	}

	public void setDcrdId(Integer dcrdId) {
		this.dcrdId = dcrdId;
	}

	/**
	 * 派单主键
	 * @author WKX
	 * @since 2016年6月1日 下午4:30:35
	 */
	@Column(name="dtbo_id")
	public Integer getDtboId() {
		return dtboId;
	}

	public void setDtboId(Integer dtboId) {
		this.dtboId = dtboId;
	}

	/**
	 * 拒绝方（1企业拒绝，2机构拒绝）
	 * @author WKX
	 * @since 2016年9月18日 下午3:43:26
	 */
	@Column(name="cancel_role")
	public Integer getCancelRole() {
		return cancelRole;
	}

	public void setCancelRole(Integer cancelRole) {
		this.cancelRole = cancelRole;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}
	
	/**
	 * 拒绝结果（1退给企业，2退给机构）
	 * @author WKX
	 * @since 2016年9月18日 下午3:43:26
	 */
	@Column(name="cancel_result")
	public Integer getCancelResult() {
		return cancelResult;
	}

	public void setCancelResult(Integer cancelResult) {
		this.cancelResult = cancelResult;
	}

	/**
	 * 操作人主键
	 * @author WKX
	 * @since 2016年6月1日 下午4:48:39
	 */
	@Column(name="operator_id")
	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	/**
	 * 创建时间
	 * @author WKX
	 */
	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 操作时间
	 * @author WKX
	 * @since 2016年6月1日 下午4:49:34
	 */
	@Column(name="operator_time")
	public Date getOperatorTime() {
		return operatorTime;
	}

	public void setOperatorTime(Date operatorTime) {
		this.operatorTime = operatorTime;
	}
}