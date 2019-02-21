package com.ry.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
@Entity(name="gongcui")
public class Gongcui implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String gongcuinumber;//公崔号码
	private String gongcuimember;//公崔人
	private String fayuan;//法院
	private Date gongcuidate;//公崔日期
	private String gongcuidateStr;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "gongcuinumber")
	public String getGongcuinumber() {
		return gongcuinumber;
	}
	public void setGongcuinumber(String gongcuinumber) {
		this.gongcuinumber = gongcuinumber;
	}
	@Column(name = "gongcuimember")
	public String getGongcuimember() {
		return gongcuimember;
	}
	public void setGongcuimember(String gongcuimember) {
		this.gongcuimember = gongcuimember;
	}
	@Column(name = "fayuan")
	public String getFayuan() {
		return fayuan;
	}
	public void setFayuan(String fayuan) {
		this.fayuan = fayuan;
	}
	@Column(name = "gongcuidate")
	public Date getGongcuidate() {
		return gongcuidate;
	}
	public void setGongcuidate(Date dateT) {
		this.gongcuidate = dateT;
	}
	@Transient
	public String getGongcuidateStr() {
		return gongcuidateStr;
	}
	public void setGongcuidateStr(String gongcuidateStr) {
		this.gongcuidateStr = gongcuidateStr;
	}
	
	
}
