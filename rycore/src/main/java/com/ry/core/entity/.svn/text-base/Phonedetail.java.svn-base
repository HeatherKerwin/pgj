package com.ry.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="phonedetail")
public class Phonedetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2772639344384122085L;

	private Integer id;
	private String banben;
	private String zhizhaoshang;
	private String deviceinfo;
	private String yunyingshang;
	private String shoujixinghao;
	private Integer memberid;
	private String qudao;
	private String fenbianlv;
	private Date createDate;
	private Date updateDate;
	
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="banben")
	public String getBanben() {
		return banben;
	}
	public void setBanben(String banben) {
		this.banben = banben;
	}
	
	@Column(name="zhizhaoshang")
	public String getZhizhaoshang() {
		return zhizhaoshang;
	}
	public void setZhizhaoshang(String zhizhaoshang) {
		this.zhizhaoshang = zhizhaoshang;
	}
	
	@Column(name="deviceinfo")
	public String getDeviceinfo() {
		return deviceinfo;
	}
	public void setDeviceinfo(String deviceinfo) {
		this.deviceinfo = deviceinfo;
	}
	
	@Column(name="yunyingshang")
	public String getYunyingshang() {
		return yunyingshang;
	}
	public void setYunyingshang(String yunyingshang) {
		this.yunyingshang = yunyingshang;
	}
	
	@Column(name="shoujixinghao")
	public String getShoujixinghao() {
		return shoujixinghao;
	}
	public void setShoujixinghao(String shoujixinghao) {
		this.shoujixinghao = shoujixinghao;
	}
	
	@Column(name="memberid")
	public Integer getMemberid() {
		return memberid;
	}
	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}
	
	@Column(name="qudao")
	public String getQudao() {
		return qudao;
	}
	public void setQudao(String qudao) {
		this.qudao = qudao;
	}
	
	@Column(name="fenbianlv")
	public String getFenbianlv() {
		return fenbianlv;
	}
	public void setFenbianlv(String fenbianlv) {
		this.fenbianlv = fenbianlv;
	}
	
	@Column(name="createDate")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(name="updateDate")
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
}
