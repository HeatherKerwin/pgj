package com.ry.core.form;

import com.ry.core.entity.Accountrecord;

/**
 * @author RY
 * @date 2016年1月12日
 * 票据查询form类
 */
public class AccountrecordForm extends Accountrecord{
	
	private static final long serialVersionUID = 1L;
	
	private String type;
	private String date;
	private String email;
	private Integer notice;
	private Integer shuxing;
	//开始时间[查询间隔]
	private String start;
	
	//结束时间[查询间隔]
	private String end;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getStart() {
		return start;
	}
	
	public void setStart(String start) {
		this.start = start;
	}
	
	public String getEnd() {
		return end;
	}
	
	public void setEnd(String end) {
		this.end = end;
	}
	
	public Integer getNotice() {
		return notice;
	}
	
	public void setNotice(Integer notice) {
		this.notice = notice;
	}
	
	public Integer getShuxing() {
		return shuxing;
	}
	
	public void setShuxing(Integer shuxing) {
		this.shuxing = shuxing;
	}
}