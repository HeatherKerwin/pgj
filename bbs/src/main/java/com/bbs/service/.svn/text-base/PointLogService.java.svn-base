package com.bbs.service;

import java.util.List;

import com.bbs.model.PointLog;
import com.bbs.model.PointLog.Fun;
import com.blade.jdbc.QueryParam;

public interface PointLogService {
	
	public boolean save(PointLog pointLog);
	
	/**
	 * 积分行为记录
	 * @author WKX
	 * @param uid 获取积分用户
	 * @param hid 触发行为用户
	 * @param fun 行为
	 * @param fk_id 外键（帖子）
	 * @since 2016年10月31日 下午5:38:41
	 */
	public boolean save(Long uid,Long hid,Fun fun,Long fk_id);
	
	/**
	 * 获取集合
	 * @author WKX
	 * @param queryParam
	 * @since 2016年11月7日 上午9:47:23
	 */
	public List<PointLog> getList(QueryParam queryParam);
	
	/**
	 * 根据主键删除对象
	 * @author WKX
	 * @param id
	 * @since 2016年11月7日 上午9:44:12
	 */
	public boolean delete(Long id);
	
	/**
	 * 发帖（编辑帖子不计算）
	 * @author WKX
	 * @param uid 用户主键
	 * @param fk_id 帖子主键
	 * @since 2016年11月7日 上午10:47:54
	 */
	public void saveFATIE(Long uid,Long fk_id);
	
	/**
	 * 点赞（只计算受体）含取消
	 * @author WKX
	 * @param uid 受体用户
	 * @param hid 触发用户
	 * @param fk_id 帖子主键
	 * @since 2016年11月7日 上午10:48:14
	 */
	public void saveDIANZAN(Long uid,Long hid,Long fk_id);
	
	/**
	 * 精华帖（只计算受体）含取消
	 * @author WKX
	 * @param uid 受体用户
	 * @param hid 触发用户
	 * @param fk_id 帖子主键
	 * @since 2016年11月7日 下午12:10:01
	 */
	public void saveJINGHUA(Long uid,Long hid,Long fk_id);
	
	/**
	 * 完善信息
	 * @author WKX
	 * @param uid 用户主键
	 * @since 2016年11月7日 下午12:10:37
	 */
	public void saveWANSHAN(Long uid);
}