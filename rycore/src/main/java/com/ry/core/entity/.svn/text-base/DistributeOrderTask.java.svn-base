package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.ry.core.entity.DiscountrecordTask.OperatorType;
import com.ry.core.entity.Systeminfo.ReadState;

/**
 * 派单记录流程表（机构端订单）
 * @author WKX
 */
@Entity(name="distribute_order_task")
public class DistributeOrderTask extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	
	private Integer dtboId;//派单记录（机构端订单）主键
	private Integer state;//状态（拒绝订单-2（机构端造成）、验票失败-1、无效订单0（企业端造成）、待确认1、验票中2、待付款4、已完成3、已付款5、待结单6.....）
	private String remarks;//备注
	private Integer operatorId;//操作的人是谁
	private OperatorType operatorType;//操作的人是什么表里的
	private Date createTime;//创建时间（操作日期）
	private ReadState readState = ReadState.UNREAD;//读取状态[UNREAD未读,READ已读]
	
	private Integer type = 0;//@APP2.3 WKX 类型：银票0、商票1、批量2（注：银票可能为null，且默认银票）
	
	/**
	 * 派单记录（机构端订单）主键
	 * @author WKX
	 * @since 2016年3月2日 下午7:52:31
	 */
	@Column(name="dtbo_id")
	public Integer getDtboId() {
		return dtboId;
	}

	public void setDtboId(Integer dtboId) {
		this.dtboId = dtboId;
	}

	/**
	 * 状态（无效订单0、待确认1、验票中2、待付款4、已完成3、已付款5）
	 * @author WKX
	 * @since 2016年3月2日 下午7:53:30
	 */
	@Column(length=1)
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * 备注
	 * @author WKX
	 * @since 2016年3月2日 下午7:53:47
	 */
	@Column(length=255)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * 操作的人是谁
	 * @author WKX
	 * @since 2016年3月2日 下午7:54:20
	 */
	@Column(name="operator_id")
	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}
	
	/**
	 * 操作员类型（操作的人是什么表里的）
	 * @author WKX
	 * @since 2016年3月14日 上午10:55:19
	 */
	@Column(name="operator_type")
	@Enumerated(EnumType.STRING)
	public OperatorType getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(OperatorType operatorType) {
		this.operatorType = operatorType;
	}

	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 读取状态[UNREAD未读,READ已读]
	 * @author WKX
	 * @since 2016年3月3日 上午9:02:32
	 */
	@Enumerated(EnumType.STRING)
	@Column(name="read_state",length=20)
	public ReadState getReadState() {
		return readState;
	}

	public void setReadState(ReadState readState) {
		this.readState = readState;
	}
	
	/**
	 * 类型：银票0、商票1、批量2
	 * @author WKX
	 * @since 2016年8月16日 上午9:32:50
	 */
	@Column(name = "type_")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}