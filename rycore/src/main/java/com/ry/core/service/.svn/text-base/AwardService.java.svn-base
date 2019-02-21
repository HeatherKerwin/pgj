package com.ry.core.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.entity.Award;

/**
 * 中奖纪录表
 * @author WKX
 */
@Repository
public interface AwardService {

	/**
	 * 查询该用户中奖纪录
	 * @author WKX
	 * @param memberId
	 * @since 2016年2月23日 上午9:40:31
	 */
	public List<Award> getByMemberId(Integer memberId);
	
	/**
	 * 保存（更新）
	 * @author WKX
	 * @param award
	 * @since 2016年2月23日 上午9:40:14
	 */
	public void save(Award award) throws Exception;
}