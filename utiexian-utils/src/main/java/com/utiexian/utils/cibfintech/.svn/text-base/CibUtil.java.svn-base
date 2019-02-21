package com.utiexian.utils.cibfintech;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.utiexian.utils.MyException;

/**
 * 兴业数金工具类
 * @author WKX
 * @see 功能备注：
 * 1.Map<String,Object> result = JSON.parseObject(jsonMsg);
 * 2.以下是页面跳转至第三方支付平台
 * String res = "PCFET0NUWVBFIEhUTUwgUFVCTElDICItLy9XM0MvL0RURCBIVE1MIDQuMDEgVHJhbnNpdGlvbmFsLy9FTiI+PGh0bWw+PGhlYWQ+PG1ldGEgaHR0cC1lcXVpdj0iQ29udGVudC1UeXBlIiBjb250ZW50PSJ0ZXh0L2h0bWw7IGNoYXJzZXQ9dXRmLTgiIC8+PHRpdGxlPuaUtuS7mOebtOmAmui9pui3s+i9rOaOpeWPozwvdGl0bGU+PC9oZWFkPjxmb3JtIGlkPSJlcGF5cmVkaXJlY3QiIG5hbWU9ImVwYXlyZWRpcmVjdCIgYWN0aW9uPSJodHRwczovLzNndGVzdC5jaWIuY29tLmNuOjM3MDMxL2FjcXVpcmUvY2FzaGllci5kbyIgbWV0aG9kPSJwb3N0Ij48aW5wdXQgdHlwZT0iaGlkZGVuIiBuYW1lPSJhcHBpZCIgdmFsdWU9IlEwMDAwODY3Ii8+PGlucHV0IHR5cGU9ImhpZGRlbiIgbmFtZT0ibWFjIiB2YWx1ZT0iOTlDREFGNjJBM0ZCOTM0MUVDREFGQjAzQTQwN0YwMEE5OUE3N0JBQyIvPjxpbnB1dCB0eXBlPSJoaWRkZW4iIG5hbWU9InZlciIgdmFsdWU9IjAxIi8+PGlucHV0IHR5cGU9ImhpZGRlbiIgbmFtZT0ib3JkZXJfbm8iIHZhbHVlPSIwMTAxNDUtMDE4NjEyMzcwNjQ5ODEyODU4OTFDR1IiLz48aW5wdXQgdHlwZT0iaGlkZGVuIiBuYW1lPSJvcmRlcl90aXRsZSIgdmFsdWU9IuWuouaIt1vkuozkuInpm7bkvIHkuIBd572R5YWz5YWF5YC8Ii8+PGlucHV0IHR5cGU9ImhpZGRlbiIgbmFtZT0idGltZXN0YW1wIiB2YWx1ZT0iMjAxNzExMTUxMDA4NDIiLz48aW5wdXQgdHlwZT0iaGlkZGVuIiBuYW1lPSJvcmRlcl9hbW91bnQiIHZhbHVlPSIxMTAwMC4wMCIvPjxpbnB1dCB0eXBlPSJoaWRkZW4iIG5hbWU9Im9yZGVyX2Rlc2MiIHZhbHVlPSLkuozkuInpm7bkvIHkuIAt5pSv5LuYLTU5MTAiLz48aW5wdXQgdHlwZT0iaGlkZGVuIiBuYW1lPSJvcmRlcl90aW1lIiB2YWx1ZT0iMjAxNzExMTUxMDA4NDIiLz48aW5wdXQgdHlwZT0iaGlkZGVuIiBuYW1lPSJwYXlfdHlwZSIgdmFsdWU9IjUzIi8+PGlucHV0IHR5cGU9ImhpZGRlbiIgbmFtZT0ic2VydmljZSIgdmFsdWU9ImNpYi5lcGF5LmFjcXVpcmUuY2FzaGllci5xdWlja05ldFBheSIvPjxpbnB1dCB0eXBlPSJoaWRkZW4iIG5hbWU9Im9yZGVyX2lwIiB2YWx1ZT0iMTgwLjE2OS4yMDguMTA2Ii8+PGlucHV0IHR5cGU9ImhpZGRlbiIgbmFtZT0iYmFua19ubyIgdmFsdWU9IjMwOTM5MTAwMDAxMSIvPjxpbnB1dCB0eXBlPSJoaWRkZW4iIG5hbWU9ImN1ciIgdmFsdWU9IkNOWSIvPjxpbnB1dCB0eXBlPSJoaWRkZW4iIG5hbWU9InN1Yl9tcmNoIiB2YWx1ZT0i56Wo566h5a62Ii8+PGlucHV0IHR5cGU9InN1Ym1pdCIgdmFsdWU9InN1Ym1pdCIgc3R5bGU9ImRpc3BsYXk6bm9uZTsiPjwvZm9ybT48c2NyaXB0PmRvY3VtZW50LmZvcm1zWydlcGF5cmVkaXJlY3QnXS5zdWJtaXQoKTs8L3NjcmlwdD48Ym9keT48L2JvZHk+PC9odG1sPg==";
 * byte[] encoded = Base64.decodeBase64(res);
 * model.addAttribute("data", new String(encoded));
 * return "out-url";
 */
