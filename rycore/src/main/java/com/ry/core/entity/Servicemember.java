package com.ry.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.ry.core.Enum.ServicememberEnum;


@Entity(name="servicemember")
public class Servicemember implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String servicemember;
	private String servicenumber;
	private Integer dayCount;
	private Integer weekCount;
	private Integer monthCount;
	private Integer allCount;
	private Integer memberCount;
	private Integer newMemberByMonth;
	private Integer flag;
	
	private ServicememberEnum servicememberEnum;//前后端客户
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="servicemember")
	public String getServicemember() {
		return servicemember;
	}
	public void setServicemember(String servicemember) {
		this.servicemember = servicemember;
	}
	
	@Column(name="servicenumber")
	public String getServicenumber() {
		return servicenumber;
	}
	public void setServicenumber(String servicenumber) {
		this.servicenumber = servicenumber;
	}
	
	@Column(name="flag")
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	@Transient
	public Integer getDayCount() {
		return dayCount;
	}
	public void setDayCount(Integer daycount) {
		this.dayCount = daycount;
	}
	@Transient
	public Integer getMonthCount() {
		return monthCount;
	}
	public void setMonthCount(Integer monthcount) {
		this.monthCount = monthcount;
	}
	@Transient
	public Integer getAllCount() {
		return allCount;
	}
	public void setAllCount(Integer allcount) {
		this.allCount = allcount;
	}
	@Transient
	public Integer getMemberCount() {
		return memberCount;
	}
	public void setMemberCount(Integer membercount) {
		this.memberCount = membercount;
	}
	@Transient
	public Integer getNewMemberByMonth() {
		return newMemberByMonth;
	}
	public void setNewMemberByMonth(Integer newMemberByMonth) {
		this.newMemberByMonth = newMemberByMonth;
	}
	@Transient
	public Integer getWeekCount() {
		return weekCount;
	}
	public void setWeekCount(Integer weekCount) {
		this.weekCount = weekCount;
	}

	/**
	 * 用户归属（FRONT(0,"前端客服"),AFTER(1,"后端客服")）
	 * @author MH
	 */
	@Column(name="service_customer")
	@Enumerated(EnumType.STRING)
	public ServicememberEnum getServicememberEnum() {
		return servicememberEnum;
	}
	public void setServicememberEnum(ServicememberEnum servicememberEnum) {
		this.servicememberEnum = servicememberEnum;
	}
	
}
