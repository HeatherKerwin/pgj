package com.bbs.service;

import java.util.List;
import java.util.Map;

import com.bbs.form.UserForm;
import com.bbs.model.Topic;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

public interface TopicService {
	
	Topic getTopic(Long tid);
	
	List<Long> topicIds();
	
	Map<String, Object> getTopicMap(Topic topic, boolean isDetail);
	
	List<Map<String, Object>> getTopicList(QueryParam queryParam);
	
	Page<Map<String, Object>> getPageList(QueryParam queryParam);
	
	/**
	 * 保存帖子
	 * @param topic 帖子对象
	 */
	public Long save(Topic topic);
	
	/**
	 * 保存对象
	 * @author WKX
	 * @param uid 用户主键
	 * @param nid 板块主键
	 * @param title 标题
	 * @param content 内容
	 * @param isTop 是否置顶
	 * @param attach_type 附加内容类型
	 * @param attach_content 附加内容类型
	 */
	public Long save(Long uid, Long nid, String title, String content, Integer isTop,Long attach_type,String attach_content);
	
	/**
	 * 编辑帖子
	 * @author WKX
	 * @param tid 帖子主键
	 * @param nid 板块主键
	 * @param title 标题
	 * @param content 内容
	 * @param attach_type 附加内容类型
	 * @param attach_content 附加内容类型
	 */
	public Long update(Long tid, Long nid, String title, String content,Long attach_type,String attach_content);
	
	boolean comment(Long uid, Long to_uid, Long tid, String content, String ua);
	
	boolean delete(Long tid);
	
	boolean refreshWeight();
	
	boolean updateWeight(Long tid);
	
	boolean updateWeight(Long tid, Long loves, Long favorites, Long comment, Long sinks, Long create_time);
	
	Long getTopics(Long uid);

	Long getLastCreateTime(Long uid);
	
	Long getLastUpdateTime(Long uid);

	Page<Map<String, Object>> getHotTopic(Long nid, Integer page, Integer count);
	
	Page<Map<String, Object>> getRecentTopic(Long nid, Integer page, Integer count);

	void essence(Long tid, Integer count);
	
	/**
	 * 搜索
	 * @author WKX
	 * @param q 关键字
	 * @param page 页
	 * @param count 数量
	 * @since 2016年11月4日 上午10:01:26
	 */
	public Page<Map<String, Object>> getSearchTopic(String q, Integer page, Integer count);
	
	/**
	 * 获取会员排名（前几名）
	 * @author WKX
	 * @param queryParam
	 * @since 2016年11月7日 上午9:52:06
	 */
	public List<UserForm> getTopUser();
	
	/**
	 * 更新帖子板块
	 * @author WKX
	 * @param nid 板块
	 * @param tid 帖子
	 * @since 2017年6月15日 上午10:37:41
	 */
	public void updateNidByTid(Long nid,Long tid);
	
	/**
	 * 删除评论（虚拟删除）含相关通知
	 * @author WKX
	 * @param tid 帖子主键
	 * @param cid 评论主键
	 * @since 2017年6月20日 下午4:37:41
	 */
	public boolean updateAndDelComment(Long tid,Long cid);
}