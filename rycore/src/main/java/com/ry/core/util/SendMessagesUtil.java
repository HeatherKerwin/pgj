package com.ry.core.util;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class SendMessagesUtil {
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
}
