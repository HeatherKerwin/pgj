package com.ry.rycms.controller;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ry.core.entity.Lianhang;
import com.ry.core.service.LianhangService;
import com.ry.core.util.Constant;
import com.ry.rycms.util.ExcelExport;


/**
 *  
 * @author Ry-ljb
 *
 */
@Controller
public class LianhangController {
	@Resource
	LianhangService lianhangService;
	
	private Log log = LogFactory.getLog(this.getClass());
	
	@RequestMapping("/lianhang/bimport/")
	public void bimport(HttpServletRequest request, HttpServletResponse response) throws Exception{		
		request.getRequestDispatcher("/manage/lianhang.jsp").forward(request, response);
	}
	
	@RequestMapping("/lianhang/importImg/")
	public void importImg(@RequestParam(value = "file", required = false) MultipartFile file, String dir, String yinhang,HttpServletRequest request, HttpServletResponse response) throws Exception{
		PrintWriter out = response.getWriter();		
		//文件保存目录路径
		String savePath = request.getServletContext().getRealPath("/") + Constant.UPLOAD_PATH;

		//文件保存目录URL
		String saveUrl  = request.getContextPath() + "/" + Constant.UPLOAD_PATH;

		//定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2,pdf");

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
			out.println("上传目录不存在。");			
			return;
		}
		//检查目录写权限
		if(!uploadDir.canWrite()){
			out.println("上传目录没有写权限。");				
			return;
		}

		String dirName = dir;
		if (dirName == null) {
			dirName = "file";
		}
		if(!extMap.containsKey(dirName)){
			log.info("目录名不正确。");				
			return;
		}
		//创建文件夹
		savePath += dirName + "/";
		saveUrl += dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		
		try {  			
			int index = file.getOriginalFilename().lastIndexOf(".");
			String filename = file.getOriginalFilename();
			String fileExt = filename.substring(index, filename.length());
			if (!".xls".equals(fileExt)) {
				out.println("只支持.xls文件");
				return;
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + fileExt;
			File targetFile = new File(savePath, newFileName);  
            file.transferTo(targetFile);  
       
			List<Lianhang> list = ExcelExport.readLianhangExcel(savePath+newFileName, yinhang);
			lianhangService.addAllLianhang(list);
			out.println("导入成功");					
		}catch(Exception e){
			e.printStackTrace();
			out.println(e.getMessage());
		}
		out.close();		
		
	}
	
}
