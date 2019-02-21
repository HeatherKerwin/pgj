package com.bbs.service;

import java.util.List;

import com.bbs.model.Userlog;
import com.blade.jdbc.QueryParam;

public interface UserlogService {
	
	List<Userlog> getUserlogList(QueryParam queryParam);
	
	void save(Long uid, String action, String content);
	
	/**
	 * 保存访问记录（每天仅仅一条）
	 * @author WKX
	 * @param uid 用户主键
	 * @param action 用户动作
	 * @param content 描述
	 */
	public void saveVisit(final Long uid, final String action, final String content);
}