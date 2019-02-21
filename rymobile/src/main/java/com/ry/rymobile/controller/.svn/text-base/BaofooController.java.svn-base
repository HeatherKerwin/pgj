package com.ry.rymobile.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Discountrecord;
import com.ry.core.entity.DistributeOrder;
import com.ry.core.entity.InquiryReply;
import com.ry.core.entity.IntegraltradingDetail;
import com.ry.core.entity.Member;
import com.ry.core.entity.Org;
import com.ry.core.entity.Systeminfo;
import com.ry.core.entity.Systeminfo.Type;
import com.ry.core.service.ActivityService;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.DistributeOrderService;
import com.ry.core.service.InquiryReplyService;
import com.ry.core.service.MemberService;
import com.ry.core.service.OrgInfoService;
import com.ry.core.service.OrgService;
import com.ry.core.service.PaidanService;
import com.ry.core.service.SysteminfoService;
import com.ry.core.service.ThirdAuthService;
import com.ry.core.util.JPushUtil;
import com.ry.util.DateUtil;
import com.ry.util.SendMessagesUtil;
import com.ry.util.baofoo.BaofooUtil;
import com.ry.util.baofoo.util.HttpUtil;
import com.ry.util.baofoo.util.JXMConvertUtil;

import net.sf.json.JSONObject;

/**
 * 宝付支付
 * @author WKX
 */
@Controller
@RequestMapping("/baofoo")
public class BaofooController {
	
	private static Logger logger = Logger.getLogger(BaofooController.class);
	
	@Resource
	ThirdAuthService thirdAuthService;
	
	@Resource
	MemberService memberService;
	
	@Resource
	InquiryReplyService inquiryReplyService;
	
	@Resource
	DiscountrecordService discountrecordService;
	
	@Resource
	DistributeOrderService distributeOrderService;
	
	@Resource
	OrgService orgService;
	
	@Resource
	OrgInfoService orgInfoService;
	
	@Resource
	SysteminfoService systeminfoService;
	
	@Resource
	PaidanService paidanService;
	
	@Resource
	ActivityService activityService;
	
