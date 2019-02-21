package com.ry.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity(name="piaojujiangtang")
public class Piaojujiangtang implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer type;
	private String title;
	private String content;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm") 
	private Date publishtime;
	private String publishtimeStr;
	private String titleShow;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="type")
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	@Column(name="title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name="content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name="publishtime")
	public Date getPublishtime() {
		return publishtime;
	}
	public void setPublishtime(Date publishtime) {
		this.publishtime = publishtime;
	}
	

	@Transient
	public String getPublishtimeStr() {
		return publishtimeStr;
	}
	public void setPublishtimeStr(String publishtimeStr) {
		this.publishtimeStr = publishtimeStr;
	}
	
	@Transient
	public String getTitleShow() {
		return titleShow;
	}
	public void setTitleShow(String titleShow) {
		this.titleShow = titleShow;
	}
	
}
