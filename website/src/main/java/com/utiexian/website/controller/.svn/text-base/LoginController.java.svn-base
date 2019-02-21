package com.utiexian.website.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Appimage;
import com.ry.core.entity.ClickCount;
import com.ry.core.entity.Gongcui;
import com.ry.core.entity.Member;
import com.ry.core.entity.News;
import com.ry.core.entity.Org;
import com.ry.core.entity.Shibor;
import com.ry.core.entity.SiteContent;
import com.ry.core.service.ClickCountService;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.GongcuiService;
import com.ry.core.service.MemberService;
import com.ry.core.service.NewsService;
import com.ry.core.service.OrgService;
import com.ry.core.service.PicmanageService;
import com.ry.core.service.ShiborService;
import com.ry.core.service.SiteContentService;
import com.ry.core.service.SysteminfoService;
import com.ry.core.service.VariablesService;
import com.ry.core.util.Constant;
import com.ry.util.DateUtil;
import com.ry.util.HttpUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.MD5Util;
import com.ry.util.StringUtil;

@Controller
public class LoginController {
	
	@Resource
	MemberService memberService;
	
	@Resource
	ShiborService shiborService;
	
	@Resource
	GongcuiService gongcuiService;
	
	@Resource
	OrgService orgService;
	
	@Resource					
	NewsService newsService;
	
	@Resource					
	SysteminfoService systeminfoService;
	
	@Resource
	PicmanageService picmanageService;
	
	@Resource
	DiscountrecordService discountrecordService;
	
	@Resource
	VariablesService variablesService;
	
	@Resource
	SiteContentService siteContentService;
	
	@Resource
	private ClickCountService clickCountService;
	
	/**
	 * 登录页面
	 * @author WKX
	 * @param redirect_url
	 * @param model
	 * @since 2016年10月20日 下午6:34:11
	 */
	@RequestMapping("/login")
	public String login(String redirect_url,Model model){
		model.addAttribute("redirect_url", redirect_url);
		return "login";
	}
	
	/**
	 * 首页
	 * @author WKX
	 * @param model
	 * @since 2016年10月20日 下午6:53:35
	 */
	@RequestMapping("/index")
	public String index(String from,HttpServletRequest request){
		if(StringUtils.isNotBlank(from))request.setAttribute("from", from);//来源（品友统计用）
		
		Map<String, Object> result = getJye();
		request.setAttribute("month", reverse(result.get("month").toString()));
		request.setAttribute("all",  reverse(result.get("all").toString()));
		//bannner
		List<Appimage> banners = picmanageService.getPicList("index_pc");
		request.setAttribute("banners", banners);
		
		//shibor查询
		List<Shibor> shibors = shiborService.getList(0, 1);
		request.setAttribute("shibor", shibors.get(0));
		//工催
		List<Gongcui> gongcuis = gongcuiService.getList(0, 5);
		request.setAttribute("gongcuiList", gongcuis);
		
		//第一条图片新闻
		SiteContent siteContent = siteContentService.get();
		if(siteContent!=null && StringUtils.isNotBlank(siteContent.getAbstracts()) && siteContent.getAbstracts().length()>=50){
			siteContent.setAbstracts(siteContent.getAbstracts().substring(0,50)+"...");
		}
		request.setAttribute("news", siteContent);
		
		//市场信息
		List<News> newslist = newsService.getList(0, 3);
		List<News> NewsList = new ArrayList<News>();
		if(!(newslist == null || newslist.size() == 0)){
			for(News news : newslist){					
				String title = news.getTitle();
				String content = news.getContent();
				news.setContentShow(content);
				news.setPublishtimeStr(new SimpleDateFormat("yyyy-MM-dd").format(news.getPublishtime()));
				if(title!=null) news.setTitleShow(title.length()>30?title.substring(0,30)+"...":title);
				NewsList.add(news);
			}
//			News new1=NewsList.get(0);
//			if(new1.getAbstracts()!=null){
//				new1.setAbstracts(new1.getAbstracts().length()>30?new1.getAbstracts().substring(0,30)+"...":new1.getAbstracts());
//			}
//			request.setAttribute("news", new1);
//			NewsList.remove(0);
		}
		request.setAttribute("newsList", NewsList);
		return "index";
	}
	
