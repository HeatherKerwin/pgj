package com.ry.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 角色类
 * @author lvyanqin
 * @date 2016年3月7日
 */
@Entity(name="role")
public class Role {

	private Integer id;
	private String name;//角色名
	private Integer state;//状态，-1,1
	
	public Role() {
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

	/**
	 * 角色名
	 * @return
	 * @date 2016年3月7日
	 * @author lvyanqin
	 */
	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 状态，-1,1
	 * @return
	 * @date 2016年3月7日
	 * @author lvyanqin
	 */
	@Column(name="state")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}


	
}
