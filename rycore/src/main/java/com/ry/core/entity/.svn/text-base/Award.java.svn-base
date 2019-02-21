package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 活动中奖纪录（转盘等）
 * @author WKX
 */
@Entity(name="award")
public class Award extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	
	private Integer hid;//活动主键
	
	private String goods;//奖品
	
	private Integer memberId;//用户主键
	
	private String memberName;//收件人
	
	private String company;//公司名称
	
	private String email;//电子邮件
	
	private String phone;//电话号码
	
	private String prov;//省
	
	private String city;//城市
	
	private String dist;//区县
	
	private String address;//收件地址
	
	private Integer genre;//类型（0实物、1话费、2红包、3线下（已派奖品））
	
	private Date createTime;//创建时间
	
	private String remarks;//备注（例如：线下注册后默认参与过活动，也保存一条抽奖记录，但是备注上线下不需要派奖）

	/**
	 * 活动主键（转盘活动）
	 * @author WKX
	 * @since 2016年2月24日 下午1:43:47
	 */
	public Integer getHid() {
		return hid;
	}

	public void setHid(Integer hid) {
		this.hid = hid;
	}

	public String getGoods() {
		return goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	@Column(length=20)
	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(length=50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(length=20)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 省
	 * @author WKX
	 * @since 2016年2月24日 下午5:37:03
	 */
	@Column(length=20)
	public String getProv() {
		return prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}

	/**
	 * 城市
	 * @author WKX
	 * @since 2016年2月24日 下午5:37:12
	 */
	@Column(length=20)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 区县
	 * @author WKX
	 * @since 2016年2月24日 下午5:37:21
	 */
	@Column(length=30)
	public String getDist() {
		return dist;
	}

	public void setDist(String dist) {
		this.dist = dist;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getGenre() {
		return genre;
	}

	public void setGenre(Integer genre) {
		this.genre = genre;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}