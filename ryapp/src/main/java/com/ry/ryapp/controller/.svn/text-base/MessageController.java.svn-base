package com.ry.ryapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Discountrecord;
import com.ry.core.entity.DiscountrecordPl;
import com.ry.core.entity.DiscountrecordSp;
import com.ry.core.entity.InquiryReply;
import com.ry.core.entity.PreferentialInfo;
import com.ry.core.entity.Systeminfo;
import com.ry.core.entity.Systeminfo.ReadState;
import com.ry.core.entity.Systeminfo.Type;
import com.ry.core.form.SysteminfoForm;
import com.ry.core.service.DiscountrecordPlService;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.DiscountrecordSpService;
import com.ry.core.service.DistributeOrderPlService;
import com.ry.core.service.DistributeOrderService;
import com.ry.core.service.DistributeOrderSpService;
import com.ry.core.service.InquiryReplyService;
import com.ry.core.service.MemberService;
import com.ry.core.service.PreferentialInfoService;
import com.ry.core.service.SysteminfoService;
import com.ry.util.DateUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.page.PageResults;

import cn.jimmyshi.beanquery.BeanQuery;

/**
 * 消息中心[含订单消息、优惠消息]
 * @author WKX
 */
@Controller
@RequestMapping("/app/message/")
public class MessageController {
	
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
	
