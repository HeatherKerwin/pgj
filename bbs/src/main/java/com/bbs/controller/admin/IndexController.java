package com.bbs.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bbs.Constant;
import com.bbs.Types;
import com.bbs.controller.BaseController;
import com.bbs.form.UserForm;
import com.bbs.kit.ResponseKit;
import com.bbs.kit.Utils;
import com.bbs.model.Banner;
import com.bbs.model.Node;
import com.bbs.model.NodeUser;
import com.bbs.model.User;
import com.bbs.service.ActivecodeService;
import com.bbs.service.BannerService;
import com.bbs.service.NodeService;
import com.bbs.service.NodeUserService;
import com.bbs.service.SettingsService;
import com.bbs.service.TopicService;
import com.bbs.service.UserService;
import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.AR;
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

@Path("/admin/")
public class IndexController extends BaseController {

	@Inject
	private TopicService topicService;
	
	@Inject
	private NodeService nodeService;
	
	@Inject
	private BannerService bannerService;
	
	@Inject
	private UserService userService;
	
	@Inject
	private SettingsService settingsService;
	
	@Inject
	private ActivecodeService activecodeService;
	
	@Inject
	private NodeUserService nodeUserService;
	
	/**
	 * 首页
	 */
	@Route(value = "/")
	public ModelAndView show_home(Request request, Response response){
		return this.getAdminView("home");
	}
	
	/**
	 * 节点列表页面
	 */
	@Route(value = "nodes")
	public ModelAndView show_nodes(Request request, Response response){
		Integer page = request.queryAsInt("p");
		if(null == page || page < 1){
			page = 1;
		}
		QueryParam np = QueryParam.me();
		np.eq("is_del", 0).orderby("topics desc").page(page, 10);
		Page<Map<String, Object>> nodePage = nodeService.getPageList(np);
		request.attribute("nodePage", nodePage);
		return this.getAdminView("nodes");
	}
	
	/**
	 * banner设置
	 */
	@Route(value = "banner")
	public ModelAndView show_banner(Request request, Response response){
		Integer page = request.queryAsInt("p");
		if(null == page || page < 1){
			page = 1;
		}
		QueryParam np = QueryParam.me();
		np.eq("is_del", 0).orderby("create_time desc").page(page, 10);
		Page<Map<String, Object>> bannerPage = bannerService.getPageList(np);
		request.attribute("bannerPage", bannerPage);
		return this.getAdminView("banner");
	}
	
	/**
	 * 添加节点页面
	 */
	@Route(value = "nodes/add", method = HttpMethod.GET)
	public ModelAndView show_add_node(Request request, Response response){
		putData(request);
		return this.getAdminView("add_node");
	}
	
	/**
	 * 添加banner页面
	 */
	@Route(value = "banner/add", method = HttpMethod.GET)
	public ModelAndView show_add_banner(Request request, Response response){
		return this.getAdminView("add_banner");
	}
	
	public void putData(Request request){
		QueryParam np = QueryParam.me();
		np.eq("is_del", 0).eq("pid", 0).orderby("topics desc");
		List<Node> nodes = nodeService.getNodeList(np);
		request.attribute("nodes", nodes);
	}
	
	/**
	 * 添加新节点 
	 * @return
	 */
	@Route(value = "nodes/add", method = HttpMethod.POST)
	public ModelAndView add_node(Request request, Response response){
		
		String title = request.query("node_name");
		String description = request.query("description");
		String node_slug = request.query("node_slug");
		Long pid = request.queryAsLong("pid");
		String node_pic = request.query("node_pic");
		
		if(StringKit.isBlank(title) || StringKit.isBlank(node_slug) || null == pid){
			request.attribute(this.ERROR, "骚年，有些东西木有填哎！！");
			request.attribute("node_name", title);
			request.attribute("node_slug", node_slug);
			return this.getAdminView("add_node");
		}
		
		boolean flag = nodeService.save(pid, title, description, node_slug, node_pic);
		if(flag){
			ResponseKit.go(response, "/admin/nodes");
			return null;
		} else {
			request.attribute(this.ERROR, "节点添加失败");
			request.attribute("node_name", title);
			request.attribute("node_slug", node_slug);
			return this.getAdminView("add_node");
		}
	}
	
	/**
	 * 添加新节点 
	 * @return
	 */
	@Route(value = "banner/add", method = HttpMethod.POST)
	public ModelAndView add_banner(Request request, Response response){
		
		String url = request.query("url");
		Long sort = request.queryAsLong("sort");
		String path = request.query("node_pic");
		
		boolean flag = bannerService.save(sort, url, path);
		if(flag){
			ResponseKit.go(response, "/admin/banner");
			return null;
		} else {
			request.attribute(this.ERROR, "banner添加失败");
			return this.getAdminView("add_banner");
		}
	}
	
