package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 公测码
 * @author WKX
 */
@Entity(name="beat_code")
public class BeatCode extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	
	private String no;//公测码
	private String phone;//手机号（用户申请认证关联的member）
	private Date createTime;
	private Integer state;//绑定状态（使用状态：已使用0、未使用1）
	
	/**
	 * 公测码
	 * @author WKX
	 * @since 2016年3月3日 上午10:12:16
	 */
	@Column(length=20)
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	/**
	 * 手机号（用户申请认证关联的member）
	 * @author WKX
	 * @since 2016年3月3日 上午10:12:39
	 */
	@Column(length=20)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 绑定状态（使用状态：已使用0、未使用1）
	 * @author WKX
	 * @since 2016年3月3日 上午10:13:10
	 */
	@Column(length=1)
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}