package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "loggers")
public class Loggers extends BaseModel {

	private static final long serialVersionUID = 1L;
	private Integer createrId;
	private Date createTime;
	private Integer lastEditorId;
	private Date lastEditTime;
	private String content;
	
	@Column(name = "creater_id")
	public Integer getCreaterId() {
		return createrId;
	}
	
	public void setCreaterId(Integer createrId) {
		this.createrId = createrId;
	}
	
	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "last_editor_id")
	public Integer getLastEditorId() {
		return lastEditorId;
	}
	
	public void setLastEditorId(Integer lastEditorId) {
		this.lastEditorId = lastEditorId;
	}
	
	@Column(name = "last_edit_time")
	public Date getLastEditTime() {
		return lastEditTime;
	}
	
	public void setLastEditTime(Date lastEdit_Time) {
		this.lastEditTime = lastEdit_Time;
	}
	
	@Column(name = "content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
