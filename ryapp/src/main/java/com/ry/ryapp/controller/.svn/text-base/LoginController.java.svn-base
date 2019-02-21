package com.ry.ryapp.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ry.core.entity.Member;
import com.ry.core.entity.Org;
import com.ry.core.entity.ThirdAuth;
import com.ry.core.service.MemberService;
import com.ry.core.service.OrgService;
import com.ry.core.service.SysteminfoService;
import com.ry.core.service.ThirdAuthService;
import com.ry.ryapp.util.unionpay.HttpRequestUtil;

/**
 * 账号绑定的处理
 * @author ZWD
 *
 */
@Controller
@RequestMapping("login")
public class LoginController {
	private static final Logger logger = Logger.getLogger(LoginController.class);
	public static Map<String , Object> CACHEMAP = new ConcurrentHashMap<String , Object>();
	@Resource
	ThirdAuthService thirdAuthService;
	
	@Resource
	MemberService memberService;
    
	@Resource
	OrgService orgService;
	
	@Resource					
	SysteminfoService systeminfoService;
	
	/**
     * 
     * [请求返回值中还包含：errcode、errmsg]
     * @author ZWD
     * @throws Exception
     * @since 2017年3月22日 下午4:44:34
     */
	private static final String APPID = "wx9ebf6146059f1e1e";//第三方用户唯一凭证
    private static final String SECRET = "2228ff9b6e129cb4c783c0d2cd183480";//第三方用户唯一凭证密钥，即appsecret
    @RequestMapping("getopid")
    public @ResponseBody Map<String,Object> getopid(HttpServletRequest request , String code){
    	Map<String,Object> result = new HashMap<String, Object>();
		String param = "appid="+APPID+"&secret="+SECRET+"&js_code="+code+"&grant_type=autho rization_code";
		String json = HttpRequestUtil.sendPost("https://api.weixin.qq.com/sns/jscode2session" , param);
		Map<String, Object> map = JSON.parseObject(json,new TypeReference<Map<String, Object>>(){});
		String openid ="";
		try{
			openid = map.get("openid").toString();
		}catch(Exception e){
			result.put("msg", "failed");
			return result;
		}
		result.put("openid", openid);
		List<ThirdAuth> thirdAuth = thirdAuthService.getByAttr(null, 4, openid, null);
		if(thirdAuth.size()>0){
			Integer memberId = thirdAuth.get(0).getMemberId();
			//request.setAttribute("USER", memberService.getById(memberId));
			Member member= memberService.getById(memberId);
			CACHEMAP.put(openid, member);
			result.put("mobile", member.getMobile());
			result.put("memberId", memberId);
			result.put("msg", "success");
		}else{
			result.put("msg", "failed");
		}
		return result;
	}
    
    
    /**
	 * 获取手机验证码
	 * @author ZWD
	 * @param flag
	 * @param mobile
	 * @param myCode
	 * @param request
	 * @param response
	 * @throws IOException
	 * @since 2016年10月26日 下午7:20:16
	 */
	@RequestMapping("/sendcode")
	public @ResponseBody Map<String, Object> sendcode(String mobile, HttpServletRequest request,String openid) throws IOException{
		Map<String, Object> result = new HashMap<String, Object>();
		try{
//			if("reg".equals(flag)){
//			Member lint= memberService.shouji(mobile);
//				if(lint!=null){
//					out.print("手机号码已经存在");
//					return "ajax";
//				}
//			}else if("forget".equals(flag)){
//				Member lint1 = memberService.shouji(mobile);
//				if(lint1==null){
//					out.print("手机号码不存在");
//					return "ajax";
//				}
//			}
			String code = String.valueOf((Math.random())).split("\\.")[1];
			code = code.substring(0,4);
			//发送验证码
			
			com.ry.util.SendMessagesUtil.aliSendRegistMessage(mobile, code);
			System.out.println(code);
			logger.info("mobile:"+mobile+"****code:"+code);
			CACHEMAP.put(openid+"CODE", code);
			CACHEMAP.put(openid+"MOBILE", mobile);
			result.put("msg", "success");
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "failed");
		}
		return result;
	}
	
	/**
	 * 绑定账号
	 * @author MH
	 * @return
	 */
	@RequestMapping("binding")
	public @ResponseBody Map<String, Object> binding(HttpServletRequest request,Model model,String mobile,String code,String openid){
		Map<String, Object> map = new HashMap<String, Object>();
		Member member = null;
		try {
			String sessioncode = CACHEMAP.get(openid + "CODE").toString();
			String sessionmobile = CACHEMAP.get(openid + "MOBILE").toString();
			if(!code.equals(sessioncode) || !mobile.equals(sessionmobile)){
				map.put("msg","codeError");
				return map;
			}
			CACHEMAP.remove(openid + "CODE");
			CACHEMAP.remove(openid + "MOBILE");
			List<Member> members = memberService.getListByMobile(mobile);
			if(members!=null && members.size()>0){
				member = members.get(0);
				List<ThirdAuth> thirdAuth = thirdAuthService.getByAttr(member.getId(), null, null, null); 
				if(thirdAuth != null && thirdAuth.size()>0){
					ThirdAuth thirdAuth1 = thirdAuth.get(0);
					thirdAuth1.setWxappletId(openid);
					thirdAuthService.saveOrUpdate(thirdAuth1);
				}else{
					ThirdAuth thirdAuth1 = new ThirdAuth ();
					thirdAuth1.setMemberId(member.getId());
					thirdAuth1.setWxappletId(openid);
					thirdAuthService.saveOrUpdate(thirdAuth1);
				}
				if(member!=null){//未读消息
					//保存用户信息
					CACHEMAP.put(openid, member); 
					Integer message = systeminfoService.getMessage(member.getId());
					if(message!=null)request.getSession().setAttribute("MEMBER_MESSAGE", "("+message+")");
				}
				map.put("response", "success");
				map.put("memberId", member.getId());
			}else{
				member = new Member();
				Org org = new Org();
				member.setMobile(mobile);
				member.setRegtime(new Date());
				member.setQudao("WXMINI");			
				memberService.addMember(member);
				member = Member.deMember(member);
				org.setMemberId(member.getId());
				org.setType(1);
				org.setState(0);
				org.setCreateTime(new Date());
				org = orgService.saveModel(org);
				
				ThirdAuth thirdAuth1 = new ThirdAuth ();
				thirdAuth1.setMemberId(member.getId());
				thirdAuth1.setWxappletId(openid);
				thirdAuthService.saveOrUpdate(thirdAuth1);
				
				//保存用户信息
				CACHEMAP.put(openid, member); 
				map.put("response", "success");
				map.put("memberId", member.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "登录失败");
		}
		//model.addAttribute("retValue", JacksonUtil.objToJson(map));
		return map;
	}
	
	@RequestMapping("unbinding")
	public @ResponseBody Map<String,Object> unbinding(String openid){
		Map<String , Object> result = new HashMap<String , Object>();
		Integer memberId = ((Member)CACHEMAP.get(openid)).getId();
		List<ThirdAuth> thirdAuth = thirdAuthService.getByAttr(Integer.valueOf(memberId).intValue(), null, null, null); 
		try {
			if(thirdAuth != null && thirdAuth.size()>0) {
				ThirdAuth thirdAuth1 = thirdAuth.get(0); 
				thirdAuth1.setWxappletId(null);
				thirdAuthService.saveOrUpdate(thirdAuth1);
			}
			result.put("response", "success");
		} catch (Exception e) {
			result.put("response", "failed");
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("getmobile")
	public @ResponseBody Map<String,Object> getmobile(String openid){
		Map<String , Object> result = new HashMap<String , Object>();
		Member member = (Member)CACHEMAP.get(openid);
		String mobile1 = member.getMobile().substring(0, 3);
		String mobile2 = member.getMobile().substring(7, 11);
		String mmobile = mobile1+"****"+mobile2;
		result.put("mmobile", mmobile);
		return result;
	}
	
}
