package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 票据指数
 * @author GXW
 * @date 2016年5月13日
 */
@Entity(name = "bill_quota")
public class BillQuota extends BaseModel{

	private static final long serialVersionUID = 1L;
	
	private Double value;// 指数值
	private Double varyValue;// 涨跌（暂不用）
	private int type;// 纸票0 电票1
	private String day;//2016-05-05
	private Date createTime;//创建时间（发布时间）

	@Column(name = "value")
	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	/**
	 * 涨跌
	 * @author GXW
	 * @date 2016年5月13日
	 */
	@Column(name = "vary_value")
	public Double getVaryValue() {
		return varyValue;
	}

	public void setVaryValue(Double varyValue) {
		this.varyValue = varyValue;
	}

	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name="day")
	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	@Column(name="type")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
}
