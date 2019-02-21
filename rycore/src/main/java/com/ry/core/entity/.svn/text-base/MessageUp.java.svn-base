package com.ry.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="messageup")
public class MessageUp extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String content;//短信内容
	private String dest_code;//扩展码
	private String sender;//手机号码
	private String sender_time;//时间
	private String sms_seq;//唯一ID
	
	public MessageUp() {}
	public MessageUp(String content, String dest_code, String sender, String sender_time, String sms_seq) {
		super();
		this.content = content;
		this.dest_code = dest_code;
		this.sender = sender;
		this.sender_time = sender_time;
		this.sms_seq = sms_seq;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDest_code() {
		return dest_code;
	}
	public void setDest_code(String dest_code) {
		this.dest_code = dest_code;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getSender_time() {
		return sender_time;
	}
	public void setSender_time(String sender_time) {
		this.sender_time = sender_time;
	}
	public String getSms_seq() {
		return sms_seq;
	}
	public void setSms_seq(String sms_seq) {
		this.sms_seq = sms_seq;
	}
	
	
}
