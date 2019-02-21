package com.ry.core.service;

import org.springframework.stereotype.Repository;

import com.ry.core.entity.OperateLog;

/**
 * 操作日志
 * @author WKX
 */
@Repository
public interface OperateLogService {
	
	/**
	 * 保存操作日志
	 * @author WKX
	 * @param log
	 * @since 2016年7月20日 下午4:43:56
	 */
	public void saveModel(OperateLog log);
}