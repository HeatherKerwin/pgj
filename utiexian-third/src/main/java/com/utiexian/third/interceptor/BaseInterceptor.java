package com.utiexian.third.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class BaseInterceptor implements HandlerInterceptor {

	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {		
		if(request.getParameter("memberId") != null){
			Object memberId = request.getParameter("memberId");
			if(memberId != null){
				Map<String, Object> member = (Map<String, Object>) request.getSession().getAttribute("member");
				if(member==null || member.get("id") == null || !(member.get("id").toString()).equals(memberId.toString())){
					new Exception("获取用户信息失败！请重新登录");
					return false;
				}
					
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		
	}
}