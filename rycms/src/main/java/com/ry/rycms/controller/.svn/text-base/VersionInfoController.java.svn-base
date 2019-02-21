package com.ry.rycms.controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.VersionInfo;
import com.ry.core.form.versioninfo.VersionInfoRequest;
import com.ry.core.service.PicmanageService;
import com.ry.core.service.VersionInfoService;
import com.ry.core.util.Constant;
import com.ry.core.util.FileUtil;
import com.ry.util.DateUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.page.PageResults;
import com.ry.web.html.CommonHtmlGenerator;

@Controller
@RequestMapping("/versioninfo/")
public class VersionInfoController {

	@Autowired
	CommonHtmlGenerator commonHtmlGenerator;
	
	@Resource
	private VersionInfoService versionInfoService;
	
	@Resource
	PicmanageService picmanageService;
	
	/**
	 * 页面初始化
	 */
	@RequestMapping("/list")
	public String list(PageResults pr, String begintime, String endtime, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		VersionInfoRequest req = new VersionInfoRequest();
		if (pr.getCurrentPage() == null) {
			req.setStart(1L);
		} else {
			req.setStart((pr.getCurrentPage().longValue() - 1) * req.getLength());
		}
		Calendar c = Calendar.getInstance();
		if (StringUtils.hasText(begintime)) {
			try {
				c.setTime(DateUtil.parser(endtime, DateUtil.FORMART3));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			req.setStartDate(begintime);
			request.setAttribute("begintimeStr", begintime);
		}
		if (StringUtils.hasText(endtime)) {
			try {
				c.setTime(DateUtil.parser(endtime, DateUtil.FORMART3));
				c.add(Calendar.DATE, +1);
				req.setEndDate(DateUtil.formart(c.getTime(), DateUtil.FORMART));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			request.setAttribute("endtimeStr", endtime);
		}
		try {
			List<VersionInfo> versionInfo = versionInfoService.getMaxDate();
			String maxDate = DateUtil.formart(versionInfo.get(0).getShijian(),DateUtil.FORMART3);
			pr = versionInfoService.getPage(req);
			model.addAttribute("pr", pr);
			model.addAttribute("maxDate", maxDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "information/versionInfoList";
	}

	/**
	 * 显示最近的版本信息
	 * @param model
	 * @return
	 */
	@RequestMapping("toAddOrEdit")
	public String toAdd(Model model,Integer id) {
		VersionInfo info = null;
		if(id==null){
			info = versionInfoService.getNewVersionInfo();
			model.addAttribute("info", info);
		}else{
			info = versionInfoService.getVersionInfo(id);
			model.addAttribute("info", info);
			model.addAttribute("edit","ok");
		}
		if(info!=null){
			String showUrl = Constant.properties.getProperty("preimgUrl");
			info.setBanner1(showUrl+info.getBanner1());
			info.setBanner2(showUrl+info.getBanner2());
		}
		return "information/versionInfoAddOrEdit";
	}

	
	
	
	
	@RequestMapping("save")
	public String save(VersionInfo versionInfo,HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {	
			List<String> p = new ArrayList<String>();
			for (int i = 2; i > 0; i--) {
				String path = request.getParameter("banner"+i+"path");
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
											
					path = path.replaceFirst("temp", "upload");
					int idx = path.indexOf("upload");
					p.add(path.substring(idx));
				}															
			}
			versionInfo.setBanner1(p.get(0));
			versionInfo.setBanner2(p.get(1));
			if(versionInfo.getId()!=null){	//修改
				versionInfoService.updateVersionInfo(versionInfo);
			}else{	//新增
				versionInfo.setState("0");
				versionInfoService.addVersionInfo(versionInfo);
			}
		} catch (Exception e) {
		}
		return "redirect:list";
	}
	
	@RequestMapping("preview")
	public String preview(Model model,Integer id){
		VersionInfo info = versionInfoService.getVersionInfo(id);
		String showUrl = Constant.properties.getProperty("preimgUrl");
		info.setBanner1(showUrl+info.getBanner1());
		info.setBanner2(showUrl+info.getBanner2());
		info.setContent(info.getContent().replace("\r\n", "<br/>"));
		info.setJieshao(info.getJieshao().replace("\r\n", "<br/>"));
		model.addAttribute("info",info);
		return "information/yulan";
	}
	
	@RequestMapping("banner")
	public String fabu(Model model,Integer id){
		return "information/banner";
	}
	
	@RequestMapping(value = "/down.htm")
	public String down(Model model,HttpServletRequest requestd){
		Map<String, Object> m = new HashMap<String, Object>();
		CommonHtmlGenerator generator = CommonHtmlGenerator.clone(commonHtmlGenerator);
		Integer id = Integer.parseInt(requestd.getParameter("id"));
		//获取当前发布的版本
		VersionInfo nowVersionInf = versionInfoService.getNewVersionInfo();
		//先把当前发布的版本状态改为未发布 1
		if(nowVersionInf!=null){
			nowVersionInf.setState("1");
			versionInfoService.updateVersionInfo(nowVersionInf);
		}
		VersionInfo versionInfo = versionInfoService.getVersionInfo(id);
		versionInfo.setState("2");
		versionInfoService.updateVersionInfo(versionInfo);
		String showUrl = Constant.properties.getProperty("preimgUrl");
		versionInfo.setBanner1(showUrl+versionInfo.getBanner1());
		versionInfo.setBanner2(showUrl+versionInfo.getBanner2());
		versionInfo.setContent(versionInfo.getContent().replace("\r\n", "<br/>"));
		versionInfo.setJieshao(versionInfo.getJieshao().replace("\r\n", "<br/>"));
		model.addAttribute("info",versionInfo);
		try {
			//generator.setContextRealPath(PropertiesUtil.wwwroot);
			generator.setOutputPath(Constant.properties.getProperty("rymobileRootPath"));//此路径和ContextRealPath 只要一个完整配置就可以了
			generator.setOutputFileName("download.html");
			generator.setTemplateFileName("/information/download.ftl");
			generator.generate(model.asMap());
			m.put("message", "发布成功！");
		} catch (Exception e) {
			e.printStackTrace();
			m.put("message", "发布失败！");
		}
		model.addAttribute("retValue", JacksonUtil.objWriteStr(m));
		return "ajax";
	}
}
