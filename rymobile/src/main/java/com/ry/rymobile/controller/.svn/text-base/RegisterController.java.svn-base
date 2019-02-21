package com.ry.rymobile.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Member;
import com.ry.core.entity.Org;
import com.ry.core.service.MemberService;
import com.ry.core.service.OrgService;
import com.ry.util.Base64Util;
import com.ry.util.HttpUtil;
import com.ry.util.JacksonUtil;

@Controller
@RequestMapping("/register")
public class RegisterController {
	private Logger log = Logger.getLogger(RegisterController.class);
	@Resource
	MemberService memberService;
	
	@Resource
	OrgService orgService;
	/**
	 * 验证 验证码，通过保存注册用户
	 * @param mobile			手机号
	 * @param code				验证码
	 * @param invitationCode	邀请码
	 * @param model
	 */
	@RequestMapping("/registerToDo")
	public String register(String mobile, String code, String invitationCode,HttpServletRequest request,Model model){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Member> list = memberService.getListByMobile(mobile);
		if(list!= null && list.size()>0){
			Member temp = list.iterator().next();
			resultMap.put("state", "error");
			resultMap.put("msg","您已经注册！");
			resultMap.put("invitationCode",temp.getMyInvitationCode());
			model.addAttribute("retValue", JacksonUtil.objToJson(resultMap));
			return "ajax";
		}
		String sessioncode = (String)request.getSession().getAttribute("code");					//session中的验证码
		String sessionmobile = (String)request.getSession().getAttribute("mobile");
		if(!code.equals(sessioncode) || !mobile.equals(sessionmobile)){
			resultMap.put("state", "error");
			resultMap.put("msg","验证码不正确！");
			resultMap.put("invitationCode",invitationCode);
			model.addAttribute("retValue", JacksonUtil.objToJson(resultMap));
			return "ajax";
		}
		try {
			String registerToken = (String) request.getSession().getAttribute("registerToken");	//session中的加密值
			registerToken = Base64Util.decryptBASE64(registerToken);							//解密
			boolean bool = false;
			Cookie cookie = HttpUtil.getCookieByName(request, "registerToken");					//cookie中的加密值
			if (cookie != null) {				
				String registerToken2 = Base64Util.decryptBASE64(cookie.getValue());			//解密
				if (registerToken.equals(registerToken2)) {
					bool = true;
				}
			}
			if (!bool) {
				resultMap.put("state", "error");
				resultMap.put("msg","请刷新页面！");
				resultMap.put("invitationCode",invitationCode);
				model.addAttribute("retValue", JacksonUtil.objToJson(resultMap));
				return "ajax";
			}
			Member member = new Member();
			member.setRegtime(new Date());
			member.setMobile(mobile);
			member.setInvitationCode(invitationCode);
			memberService.addMember(member);
			Org org = new Org();
			org.setMemberId(member.getId());
			org.setType(1);
			org.setState(0);
			org.setCreateTime(new Date());
			org = orgService.saveModel(org);
			resultMap.put("state", "success");
			resultMap.put("msg","注册成功！");
			resultMap.put("invitationCode",invitationCode);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("state", "error");
			resultMap.put("msg","注册失败！");
			resultMap.put("invitationCode",invitationCode);
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(resultMap));
		return "ajax";
	}
	
	/**
	 * 跳转至分享注册页面
	 * @param code
	 * @param model
	 * @throws IOException
	 */
	@RequestMapping("/shareRegister")
	public String shareRegister(HttpServletResponse response, HttpServletRequest request, String code,Model model){
		model.addAttribute("invitationCode",code);
		Member m = memberService.getByMyInvitationCode(code);
		model.addAttribute("memberId",m.getId());
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date()) + "UTIEXIAN";
		try {
			String token = Base64Util.encryptBASE64(date.getBytes()).trim();
			HttpUtil.addCookie(response, "registerToken", token, 1800);		//cookie保存30分钟
			request.getSession().setAttribute("registerToken", token);
		} catch (Exception e) {
			log.error("跳转至分享注册页面操作失败",  e);
		}
		return "shareRegister/shareRegister";
	}
	
	/**
	 * 发送验证码
	 * @param mobile
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/sendcode.htm")
	public void sendCode(String mobile, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		try{
			String registerToken = (String) request.getSession().getAttribute("registerToken");		//session中的加密值
			registerToken = Base64Util.decryptBASE64(registerToken);//解密
			boolean bool = false;
			Cookie cookie = HttpUtil.getCookieByName(request, "registerToken");						//cookie中的加密值
			if (cookie != null) {				
				String registerToken2 = Base64Util.decryptBASE64(cookie.getValue());//解密
				if (registerToken.equals(registerToken2)) {
					bool = true;
				}
			}
			if (bool) {
				String code = String.valueOf((Math.random())).split("\\.")[1];
				code = code.substring(0,4);
				com.ry.util.SendMessagesUtil.aliSendRegistMessage(mobile, code);
				
				request.getSession().setAttribute("code", code);
				request.getSession().setAttribute("mobile", mobile);
				out.print("success");
			} else {
				out.print("timeout");
			}
		}catch(Exception e){
			e.printStackTrace();
			out.print("error");
		}
	}
	
	/**
	 * 跳转注册成功页面
	 */
	@RequestMapping("/success.htm")
	public String success() throws IOException{
		return "shareRegister/success";
	}
}
