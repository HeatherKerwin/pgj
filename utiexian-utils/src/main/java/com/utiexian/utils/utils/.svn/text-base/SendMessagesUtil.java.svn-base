package com.utiexian.utils.utils;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.utiexian.utils.MyException;


public class SendMessagesUtil {
	
	/**
	 * 模板名称: 用户注册验证码
	 * @param mobile 手机号
	 * @param code 验证码
	 * 模板内容: 验证码${code}，您正在注册成为${product}用户，感谢您的支持！
	 */
	public static void aliSendRegistMessage(String mobile,String code){
		TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23282500", "215f4916892e24cf1af1b076fa371525");
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setSmsType("normal");
		req.setSmsFreeSignName("票据管家");
		
		req.setSmsParam("{\"code\":\""+code+"\",\"product\":\"票据管家\"}");
		req.setRecNum(mobile);
		req.setSmsTemplateCode("SMS_3135164");
		try {
			client.execute(req);
		} catch (ApiException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 模板名称: 修改密码验证码
	 * @param mobile 手机号
	 * @param code 验证码
	 * 模板内容: 验证码${code}，您正在尝试修改${product}登录密码，请妥善保管账户信息。
	 */
	public static void aliSendFindPwdMessage(String mobile, String code) throws ApiException{
		TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23282500", "215f4916892e24cf1af1b076fa371525");
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setSmsType("normal");
		req.setSmsFreeSignName("票据管家");
		req.setSmsParam("{\"code\":\""+code+"\",\"product\":\"票据管家\"}");
		req.setRecNum(mobile);
		req.setSmsTemplateCode("SMS_3135162");
		client.execute(req);
	}
	
	/**
	 * 模板名称: 身份验证验证码
	 * @param mobile 手机号
	 * @param code 验证码
	 * 模板内容: 验证码${code}，您正在进行${product}身份验证，打死不要告诉别人哦！
	 */
	public static void aliSendIdentityMessage(String mobile, String code) throws ApiException{
		TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23282500", "215f4916892e24cf1af1b076fa371525");
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setSmsType("normal");
		req.setSmsFreeSignName("票据管家");
		req.setSmsParam("{\"code\":\""+code+"\",\"product\":\"票据管家\"}");
		req.setRecNum(mobile);
		req.setSmsTemplateCode("SMS_3135167");
		client.execute(req);
	}
	
	/**
	 * 发送短信
	 * @author WKX
	 * @param mobile 手机号
	 * @param template 模板编号
	 * @param param 参数
	 * @throws ApiException 
	 */
	public static void sendMessage(String mobile,String template,Map<String,String> param) throws ApiException{
		TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23282500", "215f4916892e24cf1af1b076fa371525");
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setSmsType("normal");
		req.setSmsFreeSignName("票据管家");
		req.setSmsParam(JacksonUtil.objToJson(param));
		req.setRecNum(mobile);
		req.setSmsTemplateCode(template);
		client.execute(req);
	}
	
	/**
	 * 发送验证码（笼统的模板）
	 * @author WKX
	 * @param mobile 手机号
	 * @param code 验证码
	 * 模板内容: 您的手机验证码是：${code}，请勿泄露
	 * @throws ApiException 
	 */
	public static void sendMessage(String mobile,String code) throws ApiException {
		if(StringUtils.isBlank(mobile) || StringUtils.isBlank(code))throw new MyException();
		Map<String,String> param = new HashMap<String, String>();
		param.put("code", code);
		sendMessage(mobile, "SMS_106895156", param);
	}
}