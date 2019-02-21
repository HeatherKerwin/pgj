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

import com.ry.core.util.Utility;

@Entity(name="message")
public class Message implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String messagecontent;//用户反馈的内容
	private String messagenumber;//之前都没存什么内容（现在当做保存手机号）
	private Integer memberid;//用户主键
	private Date messagetime;//反馈时间
	
	private String returnVisit;//回访备注
	
	private String source;//来源（APP、PC、BBS）
	
	private String messagetimeshow;//临时变量
	private String messagemobile;//临时变量
	private String memberName;//用户名称
	
	public Message(){
		super();
	}
	
	public Message(Integer id,String memberName,String messagemobile,Date messagetime,String messagecontent,String returnVisit){
		this.id = id;
		this.memberName = memberName;
		this.messagemobile = Utility.decodeMobile(messagemobile);
		this.messagetime = messagetime;
		this.messagecontent = messagecontent;
		this.returnVisit = returnVisit;
	}
	
	public Message(Integer id,String memberName,String messagemobile,Date messagetime,String messagecontent,String returnVisit,String source,String messagenumber){
		this.id = id;
		this.memberName = memberName;
		this.messagemobile = Utility.decodeMobile(messagemobile);
		this.messagetime = messagetime;
		this.messagecontent = messagecontent;
		this.returnVisit = returnVisit;
		this.source = source;
		this.messagenumber = messagenumber;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="messagecontent")
	public String getMessagecontent() {
		return messagecontent;
	}
	public void setMessagecontent(String messagecontent) {
		this.messagecontent = messagecontent;
	}
	
	@Column(name="memberid")
	public Integer getMemberid() {
		return memberid;
	}
	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="messagetime")
	public void setMessagetime(Date messagetime) {
		this.messagetime = messagetime;
	}
	public void setMessagenumber(String messagenumber) {
		this.messagenumber = messagenumber;
	}
	
	@Column(name="messagenumber")
	public String getMessagenumber() {
		return messagenumber;
	}
	public Date getMessagetime() {
		return messagetime;
	}
	
	@Transient
	public String getMessagetimeshow() {
		return messagetimeshow;
	}
	public void setMessagetimeshow(String messagetimeshow) {
		this.messagetimeshow = messagetimeshow;
	}
	
	@Transient
	public String getMessagemobile() {
		return messagemobile;
	}
	public void setMessagemobile(String messagemobile) {
		this.messagemobile = messagemobile;
	}
	
	/**
	 * 用户名称
	 * @author WKX
	 */
	@Transient
	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	/**
	 * 回访备注
	 * @author WKX
	 */
	public String getReturnVisit() {
		return returnVisit;
	}
	
	public void setReturnVisit(String returnVisit) {
		this.returnVisit = returnVisit;
	}

	/**
	 * 来源（APP、PC、BBS）
	 * @author WKX
	 * @since 2016年11月18日 上午9:59:47
	 */
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}