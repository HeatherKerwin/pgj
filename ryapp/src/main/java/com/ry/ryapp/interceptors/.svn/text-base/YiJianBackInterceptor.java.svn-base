package com.ry.ryapp.interceptors;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ry.core.entity.Actionlog;
import com.ry.core.service.ActionlogService;
import com.ry.core.service.OrgService;
import com.ry.core.util.Constant;
import com.ry.util.HttpUtil;

public class YiJianBackInterceptor implements HandlerInterceptor {
	@Resource
	private ActionlogService actionlogService;
	@Resource
	private OrgService orgService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}
	
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		String ipadr = HttpUtil.getIpAddr(request);
		String member_id = request.getParameter("memberid");
		String belong = request.getParameter("belong");
		Actionlog actionlog = new Actionlog();
		if (StringUtils.isNoneBlank(member_id)&&!"undefined".equals(member_id) && StringUtils.isNoneBlank(belong)) {
			if("0".equals(belong)){
				actionlog.setCode(Constant.YIJIANBACK);//企业
			}else{
				actionlog.setCode(Constant.YIJIANBACK_ORG);//机构
			}
			actionlog.setMemberId(Integer.valueOf(member_id));
			String from = request.getParameter("from");
			if (from != null) {
				actionlog.setFrom(from);
			} else {
				actionlog.setFrom("APP");
			}
			actionlog.setIpadr(ipadr);
			actionlog.setActiontime(new Date());

			actionlogService.addActionlog(actionlog);
		}
	}
}
