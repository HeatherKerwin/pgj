package com.bbs.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;

import com.bbs.Actions;
import com.bbs.Constant;
import com.bbs.Types;
import com.bbs.form.UserForm;
import com.bbs.kit.HttpUtil;
import com.bbs.kit.MyStackKit;
import com.bbs.kit.NameKit;
import com.bbs.kit.ResponseKit;
import com.bbs.kit.Result;
import com.bbs.kit.SessionKit;
import com.bbs.kit.Utils;
import com.bbs.model.CheckIn;
import com.bbs.model.LoginUser;
import com.bbs.model.Node;
import com.bbs.model.PointLog.Cosname;
import com.bbs.model.Ticket;
import com.bbs.model.User;
import com.bbs.model.Userinfo;
import com.bbs.service.BannerService;
import com.bbs.service.CheckInService;
import com.bbs.service.FavoriteService;
import com.bbs.service.NodeService;
import com.bbs.service.NodeUserService;
import com.bbs.service.NoticeService;
import com.bbs.service.TicketService;
import com.bbs.service.TopicService;
import com.bbs.service.UserService;
import com.bbs.service.UserinfoService;
import com.bbs.service.UserlogService;
import com.blade.Blade;
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
import com.blade.web.multipart.FileItem;
import com.redfin.sitemapgenerator.ChangeFreq;
import com.redfin.sitemapgenerator.WebSitemapGenerator;
import com.redfin.sitemapgenerator.WebSitemapUrl;

import blade.kit.DateKit;
import blade.kit.FileKit;
import blade.kit.PatternKit;
import blade.kit.StringKit;
import blade.kit.json.JSONKit;
import blade.kit.json.JSONObject;
import blade.kit.json.JSONValue;
import net.sf.json.JSONArray;

@Path("/")
public class IndexController extends BaseController {

	@Inject
	private TopicService topicService;
	
	@Inject
	private NodeService nodeService;
	
	@Inject
	private NoticeService noticeService;
	
	@Inject
	private FavoriteService favoriteService;
	
	@Inject
	private CheckInService checkInService;
	
	@Inject
	private NodeUserService nodeUserService;
	
	@Inject
	private UserService userService;
	
	@Inject
	private UserlogService userlogService;
	
	@Inject
	private TicketService ticketService;
	
	@Inject
	private UserinfoService userinfoService;
	
	@Inject
	private BannerService bannerService;
	
	//加密拼接规则
	public static String qian = "MID";
	public static String zhong = "MOBILE";
	public static String hou = "50965066";
	
	/**
	 * 首页热门（已弃用）
	 */
	@Route(value = "/index", method = HttpMethod.GET)
	public ModelAndView show_home(Request request, Response response){
		this.putData(request);
		
		//帖子
		String tab = request.query("tab");
		Integer page = request.queryAsInt("p");
		Long nid = null;
		
		if(StringKit.isNotBlank(tab)){
			QueryParam np = QueryParam.me();
			np.eq("is_del", 0).eq("slug", tab);
			Node node = nodeService.getNode(np);
			if(null != node){
				nid = node.getNid();
				request.attribute("tab", tab);
				request.attribute("node_name", node.getTitle());
			}
		}
		
		Page<Map<String, Object>> topicPage = topicService.getHotTopic(nid, page, 20);
		request.attribute("topicPage", topicPage);
		
		//最热帖子
		List<Map<String, Object>> hot_topics = topicService.getHotTopic(null, 1, 5).getResults();
		request.attribute("hot_topics", hot_topics);
		
		//最热门的8个节点
		QueryParam np = QueryParam.me();
		np.eq("is_del", 0).notEq("pid", 0).orderby("topics desc").add("limit 8");
		List<Node> hot_nodes = nodeService.getNodeList(np);
		request.attribute("hot_nodes", hot_nodes);
		
		request.attribute("top_user", getTopUser());//首页显示会员排名
		return this.getView("home");
	}
	
