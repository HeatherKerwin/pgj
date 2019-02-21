package com.ry.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 优惠消息
 * @author WKX
 */
@Entity(name="preferential_info")
public class PreferentialInfo extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	
	//标题
	private String title;
	
	//跳转页面
	private String url;
	
	//显示图片
	private String imgPath;
	
	//创建时间
	private Date createTime;
	
	/**
	 * 标题
	 * @author WKX
	 */
	@Column(length=30)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 跳转请求
	 * @author WKX
	 */
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 显示图片
	 * @author WKX
	 */
	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	/**
	 * 创建时间
	 * @author WKX
	 */
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}