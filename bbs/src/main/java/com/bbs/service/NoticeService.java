package com.bbs.service;

import java.util.Map;

import com.blade.jdbc.Page;

public interface NoticeService {
	
	boolean save(String type,Long uid, Long to_uid, Long event_id);
	
	boolean read(Long to_uid);
	
	Page<Map<String, Object>> getNoticePage(Long to_uid, Integer page, Integer count);

	Long getNotices(Long to_uid);
	
	/**
	 * 删除该评论相关通知
	 * @author WKX
	 * @param cid 评论主键（事件主键）
	 */
	public boolean deleteFromComment(Long cid);
}