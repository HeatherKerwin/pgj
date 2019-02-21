package com.ry.core.form.org;

import com.ry.util.datatable.BasePageRequestData;

/**
 * 名称: ListRequest.java<br>
 * 描述: <br>
 * 类型: JAVA<br>
 * 最近修改时间:2016年3月2日 下午2:03:19<br>
 * @since  2016年3月2日
 * @author li.xiaofei 
 */
public class ReviewOrderRequest extends BasePageRequestData{
	
	private String startDate; //起始时间
	private String endDate;//截止时间
	private String state;  //验票失败-1、无效订单0、待确认1、验票中2、待付款4、已完成3、已付款5.....  
	
	private String no;//订单号
	private Integer list;//0.全部，1.近两周
	
	private String mobile;//手机号码
	
	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	
	/**
	 * @author KHC
	 * @since 2016年10月19日 下午6:58:11
	 */
	public Integer getList() {
		return list;
	}
	public void setList(Integer list) {
		this.list = list;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}