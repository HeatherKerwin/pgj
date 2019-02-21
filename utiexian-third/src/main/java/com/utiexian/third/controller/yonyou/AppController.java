package com.utiexian.third.controller.yonyou;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.yonyou.yht.ex.sdk.AppCenter;
import com.yonyou.yht.sdkutils.JsonResponse;
@RestController
public class AppController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    RestTemplate restTemplate;
	
    @PostMapping("/openapp")
    public String hello(HttpServletRequest req){
        String url = req.getRequestURL().toString();
        if (StringUtils.isNotBlank(req.getQueryString())) {
            url = url + "?" + req.getQueryString();
        }
        Map<String, String[]> param = req.getParameterMap();
        Map<String,Object> ps = new HashMap<String,Object>();
        for(Map.Entry<String, String[]> entry: param.entrySet()){
            ps.put(entry.getKey(),entry.getValue()[0]);
        }
        JsonResponse response = AppCenter.checkRequestParams(req);//检查签名和激活码
        logger.info("checkActiveCodeRet is valid {}",!response.isfailed());
        String userId = ps.get("userId").toString();
        String action = ps.get("action").toString();
        String activeCode=ps.get("activationCode").toString();
        String skuId = ps.get("skuId").toString();
        String orderId = ps.get("orderId").toString();
        String instanceId = ps.get("instanceId").toString();
        String expiredOn = ps.get("expiredOn").toString();
        String enterpriseId=ps.get("enterpriseId").toString();
        String resCode=ps.get("resCode").toString();
        logger.info("open app ....");
        
        //将订单信息保存到我们的数据库中
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
        params.set("userId", userId);
        if(ps.get("mobile") != null)params.set("mobile", ps.get("mobile").toString());
        if(ps.get("email") != null)params.set("email", ps.get("email").toString());
        params.set("action", action);
        params.set("activationCode", activeCode);
        params.set("skuId", skuId);
        params.set("orderId", orderId);
        params.set("instanceId", instanceId);
        params.set("expiredTime", expiredOn);
        params.set("enterpriseId", enterpriseId);
        params.set("resCode", resCode);
        String res = restTemplate.postForObject("http://utiexian-server/yonyoulog/save",params, String.class);
		System.out.println(res);
		
        String pushStatus = AppCenter.openResCallBack(activeCode,enterpriseId,resCode);//服务开通状态回写
        logger.info("pushStatus {}",pushStatus);
        logger.info("params is {}",ps);
        return pushStatus;
    }
    
    public String saveYonyouLog(MultiValueMap<String, Object> params){
    	String res = restTemplate.postForObject("http://utiexian-server/yonyoulog/save",params, String.class);
    	return res;
    }
}