	/**
	 * 新首页（含各版块数据等）
	 * @author WKX
	 * @param request
	 * @param response
	 * 注：附加自动登录参数 mid 用户主键、mobile手机号
	 */
	@Route(value = "/", method = HttpMethod.GET)
	public ModelAndView index(Request request, Response response){
		//step.1 记录首页访问
		LoginUser user = SessionKit.getLoginUser();
		Long uid = null;
		String content = null;
		if(user!=null){
			uid = user.getUid();
			content = user.getUser_name();
		}else{
			content = "未知用户访问主页！";
		}
		userlogService.saveVisit(uid, Actions.VISIT_INDEX, content);
		
		//step.2 初始化相关数据
		this.putData(request);
		
		QueryParam np = QueryParam.me();
		np.eq("is_del", 0).notEq("pid", 0);
		List<Node> nodes = nodeService.getNodeList(np);
		if(nodes!=null){
			for(Node node:nodes){
				Map<String, Object> nodeMap = nodeService.getNodeDetail(null, node.getNid());
				if(nodeMap!=null && nodeMap.get("node_slug")!=null && "talk".equals(nodeMap.get("node_slug").toString())){
					request.attribute(node.getSlug(), nodeMap);//节点信息
					Page<Map<String, Object>> topicPage = topicService.getHotTopic(node.getNid(), 1, 10);
					if(topicPage!=null)request.attribute("node_"+node.getSlug(), topicPage.getResults());//节点帖子
				}else{
					request.attribute(node.getSlug(), nodeMap);//节点信息
					Page<Map<String, Object>> topicPage = topicService.getHotTopic(node.getNid(), 1, 5);
					if(topicPage!=null)request.attribute("node_"+node.getSlug(), topicPage.getResults());//节点帖子
				}
			}
		}
		
		//精华帖子
		QueryParam tp = QueryParam.me();
		tp.eq("status", 1).eq("is_essence", 1).orderby("essence_time desc,create_time desc, update_time desc").page(1,4);
		Page<Map<String, Object>> topicPage = topicService.getPageList(tp);
		if(topicPage!=null)request.attribute("essence", topicPage.getResults());
		
		//首页显示会员排名
		request.attribute("top_user", getTopUser());
		
		String mid = request.query("mid");
		if(StringUtils.isNotBlank(mid)){
			return this.getMemberById(request, response);
		}else{
			return this.getView("index");
		}
	}
	
	/**
	 * 最新
	 */
	@Route(value = "/recent", method = HttpMethod.GET)
	public ModelAndView show_recent(Request request, Response response){
		
		this.putData(request);
		
		// 帖子
		String tab = request.query("tab");
		Integer page = request.queryAsInt("p");
		Long nid = null;
		
		if(StringKit.isNotBlank(tab)){
			QueryParam np = QueryParam.me();
			np.eq("is_del", 0).eq("slug", tab);
			Node node = nodeService.getNode(np);
			if(null != node){
				nid = node.getNid();
				request.attribute("tab", tab);
				request.attribute("node_name", node.getTitle());
			}
		}
		
		Page<Map<String, Object>> topicPage = topicService.getRecentTopic(nid, page, 15);
		request.attribute("topicPage", topicPage);
				
		// 最热帖子
		List<Map<String, Object>> hot_topics = topicService.getHotTopic(null, 1, 10).getResults();
		request.attribute("hot_topics", hot_topics);
		
		// 最热门的10个节点
		QueryParam np = QueryParam.me();
		np.eq("is_del", 0).notEq("pid", 0).orderby("topics desc").add("limit 8");
		List<Node> hot_nodes = nodeService.getNodeList(np);
		request.attribute("hot_nodes", hot_nodes);
		
		return this.getView("recent");
	}
	
