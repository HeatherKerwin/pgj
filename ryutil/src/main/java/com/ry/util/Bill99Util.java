package com.ry.util;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang.StringUtils;
import org.w3c.dom.NodeList;

import com.ry.util.bill.Pkipair;
import com.ry.util.bill.com._99bill.sandbox.apipay.services.gatewayOrderQuery.GatewayOrderQueryServiceLocator;
import com.ry.util.bill.com.bill99.seashell.domain.dto.gatewayquery.GatewayOrderDetail;
import com.ry.util.bill.com.bill99.seashell.domain.dto.gatewayquery.GatewayOrderQueryRequest;
import com.ry.util.bill.com.bill99.seashell.domain.dto.gatewayquery.GatewayOrderQueryResponse;
import com.ry.util.bill.util.HttpProtocolHandler;
import com.ry.util.bill.util.HttpRequest;
import com.ry.util.bill.util.HttpResponse;
import com.ry.util.bill.util.HttpResultType;

/**
 * 快钱支付（工具类，含配置）
 * @author WKX
 */
public class Bill99Util {
	
	private static final String WAPURL = "https://www.99bill.com/mobilegateway/recvMerchantInfoAction.htm";
	private static final String URL = "https://www.99bill.com/gateway/recvMerchantInfoAction.htm";
	private static final String RECEIVEURL = "https://www.99bill.com/webapp/receiveDrawbackAction.do";
	private static final String METHOD = "post";
	
	//人民币网关账号，该账号为11位人民币网关商户编号+01,该参数必填。
	private static String merchantAcctId = "1018504529201";
	//客户编号所对应的密钥。。在账户邮箱中获取
	private static String query_key = "WYXA3IEU5SWYQEAR";//查询密钥
	private static String merchant_key = "N6D33GM2XXCJX4N3";//退款密钥
	//编码方式，1代表 UTF-8; 2 代表 GBK; 3代表 GB2312 默认为1,该参数必填。
	private static String inputCharset = "1";
	//接收支付结果的页面地址，该参数一般置为空即可。
	private static String pageUrl = "";
	//服务器接收支付结果的后台地址，该参数务必填写，不能为空。
	private static String bgUrl = "";
	//网关版本，固定值：v2.0,该参数必填。（注：WAP网关支付版本不同）
	private static String wap_version =  "mobile1.0";
	private static String version =  "v2.0";
	private static String mobileGateway="phone";//WAP网关支付需要配置
	
	//语言种类，1代表中文显示，2代表英文显示。默认为1,该参数必填。
	private static String language =  "1";
	//签名类型,该值为4，代表PKI加密方式,该参数必填。
	private static String signType =  "4";
	//支付人姓名,可以为空。
	private static String payerName= ""; 
	//支付人联系类型，1 代表电子邮件方式；2 代表手机联系方式。可以为空。
	private static String payerContactType =  "";
	//支付人联系方式，与payerContactType设置对应，payerContactType为1，则填写邮箱地址；payerContactType为2，则填写手机号码。可以为空。
	private static String payerContact =  "";
	//扩展字段1，商户可以传递自己需要的参数，支付完快钱会原值返回，可以为空。
	private static String ext1 = "";
	//扩展自段2，商户可以传递自己需要的参数，支付完快钱会原值返回，可以为空。
	private static String ext2 = "";
	/**
	 * 支付方式，一般为00，代表所有的支付方式。如果是银行直连商户，该值为10，必填。
	 * 固定选择值：00、21、21-1、21-2 00代表显示快钱各支付方式列表；
	 * 21 快捷支付
	 * 21-1 代表储蓄卡快捷；21-2 代表信用卡快捷
	 * 其中”-”只允许在半角状态下输入。
	 * */
	private static String payType = "00";
	//银行代码，如果payType为00，该值可以为空；如果payType为10，该值必须填写，具体请参考银行列表。
	private static String bankId = "";
	//同一订单禁止重复提交标志，实物购物车填1，虚拟产品用0。1代表只能提交一次，0代表在支付不成功情况下可以再提交。可为空。
	private static String redoFlag = "";
	//快钱合作伙伴的帐户号，即商户编号，可为空。
	private static String pid = "";
	
	
	//商户订单号，以下采用时间来定义订单号，商户可以根据自己订单号的定义规则来定义该值，不能为空。
	private static String orderId = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
	//订单金额，金额以“分”为单位，商户测试以1分测试即可，切勿以大金额测试。该参数必填。
	private static String orderAmount = "1";
	//订单提交时间，格式：yyyyMMddHHmmss，如：20071117020101，不能为空。
	private static String orderTime = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
	//商品名称，可以为空。
	private static String productName= ""; 
	//商品数量，可以为空。
	private static String productNum = "";
	//商品代码，可以为空。
	private static String productId = "";
	//商品描述，可以为空。
	private static String productDesc = "";
	//指定付款人
	private static String payerIdType="3";
	//付款人标识
	private static String payerId="";
	
