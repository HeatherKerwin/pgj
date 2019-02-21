package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 认证机构的额度
 * @author WKX
 */
@Entity(name="org_limit")
public class OrgLimit extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	
	private Integer orgId;//认证主键
	private String day;//哪一天的额度
	private Integer price;//额度（金钱）
	private Date createTime;
	
	private Float usedPrice;//@APP2.2 已用额度(金钱)
	
	private Integer priceSp;//@APP2.3 商票额度
	private Float usedPriceSp;//@APP2.3 商票已用额度
	
	private String remarkSp;//商票备注
	private String remarkYp;//银票备注
	
	/**
	 * 认证主键
	 * @author WKX
	 * @since 2016年3月3日 上午9:23:52
	 */
	@Column(name="org_id")
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	/**
	 * 哪一天的额度
	 * @author WKX
	 * @since 2016年3月3日 上午9:24:32
	 */
	@Column(length=20)
	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	/**
	 * 创建日期
	 * @author WKX
	 * @since 2016年3月3日 上午9:25:26
	 */
	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name="used_price")
	public Float getUsedPrice() {
		return usedPrice;
	}

	public void setUsedPrice(Float usedPrice) {
		this.usedPrice = usedPrice;
	}

	/**
	 * 商票额度
	 * @author WKX
	 * @since 2016年8月15日 上午10:18:57
	 */
	@Column(name="price_sp")
	public Integer getPriceSp() {
		return priceSp;
	}

	public void setPriceSp(Integer priceSp) {
		this.priceSp = priceSp;
	}

	/**
	 * 已用商票额度
	 * @author WKX
	 * @since 2016年8月15日 上午10:21:26
	 */
	@Column(name="used_price_sp")
	public Float getUsedPriceSp() {
		return usedPriceSp;
	}

	public void setUsedPriceSp(Float usedPriceSp) {
		this.usedPriceSp = usedPriceSp;
	}

	/**
	 * 商票的备注
	 * @author MH
	 * @return
	 */
	@Column(name="remark_sp")
	public String getRemarkSp() {
		return remarkSp;
	}

	public void setRemarkSp(String remarkSp) {
		this.remarkSp = remarkSp;
	}
	
	/**
	 * 银票的备注
	 * @author MH
	 * @return
	 */
	@Column(name="remark_yp")
	public String getRemarkYp() {
		return remarkYp;
	}

	public void setRemarkYp(String remarkYp) {
		this.remarkYp = remarkYp;
	}
}