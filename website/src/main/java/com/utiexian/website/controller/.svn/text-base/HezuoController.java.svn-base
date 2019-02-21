package com.utiexian.website.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.ClickCount;
import com.ry.core.entity.InquiryReply;
import com.ry.core.entity.Member;
import com.ry.core.entity.Notice;
import com.ry.core.entity.Org;
import com.ry.core.form.InquiryReplyForm;
import com.ry.core.service.ClickCountService;
import com.ry.core.service.InquiryReplyService;
import com.ry.core.service.MemberService;
import com.ry.core.service.OrgInfoService;
import com.ry.core.service.OrgService;
import com.ry.core.service.TiexianNoticeService;
import com.ry.core.util.Constant;
import com.ry.util.DateUtil;
import com.ry.util.HttpUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.page.PageResults;


/**
 * 合作（红眼兔）用友
 */
@Controller
@RequestMapping("/hezuo/")
public class HezuoController {
	
	private final String CODE = "hezuo";//合作
	private final String STYLE = "PC_HYT";//电脑端（默认：红眼兔）PC_HYT红眼兔、PC_YY用友
	
	@Resource
	TiexianNoticeService tiexianNoticeService;
	
	@Resource
	InquiryReplyService inquiryReplyService;
	
	@Resource
	MemberService memberService;
	
	@Resource
	OrgService orgService;
	
	@Resource
	OrgInfoService orgInfoService;
	
	@Resource
	ClickCountService clickCountService;
	
	/**
	 * 嵌套外壳
	 * @author WKX
	 * @param url
	 * @param model
	 * @throws Exception 
	 */
	@RequestMapping("iframe")
	public String iframe() throws Exception{
		return "hezuo/iframe";
	}
	
