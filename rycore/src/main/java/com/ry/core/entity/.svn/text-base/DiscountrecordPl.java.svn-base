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
 * 批量贴现
 * @author KHC
 */
@Entity(name="discountrecord_pl")
public class DiscountrecordPl extends BaseModel {

	private static final long serialVersionUID = 1L;

	private Integer type1;//票据类型1.纸票，2.电票
	private String type2;//承兑行类型 1.国股，2.大商，3.小商，4.三农，5.其它
	private Integer memberSex;//性别（1.男，2.女）
	private String memberMobile;//联系方式
	private String remarks;//备注
	private Date createTime;//下单时间
	private String no;//订单号
	private Integer memberId;//用户Id
	private Integer orderflag;//订单状态（-2.取消订单，0.无效订单，1.待确认，2.进行中，3.已完成）
	private String memberName;//联系人
	
	private Date endtime;//到期时间
	
	private Double allmoney;//总金额
	private Double minMoney;//收票金额区间（小）
	private Double maxMoney;//收票金额区间（大）
	
	private Integer amount;//票据总数量
	private Integer minExpiryDay;//最短到期天数（小）
	private Integer maxExpiryDay;//最长到期天数（大）
	
	private String isValid = "T";//T ：有效  F：无效（表示删除）
	private Float longitude;//经度
	private Float latitude;//纬度
	private String place;//地区（交易地址）
	private String address;//详细地址
	private Integer needTodoor;//要求上门（1是 0否）
	private Integer flawTicket;//瑕疵票（0是 1否）
	
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
	
	/**
	 * 票据类型1.纸票，2.电票
	 * @author KHC
	 * @since 2016年7月28日 下午2:40:36
	 */
	@Column(length=1)
	public Integer getType1() {
		return type1;
	}
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setType1(Integer type1) {
		this.type1 = type1;
	}
	
	/**
	 * 承兑行类型 1.国股，2.大商，3.小商，4.三农，5.其它
	 * @author KHC
	 * @since 2016年7月28日 下午2:41:50
	 */
	@Column(length=50)
	public String getType2() {
		return type2;
	}
	
	public void setType2(String type2) {
		this.type2 = type2;
	}
	
	/**
	 * 性别 1男2女
	 * @author KHC
	 * @since 2016年7月28日 下午2:47:25
	 */
	@Column(name="member_sex",length=1)
	public Integer getMemberSex() {
		return memberSex;
	}
	
	public void setMemberSex(Integer memberSex) {
		this.memberSex = memberSex;
	}
	
	/**
	 * 联系方式
	 * @author KHC
	 * @since 2016年7月28日 下午2:48:27
	 */
	@Column(name="member_mobile")
	public String getMemberMobile() {
		return memberMobile;
	}
	
	public void setMemberMobile(String memberMobile) {
		this.memberMobile = memberMobile;
	}
	
	/**
	 * 备注
	 * @author KHC
	 * @since 2016年7月28日 下午2:49:19
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
	 * @since 2016年7月28日 下午2:51:29
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
	 * @since 2016年7月28日 下午2:53:58
	 */
	@Column(length=100)
	public String getNo() {
		return no;
	}
	
	public void setNo(String no) {
		this.no = no;
	}
	
	/**
	 * 用户id
	 * @author KHC
	 * @since 2016年7月28日 下午2:54:35
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
	 * @since 2016年7月28日 下午2:55:47
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
	 * @since 2016年7月28日 下午2:56:46
	 */
	@Column(name="member_name",length=100)
	public String getMemberName() {
		return memberName;
	}
	
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	/**
	 * 到期日期
	 * @author WKX
	 */
	public Date getEndtime() {
		return endtime;
	}
	
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	
	/**
	 * 总金额
	 * @author KHC
	 * @since 2016年7月28日 下午2:57:16
	 */
	public Double getAllmoney() {
		return allmoney;
	}
	
	public void setAllmoney(Double allmoney) {
		this.allmoney = allmoney;
	}
	
	/**
	 * 票据最小金额
	 * @author WKX
	 * @since 2016年8月15日 下午5:55:58
	 */
	@Column(name="min_money")
	public Double getMinMoney() {
		return minMoney;
	}

	public void setMinMoney(Double minMoney) {
		this.minMoney = minMoney;
	}

	/**
	 * 票据最大金额
	 * @author WKX
	 * @since 2016年8月15日 下午5:56:32
	 */
	@Column(name="max_money")
	public Double getMaxMoney() {
		return maxMoney;
	}

	public void setMaxMoney(Double maxMoney) {
		this.maxMoney = maxMoney;
	}
	
