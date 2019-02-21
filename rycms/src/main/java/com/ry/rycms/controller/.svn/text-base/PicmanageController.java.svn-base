package com.ry.rycms.controller;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.ry.core.entity.Appimage;
import com.ry.core.service.PicmanageService;
import com.ry.core.util.Constant;
import com.ry.core.util.FileUtil;
import com.ry.util.JacksonUtil;

/**
 *  
 * @author Ry-ljb
 *
 */
@Controller
public class PicmanageController {
	@Resource
	PicmanageService picmanageService;
	
	private Log log = LogFactory.getLog(this.getClass());
	
	@RequestMapping("/pic/list/")
	public String list(String code, HttpServletRequest request, HttpServletResponse response) throws Exception{						
		List<Appimage> appimageList = picmanageService.getPicList(code);
		String showUrl = Constant.properties.getProperty("preimgUrl");
		for (Appimage appimage : appimageList) {
			appimage.setPath(showUrl+appimage.getPath());
		}
		request.setAttribute("appimageList", appimageList);	
		
		if ("index_banner".equals(code)) {
			return "/information/focusPicture";
		}else if ("index_jigou".equals(code)) {
			return "/information/focusPicture";
		}else if ("index_rili".equals(code)) {
			return "/information/calendar";
		}else if ("index_ad".equals(code)) {
			return "/advertisement/update";
		}else if ("index_pc".equals(code)) {
			return "/information/pcBanner";
		}else if ("index_app_zx".equals(code)) {//APP配置首页咨询
			return "/information/appzixun";
		}else if("index_rili_pc".equals(code)){
			return "/information/calendar_pc";
		}else{
			return "ajax";
		}
	}
	
	//选择显示企业端图片，还是显示机构端的图片
	//进行判断，是机构端还是企业端
	@RequestMapping("/pic/xztplist")
	public String saves(String code,HttpServletRequest request, HttpServletResponse response,Model model) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Appimage> list = picmanageService.getXzPiclist(code);
		String showUrl = Constant.properties.getProperty("preimgUrl");
		for (Appimage appimage : list) {
			appimage.setPath(showUrl+appimage.getPath());
		}
		result.put("data", list);
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	@RequestMapping("/pic/save/")
	public void save(String code,String url,String sort,HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {	
			//删除文件夹下所有文件
			//FileUtil.deletefile(uploadPath);			
			
			//@WKX EDIT如果是日历明细
			String token = request.getParameter("token");
			if(StringUtils.isNotBlank(token))code += token;
			
			//删除表记录
			picmanageService.deleteAll(code);
			
			List<Appimage> applist = new ArrayList<Appimage>();
						
			for (int i = 5; i > 0; i--) {
				String path = request.getParameter("picpath"+i);
				if (path != null && !"".equals(path)) {
					//将临时目录文件转移到目录文件中									
					//临时文件夹 的文件 转到 保存目录中
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
											
					Appimage appimage = new Appimage();
					
					if(StringUtils.isNotBlank(url)){
						appimage.setUrl(url);//存储链接[广告]
					}else{
						String url_ = request.getParameter("url"+i);
						appimage.setUrl(url_);//存储链接[焦点图(横幅)]
					}
					path = path.replaceFirst("temp", "upload");
					int idx = path.indexOf("upload");
					String p = path.substring(idx);
					String q[] = sort.split(",");
					appimage.setSort(q[i-1]);
					appimage.setPath(p); 	
					appimage.setCode(code);
					applist.add(appimage);						
				}															
			}
			picmanageService.addAll(applist);
		} catch (Exception e) {
			
		}
		request.getRequestDispatcher("/pic/list/").forward(request, response);
	}
	
	@RequestMapping("pic/upload")
	public void uploadpic(@RequestParam(value = "file", required = false) MultipartFile file, String dir, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PrintWriter out = response.getWriter();		
		//文件保存目录路径
		String savePath = Constant.properties.getProperty("uploadpath") + Constant.TEMP_PATH;

		//文件保存目录URL
		String saveUrl  = Constant.properties.getProperty("uploadpath") + Constant.TEMP_PATH;
		String showUrl = Constant.properties.getProperty("preimgUrl") + Constant.TEMP_PATH;

		//定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");

		//最大文件大小
		long maxSize = 100*1024*1024;

		response.setContentType("text/html; charset=UTF-8");

		if(!ServletFileUpload.isMultipartContent(request)){			
			out.println("请选择文件。");
			return;
		}
		//检查目录
		File uploadDir = new File(savePath);
		if(!uploadDir.isDirectory()){
			uploadDir.mkdirs();					
		}
		//检查目录写权限
		if(!uploadDir.canWrite()){
			out.println("上传目录没有写权限。");				
			return;
		}

		String dirName = dir;
		if (dirName == null) {
			dirName = "image";
		}
		if(!extMap.containsKey(dirName)){
			log.info("目录名不正确。");				
			return;
		}
		//创建文件夹
		savePath += dirName + "/";
		saveUrl += dirName + "/";
		showUrl += dirName + "/";
		
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		showUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		
		int index = file.getOriginalFilename().lastIndexOf(".");
		String filename = file.getOriginalFilename();
		String fileExt = filename.substring(index, filename.length());
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + fileExt;
		
		try {  						
//			if (!".xls".equals(fileExt)) {
//				out.println("只支持.xls文件");
//				return;
//			}						
			File targetFile = new File(savePath, newFileName);
            file.transferTo(targetFile);  
                   			
		}catch(Exception e){
			e.printStackTrace();
			out.println(e.getMessage());
		}
		out.print(showUrl + newFileName);
		out.close();		
	}
	
