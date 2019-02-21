package com.utiexian.third.utils.sign;

import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 这是一个完整的案例（考虑效率：签名建议对摘要签名）
 * 注意：公钥加密，私钥解密（私钥签名，公钥验签）
 * @author WKX
 */
public class RSATester {
    static String publicKey;
    static String privateKey;

    static {
        try {
            Map<String, Object> keyMap = RSAUtils.genKeyPair();
            publicKey = RSAUtils.getPublicKey(keyMap);
            privateKey = RSAUtils.getPrivateKey(keyMap);
            //System.err.println("公钥: \n" + publicKey);
            //System.err.println("私钥： \n" + privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String A_PublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDVU4gnEeL4guUqCx7bAyFlRxLhunlk/647UYA68ALOqV/yRBtINrPCAboXwVnv+Tvo5TTgW7Mu3+/9P6YvDkI+jtEWgYOLCMyeZuE4zmsFnuz1H4e++I5D6qrtMEUACadBKuRrw6WhbHFBhdy59tebpzJCaCaZpnHVyolEuBwBzwIDAQAB";
    static String A_PrivateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANVTiCcR4viC5SoLHtsDIWVHEuG6eWT/rjtRgDrwAs6pX/JEG0g2s8IBuhfBWe/5O+jlNOBbsy7f7/0/pi8OQj6O0RaBg4sIzJ5m4TjOawWe7PUfh774jkPqqu0wRQAJp0Eq5GvDpaFscUGF3Ln215unMkJoJpmmcdXKiUS4HAHPAgMBAAECgYA5mEnkMoITElQI2+PCwb+cdmn0o8HusNE7lq+GOhKn9TbAU/8BqjMyKAE0NLGxhprUMD3sbRWdjDxhJXQatEDR4HeuGRcYTbjih7BTGZWCmemM4ozFmBd0Eqw2FqTayRxE5C7c9d5Xbkrv/UClANxrvo7PSAJ1Dx+DZSpZa/NJAQJBAPu8H3qACCiBuUWFphiWVJIckC4D/no5jTuPUpNZcJKXiNP0mzaa33hWrMoO7BmbvXzni5bEFxzYFIQJCKIylg8CQQDY8NC0Wq0j7bdfpTRocjfuSLM+Z04urtGuCHdMWQGIYQf+rzZPPnof0kPTJXpGMcKh74m5I9F7YnM5ZX0wJZhBAkEAzW8h1Uuk3MG429aMzOXp0G+E7jiONVrAOknlXqCP8OG/ZRHqlO5gzokVa7Qv01o4kjU3wmr2XbCtVW8134Ae2wJAYTDaP8LUCOGqIraP2S86sY1uiEmd9DosBE0UrWa+0guumkKDEVOLQRBhhIoo0qPeb9AyInjSk/WSa6d1Q4nEQQJBAI7eGjmpsPCSltSLZEE3meIM+nGI7VQ27ugd5IKVEA8rZ5jJpkZLqBBoSrBui9aWMoJ4hHUDxh21WMvdJ2PqGEg=";
    
    static String B_PublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDD8eREb/7X8f3eEn5Y3wDWxovpt/ETDqTMkmp9SUEePA6xzsgyor7IImlMDLP6zrCK/9AVEig0+lHPE1wpIof/o61siaIf9iugQk0yEhsGgEfYyQrzu0dHTQ9MxKoEQSMrsMDpLmDL3ssN/bl4w3xO8kz0Oxtz0rWUBOXSycj5EwIDAQAB";
    static String B_PrivateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAMPx5ERv/tfx/d4SfljfANbGi+m38RMOpMySan1JQR48DrHOyDKivsgiaUwMs/rOsIr/0BUSKDT6Uc8TXCkih/+jrWyJoh/2K6BCTTISGwaAR9jJCvO7R0dND0zEqgRBIyuwwOkuYMveyw39uXjDfE7yTPQ7G3PStZQE5dLJyPkTAgMBAAECgYEArP3uTg2irPxU6a7iJYwcrYH1HI7tI6oWDRZnNd1qxEq5nnm1fh742LCozh+Y9qFsdAuNpky1erBVccDh6N83uQf4QRkGaSGh6dgpOn3cfYJq87QPfmMKmq4mvsQEePxo3EIHxz1/hrueR1fpEQJqY0Et/w8Kv04HTXA+TfqgCsECQQD+9p/iklcvTjWS1mohLyi+IUSJYgW3TTyFJECQjCEFkCMML/8NDGGHqs3MpQTrM2VKT1itnJTv2uMAMF5YyydrAkEAxL3WpW+FNMFeLnfZToeuZ1c9KWZExu6sJZ9HaJOglBvV4+OTZ6YgIwlfOWDeMZvWcC8E4s8jTYIHA7N/jTFm+QJBAM6zDH332/y68A3iDFpozCQP/mtOb4nicP0TndBR+ndzGibIRoLCldMR8xREeE9h9G0hFKhzN/+YOc4uaF6eHI8CQBhvq9VogJcI3XXQFQGYAKV9RG978Fb1FhSmL+xhSbSbsHlZrtlU3PD9HQK3cWGgBYZxkJ3LNItP9ycSiGk1FaECQQCu3GK65I4mzY0kHKjSn/FLciEptD35wzRum2jcblzI87RzhtHSQV87w0jZfypvTtuldFxAfXiGfC70RExsjdqp";
    public static void main(String[] args) throws Exception {
    	String source = "发送一条信息";
    	System.out.println("AsendB：" + source);
    	String send = AsendB("001",source,B_PublicKey,A_PrivateKey);
    	
    	String receive = BreceiveA(send,B_PrivateKey,A_PublicKey);
    	System.out.println("BreceiveA：" + receive);
	}
    
    /**
     * 数据发送端（加密并签名）
     * @param source 原始数据
     * @param B_PublicKey 接收方公钥加密
     * @param A_PrivateKey 发送方私钥签名
     */
    static String AsendB(String appid,String source,String B_PublicKey,String A_PrivateKey) throws Exception {
    	byte[] data = source.getBytes("UTF-8");
        //公钥加密
        byte[] encodedData = RSAUtils.encryptByPublicKey(data, B_PublicKey);
        String message = Base64.encodeBase64String(encodedData);//base64加密
        
        //私钥签名——公钥验证签名
        String sign = RSAUtils.sign(data, A_PrivateKey);
        
        String content = StringUtils.replace(RSAUtils.REQUEST_TEMPLATE, "$message$", message);
		content = StringUtils.replace(content, "$signature$", sign);
		content = StringUtils.replace(content, "$appid$", appid);
		return content;
    }
    
    /**
     * 数据接收端（解密并验签）
     * @param source 加密并且签名的数据
     * @param B_PrivateKey 接收方私钥解密
     * @param A_PublicKey 发送方公钥验签
     */
    static String BreceiveA(String source,String B_PrivateKey,String A_PublicKey) throws Exception {
    	JSONObject responseMsg = (JSONObject) JSON.parse(source);
		String respSignature = responseMsg.getString("signature");
		String respEncoded = responseMsg.getString("message");
		String appid = responseMsg.getString("appid");
		
		byte[] encodedData = Base64.decodeBase64(respEncoded);//base64解密
		
    	//私钥解密
        byte[] decodedData = RSAUtils.decryptByPrivateKey(encodedData, B_PrivateKey);
        String target = new String(decodedData);
        
        System.err.println(appid);
        
        //私钥签名——公钥验证签名
		boolean status = RSAUtils.verify(decodedData, A_PublicKey, respSignature);
		if(!status)throw new Exception("验证签名失败！");
        return target;
    }
}