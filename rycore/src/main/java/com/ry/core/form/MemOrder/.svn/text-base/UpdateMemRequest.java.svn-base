package com.ry.core.form.MemOrder;

import com.ry.core.entity.DiscountrecordTask.OperatorType;
import com.ry.util.datatable.BasePageRequestData;

/**
 * 名称: ListRequest.java<br>
 * 描述: <br>
 * 类型: JAVA<br>
 * 最近修改时间:2016年3月2日 下午2:03:19<br>
 * @since  2016年3月2日
 * @author li.xiaofei 
 */
public class UpdateMemRequest extends BasePageRequestData{
	
	private Integer id;
	
	private Integer memId;
	
	private Integer type;//2派单  0：取消
	
	private Integer orgId;//派单机构ID
	
	private String des;//描述说明
	
	private Integer operatorId;//操作人主键
	
	private OperatorType operatorType;//操作人类型
	
	private Integer [] orgIds;//多家机构
	
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 操作人主键
	 * @author WKX
	 * @since 2016年3月14日 上午10:42:31
	 */
	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}
	
	/**
	 * 描述（说明）
	 * @author WKX
	 * @since 2016年3月14日 下午1:24:17
	 */
	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	/**
	 * 操作员类型（后台用户ADMIN，普通用户MEMBER）
	 * @author WKX
	 * @since 2016年3月14日 上午10:50:46
	 */
	public OperatorType getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(OperatorType operatorType) {
		this.operatorType = operatorType;
	}

	/**
	 * 多家机构
	 * @author KHC
	 * @since 2016年9月11日 上午11:04:36
	 */
	public Integer[] getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(Integer[] orgIds) {
		this.orgIds = orgIds;
	}
}