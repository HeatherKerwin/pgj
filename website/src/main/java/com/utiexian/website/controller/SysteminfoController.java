package com.utiexian.website.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Discountrecord;
import com.ry.core.entity.DiscountrecordPl;
import com.ry.core.entity.DiscountrecordSp;
import com.ry.core.entity.DistributeOrder;
import com.ry.core.entity.DistributeOrderPl;
import com.ry.core.entity.DistributeOrderSp;
import com.ry.core.entity.InquiryReply;
import com.ry.core.entity.Member;
import com.ry.core.entity.PreferentialInfo;
import com.ry.core.entity.Systeminfo;
import com.ry.core.entity.Systeminfo.ReadState;
import com.ry.core.entity.Systeminfo.Type;
import com.ry.core.form.SysteminfoForm;
import com.ry.core.service.AccountrecordService;
import com.ry.core.service.DiscountrecordPlService;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.DiscountrecordSpService;
import com.ry.core.service.DistributeOrderPlService;
import com.ry.core.service.DistributeOrderService;
import com.ry.core.service.DistributeOrderSpService;
import com.ry.core.service.HistorypriceService;
import com.ry.core.service.InquiryReplyService;
import com.ry.core.service.MemberService;
import com.ry.core.service.PreferentialInfoService;
import com.ry.core.service.SysteminfoService;
import com.ry.core.util.Constant;
import com.ry.util.DateUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.StringUtil;
import com.ry.util.page.PageResults;

import cn.jimmyshi.beanquery.BeanQuery;

@Controller
public class SysteminfoController {

	@Resource
	SysteminfoService systeminfoService;
	
	@Resource
	MemberService memberService;
	
	@Resource
	PreferentialInfoService preferentialInfoService;
	
	@Resource
	DiscountrecordService discountrecordService;
	
	@Resource
	DiscountrecordSpService discountrecordSpService;
	
	@Resource
	DiscountrecordPlService discountrecordPlService;
	
	@Resource
	InquiryReplyService  inquiryReplyService;
	
	@Resource
	DistributeOrderService distributeOrderService;
	
	@Resource
	DistributeOrderSpService distributeOrderSpService;
	
	@Resource
	DistributeOrderPlService distributeOrderPlService;
	
	@Resource
	private HistorypriceService historypriceService;
	
	@Resource
	private AccountrecordService accountrecordService;
	
	/**
	 * 消息中心
	 * @author WKX
	 * @param model
	 * @since 2016年11月20日 下午2:26:52
	 */
	@RequestMapping("/systeminfo/list")
	public String list(Model model){
		return "/member/news";
	}
	
	/**
	 * 系统消息
	 * @author WKX
	 * @param model
	 * @since 2016年11月20日 下午3:57:14
	 */
	@RequestMapping("/systeminfo/list/system")
	public String listSystem(Model model){
		return "/member/news_system";
	}
	
	/**
	 * 查询查复
	 * @author WKX
	 * @param model
	 * @since 2016年11月20日 下午3:58:41
	 */
	@RequestMapping("/systeminfo/list/inquiryreply")
	public String listInquiryreply(Model model){
		return "/member/news_inquiryreply";
	}
	
	/**
	 * 优惠消息
	 * @author WKX
	 * @param model
	 * @since 2016年11月20日 下午4:29:12
	 */
	@RequestMapping("/systeminfo/list/preferentialinfo")
	public String listPreferentialinfo(Model model){
		return "/member/news_preferentialinfo";
	}
	
	@RequestMapping("/systeminfo/list/notice")
	public String listNotice(Model model){
		return "/member/news_notice";
	}
	
	/**
	 * 查询是否有消息（未读）3天内的最新消息
	 * @author WKX
	 * @param memberId
	 * @param model
	 * @throws IOException
	 * @since 2016年11月20日 下午2:07:41
	 */
	@RequestMapping("/systeminfo/has")
	public String hasMessage(HttpServletRequest request,Model model) throws IOException{
		Member member = (Member) request.getSession().getAttribute("member");
		if(member==null){
			return StringUtil.processRedirectUrl(Constant.properties.getProperty("baseUrl"),"");
		}
		Integer memberId = member.getId();
		int count = 0;
		List<Systeminfo> infos = systeminfoService.getByMemberIdAndReadState(memberId, ReadState.UNREAD);
		if(infos!=null && infos.size()>0)count = infos.size();
		List<PreferentialInfo> preferentialInfos = preferentialInfoService.getBetweenCreateTime(DateUtil.addDays(-3), new Date());
		if(preferentialInfos!=null && preferentialInfos.size()>0)count += preferentialInfos.size();
		model.addAttribute("retValue", count);
		return "ajax";
	}
	
