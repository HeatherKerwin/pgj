package com.utiexian.website.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ry.core.Enum.AccountLogState;
import com.ry.core.Enum.AccountLogType;
import com.ry.core.Enum.PayWay;
import com.ry.core.entity.AccountLog;
import com.ry.core.service.AccountLogService;
import com.ry.core.service.AccountService;
import com.ry.core.util.Constant;
import com.ry.util.AlipayUtil;
import com.ry.util.Bill99Util;
import com.ry.util.DateUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.StringUtil;
import com.ry.util.baofoo.BaofooUtil;
import com.ry.util.baofoo.util.HttpUtil;
import com.ry.util.baofoo.util.JXMConvertUtil;

import net.sf.json.JSONObject;

/**
 * 账户充值（充值记录）
 * @author WKX
 * 备注：（以下是两个支付方式）
 */
@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Resource
	AccountService accountService;
	
	@Resource
	AccountLogService accountLogService;
	
	/**
	 * 支付
	 * @author WKX
	 * @param money 金额（元）
	 * @param memberId 用户主键
	 * @param type 支付方式[BILL99块钱、BAOFOO宝付、ALIPAY支付宝]
	 * @param model
	 */
	@RequestMapping("/pay")
	public String pay(Float money,String memberId,String type,Model model){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(money<1){
				String msg = "您输入的金额不合法！";
				result.put("msg", msg);
				result.put("response", "failed");
				model.addAttribute("retValue",msg);
				return "ajax";
			}
			
			AccountLog log = new AccountLog();
			log.setAccountLogState(AccountLogState.WAITIN);//待入账
			log.setAccountLogType(AccountLogType.IN);
			log.setCreateTime(new Date());
			log.setMemberId(Integer.valueOf(memberId));
			log.setMoney(money);
			log.setFee(0F);
			log.setMoneyInto(money);
			if(type.equals("BILL99")){
				log.setWay(PayWay.CZ_BILL99);
				accountLogService.saveModel(log);
				return this.bill99(log.getNo(), model);
			}else if(type.equals("BAOFOO")){
				log.setWay(PayWay.CZ_BAOFOO);
				accountLogService.saveModel(log);
				return this.baofoo(log.getNo(), model);
			}else if(type.equals("ALIPAY")){
				log.setWay(PayWay.CZ_ALIPAY);
				accountLogService.saveModel(log);
				return this.alipay(log.getNo(), model);
			}
			result.put("response", "success");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", "数据异常！");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	//-----------------------------------------------块钱支付------------------------------------------------------------------
	
	/**
	 * [块钱]保证金支付
	 * @author WKX
	 * @param money 金额
	 * @param memberId 用户主键
	 * @param model 商户订单号
	 */
	@RequestMapping("/bill99/pay")
	public String bill99(String no,Model model){
		String path_cb = Constant.properties.getProperty("baseUrl");
		Map<String, String> paras = new LinkedHashMap<String, String>();
		try {
			AccountLog log = accountLogService.getByNo(no);
			
			paras.put("orderId", log.getNo());//商户订单号
			paras.put("payerId", log.getMemberId()+"");//用户标识
			paras.put("orderAmount", (int)(log.getMoneyInto()*100)+"");//订单金额（分）
			paras.put("orderTime", new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()));//订单提交时间
			paras.put("productName", "保证金（充值）");//产品名称
			paras.put("productNum", "1");//数量
			paras.put("bgUrl", path_cb + "account/bill99/pay/cb");//后台回调
			paras.put("pageUrl", path_cb + "account/bill99/pay/cb/page");//回调页面
			model.addAttribute("retValue",Bill99Util.buildRequestPay(paras));
		} catch (Exception e) {
			model.addAttribute("retValue","数据异常！");
			e.printStackTrace();
		}
		return "ajax";
	}
	
	/**
	 * [块钱]保证金支付（同支付宝一样，隔一段时间也会回调一次）
	 * @author WKX
	 * @param merchantAcctId 人民币账号
	 * @param bankId 银行代码
	 * @param orderId 商户订单号
	 * @param orderTime 订单交易时间
	 * @param orderAmount 商户订单金额
	 * @param bindCard 已绑短卡号
	 * @param bindMobile 已绑短手机尾号
	 * @param dealId 快钱交易号
	 * @param bankDealId 银行交易号
	 * @param fee 费用
	 * @param payResult 处理结果 (10：支付成功)
	 * @throws Exception
	 */
	@RequestMapping("/bill99/pay/cb")
	public @ResponseBody String cbPay(String merchantAcctId,String bankId,String orderId,String orderTime,String orderAmount,String bindCard,String bindMobile,String dealId,String bankDealId,String fee,String payResult,Model model) throws Exception{
		String path_cb = Constant.properties.getProperty("baseUrl");
		String page = path_cb + "/account/bill99/pay/cb/page";
		
		AccountLog log = accountLogService.getByNo(orderId);
		if(log!=null && "10".equals(payResult)){
			log.setJyh(dealId);
			accountService.updateMoneyByLog(log);
		}
		return "<result>1</result><redirecturl>" + page + "</redirecturl>";
	}
	
	/**
	 * [块钱支付]成功回调页面
	 * @author WKX
	 * @param merchantAcctId 人民币账号
	 * @param bankId 银行代码
	 * @param orderId 商户订单号
	 * @param orderTime 订单交易时间
	 * @param orderAmount 商户订单金额
	 * @param bindCard 已绑短卡号
	 * @param bindMobile 已绑短手机尾号
	 * @param dealId 快钱交易号
	 * @param bankDealId 银行交易号
	 * @param fee 费用
	 * @param payResult 处理结果 (10：支付成功)
	 * @throws Exception
	 */
	@RequestMapping("/bill99/pay/cb/page")
	public String cbPayPageDisc(String merchantAcctId,String bankId,String orderId,String orderTime,String orderAmount,String bindCard,String bindMobile,String dealId,String bankDealId,String fee,String payResult,Model model) throws Exception{
		model.addAttribute("merchantAcctId",merchantAcctId);
		model.addAttribute("bankId",bankId);
		model.addAttribute("orderId",orderId);
		if(StringUtils.isNotBlank(orderTime)){
			Date src = DateUtil.parser(orderTime, "yyyyMMddHHmmss");
			model.addAttribute("orderTime",DateUtil.formart(src, "yyyy/MM/dd"));
		}
		if(StringUtils.isNotBlank(orderAmount))model.addAttribute("orderAmount",""+Double.valueOf(orderAmount)/100);
		model.addAttribute("bindCard",bindCard);
		model.addAttribute("bindMobile",bindMobile);
		model.addAttribute("dealId",dealId);
		model.addAttribute("bankDealId",bankDealId);
		model.addAttribute("fee",fee);
		model.addAttribute("payResult",payResult);
		model.addAttribute("productName","保证金（充值）");
		model.addAttribute("way", "快钱支付");//支付方式
		return StringUtil.processRedirectUrl(Constant.properties.getProperty("baseUrl"), "");
	}
	
	//--------------------------------------支付宝---------------------------------------------------
	
		/**
		 * [支付宝]保证金支付
		 * @author WKX
		 * @param no 预充值订单号
		 */
		@RequestMapping("/alipay/pay")
		public String alipay(String no,Model model){
			String path_cb = Constant.properties.getProperty("baseUrl");
			Map<String, String> paras = new LinkedHashMap<String, String>();
			try {
				AccountLog log = accountLogService.getByNo(no);
				if(log==null || log.getAccountLogState()!=AccountLogState.WAITIN){
					model.addAttribute("retValue","提交支付请求失败！请联系客服");
					return "ajax";
				}
				
				paras.put("out_trade_no", log.getNo());
				paras.put("subject", "保证金（充值）");
				paras.put("total_fee", log.getMoneyInto().toString());
				paras.put("body", "保证金（充值）");
				paras.put("goods_type", "0");//商品类型:1表示实物类商品、0表示虚拟类商品
				paras.put("platform", "PC");//手机页面
				
				paras.put("notify_url", path_cb + "/account/alipay/pay/cb");//后台回调
				paras.put("return_url", path_cb + "/account/alipay/pay/cb/page");//回调页面
				model.addAttribute("retValue",AlipayUtil.createDirectPay(paras));
			} catch (Exception e) {
				model.addAttribute("retValue","数据异常！请联系客服");
				e.printStackTrace();
			}
			return "ajax";
		}
		
		/**
		 * 支付成功回调
		 * @author WKX
		 * @param out_trade_no 商户订单号
		 * @param trade_no 支付宝交易号
		 * @param trade_status 交易状态
		 * （WAIT_BUYER_PAY交易创建，等待买家付款）
		 * （TRADE_CLOSED未付款交易超时关闭，或支付完成后全额退款）
		 * （TRADE_SUCCESS交易支付成功）
		 * （TRADE_FINISHED交易结束，不可退款）
		 * @param model
		 */
		@RequestMapping("/alipay/pay/cb")
		public @ResponseBody String alipayPage(String out_trade_no,String trade_no,String trade_status,HttpServletRequest request,Model model) throws Exception{
			Map<String,String> params = new HashMap<String,String>();
			Map<?,?> requestParams = request.getParameterMap();
			for (Iterator<?> iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				params.put(name, valueStr);
			}
			params.remove("sign");
			params.remove("sign_type");
			String params_sign = AlipayUtil.buildRequestMysign(params);
			String sign = request.getParameter("sign");
			if(StringUtils.isBlank(sign) || !sign.equals(params_sign)){//验证签名
				return "fail";
			}
			
			AccountLog log = accountLogService.getByNo(out_trade_no);
			if(log!=null && ("TRADE_SUCCESS".equals(trade_status) || "TRADE_FINISHED".equals(trade_status))){
				log.setJyh(trade_no);
				accountService.updateMoneyByLog(log);
				return "success";
			}else{
				return "fail";
			}
		}
		
		/**
		 * 回调页面
		 * @author WKX
		 * @param out_trade_no 商户订单号
		 * @param trade_no 支付宝交易号
		 * @param trade_status 交易状态
		 * （WAIT_BUYER_PAY交易创建，等待买家付款）
		 * （TRADE_CLOSED未付款交易超时关闭，或支付完成后全额退款）
		 * （TRADE_SUCCESS交易支付成功）
		 * （TRADE_FINISHED交易结束，不可退款）
		 * @param model
		 */
		@RequestMapping("/alipay/pay/cb/page")
		public String alipay(String out_trade_no,String trade_no,String total_fee,String trade_status,HttpServletRequest request,Model model) throws Exception{
			AccountLog log = accountLogService.getByNo(out_trade_no);
			model.addAttribute("orderId",log.getNo());
			model.addAttribute("orderAmount",total_fee);
			model.addAttribute("way", "支付宝");//支付方式
			return StringUtil.processRedirectUrl(Constant.properties.getProperty("baseUrl"), "");
		}
	
	//-----------------------------------------------宝付支付------------------------------------------------------------------
	
	/**
	 * [宝付]绑定页面
	 * @author WKX
	 * @param orderNo 订单号
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping("/baofoo/bindcard")
	public String bindCard(String orderNo,Model model){
		try {
			AccountLog log = accountLogService.getByNo(orderNo);
			model.addAttribute("memberId", log.getMemberId());
			model.addAttribute("orderNo", log.getNo());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "baofoo/bindcard";
	}
	
	/**
	 * [宝付]绑定-发送验证码（快捷支付1）
	 * @author WKX
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping("/baofoo/baofoo1")
	public String baofoo1(String pay_code,String acc_no,String id_card,String id_holder,String mobile,String user_id,String valid_date,String cvv,Model model) throws Exception{
		Map<String,String> param = new HashMap<String,String>();
		
		param.put("type","1");
		param.put("pay_code",pay_code);//银行名称
		param.put("acc_no",acc_no);// 银行卡卡号
		param.put("id_card",id_card);// 身份证号码
		param.put("id_holder",id_holder);// 姓名
		param.put("mobile",mobile);// 银行预留手机号
		param.put("user_id",user_id);//用户ID
		
		param.put("valid_date",valid_date);//银行卡有效期
		param.put("cvv",cvv);//银行卡安全码
		
		String result = BaofooUtil.Api(param);
		model.addAttribute("retValue", result);
		return "ajax";
	}
	
	/**
	 * [宝付]绑卡（快捷支付2）
	 * @author WKX
	 * @param unique_code 预绑卡唯一码
	 * @param sms_code 验证码
	 * @param model
	 */
	@RequestMapping("/baofoo2")
	public String baofoo2(String unique_code,String sms_code,Model model) throws Exception{
		Map<String,String> param = new HashMap<String,String>();
		
		param.put("type","2");
		param.put("unique_code",unique_code);//预绑卡唯一码
		param.put("sms_code",sms_code);//验证码
		
		model.addAttribute("retValue", BaofooUtil.Api(param));
		return "ajax";
	}
	
	/**
	 * [宝付]解除绑卡（快捷支付3）
	 * @author WKX
	 * @param type 类型3
	 * @param bind_id 预绑卡唯一标识
	 * @param user_id 用户ID
	 * @param model
	 */
	@RequestMapping("/baofoo3")
	public String baofoo3(String type,String bind_id,String user_id,Model model) throws Exception{
		Map<String,String> param = new HashMap<String,String>();
		param.put("type",type);
		param.put("bind_id",bind_id);//预绑卡唯一码
		param.put("user_id",user_id);//用户ID
		model.addAttribute("retValue", BaofooUtil.Api(param));
		return "ajax";
	}
	
	/**
	 * [宝付]支付页面（快捷支付4）
	 * @author WKX
	 * @param orderNo 商户订单号
	 * @param model
	 */
	@RequestMapping("/baofoo/pay")
	public String baofoo(String orderNo,Model model){
		try {
			Map<String,String> param = new HashMap<String,String>();
			AccountLog log = accountLogService.getByNo(orderNo);
			
			model.addAttribute("memberId", log.getMemberId());
			model.addAttribute("money", log.getMoneyInto());
			model.addAttribute("name", "保证金（充值）");
			model.addAttribute("orderNo", log.getNo());
			model.addAttribute("orderDate", new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(log.getCreateTime()));
			
			param.put("type","4");
			param.put("user_id",log.getMemberId()+"");//用户ID
			String res = BaofooUtil.Api(param);
			Map<String,Object> map = JXMConvertUtil.IteratorHash(JSONObject.fromObject(res));
			model.addAttribute("card", map.get("card"));
			
			//获取设备指纹
			String time_str = System.currentTimeMillis()+"";
			String memberId = BaofooUtil.member_id;
			String strId = time_str + "_" + memberId + "_" + orderNo;	
			model.addAttribute("strId", strId);
			model.addAttribute("queryOrderNo", memberId + orderNo);
			model.addAttribute("orderNo", orderNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "baofoo/pay";
	}
	
	/**
	 * [宝付]支付-发送验证码（快捷支付5）
	 * @author WKX
	 * @param unique_code 预绑卡唯一码
	 * @param model
	 */
	@RequestMapping("/baofoo5")
	public String baofoo5(String user_id,String txn_amt,String trans_id,String bind_id,String device_id,Model model) throws Exception{
		Map<String,String> param = new HashMap<String,String>();
		
		param.put("type","5");
		param.put("bind_id",bind_id);//绑定 ID
		param.put("user_id",user_id);//用户 ID
		param.put("txn_amt",txn_amt);//金额
		param.put("trans_id",trans_id);//商户订单号
		param.put("device_id",device_id);//设备指纹
		
		model.addAttribute("retValue", BaofooUtil.Api(param));
		return "ajax";
	}
	
	/**
	 * [宝付]获取设备指纹（前页面已经在服务器端注册过设备指纹）
	 * @author WKX
	 * @param session_id 商户号+商户订单号
	 * @param model
	 */
	@RequestMapping("/zhiwen")
	public String DFQuery(String session_id,Model model) throws IOException{
		Map<String,String> HeadPostParam = new HashMap<String,String>();
        HeadPostParam.put("member_id", BaofooUtil.member_id);
        HeadPostParam.put("session_id", session_id);
        
        String res = HttpUtil.RequestForm("https://fk.baofoo.com/getDeviceMem", HeadPostParam);
		model.addAttribute("retValue",res);
		return "ajax";
	}
	
	/**
	 * [宝付]支付（快捷支付6）
	 * @author WKX
	 * @param business_no 宝付业务流水号
	 * @param sms_code 短信验证码
	 * @param trans_id 商户订单号
	 * @param model
	 */
	@RequestMapping("/baofoo/baofoo6")
	public String baofoo6(String business_no,String sms_code,String trans_id,Model model) throws Exception{
		Map<String,String> param = new HashMap<String,String>();
		param.put("type","6");
		param.put("business_no",business_no);//宝付业务流水号
		param.put("sms_code",sms_code);//验证码
		
		if(StringUtils.isNotBlank(trans_id)){//商户订单号
			String res = BaofooUtil.Api(param);
			model.addAttribute("retValue", res);
			Map<String,Object> map = JXMConvertUtil.IteratorHash(JSONObject.fromObject(res));
			if(map!=null && map.get("resp_code")!=null && "0000".equals(map.get("resp_code").toString())){//支付成功
				AccountLog log = accountLogService.getByNo(trans_id);
				if(log!=null){
					log.setJyh(business_no);
					accountService.updateMoneyByLog(log);
				}
				
				model.addAttribute("redirect_url", "/bisicmessage/deposit");
				model.addAttribute("code", "T");//成功
				return "baofoo/result";
			}else{
				model.addAttribute("code", "F");//失败
				return "baofoo/result";
			}
		}
		return "ajax";
	}
}