package com.utiexian.third.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.taobao.api.ApiException;
import com.utiexian.third.utils.sign.RSAUtils;
import com.utiexian.utils.BaseController;
import com.utiexian.utils.MyException;
import com.utiexian.utils.utils.SendMessagesUtil;

@Controller
public class IndexController extends BaseController{

	@Autowired
    RestTemplate restTemplate;
	
	/**
	 * 首页
	 * @author WKX
	 */
	@RequestMapping(value={"/","/index"})
	public String index() {
		return "index";
	}
	
    /**
     * 获取当前所有服务（含服务降级）
     * @author WKX
     */
	@RequestMapping("/test")
    @HystrixCommand(fallbackMethod = "fallback")
    public @ResponseBody String data() {
    	return restTemplate.getForObject("http://utiexian-server/test", String.class);
    }
    
    /**
     * 服务消费者就通过HystrixCommand注解中指定的降级逻辑进行执行
     * 为异常情况提供了自动的服务降级切换机制
     * @author WKX
     */
    public String fallback() {
        return "Hystrix:fallback";
    }
    
	/**
	 * 跳转页面，传值（表单的形式）
	 * @author MH
	 * @param _PAGE 打开的页面
	 * @param request
	 * @param model
	 * @since 2016年11月1日 下午5:22:11
	 */
	@RequestMapping("/open/page")
	public String open(String _PAGE,HttpServletRequest request, Model model){
        Enumeration<?> paramNames = request.getParameterNames();  
        while (paramNames.hasMoreElements()) {  
            String paramName = (String) paramNames.nextElement();  
  
            String[] paramValues = request.getParameterValues(paramName);  
            if (paramValues.length == 1) {  
                String paramValue = paramValues[0];  
                if (paramValue.length() != 0) {
                    if(!"_PAGE".equals(paramName))model.addAttribute(paramName, paramValue);
                }
            }
        }
		if(StringUtils.isNotBlank(_PAGE)){
			return _PAGE;
		}else{
			return "index";
		}
	}
	
	/**
	 * 跳转等待的页面
	 * @author ZWD
	 * @return
	 */
	@RequestMapping("/loading")
	public String loading(){
		return "loading";
	}
	
	/**
	 * 跳转错误的页面
	 * @author MH
	 * @return
	 */
	@RequestMapping("/errors")
	public String error(){
		return "error";
	}
	
	/**
	 * 公用发送验证码
	 * @author ZWD
	 * @param mobile 手机号码
	 * @param type 类型（TIEXIAN贴现支付、PRICE接单、BEISHU确认背书、TX提现、INQUIRY查询查复）
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value="/send/sms")
	public @ResponseBody String sendSms(String mobile,String type,HttpServletRequest request) {
		String code = null;
		try {
			if(StringUtils.isBlank(mobile) || StringUtils.isBlank(type))throw new MyException();
			
			code = String.valueOf((Math.random())).split("\\.")[1];
			code = code.substring(0,4);
			
			Map<String, Object> member = (Map<String, Object>) request.getSession().getAttribute("member");
			if(member==null || member.get("mobile") == null || !(member.get("mobile").toString()).equals(mobile.toString())) new Exception("获取用户信息失败！请重新登录");
			
			SendMessagesUtil.sendMessage(mobile,code);
			request.getSession().setAttribute("code", code);
		} catch (ApiException e) {
			e.printStackTrace();
			return this.FAILED("发送失败，请确定手机号码输入正确！");
		}
		return this.SUCCESS("发送成功，请注意查收！");
	}
	
	/**
	 * 测试接口
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String appId = "509650660001";
		String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCq46u85ZpE3hXkWjuDX8IjcXIYq+KmMAuC59ebwQrll9iYL66JJsIAWkvKcXOLjNVo5YevWFwD2Ea0ZHKcqQi1YrxhI7HOAciGWt1xLkZY/rALxoduK+CVsbIAg5EmBAlXi4/Bh9lww2PgAptYX9f0jVJaw665eoDenH55FQFBuwIDAQAB";
		String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKrjq7zlmkTeFeRaO4NfwiNxchir4qYwC4Ln15vBCuWX2JgvrokmwgBaS8pxc4uM1Wjlh69YXAPYRrRkcpypCLVivGEjsc4ByIZa3XEuRlj+sAvGh24r4JWxsgCDkSYECVeLj8GH2XDDY+ACm1hf1/SNUlrDrrl6gN6cfnkVAUG7AgMBAAECgYBnotUnJNOPCmSabQKEpNE273jzb2JvyNliVToT0lnKbXFEsj4WHTQwRJKMPouFSS6vg+vqshpzlnkis3wyd+y4HaTHMGATakYFw81wfRxZdKuX2DHBCY5uSpb8Wby/5HTReygovfWJ/1MhzhpWm1uatEFOrULQrn/67xufld2DgQJBAOI2fG3h9IrVAe4uASBQJkyX/oyQ5sYkSOYqbGkSA3MuTJ3+PoVvMvYibfQY1FTEAqdH4KXLVmU9aCQsPj/vyXsCQQDBZEJeYruYXv/AsDVxmyFyRo2FVsHCA3zRyDcILXb5gWPT4+TCGwralnrGv8C5+iXnljJskAicIO+WUEsD+lTBAkEAiR/8N2PEKDBNFkN+3UaYoM92N2FS8wNlyVgoFV0dCEIloZRP8/TfUIK4YkJ229dhwGN60yW6fm5F5b+IEJvbZwJAeboEuGpHEDKDoqKHEToHBGn6PDqOfPM7BsSSNxh+8a+KyV2BqNfirhHXt/AMfENCxpFYrdK/SJ7Gj4N8pEVWQQJBAMAgboAGZxgs9W4lotV3aZ/azXEj/7Ivsm9k9lBSXlHrsnwMGIb4EOyOMlus9uVS7XX0y0kkb929NbS/Tq4ghYA=";
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("bank_branch", "309791001010");//原始参数
		
		String url = "http://test.utiexian.com:3030/cib/query/back";
		System.err.println(RSAUtils.sendPost(url, appId, privateKey, publicKey, params));
	}
}