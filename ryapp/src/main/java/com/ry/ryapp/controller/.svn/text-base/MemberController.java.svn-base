package com.ry.ryapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.HongbaoActivity;
import com.ry.core.entity.Member;
import com.ry.core.entity.Message;
import com.ry.core.entity.Org;
import com.ry.core.entity.Servicemember;
import com.ry.core.entity.Tag;
import com.ry.core.entity.ThirdAuth;
import com.ry.core.service.HongbaoService;
import com.ry.core.service.MemberService;
import com.ry.core.service.MessageService;
import com.ry.core.service.OrgImageService;
import com.ry.core.service.OrgInfoService;
import com.ry.core.service.OrgPartnerService;
import com.ry.core.service.OrgService;
import com.ry.core.service.ServicememberService;
import com.ry.core.service.TagService;
import com.ry.core.service.ThirdAuthService;
import com.ry.core.util.SendMessagesUtil;
import com.ry.ryapp.util.JsonUtil;
import com.ry.util.HttpUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.MD5Util;
import com.ry.util.PropertiesUtil;

import cn.jimmyshi.beanquery.BeanQuery;
import net.sf.json.JSONArray;

/**
 * @author RY
 * @date 2016年1月4日
 * 用户信息管理controller(用户登录,注册,修改密码)
 */
@Controller
public class MemberController {

	@Resource
	MemberService memberService;

	@Resource
	MessageService messageService;

	@Resource
	HongbaoService hongbaoService;

	@Resource
	ServicememberService servicememberService;
	
	@Resource
	TagService tagService;
	
	@Resource
	OrgInfoService orgInfoService;
	
	@Resource
	ThirdAuthService thirdAuthService;
	
	@Resource
	private OrgService orgService;
	
	@Resource
	OrgImageService orgImageService;
	
	@Resource
	OrgPartnerService orgPartnerService;
	
