package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.ry.core.entity.Systeminfo.ReadState;

/**
 * 贴现流程表（记录每一步的操作历史）
 * @author WKX
 */
@Entity(name="discountrecord_task")
public class DiscountrecordTask extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	
	private Integer discountrecordId;//贴现主键
	
	private Integer operatorId;//操作员主键
	
	private OperatorType operatorType;//操作员表（ADMIN、MEMBER）
	
	private Date createTime;//创建时间
	
	private Operator operator;//操作
	
	private String operatorDesc;//操作描述
	
	private ReadState readState = ReadState.UNREAD;//读取状态[UNREAD未读,READ已读]
	
	private Integer type = 0;//@APP2.3 WKX 类型：银票0、商票1、批量2（注：银票可能为null，且默认银票）
	
	/**
	 * 贴现主表主键
	 * @author WKX
	 * @return
	 */
	public Integer getDiscountrecordId() {
		return discountrecordId;
	}

	public void setDiscountrecordId(Integer discountrecordId) {
		this.discountrecordId = discountrecordId;
	}

	/**
	 * 操作员主键
	 * @author WKX
	 * @return
	 */
	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	/**
	 * 操作员表（ADMIN、MEMBER）
	 * @author WKX
	 * @since 2016年3月14日 上午10:33:28
	 */
	@Enumerated(EnumType.STRING)
	public OperatorType getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(OperatorType operatorType) {
		this.operatorType = operatorType;
	}

	/**
	 * 操作时间
	 * @author WKX
	 */
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 操作
	 * @author WKX
	 */
	@Enumerated(EnumType.STRING)
	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	/**
	 * 操作描述
	 * @author WKX
	 */
	public String getOperatorDesc() {
		return operatorDesc;
	}

	public void setOperatorDesc(String operatorDesc) {
		this.operatorDesc = operatorDesc;
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

	/**
	 * 操作枚举
	 * 注：PAID已付款只在推送时使用，不参与企业端订单状态
	 * @author WKX
	 */
	public static enum Operator{
		FAILED("订单失败"),INVALID("无效订单"),UNCONFIRM("待确认"),CONFIRM("验票中"),FINISH("已完成"),UNTRANSACTION("待收款"),NOAUDIT("取消订单（待复核）"),PAID("已付款");
		public String name;
		private String value;
		Operator(String name){
			this.name = name;
			this.value = this.name();
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		
		/**
		 * 类型转换[前台传的值是int]
		 * 注：PAID已付款只在推送时使用，不参与企业端订单状态
		 * @author WKX
		 * @param falg
		 */
		public static Operator valueOf(int falg){
			switch (falg) {
			case -2:
				return NOAUDIT;
			case -1:
				return FAILED;
			case 0:
				return INVALID;
			case 1:
				return UNCONFIRM;
			case 2:
				return CONFIRM;
			case 3:
				return FINISH;
			case 4:
				return UNTRANSACTION;
			case 5:
				return PAID;
			}
			return null;
		}
	}
	
	/**
	 * 操作员类型
	 * ADMIN("客服"),MEMBER("用户")
	 * @author WKX
	 */
	public static enum OperatorType{
		ADMIN("客服"),MEMBER("用户"),SYSTEM("系统");
		public String name;
		private String value;
		OperatorType(String name){
			this.name = name;
			this.value = this.name();
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}
}