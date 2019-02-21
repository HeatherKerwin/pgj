package com.ry.core.form;

import java.util.Date;

import com.ry.core.entity.News;
import com.ry.util.DateUtil;

public class PiaojujiangtangForm extends News{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7094937619476577856L;

	private Date beginDate;
	
	private Date endDate;
	
	private String publishtimeStr;
	
	private String titleShow;
	
	private String keyword;
	
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		if(endDate==null){
			this.endDate = endDate;
		}else{
			this.endDate = DateUtil.addDays(endDate,1);
		}
		
	}
	public String getPublishtimeStr() {
		return publishtimeStr;
	}
	public void setPublishtimeStr(String publishtimeStr) {
		this.publishtimeStr = publishtimeStr;
	}
	public String getTitleShow() {
		return titleShow;
	}
	public void setTitleShow(String titleShow) {
		this.titleShow = titleShow;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
