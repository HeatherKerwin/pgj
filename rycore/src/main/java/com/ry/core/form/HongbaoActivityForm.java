package com.ry.core.form;

import com.ry.core.entity.HongbaoActivity;

public class HongbaoActivityForm extends HongbaoActivity {

	private static final long serialVersionUID = 9152488005631626088L;
	
	private String startdateStr;
	
	private String enddateStr;
	
	private String createtimeStr;
	
	private Integer pageNo;
	
	private Integer pageSize;
	
	//红包类型编号[字典表]
	private String tagCode;

	public String getStartdateStr() {
		return startdateStr;
	}

	public void setStartdateStr(String startdateStr) {
		this.startdateStr = startdateStr;
	}

	public String getEnddateStr() {
		return enddateStr;
	}

	public void setEnddateStr(String enddateStr) {
		this.enddateStr = enddateStr;
	}

	public String getCreatetimeStr() {
		return createtimeStr;
	}

	public void setCreatetimeStr(String createtimeStr) {
		this.createtimeStr = createtimeStr;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 红包活动的类型编号[字典表]
	 * @author WKX
	 * @since 2016年1月15日 下午3:24:57
	 */
	public String getTagCode() {
		return tagCode;
	}

	public void setTagCode(String tagCode) {
		this.tagCode = tagCode;
	}	
}