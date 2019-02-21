package com.bbs.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.bbs.Actions;
import com.bbs.Constant;
import com.bbs.Types;
import com.bbs.kit.DateKit;
import com.bbs.kit.ResponseKit;
import com.bbs.kit.SessionKit;
import com.bbs.model.Comment;
import com.bbs.model.LoginUser;
import com.bbs.model.Node;
import com.bbs.model.NodeUser;
import com.bbs.model.Topic;
import com.bbs.service.CommentService;
import com.bbs.service.FavoriteService;
import com.bbs.service.NodeService;
import com.bbs.service.NodeUserService;
import com.bbs.service.PointLogService;
import com.bbs.service.SettingsService;
import com.bbs.service.TopicCountService;
import com.bbs.service.TopicService;
import com.bbs.service.UserService;
import com.bbs.service.UserlogService;
import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;
import com.blade.route.annotation.Path;
import com.blade.route.annotation.PathVariable;
import com.blade.route.annotation.Route;
import com.blade.view.ModelAndView;
import com.blade.web.http.HttpMethod;
import com.blade.web.http.Request;
import com.blade.web.http.Response;

import blade.kit.StringKit;

@Path("/")
public class TopicController extends BaseController {
	
	private static Logger info = Logger.getLogger(TopicController.class);

	@Inject
	private TopicService topicService;
	
	@Inject
	private TopicCountService topicCountService;
	
	@Inject
	private NodeService nodeService;
	
	@Inject
	private CommentService commentService;
	
	@Inject
	private SettingsService settingsService;
	
	@Inject
	private FavoriteService favoriteService;
	
	@Inject
	private UserService userService;
	
	@Inject
	private UserlogService userlogService;
	
	@Inject
	private TopicCountService typeCountService;
	
	@Inject
	private NodeUserService nodeUserService;
	
	@Inject
	private PointLogService pointLogService;
	
	/**
	 * 发布帖子页面
	 */
	@Route(value = "/topic/add", method = HttpMethod.GET)
	public ModelAndView show_add_topic(Request request, Response response){
		LoginUser user = SessionKit.getLoginUser();
		if(null == user){
			SessionKit.setCookie(response, Constant.JC_REFERRER_COOKIE, request.uri());
			//request.session().attribute(Constant.JC_REFERRER_COOKIE, request.uri());//保存session登录后跳转回来
			ResponseKit.go(response, "/signin/quick");
			return null;
		}
		this.putData(request);
		Long pid = request.queryAsLong("pid");
		request.attribute("pid", pid);
		return this.getView("topic_add");
	}
	
	/**
	 * 编辑帖子页面
	 */
	@Route(value = "/topic/edit/:tid", method = HttpMethod.GET)
	public ModelAndView show_ediot_topic(@PathVariable("tid") Long tid, Request request, Response response){
		
		LoginUser user = SessionKit.getLoginUser();
		if(null == user){
			ResponseKit.go(response, "/");
			return null;
		}
		
		Topic topic = topicService.getTopic(tid);
		if(null == topic){
			request.attribute(this.ERROR, "不存在该帖子");
			return this.getView("info");
		}
		
		if(!topic.getUid().equals(user.getUid())){
			request.attribute(this.ERROR, "您无权限编辑该帖");
			return this.getView("info");
		}
		
		//超过1800秒
		if((DateKit.getCurrentUnixTime() - topic.getCreate_time()) > 1800 ){
			request.attribute(this.ERROR, "发帖已经超过30分钟，不允许编辑");
			return this.getView("info");
		}
		
		this.putData(request);
		request.attribute("topic", topic);
		
		return this.getView("topic_edit");
	}
	
