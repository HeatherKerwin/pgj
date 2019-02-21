package com.utiexian.rywap.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Member;
import com.ry.core.entity.Org;
import com.ry.core.service.MemberService;
import com.ry.core.service.OrgService;
import com.ry.core.util.Constant;
import com.ry.util.JacksonUtil;
import com.ry.util.MD5Util;
import com.ry.util.StringUtil;

/**
 * 登陆
 * @author MH
 */
@Controller
@RequestMapping("/wap/login")
public class LoginController {
	
	@Resource
	MemberService memberService;

	@Resource
	OrgService orgService;
	
	/**
	 * 跳转到登陆页面
	 * @author MH
	 * @return
	 */
	@RequestMapping("/page")
	public String login(String redirect_url,Model model){
		model.addAttribute("redirect_url", redirect_url);
		return "/login";
	}

	/**
	 * 跳转到免责声明
	 * @author MH
	 * @return
	 */
	@RequestMapping("/statement")
	public String statement(){
		return "/statement";
	}
	/**
	 * 跳转到票据应收款转让服务合同
	 * @author MH
	 * @return
	 */
	@RequestMapping("/payment")
	public String payment(){
		return "/appMent/payment";
	}
	/**
	 * 跳转到承诺函和授权书
	 * @author MH
	 * @return
	 */
	@RequestMapping("/authorization")
	public String authorization(){
		return "/appMent/authorization";
	}
	
	/**
	 * 用户密码登录
	 * @author MH
	 * @param mobile 手机号码
	 * @param pwd 用户密码
	 * @param redirect_url 跳转路径
	 * @param request
	 * @param model
	 * @since 2016年10月26日 下午2:13:16
	 */
	@RequestMapping("")
	public String login(String mobile, String pwd,String redirect_url,HttpServletRequest request,Model model){
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(mobile)){
			List<Member> membersList = memberService.getList(mobile, null);
			if(membersList!=null && membersList.size()>0){
				List<Member> members = memberService.getList(mobile, MD5Util.getMD5Str(pwd));
				if(members!=null && members.size()>0){
					Member member = members.get(0);
					request.getSession().setAttribute("member", member);
					map.put("member", member);
					map.put("response", "success");
					map.put("redirect_url", redirect_url);

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
	 * @author MH
	 * @param mobile 手机
	 * @param code 验证码
	 * @param redirect_url 跳转路径
	 * @param request
	 * @param model
	 */
	@RequestMapping("/quicklogin")
	public String loginquick(String mobile, String code,String redirect_url,HttpServletRequest request,Model model){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String sessioncode = (String)request.getSession().getAttribute("code");
			if(!code.equals(sessioncode)){
				map.put("msg","codeError");
				model.addAttribute("retValue", JacksonUtil.objToJson(map));
				return "ajax";
			}
			List<Member> members = memberService.getListByMobile(mobile);
			if(members!=null && members.size()>0){
				Member member = members.get(0);
				request.getSession().setAttribute("member", member);
				
				map.put("response", "success");
				map.put("redirect_url", redirect_url);
			}else{
				Member member = new Member();
				Org org = new Org();
				member.setMobile(mobile);
				member.setRegtime(new Date());
				member.setQudao("WAP");			
				memberService.addMember(member);
				member = Member.deMember(member);
				org.setMemberId(member.getId());
				org.setType(1);
				org.setState(0);
				org.setCreateTime(new Date());
				org = orgService.saveModel(org);
				request.getSession().setAttribute("member", member);
				map.put("member", member);
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
	 * 登出
	 * @author MH
	 * @param request
	 */
	@RequestMapping("/member/logout")
	public String login(HttpServletRequest request){
		request.getSession().removeAttribute("member");
		return StringUtil.processRedirectUrl(Constant.properties.getProperty("baseUrl"), "/wap/index");
	}
}
