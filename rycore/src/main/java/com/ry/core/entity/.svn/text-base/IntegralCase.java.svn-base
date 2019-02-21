package com.ry.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 积分方案
 * 
 * @author ZWD
 */
@Entity(name="integral_case")
public class IntegralCase extends BaseModel {

	private static final long serialVersionUID = 1L;

	private String name; // 积分方案名称
	private Integer pinlv; // 积分方案频率
	private Integer state; // 积分方案状态
	private Integer integral;// 积分

	@Column(length = 20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 10)
	public Integer getPinlv() {
		return pinlv;
	}

	public void setPinlv(Integer pinlv) {
		this.pinlv = pinlv;
	}

	@Column(length = 10)
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(length = 10)
	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

}
