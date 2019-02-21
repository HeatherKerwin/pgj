package com.utiexian.website.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ry.core.entity.InquiryReply;
import com.ry.core.entity.Invoice;
import com.ry.core.entity.Member;
import com.ry.core.entity.Org;
import com.ry.core.entity.Region;
import com.ry.core.form.InquiryReplyForm;
import com.ry.core.service.InquiryReplyService;
import com.ry.core.service.InvoiceService;
import com.ry.core.service.MemberService;
import com.ry.core.service.OrgInfoService;
import com.ry.core.service.OrgService;
import com.ry.core.service.RegionService;
import com.ry.core.util.Constant;
import com.ry.util.AlipayUtil;
import com.ry.util.Bill99Util;
import com.ry.util.DateUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.StringUtil;
import com.ry.util.page.PageResults;

/**
 * 查询查复
 * @author WKX
 */
@Controller
public class InquiryReplyController {
	
	@Resource
	InquiryReplyService inquiryReplyService;
	
	@Resource
	MemberService memberService;
	
	@Resource
	OrgService orgService;
	
	@Resource
	OrgInfoService orginfoService;
	
	@Resource
	RegionService regionService;
	
	@Resource
	InvoiceService invoiceService;
	
	/**
	 * 跳转在线查询查复
	 * @author ZY
	 * 2016年11月1日下午6:11:15
	 */
	@RequestMapping("/inquiryreply/inquiryreply")
	public String loginMember(){
		return "inquiry_reply/inquiry";
	}
	
	/**
	 * 跳转成功页面
	 * @author ZY
	 * @param request
	 * 2016年11月1日下午6:11:15
	 */
	@RequestMapping("/inquiryreply/success")
	public String success(){
		return "inquiry_reply/success";
	}
	
	/**
	 * 我的查询查复
	 * @author KHC
	 * @param request
	 * @since 2016年11月9日 下午3:20:07
	 */
	@RequestMapping("/inquiryreply/list")
	public String list(){
		return "inquiry_reply/inquiry_list";
	}
	
	/**
	 * 查询查复详情
	 * @author KHC
	 * @param request
	 * @since 2016年11月9日 下午3:20:07
	 */
	@RequestMapping("/inquiryreply/detail")
	public String detail(String no,Model model){
		model.addAttribute("no",no);
		return "inquiry_reply/inquiry_xq";
	}
	
	/**
	 * @author MH
	 * 			修改查询查复的跳转
	 * @param no  订单号
	 * @param model
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/inquiryreply/update")
	public String inquiryreplyUpdate(String no,Model model) throws ParseException{
		InquiryReply inquiryreply = inquiryReplyService.getByNo(no).get(0);
		model.addAttribute("inquiryreply",inquiryreply);
		return "inquiry_reply/inquiry";
	}
	
	@RequestMapping("/inquiryreply/savaupdate")
	public String savaUpdate(InquiryReply inquiryreply,String start,String end,Model model) throws ParseException{
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			InquiryReply inquiryreply1 = inquiryReplyService.getByNo(inquiryreply.getNo()).get(0);
			inquiryreply1.setDraftNo(inquiryreply.getDraftNo());
			inquiryreply1.setMoney(inquiryreply.getMoney());
			inquiryreply1.setDrawer(inquiryreply.getDrawer());
			inquiryreply1.setPayee(inquiryreply.getPayee());
			inquiryreply1.setBank(inquiryreply.getBank());
			inquiryreply1.setBankNo(inquiryreply.getBankNo());
			inquiryreply1.setEditState(1);
			if(StringUtils.isNotBlank(start))inquiryreply1.setStartDate(DateUtil.parser(start, DateUtil.FORMART3));
			if(StringUtils.isNotBlank(end))inquiryreply1.setEndDate(DateUtil.parser(end, DateUtil.FORMART3));
			inquiryReplyService.updateInquiryReply(inquiryreply1);
			map.put("response", "success");
			map.put("msg", "修改成功");
		} catch (Exception e) {
			map.put("response", "failed");
			map.put("msg", "修改失败");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(map));
		return "ajax";
	}
	
	/**
	 * 动态分页获取查询查复列表
	 * @author KHC
	 * @param form
	 * @param pageIndex
	 * @param pageSize
	 * @param request
	 * @param model
	 * @since 2016年11月9日 下午4:39:47
	 */
	@RequestMapping("/inquiryreply/list/serach")
	public String getlist(InquiryReplyForm form,Integer pageIndex,Integer pageSize,HttpServletRequest request,Model model){
		Member member = (Member) request.getSession().getAttribute("member");
		Integer memberId = member.getId();
		Map<String,Object> map = new HashMap<String, Object>();
		form.setMemberId(memberId);
		if(pageIndex==null)pageIndex = 0;
		if(pageSize==null)pageSize = 10;
		try {
			PageResults<Map<String, Object>> page = inquiryReplyService.getPageList(form, pageIndex, pageSize);
			map.put("data",page);
			map.put("response", "success");
			map.put("msg", "获取成功");
		} catch (Exception e) {
			map.put("response", "failed");
			map.put("msg", "获取失败");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(map));
		return "ajax";
	}
	
