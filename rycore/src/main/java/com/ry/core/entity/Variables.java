package com.ry.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 系统参数表
 * @author 
 */
@Entity(name="variables")
public class Variables extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	
	//名称
	private String name;
	//编号
	private String code;
	//值
	private String value;
	//备注
	private String remarks;
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name="value")
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Column(name="remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
