package com.ry.core.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.beans.BeanUtils;

import com.ry.core.util.Utility;
import com.ry.util.page.PageResults;

@Entity(name="discountrecord")
public class Discountrecord implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer type1;//票据金额 1纸票2电票
	private Integer type2;//承兑行类型 1国股2城商3三农
	private Date begintime;//开始时间
	private Date endtime;//结束时间
	private String place;//交易城市
	private float allmoney;//总额
	private Integer membersex;//1男2女
	private String membermobile;//手机号
	private String memberother;//备注
	private Integer salepriceid;//特价id
	private Date ordertime;//订单时间
	private String ordernumber;//订单号
	private Integer memberid;//账户Id
	private Integer orderflag;//订单状态（-2删除（待复核）、-1订单失败、0无效订单、1待确认、2验票中、3已完成、4待收款）
	private String membername;//贴现地址的姓名
	private String picpath;//图片
	private Integer confirmflag;//管理员确认状态
	private Integer reasonflag;//无效原因
	private String reasondesc;//原因描述
	private String admindesc;//管理员描述
	
	private String isValid;//@LXF (APP2.1) T ：有效  F：无效（表示删除）
	
	private Integer acceptTime;//@APP2.2 承兑期限（0半年期、1一年期）
	private Integer tradeModel;//@APP2.2 交易模式（0先背书后打款、1先打款后背书）
	
	private String bank;//@APP2.2 承兑行全称
	private Float longitude;//@APP2.2 经度
	private Float latitude;//@APP2.2 纬度
	private Integer cityId;//@APP2.2 城市主键
	private String address;//@APP2.2 详细地址
	
	private Integer payWay;//@APP2.2 付款方式（0支付宝A、1微信W、2银联U、3快钱K、4宝付B）无支付N
	private String card;//@APP2.2 账号（银联、支付宝）
	
	private String bnsNo;//@APP2.2 商户订单号
	private String jyh;//@APP2.2 交易号（银联、支付宝）
	
	private Float deposit;//@APP2.2 保证金
	private Integer depositState = 0;//@APP2.2 保证金状态（0待支付、1初始状态（已支付）、2退换自己、3我给机构、4机构给我（含我））
	
	private Integer endorse;//@APP2.3 背书
	private Integer hasTicket;//@APP2.3 票已开出（0是 1否）
	private Integer flawTicket;//@APP2.3 瑕疵票（0是 1否）
	private Integer needTodoor;//@APP2.3 要求上门（1是 0否）
	
	private String cancelCause;//@APP2.3 取消原因
	private Integer cancel;//@APP2.3 拒绝理由（0票面信息有误、1只为熟悉流程和询问价格、2价格不合适、3已提前出票、4其他）
	
	private String source;//@PC 来源，判断订单来自app还是pc
	
	private Integer handleState;//处理状态(0.待处理，1.处理中，2.无效订单，3.订单进行中，4.订单完成)
	private Integer visitState;//回访状态(0.待回访，1.订单客户回访中，2.待持续回访)
	private Integer refundState;//是否退款，快钱新添。（0未退款，1已退款）；宝付（2待退款）
	
	private String ordertimeshow;
	private String type1show;
	private String type2show;
	private String orderflagshow;
	private String begintimeshow;
	private String endtimeshow;
	private String membersexshow;
	private String recommendpeople;
	private Integer hongbaoprice;
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="type1")
	public Integer getType1() {
		return type1;
	}
	public void setType1(Integer type1) {
		this.type1 = type1;
	}
	
	@Column(name="type2")
	public Integer getType2() {
		return type2;
	}
	public void setType2(Integer type2) {
		this.type2 = type2;
	}
	
	@Column(name="begintime")
	public Date getBegintime() {
		return begintime;
	}
	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}
	
	@Column(name="endtime")
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	
	@Column(name="place")
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	
	@Column(name="allmoney")
	public Float getAllmoney() {
		return allmoney;
	}
	public void setAllmoney(Float allmoney) {
		this.allmoney = allmoney;
	}
	
	@Column(name="membersex")
	public Integer getMembersex() {
		return membersex;
	}
	public void setMembersex(Integer membersex) {
		this.membersex = membersex;
	}
	
	@Column(name="membermobile")
	public String getMembermobile() {
		return membermobile;
	}
	public void setMembermobile(String membermobile) {
		this.membermobile = membermobile;
	}
	
	@Column(name="memberother")
	public String getMemberother() {
		return memberother;
	}
	public void setMemberother(String memberother) {
		this.memberother = memberother;
	}
	
	@Column(name="salepriceid")
	public Integer getSalepriceid() {
		return salepriceid;
	}
	public void setSalepriceid(Integer salepriceid) {
		this.salepriceid = salepriceid;
	}
	
	@Column(name="ordertime")
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	
	@Column(name="ordernumber")
	public String getOrdernumber() {
		return ordernumber;
	}
	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}
	
	@Column(name="memberid")
	public Integer getMemberid() {
		return memberid;
	}
	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}
	
	/**
	 * 订单状态（-2删除（待复核）、-1订单失败、0无效订单、1待确认、2待验票、3已完成、4代收款、）
	 * @author WKX
	 * @since 2016年3月14日 上午10:24:24
	 */
	@Column(name="orderflag")
	public Integer getOrderflag() {
		return orderflag;
	}
	public void setOrderflag(Integer orderflag) {
		this.orderflag = orderflag;
	}
	
	@Column(name="membername")
	public String getMembername() {
		return membername;
	}
	public void setMembername(String membername) {
		this.membername = membername;
	}
	
	@Column(name="picpath")
	public String getPicpath() {
		return picpath;
	}
	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}
	
	
	@Column(name="confirmflag")
	public Integer getConfirmflag() {
		return confirmflag;
	}
	public void setConfirmflag(Integer confirmflag) {
		this.confirmflag = confirmflag;
	}
	
	@Column(name="admindesc")
	public String getAdmindesc() {
		return admindesc;
	}
	public void setAdmindesc(String admindesc) {
		this.admindesc = admindesc;
	}
	
	@Column(name="reasonflag")
	public Integer getReasonflag() {
		return reasonflag;
	}
	public void setReasonflag(Integer reasonflag) {
		this.reasonflag = reasonflag;
	}
	
	@Column(name="reasondesc")
	public String getReasondesc() {
		return reasondesc;
	}
	public void setReasondesc(String reasondesc) {
		this.reasondesc = reasondesc;
	}
	
	@Transient
	public String getOrdertimeshow() {
		return ordertimeshow;
	}
	public void setOrdertimeshow(String ordertimeshow) {
		this.ordertimeshow = ordertimeshow;
	}
	
	@Transient
	public String getType1show() {
		return type1show;
	}
	public void setType1show(String type1show) {
		this.type1show = type1show;
	}
	
	@Transient
	public String getType2show() {
		return type2show;
	}
	public void setType2show(String type2show) {
		this.type2show = type2show;
	}
	
	@Transient
	public String getOrderflagshow() {
		return orderflagshow;
	}
	public void setOrderflagshow(String orderflagshow) {
		this.orderflagshow = orderflagshow;
	}
	
	@Transient
	public String getBegintimeshow() {
		return begintimeshow;
	}
	public void setBegintimeshow(String begintimeshow) {
		this.begintimeshow = begintimeshow;
	}
	
	@Transient
	public String getEndtimeshow() {
		return endtimeshow;
	}
	public void setEndtimeshow(String endtimeshow) {
		this.endtimeshow = endtimeshow;
	}
	
	@Transient
	public String getMembersexshow() {
		return membersexshow;
	}
	public void setMembersexshow(String membersexshow) {
		this.membersexshow = membersexshow;
	}
	
	@Transient
	public String getRecommendpeople() {
		return recommendpeople;
	}
	public void setRecommendpeople(String recommendpeople) {
		this.recommendpeople = recommendpeople;
	}
	
	@Transient
	public Integer getHongbaoprice() {
		return hongbaoprice;
	}
	public void setHongbaoprice(Integer hongbaoprice) {
		this.hongbaoprice = hongbaoprice;
	}
	
	public Discountrecord() {
	}
	
	public Discountrecord(Integer type1, Integer allmoney, String membermobile) {
		super();
		this.type1 = type1;
		this.allmoney = allmoney;
		this.membermobile = membermobile;
	}
	/**
	 * @return the isValid
	 */
	@Column(name="is_valid")
	public String getIsValid() {
		return isValid;
	}
	/**
	 * @param isValid the isValid to set
	 */
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	
	/**
	 * 承兑期限（0半年期、1一年期）
	 * @author WKX
	 * @since 2016年6月12日 上午9:20:23
	 */
	@Column(name="accept_time")
	public Integer getAcceptTime() {
		return acceptTime;
	}
	
	public void setAcceptTime(Integer acceptTime) {
		this.acceptTime = acceptTime;
	}
	
	/**
	 * 交易模式（0先背书后打款、1先打款后背书）
	 * @author WKX
	 * @since 2016年6月15日 下午2:32:11
	 */
	@Column(name="trade_model")
	public Integer getTradeModel() {
		return tradeModel;
	}
	
	public void setTradeModel(Integer tradeModel) {
		this.tradeModel = tradeModel;
	}
	
	/**
	 * 承兑行全称
	 * @author WKX
	 * @since 2016年5月11日 上午10:33:35
	 */
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	
	/**
	 * 经度
	 * @author WKX
	 * @since 2016年5月11日 上午10:44:27
	 */
	public Float getLongitude() {
		return longitude;
	}
	
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}
	
	/**
	 * 纬度
	 * @author WKX
	 * @since 2016年5月11日 上午10:44:42
	 */
	public Float getLatitude() {
		return latitude;
	}
	
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}
	
	/**
	 * 保存城市主键（来自place中文转数据库对应城市）
	 * @author WKX
	 * @since 2016年6月3日 上午9:27:56
	 */
	@Column(name="city_id")
	public Integer getCityId() {
		return cityId;
	}
	
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
	/**
	 * 详细地址
	 * @author WKX
	 * @since 2016年6月3日 上午9:28:28
	 */
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 商户订单号
	 * @author WKX
	 * @since 2016年5月18日 下午4:48:57
	 */
	@Column(name="bns_no")
	public String getBnsNo() {
		return bnsNo;
	}
	
	public void setBnsNo(String bnsNo) {
		this.bnsNo = bnsNo;
	}
	
	/**
	 * 付款方式（0支付宝、1微信、2银联）
	 * @author WKX
	 * @since 2016年5月18日 下午3:28:42
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
	 * @since 2016年5月18日 下午7:22:35
	 */
	public String getCard() {
		return card;
	}
	
	public void setCard(String card) {
		this.card = card;
	}
	
	/**
	 * 交易号（银联、支付宝）
	 * @author WKX
	 * @since 2016年5月18日 下午7:22:48
	 */
	public String getJyh() {
		return jyh;
	}
	
	public void setJyh(String jyh) {
		this.jyh = jyh;
	}
	
	/**
	 * 保证金
	 * @author WKX
	 * @since 2016年5月11日 上午10:29:23
	 */
	public Float getDeposit() {
		return deposit;
	}
	
	public void setDeposit(Float deposit) {
		this.deposit = deposit;
	}
	
	/**
	 * 保证金状态（0待支付、1初始状态（已支付）、2退换自己、3我给机构、4机构给我（含我））
	 * @author WKX
	 * @since 2016年5月11日 上午10:29:40
	 */
	@Column(name="deposit_state")
	public Integer getDepositState() {
		return depositState;
	}
	
	public void setDepositState(Integer depositState) {
		this.depositState = depositState;
	}
	
	/**
	 * 背书
	 * @author WKX
	 * @since 2016年8月12日 下午3:42:11
	 */
	@Column(length=3)
	public Integer getEndorse() {
		return endorse;
	}
	
	public void setEndorse(Integer endorse) {
		this.endorse = endorse;
	}
	
	/**
	 * 票已开出（0是 1否）
	 * @author WKX
	 * @since 2016年8月12日 下午3:42:21
	 */
	@Column(name="has_ticket",length=1)
	public Integer getHasTicket() {
		return hasTicket;
	}
	
	public void setHasTicket(Integer hasTicket) {
		this.hasTicket = hasTicket;
	}
	
	/**
	 * 瑕疵票（0是 1否）
	 * @author WKX
	 * @since 2016年8月12日 下午3:42:36
	 */
	@Column(name="flaw_ticket",length=1)
	public Integer getFlawTicket() {
		return flawTicket;
	}
	
	public void setFlawTicket(Integer flawTicket) {
		this.flawTicket = flawTicket;
	}
	
	/**
	 * 要求上门（1是 0否）
	 * @author WKX
	 * @since 2016年9月1日 下午4:02:06
	 */
	@Column(name="need_todoor",length=1)
	public Integer getNeedTodoor() {
		return needTodoor;
	}
	
	public void setNeedTodoor(Integer needTodoor) {
		this.needTodoor = needTodoor;
	}
	
	/**
	 * 取消原因
	 * @author WKX
	 */
	@Column(name="cancel_cause")
	public String getCancelCause() {
		return cancelCause;
	}
	
	public void setCancelCause(String cancelCause) {
		this.cancelCause = cancelCause;
	}
	
	/**
	 * 拒绝理由（0票面信息有误、1只为熟悉流程和询问价格、2价格不合适、3已提前出票、4其他）
	 * @author WKX
	 */
	public Integer getCancel() {
		return cancel;
	}

	public void setCancel(Integer cancel) {
		this.cancel = cancel;
	}
	
	
	/**
	 * 解密
	 * @author WKX
	 * @param disc
	 * @since 2016年8月19日 上午10:47:02
	 */
	public static Discountrecord deDiscountrecord(Discountrecord disc){
		if(disc==null)return disc;
		Discountrecord temp = new Discountrecord();
		BeanUtils.copyProperties(disc, temp);
		if(temp!=null){
			temp.setMembermobile(Utility.decodeMobile(temp.getMembermobile()));
		}
		return temp;
	}
	
	/**
	 * 解密
	 * @author WKX
	 * @param list
	 * @since 2016年8月19日 上午10:47:13
	 */
	public static List<Discountrecord> deDiscountrecord(List<Discountrecord> list){
		List<Discountrecord> temp = new ArrayList<Discountrecord>();
		if(list!=null){
			for(Discountrecord disc:list){
				temp.add(deDiscountrecord(disc));
			}
		}
		return temp;
	}
	
	/**
	 * 解密
	 * @author WKX
	 * @param list
	 * @since 2016年8月19日 上午10:47:13
	 */
	public static PageResults<Discountrecord> deDiscountrecord(PageResults<Discountrecord> page){
		if(page!=null){
			List<Discountrecord> list = page.getResults();
			page.setResults(deDiscountrecord(list));
		}
		return page;
	}
	
	/**
	 * 加密
	 * @author WKX
	 * @param disc
	 * @since 2016年8月19日 上午10:47:02
	 */
	public static Discountrecord enDiscountrecord(Discountrecord disc){
		if(disc!=null){
			disc.setMembermobile(Utility.encodeMobile(disc.getMembermobile()));
		}
		return disc;
	}
	
	/**
	 * 加密
	 * @author WKX
	 * @param list
	 * @since 2016年8月19日 上午10:47:13
	 */
	public static List<Discountrecord> enDiscountrecord(List<Discountrecord> list){
		if(list!=null){
			for(Discountrecord disc:list){
				disc = enDiscountrecord(disc);
			}
		}
		return list;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	@Column(name="handle_state")
	public Integer getHandleState() {
		return handleState;
	}
	
	public void setHandleState(Integer handleState) {
		this.handleState = handleState;
	}
	
	@Column(name="visit_state")
	public Integer getVisitState() {
		return visitState;
	}
	
	public void setVisitState(Integer visitState) {
		this.visitState = visitState;
	}
	
	/**
	 * 是否退款，快钱新添。（0未退款，1已退款，宝付（2待退款））
	 */
	@Column(name="refund_state",length=1)
	public Integer getRefundState() {
		return refundState;
	}
	
	public void setRefundState(Integer refundState) {
		this.refundState = refundState;
	}
	
}