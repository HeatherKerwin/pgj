package com.ry.util;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;


public class SendMessagesUtil {
	
	/**
	 * 改方法已废弃[用下面的]
	 * @author RY
	 * @param mobile
	 * @param messages
	 * @since 2016年1月20日 下午7:41:32
	 */
	public static Map<String, Object> sendMessages(String mobile,String messages){
		int status = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			HttpClient client = new HttpClient();
			//                                http://si.800617.com:4400/SendLenSms.aspx?un=ctyswse-47&pwd=8a9e3f&mobile=15306275568&msg=11111
			PostMethod post = new PostMethod("http://si.800617.com:4400/SendLenSms.aspx"); 
			post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gb2312");//在头文件中设置转码
			NameValuePair[] data ={ new NameValuePair("un", "shryjr-1"),new NameValuePair("pwd", "2b8d4f"),new NameValuePair("mobile",mobile),new NameValuePair("msg",messages)};
			post.setRequestBody(data);
			
			client.executeMethod(post);
			Header[] headers = post.getResponseHeaders();
			int statusCode = post.getStatusCode();
			for(Header h : headers)
			{
//				System.out.println(h.toString());
			}
			String result = new String(post.getResponseBodyAsString().getBytes("gb2312")); 
			System.out.println(result);
//			status = Integer.valueOf(result);
			post.releaseConnection();
			map.put("response", "success");
			map.put("msg", "发送成功");
			return map;
		}catch(Exception e){
			status = 0;
			map.put("response", "failed");
			map.put("msg", "发送验证码失败");
			e.printStackTrace();
			return map;
		}
//		}finally{
//			return status;
//		}
	} 
//	public static void main(String[] args) {
//		try {
//			sendMessages("15306275568","测试");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public static void aliSendRegistMessage(String mobile,String code){
		TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23282500", "215f4916892e24cf1af1b076fa371525");
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setSmsType("normal");
		req.setSmsFreeSignName("票据管家");
		
		req.setSmsParam("{\"code\":\""+code+"\",\"product\":\"票据管家\"}");
		
		req.setRecNum(mobile);
		req.setSmsTemplateCode("SMS_3135164");
		try {
			AlibabaAliqinFcSmsNumSendResponse response = client.execute(req);
		} catch (ApiException e) {
			e.printStackTrace();
		}
	}
	
	public static void aliSendFindPwdMessage(String mobile, String code) throws ApiException{
		TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23282500", "215f4916892e24cf1af1b076fa371525");
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setSmsType("normal");
		req.setSmsFreeSignName("票据管家");
		req.setSmsParam("{\"code\":\""+code+"\",\"product\":\"票据管家\"}");
		req.setRecNum(mobile);
		req.setSmsTemplateCode("SMS_3135162");
		AlibabaAliqinFcSmsNumSendResponse response = client.execute(req);
	}
	
	public static void aliSendIdentityMessage(String mobile, String code) throws ApiException{
		TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23282500", "215f4916892e24cf1af1b076fa371525");
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setSmsType("normal");
		req.setSmsFreeSignName("票据管家");
		req.setSmsParam("{\"code\":\""+code+"\",\"product\":\"票据管家\"}");
		req.setRecNum(mobile);
		req.setSmsTemplateCode("SMS_3135167");
		AlibabaAliqinFcSmsNumSendResponse response = client.execute(req);
	}
	
	/**
	 * 发送短信
	 * @author WKX
	 * @param mobile
	 * @param template
	 * @param param
	 * @since 2016年1月20日 下午7:55:51
	 */
	public static Map<String, Object> sendMessage(String mobile,String template,Map<String,String> param){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23282500", "215f4916892e24cf1af1b076fa371525");
			AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
			req.setSmsType("normal");
			req.setSmsFreeSignName("票据管家");
			req.setSmsParam(JacksonUtil.objToJson(param));
			req.setRecNum(mobile);
			req.setSmsTemplateCode(template);
			client.execute(req);
			map.put("response", "success");
			map.put("msg", "发送成功");
		} catch (ApiException e) {
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "发送失败");
		}
		return map;
	}
}