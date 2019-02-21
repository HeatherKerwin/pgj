package com.ry.ryapp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
/**
 * ����
 * @author Administrator
 *
 */
public class SendRequest {
	
	/**
	 * ����ģ��get����
	 * @param url
	 * @param headers
	 * @param params
	 * @param encoding
	 * @param duan
	 * @return
	 */
    public static Result sendGet(String url,Map<String,String> headers,Map<String,String>  params,String encoding,boolean duan){
		Result result = null;
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			url = url+(null==params?"":assemblyParameter(params));
			HttpGet hp = new HttpGet(url);
			if(null!=headers)hp.setHeaders(assemblyHeader(headers));
			HttpResponse response = client.execute(hp);
			if(duan) hp.abort();
			HttpEntity  entity = response.getEntity();
			result = new Result();
			result.setCookie(assemblyCookie(client.getCookieStore().getCookies()));
			result.setStatusCode(response.getStatusLine().getStatusCode());
			result.setHeaders(response.getAllHeaders());
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
     * ����ģ��post����
     * @param url
     * @param headers
     * @param params
     * @param encoding
     * @return
     */
    public static Result sendPost(String url,Map<String,String> headers,Map<String,String>  params,String encoding) {
		Result result = null;
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			List<NameValuePair> list  = new ArrayList<NameValuePair>();
			if(params != null){
				for (String temp : params.keySet()) {
					list.add(new BasicNameValuePair(temp,params.get(temp)));
				}
			}
			post.setEntity(new UrlEncodedFormEntity(list,encoding));
			
			if(null!=headers)post.setHeaders(assemblyHeader(headers));

			HttpResponse response = client.execute(post);
			HttpEntity  entity = response.getEntity();
			
			result = new Result();
			result.setStatusCode(response.getStatusLine().getStatusCode());
			result.setHeaders(response.getAllHeaders());
			result.setCookie(assemblyCookie(client.getCookieStore().getCookies()));
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
     * ������װcookie
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
    	params.put("companyName", "��Զ");
		Result r = SendRequest.sendPost("http://192.168.0.64:8080/front/html/company.htm", null,params,"gbk");
		System.out.println(r);
	}
    
    /**
	 * ��ȡ����
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
	 * get���� url ����ĵ�ַ
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
}