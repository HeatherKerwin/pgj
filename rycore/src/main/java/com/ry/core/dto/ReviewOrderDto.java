/**
 * 
 */
package com.ry.core.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.ry.core.entity.DistributeOrder;

/**
 * 名称: orgListDto.java<br>
 * 描述: 企业机构认证列表反射实体<br>
 * 类型: JAVA<br>
 * 最近修改时间:2016年3月2日 下午7:01:11<br>
 * @since  2016年3月2日
 * @author li.xiaofei 
 */
public class ReviewOrderDto{
	
	private Integer id;
	private Integer dcrdId;//企业提交的  订单的主键
	private Integer orgId;//机构端，机构主键
	private Integer state;//状态  无效订单0、待确认1、验票中2、待付款4、已完成3、已付款5 待复核 6）
	private String orgNo;//订单号 例如：JG20160217144633789
	private Date createTime;//创建时间（派单时间）
	private Integer memberNo;
	private Long memTime;//订单创建时间
	private BigDecimal orderMoney;

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
	 * @return the dcrdId
	 */
	public Integer getDcrdId() {
		return dcrdId;
	}
	/**
	 * @param dcrdId the dcrdId to set
	 */
	public void setDcrdId(Integer dcrdId) {
		this.dcrdId = dcrdId;
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
	
	
}