	/**
	 * 获取节假日提醒（查询查复将不可用）
	 * @author WKX
	 * @param model
	 */
	@RequestMapping("notice")
	public String jiejia(Model model){
		Map<String, Object> result = new HashMap<String, Object>();
		Notice notice = tiexianNoticeService.getNoticeByNowTime();//获取节假日提示（法定节日、周末（非补班））用来贴现和查询查复的提醒
		//如果在节假日定会额度受限,所以提醒非工作日的文字
		if(notice!=null && org.apache.commons.lang.StringUtils.isNotBlank(notice.getAlert())){
			result.put("response", "success");
			result.put("msg", notice.getAlert());
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 记录访问日志
	 * @param req
	 * @param record 功能（QUERY查询查复、INQUIRY询价、CALCULATOR计算器、BANK联行号）
	 * @since 2017年8月7日 下午3:47:05
	 */
	private void log(HttpServletRequest req,String record){
		String url = req.getRequestURL().toString() ;
		ClickCount cc = new ClickCount();
		cc.setCode(CODE);
		cc.setCurrentDate(new Date());
		cc.setIp(HttpUtil.getIpAddr(req));
		
		String _s = req.getParameter("_s");//PC_HYT红眼兔、PC_YY用友
		if(StringUtils.isNotBlank(_s)){
			cc.setStyle(_s);
		}else{
			cc.setStyle(STYLE);
		}
		cc.setUrl(url);
		cc.setRecord(record);
		clickCountService.saveClickCount(cc);
	}
	
	/**
	 * 发送验证码
	 * @author WKX
	 * @param mobile 手机号
	 * @param request
	 * @param response
	 */
	@RequestMapping("sendcode")
	public String sendcode(String mobile,HttpServletRequest request, HttpServletResponse response)throws IOException{
		PrintWriter out = response.getWriter();
		try{
			String code = String.valueOf((Math.random())).split("\\.")[1];
			code = code.substring(0,4);
			
			com.ry.util.SendMessagesUtil.aliSendIdentityMessage(mobile,code);
			request.getSession().setAttribute("CODE_HEZUO", code);
			request.getSession().setAttribute("MOBILE_HEZUO", mobile);
			out.print("success");
		}catch(Exception e){
			e.printStackTrace();
			out.print("发送失败");
		}
		return "ajax";
	}
	
	/**
	 * [功能]保存查询查复
	 * @author WKX
	 * @param inquiryReply
	 * @param start 出票日期
	 * @param end 到期日期
	 * @param mobile 手机号
	 * @param code 验证码
	 */
	@RequestMapping("save")
	public String save(InquiryReply inquiryReply,String start,String end,String mobile,String code,Model model,HttpServletRequest request, HttpServletResponse response){
		Map<String,Object> result = new HashMap<String, Object>();
		String switch_ = Constant.properties.getProperty("PAYSWITCH");//支付开关
		try {
			if(inquiryReply==null)throw new ServiceException("数据异常！");
			String sessioncode = (String)request.getSession().getAttribute("CODE_HEZUO");
			String sessionmobile = (String)request.getSession().getAttribute("MOBILE_HEZUO");
			if(!code.equals(sessioncode) || !mobile.equals(sessionmobile)){
				result.put("response", "failed");
				result.put("msg", "验证码错误！");
				model.addAttribute("retValue",JacksonUtil.objToJson(result));
				return "ajax";
			}
			Member member = null;
			List<Member> members = memberService.getListByMobile(mobile);
			if(members!=null && members.size()>0){
				member = members.get(0);
				Org org = orgService.getByMemberId(member.getId());
				inquiryReply.setMemberId(member.getId());
				if(org!=null)inquiryReply.setOrgId(org.getId());
			}else{
				member = new Member();
				Org org = new Org();
				member.setMobile(mobile);
				member.setRegtime(new Date());
				member.setQudao("PC");			
				memberService.addMember(member);
				member = Member.deMember(member);
				org.setMemberId(member.getId());
				org.setType(1);
				org.setState(0);
				org.setCreateTime(new Date());
				org = orgService.saveModel(org);
				inquiryReply.setMemberId(member.getId());
				inquiryReply.setOrgId(org.getId());
			}
			
			if(inquiryReply.getOrgId()==null && inquiryReply.getMemberId()==null)throw new ServiceException("请求失败");
			if(StringUtils.isNotBlank(start))inquiryReply.setStartDate(DateUtil.parser(start, DateUtil.FORMART3));
			if(StringUtils.isNotBlank(end))inquiryReply.setEndDate(DateUtil.parser(end, DateUtil.FORMART3));
			
			Map<String,Object> map = orgInfoService.getByMemberIdAndType(inquiryReply.getMemberId(), 0);
			if(map!=null&&map.get("org_id")!=null){
				inquiryReply.setOrgId(new Integer(map.get("org_id").toString()));
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
			inquiryReply.setOrgType(0);//PC端的都是默认为企业
			
			inquiryReply.setNo(random("QY"));//获取订单号
			inquiryReplyService.saveInquiryReplyAndInvoice(inquiryReply,null);
			result.put("response", "success");
			result.put("msg", "操作成功");
			result.put("no", inquiryReply.getNo());
			result.put("payState", inquiryReply.getPayState());//支付状态
			result.put("member", member);//支付状态
			
			this.log(request, "QUERY");//统计功能使用情况
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", "操作失败");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}

	/**
	 * 获取查询查复订单号
	 * @author WKX
	 * @param prefix 前缀
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
	 * 获取用户信息
	 * @author WKX
	 * @param mobile 手机号
	 * @param code 验证码
	 */
	@RequestMapping("get/member")
	public String member(String mobile,String code,Model model,HttpServletRequest request, HttpServletResponse response){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			String sessioncode = (String)request.getSession().getAttribute("CODE_HEZUO");
			String sessionmobile = (String)request.getSession().getAttribute("MOBILE_HEZUO");
			if(!code.equals(sessioncode) || !mobile.equals(sessionmobile)){
				result.put("response", "failed");
				result.put("msg", "验证码错误！");
				model.addAttribute("retValue",JacksonUtil.objToJson(result));
				return "ajax";
			}
			Member member = null;
			List<Member> members = memberService.getListByMobile(mobile);
			if(members!=null && members.size()>0){
				member = members.get(0);
			}else{
				member = new Member();
				Org org = new Org();
				member.setMobile(mobile);
				member.setRegtime(new Date());
				member.setQudao("PC");
				memberService.addMember(member);
				member = Member.deMember(member);
				org.setMemberId(member.getId());
				org.setType(1);
				org.setState(0);
				org.setCreateTime(new Date());
				org = orgService.saveModel(org);
			}
			result.put("data", member);
			result.put("response", "success");
			result.put("msg", "操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", "操作失败!");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 查询查复列表（数据）
	 * @author WKX
	 * @param form 条件
	 * @param pageIndex
	 * @param pageSize
	 * @param request
	 * @param model
	 */
	@RequestMapping("query/list/data")
	public String getlist(InquiryReplyForm form,Integer pageIndex,Integer pageSize,HttpServletRequest request,Model model){
		Map<String,Object> map = new HashMap<String, Object>();
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
	 * 查询查复结果（页面）
	 */
	@RequestMapping("query/list")
	public String querylist(Integer mid,Model model){
		if(mid==null){
			return "hezuo/query";
		}
		model.addAttribute("memberId", mid);
		return "hezuo/query-list";
	}
	
	/**
	 * 更新订单（选择支付方式）
	 * @author WKX
	 * @param no 订单号
	 * @param payWay 支付方式
	 * @param model
	 */
	@RequestMapping("update")
	public String update(String no,Integer payWay,Model model) throws ParseException{
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			List<InquiryReply> list = inquiryReplyService.getByNo(no);
			if(list!=null && list.size()>0){
				InquiryReply inq = list.get(0);
				inq.setPayWay(payWay);
				inquiryReplyService.updateInquiryReply(inq);
			}
			map.put("response", "success");
			map.put("msg", "操作成功！");
		} catch (Exception e) {
			map.put("response", "failed");
			map.put("msg", "操作失败！");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(map));
		return "ajax";
	}
	
	/**
	 * 查询查复支付（页面）支付页面
	 */
	@RequestMapping("query/pay")
	public String querypay(String no,Model model){
		Map<String,Object> result = inquiryReplyService.getInfoByNo(no);
		if(result==null){//数据异常则回到查询查复首页
			return "hezuo/query";
		}
		model.addAttribute("result", result);
		return "hezuo/query-pay";
	}
	
	/**
	 * 查询查复（页面）
	 */
	@RequestMapping("query")
	public String query(HttpServletRequest req){
		return "hezuo/query";
	}
	
	/**
	 * 询价（页面）
	 */
	@RequestMapping("inquiry")
	public String xunjia(HttpServletRequest req){
		return "hezuo/inquiry";
	}
	
	/**
	 * 计算器（页面）
	 */
	@RequestMapping("calculator")
	public String jisuanqi(HttpServletRequest req){
		return "hezuo/calculator";
	}
	
	/**
	 * 联行号（页面）
	 */
	@RequestMapping("bank")
	public String lianhanghao(HttpServletRequest req){
		return "hezuo/bank";
	}
	/**
	 * 联行号结果（页面）
	 */
	@RequestMapping("bank/list")
	public String lianhanghaojieguo(String city,String provice,String keyword,String yinhang,Model model){
		model.addAttribute("city", city);
		model.addAttribute("provice", provice);
		model.addAttribute("keyword", keyword);
		model.addAttribute("yinhang", yinhang);
		return "hezuo/bank-list";
	}
}