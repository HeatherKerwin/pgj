package com.bbs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;

import com.bbs.Actions;
import com.bbs.Constant;
import com.bbs.Types;
import com.bbs.kit.HttpUtil;
import com.bbs.kit.MyStackKit;
import com.bbs.kit.NameKit;
import com.bbs.kit.ResponseKit;
import com.bbs.kit.Result;
import com.bbs.kit.SendMessagesUtil;
import com.bbs.kit.SessionKit;
import com.bbs.kit.Utils;
import com.bbs.model.Activecode;
import com.bbs.model.Award;
import com.bbs.model.CheckIn;
import com.bbs.model.LoginUser;
import com.bbs.model.PointLog.Cosname;
import com.bbs.model.Ticket;
import com.bbs.model.User;
import com.bbs.model.Userinfo;
import com.bbs.service.ActivecodeService;
import com.bbs.service.AwardService;
import com.bbs.service.CheckInService;
import com.bbs.service.CommentService;
import com.bbs.service.FavoriteService;
import com.bbs.service.NoticeService;
import com.bbs.service.PointLogService;
import com.bbs.service.SettingsService;
import com.bbs.service.TicketService;
import com.bbs.service.TopicService;
import com.bbs.service.UserService;
import com.bbs.service.UserinfoService;
import com.bbs.service.UserlogService;
import com.blade.context.BladeWebContext;
import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.AR;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;
import com.blade.patchca.PatchcaService;
import com.blade.route.annotation.Path;
import com.blade.route.annotation.PathVariable;
import com.blade.route.annotation.Route;
import com.blade.view.ModelAndView;
import com.blade.web.http.HttpMethod;
import com.blade.web.http.Request;
import com.blade.web.http.Response;
import com.taobao.api.ApiException;

import blade.kit.DateKit;
import blade.kit.EncrypKit;
import blade.kit.PatternKit;
import blade.kit.StringKit;
import blade.kit.json.JSONKit;
import blade.kit.json.JSONObject;
import blade.kit.json.JSONValue;

@Path("/")
public class UserController extends BaseController {
	
	@Inject
	private SettingsService settingsService;
	
	@Inject
	private ActivecodeService activecodeService;
	
	@Inject
	private UserService userService;
	
	@Inject
	private UserinfoService userinfoService;
	
	@Inject
	private CommentService commentService;
	
	@Inject
	private NoticeService noticeService;
	
	@Inject
	private FavoriteService favoriteService;
	
	@Inject
	private TopicService topicService;
	
	@Inject
	private UserlogService userlogService;
	
	@Inject
	private PointLogService pointLogService;
	
	@Inject
	private TicketService ticketService;
	
	@Inject
	private AwardService awardService;
	
	@Inject
	private CheckInService checkInService;
	
	/**
	 * 获取验证码
	 */
	@Route(value = "/captcha", method = HttpMethod.GET)
	public void show_captcha(Request request, Response response){
		PatchcaService.get().size(200, 40).render(request, response);
	}
	
	/**
	 * 登录操作（已弃用/signin）
	 */
	@Route(value = "/signin/old", method = HttpMethod.POST)
	public ModelAndView signin(Request request, Response response){
		
		String login_name = request.query("login_name");
		String pass_word = request.query("pass_word");
		String rememberme = request.query("rememberme");
		
		if(StringKit.isBlank(login_name) || StringKit.isBlank(pass_word)){
			request.attribute(this.ERROR, "用户名和密码不能为空");
			request.attribute("login_name", login_name);
			return this.getView("signin");
		}
		
		boolean hasUser = userService.hasUser(login_name);
		if(!hasUser){
			request.attribute(this.ERROR, "该用户不存在");
			request.attribute("login_name", login_name);
			return this.getView("signin");
		}
		
		User user = userService.signin(login_name, pass_word);
		if(null == user){
			request.attribute(this.ERROR, "用户名或密码错误");
			request.attribute("login_name", login_name);
			return this.getView("signin");
		}
		
		if(user.getStatus() == 0){
			request.attribute(this.ERROR, "该用户尚未激活，请登录邮箱激活帐号后登录");
			request.attribute("login_name", login_name);
			return this.getView("signin");
		}
		
		LoginUser loginUser = userService.getLoginUser(user, null);
		SessionKit.setLoginUser(request.session(), loginUser);
		if(StringKit.isNotBlank(rememberme) && rememberme.equals("on")){
			SessionKit.setCookie(response, Constant.USER_IN_COOKIE, loginUser.getUid());
		}
		
		userlogService.save(user.getUid(), Actions.SIGNIN, login_name);
		
		String val = SessionKit.getCookie(request, Constant.JC_REFERRER_COOKIE);
		if(StringKit.isNotBlank(val)){
			response.redirect(val);
		} else {
			ResponseKit.go(response, "/");
		}
		return null;
	}
	
	/**
	 * 注册页面
	 */
	@Route(value = "/signup", method = HttpMethod.GET)
	public ModelAndView show_signup(Request request, Response response){
		Object allow_signup = Constant.SYS_INFO.get(Types.allow_signup.toString());
		if(null != allow_signup && allow_signup.toString().equals("false")){
			request.attribute(this.INFO, "暂时停止注册");
		}
		return this.getView("signup");
	}
	
	/**
	 * 注销
	 */
	@Route(value = "/logout")
	public void logout(Request request, Response response){
		SessionKit.removeUser(request.session());
		SessionKit.removeCookie(response);
		SessionKit.setCookie(response, Constant.USER_IN_COOKIE,"");
		ResponseKit.go(response, "/");
	}
	
	/**
	 * 检查是否有通知
	 */
	@Route(value = "/check_notice", method = HttpMethod.GET)
	public void check_notice(Request request, Response response){
		LoginUser user = SessionKit.getLoginUser();
		if(null == user){
			response.text("0");
			return;
		}
		
		Long notices = noticeService.getNotices(user.getUid());
		response.text(notices.toString());
	}
	
	/**
	 * 通知列表
	 */
	@Route(value = "/notices", method = HttpMethod.GET)
	public ModelAndView notices(Request request, Response response){
		LoginUser user = SessionKit.getLoginUser();
		if(null == user){
			ResponseKit.go(response, "/signin/quick");
			return null;
		}
		
		Integer page = request.queryAsInt("p");
		
		Page<Map<String, Object>> noticePage = noticeService.getNoticePage(user.getUid(), page, 10);
		request.attribute("noticePage", noticePage);
		
		// 清空我的通知
		if(null != noticePage && noticePage.getResults().size() > 0){
			noticeService.read(user.getUid());
		}
		
		return this.getView("notices");
	}
	
