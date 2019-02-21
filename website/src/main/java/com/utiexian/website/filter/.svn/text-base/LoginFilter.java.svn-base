package com.utiexian.website.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ry.core.entity.Member;

public class LoginFilter implements Filter {
	
	public void init(FilterConfig filterConfig) throws ServletException {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response;
		String requestUri = req.getRequestURI();

		String contextPath = req.getContextPath();
		String url = requestUri.substring(contextPath.length());
		
		if(url.endsWith(".jpeg")||url.endsWith(".jpg")||url.endsWith(".png")||url.endsWith(".gif")||url.endsWith(".css")||url.endsWith(".js")){
			chain.doFilter(request, response);
			return;
		}else if(!url.endsWith("login") && !url.endsWith("logout")){
			HttpSession session = req.getSession();
			Member member = (Member) session.getAttribute("member");
			if(member!=null){
				chain.doFilter(request, response);
				return;
			}
			String fullUrl = req.getRequestURL().toString() ;
			if(fullUrl.indexOf("utiexian.com")>-1 || fullUrl.indexOf("ryfinance.com")>-1){//区别于线上的访问路径
				resp.sendRedirect("/login?redirect_url="+url);
			}else{
				resp.sendRedirect(req.getContextPath()+"/login?redirect_url="+url);
			}
		}else{
			chain.doFilter(request, response);
		}
	}

	public void destroy() {}
}