package com.ry.core.service;

import java.util.Map;

import com.ry.core.entity.BeatCode;

public interface BeatCodeService {

	/**
	 * 根据公测码获取有效对象（功能在于验证该验证吗是否有效）
	 * @author WKX
	 * @param no
	 * @since 2016年3月23日 下午1:30:55
	 */
	public BeatCode getEnableByNo(String no);
	
	/**
	 * 更新对象
	 * @author WKX
	 * @param beatCode
	 * @since 2016年3月23日 下午1:33:01
	 */
	public void updateModel(BeatCode beatCode);
	
	/**
	 * 保存对象
	 * @author WKX
	 * @param beatCode
	 * @since 2016年8月5日 下午3:22:39
	 */
	public String saveModel(BeatCode beatCode);
	
	/**
	 * 根据电话号码查询该用户已经输入过公测码
	 * @author WKX
	 * @param memberId
	 * @since 2016年3月23日 下午3:32:37
	 */
	public Map<String,Object> getByMemberId(Integer memberId);
	
	/**
	 * 根据编号、手机号（查询是否公测过）
	 * @author WKX
	 * @param no 
	 * @param phone
	 * @since 2016年8月25日 上午10:11:08
	 */
	public BeatCode getByNoAndPhone(String no,String phone);
}