package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 评价表（订单完成用户评价）APP2.2
 * @author WKX
 */
@Entity(name="comments")
public class Comments extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	
	private Integer dcrdId;//Discountrecord
	private Integer dtboId;//DistributeOrder
	private Integer type;//@app2.3 类型：银票0、商票1、批量2
	private Date createTime;//创建时间（评论时间）
	
	private Integer operatorId;//操作人主键（评论人）
	private String content;//评论内容
	private Integer isToDoor;//是否上门（1是、0否）
	private Float price;//实际成交价格（评分）
	private Float service;//服务态度（评分）
	private Float speed;//打款速度（评分）
	
	private Integer role;//评论人角色：企业0、机构1
	
	/**
	 * 下单主键
	 * Discountrecord
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
	 * DistributeOrder
	 */
	@Column(name="dtbo_id")
	public Integer getDtboId() {
		return dtboId;
	}

	public void setDtboId(Integer dtboId) {
		this.dtboId = dtboId;
	}
	
	 /**
	  * 类型：银票0、商票1、批量2
	 * @author ZY
	 * @since 2016年8月16日 上午9:32:50
	 */
	@Column(name="type_")
	public Integer getType() {
		return type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 操作人主键（评论人）
	 * @author WKX
	 * @since 2016年5月11日 下午1:34:39
	 */
	@Column(name="operator_id")
	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}
	/**
	 * 评论创建时间）
	 * @author WKX
	 * @since 2016年5月11日 下午1:34:39
	 */
	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 评论内容
	 * @author WKX
	 * @since 2016年5月11日 下午1:35:40
	 */
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 是否上门
	 * @author WKX
	 * @since 2016年5月11日 下午1:35:55
	 */
	public Integer getIsToDoor() {
		return isToDoor;
	}

	public void setIsToDoor(Integer isToDoor) {
		this.isToDoor = isToDoor;
	}

	/**
	 * 实际成交价格
	 * @author WKX
	 * @since 2016年5月11日 下午1:37:40
	 */
	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	/**
	 * 服务态度
	 * @author WKX
	 * @since 2016年5月11日 下午1:37:19
	 */
	public Float getService() {
		return service;
	}

	public void setService(Float service) {
		this.service = service;
	}

	/**
	 * 打款速度
	 * @author WKX
	 * @since 2016年5月11日 下午1:37:11
	 */
	public Float getSpeed() {
		return speed;
	}

	public void setSpeed(Float speed) {
		this.speed = speed;
	}

	/**
	 * 评论人角色：企业0、机构1
	 */
	public Integer getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Integer role) {
		this.role = role;
	}
}