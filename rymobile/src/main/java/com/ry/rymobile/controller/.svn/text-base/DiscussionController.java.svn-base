package com.ry.rymobile.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Discussion;
import com.ry.core.service.DiscussionService;
import com.ry.core.util.Constant;
import com.ry.util.JacksonUtil;
import com.ry.util.PropertiesUtil;
import com.ry.util.SendMessagesUtil;


@Controller
@RequestMapping("/discussion/")
public class DiscussionController {
	
	@Resource
	private DiscussionService discussionService;
	
	private static String INSIDE = "RUIYIN50965066";//授权码
	
	/**
	 * 座谈会验证页面
	 * @author WKX
	 * @param model
	 * @throws Exception
	 * @since 2016年10月12日 下午2:27:25
	 */
	@RequestMapping("phone.htm")
	public String phone(Model model) throws Exception{
		return "/zuotan/phone";
	}
	
	/**
	 * 签到页面调整
	 * @author WKX
	 * @param model
	 * @throws IOException
	 * @since 2016年10月17日 下午3:06:08
	 */
	@RequestMapping("/check.htm")
	public String checkQrcode(Integer id,String phone,Model model) throws IOException {
		model.addAttribute("id", id);
		model.addAttribute("phone", phone);
		return "/zuotan/fankui";
	}
	
	/**
	 * 发送验证码
	 * @author WKX
	 * @param mobile 手机号
	 * @param request
	 * @param response
	 * @throws Exception
	 * @since 2016年10月16日 下午6:49:48
	 */
	@RequestMapping("sendcode.htm")
	public String sendCode(String phone,String jsonCallback,HttpServletRequest request,HttpServletResponse response,Model model) throws Exception {
		response.setHeader("Access-Control-Allow-Methods","GET,POST");
		Map<String,Object> result = new HashMap<String, Object>();
		try{
			if(StringUtils.isBlank(phone))throw new Exception("请输入手机号");
			Discussion temp = discussionService.getByPhone(phone);
			if(temp==null){
				String code = String.valueOf((Math.random())).split("\\.")[1];
				code = code.substring(0,4);
				
				Map<String,String> param = new HashMap<String, String>();
				param.put("code",code);
				SendMessagesUtil.sendMessage(phone, "SMS_18285018", param);
				
				request.getSession().setAttribute("code", code);
				request.getSession().setAttribute("phone", phone);
				result.put("response", "success");
				result.put("msg", "发送成功，请注意查收");
			}else{
				result.put("response", "failed");
				result.put("msg", "您已申请该活动");
			}
		}catch(Exception e){
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", "获取失败");
		}
		String json = JacksonUtil.objToJson(result);
		if(StringUtils.isNotBlank(jsonCallback)){
			json = jsonCallback + "(" +json+ ")";
		}
		model.addAttribute("retValue",json);
		return "ajax";
	}
	
	/**
	 * 创建二维码（座谈会入场用）
	 * @author WKX
	 * @param name 用户名
	 * @param pwd 密码
	 * @param count 数量
	 * @param path 二维码保存路径
	 * @param model
	 * @throws IOException
	 * @since 2016年10月16日 下午2:04:49
	 */
	@RequestMapping("/create.htm")
	public String createQrcode(Discussion discussion,String code,String jsonCallback,HttpServletRequest request,HttpServletResponse response,Model model) throws IOException {
		response.setHeader("Access-Control-Allow-Methods","GET,POST");
		Map<String,Object> result = new HashMap<String, Object>();
		String prefix = PropertiesUtil.getConfigPropertiesValue("ZUOTAN",null);
		try {
			if(StringUtils.isBlank(prefix))throw new Exception("操作异常，请联系客服");
			if(discussion==null)throw new Exception("请填写信息");
			if(StringUtils.isBlank(discussion.getCompany()))throw new Exception("请填写企业名称");
			if(StringUtils.isBlank(discussion.getName()))throw new Exception("请填写联系人");
			if(StringUtils.isBlank(discussion.getPhone()))throw new Exception("请填写联系方式");
			if(StringUtils.isBlank(code))throw new Exception("请填写验证码");
			
			String sessioncode = (String)request.getSession().getAttribute("code");
			String sessionphone = (String)request.getSession().getAttribute("phone");
			if(!code.equals(sessioncode) || !discussion.getPhone().equals(sessionphone))throw new Exception("验证码不正确");
			
			Discussion temp = discussionService.getByPhone(discussion.getPhone());
			if(temp!=null)throw new Exception("您已申请该活动，请勿重复提交");
			
			String uploadPath = Constant.properties.getProperty("uploadpath") + Constant.UPLOAD_SRC;//保存目录包名的绝对路径
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			String extpath = df.format(new Date());
			
			String fileName = randomPath();
			String savePath = uploadPath + extpath + fileName;
			String path = Constant.UPLOAD_SRC + extpath + fileName;
			discussionService.saveAndCreateQrcode(discussion,prefix,savePath,path);
			
			String imgPath = Constant.properties.getProperty("preimgUrl");
			result.put("imgPath", imgPath);//图片访问路径
			result.put("data", discussion);
			result.put("response", "success");
		} catch (Exception e) {
			result.put("msg", e.getMessage());
			result.put("response", "failed");
			e.printStackTrace();
		}
		String json = JacksonUtil.objToJson(result);
		if(StringUtils.isNotBlank(jsonCallback)){
			json = jsonCallback + "(" +json+ ")";
		}
		model.addAttribute("retValue",json);
		return "ajax";
	}
	
