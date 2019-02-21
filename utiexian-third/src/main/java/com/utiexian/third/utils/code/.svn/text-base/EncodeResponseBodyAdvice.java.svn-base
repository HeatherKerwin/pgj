package com.utiexian.third.utils.code;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.alibaba.fastjson.JSON;
import com.utiexian.third.service.ParamsService;
import com.utiexian.third.utils.sign.RSAUtils;
import com.utiexian.utils.BaseController;

@SuppressWarnings("all")
@RestControllerAdvice
public class EncodeResponseBodyAdvice implements ResponseBodyAdvice<Object> {

	@Autowired
    ParamsService paramsService;
	
	@Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return returnType.getMethodAnnotation(ResponseEncode.class) != null;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        try {
			ResponseEncode responseEncode = returnType.getMethodAnnotation(ResponseEncode.class);
			if (responseEncode.method() == SecurityMethod.NULL || responseEncode.method() == SecurityMethod.RSA) {
			    String result = "";
				if(body instanceof String){
					result = body.toString();
			    }else{
			    	result = JSON.toJSONString(body);
			    }
				
				ServletServerHttpRequest sshr = (ServletServerHttpRequest) request;
				HttpServletRequest r = sshr.getServletRequest();
				String appid = r.getParameter("appid");//可以取到入参
				
				String privateKey = paramsService.getPrivateKey(appid);
				result = RSAUtils.encode(result,appid, privateKey);
			    return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseController().FAILED("验签失败！");
		}
        return body;
    }
}