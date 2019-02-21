package com.ry.rycms.task;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ry.core.entity.Discountrecord;
import com.ry.core.entity.DistributeOrder;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.DistributeOrderService;
import com.ry.core.util.Constant;
import com.ry.util.Bill99Util;
import com.ry.util.baofoo.BaofooUtil;

/**
 * 快钱定时退款（因为快钱对应的银行退款要有时间限制，所以要定时退款）
 * 退款成功更新状态
 * @author WKX
 * @EDIT WKX 退款添加宝付定时任务
 */
public class Bill99Task {

	@Resource
	private DiscountrecordService discountrecordService;
	
	@Resource
	private DistributeOrderService distributeOrderService;

	private static Logger logger = Logger.getLogger(Bill99Task.class);

	public void execute() {
		try {
			String path = Constant.properties.getProperty("BAOFOO_RETURNURL");
			Map<String, String> paras = null;
			List<Discountrecord> disc_list = discountrecordService.getByRefundState(0);//待退款
			List<DistributeOrder> dist_list = distributeOrderService.getByRefundState(0);//待退款
			if(disc_list!=null && disc_list.size()>0){
				for(Discountrecord disc:disc_list){
					if(disc.getPayWay() == 3){//块钱
						paras = new LinkedHashMap<String, String>();
						paras.put("orderid", disc.getBnsNo());//商户订单号
						paras.put("amount", ""+disc.getDeposit());//退款金额，整数或小数，小数位为2位   以人民币元为单位
						Map<String, String> res = Bill99Util.receive(paras);
						if(res!=null && "Y".equals(res.get("RESULT"))){//退款成功
							disc.setRefundState(1);//退款成功
							discountrecordService.updateModel(disc);
						}
					}else if(disc.getPayWay() == 4){//宝付
						Map<String,String> param = new HashMap<String,String>();
						param.put("bnsNo",disc.getBnsNo());//原商户发起的支付订单号
						param.put("money",disc.getDeposit().toString());//退款金额，整数或小数，小数位为2位   以人民币元为单位
						param.put("returnUrl", path + "/baofoo/refund");//退款结果回调链接
						param.put("reason","退还接单押金");//退款原因
						if("0000".equals(BaofooUtil.RefundFrom(param))){
							disc.setRefundState(2);//退款中（宝付）
							discountrecordService.updateModel(disc);
						}
					}
					Thread.sleep(3000);
				}
			}
			if(dist_list!=null && dist_list.size()>0){
				for(DistributeOrder dist:dist_list){
					if(dist.getPayWay() == 3){//块钱
						paras = new LinkedHashMap<String, String>();
						paras.put("orderid", dist.getBnsNo());//商户订单号
						paras.put("amount", ""+dist.getDeposit());//退款金额，整数或小数，小数位为2位   以人民币元为单位
						Map<String, String> res = Bill99Util.receive(paras);
						if(res!=null && "Y".equals(res.get("RESULT"))){//退款成功
							dist.setRefundState(1);//退款成功
							distributeOrderService.updateDistributeOrder(dist);
						}
					}else if(dist.getPayWay() == 4){//宝付
						Map<String,String> param = new HashMap<String,String>();
						param.put("bnsNo",dist.getBnsNo());//原商户发起的支付订单号
						param.put("money",dist.getDeposit().toString());//退款金额，整数或小数，小数位为2位   以人民币元为单位
						param.put("returnUrl", path + "/baofoo/refund");//退款结果回调链接
						param.put("reason","退还接单押金");//退款原因
						if("0000".equals(BaofooUtil.RefundFrom(param))){
							dist.setRefundState(2);//退款中（宝付）
							distributeOrderService.updateDistributeOrder(dist);
						}
					}
					Thread.sleep(3000);
				}
			}
		} catch (Exception e) {
			logger.error("快钱（宝付）定时退款异常：" + e.getMessage());
			e.printStackTrace();
		}
	}
}