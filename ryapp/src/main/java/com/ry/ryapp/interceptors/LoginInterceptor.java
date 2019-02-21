package com.ry.ryapp.interceptors;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ry.core.dao.impl.ActionlogDaoImpl;
import com.ry.core.entity.Actionlog;
import com.ry.core.entity.Member;
import com.ry.core.service.ActionlogService;
import com.ry.core.util.Constant;
import com.ry.util.HttpUtil;

/**
 * 
 * <p>Title: HandlerInterceptor1</p>
 * <p>Description:登陆认证拦截器 </p>
 * <p>Company: www.itcast.com</p> 
 */
@Controller
public class LoginInterceptor implements HandlerInterceptor {

	@Resource
	private ActionlogService actionlogService;					

	//进入 Handler方法之前执行
	//用于身份认证、身份授权
	//比如身份认证，如果认证通过表示当前用户没有登陆，需要此方法拦截不再向下执行
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		return true;
	}

	//进入Handler方法之后，返回modelAndView之前执行
	//应用场景从modelAndView出发：将公用的模型数据(比如菜单导航)在这里传到视图，也可以在这里统一指定视图
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {				
		
	}

	//执行Handler完成执行此方法
	//应用场景：统一异常处理，统一日志处理
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		String ipadr = HttpUtil.getIpAddr(request);
			Member member = (Member)request.getSession().getAttribute("member");		
			Actionlog actionlog = new Actionlog();		
			String from = request.getParameter("from");
			if (from != null) {
				actionlog.setFrom(from);
				actionlog.setCode(Constant.LOGIN);
			} else {
				actionlog.setFrom("APP");
				actionlog.setCode(Constant.QUICKLOGIN);
			}			
		    if (member != null) {
		    	actionlog.setMemberId(member.getId());
			}			
			actionlog.setIpadr(ipadr);
			actionlog.setActiontime(new Date());
			actionlogService.addActionlog(actionlog);
		}
}

