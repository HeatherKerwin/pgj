package com.ry.rycms.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Notice;
import com.ry.core.entity.NoticeAdd;
import com.ry.core.service.NoticeAddService;
import com.ry.core.service.TiexianNoticeService;
import com.ry.util.DateUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.page.PageResults;

/**
 * 节假日管理（含补班）
 * （原：贴现提示信息动态维护）
 */
@Controller
public class TiexianController {

	@Resource
	private TiexianNoticeService tiexianNoticeService; 
	
	@Resource
	private NoticeAddService noticeAddService;
	
	@RequestMapping("/tiexian/add")
	public String add(Notice notice,String start,String end, String pageName){
		try {
			if(notice!=null && notice.getId()!=null){
				Notice temp = tiexianNoticeService.findOne(notice.getId());
				if(temp!=null){
					temp.setAlert(notice.getAlert());
					temp.setContent(notice.getContent());
					if(StringUtils.isNotBlank(start))temp.setStartDate(DateUtil.parser(start+" 00:00:00", DateUtil.FORMART));
					if(StringUtils.isNotBlank(end))temp.setEndDate(DateUtil.parser(end+" 23:59:59", DateUtil.FORMART));
					temp.setRemark(notice.getRemark());
					tiexianNoticeService.add(temp);
				}else{
					tiexianNoticeService.add(notice);
				}
			}else{
				if(StringUtils.isNotBlank(start)) {
					notice.setStartDate(DateUtil.parser(start+" 00:00:00", DateUtil.FORMART));
					notice.setYearToken(start.substring(0, 4));
				}
				if(StringUtils.isNotBlank(end))notice.setEndDate(DateUtil.parser(end+" 23:59:59", DateUtil.FORMART));
				
				tiexianNoticeService.add(notice);
			}
			if ("holidayEdit".equals(pageName)) {
				return "redirect:/tiexian/findHoliday";
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "redirect:/tiexian/findall";
	}
	
	/**
	 * 获取贴现提示语（弃用）
	 * @author WKX
	 * @param content
	 * @param code
	 * @param model
	 * @since 2016年1月27日 下午2:08:38
	 */
	@RequestMapping("/tiexian/findall/old")
	public String findAll(String content,String code,Model model){
		List<Notice> list = tiexianNoticeService.findByYearToken(DateUtil.formart(new Date(), "yyyy"));//查询本年度提示
		if(list!=null && list.size()>0){
			for(Notice no:list){
				if(StringUtils.isNotBlank(no.getCode()))model.addAttribute(no.getCode(), no);
			}
		}
		return "/information/prompt";
	}
	
	@RequestMapping("/tiexian/findHoliday")
	public String findHoliday(){
		return "/information/holidayEdit";
	}
	
	@RequestMapping("/tiexian/findByYeay")
	public String findByYear(String year, Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (year == null || year.trim().equals("")){
				year = DateUtil.formart(new Date(), "yyyy");
			}
			List<Notice> noticeList = tiexianNoticeService.findByYearToken(year);
			if (noticeList!=null && noticeList.size()>0) {
				result.put("noticeList", noticeList);
			}
			result.put("state", "success");
		} catch (Exception e) {
			result.put("state", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/* --------------------------------------新的节假日管理（含补班）--------------------------------------- */
	
	/**
	 * 节假日（法定节假日，非周末）
	 * @author WKX
	 * @param model
	 * @since 2017年5月11日 下午3:00:29
	 */
	@RequestMapping("/tiexian/findall")
	public String noticeList(PageResults<?> pr,String name,Model model){
		if(pr.getCurrentPage()==null){
			pr.setCurrentPage(0);
		}
		Notice notice = new Notice();
		notice.setCode("FESTIVAL");
		notice.setName(name);
		pr = tiexianNoticeService.getPageList(pr.getCurrentPage(),10,notice);	
		model.addAttribute("pageModel", pr);
		model.addAttribute("notice", tiexianNoticeService.findByCode("OFFDAY"));
		return "/information/notice_list";
	}
	
	/**
	 * 获取节日（含补班）
	 * @author WKX
	 * @param id 节日主键
	 * @param model
	 * @since 2017年5月12日 下午4:05:12
	 */
	@RequestMapping("/tiexian/findall/get")
	public String get(Integer id, Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(id==null)throw new Exception();
			Notice notice = tiexianNoticeService.findOne(id);
			result.put("notice", notice);
			result.put("noticeAdds", noticeAddService.getByNoticeId(id));
			result.put("response", "success");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 删除节日（含补班）
	 * @author WKX
	 * @param id 节日主键
	 * @param model
	 * @since 2017年5月12日 下午4:05:12
	 */
	@RequestMapping("/tiexian/findall/del")
	public String del(Integer id, Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(id==null)throw new Exception();
			tiexianNoticeService.deleteGroupById(id);
			result.put("response", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 删除补班
	 * @author WKX
	 * @param id 补班主键
	 * @param model
	 * @since 2017年5月12日 下午7:14:52
	 */
	@RequestMapping("/tiexian/findall/noticeadd/del")
	public String delNoticeAdd(Integer id, Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(id==null)throw new Exception();
			noticeAddService.deleteById(id);
			result.put("response", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 保存节假日（含补班）
	 * @author WKX
	 * @param notice 节假日
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param model
	 * @since 2017年5月12日 下午7:20:01
	 */
	@RequestMapping("/tiexian/findall/save")
	public String save(Notice notice,String[]notice_add_day,Integer[]notice_add_id,String start,String end,Model model){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<NoticeAdd> noticeAdds = new ArrayList<NoticeAdd>();//补班
			if(notice_add_day!=null && notice_add_day.length>0){
				NoticeAdd noticeAdd = null;
				for(int i=0;i<notice_add_day.length;i++){
					String day = notice_add_day[i];
					if(StringUtils.isNotBlank(day)){
						noticeAdd = new NoticeAdd();
						noticeAdd.setDay(day);
						if(notice_add_id!=null && notice_add_id.length>i)noticeAdd.setId(notice_add_id[i]);//编辑含id
						noticeAdds.add(noticeAdd);
					}
				}
			}
			
			if(notice!=null && notice.getId()!=null){//节假日
				Notice temp = tiexianNoticeService.findOne(notice.getId());
				if(temp!=null){
					temp.setAlert(notice.getAlert());
					temp.setContent(notice.getContent());
					if(StringUtils.isNotBlank(start))temp.setStartDate(DateUtil.parser(start+" 00:00:00", DateUtil.FORMART));
					if(StringUtils.isNotBlank(end))temp.setEndDate(DateUtil.parser(end+" 23:59:59", DateUtil.FORMART));
					temp.setRemark(notice.getRemark());
					temp.setName(notice.getName());
					tiexianNoticeService.saveAndNoticeAdd(temp,noticeAdds);
				}else{
					tiexianNoticeService.saveAndNoticeAdd(notice,noticeAdds);
				}
			}else{
				if(StringUtils.isNotBlank(start)) {
					notice.setStartDate(DateUtil.parser(start+" 00:00:00", DateUtil.FORMART));
					notice.setYearToken(start.substring(0, 4));
				}
				if(StringUtils.isNotBlank(end))notice.setEndDate(DateUtil.parser(end+" 23:59:59", DateUtil.FORMART));
				tiexianNoticeService.saveAndNoticeAdd(notice,noticeAdds);
			}
		} catch (ParseException e) {
			result.put("response", "failed");
			result.put("msg", "保存失败");
			e.printStackTrace();
		}
		return "redirect:/tiexian/findall";
	}
}