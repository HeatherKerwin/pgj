package com.ry.ryapp.controller;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ry.core.entity.Admin;
import com.ry.core.entity.Discountrecord;
import com.ry.core.entity.Member;
import com.ry.core.service.AdminService;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.MemberService;
import com.ry.core.service.ShiborService;
import com.ry.core.service.SitestatisticsService;
import com.ry.util.Bill99Util;
import com.ry.util.DateUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.MD5Util;

@Controller
public class TestController {
	@Resource
	ShiborService shiborService;
	
//	@Resource
//	CrawlerService crawlerService;
	
	@Resource
	SitestatisticsService sitestatisticsService;
	
	@Resource
	MemberService memberService;
	
	@Resource
	DiscountrecordService discountrecordService;
	
	@Resource
	AdminService adminService;
	
	@RequestMapping("/getdata/shibor/")
	public String shibor(){
//		crawlerService.crawShiborInfo();
//		shiborService.saveShibor(shibor);;
		return "ajax";
	}
	@RequestMapping("/test/dataemail/")
	public String dataemail(){
		sitestatisticsService.emailData();
		return "ajax";
	}
	
	/**
	 * 初始化加密（用户，订单）
	 * @author WKX
	 * @param name
	 * @param pwd
	 * @since 2016年8月22日 下午1:10:04
	 */
	@RequestMapping("/app/encode")
	public @ResponseBody String init(String name,String pwd){
		try {
			if(StringUtils.isBlank(name) || StringUtils.isBlank(pwd))throw new Exception("please login...");
			Admin admin = adminService.login(null, name, MD5Util.getMD5Str(pwd));
			if(admin!=null){
				List<Member> list = memberService.getListByMobile(null);
				if(list!=null){
					for(Member m:list){
						memberService.updateMember(m);
					}
				}
				List<Discountrecord> list1 = discountrecordService.getList("");
				if(list1!=null){
					for(Discountrecord d:list1){
						discountrecordService.updateModel(d);
					}
				}
			}else{
				return "login failed...";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "success...";
	}
	
	/**
	 * 回调后台（同支付宝一样，隔一段时间也会回调一次）
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
	 * @since 2017年2月24日 上午10:13:01
	 */
	@RequestMapping("/test/pay/cb")
	public @ResponseBody String cbPay(String merchantAcctId,String bankId,String orderId,String orderTime,String orderAmount,String bindCard,String bindMobile,String dealId,String bankDealId,String fee,String payResult,Model model) throws Exception{
		System.err.println(merchantAcctId);
		System.err.println(bankId);
		System.err.println(orderId);
		System.err.println(orderTime);
		System.err.println(orderAmount);
		System.err.println(bindCard);
		System.err.println(bindMobile);
		System.err.println(dealId);
		System.err.println(bankDealId);
		System.err.println(fee);
		System.err.println(payResult);
		return "<result>1</result><redirecturl>http://180.166.201.178:8080/ryapp/test/pay/cb/page</redirecturl>";
	}
	
	/**
	 * 回调页面
	 * @author WKX
	 * @throws Exception
	 * @since 2017年2月24日 上午10:39:43
	 */
	@RequestMapping("/test/pay/cb/page")
	public String cbPayPage(String merchantAcctId,String bankId,String orderId,String orderTime,String orderAmount,String bindCard,String bindMobile,String dealId,String bankDealId,String fee,String payResult,Model model) throws Exception{
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
		model.addAttribute("productName","王者农药");
		return "test";
	}
	
	/**
	 * 支付测试
	 * @author WKX
	 * @param model
	 * @throws Exception
	 * @since 2017年2月21日 下午6:31:19
	 */
	@RequestMapping("/test/pay")
	public String toPay(String orderAmount,String payerId,String orderId,Model model) throws Exception{
		Map<String, String> paras = new LinkedHashMap<String, String>();

		if(StringUtils.isBlank(orderAmount))orderAmount = "1";//1分钱
		paras.put("orderId", orderId);//商户订单号
		paras.put("payerId", payerId);//用户标识
		paras.put("orderAmount", orderAmount);//订单金额（分）
		paras.put("orderTime", new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()));//数量
		paras.put("productName", "王者农药");//产品名称
		paras.put("productNum", "1");//数量
		paras.put("bgUrl", "http://180.166.201.178:8080/ryapp/test/pay/cb");//后台回调
		paras.put("pageUrl", "http://180.166.201.178:8080/ryapp/test/pay/cb/page");//回调页面
		model.addAttribute("retValue",Bill99Util.buildRequestPayForWap(paras));
		return "ajax";
	}
	
	/**
	 * 订单查询
	 * @author WKX
	 * @param orderId 商户订单号
	 * @param model
	 * @throws Exception
	 * @since 2017年3月2日 下午2:01:12
	 */
	@RequestMapping("/test/query")
	public String query(String orderId,Model model) throws Exception{
		Map<String, String> paras = new LinkedHashMap<String, String>();
		paras.put("orderId", orderId);//商户订单号
		model.addAttribute("retValue",JacksonUtil.objToJson(Bill99Util.query(paras)));
		return "ajax";
	}
	
	/**
	 * 订单退款
	 * @author WKX
	 * @param orderId 商户订单号
	 * @param model
	 * @throws Exception
	 * @since 2017年3月2日 下午2:01:12
	 */
	@RequestMapping("/test/receive")
	public String receive(String orderId,String amount,Model model) throws Exception{
		Map<String, String> paras = new LinkedHashMap<String, String>();
		paras.put("orderid", orderId);//商户订单号（小写）
		paras.put("amount", ""+Double.valueOf(amount)/100);//退款金额，整数或小数，小数位为2位   以人民币元为单位
		
		Map<String, String> res = Bill99Util.receive(paras);
		model.addAttribute("retValue",JacksonUtil.objToJson(res));
		return "ajax";
	}
}