package com.ry.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "shipping_address")
public class ShippingAddress extends BaseModel {

	private static final long serialVersionUID = 1L;

	private Integer memberId; // 用户Id
	private String consignee; // 收件人
	private String tel; // 收件人电话
	private String address;//省市县地址
	private String detailAddress;// 详细地址
	private Integer state=1;//（0：默认选中，1：普通未选中）
	private String address_index;//选中地址的下标

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	@Column(name="detail_address")
	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getAddress_index() {
		return address_index;
	}

	public void setAddress_index(String address_index) {
		this.address_index = address_index;
	}

}