	/**
	 * 编辑节点页面
	 */
	@Route(value = "nodes/:nid", method = HttpMethod.GET)
	public ModelAndView show_edit_node(@PathVariable("nid") Long nid, Request request, Response response){
		
		Map<String, Object> nodeMap = nodeService.getNodeDetail(null, nid);
		request.attribute("node", nodeMap);
		putData(request);
		return this.getAdminView("edit_node");
	}
	
	/**
	 * 编辑节点页面
	 */
	@Route(value = "banner/:id", method = HttpMethod.GET)
	public ModelAndView show_edit_banner(@PathVariable("id") Long id, Request request, Response response){
		Map<String, Object> bannerMap = bannerService.getBannerDetail(null, id);
		request.attribute("banner", bannerMap);
		return this.getAdminView("edit_banner");
	}
	
	/**
	 * 编辑节点
	 */
	@Route(value = "nodes/edit", method = HttpMethod.POST)
	public void edit_node(Request request, Response response){
		
		Long nid = request.queryAsLong("nid");
		String title = request.query("node_name");
		String description = request.query("description");
		String node_slug = request.query("node_slug");
		Long pid = request.queryAsLong("pid");
		String node_pic = request.query("node_pic");
		
		boolean flag = nodeService.update(nid, pid, title, description, node_slug, node_pic);
		if(flag){
			this.success(response, "");
		} else {
			this.error(response, "节点修改失败");
		}
		
	}
	
	/**
	 * 编辑banner
	 */
	@Route(value = "banner/edit", method = HttpMethod.POST)
	public void edit_banner(Request request, Response response){
		Long id = request.queryAsLong("id");
		String url = request.query("url");
		Long sort = request.queryAsLong("sort");
		String node_pic = request.query("node_pic");
		
		boolean flag = bannerService.update(id, sort, url,  node_pic);
		if(flag){
			this.success(response, "");
		} else {
			this.error(response, "节点修改失败");
		}
		
	}
	
	/**
	 * 用户列表页面
	 */
	@Route(value = "users")
	public ModelAndView show_users(Request request, Response response){
		Integer page = request.queryAsInt("p");
		String keyword = request.query("keyword");
		if(null == page || page < 1){
			page = 1;
		}
		QueryParam up = QueryParam.me();
		if(StringKit.isNotBlank(keyword)){
			up.like("login_name", "%"+keyword+"%");
			request.attribute("keyword", keyword);
		}
		up.orderby("update_time desc").page(page, 15);
		Page<User> userPage = userService.getPageList(up);
		request.attribute("userPage", userPage);
		return this.getAdminView("users");
	}
	
	/**
	 * 系统设置页面
	 */
	@Route(value = "settings")
	public ModelAndView show_settings(Request request, Response response){
		Map<String, Object> settings = settingsService.getSystemInfo();
		request.attribute("settings", settings);
		return this.getAdminView("settings");
	}
	
	/**
	 * 保存系统设置
	 */
	@Route(value = "settings", method = HttpMethod.POST)
	public void save_settings(Request request, Response response){
		String site_title = request.query("site_title");
		String site_keywords = request.query("site_keywords");
		String site_description = request.query("site_description");
		String allow_signup = request.query("allow_signup");
		settingsService.update(site_title, site_keywords, site_description, allow_signup);
		Constant.SYS_INFO = settingsService.getSystemInfo();
		Constant.VIEW_CONTEXT.set("sys_info", Constant.SYS_INFO);
		this.success(response, "");
	}
	
	/**
	 * 修改用户状态
	 */
	@Route(value = "status", method = HttpMethod.POST)
	public void updateStatus(Request request, Response response){
		String type = request.query("type");
		Long uid = request.queryAsLong("uid");
		if(StringKit.isBlank(type) || null == uid){
			this.error(response, "缺少参数");
			return;
		}
		Integer status = null;
		Integer role_id = null;
		if(type.equals(Types.activeAccount.toString()) || 
				type.equals(Types.recoveryAccount.toString())){
			status = 1;
		}
		
		if(type.equals(Types.disable.toString())){
			status = 2;
		}
		
		if(type.equals(Types.removeAdmin.toString())){
			role_id = 5;
		}
		
		if(type.equals(Types.setAdmin.toString())){
			role_id = 3;
		}
		
		// 重新发送激活邮件
		if(type.equals(Types.resend.toString())){
			activecodeService.resend(uid);
		}
		
		if(null != status){
			userService.updateStatus(uid, status);
		}
		
		if(null != role_id){
			userService.updateRole(uid, role_id);
		}
		this.success(response, "");
	}
	
