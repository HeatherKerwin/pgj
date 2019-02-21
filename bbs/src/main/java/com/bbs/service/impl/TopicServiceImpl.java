package com.bbs.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.bbs.Types;
import com.bbs.form.UserForm;
import com.bbs.kit.DateKit;
import com.bbs.kit.Utils;
import com.bbs.model.Comment;
import com.bbs.model.Node;
import com.bbs.model.PointLog.Fun;
import com.bbs.model.Topic;
import com.bbs.model.TopicCount;
import com.bbs.model.User;
import com.bbs.model.Userinfo;
import com.bbs.service.CommentService;
import com.bbs.service.NodeService;
import com.bbs.service.NoticeService;
import com.bbs.service.PointLogService;
import com.bbs.service.SettingsService;
import com.bbs.service.TopicCountService;
import com.bbs.service.TopicService;
import com.bbs.service.UserService;
import com.bbs.service.UserinfoService;
import com.blade.ioc.annotation.Inject;
import com.blade.ioc.annotation.Service;
import com.blade.jdbc.AR;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

import blade.kit.CollectionKit;
import blade.kit.StringKit;

@Service
public class TopicServiceImpl implements TopicService {
	
	@Inject
	private UserService userService;
	
	@Inject
	private NodeService nodeService;
	
	@Inject
	private CommentService commentService;
	
	@Inject
	private NoticeService noticeService;
	
	@Inject
	private SettingsService settingsService;
	
	@Inject
	private TopicCountService topicCountService;
	
	@Inject
	private PointLogService pointLogService;
	
	@Inject
	private UserinfoService userinfoService;
	
	@Override
	public Topic getTopic(Long tid) {
		return AR.findById(Topic.class, tid);
	}
	
	@Override
	public List<Map<String, Object>> getTopicList(QueryParam queryParam) {
		if(null != queryParam){
			List<Topic> topics = AR.find(queryParam).list(Topic.class);
			return this.getTopicListMap(topics);
		}
		return null;
	}
	
	@Override
	public Page<Map<String, Object>> getPageList(QueryParam queryParam) {
		if(null != queryParam){
			Page<Topic> topicPage = AR.find(queryParam).page(Topic.class);
			return this.getTopicPageMap(topicPage);
		}
		return null;
	}
	
	private List<Map<String, Object>> getTopicListMap(List<Topic> topics){
		List<Map<String, Object>> topicMaps = new ArrayList<Map<String,Object>>();
		if(null != topics && topics.size() > 0){
			for(Topic topic : topics){
				Map<String, Object> map = this.getTopicMap(topic, false);
				if(null != map && !map.isEmpty()){
					topicMaps.add(map);
				}
			}
		}
		return topicMaps;
	}
	
	private Page<Map<String, Object>> getTopicPageMap(Page<Topic> topicPage){
		
		long totalCount = topicPage.getTotalCount();
		int page = topicPage.getPage();
		int pageSize = topicPage.getPageSize();
		Page<Map<String, Object>> result = new Page<Map<String,Object>>(totalCount, page, pageSize);
		
		List<Topic> topics = topicPage.getResults();
		List<Map<String, Object>> topicMaps = this.getTopicListMap(topics);
		result.setResults(topicMaps);
		
		return result;
	}
	
	@Override
	public Long save(Long uid, Long nid, String title, String content, Integer isTop,Long attach_type,String attach_content){
		if(attach_type==null)attach_content = null;//附加内容设置
		Topic topic = new Topic();
		topic.setUid(uid);
		topic.setNid(nid);
		topic.setTitle(title);
		topic.setContent(content);
		topic.setIs_top(isTop);
		topic.setAttach_type(attach_type);
		topic.setAttach_content(attach_content);
		return this.save(topic);
	}
	
