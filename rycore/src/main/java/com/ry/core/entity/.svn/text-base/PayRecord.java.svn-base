package com.ry.core.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;

/**
 * 支付流水
 * @author WKX
 */
@Entity(name="pay_record")
public class PayRecord extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	
	private Integer pkId;//外键
	private String pkType;//外键类型：1查询查复inquiry_reply、2下单discountrecord、3派单distribute_order
	private BigDecimal payMoney;//付款金额
	private Integer payWay;//付款方式（0支付宝、1微信、2银联）
	private Integer state;//支付状态（0支付成功、2支付失败、4支付取消）
	private String description;//描述
	
	private String jyh;//@APP2.2 交易号
	private String card;//@APP2.2 账号（银联、支付宝）
	
	public Integer getPkId() {
		return pkId;
	}

	public void setPkId(Integer pkId) {
		this.pkId = pkId;
	}

	public String getPkType() {
		return pkType;
	}

	public void setPkType(String pkType) {
		this.pkType = pkType;
	}

	/**
	 * 支付金额
	 * @author WKX
	 * @since 2016年3月10日 上午9:15:40
	 */
	public BigDecimal getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(BigDecimal payMoney) {
		this.payMoney = payMoney;
	}

	/**
	 * 支付方式
	 * @author WKX
	 * @since 2016年3月10日 上午9:16:40
	 */
	public Integer getPayWay() {
		return payWay;
	}

	public void setPayWay(Integer payWay) {
		this.payWay = payWay;
	}

	/**
	 * 状态（该状态对应支付方式）
	 * @author WKX
	 * @since 2016年3月10日 上午9:16:55
	 */
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * 描述
	 * @author WKX
	 * @since 2016年3月10日 上午9:18:08
	 */
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 交易号
	 * @author WKX
	 * @since 2016年5月10日 上午9:28:50
	 */
	public String getJyh() {
		return jyh;
	}

	public void setJyh(String jyh) {
		this.jyh = jyh;
	}

	/**
	 * 付款人（付款账号）
	 * @author WKX
	 * @since 2016年5月10日 上午9:28:59
	 */
	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}
}