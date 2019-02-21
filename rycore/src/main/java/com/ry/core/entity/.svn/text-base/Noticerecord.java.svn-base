package com.ry.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * 提醒（票据管理）
 * @author RY
 */
@Entity(name="noticerecord")
public class Noticerecord implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer memberid;
	private Date expiredate;//到期时间
	private Date noticetime;//提醒时间
	private Integer type;//票据类型 1:到期买断 2:回购
	
	private String noticedesc;//内容
	private Long noticeaddtime;//创建时间
	private Integer allprice;//总价格
	
	private Integer fkId;//@WKX APP2.1外键（对应票据的表）
	
	private String expiredateshow;
	private String noticetimeshow;
	private String noticetimedescshow;
	private String expiredateweek;
	private String noticetimeweek;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="memberid")
	public Integer getMemberid() {
		return memberid;
	}
	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}
	
	@Column(name="expiredate")
	public Date getExpiredate() {
		return expiredate;
	}
	public void setExpiredate(Date expiredate) {
		this.expiredate = expiredate;
	}
	
	@Column(name="noticetime")
	public Date getNoticetime() {
		return noticetime;
	}
	public void setNoticetime(Date noticetime) {
		this.noticetime = noticetime;
	}
	
	@Column(name="type")
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	@Column(name="noticedesc")
	public String getNoticedesc() {
		return noticedesc;
	}
	public void setNoticedesc(String noticedesc) {
		this.noticedesc = noticedesc;
	}
	
	@Column(name="noticeaddtime")
	public Long getNoticeaddtime() {
		return noticeaddtime;
	}
	public void setNoticeaddtime(Long noticeaddtime) {
		this.noticeaddtime = noticeaddtime;
	}
	
	@Column(name="allprice")
	public Integer getAllprice() {
		return allprice;
	}
	public void setAllprice(Integer allprice) {
		this.allprice = allprice;
	}
	
	public Integer getFkId() {
		return fkId;
	}
	public void setFkId(Integer fkId) {
		this.fkId = fkId;
	}
	
	@Transient
	public String getExpiredateshow() {
		return expiredateshow;
	}
	public void setExpiredateshow(String expiredateshow) {
		this.expiredateshow = expiredateshow;
	}
	
	@Transient
	public String getNoticetimeshow() {
		return noticetimeshow;
	}
	public void setNoticetimeshow(String noticetimeshow) {
		this.noticetimeshow = noticetimeshow;
	}
	
	@Transient
	public String getNoticetimedescshow() {
		return noticetimedescshow;
	}
	public void setNoticetimedescshow(String noticetimedescshow) {
		this.noticetimedescshow = noticetimedescshow;
	}
	
	@Transient
	public String getExpiredateweek() {
		return expiredateweek;
	}
	public void setExpiredateweek(String expiredateweek) {
		this.expiredateweek = expiredateweek;
	}
	
	@Transient
	public String getNoticetimeweek() {
		return noticetimeweek;
	}
	public void setNoticetimeweek(String noticetimeweek) {
		this.noticetimeweek = noticetimeweek;
	}
}
