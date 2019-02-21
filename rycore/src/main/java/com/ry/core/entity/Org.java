package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 名称: Org.java<br>
 * 描述: <br>
 * 类型: JAVA<br>
 * 最近修改时间:2016年3月2日 下午5:14:51<br>
 * @since  2016年3月2日
 * @author li.xiaofei 
 */
@Entity(name="org")
public class Org extends BaseModel{

	private static final long serialVersionUID = 1L;
	
	private Integer memberId; //用户主键
	private Integer type;//角色：企业0、机构1
	private Date createTime;
	
	private Integer state = 0;//@APP2.2 认证状态（未认证0、审核中1、已认证2、未通过3）
	
	public Org(){
		super();
	}
	
	public Org(Integer id){
		this.id = id;
	}
	
	/**
	 * @return the memberId
	 * 用户主键
	 */
	@Column(name="member_id")
	public Integer getMemberId() {
		return memberId;
	}
	/**
	 * @param memberId the memberId to set
	 * 用户主键
	 */
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	
	/**
	 * 角色：企业0、机构1
	 */
	@Column(name="type_")
	public Integer getType() {
		return type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	/**
	 * @return the createTime
	 */
	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}
	
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(length=8)
	public Integer getState() {
		return state;
	}
	
	public void setState(Integer state) {
		this.state = state;
	}
}