package com.ry.ryapp.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ry.core.entity.InquiryReply;
import com.ry.core.entity.IntegraltradingDetail;
import com.ry.core.entity.Invoice;
import com.ry.core.entity.Notice;
import com.ry.core.entity.Org;
import com.ry.core.entity.Region;
import com.ry.core.service.ActivityService;
import com.ry.core.service.InquiryReplyService;
import com.ry.core.service.InvoiceService;
import com.ry.core.service.MemberService;
import com.ry.core.service.OrgService;
import com.ry.core.service.RegionService;
import com.ry.core.service.TiexianNoticeService;
import com.ry.core.util.Constant;
import com.ry.util.AlipayUtil;
import com.ry.util.Bill99Util;
import com.ry.util.DateUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.page.PageResults;

import cn.jimmyshi.beanquery.BeanQuery;

/**
 * 查询查复
 * @author WKX
 */
@Controller
@RequestMapping("/app/inquiryReply/")
public class InquiryReplyController {
	
	@Resource
	InquiryReplyService inquiryReplyService;
	
	@Resource
	MemberService memberService;
	
	@Resource
	OrgService orgService;
	
	@Resource
	RegionService regionService;
	
	@Resource
	InvoiceService invoiceService;
	
	@Resource
	TiexianNoticeService tiexianNoticeService;
	
	@Resource
	ActivityService activityService; 
	
