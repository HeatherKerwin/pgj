package com.ry.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 角色权限中间表
 * @author lvyanqin
 * @date 2016年3月7日
 */
@Entity(name="rolePower")
public class RolePower {

	private Integer id;
	private Integer role_id;
	private Integer menu_id;//菜单id（权限id）
	
	public RolePower() {
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
	 * 角色id
	 * @return
	 * @date 2016年3月7日
	 * @author lvyanqin
	 */
	@Column(name="role_id")
	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	/**
	 * 菜单id（权限id）
	 * @return
	 * @date 2016年3月7日
	 * @author lvyanqin
	 */
	@Column(name="menu_id")
	public Integer getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(Integer menu_id) {
		this.menu_id = menu_id;
	}
	
}
