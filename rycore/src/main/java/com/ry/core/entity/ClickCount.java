package com.ry.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * 记录访问注册数据量
 * @EDIT WKX {
 * code字段添加：hezuo（合作页面，其他网站内嵌该页面的访问统计）
 * style字段添加：PC_HYT（PC端 红眼兔合作页面）
 * }
 */
@Entity(name="clickCount")
public class ClickCount implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;//主键
	private String record;//事件记录字段
	private String ip;//ip
	private Integer memberId;//用户登录id
	private String url;//访问的当前页面
	private String referrerUrl;//跳转前url
	/*visit,
	 * pc:homepage,actitvty,function,back,topbt,logo,weixin,phone
	 * app:phone,function,button,register,topDownload,bottomDownload*/
	private String code;//用来统计不同类型的访问量,
	private Date currentDate;//访问的当前日期
	private String style;//PC、APP、HEZUO_HYT（红眼兔合作页面）
	private String uuid;//用cookie存在客户端
	
	private String keyword;//@WKX 保存来自百度搜索的 关键字

	public ClickCount() {
		
	}

	@Id
	@GenericGenerator(name = "generator", strategy = "identity")
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="ip")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name="memberId")
	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	@Column(name="record")
	public String getRecord() {
		return record;
	}
	
	public void setRecord(String record) {
		this.record = record;
	}

	@Column(name="url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name="referrerUrl")
	public String getReferrerUrl() {
		return referrerUrl;
	}

	public void setReferrerUrl(String referrerUrl) {
		this.referrerUrl = referrerUrl;
	}

	@Column(name="code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name="currentDate")
	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	@Column(name="style")
	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	@Column(name="uuid")
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * 保存来自百度搜索的 关键字
	 * @author WKX
	 * @since 2016年5月3日 上午11:21:25
	 */
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}