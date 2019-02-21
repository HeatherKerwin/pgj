package com.ry.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="lianhang")
public class Lianhang implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String yinhang;//银行类型
	private String lianhanghao;//联行号
	private String address;//地址
	private String phone;//电话
	private String provice;//省
	private String city;//市
	private String yinhangdesc;//具体银行名称
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="yinhang")
	public String getYinhang() {
		return yinhang;
	}
	public void setYinhang(String yinhang) {
		this.yinhang = yinhang;
	}
	
	@Column(name="lianhanghao")
	public String getLianhanghao() {
		return lianhanghao;
	}
	public void setLianhanghao(String lianhanghao) {
		this.lianhanghao = lianhanghao;
	}
	
	@Column(name="address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name="phone")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name="provice")
	public String getProvice() {
		return provice;
	}
	public void setProvice(String provice) {
		this.provice = provice;
	}
	
	@Column(name="city")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name="yinhangdesc")
	public String getYinhangdesc() {
		return yinhangdesc;
	}
	public void setYinhangdesc(String yinhangdesc) {
		this.yinhangdesc = yinhangdesc;
	}
}
