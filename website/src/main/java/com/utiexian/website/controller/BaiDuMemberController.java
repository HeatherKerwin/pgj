package com.utiexian.website.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Member;
import com.ry.core.entity.Org;
import com.ry.core.service.MemberService;
import com.ry.core.service.OrgService;
import com.ry.core.util.Constant;
import com.ry.util.HttpUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.PropertiesUtil;
import com.ry.util.StringUtil;

/**
 * @author KHC
 * 各种渠道用户注册
 */
@Controller
@RequestMapping("/tg")
public class BaiDuMemberController {
	
	@Resource
	OrgService orgService;
	
	@Resource
	MemberService memberService;
	
	/**
	 * 注册验证
	 * @author KHC
	 * @param phone
	 * @param code
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 * @since 2016年12月12日 上午9:58:42
	 */
	@RequestMapping("/register_yanzheng.htm")
	public void register2(String phone, String code, HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		try{
			List<Member> memberList1 = memberService.getListByMobile(phone);			
			if(memberList1!= null && memberList1.size()!=0){
				out.print("手机号码已经存在");
				return;
			}
			String sessioncode = (String)request.getSession().getAttribute("code");
			String sessionmobile = (String)request.getSession().getAttribute("mobile");
			if(!code.equals(sessioncode) || !phone.equals(sessionmobile)){
				out.print("验证码不正确");
				return;
			}
			out.print("success");
		}catch(Exception e){
			e.printStackTrace();
			out.print("注册失败");	
		}
	}
	
	/**
	 * 注册
	 * @author KHC
	 * @param phone
	 * @param code
	 * @param qudao
	 * @param uuid
	 * @param request
	 * @param response
	 * @param model
	 * @throws IOException
	 * @throws ServletException
	 * @since 2016年12月12日 上午9:59:06
	 */
	@RequestMapping("/register.htm")
	public String register1(String phone,String code,String qudao,String uuid,HttpServletRequest request, HttpServletResponse response,Model model) 
			throws IOException, ServletException {
		try{
			List<Member> memberList1 = memberService.getListByMobile(phone);			
			if(memberList1!= null && memberList1.size()!=0){
				return StringUtil.processRedirectUrl(Constant.properties.getProperty("baseUrl"), "");
			}
			String sessioncode = request.getParameter("code");
			String sessionmobile = request.getParameter("phone");
			if(!code.equals(sessioncode) || !phone.equals(sessionmobile)){
				return StringUtil.processRedirectUrl(Constant.properties.getProperty("baseUrl"), "");
			}
			
			Member member = new Member();
			member.setMobile(phone);
			member.setRegtime(new Date());
			member.setQudao(qudao);
			member.setUuid(uuid);//对应ClickCount 用户是百度推广而来
			member.setIp(HttpUtil.getIpAddr(request));//记录注册时的IP
			
			Cookie hezuo = HttpUtil.getCookieByName(request, "hezuo");
			if(hezuo!=null){
				member.setHezuo(hezuo.getValue());
			}else{
				String hezuo_ = request.getParameter("hezuo");
				if(org.apache.commons.lang.StringUtils.isNotBlank(hezuo_))member.setHezuo(hezuo_);//合作方（sougou、360等）
			}
			memberService.addMember(member);
			Org org = new Org();
			org.setMemberId(member.getId());
			org.setType(1);
			org.setState(0);
			org.setCreateTime(new Date());
			org = orgService.saveModel(org);
			model.addAttribute("hezuo", hezuo.getValue());
			return "register_success";
		}catch(Exception e){
			e.printStackTrace();
			return StringUtil.processRedirectUrl(Constant.properties.getProperty("baseUrl"), "");
		}
	}

	/**
	 * 获取验证码
	 * @author KHC
	 * @param phone
	 * @param flag
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 * @since 2016年12月12日 上午10:22:31
	 */
	@RequestMapping("/sendcode")
	public void sendcode(String phone, String flag, HttpServletRequest request, HttpServletResponse response,Model model) throws Exception {
		PrintWriter out = response.getWriter(); 
		try{						
			if("reg".equals(flag)){
				List<Member> baseEntityList = memberService.getListByMobile(phone);
				if(baseEntityList!=null&&baseEntityList.size()!=0){
					out.print("手机号码已经存在");
					model.addAttribute("baiduLoginName", phone);//新增，该手机号已存在，就将其保存在session中，然后在登录页面进行读取
					return;
				}
			}else if("forget".equals(flag)){
				List<Member> baseEntityList = memberService.getListByMobile(phone);
				if(baseEntityList==null||baseEntityList.size()==0){
					out.print("手机号码不存在");
					return;
				}
			}
			String code = String.valueOf((Math.random())).split("\\.")[1];
			code = code.substring(0,4);
			
			com.ry.util.SendMessagesUtil.aliSendRegistMessage(phone, code);
			
			request.getSession().setAttribute("code", code);
			request.getSession().setAttribute("mobile", phone);
			out.print("success");
		}catch(Exception e){
			e.printStackTrace();
			out.print("发送失败"); 
		}
	}
	
	/**
	 * 获取最新入住机构名称(来自配置数据)
	 * @author KHC
	 * @param list
	 * @param model
	 */
	@RequestMapping("/list/org")
	public String getMemberPageList(Model model){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String org = PropertiesUtil.getConfigPropertiesValue("INDEX_ORG", "");
			org = new String(org.getBytes("ISO-8859-1"),"UTF-8");
			List<String> orgs = Arrays.asList(org.split(","));
			result.put("data",orgs);
			result.put("response", "success");
			result.put("msg", "获取成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "获取失败");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 快速注册
	 * @author KHC
	 * @param phone
	 * @param code
	 * @param qudao
	 * @param uuid
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping("/quicklogin.htm")
	public String register(String phone, String code, String qudao,String uuid, HttpServletRequest request, HttpServletResponse response,Model model)throws IOException, ServletException {
		try{
			if(StringUtils.isBlank(phone))throw new Exception();
			String sessioncode = request.getParameter("code");
			String sessionmobile = request.getParameter("phone");
			if(!code.equals(sessioncode) || !phone.equals(sessionmobile)){
				return StringUtil.processRedirectUrl(Constant.properties.getProperty("baseUrl"), "");
			}
			List<Member> list = memberService.getListByMobile(phone);
			if(list!=null && list.size()>0){//查询用户
				return StringUtil.processRedirectUrl(Constant.properties.getProperty("baseUrl"), "");
			}else{//不存在该用户则新增
				Member member = new Member();
				member.setMobile(phone);
				member.setRegtime(new Date());
				member.setQudao(qudao);
				member.setUuid(uuid);//对应ClickCount 用户是百度推广而来
				member.setIp(HttpUtil.getIpAddr(request));//记录注册时的IP
				Cookie hezuo = HttpUtil.getCookieByName(request, "hezuo");
				if(hezuo!=null){
					member.setHezuo(hezuo.getValue());
				}
				memberService.addMember(member);
				Org org = new Org();
				org.setMemberId(member.getId());
				org.setType(1);
				org.setState(0);
				org.setCreateTime(new Date());
				org = orgService.saveModel(org);
				model.addAttribute("hezuo", hezuo.getValue());
			}
			return "register_success";
		}catch(Exception e){
			e.printStackTrace();
			return StringUtil.processRedirectUrl(Constant.properties.getProperty("baseUrl"), "");
		}
	}
}