	/**
	 * 首页（新浪推广等页面使用）
	 * @author WKX
	 * @param src 来源（sina）
	 */
	@RequestMapping(value={"/index/{src}"})
	public String index(@PathVariable("src") String src,HttpServletRequest req,Model model){
		if(StringUtils.isNotBlank(src)){
			ClickCount clickCount = new ClickCount();
			clickCount.setUuid(req.getParameter("uuid"));
			clickCount.setIp(HttpUtil.getIpAddr(req));
			clickCount.setStyle("PC");
			clickCount.setCurrentDate(new Date());
			clickCount.setCode(src);
			clickCount.setReferrerUrl(req.getRequestURI());
			clickCount.setUrl(req.getRequestURL().toString());
			
			Member member = (Member) req.getSession().getAttribute("member");
			if(member!=null && member.getId()!=null){
				clickCount.setMemberId(member.getId());
			}
			
			clickCountService.saveClickCount(clickCount);
		}
		return index(null,req);
	}
	
	//首页-专业、安全、便捷
	@RequestMapping("/index/introduce")
	public String introduce(HttpServletRequest request){
		return "/introduce";
	}
	
	/**
	 * 用户密码登录
	 * @author KHC
	 * @param mobile
	 * @param pwd
	 * @param sessionTime 登陆的有效天数（单位：天）
	 * @param redirect_url
	 * @param request
	 * @param model
	 * @throws Exception 
	 * @since 2016年10月26日 下午2:13:16
	 */
	@RequestMapping("/member/login")
	public String login(String mobile, String pwd,Integer sessionTime,String redirect_url,HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		request.getSession().setMaxInactiveInterval(24*60*60);
		if(StringUtils.isNotBlank(mobile)){
			List<Member> membersList = memberService.getList(mobile, null);
			if(membersList!=null && membersList.size()>0){
				List<Member> members = memberService.getList(mobile, MD5Util.getMD5Str(pwd));
				if(members!=null && members.size()>0){
					Member member = members.get(0);
					request.getSession().setAttribute("member", member);
					map.put("memberId", member.getId());
					map.put("response", "success");
					map.put("redirect_url", redirect_url);
					
					if(sessionTime != null && sessionTime >0){
						String value = mobile + ","+MD5Util.getMD5Str(mobile+"SIGN:@UTIEXIAN@50965066"+member.getId());
						System.out.println(value);
						Cookie userCookie=new Cookie("nopwdlogin",value); 
					    userCookie.setMaxAge(sessionTime*24*60*60);  //存活期为天数*24*60*60
					    userCookie.setPath("/");
					    response.addCookie(userCookie); 
					}

					if(member!=null){//未读消息
						Integer message = systeminfoService.getMessage(member.getId());
						if(message!=null)request.getSession().setAttribute("MEMBER_MESSAGE", "("+message+")");
					}
					
					Org org = orgService.getByMemberId(member.getId());
					if(org != null){
						request.getSession().setAttribute("orgId", org.getId());
					}
				}else{
					map.put("response", "failed");
					map.put("msg", "手机号或密码错误");
				}
			}else{
				map.put("response", "failed");
				map.put("msg", "手机号不存在");
			}
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(map));
		return "ajax";
	}
	
