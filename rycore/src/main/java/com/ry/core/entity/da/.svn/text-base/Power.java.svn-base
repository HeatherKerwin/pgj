package com.ry.core.entity.da;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="power")
public class Power implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String powerName;//目录名
	private Integer powerValue;//目录值
	private Integer statue;//状态，0已弃用，1在用
	private Integer parentId;//父类id
	
	public Power() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="powerName")
	public String getPowerName() {
		return powerName;
	}

	public void setPowerName(String powerName) {
		this.powerName = powerName;
	}

	@Column(name="powerValue")
	public Integer getPowerValue() {
		return powerValue;
	}

	public void setPowerValue(Integer powerValue) {
		this.powerValue = powerValue;
	}

	@Column(name="statue")
	public Integer getStatue() {
		return statue;
	}

	public void setStatue(Integer statue) {
		this.statue = statue;
	}

	@Column(name="parentId")
	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	
	
	
	
}
