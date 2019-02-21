package com.ry.core.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
@Entity(name="shibor")
public class Shibor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String day;
	private String shibor1;
	private String shibor2;
	private String shibor3;
	private String shibor4;
	private String shibor5;
	private String shibor6;
	private String shibor7;
	private String shibor8;
	private String updown1;
	private String updown2;
	private String updown3;
	private String updown4;
	private String updown5;
	private String updown6;
	private String updown7;
	private String updown8;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	@Column
	public String getShibor1() {
		return shibor1;
	}
	public void setShibor1(String shibor1) {
		this.shibor1 = shibor1;
	}
	@Column
	public String getShibor2() {
		return shibor2;
	}
	public void setShibor2(String shibor2) {
		this.shibor2 = shibor2;
	}
	@Column
	public String getShibor3() {
		return shibor3;
	}
	public void setShibor3(String shibor3) {
		this.shibor3 = shibor3;
	}
	@Column
	public String getShibor4() {
		return shibor4;
	}
	public void setShibor4(String shibor4) {
		this.shibor4 = shibor4;
	}
	@Column
	public String getShibor5() {
		return shibor5;
	}
	public void setShibor5(String shibor5) {
		this.shibor5 = shibor5;
	}
	@Column
	public String getShibor6() {
		return shibor6;
	}
	public void setShibor6(String shibor6) {
		this.shibor6 = shibor6;
	}
	@Column
	public String getShibor7() {
		return shibor7;
	}
	public void setShibor7(String shibor7) {
		this.shibor7 = shibor7;
	}
	@Column
	public String getShibor8() {
		return shibor8;
	}
	public void setShibor8(String shibor8) {
		this.shibor8 = shibor8;
	}
	@Column
	public String getUpdown1() {
		return updown1;
	}
	public void setUpdown1(String updown1) {
		this.updown1 = updown1;
	}
	@Column
	public String getUpdown2() {
		return updown2;
	}
	public void setUpdown2(String updown2) {
		this.updown2 = updown2;
	}
	@Column
	public String getUpdown3() {
		return updown3;
	}
	public void setUpdown3(String updown3) {
		this.updown3 = updown3;
	}
	@Column
	public String getUpdown4() {
		return updown4;
	}
	public void setUpdown4(String updown4) {
		this.updown4 = updown4;
	}
	@Column
	public String getUpdown5() {
		return updown5;
	}
	public void setUpdown5(String updown5) {
		this.updown5 = updown5;
	}
	@Column
	public String getUpdown6() {
		return updown6;
	}
	public void setUpdown6(String updown6) {
		this.updown6 = updown6;
	}
	@Column
	public String getUpdown7() {
		return updown7;
	}
	public void setUpdown7(String updown7) {
		this.updown7 = updown7;
	}
	@Column
	public String getUpdown8() {
		return updown8;
	}
	public void setUpdown8(String updown8) {
		this.updown8 = updown8;
	}
	
	
}
