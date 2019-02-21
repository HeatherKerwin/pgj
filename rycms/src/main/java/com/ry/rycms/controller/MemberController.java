package com.ry.rycms.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Admin;
import com.ry.core.entity.Member;
import com.ry.core.entity.Systeminfo;
import com.ry.core.entity.Systeminfo.Type;
import com.ry.core.service.MemberService;
import com.ry.core.service.SysteminfoService;
import com.ry.core.util.JPushUtil;
import com.ry.util.DateUtil;
import com.ry.util.page.PageResults;

import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;


@Controller
public class MemberController {
	@Resource
	MemberService memberService;		
	
	@Resource
	SysteminfoService systeminfoService;
	
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
		public String alert;//标题
		public String content;//内容
		public boolean pushAll = false;//发送类型[true全部，false指定用户]
		public PushJob(Integer memberId,String alert,String content,boolean pushAll) {
	        this.memberId = memberId;
	        this.alert = alert;
	        this.content = content;
	        this.pushAll = pushAll;
	    }
		public void run() {
			try {
				Systeminfo systeminfo = new Systeminfo();
				if(pushAll)memberId = -1;
				systeminfo.setMemberId(memberId);
				systeminfo.setType(Type.OTHER);
				systeminfo.setAlert(alert);
				systeminfo.setContent(content);
				systeminfo.setCreateTime(new Date());
				systeminfoService.addSysteminfo(systeminfo);
				
				if(pushAll){
					JPushUtil.pushAllUser("【系统消息】"+alert, Type.SYSTEM);
				}else{
					JPushUtil.pushToAlias(String.valueOf(memberId), "【系统消息】"+alert,Type.SYSTEM);
				}
			} catch (APIConnectionException e) {
				e.printStackTrace();
			} catch (APIRequestException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 系统指定用户发送自定义消息[估计没什么用，后期可能会去掉]
	 * @author WKX
	 * @param memberId
	 * @param alert
	 * @param content
	 * @param pushAll 是否是全部推送
	 * @since 2016年1月6日 下午3:15:10
	 */
	private void doPushJob(Integer memberId,String alert,String content,boolean pushAll){
		PushJob job = new PushJob(memberId,alert,content,pushAll);
		initPool().execute(job);
	}
	
	@RequestMapping("/member/list/")
	public String list(String begintime, String endtime, String recommendpeople, Integer currentPage, HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		try{
			String begintimeStr = "";
			String endtimeStr = "";
			Date begintimed = null;
			Date endtimed = null;
			if(begintime==null||"".equals(begintime)){
				begintimed = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1970-01-01 00:00:00");
			}else{
				begintimed = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(begintime+" 00:00:00");
				begintimeStr = begintime;
			}
			if(endtime==null||"".equals(endtime)){
				endtimed = new Date();
			}else{
				endtimed = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endtime+" 23:59:59");
				endtimeStr = endtime;
			}
			if(recommendpeople==null){
				recommendpeople = "";
			}
			
			if(currentPage == null || currentPage < 0){
				currentPage = 0;
			}
			
			PageResults<Member> pageResults = new PageResults<Member>();			
			pageResults = memberService.getPageResults(begintimed, endtimed, recommendpeople, currentPage, 20);	
						
			request.setAttribute("memberallcount", memberService.count(null, null, null));
						
			request.setAttribute("begintimeStr", begintimeStr);
			request.setAttribute("endtimeStr", endtimeStr);
			request.setAttribute("memberList", pageResults.getResults());
			request.setAttribute("pageModel", pageResults);
			request.setAttribute("recommendpeople", recommendpeople);
		//	request.getRequestDispatcher("/manage/memberList.jsp").forward(request, response);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/customer/list";
	}
	
	@RequestMapping("/member/saleslist/")
	public String saleslist(String begintime, String endtime, String mobile,Integer isAccreditation,String isAdiminId, Integer currentPage, HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		try{
			if(currentPage == null || currentPage < 0){
				currentPage = 0;
			}
			Integer adminId = null;
			Admin admin = (Admin) request.getSession().getAttribute("admin");
			if(StringUtils.hasText(endtime)){//不为空，查询时间加一天
				endtime = DateUtil.formart(DateUtil.addDays(DateUtil.parser(endtime, DateUtil.FORMART3),1), DateUtil.FORMART3);
			}
			if(StringUtils.hasText(isAdiminId)){//不为空：是查看自己的，为空：查看所有销售的
				adminId = admin.getId();
			}
			if(!StringUtils.hasText(begintime)){
				begintime = DateUtil.formart(new Date(), DateUtil.FORMART3);
			}
			if(!StringUtils.hasText(endtime)){
				endtime = DateUtil.formart(DateUtil.addDays(1), DateUtil.FORMART3);
			}
			PageResults<Map<String,Object>> pageResults = new PageResults<Map<String,Object>>();	
			pageResults = memberService.getPageSalesMember(currentPage, 10, begintime, endtime, mobile, isAccreditation, adminId);
						
			request.setAttribute("memberallcount", memberService.count(null, null, null));
						
			request.setAttribute("begintimeStr", begintime);
			request.setAttribute("endtimeStr", endtime);
			request.setAttribute("memberList", pageResults.getResults());
			request.setAttribute("pageModel", pageResults);
			request.setAttribute("adminId", adminId);
			request.setAttribute("mobile", mobile);
		//	request.getRequestDispatcher("/manage/memberList.jsp").forward(request, response);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/customer/saleslist";
	}
	
	@RequestMapping("/member/bupdate/")
	public String bupdate(String memberid, HttpServletRequest request, HttpServletResponse response) {
		try{			
			List<Member> baseEntityList = memberService.getMemberList(memberid);
			if(baseEntityList!=null&&baseEntityList.size()!=0){
				request.setAttribute("member", (Member)baseEntityList.get(0));
				//request.getRequestDispatcher("/manage/memberUpdate.jsp").forward(request, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/customer/info";
	}
	
	/**
	 * 会员管理里面的发送消息
	 * @param admindesc
	 * @param sendmessage
	 * @param idcheckbox
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @since 2016年1月6日 下午3:18:52
	 */
	@RequestMapping("/member/sendmessage/")
	public void sendmessage(String admindesc, String sendmessage, String idcheckbox, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			if("1".equals(sendmessage)){//全部人
				//开启推送消息线程
				doPushJob(-1, admindesc, admindesc,true);
			}else if("2".equals(sendmessage)){
				if(!"".equals(idcheckbox)){
					String[] idcheckboxArray = idcheckbox.substring(0,idcheckbox.length()-1).split(",");
					for(String s : idcheckboxArray){
						//开启推送消息线程
						doPushJob(Integer.valueOf(s), admindesc, admindesc,false);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/member/update/")
	public void update(String memberid, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		try{			
//			List<Member> baseEntityList = memberService.getMemberList(memberid);
//			if(baseEntityList!=null&&baseEntityList.size()!=0){
//				request.setAttribute("member", (Member)baseEntityList.get(0));
//				request.getRequestDispatcher("/manage/memberUpdate.jsp").forward(request, response);
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		request.getRequestDispatcher("/manage/memberList.jsp").forward(request, response);
	}
	
	@RequestMapping("/member/delete/")
	public void delete(String memberid, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/manage/memberList.jsp").forward(request, response);
	}
}
