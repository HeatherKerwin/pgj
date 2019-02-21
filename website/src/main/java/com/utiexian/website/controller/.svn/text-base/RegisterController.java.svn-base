package com.utiexian.website.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Member;
import com.ry.core.entity.Org;
import com.ry.core.service.MemberService;
import com.ry.core.service.OrgService;
import com.ry.util.HttpUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.MD5Util;
import com.ry.util.SendMessagesUtil;

/**
 * @author KHC
 * 注册
 */
@Controller
public class RegisterController {

	private static final Logger logger = Logger.getLogger(RegisterController.class);
	
	@Resource
	MemberService  memberService;	
	
	@Resource
	OrgService orgService;
	
	/**
	 * 用户注册
	 * @author KHC
	 * @param mobile
	 * @param pwd
	 * @param code
	 * @param inviteID
	 * @param request
	 * @param model
	 * @throws Exception
	 * @since 2016年11月14日 下午4:58:01
	 */
	@RequestMapping("/member/register")
	public String register(String mobile,String pwd,String code,String inviteID,String hezuo,HttpServletRequest request,Model model)throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			List<Member> memberList1 = memberService.getListByMobile(mobile);			
			if(memberList1!= null && memberList1.size()!=0){
				map.put("msg","phoneError");
				model.addAttribute("retValue", JacksonUtil.objToJson(map));
				return "ajax";
			}
			String sessioncode = (String)request.getSession().getAttribute("code");
			String sessionmobile = (String)request.getSession().getAttribute("mobile");
			if(!code.equals(sessioncode) || !mobile.equals(sessionmobile)){
				map.put("msg","codeError");
				model.addAttribute("retValue", JacksonUtil.objToJson(map));
				return "ajax";
			}
			Member member = new Member();
			Org org = new Org();
			member.setMobile(mobile);
			member.setPwd(MD5Util.getMD5Str(pwd));
			member.setRegtime(new Date());
			member.setQudao("PC");
			member.setRecommendpeople(inviteID);
			if(StringUtils.hasText(hezuo))member.setHezuo(hezuo);
			memberService.addMember(member);
			member = Member.deMember(member);//@WKX EDIT2016-08-19 手机号转码
			org.setMemberId(member.getId());
			org.setType(1);
			org.setState(0);
			org.setCreateTime(new Date());
			org = orgService.saveModel(org);
			map.put("response", "success");
			request.getSession().setAttribute("member", member);
		}catch(Exception e){
			e.printStackTrace();
			map.put("msg","注册失败！");		
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(map));
		return "ajax";
	}
	
	/**
	 * 推广注册获取验证码
	 * @author KHC
	 * @param flag
	 * @param mobile
	 * @param myCode
	 * @param request
	 * @param response
	 * @throws IOException
	 * @since 2016年11月24日 上午9:52:30
	 */
	@RequestMapping("/tg/member/sendcode.htm")
	public String sendcode(String flag, String mobile,String myCode, HttpServletRequest request, HttpServletResponse response)throws IOException{
		String refer = request.getHeader("referer");
		Cookie uuid = HttpUtil.getCookieByName(request, "uuid");
		PrintWriter out = response.getWriter();
		
		if(uuid==null || !StringUtils.hasText(refer) 
				|| !(refer.indexOf("https://www.utiexian.com/register.jsp")>-1 
				|| refer.indexOf("www.utiexian.com/register.jsp")>-1
				|| refer.indexOf("www.ryfinance.com/forgetpwd.jsp")>-1 
				|| refer.indexOf("www.utiexian.com/forgetpwd.jsp")>-1
				|| refer.indexOf("192.168.1")>-1)){
			out.print("发送失败");
			return "ajax";
		}
		//前台页面输入的随机安全码
		String code_ = (String)request.getSession().getAttribute("IMAGECODE");
		if(org.apache.commons.lang.StringUtils.isBlank(myCode) || !myCode.toUpperCase().equals(code_)){
			out.print("安全码错误");
			return "ajax";
		}
		logger.info("referer:"+refer);
		try{
			if("reg".equals(flag)){
			Member lint= memberService.shouji(mobile);
				if(lint!=null){
					out.print("手机号码已经存在");
					return "ajax";
				}
			}else if("forget".equals(flag)){
				Member lint1 = memberService.shouji(mobile);
				if(lint1==null){
					out.print("手机号码不存在");
					return "ajax";
				}
			}
			String code = String.valueOf((Math.random())).split("\\.")[1];
			code = code.substring(0,4);
			
			if("reg".equals(flag)){
				SendMessagesUtil.aliSendRegistMessage(mobile, code);
			}else if("forget".equals(flag)){
				SendMessagesUtil.aliSendFindPwdMessage(mobile, code);
			}else {
				String message = "验证码:"+code+"【票据管家】";
				SendMessagesUtil.sendMessages(mobile, message);
			}
			
			logger.info("mobile:"+mobile+"****code:"+code);
			request.getSession().setAttribute("code", code);
			request.getSession().setAttribute("mobile", mobile);
			out.print("success");
		}catch(Exception e){
			e.printStackTrace();
			out.print("发送失败");
		}
		return "ajax";
	}
	
	/**
	 * 推广注册
	 * @author KHC
	 * @param mobile
	 * @param pwd
	 * @param code
	 * @param inviteID
	 * @param hezuo
	 * @param qudao
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 * @since 2016年11月24日 上午9:51:59
	 */
	@RequestMapping("/tg/member/register.htm")
	public void register(String mobile, String pwd, String code, String inviteID, String hezuo, String qudao, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		try{
			List<Member> memberList1 = memberService.getListByMobile(mobile);			
			if(memberList1!= null && memberList1.size()!=0){
				out.print("手机号码已经存在");
				return;
			}
			String sessioncode = (String)request.getSession().getAttribute("code");
			String sessionmobile = (String)request.getSession().getAttribute("mobile");
			if(!code.equals(sessioncode) || !mobile.equals(sessionmobile)){
				out.print("验证码不正确");
				return;
			}
			Member member = new Member();
			member.setMobile(mobile);
			member.setPwd(MD5Util.getMD5Str(pwd));
			member.setRegtime(new Date());
			member.setHezuo(hezuo);
			member.setQudao(qudao);
			member.setRecommendpeople(inviteID);
			memberService.addMember(member);
			Org org = new Org();
			org.setMemberId(member.getId());
			org.setType(1);
			org.setState(0);
			org.setCreateTime(new Date());
			org = orgService.saveModel(org);
			out.print("success");
		}catch(Exception e){
			e.printStackTrace();
			out.print("注册失败");		
		}
	}
}
