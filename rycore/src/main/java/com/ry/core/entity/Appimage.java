package com.ry.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="appimage")
public class Appimage implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String code;//index_banner横幅,index_rili日历,index_ad广告;
	private String path;
	private String picdesc;
	private String sort; //@app2.3 图片显示的顺序
	
	//请求路径[含http://则是外网请求]
	private String url;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name="path")
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	@Column(name="picdesc")
	public String getPicdesc() {
		return picdesc;
	}
	public void setPicdesc(String picdesc) {
		this.picdesc = picdesc;
	}
	
	@Column(name="sort")
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	/**
	 * 请求路径
	 * @author WKX
	 */
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
}