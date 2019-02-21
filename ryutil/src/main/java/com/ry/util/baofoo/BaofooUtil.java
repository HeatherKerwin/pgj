package com.ry.util.baofoo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.ry.util.baofoo.rsa.RsaCodingUtil;
import com.ry.util.baofoo.util.HttpUtil;
import com.ry.util.baofoo.util.JXMConvertUtil;
import com.ry.util.baofoo.util.MapToXMLString;
import com.ry.util.baofoo.util.SecurityUtil;

import net.sf.json.JSONObject;

public class BaofooUtil {
    
	private static final String notice_type = "1";//通知类型（0：服务器通知，1：页面跳转+服务器通知）
	
	private static final String md5_key = "MIICTTCCAbagAwIBAgIGAVqOHuABMA0GCSqGSIb3DQEBDQUAMGoxFzAVBgNVBAMMDjExMzU1OTNAQDMzNjExMREwDwYDVQQHDAhzaGFuZ2hhaTERMA8GA1UECAwIU2hhbmdIYWkxCzAJBgNVBAYTAkNOMQswCQYDVQQKDAJiZjEPMA0GA1UECwwGYmFvZm9vMB4XDTE3MDMwMjA4MjQzMloXDTIyMDMwMjA4MjQzMlowajEXMBUGA1UEAwwOMTEzNTU5M0BAMzM2MTExETAPBgNVBAcMCHNoYW5naGFpMREwDwYDVQQIDAhTaGFuZ0hhaTELMAkGA1UEBhMCQ04xCzAJBgNVBAoMAmJmMQ8wDQYDVQQLDAZiYW9mb28wgZ8wDQYJKoZIhvcNAQEBBQADgY0AMIGJAoGBALYQ6CgOzHcyfJMyEHdwYBzHLz2y/+8zo2zim1dApOejuaHxjDqID5qAThLXTmDgs21dnxXAISkt/qtiNbiqu+AvNnV5s5OO9atPP+3fFRlrUiU2OP/U36bQANk/Wv2a871186Y8yrIHSPrQt38qQ7OvLS1KNtvEpS6esqkqZ3vrAgMBAAEwDQYJKoZIhvcNAQENBQADgYEAf1f3nuIzo5Lv+L6f+5ZLEWkPtedKdolNkLC9P/NZp2pER2R+JIqyOdrUQt3ih91l/CQ7pbLNK2emTmhrq0GKKFu/brhybfjQzPEucVfpo3lsKIg/BTUpoMijatJv9Bd+tK5Edyy94YHxABNBT/UVg2DXBWy9jcdPl60NKVsNJcU=";//商户密钥
	public static final String terminal_id = "33611";//终端号
    public static final String member_id = "1135593";//商户号
    public static final String pfx_pwd = "5096";
    public static final String pfxFlieName = "piaoguanjia.pfx";//商户私钥
    public static final String cerFlieName = "piaoguanjia.cer";//宝付公钥
    
//    private static final String md5_key = "abcdefg";//商户密钥（lt377e2srktj8d6u）
//    public static final String terminal_id = "100000916";//终端号（33612）
//    public static final String member_id = "100000178";//商户号（1135593）
//	private static final String pfx_pwd = "100000178_204500";
//	private static final String pfxFlieName = "bfkey.pfx";//商户私钥
//	private static final String cerFlieName = "bfkey.cer";//宝付公钥
	
    /**
	 * 发送报文 响应数据
	 * @param HeadPostParam  
	 * @param XMLArray
	 * @param request_url
	 * @throws Exception
	 */
	private static String Send(Map<String, String> HeadPostParam, Map<String, Object> XMLArray, String request_url, String pfxpath) throws IOException {
		Map<Object, Object> ArrayToObj = new HashMap<Object, Object>();

		String XmlOrJson = "";
		if (HeadPostParam.get("data_type").equals("xml")) {
			ArrayToObj.putAll(XMLArray);
			XmlOrJson = MapToXMLString.converter(ArrayToObj, "data_content");
		} else {
			JSONObject jsonObjectFromMap = JSONObject.fromObject(XMLArray);
			XmlOrJson = jsonObjectFromMap.toString();
		}
		String base64str = SecurityUtil.Base64Encode(XmlOrJson);
		String data_content = RsaCodingUtil.encryptByPriPfxFileBuffer(base64str, pfxpath,pfx_pwd);
		HeadPostParam.put("data_content", data_content);// 加密数据
		String PostString = HttpUtil.RequestForm(request_url, HeadPostParam);
		return PostString;

	}

