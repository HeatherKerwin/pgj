package com.ry.core.entity.da;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="remainMonthByFunction")
public class RemainMonthByFunction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Date date;
	private Float yi;
	private Float er;
	private Float san;
	private Float si;
	private Float wu;
	private Float liu;
	private Float qi;
	private Float ba;
	private Float jiu;
	private String code;
	
	private String field;//标识功能和推广里的留存信息， 1功能，2推广PC,3推广WAP
	
	
	public RemainMonthByFunction() {
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

	@Column(name="date")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@Column(name="yi")
	public Float getYi() {
		return yi;
	}

	public void setYi(Float yi) {
		this.yi = yi;
	}

	@Column(name="er")
	public Float getEr() {
		return er;
	}

	public void setEr(Float er) {
		this.er = er;
	}

	@Column(name="san")
	public Float getSan() {
		return san;
	}

	public void setSan(Float san) {
		this.san = san;
	}

	@Column(name="si")
	public Float getSi() {
		return si;
	}

	public void setSi(Float si) {
		this.si = si;
	}

	@Column(name="wu")
	public Float getWu() {
		return wu;
	}

	public void setWu(Float wu) {
		this.wu = wu;
	}

	@Column(name="liu")
	public Float getLiu() {
		return liu;
	}

	public void setLiu(Float liu) {
		this.liu = liu;
	}

	@Column(name="qi")
	public Float getQi() {
		return qi;
	}

	public void setQi(Float qi) {
		this.qi = qi;
	}

	@Column(name="ba")
	public Float getBa() {
		return ba;
	}

	public void setBa(Float ba) {
		this.ba = ba;
	}

	@Column(name="jiu")
	public Float getJiu() {
		return jiu;
	}

	public void setJiu(Float jiu) {
		this.jiu = jiu;
	}

	@Column(name="code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 标识功能和推广里的留存信息，1功能，2推广PC,3推广WAP
	 * @return
	 * @date 2016年2月15日
	 * @author lvyanqin
	 */
	@Column(name="field")
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
	

}