package com.ry.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="saleprice")
public class Saleprice implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String price;
	private Date begintime;
	private Date endtime;
	private Integer alllimit;
	
	private String begintimeStr;
	private String endtimeStr;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	 
	@Column(name="price")
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	@Column(name="begintime")
	public Date getBegintime() {
		return begintime;
	}
	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}
	
	@Column(name="endtime")
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	
	@Column(name="alllimit")
	public Integer getAlllimit() {
		return alllimit;
	}
	public void setAlllimit(Integer alllimit) {
		this.alllimit = alllimit;
	}
	
	@Transient
	public String getBegintimeStr() {
		return begintimeStr;
	}
	public void setBegintimeStr(String begintimeStr) {
		this.begintimeStr = begintimeStr;
	}
	
	@Transient
	public String getEndtimeStr() {
		return endtimeStr;
	}
	public void setEndtimeStr(String endtimeStr) {
		this.endtimeStr = endtimeStr;
	}
}
