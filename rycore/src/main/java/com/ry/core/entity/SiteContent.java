package com.ry.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity(name="site_content")
public class SiteContent implements Serializable {//首页的第一条可维护的新闻
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String title;//标题
	private String content;//内容
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private Date publishtime;//发布时间
	private String pic;//官网图片
	private String abstracts;// 摘要
	private String url;// 地址
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	
	@Column(name="pic")
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	
	@Column(name="abstract")
	public String getAbstracts() {
		return abstracts;
	}
	
	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}
	
	@Column(name="url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
