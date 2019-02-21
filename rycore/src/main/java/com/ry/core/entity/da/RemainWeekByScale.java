package com.ry.core.entity.da;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="remainWeekByScale")
public class RemainWeekByScale {

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
	private Float shi;
	private Float shiEr;
	private Float shiSan;
	private Float shiWu;
	private Float shiBa;
	private Float erShiEr;
	private Float erShiLiu;
	
	public RemainWeekByScale() {
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

	@Column(name="shi")
	public Float getShi() {
		return shi;
	}

	public void setShi(Float shi) {
		this.shi = shi;
	}

	@Column(name="shiEr")
	public Float getShiEr() {
		return shiEr;
	}

	public void setShiEr(Float shiEr) {
		this.shiEr = shiEr;
	}

	@Column(name="shiSan")
	public Float getShiSan() {
		return shiSan;
	}

	public void setShiSan(Float shiSan) {
		this.shiSan = shiSan;
	}

	@Column(name="shiWu")
	public Float getShiWu() {
		return shiWu;
	}

	public void setShiWu(Float shiWu) {
		this.shiWu = shiWu;
	}

	@Column(name="shiBa")
	public Float getShiBa() {
		return shiBa;
	}

	public void setShiBa(Float shiBa) {
		this.shiBa = shiBa;
	}

	@Column(name="erShiEr")
	public Float getErShiEr() {
		return erShiEr;
	}

	public void setErShiEr(Float erShiEr) {
		this.erShiEr = erShiEr;
	}

	@Column(name="erShiLiu")
	public Float getErShiLiu() {
		return erShiLiu;
	}

	public void setErShiLiu(Float erShiLiu) {
		this.erShiLiu = erShiLiu;
	}
	
}
