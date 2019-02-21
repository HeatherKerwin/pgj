package com.ry.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity(name="news")
public class News implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer type;
	private String title;
	private String content;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")  
	private Date publishtime;//新闻发布时间
	private String pic;//APP图片
	private String pic1;//官网图片
	private Integer topflag;
	private String publishtimeStr;
	private String titleShow;
	
	private String urlSource;// 地址来源
	private String articleSource;// 文章来源
	private String abstracts;// 摘要
	private Integer state;// 发布状态（已发布0、未发布1、已删除2）
	
	private String contentShow;//PC内容显示
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date createTime;//新闻创建时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date updateTime;//新闻修改时间
	
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
	
	@Column(name="pic1")
	public String getPic1() {
		return pic1;
	}
	
	public void setPic1(String pic1) {
		this.pic1 = pic1;
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
	
	@Column(name="topflag")
	public Integer getTopflag() {
		return topflag;
	}
	public void setTopflag(Integer topflag) {
		this.topflag = topflag;
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
	
	@Column(name="url_source",length=100)
	public String getUrlSource() {
		return urlSource;
	}
	
	public void setUrlSource(String urlSource) {
		this.urlSource = urlSource;
	}
	
	@Column(name="article_source",length=100)
	public String getArticleSource() {
		return articleSource;
	}
	
	public void setArticleSource(String articleSource) {
		this.articleSource = articleSource;
	}
	
	@Column(name="abstract")
	public String getAbstracts() {
		return abstracts;
	}
	
	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}
	
	@Column(length=1)
	public Integer getState() {
		return state;
	}
	
	public void setState(Integer state) {
		this.state = state;
	}
	
	@Transient
	public String getContentShow() {
		return contentShow;
	}
	
	public void setContentShow(String contentShow) {
		this.contentShow = contentShow;
	}
	
	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name="update_time")
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
