package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 贴现返利（活动时间：201610014-20161231）
 * @author WKX
 */
@Entity(name="discount_rebate")
public class DiscountRebate extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	
	private Integer dcrdId;//企业提交的  订单的主键
	
	private String memberMobile;//用户名称
	
	private String memberName;//用户联系方式
	
	private Integer index;//第几次（第一单减10元、第二单减20元、第三单减30元、第四单减40元、第六单减50元）
	
	private Float money;//保证金-金额
	
	private String card;//账号（银联、支付宝）
	
	private Integer state;//处理状态（0未处理、1已打款、2无效）
	
	private Integer operatorId;//操作人主键
	private Date createTime;//创建时间
	private Date operatorTime;//操作时间
	
	private String rebatejyh;// 交易号，转账方的账号
	
	/**
	 * 订单主键（银票）
	 * @author WKX
	 * @since 2016年9月26日 下午2:25:30
	 */
	@Column(name="dcrd_id")
	public Integer getDcrdId() {
		return dcrdId;
	}

	public void setDcrdId(Integer dcrdId) {
		this.dcrdId = dcrdId;
	}

	/**
	 * 用户名称
	 * @author WKX
	 * @since 2016年9月26日 下午4:18:42
	 */
	@Column(name="member_mobile")
	public String getMemberMobile() {
		return memberMobile;
	}

	public void setMemberMobile(String memberMobile) {
		this.memberMobile = memberMobile;
	}

	/**
	 * 用户联系方式
	 * @author WKX
	 * @since 2016年9月26日 下午2:39:50
	 */
	@Column(name="member_name")
	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	/**
	 * 第几次（第一单减10元、第二单减20元、第三单减30元、第四单减40元、第六单减50元）
	 * @author WKX
	 * @since 2016年9月26日 下午4:18:23
	 */
	@Column(name="index_")
	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Float getMoney() {
		return money;
	}

	public void setMoney(Float money) {
		this.money = money;
	}
	
	/**
	 * @author WKX
	 * @since 2016年9月26日 下午4:18:51
	 */
	public String getCard() {
		return card;
	}
	
	public void setCard(String card) {
		this.card = card;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * 操作人主键
	 * @author WKX
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
	 */
	@Column(name="operator_time")
	public Date getOperatorTime() {
		return operatorTime;
	}

	public void setOperatorTime(Date operatorTime) {
		this.operatorTime = operatorTime;
	}

	public String getRebatejyh() {
		return rebatejyh;
	}

	public void setRebatejyh(String rebatejyh) {
		this.rebatejyh = rebatejyh;
	}
	
}