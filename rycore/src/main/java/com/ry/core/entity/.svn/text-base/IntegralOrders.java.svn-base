package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 订单
 * @author ZWD
 */
@Entity(name = "integral_orders")
public class IntegralOrders extends BaseModel {

	private static final long serialVersionUID = 1L;

	private String no;//订单编号
	private Integer sumPrice;//总价(总积分)
	private Integer state;//订单状态0待发货1已发货2无效
	private String expressCompany;//快递公司
	private String expressNo;//快递单号
	private Date createTime;//下单时间
	private Integer memberId;//用户Id
	private String remark;// 留言
	private Integer shipingAddressId;//收货地址的Id
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	
	@Column(name = "sum_price",length = 10)
	public Integer getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(Integer sumPrice) {
		this.sumPrice = sumPrice;
	}
	@Column(length = 10)
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	@Column(name = "express_company",length = 20)
	public String getExpressCompany() {
		return expressCompany;
	}

	public void setExpressCompany(String expressCompany) {
		this.expressCompany = expressCompany;
	}

	@Column(name = "express_no",length = 20)
	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	
	@Column(name = "create_time")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(length = 10)
	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name="shiping_address_id",length = 10)
	public Integer getShipingAddressId() {
		return shipingAddressId;
	}

	public void setShipingAddressId(Integer shipingAddressId) {
		this.shipingAddressId = shipingAddressId;
	}
	
}
