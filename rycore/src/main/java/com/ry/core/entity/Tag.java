package com.ry.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 字典表[优惠红包类型等]
 * @author WKX
 */
@Entity(name="tag")
public class Tag extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	
	//编号[大类:HONGBAO...;小类:TIEXIAN、BANNER、TUIGUANG...]
	private String code;
	
	private String name;
	
	private String remark;
	
	private Integer parentId;

	/**
	 * 编号
	 * @author WKX
	 * @since 2016年1月15日 上午11:01:26
	 */
	@Column(unique=true,length=20)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 名称
	 * @author WKX
	 * @since 2016年1月15日 上午11:01:40
	 */
	@Column(length=20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 备注
	 * @author WKX
	 * @since 2016年1月15日 上午11:01:49
	 */
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 父类主键
	 * @author WKX
	 * @since 2016年1月15日 上午11:01:56
	 */
	@Column(name="parentId")
	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
}