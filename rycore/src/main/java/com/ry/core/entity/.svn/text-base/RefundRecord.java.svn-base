package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * APP2.2转账退款记录（含退款失败需要人工转账）
 * @author WKX
 */
@Entity(name="refund_record")
public class RefundRecord extends BaseModel{

	private static final long serialVersionUID = 1L;
	
	private Integer dcrdId;//Discountrecord
	private Integer dtboId;//DistributeOrder
	
	private Integer memberId;//收款人主键
	private Integer belong;//什么角色（0企业、1机构）
	private Integer payWay;//当初支付押金的方式（0支付宝、1微信、2银联）
	private String card;//账号（银联、支付宝）
	
	private String bnsNo;//商户订单号
	private String jyh;//交易号（银联、支付宝）
	private Float money;//保证金-金额
	
	private Integer type;//类型（1转账、2退款（系统退款失败需要人工转账））
	private Integer state;//操作类型（1待转账（退款）、2已完成）
	
	private String refundJyh;//转账（退款）交易号[人工录入]
	private String remark;//备注（人工转账后的操作备注）
	
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
	 * 用户主键
	 * @author WKX
	 * @since 2016年6月1日 下午4:32:19
	 */
	@Column(name="member_id")
	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	/**
	 * 什么角色（0企业、1机构）
	 * @author WKX
	 * @since 2016年6月1日 下午4:34:02
	 */
	public Integer getBelong() {
		return belong;
	}

	public void setBelong(Integer belong) {
		this.belong = belong;
	}

	/**
	 * 付款方式（0支付宝、1微信、2银联）
	 * @author WKX
	 */
	@Column(name="pay_way",length=1)
	public Integer getPayWay() {
		return payWay;
	}

	public void setPayWay(Integer payWay) {
		this.payWay = payWay;
	}

	/**
	 * 账号（银联、支付宝）
	 * @author WKX
	 * @since 2016年6月1日 下午4:44:30
	 */
	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	/**
	 * 商户订单号
	 * @author WKX
	 * @since 2016年6月1日 下午4:45:11
	 */
	@Column(name="bns_no")
	public String getBnsNo() {
		return bnsNo;
	}

	public void setBnsNo(String bnsNo) {
		this.bnsNo = bnsNo;
	}

	/**
	 * 交易号（银联、支付宝）
	 * @author WKX
	 */
	public String getJyh() {
		return jyh;
	}

	public void setJyh(String jyh) {
		this.jyh = jyh;
	}

	/**
	 * 保证金-金额
	 * @author WKX
	 * @since 2016年6月1日 下午4:45:49
	 */
	public Float getMoney() {
		return money;
	}

	public void setMoney(Float money) {
		this.money = money;
	}

	/**
	 * 类型（1转账、2退款（系统退款失败需要人工转账））
	 * @author WKX
	 * @since 2016年6月1日 下午4:46:18
	 */
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	/**
	 * 操作类型（1待转账（退款）、2已完成）
	 * @author WKX
	 * @since 2016年6月1日 下午4:46:57
	 */
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * 转账（退款）交易号[人工录入]
	 * @author WKX
	 * @since 2016年6月1日 下午4:47:11
	 */
	@Column(name="refund_jyh")
	public String getRefundJyh() {
		return refundJyh;
	}

	public void setRefundJyh(String refundJyh) {
		this.refundJyh = refundJyh;
	}

	/**
	 * 备注（人工转账后的操作备注）
	 * @author WKX
	 * @since 2016年6月1日 下午4:48:25
	 */
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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