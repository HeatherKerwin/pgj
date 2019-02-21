package com.ry.rycms.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.PushLog;
import com.ry.core.entity.Region;
import com.ry.core.form.PushLogForm;
import com.ry.core.service.PushLogService;
import com.ry.core.service.RegionService;
import com.ry.util.DateUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.page.PageResults;

@Controller
@RequestMapping("/pushlog")
public class PushLogController {

	@Resource
	private PushLogService pushLogService;
	
	@Resource
	private RegionService regionService;
	
	/**
	 * APP消息推送页面（分页）
	 * @author WKX
	 * @param model
	 * @throws ParseException
	 */
	@RequestMapping("/page")
	public String page(PageResults<?> pr,String start,String end,Integer type,String cityId,Model model) throws ParseException{
		if(pr.getCurrentPage()==null){
			pr.setCurrentPage(0);
		}
		PushLogForm form = new PushLogForm();
		form.setPageNo(pr.getCurrentPage());
		form.setPageSize(10);
		
		if(StringUtils.isNotBlank(start))form.setStart(DateUtil.parser(start+" 00:00:00", DateUtil.FORMART));
		if(StringUtils.isNotBlank(end))form.setEnd(DateUtil.parser(end+" 23:59:59", DateUtil.FORMART));
		
		if(type==null)type=0;//默认查询全部（同步页面显示）
		form.setType(type);
		if(StringUtils.isNotBlank(cityId))form.setCityId(Integer.valueOf(cityId));
		
		pr = pushLogService.getPageList(form);
		model.addAttribute("pageModel", pr);
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("type", type);
		model.addAttribute("cityId", cityId);
		
		List<Region> citys = regionService.getByType("C");//市
		model.addAttribute("citys", citys);
		return "/information/pushlog";
	}
	
	/**
	 * 打开APP推送页面
	 * @author WKX
	 */
	@RequestMapping("/add")
	public String add(Model model){
		List<Region> citys = regionService.getByType("C");//市
		model.addAttribute("citys", citys);
		model.addAttribute("now", DateUtil.formart(new Date(),DateUtil.FORMART3));
		return "/information/pushlog_add";
	}
	
	/**
	 * 保存
	 * @author WKX
	 */
	@RequestMapping("/save")
	public String save(final PushLog pushLog,Model model){
		Map<String, Object> result = new HashMap<String, Object>();
		if(pushLog!=null){
			
			new Thread(new Runnable() {
                @Override
                public void run() {
                	pushLog.setCreateTime(new Date());
        			pushLogService.saveModelAndPushMsg(pushLog);
                }
            }).start();
			
			result.put("response", "success");
			result.put("msg", "正在执行推送...");
		}else{
			result.put("response", "failed");
			result.put("msg", "推送失败...");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
}