package com.bbs.service;

import java.util.List;
import java.util.Map;

import com.bbs.model.Comment;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

public interface CommentService {
	
	Comment getComment(Long cid);
		
	Comment getTopicLastComment(Long tid);
	
	List<Comment> getCommentList(QueryParam queryParam);
	
	Page<Map<String, Object>> getPageListMap(QueryParam queryParam);
	
	Long save( Long uid, Long toUid, Long tid, String content, String ua);
	
	boolean delete(Long cid);
	
	Long getComments(Long uid);

	/**
	 * 更新状态（1:正常 2:删除）
	 * @param cid
	 * @param status
	 */
	public boolean update(Long cid,Integer status);
	
	/**
	 * 根据帖子主键和用户主键查询是否评论过
	 * @author WKX
	 * @param tid 帖子主键
	 * @param uid 用户主键（评论人）
	 */
	public List<Comment> getCommentByTidAndUid(Long tid,Long uid);
}