	/**
	 * 注册操作
	 */
	@Route(value = "/signup", method = HttpMethod.POST)
	public ModelAndView signup(Request request, Response response){
		
		String login_name = request.query("login_name");
		String email = request.query("email");
		String pass_word = request.query("pass_word");
		String auth_code = request.query("auth_code");
		
		request.attribute("login_name", login_name);
		request.attribute("email", email);
		
		if(StringKit.isBlank(login_name) || StringKit.isBlank(pass_word) 
				|| StringKit.isBlank(email) || StringKit.isBlank(auth_code) ){
			request.attribute(this.ERROR, "参数不能为空");
			return this.getView("signup");
		}
		
		if(login_name.length() > 16 || login_name.length() < 4){
			request.attribute(this.ERROR, "请输入4-16位用户名");
			return this.getView("signup");
		}
		
		if(!Utils.isLegalName(login_name)){
			request.attribute(this.ERROR, "请输入只包含字母／数字／下划线的用户名");
			return this.getView("signup");
		}
		
		if(!Utils.isSignup(login_name)){
			request.attribute(this.ERROR, "您的用户名中包含禁用字符，请修改后注册");
			return this.getView("signup");
		}
		
		if(!Utils.isEmail(email)){
			request.attribute(this.ERROR, "请输入正确的邮箱");
			return this.getView("signup");
		}
		
		if(pass_word.length() > 20 || pass_word.length() < 6){
			request.attribute(this.ERROR, "请输入6-20位字符的密码");
			return this.getView("signup");
		}
		
		String patchca = request.session().attribute("patchca");
		if(StringKit.isNotBlank(patchca) && !patchca.equalsIgnoreCase(auth_code)){
			request.attribute(this.ERROR, "验证码输入错误");
			return this.getView("signup");
		}
		
		QueryParam queryParam = QueryParam.me();
		queryParam.eq("login_name", login_name);
		queryParam.in("status", AR.in(0, 1));
		User user = userService.getUser(queryParam);
		if(null != user){
			request.attribute(this.ERROR, "该用户名已经被占用，请更换用户名");
			return this.getView("signup");
		}
		
		queryParam = QueryParam.me();
		queryParam.eq("email", email);
		queryParam.in("status", 0, 1);
		user = userService.getUser(queryParam);
		if(null != user){
			request.attribute(this.ERROR, "该邮箱已经被注册，请直接登录");
			return this.getView("signup");
		}
		
		User user_ = userService.signup(login_name, pass_word, email);
		if(null != user_){
			userlogService.save(user_.getUid(), Actions.SIGNUP, login_name + ":" + email);
			request.attribute(this.INFO, "注册成功，已经向您的邮箱 " + email + " 发送了一封激活申请，请注意查收！");
		} else {
			request.attribute(this.ERROR, "注册发生异常");
		}
		return this.getView("signup");
	}
	
	/**
	 * 激活账户
	 */
	@Route(value = "/active/:code", method = HttpMethod.GET)
	public ModelAndView activeAccount(@PathVariable("code") String code, Request request, Response response){
		Activecode activecode = activecodeService.getActivecode(code);
		if(null == activecode){
			request.attribute(this.ERROR, "无效的激活码");
			return this.getView("info");
		}
		
		Long expries = activecode.getExpires_time();
		if(expries < DateKit.getCurrentUnixTime()){
			request.attribute(this.ERROR, "该激活码已经过期，请重新发送");
			return this.getView("info");
		}
		
		if(activecode.getIs_use() == 1){
			request.attribute(this.ERROR, "激活码已经被使用");
			return this.getView("info");
		}
		
		// 找回密码
		if(activecode.getType().equals(Types.forgot.toString())){
			request.attribute("code", code);
			return this.getView("reset_pwd");
		}
		
		boolean flag = userService.updateStatus(activecode.getUid(), 1);
		if(!flag){
			activecodeService.useCode(code);
			request.attribute(this.ERROR, "激活失败");
		} else {
			request.attribute(this.INFO, "激活成功，您可以凭密码登陆");
			settingsService.updateCount(Types.user_count.toString(), +1);
			Constant.SYS_INFO = settingsService.getSystemInfo();
			Constant.VIEW_CONTEXT.set("sys_info", Constant.SYS_INFO);
		}
		return this.getView("active");
	}
	
	/**
	 * 忘记密码页面
	 */
	@Route(value = "/forgot", method = HttpMethod.GET)
	public ModelAndView show_forgot(Request request, Response response){
		return this.getView("forgot");
	}
	
	/**
	 * 忘记密码发送链接
	 */
	@Route(value = "/forgot", method = HttpMethod.POST)
	public ModelAndView forgot(Request request, Response response){
		String email = request.query("email");
		if(StringKit.isBlank(email)){
			request.attribute(this.ERROR, "邮箱不能为空");
			return this.getView("forgot");
		}
		
		if(!PatternKit.isEmail(email)){
			request.attribute(this.ERROR, "请输入正确的邮箱");
			request.attribute("email", email);
			return this.getView("forgot");
		}
		
		User user = userService.getUser(QueryParam.me().eq("email", email));
		if(null == user){
			request.attribute(this.ERROR, "该邮箱没有注册账户,请检查您的邮箱是否正确");
			request.attribute("email", email);
			return this.getView("forgot");
		}
		if(user.getStatus() == 0){
			request.attribute(this.ERROR, "该邮箱未激活");
			request.attribute("email", email);
			return this.getView("forgot");
		}
		String code = activecodeService.save(user, "forgot");
		if(StringKit.isNotBlank(code)){
			request.attribute(this.INFO, "修改密码链接已经发送到您的邮箱，请注意查收！");
		} else {
			request.attribute(this.ERROR, "找回密码失败");
		}
		
		return this.getView("forgot");
	}
	
	/**
	 * 修改新密码
	 */
	@Route(value = "/reset_pwd", method = HttpMethod.POST)
	public ModelAndView reset_pwd(Request request, Response response){
		String code = request.query("code");
		String password = request.query("pass_word");
		String re_password = request.query("re_pass_word");
		
		if(StringKit.isBlank(code) || StringKit.isBlank(password) || StringKit.isBlank(re_password)){
			return null;
		}
		
		request.attribute("code", code);
		
		if(!password.equals(re_password)){
			request.attribute(this.ERROR, "两次密码不一致，请确认后提交");
			return this.getView("reset_pwd");
		}
		
		if(password.length() > 20 || password.length() < 6){
			request.attribute(this.ERROR, "请输入6-20位字符的密码");
			return this.getView("reset_pwd");
		}
		
		Activecode activecode = activecodeService.getActivecode(code);
		if(null == activecode || !activecode.getType().equals(Types.forgot.toString())){
			request.attribute(this.ERROR, "无效的激活码");
			return this.getView("reset_pwd");
		}
		
		Long expries = activecode.getExpires_time();
		if(expries < DateKit.getCurrentUnixTime()){
			request.attribute(this.ERROR, "该激活码已经过期，请重新发送");
			return this.getView("reset_pwd");
		}
		
		if(activecode.getIs_use() == 1){
			request.attribute(this.ERROR, "激活码已经被使用");
			return this.getView("reset_pwd");
		}
		
		User user = userService.getUser(activecode.getUid());
		if(null == user){
			request.attribute(this.ERROR, "激活码已经被使用");
			return this.getView("reset_pwd");
		}
		
		String new_pwd = EncrypKit.md5(password);//只对密码加密
		boolean flag = userService.updatePwd(user.getUid(), new_pwd);
		if(flag){
			activecodeService.useCode(code);
			request.attribute(this.INFO, "密码修改成功，您可以直接登录！");
		} else {
			request.attribute(this.ERROR, "密码修改失败");
		}
		return this.getView("reset_pwd");
	}
	
