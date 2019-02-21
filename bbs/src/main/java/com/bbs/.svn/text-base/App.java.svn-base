package com.bbs;

import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import com.bbs.ext.Funcs;
import com.bbs.ext.Methods;
import com.bbs.kit.HttpUtil;
import com.bbs.kit.Result;
import com.bbs.kit.Utils;
import com.bbs.model.Banner;
import com.bbs.service.BannerService;
import com.bbs.service.SettingsService;
import com.blade.Blade;
import com.blade.Bootstrap;
import com.blade.context.BladeWebContext;
import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.DB;
import com.blade.jdbc.QueryParam;
import com.blade.jdbc.cache.memory.FIFOCache;
import com.blade.web.verify.CSRFConfig;
import com.blade.web.verify.CSRFTokenManager;
import com.bladejava.view.template.JetbrickTemplateEngine;

import blade.kit.AES;
import blade.kit.json.JSONKit;
import jetbrick.template.resolver.GlobalResolver;

public class App extends Bootstrap {
	
	@Inject
	private SettingsService settingsService;
	
	@Inject
	private BannerService bannerService;
	
	@Override
	public void init(Blade blade) {
		// 模板引擎
		JetbrickTemplateEngine jetbrickTemplateEngine = new JetbrickTemplateEngine(BladeWebContext.servletContext());
		
		GlobalResolver resolver = jetbrickTemplateEngine.getJetEngine().getGlobalResolver();
		resolver.registerFunctions(Funcs.class);
		resolver.registerMethods(Methods.class);
		Constant.VIEW_CONTEXT = jetbrickTemplateEngine.getJetEngine().getGlobalContext();
		blade.viewEngin(jetbrickTemplateEngine);
		
		// 配置数据库
		DB.open(blade.config().get("jdbc.driver"), 
				blade.config().get("jdbc.url"), 
				blade.config().get("jdbc.user"), 
				blade.config().get("jdbc.pass"), true);
		
		// 开启数据库缓存
		if(blade.config().getAsBoolean("app.db_cahce")){
			DB.setCache(new FIFOCache());
		}
		
		// 初始化配置
		Constant.IS_DEV = blade.isDev();
		Constant.SITE_URL = blade.config().get("app.site_url");
		Constant.APP_VERSION = blade.config().get("app.version");
		AES.setKey(blade.config().get("app.aes_salt"));
		Constant.CDN_URL = blade.config().get("qiniu.cdn");
		Constant.FAMOUS_KEY = blade.config().get("famous.key");
		
		Constant.IMG_PATH = blade.config().get("img.preimgUrl");//图片路径（上传图片的路径）
		Constant.APP_URL = blade.config().get("app.url");//跨网调用服务（首页报价、意见反馈）
		Constant.MOBILE_URL = blade.config().get("mobile.url");//rymobile项目地址（邀请好友用）
		
		// csrf 防御
		CSRFConfig conf = new CSRFConfig();
		conf.setSecret(blade.config().get("app.aes_salt"));
		conf.setSetHeader(true);
		CSRFTokenManager.config(conf);
		
		// 配置邮箱
		Constant.MAIL_API_USER = blade.config().get("mail.api_user");
		Constant.MAIL_API_KEY = blade.config().get("mail.api_key");
		
		// github授权key
		Constant.GITHUB_CLIENT_ID = blade.config().get("github.CLIENT_ID");
		Constant.GITHUB_CLIENT_SECRET = blade.config().get("github.CLIENT_SECRET");
		Constant.GITHUB_REDIRECT_URL = blade.config().get("github.REDIRECT_URL");
		
		Timer timer = new Timer();//实例化Timer类
		timer.schedule(new TimerTask() {
			public void run() {
				initRegion();//初始化省市区
				this.cancel();
			}
		},20000);//20秒
	}
	
	@Override
	public void contextInitialized(Blade blade) {
		settingsService.refreshCount();
		Constant.SYS_INFO = settingsService.getSystemInfo();
		Constant.VIEW_CONTEXT.set("base", blade.config().get("app.site_url"));//BladeWebContext.servletContext().getContextPath()
		Constant.VIEW_CONTEXT.set("version", Constant.APP_VERSION);
		Constant.VIEW_CONTEXT.set("cdn", Constant.CDN_URL);
		Constant.VIEW_CONTEXT.set(Map.class, "sys_info", Constant.SYS_INFO);//系统初始化时获取BBS信息（后台可以刷新运营状况）
		Constant.VIEW_CONTEXT.set(String.class, "site_url", Constant.SITE_URL);
		//banner设置
		QueryParam np1 = QueryParam.me();
		np1.eq("is_del", 0).orderby("sort asc");
		List<Banner> bannerList=bannerService.getBannerList(np1);
		for(Banner banner:bannerList){
			banner.setPath(Utils.getPhoto(banner.getPath(),""));
		}
		Constant.VIEW_CONTEXT.set(List.class, "banners", bannerList);//系统初始化时获取BBS信息（后台可以刷新运营状况）		
		//静态文件配置路径（image、css、js等）
		Constant.VIEW_CONTEXT.set("imgPath", blade.config().get("path.img"));
		Constant.VIEW_CONTEXT.set("staticPath", blade.config().get("path.static"));
	}
	
	/**
	 * 初始化获取省市区
	 * @author WKX
	 * @param request
	 * @param response
	 * @since 2016年11月19日 下午4:25:51
	 */
	public void initRegion(){
		try {
			Result r = HttpUtil.sendPost(Constant.APP_URL+"/app/region", null,null,"utf-8");
			if(r!=null){
				HttpEntity he = r.getHttpEntity();
				String res = EntityUtils.toString(he);
				Constant.APP_REGION = JSONKit.parseObject(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}