package com.utiexian.website.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Member;
import com.ry.core.service.MemberService;
import com.ry.util.JacksonUtil;
import com.ry.util.MD5Util;
import com.ry.util.SendMessagesUtil;
import com.utiexian.website.util.ValidateCode;

/**
 * @author KHC
 * 用户获取验证码
 */
@Controller
public class MemberController {

	private static final Logger logger = Logger.getLogger(MemberController.class);
	
	@Resource
	MemberService memberService;
	
	/**
	 * 进入修改密码页面
	 * @author KHC
	 * @param request
	 * @since 2016年12月12日 上午10:03:37
	 */
	@RequestMapping("/member/forgetpwd")
	public String forgetpwd(HttpServletRequest request){
		return "member/forgetpwd";
	}
	
	/**
	 * 获取手机验证码
	 * @author KHC
	 * @param flag
	 * @param mobile
	 * @param myCode
	 * @param request
	 * @param response
	 * @throws IOException
	 * @since 2016年10月26日 下午7:20:16
	 */
	@RequestMapping("/member/sendcode")
	public String sendcode(String flag, String mobile,String myCode, HttpServletRequest request, HttpServletResponse response)throws IOException{
		PrintWriter out = response.getWriter();
		//前台页面输入的随机安全码
		String code_ = null;
		if("reg".equals(flag)){
			code_ = (String)request.getSession().getAttribute("IMAGECODE1");
		}else if("quicklogin".equals(flag) || "forget".equals(flag)) {
			code_ = (String)request.getSession().getAttribute("IMAGECODE");
		}
		if(org.apache.commons.lang.StringUtils.isBlank(myCode) || !myCode.toUpperCase().equals(code_)){
			out.print("安全码错误");
			return "ajax";
		}
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
			}else if ("quicklogin".equals(flag)) {
				com.ry.util.SendMessagesUtil.aliSendIdentityMessage(mobile,code);
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
	 * 修改密码
	 * @author KHC
	 * @param mobile
	 * @param code
	 * @param newpwd
	 * @param request
	 * @param model
	 * @throws Exception
	 * @since 2016年11月14日 下午4:57:29
	 */
	@RequestMapping("/member/updatepwd")
	public String updatepwd(String mobile,String code,String newpwd,HttpServletRequest request,Model model) throws Exception{
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
			if(members==null||members.size()==0){
				map.put("msg","phoneError");
				model.addAttribute("retValue", JacksonUtil.objToJson(map));
				return "ajax";
			}else{
				Member member = new Member();
				member = members.get(0);
				member.setPwd(MD5Util.getMD5Str(newpwd));
				memberService.updateMember(member);
				member = Member.deMember(member);//@WKX EDIT2016-08-19 手机号转码
				request.getSession().setAttribute("member", member);
				map.put("response", "success");
				map.put("msg","修改成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg","修改失败");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(map));
		return "ajax";
	}
	
	/**
	 * 获取图形验证码
	 * @author WKX
	 * @param reqeust
	 * @param response
	 * @throws IOException
	 * @since 2016年10月20日 下午19:19:22
	 */
	@RequestMapping("/member/image")
	protected void image(HttpServletRequest reqeust,HttpServletResponse response) throws IOException {  
        response.setContentType("image/jpeg");//设置响应的类型格式为图片格式
        response.setHeader("Pragma", "no-cache");//禁止图像缓存。  
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        HttpSession session = reqeust.getSession();
        
        ValidateCode vCode = new ValidateCode(122,46,4,30);
        session.setAttribute("IMAGECODE", vCode.getCode()); 
        vCode.write(response.getOutputStream());  
    }
	
	/**
	 * 获取图形验证码
	 * @author WKX
	 * @param reqeust
	 * @param response
	 * @throws IOException
	 * @since 2016年10月20日 下午19:19:22
	 */
	@RequestMapping("/member/image1")
	protected void image1(HttpServletRequest reqeust,HttpServletResponse response) throws IOException {  
        response.setContentType("image/jpeg");//设置响应的类型格式为图片格式
        response.setHeader("Pragma", "no-cache");//禁止图像缓存。  
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        HttpSession session = reqeust.getSession();
        
        ValidateCode vCode = new ValidateCode(122,46,4,30);
        session.setAttribute("IMAGECODE1", vCode.getCode()); 
        vCode.write(response.getOutputStream());  
    }
	
	/**
	 * 登录验证图形码
	 * @author KHC
	 * @param myCode
	 * @param reqeust
	 * @param response
	 * @param model
	 * @throws IOException
	 * @since 2016年10月24日 下午5:24:25
	 */
	@RequestMapping("/member/yzimage")
	public void getMyCode(String imgCode,HttpServletRequest reqeust,HttpServletResponse response,Model model) throws IOException{
		PrintWriter out = response.getWriter();
		String code = (String)reqeust.getSession().getAttribute("IMAGECODE");
		if(org.apache.commons.lang.StringUtils.isNotBlank(imgCode) && imgCode.toUpperCase().equals(code)){
			out.print("success");
		}else{
			out.print("failed");
		}
	}
	
	/**
	 * 注册验证图形码
	 * @author KHC
	 * @param myCode
	 * @param reqeust
	 * @param response
	 * @param model
	 * @throws IOException
	 * @since 2016年10月24日 下午5:24:25
	 */
	@RequestMapping("/member/yzimage1")
	public void getMyCode1(String imgCode,HttpServletRequest reqeust,HttpServletResponse response,Model model) throws IOException{
		PrintWriter out = response.getWriter();
		String code = (String)reqeust.getSession().getAttribute("IMAGECODE1");
		if(org.apache.commons.lang.StringUtils.isNotBlank(imgCode) && imgCode.toUpperCase().equals(code)){
			out.print("success");
		}else{
			out.print("failed");
		}
	}
}