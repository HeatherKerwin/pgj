package com.ry.ryapp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.OrgPartner;
import com.ry.core.service.OrgPartnerService;
import com.ry.util.JacksonUtil;

/**
 * 合作机构
 * @author WKX
 */
@Controller
@RequestMapping("/app/orgpartner/")
public class OrgPartnerController {
	
	@Resource
	OrgPartnerService orgPartnerService;
	
	
	/**
	 * 接单列表
	 * @param id 合作机构主键
	 * @param model
	 * @author WKX
	 */
	@RequestMapping("get")
	public String get(Integer id,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			if(id==null)throw new Exception();
			result.put("data", orgPartnerService.getById(id));
			result.put("response", "success");
			result.put("msg", "查询成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "查询失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 新增/编辑
	 * @author WKX
	 * @param orgPartner
	 * @param model
	 * @since 2017年1月11日 上午11:00:13
	 */
	@RequestMapping("save")
	public String save(OrgPartner orgPartner,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		String msg = "操作失败";
		try {
			if(orgPartner==null || orgPartner.getOrgId()==null)throw new Exception();
			if(orgPartner.getId()!=null){
				orgPartnerService.updateModel(orgPartner);
			}else{
				List<OrgPartner> list = orgPartnerService.getByOrgId(orgPartner.getOrgId());
				if(list!=null && list.size()>=10){
					msg = "操作失败：最多设置10个合作机构";
					throw new Exception();
				}
				orgPartner.setCreateTime(new Date());
				orgPartnerService.saveModel(orgPartner);
			}
			result.put("response", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", msg);
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 根据主键删除合作机构
	 * @author WKX
	 * @param id 主键
	 * @param model
	 * @since 2017年1月11日 上午11:01:31
	 */
	@RequestMapping("delete")
	public String delete(Integer id,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			if(id==null)throw new Exception();
			orgPartnerService.deleteById(id);
			result.put("response", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 根据机构主键获取合作机构
	 * @author WKX
	 * @param orgId 机构主键
	 * @param model
	 * @since 2017年1月11日 上午11:02:25
	 */
	@RequestMapping("list")
	public String list(Integer orgId,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			if(orgId==null)throw new Exception();
			List<OrgPartner> list = orgPartnerService.getByOrgId(orgId);
			result.put("data", list);
			result.put("response", "success");
			result.put("msg", "查询成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "查询失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
}