package com.bbs.service;

import java.util.List;

import com.bbs.model.Ticket;
import com.blade.jdbc.QueryParam;

public interface TicketService {
	
	/**
	 * 保存一张抽奖券
	 * @author WKX
	 * @param ticket
	 * @since 2016年11月9日 下午4:58:13
	 */
	public boolean save(Ticket ticket);
	
	/**
	 * 更新奖券（变成已使用）
	 * @author WKX
	 * @param id 奖券主键
	 * @since 2016年11月9日 下午5:41:59
	 */
	public boolean updateToUse(Long id);
	
	/**
	 * 获取列表
	 * @author WKX
	 * @param queryParam
	 * @since 2016年11月9日 下午6:03:23
	 */
	public List<Ticket> getList(QueryParam queryParam);
	
	/**
	 * 根据用户主键和状态 获取奖券
	 * @author WKX
	 * @param uid 用户主键
	 * @param state 状态（0未使用、1已使用、2无效）
	 * @since 2016年11月9日 下午6:18:13
	 */
	public List<Ticket> getByUidState(Long uid,Integer state);
	
	/**
	 * 保存奖券
	 * @author WKX
	 * @param uid 用户主键
	 * @param source 来源（0注册、1邀请用户）
	 * @since 2016年11月11日 下午3:59:06
	 */
	public boolean save(Long uid,Integer source);
	
	/**
	 * 根据用户和来源获取奖券
	 * @author WKX
	 * @param uid 用户主键
	 * @param source 来源（0注册、1邀请用户）
	 * @since 2016年11月17日 下午2:16:51
	 */
	public List<Ticket> getByUidSource(Long uid,Integer source);
}