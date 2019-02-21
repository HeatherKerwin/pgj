package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.ry.core.entity.DiscountrecordTask.OperatorType;

/**
 * 操作日志
 * @author WKX
 */
@Entity(name="operate_log")
public class OperateLog extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	
	private String clazz;//类
	
	private String method;//方法名
	
	private String description;//描述
	
	private Integer operatorId;//操作员主键
	
	private OperatorType operatorType;//操作员表（ADMIN、MEMBER）
	
	private String ip;//访问IP
	
	private Date createTime;//创建时间
	
	/**
	 * 执行的类
	 * @author WKX
	 */
	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	/**
	 * 方法名
	 * @author WKX
	 */
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * 描述
	 * @author WKX
	 */
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 操作员主键
	 * @author WKX
	 */
	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	/**
	 * 操作员表（ADMIN、MEMBER）
	 * @author WKX
	 */
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
	 * 操作时间
	 * @author WKX
	 */
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}