	/**
	 * 票据总数量
	 * @author WKX
	 */
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	/**
	 * 最小到期天数
	 * @author WKX
	 */
	@Column(name="min_expiry_day")
	public Integer getMinExpiryDay() {
		return minExpiryDay;
	}

	public void setMinExpiryDay(Integer minExpiryDay) {
		this.minExpiryDay = minExpiryDay;
	}

	/**
	 * 最大到期天数
	 * @author WKX
	 */
	@Column(name="max_expiry_day")
	public Integer getMaxExpiryDay() {
		return maxExpiryDay;
	}

	public void setMaxExpiryDay(Integer maxExpiryDay) {
		this.maxExpiryDay = maxExpiryDay;
	}

	@Column(name="is_valid",length=1)
	public String getIsValid() {
		return isValid;
	}
	
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	
	/**
	 * 经度
	 * @author KHC
	 * @since 2016年7月28日 下午2:59:01
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
	 * @since 2016年7月28日 下午2:58:47
	 */
	public Float getLatitude() {
		return latitude;
	}
	
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}
	
	/**
	 * 地区
	 * @author KHC
	 * @since 2016年8月24日 下午4:37:25
	 */
	@Column(name="place")
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
	
	/**
	 * 详细地址
	 * @author KHC
	 * @since 2016年7月28日 下午2:59:24
	 */
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 要求上门（1是 0否）
	 * @author KHC
	 * @since 2016年7月28日 下午3:04:26
	 */
	@Column(name="need_todoor",length=1)
	public Integer getNeedTodoor() {
		return needTodoor;
	}
	
	public void setNeedTodoor(Integer needTodoor) {
		this.needTodoor = needTodoor;
	}
	
	/**
	 * 瑕疵票（0是 1否）
	 * @author KHC
	 * @since 2016年7月28日 下午3:05:39
	 */
	@Column(name="flaw_ticket",length=1)
	public Integer getFlawTicket() {
		return flawTicket;
	}
	
	public void setFlawTicket(Integer flawTicket) {
		this.flawTicket = flawTicket;
	}
	
	@Transient
	public String getOrderflagshow() {
		return orderflagshow;
	}

	public void setOrderflagshow(String orderflagshow) {
		this.orderflagshow = orderflagshow;
	}
	
	/**
	 * 解密单个DiscountrecordPl对象
	 * @author ZY
	 * @param disc
	 * 2016年9月23日下午2:44:37
	 */
	public static DiscountrecordPl deDiscountrecordPl(DiscountrecordPl disc){
		if(disc==null)return disc;
		DiscountrecordPl temp = new DiscountrecordPl();
		BeanUtils.copyProperties(disc, temp);
		if(temp!=null){
			temp.setMemberMobile(Utility.decodeMobile(temp.getMemberMobile()));
		}
		return temp;
	}
	
	/**
	 * 解密List<DiscountrecordPl>
	 * @author ZY
	 * @param list
	 * 2016年9月23日下午2:44:24
	 */
	public static List<DiscountrecordPl> deDiscountrecordPl(List<DiscountrecordPl> list){
		List<DiscountrecordPl> temp = new ArrayList<DiscountrecordPl>();
		if(list!=null){
			for(DiscountrecordPl disc:list){
				temp.add(deDiscountrecordPl(disc));
			}
		}
		return temp;
	}
	
	/**
	 * 解密PageResults<DiscountrecordPl>
	 * @author ZY
	 * @param page
	 * 2016年9月23日下午2:44:24
	 */
	public static PageResults<DiscountrecordPl> deDiscountrecordPl(PageResults<DiscountrecordPl> page){
		if(page!=null){
			List<DiscountrecordPl> list = page.getResults();
			page.setResults(deDiscountrecordPl(list));
		}
		return page;
	}
	
	/**
	 * 加密单个DiscountrecordPl对象
	 * @author ZY
	 * @param disc
	 * 2016年9月23日下午2:46:34
	 */
	public static DiscountrecordPl enDiscountrecordPl(DiscountrecordPl disc){
		if(disc!=null){
			disc.setMemberMobile(Utility.encodeMobile(disc.getMemberMobile()));
		}
		return disc;
	}
	
	/**
	 * 加密List<DiscountrecordPl>
	 * @author ZY
	 * @param list
	 * 2016年9月23日下午2:48:16
	 */
	public static List<DiscountrecordPl> enDiscountrecordPl(List<DiscountrecordPl> list){
		if(list!=null){
			for(DiscountrecordPl disc:list){
				disc = enDiscountrecordPl(disc);
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