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

public class JisuanqiInterceptor implements HandlerInterceptor {
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
			String member_id = request.getParameter("memberid");
			String belong = request.getParameter("belong");
			Actionlog actionlog = new Actionlog();
			if (StringUtils.isNotBlank(member_id)&&!"undefined".equals(member_id)) {
				if(StringUtils.isNotBlank(belong)){//new user
					if("0".equals(belong)){
						actionlog.setMemberId(Integer.valueOf(member_id));
						actionlog.setCode(Constant.JISUANQI);
					}else{
						actionlog.setMemberId(Integer.valueOf(member_id));
						actionlog.setCode(Constant.JISUANQI_ORG);
					}
				}else{//old user
					actionlog.setCode(Constant.JISUANQI);
					actionlog.setMemberId(Integer.valueOf(member_id));
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
}
