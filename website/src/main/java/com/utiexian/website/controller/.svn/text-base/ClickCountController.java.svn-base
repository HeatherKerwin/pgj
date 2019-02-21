package com.utiexian.website.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ry.core.entity.ClickCount;
import com.ry.core.entity.Member;
import com.ry.core.service.ClickCountService;
import com.ry.util.HttpUtil;
import com.ry.util.UrlUtil;

/**
 * @author KHC
 * 统计
 */
@Controller
@RequestMapping("/tg/clickCount")
public class ClickCountController {
	
	@Resource
	ClickCountService clickCountService;

	/**
	 * 获取客户端的ip
	 * @author KHC
	 * @param request
	 * @param resp
	 * @throws IOException
	 * @since 2016年12月12日 上午10:27:38
	 */
	@RequestMapping("/getIp")
	public String getIpAddr(HttpServletRequest request,HttpServletResponse resp) throws IOException {  
	    String ip = request.getHeader("x-forwarded-for");  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("WL-Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getRemoteAddr();  
	    }  
	    return ip;  
	}  
	
	/**
	 * 保存统计
	 * @author KHC
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @since 2016年12月12日 上午10:28:51
	 */
	@RequestMapping("/save")
	public void saveClickCount(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String refer = req.getHeader("referer");
		String url = req.getParameter("url");
		
		ClickCount clickCount = new ClickCount();
		clickCount.setUrl(url);
		
		clickCount.setCurrentDate(new Date());
		clickCount.setUuid(req.getParameter("uuid"));
		clickCount.setIp(HttpUtil.getIpAddr(req));
		
		String code=  req.getParameter("code");
		clickCount.setCode(code);
		//广告点击统计中带有_
		if("visit".equals(code)||code.indexOf("_")>-1){
			String referrerUrl = req.getParameter("referrerUrl");
			clickCount.setReferrerUrl(referrerUrl);
			
			if(StringUtils.isNotBlank(referrerUrl)){//获取关键字（保存关键字）
				Map<String, String> map = UrlUtil.URLRequest(referrerUrl);
				Object word = map.get("word");
				Object wd = map.get("wd");
				Object kw = map.get("kw");
				Object keyword = map.get("keyword");
				Object q = map.get("q");//360搜索关键字
				if(word!=null){
					clickCount.setKeyword(UrlUtil.removeFourChar(word.toString()));
				}else if(wd!=null){
					clickCount.setKeyword(UrlUtil.removeFourChar(wd.toString()));
				}else if(kw!=null){
					clickCount.setKeyword(UrlUtil.removeFourChar(kw.toString()));
				}else if(keyword!=null){
					clickCount.setKeyword(UrlUtil.removeFourChar(keyword.toString()));
				}else if(q!=null){
					clickCount.setKeyword(UrlUtil.removeFourChar(q.toString()));
				}
			}
		}else{
			clickCount.setReferrerUrl(refer);
		}
		
		clickCount.setStyle(req.getParameter("style"));
		Member member = (Member) req.getSession().getAttribute("member");//从session中读取memberid
		if(member != null){
			clickCount.setMemberId(member.getId());
		}
		clickCountService.saveClickCount(clickCount);
		PrintWriter out = resp.getWriter();
		out.write("success");
		out.close();
	}
	
	/**
	 * 初始化请求来源（百度推广关键字）
	 * @author WKX
	 * @since 2016年5月3日 上午11:45:31
	 */
	@RequestMapping("/init")
	public @ResponseBody String init(String url){
		List<ClickCount> list = clickCountService.getNoKeyword(url);
		if(list!=null && list.size()>0){
			Map<String, String> map = null;
			for(ClickCount cc:list){
				if(StringUtils.isNotBlank(cc.getUrl()) && StringUtils.isNotBlank(cc.getReferrerUrl()) && StringUtils.isBlank(cc.getKeyword())){
					map = UrlUtil.URLRequest(cc.getReferrerUrl());
					
					cc.setKeyword(getValue(map, cc.getUrl()));//解析关键字
					
					if(StringUtils.isNotBlank(cc.getKeyword())){
						clickCountService.updateClickCount(cc);
					}
				}
			}
		}
		return "success...";
	}
	
	/**
	 * 获取解析数据的配置
	 * @author WKX
	 * @since 2016年9月20日 下午4:05:52
	 */
	private static Map<String,Object> config = null;
	private static synchronized Map<String,Object> getConfig(){
		if(config!=null){
			return config;
		}else{
			config = new HashMap<String, Object>();
			List<String>baidu = new ArrayList<String>();
			baidu.add("word");
			baidu.add("wd");
			config.put("from=baidu", baidu);
			
			List<String>pinyou = new ArrayList<String>();
			pinyou.add("word");
			config.put("/pinyou/", pinyou);
			
			List<String>sougou = new ArrayList<String>();
			sougou.add("keyword");
			sougou.add("query");
			config.put("/sougou/", sougou);
			
			List<String>_360 = new ArrayList<String>();
			_360.add("q");
			config.put("/360/", _360);
			
			return config;
		}
	}
	
	/**
	 * 解析关键字
	 * @author WKX
	 * @param src 解析的params
	 * @param url 原始请求
	 * @since 2016年9月20日 上午11:11:00
	 */
	private String getValue(Map<String, String> src,String url){
		String value = "";
		
		Map<String,Object> config_ = new ClickCountController().getConfig();
		Iterator<?> it = config_.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			Object key = entry.getKey();
			List<String> value_ = (List<String>)entry.getValue();
			if(value_!=null && value_.size()>0){
				for(String one:value_){
					if(StringUtils.isNotBlank(value))return value;
					
					if(key!=null && (url.indexOf(key.toString())!=-1)){
						Object t = src.get(one);
						if(t!=null){
							value = UrlUtil.removeFourChar(t.toString());
						}
					}
				}
			}
		}
		return value;
	}
	
	@RequestMapping("/initSave")
	public void initSave(ClickCount clickCount,HttpServletRequest req,HttpServletResponse resp) throws IOException{
		clickCount.setIp(HttpUtil.getIpAddr(req));
		clickCount.setCode("visit");
		clickCount.setCurrentDate(new Date());
		
		if(StringUtils.isNotBlank(clickCount.getReferrerUrl())){//获取关键字（保存关键字）
			Map<String, String> map = UrlUtil.URLRequest(clickCount.getReferrerUrl());
			
			if(StringUtils.isNotBlank(clickCount.getUrl())){
				clickCount.setKeyword(getValue(map, clickCount.getUrl()));//解析关键字
			}
		}
		clickCountService.saveClickCount(clickCount);
		PrintWriter out = resp.getWriter();
		out.write("success");
		out.close();
	}
}