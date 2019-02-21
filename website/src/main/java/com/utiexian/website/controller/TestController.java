package com.utiexian.website.controller;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.News;
import com.ry.core.form.NewsForm;
import com.ry.core.service.NewsService;
import com.ry.core.util.Constant;
import com.ry.util.AlipayUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.StringUtil;
import com.ry.util.page.PageResults;

/**
 * 此为测试
 * @author WKX
 */
@Controller
public class TestController {
	
	@Resource
	NewsService newsService;

	/**
	 * 测试未登录，到调整登录页面后，能返回之前的页面
	 * 注：登录拦截器参照web.xml
	 * @author WKX
	 * @param request
	 * @since 2016年10月20日 下午7:02:43
	 */
	@RequestMapping("/test")
	public String test(HttpServletRequest request){
		return "test";
	}
	
	/**
	 * 跳转等待的页面
	 * @author MH
	 * @return
	 */
	@RequestMapping("/loading")
	public String loading(){
		return "loading";
	}
	
	/**
	 * 测试银票
	 * 注：登录拦截器参照web.xml
	 * @author WKX
	 * @param request
	 * @since 2016年10月20日 下午7:02:43
	 */
	@RequestMapping("/discountrecord/test1")
	public String test1(HttpServletRequest request){
		return "discountorder/discountorder_yp1";
	}
	
	/**
	 * 测试商票
	 * 注：登录拦截器参照web.xml
	 * @author WKX
	 * @param request
	 * @since 2016年10月20日 下午7:02:43
	 */
	@RequestMapping("/discountrecord/test2")
	public String test2(HttpServletRequest request){
		return "bisic_message/discountplace";
	}
	
	/**
	 * 地图
	 * @author WKX
	 * @param request
	 * @since 2016年10月21日 下午4:25:20
	 */
	@RequestMapping("/test/map")
	public String map(HttpServletRequest request){
		return "map";
	}
	
	/**
	 * 分页测试
	 * @author WKX
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @since 2016年10月21日 下午2:27:31
	 */
	@RequestMapping("/test/news")
	public String inqueryReplyList(String name,Integer pageIndex,Integer pageSize,Model model){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			if(pageIndex==null)pageIndex = 0;
			if(pageSize==null)pageSize = 10;
			
			NewsForm nf = new NewsForm();
			PageResults<News> pageResults = newsService.getPageList(nf, pageIndex, pageSize);
			map.put("response", "success");
			map.put("msg", "操作成功");
			map.put("data",pageResults);
		}catch(Exception e){
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "获取失败");
		}	
		model.addAttribute("retValue", JacksonUtil.objToJson(map));
		return "ajax";
	}
	
	/**
	 * 跳转支付页面
	 * @author WKX
	 * @param request
	 * @since 2016年10月24日 下午5:57:48
	 */
	@RequestMapping("/test/pay")
	public String pay(HttpServletRequest request){
		return "pay";
	}
	
	/**
	 * 支付回调
	 * @param out_trade_no 商户订单号
	 * @param is_success 成功标识 T、F
	 * @param request
	 * @since 2016年10月25日 上午10:20:40
	 */
	@RequestMapping("/test/pay/callback1")
	public String payCallback(String out_trade_no,String is_success){
		System.err.println(out_trade_no);
		System.err.println(is_success);
		return StringUtil.processRedirectUrl(Constant.properties.getProperty("baseUrl"),"/test/pay/callback/redirect1");
	}
	
	/**
	 * 支付回调（重定向订单列表）
	 * @author WKX
	 * @param request
	 * @since 2016年10月25日 上午10:09:36
	 */
	@RequestMapping("/test/pay/callback/redirect1")
	public String redirectCallback(){
		return "pay_callback";
	}
	
	/**
	 * 异步回调，根据支付状态更新订单
	 * @author WKX
	 * @param out_trade_no 商户订单号
	 * @param trade_no 交易号
	 * @param buyer_email 支付账号
	 * @param trade_status 【如下】
	 * 【WAIT_BUYER_PAY：交易创建，等待买家付款。】
	 * 【TRADE_CLOSED：在指定时间段内未支付时关闭的交易；在交易完成全额退款成功时关闭的交易。】
	 * 【TRADE_SUCCESS：交易成功，且可对该交易做操作，如：多级分润、退款等。】
	 * 【TRADE_PENDING：等待卖家收款（买家付款后，如果卖家账号被冻结）。】
	 * 【TRADE_FINISHED：交易成功且结束，即不可再做任何操作】
	 * @since 2016年10月28日 下午4:19:39
	 */
	@RequestMapping("/test/pay/cb1")
	public String cbPay(String out_trade_no,String trade_no,String buyer_email,String trade_status){
		System.err.println("------"+out_trade_no);
		System.err.println("------"+trade_status);
		return "ajax";
	}
	
	/**
	 * 支付宝付款
	 * @author WKX
	 * @param out_trade_no 商户订单号
	 * @param subject 商品名称
	 * @param total_fee 付款金额
	 * @param body 商品描述
	 * @param model
	 * @throws Exception
	 * @since 2016年10月24日 下午5:56:04
	 */
	@RequestMapping("/test/pay/do")
	public String toPay(String out_trade_no,String subject,String total_fee,String body,HttpServletRequest request,Model model) throws Exception{
		String ip = InetAddress.getLocalHost().getHostAddress();
		int port = request.getLocalPort();
		String cp = request.getContextPath();
		String path = "http://"+ip+":"+port+cp;
		
		Map<String, String> paras = new HashMap<String, String>();
		paras.put("goods_type", "0");//商品类型:1表示实物类商品、0表示虚拟类商品
		paras.put("return_url", path+"/test/pay/callback1");//此链接需要配置线上地址（并外网可访问）
		paras.put("notify_url", path+"/test/pay/cb1");//此链接需要配置线上地址（并外网可访问）

		paras.put("out_trade_no", out_trade_no);
		paras.put("subject", subject);
		paras.put("total_fee", total_fee);
		paras.put("body", body);
		model.addAttribute("retValue",AlipayUtil.createDirectPay(paras));
		return "ajax";
	}
	
	/**
	 * 订单查询
	 * @author WKX
	 * @param out_trade_no
	 * @param model
	 * @throws Exception
	 * @since 2016年10月25日 上午10:42:22
	 */
	@RequestMapping("/test/query")
	public String payCallback(String out_trade_no,Model model) throws Exception{
		Map<String,Object> result = AlipayUtil.tradeQuery(out_trade_no);
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	public static void main(String[] args) {
		try {
			InetAddress ia=InetAddress.getLocalHost();
			String localip=ia.getHostAddress();
			System.out.println("本机的ip是 ："+localip);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}