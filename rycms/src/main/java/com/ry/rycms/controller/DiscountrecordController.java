package com.ry.rycms.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Accountrecord;
import com.ry.core.entity.Admin;
import com.ry.core.entity.Daylimit;
import com.ry.core.entity.Discountrecord;
import com.ry.core.entity.DiscountrecordTask.Operator;
import com.ry.core.entity.HongbaoDetail;
import com.ry.core.entity.Member;
import com.ry.core.entity.Systeminfo;
import com.ry.core.entity.Systeminfo.Type;
import com.ry.core.service.AccountrecordService;
import com.ry.core.service.DaylimitService;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.HongbaoService;
import com.ry.core.service.MemberService;
import com.ry.core.service.SysteminfoService;
import com.ry.core.util.Constant;
import com.ry.core.util.JPushUtil;
import com.ry.util.DataMatchUtil;
import com.ry.util.DateUtil;
import com.ry.util.SendMessagesUtil;
import com.ry.util.page.PageResults;

import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;

@Controller
public class DiscountrecordController {

	@Resource
	DaylimitService daylimitService;
	
	@Resource
	DiscountrecordService discountrecordService;
			
	@Resource
	MemberService memberService;
	
	@Resource
	SysteminfoService systeminfoService;
	
	@Resource
	HongbaoService hongbaoService;
	
	@Resource
	AccountrecordService accountrecordService;
	
	private static final Logger logger = Logger.getLogger(DiscountrecordController.class);
	
	/**
	 * 创建线程池
	 * @author WKX
	 */
	public static ExecutorService  pool;
	public static synchronized ExecutorService initPool(){
		if(pool!=null){
			return pool;
		}else{
			pool = Executors.newFixedThreadPool(5);
			return pool;
		}
	}
	