public class CibUtil {
	
	private static final HttpClient CLIENT = new HttpClient();
	
	/**
	 * 随机数（指定几位）
	 * @author WKX
	 * @param size 总长度
	 */
	public static String random(Integer size){
		StringBuffer str = new StringBuffer();
		if(size==null)size=6;
		for(int i = 0; i < size; i++){
			str.append((int)(Math.random()*10));
		}
		return str.toString();
	}
	
	/**
	 * 随机数（含日期）
	 * @author WKX
	 * @param size 补足长度（总长度：12+size）
	 */
	public static String date_random(Integer size){
		SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
		StringBuffer str = new StringBuffer(format.format(new Date()));
		if(size==null)size=6;
		for(int i = 0; i < size; i++){
			str.append((int)(Math.random()*10));
		}
		return str.toString();
	}
	
	/**
	 * MAP转JSON
	 * @param map 数据（有序）
	 */
	public static String mapToJsonString(Map<String,Object> map) {
		if(map!=null){
			map.put("timestamp", DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss"));
			map.put("nonce", date_random(3));
			map.put("ex_serial_no", date_random(6));//平台流水
			
			JSONObject json = new JSONObject(map);
			String body = json.toJSONString();
			return body;
		}else{
			return null;
		}
	}

	/**
	 * 调用发生错误-返回值
	 * @author WKX
	 */
	private static Map<String,Object> ERROR(){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("return_code", "-1");//标识调用失败（-1:失败 99:成功）
		result.put("trans_status", "F");//交易状态（F：失败N：新建P：处理中S：成功）
		result.put("error_code", "EXCEPTION");//调用异常
		result.put("error_msg", "操作失败！请联系客服");
		return result;
	}
	
	/**
     * 根据贴现金额计算手续费（兴业数金）
     * @author WKX
     * @param money 贴现金额（元）
     */
    public static String getCibFee(String money_){
    	money_ = money_.replaceAll(",", "");//处理传入金额
    	
    	BigDecimal money = new BigDecimal(money_);
    	BigDecimal zero = new BigDecimal("0");
    	if(money.compareTo(zero)!=1)throw new MyException("贴现金额不能小于0元！");
    	
    	BigDecimal all = getFee(money);
    	return all.setScale(2, BigDecimal.ROUND_UP).toString();
    }
    
    /**
     * 根据贴现金额计算手续费（兴业数金）含提现费用
     * @author WKX
     * @param money 贴现金额（元）
     */
    public static String getCibFeeAndTx(String money_){
    	money_ = money_.replaceAll(",", "");//处理传入金额
    	
    	BigDecimal money = new BigDecimal(money_);
    	BigDecimal zero = new BigDecimal("0");
    	if(money.compareTo(zero)!=1)throw new MyException("贴现金额不能小于0元！");
    	
    	//step.1服务费用
    	BigDecimal amt_1 = getFee(money);
    	
    	//step.2提现费用
    	BigDecimal amt_2 = new BigDecimal("0");
    	BigDecimal lv = new BigDecimal("0.00002");
    	
    	BigDecimal wan_10 = new BigDecimal("100000");
    	BigDecimal wan_50 = new BigDecimal("500000");
    	BigDecimal wan_100 = new BigDecimal("1000000");
    	
    	if(money.compareTo(wan_10)==-1 || money.compareTo(wan_10)==0){//小于等于10万
    		amt_2 = new BigDecimal("10");//10元
    	}else if(money.compareTo(wan_10)==1 && (money.compareTo(wan_50)==-1 || money.compareTo(wan_50)==0)){//大于10万，小于等于50万
    		amt_2 = new BigDecimal("15");//15元
    	}else if(money.compareTo(wan_50)==1 && (money.compareTo(wan_100)==-1 || money.compareTo(wan_100)==0)){//大于50万，小于等于100万
    		amt_2 = new BigDecimal("20");//20元
    	}else if(money.compareTo(wan_100)==1){//大于100万
    		amt_2 = money.multiply(lv);
    		if(amt_2.compareTo(new BigDecimal("200"))==1){
    			amt_2 = new BigDecimal("200");
    		}
    	}
    	BigDecimal all = amt_1.add(amt_2);
    	return all.setScale(2, BigDecimal.ROUND_UP).toString();
    }
    
    /**
     * 服务费用0.6BP(6元到300元)
     * @author WKX
     * @param money 贴现金额
     */
    private static BigDecimal getFee(BigDecimal money){
    	BigDecimal bp = new BigDecimal("0.00006");//0.6BP(6元到300元)
    	
    	BigDecimal amt_1 = money.multiply(bp);
    	if(amt_1.compareTo(new BigDecimal("6"))==-1){//小于6元
    		amt_1 = new BigDecimal("6");
    	}else if(amt_1.compareTo(new BigDecimal("300"))==1){//大于300元
    		amt_1 = new BigDecimal("300");
    	}
    	return amt_1;
    }
    
	//======================================================公共接口（常规下单交易流程）===========================================================
	
	/**
	 * 6.5 企业开户
	 * @author WKX
	 * @param corpNo 企业编号
	 */
	public static Map<String,Object> corp(String corpNo,Map<String,Object> map) {
		String url = CibConfig.DOMAIN + "/biz/corp/" + CibConfig.APPID + "/" + corpNo;
		try {
			String body = CibUtil.mapToJsonString(map);

			String jsonMsg = CLIENT.send(url, CibConfig.APPID, CibConfig.EXCH_PRIVATE_KEY, CibConfig.UFM_PUBLIC_KEY, body, HttpClient.METHOD.POST);
			Map<String,Object> result = JSON.parseObject(jsonMsg);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR();
		}
	}
	
	/**
	 * 6.10 企业账户（查询）
	 * @author WKX
	 * @param corpNo 企业编号
	 * @return 响应字段说明：
	 * /corp/corp_no 企业编号
	 * /corp/name 企业名称
	 * /corp/status 公司状态（0：待审核 1：审核失败 2：正常 3：锁定 4：无效 5：审核通过待鉴定 6：鉴定失败）
	 * /corp/v_acct/acct_no 虚拟账户
	 * /corp/v_acct/acct_name 账户名
	 * /corp/v_acct/balance 可用金额
	 * /corp/v_acct/frozen_balance 冻结金额
	 * /corp/v_acct/trading_amt 交易中金额
	 */
	public static Map<String,Object> corpQuery(String corpNo){
		String url = CibConfig.DOMAIN + "/biz/corp/" + CibConfig.APPID + "/" + corpNo + "/";
		try {
			if(StringUtils.isBlank(corpNo))throw new MyException();
			Map<String,Object> map = new LinkedHashMap<String, Object>();
			String body = CibUtil.mapToJsonString(map);

			String jsonMsg = CLIENT.send(url, CibConfig.APPID, CibConfig.EXCH_PRIVATE_KEY, CibConfig.UFM_PUBLIC_KEY, body, HttpClient.METHOD.GET);
			Map<String,Object> result = JSON.parseObject(jsonMsg);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR();
		}
	}
	
	/**
	 * 6.9 企业管理-文件上传
	 * @author WKX
	 * @param corpNo 企业编号
	 * @param type 证件类型（
	 * A0：法人身份证
	 * A1: 法人身份证-正面
	 * A2：法人身份证-背面
	 * B0：经办人身份证
	 * B1: 经办人身份证-正面
	 * B2：经办人身份证-背面
	 * C0：财务身份证
	 * C1: 财务身份证-正面
	 * C2: 财务身份证-背面
	 * D0：被授权人身份证
	 * D1：被授权人身份证-正面
	 * D2：被授权人身份证-背面
	 * 10：机构信用代码证
	 * 11：组织机构代码证
	 * 20：企业法人营业执照
	 * 21：税务登记证
	 * 30：开户许可证
	 * 40：企业授权委托书--兴业数金
	 * 41：单位授权书--兴业银行）
	 * @param file 文件（类型：MultipartFile）
	 * @return 响应字段说明：
	 * /corp/corp_no 企业编号
	 * /corp/name 企业名称
	 * /corp/status 公司状态（0：待审核 1：审核失败 2：正常 3：锁定 4：无效 5：审核通过待鉴定 6：鉴定失败）
	 * /corp/licences/licence_type 证照类型 
	 */
	public static Map<String,Object> uploadFlie(String corpNo,String type,String fileUrl){
		String url = CibConfig.DOMAIN + "/biz/corp/" + CibConfig.APPID + "/" + corpNo + "/licence/" + type;
		try {
			if(StringUtils.isBlank(corpNo) || StringUtils.isBlank(type))throw new MyException();
			Map<String,Object> map = new LinkedHashMap<String, Object>();
			
			String body = CibUtil.mapToJsonString(map);
			String jsonMsg = CLIENT.upload(url,fileUrl, CibConfig.APPID, CibConfig.EXCH_PRIVATE_KEY, CibConfig.UFM_PUBLIC_KEY, body);
			Map<String,Object> result = JSON.parseObject(jsonMsg);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR();
		}
	}
	
	/**
	 * 6.7 开户（小额鉴定）
	 * @author WKX
	 * @param corpNo 企业编号
	 * @param type 金额
	 * @param model
	 * @return 响应字段说明：
	 * /corp/corp_no 企业编号
	 * /corp/name 企业名称
	 * /corp/status 公司状态(0：待审核 1：审核失败 2：正常 3：锁定 4：无效 5：审核通过待鉴定 6：鉴定失败)
	 * /authenticate_order/left_count 剩余次数（总共6次）
	 */
	public static Map<String,Object> authenticate(String corpNo,Map<String,Object> map){
		String url = CibConfig.DOMAIN + "/biz/corp/" + CibConfig.APPID + "/" + corpNo + "/authenticate";
		try {
			if(StringUtils.isBlank(corpNo))throw new MyException();
			String body = CibUtil.mapToJsonString(map);
			String jsonMsg = CLIENT.send(url, CibConfig.APPID, CibConfig.EXCH_PRIVATE_KEY, CibConfig.UFM_PUBLIC_KEY, body, HttpClient.METHOD.POST);
			Map<String,Object> result = JSON.parseObject(jsonMsg);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR();
		}
	}
	
	/**
	 * 7.1 下单（由资方发起）下单并去打款
	 * @author WKX
	 * @param corpNo 资金方企业编号
	 * @param map 请求的参数
	 * @return 响应字段说明：
	 * /biz_order/order_no 订单编号
	 * /biz_order/status 业务订单状态（00新建、11待支付、12支付成功、19支付失败、28交易成功、29交易取消）
	 * /biz_order/recharge_url 充值URL（企业网银充值URL（Base64编码的），平台收到此URL后，做Base64解码并指引客户端浏览器跳转。）
	 */
	public static Map<String,Object> order(String corpNo,Map<String,Object> map){
		String url = CibConfig.DOMAIN + "/biz/ecd/" + CibConfig.APPID + "/corp/" + corpNo + "/bizOrder";
		try {
			if(StringUtils.isBlank(corpNo))throw new MyException();
			String body = CibUtil.mapToJsonString(map);
			String jsonMsg = CLIENT.send(url, CibConfig.APPID, CibConfig.EXCH_PRIVATE_KEY, CibConfig.UFM_PUBLIC_KEY, body, HttpClient.METHOD.POST);
			Map<String,Object> result = JSON.parseObject(jsonMsg);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR();
		}
	}
	
	/**
	 * 7.2 订单流转【买方确认（确认已签收）】订单申请完成
	 * @author WKX
	 * @param corpNo 资金方编号
	 * @param orderNo 订单编号
	 */
	public static Map<String,Object> finish(String corpNo,String orderNo){
		String url = CibConfig.DOMAIN + "/biz/ecd/" + CibConfig.APPID + "/corp/" + corpNo + "/bizOrder/" + orderNo;
		try {
			if(StringUtils.isBlank(corpNo))throw new MyException();
			if(StringUtils.isBlank(orderNo))throw new MyException();
			
			Map<String,Object> map = new LinkedHashMap<String, Object>();
			Map<String,Object> biz_order = new LinkedHashMap<String, Object>();//订单信息
			
			biz_order.put("status", "28");//业务订单状态（00新建、11待支付、12支付成功、19支付失败、28交易成功、29交易取消）
			map.put("biz_order", biz_order);
			String body = CibUtil.mapToJsonString(map);
			
			String jsonMsg = CLIENT.send(url, CibConfig.APPID, CibConfig.EXCH_PRIVATE_KEY, CibConfig.UFM_PUBLIC_KEY, body, HttpClient.METHOD.PUT);
			Map<String,Object> result = JSON.parseObject(jsonMsg);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR();
		}
	}
	
	/**
	 * 7.2 取消订单申请
	 * @author WKX
	 * @param corpNo 资金方编号
	 * @param orderNo 订单编号
	 * @return 响应字段说明：
	 * /biz_order/buyer/corp_no 买方企业编号
	 * /biz_order/saler/corp_no 卖方企业编号
	 * /biz_order/order_no 订单编号
	 * /biz_order/status 状态
	 */
	public static Map<String,Object> cancel(String corpNo,String orderNo){
		String url = CibConfig.DOMAIN + "/biz/ecd/" + CibConfig.APPID + "/corp/" + corpNo + "/bizOrder/" + orderNo;
		try {
			if(StringUtils.isBlank(corpNo))throw new MyException();
			if(StringUtils.isBlank(orderNo))throw new MyException();
			
			Map<String,Object> map = new LinkedHashMap<String, Object>();
			Map<String,Object> biz_order = new LinkedHashMap<String, Object>();//订单信息
			
			biz_order.put("status", "29");//业务订单状态（00新建、11待支付、12支付成功、19支付失败、28交易成功、29交易取消）
			map.put("biz_order", biz_order);
			String body = CibUtil.mapToJsonString(map);
			
			String jsonMsg = CLIENT.send(url, CibConfig.APPID, CibConfig.EXCH_PRIVATE_KEY, CibConfig.UFM_PUBLIC_KEY, body, HttpClient.METHOD.PUT);
			Map<String,Object> result = JSON.parseObject(jsonMsg);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR();
		}
	}
	
	/**
	 * 7.3 订单（查询）注：跨平台订单通用
	 * @author WKX
	 * @param corpNo 企业编号
	 * @param orderNo 业务订单编号
	 * @return 响应字段说明：
	 * /biz_order/buyer/corp_no 资金方企业编号
	 * /biz_order/saler/corp_no 持票方企业编号
	 * /biz_order/order_no 订单编号
	 * /biz_order/status 状态
	 * /biz_order/applications 申请记录（list）
	 * /biz_order/applications/create_time 请求时间
	 * /biz_order/applications/status 状态
	 * /biz_order/applications/endorse_status 签收结果
	 * /biz_order/applications/endorse_desc 签收结果描述
	 * /biz_order/applications/endorse_time 签收结果时间
	 * /biz_order/applications/payment_approved 确认支付
	 */
	public static Map<String,Object> orderQuery(String corpNo, String orderNo) {
		String url = CibConfig.DOMAIN + "/biz/ecd/" + CibConfig.APPID + "/corp/" + corpNo + "/bizOrder/" + orderNo;
		try {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			String body = CibUtil.mapToJsonString(map);

			String jsonMsg = CLIENT.send(url, CibConfig.APPID, CibConfig.EXCH_PRIVATE_KEY, CibConfig.UFM_PUBLIC_KEY,body, HttpClient.METHOD.GET);
			Map<String,Object> result = JSON.parseObject(jsonMsg);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR();
		}
	}
	
	/**
	 * 6.27 根据分支行名称模糊查询（大小额行号）
	 * @author WKX
	 * @param keyword 关键字（分支行名称、分支行号[精准查询]）
	 * @return 响应字段说明：
	 * /banks 大小额系统信息（返回结果列表）
	 * /banks/bank_branch 分支行全称
	 * /banks/bank_no 开户银行行号
	 * /banks/cnaps_code 人行大小额行号
	 */
	public static Map<String,Object> queryBank(String keyword) {
		String url = CibConfig.DOMAIN + "/biz/assist/" + CibConfig.APPID + "/pboc/cnaps";
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			Map<String,Object> bank = new LinkedHashMap<String, Object>();//订单信息
			if(isNumeric(keyword)){//如果是数字，认为是分支行号查询
				bank.put("cnaps_code", keyword);
				map.put("bank", bank);
				
				String body = CibUtil.mapToJsonString(map);
				
				String jsonMsg = CLIENT.send(url, CibConfig.APPID, CibConfig.EXCH_PRIVATE_KEY, CibConfig.UFM_PUBLIC_KEY,body, HttpClient.METHOD.GET);
				result = JSON.parseObject(jsonMsg);
			}else{//关键字查询
				bank.put("bank_branch", keyword);
				map.put("bank", bank);
				
				String body = CibUtil.mapToJsonString(map);
				
				String jsonMsg = CLIENT.send(url, CibConfig.APPID, CibConfig.EXCH_PRIVATE_KEY, CibConfig.UFM_PUBLIC_KEY,body, HttpClient.METHOD.POST);
				result = JSON.parseObject(jsonMsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR();
		}
		return result;
	}
	
	/**
	 * 判断是否是数字
	 * @author WKX
	 * @param str 字符串
	 */
	public static boolean isNumeric(String str){
	    Pattern pattern = Pattern.compile("[0-9]*");
	    return pattern.matcher(str).matches();   
	}
	
	/**
	 * 6.6 企业管理-多账户（操作一般虚拟账户）新增、解锁、锁定、关闭
	 * @author WKX
	 * @param corpNo 企业编号
	 * @param map 请求的参数
	 * @param m （POST：增加一般虚拟账户 DELETE:关闭一般虚拟账户 PUT：更新一般虚拟账户。目前只能更新状态进行锁定、解锁。）
	 * @return 响应字段说明：
	 * /corp/corp_no 企业编号
	 * /corp/name 企业名称
	 * /corp/status 公司状态（0：待审核 1：审核失败 2：正常 3：锁定 4：无效 5：审核通过待鉴定 6：鉴定失败）
	 */
	public static Map<String,Object> general(String corpNo,Map<String,Object> map,HttpClient.METHOD m){
		String url = CibConfig.DOMAIN + "/biz/corp/" + CibConfig.APPID + "/" + corpNo + "/general";
		try {
			if(StringUtils.isBlank(corpNo) || m==null)throw new MyException();
			String body = CibUtil.mapToJsonString(map);
			String jsonMsg = CLIENT.send(url, CibConfig.APPID, CibConfig.EXCH_PRIVATE_KEY, CibConfig.UFM_PUBLIC_KEY, body, m);
			Map<String,Object> result = JSON.parseObject(jsonMsg);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR();
		}
	}
	
	//======================================================公共接口（跨平台交易流程）===========================================================
	
	/**
	 * 买方报价（跨平台票）注：定价成功后询价流水号=订单编号
	 * @param draftNo 票号
	 * @param map 请求参数
	 * @return 返回结果{
	 * 	serial_no 执剑人系统流水号（作为报价流水号）
	 * }
	 */
	public static Map<String,Object> unionQuote(String draftNo,Map<String,Object> map){
		String url = CibConfig.DOMAIN + "/biz/union/" + CibConfig.APPID + "/ecd/" + draftNo + "/quote";
		try {
			if(StringUtils.isBlank(draftNo))throw new MyException();
			String body = CibUtil.mapToJsonString(map);
			
			String jsonMsg = CLIENT.send(url, CibConfig.APPID, CibConfig.EXCH_PRIVATE_KEY, CibConfig.UFM_PUBLIC_KEY, body,HttpClient.METHOD.POST);
			Map<String,Object> result = JSON.parseObject(jsonMsg);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR();
		}
	}
	
	/**
	 * 查询订单（跨平台票）
	 * @param draftNo 票号
	 * @param map 请求参数
	 * @return 响应字段说明：
	 * /biz_order/order_no 订单编号
	 * /biz_order/status 状态（00：新建 11：待支付 12：支付成功 19：支付失败 28：交易成功 29：交易取消）
	 * /biz_order/applications 申请记录
	 */
	public static Map<String,Object> unionQuery(String draftNo,Map<String,Object> map){
		String url = CibConfig.DOMAIN + "/biz/union/" + CibConfig.APPID + "/ecd/" + draftNo + "/order";
		try {
			if(StringUtils.isBlank(draftNo))throw new MyException();
			String body = CibUtil.mapToJsonString(map);
			
			String jsonMsg = CLIENT.send(url, CibConfig.APPID, CibConfig.EXCH_PRIVATE_KEY, CibConfig.UFM_PUBLIC_KEY, body,HttpClient.METHOD.GET);
			Map<String,Object> result = JSON.parseObject(jsonMsg);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR();
		}
	}
	
	/**
	 * 申请订单完成（跨平台票）
	 * @param draftNo 票号
	 * @param orderNo 订单号
	 * @param map 请求参数
	 * @return 响应字段说明：
	 * /biz_order/buyer/corp_no 资金方企业编号
	 * /biz_order/saler/corp_no 持票方企业编号
	 * /biz_order/order_no 订单编号
	 * /biz_order/status 状态（00：新建 11：待支付 12：支付成功 19：支付失败 28：交易成功 29：交易取消）
	 * /biz_order/applications 申请记录
	 */
	public static Map<String,Object> unionFinish(String draftNo,String orderNo,String quote_serial_no){
		String url = CibConfig.DOMAIN + "/biz/union/" + CibConfig.APPID + "/ecd/" + draftNo + "/order/" + orderNo;
		try {
			if(StringUtils.isBlank(draftNo))throw new MyException();
			
			Map<String,Object> map = new LinkedHashMap<String, Object>();
			Map<String,Object> biz_order = new LinkedHashMap<String, Object>();//订单信息
			Map<String,Object> union_order = new LinkedHashMap<String, Object>();//订单信息
			
			biz_order.put("status", "28");//业务订单状态（00新建、11待支付、12支付成功、19支付失败、28交易成功、29交易取消）
			union_order.put("quote_serial_no", quote_serial_no);//报价流水号
			
			map.put("biz_order", biz_order);
			map.put("union_order", union_order);
			String body = CibUtil.mapToJsonString(map);
			
			String jsonMsg = CLIENT.send(url, CibConfig.APPID, CibConfig.EXCH_PRIVATE_KEY, CibConfig.UFM_PUBLIC_KEY, body,HttpClient.METHOD.PUT);
			Map<String,Object> result = JSON.parseObject(jsonMsg);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR();
		}
	}
	
	/**
	 * 申请订单取消（跨平台票）
	 * @param draftNo 票号
	 * @param orderNo 订单号
	 * @param map 请求参数
	 * @return 响应字段说明：
	 * /biz_order/buyer/corp_no 资金方企业编号
	 * /biz_order/saler/corp_no 持票方企业编号
	 * /biz_order/order_no 订单编号
	 * /biz_order/status 状态（00：新建 11：待支付 12：支付成功 19：支付失败 28：交易成功 29：交易取消）
	 * /biz_order/applications 申请记录
	 */
	public static Map<String,Object> unionCancel(String draftNo,String orderNo,String quoteSerialNo){
		String url = CibConfig.DOMAIN + "/biz/union/" + CibConfig.APPID + "/ecd/" + draftNo + "/order/" + orderNo;
		try {
			if(StringUtils.isBlank(draftNo))throw new MyException();
			if(StringUtils.isBlank(orderNo))throw new MyException();
			if(StringUtils.isBlank(quoteSerialNo))throw new MyException();
			
			Map<String,Object> map = new LinkedHashMap<String, Object>();
			Map<String,Object> biz_order = new LinkedHashMap<String, Object>();//订单信息
			Map<String,Object> union_order = new LinkedHashMap<String, Object>();//订单信息
			
			biz_order.put("status", "29");//业务订单状态（00新建、11待支付、12支付成功、19支付失败、28交易成功、29交易取消）
			union_order.put("quote_serial_no", quoteSerialNo);//报价流水号
			
			map.put("biz_order", biz_order);
			map.put("union_order", union_order);
			String body = CibUtil.mapToJsonString(map);
			
			String jsonMsg = CLIENT.send(url, CibConfig.APPID, CibConfig.EXCH_PRIVATE_KEY, CibConfig.UFM_PUBLIC_KEY, body,HttpClient.METHOD.PUT);
			Map<String,Object> result = JSON.parseObject(jsonMsg);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR();
		}
	}
}