	/**
	 * 根据主键阅读该消息
	 * @author WKX
	 * @param id 消息主键
	 * @param model
	 * @throws IOException
	 */
	@RequestMapping("/systeminfo/read")
	public String readDiscountrecord(Integer id,HttpServletRequest request,Model model)  throws IOException{
		Member member = (Member) request.getSession().getAttribute("member");
		if(member==null){
			return StringUtil.processRedirectUrl(Constant.properties.getProperty("baseUrl"),"");
		}
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Systeminfo info = systeminfoService.updateSysteminfoToReadById(id);
			
			if(info!=null && info.getType()==Type.DISCOUNTRECORD){
				Discountrecord dis = discountrecordService.getById(info.getDiscountrecordId());
				if(dis!=null){
					result.put("no", dis.getOrdernumber());
				}
			}else if(info!=null && info.getType()==Type.DISCOUNTRECORDSP){
				DiscountrecordSp dis = discountrecordSpService.getById(info.getDiscountrecordId());
				if(dis!=null){
					result.put("no", dis.getNo());
				}
			}else if(info!=null && info.getType()==Type.DISCOUNTRECORDPL){
				DiscountrecordPl dis = discountrecordPlService.getById(info.getDiscountrecordId());
				if(dis!=null){
					result.put("no", dis.getNo());
				}
			}else if(info!=null && info.getType()==Type.DISTRIBUTEORDER){
				DistributeOrder dit = distributeOrderService.getById(info.getDiscountrecordId());
				if(dit!=null){
					result.put("no",dit.getNo());
				}
			}else if(info!=null && info.getType()==Type.DISTRIBUTEORDERSP){
				DistributeOrderSp dit = distributeOrderSpService.getById(info.getDiscountrecordId());
				if(dit!=null){
					result.put("no",dit.getNo());
				}
			}else if(info!=null && info.getType()==Type.DISTRIBUTEORDERPL){
				DistributeOrderPl dit = distributeOrderPlService.getById(info.getDiscountrecordId());
				if(dit!=null){
					result.put("no",dit.getNo());
				}
			}else if(info!=null && info.getType()==Type.INQUIRYREPLY){
				InquiryReply inq=inquiryReplyService.getById(info.getDiscountrecordId());
				if(inq!=null){
					result.put("no",inq.getNo());
				}
			}
			//重新获取消息数量
			Integer message = systeminfoService.getMessage(member.getId());
			if(message!=null)request.getSession().setAttribute("MEMBER_MESSAGE", "("+message+")");
			
			result.put("response","success");
			result.put("msg","操作成功");
			result.put("data",info.getDiscountrecordId());
			result.put("type",info.getType());
		} catch (Exception e) {
			result.put("response","failed");
			result.put("msg","操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 订单消息
	 * @author WKX
	 * @param memberId
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @throws IOException
	 * @since 2016年11月20日 下午2:11:58
	 */
	@RequestMapping("/systeminfo/order")
	public String getSysteminfo(Integer pageIndex,Integer pageSize,HttpServletRequest request,Model model)  throws IOException{
		Member member = (Member) request.getSession().getAttribute("member");
		if(member==null){
			return StringUtil.processRedirectUrl(Constant.properties.getProperty("baseUrl"),"");
		}
		Integer memberId = member.getId();
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			if(pageIndex==null)pageIndex = 0;
			if(pageSize==null)pageSize = 10;
			
			List<Type> types = new ArrayList<Systeminfo.Type>();
			types.add(Type.DISCOUNTRECORD);
			types.add(Type.DISCOUNTRECORDSP);
			types.add(Type.DISCOUNTRECORDPL);
			types.add(Type.DISTRIBUTEORDER);
			types.add(Type.DISTRIBUTEORDERSP);
			types.add(Type.DISTRIBUTEORDERPL);
			types.add(Type.DISPATCH);
			
			SysteminfoForm nf = new SysteminfoForm();
			nf.setTypes(types);
			nf.setMemberId(memberId);
			PageResults<Systeminfo> pageResults = systeminfoService.getPageList(nf, pageIndex, pageSize);
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			PageResults<Map<String,Object>> result = new PageResults<Map<String,Object>>();
			if(pageResults.getResults()!=null){
				list = BeanQuery.select("id,memberId,type,alert,content,discountrecordId,readState,createTime").from(pageResults.getResults()).execute();
				for(Map<String,Object> m:list){
					Object id = m.get("discountrecordId");
					if(m!=null && id!=null && m.get("type").equals(Type.DISCOUNTRECORD)){
						Discountrecord dis = discountrecordService.getById((Integer)id);
						if(dis!=null){
							m.put("allmoney", dis.getAllmoney());
							m.put("no", dis.getOrdernumber());
							m.put("orderflag", dis.getOrderflag());
							m.put("deposit", dis.getDeposit());
							m.put("bank", dis.getBank());
							m.put("endorse", dis.getEndorse());
							m.put("type1", dis.getType1());
						}
					}else if(m!=null && id!=null && m.get("type").equals(Type.DISCOUNTRECORDSP)){
						DiscountrecordSp dis = discountrecordSpService.getById((Integer)id);
						if(dis!=null){
							m.put("allmoney", dis.getAllmoney());
							m.put("no", dis.getNo());
							m.put("orderflag", dis.getOrderflag());
							m.put("bank", dis.getBank());
							m.put("endorse", dis.getEndorse());
							m.put("type1", dis.getType1());
						}
					}else if(m!=null && id!=null && m.get("type").equals(Type.DISCOUNTRECORDPL)){
						DiscountrecordPl dis = discountrecordPlService.getById((Integer)id);
						if(dis!=null){
							m.put("allmoney", dis.getAllmoney());
							m.put("no", dis.getNo());
							m.put("orderflag", dis.getOrderflag());
							m.put("amount", dis.getAmount());
							m.put("type1", dis.getType1());
							m.put("type2", dis.getType2());
						}
					}if(m!=null && id!=null && m.get("type").equals(Type.DISTRIBUTEORDER)){
						Map<String,Object> dit = distributeOrderService.getInfoById((Integer)id);
						if(dit!=null){
							m.put("state",dit.get("state"));
							m.put("no",dit.get("no"));
							m.put("allmoney", dit.get("allmoney"));
							m.put("ordernumber", dit.get("ordernumber"));
							m.put("deposit", dit.get("dtdeposit"));
							m.put("bank", dit.get("bank"));
							m.put("endorse", dit.get("endorse"));
							m.put("type1", dit.get("type1"));
						}
					}if(m!=null && id!=null && m.get("type").equals(Type.DISTRIBUTEORDERSP)){
						Map<String,Object> dit = distributeOrderSpService.getInfoByDtboId((Integer)id);
						if(dit!=null){
							m.put("state",dit.get("state"));
							m.put("no",dit.get("distNo"));
							m.put("allmoney", dit.get("allmoney"));
							m.put("ordernumber", dit.get("no"));
							m.put("bank", dit.get("bank"));
							m.put("endorse", dit.get("endorse"));
							m.put("type1", dit.get("type1"));
						}
					}if(m!=null && id!=null && m.get("type").equals(Type.DISTRIBUTEORDERPL)){
						Map<String,Object> dit = distributeOrderPlService.getInfoById((Integer)id);
						if(dit!=null){
							m.put("state",dit.get("state"));
							m.put("no",dit.get("distNo"));
							m.put("allmoney", dit.get("allmoney"));
							m.put("ordernumber", dit.get("no"));
							m.put("amount", dit.get("amount"));
							m.put("type1", dit.get("type1"));
							m.put("type2", dit.get("type2"));
						}
					}
				}
				result.setResults(list);
			}
			result.setCurrentPage(pageResults.getCurrentPage());
			result.setPageCount(pageResults.getPageCount());
			result.setPageNo(pageResults.getPageNo());
			result.setPageSize(pageResults.getPageSize());
			result.setRecordsTotal(pageResults.getRecordsTotal());
			result.setTotalCount(pageResults.getTotalCount());
			map.put("response", "success");
			map.put("msg", "操作成功");
			map.put("data",result);
		}catch(Exception e){
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "获取失败");
		}		
		model.addAttribute("retValue", JacksonUtil.objToJson(map));
		return "ajax";
	}
	
