package com.bbs.model;

import java.io.Serializable;
import java.util.Date;

import com.blade.jdbc.annotation.Table;

/**
 * 中奖纪录（含发货地址）[每三个用户一张奖券]暂不定上限
 */
@Table(value = "t_ticket", PK = "id")
public class Ticket implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long uid;//用户主键
	private Integer source;//来源（0注册、1邀请用户）
	private Integer state = 0;//状态（0未使用、1已使用、2无效）
	private Date create_time;//创建时间
	private Date update_time;//更新时间
	
	public Ticket(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
}