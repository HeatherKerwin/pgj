package com.utiexian.third.utils.sign;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.net.ssl.SSLException;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.utiexian.utils.MyException;

public class RSAUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(RSAUtils.class);

	public static final String REQUEST_TEMPLATE = "{\"message\":\"$message$\",\"signature\":\"$signature$\",\"appid\":\"$appid$\"}";

	/**
	 * 加密算法RSA
	 */
	public static final String KEY_ALGORITHM = "RSA";

	/**
	 * 签名算法
	 */
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

	/**
	 * 获取公钥的key
	 */
	private static final String PUBLIC_KEY = "RSAPublicKey";

	/**
	 * 获取私钥的key
	 */
	private static final String PRIVATE_KEY = "RSAPrivateKey";

	/**
	 * RSA最大加密明文大小
	 */
	private static final int MAX_ENCRYPT_BLOCK = 117;

	/**
	 * RSA最大解密密文大小
	 */
	private static final int MAX_DECRYPT_BLOCK = 128;

	/**
	 * 生成密钥对(公钥和私钥)
	 */
	public static Map<String, Object> genKeyPair() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		keyPairGen.initialize(1024);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		return keyMap;
	}

	/**
	 * 用私钥对信息生成数字签名
	 * @param data 已加密数据
	 * @param privateKey 私钥(BASE64编码)
	 */
	public static String sign(byte[] data, String privateKey) throws Exception {
		byte[] keyBytes = Base64.decode(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initSign(privateK);
		signature.update(data);
		return Base64.encode(signature.sign());
	}

	/**
	 * 校验数字签名
	 * @param data 已加密数据
	 * @param publicKey 公钥(BASE64编码)
	 * @param sign 数字签名
	 */
	public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
		byte[] keyBytes = Base64.decode(publicKey);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PublicKey publicK = keyFactory.generatePublic(keySpec);
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initVerify(publicK);
		signature.update(data);
		return signature.verify(Base64.decode(sign));
	}

	/**
	 * 私钥解密
	 * @param encryptedData 已加密数据
	 * @param privateKey 私钥(BASE64编码)
	 */
	public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey) throws Exception {
		byte[] keyBytes = Base64.decode(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateK);
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;

		while (inputLen - offSet > 0) {//对数据分段解密
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

	/**
	 * 公钥解密
	 * @param encryptedData 已加密数据
	 * @param publicKey 公钥(BASE64编码)
	 */
	public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey) throws Exception {
		byte[] keyBytes = Base64.decode(publicKey);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicK = keyFactory.generatePublic(x509KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicK);
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		
		while (inputLen - offSet > 0) {//对数据分段解密
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

	/**
	 * 公钥加密
	 * @param data 源数据
	 * @param publicKey 公钥(BASE64编码)
	 */
	public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
		byte[] keyBytes = Base64.decode(publicKey);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicK = keyFactory.generatePublic(x509KeySpec);

		//对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicK);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;

		//对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	/**
	 * 私钥加密
	 * @param data 源数据
	 * @param privateKey 私钥(BASE64编码)
	 */
	public static byte[] encryptByPrivateKey(byte[] data, String privateKey) throws Exception {
		byte[] keyBytes = Base64.decode(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateK);
		
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		int offSet = 0;
		byte[] cache;
		int i = 0;

		//对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	/**
	 * 获取私钥
	 * @param keyMap 密钥对
	 */
	public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		return Base64.encode(key.getEncoded());
	}

	/**
	 * 获取公钥
	 * @param keyMap 密钥对
	 */
	public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key) keyMap.get(PUBLIC_KEY);
		return Base64.encode(key.getEncoded());
	}

	/** ====================================================以下是加密解密================================================== */

	/**
	 * 数据加密
	 * @param source 原始数据
	 * @param appid APPID
	 * @param privateKey 私钥
	 */
	public static String encode(String source,String appid,String privateKey) throws Exception {
		byte[] data = source.getBytes();

		//私钥加密
		byte[] encodedData = RSAUtils.encryptByPrivateKey(data, privateKey);

		String sign = RSAUtils.sign(encodedData, privateKey);

		String content = StringUtils.replace(RSAUtils.REQUEST_TEMPLATE, "$message$", org.apache.commons.codec.binary.Base64.encodeBase64String(encodedData));
		content = StringUtils.replace(content, "$signature$", sign);
		content = StringUtils.replace(content, "$appid$", appid);
		return content;
	}

	/**
	 * 数据解密
	 * @param source 原始数据
	 * @param publicKey 公钥
	 */
	public static String decode(String source, String publicKey) throws Exception {
		JSONObject responseMsg = (JSONObject) JSON.parse(source);
		
		String respSignature = responseMsg.getString("signature");//验签
		String respEncoded = responseMsg.getString("message");

		byte[] encodedData = org.apache.commons.codec.binary.Base64.decodeBase64(respEncoded);
		boolean status = RSAUtils.verify(encodedData, publicKey, respSignature);//校验数字签名
		if(!status)throw new MyException("校验数字签名失败！");
		
		byte[] decodedData = RSAUtils.decryptByPublicKey(encodedData, publicKey);
		String target = new String(decodedData);
		return target;
	}
	
	/**
	 * POST请求
	 * @param url 请求路径
	 * @param appId 企业
	 * @param privateKey 私钥
	 * @param publicKey 公钥
	 * @param params 参数
	 */
	public static String sendPost(String url,String appId,String privateKey,String publicKey,Map<String,Object> pp) throws Exception {
    	String result = null;
		try {
			CloseableHttpClient client = HttpClients.createDefault();//实例化一个Httpclient的
			HttpPost post = new HttpPost(url);//实例化一个post请求
			
			JSONObject json = new JSONObject(pp);//JSON
			String body = json.toJSONString();
			
			logger.info("*********发送参数**********");
			logger.info("参数：" + body);
			
			String src = RSAUtils.encode(body,appId,privateKey);//加密
			Map<String,Object> params = JSON.parseObject(src);
			
			//设置需要提交的参数
			List<NameValuePair> list  = new ArrayList<NameValuePair>();
			if(params != null){
				for (String temp : params.keySet()) {
					list.add(new BasicNameValuePair(temp,params.get(temp).toString()));
				}
			}
			post.setEntity(new UrlEncodedFormEntity(list,"UTF-8"));
			HttpResponse response = client.execute(post);//实行请求并返回
			HttpEntity entity = response.getEntity();
			
			result = EntityUtils.toString(entity);
			result = RSAUtils.decode(result, publicKey);
			
			logger.info("*********返回结果**********");
			logger.info("结果：" + result);
			return result;
		}catch (Exception e) {
			if(e instanceof InterruptedIOException){
				logger.error("连接服务器超时");
			}else if (e instanceof UnknownHostException){
				logger.error("UnknownHost");
			}else if (e instanceof ConnectException){
				logger.error("服务器拒绝连接");
			}else if (e instanceof SSLException){
				logger.error("SSL协议错误");
			}else if(e instanceof IOException){
				logger.error("连接错误");
			}else{
				logger.error("系统错误");
			}
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
}