package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 爬虫爬取票号信息
 * @author MH
 *
 */
@Entity(name = "reptile_ticket")
public class ReptileTicket extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	private String title;//爬取的内容头部
	private String content;//爬取的内容
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date createTime;//录入数据库的时间
	private String court;//发布信息的法院
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date releaseTime;//信息内容发布的时间
	
	@Column(name = "title")
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
	
	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "court")
	public String getCourt() {
		return court;
	}
	public void setCourt(String court) {
		this.court = court;
	}
	
	@Column(name = "release_time")
	public Date getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	
	@Override
	public String toString() {
		return "ReptileTicket [title=" + title + ", content=" + content + ", createTime=" + createTime + ", court="
				+ court + ", releaseTime=" + releaseTime + "]";
	}
	
}