	/**
	 * 用户主页
	 */
	@Route(value = "/member/:username")
	public ModelAndView member(@PathVariable("username") String username, Request request, Response response){
		QueryParam up = QueryParam.me();
		up.eq("status", 1).eq("login_name", username);
		
		User user = userService.getUser(up);
		if(null == user){
			//不存在的用户
			response.text("not found user.");
			return null;
		}
		
		Map<String, Object> profile = userService.getUserDetail(user.getUid());
		request.attribute("profile", profile);
		
		//是否关注了该用户
		LoginUser loginUser = SessionKit.getLoginUser();
		if(null == loginUser){
			request.attribute("is_follow", false);
			SessionKit.setCookie(response, Constant.JC_REFERRER_COOKIE, request.url());
		} else {
			boolean is_follow = favoriteService.isFavorite(Types.following.toString(), loginUser.getUid(), user.getUid());
			request.attribute("is_follow", is_follow);
		}
		
		//最新创建的主题
		QueryParam tp = QueryParam.me();
		tp.eq("status", 1).eq("uid", user.getUid()).orderby("create_time desc, update_time desc").page(1, 10);
		Page<Map<String, Object>> topicPage = topicService.getPageList(tp);
		request.attribute("topicPage", topicPage);
		
		//最新发布的回复
		QueryParam cp = QueryParam.me();
		cp.eq("uid", user.getUid()).orderby("create_time desc").page(1, 10);
		Page<Map<String, Object>> commentPage = commentService.getPageListMap(cp);
		request.attribute("commentPage", commentPage);
		
		//角色
		Long score = userService.getScoreByUid(user.getUid());
		request.attribute("cosname", Cosname.getCurrent(score));
		return this.getView("member_detail");
	}
	
	/**
	 * 我收藏的帖子
	 */
	@Route(value = "/my/topics")
	public ModelAndView myTopics(Request request, Response response){
		
		LoginUser user = SessionKit.getLoginUser();
		if(null == user){
			ResponseKit.go(response, "/");
			return null;
		}
		
		Integer page = request.queryAsInt("p");
		
		Page<Map<String, Object>> favoritesPage = favoriteService.getMyTopics(user.getUid(), page, 10);
		request.attribute("favoritesPage", favoritesPage);
		
		return this.getView("my_topics");
	}
	
	/**
	 * 我收藏的节点
	 */
	@Route(value = "/my/nodes")
	public ModelAndView myNodes(Request request, Response response){
		
		LoginUser user = SessionKit.getLoginUser();
		if(null == user){
			ResponseKit.go(response, "/");
			return null;
		}
		
		List<Map<String, Object>> nodes = favoriteService.getMyNodes(user.getUid());
		request.attribute("nodes", nodes);
		
		return this.getView("my_nodes");
	}
	
	/**
	 * 我的关注
	 */
	@Route(value = "/my/following")
	public ModelAndView following(Request request, Response response){
		
		LoginUser user = SessionKit.getLoginUser();
		if(null == user){
			ResponseKit.go(response, "/");
			return null;
		}
		
		Integer page = request.queryAsInt("p");
		
		Page<Map<String, Object>> followingPage = favoriteService.getFollowing(user.getUid(), page, 10);
		request.attribute("followingPage", followingPage);
		
		return this.getView("following");
	}
	

	/**
	 * 关注／收藏／点赞／下沉帖
	 */
	@Route(value = "/favorite", method = HttpMethod.POST)
	public void favorite(Request request, Response response){
		LoginUser user = SessionKit.getLoginUser();
		if(null == user){
			this.nosignin(response);
			return;
		}
		
		// topic：帖子，node：节点，love：喜欢，following：关注
		String type = request.query("type");
		Long event_id = request.queryAsLong("event_id");
		
		if(StringKit.isBlank(type) || null == event_id || event_id == 0){
			return;
		}
		
		Integer count = favoriteService.update(type, user.getUid(), event_id);
		LoginUser loginUser = userService.getLoginUser(null, user.getUid());
		SessionKit.setLoginUser(request.session(), loginUser);
		
		this.success(response, count);
	}
	
	/**
	 * 个人设置
	 */
	@Route(value = "settings", method = HttpMethod.GET)
	public ModelAndView show_settings(Request request, Response response){
		
		LoginUser user = SessionKit.getLoginUser();
		if(null == user){
			ResponseKit.go(response, "/");
			return null;
		}
		Map<String, Object> profile = userService.getUserDetail(user.getUid());
		request.attribute("profile", profile);
		request.attribute("cosname", userService.getCosnameByUid(user.getUid()));//新添加
		return this.getView("settings");
	}
	
