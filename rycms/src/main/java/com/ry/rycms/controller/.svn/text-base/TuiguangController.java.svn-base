package com.ry.rycms.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Activity;
import com.ry.core.form.ActivityForm;
import com.ry.core.form.MemberForm;
import com.ry.core.service.ActivityService;
import com.ry.core.service.MemberService;
import com.ry.util.page.PageResults;


@Controller
public class TuiguangController {
	
	@Resource
	ActivityService activityService;
	
	@Resource
	MemberService memberService;
	
	@RequestMapping("/activity/list/")
	public String list(ActivityForm activityForm, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException, ServletException, ParseException{
		if (activityForm.getPageNo() == null) {
			activityForm.setPageNo(1);
		}
		if (activityForm.getPageSize() == null) {
			activityForm.setPageSize(30);
		}
		if (StringUtils.hasText(activityForm.getStartdateStr())) {			
			activityForm.setStartdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(activityForm.getStartdateStr()+ " 00:00:00 "));
		}
		if (StringUtils.hasText(activityForm.getEnddateStr())) {						
			activityForm.setEnddate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(activityForm.getEnddateStr()+ " 23:59:59 "));
		}
		PageResults<Activity> page = activityService.getPageResults(activityForm);
		List<Activity> activities = page.getResults();		
		MemberForm mForm;
		for (int i = 0, length = activities.size(); i < length; i++) {
			mForm = new MemberForm();
			mForm.setHezuo(activities.get(i).getId().toString());
			mForm.setQudao("AC");
			activities.get(i).setRenshu(memberService.count(mForm));							
		}
		page.setResults(activities);
		if (StringUtils.hasText(activityForm.getStartdateStr())) model.addAttribute("begintime", activityForm.getStartdateStr()); 
		if (StringUtils.hasText(activityForm.getEnddateStr())) model.addAttribute("endtime", activityForm.getEnddateStr());		
		model.addAttribute("page",page);
		
		return "/activity/list";		
	}
	
	@RequestMapping("/activity/badd/")
	public String badd(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		return "/activity/add";
	}
	
	@RequestMapping("/activity/add/")
	public String add(ActivityForm aForm, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, Exception{
				
		Activity activity = new Activity();
		activity.setHeader(aForm.getHeader());
		activity.setPrice(aForm.getPrice());
		activity.setRemarks(aForm.getRemarks());
		activity.setShichang(aForm.getShichang());
		activity.setUrl(aForm.getUrl());
		activity.setUpdatetime(new Date());		
		activity.setFlag(0);
		if (aForm.getStartdateStr() != null && !"".equals(aForm.getStartdateStr())) {
			activity.setStartdate(new SimpleDateFormat("yyyy-MM-dd").parse(aForm.getStartdateStr()));
		}
		if (aForm.getEnddateStr() != null && !"".equals(aForm.getEnddateStr())) {
			activity.setEnddate(new SimpleDateFormat("yyyy-MM-dd").parse(aForm.getEnddateStr()));
		}		
		activity.setCreatetime(new Date());
		activityService.addActivity(activity);
		return "redirect:/activity/list/";
	}
	
	@RequestMapping("/activity/bupdate/")
	public String bupdate(Integer id,HttpServletRequest request, HttpServletResponse response, Model model) throws IOException, ServletException{
		Activity param = new Activity();
		param.setId(id);
		Activity activity = activityService.getActivity(param);
		model.addAttribute("activity", activity);
		return "/activity/update";
	}
	
	@RequestMapping("/activity/update/")
	public String update(ActivityForm aForm, HttpServletRequest request, HttpServletResponse response) throws Exception{
		Activity activity = new Activity();
		activity.setId(aForm.getId());
		activity.setHeader(aForm.getHeader());
		activity.setPrice(aForm.getPrice());
		activity.setRemarks(aForm.getRemarks());
		activity.setShichang(aForm.getShichang());
		activity.setUrl(aForm.getUrl());
		activity.setUpdatetime(new Date());		
		activity.setFlag(0);
		if (aForm.getStartdateStr() != null && !"".equals(aForm.getStartdateStr())) {
			activity.setStartdate(new SimpleDateFormat("yyyy-MM-dd").parse(aForm.getStartdateStr()));
		}
		if (aForm.getEnddateStr() != null && !"".equals(aForm.getEnddateStr())) {
			activity.setEnddate(new SimpleDateFormat("yyyy-MM-dd").parse(aForm.getEnddateStr()));
		}
		if (aForm.getCreatetimeStr() != null && !"".equals(aForm.getCreatetimeStr())) {
			activity.setCreatetime(new SimpleDateFormat("yyyy-MM-dd").parse(aForm.getCreatetimeStr()));
		}
		
		activityService.updateActivity(activity);
		return "redirect:/activity/list/";
	}
}