	/**
	 * 编辑帖子操作
	 */
	@Route(value = "/topic/edit", method = HttpMethod.POST)
	public void edit_topic(Request request, Response response){
		Long tid = request.queryAsLong("tid");
		String title = request.query("title");
		String content = request.query("content");
		Long nid = request.queryAsLong("nid");
		
		Long attach_type = request.queryAsLong("attach_type");
		String attach_content = request.query("attach_content");
		
		LoginUser user = SessionKit.getLoginUser();
		if(null == user){
			this.nosignin(response);
			return;
		}
		
		if(null == tid){
			this.error(response, "不存在该帖子");
			return;
		}
		//不存在该帖子
		Topic topic = topicService.getTopic(tid);
		if(null == topic){
			this.error(response, "不存在该帖子");
			return;
		}
		//无权限操作
		if(!topic.getUid().equals(user.getUid())){
			this.error(response, "无权限操作该帖");
			return;
		}
		//超过30分钟
		if((DateKit.getCurrentUnixTime() - topic.getCreate_time()) > 1800 ){
			this.error(response, "发帖已经超过30分钟，不允许编辑");
			return;
		}
		if(null == nid){
			this.error(response, "网络异常请稍候再试");
			return;
		}
		if(StringKit.isBlank(title)){
			this.error(response, "请输入帖子标题");
			return;
		}
		if(StringKit.isBlank(content)){
			this.error(response, "请输入帖子内容");
			return;
		}
		if(title.length() < 4 || title.length() > 50){
			this.error(response, "标题长度在4-50个字符哦");
			return;
		}
		if(content.length() < 5){
			this.error(response, "您真是一字值千金啊。");
			return;
		}
		if(content.length() > 15000){
			this.error(response, "内容太长了，试试缩短一下内容呢");
			return;
		}
		if(attach_type!=null && StringUtils.isBlank(attach_content)){
			this.error(response, "请输入帖子附加内容！");
			return;
		}
		if(StringUtils.isNotBlank(attach_content) && attach_content.length() > 2000){
			this.error(response, "附加内容太长了，试试缩短一下内容呢");
			return;
		}
		
		Long last_time = topicService.getLastUpdateTime(user.getUid());
		if(null != last_time && (DateKit.getCurrentUnixTime() - last_time) < 10 ){
			this.error(response, "您操作频率太快，过一会儿操作吧！");
			return;
		}
		
		try {
			//编辑帖子
			topicService.update(tid, nid, title, content,attach_type,attach_content);
			userlogService.save(user.getUid(), Actions.UPDATE_TOPIC, content);
			
			this.success(response, tid);
		} catch (Exception e) {
			e.printStackTrace();
			this.error(response, "帖子编辑失败");
			return;
		}
	}
	
