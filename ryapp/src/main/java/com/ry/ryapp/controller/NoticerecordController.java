package com.ry.ryapp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Noticerecord;
import com.ry.core.service.NoticerecordService;
import com.ry.util.DateUtil;
import com.ry.util.JacksonUtil;


/**
 * 票据管理提醒
 * @author WKX
 */
@Controller
@RequestMapping("/app/noticerecord/")
public class NoticerecordController {
	
	@Resource					
	NoticerecordService noticerecordService;
	
	/**
	 * 根据票据主键查找-提醒
	 * @author WKX
	 * @param accountrecordId
	 * @param model
	 * @since 2016年3月31日 下午7:27:08
	 */
	@RequestMapping("getByAccountrecordId")
	public String getByFkId(Integer accountrecordId,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		Noticerecord not= noticerecordService.getByFkId(accountrecordId);
		try {
			if(not==null)throw new Exception();
			Long expiredate= not.getExpiredate().getTime();
			Long noticeaddtime= not.getNoticeaddtime();
			result.put("response", "success");
			result.put("msg", "获取提醒");
			result.put("data", not);
			result.put("expiredate", expiredate);
			result.put("noticeaddtime", noticeaddtime);
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "没有提醒");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 保存（编辑）
	 * @author WKX
	 * @param noticerecord
	 * @param model
	 * @since 2016年4月1日 上午10:38:06
	 */
	@RequestMapping("save")
	public String save(Noticerecord noticerecord,String start,String end,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			if(noticerecord==null || noticerecord.getFkId()==null)throw new Exception();
			
			if(StringUtils.isNotBlank(start))noticerecord.setExpiredate(DateUtil.parser(start, DateUtil.FORMART3));
			if(StringUtils.isNotBlank(end))noticerecord.setNoticetime(DateUtil.parser(end, DateUtil.FORMART3));
			
			Noticerecord not= noticerecordService.getByFkId(noticerecord.getFkId());
			if(not!=null){
				noticerecord.setId(not.getId());
				noticerecord.setNoticeaddtime(not.getNoticeaddtime());//创建时间
			}else{
				noticerecord.setNoticeaddtime(new Date().getTime());//创建时间
			}
			noticerecordService.addNoticerecord(noticerecord);
			result.put("response", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "请求失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 更新对象
	 * @author WKX
	 * @param id
	 * @param noticedesc
	 * @param model
	 * @since 2016年4月1日 下午1:04:54
	 */
	@RequestMapping("update")
	public String update(Integer id,String noticedesc,String noticetime,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			Noticerecord not = noticerecordService.getById(id);
			if(not==null)throw new Exception();
			Date date = DateUtil.parser(noticetime, DateUtil.FORMART3);
			not.setNoticedesc(noticedesc);
			not.setNoticetime(date);
			noticerecordService.addNoticerecord(not);
			result.put("response", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "请求失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 根据主键删除对象
	 * @author WKX
	 * @param id
	 * @param model
	 * @since 2016年4月1日 下午2:10:24
	 */
	@RequestMapping("del")
	public String del(Integer id,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			if(id==null)throw new Exception();
			noticerecordService.delById(id);
			result.put("response", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "请求失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
}