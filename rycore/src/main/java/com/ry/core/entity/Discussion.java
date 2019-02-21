package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 座谈会（入场券）
 * @author WKX
 */
@Entity(name="discussion")
public class Discussion extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	
	private String company;//企业名称
	private String name;//联系人
	private String phone;//联系方式
	private String address;//所在地
	private String imgPath;//二维码路径
	
	private Integer state = 0;//入场券状态 [0待入场、1已入场、2无效]
	private String qudao;//渠道[SALE地推、KEFU客服、XIAOSHOU销售、DIANXIAO电销]
	
	private Date createTime;//创建时间
	private Date checkInTime;//签到时间

	/**
	 * 企业名称
	 * @author WKX
	 * @since 2016年10月16日 下午6:22:57
	 */
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * 联系人
	 * @author WKX
	 * @since 2016年10月16日 下午6:23:05
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 联系方式
	 * @author WKX
	 * @since 2016年10月16日 下午6:23:14
	 */
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 所在地
	 * @author WKX
	 * @since 2016年10月16日 下午6:23:21
	 */
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 二维码路径
	 * @author WKX
	 * @since 2016年10月16日 下午6:23:32
	 */
	@Column(name="img_path")
	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	/**
	 * 入场券状态 [0待入场、1已入场、2无效]
	 * @author WKX
	 * @since 2016年10月16日 下午3:49:46
	 */
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	/**
	 * 渠道
	 * @author WKX
	 * @since 2016年10月24日 下午3:34:19
	 */
	public String getQudao() {
		return qudao;
	}

	public void setQudao(String qudao) {
		this.qudao = qudao;
	}

	/**
	 * 创建时间
	 * @author WKX
	 * @since 2016年10月16日 下午3:50:24
	 */
	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 签到时间
	 * @author WKX
	 * @since 2016年10月16日 下午3:50:37
	 */
	@Column(name="check_in_time")
	public Date getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(Date checkInTime) {
		this.checkInTime = checkInTime;
	}
}