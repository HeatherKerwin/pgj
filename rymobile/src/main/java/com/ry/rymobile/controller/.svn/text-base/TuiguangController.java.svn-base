package com.ry.rymobile.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ry.core.entity.ClickCount;
import com.ry.core.entity.Member;
import com.ry.core.entity.Org;
import com.ry.core.entity.Sitestatistics;
import com.ry.core.form.MemberForm;
import com.ry.core.service.ClickCountService;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.MemberService;
import com.ry.core.service.OrgService;
import com.ry.core.service.SitestatisticsService;
import com.ry.core.service.VariablesService;
import com.ry.util.Base64Util;
import com.ry.util.DateUtil;
import com.ry.util.HttpUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.MD5Util;


@Controller
public class TuiguangController {
	
	@Resource
	MemberService memberService;
	
	@Resource
	SitestatisticsService sitestatisticsService;
	
	@Resource
	DiscountrecordService discountrecordService;
	
	@Resource
	VariablesService variablesService;
	
	@Resource
	OrgService orgService;
	
	@Resource
	ClickCountService clickCountService;
	
	@RequestMapping("/activity/tongji.htm")
	public void tongji(MemberForm memberForm, HttpServletRequest request, HttpServletResponse response) throws IOException{
		String ip = HttpUtil.getIpAddr(request);
		
		if ("180.166.201.178".equals(ip)) {
			return;
		}
		Sitestatistics s = new Sitestatistics();
		s.setHezuo(memberForm.getHezuo());
		s.setIp(ip);
		s.setType("AC");
		s.setUrl(memberForm.getUrl());		
		s.setInvitedate(new Date());
		sitestatisticsService.saveSitestatistics(s);		
	}
	
