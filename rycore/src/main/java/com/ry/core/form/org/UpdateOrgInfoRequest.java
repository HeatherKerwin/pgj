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
public class UpdateOrgInfoRequest extends BasePageRequestData{
	
	private Integer id;
	private Integer state;
	private String reason;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the state
	 */
	public Integer getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}
	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	
}