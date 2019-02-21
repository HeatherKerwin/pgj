package com.utiexian.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.httpclient.NameValuePair;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.w3c.dom.NodeList;

import com.utiexian.utils.alipay.AlipayConfig;
import com.utiexian.utils.alipay.AlipayCore;
import com.utiexian.utils.alipay.HttpProtocolHandler;
import com.utiexian.utils.alipay.HttpRequest;
import com.utiexian.utils.alipay.HttpResponse;
import com.utiexian.utils.alipay.HttpResultType;
import com.utiexian.utils.alipay.MD5;
import com.utiexian.utils.utils.DateUtil;

public class AlipayUtil {
    
    /**
     * 支付宝提供给商户的服务接入网关URL(新)
     */
    private static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";
	
    /**
     * 生成签名结果
     * @param sPara 要签名的数组
     * @return 签名结果字符串
     */
	public static String buildRequestMysign(Map<String, String> sPara) {
    	String prestr = AlipayCore.createLinkString(sPara); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String mysign = "";
        if(AlipayConfig.sign_type.equals("MD5") ) {
        	mysign = MD5.sign(prestr, AlipayConfig.key, AlipayConfig.input_charset);
        }
        return mysign;
    }
	
    /**
     * 生成要请求给支付宝的参数数组
     * @param sParaTemp 请求前的参数数组
     * @return 要请求的参数数组
     */
    private static Map<String, String> buildRequestPara(Map<String, String> sParaTemp) {
        //除去数组中的空值和签名参数
        Map<String, String> sPara = AlipayCore.paraFilter(sParaTemp);
        //生成签名结果
        String mysign = buildRequestMysign(sPara);

        //签名结果与签名方式加入请求提交参数组中
        sPara.put("sign", mysign);
        sPara.put("sign_type", AlipayConfig.sign_type);

        return sPara;
    }

    /**
     * 建立请求，以表单HTML形式构造（默认）
     * @param sParaTemp 请求参数数组
     * @param strMethod 提交方式。两个值可选：post、get
     * @param strButtonName 确认按钮显示文字
     * @return 提交表单HTML文本
     */
    public static String buildRequest(Map<String, String> sParaTemp, String strMethod, String strButtonName) {
        //待请求参数数组
        Map<String, String> sPara = buildRequestPara(sParaTemp);
        List<String> keys = new ArrayList<String>(sPara.keySet());

        StringBuffer sbHtml = new StringBuffer();

        sbHtml.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\" action=\"" + ALIPAY_GATEWAY_NEW
                      + "_input_charset=" + AlipayConfig.input_charset + "\" method=\"" + strMethod
                      + "\">");

        for (int i = 0; i < keys.size(); i++) {
            String name = (String) keys.get(i);
            String value = (String) sPara.get(name);

            sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
        }

        //submit按钮控件请不要含有name属性
        sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");
        sbHtml.append("<script>document.forms['alipaysubmit'].submit();</script>");