	@Override
	public Long save(Topic topic) {
		try {
			Integer time = DateKit.getCurrentUnixTime();
			
			Long tid = (Long) AR.update("insert into t_topic(uid, nid, title, content, is_top, create_time, update_time, status,attach_type,attach_content) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
					topic.getUid(), topic.getNid(), topic.getTitle(), topic.getContent(), topic.getIs_top(), time, time, 1,topic.getAttach_type(),topic.getAttach_content()).key();
			
			if(null != tid){
				topicCountService.save(tid, time);
				
				this.updateWeight(tid);
				
				//更新节点下的帖子数
				nodeService.updateCount(topic.getUid(), Types.topics.toString(), +1);
				
				//更新总贴数
				settingsService.updateCount(Types.topic_count.toString(), +1);
				
				//通知@的人
				if(null != tid){
					Set<String> atUsers = Utils.getAtUsers(topic.getContent());
					if(CollectionKit.isNotEmpty(atUsers)){
						for(String user_name : atUsers){
							User user = userService.getUserByLoginName(user_name);
							if(null != user && !user.getUid().equals(topic.getUid())){
								noticeService.save(Types.topic_at.toString(),topic.getUid(), user.getUid(), tid);
							}
						}
					}
				}
			}
			return tid;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean delete(Long tid) {
		if(null != tid){
			Topic topic = this.getTopic(tid);
			if(null == topic){
				return false;
			}
			AR.update("update t_topic set status = 2 where tid = ?", tid).executeUpdate(true);
			//更新节点下的帖子数
			nodeService.updateCount(topic.getNid(), Types.topics.toString(), +1);
			//更新总贴数
			settingsService.updateCount(Types.topic_count.toString(), +1);
			return true;
		}
		return false;
	}

	@Override
	public Map<String, Object> getTopicMap(Topic topic, boolean isDetail) {
		if(null == topic){
			return null;
		}
		Long tid = topic.getTid();
		Long uid = topic.getUid();
		Long nid = topic.getNid();
		
		User user = userService.getUser(uid);
		Node node = nodeService.getNode(nid);
		Userinfo info = userinfoService.getUserinfo(uid);
		
		if(null == user || null == node){
			return null;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tid", tid);
		
		TopicCount topicCount = topicCountService.getCount(tid);
		Long views = 0L, loves = 0L, favorites = 0L, comments = 0L;
		if(null != topicCount){
			views = topicCount.getViews();
			loves = topicCount.getLoves();
			favorites = topicCount.getFavorites();
			comments = topicCount.getComments();
		}
		
		map.put("views", views);
		map.put("loves", loves);
		map.put("favorites", favorites);
		map.put("comments", comments);
		map.put("title", topic.getTitle());
		map.put("is_essence", topic.getIs_essence());
		map.put("create_time", topic.getCreate_time());
		map.put("update_time", topic.getUpdate_time());
		map.put("user_name", user.getLogin_name());
		map.put("uid", topic.getUid());
		map.put("nid", topic.getNid());
		
		map.put("attach_type", topic.getAttach_type());
		map.put("attach_content", topic.getAttach_content());
		if(info!=null)map.put("nick_name", info.getNick_name());
		
		String avatar = Utils.getPhoto(user.getAvatar(),"user");
		
		map.put("avatar", avatar);
		map.put("node_name", node.getTitle());
		map.put("node_slug", node.getSlug());
		map.put("has_pic", this.hasPicture(topic.getContent(),topic.getAttach_content()));//校验是否存在图片
		
		if(comments > 0){
			Comment comment = commentService.getTopicLastComment(tid);
			if(null != comment){
				User reply_user = userService.getUser(comment.getUid());
				Userinfo reply_info = userinfoService.getUserinfo(comment.getUid());
				map.put("reply_name", reply_user.getLogin_name());
				if(reply_info!=null)map.put("reply_nick_name", reply_info.getNick_name());
			}
		}
		if(isDetail){
			String content = Utils.markdown2html(topic.getContent());
			map.put("content", content);
		}
		return map;
	}
	
	/**
	 * 校验内容是否含（图片）
	 * @author WKX
	 * @param content 帖子内容
	 */
	private Boolean hasPicture(String content,String attach_content){
		Boolean bl = false;
		if (content.indexOf(".jpg")!=-1){bl = true;return bl;}
		if (content.indexOf(".JPG")!=-1){bl = true;return bl;}
		if (content.indexOf(".png")!=-1){bl = true;return bl;}
		if (content.indexOf(".PNG")!=-1){bl = true;return bl;}
		if (content.indexOf("<img src=")!=-1){bl = true;return bl;}
		if(!bl){
			if (StringUtils.isNotBlank(attach_content) && attach_content.indexOf(".jpg")!=-1){bl = true;return bl;}
			if (StringUtils.isNotBlank(attach_content) && attach_content.indexOf(".JPG")!=-1){bl = true;return bl;}
			if (StringUtils.isNotBlank(attach_content) && attach_content.indexOf(".png")!=-1){bl = true;return bl;}
			if (StringUtils.isNotBlank(attach_content) && attach_content.indexOf(".PNG")!=-1){bl = true;return bl;}
			if (StringUtils.isNotBlank(attach_content) && attach_content.indexOf("<img src=")!=-1){bl = true;return bl;}
		}
		return bl;
	}

	/**
	 * 评论帖子
	 * @param uid		评论人uid
	 * @param to_uid	发帖人uid
	 * @param tid		帖子id
	 * @param content	评论内容
	 * @return
	 */
	@Override
	public boolean comment(Long uid, Long to_uid, Long tid, String content, String ua) {
		try {
			Long cid = commentService.save(uid, to_uid, tid, content, ua);
			if(null != cid){
				
				topicCountService.update(Types.comments.toString(), tid, 1);
				this.updateWeight(tid);
				
				// 通知
				if(!uid.equals(to_uid)){
					noticeService.save(Types.comment.toString(),uid, to_uid, cid);
					
					// 通知@的用户
					Set<String> atUsers = Utils.getAtUsers(content);
					if(CollectionKit.isNotEmpty(atUsers)){
						for(String user_name : atUsers){
							User user = userService.getUserByLoginName(user_name);
							if(null != user && !user.getUid().equals(uid)){
								noticeService.save(Types.comment_at.toString(),uid,user.getUid(), cid);
							}
						}
					}
					//更新总评论数
					settingsService.updateCount(Types.comment_count.toString(), +1);
					pointLogService.save(to_uid, uid, Fun.HUIFU, tid);//评论（获取积分）
				}
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Long getTopics(Long uid) {
		if(null != uid){
			return AR.find("select count(1) from t_topic where uid = ? and status = 1", uid).first(Long.class);
		}
		return 0L;
	}

	@Override
	public Long update(Long tid, Long nid, String title, String content,Long attach_type,String attach_content) {
		if(null != tid && null != nid && StringKit.isNotBlank(title) && StringKit.isNotBlank(content)){
			if(attach_type==null)attach_content = null;//附加内容设置
			try {
				AR.update("update t_topic set nid = ?, title = ?, content = ?,attach_type=?,attach_content=?, update_time = ? where tid = ?",
						nid, title, content,attach_type,attach_content, DateKit.getCurrentUnixTime(), tid).executeUpdate(true);
				return tid;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Long getLastCreateTime(Long uid) {
		if(null == uid){
			return null;
		}
		return AR.find("select create_time from t_topic where uid = ? order by create_time desc", uid).first(Long.class);
	}
	
	@Override
	public Long getLastUpdateTime(Long uid) {
		if(null == uid){
			return null;
		}
		return AR.find("select update_time from t_topic where uid = ? order by update_time desc", uid).first(Long.class);
	}
	
	@Override
	public boolean refreshWeight() {
		List<Long> topics = AR.find("select tid from t_topic where status = 1").list(Long.class);
		if(null != topics) {
			for(Long tid : topics){
				this.updateWeight(tid);
			}
		}
		return false;
	}

	public boolean updateWeight(Long tid, Long loves, Long favorites, Long comment, Long sinks, Long create_time) {
		if(null == tid){
			return false;
		}
		
		try {
			double weight = Utils.getWeight(loves, favorites, comment, sinks, create_time);
			AR.update("update t_topic set weight = ? where tid = ?", weight, tid).executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Page<Map<String, Object>> getHotTopic(Long nid, Integer page, Integer count) {
		if(null == page || page < 1){
			page = 1;
		}
		QueryParam tp = QueryParam.me();
		if(null != nid){
			tp.eq("nid", nid);
		}
		tp.eq("status", 1).orderby("weight desc").page(page, count);
		return this.getPageList(tp);
	}

	@Override
	public Page<Map<String, Object>> getRecentTopic(Long nid, Integer page, Integer count) {
		if(null == page || page < 1){
			page = 1;
		}
		QueryParam tp = QueryParam.me();
		if(null != nid){
			tp.eq("nid", nid);
		}
		tp.eq("status", 1).orderby("create_time desc").page(page, count);
		return this.getPageList(tp);
	}

	@Override
	public void essence(Long tid, Integer count) {
		try {
			AR.update("update t_topic set is_essence = ?,essence_time=? where tid = ?", count,DateKit.getCurrentUnixTime(), tid).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean updateWeight(Long tid) {
		if(null != tid){
			TopicCount topicCount = topicCountService.getCount(tid);
			if(topicCount==null)return false;
			
			Long loves = topicCount.getLoves();
			Long favorites = topicCount.getFavorites();
			Long comment = topicCount.getComments();
			Long sinks = topicCount.getSinks();
			Long create_time = topicCount.getCreate_time();
			return this.updateWeight(tid, loves, favorites, comment, sinks, create_time);
		}
		return false;
	}

	@Override
	public List<Long> topicIds() {
		return AR.find("select tid from t_topic where status = 1").list(Long.class);
	}
	
	@Override
	public Page<Map<String, Object>> getSearchTopic(String q, Integer page, Integer count) {
		if(null == page || page < 1){
			page = 1;
		}
		StringBuffer sql = new StringBuffer("SELECT * FROM t_topic t WHERE t.status =? AND (t.title LIKE ? OR t.content LIKE ?) order by t.create_time desc ");
		List<Object> param = new ArrayList<Object>();
		param.add(1);
		param.add("%"+q+"%");
		param.add("%"+q+"%");
		
		QueryParam tp = QueryParam.me();
		tp.add(sql.toString(), param.toArray());
		tp.page(page, count);
		return this.getPageList(tp);
	}
	
	@Override
	public List<UserForm> getTopUser() {
		QueryParam up = QueryParam.me();
		StringBuffer sql = new StringBuffer("SELECT u.*,p.score FROM t_user u LEFT JOIN (SELECT p.uid,SUM(p.score)score FROM t_pointlog p GROUP BY uid)p ON u.uid=p.uid ORDER BY p.score DESC LIMIT ?,?");
		List<Object> param = new ArrayList<Object>();
		param.add(0);
		param.add(6);
		up.add(sql.toString(),param.toArray());
		
		return AR.find(up).list(UserForm.class);
	}
	
	/* (non-Javadoc)
	 * @see com.bbs.service.TopicService#updateNidByTid(java.lang.Long, java.lang.Long)
	 */
	@Override
	public void updateNidByTid(Long nid,Long tid){
		if(nid!=null && tid!=null){
			Topic topic = AR.findById(Topic.class, tid);
			
			//更新节点下的帖子数
			nodeService.updateCount(topic.getNid(), Types.topics.toString(), -1);
			nodeService.updateCount(nid, Types.topics.toString(), +1);
			AR.update("update t_topic set nid = ? where tid = ?", nid, tid).executeUpdate();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.bbs.service.TopicService#updateAndDelComment(java.lang.Long, java.lang.Long)
	 */
	@Override
	public boolean updateAndDelComment(Long tid,Long cid) {
		if(null != cid){
			commentService.update(cid, 2);//1:正常 2:删除
			topicCountService.update(Types.comments.toString(), tid, -1);
			noticeService.deleteFromComment(cid);
			return true;
		}
		return false;
	}
}