	/**
	 * 系统工具
	 */
	@Route(value = "tools")
	public ModelAndView show_tools(Request request, Response response){
		return this.getAdminView("tools");
	}
	
	/**
	 * 执行系统工具
	 */
	@Route(value = "tools", method = HttpMethod.POST)
	public ModelAndView save_tools(Request request, Response response){
		String type = request.query("type");
		if(StringKit.isBlank(type)){
			request.attribute(this.ERROR, "请选择执行的操作");
			return this.getAdminView("tools");
		}
		if(type.equals("clean_cache")){
			AR.cleanCache();
			request.attribute(this.INFO, "执行成功");
		}else if(type.equals("refresh_weight")){//刷新帖子权重，慎用会扫描全表
			try {
				topicService.refreshWeight();
				request.attribute(this.INFO, "权重刷新成功，请在首页进行查看。");
			} catch (Exception e) {
				e.printStackTrace();
				request.attribute(this.ERROR, "刷新失败");
			}
		}else if(type.equals("refresh_count")){
			try {
				settingsService.refreshCount();
				Constant.SYS_INFO = settingsService.getSystemInfo();
				Constant.VIEW_CONTEXT.set("sys_info", Constant.SYS_INFO);
				request.attribute(this.INFO, "社区运行状况刷新成功，请在首页进行查看。");
			} catch (Exception e) {
				e.printStackTrace();
				request.attribute(this.ERROR, "刷新失败");
			}
		}else if(type.equals("refresh_banner")){
			try {
				QueryParam np1 = QueryParam.me();
				np1.eq("is_del", 0).orderby("sort asc");
				List<Banner> bannerList=bannerService.getBannerList(np1);
				for(Banner banner:bannerList){
					banner.setPath(Utils.getPhoto(banner.getPath(),""));
				}
				Constant.VIEW_CONTEXT.set("banners", bannerList);
				request.attribute(this.INFO, "banner刷新成功，请在首页进行查看。");
			} catch (Exception e) {
				e.printStackTrace();
				request.attribute(this.ERROR, "刷新失败");
			}
		}
		return this.getAdminView("tools");
	}
	
	/**
	 * 用户列表（弹框）
	 * @author WKX
	 * @param request
	 * @param response
	 * @since 2016年11月1日 下午8:16:29
	 */
	@Route(value = "users/panel/do")
	public void panelDo(Request request, Response response){
		Integer page = request.queryAsInt("pageIndex");
		String keyword = request.query("keyword");
		String nid = request.query("nid");
		if(null == page || page < 1){
			page = 1;
		}
		QueryParam up = QueryParam.me();
		List<Object> param = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("SELECT * FROM (SELECT u.*,nu.id node_user_id FROM (SELECT * FROM t_user WHERE 1=1) u LEFT JOIN t_node_user nu ON u.uid=nu.uid and nu.nid =?");
		param.add(nid);
		if(StringKit.isNotBlank(keyword)){
			sql.append(" WHERE u.login_name LIKE ?");
			param.add("%"+keyword+"%");
		}
		sql.append(")res ORDER BY res.node_user_id DESC ");
		up.add(sql.toString(),param.toArray());
		
		up.page(page, 10);
		Page<UserForm> userPage = nodeUserService.getPageList(up);
		request.attribute("userPage", userPage);
		this.success(response, userPage);
	}
	
	/**
	 * 设置版主
	 * @author WKX
	 * @param request
	 * @param response
	 * @since 2016年11月3日 上午10:33:03
	 */
	@Route(value = "nodeuser/set")
	public void set(Request request, Response response){
		Long nuid = request.queryAsLong("nuid");
		Long uid = request.queryAsLong("uid");
		Long nid = request.queryAsLong("nid");
		if(nuid!=null){
			nodeUserService.delete(nuid);
		}else{
			NodeUser nu = new NodeUser();
			nu.setNid(nid);
			nu.setUid(uid);
			nodeUserService.save(nu);
		}
		this.success(response, "");
	}
	
	/**
	 * 删除banner
	 * @author ZY
	 * @param request
	 * @param response
	 * 2017年2月4日上午10:23:53
	 */
	@Route(value = "banner/delete")
	public void delete(Request request,Response response){
		Long id=request.queryAsLong("id");
		if(id != null){
			bannerService.delete(id);
			this.success(response, "删除成功");
		}else{
			this.error(response, "删除失败");
		}
	}
}