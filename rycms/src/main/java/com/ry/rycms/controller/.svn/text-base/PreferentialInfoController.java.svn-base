package com.ry.rycms.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.PreferentialInfo;
import com.ry.core.entity.Systeminfo.Type;
import com.ry.core.service.PreferentialInfoService;
import com.ry.core.util.Constant;
import com.ry.core.util.FileUtil;
import com.ry.core.util.JPushUtil;
import com.ry.util.page.PageResults;

import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;

@Controller
@RequestMapping("/preferentialInfo/")
public class PreferentialInfoController {
	
	@Resource
	PreferentialInfoService preferentialInfoService;
	
	/**
	 * 创建线程池
	 * @author WKX
	 */
	public static ExecutorService  pool;
	public static synchronized ExecutorService initPool(){
		if(pool!=null){
			return pool;
		}else{
			pool = Executors.newFixedThreadPool(5);
			return pool;
		}
	}
	
	/**
	 * 推送消息的线程
	 * @author WKX
	 */
	public class PushJob implements Runnable {
		public String alert;
		public PushJob(String alert) {
	        this.alert = alert;
	    }
		public void run() {
			try {
				JPushUtil.pushAllUser("【优惠消息】"+alert,Type.PREFERENTIALINFO);
			} catch (APIConnectionException e) {
				e.printStackTrace();
			} catch (APIRequestException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 获取优惠信息[分页]
	 * @author WKX
	 * @param pr
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("list")
	public String list(PageResults<?> pr,HttpServletRequest request, HttpServletResponse response) throws Exception{
		if(pr.getCurrentPage()==null){
			pr.setCurrentPage(0);
		}
		pr = preferentialInfoService.getPageList(pr.getCurrentPage(), 20);				
		
		request.setAttribute("pageModel", pr);
		//request.getRequestDispatcher("/manage/preferentialInfoList.jsp").forward(request, response);
		return "/specialOffers/list";
	}
	
	/**
	 * 保存对象
	 * @author WKX
	 * @param info
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("save")
	public void save(PreferentialInfo info,HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			if(info==null)throw new Exception("保存失败！信息不完整");
			if(StringUtils.isBlank(info.getTitle()))throw new Exception("保存失败！请输入标题");
			if(StringUtils.isBlank(info.getUrl()))throw new Exception("保存失败！请输入请求");
			
			if(StringUtils.isNotBlank(info.getImgPath())){//保存图片
				String path = info.getImgPath();
				String temp = Constant.properties.getProperty("uploadpath") + Constant.TEMP_PATH;//保存目录包名的绝对路径
				String uploadPath = Constant.properties.getProperty("uploadpath") + Constant.UPLOAD_SRC;//保存目录包名的绝对路径			
				
				//检查目录
				File uploadDir = new File(uploadPath);
				if(!uploadDir.isDirectory()){
					uploadDir.mkdirs();
				}
				
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
				String extpath = df.format(new Date());
				uploadPath += extpath;
				temp += "image" + File.separator + extpath;
				temp += path.substring(path.lastIndexOf("/"), path.length());
				FileUtil.moveFile(temp, uploadPath);
				
				path = path.replaceFirst("temp", "upload");
				int idx = path.indexOf("upload");
				String p = path.substring(idx);
				info.setImgPath(p);
			}
			if(info.getId()==null){
				info.setCreateTime(new Date());
			}else{
				PreferentialInfo tmp = preferentialInfoService.getById(info.getId());
				if(tmp!=null)info.setCreateTime(tmp.getCreateTime());
			}
			preferentialInfoService.saveInfo(info);
			
			//开启优惠消息发送线程
			PushJob push = new PushJob(info.getTitle());
			initPool().execute(push);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", e.getMessage());
			//request.getRequestDispatcher("/manage/tip.jsp").forward(request, response);
		}
		request.getRequestDispatcher("/preferentialInfo/list").forward(request, response);
	}
	
	/**
	 * 根据主键获取对象
	 * @author WKX
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping("get")
	public String get(Integer id,HttpServletRequest request, HttpServletResponse response){
		try {
			PreferentialInfo info = preferentialInfoService.getById(id);
			if (info.getImgPath()!=null) {
				info.setImgPath(Constant.properties.getProperty("preimgUrl")+info.getImgPath());				
			}
			request.setAttribute("info",info);
			//request.getRequestDispatcher("/manage/preferentialInfoAdd.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/specialOffers/add";
	}
	
	/**
	 * 根据主键删除对象
	 * @author WKX
	 * @param id
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	public String delete(Integer id, HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			preferentialInfoService.deleteById(id);
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "删除失败");
			try {
				request.getRequestDispatcher("/manage/tip.jsp").forward(request, response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return "redirect:/preferentialInfo/list";
	}
	@RequestMapping("add")
	public String add(HttpServletRequest request, HttpServletResponse response) throws Exception{
		return "/specialOffers/add";
	}
}