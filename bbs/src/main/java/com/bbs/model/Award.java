package com.bbs.model;

import java.io.Serializable;
import java.util.Date;

import com.blade.jdbc.annotation.Table;

/**
 * 中奖纪录（含发货地址）
 */
@Table(value = "t_award", PK = "id")
public class Award implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long uid;//用户主键
	private Long tid;//奖券主键
	
	private String name;//收件人
	private String phone;//电话号码
	private Integer genre;//类型（0实物、1话费）
	private String goods;//奖品
	
	private String prov;//省
	private String city;//城市
	private String dist;//区县
	private String address;//收件地址
	private Date create_time;//创建时间
	
	private String remarks;//备注
	private Integer state = 0;//状态（0未处理、1已处理、2无效）
	
	public Award(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProv() {
		return prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

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

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Integer getGenre() {
		return genre;
	}

	public void setGenre(Integer genre) {
		this.genre = genre;
	}

	public String getGoods() {
		return goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}