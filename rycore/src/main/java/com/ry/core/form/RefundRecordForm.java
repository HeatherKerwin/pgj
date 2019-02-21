package com.ry.core.form;

import com.ry.core.entity.RefundRecord;

public class RefundRecordForm extends RefundRecord{

	private static final long serialVersionUID = 1L;
	
	private String userName;//用户名（机构、企业）
	
	private String user_time;//操作日期
	
	/**
	 * 用户名（机构、企业）
	 * @author WKX
	 * @since 2016年6月2日 下午1:57:08
	 */
	public String getUserName() {
		return userName;
	}

	public String getUser_time() {
		return user_time;
	}

	/**
	 * 操作时间
	 * @author MH
	 * @param user_time
	 */
	public void setUser_time(String user_time) {
		this.user_time = user_time;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}