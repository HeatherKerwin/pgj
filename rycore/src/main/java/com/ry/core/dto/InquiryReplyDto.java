package com.ry.core.dto;

import java.math.BigDecimal;
import java.util.Date;


public class InquiryReplyDto{

	private Integer id;
	private Integer orgId;//认证表主键
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
	private Integer payState;//订单状态：支付成功0、已退款1
	private BigDecimal payMoney;//付款金额
	private Integer payWay;//付款方式（0支付宝、1微信）
	private Integer needInvoice;//是否需要发票（是0、否1）
	private Integer state;//验票结果（待验票0、成功1、失败2） ==》后台处理状态  0待处理 1处理中  2取消查询 3已发送银行 4查询完成
	private String result;//验票结果（银行返回的一段文本）
	private String fkId;
	private String fkType;
	private String invState;//寄送结果
	private String stateName;
	
	private Integer orgType;//角色：企业0、机构1
	private Integer importState = 0;//导出状态：未导出0、已导出1、已导入2
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the orgId
	 */
	public Integer getOrgId() {
		return orgId;
	}
	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	/**
	 * @return the draftNo
	 */
	public String getDraftNo() {
		return draftNo;
	}
	/**
	 * @param draftNo the draftNo to set
	 */
	public void setDraftNo(String draftNo) {
		this.draftNo = draftNo;
	}
	/**
	 * @return the money
	 */
	public BigDecimal getMoney() {
		return money;
	}
	/**
	 * @param money the money to set
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	/**
	 * @return the drawer
	 */
	public String getDrawer() {
		return drawer;
	}
	/**
	 * @param drawer the drawer to set
	 */
	public void setDrawer(String drawer) {
		this.drawer = drawer;
	}
	/**
	 * @return the payee
	 */
	public String getPayee() {
		return payee;
	}
	/**
	 * @param payee the payee to set
	 */
	public void setPayee(String payee) {
		this.payee = payee;
	}
	/**
	 * @return the bank
	 */
	public String getBank() {
		return bank;
	}
	/**
	 * @param bank the bank to set
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}
	/**
	 * @return the bankNo
	 */
	public String getBankNo() {
		return bankNo;
	}
	/**
	 * @param bankNo the bankNo to set
	 */
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the no
	 */
	public String getNo() {
		return no;
	}
	/**
	 * @param no the no to set
	 */
	public void setNo(String no) {
		this.no = no;
	}
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the payState
	 */
	public Integer getPayState() {
		return payState;
	}
	/**
	 * @param payState the payState to set
	 */
	public void setPayState(Integer payState) {
		this.payState = payState;
	}
	/**
	 * @return the payMoney
	 */
	public BigDecimal getPayMoney() {
		return payMoney;
	}
	/**
	 * @param payMoney the payMoney to set
	 */
	public void setPayMoney(BigDecimal payMoney) {
		this.payMoney = payMoney;
	}
	/**
	 * @return the payWay
	 */
	public Integer getPayWay() {
		return payWay;
	}
	/**
	 * @param payWay the payWay to set
	 */
	public void setPayWay(Integer payWay) {
		this.payWay = payWay;
	}
	/**
	 * @return the needInvoice
	 */
	public Integer getNeedInvoice() {
		return needInvoice;
	}
	/**
	 * @param needInvoice the needInvoice to set
	 */
	public void setNeedInvoice(Integer needInvoice) {
		this.needInvoice = needInvoice;
	}
	/**
	 * @return the state
	 */
	public Integer getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}
	/**
	 * @return the fkId
	 */
	public String getFkId() {
		return fkId;
	}
	/**
	 * @param fkId the fkId to set
	 */
	public void setFkId(String fkId) {
		this.fkId = fkId;
	}
	/**
	 * @return the fkType
	 */
	public String getFkType() {
		return fkType;
	}
	/**
	 * @param fkType the fkType to set
	 */
	public void setFkType(String fkType) {
		this.fkType = fkType;
	}
	/**
	 * @return the invState
	 */
	public String getInvState() {
		return invState;
	}
	/**
	 * @param invState the invState to set
	 */
	public void setInvState(String invState) {
		this.invState = invState;
	}
	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}
	/**
	 * @param stateName the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
	/**
	 * 角色：企业0、机构1
	 * @author WKX
	 * @since 2016年4月12日 下午6:21:13
	 */
	public Integer getOrgType() {
		return orgType;
	}
	
	public void setOrgType(Integer orgType) {
		this.orgType = orgType;
	}
	
	/**
	 * 导出状态：未导出0、已导出1、已导入2
	 * @author WKX
	 * @since 2016年4月12日 下午6:21:23
	 */
	public Integer getImportState() {
		return importState;
	}
	
	public void setImportState(Integer importState) {
		this.importState = importState;
	}
}