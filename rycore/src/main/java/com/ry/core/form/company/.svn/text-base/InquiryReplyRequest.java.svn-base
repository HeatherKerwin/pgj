/**
 * 
 */
package com.ry.core.form.company;

import com.ry.util.datatable.BasePageRequestData;

/**
 * 名称: OrderInvoiceRequest.java<br>
 * 描述: <br>
 * 类型: JAVA<br>
 * 最近修改时间:2016年3月5日 下午5:17:35<br>
 * @since  2016年3月5日
 * @author li.xiaofei 
 */
public class InquiryReplyRequest extends BasePageRequestData{
	
	private Integer id;//发票主键
	private Integer type;//外键的关联类型（区分是 查询查复 还是  其他订单） 1 ：机构  2：查询查复
	private String  queryNo; //查询查复编号   
	private Integer Iid;//唯一主键
	private Integer importState;//导出状态：未导出0、已导出1、已导入2
	
	private String orderNo;//订单号     票号
	private String startDate;//开始时间
	private String endDate;//结束时vi间
	private Integer orderState;//订单状态  新的CRM：后台处理状态 0待处理 1处理中 2取消查询 3已发送银行 4查询完成
	private Integer invState;//寄送状态
	private Integer checkState;//验票状态
	private Integer[] idStr;

	private Integer needInvoice;//是否需要发票（0.是，1.否）
	
	
	private String minMoney;//最小金额
	private String maxMoney;//最大金额
	private String phone;//手机号
	private Integer payState;//支付状态 待付款0、支付成功1、已退款2
	private Integer visitState;//回访状态 待付款0、支付成功1、已退款2
	private Integer visit;//0 待回访 1全部查询查复（后台crm系统用于列表查询）
	
	private String begremarksTime;//开始回访时间
	private String endremarksTime;//结束回访时间
	private String location;//城市
	
	public Integer getVisit() {
		return visit;
	}

	public void setVisit(Integer visit) {
		this.visit = visit;
	}

	public Integer getVisitState() {
		return visitState;
	}

	public void setVisitState(Integer visitState) {
		this.visitState = visitState;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getPayState() {
		return payState;
	}

	public void setPayState(Integer payState) {
		this.payState = payState;
	}

	public String getMinMoney() {
		return minMoney;
	}

	public void setMinMoney(String minMoney) {
		this.minMoney = minMoney;
	}

	public String getMaxMoney() {
		return maxMoney;
	}

	public void setMaxMoney(String maxMoney) {
		this.maxMoney = maxMoney;
	}

	/**
	 * @return the idStr
	 */
	public Integer[] getIdStr() {
		return idStr;
	}

	/**
	 * @param idStr the idStr to set
	 */
	public void setIdStr(Integer[] idStr) {
		this.idStr = idStr;
	}

	/**
	 * @return the iid
	 */
	public Integer getIid() {
		return Iid;
	}

	/**
	 * @param iid the iid to set
	 */
	public void setIid(Integer iid) {
		Iid = iid;
	}

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
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return the queryNo
	 */
	public String getQueryNo() {
		return queryNo;
	}

	/**
	 * @param queryNo the queryNo to set
	 */
	public void setQueryNo(String queryNo) {
		this.queryNo = queryNo;
	}

	/**
	 * @return the orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * @param orderNo the orderNo to set
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

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
	 * @return the orderState
	 */
	public Integer getOrderState() {
		return orderState;
	}

	/**
	 * @param orderState the orderState to set
	 */
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}

	/**
	 * @return the invState
	 */
	public Integer getInvState() {
		return invState;
	}

	/**
	 * @param invState the invState to set
	 */
	public void setInvState(Integer invState) {
		this.invState = invState;
	}

	/**
	 * @return the checkState
	 */
	public Integer getCheckState() {
		return checkState;
	}

	/**
	 * @param checkState the checkState to set
	 */
	public void setCheckState(Integer checkState) {
		this.checkState = checkState;
	}

	/**
	 * 导出状态：未导出0、已导出1、已导入2
	 * @author WKX
	 * @since 2016年4月12日 下午5:40:55
	 */
	public Integer getImportState() {
		return importState;
	}

	public void setImportState(Integer importState) {
		this.importState = importState;
	}

	/**
	 * 是否需要发票（0.是，1.否）
	 * @author KHC
	 * @since 2016年9月11日 下午3:43:00
	 */
	public Integer getNeedInvoice() {
		return needInvoice;
	}

	public void setNeedInvoice(Integer needInvoice) {
		this.needInvoice = needInvoice;
	}

	public String getBegremarksTime() {
		return begremarksTime;
	}

	public void setBegremarksTime(String begremarksTime) {
		this.begremarksTime = begremarksTime;
	}

	public String getEndremarksTime() {
		return endremarksTime;
	}

	public void setEndremarksTime(String endremarksTime) {
		this.endremarksTime = endremarksTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
}