	/**
	 * 获取查询查复消息
	 * @author WKX
	 * @param pageIndex
	 * @param pageSize
	 * @param request
	 * @param model
	 * @throws IOException
	 * @since 2016年11月20日 下午2:17:18
	 */
	@RequestMapping("/systeminfo/inquiryreply")
	public String getSysteminfoInquiryreply(Integer pageIndex,Integer pageSize,HttpServletRequest request,Model model)  throws IOException{
		Member member = (Member) request.getSession().getAttribute("member");
		if(member==null){
			return StringUtil.processRedirectUrl(Constant.properties.getProperty("baseUrl"),"");
		}
		Integer memberId = member.getId();
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			if(pageIndex==null)pageIndex = 0;
			if(pageSize==null)pageSize = 10;
			
			List<Type> types = new ArrayList<Systeminfo.Type>();
			types.add(Type.INQUIRYREPLY);
			
			SysteminfoForm nf = new SysteminfoForm();
			nf.setTypes(types);
			nf.setMemberId(memberId);
			PageResults<Systeminfo> pageResults = systeminfoService.getPageList(nf, pageIndex, pageSize);
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			PageResults<Map<String,Object>> result = new PageResults<Map<String,Object>>();
			if(pageResults.getResults()!=null){
				list = BeanQuery.select("id,memberId,type,alert,content,discountrecordId,readState,createTime").from(pageResults.getResults()).execute();
				for(Map<String,Object> m:list){
					Object id = m.get("discountrecordId");
					if(m!=null && id!=null && m.get("type").equals(Type.INQUIRYREPLY)){
						InquiryReply inq=inquiryReplyService.getById((Integer)id);
						if(inq!=null){
							m.put("allmoney",inq.getPayMoney());
							m.put("no",inq.getNo());
						}
					}
				}
				result.setResults(list);
			}
			result.setCurrentPage(pageResults.getCurrentPage());
			result.setPageCount(pageResults.getPageCount());
			result.setPageNo(pageResults.getPageNo());
			result.setPageSize(pageResults.getPageSize());
			result.setRecordsTotal(pageResults.getRecordsTotal());
			result.setTotalCount(pageResults.getTotalCount());
			map.put("response", "success");
			map.put("msg", "操作成功");
			map.put("data",result);
		}catch(Exception e){
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "获取失败");
		}		
		model.addAttribute("retValue", JacksonUtil.objToJson(map));
		return "ajax";
	}
	
	/**
	 * 系统消息
	 * @author WKX
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @throws IOException
	 * @since 2016年11月20日 下午2:20:35
	 */
	@RequestMapping("/systeminfo/system")
	public String systemNews(Integer pageIndex,Integer pageSize,HttpServletRequest request,Model model)  throws IOException{
		Member member = (Member) request.getSession().getAttribute("member");
		if(member==null){
			return StringUtil.processRedirectUrl(Constant.properties.getProperty("baseUrl"),"");
		}
		Integer memberId = member.getId();
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			if(pageIndex==null)pageIndex = 0;
			if(pageSize==null)pageSize = 10;
			List<Type> types = new ArrayList<Systeminfo.Type>();
			types.add(Type.SYSTEM);
			SysteminfoForm nf = new SysteminfoForm();
			nf.setTypes(types);
			nf.setMemberId(memberId);
			PageResults<Systeminfo> pageResults = systeminfoService.getPageList(nf, pageIndex, pageSize);
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
	 * 优惠消息
	 * @author WKX
	 * @param request
	 * @param response
	 * @param model
	 * @throws IOException
	 * @since 2016年11月20日 下午2:21:24
	 */
	@RequestMapping("/systeminfo/preferentialinfo")
	public String getPreferentialInfoPage(HttpServletRequest request,HttpServletResponse response,Model model)  throws IOException{
		Map<String, Object> map = new HashMap<String, Object>();
		try{							
			Integer pageIndex = request.getParameter("pageIndex")==null? 1 : Integer.parseInt(request.getParameter("pageIndex"));
			Integer pageSize = request.getParameter("pageSize")==null? 10 : Integer.parseInt(request.getParameter("pageSize"));
			
			PageResults<PreferentialInfo> page = preferentialInfoService.getPageList(pageIndex, pageSize);
			
			map.put("response", "success");
			map.put("msg", "操作成功");
			map.put("data",page);
		}catch(Exception e){
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "获取失败");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(map));
		return "ajax";
	}
	
	/**
	 * 分页显示用户 票据管理 提醒
	 * @author WKX
	 * @param pageIndex 第几页
	 * @param pageSize 一页多少条
	 * @param request
	 * @param model
	 * @since 2016年11月20日 下午5:52:05
	 */
	@RequestMapping("/systeminfo/notice")
	public String getNotice(Integer pageIndex,Integer pageSize,HttpServletRequest request,Model model){
		Map<String, Object> result = new HashMap<String, Object>();
		Member member = (Member) request.getSession().getAttribute("member");
		if(member==null){
			return StringUtil.processRedirectUrl(Constant.properties.getProperty("baseUrl"),"");
		}
		Integer memberId = member.getId();
		if(pageIndex==null)pageIndex = 0;
		if(pageSize==null)pageSize = 10;
		try {
			PageResults<Map<String, Object>> page = accountrecordService.getNoticePage(memberId, pageIndex, pageSize);
			result.put("response", "success");
			result.put("msg", "获取成功");
			result.put("data",page);
			System.err.println(page.getResults());
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", "获取失败");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
	    return "ajax";
	}
	
	/**
	 * 全部已读（根据类型）
	 * @author WKX
	 * @param type
	 * @param request
	 * @param model
	 * @throws IOException
	 * @since 2016年11月20日 下午6:27:42
	 */
	@RequestMapping("/systeminfo/read/type")
	public String read(String type,HttpServletRequest request,Model model)  throws IOException{
		Map<String, Object> result = new HashMap<String, Object>();
		Member member = (Member) request.getSession().getAttribute("member");
		if(member==null){
			return StringUtil.processRedirectUrl(Constant.properties.getProperty("baseUrl"),"");
		}
		Integer memberId = member.getId();
		try {
			List<Type> types = new ArrayList<Systeminfo.Type>();
			if(StringUtils.isBlank(type))throw new Exception();
			if("order".equals(type)){
				types.add(Type.DISCOUNTRECORD);
				types.add(Type.DISCOUNTRECORDSP);
				types.add(Type.DISCOUNTRECORDPL);
				types.add(Type.DISTRIBUTEORDER);
				types.add(Type.DISTRIBUTEORDERSP);
				types.add(Type.DISTRIBUTEORDERPL);
			}else if("inquiryreply".equals(type)){
				types.add(Type.INQUIRYREPLY);
			}else if("system".equals(type)){
				types.add(Type.SYSTEM);
			}
			
			systeminfoService.updateToReadByMemberId(memberId,types);
			
			//重新获取消息数量
			Integer message = systeminfoService.getMessage(member.getId());
			if(message!=null)request.getSession().setAttribute("MEMBER_MESSAGE", "("+message+")");
			result.put("response","success");
			result.put("msg","操作成功");
		} catch (Exception e) {
			result.put("response","failed");
			result.put("msg","操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 验证是否登录（含未读消息）
	 * @author WKX
	 * @param request
	 * @param model
	 * @throws IOException
	 * @since 2016年11月22日 下午6:56:21
	 */
	@RequestMapping("/islogin")
	public String isLogin(HttpServletRequest request,Model model) throws IOException{
		Map<String,Object> result = new HashMap<String, Object>();
		Object member = request.getSession().getAttribute("member");
		Object message = request.getSession().getAttribute("MEMBER_MESSAGE");
		
		if(member!=null)result.put("member", (Member)member);
		if(message!=null)result.put("message", (String)message);
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
}