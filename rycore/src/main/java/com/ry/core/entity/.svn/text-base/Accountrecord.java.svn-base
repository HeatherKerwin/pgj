package com.ry.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity(name="accountrecord")
public class Accountrecord implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer memberid;
	private Integer type1;//承兑行：1国股、2城商、3外资、4农商、5农合、6农信、7村镇
	
	private Float allprice;
	private Double yuelilv;//月利率
	private Double nianlilv;//年利率
	private Date tiexiandate;//贴现日期
	private Date daoqidate;//到期日期
	private Integer tiaozheng;//调整
	private Integer jixi;//计息
	private Double tiexianlixi;//贴现利息
	private Double tiexianjine;//贴现金额
	private String accountdesc;//备注
	private String price;//价格
	
	private Date publishtime;//发布时间
	
	private String tiexiandateStr;
	private String tiexiandateweek;
	private String daoqidateStr;
	private String tiexianType;
	private String piaojushuxing;//票据属性（纸票电票）
	
	
	private String istiexian;//是否贴现（1为已贴现，0为未贴现）
	
	private String statue;//记录状态（状态 0：删除  1：正常）
	private Integer discountrecordId;//discountrecord外建
	
	private String orderStatue;//手动订单状态,系统的状态是通过联表查询
	private Date createTime;//创建时间
	
	private Integer acceptTime;//@APP2.2 承兑期限（0半年期、1一年期）
	private Integer belong;//@WKX APP2.1 所属人（0企业member、1机构org）
	
	private Double ten;//每十万扣
	private String source;//来源
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="memberid")
	public Integer getMemberid() {
		return memberid;
	}
	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}
	
	@Column(name="type1")
	public Integer getType1() {
		return type1;
	}
	public void setType1(Integer type1) {
		this.type1 = type1;
	}
	
	@Column(name="allprice")
	public Float getAllprice() {
		return allprice;
	}
	public void setAllprice(Float allprice) {
		this.allprice = allprice;
	}
	
	@Column(name="yuelilv")
	public Double getYuelilv() {
		return yuelilv;
	}
	public void setYuelilv(Double yuelilv) {
		this.yuelilv = yuelilv;
	}
	
	@Column(name="nianlilv")
	public Double getNianlilv() {
		return nianlilv;
	}
	public void setNianlilv(Double nianlilv) {
		this.nianlilv = nianlilv;
	}
	
	@Column(name="tiexiandate")
	public Date getTiexiandate() {
		return tiexiandate;
	}
	public void setTiexiandate(Date tiexiandate) {
		this.tiexiandate = tiexiandate;
	}
	
	@Column(name="daoqidate")
	public Date getDaoqidate() {
		return daoqidate;
	}
	public void setDaoqidate(Date daoqidate) {
		this.daoqidate = daoqidate;
	}
	
	@Column(name="tiaozheng")
	public Integer getTiaozheng() {
		return tiaozheng;
	}
	public void setTiaozheng(Integer tiaozheng) {
		this.tiaozheng = tiaozheng;
	}
	
	@Column(name="jixi")
	public Integer getJixi() {
		return jixi;
	}
	public void setJixi(Integer jixi) {
		this.jixi = jixi;
	}
	
	@Column(name="tiexianlixi")
	public Double getTiexianlixi() {
		return tiexianlixi;
	}
	public void setTiexianlixi(Double tiexianlixi) {
		this.tiexianlixi = tiexianlixi;
	}
	
	@Column(name="tiexianjine")
	public Double getTiexianjine() {
		return tiexianjine;
	}
	public void setTiexianjine(Double tiexianjine) {
		this.tiexianjine = tiexianjine;
	}
	
	@Column(name="accountdesc")
	public String getAccountdesc() {
		return accountdesc;
	}
	public void setAccountdesc(String accountdesc) {
		this.accountdesc = accountdesc;
	}
	
	@Column(name="price")
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	@Column(name="publishtime")
	public Date getPublishtime() {
		return publishtime;
	}
	public void setPublishtime(Date publishtime) {
		this.publishtime = publishtime;
	}
	@Transient
	public String getTiexiandateStr() {
		return tiexiandateStr;
	}
	public void setTiexiandateStr(String tiexiandateStr) {
		this.tiexiandateStr = tiexiandateStr;
	}
	
	@Transient
	public String getTiexiandateweek() {
		return tiexiandateweek;
	}
	public void setTiexiandateweek(String tiexiandateweek) {
		this.tiexiandateweek = tiexiandateweek;
	}
	
	@Transient
	public String getDaoqidateStr() {
		return daoqidateStr;
	}
	public void setDaoqidateStr(String daoqidateStr) {
		this.daoqidateStr = daoqidateStr;
	}
	@Column(name="istiexian")
	public String getIsTiexian() {
		return istiexian;
	}
	public void setIsTiexian(String isTiexian) {
		this.istiexian = isTiexian;
	}
	@Column(name="tiexianType")
	public String getTiexianType() {
		return tiexianType;
	}
	
	public void setTiexianType(String tiexianType) {
		this.tiexianType = tiexianType;
	}
	@Column(name="piaojushuxing")
	public String getPiaojushuxing() {
		return piaojushuxing;
	}
	public void setPiaojushuxing(String piaojushuxing) {
		this.piaojushuxing = piaojushuxing;
	}
	@Column(name="statue")
	public String getStatue() {
		return statue;
	}
	public void setStatue(String statue) {
		this.statue = statue;
	}
	@Column(name="discountrecordId")
	public Integer getDiscountrecordId() {
		return discountrecordId;
	}
	public void setDiscountrecordId(Integer discountrecordId) {
		this.discountrecordId = discountrecordId;
	}
	
	@Column(name="orderStatue")
	public String getOrderStatue() {
		return orderStatue;
	}
	
	public void setOrderStatue(String orderStatue) {
		this.orderStatue = orderStatue;
	}
	@Column(name="createTime")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * 承兑期限（0半年期、1一年期）
	 * @author WKX
	 * @since 2016年6月12日 上午10:40:10
	 */
	@Column(name="accept_time")
	public Integer getAcceptTime() {
		return acceptTime;
	}
	
	public void setAcceptTime(Integer acceptTime) {
		this.acceptTime = acceptTime;
	}
	
	/**
	 * 所属人（0企业member、1机构org）
	 * @author WKX
	 * @since 2016年4月1日 下午6:09:25
	 */
	public Integer getBelong() {
		return belong;
	}
	public void setBelong(Integer belong) {
		this.belong = belong;
	}
	
	/**
	 * 每十万扣利息
	 * @author KHC
	 * @since 2016年10月31日 上午10:49:57
	 */
	@Column(name="ten")
	public Double getTen() {
		return ten;
	}
	public void setTen(Double ten) {
		this.ten = ten;
	}
	
	/**
	 * 来源
	 * @author KHC
	 * @since 2016年10月31日 上午10:50:25
	 */
	@Column(name="source")
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
}