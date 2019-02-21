package com.ry.core.form.versioninfo;

import com.ry.util.datatable.BasePageRequestData;

public class VersionInfoRequest extends BasePageRequestData {

	private String startDate; // 起始时间
	private String endDate; // 结束时间

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
