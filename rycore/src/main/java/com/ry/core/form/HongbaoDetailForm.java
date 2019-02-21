package com.ry.core.form;


import com.ry.core.entity.HongbaoDetail;

public class HongbaoDetailForm extends HongbaoDetail {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1285494855557282835L;

	public String enddate;
	
	public Integer tianshu;
	
	public Long limitprice;

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public Integer getTianshu() {
		return tianshu;
	}

	public void setTianshu(Integer tianshu) {
		this.tianshu = tianshu;
	}

	public Long getLimitprice() {
		return limitprice;
	}

	public void setLimitprice(Long limitprice) {
		this.limitprice = limitprice;
	}		
	
}
