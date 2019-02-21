package com.ry.core.form;

import java.util.Date;

import com.ry.core.entity.News;
import com.ry.util.DateUtil;

public class NewsForm extends News{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7094937619476577856L;

	private Date beginDate;
	private Date endDate;
	private Integer timeSort;//排序时间
	
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
	
	public Integer getTimeSort() {
		return timeSort;
	}
	public void setTimeSort(Integer timeSort) {
		this.timeSort = timeSort;
	}
}
