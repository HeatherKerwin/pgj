package com.ry.core.form;

import java.util.Date;

import com.ry.core.entity.Discountrecord;

public class RefundExamineForm extends Discountrecord{

	private static final long serialVersionUID = 1L;
	
	private String userName;//用户名（机构、企业）
	
	private Date operatorTime;//操作时间
	
	/**
	 * 用户名（机构、企业）
	 * @author WKX
	 * @since 2016年6月2日 下午1:57:08
	 */
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * 操作时间（机构、企业）
	 * @author WKX
	 */
	public Date getOperatorTime() {
		return operatorTime;
	}

	public void setOperatorTime(Date operatorTime) {
		this.operatorTime = operatorTime;
	}
}