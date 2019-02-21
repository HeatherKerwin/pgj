package com.ry.core.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;


/**
 * 
 * @author ZWD
 *
 */
@Entity(name = "org_extend_info")
public class OrgExtendInfo extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer daDianYin;// 大电银
	private Integer xiaoDianYin;// 小电银
	private Integer xiaoZhiYin;// 小纸银
	private Integer dianShang;// 电商
	private Integer zhiShang;// 纸商
	private BigDecimal sumPrice;// 总金额(万元每天)
	private BigDecimal yszj;// 一手资金(万元)
	private String rest;//其他
	private String bspz;//不收票种
	private String overlayArea;// 业务覆盖区域
	private String advantageAcceptor;// 优势承兑人
	private Integer orgInfoId;
	private Integer ascriptionPerson;//归属人（servicemember表主键id）
	private Integer ascriptionState;//归属状态（0：销售，1：许可，2：转化，3：平台）
	private Date conversionTime;//转化归属状态的时间
	private Date permitTime;//许可归属状态的时间 
	private Date salesTime;//销售归属状态的时间 

	@Column(name="da_dian_yin")
	public Integer getDaDianYin() {
		return daDianYin;
	}

	public void setDaDianYin(Integer daDianYin) {
		this.daDianYin = daDianYin;
	}

	@Column(name="xiao_dian_yin")
	public Integer getXiaoDianYin() {
		return xiaoDianYin;
	}

	public void setXiaoDianYin(Integer xiaoDianYin) {
		this.xiaoDianYin = xiaoDianYin;
	}
	
	@Column(name="xiao_zhi_yin")
	public Integer getXiaoZhiYin() {
		return xiaoZhiYin;
	}

	public void setXiaoZhiYin(Integer xiaoZhiYin) {
		this.xiaoZhiYin = xiaoZhiYin;
	}

	@Column(name="dian_shang")
	public Integer getDianShang() {
		return dianShang;
	}

	public void setDianShang(Integer dianShang) {
		this.dianShang = dianShang;
	}

	@Column(name="zhi_shang")
	public Integer getZhiShang() {
		return zhiShang;
	}

	public void setZhiShang(Integer zhiShang) {
		this.zhiShang = zhiShang;
	}

	@Column(name="sum_price")
	public BigDecimal getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(BigDecimal sumPrice) {
		this.sumPrice = sumPrice;
	}

	public BigDecimal getYszj() {
		return yszj;
	}

	public void setYszj(BigDecimal yszj) {
		this.yszj = yszj;
	}

	@Column(name="overlay_area")
	public String getOverlayArea() {
		return overlayArea;
	}

	public void setOverlayArea(String overlayArea) {
		this.overlayArea = overlayArea;
	}

	@Column(name="advantage_acceptor")
	public String getAdvantageAcceptor() {
		return advantageAcceptor;
	}

	public void setAdvantageAcceptor(String advantageAcceptor) {
		this.advantageAcceptor = advantageAcceptor;
	}

	@Column(name="org_info_id")
	public Integer getOrgInfoId() {
		return orgInfoId;
	}

	public void setOrgInfoId(Integer orgInfoId) {
		this.orgInfoId = orgInfoId;
	}
	
	@Column(name="ascription_person")
	public Integer getAscriptionPerson() {
		return ascriptionPerson;
	}

	public void setAscriptionPerson(Integer ascriptionPerson) {
		this.ascriptionPerson = ascriptionPerson;
	}
	
	@Column(name="ascription_state")
	public Integer getAscriptionState() {
		return ascriptionState;
	}

	public void setAscriptionState(Integer ascriptionState) {
		this.ascriptionState = ascriptionState;
	}
	
	@Column(name="conversion_time")
	public Date getConversionTime() {
		return conversionTime;
	}

	public void setConversionTime(Date conversionTime) {
		this.conversionTime = conversionTime;
	}
	
	@Column(name="permit_time")
	public Date getPermitTime() {
		return permitTime;
	}

	public void setPermitTime(Date permitTime) {
		this.permitTime = permitTime;
	}
	
	public String getRest() {
		return rest;
	}

	public void setRest(String rest) {
		this.rest = rest;
	}

	public String getBspz() {
		return bspz;
	}

	public void setBspz(String bspz) {
		this.bspz = bspz;
	}

	@Column(name="sales_time")
	public Date getSalesTime() {
		return salesTime;
	}

	public void setSalesTime(Date salesTime) {
		this.salesTime = salesTime;
	}

}
