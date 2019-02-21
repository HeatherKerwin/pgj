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

import com.ry.core.entity.HongbaoActivity;
import com.ry.core.entity.Tag;
import com.ry.core.form.HongbaoActivityForm;
import com.ry.core.service.HongbaoService;
import com.ry.core.service.TagService;
import com.ry.util.page.PageResults;


@Controller
public class HongbaoController {
	
	@Resource
	HongbaoService hongbaoService;
	
	@Resource
	TagService tagService;
	
	@RequestMapping("/hongbao/list/")
	public String list(HongbaoActivityForm hForm, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException, ServletException, ParseException{
		if (hForm.getPageNo() == null) {
			hForm.setPageNo(1);
		}
		if (hForm.getPageSize() == null) {
			hForm.setPageSize(30);
		}
		if (StringUtils.hasText(hForm.getStartdateStr())) {			
			hForm.setStartdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(hForm.getStartdateStr()+ " 00:00:00 "));
		}
		if (StringUtils.hasText(hForm.getEnddateStr())) {						
			hForm.setEnddate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(hForm.getEnddateStr()+ " 23:59:59 "));
		}
		PageResults<HongbaoActivity> page = hongbaoService.getPageResults(hForm);
		
		if (StringUtils.hasText(hForm.getStartdateStr())) model.addAttribute("begintime", hForm.getStartdateStr()); 
		if (StringUtils.hasText(hForm.getEnddateStr())) model.addAttribute("endtime", hForm.getEnddateStr());
		model.addAttribute("tagId", hForm.getTagId());
		model.addAttribute("name", hForm.getName());
		
