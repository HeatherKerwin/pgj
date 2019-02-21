package com.ry.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="daylimit")
public class Daylimit implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer limitprice;
	private String day;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="limitprice")
	public Integer getLimitprice() {
		return limitprice;
	}
	public void setLimitprice(Integer limitprice) {
		this.limitprice = limitprice;
	}

	@Column(name="day")
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
}
