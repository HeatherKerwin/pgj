package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 回访记录
 * @author WKX
 */
@Entity(name="return_visit_record")
public class ReturnVisitRecord extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	
	private Integer memberId;//访问的用户
	
	private Integer state;//回访状态（0已回访、1未拨通、2未访问）
	
	private Integer operatorId;//操作员主键（填写人）对应表admin
	
	private String remarks;//备注（回访记录）
	
	private Date createTime;//创建时间（回访记录时间）
	
	private Integer type;//回访类型(1:5笔  2:500万  3：1星期内未下单)
	
	private Integer num;//下单次数
	
	private Float money;//下单额度
	
	@Column(name="num")
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Column(name="money")
	public Float getMoney() {
		return money;
	}

	public void setMoney(Float money) {
		this.money = money;
	}

	@Column(name="type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 访问的用户主键
	 * @author WKX
	 * @since 2016年4月27日 下午5:04:25
	 */
	@Column(name="member_id")
	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	/**
	 * @author WKX
	 * @since 2016年4月27日 下午5:23:18
	 */
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * 操作人主键（填写人）
	 * @author WKX
	 * @since 2016年4月28日 上午8:57:43
	 */
	@Column(name="operator_id")
	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * 创建日期（回访日期）
	 * @author WKX
	 * @since 2016年4月28日 上午8:58:52
	 */
	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}