	/**
	 * 个人设置
	 */
	@Route(value = "settings", method = HttpMethod.POST)
	public void settings(Request request, Response response){
		LoginUser loginUser = SessionKit.getLoginUser();
		if(null == loginUser){
			this.nosignin(response);
			return;
		}
		String type = request.query("type");
		if(StringKit.isBlank(type)){
			return;
		}
		
		//修改头像（含昵称）
		if(type.equals("avatar")){
			String avatar = request.query("avatar");
			String login_name = request.query("login_name");//用户名（帐号）
			login_name = login_name.trim();//去掉前后空格
			if(!Utils.isLegalName(login_name)){
				this.error(response, "请输入只包含字母／数字／下划线的用户名！");
				return;
			}
			if(!Utils.isSignup(login_name)){
				this.error(response, "您的用户名中包含禁用字符，请修改！");
				return;
			}
			User temp = userService.getUserByLoginName(login_name);
			if(temp!=null && temp.getUid()!=loginUser.getUid()){//已存在相同用户名
				this.error(response, "该用户名已存在！");
				return;
			}
			try {
				if(StringKit.isNotBlank(avatar)){
					userService.updateAvatar(loginUser.getUid(), avatar);
				}
				if(!login_name.equals(loginUser.getUser_name())){
					userService.updateLoginName(loginUser.getUid(), login_name);
				}
				LoginUser loginUserTemp = userService.getLoginUser(null, loginUser.getUid());
				SessionKit.setLoginUser(request.session(), loginUserTemp);
			} catch (Exception e) {
				e.printStackTrace();
				this.error(response, "头像更换失败");
				return;
			}
			
			String nick_name = request.query("nick_name");
			Userinfo userinfo = new Userinfo();//更新昵称（新添加）
			userinfo.setUid(loginUser.getUid());
			userinfo.setNick_name(nick_name);
			userinfoService.update(userinfo);
			
			this.success(response, "");
			return;
		}
		//修改基本信息
		if(type.equals("info")){
			String nickName = request.query("nick_name");
			String jobs = request.query("jobs");
			String webSite = request.query("web_site");
			String github = request.query("github");
			String weibo = request.query("weibo");
			String location = request.query("location");
			String signature = request.query("signature");
			String instructions = request.query("instructions");
			
			String name = request.query("name");
			String company = request.query("company");
			String phone = request.query("phone");
			String wechat = request.query("wechat");
			String qq = request.query("qq");
			try {
				boolean flag = userinfoService.update(loginUser.getUid(), nickName, jobs, webSite, github, weibo, location, signature, instructions,name,company,phone,wechat,qq);
				if(flag){
					pointLogService.saveWANSHAN(loginUser.getUid());
					LoginUser loginUserTemp = userService.getLoginUser(null, loginUser.getUid());
					SessionKit.setLoginUser(request.session(), loginUserTemp);
					this.success(response, "");
				} else {
					this.error(response, "修改失败");
				}
			} catch (Exception e) {
				e.printStackTrace();
				this.error(response, "修改失败");
			}
			return;
		}
		//修改密码
		if(type.equals("pwd")){
			Map<String, Object> profile = userService.getUserDetail(loginUser.getUid());
			request.attribute("profile", profile);
			
			String curpwd = request.query("curpwd");
			String newpwd = request.query("newpwd");
			
			if(StringKit.isBlank(curpwd) || StringKit.isBlank(newpwd)){
				this.error(response, "参数不能为空");
				return;
			}
			if(!EncrypKit.md5(curpwd).equals(loginUser.getPass_word())){
				this.error(response, "旧密码输入错误");
				return;
			}
			try {
				String new_pwd = EncrypKit.md5(newpwd);//只对密码加密
				userService.updatePwd(loginUser.getUid(), new_pwd);
				
				LoginUser loginUserTemp = userService.getLoginUser(null, loginUser.getUid());
				SessionKit.setLoginUser(request.session(), loginUserTemp);
				userlogService.save(loginUser.getUid(), Actions.UPDATE_PWD, new_pwd);
				
				this.success(response, "");
			} catch (Exception e) {
				e.printStackTrace();
				this.error(response, "密码修改失败");
			}
			return;
		}
	}
	
	/**
	 * 显示markdown预览
	 */
	@Route(value = "markdown", method = HttpMethod.POST)
	public void getMarkdown(Request request, Response response){
		LoginUser user = SessionKit.getLoginUser();
		if(null == user){
			response.text("");
			return;
		}
		String content = request.query("content");
		response.text(Utils.markdown2html(content));
	}
	
	/* --------------------------------------------------新个人中心（后台功能）------------------------------------------------------ */
	
	/**
	 * 用户展示积分
	 * @author WKX
	 * @param request
	 * @param response
	 * @since 2016年11月9日 下午2:51:00
	 */
	@Route(value = "/my/point", method = HttpMethod.GET)
	public ModelAndView point(Request request, Response response){
		LoginUser user = SessionKit.getLoginUser();
		if(null == user){
			ResponseKit.go(response, "/");
			return null;
		}
		request.attribute("cosname", userService.getCosnameByUid(user.getUid()));
		return this.getView("my_point");
	}
	
	/**
	 * 我发布的帖子
	 * @author WKX
	 * @param request
	 * @param response
	 * @since 2016年11月9日 下午3:25:19
	 */
	@Route(value = "/my/topic")
	public ModelAndView myTopic(Request request, Response response){
		LoginUser user = SessionKit.getLoginUser();
		if(null == user){
			SessionKit.setCookie(response, Constant.JC_REFERRER_COOKIE, request.uri());
			ResponseKit.go(response, "/signin/quick");
			return null;
		}
		Integer p = request.queryAsInt("p");
		if(null == p || p < 1){
			p = 1;
		}
		QueryParam tp = QueryParam.me();
		tp.eq("status", 1).eq("uid", user.getUid()).orderby("create_time desc, update_time desc").page(p, 10);
		Page<Map<String, Object>> page = topicService.getPageList(tp);
		request.attribute("topicPage", page);
		return this.getView("my_topic");
	}
	
	/**
	 * 我的相关回复（我参与的）
	 * @author WKX
	 * @param request
	 * @param response
	 * @since 2016年11月9日 下午3:25:31
	 */
	@Route(value = "/my/topic/comment")
	public ModelAndView myComment(Request request, Response response){
		LoginUser user = SessionKit.getLoginUser();
		if(null == user){
			ResponseKit.go(response, "/");
			return null;
		}
		Integer p = request.queryAsInt("p");
		if(null == p || p < 1){
			p = 1;
		}
		QueryParam cp = QueryParam.me();
		cp.eq("uid", user.getUid()).orderby("create_time desc").page(p, 10);
		Page<Map<String, Object>> page = commentService.getPageListMap(cp);
		request.attribute("commentPage", page);
		return this.getView("my_topic_comment");
	}
	
	/**
	 * 我收藏的帖子
	 * @author WKX
	 * @param request
	 * @param response
	 * @since 2016年11月9日 下午3:27:57
	 */
	@Route(value = "/my/topic/collect")
	public ModelAndView myCollectTopics(Request request, Response response){
		LoginUser user = SessionKit.getLoginUser();
		if(null == user){
			ResponseKit.go(response, "/");
			return null;
		}
		Integer p = request.queryAsInt("p");
		if(null == p || p < 1){
			p = 1;
		}
		Page<Map<String, Object>> page = favoriteService.getMyTopics(user.getUid(), p, 10);
		request.attribute("topicPage", page);
		return this.getView("my_topic_collect");
	}
	
