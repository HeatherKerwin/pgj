package com.utiexian.third.filter;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AssertionHolder;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.utiexian.utils.utils.HttpUtil;

@Configuration
@Component
public class LoginFilter extends HttpServlet implements Filter {

	private static final long serialVersionUID = 1L;
	
	@Autowired
    RestTemplate restTemplate;

	@Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    @SuppressWarnings("unchecked")
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    	HttpServletRequest req = (HttpServletRequest) servletRequest;
        String contextPath = req.getContextPath();
        String requestUri = req.getRequestURI();
		String url = requestUri.substring(contextPath.length());
		
        if(url.endsWith(".jpeg")||url.endsWith(".jpg")||url.endsWith(".png")||url.endsWith(".gif")||url.endsWith(".css")||url.endsWith(".js")
			||url.endsWith("image")||url.endsWith("input-lg")||url.endsWith(".ttf")||url.endsWith(".svg")||url.endsWith(".ico")){
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}
		if(!url.endsWith("/login") && !url.endsWith("/logout")){
			
			HttpSession session = req.getSession();
			Map<String, Object> member = (Map<String, Object>) session.getAttribute("member");
			if(member == null){//session 失效，用友登陆不在走首页，直接在此获取member赋值
				Assertion assertion = AssertionHolder.getAssertion(); 
				if(assertion != null){
					AttributePrincipal principal = assertion.getPrincipal(); 
					Map<String, Object> info = principal.getAttributes(); 
					String userId = (String)info.get("userId");
					String enterpriseId = (String)info.get("enterpriseId");
					
					MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
					params.set("userId",userId);
					params.set("enterpriseId",enterpriseId);
					
					params.set("qudao","PC");//渠道：PC,APP
					params.set("hezuo","yonyou");//用友
					params.set("ip",HttpUtil.getIpAddr(req));
					params.set("uuid",UUID.randomUUID().toString());
					String res = restTemplate.postForObject("http://utiexian-server/thirdkey/get/member",params, String.class);
					
					JSONObject result = JSON.parseObject(res);
					JSONObject object = result.getJSONObject("data");
					if(object != null){
						session.setAttribute("member", object);
					}
				}
			}
				
			filterChain.doFilter(servletRequest, servletResponse);
		}else{
			filterChain.doFilter(servletRequest, servletResponse);
		}
    }

    @Override
    public void destroy() {
    }
}