	/**
	 * 签名
	 * @author WKX
	 * @param param
	 */
	private static String sign(Map<String, String> param){
		String signMsgVal = appendParam(param);
		Pkipair pki = new Pkipair();
		String signMsg = pki.signMsg(signMsgVal);
		return signMsg;
	}
	
	/**
	 * 装载参数
	 * @author WKX
	 * @param param
	 * @since 2017年2月21日 下午6:47:04
	 */
	private static String appendParam(Map<String, String> param) {
		String returns = "";
		List<String> keys = new ArrayList<String>(param.keySet());
		for (int i = 0; i < keys.size(); i++) {
            String paramId = (String) keys.get(i);
            String paramValue = (String) param.get(paramId);
            if (returns != "") {
    			if (paramValue != "") {
    				returns += "&" + paramId + "=" + paramValue;
    			}
    		} else {
    			if (paramValue != "") {
    				returns = paramId + "=" + paramValue;
    			}
    		}
        }
		return returns;
	}
	
	/**
	 * 装载参数（无@）
	 * @author WKX
	 * @param param
	 * @since 2017年3月3日 下午2:31:01
	 */
	private static String appendParamNoAt(Map<String, String> param) {
		String returns = "";
		List<String> keys = new ArrayList<String>(param.keySet());
		for (int i = 0; i < keys.size(); i++) {
            String paramId = (String) keys.get(i);
            String paramValue = (String) param.get(paramId);
            if (returns != "") {
    			if (paramValue != "") {
    				returns += paramId + "=" + paramValue;
    			}
    		} else {
    			if (paramValue != "") {
    				returns = paramId + "=" + paramValue;
    			}
    		}
        }
		return returns;
	}
	
	/**
	 * 初始化参数
	 * @author WKX
	 * @param src 请求的参数（支付金额等）
	 * @param mode
	 * @since 2017年2月24日 上午10:46:03
	 */
	private static Map<String, String> initParam(Map<String, String> src,String mode){
		Map<String, String> param = new LinkedHashMap<String, String>();
		param.put("inputCharset", inputCharset);
		
		if(src.get("pageUrl")!=null)param.put("pageUrl", src.get("pageUrl"));else param.put("pageUrl", pageUrl);
		if(src.get("bgUrl")!=null)param.put("bgUrl", src.get("bgUrl"));else param.put("bgUrl", bgUrl);
		
		if(StringUtils.isNotBlank(mode) && URL.equals(mode.trim())){
			param.put("version", version);
		}
		if(StringUtils.isNotBlank(mode) && WAPURL.equals(mode.trim())){
			param.put("version", wap_version);
		}
		param.put("language", language);
		param.put("signType", signType);
		param.put("merchantAcctId", merchantAcctId);
		param.put("payerName", payerName);
		param.put("payerContactType", payerContactType);
		param.put("payerContact", payerContact);
		
		if(src.get("payerIdType")!=null)param.put("payerIdType", src.get("payerIdType"));else param.put("payerIdType", payerIdType);
		if(src.get("payerId")!=null)param.put("payerId", "UTIEXIAN"+src.get("payerId"));else param.put("payerId", payerId);
		if(src.get("orderId")!=null)param.put("orderId", src.get("orderId"));else param.put("orderId", orderId);
		if(src.get("orderAmount")!=null)param.put("orderAmount", src.get("orderAmount"));else param.put("orderAmount", orderAmount);
		if(src.get("orderTime")!=null)param.put("orderTime", src.get("orderTime"));else param.put("orderTime", orderTime);
		if(src.get("productName")!=null)param.put("productName", src.get("productName"));else param.put("productName", productName);
		if(src.get("productNum")!=null)param.put("productNum", src.get("productNum"));else param.put("productNum", productNum);
		if(src.get("productId")!=null)param.put("productId", src.get("productId"));else param.put("productId", productId);
		if(src.get("productDesc")!=null)param.put("productDesc", src.get("productDesc"));else param.put("productDesc", productDesc);
		
		param.put("ext1", ext1);
		param.put("ext2", ext2);
		param.put("payType", payType);
		param.put("bankId", bankId);
		param.put("redoFlag", redoFlag);
		param.put("pid", pid);
		
		if(StringUtils.isNotBlank(mode) && WAPURL.equals(mode.trim()))param.put("mobileGateway", mobileGateway);//WAP请求网关
		param.put("signMsg", sign(param));
		return param;
	}
	
