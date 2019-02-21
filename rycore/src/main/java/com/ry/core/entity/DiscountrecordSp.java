package com.ry.core.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.springframework.beans.BeanUtils;

import com.ry.core.util.Utility;
import com.ry.util.page.PageResults;

/**
 * 商票贴现
 * @author KHC
 */
@Entity(name="discountrecord_sp")
public class DiscountrecordSp extends BaseModel{

	private static final long serialVersionUID = 1L;
	
	private Integer type1;//票据类型（1.纸票，2.电票）
	
	private Date printtime;//开票日期
	private Date begintime;//贴现时间
	private Date endtime;//到期时间
	private Integer memberSex;//性别（1.男，2.女）
	private String memberMobile;//联系方式
	private String remarks;//备注
	private Date createTime;//下单时间
	private String no;//订单号
	private Integer memberId;//用户Id
	private Integer orderflag;//订单状态（0.无效订单，1.待交易，2.交易中，3.已完成）
	private String memberName;//联系人
	private String picpath;//票据图片
	private Double allmoney;//总金额
	private String isValid = "T";//T ：有效  F：无效（表示删除）
	private String bank;//承兑行全称
	private Float longitude;//经度
	private Float latitude;//纬度
	private String place;//交易城市
	private String address;//详细地址
	private Integer acceptTime;//承兑期限（0.半年期，1.一年期）
	private Integer tradeModel;//交易模式（0.先背书后打款，1.先打款后背书）
	private Integer endorse;//背书
	private Integer hasTicket;//票已开出（0.是，1.否）
	private Integer needTodoor;//要求上门（1是 0否）
	
	private String cancelCause;//取消原因
	private Integer cancel;//取消理由（0票面信息有误、1只为熟悉流程和询问价格、2价格不合适、3已提前出票、4其他）
	
	private String orderflagshow;//派单标识
	private String source;//来源，区分单子来之APP还是PC
	
	private Integer handleState;//处理状态(0.待处理，1.处理中，2.无效订单，3.订单进行中，4.订单完成)
	
	@Column(name="handle_state")
	public Integer getHandleState() {
		return handleState;
	}
	
	public void setHandleState(Integer handleState) {
		this.handleState = handleState;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * 票据类型（1.纸票，2.电票）
	 * @author KHC
	 * @since 2016年7月28日 下午1:21:40
	 */
	@Column(length=1)
	public Integer getType1() {
		return type1;
	}
	
	public void setType1(Integer type1) {
		this.type1 = type1;
	}
	
	/**
	 * 出票日期
	 * @author WKX
	 * @since 2016年9月10日 上午10:58:14
	 */
	public Date getPrinttime() {
		return printtime;
	}

	public void setPrinttime(Date printtime) {
		this.printtime = printtime;
	}

	/**
	 * 贴现时间
	 * @author KHC
	 * @since 2016年7月28日 下午1:22:51
	 */
	public Date getBegintime() {
		return begintime;
	}
	
	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}
	
	/**
	 * 到期时间
	 * @author KHC
	 * @since 2016年7月28日 下午1:23:25
	 */
	public Date getEndtime() {
		return endtime;
	}
	
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	
	/**
	 * 性别（1.男，2.女）
	 * @author KHC
	 * @since 2016年7月28日 下午1:23:49
	 */
	@Column(name="member_sex")
	public Integer getMemberSex() {
		return memberSex;
	}
	
	public void setMemberSex(Integer memberSex) {
		this.memberSex = memberSex;
	}
	
	/**
	 * 联系方式
	 * @author KHC
	 * @since 2016年7月28日 下午1:24:22
	 */
	@Column(name="member_mobile",length=50)
	public String getMemberMobile() {
		return memberMobile;
	}
	
	public void setMemberMobile(String memberMobile) {
		this.memberMobile = memberMobile;
	}
	
	/**
	 * 备注
	 * @author KHC
	 * @since 2016年7月28日 下午1:24:41
	 */
	@Column(length=100)
	public String getRemarks() {
		return remarks;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	/**
	 * 下单时间
	 * @author KHC
	 * @since 2016年7月28日 下午1:25:24
	 */
	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * 订单号
	 * @author KHC
	 * @since 2016年7月28日 下午1:25:46
	 */
	@Column(length=100)
	public String getNo() {
		return no;
	}
	
	public void setNo(String no) {
		this.no = no;
	}
	
	/**
	 * 用户主键id
	 * @author KHC
	 * @since 2016年7月28日 下午1:26:04
	 */
	@Column(name="member_id")
	public Integer getMemberId() {
		return memberId;
	}
	
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	
	/**
	 * 订单状态（0.无效订单，1.待确认，2.进行中，3.已完成）
	 * @author KHC
	 * @since 2016年7月28日 下午1:26:28
	 */
	@Column(length=1)
	public Integer getOrderflag() {
		return orderflag;
	}
	
	public void setOrderflag(Integer orderflag) {
		this.orderflag = orderflag;
	}
	
	/**
	 * 联系人
	 * @author KHC
	 * @since 2016年7月28日 下午1:27:14
	 */
	@Column(name="member_name",length=100)
	public String getMemberName() {
		return memberName;
	}
	
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	/**
	 * 票据图片
	 * @author KHC
	 * @since 2016年7月28日 下午1:27:30
	 */
	public String getPicpath() {
		return picpath;
	}
	
	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}
	
	/**
	 * 总金额
	 * @author KHC
	 * @since 2016年7月28日 下午1:27:52
	 */
	public Double getAllmoney() {
		return allmoney;
	}
	
	public void setAllmoney(Double allmoney) {
		this.allmoney = allmoney;
	}
	
