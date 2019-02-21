package com.bbs.model;

import java.io.Serializable;

import com.blade.jdbc.annotation.Table;

/**
 * 版主（把节点赋予用户）
 */
@Table(value = "t_node_user", PK = "id")
public class NodeUser implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	//节点id
	private Long nid;
	
	//用户id
	private Long uid;
	
	//创建时间
	private Long create_time;
	
	public NodeUser(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 节点主键
	 * @author WKX
	 * @since 2016年11月1日 下午4:33:05
	 */
	public Long getNid() {
		return nid;
	}

	public void setNid(Long nid) {
		this.nid = nid;
	}

	/**
	 * 用户主键
	 * @author WKX
	 * @since 2016年11月1日 下午4:33:14
	 */
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}
}