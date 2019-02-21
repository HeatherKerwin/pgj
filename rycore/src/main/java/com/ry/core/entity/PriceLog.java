package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.ry.core.entity.DiscountrecordTask.OperatorType;

/**
 * 价格操作日志
 * @author WKX
 */
@Entity(name="price_log")
public class PriceLog extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	
	private String description;//描述
	
	private Integer operatorId;//操作员主键
	
	private String operatorCode;//操作员账号（如：admin或13800000000）
	
	private OperatorType operatorType;//操作员类型（ADMIN、MEMBER）
	
	private String ip;//访问IP
	
	private Date createTime;//创建时间

	/**
	 * 描述
	 */
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 操作员ID
	 */
	@Column(name="operator_id")
	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	/**
	 * 操作员账号
	 */
	@Column(name="operator_code")
	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	/**
	 * 操作员类型（ADMIN、MEMBER）
	 */
	@Column(name="operator_type")
	@Enumerated(EnumType.STRING)
	public OperatorType getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(OperatorType operatorType) {
		this.operatorType = operatorType;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 操作日期
	 */
	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}