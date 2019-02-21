package com.bbs.model;

import java.io.Serializable;

import com.blade.jdbc.annotation.Table;

/**
 * Banner对象
 */
@Table(value = "t_banner", PK = "id")
public class Banner implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	// 图片URL
	private String url;
	
	// 图片路径
	private String path;
	
	// 图片顺序
	private Integer sort;
	
	// 是否删除 0没有删除，1删除
	private Integer is_del;
	
	// 创建时间
	private Long create_time;
	
	public Banner(){}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getIs_del() {
		return is_del;
	}

	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}
}