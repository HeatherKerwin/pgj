package com.utiexian.utils;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.ui.Model;

import com.utiexian.utils.utils.JacksonUtil;

/**
 * 数据请求返回值
 * @author WKX
 * @异常代码：{
 * 	E00:Exception
 * 	E01:ParseException
 * }
 */
public class BaseController {
	
	protected final String SUCCESS = "success";
	protected final String FAILED = "failed";
	
	protected final String SUCCESS_MSG = "操作成功！";
	protected final String FAILED_MSG = "操作失败！请联系客服";
	
	//***************SERVER端返回数据封装***************************************************************************************************
	
	/**
	 * 操作成功
	 * @author WKX
	 * @param msg 提示内容
	 */
	public String SUCCESS(String msg){
		return this.SUCCESS(msg, null);
	}
	
	/**
	 * 操作成功
	 * @author WKX
	 * @param msg 提示内容
	 * @param data 数据
	 */
	public String SUCCESS(String msg,Object data){
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("response", this.SUCCESS);
		result.put("msg", StringUtils.isNotBlank(msg)?msg:SUCCESS_MSG);
		result.put("data", data);
		return JacksonUtil.objToJson(result);
	}
	
	/**
	 * 操作失败
	 * @author WKX
	 * @param msg 提示内容
	 */
	public String FAILED(String msg){
		return this.FAILED(msg, null);
	}
	
	/**
	 * 操作失败（根据异常处理）
	 * @author WKX
	 * @param e 异常类（自定义的异常则返回指定提示内容）
	 */
	public String FAILED(Exception e){
		return FAILED(e,null);
	}
	
	/**
	 * 操作失败（倾向自定义错误信息，如果没有则使用传递的提示）
	 * @author WKX
	 * @param e 异常类（自定义的异常则返回指定提示内容）
	 */
	public String FAILED(Exception e,String msg){
		if(e instanceof MyException && StringUtils.isNotBlank(e.getMessage())){
			msg = e.getMessage();
		}else if(e instanceof ParseException){
			msg = "数据异常，请联系客服！（E01）";
		}
		if(StringUtils.isBlank(msg)){
			msg = FAILED_MSG;
		}
		return this.FAILED(msg, null);
	}
	
	/**
	 * 操作失败
	 * @author WKX
	 * @param msg 提示内容
	 * @param data 数据
	 */
	public String FAILED(String msg,Object data){
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("response", this.FAILED);
		result.put("msg", StringUtils.isNotBlank(msg)?msg:FAILED_MSG);
		result.put("data", data);
		return JacksonUtil.objToJson(result);
	}
	
	//***************CLIENT-APP端返回数据封装***************************************************************************************************
	
	/**
	 * [CLIENT]端数据返回（成功）
	 * @author WKX
	 * @param res 接口数据
	 * @param model
	 */
	public String DATE_SUCCESS(String res,Model model){
		if(StringUtils.isBlank(res)){
			res = "{\"data\":\"\",\"response\":\"success\",\"msg\":\"" + SUCCESS_MSG + "\"}";
		}
		model.addAttribute("data", res);
		model.addAttribute("sign", "DATA");
		return "ajax";
	}
	
	/**
	 * [CLIENT]端数据返回（失败）
	 * @author WKX
	 * @param msg 提示内容
	 * @param model
	 */
	public String DATE_FAILED(String msg,Model model){
		String res = "{\"data\":\"\",\"response\":\"failed\",\"msg\":\""+ msg +"\"}";
		model.addAttribute("data", res);
		model.addAttribute("sign", "DATA");
		return "ajax";
	}
	
	/*--------------------------------------------------------------------------------------------------*/
	
	/**
	 * [CLIENT]端数据返回（成功）_csrf
	 * @author WKX
	 * @param res 接口数据
	 */
	public String DATE_SUCCESS(Object res,HttpServletRequest request){
		Map<String,Object> result = new HashMap<String, Object>();
		if(res==null){
			res = "{\"data\":\"\",\"response\":\"success\",\"msg\":\"" + SUCCESS_MSG + "\"}";
		}
		result.put("data", res);
		result.put("sign", "DATA");
		return this.AJAX(request, result);
	}
	
	/**
	 * [CLIENT]端数据返回（失败）_csrf
	 * @author WKX
	 * @param msg 提示内容
	 */
	public String DATE_FAILED(Object msg,HttpServletRequest request){
		Map<String,Object> result = new HashMap<String, Object>();
		if(msg==null){
			msg = FAILED_MSG;
		}
		String res = "{\"data\":\"\",\"response\":\"failed\",\"msg\":\""+ msg +"\"}";
		result.put("data", res);
		result.put("sign", "DATA");
		return this.AJAX(request, result);
	}
	
	/**
	 * 封装数据(获取_csrf)
	 */
	private String AJAX(HttpServletRequest request,Map<String,Object>result){
		Object object = request.getAttribute("_csrf");
		if(object!=null){
			CsrfToken csrf = (CsrfToken)object;
			result.put("token", csrf.getToken());
			result.put("header", csrf.getHeaderName());
		}
		if(!result.containsKey("state"))result.put("state","");
		return JacksonUtil.objToJson(result);
	}
}