	/**
	 * 发布帖子操作
	 */
	@Route(value = "/topic/add", method = HttpMethod.POST)
	public void add_topic(Request request, Response response){
		
		String title = request.query("title");
		String content = request.query("content");
		Long nid = request.queryAsLong("nid");
		
		Long attach_type = request.queryAsLong("attach_type");
		String attach_content = request.query("attach_content");
		
		LoginUser user = SessionKit.getLoginUser();
		
		if(null == user){
			this.nosignin(response);
			return;
		}
		if(StringKit.isBlank(title)){
			this.error(response, "请输入帖子标题");
			return;
		}
		if(null == nid){
			this.error(response, "请选择帖子板块");
			return;
		}
		if(StringKit.isBlank(content)){
			this.error(response, "请输入帖子内容");
			return;
		}
		if(title.length() < 4 || title.length() > 50){
			this.error(response, "标题长度在4-50个字符哦");
			return;
		}
		if(content.length() < 5){
			this.error(response, "您真是一字值千金啊。");
			return;
		}
		if(content.length() > 15000){
			this.error(response, "内容太长了，试试缩短一下内容呢");
			return;
		}
		if(attach_type!=null && StringUtils.isBlank(attach_content)){
			this.error(response, "请输入帖子附加内容！");
			return;
		}
		if(StringUtils.isNotBlank(attach_content) && attach_content.length() > 2000){
			this.error(response, "附加内容太长了，试试缩短一下内容呢");
			return;
		}
		
		Long last_time = topicService.getLastCreateTime(user.getUid());
		if(null != last_time && (DateKit.getCurrentUnixTime() - last_time) < 10 ){
			this.error(response, "您操作频率太快，过一会儿操作吧！");
			return;
		}
		
		//发布帖子
		try {
			Long tid = topicService.save(user.getUid(), nid, title, content, 0,attach_type,attach_content);
			if(null != tid){
				Constant.SYS_INFO = settingsService.getSystemInfo();
				Constant.VIEW_CONTEXT.set("sys_info", Constant.SYS_INFO);
				
				userlogService.save(user.getUid(), Actions.ADD_TOPIC, content);
				
				pointLogService.saveFATIE(user.getUid(), tid);//发帖（获取积分）
				this.success(response, tid);
			} else {
				this.error(response, "帖子发布失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.error(response, "帖子发布失败");
		}
		return;
	}
	
	private void putData(Request request){
		List<Map<String, Object>> nodes = nodeService.getNodeList();
		request.attribute("nodes", nodes);
	}
	
	/**
	 * 帖子详情页面
	 */
	@Route(value = "/topic/:tid", method = HttpMethod.GET)
	public ModelAndView show_topic(@PathVariable("tid") Long tid, Request request, Response response){
		LoginUser user = SessionKit.getLoginUser();
		Long uid = null;
		if(null != user){
			uid = user.getUid();
		} else {
			SessionKit.setCookie(response, Constant.JC_REFERRER_COOKIE, request.uri());
			//request.session().attribute(Constant.JC_REFERRER_COOKIE, request.uri());//保存session登录后跳转回来
		}
		
		Topic topic = topicService.getTopic(tid);
		if(null == topic){
			ResponseKit.go(response, "/");
			return null;
		}
		
		this.putDetail(request, response, uid, topic);
		
		//最热门的8个节点（新添加）
		QueryParam qp = QueryParam.me();
		qp.eq("is_del", 0).notEq("pid", 0).orderby("topics desc");
		List<Node> nodes = nodeService.getNodeList(qp);
		request.attribute("all_nodes", nodes);
		
		// 刷新浏览数
		typeCountService.update(Types.views.toString(), tid, 1);
		return this.getView("topic_detail");
	}
	
	private void putDetail(Request request, Response response, Long uid, Topic topic){
		Integer page = request.queryAsInt("p");
		if(null == page || page < 1){
			page = 1;
		}
		
		//帖子详情
		Map<String, Object> topicMap = topicService.getTopicMap(topic, true);
		request.attribute("topic", topicMap);
		
		//当前用户是否评论过
		List<Comment> comments = commentService.getCommentByTidAndUid(topic.getTid(), uid);
		if(comments!=null && comments.size()>0)topicMap.put("uid_comment", true);
		
		//是否收藏
		boolean is_favorite = favoriteService.isFavorite(Types.topic.toString(), uid, topic.getTid());
		request.attribute("is_favorite", is_favorite);
		
		//是否点赞
		boolean is_love = favoriteService.isFavorite(Types.love.toString(), uid, topic.getTid());
		request.attribute("is_love", is_love);
		
		//获取当前用户是否是版主
		List<NodeUser> nus = nodeUserService.getListByNidUid(topic.getNid(),uid);
		if(nus!=null && nus.size()>0)request.attribute("is_nu", nus);
		
		QueryParam cp = QueryParam.me();
		cp.eq("status", 1);
		cp.eq("tid", topic.getTid()).orderby("cid asc").page(page, 20);
		Page<Map<String, Object>> commentPage = commentService.getPageListMap(cp);
		request.attribute("commentPage", commentPage);
	}
	
	/**
	 * 评论帖子操作
	 */
	@Route(value = "/comment/add", method = HttpMethod.POST)
	public void add_comment(Request request, Response response){
		LoginUser user = SessionKit.getLoginUser();
		if(null == user){
			this.nosignin(response);
			return;
		}
		
		Long uid = user.getUid();
		
		String content = request.query("content");
		Long tid = request.queryAsLong("tid");
		Topic topic = topicService.getTopic(tid);
		if(null == topic){
			ResponseKit.go(response, "/");
			return;
		}
		info.info(content);
		
		if(null == tid || StringKit.isBlank(content)){
			this.error(response, "大人，有些东西木有填哎！");
			return;
		}
		
		if(content.length() > 20000){
			this.error(response, "内容太长了，试试少吐点口水。");
			return;
		}
		
		Long last_time = topicService.getLastUpdateTime(user.getUid());
		if(null != last_time && (DateKit.getCurrentUnixTime() - last_time) < 10 ){
			this.error(response, "您操作频率太快，过一会儿操作吧！");
			return;
		}
		
		// 评论帖子
		try {
			String ua = request.userAgent();
			
			boolean flag = topicService.comment(uid, topic.getUid(), tid, content, ua);
			if(flag){
				Constant.SYS_INFO = settingsService.getSystemInfo();
				Constant.VIEW_CONTEXT.set("sys_info", Constant.SYS_INFO);
				
				userlogService.save(user.getUid(), Actions.ADD_COMMENT, content);
				
				this.success(response, "");
			} else {
				this.error(response, "帖子评论失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.error(response, "帖子评论失败");
		}
	}
	
	/**
	 * 加精和取消加精
	 */
	@Route(value = "/essence", method = HttpMethod.POST)
	public void essence(Request request, Response response){
		Long tid = request.queryAsLong("tid");
		if(null == tid || tid == 0){
			return;
		}
		LoginUser user = SessionKit.getLoginUser();
		if(null == user){
			this.nosignin(response);
			return;
		}
		Topic topic = topicService.getTopic(tid);
		if(null == topic){
			this.error(response, "不存在该帖子");
			return;
		}
		List<NodeUser> nu = nodeUserService.getListByNidUid(topic.getNid(),user.getUid());
		
		if(user.getRole_id() > 3 && (nu==null || nu.size()==0)){
			this.error(response, "您无权限操作");
			return;
		}
		try {
			Integer count = topic.getIs_essence() == 1 ? 0 : 1;
			topicService.essence(tid, count);
			userlogService.save(user.getUid(), Actions.ESSENCE, tid+":" + count);
			
			pointLogService.saveJINGHUA(topic.getUid(), user.getUid(), tid);//精华帖（获取积分）
			this.success(response, tid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 帖子下沉
	 */
	@Route(value = "/sink", method = HttpMethod.POST)
	public void sink(Request request, Response response){
		
		LoginUser user = SessionKit.getLoginUser();
		if(null == user){
			this.nosignin(response);
			return;
		}
		
		Long tid = request.queryAsLong("tid");
		if(null == tid || tid == 0){
			return;
		}
		
		try {
			boolean isFavorite = favoriteService.isFavorite(Types.sinks.toString(), user.getUid(), tid);
			if(!isFavorite){
				favoriteService.update(Types.sinks.toString(), user.getUid(), tid);
				topicCountService.update(Types.sinks.toString(), tid, 1);
				topicService.updateWeight(tid);
				userlogService.save(user.getUid(), Actions.SINK, tid+"");
			}
			this.success(response, tid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除帖子
	 */
	@Route(value = "/delete", method = HttpMethod.POST)
	public void delete(Request request, Response response){
		LoginUser user = SessionKit.getLoginUser();
		if(null == user){
			this.nosignin(response);
			return;
		}
		Long tid = request.queryAsLong("tid");
		if(null == tid || tid == 0){
			return;
		}
		Topic topic = topicService.getTopic(tid);
		if(null == topic){
			this.error(response, "不存在该帖子");
			return;
		}
		List<NodeUser> nu = nodeUserService.getListByNidUid(topic.getNid(),user.getUid());
		if(user.getRole_id() > 2 && (nu==null || nu.size()==0)){
			return;
		}
		try {
			topicService.delete(tid);
			pointLogService.saveJINGHUA(topic.getUid(), user.getUid(), tid);
			this.success(response, tid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 精华帖页面
	 */
	@Route(value = "/essence", method = HttpMethod.GET)
	public ModelAndView essencePage(Request request, Response response){
		
		// 帖子
		QueryParam tp = QueryParam.me();
		Integer page = request.queryAsInt("p");
		
		if(null == page || page < 1){
			page = 1;
		}
		
		tp.eq("status", 1).eq("is_essence", 1).orderby("create_time desc, update_time desc").page(page, 15);
		Page<Map<String, Object>> topicPage = topicService.getPageList(tp);
		request.attribute("topicPage", topicPage);
		
		return this.getView("essence");
	}
	
	/**
	 * 帖子更新板块
	 * @author WKX
	 */
	@Route(value = "/topic/change", method = HttpMethod.POST)
	public void change(Request request, Response response){
		LoginUser user = SessionKit.getLoginUser();
		if(null == user){
			this.nosignin(response);
			return;
		}
		Long tid = request.queryAsLong("tid");
		Long nid = request.queryAsLong("nodeId");
		if(null == tid || nid == null){
			this.error(response, "数据异常！");
			return;
		}
		try {
			topicService.updateNidByTid(nid, tid);
			this.success(response, tid);
		} catch (Exception e) {
			e.printStackTrace();
			this.error(response, "数据异常！");
			return;
		}
	}
	
	/**
	 * 删除评论（含相关通知）
	 * @author WKX
	 * @param request
	 * @param response
	 * @since 2017年6月20日 下午4:01:46
	 */
	@Route(value = "/comment/del", method = HttpMethod.POST)
	public void delComment(Request request, Response response){
		LoginUser user = SessionKit.getLoginUser();
		if(null == user){
			this.nosignin(response);
			return;
		}
		Long tid = request.queryAsLong("tid");
		Long cid = request.queryAsLong("cid");
		if(tid==null || cid==null){
			this.error(response, "数据异常！");
			return;
		}
		try {
			topicService.updateAndDelComment(tid,cid);
			this.success(response, cid);
		} catch (Exception e) {
			e.printStackTrace();
			this.error(response, "数据异常！");
			return;
		}
	}
}