	/**
     * MAP类型数组转换成NameValuePair类型
     * @param properties  MAP类型数组
     * @return NameValuePair类型数组
     */
    private static NameValuePair[] generatNameValuePair(Map<String, String> properties) {
        NameValuePair[] nameValuePair = new NameValuePair[properties.size()];
        int i = 0;
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            nameValuePair[i++] = new NameValuePair(entry.getKey(), entry.getValue());
        }
        return nameValuePair;
    }
    
    /**
	 * 生成随机退款号
	 * @author WKX
	 * @since 2017年3月15日 下午4:31:32
	 */
	private static String getTxOrder(){
		String prefix = "T"+new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
		String suffix = "";
		for(int i = 0; i < 6; i++){
			suffix += (int)(Math.random()*10);
		}
		return prefix + suffix;
	}
	
	/* -------------------------这是分割线（下面是封装的公共接口）----------------------------------------------------- */
	
	/**
	 * 创建支付请求（WAP）
	 * @author WKX
	 * @param param 订单参数
	 * @since 2017年2月21日 下午6:22:11
	 */
	public static String buildRequestPayForWap(Map<String, String> param) {
		Map<String, String> result = initParam(param,WAPURL);
        List<String> keys = new ArrayList<String>(result.keySet());

        StringBuffer sbHtml = new StringBuffer();
        sbHtml.append("<form id=\"kqPay\" name=\"kqPay\" action=\"" + WAPURL + "\" method=\"" + METHOD + "\">");

        for (int i = 0; i < keys.size(); i++) {
            String name = (String) keys.get(i);
            String value = (String) result.get(name);
            sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
        }

        sbHtml.append("<input type=\"submit\" value=\"提交到快钱\" style=\"display:none;\"></form>");
        sbHtml.append("<script>document.forms['kqPay'].submit();</script>");

        return sbHtml.toString();
    }
	
	/**
	 * 创建支付请求
	 * @author WKX
	 * @since 2017年2月22日 下午1:54:36
	 */
	public static String buildRequestPay(Map<String, String> param) {
		Map<String, String> result = initParam(param,URL);
        List<String> keys = new ArrayList<String>(result.keySet());

        StringBuffer sbHtml = new StringBuffer();
        sbHtml.append("<form id=\"kqPay\" name=\"kqPay\" action=\"" + URL + "\" method=\"" + METHOD + "\">");

        for (int i = 0; i < keys.size(); i++) {
            String name = (String) keys.get(i);
            String value = (String) result.get(name);
            sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
        }

        sbHtml.append("<input type=\"submit\" value=\"提交到快钱\" style=\"display:none;\"></form>");
        sbHtml.append("<script>document.forms['kqPay'].submit();</script>");

        return sbHtml.toString();
    }
	
	//查询方式   固定选择值：0、1  
	//0按商户订单号单笔查询（返回该订单信息）
	//1按交易结束时间批量查询（只返回成功订单）
	private static String queryType ="0";
	//查询模式   固定值：1  1代表简单查询（返回基本订单信息）
	private static String queryMode ="1";
	//交易开始时间  数字串，一共14位
	//格式为：年[4位]月[2位]日[2位]时[2位]分[2位]秒[2位]，例如：20071117020101
	//private static String startTime ="";//"20120319150000" ;
	//交易结束时间  数字串，一共14位
	//格式为：年[4位]月[2位]日[2位]时[2位]分[2位]秒[2位]，例如：20071117020101
	//private static String endTime ="";//"20120320142624";
	//请求记录集页码  在查询结果数据总量很大时，快钱会将支付结果分多次返回。本参数表示商户需要得到的记录集页码。
	//默认为1，表示第1页。
	//private static String requestPage ="1";
	
	/**
	 * 订单查询
	 * @author WKX
	 * @param param 参数
	 * @throws Exception
	 * @since 2017年3月2日 上午11:01:34
	 */
	public static Map<String,Object> query(Map<String, String> src) throws Exception {
		String _signType = "1";//签名类型   固定值：1  1代表MD5加密签名方式
		Map<String,Object> result = new HashMap<String, Object>();
		List<Map<String,String>> data = new ArrayList<Map<String,String>>();
		Map<String,String> param = new LinkedHashMap<String, String>();//请求参数
		param.put("inputCharset", inputCharset);
		param.put("version", version);
		param.put("signType", _signType);//签名类型   固定值：1  1代表MD5加密签名方式
		param.put("merchantAcctId", merchantAcctId);
		param.put("queryType", queryType);
		param.put("queryMode", queryMode);
		if(src.get("orderId")!=null)param.put("orderId", src.get("orderId"));
		param.put("key", query_key);
		String signMsgParam = appendParam(param);
		String signMsg = com.ry.util.bill.md5.MD5Util.md5Hex(signMsgParam.getBytes("utf-8")).toUpperCase();
		
		GatewayOrderQueryRequest queryRequest = new GatewayOrderQueryRequest();
		queryRequest.setInputCharset(inputCharset);
		queryRequest.setVersion(version);
		queryRequest.setSignType(Integer.parseInt(_signType));
		queryRequest.setMerchantAcctId(merchantAcctId);
		queryRequest.setQueryType(Integer.parseInt(queryType));
		queryRequest.setQueryMode(Integer.parseInt(queryMode));
		queryRequest.setOrderId(param.get("orderId"));
		queryRequest.setStartTime("");
		queryRequest.setEndTime("");
		queryRequest.setRequestPage("");
		queryRequest.setSignMsg(signMsg);
		
		GatewayOrderQueryServiceLocator locator = new GatewayOrderQueryServiceLocator();
		GatewayOrderQueryResponse queryResponse = locator.getgatewayOrderQuery().gatewayOrderQuery(queryRequest);

		String signMsgVal="";
		Map<String,String> val = new LinkedHashMap<String, String>();//请求参数
		val.put("version",queryResponse.getVersion() );
		val.put("signType",""+queryResponse.getSignType());
		val.put("merchantAcctId",queryResponse.getMerchantAcctId());
		val.put("errCode",queryResponse.getErrCode());
		val.put("currentPage",queryResponse.getCurrentPage());
		val.put("pageCount",queryResponse.getPageCount());
		val.put("pageSize",queryResponse.getPageSize());
		val.put("recordCount",queryResponse.getRecordCount());
		val.put("key",query_key);
		signMsgVal = appendParam(val);
		String mysignMsg = com.ry.util.bill.md5.MD5Util.md5Hex(signMsgVal.getBytes("utf-8")).toUpperCase();
		if (queryResponse.getErrCode() != null && !"".equals(queryResponse.getErrCode())) {
			result.put("error", queryResponse.getErrCode());
		}else{
			Map<String,String> v = new LinkedHashMap<String, String>();
			GatewayOrderDetail[] orders = queryResponse.getOrders();
			for (int i = 0; i < orders.length; i++) {
				GatewayOrderDetail detail = orders[i];
				signMsgVal=""; 
				v.put("orderId",detail.getOrderId());
				v.put("orderAmount",""+detail.getOrderAmount());
				v.put("orderTime",detail.getOrderTime());
				v.put("dealTime",detail.getDealTime());
				v.put("payResult",detail.getPayResult());
				v.put("payType",detail.getPayType());
				v.put("payAmount",""+detail.getPayAmount());
				v.put("fee",""+detail.getFee());
				v.put("dealId",detail.getDealId());
				v.put("key",query_key);
				signMsgVal = appendParam(v);
				mysignMsg = com.ry.util.bill.md5.MD5Util.md5Hex(signMsgVal.getBytes()).toUpperCase();
				v.put("mysignMsg", mysignMsg);
				data.add(v);
			}
		}
		result.put("data", data);
		result.put("merchantAcctId", queryResponse.getMerchantAcctId());
        return result;
    }
	
	//商户编号，线上的话改成你们自己的商户编号的，发到商户的注册快钱账户邮箱的
	private static String merchant_id = "10185045292";
	//退款接口版本号 目前固定为此值
	private static String receive_version = "bill_drawback_api_1";
	//操作类型
	private static String command_type = "001";
	
	/**
	 * 退款接口（对外接口）
	 * @author WKX
	 * @param src 参数
	 * @throws Exception 
	 * @since 2017年3月3日 上午9:49:29
	 */
	public static Map<String, String> receive(Map<String, String> src) throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		Map<String, String> param = new LinkedHashMap<String, String>();
		param.put("merchant_id", merchant_id);
		param.put("version", receive_version);
		param.put("command_type", command_type);
		
		param.put("orderid", src.get("orderid"));
		param.put("amount", src.get("amount"));//退款金额，整数或小数，小数位为2位   以人民币元为单位
		param.put("postdate", new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()));//退款提交时间 数字串，一共14位 格式为：年[4 位]月[2 位]日[2 位]时[2 位]分[2 位]秒[2位]
		param.put("txOrder", getTxOrder());//退款流水号  字符串
		
		param.put("merchant_key", merchant_key);
		String mac = appendParamNoAt(param);
		param.put("mac", com.ry.util.bill.md5.MD5Util.md5Hex(mac.getBytes("utf-8")).toUpperCase());
		
		//创建请求
		HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler.getInstance();
        HttpRequest request = new HttpRequest(HttpResultType.BYTES);
        request.setCharset("utf-8");//设置编码集
        request.setParameters(generatNameValuePair(param));
        request.setUrl(RECEIVEURL);
        HttpResponse response = httpProtocolHandler.execute(request,"","");
        if (response == null) {
            return null;
        }
        String strResult = response.getStringResult();
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();    
        DocumentBuilder builder;
		builder  =  factory.newDocumentBuilder();   
		org.w3c.dom.Document doc = builder.parse(new ByteArrayInputStream(strResult.getBytes("UTF-8")));
		NodeList list = doc.getElementsByTagName("ORDERID");//订单号
		if(list!=null && list.getLength()>0 && list.item(0)!=null && list.item(0).getFirstChild()!=null){
			result.put("ORDERID", list.item(0).getFirstChild().getNodeValue());
		}
		list = doc.getElementsByTagName("TXORDER");//退款流水号  字符串
		if(list!=null && list.getLength()>0 && list.item(0)!=null && list.item(0).getFirstChild()!=null){
			result.put("TXORDER", list.item(0).getFirstChild().getNodeValue());
		}
		list = doc.getElementsByTagName("RESULT");//结果（YN）
		if(list!=null && list.getLength()>0 && list.item(0)!=null && list.item(0).getFirstChild()!=null){
			result.put("RESULT", list.item(0).getFirstChild().getNodeValue());
		}
		list = doc.getElementsByTagName("CODE");
		if(list!=null && list.getLength()>0 && list.item(0)!=null && list.item(0).getFirstChild()!=null){
			result.put("CODE", list.item(0).getFirstChild().getNodeValue());
		}
		return result;
    }
}