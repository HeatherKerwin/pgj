package com.ry.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="sitestatistics")
public class Sitestatistics implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2609108773998193084L;
	private Integer id;
	private String hezuo;	
	private String url;
	private String ip;
	private Date invitedate;
	private String type;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="hezuo")
	public String getHezuo() {
		return hezuo;
	}
	public void setHezuo(String hezuo) {
		this.hezuo = hezuo;
	}		
	
	@Column(name="url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Column(name="invitedate")
	public Date getInvitedate() {
		return invitedate;
	}
	public void setInvitedate(Date invitedate) {
		this.invitedate = invitedate;
	}
	
	@Column(name="ip")
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@Column(name="type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}			
		
}
