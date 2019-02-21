/**
 * 
 */
package com.ry.core.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 名称: orgListDto.java<br>
 * 描述: 企业机构认证列表反射实体<br>
 * 类型: JAVA<br>
 * 最近修改时间:2016年3月2日 下午7:01:11<br>
 * @since  2016年3月2日
 * @author li.xiaofei 
 */
public class OrderInvoiceDto{
	
	private Integer id;
	private Integer invoiceState;
	private Integer orgId;//机构端，机构主键
	private String orgNo;//订单号 例如：JG20160217144633789
	private Date orgTime;//派单时间
	private Integer orgState;//机构处理状态
	private Integer memState;//企业处理状态
	private BigDecimal orderMoney;//订单金额
	private Integer memberNo;//企业订单号
	private Long memTime;//企业订单创建时间
	private Date createTime;//创建时间（派单时间）
	
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
	 * @return the invoiceState
	 */
	public Integer getInvoiceState() {
		return invoiceState;
	}
	/**
	 * @param invoiceState the invoiceState to set
	 */
	public void setInvoiceState(Integer invoiceState) {
		this.invoiceState = invoiceState;
	}
	/**
	 * @return the orgId
	 */
	public Integer getOrgId() {
		return orgId;
	}
	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	/**
	 * @return the orgNo
	 */
	public String getOrgNo() {
		return orgNo;
	}
	/**
	 * @param orgNo the orgNo to set
	 */
	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}
	/**
	 * @return the orgTime
	 */
	public Date getOrgTime() {
		return orgTime;
	}
	/**
	 * @param orgTime the orgTime to set
	 */
	public void setOrgTime(Date orgTime) {
		this.orgTime = orgTime;
	}
	/**
	 * @return the orgState
	 */
	public Integer getOrgState() {
		return orgState;
	}
	/**
	 * @param orgState the orgState to set
	 */
	public void setOrgState(Integer orgState) {
		this.orgState = orgState;
	}
	/**
	 * @return the memState
	 */
	public Integer getMemState() {
		return memState;
	}
	/**
	 * @param memState the memState to set
	 */
	public void setMemState(Integer memState) {
		this.memState = memState;
	}
	/**
	 * @return the orderMoney
	 */
	public BigDecimal getOrderMoney() {
		return orderMoney;
	}
	/**
	 * @param orderMoney the orderMoney to set
	 */
	public void setOrderMoney(BigDecimal orderMoney) {
		this.orderMoney = orderMoney;
	}
	/**
	 * @return the memberNo
	 */
	public Integer getMemberNo() {
		return memberNo;
	}
	/**
	 * @param memberNo the memberNo to set
	 */
	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}
	/**
	 * @return the memTime
	 */
	public Long getMemTime() {
		return memTime;
	}
	/**
	 * @param memTime the memTime to set
	 */
	public void setMemTime(Long memTime) {
		this.memTime = memTime;
	}
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}