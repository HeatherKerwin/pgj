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
import com.ry.core.service.DistributeOrderService;
import com.ry.core.service.OrgService;
import com.ry.core.util.Constant;
import com.ry.util.HttpUtil;

public class BindInterceptor implements HandlerInterceptor {
	@Resource
	private ActionlogService actionlogService;
	
	@Resource
	private OrgService orgService;
	
	@Resource
	private DistributeOrderService distributeOrderService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {				
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) throws Exception {
			String ipadr = HttpUtil.getIpAddr(request);
			String member_id = request.getParameter("memberId");
			String type = request.getParameter("type");
			String belong = request.getParameter("belong");
			Actionlog actionlog = new Actionlog();
			if(StringUtils.isNotBlank(member_id) && StringUtils.isNotBlank(type) && StringUtils.isNotBlank(belong)&&!"undefined".equals(member_id)){
				if("0".equals(belong)){//企业
					if("1".equals(type)){
						actionlog.setCode(Constant.WEICHAT);
					}else if("2".equals(type)){
						actionlog.setCode(Constant.QQ);
					}else if("3".equals(type)){
						actionlog.setCode(Constant.WEBO);
					}
				}else if("1".equals(belong)){
					if("1".equals(type)){
						actionlog.setCode(Constant.WEICHAT_ORG);
					}else if("2".equals(type)){
						actionlog.setCode(Constant.QQ_ORG);
					}else if("3".equals(type)){
						actionlog.setCode(Constant.WEBO_ORG);
					}
				}
				actionlog.setMemberId(Integer.parseInt(member_id));
			}
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
