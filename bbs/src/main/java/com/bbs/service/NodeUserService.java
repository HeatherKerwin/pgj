package com.bbs.service;

import java.util.List;

import com.bbs.form.UserForm;
import com.bbs.model.NodeUser;
import com.bbs.model.User;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

public interface NodeUserService {
	
	/**
	 * 分页显示用户（根据板块 显示用户列表设置过的主键）
	 * @author WKX
	 * @param queryParam
	 * @since 2016年11月3日 上午10:09:38
	 */
	public Page<UserForm> getPageList(QueryParam queryParam);
	
	/**
	 * 设置版主
	 * @author WKX
	 * @param nodeUser
	 * @since 2016年11月3日 上午10:09:30
	 */
	public boolean save(NodeUser nodeUser);
	
	/**
	 * 取消版主
	 * @author WKX
	 * @param id
	 * @since 2016年11月3日 上午10:09:20
	 */
	public boolean delete(Long id);
	
	/**
	 * 获取版主设置（可根据用户主键获取负责的板块，也可以根据板块获取相应的版主）
	 * @author WKX
	 * @param queryParam
	 * @since 2016年11月3日 上午10:49:14
	 */
	public List<NodeUser> getList(QueryParam queryParam);
	
	/**
	 * 根据用户 获取他有权限的 板块
	 * @author WKX
	 * @param uid 用户主键
	 * @since 2016年11月3日 上午10:56:04
	 */
	public List<NodeUser> getListByUid(Long uid);
	
	/**
	 * 根据板块 获取版主
	 * @author WKX
	 * @param nid
	 * @since 2016年11月3日 上午11:08:19
	 */
	public List<NodeUser> getListByNid(Long nid);
	
	/**
	 * 根据板块和用户 获取是否是版主
	 * @author WKX
	 * @param nid 板块主键
	 * @param uid 用户主键
	 * @since 2016年11月3日 下午1:35:28
	 */
	public List<NodeUser> getListByNidUid(Long nid,Long uid);
	
	/**
	 * 根据板块主键获取版主信息
	 * @author WKX
	 * @param nid
	 * @since 2016年11月3日 下午2:06:58
	 */
	public List<User> getUserListByNid(Long nid);
	
	/**
	 * 获取用户列表（含临时属性）
	 * @author WKX
	 * @param queryParam
	 * @since 2016年11月4日 下午12:43:25
	 */
	public List<UserForm> getUserFormList(QueryParam queryParam);
	
	/**
	 * 获取该板块用户（版主）
	 * @author WKX
	 * @param nid
	 * @since 2016年11月22日 下午2:50:40
	 */
	public List<UserForm> getUserByNid(Long nid);
}