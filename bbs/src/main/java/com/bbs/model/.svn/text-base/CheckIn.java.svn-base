package com.bbs.model;

import java.io.Serializable;

import com.blade.jdbc.annotation.Table;

/**
 * 签到表
 */
@Table(value = "t_checkin", PK = "id")
public class CheckIn implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	//签到人
	private Long uid;
	
	//签到日期
	private String day;
	
	//签到时间
	private Long create_time;
	
	public CheckIn(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}
}