	/**
	 * 首页填充数据
	 * @author WKX
	 * @param request
	 * @since 2016年11月8日 上午11:01:42
	 */
	private void putData(Request request){
		//初始化获取（内部用户-防止主页用户列表太少）
		Stack<LoginUser> d = MyStackKit.user_stack;
		if(d==null || d.size()<=3){
			List<LoginUser> temp = userService.getUserByNoMemberId();
			if(temp!=null && temp.size()>0){
				for(LoginUser lu:temp){
					MyStackKit.push(lu);//放入栈
				}
			}
		}
		
		//读取节点列表
		List<Map<String, Object>> nodes = nodeService.getNodeList();
		request.attribute("nodes", nodes);
		
		//签到
		Map<String,Object>user_info = new HashMap<String, Object>();
		user_info.put("checkIn", false);
		LoginUser loginUser = SessionKit.getLoginUser();
		if(loginUser!=null){
			CheckIn ci = checkInService.getByUidAndDay(loginUser.getUid(), com.bbs.kit.DateKit.formart(new Date(),"yyyy-MM-dd"));
			if(ci!=null){
				user_info.put("checkIn", true);
			}
			MyStackKit.push(loginUser);//放入栈
			
			//积分（展示积分）
			Long score = userService.getScoreByUid(loginUser.getUid());
			user_info.put("score", score);
			
			//奖券
			List<Ticket> tickets = ticketService.getByUidState(loginUser.getUid(), 0);//状态（0未使用、1已使用、2无效）
			if(tickets!=null)user_info.put("tickets", tickets.size());
		}
		
		//获取近期登录用户（首页滚动）
		request.attribute("user_stack", MyStackKit.getStack());
		Constant.VIEW_CONTEXT.set("user_info", user_info);
		
//		//banner设置
//		QueryParam np1 = QueryParam.me();
//		np1.eq("is_del", 0).orderby("sort asc");
//		List<Banner> bannerList=bannerService.getBannerList(np1);
//		for(Banner banner:bannerList){
//			banner.setPath(Utils.getPhoto(banner.getPath(),""));
//		}
//		request.attribute("banners", bannerList);
	}
	
	/**
	 * 节点主题页
	 */
	@Route(value = "/go/:slug", method = HttpMethod.GET)
	public ModelAndView go(@PathVariable("slug") String slug, Request request, Response response){
		LoginUser loginUser = SessionKit.getLoginUser();
		QueryParam np = QueryParam.me();
		np.eq("is_del", 0).eq("slug", slug);
		Node node = nodeService.getNode(np);
		if(null == node){
			ResponseKit.go(response, "/");
			return null;
		}
		
		if(null == loginUser){
			SessionKit.setCookie(response, Constant.JC_REFERRER_COOKIE, request.url());
		} else {
			// 查询是否收藏
			boolean is_favorite = favoriteService.isFavorite(Types.node.toString(), loginUser.getUid(), node.getNid());
			request.attribute("is_favorite", is_favorite);
		}
		
		Integer page = request.queryAsInt("page");
		
		Page<Map<String, Object>> topicPage = topicService.getRecentTopic(node.getNid(), page, 15);
		request.attribute("topicPage", topicPage);
		
		Map<String, Object> nodeMap = nodeService.getNodeDetail(null, node.getNid());
		request.attribute("node", nodeMap);
		
		//查询该板块版主（新添加）
		List<UserForm> nus = nodeUserService.getUserByNid(node.getNid());
		if(nus!=null && nus.size()>0)request.attribute("has_nus", nus);
		
		//最热门的8个节点（新添加）
		QueryParam qp = QueryParam.me();
		qp.eq("is_del", 0).notEq("pid", 0).orderby("topics desc").add("limit 8");
		List<Node> hot_nodes = nodeService.getNodeList(qp);
		request.attribute("hot_nodes", hot_nodes);
		
		return this.getView("node_detail");
	}
	
