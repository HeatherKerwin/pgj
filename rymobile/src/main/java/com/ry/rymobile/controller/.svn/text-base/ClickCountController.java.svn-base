package com.ry.rymobile.controller;

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
import com.ry.core.entity.ClickNum;
import com.ry.core.entity.Member;
import com.ry.core.service.ClickCountService;
import com.ry.core.service.ClickNumService;
import com.ry.util.HttpUtil;
import com.ry.util.UrlUtil;
import com.sun.org.apache.bcel.internal.generic.ISUB;

@Controller
@RequestMapping("/tg/clickCount")
public class ClickCountController {
	
	@Resource
	ClickCountService clickCountService;
	
	@Resource
	ClickNumService clickNumService;

	/*获取客户端的ip*/
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
	
	/*@RequestMapping("/initSaveMo")
	public void saveMobileClickCount(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		
		ClickCount clickCount = new ClickCount();
		clickCount.setUrl(req.getParameter("url"));
		clickCount.setReferrerUrl(req.getParameter("referrerUrl"));
		clickCount.setCurrentDate(new Date());
		clickCount.setUuid(req.getParameter("uuid"));
		//clickCount.setUuid(uuid);
		clickCount.setIp(HttpUtil.getIpAddr(req));
		clickCount.setCode(req.getParameter("code"));
		clickCount.setStyle(req.getParameter("style"));
		Member member = (Member) req.getSession().getAttribute("member");//从session中读取memberid
		if(member != null){
			clickCount.setMemberId(member.getId());
		}
		clickCountService.saveClickCount(clickCount);
		PrintWriter out = resp.getWriter();
		out.write("success");
		out.close();
	}*/
	
	@RequestMapping("/save")
	public void saveClickCount(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String refer = req.getHeader("referer");
		String url = req.getParameter("url");
//		if(url.indexOf("ryfinance.com")>-1 ){
//			if(refer.indexOf("ryfinance.com")==-1){
//				return;
//			}
//			
//		}
//		
//		if(url.indexOf("utiexian.com")>-1 ){
//			if(refer.indexOf("utiexian.com")==-1){
//				return;
//			}
//		}
		
		ClickCount clickCount = new ClickCount();
		clickCount.setUrl(url);
		
		clickCount.setCurrentDate(new Date());
		clickCount.setUuid(req.getParameter("uuid"));
		//clickCount.setUuid(uuid);
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
	
	@RequestMapping("/initSave")
	public void initSave(ClickCount clickCount,HttpServletRequest req,HttpServletResponse resp) throws IOException{
		/*String uuid = UUID.randomUUID().toString();
		Cookie cookie = new Cookie("uuid", uuid);
		Cookie[] cookies = req.getCookies();
		if(cookies != null){
			String key = cookies[0].getName();
			if("uuid".equalsIgnoreCase(key)){
				uuid = cookies[0].getValue();
			}else{
				resp.addCookie(cookie);
			}
		}else{
			resp.addCookie(cookie);
		}*/
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
	
	/*根据style和code进行统计*/
	@RequestMapping("/count")
	public void count(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		String style = req.getParameter("style");
		String code = req.getParameter("code");
		int count = clickCountService.count(style,code);
		ClickNum clickNum = new ClickNum(style,code,count,new Date());
		clickNumService.saveClickNum(clickNum);//保存统计记录
		
		System.out.println(style + "端的" + code + "访问总量为：" + count);
		PrintWriter out = resp.getWriter();
		out.write(count);
		out.close();
	}
	
	/*统计ip*/
	@RequestMapping("/countByIp")
	public void countByIp(HttpServletResponse resp) throws IOException{
		String style = "PC";
		int countIp = clickCountService.countByIp(style);
		
		ClickNum clickNum = new ClickNum(style,"ip",countIp,new Date());
		clickNumService.saveClickNum(clickNum);//保存统计记录
		
		System.out.println(style + "端的总访问总量为：" + countIp);
		PrintWriter out = resp.getWriter();
		out.write("clickCount");
		out.close();
	}
	
	@RequestMapping("/countByUuid")
	public void countByUuid(HttpServletResponse resp) throws IOException{
		String style = "PC";
		int count = clickCountService.countByUuid(style);
		
		ClickNum clickNum = new ClickNum(style,"uv",count,new Date());
		clickNumService.saveClickNum(clickNum);//保存统计记录
		
		System.out.println(style + "端的总访问总量为：" + count);
		PrintWriter out = resp.getWriter();
		out.write("clickCount");
		out.close();
	}
	
	@RequestMapping("/test")
	public void test(){
		List<String> styles = clickCountService.getAllStyles();
		List<String> codes = clickCountService.getAllCodes();
		for (String style : styles) {
			for (String code : codes) {
				int count = clickCountService.count(style,code);
				ClickNum clickNum = new ClickNum(style,code,count,new Date());
				clickNumService.saveClickNum(clickNum);//保存统计记录
			}
			/*统计ip*/
			int countIp = clickCountService.countByIp(style);
			
			ClickNum clickNumIp = new ClickNum(style,"ip",countIp,new Date());
			clickNumService.saveClickNum(clickNumIp);//保存统计记录
			
			/*统计uuid*/
		
			int countUuid = clickCountService.countByUuid(style);
			
			ClickNum clickNumUuid = new ClickNum(style,"uv",countUuid,new Date());
			clickNumService.saveClickNum(clickNumUuid);//保存统计记录
		}
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
}