	/**
	 * 注册操作（只保存APP端账号，BBS账号在登录时验证未注册时生成）
	 * @author WKX
	 * @param request
	 * @param response
	 * @since 2016年11月11日 下午4:28:32
	 */
	@Route(value = "/signup/new", method = HttpMethod.POST)
	public ModelAndView signupFromApp(Request request, Response response){
		String msg = "网络异常";
		String login_name = request.query("login_name");//手机号
		String pass_word = request.query("pass_word");
		String code = request.query("auth_code");//手机验证码
		String invitationCode = request.query("invitationCode");//邀请码（来自推荐用户）
		String sqxy = request.query("sqxy");//社区协议
		request.attribute("login_name", login_name);
		
		if(StringKit.isBlank(sqxy) || !sqxy.equals("on")){
			request.attribute(this.ERROR, "您对我们的协议还有什么不清楚的吗！");
			return this.getView("signup");
		}
		if(StringKit.isBlank(login_name) || StringKit.isBlank(pass_word) || StringKit.isBlank(code)){
			request.attribute(this.ERROR, "参数不能为空");
			return this.getView("signup");
		}
		if(!Utils.isPhone(login_name)){
			request.attribute(this.ERROR, "请输入正确的手机号码");
			return this.getView("signup");
		}
		if(pass_word.length() > 20 || pass_word.length() < 6){
			request.attribute(this.ERROR, "请输入6-20位字符的密码");
			return this.getView("signup");
		}
		String sms_code = request.session().attribute("sms_code");
		if(StringKit.isBlank(sms_code) || !sms_code.equalsIgnoreCase(code)){
			request.attribute(this.ERROR, "验证码输入错误");
			return this.getView("signup");
		}
		try {
			Map<String,String> result = this.registerFromApp(login_name,pass_word,invitationCode);
			if(result==null || result.get("response")==null || "failed".equals(result.get("response")) || result.get("data")==null){
				if(result.get("response")!=null)msg = result.get("msg");
				request.attribute(this.ERROR, msg);
				return this.getView("signup");
			}
			request.attribute(this.INFO, "恭喜您，注册成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.getView("signup");
	}
	
	/**
	 * 发送验证码
	 * @author WKX
	 * @param request
	 * @param response
	 * @throws ApiException 
	 * @since 2016年11月11日 下午5:19:31
	 */
	@Route(value = "/signup/sendcode", method = HttpMethod.GET)
	public void sendCode(Request request, Response response) throws ApiException{
		String type = request.query("type");//注册、快速登录
		String phone = request.query("login_name");//手机号
		if(StringKit.isBlank(phone) || !Utils.isPhone(phone)){
			this.error(response, "请输入正确的手机号");
			return;
		}
		String code = String.valueOf((Math.random())).split("\\.")[1];
		code = code.substring(0,4);
		request.session().attribute("sms_code", code);
		if("quicklogin".equals(type)){
			SendMessagesUtil.aliSendIdentityMessage(phone, code);
		}else{
			SendMessagesUtil.aliSendRegistMessage(phone, code);
		}
		this.success(response, "发送成功，请查收");
	}
	
	/**
	 * 登录（APP验证）密码登录
	 * @author WKX
	 * @param mobile 手机号
	 * @param pwd 密码
	 * @throws ParseException
	 * @throws IOException
	 * @since 2016年11月11日 上午11:13:19
	 */
	private Map<String,String> loginPwdFromApp(String mobile,String pwd,String type) throws ParseException, IOException{
		Map<String,String> params = new HashMap<String, String>();
		params.put("mobile", mobile);
		if(StringKit.isNotBlank(pwd))params.put("pwd", pwd);
		if(StringKit.isNotBlank(type))params.put("type", type);//登录类型（无参数是密码登录、有参数是快速登录）
		Result r = HttpUtil.sendPost(Constant.APP_URL+"/app/bbs/login", null,params,"utf-8");
		HttpEntity he = r.getHttpEntity();
		String res = EntityUtils.toString(he);
		JSONObject j = JSONKit.parseObject(res);
		if(j.get("data")!=null)params.put("data", j.get("data").toString());
		if(j.get("response")!=null)params.put("response", j.get("response").asString());
		if(j.get("msg")!=null)params.put("msg", j.get("msg").asString());
		return params;
	}
	
	/**
	 * 注册（APP验证）
	 * @author WKX
	 * @param mobile 手机号
	 * @param pwd 密码
	 * @param invitationCode 推荐码
	 * @throws ParseException
	 * @throws IOException
	 * @since 2016年11月11日 下午1:36:41
	 */
	private Map<String,String> registerFromApp(String mobile,String pwd,String invitationCode) throws ParseException, IOException{
		Map<String,String> params = new HashMap<String, String>();
		params.put("mobile", mobile);
		params.put("pwd", pwd);
		params.put("invitationCode", invitationCode);
		Result r = HttpUtil.sendPost(Constant.APP_URL+"/app/bbs/register", null,params,"utf-8");
		HttpEntity he = r.getHttpEntity();
		String res = EntityUtils.toString(he);
		JSONObject j = JSONKit.parseObject(res);
		if(j.get("data")!=null)params.put("data", j.get("data").toString());
		if(j.get("response")!=null)params.put("response", j.get("response").asString());
		if(j.get("msg")!=null)params.put("msg", j.get("msg").asString());
		return params;
	}
	
	/**
	 * 个人中心-设置（页面）
	 * @author WKX
	 * @param request
	 * @param response
	 * @since 2016年11月16日 下午2:34:35
	 */
	@Route(value = "my/setting/:flag", method = HttpMethod.GET)
	public ModelAndView setting(@PathVariable("flag") String flag,Request request, Response response){
		LoginUser user = SessionKit.getLoginUser();
		if(null == user){
			ResponseKit.go(response, "/signin/quick");
			return null;
		}
		Map<String, Object> profile = userService.getUserDetail(user.getUid());
		request.attribute("profile", profile);
		request.attribute("cosname", userService.getCosnameByUid(user.getUid()));//新添加
		if("info".equals(flag)){
			return this.getView("my_setting_info");
		}else if("base".equals(flag)){
			return this.getView("my_setting");
		}
		ResponseKit.go(response, "/");
		return null;
	}
	
	/**
	 * 我的奖励（没邀请三个用户）
	 * @author WKX
	 * @param request
	 * @param response
	 * @since 2016年11月17日 下午2:00:43
	 */
	@Route(value = "/my/ticket", method = HttpMethod.GET)
	public ModelAndView myTicket(Request request, Response response){
		LoginUser user = SessionKit.getLoginUser();
		if(null == user){
			SessionKit.setCookie(response, Constant.JC_REFERRER_COOKIE, request.url());
			ResponseKit.go(response, "/signin/quick");
			return null;
		}
		
		this.getTicketInfo(request, user.getUid());//获取抽奖券信息
		
		List<Ticket> has_ticket = ticketService.getByUidState(user.getUid(), 0);//0未使用
		if(has_ticket!=null)request.attribute("has_ticket", has_ticket.size());
		
		//获取中奖记录
		List<Award> awards = awardService.getByUid(user.getUid());
		request.attribute("awards", awards);
		return this.getView("my_ticket");
	}
	
	/**
	 * 根据用户主键获取奖券信息（需注意IndexController）
	 * @author WKX
	 * @param request
	 * @param uid 用户主键
	 * @since 2017年1月18日 下午3:07:40
	 */
	private void getTicketInfo(Request request,Long uid){
		List<Ticket> yaoqing = ticketService.getByUidSource(uid, 1);//来源（0注册、1邀请用户）
		String mobileUrl = Constant.MOBILE_URL;
		request.attribute("need_invitation", 3);//每邀请三个人获取一张抽奖券
		try {
			User u = userService.getUser(uid);
			Map<String,String> result = Utils.invitationFromApp(u.getMember_id());
			if(result!=null && "success".equals(result.get("response")) && result.get("data")!=null){
				String data = result.get("data");
				if(result.get("myInvitationCode")!=null)mobileUrl += ("?ic=" + result.get("myInvitationCode"));
				int can_get = 0;
				int has = 0;
				if(yaoqing!=null)has = yaoqing.size();
				if(StringKit.isNotBlank(data)){
					int count = Integer.valueOf(data);
					can_get = count/3;//每邀请三个人赠送一张奖券
					int need = 3 - count%3;
					if(need!=0 && need<3)request.attribute("need_invitation", need);//每邀请三个人获取一张抽奖券
				}
				int save = can_get-has;
				for(int i=0;i<save;i++){
					ticketService.save(uid,1);//来源（0注册、1邀请用户）
				}
			}
			request.attribute("code_invitation", mobileUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 通知列表（帖子消息）含：评论、收藏、at
	 * @author WKX
	 * @param request
	 * @param response
	 * @since 2016年11月17日 下午5:35:50
	 */
	@Route(value = "/my/notice", method = HttpMethod.GET)
	public ModelAndView myNotice(Request request, Response response){
		LoginUser user = SessionKit.getLoginUser();
		if(null == user){
			ResponseKit.go(response, "/signin/quick");
			return null;
		}
		Integer page = request.queryAsInt("p");
		Page<Map<String, Object>> noticePage = noticeService.getNoticePage(user.getUid(), page, 10);
		request.attribute("noticePage", noticePage);
		
		if(null != noticePage && noticePage.getResults().size() > 0){//清空我的通知
			noticeService.read(user.getUid());
		}
		return this.getView("my_notice");
	}
	
	/**
	 * 获取系统消息（该模块暂无实际功能）
	 * @author WKX
	 * @param request
	 * @param response
	 * @since 2016年11月17日 下午5:48:06
	 */
	@Route(value = "/my/notice/system", method = HttpMethod.GET)
	public ModelAndView myNoticeSystem(Request request, Response response){
		LoginUser user = SessionKit.getLoginUser();
		if(null == user){
			ResponseKit.go(response, "/signin/quick");
			return null;
		}
		request.attribute("noticePage", null);
		return this.getView("my_notice_system");
	}
	
	/**
	 * 更改密码（页面）
	 * @author WKX
	 * @param request
	 * @param response
	 * @since 2016年11月18日 下午2:14:35
	 */
	@Route(value = "/my/password", method = HttpMethod.GET)
	public ModelAndView myPassword(Request request, Response response){
		LoginUser user = SessionKit.getLoginUser();
		if(null == user){
			ResponseKit.go(response, "/signin/quick");
			return null;
		}
		return this.getView("my_password");
	}
	
	/**
	 * 修改密码（先同步PC密码，在更新BBS）
	 * @author WKX
	 * @param request
	 * @param response
	 * @since 2016年11月18日 下午3:02:21
	 */
	@Route(value = "/my/password/set", method = HttpMethod.POST)
	public void myPasswordSet(Request request, Response response){
		String msg = "密码修改失败";
		LoginUser loginUser = SessionKit.getLoginUser();
		if(null == loginUser){
			this.nosignin(response);
			return;
		}
		String curpwd = request.query("curpwd");
		String newpwd = request.query("newpwd");
		String renewpwd = request.query("renewpwd");
		
		if(StringKit.isBlank(curpwd) || StringKit.isBlank(newpwd) || StringKit.isBlank(renewpwd)){
			this.error(response, "参数不能为空");
			return;
		}
		if(newpwd.length()<6){
			this.error(response, "新密码长度位6~11位");
			return;
		}
		if(!newpwd.equals(renewpwd)){
			this.error(response, "两次输入的新密码不相同");
			return;
		}
		try {
			User u = userService.getUser(loginUser.getUid());
			if(u.getMember_id()!=null){
				Map<String,String> result = this.setPassword(u.getMember_id(), curpwd, newpwd);
				if(result==null || !"success".equals(result.get("response"))){//失败
					if(result.get("msg")!=null)msg = result.get("msg");
					this.error(response, msg);
					return;
				}
			}else{
				String temp = EncrypKit.md5(curpwd);
				if(!temp.equals(u.getPass_word())){
					this.error(response, "当前密码输入有误");
					return;
				}
			}
			
			String new_pwd = EncrypKit.md5(newpwd);//只对密码加密
			userService.updatePwd(loginUser.getUid(), new_pwd);
			
			LoginUser loginUserTemp = userService.getLoginUser(null, loginUser.getUid());
			SessionKit.setLoginUser(request.session(), loginUserTemp);
			userlogService.save(loginUser.getUid(), Actions.UPDATE_PWD, new_pwd);
			this.success(response, "");
		} catch (Exception e) {
			e.printStackTrace();
			this.error(response, msg);
		}
	}
	
	/**
	 * APP接口更改密码
	 * @author WKX
	 * @param memberId 用户主键
	 * @param pwd 密码
	 * @param newpwd 新密码
	 * @throws ParseException
	 * @throws IOException
	 * @since 2016年11月18日 下午3:23:38
	 */
	private Map<String,String> setPassword(Long memberId,String pwd,String newpwd) throws ParseException, IOException{
		Map<String,String> params = new HashMap<String, String>();
		if(memberId!=null)params.put("memberId", memberId.toString());
		params.put("pwd", pwd);
		params.put("newpwd", newpwd);
		Result r = HttpUtil.sendPost(Constant.APP_URL+"/app/bbs/password", null,params,"utf-8");
		HttpEntity he = r.getHttpEntity();
		String res = EntityUtils.toString(he);
		JSONObject j = JSONKit.parseObject(res);
		if(j.get("response")!=null)params.put("response", j.get("response").asString());
		if(j.get("msg")!=null)params.put("msg", j.get("msg").asString());
		return params;
	}
	
	/**
	 * 登录页面（密码登录）
	 * @author WKX
	 * @param request
	 * @param response
	 * @since 2016年11月18日 下午6:43:01
	 */
	@Route(value = "/signin", method = HttpMethod.GET)
	public ModelAndView signinQuick(Request request, Response response){
		return this.getView("signin");
	}
	
	/**
	 * 登录操作（含验证APP用户，并获取或新增BBS端用户）密码登录
	 * @author WKX
	 * @param request
	 * @param response
	 * @since 2016年11月11日 下午4:01:29
	 */
	@Route(value = "/signin", method = HttpMethod.POST)
	public ModelAndView signinPwdFromApp(Request request, Response response){
		String msg = "网络异常";
		String login_name = request.query("login_name");//手机号（或用户名）
		String pass_word = request.query("pass_word");
		String rememberme = request.query("rememberme");
		
		if(StringKit.isBlank(login_name) || StringKit.isBlank(pass_word)){
			request.attribute(this.ERROR, "用户名和密码不能为空");
			request.attribute("login_name", login_name);
			return this.getView("signin");
		}
		try {
			User user = userService.signin(login_name, pass_word);
			if(user==null){//先验证用户名登录（在验证APP端帐号）
				Map<String,String> result = this.loginPwdFromApp(login_name, pass_word,null);//密码登录
				if(result==null || result.get("response")==null || "failed".equals(result.get("response")) || result.get("data")==null){
					if(result.get("response")!=null)msg = result.get("msg");
					request.attribute(this.ERROR, msg);
					request.attribute("login_name", login_name);
					return this.getView("signin");
				}
				String member_id = result.get("data");
				user = userService.getUserByMemberId(Long.valueOf(member_id));
				if(user==null){
					user = userService.save(login_name, pass_word, Long.valueOf(member_id));//BBS端不存在则保存用户
					if(user==null)throw new Exception();
					userlogService.save(user.getUid(), Actions.SIGNUP, login_name);//记录注册日志
				}
			}
			
			LoginUser loginUser = userService.getLoginUser(user, null);
			SessionKit.setLoginUser(request.session(), loginUser);
			if(StringKit.isNotBlank(rememberme) && rememberme.equals("on")){
				SessionKit.setCookie(response, Constant.USER_IN_COOKIE, loginUser.getUid());
			}
			
			userlogService.save(user.getUid(), Actions.SIGNIN, login_name);
			String val = SessionKit.getCookie(request, Constant.JC_REFERRER_COOKIE);
			if(StringKit.isNotBlank(val)){
				this.putData(request);
				String path = BladeWebContext.servletContext().getContextPath();
				response.redirect(Constant.SITE_URL + val.replaceFirst(path, ""));
			} else {
				ResponseKit.go(response, "/");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.attribute(this.ERROR, msg);
			request.attribute("login_name", login_name);
			return this.getView("signin");
		}
		return null;
	}
	
	/**
	 * 登录页面（快速登录）
	 */
	@Route(value = "/signin/quick", method = HttpMethod.GET)
	public ModelAndView show_signin(Request request, Response response){
		return this.getView("signin_quick");
	}
	
	/**
	 * 登录操作（含验证APP用户，并获取或新增BBS端用户）快速登录
	 * @author WKX
	 * @param request
	 * @param response
	 * @since 2016年11月11日 下午4:01:29
	 */
	@Route(value = "/signin/quick", method = HttpMethod.POST)
	public ModelAndView signinFromApp(Request request, Response response){
		String msg = "网络异常";
		String login_name = request.query("login_name");//手机号APP验证
		String code = request.query("code");
		request.attribute("login_name", login_name);
		
		if(StringKit.isBlank(login_name) || StringKit.isBlank(code)){
			request.attribute(this.ERROR, "手机号和验证码不能为空");
			return this.getView("signin_quick");
		}
		String sms_code = request.session().attribute("sms_code");
		if(StringKit.isBlank(sms_code) || !sms_code.equalsIgnoreCase(code)){
			request.attribute(this.ERROR, "验证码输入错误");
			return this.getView("signin_quick");
		}
		try {
			Map<String,String> result = this.loginPwdFromApp(login_name,null,"quicklogin");//快速登录
			if(result==null || result.get("response")==null || "failed".equals(result.get("response")) || result.get("data")==null){
				if(result.get("response")!=null)msg = result.get("msg");
				request.attribute(this.ERROR, msg);
				request.attribute("login_name", login_name);
				return this.getView("signin_quick");
			}
			String member_id = result.get("data");
			User user = userService.getUserByMemberId(Long.valueOf(member_id));
			if(user==null){
				String pwd = NameKit.end4Phone(login_name, null);//手机号后六位默认为密码
				user = userService.save(login_name, pwd, Long.valueOf(member_id));//BBS端不存在则保存用户（无密码则默认密码为手机号）
				if(user==null)throw new Exception();
				userlogService.save(user.getUid(), Actions.SIGNUP, login_name);//记录注册日志
			}
			
			LoginUser loginUser = userService.getLoginUser(user, null);
			SessionKit.setLoginUser(request.session(), loginUser);
			
			userlogService.save(user.getUid(), Actions.SIGNIN, login_name);
			SessionKit.setCookie(response, Constant.USER_IN_COOKIE, loginUser.getUid());
			
			//String val = request.session().attribute(Constant.JC_REFERRER_COOKIE);
			String val = SessionKit.getCookie(request, Constant.JC_REFERRER_COOKIE);
			if(StringKit.isNotBlank(val)){
				this.putData(request);
				String path = BladeWebContext.servletContext().getContextPath();
				response.redirect(Constant.SITE_URL + val.replaceFirst(path, ""));
			} else {
				ResponseKit.go(response, "/");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.attribute(this.ERROR, msg);
			request.attribute("login_name", login_name);
			return this.getView("signin_quick");
		}
		return null;
	}
	
	/**
	 * wechat 登录操作（含验证APP用户，并获取或新增BBS端用户）快速登录
	 * @author MH
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ParseException 
	 */
	@Route(value = "/wechat/signin/quick", method = HttpMethod.GET)
	public ModelAndView signinWechatFromApp(Request request, Response response) throws ParseException, IOException{
		String login_name = request.query("login_name");//
		Map<String,String> params = new HashMap<String, String>();
		params.put("openid", login_name);
		Result r = HttpUtil.sendPost(Constant.APP_URL+"/app/bbs/openid", null,params,"utf-8");
		HttpEntity he = r.getHttpEntity();
		String res = EntityUtils.toString(he);
		JSONObject j = JSONKit.parseObject(res);
		login_name =  j.get("mobile").asString();
		if(login_name == null || login_name == ""){
			return this.getView("signin_quick");
		}
		String msg = "网络异常";
		request.attribute("login_name", login_name);
		
		try {
			Map<String,String> result = this.loginPwdFromApp(login_name,null,"quicklogin");//快速登录
			if(result==null || result.get("response")==null || "failed".equals(result.get("response")) || result.get("data")==null){
				if(result.get("response")!=null)msg = result.get("msg");
				request.attribute(this.ERROR, msg);
				request.attribute("login_name", login_name);
				return this.getView("signin_quick");
			}
			String member_id = result.get("data");
			User user = userService.getUserByMemberId(Long.valueOf(member_id));
			if(user==null){
				String pwd = NameKit.end4Phone(login_name, null);//手机号后六位默认为密码
				user = userService.save(login_name, pwd, Long.valueOf(member_id));//BBS端不存在则保存用户（无密码则默认密码为手机号）
				if(user==null)throw new Exception();
				userlogService.save(user.getUid(), Actions.SIGNUP, login_name);//记录注册日志
			}
			
			LoginUser loginUser = userService.getLoginUser(user, null);
			SessionKit.setLoginUser(request.session(), loginUser);
			
			userlogService.save(user.getUid(), Actions.SIGNIN, login_name);
			SessionKit.setCookie(response, Constant.USER_IN_COOKIE, loginUser.getUid());
			String val = SessionKit.getCookie(request, Constant.JC_REFERRER_COOKIE);
			if(StringKit.isNotBlank(val)){
				this.putData(request);
				response.redirect(val);
			} else {
				ResponseKit.go(response, "/");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.attribute(this.ERROR, msg);
			request.attribute("login_name", login_name);
			return this.getView("signin_quick");
		}
		return null;
	}
	
	/**
	 * 使用奖券（转盘页面）
	 * @author WKX
	 * @param request
	 * @param response
	 * @since 2016年11月19日 下午12:08:15
	 */
	@Route(value = "/my/ticket/use", method = HttpMethod.GET)
	public ModelAndView useTicketPage(Request request, Response response){
		LoginUser loginUser = SessionKit.getLoginUser();
		List<Ticket> tickets = new ArrayList<Ticket>();
		if(null != loginUser){
			this.getTicketInfo(request, loginUser.getUid());//获取抽奖券信息
			tickets = ticketService.getByUidState(loginUser.getUid(), 0);//状态（0未使用、1已使用、2无效）
		}else{
			SessionKit.setCookie(response, Constant.JC_REFERRER_COOKIE, request.uri());
		}
		request.attribute("has", tickets.size());
		return this.getView("my_ticket_use");
	}
	
	/**
	 * 抽奖（验证是否可以抽奖）
	 * @author WKX
	 * @param request
	 * @param response
	 * @since 2016年11月19日 下午1:06:20
	 */
	@Route(value = "/my/ticket/can", method = HttpMethod.POST)
	public void canTicket(Request request, Response response){
		LoginUser loginUser = SessionKit.getLoginUser();
		if(null == loginUser){
			this.nosignin(response);
			return;
		}
		List<Ticket> tickets = ticketService.getByUidState(loginUser.getUid(), 0);//状态（0未使用、1已使用、2无效）
		if(tickets!=null && tickets.size()>0){
			this.success(response, "");
		}else{
			this.error(response, "您的抽奖机会已用完,邀请好友可继续获得抽奖机会！");
		}
	}
	
	/**
	 * 抽中奖品（保存记录）
	 * @author WKX
	 * @param request
	 * @param response
	 * @since 2016年11月19日 下午2:05:43
	 */
	@Route(value = "/my/ticket/use", method = HttpMethod.POST)
	public void useTicket(Request request, Response response){
		LoginUser loginUser = SessionKit.getLoginUser();
		if(null == loginUser){
			this.nosignin(response);
			return;
		}
		List<Ticket> tickets = ticketService.getByUidState(loginUser.getUid(), 0);//状态（0未使用、1已使用、2无效）
		if(tickets!=null && tickets.size()>0){
			Ticket t = tickets.get(0);
			String goods = request.query("goods");
			Integer genre = request.queryAsInt("genre");
			Award award = new Award();
			award.setUid(loginUser.getUid());
			award.setCreate_time(new Date());
			award.setGoods(goods);
			award.setTid(t.getId());
			award.setGenre(genre);
			award.setPhone(getMemberMobileById(loginUser.getUid()));//获取注册时手机号码（抽到话费要记录手机号）
			awardService.save(award);
			
			Map<String,Object> data = new HashMap<String, Object>();
			Award a = awardService.getByTid(t.getId());
			if(a!=null)data.put("aid", a.getId());
			data.put("has", tickets.size()-1);
			this.success(response, data);//返回中奖记录主键
		}else{
			this.error(response, "您的抽奖机会已用完,邀请好友可继续获得抽奖机会！");
		}
	}
	
	/**
	 * 根据用户主键获取用户注册电话号码
	 * @author WKX
	 * @param uid 用户主键
	 * @throws ParseException
	 * @throws IOException
	 * @since 2016年12月14日 下午3:57:36
	 */
	private String getMemberMobileById(Long uid){
		if(uid==null)return null;
		User user = userService.getUser(uid);
		if(user==null || user.getMember_id()==null)return null;
		Long memberId = user.getMember_id();
		try {
			Map<String,String> params = new HashMap<String, String>();
			params.put("id", memberId.toString());
			Result r = HttpUtil.sendPost(Constant.APP_URL+"/app/searchMyInfoById", null,params,"utf-8");
			HttpEntity he = r.getHttpEntity();
			String res = EntityUtils.toString(he);
			JSONObject j = JSONKit.parseObject(res);
			if(j.get("member")!=null){
				JSONValue temp = j.get("member");
				if(temp.isObject()){
					JSONObject member = temp.asJSONObject();
					return member.get("mobile").asString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 领取实物奖品（完善中奖记录的详细信息）页面
	 * @author WKX
	 * @param request
	 * @param response
	 * @since 2016年11月19日 下午3:43:12
	 */
	@Route(value = "/my/award/:aid", method = HttpMethod.GET)
	public ModelAndView setAward(@PathVariable("aid") String aid,Request request, Response response){
		LoginUser loginUser = SessionKit.getLoginUser();
		if(null == loginUser || StringKit.isBlank(aid)){
			ResponseKit.go(response, "/");
		}
		Award award = awardService.getById(Long.valueOf(aid));
		
		if(award!=null && loginUser.getUid()!=award.getUid()){//中间记录不存在，或非本人
			ResponseKit.go(response, "/my/ticket");
		}
		request.attribute("aid", aid);
		return this.getView("my_ticket_get");
	}
	
	/**
	 * 获取省市区
	 * @author WKX
	 * @param request
	 * @param response
	 * @since 2016年11月19日 下午4:20:50
	 */
	@Route(value = "/get/region", method = HttpMethod.POST)
	public void getRegion(Request request, Response response){
		try {
			this.success(response, Constant.APP_REGION);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.error(response, "平台暂无报价");
	}
	
	/**
	 * 领取奖品（填写收货地址等）
	 * @author WKX
	 * @param request
	 * @param response
	 * @since 2016年11月19日 下午5:01:23
	 */
	@Route(value = "/my/award/save", method = HttpMethod.POST)
	public void saveAward(Request request, Response response){
		String msg = "领取成功，请耐心等待发货！";
		LoginUser loginUser = SessionKit.getLoginUser();
		if(null == loginUser){
			this.nosignin(response);
			return;
		}
		Long aid = request.queryAsLong("aid");
		String name = request.query("name");
		String phone = request.query("phone");
		String prov = request.query("prov");
		String city = request.query("city");
		String dist = request.query("dist");
		String address = request.query("address");
		if(StringKit.isBlank(name) || StringKit.isBlank(phone) || StringKit.isBlank(prov) || StringKit.isBlank(city) || StringKit.isBlank(dist) || StringKit.isBlank(address)){
			this.error(response, "信息填写不完整");
			return;
		}
		Award award = awardService.getById(aid);
		if(award==null || loginUser.getUid()!=award.getUid()){//中间记录不存在，或非本人
			this.error(response, "数据异常");
			return;
		}
		if(StringKit.isNotBlank(award.getName()) && StringKit.isNotBlank(award.getAddress()))msg = "资料修改成功！";
		try {
			awardService.update(aid,name,phone, prov, city, dist, address, null, null);
			this.success(response, msg);
		} catch (Exception e) {
			e.printStackTrace();
			this.error(response, "数据异常");
		}
	}
	
	/**
	 * 登录初始化数据
	 * @author WKX
	 * @param request
	 * @since 2016年11月22日 下午1:48:19
	 */
	private void putData(Request request){
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
		Constant.VIEW_CONTEXT.set("user_info", user_info);
	}
}