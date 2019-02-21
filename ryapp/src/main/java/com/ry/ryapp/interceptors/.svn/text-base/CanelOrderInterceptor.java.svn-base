package com.ry.ryapp.interceptors;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ry.core.entity.Actionlog;
import com.ry.core.service.ActionlogService;
import com.ry.core.service.DistributeOrderService;
import com.ry.core.service.OrgService;
import com.ry.core.util.Constant;
import com.ry.util.HttpUtil;

@Controller
public class CanelOrderInterceptor implements HandlerInterceptor{
	@Resource
	private ActionlogService actionlogService;
	
	@Resource
	private OrgService orgService;
	
	@Resource
	private DistributeOrderService distributeOrderService;

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
	//应用场景：统一异常处理，统一日志处理		取消订单
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) throws Exception {
			String ipadr = HttpUtil.getIpAddr(request);
			String dtbo_id = request.getParameter("memberid");
			String belong = request.getParameter("belong");
			if (StringUtils.isNotBlank(dtbo_id)) {
				Actionlog actionlog = new Actionlog();	
				if(StringUtils.isNotBlank(belong)){//新用户
					actionlog.setMemberId(Integer.valueOf(dtbo_id));
					if("0".equals(belong)){
						actionlog.setCode(Constant.QUXIAODINGDAN);
					}else if("1".equals(belong)){
						actionlog.setCode(Constant.QUXIAODINGDAN_ORG);
					}
				}else{//老用户
					actionlog.setCode(Constant.QUXIAODINGDAN);			//TODO:这是BNS企业的常量
					actionlog.setMemberId(Integer.valueOf(dtbo_id));
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