	/**
	 * 上传头像
	 */
	@Route(value = "/uploadimg", method = HttpMethod.POST)
	public void uploadimg(Request request, Response response){
		LoginUser user = SessionKit.getLoginUser();
		if(null == user){
			return;
		}
		FileItem[] fileItems = request.files();
		if(null != fileItems && fileItems.length > 0){
			FileItem fileItem = fileItems[0];
			
			String suffix = FileKit.getExtension(fileItem.getFileName());
			if(StringKit.isNotBlank(suffix)){
				suffix = "." + suffix;
			}
			if(!PatternKit.isImage(suffix)){
				return;
			}
			
			String savePath = Blade.me().config().get("img.uploadPath") + Constant.UPLOAD_FOLDER;
			String showUrl = Constant.UPLOAD_FOLDER;
			
			//创建文件夹
			File uploadDir = new File(savePath);
			if(!uploadDir.isDirectory()){
				uploadDir.mkdirs();			
			}
			savePath += "image" + File.separator;
			showUrl += "image" + File.separator;
			File saveDirFile = new File(savePath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String ymd = sdf.format(new Date());
			savePath += ymd + File.separator;
			showUrl += ymd + File.separator;
			File dirFile = new File(savePath);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			
			String saveName = DateKit.dateFormat(new Date(), "yyyyMMddHHmmssSSS")  + "_" + StringKit.getRandomChar(10) + suffix;
			File file = new File(savePath + saveName);
			try {
				Utils.copyFileUsingFileChannels(fileItem.getFile(), file);
				
				saveName = showUrl + saveName;
				showUrl = Blade.me().config().get("img.preimgUrl") + saveName;
				
				JSONObject res = new JSONObject();
				res.put("status", 200);
				res.put("savekey", saveName);
				res.put("savepath", saveName);
				res.put("url", showUrl);
				
				response.json(res.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * markdown页面
	 */
	@Route(value = "/markdown", method = HttpMethod.GET)
	public ModelAndView markdown(Request request, Response response){
		return this.getView("markdown");
	}
	
	/**
	 * about页面
	 */
	@Route(value = "/about", method = HttpMethod.GET)
	public ModelAndView about(Request request, Response response){
		return this.getView("about");
	}
	
	/**
	 * faq页面
	 */
	@Route(value = "/faq", method = HttpMethod.GET)
	public ModelAndView faq(Request request, Response response){
		String qa = request.query("aaa");
		System.out.println(qa);
		return this.getView("faq");
	}
	
	/**
	 * donate页面
	 */
	@Route(value = "/donate", method = HttpMethod.GET)
	public ModelAndView donate(Request request, Response response){
		return this.getView("donate");
	}
	
	/**
	 * robots.txt
	 */
	@Route(value = "/robots.txt", method = HttpMethod.GET)
	public ModelAndView robots(Request request, Response response){
		return this.getView("robots");
	}
	
	/**
	 * sitemap页面
	 */
	@Route(value = "/sitemap.xml", method = HttpMethod.GET)
	public void sitemap(Request request, Response response){
		try {
			WebSitemapGenerator wsg = new WebSitemapGenerator(Constant.SITE_URL);
			wsg.addUrl(new WebSitemapUrl.Options(Constant.SITE_URL).lastMod(new Date()).priority(0.8).changeFreq(ChangeFreq.ALWAYS).build());
			wsg.addUrl(new WebSitemapUrl.Options(Constant.SITE_URL + "/markdown").lastMod(new Date()).priority(0.8).changeFreq(ChangeFreq.MONTHLY).build());
			wsg.addUrl(new WebSitemapUrl.Options(Constant.SITE_URL + "/essence").lastMod(new Date()).priority(0.8).changeFreq(ChangeFreq.MONTHLY).build());
			wsg.addUrl(new WebSitemapUrl.Options(Constant.SITE_URL + "/signup").lastMod(new Date()).priority(0.8).changeFreq(ChangeFreq.MONTHLY).build());
			wsg.addUrl(new WebSitemapUrl.Options(Constant.SITE_URL + "/signin").lastMod(new Date()).priority(0.8).changeFreq(ChangeFreq.MONTHLY).build());
			wsg.addUrl(new WebSitemapUrl.Options(Constant.SITE_URL + "/faq").lastMod(new Date()).priority(0.8).changeFreq(ChangeFreq.MONTHLY).build());
			wsg.addUrl(new WebSitemapUrl.Options(Constant.SITE_URL + "/about").lastMod(new Date()).priority(0.8).changeFreq(ChangeFreq.MONTHLY).build());
			wsg.addUrl(new WebSitemapUrl.Options(Constant.SITE_URL + "/donate").lastMod(new Date()).priority(0.8).changeFreq(ChangeFreq.MONTHLY).build());
			
			List<Long> tids = topicService.topicIds();
			for(Long tid : tids){
				WebSitemapUrl url = new WebSitemapUrl.Options(Constant.SITE_URL + "/topic/" + tid).lastMod(new Date()).priority(0.8).changeFreq(ChangeFreq.DAILY).build();
				wsg.addUrl(url);
			}
			
			response.xml(wsg.writeAsStrings().get(0));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 签到
	 * @author WKX
	 * @param request
	 * @param response
	 * @since 2016年10月31日 下午3:58:04
	 */
	@Route(value = "/checkin", method = HttpMethod.POST)
	public void checkin(Request request, Response response){
		LoginUser user = SessionKit.getLoginUser();
		if(null == user){
			this.nosignin(response);
			return;
		}
		CheckIn ci = new CheckIn();
		ci.setUid(user.getUid());
		ci.setDay(com.bbs.kit.DateKit.formart(new Date(),"yyyy-MM-dd"));
		try {
			CheckIn temp = checkInService.getByUidAndDay(user.getUid(), com.bbs.kit.DateKit.formart(new Date(),"yyyy-MM-dd"));
			if(temp==null){
				checkInService.save(ci);
				this.putData(request);
			}
			this.success(response, ci.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 帖子搜索
	 * @author WKX
	 * @param q 关键字
	 * @param request
	 * @param response
	 * @since 2016年11月4日 上午10:21:51
	 */
	@Route(value = "/search/:q", method = HttpMethod.POST)
	public ModelAndView doSearch(@PathVariable("q")String q, Request request, Response response){
		Integer p = request.queryAsInt("p");
		
		Page<Map<String, Object>> topicPage = topicService.getSearchTopic(q, p, 15);
		if(topicPage!=null && topicPage.getResults()!=null){
			for(Map<String, Object> map:topicPage.getResults()){//标示关键字
				if(map!=null && map.get("title")!=null){
					String temp = map.get("title").toString();
					map.put("title", temp.replaceAll(q, "<font style='color:red'>"+q+"</font>"));
				}
			}
		}
		request.attribute("topicPage", topicPage);
		request.attribute("q", q);
		return this.getView("search");
	}
	
	/**
	 * 搜索帖子（之前的二次搜索有问题）
	 * @author WKX
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException 
	 * @since 2016年11月21日 下午5:29:16
	 */
	@Route(value = "/search", method = HttpMethod.POST)
	public ModelAndView doSearch(Request request, Response response) throws UnsupportedEncodingException{
		Integer p = request.queryAsInt("p");
		String q = request.query("q");
		q = java.net.URLDecoder.decode(q,"utf-8");
		
		Page<Map<String, Object>> topicPage = topicService.getSearchTopic(q, p, 15);
		if(topicPage!=null && topicPage.getResults()!=null){
			for(Map<String, Object> map:topicPage.getResults()){//标示关键字
				if(map!=null && map.get("title")!=null){
					String temp = map.get("title").toString();
					map.put("title", temp.replaceAll(q, "<font style='color:red'>"+q+"</font>"));
				}
			}
		}
		request.attribute("topicPage", topicPage);
		request.attribute("q", q);
		return this.getView("search");
	}
	
	/**
	 * 获取会员排名（积分前多少名）
	 * @author WKX
	 * @since 2016年11月4日 上午11:24:05
	 */
	private List<UserForm> getTopUser(){
		List<UserForm> list = new ArrayList<UserForm>();
		List<UserForm> temp = topicService.getTopUser();
		if(temp!=null){
			for(UserForm uf:temp){
				uf.setAvatar(Utils.getPhoto(uf.getAvatar(), "user"));
				uf.setCosname(Cosname.getCurrent(uf.getScore()));
				Userinfo info = userinfoService.getUserinfo(uf.getUid());
				if(info!=null){
					uf.setNick_name(info.getNick_name());
				}
				list.add(uf);
			}
		}
		return list;
	}
	
	/**
	 * 获取平台报价
	 * @author WKX
	 * @throws ParseException
	 * @throws IOException
	 * @since 2016年11月10日 下午1:58:29
	 */
	@Route(value = "/get/price", method = HttpMethod.POST)
	public void getPrice(Request request, Response response){
		JSONValue productMap = null;
		try {
			Result r = HttpUtil.sendPost(Constant.APP_URL+"/app/getAllImgAndPrice", null,null,"utf-8");
			if(r!=null){
				HttpEntity he = r.getHttpEntity();
				String res = EntityUtils.toString(he);
				JSONObject j = JSONKit.parseObject(res);
				productMap = j.get("historypriceList");
				this.success(response, productMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.error(response, "平台暂无报价");
	}
	
	/**
	 * 意见反馈页面
	 * @author WKX
	 * @param request
	 * @param response
	 * @since 2016年11月17日 下午8:14:56
	 */
	@Route(value = "/opinion", method = HttpMethod.GET)
	public ModelAndView opinion(Request request, Response response){
		
		return this.getView("opinion");
	}
	
	/**
	 * 意见反馈
	 * @author WKX
	 * @param request
	 * @param response
	 * @since 2016年11月10日 下午5:00:20
	 */
	@Route(value = "/opinion/do", method = HttpMethod.POST)
	public void opinionDo(Request request, Response response){
		LoginUser user = SessionKit.getLoginUser();
		try {
			Map<String,String> params = new HashMap<String, String>();
			params.put("messagecontent", request.query("content"));
			params.put("messagenumber", request.query("phone"));
			params.put("source", "BBS");
			if(user!=null && user.getUid()!=null){
				User u = userService.getUser(user.getUid());
				if(u!=null && u.getMember_id()!=null)params.put("memberid", u.getMember_id().toString());
			}
			Result r = HttpUtil.sendPost(Constant.APP_URL+"/app/message/", null,params,"utf-8");
			HttpEntity he = r.getHttpEntity();
			if(he==null)throw new Exception();
			
			String res = EntityUtils.toString(he);
			JSONArray j = JSONArray.fromString(res);
			if(j==null || j.length()<=0)throw new Exception();
			net.sf.json.JSONObject o = j.getJSONObject(0);
			
			if(o==null || o.get("response")==null || !"success".equals(o.get("response").toString()))throw new Exception();
			this.success(response, "反馈成功");
		} catch (Exception e) {
			e.printStackTrace();
			this.error(response, "反馈失败");
		}
	}
	
	/**
	 * 新手指引
	 * @author WKX
	 * @param request
	 * @param response
	 * @since 2016年11月17日 下午8:05:15
	 */
	@Route(value = "/guide", method = HttpMethod.GET)
	public ModelAndView guide(Request request, Response response){
		return this.getView("guide");
	}
	
	/**
	 * 根据用户主键获取奖券信息（需注意UserController）
	 * @author WKX
	 * @param request
	 * @param uid 用户主键
	 * @since 2017年1月18日 下午3:07:40
	 */
	private Map<String,Object> getTicketInfo(Long uid){
		Map<String,Object> result = new HashMap<String, Object>();
		List<Ticket> yaoqing = ticketService.getByUidSource(uid, 1);//来源（0注册、1邀请用户）
		String mobileUrl = Constant.MOBILE_URL;
		result.put("need_invitation", 3);//每邀请三个人获取一张抽奖券
		try {
			User u = userService.getUser(uid);
			Map<String,String> res = Utils.invitationFromApp(u.getMember_id());
			if(res!=null && "success".equals(res.get("response")) && res.get("data")!=null){
				String data = res.get("data");
				if(res.get("myInvitationCode")!=null)mobileUrl += ("?ic=" + res.get("myInvitationCode"));
				int can_get = 0;
				int has = 0;
				if(yaoqing!=null)has = yaoqing.size();
				if(StringKit.isNotBlank(data)){
					int count = Integer.valueOf(data);
					can_get = count/3;//每邀请三个人赠送一张奖券
					int need = 3 - count%3;
					if(need!=0 && need<3)result.put("need_invitation", need);//每邀请三个人获取一张抽奖券
				}
				int save = can_get-has;
				for(int i=0;i<save;i++){
					ticketService.save(uid,1);//来源（0注册、1邀请用户）
				}
			}
			result.put("code_invitation", mobileUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获取邀请用户的提醒
	 * @author WKX
	 * @param request
	 * @param response
	 * @since 2017年1月18日 下午3:28:57
	 */
	@Route(value = "/get/remind", method = HttpMethod.POST)
	public void getRemind(Request request, Response response){
		try {
			LoginUser user = SessionKit.getLoginUser();
			if(user!=null){
				this.success(response, getTicketInfo(user.getUid()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.error(response, "平台暂无报价");
	}
	
	/**
	 * 根据mid、mobile登录（经过首页时）需要解密
	 * @author WKX
	 * @param request
	 * @param response
	 */
	public ModelAndView getMemberById(Request request,Response response){
		String encodedText = request.query("mid");//加密数据
		try {
			String mid = getMid(encodedText);//用户主键
			String mobile = getMobile(encodedText);//手机号
			
			if(StringUtils.isBlank(mid) || StringUtils.isBlank(mobile))throw new Exception();//没有值
			User user = userService.getUserByMemberId(Long.valueOf(mid));
			if(user==null){
				String login_name = null;
				Map<String,String> params = new HashMap<String, String>();
				params.put("id", mid);
				params.put("type", "0");
				Result r = HttpUtil.sendPost(Constant.APP_URL+"/app/searchMyInfoById", null,params,"utf-8");
				HttpEntity he = r.getHttpEntity();
				String res = EntityUtils.toString(he);
				JSONObject j = JSONKit.parseObject(res);
				if(j.get("member")==null)throw new Exception();//差不多用户
					
				JSONObject member = j.get("member").asJSONObject();
				login_name = member.getString("mobile");
				
				if(StringUtils.isBlank(login_name))throw new Exception();//没有获取到手机号
				if(!(login_name.trim()).equals(mobile.trim()))throw new Exception();//没有获取到手机号
				
				if(StringUtils.isNotBlank(login_name)){
					String pwd = NameKit.end4Phone(login_name, null);//手机号后六位默认为密码
					user = userService.save(login_name, pwd, Long.valueOf(mid));//BBS端不存在则保存用户（无密码则默认密码为手机号）
					if(user==null)throw new Exception();
					userlogService.save(user.getUid(), Actions.SIGNUP, login_name);//记录注册日志
				}else{
					SessionKit.removeUser(request.session());
					SessionKit.removeCookie(response);
					SessionKit.setCookie(response, Constant.USER_IN_COOKIE,"");
					return this.getView("index");
				}
			}
			
			LoginUser loginUser = userService.getLoginUser(user, null);
			SessionKit.setLoginUser(request.session(), loginUser);
			
			userlogService.save(user.getUid(), Actions.SIGNIN, user.getLogin_name());
			SessionKit.setCookie(response, Constant.USER_IN_COOKIE, loginUser.getUid());
			return this.getView("index");
		} catch (Exception e) {
			e.printStackTrace();
			SessionKit.removeUser(request.session());
			SessionKit.removeCookie(response);
			SessionKit.setCookie(response, Constant.USER_IN_COOKIE,"");
			return this.getView("index");
		}
	}
	
	/**
	 * 获取随机数（字母和数字组合）
	 * @author WKX
	 * @param n 位数
	 */
	private static String getItemID(int n){
        String val = "";
        Random random = new Random();
        for ( int i = 0; i < n; i++ ){
            String str = random.nextInt( 2 ) % 2 == 0 ? "num" : "char";
            if ( "char".equalsIgnoreCase( str ) ){//产生字母
                int nextInt = random.nextInt( 2 ) % 2 == 0 ? 65 : 97;
                val += (char) ( nextInt + random.nextInt( 26 ) );
            }else if ( "num".equalsIgnoreCase( str ) ){
                val += String.valueOf( random.nextInt( 10 ) );
            }
        }
        return val;
    }
	
	/**
	 * 加密-解密（测试用）
	 * @author WKX
	 * @param mid 用户主键
	 * @param mobile 手机号
	 */
	public static void jiami(String mid,String mobile) throws Exception{
		String temp = qian + mid + zhong + mobile + hou;
		System.err.println(temp);
		
		final Base64 base64 = new Base64();
		
		final byte[] textByte = temp.getBytes("UTF-8");
		final String encodedText = getItemID(10) + base64.encodeToString(textByte);
		System.err.println(encodedText);
		
		String en = new String(base64.decode(encodedText.substring(10, encodedText.length())), "UTF-8");
		System.err.println(en);
		
		String mid_ = en.split(zhong)[0];
		String mobile_ = en.split(zhong)[1];
		System.err.println(mid_.substring(qian.length(), mid_.length()));
		System.err.println(mobile_.substring(0, mobile_.length()-hou.length()));
	}
	
	/**
	 * 获取mid
	 * @author WKX
	 * @param encodedText APP端加密文本
	 */
	private static String getMid(String encodedText) throws Exception{
		final Base64 base64 = new Base64();
		String en = new String(base64.decode(encodedText.substring(10, encodedText.length())), "UTF-8");
		
		String mid_ = en.split(zhong)[0];
		return mid_.substring(qian.length(), mid_.length());
	}
	
	/**
	 * 获取手机号
	 * @author WKX
	 * @param encodedText APP端加密文本
	 */
	private static String getMobile(String encodedText) throws Exception{
		final Base64 base64 = new Base64();
		String en = new String(base64.decode(encodedText.substring(10, encodedText.length())), "UTF-8");
		
		String mobile_ = en.split(zhong)[1];
		return mobile_.substring(0, mobile_.length()-hou.length());
	}
}