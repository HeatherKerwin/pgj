package com.utiexian.utils.cibfintech.econtract.until;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author jinchaolong
 * Created in 2017/10/19
 */
public class VerifyUtil {

	//加密方式
    public static final String KEY_ALGORITHM = "RSA";
    
    //数字签名 签名/验证算法
    private static final String SIGNATURE_ALGORRITHM = "SHA1WithRSA";

    //默认编码集
    private static final String CHARSET_ENCODER = "UTF-8";

    /**
     * 私钥签名
     * @param data 待签名的加密数据
     * @param privateKey 私钥
     * @return String 数字签名
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public static String sign(String data, String privateKey) throws Exception {
        return sign(data, privateKey, CHARSET_ENCODER);
    }

    public static String sign(String data, String privateKey, String input_charset) throws Exception {
        byte[] privateKeyByte = Base64.decode(privateKey);
        // 转接私钥材料
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKeyByte);
        // 实例化密钥工厂
        KeyFactory keyFactory = null;
        byte[] signbyte = null;
        try {
            keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            // 取私钥对象
            PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
            // 实例化Signature
            Signature signature = Signature.getInstance(SIGNATURE_ALGORRITHM);
            // 初始化Signature
            signature.initSign(priKey);
            // 更新
            // signature.update(Base64.decode(data));
            String charset = isEmpty(input_charset) ? CHARSET_ENCODER : input_charset;
            signature.update(data.getBytes(charset));
            // 签名
            signbyte = signature.sign();
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此加密算法");
        } catch (UnsupportedEncodingException e) {
            throw new Exception("不支持加密类型");
        } catch (SignatureException e) {
            throw new Exception("签章读取失败");
        } catch (InvalidKeyException e) {
            throw new Exception("公钥错误");
        } catch (InvalidKeySpecException e) {
            throw new Exception("公钥加密错误");
        }
        String signstr = Base64.encode(signbyte);
        return signstr;
    }

    private static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0 || str.equals("null");
    }

    public static String parseRequestData(String jsonData) {
        JSONObject jsonObject = JSONObject.parseObject(jsonData);
        jsonObject.remove("signVal");
        Map<String, String> map = new LinkedHashMap<String, String>(jsonObject.size());
        for (String key : jsonObject.keySet()) {
            if (jsonObject.get(key) instanceof JSONArray) {
                /**
                 * 对请求中的List数据key值排序
                 */
                map.put(key, sortJsonList((JSONArray) jsonObject.get(key)));
                continue;
            }
            if (!isEmpty(String.valueOf(jsonObject.get(key)))) {
                map.put(key, String.valueOf(jsonObject.get(key)));
            }
        }
        List<String> keys = new ArrayList<String>(map.keySet());
        Collections.sort(keys);
        String linkFormatRequestData = "";
        for (int i = 0; i < keys.size(); i++) {
            String str = String.valueOf(map.get(keys.get(i)));
            if (isEmpty(str)) {
                continue;
            }
            if (i < keys.size() - 1) {
                linkFormatRequestData += keys.get(i) + "=" + str + "&";
            } else {
                linkFormatRequestData += keys.get(i) + "=" + str;
            }
        }
        return linkFormatRequestData;
    }

    private static String sortJsonList(JSONArray jsonArray) {
        if (jsonArray.isEmpty()) {
            return null;
        }
        List<Object> jsonArrayValueList = new ArrayList<Object>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) (jsonArray).get(i);
            List<String> jsonObjectKeys = new ArrayList<String>(jsonObject.keySet());
            Collections.sort(jsonObjectKeys);
            Map<Object, Object> convertMap = new LinkedHashMap<Object, Object>(jsonObjectKeys.size());
            for (String k : jsonObjectKeys) {
                convertMap.put(k, jsonObject.get(k));
            }
            jsonArrayValueList.add(convertMap);
        }
        String ss = jsonArrayValueList.toString();
        return ss;
    }
}