	@RequestMapping("/activity/register.htm")
	public void register(MemberForm memberForm, HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		try{
			List<Member> memberList1 = memberService.getListByMobile(memberForm.getMobile());			
			if(memberList1!= null && memberList1.size()!=0){	
				out.print("registered");
				return;
			}
			String sessioncode = (String)request.getSession().getAttribute("code");
			String sessionmobile = (String)request.getSession().getAttribute("mobile");
			if(!memberForm.getCode().equals(sessioncode) || !memberForm.getMobile().equals(sessionmobile)){
				out.print("验证码不正确");
				return;
			}
			Member member = new Member();			
			member.setRegtime(new Date());			
			member.setMobile(memberForm.getMobile());
			if(StringUtils.hasText(memberForm.getPwd())){
				member.setPwd(MD5Util.getMD5Str(memberForm.getPwd()));	
			}
					
			member.setHezuo(memberForm.getHezuo());
			member.setQudao("AC");	
			if(StringUtils.hasText(memberForm.getRecommendpeople())){
				member.setRecommendpeople(memberForm.getRecommendpeople());
			}
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
	
	/**
	 * 注册
	 * @param phone 手机号
	 * @param pwd 密码
	 * @param code 验证码
	 * @param qudao 渠道（APP、BBS、PC）
	 * @param ic 推荐码
	 * @param sale 销售推荐码
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping("/activity/registerAcross.htm")
	public void registerAcross(String phone,String pwd,String code,String qudao,String ic,String sale,HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		String jsonp = request.getParameter("jsonpcallback");
		String str = "";
		PrintWriter out = response.getWriter(); 
		try{
			List<Member> memberList1 = memberService.getListByMobile(phone);			
			if(memberList1!= null && memberList1.size()!=0){
				str = "{\"result\":\"手机号码已经存在\"}";
				str = jsonp + "(" +str+")";
				out.write(str);
				return;
			}
			String sessioncode = (String)request.getSession().getAttribute("code");
			String sessionmobile = (String)request.getSession().getAttribute("phone");
			if(!code.equals(sessioncode) || !phone.equals(sessionmobile)){
				str = "{\"result\":\"验证码不正确\"}";
				str = jsonp + "(" +str+")";
				out.write(str);
				return;
			}
			Member member = new Member();
			member.setMobile(phone);
			member.setRegtime(new Date());
			member.setQudao(qudao);
			if(org.apache.commons.lang.StringUtils.isNotBlank(sale))member.setRecommendpeople(sale);//保存推荐人（销售员）
			if(org.apache.commons.lang.StringUtils.isNotBlank(ic)){
				Member m = memberService.getByMyInvitationCode(ic);
				if(m!=null){//如果推荐码正确则保存 推荐码
					member.setInvitationCode(ic);
				}
			}
			
			Cookie uuid = HttpUtil.getCookieByName(request, "uuid");
			if(uuid!=null){
				member.setUuid(uuid.getValue());//对应ClickCount 用户唯一标示
			}
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
			str = "{\"result\":\"success\"}";
			str = jsonp + "(" +str+")";
			out.write(str);
		}catch(Exception e){
			e.printStackTrace();
			str = "{\"result\":\"注册失败\"}";
			str = jsonp + "(" +str+")";
			out.write(str);
		}
	}
	
	/**
	 * bbs注册和登录
	 * @author KHC
	 * @param phone
	 * @param pwd
	 * @param code
	 * @param qudao
	 * @param ic
	 * @param request
	 * @param response
	 * @param model
	 * @throws IOException
	 * @throws ServletException
	 * @since 2016年12月8日 下午3:02:35
	 */
	@RequestMapping("/activity/bbs/registerAcross.htm")
	public String bbsRegisterAcross(String phone,String pwd,String code,String qudao,String ic,HttpServletRequest request, HttpServletResponse response,Model model) 
			throws IOException, ServletException {
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			String sessioncode = (String)request.getSession().getAttribute("code");
			String sessionmobile = (String)request.getSession().getAttribute("phone");
			if(!code.equals(sessioncode) || !phone.equals(sessionmobile)){
				map.put("response", "failed");
				map.put("msg","手机验证码不正确！");
			}else{
				List<Member> memberList = memberService.getListByMobile(phone);			
				if(memberList!= null && memberList.size()!=0){
					map.put("response", "success");
					map.put("msg", "login");
					map.put("ic", memberList.get(0).getMyInvitationCode());
					request.getSession().setAttribute("member", memberList.get(0));
				}else{
					Member member = new Member();
					member.setMobile(phone);
					member.setRegtime(new Date());
					member.setQudao(qudao);
					
					if(org.apache.commons.lang.StringUtils.isNotBlank(ic)){
						Member m = memberService.getByMyInvitationCode(ic);
						if(m!=null){//如果推荐码正确则保存 推荐码
							member.setInvitationCode(ic);
						}
					}
					
					Cookie uuid = HttpUtil.getCookieByName(request, "uuid");
					if(uuid!=null){
						member.setUuid(uuid.getValue());//对应ClickCount 用户唯一标示
					}
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
					map.put("response", "success");
					map.put("msg", "register");
					map.put("ic", member.getMyInvitationCode());
					request.getSession().setAttribute("member", member);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg","注册失败！");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(map));
		return "ajax";
	}
	
	@RequestMapping("/activity/checkcode.htm")
	public void checkcode(String sessioncode, String code, String sessionmobile,String mobile, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		if(!code.equals(sessioncode) || !mobile.equals(sessionmobile)){
			out.print("验证码不正确");
		}else{
			out.print("success");
		}
	}
	
	@RequestMapping("/activity/sendcode.htm")
	public void sendcode(String mobile, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter(); 
		try{						
			List<Member> baseEntityList = memberService.getListByMobile(mobile);
			if(baseEntityList!=null&&baseEntityList.size()!=0){
				out.print("registered"); //已注册				
				return;
			}
			String code = String.valueOf((Math.random())).split("\\.")[1];
			code = code.substring(0,4);
			/*String message = "验证码:"+code+"【票据管家】";
			SendMessagesUtil.sendMessages(mobile, message);*/
			
			com.ry.util.SendMessagesUtil.aliSendRegistMessage(mobile, code);
			
			request.getSession().setAttribute("code", code);
			request.getSession().setAttribute("mobile", mobile);
			out.print("success");
		}catch(Exception e){
			e.printStackTrace();
			out.print("error");			
		}
				
	}
	@RequestMapping("/activity/sendcode1.htm")
	public void sendcode1(String phone, String flag, HttpServletRequest request, HttpServletResponse response,Model model) throws Exception {
		PrintWriter out = response.getWriter(); 
		try{						
			if("reg".equals(flag)){
				List<Member> baseEntityList = memberService.getListByMobile(phone);
				if(baseEntityList!=null&&baseEntityList.size()!=0){
					out.print("手机号码已经存在");
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
			request.getSession().setAttribute("phone", phone);
			out.print("success");
		}catch(Exception e){
			e.printStackTrace();
			out.print("发送失败"); 
		}
	}
	
	/**
	 * 获取交易金额
	 * @author KHC
	 * @throws IOException
	 */
	@RequestMapping("/activity/jiaoyijine")
	public @ResponseBody Map<String, Object> jiaoyijine() throws IOException{
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Map<String, Object> result = getJye();
			map.put("state", "success");
			map.put("month", result.get("month"));
			map.put("all", result.get("all"));
		}catch(Exception e){
			map.put("state", "error");
			map.put("msg", "获取失败!");
		}
		return map;
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
		if(org.apache.commons.lang.StringUtils.isNotBlank(add)){
			allMoney += Double.valueOf(add);
		}
		String add_m = variablesService.getByCode("ADD_TURNOVER_MONTH", null);//获取额外配置额(上月交易额)
		if(org.apache.commons.lang.StringUtils.isNotBlank(add_m)){
			month += Double.valueOf(add_m);
		}
		DecimalFormat df = new DecimalFormat("0");
		index_params.put("date", DateUtil.formart(new Date(),"yyyy-MM-dd"));
		index_params.put("month", df.format(month));
		index_params.put("all", df.format(allMoney));
		return index_params;
	}
	
	/**
	 * 获取邀请码
	 * @author KHC
	 * @param request
	 * @param response
	 * @param model
	 * @since 2016年12月7日 下午2:10:45
	 */
	@RequestMapping("/activity/invite")
	public String myInvitationCode(HttpServletRequest request,HttpServletResponse response,Model model){
		Member member = (Member)request.getSession().getAttribute("member");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Long invite = memberService.getByInvitationCode(member.getMyInvitationCode());
			map.put("invite",invite);
			map.put("response", "success");
		} catch (Exception e) {
			map.put("response", "failed");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(map));
		return "ajax";
	}
	
	//---------------------------------------微信文章推广-----------------------------------------------------
	
	private final String CODE = "tuiguang";//推广
	private final String STYLE = "WECHAT_GZQ";//微信推广-工作圈
	
	/**
	 * 打开推广页面
	 * @author WKX
	 * @param url 路径（加密过）
	 * @param model
	 */
	@RequestMapping("/tg")
	public String tg(String url,HttpServletRequest req,Model model){
		this.log(url, req);//统计访问量
		model.addAttribute("url", url);
		return "out-url";
	}
	
	/**
	 * 访问记录
	 * @author WKX
	 * @param wechat_url 文章加密路径
	 */
	private void log(String wechat_url,HttpServletRequest req){
		try {
			String url = req.getRequestURL().toString() ;
			ClickCount cc = new ClickCount();
			cc.setCode(CODE);
			cc.setCurrentDate(new Date());
			cc.setIp(HttpUtil.getIpAddr(req));
			cc.setStyle(STYLE);
			cc.setUrl(url);
			
			cc.setReferrerUrl(Base64Util.decryptBASE64(wechat_url));
			clickCountService.saveClickCount(cc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}