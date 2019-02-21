/**
 *
 */
package com.utiexian.website.interceptors;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ry.core.entity.Actionlog;
import com.ry.core.entity.Member;
import com.ry.core.service.ActionlogService;
import com.ry.core.util.Constant;
import com.ry.util.HttpUtil;

/**
 * @author MH
 *
 */
public class YPAcceptInterceptor implements HandlerInterceptor {
	@Resource
	private ActionlogService actionlogService;

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
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex) throws Exception {
		String ipadr = HttpUtil.getIpAddr(request);
		Member member = (Member)request.getSession().getAttribute("member");
		Integer memberId = null;
		if(member!=null){
			memberId = member.getId();
		}
		Actionlog actionlog = new Actionlog();
		actionlog.setCode(Constant.ACCPECT_YP);
		actionlog.setMemberId(memberId);
		actionlog.setFrom("PC");
		actionlog.setIpadr(ipadr);
		actionlog.setActiontime(new Date());
		actionlogService.addActionlog(actionlog);
	}
}