	/**
	 * 根据用户主键获取订单信息
	 * @author WKX
	 * @param memberId 用户主键
	 * @param request
	 * @param response
	 * @param model
	 * @throws IOException
	 * @since 2016年1月7日 上午9:34:33
	 */
	@RequestMapping("discountrecord")
	public String getSysteminfoPageByMemberId(Integer memberId, HttpServletRequest request, HttpServletResponse response,Model model)  throws IOException{
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Integer pageIndex = request.getParameter("pageIndex")==null? 1 : Integer.parseInt(request.getParameter("pageIndex"));
			Integer pageSize = request.getParameter("pageSize")==null? 10 : Integer.parseInt(request.getParameter("pageSize"));
			
			SysteminfoForm nf = new SysteminfoForm();
			nf.setType(Type.DISCOUNTRECORD);
			nf.setMemberId(memberId);
			PageResults<Systeminfo> pageResults = systeminfoService.getPageList(nf, pageIndex, pageSize);
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			if(pageResults.getResults()!=null){
				list = BeanQuery.select("id,memberId,type,alert,content,discountrecordId,readState,createTime").from(pageResults.getResults()).execute();
				for(Map<String,Object> m:list){
					if(m!=null && m.get("discountrecordId")!=null){
						String id = m.get("discountrecordId").toString();
						if(StringUtils.hasText(id)){
							Discountrecord dis = discountrecordService.getById(Integer.valueOf(id));
							if(dis!=null){
								String type1 = dis.getType1()==1?"纸票":"电票";
								String type2 = dis.getType2()==1?"国股":dis.getType2()==2?"城商":dis.getType2()==3?"三农":"其他";
								String falg = dis.getOrderflag()==2?"已由客服确认，客服电话021-68750331，感谢您的耐心等待":dis.getOrderflag()==3?"已完成，客服电话021-68750331":"客服电话021-68750331";
								String start = DateUtil.formart(dis.getBegintime(), "yyyy-MM-dd");
								String end = DateUtil.formart(dis.getEndtime(), "yyyy-MM-dd");
								m.put("content", type1+type2+" "+dis.getAllmoney()+"万 订单（贴现时间"+start+"，到期日期"+end+"）"+falg);
							}
						}
					}
				}
			}
			map.put("state", "success");
			map.put("msg", "操作成功");
			map.put("data",list);
		}catch(Exception e){
			e.printStackTrace();
			map.put("state", "failed");
			map.put("msg", "获取失败");
		}		
		model.addAttribute("retValue", JacksonUtil.objToJson(map));
		return "ajax";
	}
	
	/**
	 * 分页查询优惠消息
	 * @author WKX
	 * @param request
	 * @param response
	 * @param model
	 * @throws IOException
	 * @since 2016年1月7日 上午9:37:25
	 */
	@RequestMapping("preferentialInfo")
	public String getPreferentialInfoPage(HttpServletRequest request,HttpServletResponse response,Model model)  throws IOException{
		Map<String, Object> map = new HashMap<String, Object>();
		try{							
			Integer pageIndex = request.getParameter("pageIndex")==null? 1 : Integer.parseInt(request.getParameter("pageIndex"));
			Integer pageSize = request.getParameter("pageSize")==null? 10 : Integer.parseInt(request.getParameter("pageSize"));
			
			PageResults<PreferentialInfo> page = preferentialInfoService.getPageList(pageIndex, pageSize);
			
			map.put("state", "success");
			map.put("msg", "操作成功");
			map.put("data",page.getResults());
		}catch(Exception e){
			e.printStackTrace();
			map.put("state", "failed");
			map.put("msg", "获取失败");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(map));
		return "ajax";
	}
	
	/**
	 * 根据用户主键获取未读消息[仅仅获取未读数量]
	 * @author WKX
	 * @param memberId 用户主键
	 * @param model
	 * @param request
	 * @param response
	 * @throws IOException
	 * @since 2016年1月8日 上午11:12:49
	 */
	@RequestMapping("has")
	public String hasMessage(Integer memberId,Model model,HttpServletRequest request,HttpServletResponse response)  throws IOException{
		int count = 0;
		//订单未读信息
		List<Systeminfo> infos = systeminfoService.getByMemberIdAndReadState(memberId, ReadState.UNREAD);
		if(infos!=null && infos.size()>0)count = infos.size();
		//3天内的最新消息
		List<PreferentialInfo> preferentialInfos = preferentialInfoService.getBetweenCreateTime(DateUtil.addDays(-3), new Date());
		if(preferentialInfos!=null && preferentialInfos.size()>0)count += preferentialInfos.size();
		model.addAttribute("retValue", count);
		return "ajax";
	}
	
	/**
	 * 根据主键阅读该消息
	 * @author WKX
	 * @param id
	 * @param model
	 * @throws IOException
	 * @since 2016年1月12日 上午11:00:40
	 */
	@RequestMapping("discountrecord/read")
	public String readDiscountrecord(Integer id,Model model)  throws IOException{
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Systeminfo info = systeminfoService.updateSysteminfoToReadById(id);
			map.put("state","success");
			map.put("msg","操作成功");
			map.put("data",info.getDiscountrecordId());
		} catch (Exception e) {
			map.put("state","failed");
			map.put("msg","操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(map));
		return "ajax";
	}
	
	//**************************************APP2.1******************************************************************
	
	/**
	 * 企业订单消息列表
	 * @author xiaoc
	 */
	@RequestMapping("discountrecord2")
	public String getSysteminfoPageListByMemberId(Integer memberId,Integer pageIndex,Integer pageSize,Model model)  throws IOException{
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			if(pageIndex==null)pageIndex = 0;
			if(pageSize==null)pageSize = 10;
			
			List<Type> types = new ArrayList<Systeminfo.Type>();
			types.add(Type.DISCOUNTRECORD);
			types.add(Type.DISCOUNTRECORDSP);
			types.add(Type.DISCOUNTRECORDPL);
			types.add(Type.INQUIRYREPLY);
			
			SysteminfoForm nf = new SysteminfoForm();
			nf.setTypes(types);
			nf.setMemberId(memberId);
			PageResults<Systeminfo> pageResults = systeminfoService.getPageList(nf, pageIndex, pageSize);
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			if(pageResults.getResults()!=null){
				list = BeanQuery.select("id,memberId,type,alert,content,discountrecordId,readState,createTime").from(pageResults.getResults()).execute();
				for(Map<String,Object> m:list){
					Object id = m.get("discountrecordId");
					if(m!=null && id!=null && m.get("type").equals(Type.DISCOUNTRECORD)){
						Discountrecord dis = discountrecordService.getById((Integer)id);
						if(dis!=null){
							m.put("allmoney", dis.getAllmoney());
							m.put("ordernumber", dis.getOrdernumber());
							m.put("orderflag", dis.getOrderflag());
							m.put("deposit", dis.getDeposit());
							m.put("bank", dis.getBank());
							m.put("endorse", dis.getEndorse());
							m.put("type1", dis.getType1());
							m.put("createTime", dis.getEndtime());
						}
					}else if(m!=null && id!=null && m.get("type").equals(Type.DISCOUNTRECORDSP)){
						DiscountrecordSp dis = discountrecordSpService.getById((Integer)id);
						if(dis!=null){
							m.put("allmoney", dis.getAllmoney());
							m.put("ordernumber", dis.getNo());
							m.put("orderflag", dis.getOrderflag());
							m.put("bank", dis.getBank());
							m.put("endorse", dis.getEndorse());
							m.put("type1", dis.getType1());
							m.put("createTime", dis.getEndtime());
						}
					}else if(m!=null && id!=null && m.get("type").equals(Type.DISCOUNTRECORDPL)){
						DiscountrecordPl dis = discountrecordPlService.getById((Integer)id);
						if(dis!=null){
							m.put("allmoney", dis.getAllmoney());
							m.put("ordernumber", dis.getNo());
							m.put("orderflag", dis.getOrderflag());
							m.put("amount", dis.getAmount());
							m.put("type1", dis.getType1());
							m.put("type2", dis.getType2());
							m.put("createTime", dis.getEndtime());
						}
					}else if(m!=null && id!=null && m.get("type").equals(Type.INQUIRYREPLY)){
						InquiryReply inq=inquiryReplyService.getById((Integer)id);
						if(inq!=null){
							m.put("allmoney",inq.getPayMoney());
							m.put("ordernumber",inq.getNo());
						}
					}
				}
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
	 * 机构订单消息列表
	 * @author xiaoc
	 */
	@RequestMapping("distributeorder2")
	public String getOrginfoPageListByMemberId(Integer memberId,Integer pageIndex,Integer pageSize,Model model)  throws IOException{
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			if(pageIndex==null)pageIndex = 0;
			if(pageSize==null)pageSize = 10;
			
			List<Type> types = new ArrayList<Systeminfo.Type>();
			types.add(Type.DISTRIBUTEORDER);
			types.add(Type.DISTRIBUTEORDERSP);
			types.add(Type.DISTRIBUTEORDERPL);
			types.add(Type.INQUIRYREPLY);
			
			SysteminfoForm nf = new SysteminfoForm();
			nf.setTypes(types);
			nf.setMemberId(memberId);
			PageResults<Systeminfo> pageResults = systeminfoService.getPageList(nf, pageIndex, pageSize);
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			if(pageResults.getResults()!=null){
				list = BeanQuery.select("id,memberId,type,alert,content,discountrecordId,readState,createTime").from(pageResults.getResults()).execute();
				for(Map<String,Object> m:list){
					Object id = m.get("discountrecordId");
					if(m!=null && id!=null && m.get("type").equals(Type.DISTRIBUTEORDER)){
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
							m.put("createTime", dit.get("endtime"));
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
							m.put("createTime", dit.get("endtime"));
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
							m.put("createTime", dit.get("endtime"));
							m.put("type2", dit.get("type2"));
						}
					}else if(m!=null && id!=null && m.get("type").equals(Type.INQUIRYREPLY)){
						InquiryReply inq=inquiryReplyService.getById((Integer)id);
						if(inq!=null){
							m.put("allmoney",inq.getPayMoney());
							m.put("no",inq.getNo());
						}
					}
				}
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
	 * 机构系统消息
	 * @author xiaoc
	 */
	@RequestMapping("systemnews")
	public String systemNews(Integer memberId,Integer pageIndex,Integer pageSize,Model model)  throws IOException{
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
			map.put("data",pageResults.getResults());
		}catch(Exception e){
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "获取失败");
		}		
		model.addAttribute("retValue", JacksonUtil.objToJson(map));
		return "ajax";
	}
	
	/**
	 * 更改阅读状态
	 * @author xiaoc
	 */
	@RequestMapping("changeReadState")
	public String read(Integer id,Float version,Model model)  throws IOException{
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Systeminfo info = systeminfoService.updateSysteminfoToReadById(id);
			
			if(info!=null && info.getType()==Type.DISCOUNTRECORD){
				Discountrecord dis = discountrecordService.getById(info.getDiscountrecordId());
				if(dis!=null){
					map.put("state", dis.getOrderflag());//订单状态
				}
			}else if(info!=null && info.getType()==Type.DISTRIBUTEORDER){
				Map<String,Object> dit = distributeOrderService.getInfoById(info.getDiscountrecordId());
				if(dit!=null){
					map.put("state",dit.get("state"));
				}
			}
			
			map.put("response","success");
			map.put("msg","操作成功");
			map.put("id",info.getDiscountrecordId());
			map.put("type",info.getType());
		} catch (Exception e) {
			map.put("response","failed");
			map.put("msg","操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(map));
		return "ajax";
	}
}