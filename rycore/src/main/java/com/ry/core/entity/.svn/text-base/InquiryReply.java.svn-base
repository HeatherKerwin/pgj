package com.ry.core.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 查询查复
 * @author WKX
 */
@Entity(name="inquiry_reply")
public class InquiryReply extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	
	private Integer orgId;//认证表主键
	private Integer orgType;//角色：企业0、机构1
	private Integer importState = 0;//导出状态：未导出0、已导出1、已导入2
	
	private String draftNo;//票号
	private BigDecimal money;//金额（票金额）
	private String drawer;//出票人
	private String payee;//收款人
	private String bank;//承兑行
	private String bankNo;//承兑行号
	private Date startDate;//出票日期
	private Date endDate;//到期日期
	private String no;//查询查复--编号（订单号）
	private Date createTime;//下单日期（创建日期）
	private Integer payState;//订单状态：待付款0、支付成功1、已退款2；宝付（退款中）3
	private BigDecimal payMoney;//付款金额
	private Integer payWay;//付款方式（0支付宝、1微信、2银联、3块钱、4宝付）
	private Integer needInvoice;//是否需要发票（是0、否1）
	private Integer state;//后台处理状态  0待处理 1处理中  2取消查询 3已发送银行 4查询完成
	private String result;//验票结果（银行返回的一段文本）
	private String refundReason;//退款理由
	private String jyh;//支付宝交易号（APP2.2也是银联）
	private String qudao;//渠道:PC/APP
	
	private Integer memberId;//APP2.2 用户主键（在2.2后企业端可能没有orgId）
	private Integer visitState;//0待回访 1回访中 2待持续回访
	private Integer editState;//修改授权（0允许修改[表示已经开启授权]、1不允许修改）NULL也是不允许修改【用户只能在开放修改权限后修改一次，当用户修改完状态变成1】
	
	/**
	 * 认证表主键
	 * @author WKX
	 * @since 2016年3月2日 下午7:08:53
	 */
	@Column(name="org_id")
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
	/**
	 * 认证类型，当前是什么角色做的查询查复（企业0、机构1）
	 * @author WKX
	 * @since 2016年4月12日 下午2:29:25
	 */
	@Column(name="org_type")
	public Integer getOrgType() {
		return orgType;
	}

	public void setOrgType(Integer orgType) {
		this.orgType = orgType;
	}

	/**
	 * 导出导入状态（未导出0、已导出1、已导入2）
	 * @author WKX
	 * @since 2016年4月12日 下午2:29:55
	 */
	@Column(name="import_state")
	public Integer getImportState() {
		return importState;
	}

	public void setImportState(Integer importState) {
		this.importState = importState;
	}

	/**
	 * 票号
	 * @author WKX
	 * @since 2016年3月2日 下午7:08:53
	 */
	@Column(name="draft_no",length=50)
	public String getDraftNo() {
		return draftNo;
	}

	public void setDraftNo(String draftNo) {
		this.draftNo = draftNo;
	}

	/**
	 * 金额（票金额）
	 * @author WKX
	 * @since 2016年3月2日 下午7:08:53
	 */
	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	/**
	 * 出票人
	 * @author WKX
	 * @since 2016年3月2日 下午7:08:53
	 */
	public String getDrawer() {
		return drawer;
	}

	public void setDrawer(String drawer) {
		this.drawer = drawer;
	}

	/**
	 * 收款人
	 * @author WKX
	 * @since 2016年3月2日 下午7:08:53
	 */
	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}
	
	/**
	 * 承兑行
	 * @author WKX
	 * @since 2016年3月2日 下午7:08:53
	 */
	@Column(length=100)
	public String getBank() {
		return bank;
	}
	
	public void setBank(String bank) {
		this.bank = bank;
	}
	
	/**
	 * 承兑行号
	 * @author WKX
	 * @since 2016年3月2日 下午7:08:53
	 */
	@Column(name="bank_no",length=50)
	public String getBankNo() {
		return bankNo;
	}
	
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	/**
	 * 出票日期
	 * @author WKX
	 * @since 2016年3月2日 下午7:08:53
	 */
	@Column(name="start_date")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * 到期日期
	 * @author WKX
	 * @since 2016年3月2日 下午7:08:53
	 */
	@Column(name="end_date")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * 查询查复--编号（订单号）
	 * @author WKX
	 * @since 2016年3月2日 下午7:13:23
	 */
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	/**
	 * 下单日期（创建日期）
	 * @author WKX
	 * @since 2016年3月2日 下午7:13:47
	 */
	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 订单状态：支付成功0、已退款1
	 * @author WKX
	 * @since 2016年3月2日 下午7:13:47
	 */
	@Column(name="pay_state",length=1)
	public Integer getPayState() {
		return payState;
	}

	public void setPayState(Integer payState) {
		this.payState = payState;
	}

	/**
	 * 付款金额
	 * @author WKX
	 * @since 2016年3月2日 下午7:15:11
	 */
	@Column(name="pay_money")
	public BigDecimal getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(BigDecimal payMoney) {
		this.payMoney = payMoney;
	}

	/**
	 * 付款方式（0支付宝、1微信、2银联）
	 * @author WKX
	 * @since 2016年3月2日 下午7:15:11
	 */
	@Column(name="pay_way",length=1)
	public Integer getPayWay() {
		return payWay;
	}

	public void setPayWay(Integer payWay) {
		this.payWay = payWay;
	}

	/**
	 * 是否需要发票（是0、否1）
	 * @author WKX
	 * @since 2016年3月2日 下午7:15:11
	 */
	@Column(name="need_invoice",length=1)
	public Integer getNeedInvoice() {
		return needInvoice;
	}

	public void setNeedInvoice(Integer needInvoice) {
		this.needInvoice = needInvoice;
	}

	/**
	 * 处理状态  0待处理 1处理中  2取消查询 3已发送银行 4查询完成
	 * @author ZY
	 * @since 2017年1月3日 下午4:46:10
	 */
	@Column(length=1)
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * 验票结果（银行返回的一段文本）
	 * @author WKX
	 * @since 2016年3月2日 下午7:17:16
	 */
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * 退款理由
	 */
	@Column(name="refund_reason")
	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}

	/**
	 * 支付宝交易号
	 */
	@Column(name="jyh")
	public String getJyh() {
		return jyh;
	}

	public void setJyh(String jyh) {
		this.jyh = jyh;
	}
	
	/**
	 * 用户主键（在2.2后企业端可能没有orgId）
	 * @author WKX
	 * @since 2016年6月13日 下午1:49:00
	 */
	@Column(name="member_id")
	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	
	/**
	 * 渠道
	 * @author ZY
	 * 2016年11月8日下午5:03:43
	 */
	public String getQudao() {
		return qudao;
	}

	public void setQudao(String qudao) {
		this.qudao = qudao;
	}

	/**
	 * 回访状态
	 * @author ZY
	 * @since 2017年1月3日 下午4:46:10
	 */
	@Column(name="visit_state")
	public Integer getVisitState() {
		return visitState;
	}

	public void setVisitState(Integer visitState) {
		this.visitState = visitState;
	}

	/**
	 * 修改授权（0允许修改、1不允许修改）NULL也是不允许修改【用户只能在开放修改权限后修改一次，当用户修改完状态变成1】
	 * @author WKX
	 */
	@Column(name="edit_state")
	public Integer getEditState() {
		return editState;
	}

	public void setEditState(Integer editState) {
		this.editState = editState;
	}
}