        return sbHtml.toString();
    }
    
    /**
     * 建立请求，以表单HTML形式构造，带文件上传功能
     * @param sParaTemp 请求参数数组
     * @param strMethod 提交方式。两个值可选：post、get
     * @param strButtonName 确认按钮显示文字
     * @param strParaFileName 文件上传的参数名
     * @return 提交表单HTML文本
     */
    public static String buildRequest(Map<String, String> sParaTemp, String strMethod, String strButtonName, String strParaFileName) {
        //待请求参数数组
        Map<String, String> sPara = buildRequestPara(sParaTemp);
        List<String> keys = new ArrayList<String>(sPara.keySet());

        StringBuffer sbHtml = new StringBuffer();

        sbHtml.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\"  enctype=\"multipart/form-data\" action=\"" + ALIPAY_GATEWAY_NEW
                      + "_input_charset=" + AlipayConfig.input_charset + "\" method=\"" + strMethod
                      + "\">");

        for (int i = 0; i < keys.size(); i++) {
            String name = (String) keys.get(i);
            String value = (String) sPara.get(name);

            sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
        }
        
        sbHtml.append("<input type=\"file\" name=\"" + strParaFileName + "\" />");

        //submit按钮控件请不要含有name属性
        sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");

        return sbHtml.toString();
    }
    
    /**
     * 建立请求，以模拟远程HTTP的POST请求方式构造并获取支付宝的处理结果
     * 如果接口中没有上传文件参数，那么strParaFileName与strFilePath设置为空值
     * 如：buildRequest("", "",sParaTemp)
     * @param strParaFileName 文件类型的参数名
     * @param strFilePath 文件路径
     * @param sParaTemp 请求参数数组
     * @return 支付宝处理结果
     * @throws Exception
     */
    public static String buildRequest(String strParaFileName, String strFilePath,Map<String, String> sParaTemp) throws Exception {
        //待请求参数数组
        Map<String, String> sPara = buildRequestPara(sParaTemp);

        HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler.getInstance();

        HttpRequest request = new HttpRequest(HttpResultType.BYTES);
        //设置编码集
        request.setCharset(AlipayConfig.input_charset);

        request.setParameters(generatNameValuePair(sPara));
        request.setUrl(ALIPAY_GATEWAY_NEW+"_input_charset="+AlipayConfig.input_charset);

        HttpResponse response = httpProtocolHandler.execute(request,strParaFileName,strFilePath);
        if (response == null) {
            return null;
        }
        
        String strResult = response.getStringResult();

        return strResult;
    }
    
    /**
     * 单笔订单查询
     * @author WKX
     * @param 商户订单号
     * @throws Exception
     * @return trade_no 商家订单号、 buyer_email 付款账号、 is_success请求状态（T、F）
     * @since 2016年3月18日 上午10:49:41
     */
    public static Map<String,Object> tradeQuery(String out_trade_no) throws Exception{
    	Map<String,Object> result = new HashMap<String, Object>();
    	NodeList list = null;
    	
    	//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "single_trade_query");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("trade_no", null);
		sParaTemp.put("out_trade_no", out_trade_no);
    	
    	String strResult = buildRequest("", "", sParaTemp);
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();    
        DocumentBuilder builder;
		builder  =  factory.newDocumentBuilder();   
		org.w3c.dom.Document doc = builder.parse(new ByteArrayInputStream(strResult.getBytes("UTF-8")));
		list = doc.getElementsByTagName("trade_no");
		if(list!=null && list.getLength()>0){
			result.put("trade_no", list.item(0).getFirstChild().getNodeValue());
		}
		list = doc.getElementsByTagName("buyer_email");
		if(list!=null && list.getLength()>0){
			result.put("buyer_email", list.item(0).getFirstChild().getNodeValue());
		}
		list = doc.getElementsByTagName("is_success");
		if(list!=null && list.getLength()>0){
			result.put("is_success", list.item(0).getFirstChild().getNodeValue());
		}
		list = doc.getElementsByTagName("trade_status");
		if(list!=null && list.getLength()>0){
			result.put("trade_status", list.item(0).getFirstChild().getNodeValue());
		}
		return result;
    }
    
    /**
     * 查询订单支付状态（TRADE_SUCCESS、TRADE_CLOSED）
     * @param out_trade_no
     * @throws Exception
     */
    public static String tradeClose(String out_trade_no) throws Exception{
    	String result = out_trade_no;
    	//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "single_trade_query");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("trade_no", null);
		sParaTemp.put("out_trade_no", out_trade_no);
    	
    	String strResult = buildRequest("", "", sParaTemp);
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();    
        DocumentBuilder builder;
		builder  =  factory.newDocumentBuilder();   
		org.w3c.dom.Document doc = builder.parse(new ByteArrayInputStream(strResult.getBytes("UTF-8")));
		NodeList list = doc.getElementsByTagName("trade_status");
		if(list!=null && list.getLength()>0){
			result = list.item(0).getFirstChild().getNodeValue();
		}
		return result;
    }
    
    /**
     * 根据交易号无密退款
     * @author WKX
     * @param out_trade_no 交易号
     * @param money 退款金额
     * @param title 退款标题
     * @return （is_success：T、F、P）（error：DUPLICATE_BATCH_NO、TRADE_STATUS_ERROR、ACCOUNT_NOT_EXISTS、TRADE_NOT_EXISTS、TRADE_HAS_CLOSED、TRADE_HAS_FINISHED）
     * @throws Exception
     * @since 2016年5月31日 下午2:12:04
     */
    public static Map<String,Object> refundsNoPwdByTradeNo(String out_trade_no,String money,String title) throws Exception{
    	Map<String,Object> result = new HashMap<String, Object>();
    	
    	Date date = new Date();
		String refund_date = DateUtil.formart(date, "yyyy-MM-dd HH:mm:ss");
		String token = DateUtil.formart(date, "yyyyMMdd");
    	String end = "";
		for(int i = 0; i < 12; i++){
			end += (int)(Math.random()*10);
		}
    	
    	//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "refund_fastpay_by_platform_nopwd");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("refund_date", refund_date);
		sParaTemp.put("batch_no", token+end);
		sParaTemp.put("batch_num", "1");
		sParaTemp.put("detail_data", out_trade_no+"^"+money+"^"+title);
    	
    	String strResult = buildRequest("", "", sParaTemp);
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();    
        DocumentBuilder builder;
		builder  =  factory.newDocumentBuilder();   
		org.w3c.dom.Document doc = builder.parse(new ByteArrayInputStream(strResult.getBytes("UTF-8")));
		NodeList list1 = doc.getElementsByTagName("is_success");
		if(list1!=null && list1.getLength()>0){
			result.put("is_success",list1.item(0).getFirstChild().getNodeValue());
		}
		NodeList list2 = doc.getElementsByTagName("error");
		if(list2!=null && list2.getLength()>0){
			result.put("error",list2.item(0).getFirstChild().getNodeValue());
		}
		return result;
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
     * 用于防钓鱼，调用接口query_timestamp来获取时间戳的处理函数
     * 注意：远程解析XML出错，与服务器是否支持SSL等配置有关
     * @return 时间戳字符串
     * @throws IOException
     * @throws DocumentException
     * @throws MalformedURLException
     */
	@SuppressWarnings("unchecked")
	public static String query_timestamp() throws MalformedURLException,DocumentException, IOException {
        //构造访问query_timestamp接口的URL串
        String strUrl = ALIPAY_GATEWAY_NEW + "service=query_timestamp&partner=" + AlipayConfig.partner + "&_input_charset" +AlipayConfig.input_charset;
        StringBuffer result = new StringBuffer();

        SAXReader reader = new SAXReader();
        Document doc = reader.read(new URL(strUrl).openStream());

        List<Node> nodeList = doc.selectNodes("//alipay/*");

        for (Node node : nodeList) {
            // 截取部分不需要解析的信息
            if (node.getName().equals("is_success") && node.getText().equals("T")) {
                // 判断是否有成功标示
                List<Node> nodeList1 = doc.selectNodes("//response/timestamp/*");
                for (Node node1 : nodeList1) {
                    result.append(node1.getText());
                }
            }
        }
        return result.toString();
    }
	
	/**
	 * 即时到账交易接口（alipay.wap.create.direct.pay.by.user）（create_direct_pay_by_user）
	 * @author WKX
	 * @param out_trade_no 商户订单号
	 * @param subject 商品名称
	 * @param total_fee 付款金额
	 * @param body 商品描述
	 * @throws Exception
	 * @since 2016年10月24日 下午5:52:27
	 */
	public static String createDirectPay(Map<String, String> paras) throws Exception{	
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "create_direct_pay_by_user");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("seller_email", AlipayConfig.seller_email);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", AlipayConfig.payment_type);
		sParaTemp.put("notify_url", AlipayConfig.notify_url);
		sParaTemp.put("return_url", AlipayConfig.return_url);
		sParaTemp.put("anti_phishing_key", AlipayConfig.anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", AlipayConfig.exter_invoke_ip);
		
		sParaTemp.put("out_trade_no", paras.get("out_trade_no"));//商户订单号
		sParaTemp.put("subject", paras.get("subject"));//商品名称
		sParaTemp.put("total_fee", paras.get("total_fee"));//付款金额
		sParaTemp.put("body", paras.get("body"));//商品描述
		
		if("PC".equals(paras.get("platform"))){
			sParaTemp.put("service", "create_direct_pay_by_user");
		}else if("WAP".equals(paras.get("platform"))){
			sParaTemp.put("service", "alipay.wap.create.direct.pay.by.user");
		}
		if(paras.get("goods_type")!=null)sParaTemp.put("goods_type", paras.get("goods_type"));//商品类型:1表示实物类商品、0表示虚拟类商品
		if(paras.get("return_url")!=null)sParaTemp.put("return_url", paras.get("return_url"));//页面跳转同步通知页面路径
		if(paras.get("notify_url")!=null)sParaTemp.put("notify_url", paras.get("notify_url"));//服务器异步通知页面路径
		
		//退款页面
		return AlipayUtil.buildRequest(sParaTemp,"get","确认");
	}
}