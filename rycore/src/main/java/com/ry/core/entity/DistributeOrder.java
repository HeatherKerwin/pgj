package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 派单记录（机构端订单）
 * @author WKX
 */
@Entity(name="distribute_order")
public class DistributeOrder extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	
	private Integer dcrdId;//企业提交的  订单的主键
	private Integer orgId;//机构端，机构主键
	private Integer state;//状态（拒绝订单-2（机构端造成）、验票失败-1、无效订单0（企业端造成）、待确认1、验票中2、待付款4、已完成3、已付款5.....）
	private String no;//订单号 例如：JG20160217144633789
	private Date createTime;//创建时间（派单时间）
	private String imagePath;//上传付款凭证
	
	private String price;//年（月）利息
	private String price1;//参数
	private String price2;//每十万贴息
	private Integer way;//报价方式（0方式A：月利率+参数）（1方式B：每十万贴现成本）
	
	private String tzts;//调整天数
	private String jxts;//计息天数
	private String txlx;//贴现利息
	private String txje;//贴现金额
	
	private String lostCause;//@APP2.2 验票失败原因
	
	private Integer payWay;//@APP2.2 付款方式（0支付宝、1微信、2银联、3快钱）
	private String card;//@APP2.2 账号（银联、支付宝）
	
	private String bnsNo;//@APP2.2 商户订单号
	private String jyh;//@APP2.2 交易号（银联、支付宝）
	
	private Float deposit;//@APP2.2 保证金
	private Integer depositState = 0;//@APP2.2 保证金状态（0待支付、1初始状态（已支付）、2退换自己、3我给企业、4企业给我（含我））
	
	private Float todoorPrice;//@APP2.3 上门费用
	private Integer todoorTime;//@APP2.3 上门时间（2小时以内 0；4小时以内 1；6小时以内 2；8小时以内 3；8小时以上 4）
	private Float longitude;//@APP2.3 经度(接单时获取，用来计算贴现订单的距离)
	private Float latitude;//@APP2.3 纬度
	
	private Integer cancel1;//@APP2.3 拒绝理由（1不想支付押金、2额度不够、3票据有问题、4不在业务范围内、5银行大额支付系统已关闭、6其他）
	private Integer cancel2;//@APP2.3 验票失败理由（1票面信息不完整、2票据不真实、3出票人印章与出票人不符合、4印章不清晰、5被背书人填写不正确）
	
	private Integer refundState;//是否退款，快钱新添。（0未退款，1已退款）；宝付（2待退款）
		
	private String rechargeUrl;//兴业数金支付表单【兴业数金】
	
	/**
	 * 企业提交的  订单的主键
	 * @author WKX
	 * @since 2016年3月2日 下午7:45:03
	 */
	@Column(name="dcrd_id")
	public Integer getDcrdId() {
		return dcrdId;
	}

	public void setDcrdId(Integer dcrdId) {
		this.dcrdId = dcrdId;
	}

	/**
	 * 认证主键
	 * @author WKX
	 * @since 2016年3月2日 下午7:45:27
	 */
	@Column(name="org_id")
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	/**
	 * 状态（拒绝订单-2（机构端造成）、验票失败-1、无效订单0（企业端造成）、待确认1、验票中2、待付款4、已完成3、已付款5.....）
	 * @author WKX
	 * @since 2016年3月2日 下午7:45:52
	 */
	@Column(length=1)
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * 订单号 例如：JG20160217144633789
	 * @author WKX
	 * @since 2016年3月2日 下午7:47:45
	 */
	@Column(length=50)
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 上传付款凭证
	 * @author WKX
	 * @since 2016年3月2日 下午7:49:10
	 */
	@Column(name="image_path",length=255)
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	/**
	 * 年（月）利息
	 * @author WKX
	 * @since 2016年3月15日 下午3:47:20
	 */
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	/**
	 * 参数
	 * @author WKX
	 * @since 2016年4月7日 上午11:12:27
	 */
	public String getPrice1() {
		return price1;
	}

	public void setPrice1(String price1) {
		this.price1 = price1;
	}

	/**
	 * 每十万贴息
	 * @author WKX
	 * @since 2016年4月7日 上午11:12:29
	 */
	public String getPrice2() {
		return price2;
	}

	public void setPrice2(String price2) {
		this.price2 = price2;
	}

	/**
	 * 报价方式（0方式A：月利率+参数）（1方式B：每十万贴现成本）
	 * @author WKX
	 * @since 2016年4月21日 上午10:09:15
	 */
	public Integer getWay() {
		return way;
	}

	public void setWay(Integer way) {
		this.way = way;
	}
	
	/**
	 * 调整天数
	 * @author WKX
	 * @since 2016年4月21日 下午1:16:21
	 */
	public String getTzts() {
		return tzts;
	}

	public void setTzts(String tzts) {
		this.tzts = tzts;
	}

	/**
	 * 计息天数
	 * @author WKX
	 * @since 2016年4月21日 下午1:16:30
	 */
	public String getJxts() {
		return jxts;
	}

	public void setJxts(String jxts) {
		this.jxts = jxts;
	}

	/**
	 * 贴现利息
	 * @author WKX
	 * @since 2016年4月21日 下午1:16:39
	 */
	public String getTxlx() {
		return txlx;
	}

	public void setTxlx(String txlx) {
		this.txlx = txlx;
	}

	/**
	 * 贴现金额()
	 * @author WKX
	 * @since 2016年4月21日 下午1:16:47
	 */
	public String getTxje() {
		return txje;
	}

	public void setTxje(String txje) {
		this.txje = txje;
	}
	
	/**
	 * 验票失败
	 * @author WKX
	 * @since 2016年5月10日 下午5:54:18
	 */
	public String getLostCause() {
		return lostCause;
	}

	public void setLostCause(String lostCause) {
		this.lostCause = lostCause;
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
	 * 保证金
	 * @author WKX
	 * @since 2016年5月11日 上午10:27:00
	 */
	public Float getDeposit() {
		return deposit;
	}

	public void setDeposit(Float deposit) {
		this.deposit = deposit;
	}

	/**
	 * 保证金状态（0待支付、1初始状态（已支付）、2退换自己、3我给企业、4企业给我（含我））
	 * @author WKX
	 * @since 2016年5月11日 上午10:27:09
	 */
	@Column(name="deposit_state")
	public Integer getDepositState() {
		return depositState;
	}

	public void setDepositState(Integer depositState) {
		this.depositState = depositState;
	}
	
	/**
	 * 上门费用
	 * @author WKX
	 * @since 2016年8月18日 下午3:31:13
	 */
	@Column(name="todoor_price")
	public Float getTodoorPrice() {
		return todoorPrice;
	}
	
	public void setTodoorPrice(Float todoorPrice) {
		this.todoorPrice = todoorPrice;
	}
	
	/**
	 * 上门时间（2小时以内 0；4小时以内 1；6小时以内 2；8小时以内 3；8小时以上 4）
	 * @author WKX
	 * @since 2016年8月18日 下午3:31:00
	 */
	@Column(name="todoor_time",length=1)
	public Integer getTodoorTime() {
		return todoorTime;
	}
	
	public void setTodoorTime(Integer todoorTime) {
		this.todoorTime = todoorTime;
	}
	
	/**
	 * 经度
	 * @author WKX
	 * @since 2016年8月18日 下午2:46:45
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
	 * @since 2016年8月18日 下午2:47:17
	 */
	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	/**
	 * 拒绝理由（1不想支付押金、2额度不够、3票据有问题、4不在业务范围内、5银行大额支付系统已关闭、6其他）
	 * @author WKX
	 * @since 2016年8月29日 下午2:54:52
	 */
	@Column(length=1)
	public Integer getCancel1() {
		return cancel1;
	}

	public void setCancel1(Integer cancel1) {
		this.cancel1 = cancel1;
	}

	/**
	 * 验票失败理由（1票面信息不完整、2票据不真实、3出票人印章与出票人不符合、4印章不清晰、5被背书人填写不正确）
	 * @author WKX
	 * @since 2016年8月29日 下午2:55:09
	 */
	@Column(length=1)
	public Integer getCancel2() {
		return cancel2;
	}

	public void setCancel2(Integer cancel2) {
		this.cancel2 = cancel2;
	}
	
	/**
	 * @author MH
	 * @return 是否退款，快钱新添
	 */
	@Column(name="refund_state",length=1)
	public Integer getRefundState() {
		return refundState;
	}

	public void setRefundState(Integer refundState) {
		this.refundState = refundState;
	}
	
	/**
	 * 兴业数金支付表单
	 */
	@Column(name="recharge_url")
	public String getRechargeUrl() {
		return rechargeUrl;
	}

	public void setRechargeUrl(String rechargeUrl) {
		this.rechargeUrl = rechargeUrl;
	}

	/**
	 * 状态（无效订单0、待确认1、验票中2、待付款4、已完成3、已付款5、待复核6.....）
	 * @author WKX
	 */
	public static enum State{
		INVALID(0,"无效订单"),UNCONFIRM(1,"待确认"),CONFIRM(2,"验票中"),FINISH(3,"已完成"),WAITPAY(4,"待付款"),PAY(5,"已付款"),EXAMINE(6,"待复核");
		public String name;
		private Integer key;
		private String value;
		State(Integer key,String name){
			this.key = key;
			this.name = name;
			this.value = name;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Integer getKey() {
			return key;
		}
		public void setKey(Integer key) {
			this.key = key;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		
		public static State valueOf(int falg){
			switch (falg) {
			case 0:
				return INVALID;
			case 1:
				return UNCONFIRM;
			case 2:
				return CONFIRM;
			case 3:
				return FINISH;
			case 4:
				return WAITPAY;
			case 5:
				return PAY;
			case 6:
				return EXAMINE;
			}
			return null;
		}
	}
}