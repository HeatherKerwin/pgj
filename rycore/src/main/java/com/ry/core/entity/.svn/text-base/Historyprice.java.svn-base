package com.ry.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="historyprice")
public class Historyprice implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer type1;//承兑行：1国股、2城商、3外资、4农商、5农合、6农信、7村镇、8大商
	private Integer type2;//票据金额 :1:500以上 2:100-500 3:50-100  4:<50
	private Integer type4;//地域 1长三角2珠三角3华中4环渤海5西南
	private String price;//对应 APP2.1机构报价表Price 年（月）利率
	private String updown;//涨跌
	private String day;//哪一天的报价
	private String matrueprice;//足月票价
	
	private Integer type3;//@WKX 对应 APP2.1 买断带票 1买断 2带票 
	private Integer type5;//@WKX 对应 APP2.1 月份（1三个月、2六个月）APP2.1增加：3六个月以上
	private Integer type6;//@WKX 对应 APP2.1机构报价表Price（纸票1、电票2）
	
	private Integer type7;//@APP2.2  期限：半年期0、一年期1
	private Integer cityId;//@APP2.2 城市主键
	
	private String price1;//@WKX 对应 APP2.1机构报价表Price（参数）
	private String price2;//@WKX 对应 APP2.1机构报价表Price（每十万贴息）
	
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
	
	@Column(name="type4")
	public Integer getType4() {
		return type4;
	}
	public void setType4(Integer type4) {
		this.type4 = type4;
	}
	
	@Column(name="type7")
	public Integer getType7() {
		return type7;
	}
	public void setType7(Integer type7) {
		this.type7 = type7;
	}
	
	/**
	 * @APP2.2 城市主键
	 * @author WKX
	 * @since 2016年6月3日 下午1:55:23
	 */
	@Column(name="city_id")
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	@Column(name="price")
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	@Column(name="updown")
	public String getUpdown() {
		return updown;
	}
	public void setUpdown(String updown) {
		this.updown = updown;
	}
	
	@Column(name="day")
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	
	public Integer getType3() {
		return type3;
	}
	public void setType3(Integer type3) {
		this.type3 = type3;
	}
	
	public Integer getType5() {
		return type5;
	}
	public void setType5(Integer type5) {
		this.type5 = type5;
	}
	
	public Integer getType6() {
		return type6;
	}
	public void setType6(Integer type6) {
		this.type6 = type6;
	}
	
	public String getPrice1() {
		return price1;
	}
	public void setPrice1(String price1) {
		this.price1 = price1;
	}
	
	public String getPrice2() {
		return price2;
	}
	public void setPrice2(String price2) {
		this.price2 = price2;
	}
	/**
	 * 足月票价
	 * @author ZWD
	 * 2017年4月26日18:28:46
	 * @return
	 */
	@Column(name="matrueprice")
	public String getMatrueprice() {
		return matrueprice;
	}

	public void setMatrueprice(String matrueprice) {
		this.matrueprice = matrueprice;
	}
}