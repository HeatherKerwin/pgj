package com.utiexian.website.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Actionlog;
import com.ry.core.entity.Member;
import com.ry.core.service.ActionlogService;
import com.ry.util.HttpUtil;
import com.ry.util.JacksonUtil;

/**
 * @author KHC
 * 功能统计
 */
@Controller
public class VisitLogController {

	@Resource
	private ActionlogService actionlogService;
	
	/**
	 * 功能统计(不调用拦截器的功能统计)
	 * @author KHC
	 * @param actionlog
	 * @param model
	 * @param request
	 * @throws IOException
	 * @since 2016年11月16日 上午12:06:48
	 */
	@RequestMapping("/pc/actionlog")
	public String addActionLog(String code,Model model,HttpServletRequest request){
		Map<String,Object> result = new HashMap<String, Object>();
		Member member = (Member) request.getSession().getAttribute("member");
		Integer memberId = null;
		if(member!=null){
			memberId = member.getId();
		}
		try {
			String ipadr = HttpUtil.getIpAddr(request);
			Actionlog actionlog = new Actionlog();
			actionlog.setMemberId(memberId);
			actionlog.setIpadr(ipadr);
			actionlog.setActiontime(new Date());
			actionlog.setCode(code);
			actionlog.setFrom("PC");
			actionlogService.addActionlog(actionlog);
			result.put("response", "success");
		} catch (Exception e) {
			result.put("response", "failed");
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
}