	@RequestMapping("/app/quicklogin/")
	public void quicklogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		
		try{
			String mobile = request.getParameter("mobile");			
			String code = request.getParameter("code");			
			String sessioncode = (String)request.getSession().getAttribute("code");
			if(!code.equals(sessioncode)){
				map.put("response", "failed");
				map.put("msg", "验证码不正确");
				out.print(JSONArray.fromObject(map));
				return;
			}
			HongbaoActivity a = new HongbaoActivity();
			a.setStatus(0);
			a.setFlag(1);
			List<HongbaoActivity> list = hongbaoService.getActivitys(a);	
			List<Member> baseEntityList = memberService.getListByMobile(mobile);			
			if(baseEntityList!=null&&baseEntityList.size()!=0){				
				request.getSession().setAttribute("member", baseEntityList.get(0));
				map.put("response", "success");
				map.put("msg", baseEntityList.get(0));					
				if (list != null && list.size()>0) {
					map.put("hid", list.get(0).getId());
				} 
			} else {
				Member member = new Member();
				member.setMobile(mobile);
				member.setRegtime(new Date());
				member.setQudao("APP");				
				memberService.addMember(member);
				member = Member.deMember(member);//@WKX EDIT2016-08-19 手机号转码
				request.getSession().setAttribute("member", member);
				map.put("response", "success");
				map.put("msg", member);
				if (list != null && list.size()>0) {
					map.put("hid", list.get(0).getId());
				} 
			}
			out.print(JSONArray.fromObject(map));			
		}catch(Exception e){
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "登录失败");
			out.print(JSONArray.fromObject(map));
		}
	}
	
	@RequestMapping("/app/login/")
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			String mobile = request.getParameter("mobile");
			String pwd = request.getParameter("pwd");
			List<Member> members = memberService.getList(mobile, MD5Util.getMD5Str(pwd));
			HongbaoActivity a = new HongbaoActivity();
			a.setStatus(0);
			a.setFlag(1);
			List<HongbaoActivity> list = hongbaoService.getActivitys(a);			
			if(members!=null&&members.size()!=0){
				request.getSession().setAttribute("member", members.get(0));
				map.put("response", "success");
				map.put("msg", members.get(0));
				if (list != null && list.size()>0) {
					map.put("hid", list.get(0).getId());
				} 
				out.print(JSONArray.fromObject(map));
			}else{
				map.put("response", "failed");
				map.put("msg", "手机号和密码不匹配");
				out.print(JSONArray.fromObject(map));
			}
		}catch(Exception e){
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "登录失败");
			out.print(JSONArray.fromObject(map));
		}
	}
	
	@RequestMapping("/app/register/")
	public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			String mobile = request.getParameter("mobile");
			String pwd = request.getParameter("pwd");
			String recommendpeople = request.getParameter("recommendpeople");
			String code = request.getParameter("code");
			List<Member> baseEntityList = memberService.getListByMobile(mobile);
			if(!(recommendpeople==null||"".equals(recommendpeople))){
				Pattern pattern = Pattern.compile("[0-9]*"); 
				if(recommendpeople.length()!=3&&!pattern.matcher(recommendpeople).matches()){
					map.put("response", "failed");
					map.put("msg", "推荐人为3位数字");
					out.print(JSONArray.fromObject(map));
					return;
				} else {
					Servicemember servicemember = servicememberService.getServicemember(recommendpeople);
					if (servicemember == null) {
						map.put("response", "failed");
						map.put("msg", "无此推荐人");
						out.print(JSONArray.fromObject(map));
						return;
					}
				}
			}
			if(baseEntityList!=null&&baseEntityList.size()!=0){
				map.put("response", "failed");
				map.put("msg", "手机号码已经存在");
				out.print(JSONArray.fromObject(map));
				return;
			}
			String sessioncode = (String)request.getSession().getAttribute("code");
			if(!code.equals(sessioncode)){
				map.put("response", "failed");
				map.put("msg", "验证码不正确");
				out.print(JSONArray.fromObject(map));
				return;
			}
			Member member = new Member();
			member.setMobile(mobile);
			member.setPwd(MD5Util.getMD5Str(pwd));
			member.setRecommendpeople(recommendpeople);
			member.setRegtime(new Date());
			member.setQudao("APP");
			memberService.addMember(member);
			member = Member.deMember(member);//@WKX EDIT2016-08-19 手机号转码
			map.put("response", "success");
			map.put("msg", member);
			out.print(JSONArray.fromObject(map));
		}catch(Exception e){
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "注册失败");
			out.print(JSONArray.fromObject(map));
		}
	}

	/**
	 * 快速登录[新]
	 * @author RY
	 * @param request
	 * @param response
	 * @throws IOException
	 * 快速登录的加密处理
	 */
	@RequestMapping("/app/quicklogin/new")
	public void quickloginNew(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String sessionTime = (String) request.getSession().getAttribute("time");
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, String> qudaoMap = new HashMap<String, String>();
		
		//渠道大类主键
		String qdId = PropertiesUtil.getConfigPropertiesValue("QUDAO","2");
		List<Tag> list = tagService.getByParentId(Integer.valueOf(qdId));
		if(list!=null && list.size()>0){
			int flag = 0;
			for(Tag t:list){
				qudaoMap.put("qudao"+flag,sessionTime+t.getId());
				flag ++;
			}
		}
		//添加此段代码为前台传入一个渠道字符串在此取出解密判断
//		for(int i=1;i<=15;i++){
//			qudaoMap.put("qudao"+i,sessionTime+"Q"+i);
//		}
		
		Set<Entry<String, String>> entrySet = qudaoMap.entrySet();
		List<Entry<String,String>> list1 = new ArrayList<Entry<String,String>>();
		list1.addAll(entrySet);
		try {
			String mobile = request.getParameter("mobile");
			String code = request.getParameter("code");
			String sessioncode = (String) request.getSession().getAttribute("code");
			String qudao = request.getParameter("timestamp");
			boolean b = false;
			for (int i = 0; i < list1.size(); i++) {
				Entry<String,String> entry = list1.get(i);
				String value = entry.getValue();
				if(MD5Util.getMD5Str(value).equals(qudao)){
					b = true;
					break;
				}
			}
			if(!b){
				return;
			}
			if (!code.equals(MD5Util.getMD5Str(sessioncode))) {
				map.put("response", "failed");
				map.put("msg", "验证码不正确");
				out.print(JSONArray.fromObject(map));
				return;
			}
			//获取红包分类主键（已贴现完成）
			String tagId = PropertiesUtil.getConfigPropertiesValue("XIEXIANTAGID","101");
			
			HongbaoActivity hongbao = hongbaoService.getActivityByTagId(Integer.valueOf(tagId));
			List<Member> baseEntityList = memberService.getListByMobile(mobile);
			if (baseEntityList != null && baseEntityList.size()>0) {
				Member temp = baseEntityList.get(0);
				request.getSession().setAttribute("member",temp);
				map.put("response", "success");
				map.put("msg", temp);
				if (hongbao != null)map.put("hid", hongbao.getId());
				String type = request.getParameter("type");
				if(type!=null){//是第三方登录 存入第三方信息
					List<ThirdAuth> tList = thirdAuthService.getByAttr(temp.getId(), null, null, null);
					ThirdAuth t;
					if(tList.isEmpty()){
						t = new ThirdAuth();
					}else{
						t=tList.get(0);
					}
					if("1".equals(type)){
						t.setWechatId(request.getParameter("openid"));
						t.setWechatToken(request.getParameter("token"));
					}else if("2".equals(type)){
						t.setQqId(request.getParameter("openid"));
						t.setQqToken(request.getParameter("token"));
					}
					else if("3".equals(type)){
						t.setSinaId(request.getParameter("openid"));
						t.setSinaToken(request.getParameter("token"));
					}
					t.setMemberId(temp.getId());
					thirdAuthService.saveOrUpdate(t);
				}
			} else {
				Member member = new Member();
				member.setIp(HttpUtil.getIpAddr(request));//记录注册时的IP
				member.setMobile(mobile);
				member.setRegtime(new Date());
				member.setQudao("APP");
				memberService.addMember(member);
				member = Member.deMember(member);//@WKX EDIT2016-08-19 手机号转码
				request.getSession().setAttribute("member", member);
				map.put("response", "success");
				map.put("msg", member);
				if (hongbao != null)map.put("hid", hongbao.getId());
				String type = request.getParameter("type");
				if(type!=null){//是第三方登录 存入第三方信息
					List<ThirdAuth> tList = thirdAuthService.getByAttr(member.getId(), null, null, null);
					ThirdAuth t;
					if(tList.isEmpty()){
						t = new ThirdAuth();
					}else{
						t=tList.get(0);
					}
					if("1".equals(type)){
						t.setWechatId(request.getParameter("openid"));
						t.setWechatToken(request.getParameter("token"));
					}else if("2".equals(type)){
						t.setQqId(request.getParameter("openid"));
						t.setQqToken(request.getParameter("token"));
					}
					else if("3".equals(type)){
						t.setSinaId(request.getParameter("openid"));
						t.setSinaToken(request.getParameter("token"));
					}
					t.setMemberId(member.getId());
					thirdAuthService.saveOrUpdate(t);
				}
			}
			out.print(JSONArray.fromObject(map));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "登录失败");
			out.print(JSONArray.fromObject(map));
		}
	}
	
	/**
	 * 密码登录[新]
	 * @author RY
	 * @param mobile
	 * @param code
	 * @param pwd
	 * @param request
	 * @param response
	 * @throws IOException
	 * 用户登录验证,判断手机号是否注册,密码是否正确
	 */
	@RequestMapping("/app/login/new")
	public void loginNew(String mobile,String pwd,HttpServletRequest request, HttpServletResponse response)throws Exception {
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		PrintWriter out = response.getWriter();
		List<Member> memList = memberService.getListByMobile(mobile);
		if(memList == null || memList.size() == 0){
			map.put("response", "failed");
			map.put("msg", "手机号未注册");
			mapList.add(map);
			out.print(JsonUtil.listMap2json(mapList));
			return;
		}
		if(StringUtils.isEmpty(memList.get(0).getPwd())){
			map.put("response", "failed");
			map.put("msg", "您还没有设置密码，请重新去设置密码吧");
			mapList.add(map);
			out.print(JsonUtil.listMap2json(mapList));
			return;
		}
		
		//****验证时间戳****
		Map<String, String> qudaoMap = new HashMap<String, String>();
		String sessionTime = (String) request.getSession().getAttribute("time");
		String qudao = request.getParameter("timestamp");
//		for(int i=1;i<=15;i++){//添加此段代码为前台传入一个渠道字符串在此取出解密判断
//			qudaoMap.put("qudao"+i,sessionTime+"Q"+i);
//		}
		
		//渠道大类主键
		String qdId = PropertiesUtil.getConfigPropertiesValue("QUDAO","2");
		List<Tag> list = tagService.getByParentId(Integer.valueOf(qdId));
		if(list!=null && list.size()>0){
			int flag = 0;
			for(Tag t:list){
				qudaoMap.put("qudao"+flag,sessionTime+t.getId());
				flag ++;
			}
		}
		
		Set<Entry<String, String>> entrySet = qudaoMap.entrySet();
		List<Entry<String,String>> list1 = new ArrayList<Entry<String,String>>();
		list1.addAll(entrySet);
		boolean b = false;
		for (int i = 0; i < list1.size(); i++) {
			Entry<String,String> entry = list1.get(i);
			String value = entry.getValue();
			if(MD5Util.getMD5Str(value).equals(qudao)){
				b = true;
				break;
			}
		}
		if(!b){
			return;
		}
		//验证账号和密码
		List<Member> members = memberService.getList(mobile,pwd);
		//获取红包分类主键（已贴现完成）
		String tagId = PropertiesUtil.getConfigPropertiesValue("XIEXIANTAGID","101");
		
		HongbaoActivity hongbao = hongbaoService.getActivityByTagId(Integer.valueOf(tagId));
		if (members != null && members.size()>0) {
			request.getSession().setAttribute("member", members.get(0));
			map.put("response", "success");
			map.put("msg", members.get(0));
			if (hongbao != null)map.put("hid", hongbao.getId());
			out.print(JSONArray.fromObject(map));
		} else {
			map.put("response", "failed");
			map.put("msg", "密码不匹配");
			out.print(JSONArray.fromObject(map));
		}

	}

	/**
	 * 注册[新]
	 * @author RY
	 * @param request
	 * @param response
	 * @throws IOException
	 * @since 2016年1月20日 上午8:56:11
	 */
	@RequestMapping("/app/register/new")
	public void registerNew(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		String sessionTime = (String) request.getSession().getAttribute("time");
		Map<String, String> qudaoMap = new HashMap<String, String>();
		
		//添加此段代码为前台传入一个渠道字符串在此取出解密判断
//		for(int i=1;i<=15;i++){
//			qudaoMap.put("qudao"+i,sessionTime+"Q"+i);
//		}
		//渠道大类主键
		String qdId = PropertiesUtil.getConfigPropertiesValue("QUDAO","2");
		List<Tag> list = tagService.getByParentId(Integer.valueOf(qdId));
		if(list!=null && list.size()>0){
			int flag = 0;
			for(Tag t:list){
				qudaoMap.put("qudao"+flag,sessionTime+t.getId());
				flag ++;
			}
		}
		
		Set<Entry<String, String>> entrySet = qudaoMap.entrySet();
		List<Entry<String,String>> list1 = new ArrayList<Entry<String,String>>();
		list1.addAll(entrySet);
		
		String qudao = request.getParameter("timestamp");
		boolean b = false;
		for (int j = 0; j < list1.size(); j++) {
			Entry<String,String> entry = list1.get(j);
			String value = entry.getValue();
			if(MD5Util.getMD5Str(value).equals(qudao)){
				b = true;
				break;
			}
		}
		if(!b){
			return;
		}
		try {
			String mobile = request.getParameter("mobile");
			String pwd = request.getParameter("pwd");
			String recommendpeople = request.getParameter("recommendpeople");
			String code = request.getParameter("code");
			List<Member> baseEntityList = memberService.getListByMobile(mobile);
			if (!(recommendpeople == null || "".equals(recommendpeople))) {
				Pattern pattern = Pattern.compile("[0-9]*");
				if (recommendpeople.length() != 3 && !pattern.matcher(recommendpeople).matches()) {
					map.put("response", "failed");
					map.put("msg", "推荐人为3位数字");
					out.print(JSONArray.fromObject(map));
					return;
				} else {
					Servicemember servicemember = servicememberService.getServicemember(recommendpeople);
					if (servicemember == null) {
						map.put("response", "failed");
						map.put("msg", "无此推荐人");
						out.print(JSONArray.fromObject(map));
						return;
					}
				}
			}
			if (baseEntityList != null && baseEntityList.size() != 0) {
				map.put("response", "failed");
				map.put("msg", "手机号码已经存在");
				out.print(JSONArray.fromObject(map));
				return;
			}
			String sessioncode = (String) request.getSession().getAttribute("code");
			if (!code.equals(sessioncode)) {
				map.put("response", "failed");
				map.put("msg", "验证码不正确");
				out.print(JSONArray.fromObject(map));
				return;
			}
			Member member = new Member();
			member.setIp(HttpUtil.getIpAddr(request));//记录注册时的IP
			member.setMobile(mobile);
			member.setPwd(MD5Util.getMD5Str(pwd));
			member.setRecommendpeople(recommendpeople);
			member.setRegtime(new Date());
			member.setQudao("APP");
			memberService.addMember(member);
			member = Member.deMember(member);//@WKX EDIT2016-08-19 手机号转码
			request.getSession().setAttribute("registerMember",member.getId());//APP2.1 存session（拦截器保存访问记录用）
			map.put("response", "success");
			map.put("msg", member);
			out.print(JSONArray.fromObject(map));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "注册失败");
			out.print(JSONArray.fromObject(map));
		}
	}

	/**
	 * @author GJJ
	 * @param request
	 * @param response
	 * @throws IOException
	 * 快速登录发送验证码，如果该手机号已存在则返回验证码，如果不存在则直接添加手机号
	 */
	@RequestMapping("/app/sendcode/")
	public void sendcode(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String flag = request.getParameter("flag");
			String mobile = request.getParameter("mobile");
			if ("reg".equals(flag)) {
				List<Member> baseEntityList = memberService.getListByMobile(mobile);
				if (baseEntityList != null && baseEntityList.size() != 0) {
					map.put("response", "failed");
					map.put("msg", "手机号码已经存在");
					out.print(JSONArray.fromObject(map));
					return;
				}
			} else if ("forget".equals(flag)) {
				List<Member> baseEntityList = memberService.getListByMobile(mobile);
				if (baseEntityList == null || baseEntityList.size() == 0) {
					map.put("response", "failed");
					map.put("msg", "手机号码不存在");
					out.print(JSONArray.fromObject(map));
					return;
				}
			}
			
			String code = String.valueOf((Math.random())).split("\\.")[1];
			code = code.substring(0,4);
			 

			if ("reg".equals(flag)) {
				com.ry.util.SendMessagesUtil.aliSendRegistMessage(mobile, code);
			} else if ("forget".equals(flag)) {
				com.ry.util.SendMessagesUtil.aliSendFindPwdMessage(mobile, code);
			} else if ("quicklogin".equals(flag)) {
				com.ry.util.SendMessagesUtil.aliSendIdentityMessage(mobile,code);
			} else {
				String message = "验证码:" + code + "【票据管家】";
				SendMessagesUtil.sendMessages(mobile, message);
			}

			request.getSession().setAttribute("code", code);
			map.put("response", "success");
			map.put("msg", "发送成功");
			out.print(JSONArray.fromObject(map));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "发送失败");
			out.print(JSONArray.fromObject(map));
		}
	}

	@RequestMapping("/app/updatepwd/")
	public void updatepwd(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String mobile = request.getParameter("mobile");
			String code = request.getParameter("code");
			String pwd = request.getParameter("pwd");
			String sessioncode = (String) request.getSession().getAttribute("code");
			if (!code.equals(sessioncode)) {
				map.put("response", "failed");
				map.put("msg", "验证码不正确");
				out.print(JSONArray.fromObject(map));
				return;
			}
			List<Member> baseEntityList = memberService.getListByMobile(mobile);
			if (baseEntityList != null && baseEntityList.size() != 0) {
				Member member = (Member) baseEntityList.get(0);
				member.setPwd(MD5Util.getMD5Str(pwd));
				memberService.updateMember(member);
				map.put("response", "success");
				map.put("msg", "修改成功");
				out.print(JSONArray.fromObject(map));
			} else {
				map.put("response", "failed");
				map.put("msg", "手机号码不存在");
				out.print(JSONArray.fromObject(map));
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "修改密码失败");
			out.print(JSONArray.fromObject(map));
		}
	}

	@RequestMapping("/app/update/")
	public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String memberid = request.getParameter("memberid");
			String username = request.getParameter("username");
			String weixin = request.getParameter("weixin");
			String email = request.getParameter("email");
			String qq = request.getParameter("qq");
			String zuoji = request.getParameter("zuoji");
			String gongsi = request.getParameter("gongsi");
			String zhiwu = request.getParameter("zhiwu");
			String province = request.getParameter("province");//省
			String city = request.getParameter("city");//市
			String place = request.getParameter("place");
			String recommendpeople = request.getParameter("recommendpeople");
			String headpic = request.getParameter("headpic");
			List<Member> baseEntityList = memberService.getMemberList(memberid);
			if (baseEntityList != null && baseEntityList.size() != 0) {
				Member member = (Member) baseEntityList.get(0);
				if (username != null && !"".equals(username)) {
					member.setUsername(username);
				}
				if (weixin != null && !"".equals(weixin)) {
					member.setWeixin(weixin);
				}
				if (email != null && !"".equals(email)) {
					member.setEmail(email);
				}
				if (qq != null && !"".equals(qq)) {
					member.setQq(qq);
				}
				if (zuoji != null && !"".equals(zuoji)) {
					member.setZuoji(zuoji);
				}
				if (gongsi != null && !"".equals(gongsi)) {
					member.setGongsi(gongsi);
				}
				if (zhiwu != null && !"".equals(zhiwu)) {
					member.setZhiwu(zhiwu);
				}
				if (StringUtils.isNotBlank(province)) {//设置省
					member.setProvince(province);
				}
				if (StringUtils.isNotBlank(city)) {//设置市
					member.setCity(city);
				}
				if (place != null && !"".equals(place)) {
					member.setPlace(place);
				}
				if (recommendpeople != null && !"".equals(recommendpeople)) {
					Pattern pattern = Pattern.compile("[0-9]*");
					if ((recommendpeople.length() != 3 || recommendpeople.length() != 4) && !pattern.matcher(recommendpeople).matches()) {
						map.put("response", "failed");
						map.put("msg", "推荐人为3位或4位数字");
						out.print(JSONArray.fromObject(map));
						return;
					}else{
						Servicemember servicemember = servicememberService.getServicemember(recommendpeople);
						if (servicemember == null) {
							map.put("response", "failed");
							map.put("msg", "无此推荐人");
							out.print(JSONArray.fromObject(map));
							return;
						}
					}
					member.setRecommendpeople(recommendpeople);
				}
				if (headpic != null && !"".equals(headpic)) {
					member.setHeadpic(headpic);
				}
				memberService.updateMember(member);
				map.put("response", "success");
				map.put("msg", "修改信息成功");
				out.print(JSONArray.fromObject(map));
			} else {
				map.put("response", "failed");
				map.put("msg", "用户不存在");
				out.print(JSONArray.fromObject(map));
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "修改失败");
			out.print(JSONArray.fromObject(map));
		}
	}

	/**
	 * 意见反馈（现在BBS也会调用）
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/app/message/")
	public void message(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			String messagecontent = request.getParameter("messagecontent");
			String messagenumber = request.getParameter("messagenumber");
			String memberid = request.getParameter("memberid");
			String source = request.getParameter("source");//来源（APP、PC、BBS）
			if(StringUtils.isBlank(source))source = "APP";
			
			Message message = new Message();
			if(StringUtils.isNotBlank(memberid))message.setMemberid(Integer.valueOf(memberid));
			
			if(StringUtils.isBlank(messagenumber) && StringUtils.isNotBlank(memberid)){
				Member member = memberService.getById(Integer.valueOf(memberid));
				message.setMessagenumber(member.getMobile());
			}else{
				message.setMessagenumber(messagenumber);
			}
			message.setMessagecontent(messagecontent);
			message.setMessagetime(new Date());
			message.setSource(source);//来源
			messageService.saveMessage(message);
			map.put("response", "success");
			map.put("msg", "反馈成功");
			out.print(JSONArray.fromObject(map));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "反馈失败");
			out.print(JSONArray.fromObject(map));
		}
	}
	
	/**
	 * @author GJJ
	 * @param request
	 * @param response
	 * @return map
	 * 获取时间戳返回
	 * @throws IOException 
	 */
	@RequestMapping("/app/timestamp/")
	public void time(HttpServletRequest request,HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		Map<String, Object> map = new HashMap<String, Object>();
		PrintWriter out = response.getWriter();
		long time = new Date().getTime();
		session.setAttribute("time", time+"");
		map.put("time", time);
		out.print(JSONArray.fromObject(map));
	}
	
	/**
	 * 根据用户主键获取邀请码及邀请人数
	 * @author WKX
	 * @param id
	 * @param model
	 * @throws IOException
	 * @since 2016年1月12日 下午2:49:35
	 */
	@RequestMapping("/app/member/get")
	public String getById(Integer id,Model model) throws IOException{
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Member member = memberService.getById(id);
			Map<String,Object> result = BeanQuery.select("id,recommendpeople,invitationCode,myInvitationCode").executeFrom(member);
			result.put("count",memberService.getByInvitationCode(member.getMyInvitationCode()));
			map.put("state","success");
			map.put("msg","操作成功");
			map.put("data",result);
		} catch (Exception e) {
			map.put("state","failed");
			map.put("msg","操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(map));
		return "ajax";
	}
	
	/**
	 * 更新用户邀请码
	 * @author WKX
	 * @param memberId 用户主键
	 * @param invitationCode 邀请码
	 * @param model
	 * @throws IOException
	 * @since 2016年1月12日 下午4:33:43
	 */
	@RequestMapping("/app/member/update")
	public String updateById(Integer memberId,String invitationCode,Model model) throws IOException{
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Member temp = memberService.getByMyInvitationCode(invitationCode);
			if(temp==null)throw new Exception("邀请码不正确");
			Member member = memberService.getById(memberId);
			if(member!=null && StringUtils.isNotBlank(member.getMyInvitationCode()) && invitationCode.equals(member.getMyInvitationCode()))throw new Exception("邀请码无效");
			member.setInvitationCode(invitationCode);
			memberService.updateMember(member);
			map.put("state","success");
			map.put("msg","操作成功");
		} catch (Exception e) {
			map.put("state","failed");
			map.put("msg","操作失败，"+e.getMessage());
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(map));
		return "ajax";
	}
	
	/**
	 * 查询登录用户的基本信息
	 * @param id 主键
	 */
	@RequestMapping("/app/searchMyInfoById")
	public String searchMyInfoById(Integer id,Integer type,Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Member member = memberService.getById(id);
			if(type==1){//机构端则显示合作机构
				result.put("partner", orgPartnerService.getByMemberId(member.getId()));
			}
			result.put("member",member);
			result.put("state","success");
			result.put("msg","操作成功");
		} catch (Exception e) {
			result.put("state","failed");
			result.put("msg","查询用户信息操作失败，"+e.getMessage());
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 查询登录用户的认证信息
	 */
	@RequestMapping("/app/search/memberAndInfo")
	public String memberAndInfo(Model model, Integer infoOrgId) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Map<String,Object> info = orgInfoService.getCurrentTypeInfoById(infoOrgId);
			if(info!=null && info.get("state")!=null){
				if ("0".equals(info.get("type_").toString())) {
					if ("0".equals(info.get("state").toString()) || "3".equals(info.get("state").toString())) {
					} else {						
						result.put("info", info);
					}
				}else {
					result.put("info", info);
				}
			} else {
				Org org = orgService.getById(infoOrgId);
				if (org != null) {
					Map<String,Object> infoState = new HashMap<String, Object>();
					if (org.getType() == 1) {
						infoState.put("state", 0);
					} else {
						infoState.put("state", 4);				
					}
					result.put("info", infoState);
				}
			}
			result.put("response","success");
			result.put("msg","操作成功");
		} catch (Exception e) {
			result.put("state","failed");
			result.put("msg","查询登录用户的认证信息操作失败，"+e.getMessage());
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * @APP2.2  更正上面的跳转错误问题
	 * 查询登录用户的认证信息
	 */
	@RequestMapping("/app/member/getInfo")
	public String getInfo(Model model, Integer infoOrgId,Integer memberId,Float version, Integer type) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(version!=null && version>=2.2F){//APP2.2新版本的方式
				Map<String,Object> info = orgInfoService.getByMemberIdAndType(memberId, type);
				if(info!=null && info.get("state")!=null){
					if("2".equals(info.get("state").toString()) && infoOrgId!=null && type==1){//机构（基本信息通过审核）
						Map<String,Object> org_img = orgImageService.getByOrgId(infoOrgId);
						if(org_img!=null){
							info.put("state", org_img.get("state"));
							result.put("info", info);
						}else{
							info.put("state", 1);//如果没有上传承诺函，就认为审核中
							result.put("info", info);
						}
					}
				}
				result.put("info", info);
			}else{
				Map<String,Object> info = orgInfoService.getByOrgIdAndType(infoOrgId, type);
				if(info!=null && info.get("state")!=null) {
					result.put("info", info);
				}
			}
			result.put("response","success");
			result.put("msg","操作成功");
		} catch (Exception e) {
			result.put("state","failed");
			result.put("msg","查询登录用户的认证信息操作失败，"+e.getMessage());
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 绑定
	 * @author 第三方登录
	 * @param request
	 * @param memberId
	 * @param type
	 * @param openId
	 * @param model
	 * @throws IOException
	 */
	@RequestMapping("/app/loginthird/")
	public void loginThird(HttpServletRequest request, HttpServletResponse response,String openId,Integer type) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> objs = memberService.getByThird(openId,type);
		if(objs!=null&&objs.size()>0){
			Map<String, Object> temp = objs.get(0);
			Member member=memberService.getById(Integer.valueOf(temp.get("id").toString()));
			if(member!=null){
				request.getSession().setAttribute("member", member);
				result.put("response", "success");
				result.put("msg", member);
			}else result.put("response", "failed");
		}else{
			result.put("response", "failed");
		}
		out.print(JSONArray.fromObject(result));
	}
	
	/**
	 * 绑定
	 * @author GXW
	 * @param request
	 * @param memberId
	 * @param type
	 * @param openId
	 * @param token
	 * @param model
	 * @throws IOException
	 */
	@RequestMapping("/app/bind/third/")
	public String bindThird(HttpServletRequest request, Integer memberId, Integer type, String openId, String token,
			Model model) throws IOException {
		request.setCharacterEncoding("utf-8");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<ThirdAuth> list = thirdAuthService.getByAttr(memberId, null, null, null);
			ThirdAuth t;
			if (list != null && list.size() > 0) {
				t = list.get(0);
				if (type == 1) {// 微信
					t.setWechatId(openId);
					t.setWechatToken(token);
				} else if (type == 2) {// QQ
					t.setQqId(openId);
					t.setQqToken(token);
				} else if (type == 3) {// 微博
					t.setSinaId(openId);
					t.setSinaToken(token);
				}
			} else {
				t = new ThirdAuth();
				t.setMemberId(memberId);
				if (type == 1) {// 微信
					t.setWechatId(openId);
					t.setWechatToken(token);
				} else if (type == 2) {// QQ
					t.setQqId(openId);
					t.setQqToken(token);
				} else if (type == 3) {// 微博
					t.setSinaId(openId);
					t.setSinaToken(token);
				}
			}
			thirdAuthService.saveOrUpdate(t);
			result.put("response", "success");
			result.put("msg", "绑定成功！");
		} catch (Exception e) {
			result.put("state", "failed");
			result.put("msg", "绑定失败，请刷新重试！");
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	
	/**
	 * 解绑
	 * @author GXW
	 * @param request
	 * @param memberId
	 * @param type
	 * @param model
	 * @throws IOException
	 */
	@RequestMapping("/app/unbind/third/")
	public String unbindThird(HttpServletRequest request, Integer memberId, Integer type, Model model)
			throws IOException {
		request.setCharacterEncoding("utf-8");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<ThirdAuth> list = thirdAuthService.getByAttr(memberId, null, null, null);
			ThirdAuth t;
			if (list != null && list.size() > 0) {
				t = list.get(0);
				if (type == 1) {// 微信
					t.setWechatId("");
					t.setWechatToken("");
				} else if (type == 2) {// QQ
					t.setQqId("");
					t.setQqToken("");
				} else if (type == 3) {// 微博
					t.setSinaId("");
					t.setSinaToken("");
				}
				thirdAuthService.saveOrUpdate(t);
				result.put("response", "success");
				result.put("msg", "解绑成功！");
			} else {
				result.put("state", "failed");
				result.put("msg", "解绑失败，请刷新尝试！");
			}
		} catch (Exception e) {
			result.put("state", "failed");
			result.put("msg", "解绑失败，请刷新尝试！");
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 获取绑定信息
	 * @author GXW
	 * @param request
	 * @param memberId
	 * @param model
	 * @throws IOException
	 */
	@RequestMapping("/app/getbind/")
	public String getBind(HttpServletRequest request, Integer memberId, Model model) throws IOException {
		request.setCharacterEncoding("utf-8");
		Map<String, Object> result = new HashMap<String, Object>();
		List<ThirdAuth> list = thirdAuthService.getByAttr(memberId, null, null, null);
		if (list != null && list.size() > 0) {
			ThirdAuth t = list.get(0);
			if (!StringUtils.isEmpty(t.getQqId()))
				result.put("qq", "1");
			else
				result.put("qq", "0");
			if (!StringUtils.isEmpty(t.getWechatId()))
				result.put("wechat", "1");
			else
				result.put("wechat", "0");
			if (!StringUtils.isEmpty(t.getSinaId()))
				result.put("sina", "1");
			else
				result.put("sina", "0");
			result.put("response", "success");
		}
		else{
			result.put("response", "failed");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * BBS登录验证
	 * @author WKX
	 * @param request
	 * @param response
	 * @param model
	 * @throws IOException
	 * @since 2016年11月11日 上午10:10:25
	 */
	@RequestMapping("/app/bbs/login")
	public String loginForBbs(HttpServletRequest request, HttpServletResponse response,Model model) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String msg = "用户名或密码错误";
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			String mobile = request.getParameter("mobile");
			String pwd = request.getParameter("pwd");
			String type = request.getParameter("type");
			
			List<Member> members = null;
			if("quicklogin".equals(type)){//快速登录
				if(StringUtils.isBlank(mobile))throw new Exception();
				members = memberService.getListByMobile(mobile);
				if(members==null || members.size()==0)throw new Exception();
			}else{
				if(StringUtils.isBlank(mobile) || StringUtils.isBlank(pwd))throw new Exception();
				
				members = memberService.getList(mobile, MD5Util.getMD5Str(pwd));
				if(members==null || members.size()==0)throw new Exception();
			}
			result.put("data", members.get(0).getId());
			result.put("response", "success");
			result.put("msg", "验证成功");
		}catch(Exception e){
			result.put("response", "failed");
			result.put("msg", msg);
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * BBS用户注册
	 * @author WKX
	 * @param request
	 * @param response
	 * @param model
	 * @throws IOException
	 * @since 2016年11月11日 上午10:44:32
	 */
	@RequestMapping("/app/bbs/register")
	public String registerForBbs(HttpServletRequest request, HttpServletResponse response,Model model) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			String mobile = request.getParameter("mobile");
			String pwd = request.getParameter("pwd");
			String invitationCode = request.getParameter("invitationCode");
			List<Member> baseEntityList = memberService.getListByMobile(mobile);
			
			if(baseEntityList!=null && baseEntityList.size()!=0){
				result.put("response", "failed");
				result.put("msg", "该账号已经注册票据管家，请直接登录");
				model.addAttribute("retValue", JacksonUtil.objToJson(result));
				return "ajax";
			}
			Member member = new Member();
			if(StringUtils.isNotBlank(invitationCode)){
				Member invitation = memberService.getByMyInvitationCode(invitationCode);
				if(invitation!=null){
					member.setInvitationCode(invitationCode);
				}
			}
			member.setMobile(mobile);
			member.setPwd(MD5Util.getMD5Str(pwd));
			member.setRegtime(new Date());
			member.setQudao("BBS");
			memberService.addMember(member);
			member = Member.deMember(member);//手机号转码
			result.put("response", "success");
			result.put("data", member.getId());
			result.put("msg", "注册成功！");
		}catch(Exception e){
			result.put("response", "failed");
			result.put("msg", "注册失败");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * BBS获取推荐的用户数量
	 * @author WKX
	 * @param request
	 * @param response
	 * @param model
	 * @throws IOException
	 * @since 2016年11月17日 下午2:24:20
	 */
	@RequestMapping("/app/bbs/invitation")
	public String getInvitationForBbs(HttpServletRequest request, HttpServletResponse response,Model model) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			String memberId = request.getParameter("memberId");
			if(StringUtils.isBlank(memberId))throw new Exception();
			Member member = memberService.getById(Integer.valueOf(memberId));
			if(member==null || StringUtils.isBlank(member.getMyInvitationCode()))throw new Exception();
			
			Long count = memberService.getByInvitationCode(member.getMyInvitationCode());
			result.put("response", "success");
			result.put("data", count);
			result.put("myInvitationCode", member.getMyInvitationCode());//返回我的邀请码
			result.put("msg", "获取成功");
		}catch(Exception e){
			result.put("response", "failed");
			result.put("msg", "获取失败");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * BBS密码修改
	 * @author WKX
	 * @param request
	 * @param response
	 * @param model
	 * @throws IOException
	 * @since 2016年11月18日 下午3:19:19
	 */
	@RequestMapping("/app/bbs/password")
	public String setPasswordForBbs(HttpServletRequest request, HttpServletResponse response,Model model) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		Map<String, Object> result = new HashMap<String, Object>();
		String msg = "验证失败！";
		try{
			String memberId = request.getParameter("memberId");
			String pwd = request.getParameter("pwd");
			String newpwd = request.getParameter("newpwd");
			if(StringUtils.isBlank(memberId))throw new Exception();
			if(StringUtils.isBlank(pwd))throw new Exception();
			if(StringUtils.isBlank(newpwd))throw new Exception();
			Member member = memberService.getById(Integer.valueOf(memberId));
			
			if(member==null || StringUtils.isBlank(member.getPwd())){
				msg = "请先在官网设置票据管家账号密码！";
				throw new Exception();
			}
			if(!(member.getPwd()).equals(MD5Util.getMD5Str(pwd))){//密码正确
				msg = "密码输入有误！";
				throw new Exception();
			}
			member.setPwd(MD5Util.getMD5Str(newpwd));
			memberService.updateMember(member);
			
			result.put("response", "success");
			result.put("msg", "密码修改成功！");
		}catch(Exception e){
			result.put("response", "failed");
			result.put("msg", msg);
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 		BBs根据微信的openid获取mobile 
	 * @author MH
	 * @param params 参数对象
	 * @return
	 */
	@RequestMapping("/app/bbs/openid")
	public String getMobile(Map<String,String>  params,Model model){
		List<ThirdAuth> thirdAuth = thirdAuthService.getByAttr(null, 1, params.get("openid"), null);
		Map<String, Object> result = new HashMap<String, Object>();
		Member member = memberService.getById(thirdAuth.get(0).getMemberId());
		result.put("mobile", member.getMobile());
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
}