package com.bbs.service;

import java.util.List;

import com.bbs.model.Award;

public interface AwardService {
	
	/**
	 * 保存一个抽奖记录
	 * @author WKX
	 * @param award
	 * @since 2016年11月9日 下午4:58:13
	 */
	public boolean save(Award award);
	
	/**
	 * 根据奖券主键获取奖品对象
	 * @author WKX
	 * @param tid
	 * @since 2016年11月19日 下午3:00:25
	 */
	public Award getByTid(Long tid);
	
	/**
	 * 根据主键获取对象
	 * @author WKX
	 * @param id
	 * @since 2016年11月19日 下午3:52:29
	 */
	public Award getById(Long id);
	
	/**
	 * 更新中奖记录
	 * @author WKX
	 * @param id 主键
	 * @param name 收件人
	 * @param phone 联系方式
	 * @param prov 省
	 * @param city 市
	 * @param dist 区
	 * @param address 详细地址
	 * @param remarks 备注
	 * @param state 状态（0未处理、1已处理、2无效）
	 * @since 2016年11月19日 下午3:06:15
	 */
	public boolean update(Long id,String name,String phone,String prov,String city,String dist,String address,String remarks,Integer state);
	
	/**
	 * 根据用户主键获取中奖记录
	 * @author WKX
	 * @param uid 用户主键
	 * @since 2016年11月19日 下午3:20:33
	 */
	public List<Award> getByUid(Long uid);
}