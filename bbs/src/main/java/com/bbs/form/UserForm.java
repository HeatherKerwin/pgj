package com.bbs.form;

import java.io.Serializable;

import com.bbs.model.User;
import com.bbs.model.PointLog.Cosname;

/**
 * User对象
 */
public class UserForm extends User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long uid;
	
	private String login_name;
	
	private String pass_word;
	
	//头像
	private String avatar;
	
	//电子邮箱
	private String email;
	
	//创建时间
	private Long create_time;
	
	//最后一次操作时间
	private Long update_time;
	
	//5:普通用户 2:管理员 1:系统管理员
	private Integer role_id;
	
	//0:待激活 1:正常 2：删除
	private Integer status;
	
	private String company;//企业名称（新添加）
	private String phone;//联系方式（新添加）
	private String wechat;//微信（新添加）
	private String qq;//qq（新添加）
	
	private Integer node_user_id;//节点
	
	private Long score;//汇总积分
	
	private Cosname cosname;//扮演的角色
	
	private String nick_name;//昵称
	
	public UserForm(){}
	
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	public String getLogin_name() {
		return login_name;
	}

	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	
	public String getPass_word() {
		return pass_word;
	}

	public void setPass_word(String pass_word) {
		this.pass_word = pass_word;
	}
	
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}
	
	public Long getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Long update_time) {
		this.update_time = update_time;
	}
	
	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Integer getNode_user_id() {
		return node_user_id;
	}

	public void setNode_user_id(Integer node_user_id) {
		this.node_user_id = node_user_id;
	}
	
	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}
	
	public Cosname getCosname() {
		return cosname;
	}

	public void setCosname(Cosname cosname) {
		this.cosname = cosname;
	}
	
	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", login_name=" + login_name + ", pass_word=" + pass_word + ", avatar=" + avatar
				+ ", email=" + email + ", create_time=" + create_time + ", update_time=" + update_time + ", role_id="
				+ role_id + ", status=" + status + "]";
	}
}