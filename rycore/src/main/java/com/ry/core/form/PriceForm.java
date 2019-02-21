package com.ry.core.form;

/**
 * 报价的form类
 * @author MH
 */
public class PriceForm {

	private Integer orgId;
	private String startDate; 
	private String endDate;
	
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
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
