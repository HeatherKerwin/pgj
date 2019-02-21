package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 派单记录（机构端订单）批量
 * @author KHC
 */
@Entity(name="distribute_order_pl")
public class DistributeOrderPl extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	private Integer dcrdPlId;//企业提交的  订单的主键
	private Integer orgId;//机构端，机构主键
	private Integer state;//状态（-2拒绝订单、无效订单0、待确认1、交易中(机构接单了)2、已完成3）
	private String no;//订单号 例如：JG20160217144633789
	private Date createTime;//创建时间（派单时间）
	private String cancelCause;//取消原因
	private Integer cancel;//取消理由（0票面信息有误、1只为熟悉流程和询问价格、2价格不合适、3已提前出票、4其他）
	private Float longitude;//经度
	private Float latitude;//纬度
	private Integer take;//接单标示（1接受、其他默认没接受）
	
	/**
	 * 企业提交的  订单的主键
	 * @author KHC
	 * @since 2016年7月28日 下午3:16:27
	 */
	@Column(name="dcrd_pl_id")
	public Integer getDcrdPlId() {
		return dcrdPlId;
	}
	
	public void setDcrdPlId(Integer dcrdPlId) {
		this.dcrdPlId = dcrdPlId;
	}
	
	/**
	 * 机构端，机构主键
	 * @author KHC
	 * @since 2016年7月28日 下午3:17:00
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
	 * @since 2016年7月28日 下午3:17:28
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
	 * @since 2016年7月28日 下午3:17:51
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
	 * @since 2016年7月28日 下午3:18:42
	 */
	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * 取消原因
	 * @author KHC
	 * @since 2016年7月28日 下午3:19:19
	 */
	@Column(name="cancel_cause")
	public String getCancelCause() {
		return cancelCause;
	}
	
	public void setCancelCause(String cancelCause) {
		this.cancelCause = cancelCause;
	}

	/**
	 * 取消理由
	 * @author ZY
	 * @since 2016年9月27日 下午3:19:19
	 */
	public Integer getCancel() {
		return cancel;
	}

	public void setCancel(Integer cancel) {
		this.cancel = cancel;
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
	 * 接单标示（1接受、其他默认没接受）
	 * @author WKX
	 * @since 2016年8月25日 上午11:05:27
	 */
	@Column(length=1)
	public Integer getTake() {
		return take;
	}

	public void setTake(Integer take) {
		this.take = take;
	}
}