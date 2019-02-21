package com.ry.core.form.integral;

import com.ry.util.datatable.BasePageRequestData;

public class OrdersRequest extends BasePageRequestData {

	private String ordersNo;	//订单编号
	private String expressNo;	//快递单号
	private Integer minSumIntegral;
	private Integer maxSumIntegral;
	private Integer state;
	private String startTime;
	private String endTime;
	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getOrdersNo() {
		return ordersNo;
	}

	public void setOrdersNo(String ordersNo) {
		this.ordersNo = ordersNo;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public Integer getMinSumIntegral() {
		return minSumIntegral;
	}

	public void setMinSumIntegral(Integer minSumIntegral) {
		this.minSumIntegral = minSumIntegral;
	}

	public Integer getMaxSumIntegral() {
		return maxSumIntegral;
	}

	public void setMaxSumIntegral(Integer maxSumIntegral) {
		this.maxSumIntegral = maxSumIntegral;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
