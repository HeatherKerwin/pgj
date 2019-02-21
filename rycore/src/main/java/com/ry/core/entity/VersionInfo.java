package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.format.annotation.DateTimeFormat;
/**
 * 版本信息
 * @author ZWD
 * 2017年4月14日15:02:03
 */
@Entity(name = "versionInfo")
public class VersionInfo extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String size;
	private String version;
	private String xitong;
	private Date shijian;
	private String content;
	private String jieshao;
	private String banner1;
	private String banner2;
	private String state;


	@Column(name = "size")
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Column(name = "version")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getXitong() {
		return xitong;
	}

	@Column(name = "xitong")
	public void setXitong(String xitong) {
		this.xitong = xitong;
	}

	public Date getShijian() {
		return shijian;
	}

	@Column(name = "shijian")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setShijian(Date shijian) {
		this.shijian = shijian;
	}

	public String getContent() {
		return content;
	}

	@Column(name = "content")
	public void setContent(String content) {
		this.content = content;
	}

	public String getJieshao() {
		return jieshao;
	}

	@Column(name = "jieshao")
	public void setJieshao(String jieshao) {
		this.jieshao = jieshao;
	}
	
	public String getBanner1() {
		return banner1;
	}

	@Column(name = "banner1")
	public void setBanner1(String banner1) {
		this.banner1 = banner1;
	}

	public String getBanner2() {
		return banner2;
	}

	@Column(name = "banner2")
	public void setBanner2(String banner2) {
		this.banner2 = banner2;
	}

	public String getState() {
		return state;
	}
	
	@Column(name = "state")
	public void setState(String state) {
		this.state = state;
	}
	
}
