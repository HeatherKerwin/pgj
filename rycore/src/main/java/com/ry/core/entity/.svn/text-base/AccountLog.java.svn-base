package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.ry.core.Enum.AccountLogState;
import com.ry.core.Enum.AccountLogType;
import com.ry.core.Enum.OrderType;
import com.ry.core.Enum.PayWay;

/**
 * 账户流转记录（保证金）含订单的资金流出
 * @author WKX
 * @date 2017-10-17
 */
@Entity(name="account_log")
public class AccountLog extends BaseModel{

	private static final long serialVersionUID = 0L;

	private String no;//编号（在支付时作为商户订单号使用）
	private Integer memberId;//用户主键
	private Integer accountId;//账户主键
	
	private Integer fkId;//外键（哪一个订单的交易）
	private OrderType fkType;//记录金额（哪一个订单的交易）
	
	private String cardBank;//提现账户（银行）
	private String cardUserName;//提现账户（用户名）
	private String cardNumber;//提现账户（卡号）
	
	private Float money;//提现金额（订单的收支金额）[单位：元]
	private Float moneyInto;//到账金额[单位：元]
	private Float fee;//手续费[单位：元]
	private PayWay way;//支付（充值，提现）方式
	private AccountLogState accountLogState;//处理（充值，提现）状态（待审核、成功、失败、取消）
	private AccountLogType accountLogType;//账户交易类型（账户资金记录表用：充值IN、提现OUT、收到保证金ORDERIN、支出保证金ORDEROUT、查询查复支出INQUIRYREPLYOUT、查询查复退款INQUIRYREPLYBACK、服务费（退还）SERVER_IN、服务费（支出）SERVER_OUT）
	
	private String jyh;//交易号（充值、提现）
	private String remark;//备注（可备注订单号，及购买会员的信息）
	
	private Date createTime;
	private Date updateTime;

	/**
	 * 编号（在支付时作为商户订单号使用）
	 */
	@Column(name="no")
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	@Column(name="member_id")
	public Integer getMemberId(){
		return memberId;
	}

	public void setMemberId(Integer memberId){
		this.memberId = memberId;
	}

	/**
	 * 账户主键
	 */
	@Column(name="account_id")
	public Integer getAccountId(){
		return accountId;
	}

	public void setAccountId(Integer accountId){
		this.accountId = accountId;
	}
	
	/**
	 * 外键（哪一个订单的交易）
	 */
	@Column(name="fk_id")
	public Integer getFkId() {
		return fkId;
	}

	public void setFkId(Integer fkId) {
		this.fkId = fkId;
	}

	/**
	 * 记录金额（哪一个订单的交易）
	 */
	@Column(name="fk_type")
	@Enumerated(EnumType.STRING)
	public OrderType getFkType() {
		return fkType;
	}

	public void setFkType(OrderType fkType) {
		this.fkType = fkType;
	}
	
	/**
	 * 提现账户（银行）
	 */
	@Column(name="card_bank")
	public String getCardBank() {
		return cardBank;
	}

	public void setCardBank(String cardBank) {
		this.cardBank = cardBank;
	}

	/**
	 * 提现账户（用户名）
	 */
	@Column(name="card_user_name")
	public String getCardUserName() {
		return cardUserName;
	}

	public void setCardUserName(String cardUserName) {
		this.cardUserName = cardUserName;
	}

	/**
	 * 提现账户（卡号）
	 */
	@Column(name="card_number")
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * 提现金额
	 */
	@Column(name="money")
	public Float getMoney(){
		return money;
	}

	public void setMoney(Float money){
		this.money = money;
	}

	/**
	 * 到账金额
	 */
	@Column(name="money_into")
	public Float getMoneyInto(){
		return moneyInto;
	}

	public void setMoneyInto(Float moneyInto){
		this.moneyInto = moneyInto;
	}

	/**
	 * 手续费
	 */
	@Column(name="fee")
	public Float getFee(){
		return fee;
	}

	public void setFee(Float fee){
		this.fee = fee;
	}
	
	/**
	 * CZ_BAOFOO(1,"宝付（充值）"),CZ_BILL99(2,"块钱（充值）"),TX_BAOFOO(3,"宝付（提现）"),TX_BILL99(4,"块钱（提现）"),TX_OFFLINE(5,"提现（线下）");
	 */
	@Column(name="way")
	@Enumerated(EnumType.STRING)
	public PayWay getWay() {
		return way;
	}

	public void setWay(PayWay way) {
		this.way = way;
	}

	/**
	 * 交易状态（待审核、成功、失败、取消、待入账）
	 */
	@Column(name="account_log_state")
	@Enumerated(EnumType.STRING)
	public AccountLogState getAccountLogState() {
		return accountLogState;
	}

	public void setAccountLogState(AccountLogState accountLogState) {
		this.accountLogState = accountLogState;
	}
	
	/**
	 * 账户交易类型（账户资金记录表用：充值IN、提现OUT、收到保证金ORDERIN、支出保证金ORDEROUT、查询查复支出INQUIRYREPLYOUT、查询查复退款INQUIRYREPLYBACK）
	 */
	@Column(name="account_log_type")
	@Enumerated(EnumType.STRING)
	public AccountLogType getAccountLogType() {
		return accountLogType;
	}

	public void setAccountLogType(AccountLogType accountLogType) {
		this.accountLogType = accountLogType;
	}
	
	/**
	 * 交易号
	 */
	@Column(name="jyh")
	public String getJyh() {
		return jyh;
	}

	public void setJyh(String jyh) {
		this.jyh = jyh;
	}

	/**
	 * 备注
	 */
	@Column(name="remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name="create_time")
	public Date getCreateTime(){
		return createTime;
	}

	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}

	@Column(name="update_time")
	public Date getUpdateTime(){
		return updateTime;
	}

	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
}