	/**
	 * 快速登录
	 * @author KHC
	 * @param mobile
	 * @param code
	 * @param redirect_url
	 * @param request
	 * @param model
	 * @since 2016年10月26日 下午4:56:28
	 */
	@RequestMapping("/member/quicklogin")
	public String loginquick(String mobile, String code,String redirect_url,HttpServletRequest request,Model model){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String sessioncode = (String)request.getSession().getAttribute("code");
			String sessionmobile = (String)request.getSession().getAttribute("mobile");
			if(!code.equals(sessioncode) || !mobile.equals(sessionmobile)){
				map.put("msg","codeError");
				model.addAttribute("retValue", JacksonUtil.objToJson(map));
				return "ajax";
			}
			List<Member> members = memberService.getListByMobile(mobile);
			if(members!=null && members.size()>0){
				Member member = members.get(0);
				request.getSession().setAttribute("member", member);
				
				Org org = orgService.getByMemberId(member.getId());
				if(org != null){
					request.getSession().setAttribute("orgId", org.getId());
				}
				
				if(member!=null){//未读消息
					Integer message = systeminfoService.getMessage(member.getId());
					if(message!=null)request.getSession().setAttribute("MEMBER_MESSAGE", "("+message+")");
				}
				map.put("memberId", member.getId());
				map.put("response", "success");
				map.put("redirect_url", redirect_url);
			}else{
				Member member = new Member();
				Org org = new Org();
				member.setMobile(mobile);
				member.setRegtime(new Date());
				member.setQudao("PC");			
				memberService.addMember(member);
				member = Member.deMember(member);
				org.setMemberId(member.getId());
				org.setType(1);
				org.setState(0);
				org.setCreateTime(new Date());
				org = orgService.saveModel(org);
				request.getSession().setAttribute("member", member);
				request.getSession().setAttribute("orgId", org.getId());
				map.put("memberId", member.getId());
				map.put("response", "success");
				map.put("redirect_url", redirect_url);
			}
			
			model.addAttribute("retValue", JacksonUtil.objToJson(map));
			return "ajax";
		} catch (Exception e) {
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "登录失败");
			model.addAttribute("retValue", JacksonUtil.objToJson(map));
			return "ajax";
		}
	}
	
	/**
	 * 不用密码登录
	 * @author MH
	 * @param mobile
	 * @param pwd
	 * @param request
	 */
	@RequestMapping("/member/nopwdlogin")
	public String loginnopwd(String mobile,String pwd,HttpServletRequest request,HttpServletResponse response,Model model){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(mobile == null || mobile == "")throw new Exception();
			if(pwd == null || pwd == "")throw new Exception();
			List<Member> members = memberService.getListByMobile(mobile);
			request.getSession().setMaxInactiveInterval(24*60*60);
			if(members!=null && members.size()>0){
				Member member = members.get(0);
				if(pwd.equals(MD5Util.getMD5Str(mobile+"SIGN:@UTIEXIAN@50965066"+member.getId()))){
					request.getSession().setAttribute("member", member);
					
					Org org = orgService.getByMemberId(member.getId());
					if(org != null){
						request.getSession().setAttribute("orgId", org.getId());
					}
					
					if(member!=null){//未读消息
						Integer message = systeminfoService.getMessage(member.getId());
						if(message!=null)request.getSession().setAttribute("MEMBER_MESSAGE", "("+message+")");
					}
					map.put("memberId", member.getId());
					map.put("response", "success");
				}
			}
			model.addAttribute("retValue", JacksonUtil.objToJson(map));
			return "ajax";
		} catch (Exception e) {
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "登录失败");
			model.addAttribute("retValue", JacksonUtil.objToJson(map));
			return "ajax";
		}
	}
	
	/**
	 * 登出
	 * @author WKX
	 * @param request
	 * @since 2016年10月20日 下午4:56:22
	 */
	@RequestMapping("/member/logout")
	public String login(HttpServletRequest request,HttpServletResponse response){
		request.getSession().removeAttribute("member");
		request.getSession().removeAttribute("orgId");
//		String url = request.getRequestURL().toString();
//		if(url.indexOf("utiexian.com")>-1){
//			return "redirect:http://www.utiexian.com";//退出时跳到首页
//		}else{
//			return StringUtil.processRedirectUrl(Constant.properties.getProperty("baseUrl"), "/index.html");//退出时跳到首页
//		}
		Cookie[] cookies = request.getCookies();  
        if (cookies != null) {  
            for(Cookie cookie : cookies){  
                if(cookie.getName().equals("nopwdlogin")){  
                    cookie.setValue(null);  
                    cookie.setMaxAge(0);// 立即销毁cookie  
                    cookie.setPath("/");
                    response.addCookie(cookie);  
                }
            }
        }  
		return StringUtil.processRedirectUrl(Constant.properties.getProperty("baseUrl"), "");
	}
	
	/**
	 * 跳转页面，传值（表单的形式）
	 * @author WKX
	 * @param _PAGE 打开的页面
	 * @param request
	 * @param model
	 * @since 2016年11月1日 下午5:22:11
	 */
	@RequestMapping("/open/page")
	public String open(String _PAGE,HttpServletRequest request, Model model){
        Enumeration<?> paramNames = request.getParameterNames();  
        while (paramNames.hasMoreElements()) {  
            String paramName = (String) paramNames.nextElement();  
  
            String[] paramValues = request.getParameterValues(paramName);  
            if (paramValues.length == 1) {  
                String paramValue = paramValues[0];  
                if (paramValue.length() != 0) {
                    if(!"_PAGE".equals(paramName))model.addAttribute(paramName, paramValue);
                }
            }
        }
		if(StringUtils.isNotBlank(_PAGE)){
			return _PAGE;
		}else{
			return "index";
		}
	}
	
	/**
	 * 金额每三位加，来显示
	 * @author ZY
	 * @param money  传进来的参数
	 * 2016年11月29日下午1:45:47
	 */
	private String reverse(String money){
		String str2="";
		String str1=new StringBuffer(money).reverse().toString();
		for(int i=0;i<str1.length();i++){
			if(i*3+3>=str1.length()){
				str2 += str1.substring(i*3);
				break;
			}
			str2 += str1.substring(i*3, i*3+3)+",";
		}
		if(str2.endsWith(",")){//这一步其实只是不需要了
			str2 = str2.substring(0, str2.length()-1);
		}
		return new StringBuffer(str2).reverse().toString();
	}
	
	/**
	 * 缓存交易额
	 */
	private static Map<String,Object> index_params = null;
	private Map<String,Object> getJye(){
		if(index_params!=null){
			if(index_params.get("date")!=null){
				String tag1 = index_params.get("date").toString();
				String tag2 = DateUtil.formart(new Date(),"yyyy-MM-dd");
				if(!tag1.equals(tag2)){//已过期
				}else{//未过期
					return index_params;
				}
			}else{//缓存中不存在过期标示
			}
		}
		index_params = new HashMap<String, Object>();
		//缓存中没有数据需要重新获取
		Double month = discountrecordService.getMoneyLastMonth();
		Double temp = discountrecordService.getMoneyAllFinish();
		if(month==null){
			month = 0.0;
		}
		if(temp == null){
			temp = 0.0;
		}
		Double allMoney = temp + 600000;
		String add = variablesService.getByCode("ADD_TURNOVER", null);//获取额外配置额
		if(StringUtils.isNotBlank(add)){
			allMoney += Double.valueOf(add);
		}
		String add_m = variablesService.getByCode("ADD_TURNOVER_MONTH", null);//获取额外配置额(上月交易额)
		if(StringUtils.isNotBlank(add_m)){
			month += Double.valueOf(add_m);
		}
		
		DecimalFormat df = new DecimalFormat("0");
		index_params.put("date", DateUtil.formart(new Date(),"yyyy-MM-dd"));
		index_params.put("month", df.format(month));
		index_params.put("all", df.format(allMoney));
		return index_params;
	}
}