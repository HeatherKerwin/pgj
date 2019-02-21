package com.ry.rycms.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.SiteContent;
import com.ry.core.service.SiteContentService;
import com.ry.core.util.Constant;

/**
 * ZY
 * @author ry-java-6
 *
 */
@Controller
@RequestMapping("/sitecontent/")
public class SiteContentController {
	@Resource
	SiteContentService siteContentService;
	
	/**
	 * 进入编辑页面,如果有id就查id，没有的话默认最新的那条
	 * @author ZY
	 * @param id
	 * @param request
	 * @param response
	 * 2016年11月28日下午4:40:41
	 */
	@RequestMapping("edit/")
	public String edit(Integer id,HttpServletRequest request){
		SiteContent siteContent=new SiteContent();
		if(id!=null){
			siteContent = siteContentService.getById(id);
		}else{
			siteContent = siteContentService.get();//2016.11.28 现在只会用这种
		}	
		if (siteContent!=null && siteContent.getPic()!=null) {				
			siteContent.setPic(Constant.properties.getProperty("preimgUrl")+siteContent.getPic());				
		}
		request.setAttribute("site", siteContent);
		return "/information/siteContent";
	}
	
	/**
	 * 保存信息
	 * @author ZY
	 * @param sm
	 * @param request
	 * @param response
	 * 2016年11月28日下午4:48:40
	 */
	@RequestMapping("update")
	public String update(SiteContent sm, HttpServletRequest request){
		if (StringUtils.hasText(sm.getPic())) {
			if (sm.getPic().contains("temp") || sm.getPic().contains("upload")) {
				String uPath = sm.getPic().replaceFirst("temp", "upload");
				int idx = uPath.indexOf("upload");
				String p = uPath.substring(idx);
				sm.setPic(p);
			}				
		}
		if(sm.getPublishtime()==null){
			sm.setPublishtime(new Date());
		}
		if(sm.getId()!=null){
			siteContentService.updateModel(sm);
		}else{
			siteContentService.saveModel(sm);
		}	
		request.setAttribute("message", "修改成功");
		return "redirect:/sitecontent/edit/";
	}
			
}


