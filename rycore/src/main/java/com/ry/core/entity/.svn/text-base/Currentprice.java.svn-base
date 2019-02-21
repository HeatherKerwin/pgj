package com.ry.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * 当天价格（在APP2.1 已废弃）
 * @author RY
 */
@Entity(name="currentprice")
public class Currentprice implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer type1;//承兑行：1国股、2城商、3外资、4农商、5农合、6农信、7村镇
	private Integer type2;//票据金额 :1:500以上 2:100-500 3:50-100  4:<50
	private Integer type3;//买断带票 1买断 2带票 
	private String price;//对应 APP2.1机构报价表Price 年（月）利率
	private Integer type4;//地域 1长三角2珠三角3华中4环渤海5西南
	private Integer type5;//月份（1三个月、2六个月）APP2.1增加：3六个月以上
	
	private String price1;//@WKX 对应 APP2.1机构报价表Price（参数）
	private String price2;//@WKX 对应 APP2.1机构报价表Price（每十万贴息）
	private Integer type6;//@WKX 对应 APP2.1机构报价表Price（纸票1、电票2）
	
	private Integer salepriceid;
	private Integer retainlimit;
	private String saleprice;
	private Long endtime;
	
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
	
	@Column(name="type3")
	public Integer getType3() {
		return type3;
	}
	public void setType3(Integer type3) {
		this.type3 = type3;
	}
	
	@Column(name="type4")
	public Integer getType4() {
		return type4;
	}
	public void setType4(Integer type4) {
		this.type4 = type4;
	}
	
	@Column(name="price")
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	@Column(name="type5")
	public Integer getType5() {
		return type5;
	}
	public void setType5(Integer type5) {
		this.type5 = type5;
	}
	
	/**
	 * 对应 APP2.1机构报价表Price（参数）
	 * @author WKX
	 */
	public String getPrice1() {
		return price1;
	}
	
	public void setPrice1(String price1) {
		this.price1 = price1;
	}
	
	/**
	 * 对应 APP2.1机构报价表Price（没十万贴息）
	 * @author WKX
	 */
	public String getPrice2() {
		return price2;
	}
	
	public void setPrice2(String price2) {
		this.price2 = price2;
	}
	
	/**
	 * 对应 APP2.1机构报价表Price（纸票1、电票2）
	 * @author WKX
	 */
	public Integer getType6() {
		return type6;
	}
	
	public void setType6(Integer type6) {
		this.type6 = type6;
	}
	
	@Transient
	public Integer getRetainlimit() {
		return retainlimit;
	}
	public void setRetainlimit(Integer retainlimit) {
		this.retainlimit = retainlimit;
	}
	
	@Transient
	public String getSaleprice() {
		return saleprice;
	}
	public void setSaleprice(String saleprice) {
		this.saleprice = saleprice;
	}
	
	@Transient
	public Long getEndtime() {
		return endtime;
	}
	public void setEndtime(Long endtime) {
		this.endtime = endtime;
	}
	
	@Transient
	public Integer getSalepriceid() {
		return salepriceid;
	}
	public void setSalepriceid(Integer salepriceid) {
		this.salepriceid = salepriceid;
	}
}
