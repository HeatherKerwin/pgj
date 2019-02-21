package com.utiexian.third;

import org.jasig.cas.client.util.AssertionThreadLocalFilter;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.utiexian.third.filter.LoginFilter;
import com.utiexian.third.interceptor.BaseInterceptor;
import com.yonyou.yht.ex.web.filter.EnterpriseSelectFilter;
import com.yonyou.yht.web.cas.AuthenticationFilter2;
import com.yonyou.yht.web.cas.ProxyReceivingTicketValidationFilter;
import com.yonyou.yht.web.cas.sso.SingleSignOutFilter;
/**
 * 配置拦截器（及过滤器）
 * @author WKX
 * addPathPatterns 用于添加拦截规则
 * excludePathPatterns 用户排除拦截
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

	/* *********拦截器********************************************************************************************* */
	
	@Bean
	public BaseInterceptor getBaseInterceptor() {//必须以Bean的形式创建
		return new BaseInterceptor();
	}
	
	@Override
	public void addInterceptors(final InterceptorRegistry registry) {
		registry.addInterceptor(getBaseInterceptor()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}
	
	/* *********过滤器********************************************************************************************* */
	@Bean
	public SingleSignOutFilter SingleSignOutFilter() {//必须以Bean的形式创建
		return new SingleSignOutFilter();
	}
	
	@Bean
    public FilterRegistrationBean SingleSignOutFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(SingleSignOutFilter());
        registration.addUrlPatterns("/*");
        registration.setName("CAS Single Sign Out Filter");
        return registration;
    }
	
	@Bean
	public AuthenticationFilter2 AuthenticationFilter2() {//必须以Bean的形式创建
		return new AuthenticationFilter2();
	}
	
	@Bean
    public FilterRegistrationBean AuthenticationFilter2Registration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(AuthenticationFilter2());
        registration.addUrlPatterns("/*");
        registration.setName("CAS Authentication Filter");
        return registration;
    }
	
	@Bean
	public ProxyReceivingTicketValidationFilter ProxyReceivingTicketValidationFilter() {//必须以Bean的形式创建
		return new ProxyReceivingTicketValidationFilter();
	}
	
	@Bean
    public FilterRegistrationBean ProxyReceivingTicketValidationFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(ProxyReceivingTicketValidationFilter());
        registration.addUrlPatterns("/*");
        registration.setName("CAS Validation Filter");
        return registration;
    }
	
	@Bean
	public HttpServletRequestWrapperFilter HttpServletRequestWrapperFilter() {//必须以Bean的形式创建
		return new HttpServletRequestWrapperFilter();
	}
	
	@Bean
    public FilterRegistrationBean HttpServletRequestWrapperFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(HttpServletRequestWrapperFilter());
        registration.addUrlPatterns("/*");
        registration.setName("CAS HttpServletRequest Wrapper Filter");
        return registration;
    }
	
	@Bean
	public AssertionThreadLocalFilter AssertionThreadLocalFilter() {//必须以Bean的形式创建
		return new AssertionThreadLocalFilter();
	}
	
	@Bean
    public FilterRegistrationBean AssertionThreadLocalFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(AssertionThreadLocalFilter());
        registration.addUrlPatterns("/*");
        registration.setName("CAS Assertion Thread Local Filter");
        return registration;
    }
	
	@Bean
	public EnterpriseSelectFilter EnterpriseSelectFilter() {//必须以Bean的形式创建
		return new EnterpriseSelectFilter();
	}
	
	@Bean
    public FilterRegistrationBean EnterpriseAccountSelectFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(EnterpriseSelectFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("encoding", "UTF-8");
        registration.addInitParameter("ignoreUrlPatternType", "MUTICONTAINS");
        registration.addInitParameter("ignorePattern", "");
        registration.setName("CAS EnterpriseAccountSelect Filter");
        return registration;
    }
	
	@Bean
	public LoginFilter getLoginFilter() {//必须以Bean的形式创建
		return new LoginFilter();
	}
	
	@Bean
    public FilterRegistrationBean indexFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(getLoginFilter());
        registration.addUrlPatterns("/*");
        return registration;
    }
}