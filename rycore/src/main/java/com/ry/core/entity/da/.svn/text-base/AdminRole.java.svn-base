package com.ry.core.entity.da;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.ry.core.entity.BaseModel;


/**
 * 用户角色关联表
 * @author KHC
 */
@Entity(name="admin_role")
public class AdminRole extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer adminId;
	private Integer roleId;
	
	@Column(name="admin_id")
	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}


	@Column(name="role_id")
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
}
