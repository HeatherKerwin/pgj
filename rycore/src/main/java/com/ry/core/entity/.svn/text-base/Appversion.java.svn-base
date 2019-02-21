package com.ry.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="appversion")
public class Appversion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String iosversion;//ios版本
	private String androidversion;//安卓版本
	private String iosdesc;//ios描述
	private String androiddesc;//安卓描述
	private Integer iosflag;//ios标记
	private Integer androidflag;//安卓标记
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="iosversion")
	public String getIosversion() {
		return iosversion;
	}
	public void setIosversion(String iosversion) {
		this.iosversion = iosversion;
	}
	
	@Column(name="androidversion")
	public String getAndroidversion() {
		return androidversion;
	}
	public void setAndroidversion(String androidversion) {
		this.androidversion = androidversion;
	}
	
	@Column(name="iosdesc")
	public String getIosdesc() {
		return iosdesc;
	}
	public void setIosdesc(String iosdesc) {
		this.iosdesc = iosdesc;
	}
	
	@Column(name="androiddesc")
	public String getAndroiddesc() {
		return androiddesc;
	}
	public void setAndroiddesc(String androiddesc) {
		this.androiddesc = androiddesc;
	}
	
	@Column(name="iosflag")
	public Integer getIosflag() {
		return iosflag;
	}
	public void setIosflag(Integer iosflag) {
		this.iosflag = iosflag;
	}
	
	@Column(name="androidflag")
	public Integer getAndroidflag() {
		return androidflag;
	}
	public void setAndroidflag(Integer androidflag) {
		this.androidflag = androidflag;
	}
	
	
}