	/**
	 * 初始化页面获取付款方（含省市区）
	 * @author WKX
	 * @param orgId
	 * @param model
	 * @throws Exception 
	 * @since 2016年3月9日 下午2:06:09
	 */
	@RequestMapping("init")
	public String init(Integer memberId,Model model) throws Exception{
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
	 * 保存查询查复
	 * @author WKX
	 * @param inquiryReply
	 * @param model
	 * @since 2016年3月8日 下午4:42:11
	 */
	@RequestMapping("save")
	public String save(InquiryReply inquiryReply,Invoice invoice,String start,String end,Model model,Float version){
		Map<String,Object> result = new HashMap<String, Object>();
		
		String ps = Constant.properties.getProperty("VERSION_ALERT");//@WKX EDIT版本过期提示[因之前版本强制更新有问题，现做提示处理]
    	if("on".equals(ps) && version!=null && version<=2.32F){
    		result.put("response", "failed");
			result.put("msg", "当前版本已停用，请下载最新版本票据管家APP。");
			model.addAttribute("retValue",JacksonUtil.objToJson(result));
			return "ajax";
    	}
    	
		try {//节假日不能提交订单
			Notice notice = tiexianNoticeService.getNoticeByNowTime();//获取节假日提示（法定节日、周末（非补班））用来贴现和查询查复的提醒
			if(notice!=null && org.apache.commons.lang.StringUtils.isNotBlank(notice.getAlert())){
				result.put("response", "failed");
				result.put("msg", notice.getAlert());
				model.addAttribute("retValue",JacksonUtil.objToJson(result));
				return "ajax";
			}
			
			if(inquiryReply==null)throw new ServiceException("请求失败");
			if(inquiryReply.getOrgId()==null && inquiryReply.getMemberId()==null)throw new ServiceException("请求失败");
			if(StringUtils.isNotBlank(start))inquiryReply.setStartDate(DateUtil.parser(start, DateUtil.FORMART3));
			if(StringUtils.isNotBlank(end))inquiryReply.setEndDate(DateUtil.parser(end, DateUtil.FORMART3));
			
			//step.0 照顾老版本 没传memberId 在org里面取值
			if(inquiryReply.getMemberId()==null){
				Org org = orgService.getById(inquiryReply.getOrgId());
				if(org!=null)inquiryReply.setMemberId(org.getMemberId());
			}
			if(version!=null && version==2.31F){//版本2.31暂时取消支付功能
				inquiryReply.setPayState(1);//支付完成,去掉支付
				inquiryReply.setPayMoney(new BigDecimal(0));//支付金额，默认为0
				inquiryReply.setNeedInvoice(1);//默认不需要发票
			}else{
				List<InquiryReply> list = null;
				//step.1 APP2.2 企业端可能没有orgId（含两种角色下的不同查询校验）
				if(inquiryReply.getOrgId()!=null){
					list = inquiryReplyService.getByOrgId(inquiryReply.getOrgId());
					if(list==null || list.size()==0){//如果传入的是orgId也要验证之前在企业角色下是否查询查复过
						Org org = orgService.getById(inquiryReply.getOrgId());
						if(org!=null && org.getMemberId()!=null){
							list = inquiryReplyService.getByMemberId(org.getMemberId());
						}
					}
				}else{
					list = inquiryReplyService.getByMemberId(inquiryReply.getMemberId());
					if(list==null || list.size()==0){//如果传入的是memberId也要验证之前在机构角色下是否查询查复过
						Org org = orgService.getByMemberId(inquiryReply.getMemberId());
						if(org!=null && org.getId()!=null){
							list = inquiryReplyService.getByOrgId(inquiryReply.getOrgId());
						}
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
			
			String prefix = "QY";//默认值
			if(inquiryReply.getOrgType()!=null && inquiryReply.getOrgType()==1){//机构
				prefix = "JG";
			}else if(inquiryReply.getOrgType()!=null && inquiryReply.getOrgType()==0){//企业
				prefix = "QY";
			}else{
				prefix = "QY";
				inquiryReply.setOrgType(0);
			}
			
			inquiryReply.setNo(random(prefix));//获取订单号
			inquiryReply.setQudao("APP");//增添判断查询查复的渠道
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
	 * 支付调整：支付暂时取消
	 * @author ZY
	 * @param no
	 * @param model
	 * 2017年2月15日上午10:22:02
	 */
	@RequestMapping("updatepay")
	public String updatepay(String no,Integer id,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		InquiryReply inquiryReply=null;
		if(null != no && null == id){//详情里支付
			List<InquiryReply> list = inquiryReplyService.getByNo(no);
			if(list != null && list.size() > 0){
				inquiryReply=list.get(0);
			}else{
				result.put("response", "fail");
				result.put("msg", "订单不存在");
			}	
		}else if(null != id && null == no){//列表里支付
			inquiryReply = inquiryReplyService.getById(id);
		}
		if(null !=inquiryReply){
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
	 * 记录支付流水并且更新
	 * @author WKX
	 * @param irId
	 * @param state(0支付成功、2支付失败、4支付取消)
	 * @param des(支付成功、支付失败、支付取消)
	 * @param model
	 * @since 2016年3月10日 上午9:49:55
	 */
	@RequestMapping("payRecord/save")
	public String save(Integer irId,Integer state,String des,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		InquiryReply inquiryReply = inquiryReplyService.getById(irId);
		if(inquiryReply!=null){
			if(0==state){
				inquiryReply.setPayState(1);//已付款
				try {
					Map<String,Object> cbPay = AlipayUtil.tradeQuery(inquiryReply.getNo());
					if(cbPay!=null){
						Object trade_no = cbPay.get("trade_no");
						if(trade_no!=null)inquiryReply.setJyh(trade_no.toString());//保存交易号
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			inquiryReplyService.saveInquiryReplyAndPayRecord(inquiryReply, state, des);
			result.put("response", "success");
			result.put("msg", "操作成功");
			result.put("status", state);//返回支付时的返回值到前端
			result.put("des", des);//返回支付时的返回值到前端
		}else{
			result.put("response", "failed");
			result.put("msg", "网络异常");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 我的查询查复列表
	 * @author RY
	 * @param orgid 机构主键
	 * @param memberid 用户主键
	 * @param type 角色
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @since EDIT 2016年6月13日 下午7:33:55
	 */
	@RequestMapping("inqueryReplyList")
	public String inqueryReplyList(Integer orgid,Integer memberid,Integer type,Integer pageIndex,Integer pageSize,Model model){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			if(pageIndex==null)pageIndex = 0;
			if(pageSize==null)pageSize = 10;
			if(orgid==null && memberid==null)throw new Exception();
			
			InquiryReply inq=new InquiryReply();
			inq.setOrgId(orgid);
			inq.setMemberId(memberid);
			inq.setOrgType(type);
			PageResults<InquiryReply> pageResults = inquiryReplyService.getPageList(inq, pageIndex, pageSize);
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			if(pageResults.getResults()!=null){
				list = BeanQuery.select("id,orgId,no,draftNo,createTime,payMoney,payState").from(pageResults.getResults()).execute();
			}
			map.put("response", "success");
			map.put("msg", "操作成功");
			map.put("data",list);
		}catch(Exception e){
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "获取失败");
		}	
		model.addAttribute("retValue", JacksonUtil.objToJson(map));
		return "ajax";
	}
	
	/**
	 * 查询查复列表详情（同时查询用户的信息）
	 * @author xiaoc
	 */
	@RequestMapping("inqueryReplyDeatil")
	public String inqueryReplyDeatil(Integer id,Model model){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			InquiryReply inq = inquiryReplyService.getById(id);
			if(inq!=null && inq.getOrgId()!=null){
				Org org = orgService.getById(inq.getOrgId());
				Invoice invoice = invoiceService.getByFkIdAndFkType(inq.getId(),"2");//外键的关联类型（区分是 查询查复 还是  其他订单） 1 ：机构  2：查询查复
				if(invoice!=null && invoice.getExpressNo()!=null){
					map.put("expressNo", invoice.getExpressNo());
				}
				if(org!=null && org.getMemberId()!=null){
					Map<String,Object> member = memberService.getMemberAndOrgInfoByMemberId(org.getMemberId());
					map.put("member", member);
				}
			}
			map.put("response", "success");
			map.put("data", inq);
		}catch(Exception e){
			e.printStackTrace();
			map.put("response", "failed");
		}	
		model.addAttribute("retValue", JacksonUtil.objToJson(map));
		return "ajax";
	}
	
	/**
	 * 查询查复支付
	 * @author WKX
	 * @param orderAmount 金额
	 * @param memberId 用户主键
	 * @param orderId 商户订单号
	 * @param model
	 * @throws Exception
	 * @since 2017年3月9日 上午10:46:38
	 */
	@RequestMapping("pay")
	public String toPay(String orderAmount,String memberId,String orderId,Model model) throws Exception{
		String path_cb = Constant.properties.getProperty("BILL99URL_CB");
		String path_page = Constant.properties.getProperty("BILL99URL_PAGE");
		Map<String, String> paras = new LinkedHashMap<String, String>();

		if(StringUtils.isBlank(orderAmount))orderAmount = "1";//30元
		paras.put("orderId", orderId);//商户订单号
		paras.put("payerId", memberId);//用户标识
		paras.put("orderAmount", (int)(Double.valueOf(orderAmount)*100)+"");//订单金额（分）
//		paras.put("orderAmount", "100");//订单金额（分）
		paras.put("orderTime", new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()));//订单提交时间
		paras.put("productName", "查询查复（服务费）");//产品名称
		paras.put("productNum", "1");//数量
		paras.put("bgUrl", path_cb + "/app/inquiryReply/pay/cb");//后台回调
		paras.put("pageUrl", path_page + "/app/inquiryReply/pay/cb/page");//回调页面
		model.addAttribute("retValue",Bill99Util.buildRequestPayForWap(paras));
		return "ajax";
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
	 */
	@RequestMapping("pay/cb")
	public @ResponseBody String cbPay(String merchantAcctId,String bankId,String orderId,String orderTime,String orderAmount,String bindCard,String bindMobile,String dealId,String bankDealId,String fee,String payResult,Model model) throws Exception{
		String path_page = Constant.properties.getProperty("BILL99URL_PAGE");
		List<InquiryReply> list = inquiryReplyService.getByNo(orderId);
		if(list!=null && list.size()>0){
			if("10".equals(payResult)){
				InquiryReply inquiryReply = list.get(0);
				inquiryReply.setPayState(1);//已付款
				inquiryReply.setJyh(dealId);//保存交易号
				inquiryReplyService.saveInquiryReplyAndPayRecord(inquiryReply, Integer.valueOf(payResult), "支付成功");
				
				List<IntegraltradingDetail> listactivity = activityService.getlistActivity("查询查复",DateUtil.formart(new Date(), DateUtil.FORMART3),inquiryReply.getMemberId());
				if(listactivity != null && listactivity.size()<5){
					activityService.timingIntegral(inquiryReply.getMemberId(), 20, "查询查复", null);
				}
			}
		}
		String page = path_page + "/app/inquiryReply/pay/cb/page";
		return "<result>1</result><redirecturl>" + page + "</redirecturl>";
	}
	
	/**
	 * 编辑查询查复
	 * @author WKX
	 * @param inquiryReply 查询查复
	 * @param model
	 * @param version 版本
	 */
	@RequestMapping("update")
	public String update(InquiryReply inquiryReply,String start,String end,Model model,String version){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			if(inquiryReply==null || inquiryReply.getId()==null)throw new Exception();
			InquiryReply temp = inquiryReplyService.getById(inquiryReply.getId());
			if(temp.getEditState()!=null && temp.getEditState()==1){
				throw new Exception("您已经修改过了");
			}
			temp.setDraftNo(inquiryReply.getDraftNo());
			temp.setMoney(inquiryReply.getMoney());
			temp.setDrawer(inquiryReply.getDrawer());
			temp.setPayee(inquiryReply.getPayee());
			temp.setBank(inquiryReply.getBank());
			temp.setBankNo(inquiryReply.getBankNo());
			if(StringUtils.isNotBlank(start))temp.setStartDate(DateUtil.parser(start, DateUtil.FORMART3));
			if(StringUtils.isNotBlank(end))temp.setEndDate(DateUtil.parser(end, DateUtil.FORMART3));
			
			temp.setImportState(0);//未导出
			temp.setEditState(1);//不允许修改（已经修改过
			temp.setState(0);//待处理
			inquiryReplyService.updateInquiryReply(temp);
			result.put("response", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", "操作失败");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
}