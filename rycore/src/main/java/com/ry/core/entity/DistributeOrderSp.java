package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 派单记录（机构端订单）商票
 * @author KHC
 */
@Entity(name="distribute_order_sp")
public class DistributeOrderSp extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	private Integer dcrdSpId;//企业提交的  订单的主键
	private Integer orgId;//机构端，机构主键
	private Integer state;//状态（-2拒绝订单、无效订单0、待确认1、交易中(机构接单了)2、已完成3）
	private String no;//订单号
	private Date createTime;//创建时间（派单时间）
	
	private String price;//年（月）利息
	private String price1;//参数
	private String price2;//每十万贴息
	private Integer way;//报价方式（0方式A：月利率+参数）（1方式B：每十万贴现成本）（2方式A：年利率+参数）
	
	private String jxts;//计息天数
	private String txlx;//贴现利息
	
	private String cancelCause;//取消原因
	private Integer cancel;//拒绝理由（1额度不够、2不在业务范围内、3无法上门收票、4银行大额支付系统已关闭、5其他）
	
	private String needStuff;//所需材料（逗号间隔）（1贸易合同、2增值税发票、3盖章、4法人身份证、5经办人身份证、6开户许可证、7营业执照、8税务登记证、9组织机构代码证、10贷款卡、11保函、12以上所有纸质材料的复印件）
	private Float todoorPrice;//上门费用
	private Integer todoorTime;//上门时间（2小时以内 0；4小时以内 1；6小时以内 2；8小时以内 3；8小时以上 4）
	
	private Integer isSelect;//企业端选择的机构（1选择、其它未选择过）
	private Float longitude;//经度
	private Float latitude;//纬度
	
	private String rechargeUrl;//兴业数金支付表单【兴业数金】
	
	/**
	 * 企业提交的  订单的主键
	 * @author KHC
	 * @since 2016年7月28日 下午1:57:37
	 */
	@Column(name="dcrd_sp_id")
	public Integer getDcrdSpId() {
		return dcrdSpId;
	}
	
	public void setDcrdSpId(Integer dcrdSpId) {
		this.dcrdSpId = dcrdSpId;
	}
	
	/**
	 * 机构端，机构主键
	 * @author KHC
	 * @since 2016年7月28日 下午1:58:14
	 */
	@Column(name="org_id")
	public Integer getOrgId() {
		return orgId;
	}
	
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
	/**
	 * 状态（-2拒绝订单、无效订单0、待确认1、交易中(机构接单了)2、已完成3）
	 * @author KHC
	 * @since 2016年7月28日 下午1:59:14
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
	 * @author KHC
	 * @since 2016年7月28日 下午2:00:14
	 */
	@Column(length=50)
	public String getNo() {
		return no;
	}
	
	public void setNo(String no) {
		this.no = no;
	}
	
	/**
	 * 创建时间（派单时间）
	 * @author KHC
	 * @since 2016年7月28日 下午2:00:33
	 */
	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * 年月利息
	 * @author KHC
	 * @since 2016年7月28日 下午2:01:18
	 */
	public String getPrice() {
		return price;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	
	/**
	 * 参数
	 * @author KHC
	 * @since 2016年7月28日 下午2:01:38
	 */
	public String getPrice1() {
		return price1;
	}
	
	public void setPrice1(String price1) {
		this.price1 = price1;
	}
	
	/**
	 * 每十万贴息
	 * @author KHC
	 * @since 2016年7月28日 下午2:01:56
	 */
	public String getPrice2() {
		return price2;
	}
	
	public void setPrice2(String price2) {
		this.price2 = price2;
	}
	
	/**
	 * 报价方式（0方式A：月利率+参数）（1方式B：每十万贴现成本）
	 * @author KHC
	 * @since 2016年7月28日 下午2:02:40
	 */
	@Column(length=1)
	public Integer getWay() {
		return way;
	}
	
	public void setWay(Integer way) {
		this.way = way;
	}
	
	/**
	 * 参考贴现利息
	 * @author WKX
	 */
	public String getTxlx() {
		return txlx;
	}

	public void setTxlx(String txlx) {
		this.txlx = txlx;
	}
	
	/**
	 * 计息天数
	 * @author WKX
	 * @since 2016年8月17日 上午10:34:22
	 */
	public String getJxts() {
		return jxts;
	}

	public void setJxts(String jxts) {
		this.jxts = jxts;
	}

	/**
	 * 取消原因
	 * @author KHC
	 * @since 2016年7月28日 下午2:03:36
	 */
	@Column(name="cancel_cause")
	public String getCancelCause() {
		return cancelCause;
	}
	
	public void setCancelCause(String cancelCause) {
		this.cancelCause = cancelCause;
	}
	
	/**
	 * 拒绝理由（1额度不够、2不在业务范围内、3无法上门收票、4银行大额支付系统已关闭、5其他）
	 * @author WKX
	 */
	public Integer getCancel() {
		return cancel;
	}

	public void setCancel(Integer cancel) {
		this.cancel = cancel;
	}

	/**
	 * 所需材料（逗号间隔）
	 * @author KHC
	 * @since 2016年7月28日 下午2:04:22
	 */
	@Column(name="need_stuff")
	public String getNeedStuff() {
		return needStuff;
	}
	
	public void setNeedStuff(String needStuff) {
		this.needStuff = needStuff;
	}
	
	/**
	 * 上门费用
	 * @author KHC
	 * @since 2016年7月28日 下午2:06:24
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
	 * @author KHC
	 * @since 2016年7月28日 下午2:07:04
	 */
	@Column(name="todoor_time",length=1)
	public Integer getTodoorTime() {
		return todoorTime;
	}
	
	public void setTodoorTime(Integer todoorTime) {
		this.todoorTime = todoorTime;
	}

	/**
	 * 企业端选择的机构（1选择、其它未选择过）
	 * @author WKX
	 */
	@Column(name="is_select",length=1)
	public Integer getIsSelect() {
		return isSelect;
	}

	public void setIsSelect(Integer isSelect) {
		this.isSelect = isSelect;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}
	
	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
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
}