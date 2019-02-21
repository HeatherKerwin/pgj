/**
 * 
 */
package com.ry.core.dto;

import java.util.Date;

import com.ry.core.entity.Systeminfo.ReadState;

/**
 * 名称: orgListDto.java<br>
 * 描述: 企业机构认证列表反射实体<br>
 * 类型: JAVA<br>
 * 最近修改时间:2016年3月2日 下午7:01:11<br>
 * @since  2016年3月2日
 * @author li.xiaofei 
 */
public class OrderHistoryDto{
	
	private Integer dtboId;//派单记录（机构端订单）主键
	private Integer state;//状态（无效订单-1、待确认0、验票中1、代付款2、已完成3.....）
	private String remarks;//备注
	private Integer operatorId;//操作的人是谁
	private String operatorDesc;//描述
	private Date createTime;//创建时间（操作日期）
	private ReadState readState = ReadState.UNREAD;//读取状态[UNREAD未读,READ已读]
	private String userName;
	
	
	/**
	 * @return the operatorDesc
	 */
	public String getOperatorDesc() {
		return operatorDesc;
	}
	/**
	 * @param operatorDesc the operatorDesc to set
	 */
	public void setOperatorDesc(String operatorDesc) {
		this.operatorDesc = operatorDesc;
	}
	/**
	 * @return the dtboId
	 */
	public Integer getDtboId() {
		return dtboId;
	}
	/**
	 * @param dtboId the dtboId to set
	 */
	public void setDtboId(Integer dtboId) {
		this.dtboId = dtboId;
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
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * @return the operatorId
	 */
	public Integer getOperatorId() {
		return operatorId;
	}
	/**
	 * @param operatorId the operatorId to set
	 */
	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
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
	 * @return the readState
	 */
	public ReadState getReadState() {
		return readState;
	}
	/**
	 * @param readState the readState to set
	 */
	public void setReadState(ReadState readState) {
		this.readState = readState;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}