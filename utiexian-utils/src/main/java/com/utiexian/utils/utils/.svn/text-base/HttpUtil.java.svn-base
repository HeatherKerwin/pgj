package com.utiexian.utils.utils;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLException;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.utiexian.utils.cibfintech.econtract.SSLClient;

public class HttpUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	
	public final static int maxTotal = 200;
	public final static int maxPerRoute = 50;
	public final static int timeout = 30 * 1000;
	private static final String CHARSET_UTF8 = "UTF-8";
	
	/**
	 * 根据request获取IP
	 * @param request
	 */
	public static String getIpAddr(HttpServletRequest request) {
		
		String ip = request.getHeader("x-forwarded-for");
		if(ip != null&&ip.contains(",")){
			ip=ip.split(",")[0];
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	private int socketTimeout = 100000;
    private int connectTimeout = 300000;
	private RequestConfig requestConfig;
    private CloseableHttpClient httpClient;
	
    private CloseableHttpClient getHttpClient() throws Exception {
        if(httpClient==null){
        	httpClient = HttpClients.custom().setRetryHandler(new DefaultHttpRequestRetryHandler()).setConnectionManager(SSLClient.getPoolManager()).build();
            //根据默认超时限制初始化requestConfig

            requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
        }
        return httpClient;
    }
	
	/**
	 * 模拟POST请求（兴业数金专用）
	 * @author WKX
	 * @param url 请求路径
	 * @param params 参数
	 */
	public String get(String url,Map<String, Object> params) throws Exception {
		String respText = null;
		CloseableHttpClient client = getHttpClient();
		HttpResponse response = null;
		try {
			StringBuffer sbParams = new StringBuffer();
	        if (params != null && params.size() > 0) {
	            for (Entry<String, Object> entry : params.entrySet()) {
	                sbParams.append(entry.getKey());
	                sbParams.append("=");
	                sbParams.append(entry.getValue());
	                sbParams.append("&");
	            }
	        }
			
	        HttpGet get = null;
	        if (sbParams != null && sbParams.length() > 0) {
	        	get = new HttpGet(url + "?" + sbParams.substring(0, sbParams.length() - 1));
            } else {
            	get = new HttpGet(url);
            }

			get.addHeader("Content-Type", "application/json;charset=UTF-8");
			get.setConfig(requestConfig);

			response = client.execute(get);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 200) {
				logger.info("*********发送请求成功**********");
			} else {
				logger.error("*********发送请求失败[" + statusCode + "]**********");
			}

			respText = EntityUtils.toString(response.getEntity(), CHARSET_UTF8);
			logger.info("服务器返回respText:" + respText);
		} catch (Exception e) {
			if (e instanceof InterruptedIOException) {
				logger.error("连接服务器超时");
			} else if (e instanceof UnknownHostException) {
				logger.error("UnknownHost");
			} else if (e instanceof ConnectException) {
				logger.error("服务器拒绝连接");
			} else if (e instanceof SSLException) {
				logger.error("SSL协议错误");
			} else {
				logger.error("系统错误");
			}
			logger.error(e.getMessage(), e);
			throw e;
		}
		return respText;
	}
	
	/**
	 * 模拟POST请求（兴业数金专用）
	 * @author WKX
	 * @param url 请求地址
	 * @param params 参数JSON
	 */
	public String post(String url, Map<String,String> params) throws Exception {
		String respText = null;
		CloseableHttpClient client = getHttpClient();
		HttpResponse response = null;
		try {
			HttpPost post = new HttpPost(url);
			
			String body = JSON.toJSON(params).toString();
	        StringEntity entity = new StringEntity(body, "UTF-8");
			
	        entity.setChunked(true);
	        entity.setContentType("application/json");
			post.setEntity(entity);
			post.addHeader("Content-Type", "application/json;charset=" + CHARSET_UTF8);
			post.setConfig(requestConfig);

			response = client.execute(post);
			int statusCode = response.getStatusLine().getStatusCode();

			if (statusCode == 200) {
				logger.info("*********发送请求成功**********");
			} else {
				logger.error("*********发送请求失败[" + statusCode + "]**********");
			}

			respText = EntityUtils.toString(response.getEntity(), CHARSET_UTF8);
			logger.info("服务器返回respText:" + respText);
		} catch (Exception e) {
			if (e instanceof InterruptedIOException) {
				logger.error("连接服务器超时");
			} else if (e instanceof UnknownHostException) {
				logger.error("UnknownHost");
			} else if (e instanceof ConnectException) {
				logger.error("服务器拒绝连接");
			} else if (e instanceof SSLException) {
				logger.error("SSL协议错误");
			} else {
				logger.error("系统错误");
			}
			logger.error(e.getMessage(), e);
			throw e;
		}
		return respText;
	}
	
	/**
	 * POST加密请求（兴业数金专用）
	 * @param url 请求地址
	 * @param content 请求参数JSON
	 */
	public String post(String url, String content) throws Exception {
		CloseableHttpClient client = getHttpClient();
		CloseableHttpResponse response = null;
		try {
			HttpPost post = new HttpPost(url);
			StringEntity entity = new StringEntity(content, CHARSET_UTF8);

			post.setEntity(entity);
			post.addHeader("Content-Type", "application/json;charset=UTF-8");

			response = client.execute(post);
			int statusCode = response.getStatusLine().getStatusCode();

			if (statusCode == 200) {
				logger.info("*********发送请求成功**********");
			} else {
				logger.error("*********发送请求失败[" + statusCode + "]**********");
				throw new Exception("http返回码：" + statusCode);
			}

			String respText = EntityUtils.toString(response.getEntity(), CHARSET_UTF8);
			logger.info("服务器返回respText:" + respText);

			return respText;
		} catch (Exception e) {
			if (e instanceof InterruptedIOException) {
				logger.error("连接服务器超时");
			} else if (e instanceof UnknownHostException) {
				logger.error("UnknownHost");
			} else if (e instanceof ConnectException) {
				logger.error("服务器拒绝连接");
			} else if (e instanceof SSLException) {
				logger.error("SSL协议错误");
			} else {
				logger.error("系统错误");
			}
			logger.error(e.getMessage(), e);
			throw e;
		} finally {
			if (response != null) {
				response.close();
			}
		}
	}
	
	/* --------------------------------------以下为FORM请求模拟--------------------------------------------- */
	
	/**
	 * 模拟GET请求
	 * @param url 请求路径
	 * @param params 请求参数FORM
	 * @throws Exception 
	 */
	public static String sendGet(String url,Map<String,Object> params) throws Exception{
		String result = null;
		try {
			CloseableHttpClient client = HttpClients.createDefault();//实例化一个HttpClient的
			
			//如果有参数的就拼装起来
			url = url+(null==params?"":assemblyParameter(params));
			
			HttpGet hp = new HttpGet(url);//这是实例化一个get请求
			
			//执行请求后返回一个HttpResponse
			HttpResponse response = client.execute(hp);
			
			//返回一个HttpEntity
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity);
			
			return result;
		}catch (Exception e) {
			if(e instanceof InterruptedIOException){
				logger.error("连接服务器超时");
			}else if (e instanceof UnknownHostException){
				logger.error("UnknownHost");
			}else if (e instanceof ConnectException){
				logger.error("服务器拒绝连接");
			}else if (e instanceof SSLException){
				logger.error("SSL协议错误");
			}else if(e instanceof IOException){
				logger.error("连接错误");
			}else{
				logger.error("系统错误");
			}
			logger.error(e.getMessage(), e);
			throw e;
		}
    }
	
	/**
	 * 模拟POST请求
	 * @param url 请求路径
	 * @param params 请求参数FORM
	 * @throws Exception 
	 */
	public static String sendPost(String url,Map<String,Object> params) throws Exception {
		String result = null;
		try {
			CloseableHttpClient client = HttpClients.createDefault();//实例化一个Httpclient的
			
			HttpPost post = new HttpPost(url);//实例化一个post请求
			
			//设置需要提交的参数
			List<NameValuePair> list  = new ArrayList<NameValuePair>();
			if(params != null){
				for (String temp : params.keySet()) {
					list.add(new BasicNameValuePair(temp,params.get(temp).toString()));
				}
			}
			post.setEntity(new UrlEncodedFormEntity(list,CHARSET_UTF8));
			
			//实行请求并返回
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			
			result = EntityUtils.toString(entity);
			
			return result;
		}catch (Exception e) {
			if(e instanceof InterruptedIOException){
				logger.error("连接服务器超时");
			}else if (e instanceof UnknownHostException){
				logger.error("UnknownHost");
			}else if (e instanceof ConnectException){
				logger.error("服务器拒绝连接");
			}else if (e instanceof SSLException){
				logger.error("SSL协议错误");
			}else if(e instanceof IOException){
				logger.error("连接错误");
			}else{
				logger.error("系统错误");
			}
			logger.error(e.getMessage(), e);
			throw e;
		}
    }
	
	/**
     * 这是组装参数
     * @param parameters
     */
    public static String assemblyParameter(Map<String,Object> parameters){
        String para = "?";
        for (String str:parameters.keySet()) {
            para+=str+"="+parameters.get(str)+"&";
        }
        return para.substring(0,para.length()-1);
    }
}