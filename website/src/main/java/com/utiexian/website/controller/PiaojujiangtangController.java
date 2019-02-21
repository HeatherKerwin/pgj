package com.utiexian.website.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Piaojujiangtang;
import com.ry.core.form.PiaojujiangtangForm;
import com.ry.core.service.PiaojujiangtangService;
import com.ry.core.util.Constant;
import com.ry.util.JacksonUtil;
import com.ry.util.StringUtil;
import com.ry.util.page.PageResults;


/**
 * @author KHC
 * 票据学院
 */
@Controller
public class PiaojujiangtangController {
	
	@Resource
	PiaojujiangtangService piaojujiangtangService;
	
	/**
	 * 票据学院页面
	 * @author KHC
	 * @param type
	 * @param request
	 * @since 2016年12月12日 下午4:42:37
	 */
	@RequestMapping("/piaojuxueyuan/index")
	public String index(Integer type,HttpServletRequest request){
		if(type==1){
			return "/piaojuxueyuan/zhishi";
		}else if(type==2){
			return "/piaojuxueyuan/yanpiao";
		}else if(type==3){
			return "/piaojuxueyuan/falv";
		}else if(type==4){
			return "/piaojuxueyuan/account";//开户指南
		}else if(type==5){
			return "/piaojuxueyuan/pay";//打款收款指南
		}else{
			return "/piaojuxueyuan/zhishi";
		}
	}
	
	/**
	 * 获取列表
	 * @author KHC
	 * @param nf
	 * @param pageIndex
	 * @param pageSize
	 * @param request
	 * @param response
	 * @param model
	 * @throws IOException
	 * @since 2016年11月19日 下午3:19:20
	 */
	@RequestMapping("/piaojuxueyuan/list")
	public String list(PiaojujiangtangForm nf,Integer pageIndex,Integer pageSize,HttpServletRequest request, HttpServletResponse response,Model model) throws IOException {		
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			if(pageIndex==null)pageIndex = 0;
			if(pageSize==null)pageSize = 15;
			nf.setType(nf.getType());
			nf.setTitle(nf.getKeyword());
			
			PageResults<Piaojujiangtang> pageResults = piaojujiangtangService.getPageList(nf, pageIndex, pageSize);
			map.put("response", "success");
			map.put("msg", "操作成功");
			map.put("data",pageResults.getResults());
		}catch(Exception e){
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "获取失败");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(map));
		return "ajax";
	}	
	
	/**
	 * 详情
	 * @author KHC
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 * @since 2016年11月19日 下午3:24:40
	 */
	@RequestMapping("/piaojuxueyuan/detail/{id}")
	public String detail(@PathVariable(value="id")Integer id,Integer type,HttpServletRequest request, HttpServletResponse response) throws IOException {		
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Piaojujiangtang piaojujiangtang = piaojujiangtangService.getPiaojujiangtangById(id);
			if(piaojujiangtang!=null){
				map.put("response", "success");
				request.setAttribute("piaojujiangtang", piaojujiangtang);
				if(type==1){
					return "/piaojuxueyuan/info";//票据知识详情
				}
				if(type==2){
					return "/piaojuxueyuan/yanpiaoinfo";//如何收票详情
				}
				if(type==3){
					return "/piaojuxueyuan/falvinfo";//法律法规详情
				}
			}else{
				return StringUtil.processRedirectUrl(Constant.properties.getProperty("baseUrl"),"/piaojuxueyuan/index?type="+type);
			}
		}catch(Exception e){
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "获取失败");
		}
		return null;
	}	
}