	/**
	 * 加载省市区
	 * @author KHC
	 * @param model
	 * @since 2016年11月10日 上午10:56:42
	 */
	@RequestMapping("/inquiryreply/init/region")
	public String initRegion(String provId,String cityId,String distId,Model model){
		Map<String,Object> map = new HashMap<String, Object>();
		Region prov = null;
		Region city = null;
		Region dist = null;
		if(StringUtils.isNotBlank(provId)){
			prov = regionService.getById(Integer.valueOf(provId));
		}
		if(StringUtils.isNotBlank(cityId)){
			city = regionService.getById(Integer.valueOf(cityId));
		}
		if(StringUtils.isNotBlank(distId)){
			dist = regionService.getById(Integer.valueOf(distId));
		}
		map.put("prov",prov.getName());
		map.put("city", city.getName());
		map.put("dist", dist.getName());
		model.addAttribute("retValue",JacksonUtil.objToJson(map));
		return "ajax";
	}
	
	/**
	 * 初始化页面获取付款方（含省市区）
	 * @author ZY
	 * @param request
	 * @param model
	 * @throws Exception 
	 * @since 2016年11月7日 下午2:06:09
	 */
	@RequestMapping("/inquiryreply/init")
	public String init(HttpServletRequest request,Model model) throws Exception{
		Member member1 = (Member) request.getSession().getAttribute("member");
		Integer memberId = member1.getId();
		Map<String,Object> result = new HashMap<String, Object>();
		Map<String,Object> member = memberService.getMemberAndOrgInfoByMemberId(memberId);
		
		Org org = orgService.getByMemberId(memberId);
		result.put("free", true);
		
		//APP2.2后 orgId 可能没有值 所以两种角色都要查询
		List<InquiryReply> list = null;
		if(org!=null){
			list = inquiryReplyService.getByOrgId(org.getId());
			if(list==null || list.size()==0){
				list = inquiryReplyService.getByMemberId(memberId);
			}
		}else{
			list = inquiryReplyService.getByMemberId(memberId);
		}
		if(list!=null && list.size()>0){
			result.put("free", false);
		}
		
		List<Region> p = regionService.getByType("P");//省
		List<Region> c = regionService.getByType("C");//市
		List<Region> d = regionService.getByType("D");//区
		result.put("data", member);
		result.put("p", p);
		result.put("c", c);
		result.put("d", d);
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 查询重复详情
	 * @author KHC
	 * @param no
	 * @param model
	 * @since 2016年11月10日 下午2:40:04
	 */
	@RequestMapping("/inquiryreply/get")
	public String getInfo(String no,Model model) throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		if(StringUtils.isBlank(no)) throw new Exception("数据异常");
		Map<String,Object> result = inquiryReplyService.getInfoByNo(no);
		if(result!=null){
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
			map.put("startDate", fm.format(DateUtil.parser(result.get("start_date").toString(), DateUtil.FORMART3)));
			map.put("endDate", fm.format(DateUtil.parser(result.get("end_date").toString(), DateUtil.FORMART3)));
			map.put("data",result);
			map.put("response", "success");
			map.put("msg", "获取成功");
		}else{
			map.put("response", "failed");
			map.put("msg", "获取失败");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(map));
		return "ajax";
	}
	
	/**
	 * 获取订单编号（防止重复）
	 * @author WKX
	 * @param prefix
	 * @since 2016年3月9日 下午4:14:03
	 */
	private String random(String prefix){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = format.format(new Date());
		for(int i = 0; i < 6; i++){
			str += (int)(Math.random()*10);
		}
		String code = prefix+str;
		List<InquiryReply> list = inquiryReplyService.getByNo(code);
		if(list!=null && list.size()>0)return random(prefix);
		else return code;
	}
	
