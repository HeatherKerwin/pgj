package com.utiexian.third.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.utiexian.third.utils.sign.RSAUtils;
import com.utiexian.utils.MyException;

@Service
public class ParamsService {

	@Autowired
    RestTemplate restTemplate;
	
	/**
	 * 根据APPID获取商户配置
	 * @param appId 商户应用标识
	 * @return 返回商户配置的参数
	 */
	private @SuppressWarnings("unchecked") Map<String,Object>getThirdkeyByAppId(String appId){
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.set("appId", appId);
		String res = restTemplate.postForObject("http://utiexian-server/thirdkey/get/appid",params, String.class);
		Map<String,Object> map = JSON.parseObject(res);
		if(map==null || map.get("response")==null || map.get("data")==null || "failed".equals(map.get("response").toString())){
			if(map.get("msg")!=null){
				throw new MyException(map.get("msg").toString());
			}else{
				throw new MyException();
			}
		}
		Map<String,Object> thirdkey = (Map<String, Object>) map.get("data");
		return thirdkey;
	}
	
    /**
	 * 根据商户授权编号获取公钥
	 * @param appId 用户授权编号
	 */
	public String getPrivateKey(String appId){
		String key = "";
		Map<String,Object> thirdkey = this.getThirdkeyByAppId(appId);
		if(thirdkey.get("id")==null || thirdkey.get("privateKey")==null)throw new MyException("商户不存在！");
		
		key = thirdkey.get("privateKey").toString().trim();
		return key;
	}
	
	/**
	 * 根据商户授权编号获取公钥
	 * @param appId 用户授权编号
	 */
	public String getPublicKey(String appId){
		String key = "";
		Map<String,Object> thirdkey = this.getThirdkeyByAppId(appId);
		if(thirdkey.get("id")==null || thirdkey.get("publicKey")==null)throw new MyException("商户不存在！");
		
		key = thirdkey.get("publicKey").toString().trim();
		return key;
	}
	
	/**
	 * 参数解密
	 * @param appid 商户授权编号
	 * @param signature
	 * @param message
	 */
	public Map<String,Object> getParams(String appid,String signature,String message) throws Exception{
		Map<String,Object> params = new HashMap<String,Object>();
		
		if(StringUtils.isBlank(signature) || StringUtils.isBlank(message))throw new MyException("验签失败！");
		params.put("signature", signature);
		params.put("message", message);
		
		JSONObject json = new JSONObject(params);//JSON
		String body = json.toJSONString();
		
		String publicKey = this.getPublicKey(appid);//获取公钥
		String src = RSAUtils.decode(body,publicKey);//解密
		
		Map<String,Object> map = JSON.parseObject(src);
		return map;
	}
}