	/**
	 * 生成随机文件名称
	 * @author WKX
	 * @since 2016年10月16日 下午6:24:15
	 */
	public String randomPath(){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = format.format(new Date())+"_";
		for(int i = 0; i < 3; i++){
			str += (int)(Math.random()*10);
		}
		String code = File.separator + str + ".png";
		return code;
	}
	
	/**
	 * 内部扫码授权
	 * @author WKX
	 * @param request
	 * @param model
	 * @throws IOException
	 * @since 2016年10月17日 下午3:08:29
	 * @URL:http://192.168.1.110:8080/rymobile/discussion/auth.htm?code=RUIYIN50965066
	 */
	@RequestMapping("/auth.htm")
	public String authQrcode(String code,Model model) throws IOException {
		try {
			if(StringUtils.isBlank(code) || !code.equals(INSIDE))throw new Exception("授权失败");
			model.addAttribute("code",INSIDE);
		} catch (Exception e) {
			model.addAttribute("msg","授权失败");
			e.printStackTrace();
		}
		return "/zuotan/auth";
	}
	
	/**
	 * 签到（未授权用户扫码签到无效）扫码签到和输入手机号签到
	 * @author WKX
	 * @param id 主键
	 * @param phone 手机号
	 * @param request
	 * @param model
	 * @throws IOException
	 * @since 2016年10月17日 下午3:06:08
	 */
	@RequestMapping("/check/do.htm")
	public String checkQrcode(String code,String id,String phone,Model model) throws IOException {
		Map<String,Object> result = new HashMap<String, Object>();
		Discussion discussion = null;
		try {
			if(StringUtils.isNotBlank(id)){
				discussion = discussionService.getById(Integer.valueOf(id));
			}else if(StringUtils.isNotBlank(phone)){
				discussion = discussionService.getByPhone(phone);
			}else{
				result.put("CODE", "ERROR");
				throw new Exception("很抱歉，经审核，您无法参加本次票管家举行的企业座谈会，敬请期待票管家其他活动。");
			}
			if(discussion==null || discussion.getState()==2){
				result.put("CODE", "ERROR");
				throw new Exception("很抱歉，经审核，您无法参加本次票管家举行的企业座谈会，敬请期待票管家其他活动。");
			}
			if(StringUtils.isBlank(code) || !code.equals(INSIDE)){//获取授权码
				result.put("CODE", "NOAUTH");
				throw new Exception("此二维码用于11月25号参加票管家企业座谈会入场时使用，欢迎届时光临。");
			}else{
				if(discussion.getState()==1){
					result.put("CODE", "USED");
					throw new Exception("此用户已被验证过，请核对用户信息后允许进场。");
				}
				
				discussion.setState(1);//已签到
				discussion.setCheckInTime(new Date());//签到日期
				discussionService.updateModel(discussion);//更新状态
				result.put("response", "success");
				result.put("msg", "此用户为邀请嘉宾，欢迎进场。");
			}
		} catch (Exception e) {
			result.put("msg", e.getMessage());
			result.put("response", "failed");
			e.printStackTrace();
		}
		result.put("data", discussion);
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
}