	/**
	 * 支付调整：支付暂时取消
	 * @author ZY
	 * @param no
	 * @param model
	 * 2017年2月15日上午10:22:02
	 */
	@RequestMapping("/inquiryreply/updatepay")
	public String updatepay(String no,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		InquiryReply inquiryReply=null;
		if(null != no){//详情里支付
			List<InquiryReply> list = inquiryReplyService.getByNo(no);
			if(list != null && list.size() > 0){
				inquiryReply=list.get(0);
			}else{
				result.put("response", "fail");
				result.put("msg", "订单不存在");
			}	
		}
		if(null != inquiryReply){
			if(0 == inquiryReply.getPayState()){
				inquiryReply.setPayState(1);
				inquiryReply.setPayMoney(new BigDecimal(0));
				inquiryReplyService.saveInquiryReplyAndInvoice(inquiryReply, null);
				result.put("response", "success");
				result.put("msg", "操作成功");
			}else{
				result.put("response", "fail");
				result.put("msg", "订单状态异常，请刷新");
			}
		}else{
			result.put("response", "fail");
			result.put("msg", "订单不存在");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 保存查询查复
	 * @author ZY
	 * @param inquiryReply
	 * @param invoice
	 * @param start
	 * @param end
	 * @param model
	 * 2016年12月12日下午5:03:56
	 */
	@RequestMapping("/inquiryreply/save")
	public String save(InquiryReply inquiryReply,Invoice invoice,String start,String end,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		String switch_ = Constant.properties.getProperty("PAYSWITCH");//支付开关
		try {
			if(inquiryReply==null)throw new ServiceException("请求失败");
			if(inquiryReply.getOrgId()==null && inquiryReply.getMemberId()==null)throw new ServiceException("请求失败");
			if(StringUtils.isNotBlank(start))inquiryReply.setStartDate(DateUtil.parser(start, DateUtil.FORMART3));
			if(StringUtils.isNotBlank(end))inquiryReply.setEndDate(DateUtil.parser(end, DateUtil.FORMART3));
			
			Map<String,Object> map=orginfoService.getByMemberIdAndType(inquiryReply.getMemberId(), 0);
			if(map!=null&&map.get("org_id")!=null){
				inquiryReply.setOrgId(new Integer(map.get("org_id").toString()));
			}
			//step.0 照顾老版本 没传memberId 在org里面取值
			if(inquiryReply.getMemberId()==null){
				Org org = orgService.getById(inquiryReply.getOrgId());
				if(org!=null)inquiryReply.setMemberId(org.getMemberId());
			}
			if("off".equals(switch_)){//关闭支付
				inquiryReply.setPayState(1);//免费,去掉支付
				inquiryReply.setPayMoney(new BigDecimal(0));//支付金额
				inquiryReply.setNeedInvoice(1);//默认不需要发票
			}else{
				List<InquiryReply> list = null;
				list = inquiryReplyService.getByMemberId(inquiryReply.getMemberId());
				if (list == null || list.size() == 0) {// 如果传入的是memberId也要验证之前在机构角色下是否查询查复过
					Org org = orgService.getByMemberId(inquiryReply.getMemberId());
					if (org != null && org.getId() != null) {
						list = inquiryReplyService.getByOrgId(inquiryReply.getOrgId());
					}
				}
				if(list!=null && list.size()>0){
					inquiryReply.setPayState(0);//提交订单默认待付款
				}else{
					inquiryReply.setPayState(1);//首单免费
				}
			}
			inquiryReply.setCreateTime(new Date());
			inquiryReply.setState(0);
			if(invoice!=null)invoice.setCreateTime(new Date());//不为空才设置时间
			
			String prefix = "QY";
			inquiryReply.setOrgType(0);//pc端的都是默认为企业
			
			inquiryReply.setNo(random(prefix));//获取订单号
			inquiryReplyService.saveInquiryReplyAndInvoice(inquiryReply,invoice);
			result.put("response", "success");
			result.put("msg", "操作成功");
			result.put("no", inquiryReply.getNo());
			result.put("id", inquiryReply.getId());
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", "操作失败");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 快钱付款
	 * @author MH
	 * @param out_trade_no 商户订单号
	 * @param deposit 付款金额
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping("/inquirypay/bill99pay")
	public String bill99pay(Integer memberId,String deposit,String bnsNo,HttpServletRequest request,Model model) throws Exception{
		String path = Constant.properties.getProperty("alipayUrl");
		//指定付款人
		if(memberId==null){
			Member member = (Member) request.getSession().getAttribute("member");
			memberId = member.getId();
		}
		Map<String, String> paras = new HashMap<String, String>();
		paras.put("payerId", memberId.toString());//付款人标识
		paras.put("orderId", bnsNo);//商户订单号
		paras.put("orderAmount", (int)(Double.valueOf(deposit)*100)+"");//订单金额（分）
		paras.put("orderTime", new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()));//数量
		paras.put("productName", "银票贴现保证金");//产品名称
		paras.put("productNum", "1");//数量
		paras.put("bgUrl", path+"/inquirypay/pay/bill99cb");//回调地址
		paras.put("pageUrl", path+"/inquirypay/pay/cb/page");//回调页面
		
		model.addAttribute("retValue",Bill99Util.buildRequestPay(paras));
		return "ajax";
	}
	
	/**
	 * 回调后台（同支付宝一样，隔一段时间也会回调一次）
	 * @author MH
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
	@RequestMapping("/inquirypay/pay/bill99cb")
	public @ResponseBody  String payBill99Cb(String merchantAcctId,String bankId,String orderId,String orderTime,String orderAmount,String bindCard,String bindMobile,String dealId,String bankDealId,String fee,String payResult,Model model){
		Map<String, Object> result = new HashMap<String, Object>();
		String path = Constant.properties.getProperty("alipayUrl");
		String des="查询查复付款金额";
		try {
			InquiryReply inquiryReply = inquiryReplyService.getByNo(orderId).get(0);
			if (inquiryReply != null ) {
				if ("10".equals(payResult)){
					Integer state=0;
					
					if(inquiryReply.getPayState()==1){
						return null;
					}
					inquiryReply.setPayState(1);// 初始状态（已支付）
					if(dealId != null)inquiryReply.setJyh(dealId);//保存交易号
					inquiryReplyService.saveInquiryReplyAndPayRecord(inquiryReply, state, des);
				}
			}
			result.put("response", "success");
			result.put("msg", "操作成功");
		}catch (Exception e){
			result.put("response", "failed");
			result.put("msg", "网络异常");
		} 
		return "<result>1</result><redirecturl>"+path+"/inquirypay/pay/cb/page</redirecturl>";
	}
	
	/**
	 * 快钱回调页面
	 * @author MH
	 * @throws Exception
	 * @since 2017年2月24日 上午10:39:43
	 */
	@RequestMapping("/inquirypay/pay/cb/page")
	public String cbPayPage(String merchantAcctId,String bankId,String orderId,String orderTime,String orderAmount,String bindCard,String bindMobile,String dealId,String bankDealId,String fee,String payResult,Model model) throws Exception{
		return StringUtil.processRedirectUrl(Constant.properties.getProperty("baseUrl"),"/inquiryreply/success");
	}
	
	/**
	 * 支付宝付款
	 * @author ZY
	 * @param out_trade_no 商户订单号
	 * @param subject 商品名称
	 * @param total_fee 付款金额
	 * @param body 商品描述
	 * @param model
	 * @throws Exception
	 * @since 2016年11月17日 下午5:56:04
	 */
	@RequestMapping("/inquiryreply/paydo")
	public String toPay1(String bnsNo,String deposit,HttpServletRequest request,Model model) throws Exception{
		//String ip = InetAddress.getLocalHost().getHostAddress();
		//int port = request.getLocalPort();
		//String cp = request.getContextPath();
		//String path ="http://180.166.201.178:8888/website";//"http://"+ip+":"+port+cp;
		String path = Constant.properties.getProperty("alipayUrl");
		
		Map<String, String> paras = new HashMap<String, String>();
		paras.put("goods_type", "0");//商品类型:1表示实物类商品、0表示虚拟类商品
		paras.put("return_url", path+"/inquirypay/pay/callback");//此链接需要配置线上地址（并外网可访问）
		paras.put("notify_url", path+"/inquirypay/pay/cb");//此链接需要配置线上地址（并外网可访问）
		
		paras.put("out_trade_no", bnsNo);
		paras.put("subject", "查询查复");
		//Float money = Float.valueOf(deposit)/1000;
		paras.put("total_fee", deposit);
		paras.put("body", "查询查复付款金额");
		model.addAttribute("retValue",AlipayUtil.createDirectPay(paras));
		return "ajax";
	}
	
	/**
	 * 支付回调
	 * @param trade_no 交易号
	 * @param buyer_email 支付账号
	 * @param request
	 * @since 2016年11月17日 上午10:20:40
	 */
	@RequestMapping("/inquirypay/pay/callback")
	public String payCallback(){
		return "inquiry_reply/success";
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
	@RequestMapping("/inquirypay/pay/cb")
	public String cbPay(String out_trade_no,String trade_no,String trade_status){
		Map<String, Object> result = new HashMap<String, Object>();
		String des="查询查复付款金额";
		try {
			InquiryReply inquiryReply = inquiryReplyService.getByNo(out_trade_no).get(0);
			
			if (inquiryReply != null ) {
				if (trade_status.equals("TRADE_SUCCESS")){
					Integer state=0;
					
					if(inquiryReply.getPayState()==1){
						return null;
					}
					inquiryReply.setPayState(1);// 初始状态（已支付）
					if(trade_no != null)inquiryReply.setJyh(trade_no);//保存交易号
					inquiryReplyService.saveInquiryReplyAndPayRecord(inquiryReply, state, des);
				}
			}
			result.put("response", "success");
			result.put("msg", "操作成功");
		}catch (Exception e){
			result.put("response", "failed");
			result.put("msg", "网络异常");
		} 
		
		return "ajax";
	}
	
}