package com.ry.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 名称: 机构负责城市
 * @author RY
 */
@Entity(name="org_city")
public class OrgCity extends BaseModel{

	private static final long serialVersionUID = 1L;
	
	private Integer orgId; //认证表主键（主表主键）
	private Integer cityId;//城市 主键
	
	private String address;//@APP2.2 详细地址
	private Float longitude;//@APP2.2 经度
	private Float latitude;//@APP2.2 纬度
	private String other; //@App 2.3 门牌，楼层
	
	/**
	 * 认证表主键（主表主键）
	 */
	@Column(name="org_id")
	public Integer getOrgId() {
		return orgId;
	}
	
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
	/**
	 * 城市 主键
	 */
	@Column(name="city_id")
	public Integer getCityId() {
		return cityId;
	}
	
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	/**
	 * 详细地址
	 * @author WKX
	 * @since 2016年5月10日 上午9:25:02
	 */
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 经度
	 * @author WKX
	 * @since 2016年5月10日 上午9:24:58
	 */
	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	/**
	 * 纬度
	 * @author WKX
	 * @since 2016年5月10日 上午9:24:54
	 */
	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	/**
	 *  记录地址的楼层，门牌号
	 */
	@Column(name="other")
	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}
}