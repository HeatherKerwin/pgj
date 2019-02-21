package com.ry.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 节假日（补班）
 * @author WKX
 */
@Entity(name="notice_add")
public class NoticeAdd extends BaseModel{

	private static final long serialVersionUID = 1L;

	//节假日主键
	private Integer noticeId;
	
	//哪天
	private String day;

	/**
	 * 节假日主键
	 */
	@Column(name="notice_id")
	public Integer getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}

	/**
	 * 哪天补班
	 */
	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}
}