package com.utiexian.rywap.controller;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ry.core.entity.DistributeOrder;
import com.ry.core.entity.DistributeOrderSp;
import com.ry.core.service.DistributeOrderService;
import com.ry.core.service.DistributeOrderSpService;
import com.ry.core.util.Constant;

/**
 * 订单（接单）
 */
@Controller
@RequestMapping(value="/order",produces="text/html;charset=UTF-8")
public class OrderController {

	@Resource
	DistributeOrderService distributeOrderService;
	
	@Resource
	DistributeOrderSpService distributeOrderSpService;
	
	/**
	 * 页面正在跳转
	 * @author WKX
	 */
	@RequestMapping("/loading")
	public String loading(Model model){
		return "loading";
	}
	
	/**
	 * [块钱]保证金支付
	 * @author WKX
	 * @param money 金额
	 * @param memberId 用户主键
	 * @param model 商户订单号
	 */
	@RequestMapping("/pay")
	public @ResponseBody String pay(Integer orderId,String orderType,Model model){
		String path_cb = Constant.properties.getProperty("baseUrl");
		if(StringUtils.isBlank(path_cb) || orderId==null || StringUtils.isBlank(orderType))return "请求异常！请联系客服";
		
		String src = path_cb + "/order/rechargeurl?orderId=" + orderId + "&orderType=" + orderType;
		StringBuffer res = new StringBuffer("<html>");
		res.append("<head></head>");
		res.append("<body>");
		res.append("<iframe style='width:100%;height:100%;' frameborder='no' border='0' scrolling='no' src='" + src + "'></iframe>");
		res.append("</body>");
		res.append("</html>");
		return res.toString();
	}
	
	/**
	 * [块钱]保证金支付
	 * @author WKX
	 * @param money 金额
	 * @param memberId 用户主键
	 * @param model 商户订单号
	 */
	@RequestMapping("/rechargeurl")
	public @ResponseBody String rechargeUrl(Integer orderId,String orderType,Model model){
		String res = "";
		try {
			if(orderId==null || StringUtils.isBlank(orderType))throw new Exception();
			if("DISTRIBUTEORDER".equals(orderType)){
				DistributeOrder dist = distributeOrderService.getById(orderId);
				if(dist!=null && StringUtils.isNotBlank(dist.getRechargeUrl())){
					res = dist.getRechargeUrl();
				}
			}else if("DISTRIBUTEORDERSP".equals(orderType)){
				DistributeOrderSp dist = distributeOrderSpService.getById(orderId);
				if(dist!=null && StringUtils.isNotBlank(dist.getRechargeUrl())){
					res = dist.getRechargeUrl();
				}
			}
			if(StringUtils.isNotBlank(res)){
				byte[] encoded = Base64.decodeBase64(res);
				res = new String(encoded);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
}