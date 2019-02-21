package com.utiexian.rywap.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Member;
import com.ry.core.entity.Org;
import com.ry.core.service.MemberService;
import com.ry.core.service.OrgService;
import com.ry.core.util.SendMessagesUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.MD5Util;

/**
 * 用户
 * @author MH
 */
@Controller
@RequestMapping("/wap/member")
public class MemberController {
	
	@Resource
	MemberService memberService;
	
	@Resource
	OrgService orgService;
	
	/**
	 * 跳转到注册的页面
	 * @author MH
	 * @return
	 */
	@RequestMapping("/register/page")
	public String register(){
		return "/register";
	}
	
	/**
	 * 跳转到修改密码的页面
	 * @author MH
	 * @return
	 */
	@RequestMapping("/password/page")
	public String password(){
		return "/update_password_first";
	}
	
	/**
	 * 跳转到修改密码第二步的页面
	 * @author MH
	 * @param mobile  手机号码
	 * @param model
	 * @return
	 */
	@RequestMapping("/update/password/page")
	public String password2(String update_mobile,Model model){
		model.addAttribute("update_mobile",update_mobile);
		return "/update_password_two";
	}
	
	/**
	 * 跳转到用户协议的页面
	 * @author MH
	 * @return
	 */
	@RequestMapping("/agreement/page")
	public String agreement(){
		return "/agreement";
	}
	/** 供app的外链用户协议 **/
	@RequestMapping("/agreementAPP/page")
	public String agreementAPP(){
		return "/agreementAPP";
	}
	
	/**
	 * 跳转到关于我们
	 * @author MH
	 * @return
	 */
	@RequestMapping("/about/page")
	public String about(){
		return "/about";
	}
	
	/**
	 * @author MH
	 * @param mobile 手机号码
	 * @param flag  forget为修改密码,reg 为注册,quicklogin 快速登陆
	 * @param response
	 * @param request
	 * @throws IOException
	 * 快速登录发送验证码，如果该手机号已存在则返回验证码，如果不存在则直接添加手机号
	 */
	@RequestMapping("/sendcode")
	public String sendcode(HttpServletRequest request,String mobile,String flag,Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if ("reg".equals(flag)) {
				List<Member> baseEntityList = memberService.getListByMobile(mobile);
				if (baseEntityList != null && baseEntityList.size() != 0) {
					map.put("response", "failed");
					map.put("msg", "手机号码已经存在");
					model.addAttribute("retValue",JacksonUtil.objToJson(map));
					return "ajax";
				}
			} else if ("forget".equals(flag)) {
				List<Member> baseEntityList = memberService.getListByMobile(mobile);
				if (baseEntityList == null || baseEntityList.size() == 0) {
					map.put("response", "failed");
					map.put("msg", "手机号码不存在");
					model.addAttribute("retValue",JacksonUtil.objToJson(map));
					return "ajax";
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
		} catch (Exception e) {
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "发送失败");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(map));
		return "ajax";
	}

	/**
	 * @author MH 
	 * @param mobile 手机号码
	 * @param code 验证码
	 * @param pwd 新密码
	 * @param model
	 * @throws IOException
	 */
	@RequestMapping("/updatepwd")
	public String updatepwd(String mobile,String code,String pwd,Model model,HttpServletRequest request) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String sessioncode = (String) request.getSession().getAttribute("code");
			if (!code.equals(sessioncode)) {
				map.put("response", "failed");
				map.put("msg", "验证码不正确");
				model.addAttribute("retValue",JacksonUtil.objToJson(map));
				return "ajax";
			}
			if (pwd != null && mobile != null) {
				pwd = MD5Util.getMD5Str(pwd);
				memberService.updateModelPwd(pwd,mobile);
				map.put("response", "success");
				map.put("msg", "修改成功");
			} else {
				map.put("response", "failed");
				map.put("msg", "手机号码不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "修改密码失败");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(map));
		return "ajax";
	}
	
	/**
	 * 用户注册
	 * @author MH
	 * @param mobile 手机号码
	 * @param pwd  密码
	 * @param code 验证码
	 * @param recommendpeople 推荐人
	 * @param request
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping("/register/new")
	public String register(String mobile,String pwd,String code,String recommendpeople,HttpServletRequest request,Model model)throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			List<Member> memberList1 = memberService.getListByMobile(mobile);			
			if(memberList1!= null && memberList1.size()!=0){
				map.put("msg","该手机已经注册");
				map.put("response", "failed");
				model.addAttribute("retValue", JacksonUtil.objToJson(map));
				return "ajax";
			}
			String sessioncode = (String)request.getSession().getAttribute("code");
			if(!code.equals(sessioncode)){
				map.put("msg","验证码错误");
				map.put("response", "failed");
				model.addAttribute("retValue", JacksonUtil.objToJson(map));
				return "ajax";
			}
			Member member = new Member();
			Org org = new Org();
			member.setMobile(mobile);
			member.setPwd(MD5Util.getMD5Str(pwd));
			member.setRegtime(new Date());
			member.setQudao("WAP");
			member.setRecommendpeople(recommendpeople);
			memberService.addMember(member);
			member = Member.deMember(member);//@WKX EDIT2016-08-19 手机号转码
			org.setMemberId(member.getId());
			org.setType(1);
			org.setState(0);
			org.setCreateTime(new Date());
			org = orgService.saveModel(org);
			map.put("response", "success");
			map.put("msg","注册成功！");
			request.getSession().setAttribute("member", member);
		}catch(Exception e){
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg","注册失败！");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(map));
		return "ajax";
	}
}