	/**
	 * T ：有效  F：无效（表示删除）
	 * @author KHC
	 * @since 2016年7月28日 下午1:33:41
	 */
	@Column(name="is_valid",length=1)
	public String getIsValid() {
		return isValid;
	}
	
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	
	/**
	 * 承兑行全称
	 * @author KHC
	 * @since 2016年7月28日 下午1:34:05
	 */
	@Column(length=50)
	public String getBank() {
		return bank;
	}
	
	public void setBank(String bank) {
		this.bank = bank;
	}
	
	/**
	 * 经度
	 * @author KHC
	 * @since 2016年7月28日 下午1:35:38
	 */
	public Float getLongitude() {
		return longitude;
	}
	
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}
	
	/**
	 * 纬度
	 * @author KHC
	 * @since 2016年7月28日 下午1:35:21
	 */
	public Float getLatitude() {
		return latitude;
	}
	
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}
	
	/**
	 * 详细地址
	 * @author KHC
	 * @since 2016年7月28日 下午1:35:56
	 */
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 承兑期限（0.半年期，1.一年期）
	 * @author KHC
	 * @since 2016年7月28日 下午1:37:01
	 */
	@Column(name="accept_time",length=1)
	public Integer getAcceptTime() {
		return acceptTime;
	}
	
	public void setAcceptTime(Integer acceptTime) {
		this.acceptTime = acceptTime;
	}
	
	/**
	 * 交易模式（0.先背书后打款，1.先打款后背书）
	 * @author KHC
	 * @since 2016年7月28日 下午1:38:25
	 */
	@Column(name="trade_model",length=1)
	public Integer getTradeModel() {
		return tradeModel;
	}
	
	public void setTradeModel(Integer tradeModel) {
		this.tradeModel = tradeModel;
	}
	
	/**
	 * 背书
	 * @author KHC
	 * @since 2016年7月28日 下午1:39:15
	 */
	@Column(length=1)
	public Integer getEndorse() {
		return endorse;
	}
	
	public void setEndorse(Integer endorse) {
		this.endorse = endorse;
	}
	
	/**
	 * 票已开出（0是 1否）
	 * @author KHC
	 * @since 2016年7月28日 下午2:18:25
	 */
	@Column(name="has_ticket",length=1)
	public Integer getHasTicket() {
		return hasTicket;
	}
	
	public void setHasTicket(Integer hasTicket) {
		this.hasTicket = hasTicket;
	}
	
	/**
	 * 要求上门（1是 0否）
	 * @author WKX
	 */
	@Column(name="need_todoor",length=1)
	public Integer getNeedTodoor() {
		return needTodoor;
	}
	
	public void setNeedTodoor(Integer needTodoor) {
		this.needTodoor = needTodoor;
	}

	/**
	 * 交易城市
	 * @author KHC
	 * @since 2016年8月16日 上午10:02:49
	 */
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	@Transient
	public String getOrderflagshow() {
		return orderflagshow;
	}

	public void setOrderflagshow(String orderflagshow) {
		this.orderflagshow = orderflagshow;
	}
	
	/**
	 * 解密单个DiscountrecordSp对象
	 * @author ZY
	 * @param disc
	 * 2016年9月23日下午2:44:37
	 */
	public static DiscountrecordSp deDiscountrecordSp(DiscountrecordSp disc){
		if(disc==null)return disc;
		DiscountrecordSp temp = new DiscountrecordSp();
		BeanUtils.copyProperties(disc, temp);
		if(temp!=null){
			temp.setMemberMobile(Utility.decodeMobile(temp.getMemberMobile()));
		}
		return temp;
	}
	
	/**
	 * 解密List<DiscountrecordSp>
	 * @author ZY
	 * @param list
	 * 2016年9月23日下午2:44:24
	 */
	public static List<DiscountrecordSp> deDiscountrecordSp(List<DiscountrecordSp> list){
		List<DiscountrecordSp> temp = new ArrayList<DiscountrecordSp>();
		if(list!=null){
			for(DiscountrecordSp disc:list){
				temp.add(deDiscountrecordSp(disc));
			}
		}
		return temp;
	}
	
	/**
	 * 解密PageResults<DiscountrecordSp>
	 * @author ZY
	 * @param page
	 * 2016年9月23日下午2:44:24
	 */
	public static PageResults<DiscountrecordSp> deDiscountrecordSp(PageResults<DiscountrecordSp> page){
		if(page!=null){
			List<DiscountrecordSp> list = page.getResults();
			page.setResults(deDiscountrecordSp(list));
		}
		return page;
	}
	
	/**
	 * 加密单个DiscountrecordSp对象
	 * @author ZY
	 * @param disc
	 * 2016年9月23日下午2:46:34
	 */
	public static DiscountrecordSp enDiscountrecordSp(DiscountrecordSp disc){
		if(disc!=null){
			disc.setMemberMobile(Utility.encodeMobile(disc.getMemberMobile()));
		}
		return disc;
	}
	
	/**
	 * 加密List<DiscountrecordSp>
	 * @author ZY
	 * @param list
	 * 2016年9月23日下午2:48:16
	 */
	public static List<DiscountrecordSp> enDiscountrecordSp(List<DiscountrecordSp> list){
		if(list!=null){
			for(DiscountrecordSp disc:list){
				disc = enDiscountrecordSp(disc);
			}
		}
		return list;
	}
	
	/**
	 * 取消原因
	 * @author KHC
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
	 * @author KHC
	 */
	public Integer getCancel() {
		return cancel;
	}

	public void setCancel(Integer cancel) {
		this.cancel = cancel;
	}
}