	/**
	 * 解析参数中的键值对 ret_code=BF00121&ret_msg=报文交易要素格式错误:报文内容不能为空&data_content=
	 * @param strUrlParam
	 * @return
	 */
	private static Map<String, String> ResponseValue(String strUrlParam) {
		Map<String, String> mapRequest = new HashMap<String, String>();
		String[] arrSplit = strUrlParam.split("[&]");
		for (String strSplit : arrSplit) {
			String[] arrSplitEqual = null;
			arrSplitEqual = strSplit.split("[=]");
			if (arrSplitEqual.length > 1) {//解析出键值
				mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);//正确解析
			} else {
				if (arrSplitEqual[0] != "") {
					mapRequest.put(arrSplitEqual[0], "");//只有参数没有值，不加入
				}
			}
		}
		return mapRequest;
	}

	/**
	 * 当ret_code=0000时,解析密文
	 * @param HeadPostParam
	 * @param PostStr
	 * @param cerpath
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	private static Map<String, Object> Decryption(Map<String, String> HeadPostParam, String PostStr, String cerpath) throws IOException {
		String PostString = RsaCodingUtil.decryptByPubCerFileBuffer(PostStr, cerpath);
		if (StringUtils.isBlank(PostString)) {// 判断解密是否正确。如果为空则宝付公钥不正确
			new RuntimeException("检查解密公钥是否正确");
		}
		PostString = SecurityUtil.Base64Decode(PostString);

		if (HeadPostParam.get("data_type").equals("xml")) {
			PostString = JXMConvertUtil.XmlConvertJson(PostString);
		}
		Map<String, Object> ArrayDataString = JXMConvertUtil.JsonConvertHashMap(PostString);// 将JSON转化为Map对象。
		return ArrayDataString;
	}
    
    /**
     * 支付（手机版）
     * bnsNo:订单号
     * OrderMoney:订单金额
     * product:产品
     * memberName:用户名
     * info:订单附加信息
     * pageUrl:页面跳转地址
     * returnUrl:服务器底层通知地址(该地址要在外网能被访问)
     * @author WKX
     * @since 2017年4月13日 下午3:41:04
     */
    public static String buildRequestPayForWap(Map<String, String> param) {
    	String PayID = "all";//支付渠道（宝付收银台）
		String OrderMoney = param.get("OrderMoney");//订单金额
		if (StringUtils.isNotBlank(OrderMoney)){
			BigDecimal a = new BigDecimal(OrderMoney).multiply(BigDecimal.valueOf(100)); //使用分进行提交
			OrderMoney=String.valueOf(a.setScale(0));
		}else{
			OrderMoney = ("0");
		}
		
    	String TransID = param.get("bnsNo");//商户订单号（不能重复）
		String TradeDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());//下单日期		
		String MemberID = member_id;//商户号
		String TerminalID = terminal_id;//终端号
		String ProductName = param.get("product");//商品名称
		String Amount = "1";//商品数量
		String Username = param.get("memberName");//支付用户名称
		String AdditionalInfo = param.get("info");//订单附加信息
		String PageUrl = param.get("pageUrl");//页面跳转地址
		String ReturnUrl = param.get("returnUrl");//服务器底层通知地址(该地址要在外网能被访问)
		String NoticeType = notice_type;//通知类型	
		String Md5key = md5_key;//md5密钥（KEY）
		String MARK = "|";
    	
    	String md5 = new String(MemberID+MARK+PayID+MARK+TradeDate+MARK+TransID+MARK+OrderMoney+MARK+PageUrl+MARK+ReturnUrl+MARK+NoticeType+MARK+Md5key);//MD5签名格式
    	String Signature = SecurityUtil.MD5(md5);//计算MD5值
    	String payUrl="https://vgw.baofoo.com/wapmobile";//测试请求地址
    	//String payUrl="https://gw.baofoo.com/wapmobile";//正试请求地址
    	
    	String InterfaceVersion = "4.0";
		String KeyType = "1";//加密类型(固定值为1)
    	String FormString = "<body onload=\"pay.submit()\">"+
				"努力加载中..."+
				"<form method=\"post\" name=\"pay\" id=\"pay\" action=\""+payUrl+"\">"+
				"<input name=\"MemberID\" type=\"hidden\" value=\""+MemberID+"\"/>"+
				"<input name=\"TerminalID\" type=\"hidden\" value=\""+TerminalID+"\"/>"+
				"<input name=\"InterfaceVersion\" type=\"hidden\" value= \""+InterfaceVersion+"\"/>"+
				"<input name=\"KeyType\" type=\"hidden\" value= \""+KeyType+"\"/>"+
				"<input name=\"PayID\" type=\"hidden\" value= \""+PayID+"\"/>"+	
				"<input name=\"TradeDate\" type=\"hidden\" value= \""+TradeDate+"\" />"+
				"<input name=\"TransID\" type=\"hidden\" value= \""+TransID+"\" />"+
				"<input name=\"OrderMoney\" type=\"hidden\" value= \""+OrderMoney+"\"/>"+
				"<input name=\"ProductName\" type=\"hidden\" value= \""+ProductName+"\"/>"+
				"<input name=\"Amount\" type=\"hidden\" value= \""+Amount+"\"/>"+
				"<input name=\"Username\" type=\"hidden\" value= \""+Username+"\"/>"+
				"<input name=\"AdditionalInfo\" type=\"hidden\" value= \""+AdditionalInfo+"\"/>"+
				"<input name=\"PageUrl\" type=\"hidden\" value= \""+PageUrl+"\"/>"+
				"<input name=\"ReturnUrl\" type=\"hidden\" value= \""+ReturnUrl+"\"/>"+
				"<input name=\"Signature\" type=\"hidden\" value=\""+Signature+"\"/>"+
				"<input name=\"NoticeType\" type=\"hidden\" value= \""+NoticeType+"\"/>"+
				"</form></body>";
    	return FormString;
    }
    
    /**
     * 支付（电脑版）
     * bnsNo:订单号
     * OrderMoney:订单金额
     * product:产品
     * memberName:用户名
     * info:订单附加信息
     * pageUrl:页面跳转地址
     * returnUrl:服务器底层通知地址(该地址要在外网能被访问)
     * @author WKX
     * @since 2017年4月13日 下午3:41:04
     */
    public static String buildRequestPay(Map<String, String> param) {
    	String PayID = "";//支付渠道（宝付收银台）
    	String OrderMoney = param.get("OrderMoney");//订单金额
		if (StringUtils.isNotBlank(OrderMoney)){	
			BigDecimal a = new BigDecimal(OrderMoney).multiply(BigDecimal.valueOf(100)); //使用分进行提交
			OrderMoney=String.valueOf(a.setScale(0));
		}else{
			OrderMoney = ("0");
		}
		if(PayID.isEmpty()){//PayID传空跳转宝付收银台，传功能ID跳转对应的银行		
			PayID = "";
		}
		
		String TransID = param.get("bnsNo");//商户订单号（不能重复）
		String TradeDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());//下单日期		
		String MemberID = member_id;//商户号
		String TerminalID = terminal_id;//终端号
		String ProductName = param.get("product");//商品名称
		String Amount = "1";//商品数量
		String Username = param.get("memberName");//支付用户名称
		String AdditionalInfo = param.get("info");//订单附加信息
		String PageUrl = param.get("pageUrl");//页面跳转地址
		String ReturnUrl = param.get("returnUrl");//服务器底层通知地址(该地址要在外网能被访问)
		String NoticeType = notice_type;//通知类型
		String Md5key = md5_key;//md5密钥（KEY）
		String MARK = "|";
		
		String md5 =new String(MemberID+MARK+PayID+MARK+TradeDate+MARK+TransID+MARK+OrderMoney+MARK+PageUrl+MARK+ReturnUrl+MARK+NoticeType+MARK+Md5key);//MD5签名格式
		String Signature = SecurityUtil.MD5(md5);//计算MD5值
		String payUrl="https://vgw.baofoo.com/payindex";//请求地址
		//String payUrl="https://gw.baofoo.com/payindex";//请求地址
		
		String InterfaceVersion = "4.0";
		String KeyType = "1";//加密类型(固定值为1)
		String FormString = "<body onload=\"pay.submit()\">"+
				"努力加载中..."+
				"<form method=\"post\" name=\"pay\" id=\"pay\" action=\""+payUrl+"\">"+
				"<input name=\"MemberID\" type=\"hidden\" value=\""+MemberID+"\"/>"+
				"<input name=\"TerminalID\" type=\"hidden\" value=\""+TerminalID+"\"/>"+
				"<input name=\"InterfaceVersion\" type=\"hidden\" value= \""+InterfaceVersion+"\"/>"+
				"<input name=\"KeyType\" type=\"hidden\" value= \""+KeyType+"\"/>"+
				"<input name=\"PayID\" type=\"hidden\" value= \""+PayID+"\"/>"+	
				"<input name=\"TradeDate\" type=\"hidden\" value= \""+TradeDate+"\" />"+
				"<input name=\"TransID\" type=\"hidden\" value= \""+TransID+"\" />"+
				"<input name=\"OrderMoney\" type=\"hidden\" value= \""+OrderMoney+"\"/>"+
				"<input name=\"ProductName\" type=\"hidden\" value= \""+ProductName+"\"/>"+
				"<input name=\"Amount\" type=\"hidden\" value= \""+Amount+"\"/>"+
				"<input name=\"Username\" type=\"hidden\" value= \""+Username+"\"/>"+
				"<input name=\"AdditionalInfo\" type=\"hidden\" value= \""+AdditionalInfo+"\"/>"+
				"<input name=\"PageUrl\" type=\"hidden\" value= \""+PageUrl+"\"/>"+
				"<input name=\"ReturnUrl\" type=\"hidden\" value= \""+ReturnUrl+"\"/>"+
				"<input name=\"Signature\" type=\"hidden\" value=\""+Signature+"\"/>"+
				"<input name=\"NoticeType\" type=\"hidden\" value= \""+NoticeType+"\"/>"+
				"</form></body>";
		return FormString;
    }
    
    /**
     * 快捷支付接口（1快捷支付--预绑卡、2快捷支付--确定绑卡、3快捷支付--解除银行卡绑定、4快捷支付--绑定结果查询、5快捷支付--预支付、6快捷支付--支付、7快捷支付--交易状态查询）
     * @author WKX
     * @param param 参数
     * @throws Exception
     * @since 2017年4月14日 下午4:09:30
     */
    public static String Api(Map<String, String> param) throws Exception{
    	String type = param.get("type");//接口
    	
    	//请求报文----------------
		String data_type = "xml";
		Map<String, String> HeadPostParam = new HashMap<String, String>();
		HeadPostParam.put("version", "4.0.0.0");//版本号
		HeadPostParam.put("input_charset", "1");//字符集
		HeadPostParam.put("terminal_id", terminal_id);//终端号
		HeadPostParam.put("member_id", member_id);//商户号
		HeadPostParam.put("data_type", data_type);//加密数据类型
		String user_id = "UTIEXIAN"+param.get("user_id");//用户 ID

		//加密数据
		String trade_date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());// 交易日期
		Map<String, Object> XMLArray = new HashMap<String, Object>();
		XMLArray.put("terminal_id", terminal_id);// 终端号
		XMLArray.put("member_id", member_id);// 商户号
		XMLArray.put("additional_info", "附加字段");// 附加字段
		XMLArray.put("req_reserved", "请求方保留域");// 请求方保留域
		XMLArray.put("trans_serial_no", "KJAPI" + System.currentTimeMillis());//商户流水号
		
		//请求数据
		String request_url = "";// 请求地址
		String PostString = "";
		switch (type) {
		case "1":
			//String pay_code = param.get("pay_code");// 银行卡编码
			String acc_no = param.get("acc_no");// 银行卡卡号
			String id_card = param.get("id_card");// 身份证号码
			String card_holder = param.get("id_holder");// 姓名
			String mobile = param.get("mobile");// 银行预留手机号
			
			//信用卡字段
			String valid_date = "";
			String cvv = "";
			if(StringUtils.isNotBlank(param.get("valid_date")))valid_date = param.get("valid_date");
			if(StringUtils.isNotBlank(param.get("cvv")))cvv = param.get("cvv");
			
			String card_type = "101";
			if(StringUtils.isNotBlank(cvv))card_type = "102";//信用卡
			
			XMLArray.put("acc_no", acc_no);//银行卡号
			XMLArray.put("card_type", card_type);//卡类型 借记卡 101,信用卡 102
			XMLArray.put("card_holder", card_holder);//持卡人姓名
			XMLArray.put("id_card_type", "01");//证件类型 01 身份证
			XMLArray.put("id_card", id_card);//证件号码
			XMLArray.put("mobile", mobile);//银行卡预留手 机号
			XMLArray.put("valid_date", valid_date);//银行卡有效期（信用卡）
			XMLArray.put("cvv", cvv);//银行卡安全码
			XMLArray.put("user_id", user_id);//用户 ID
			XMLArray.put("trade_date", trade_date);//交易日期
			request_url = "https://gw.baofoo.com/quickpay/api/preparebind";
			PostString = Send(HeadPostParam, XMLArray, request_url, pfxFlieName);
			// 解析返回参数
			Map<String, String> ResponseMap = ResponseValue(PostString);
			if ("0000".equals(ResponseMap.get("ret_code"))) {
				Map<String, Object> map = Decryption(HeadPostParam, ResponseMap.get("data_content"), cerFlieName);
				return "{\"resp_code\":\"" + map.get("resp_code") + "\",\"resp_msg\":\"" + map.get("resp_msg") + "\"" + ",\"unique_code\":\"" + map.get("unique_code") + "\"}";
			} else {
				String res = "{\"resp_code\":\"" + ResponseMap.get("ret_code") + "\",\"resp_msg\":\"" + ResponseMap.get("ret_msg") + "\"}";
				return getStringByIso(res);
			}
		case "2":
			String sms_code = param.get("sms_code");//短信验证码
			String unique_code = param.get("unique_code");//预绑卡唯一码
			XMLArray.put("trade_date", trade_date);//交易日期
			XMLArray.put("sms_code", sms_code);//短信验证码
			XMLArray.put("unique_code", unique_code);//预绑卡唯一码
			request_url = "https://gw.baofoo.com/quickpay/api/bindcard";
			PostString = Send(HeadPostParam, XMLArray, request_url, pfxFlieName);
			//解析返回参数
			Map<String, String> ResponseMap2 = ResponseValue(PostString);
			if ("0000".equals(ResponseMap2.get("ret_code"))) {
				Map<String, Object> map = Decryption(HeadPostParam, ResponseMap2.get("data_content"), cerFlieName);
				return "{\"resp_code\":\"" + map.get("resp_code") + "\",\"resp_msg\":\"" + map.get("resp_msg") + "\"" + ",\"user_id\":\"" + map.get("user_id") + "\"" + ",\"bind_id\":\"" + map.get("bind_id") + "\"}";
			} else {
				String res = "{\"resp_code\":\"" + ResponseMap2.get("ret_code") + "\",\"resp_msg\":\"" + ResponseMap2.get("ret_msg") + "\"}";
				return getStringByIso(res);
			}
		case "3":
			String bind_id = param.get("bind_id");//预绑卡唯一码
			XMLArray.put("user_id", user_id);//用户 ID
			XMLArray.put("bind_id", bind_id);//绑定ID
			request_url = "https://gw.baofoo.com/quickpay/api/removecard";
			PostString = Send(HeadPostParam, XMLArray, request_url, pfxFlieName);
			//解析返回参数
			Map<String, String> ResponseMap3 = ResponseValue(PostString);
			if ("0000".equals(ResponseMap3.get("ret_code"))) {
				Map<String, Object> map = Decryption(HeadPostParam, ResponseMap3.get("data_content"), cerFlieName);
				return "{\"resp_code\":\"" + map.get("resp_code") + "\"," + "\"resp_msg\":\"" + map.get("resp_msg") + "\"}";
			} else {
				String res = "{\"resp_code\":\"" + ResponseMap3.get("ret_code") + "\",\"resp_msg\":\"" + ResponseMap3.get("ret_msg") + "\"}";
				return getStringByIso(res);
			}
		case "4":
			XMLArray.put("user_id", user_id);
			request_url = "https://gw.baofoo.com/quickpay/api/querybind";
			PostString = Send(HeadPostParam, XMLArray, request_url, pfxFlieName);
			//解析返回参数
			Map<String, String> ResponseMap4 = ResponseValue(PostString);
			if ("0000".equals(ResponseMap4.get("ret_code"))) {
				Map<String, Object> temp = Decryption(HeadPostParam, ResponseMap4.get("data_content"), cerFlieName);
				Map<String, Object> map = getCards(temp);
				return "{\"resp_code\":\"" + map.get("resp_code") + "\"" + ",\"resp_msg\":\"" + map.get("resp_msg") + "\"," + "\"card\":\"" + map.get("card") + "\"}";
			} else {
				String res = "{\"resp_code\":\"" + ResponseMap4.get("ret_code") + "\",\"resp_msg\":\"" + ResponseMap4.get("ret_msg") + "\"}";
				return getStringByIso(res);
			}
		case "5":
			String bind_id2 = param.get("bind_id");//绑定 ID
			String txn_amt = param.get("txn_amt");//金额
			String trans_id = param.get("trans_id");//商户订单号
			String device_id = param.get("device_id");//设备指纹
			String trade_date2 = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());//订单日期
			
			//String risk_item = "";//风控参数
			BigDecimal txn_amt_num = new BigDecimal(txn_amt).multiply(BigDecimal.valueOf(100));//金额转换成分
    		txn_amt = String.valueOf(txn_amt_num.setScale(0));//支付金额	
			XMLArray.put("bind_id",bind_id2);
			XMLArray.put("user_id",user_id);
			XMLArray.put("trans_id",trans_id);
			XMLArray.put("txn_amt",txn_amt);
			XMLArray.put("trade_date",trade_date2);
			
			//风控基本参数
			Map<String,String> RiskItem = new HashMap<String,String>();
			 /*--------风控基础参数------------- */
			RiskItem.put("goods_category", "6001");//商品类目（票据类）
			RiskItem.put("user_no", user_id);//用户在商户系统中的标识,如user_id
			RiskItem.put("user_type", "1");
			RiskItem.put("register_time", trade_date2);//用户在平台的注册时间
			
			RiskItem.put("register_user_name", "张三");
			RiskItem.put("trans_ip", "192.168.1.110");
			RiskItem.put("identify_state", "1");
			RiskItem.put("identify_type", "1");
			
			RiskItem.put("device_no", trans_id);//设备指纹关联订单号
			RiskItem.put("device_id", device_id);//设备指纹

			XMLArray.put("risk_item",JSONObject.fromObject(RiskItem).toString());
			request_url = "https://gw.baofoo.com/quickpay/api/prepareorder";
			PostString = Send(HeadPostParam, XMLArray, request_url, pfxFlieName);
			// 解析返回参数
			Map<String, String> ResponseMap5 = ResponseValue(PostString);
			if ("0000".equals(ResponseMap5.get("ret_code"))) {
				Map<String, Object> map = Decryption(HeadPostParam, ResponseMap5.get("data_content"), cerFlieName);
				return "{\"resp_code\":\"" + map.get("resp_code") + "\",\"resp_msg\":\"" + map.get("resp_msg") + "\"" + ",\"business_no\":\"" + map.get("business_no") + "\"}";
			} else {
				String res = "{\"resp_code\":\"" + ResponseMap5.get("ret_code") + "\",\"resp_msg\":\"" + ResponseMap5.get("ret_msg") + "\"}";
				return getStringByIso(res);
			}		
		case "6":
			String business_no = param.get("business_no");//宝付业务流水号
			String sms_code2 = param.get("sms_code");//用户ID
		
			XMLArray.put("business_no",business_no);
			XMLArray.put("sms_code",sms_code2);
			request_url = "https://gw.baofoo.com/quickpay/api/confirmorder";
			PostString = Send(HeadPostParam, XMLArray, request_url, pfxFlieName);
			//解析返回参数
			Map<String, String> ResponseMap6 = ResponseValue(PostString);
			if ("0000".equals(ResponseMap6.get("ret_code"))) {
				Map<String, Object> map = Decryption(HeadPostParam, ResponseMap6.get("data_content"), cerFlieName);
				return "{\"resp_code\":\"" + map.get("resp_code") + "\",\"resp_msg\":\"" + map.get("resp_msg") + "\",\"trans_id\":\"" + map.get("trans_id") + "\",\"succ_amt\":\"" + map.get("succ_amt") + "\",\"trans_no\":\"" + map.get("trans_no") + "\"}";
			} else {
				String res = "{\"resp_code\":\"" + ResponseMap6.get("ret_code") + "\",\"resp_msg\":\"" + ResponseMap6.get("ret_msg") + "\"}";
				return getStringByIso(res);
			}
		case "7":
			String orig_trans_id = param.get("orig_trans_id");//绑定ID
			String trade_date3 = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());//订单日期
		
			XMLArray.put("orig_trans_id",orig_trans_id);
			XMLArray.put("trade_date",trade_date3);
			request_url = "https://gw.baofoo.com/quickpay/api/queryorder";
			PostString = Send(HeadPostParam, XMLArray, request_url, pfxFlieName);
			//解析返回参数
			Map<String, String> ResponseMap7 = ResponseValue(PostString);
			if ("0000".equals(ResponseMap7.get("ret_code"))) {
				Map<String, Object> map = Decryption(HeadPostParam, ResponseMap7.get("data_content"), cerFlieName);
				return "{\"resp_code\":\"" + map.get("resp_code") + "\"," + "\"resp_msg\":\"" + map.get("resp_msg") + "\"}";
			} else {
				String res = "{\"resp_code\":\"" + ResponseMap7.get("ret_code") + "\",\"resp_msg\":\"" + ResponseMap7.get("ret_msg") + "\"}";
				return getStringByIso(res);
			}	
		default:
			return "";
		}
    }
    
    /**
     * 处理乱码
     * @author WKX
     * @param src 原始字符
     * @throws Exception
     * @since 2017年4月21日 下午3:51:07
     */
    private static String getStringByIso(String src) throws Exception{
    	return new String(src.getBytes("ISO-8859-1"),"UTF-8");
    }
    
    /**
     * 获取已绑定银行卡信息（一张卡时和多张卡返回结果不同，需要重新组装数据）
     * @author WKX
     * @param map 原始获取到的数据
     * @throws Exception
     * @since 2017年4月21日 下午5:19:35
     */
    private static Map<String, Object> getCards(Map<String, Object> map) throws Exception{
    	if(map.get("card")!=null){
			return map;
		}else if(map.get("bind_id")!=null){
			List<Map<String,Object>> cards = new ArrayList<Map<String,Object>>();
			Map<String, Object> card = new HashMap<String, Object>();
			card.put("bank_name", map.get("bank_name"));
			card.put("bind_id", map.get("bind_id"));
			card.put("card_no", map.get("card_no"));
			card.put("bank_code", map.get("bank_code"));
			card.put("card_type", map.get("card_type"));
			
			cards.add(card);
			map.put("card", cards);
			return map;
		}
    	return map;
    }
    
    /**
     * 退款接口（返回的结果是受理结果，还需要3天审核才确定是否退款成功）
     * @author WKX
     * param:bnsNo 原商户发起的支付订单号
     * param:money 退款金额
     * param:returnUrl 退款结果回调链接
     * param:reason 退款原因
     * @since 2017年5月8日 下午3:12:11
     */
    public static String RefundFrom(Map<String, String> param) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException{
		String  trans_id = param.get("bnsNo");//原商户发起的支付订单号（不能重复）
		String  refund_type = "2";//退款类型（1:宝付收银台2:认证支付、代扣、快捷支付）
		
		String refund_amt = param.get("money");//退款金额
		BigDecimal txn_amt_num = new BigDecimal(refund_amt).multiply(BigDecimal.valueOf(100));//金额转换成分		
		String txn_amt = String.valueOf(txn_amt_num.setScale(0));//支付金额
		
		Map<String,String> Headparm = new HashMap<String,String>();
		//=================================================		
		Headparm.put("version", "4.0.0.0");//版本号
		Headparm.put("terminal_id", terminal_id);//终端号
		Headparm.put("txn_type", "331");
		Headparm.put("txn_sub_type", "09");
		Headparm.put("member_id", member_id);//商户号
		Headparm.put("data_type", "xml");//加密报文的数据类型（xml/json）
		Headparm.put("risk_item", "");//风险控制
		
		String request_url = "https://public.baofoo.com/cutpayment/api/backTransRequest";//请求地址	
		//=====================================================
		String notice_url = param.get("returnUrl");//商户改成自已的地址。返回方法在returnurl内请参考	
				
		String Refund_ID = "RefundID"+System.currentTimeMillis();//退款订单号
		String Refund_TSN = "RefundTSN"+System.currentTimeMillis();//退款商户流水号
		String trade_date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());//退款发起时间		
		
		Map<String,Object> ArrayData = new HashMap<String,Object>();		
		
		ArrayData.put("txn_sub_type", Headparm.get("txn_sub_type"));
		ArrayData.put("terminal_id", Headparm.get("terminal_id"));
		ArrayData.put("member_id", Headparm.get("member_id"));
		ArrayData.put("trans_id", trans_id);
		ArrayData.put("refund_order_no", Refund_ID);//退款时商户端生成的订单号
		ArrayData.put("refund_reason", StringUtils.isNotBlank(param.get("reason"))?param.get("reason"):"暂无退款原因");
		ArrayData.put("refund_amt", txn_amt);//退款金额
		ArrayData.put("refund_time", trade_date);//退款发起时间
		ArrayData.put("additional_info", "附加信息");
		ArrayData.put("req_reserved", "保留");
		ArrayData.put("trans_serial_no", Refund_TSN);//退款流水号
		ArrayData.put("notice_url", notice_url);//退款成功后通知地址。
		ArrayData.put("refund_type", refund_type);//1:网银、2:认证支付
		
		String PostString = Send(Headparm, ArrayData, request_url, pfxFlieName);
		Map<String,Object> result = Decryption(Headparm, PostString, cerFlieName);
		//return "{\"resp_code\":\"" + result.get("resp_code") + "\",\"resp_msg\":\"" + result.get("resp_msg") + "\",\"refund_business_no\":\"" + result.get("refund_business_no") + "\",\"refund_amt\":\"" + result.get("refund_amt")+ "\"}";
		return result.get("resp_code") + "";
    }
    
    /**
     * 退款接口-回调方法（3天审核时间，确定是否退款成功）
     * @author WKX
     * @param request
     * @throws IOException
     * @since 2017年5月8日 下午3:53:32
     */
    public String returnUrl(HttpServletRequest request)throws IOException{
		String data_content = request.getParameter("data_content");//回调参数
		if(data_content.isEmpty()){//判断参数是否为空
			return "返回数据为空";
		}
		data_content = RsaCodingUtil.decryptByPubCerFileBuffer(data_content,cerFlieName);
		if(data_content.isEmpty()){//判断解密是否正确。如果为空则宝付公钥不正确
			return "解密失败！";
		}
		data_content = SecurityUtil.Base64Decode(data_content);		 
		data_content = JXMConvertUtil.XmlConvertJson(data_content);
		Map<String,Object> ArrayData = JXMConvertUtil.JsonConvertHashMap((Object)data_content);//将JSON转化为Map对象。
		Object resp_code = ArrayData.get("resp_code");
		
		//这里根据ArrayData 对象里的值去写本地服务器端数据
		//商户自行写入自已的数据。。。。。。。。
		if(resp_code!=null && "0000".equals(resp_code.toString())){
			return "OK";//处理完成在页面输出OK（必须）
		}
		return "NO";
	}
}