	/**
	 * 绑定页面
	 * @author WKX
	 * @param orderNo 订单号
	 * @param type 类型（0查询查复、1贴现、2接单）
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping("/bindcard")
	public String bindCard(String orderNo,Integer type,Model model){
		try {
			Map<String, String> order = this.getOrder(orderNo,type);
			model.addAttribute("order", order);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "baofoo/bindcard";
	}
	
	/**
	 * 绑定-发送验证码（快捷支付1）
	 * @author WKX
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping("/baofoo1")
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
	 * 绑卡（快捷支付2）
	 * @author WKX
	 * @param unique_code 预绑卡唯一码
	 * @param sms_code 验证码
	 * @param model
	 * @throws Exception
	 * @since 2017年5月24日 下午2:10:11
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
	 * 解除绑卡（快捷支付3）
	 * @author WKX
	 * @param type 类型3
	 * @param bind_id 预绑卡唯一标识
	 * @param user_id 用户ID
	 * @param model
	 * @throws Exception
	 * @since 2017年4月18日 下午4:14:40
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
	 * 支付页面（快捷支付4）
	 * @author WKX
	 * @param orderNo 商户订单号
	 * @param type 类型（0查询查复、1贴现、2接单）
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping("/pay")
	public String pay(String orderNo,Integer type,Model model){
		try {
			Map<String,String> param = new HashMap<String,String>();
			Map<String,String> order = this.getOrder(orderNo,type);
			
			model.addAttribute("order", order);
			param.put("type","4");
			param.put("user_id",order.get("memberId"));//用户ID
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
	 * 支付-发送验证码（快捷支付5）
	 * @author WKX
	 * @param unique_code 预绑卡唯一码
	 * @param model
	 * @throws Exception
	 * @since 2017年4月17日 上午11:22:55
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
	 * 获取设备指纹（前页面已经在服务器端注册过设备指纹）
	 * @author WKX
	 * @param session_id 商户号+商户订单号
	 * @param model
	 * @throws IOException
	 * @since 2017年4月18日 下午3:37:28
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
	 * 支付（快捷支付6）
	 * @author WKX
	 * @param business_no 宝付业务流水号
	 * @param sms_code 短信验证码
	 * @param trans_id 商户订单号
	 * @param type 类型（0查询查复、1贴现、2接单）
	 * @param model
	 * @throws Exception
	 * @since 2017年5月24日 下午3:49:06
	 */
	@RequestMapping("/baofoo6")
	public String baofoo6(String business_no,String sms_code,String trans_id,Integer type,Model model) throws Exception{
		Map<String,String> param = new HashMap<String,String>();
		param.put("type","6");
		param.put("business_no",business_no);//宝付业务流水号
		param.put("sms_code",sms_code);//验证码
		
		if(StringUtils.isNotBlank(trans_id)){//商户订单号
			String res = BaofooUtil.Api(param);
			model.addAttribute("retValue", res);
			Map<String,Object> map = JXMConvertUtil.IteratorHash(JSONObject.fromObject(res));
			if(map!=null && map.get("resp_code")!=null && "0000".equals(map.get("resp_code").toString())){//支付成功
				Object no = map.get("trans_no");
				String trans_no = null;
				if(no!=null)trans_no = no.toString();
				if(type!=null && type==0){//查询查复
					List<InquiryReply> list = inquiryReplyService.getByNo(trans_id);
					InquiryReply ir = list.get(0);
					ir.setPayState(1);//初始状态（已支付）
					ir.setJyh(trans_no);//保存交易号
					inquiryReplyService.saveInquiryReplyAndPayRecord(ir, 0, "支付成功");
					List<IntegraltradingDetail> listactivity = activityService.getlistActivity("查询查复",DateUtil.formart(new Date(), DateUtil.FORMART3),ir.getMemberId());
					if(listactivity != null && listactivity.size()<5){
						activityService.timingIntegral(ir.getMemberId(), 20, "查询查复", null);
					}
					model.addAttribute("redirect_url", "/app/baofoo/inquiryReply?orderNo="+map.get("trans_no")+"&orderAmount="+map.get("succ_amt"));
				}else if(type!=null && type==1){//贴现
					Discountrecord disc = discountrecordService.getByBnsNoDiscount(trans_id);
					disc.setDepositState(1);//初始状态（已支付）
					disc.setJyh(trans_no);
					pushAndSend(disc.getMemberid(), disc.getId(), Type.DISCOUNTRECORD, "已支付", "亲，您的订单保证金已支付成功，我们将立马处理您的订单！", "SMS_10621004");
					paidanService.updateAndPayRecord(disc, 0, "已支付");
					
					List<IntegraltradingDetail> listactivity = activityService.getlistActivity("下单支付",DateUtil.formart(new Date(), DateUtil.FORMART3),disc.getMemberid() );
					if(listactivity != null && listactivity.size()<5){
						activityService.timingIntegral(disc.getMemberid(), 20, "下单支付", null);
					}
					
					model.addAttribute("redirect_url", "/app/baofoo/discountrecord?orderNo="+map.get("trans_no")+"&orderAmount="+map.get("succ_amt"));
				}else if(type!=null && type==2){//接单
					DistributeOrder dist = distributeOrderService.getByBnsno(trans_id);
					dist.setDepositState(1);//初始状态（已支付）
					dist.setJyh(trans_no);
					pushAndSend(dist.getOrgId(), dist.getId(), Type.DISTRIBUTEORDER, "已支付", "亲，您的订单保证金已支付成功，还请赶快处理订单吧！", "SMS_10611169");
					dist.setPayWay(4);//支付方式
					distributeOrderService.updateAndPayRecord(dist, 0, "已支付",null);
					
					Org activityorg = orgService.getById(dist.getOrgId());
					List<IntegraltradingDetail> listactivity = activityService.getlistActivity("接单支付",DateUtil.formart(new Date(), DateUtil.FORMART3),activityorg.getMemberId());
					if(listactivity != null && listactivity.size()<5){
						activityService.timingIntegral(activityorg.getMemberId(), 20, "接单支付", null);
					}
					
					model.addAttribute("redirect_url", "/app/baofoo/distributeOrder?orderNo="+map.get("trans_no")+"&orderAmount="+map.get("succ_amt"));
				}
				model.addAttribute("code", "T");//成功
				return "baofoo/result";
			}else{
				model.addAttribute("code", "F");//失败
				return "baofoo/result";
			}
		}
		return "ajax";
	}
	