	/**
	 * 推送消息[并且生成消息]线程
	 * @author WKX
	 */
	public class PushJob implements Runnable {
		public Integer memberId;//用户主键
		public Integer operator;//操作[前台传值转枚举]
		public Integer discountrecordId;//订单主键[贴现]
		public PushJob(Integer memberId,Integer operator,Integer discountrecordId) {
	        this.memberId = memberId;
	        this.operator = operator;
	        this.discountrecordId = discountrecordId;
	    }
		public void run() {
			try {
				Operator o = Operator.valueOf(operator);
				Systeminfo systeminfo = new Systeminfo();
				systeminfo.setMemberId(memberId);
				systeminfo.setType(Type.DISCOUNTRECORD);
				systeminfo.setAlert(o.getName());
				systeminfo.setContent(o.getName());
				systeminfo.setDiscountrecordId(discountrecordId);
				systeminfo.setCreateTime(new Date());
				systeminfoService.addSysteminfo(systeminfo);
				
				JPushUtil.pushToAlias(String.valueOf(memberId), "【订单消息】"+o.getName(),Type.DISCOUNTRECORD);
			} catch (APIConnectionException e) {
				e.printStackTrace();
			} catch (APIRequestException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 开启消息推送及保存线程[订单状态变更]
	 * @author WKX
	 * @param memberId 用户主键
	 * @param operator 操作标示
	 * @param discountrecordId 订单主键
	 * @since 2016年1月6日 下午2:56:38
	 */
	private void doPushJob(Integer memberId,Integer operator,Integer discountrecordId){
		PushJob job = new PushJob(memberId, operator, discountrecordId);
		initPool().execute(job);
	}
	
	@RequestMapping("/discountrecord/list/")
	public String list(String begintime, String endtime, String orderflag, Integer currentPage,HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			Admin admin = (Admin)request.getSession().getAttribute("admin");
			if(admin == null || (!"admin".equals(admin.getUsername()))&&(!admin.getUsertype().contains("2"))){
				response.sendRedirect(request.getContextPath()+"/login.jsp");
				return request.getContextPath()+"/login.jsp";
			}
//			request.setAttribute("allcount", discountrecordService.countbyOrderflag(null, null));
//			request.setAttribute("wcount", discountrecordService.countbyOrderflag(Constant.ORDER_DAIQUEREN, Constant.ORDER_YIQUEREN));
//			request.setAttribute("fcount", discountrecordService.countbyOrderflag(Constant.ORDER_YIWANCHENG, null));	
			
			//获取4天前的时间
			Date fourdaysbefore = DateUtil.addDays(-4);
			
			String begintimeStr = "";
			String endtimeStr = "";
			Long begintimed = null;
			Long endtimed = null;			
			
			if(begintime==null||"".equals(begintime)){
				begintimed = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1970-01-01 00:00:00").getTime();
			}else{
				begintimed = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(begintime+" 00:00:00").getTime();
				begintimeStr = begintime;
			}
			if(endtime==null||"".equals(endtime)){
				endtimed = new Date().getTime();
			}else{
				endtimed = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endtime+" 23:59:59").getTime();
				endtimeStr = endtime;
			}	
			currentPage = currentPage == null? 0 : currentPage;
						
			if("".equals(orderflag)||orderflag==null){
				PageResults<Discountrecord> pageResults = new PageResults<Discountrecord>();				
				pageResults = discountrecordService.findPageModel(currentPage, 20, new Object[]{begintimed,endtimed,fourdaysbefore.getTime()});
				
				List<Discountrecord> discountrecordList = new ArrayList<Discountrecord>();
				for(Discountrecord baseEntity : pageResults.getResults()){
					Discountrecord discountrecord = (Discountrecord)baseEntity;
					List<Member> list = memberService.getMemberList(discountrecord.getMemberid().toString());
					if (list != null && list.size()>0) {
						Member member = (Member)list.get(0);
						discountrecord.setRecommendpeople(member.getRecommendpeople());
						discountrecord.setMembername(member.getUsername());
					}else {
						discountrecord.setRecommendpeople("");
					}				
					discountrecord.setOrdertimeshow(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(discountrecord.getOrdertime()));
					discountrecordList.add(discountrecord);
				}
				pageResults.setResults(discountrecordList);
				
				request.setAttribute("discountrecordList", discountrecordList);
 				request.setAttribute("pageModel", pageResults);
				request.setAttribute("begintimeStr", begintimeStr);
				request.setAttribute("endtimeStr", endtimeStr);
				request.setAttribute("orderflag", "");
				//request.getRequestDispatcher("/manage/discountrecordList.jsp").forward(request, response);
			}else{
				PageResults<Discountrecord> pageResults = new PageResults<Discountrecord>();
				pageResults = discountrecordService.findPageModel(currentPage, 20, new Object[]{begintimed,endtimed,Integer.valueOf(orderflag),fourdaysbefore.getTime()});				
					
				List<Discountrecord> discountrecordList = new ArrayList<Discountrecord>();
				for(Discountrecord baseEntity : pageResults.getResults()){
					Discountrecord discountrecord = (Discountrecord)baseEntity;
					List<Member> list = memberService.getMemberList(discountrecord.getMemberid().toString());
					if (list != null && list.size()>0) {
						Member member = (Member)list.get(0);
						discountrecord.setRecommendpeople(member.getRecommendpeople());
						discountrecord.setMembername(member.getUsername());
					}else {
						discountrecord.setRecommendpeople("");
					}				
					discountrecord.setOrdertimeshow(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(discountrecord.getOrdertime()));
					discountrecordList.add(discountrecord);
				}
				pageResults.setResults(discountrecordList);
				
				request.setAttribute("discountrecordList", pageResults.getResults());
				request.setAttribute("pageModel", pageResults);
				request.setAttribute("begintimeStr", begintimeStr);
				request.setAttribute("endtimeStr", endtimeStr);
				request.setAttribute("orderflag", orderflag);
				//request.getRequestDispatcher("/manage/discountrecordList.jsp").forward(request, response);				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/order/list";
		
	}
	
	@RequestMapping("/discountrecord/bupdate/")
	public String bupdate(String discountrecordid,HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			Admin admin = (Admin)request.getSession().getAttribute("admin");
			if(admin == null || (!"admin".equals(admin.getUsername()))&&(!admin.getUsertype().contains("2"))){
				//response.sendRedirect(request.getContextPath()+"/login.jsp");
				return request.getContextPath()+"/login.jsp";
			}			
			List<Discountrecord> baseEntityList = discountrecordService.getList(discountrecordid);
			if(baseEntityList!=null&&baseEntityList.size()!=0){
				Discountrecord discountrecord = (Discountrecord)baseEntityList.get(0);
				discountrecord.setType2show(DataMatchUtil.getCDHByTypeFromNew(discountrecord.getType2()));//承兑行转换
				List<Member> baseEntityList2 = memberService.getMemberList(discountrecord.getMemberid().toString());
				if (discountrecord.getPicpath() != null && discountrecord.getPicpath() != "") {
					String[] paths = discountrecord.getPicpath().split(",");
					String path = "";
					for (String str : paths) {
						path += Constant.imgPath+str+",";
					}
					discountrecord.setPicpath(path.substring(0, path.length()-1));
				}				
				HongbaoDetail hongbaoDetail = hongbaoService.getHongbaoDetail(discountrecord.getOrdernumber());
				if (hongbaoDetail!= null) {					
					request.setAttribute("hongbaoid", hongbaoDetail.getId());
					if(hongbaoDetail.getPrice() == null || hongbaoDetail.getPrice() <= 0) {
						request.setAttribute("hongbaojine", 0);
					} else {
						request.setAttribute("hongbaojine", hongbaoDetail.getPrice());
					}
				} else {
					request.setAttribute("hongbaoid", 0);
				}									

				String[] pics = null;
				if(discountrecord.getPicpath() != null && discountrecord.getPicpath() != "") {
					String picpaths = discountrecord.getPicpath();
					pics= picpaths.split(",");
				}
				request.setAttribute("pics", pics);		   
				request.setAttribute("discountrecord", discountrecord);
				request.setAttribute("member", (Member)baseEntityList2.get(0));
				//request.getRequestDispatcher("/manage/discountrecordUpdate.jsp").forward(request, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/order/info";
	}	
	
	@RequestMapping("/discountrecord/update/")
	public String update(Integer discountrecordid, Integer flag1,Integer hongbaoid, HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			Admin admin = (Admin)request.getSession().getAttribute("admin");
			if(admin == null || (!"admin".equals(admin.getUsername()))&&(!admin.getUsertype().contains("2"))){
				response.sendRedirect(request.getContextPath()+"/login.jsp");
				return "ajax";
			}
			String reasondesc = request.getParameter("reasondesc");	
			String admindesc = request.getParameter("admindesc");				
			Integer id = discountrecordid;
			Discountrecord discountrecord = discountrecordService.getById(id);
			if(discountrecord!=null){
				discountrecord.setOrderflag(flag1);
				discountrecord.setAdmindesc(admindesc);
				discountrecord.setReasondesc(reasondesc);
				if(flag1 == 2){
					Accountrecord acc = accountrecordService.getAccountrecordByDiscountrecordId(id);
					if(acc == null){
						
						Accountrecord accountrecord = new Accountrecord();
						accountrecord.setAllprice(discountrecord.getAllmoney());
						accountrecord.setTiexiandate(discountrecord.getBegintime());
						accountrecord.setDaoqidate(discountrecord.getEndtime());
						accountrecord.setMemberid(discountrecord.getMemberid());
						accountrecord.setPublishtime(new Date());
						accountrecord.setCreateTime(new Date());
						accountrecord.setType1(discountrecord.getType2());
						if(discountrecord.getType1() == 1){
							accountrecord.setPiaojushuxing("纸票");
						}else{
							accountrecord.setPiaojushuxing("电票");
						}
						accountrecord.setIsTiexian("1");
						accountrecord.setTiexianType("系统");
						accountrecord.setDiscountrecordId(id);//关联id
						accountrecordService.saveAccountrecord(accountrecord);
					}
				}
				if(flag1==2 || flag1==3){
					//推送消息[并保存一条消息]
					Member member = memberService.getById(discountrecord.getMemberid());
					Map<String,String> param = new HashMap<String, String>();
					param.put("type1",discountrecord.getType1()==1?"纸票":"电票");
					param.put("type2",DataMatchUtil.getCDHByTypeFromNew(discountrecord.getType2()));
					param.put("allmoney", discountrecord.getAllmoney()!=null?discountrecord.getAllmoney().toString():"");
					param.put("endtime",DateUtil.formart(discountrecord.getEndtime(), "yyyy-MM-dd"));
					if(member!=null && member.getMobile()!=null){
						if(flag1==2)SendMessagesUtil.sendMessage(member.getMobile(), "SMS_4761287", param);
						if(flag1==3)SendMessagesUtil.sendMessage(member.getMobile(), "SMS_4771182", param);
					}
					doPushJob(discountrecord.getMemberid(), flag1, id);
				}
				if(flag1==0){
					if (hongbaoid != null && hongbaoid > 0) {
						HongbaoDetail hongbaoDetail = hongbaoService.getHongbaoDetail(hongbaoid);
						hongbaoDetail.setFlag(0);
						hongbaoService.updateHongbaoDetail(hongbaoDetail);
					}					
				}
				if(flag1==-1) {
					if (hongbaoid != null && hongbaoid > 0) {
						HongbaoDetail hongbaoDetail = hongbaoService.getHongbaoDetail(hongbaoid);
						hongbaoDetail.setFlag(0);
						hongbaoService.updateHongbaoDetail(hongbaoDetail);
					}					
				}
				
				discountrecordService.updateDis(discountrecord);				
			}
		}catch(Exception e){
			e.printStackTrace();			
		}
		return "redirect:/discountrecord/list/";
	}	
	
	@RequestMapping("/discountrecord/daylimitlist/")
	public String daypricesearch(String day,HttpServletRequest request, HttpServletResponse response) throws Exception{
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		if(admin == null && (!"admin".equals(admin.getUsername()))&&(!admin.getUsertype().contains("1"))){
			//response.sendRedirect(request.getContextPath()+"/login.jsp");
			return request.getContextPath()+"/login.jsp";
		}
		try{
			if(day==null||"".equals(day)){
				day = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			}
			List<Daylimit> baseEntityList = daylimitService.getDaylimitList(day,null);
			if(baseEntityList!=null&&baseEntityList.size()!=0){
				Long beginordertime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(day+" 00:00:00").getTime();
				Long endordertime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(day+" 23:59:59").getTime();
				Double allmoneytotal = daylimitService.countAllmoney(endordertime, beginordertime); 
				request.setAttribute("allmoneytotal", allmoneytotal);
				Daylimit daylimit = (Daylimit)baseEntityList.get(0);
				request.setAttribute("daylimit", daylimit);
				request.setAttribute("flag", "1");
			}else{
				request.setAttribute("flag", "0");
			}
			request.setAttribute("day", day);
			//request.getRequestDispatcher("/manage/daylimitList.jsp").forward(request, response);			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/price/daylimitList";
	}
	
	@RequestMapping("/discountrecord/daylimitbadd/")
	public void daylimitbadd(HttpServletRequest request, HttpServletResponse response) throws Exception{	
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		if((!"admin".equals(admin.getUsername()))&&(!admin.getUsertype().contains("1"))){			
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}
		request.getRequestDispatcher("/manage/daylimitAdd.jsp").forward(request, response);
	}
	
	@RequestMapping("/discountrecord/daylimitadd/")
	public String daylimitadd(String day, String limitprice, HttpServletRequest request, HttpServletResponse response) {
		try{
			Admin admin = (Admin)request.getSession().getAttribute("admin");
			if((!"admin".equals(admin.getUsername()))&&(!admin.getUsertype().contains("1"))){
				response.sendRedirect(request.getContextPath()+"/login.jsp");
				return "ajax";
			}						
			Daylimit daylimit = new Daylimit();
			daylimit.setDay(day);
			daylimit.setLimitprice(Integer.valueOf(limitprice));
			List<Daylimit> baseEntityList = daylimitService.getDaylimitList(day,null);
			if(baseEntityList==null||baseEntityList.size()==0){
				daylimitService.addDaylimit(daylimit);							
			}
		}catch(Exception e){
			//request.setAttribute("message", "添加失败");
			return "redirect:/discountrecord/daylimitlist/?day="+day;
		}
		
		return "redirect:/discountrecord/daylimitlist/";	
	}
	
	@RequestMapping("/discountrecord/daylimitupdate/")
	public String daylimitupdate(String id, String limitprice, String day,HttpServletRequest request, HttpServletResponse response) {
		try{
			Admin admin = (Admin)request.getSession().getAttribute("admin");
			if((!"admin".equals(admin.getUsername()))&&(!admin.getUsertype().contains("1"))){
				response.sendRedirect(request.getContextPath()+"/login.jsp");
				return "ajax";
			}
			List<Daylimit> baseEntityList = daylimitService.getDaylimitList(null, id);
			if(baseEntityList==null||baseEntityList.size()==0){
				
			}else{
				Daylimit daylimit = (Daylimit)baseEntityList.get(0);
				daylimit.setLimitprice(Integer.valueOf(limitprice));
				daylimitService.updateDaylimit(daylimit);								
			}
			
		}catch(Exception e){
			
			return "redirect:/discountrecord/daylimitlist/?day="+day;	
		}		
		return "redirect:/discountrecord/daylimitlist/?day="+day;	 
	}
	
	@RequestMapping("/discountrecord/delete/")
	public String delete(String discountrecordid, String limitprice, HttpServletRequest request, HttpServletResponse response) {
		try{
			Admin admin = (Admin)request.getSession().getAttribute("admin");
			if((!"admin".equals(admin.getUsername()))&&(!admin.getUsertype().contains("2"))){
				response.sendRedirect(request.getContextPath()+"/login.jsp");
				return "ajax";
			}						
			List<Discountrecord> baseEntityList = discountrecordService.getList(discountrecordid);
			if(baseEntityList!=null&&baseEntityList.size()!=0){
				Discountrecord discountrecord = (Discountrecord)baseEntityList.get(0);
				discountrecordService.deleteDis(discountrecord);
			}
			
			return "redirect:/discountrecord/list/";
		}catch(Exception e){
			e.printStackTrace();
			return "redirect:/discountrecord/list/";
		}
	}
}
