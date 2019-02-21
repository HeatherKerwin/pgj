package com.ry.rycms.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.ry.core.util.Constant;
/**
 * 文件上传
* @ClassName: UploadJsonController
* @Description: TODO
* @author Ry-wk
* @date 2015年10月16日
*
 */
@Controller
public class PicUploadJsonController {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	@RequestMapping("manage/picupload")
	public String uploadjson(HttpServletRequest request, HttpServletResponse response){

		try {
			response.setCharacterEncoding("utf-8");
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
				out.print(getError("请选择文件。"));
				return "ajax";
			}
			//检查目录
			File uploadDir = new File(savePath);
			if(!uploadDir.isDirectory()){
				uploadDir.mkdirs();
			}
			//检查目录写权限
			if(!uploadDir.canWrite()){
				out.print(getError("上传目录没有写权限。"));
				return "ajax";
			}

			String dirName = request.getParameter("dir");
			if (dirName == null) {
				dirName = "image";
			}
			if(!extMap.containsKey(dirName)){
				out.print(getError("目录名不正确。"));
				return "ajax";
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
			List items = null;
			try {
				items = upload.parseRequest(request);
			} catch (FileUploadException e1) {
				e1.printStackTrace();
			}
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				String fileName = item.getName();
				long fileSize = item.getSize();
				if (!item.isFormField()) {
					//检查文件大小
					if(item.getSize() > maxSize){
						out.print(getError("上传文件大小超过10M限制。"));
						return "ajax";
					}
					//检查扩展名
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
						out.print(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
						return "ajax";
					}

					SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
					String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "_"+new Random().nextInt(1000)+"." + fileExt;
					try{
						File uploadedFile = new File(savePath, newFileName);
						item.write(uploadedFile);
					}catch(Exception e){
						out.print(getError("上传文件失败。"));
						return "ajax";
					}

//					JSONObject obj = new JSONObject();
//					obj.put("error", 0);
//					obj.put("url", saveUrl + newFileName);
					out.print(showUrl + newFileName);
					out.close();
				}
			}
		} catch (IOException e) {
			log.info("文件上传出错", e);
		}
	
		return "ajax";
	}
	
	
	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toString();
	}
	
}
