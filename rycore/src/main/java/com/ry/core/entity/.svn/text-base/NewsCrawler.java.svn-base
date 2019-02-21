package com.ry.core.entity;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 消息爬虫
 * @author ZY
 */
@Entity(name = "news_crawler")
public class NewsCrawler extends BaseModel {

	private static final long serialVersionUID = 1L;

	private Integer type;// 票据新闻1、金融动态2、管家动态3、媒体报道4
	private String title;// 主题
	private String content;// 内容
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date createTime;// 创建时间
	private String pic;// APP图片
	private String pic1;//官网图片
	private Integer topFlag;//
	private Integer state;// 发布状态（已发布0、未发布1）
	private String urlSource;// 地址来源
	private String articleSource;// 文章来源
	private String abstractCrawler;// 摘要

	private String publishtimeStr;
	private String titleShow;
	
	@Column(name = "type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(length = 100)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name="pic")
	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	@Column(name="pic1")
	public String getPic1() {
		return pic1;
	}
	
	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}
	
	@Column(name = "top_flag")
	public Integer getTopFlag() {
		return topFlag;
	}

	public void setTopFlag(Integer topFlag) {
		this.topFlag = topFlag;
	}

	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "url_source", length = 100)
	public String getUrlSource() {
		return urlSource;
	}

	public void setUrlSource(String urlSource) {
		this.urlSource = urlSource;
	}

	@Column(name = "article_source", length = 100)
	public String getArticleSource() {
		return articleSource;
	}

	public void setArticleSource(String articleSource) {
		this.articleSource = articleSource;
	}

	@Column(name = "adstract")
	public String getAbstractCrawler() {
		return abstractCrawler;
	}

	public void setAbstractCrawler(String abstractCrawler) {
		this.abstractCrawler = abstractCrawler;
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
