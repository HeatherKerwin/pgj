package com.ry.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

/**
 * 模拟http请求
 * @author wangkai
 *
 */
public class HttpUtil {
	private static final Logger logger = Logger.getLogger(HttpUtil.class);
	private static String domain;
	private static String path = "/";
	
	/**
	 * 根据request获取IP
	 * @param request
	 * @return
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
	
	/**
	 * 这是模拟get请求
	 * @param url
	 * @param headers
	 * @param params
	 * @param encoding
	 * @param duan
	 * @return
	 */
    public static Result sendGet(String url,Map<String,String> headers,Map<String,String>  params,String encoding,boolean duan){
    	//封装返回的参数
		Result result = null;
		try {
			//实例化一个Httpclient的
			DefaultHttpClient client = new DefaultHttpClient();
			//如果有参数的就拼装起来
			url = url+(null==params?"":assemblyParameter(params));
			//这是实例化一个get请求
			HttpGet hp = new HttpGet(url);
			//如果需要头部就组装起来
			if(null!=headers)hp.setHeaders(assemblyHeader(headers));
			//执行请求后返回一个HttpResponse
			HttpResponse response = client.execute(hp);
			//如果为true则断掉这个get请求
			if(duan) hp.abort();
			//返回一个HttpEntity
			HttpEntity  entity = response.getEntity();
			result = new Result();
			//设置返回的cookie
//			result.setCookie(assemblyCookie(client.getCookieStore().getCookies()));
			//设置返回的状态
			result.setStatusCode(response.getStatusLine().getStatusCode());
			//设置返回的头部信心
			result.setHeaders(response.getAllHeaders());
			//设置返回的信息
			result.setHttpEntity(entity);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return result;
    }
    public static Result sendGet(String url,Map<String,String> headers,Map<String,String>  params,String encoding){
        return sendGet(url, headers, params, encoding,false);
    }

    /**
     * 这是模拟post请求
     * @param url
     * @param headers
     * @param params
     * @param encoding
     * @return
     */
    public static Result sendPost(String url,Map<String,String> headers,Map<String,String>  params,String encoding) {
        //封装返回的参数
		Result result = null;
		try {
			//实例化一个Httpclient的
			DefaultHttpClient client = new DefaultHttpClient();
			//实例化一个post请求
			HttpPost post = new HttpPost(url);
			
			//设置需要提交的参数
			List<NameValuePair> list  = new ArrayList<NameValuePair>();
			if(params != null){
				for (String temp : params.keySet()) {
					list.add(new BasicNameValuePair(temp,params.get(temp)));
				}
			}
			post.setEntity(new UrlEncodedFormEntity(list,encoding));
			
			//设置头部
			if(null!=headers)post.setHeaders(assemblyHeader(headers));

			//实行请求并返回
			HttpResponse response = client.execute(post);
			HttpEntity  entity = response.getEntity();
			
			result = new Result();
			//设置返回状态代码
			result.setStatusCode(response.getStatusLine().getStatusCode());
			//设置返回的头部信息
			result.setHeaders(response.getAllHeaders());
			//设置返回的cookie信心
//			result.setCookie(assemblyCookie(client.getCookieStore().getCookies()));
			//设置返回到信息
			result.setHttpEntity(entity);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return result ;
    }
    

    /**
     *  这是组装头部
     * @param headers
     * @return
     */
    public static Header[] assemblyHeader(Map<String,String> headers){
        Header[] allHeader= new BasicHeader[headers.size()];
        int i  = 0;
        for (String str :headers.keySet()) {
            allHeader[i] = new BasicHeader(str,headers.get(str));
            i++;
        }
        return allHeader;
    }
    
    /**
     * 这是组装cookie
     * @param cookies
     * @return
     */
    public static String assemblyCookie(List<Cookie> cookies){
        StringBuffer sbu = new StringBuffer();
        for (Cookie cookie : cookies) {
            sbu.append(cookie.getName()).append("=").append(cookie.getValue()).append(";");
        }
        if(sbu.length()>0)sbu.deleteCharAt(sbu.length()-1);
        return sbu.toString();
    }
    
    /**
     * 这是组装参数
     * @param parameters
     * @return
     */
    public static String assemblyParameter(Map<String,String> parameters){
        String para = "?";
        for (String str :parameters.keySet()) {
            para+=str+"="+parameters.get(str)+"&";
        }
        return para.substring(0,para.length()-1);
    }
    
    public static void main(String[] args){
    	Map<String,String>  params = new HashMap<String, String>();
//    	params.put("companyName", "申远");
//		Result r = HttpUtil.sendPost("http://192.168.0.64:8080/front/html/company.htm", null,params,"gbk");
//		System.out.println(r);
	}
    
    /**
	 * 获取参数
	 */
	public static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * get请求 url 请求的地址
	 */
	public static String getParam(Result result){
		if (result!=null && result.getHttpEntity() != null) {
			InputStream instreams;
			try {
				instreams = result.getHttpEntity().getContent();
				String str = convertStreamToString(instreams);
				return str;
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * <p class="detail">清理cookie,网站的路径与domain请设置本类静态字段</p>
	 * @author <a href="mailto:luoweijun@wokejia.com ">罗伟俊</a> 2012-11-5 下午2:19:01
	 * @param request              http请求
	 * @param response            http响应
	 * @param cookieName      需要删除的cookie名称
	 */
	public static void clearCookie(HttpServletRequest request,HttpServletResponse response,String ... cookieName){
		clearCookie(request, response, path, domain, cookieName); 
	}
	
	/**
	 * <p class="detail">清理cookie,网站的路径与domain请设置本类静态字段</p>
	 * @author <a href="mailto:luoweijun@wokejia.com ">罗伟俊</a> 2012-11-5 下午2:19:01
	 * @param request              http请求
	 * @param response            http响应
	 * @param path                   cookie Path
	 * @param domain              cookie domain
	 * @param cookieName      需要删除的cookie名称
	 */
	public static void clearCookie(HttpServletRequest request,
			HttpServletResponse response, String path, String domain,
			String... cookieName) {
		if(cookieName == null || cookieName.length == 0)return;
		Cookie[] cookies=request.getCookies();
		try{
		     for(int i=0;i<cookies.length;i++){
		    	 Cookie cookie = cookies[i];
	    	 	for(int n=0;n<cookieName.length;n++){
	    	 		 if (cookieName[n].equals(cookie.getName())){
	   		    	  cookie.setMaxAge(0);
	   		    	  cookie.setPath(path);
	   		    	  cookie.setDomain(domain);
	   		    	  response.addCookie(cookie);
	   		      }
				}
		     }
		}catch(Exception ex){
			logger.error("",ex);
		}
	}
	
	/**
	 * <p class="detail">添加cookie</p>
	 * @param response  http 响应
	 * @param key          cookie 键
	 * @param value       cookie 值
	 * @param domain    cookie 域
	 * @param path        路径
	 * @param expiry      失效时间
	 */
	public static void addCookie(HttpServletResponse response,String key,String value,String domain,String path,int expiry){
        Cookie cookie =new Cookie(key, value);
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookie.setMaxAge(expiry);
        response.addCookie(cookie);
	}
	
	/**
	 * <p class="detail">添加cookie，path，domian根据HttpUtil静态设置</p>
	 * @param response  http 响应
	 * @param key          cookie 键
	 * @param value       cookie 值
	 * @param domain    cookie 域
	 * @param path        路径
	 * @param expiry      失效时间
	 */
	public static void addCookie(HttpServletResponse response,String key,String value,int expiry){
        Cookie cookie =new Cookie(key, value);
        if (domain != null && !"".equals(domain)) {        	
        	cookie.setDomain(domain);
        }
        cookie.setPath(path);
        cookie.setMaxAge(expiry);
        response.addCookie(cookie);
	}
	
	/**
	 * 
	  * getCookieByName 方法
	  * <p>方法说明:根据名字获取cookie</p>
	  * @param request
	  * @param name cookie名字
	  * @return
	  * @return Cookie
	  * @author 王凯
	  * @date 2014-1-2
	 */
	public static Cookie getCookieByName(HttpServletRequest request,String name){
	    Map<String,Cookie> cookieMap = ReadCookieMap(request);
	    if(cookieMap.containsKey(name)){
	        Cookie cookie = (Cookie)cookieMap.get(name);
	        return cookie;
	    }else{
	        return null;
	    }   
	}
	 
	 
	 
	/**
	 * 
	  * ReadCookieMap 方法
	  * <p>方法说明:将cookie封装到Map里面</p>
	  * @param request
	  * @return
	  * @return Map<String,Cookie>
	  * @author 王凯
	  * @date 2014-1-2
	 */
	private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){  
	    Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
	    Cookie[] cookies = request.getCookies();
	    if(null!=cookies&&cookies.length > 0){
	        for(Cookie cookie : cookies){
	            cookieMap.put(cookie.getName(), cookie);
	        }
	    }
	    return cookieMap;
	}
	
	
	
	/**
	 * 添加cookie
	 * @param request
	 * @param response
	 * @param name cookie名字
	 * @param value	cookie值
	 * @param age	cookie生命周期
	 */
	public static void setCookie(HttpServletRequest request,HttpServletResponse response,String name, String value,Integer age){
		Cookie cookie = new Cookie(name, value);
		if (StringUtils.hasText(request.getHeader("Referer"))
				&& request.getHeader("Referer").indexOf("ryfinance.com") > 0) {
			cookie.setDomain("ryfinance.com");
		}
		cookie.setPath("/");
		if(age!=null){
			cookie.setMaxAge(age);// 设置登录的cookie有效期
		}
		response.addCookie(cookie);
	}
}