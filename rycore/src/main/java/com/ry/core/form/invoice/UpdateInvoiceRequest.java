/**
 * 
 */
package com.ry.core.form.invoice;

import com.ry.util.datatable.BasePageRequestData;

/**
 * 名称: UpdateInvoiceRequest.java<br>
 * 描述: <br>
 * 类型: JAVA<br>
 * 最近修改时间:2016年3月8日 下午6:12:16<br>
 * @since  2016年3月8日
 * @author li.xiaofei 
 */
public class UpdateInvoiceRequest extends BasePageRequestData{

	private Integer id;
	private Integer expressWay;
	private String expressNo;
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
	 * @return the expressWay
	 */
	public Integer getExpressWay() {
		return expressWay;
	}
	/**
	 * @param expressWay the expressWay to set
	 */
	public void setExpressWay(Integer expressWay) {
		this.expressWay = expressWay;
	}
	/**
	 * @return the expressNo
	 */
	public String getExpressNo() {
		return expressNo;
	}
	/**
	 * @param expressNo the expressNo to set
	 */
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	
	
}
