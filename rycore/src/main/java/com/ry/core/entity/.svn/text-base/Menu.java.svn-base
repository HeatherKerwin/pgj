package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="menu")
public class Menu{

	private Integer id;
	private Integer parent_id;//父类id
	private String name;//权限名称
	private Integer type_;//项目类型标识,1:数据分析系统，2：cms,3:crm
	private String href;//跳转路径
	private String url;
	private String html_id_value;//页面上对应的id的值
	private Date create_time;//创建时间
	private Integer state;//记录状态,-1,1
	private Integer sort;//子节点在父节点内的顺序
	
	public Menu() {
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

	@Column(name="parent_id")
	public Integer getParent_id() {
		return parent_id;
	}

	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 不同项目的标识,1:数据分析系统，2：cms,3:crm
	 * @return
	 * @date 2016年3月6日
	 * @author lvyanqin
	 */
	@Column(name="type_")
	public Integer getType_() {
		return type_;
	}

	public void setType_(Integer type_) {
		this.type_ = type_;
	}

	/**
	 * 跳转路径
	 * @return
	 * @date 2016年3月6日
	 * @author lvyanqin
	 */
	@Column(name="href")
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	@Column(name="url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 页面上对应的id的值
	 * @return
	 * @date 2016年3月6日
	 * @author lvyanqin
	 */
	@Column(name="html_id_value")
	public String getHtml_id_value() {
		return html_id_value;
	}

	public void setHtml_id_value(String html_id_value) {
		this.html_id_value = html_id_value;
	}

	/**
	 * 创建时间
	 * @return
	 * @date 2016年3月6日
	 * @author lvyanqin
	 */
	@Column(name="create_time")
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	/**
	 * 记录状态,-1,1
	 * @return
	 * @date 2016年3月6日
	 * @author lvyanqin
	 */
	@Column(name="state")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
}