	/**
	 * 获取订单信息（多种订单）
	 * @author WKX
	 * @param orderId 商户订单号
	 * @param type 类型（0查询查复、1贴现、2接单）
	 * @throws Exception
	 * @since 2017年5月23日 下午3:16:42
	 */
	private Map<String,String> getOrder(String orderNo,Integer type) throws Exception{
		Map<String,String> result = new HashMap<String,String>();
		Integer memberId = null;
		String orderDate = null;//订单时间
		String name = null;//资金用途
		String money = null;//资金用途
		Map<String,Object> io = new HashMap<String, Object>();
		if(type==null)throw new Exception();
		if(type==0){//查询查复
			List<InquiryReply> list = inquiryReplyService.getByNo(orderNo);
			if(list!=null && list.size()>0){
				InquiryReply ir = list.get(0);
				orderNo = ir.getNo();//商户订单号
				orderDate = DateUtil.formart(ir.getCreateTime(),DateUtil.FORMART3);
				name = "查询查复";
				money = "30.00";
				if(ir.getMemberId()!=null && ir.getOrgType()!=null){
					io = orgInfoService.getByMemberIdAndType(ir.getMemberId(), ir.getOrgType());
					memberId = ir.getMemberId();
				}
			}
		}else if(type==1){//贴现
			Discountrecord disc = discountrecordService.getByBnsNoDiscount(orderNo);
			if(disc!=null){
				memberId = disc.getMemberid();
				orderNo = disc.getBnsNo();//商户订单号
				orderDate = DateUtil.formart(disc.getOrdertime(),DateUtil.FORMART3);
				name = "贴现保证金";
				Float m = disc.getDeposit();
				if(m!=null)money = m.toString();
				io = orgInfoService.getByMemberIdAndType(disc.getMemberid(), 0);
			}
		}else if(type==2){//接单
			DistributeOrder dist = distributeOrderService.getByBnsno(orderNo);
			if(dist!=null){
				orderNo = dist.getBnsNo();//商户订单号
				orderDate = DateUtil.formart(dist.getCreateTime(),DateUtil.FORMART3);
				name = "接单保证金";
				Float m = dist.getDeposit();
				if(m!=null)money = m.toString();
				io = orgInfoService.getByOrgIdAndType(dist.getOrgId(), 1);
				if(io!=null && io.get("member_id")!=null){
					memberId = Integer.valueOf(io.get("member_id").toString());
				}else{
					Org o = orgService.getById(dist.getOrgId());
					memberId = o.getMemberId();
				}
			}
		}
		if(memberId!=null)result.put("memberId", memberId.toString());
		result.put("orderNo", orderNo);
		result.put("orderDate", orderDate);
		result.put("name", name);
		result.put("money", money);
//		result.put("money", "1.00");
		result.put("type", type.toString());
		return result;
	}
	
	/**
	 * 推送消息、保存消息、发送短信
	 * @author WKX
	 * @param memberId 用户主键
	 * @param fkId 外键
	 * @param type 外键类型
	 * @param alert 提醒
	 * @param des 备注
	 * @param smsCode 短信编号
	 */
	private void pushAndSend(Integer memberId,Integer fkId,Type type,String alert,String des,String smsCode){
		try {
			Member member = memberService.getById(memberId);
			if(member!=null){
				Systeminfo systeminfo = new Systeminfo();
				systeminfo.setMemberId(member.getId());
				systeminfo.setType(type);
				systeminfo.setAlert(alert);
				systeminfo.setContent(des);
				systeminfo.setDiscountrecordId(fkId);//外键（对应类型）
				systeminfo.setCreateTime(new Date());
				systeminfoService.addSysteminfo(systeminfo);
				
				Map<String,String> param = new HashMap<String, String>();
				SendMessagesUtil.sendMessage(member.getMobile(), smsCode, param);
				
				JPushUtil.doPushJob(member.getMobile(), type, des);
			}
		} catch (Exception e) {
			logger.error("订单流转消息发送异常："+e.getMessage());
			e.printStackTrace();
		}
	}
}