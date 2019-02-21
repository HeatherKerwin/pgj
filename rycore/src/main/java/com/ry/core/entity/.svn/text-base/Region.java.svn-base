package com.ry.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 省市区
 * @author WKX
 */
@Entity(name="region")
public class Region extends BaseModel{

	private static final long serialVersionUID = 1L;
	
	private String code;//编号
	private String name;//名称
	private Integer parentId;//父键
	private String type;//类型（COUNTRY国家、P省、C市、D区县）
	private String nameEn;//英文名（拼音）
	
	/**
	 * 编号
	 * @author WKX
	 */
	@Column(length=100)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 名称
	 * @author WKX
	 */
	@Column(length=100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 父键
	 * @author WKX
	 */
	@Column(name="parent_id")
	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/**
	 * 类型（COUNTRY国家、P省、C市、D区县）
	 * @author WKX
	 */
	@Column(name="type_",length=100)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 英文名（拼音）
	 * @author WKX
	 */
	@Column(name="name_en",length=100)
	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
}