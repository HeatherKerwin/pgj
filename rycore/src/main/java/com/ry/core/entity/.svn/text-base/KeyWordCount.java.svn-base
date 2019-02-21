package com.ry.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 百度推广关键字统计（搜索次数、新增用户数）
 * @author WKX
 */
@Entity(name="key_word_count")
public class KeyWordCount extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	
	private String day;//某一天
	
	private String keyword;//关键字
	
	private Integer sAmount;//搜索次数
	
	private Integer aAmount;//新增用户数

	/**
	 * 某一天
	 * @author WKX
	 * @since 2016年5月5日 上午9:53:38
	 */
	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	/**
	 * 关键字
	 * @author WKX
	 * @since 2016年5月5日 上午9:53:24
	 */
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * 搜索次数
	 * @author WKX
	 * @since 2016年5月5日 上午9:52:58
	 */
	@Column(name="s_amount")
	public Integer getsAmount() {
		return sAmount;
	}

	public void setsAmount(Integer sAmount) {
		this.sAmount = sAmount;
	}

	/**
	 * 新增用户数
	 * @author WKX
	 * @since 2016年5月5日 上午9:53:02
	 */
	@Column(name="a_amount")
	public Integer getaAmount() {
		return aAmount;
	}

	public void setaAmount(Integer aAmount) {
		this.aAmount = aAmount;
	}
}