	@RequestMapping("pic/uploadpic")
	@ResponseBody 
	public void uploadpic1(String dir, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//文件保存目录路径
		String savePath = Constant.properties.getProperty("uploadpath") + Constant.UPLOAD_PATH;

		//文件保存目录URL
		String saveUrl  = Constant.properties.getProperty("uploadpath") + Constant.UPLOAD_PATH;
		String showUrl = Constant.properties.getProperty("preimgUrl") + Constant.UPLOAD_PATH;

		//定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");

		//最大文件大小
		long maxSize = 100*1024*1024;

		response.setContentType("text/html; charset=UTF-8");

		if(!ServletFileUpload.isMultipartContent(request)){
			out.print("请选择文件。");
			return;
		}
		//检查目录
		File uploadDir = new File(savePath);
		if(!uploadDir.isDirectory()){
			out.print("上传目录不存在。");
			return;
		}
		//检查目录写权限
		if(!uploadDir.canWrite()){
			out.print("上传目录没有写权限。");
			return;
		}

		String dirName = request.getParameter("dir");
		if (dirName == null) {
			dirName = "image";
		}
		if(!extMap.containsKey(dirName)){
			out.print("目录名不正确。");
			return;
		}
		//创建文件夹
		savePath += dirName + "/";
		saveUrl += dirName + "/";
		showUrl += dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		showUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}

		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		List items = upload.parseRequest(request);

		DefaultMultipartHttpServletRequest mrequest= (DefaultMultipartHttpServletRequest)request;

		Map map=mrequest.getFileMap();
		Collection<MultipartFile> c = map.values();
		Iterator it = c.iterator();
		for (; it.hasNext();) {
		 CommonsMultipartFile file=(CommonsMultipartFile) it.next();
		 
		if(!file.isEmpty())
		{
		 long fileSize = file.getSize();
		 String fileName = file.getOriginalFilename();
		 String contentType=file.getContentType();
		 String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		    
		 if(fileSize > maxSize){
		  out.println("上传文件大小超过限制100M。");
		  return;
		 }
		 if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
		  out.println("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。");
		  return;
		 }
		 
		    FileItem item = null;
		    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		 String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
		 
		 try{
		  File uploadedFile = new File(savePath, newFileName);
		  file.transferTo(uploadedFile);  
		  out.print(showUrl + newFileName);
			out.close();
		 }catch(Exception e){
		   
		  out.println("上传文件失败。");
		  return;
		 }

	}else
	{
		 out.println("不可以上传空文件！");
		 return;
		}
		
		
	    
	}
	}
	
	@RequestMapping("pic/uploadpick")
	@ResponseBody 
	public void uploadpick(String dir, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//文件保存目录路径
		String savePath = Constant.properties.getProperty("uploadpath") + Constant.UPLOAD_PATH;

		//文件保存目录URL
		String saveUrl  = Constant.properties.getProperty("preimgUrl") + Constant.UPLOAD_PATH;
		String showUrl = Constant.properties.getProperty("preimgUrl") + Constant.UPLOAD_PATH;

		//定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");

		//最大文件大小
		long maxSize = 100*1024*1024;

		response.setContentType("text/html; charset=UTF-8");

		if(!ServletFileUpload.isMultipartContent(request)){
			out.print("请选择文件。");
			return;
		}
		//检查目录
		File uploadDir = new File(savePath);
		if(!uploadDir.isDirectory()){
			out.print("上传目录不存在。");
			return;
		}
		//检查目录写权限
		if(!uploadDir.canWrite()){
			out.print("上传目录没有写权限。");
			return;
		}

		String dirName = request.getParameter("dir");
		if (dirName == null) {
			dirName = "image";
		}
		if(!extMap.containsKey(dirName)){
			out.print("目录名不正确。");
			return;
		}
		//创建文件夹
		savePath += dirName + "/";
		saveUrl += dirName + "/";
		showUrl += dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		showUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}

		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		List items = upload.parseRequest(request);

		DefaultMultipartHttpServletRequest mrequest= (DefaultMultipartHttpServletRequest)request;

		Map map=mrequest.getFileMap();
		Collection<MultipartFile> c = map.values();
		Iterator it = c.iterator();
		for (; it.hasNext();) {
		 CommonsMultipartFile file=(CommonsMultipartFile) it.next();
		 
		if(!file.isEmpty())
		{
		 long fileSize = file.getSize();
		 String fileName = file.getOriginalFilename();
		 String contentType=file.getContentType();
		 String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		    
		 if(fileSize > maxSize){
		  out.println("上传文件大小超过限制100M。");
		  return;
		 }
		 if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
		  out.println("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。");
		  return;
		 }
		 
		    FileItem item = null;
		    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		 String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
		 
		 try{
		  File uploadedFile = new File(savePath, newFileName);
		  file.transferTo(uploadedFile);  
		  
		  
		  JSONObject obj = new JSONObject();
			obj.put("error", 0);
			obj.put("url", saveUrl + newFileName);
			out.println(obj);
			out.close();
		  
		 }catch(Exception e){
		   
		  out.println("上传文件失败。");
		  return;
		 }

	}else
	{
		 out.println("不可以上传空文件！");
		 return;
		}
		
		
	    
	}
	}
}
