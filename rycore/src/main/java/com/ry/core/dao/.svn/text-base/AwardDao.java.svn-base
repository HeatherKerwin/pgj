package com.ry.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.entity.Award;

@Repository
public interface AwardDao {

	/**
	 * 根据用户获取中奖纪录
	 * @author WKX
	 * @param memberId
	 * @since 2016年2月23日 上午9:24:12
	 */
	public List<Award> getByMemberId(Integer memberId);
	
	/**
	 * 保存（更新）
	 * @author WKX
	 * @param award
	 * @since 2016年2月23日 上午9:32:55
	 */
	public void saveAward(Award award);
}