		//获取红包分类
		List<Tag> tags = tagService.getByParentCode(hForm.getTagCode());
		model.addAttribute("tags",tags);
		model.addAttribute("page",page);
		return "/hongbao/list";
	}
	
	@RequestMapping("/hongbao/badd/")
	public String badd(String tagCode,Model model) throws IOException, ServletException{
		//获取红包分类
		List<Tag> tags = tagService.getByParentCode(tagCode);
		model.addAttribute("tags",tags);
		return "/hongbao/add";
	}
	
	@RequestMapping("/hongbao/add/")
	public String add(HongbaoActivityForm aForm, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, Exception{
				
		HongbaoActivity activity = new HongbaoActivity();
				
		activity.setCreatetime(new Date());
		if (aForm.getStartdateStr() != null && !"".equals(aForm.getStartdateStr())) {
			activity.setStartdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(aForm.getStartdateStr()+ " 00:00:00 "));
			aForm.setStartdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(aForm.getStartdateStr()+ " 00:00:00 "));
		}
		if (aForm.getEnddateStr() != null && !"".equals(aForm.getEnddateStr())) {
			activity.setEnddate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(aForm.getEnddateStr()+ " 23:59:59 "));
			aForm.setEnddate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(aForm.getEnddateStr()+ " 23:59:59 "));
		}		
		Date nowDate = new Date();
		if (aForm.getEnddate().getTime() < nowDate.getTime()) {
			activity.setFlag(0);
		} else if (aForm.getStartdate().getTime() <= nowDate.getTime()) {
			activity.setFlag(1);
		} else {
			activity.setFlag(2);
		}		
		activity.setHeader(aForm.getHeader());
		activity.setLianjie(aForm.getLianjie());
		activity.setStatus(0);
		activity.setTotalnum(aForm.getTotalnum());
		activity.setTotalprice(aForm.getTotalprice());
		activity.setName(aForm.getName());
		activity.setType(aForm.getType());
		activity.setSharedays(aForm.getSharedays());
		activity.setUsedays(aForm.getUsedays());
		activity.setUpdatetime(nowDate);
		activity.setLimitprice(aForm.getLimitprice());
		activity.setPrice(aForm.getPrice());	
		activity.setMaxprice(aForm.getMaxprice());
		activity.setTagId(aForm.getTagId());
		
		hongbaoService.addActivity(activity);
		return "redirect:/hongbao/list/";
	}
	
	@RequestMapping("/hongbao/bupdate/")
	public String bupdate(Integer id,String tagCode,Model model) throws IOException, ServletException{
		HongbaoActivity param = new HongbaoActivity();
		param.setId(id);
		HongbaoActivity activity = hongbaoService.getActivity(param);
		
		//获取红包分类
		List<Tag> tags = tagService.getByParentCode(tagCode);
		model.addAttribute("tags",tags);
		
		model.addAttribute("activity", activity);
		if (activity.getType() != null) {
			model.addAttribute("type", activity.getType());
		}
		
		return "/hongbao/update";
	}
	
	@RequestMapping("/hongbao/update/")
	public String update(HongbaoActivityForm aForm, HttpServletRequest request, HttpServletResponse response) throws Exception{
		HongbaoActivity activity = new HongbaoActivity();
		
		if (aForm.getStartdateStr() != null && !"".equals(aForm.getStartdateStr())) {
			activity.setStartdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(aForm.getStartdateStr()+ " 00:00:00 "));
			aForm.setStartdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(aForm.getStartdateStr()+ " 00:00:00 "));
		}
		if (aForm.getEnddateStr() != null && !"".equals(aForm.getEnddateStr())) {
			activity.setEnddate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(aForm.getEnddateStr()+ " 23:59:59 "));
			aForm.setEnddate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(aForm.getEnddateStr()+ " 23:59:59 "));
		}		
		if (aForm.getCreatetimeStr() != null && !"".equals(aForm.getCreatetimeStr())) {
			activity.setCreatetime(new SimpleDateFormat("yyyy-MM-dd").parse(aForm.getCreatetimeStr()));
		}
		Date nowDate = new Date();
		if (aForm.getEnddate().getTime() < nowDate.getTime()) {
			activity.setFlag(0);
		} else if (aForm.getStartdate().getTime() <= nowDate.getTime()) {
			activity.setFlag(1);
		} else {
			activity.setFlag(2);
		}		
		
		activity.setId(aForm.getId());
		activity.setHeader(aForm.getHeader());
		activity.setPrice(aForm.getPrice());
		activity.setLianjie(aForm.getLianjie());		
		activity.setTotalnum(aForm.getTotalnum());
		activity.setTotalprice(aForm.getTotalprice());
		activity.setName(aForm.getName());
		activity.setType(aForm.getType());
		activity.setSharedays(aForm.getSharedays());
		activity.setUsedays(aForm.getUsedays());
		activity.setUpdatetime(new Date());
		activity.setMaxprice(aForm.getMaxprice());
		activity.setLimitprice(aForm.getLimitprice());
		activity.setStatus(0);
		activity.setTagId(aForm.getTagId());
		//添加红包统计字段
		activity.setValidenum(aForm.getValidenum());
		activity.setRecivenum(aForm.getRecivenum());
		activity.setValidetotalprice(aForm.getValidetotalprice());
		activity.setRecivetotalprice(aForm.getRecivetotalprice());
		activity.setTransrate(aForm.getTransrate());

		hongbaoService.updateActivity(activity);
		return "redirect:/hongbao/list/";
	}
	
	/**
	 * 保存/编辑[活动红包]
	 * @author WKX
	 * @param aForm
	 * @param request
	 * @param response
	 * @param model
	 * @throws IOException 
	 * @throws ServletException 
	 * @since 2016年1月15日 下午4:32:37
	 */
	@RequestMapping("/hongbao/save")
	public String save(HongbaoActivityForm aForm, HttpServletRequest request, HttpServletResponse response,Model model) throws ServletException, IOException{
		try {
			HongbaoActivity activity = new HongbaoActivity();
			Date start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(aForm.getStartdateStr()+ " 00:00:00");
			if (aForm.getStartdateStr() != null && !"".equals(aForm.getStartdateStr())) {
				activity.setStartdate(start);
				aForm.setStartdate(start);
			}
			if (aForm.getEnddateStr() != null && !"".equals(aForm.getEnddateStr())) {
				Date end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(aForm.getEnddateStr()+ " 23:59:59");
				activity.setEnddate(end);
				aForm.setEnddate(end);
			}
			
			Long count = hongbaoService.countByStartAndEndAndTagIdAndNotId(aForm.getStartdate(),aForm.getEnddate(),aForm.getTagId(),aForm.getId());
			if(count>0)throw new Exception("红包活动时间存在冲突！");
			
			Date nowDate = new Date();
			if (aForm.getEnddate().getTime() < nowDate.getTime()) {
				activity.setFlag(0);//已经结束
			} else if (aForm.getStartdate().getTime() <= nowDate.getTime()) {
				activity.setFlag(1);//进行中
			} else {
				activity.setFlag(2);//还未进行
			}
			
			activity.setHeader(aForm.getHeader());
			activity.setPrice(aForm.getPrice());
			activity.setLianjie(aForm.getLianjie());		
			activity.setTotalnum(aForm.getTotalnum());
			activity.setTotalprice(aForm.getTotalprice());
			activity.setName(aForm.getName());
			activity.setType(aForm.getType());
			activity.setSharedays(aForm.getSharedays());
			activity.setUsedays(aForm.getUsedays());
			activity.setMaxprice(aForm.getMaxprice());
			activity.setLimitprice(aForm.getLimitprice());
			activity.setStatus(0);
			activity.setTagId(aForm.getTagId());
			if(aForm !=null && aForm.getId()!=null && aForm.getId()>0){
				//编辑
				if (aForm.getCreatetimeStr() != null && !"".equals(aForm.getCreatetimeStr())) {
					activity.setCreatetime(new SimpleDateFormat("yyyy-MM-dd").parse(aForm.getCreatetimeStr()));
				}
				activity.setId(aForm.getId());
				activity.setUpdatetime(new Date());
				//添加红包统计字段
				activity.setValidenum(aForm.getValidenum());
				activity.setRecivenum(aForm.getRecivenum());
				activity.setValidetotalprice(aForm.getValidetotalprice());
				activity.setRecivetotalprice(aForm.getRecivetotalprice());
				activity.setTransrate(aForm.getTransrate());
				hongbaoService.updateActivity(activity);
			}else{
				//新增
				activity.setCreatetime(new Date());
				hongbaoService.addActivity(activity);
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
			return "/hongbao/error";
		}
		return "redirect:/hongbao/list/?tagCode="+aForm.getTagCode();
	}
}