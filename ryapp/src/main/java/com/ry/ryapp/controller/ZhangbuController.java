package com.ry.ryapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Accountrecord;
import com.ry.core.entity.Activecount;
import com.ry.core.form.ActivecountForm;
import com.ry.core.service.AccountrecordService;
import com.ry.core.service.ActivecountService;
import com.ry.util.DataMatchUtil;

import net.sf.json.JSONArray;


@Controller
public class ZhangbuController {
	
	@Resource
	ActivecountService activecountService;
	
	@Resource
	AccountrecordService accountrecordService;
	
	@RequestMapping("/app/zhangbuadd/")
	public void add(HttpServletRequest request, HttpServletResponse response) throws IOException {		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		
		try{
			Integer memberid = 0;
			if (request.getParameter("memberid") != null && request.getParameter("memberid") != "") {
				memberid = Integer.valueOf(request.getParameter("memberid"));
			}				
			Activecount activecount = new Activecount();
			Long activetime = System.currentTimeMillis();
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Long begintimelong = dateFormat2.parse(dateFormat1.format(new Date(activetime))+" 00:00:00").getTime();
			Long endtimelong = dateFormat2.parse(dateFormat1.format(new Date(activetime))+" 23:59:59").getTime();
			ActivecountForm activecountForm = new ActivecountForm();
			activecountForm.setBegintimelong(begintimelong);
			activecountForm.setEndtimelong(endtimelong);
			activecountForm.setMemberid(memberid);
			List<Activecount> baseList2 = activecountService.getList(activecountForm);
			//List<BaseEntity> baseList2 = baseService.findObjectList("from Activecount where activetime >= ? and activetime <= ? and memberid like ?",new Object[]{begintimelong,endtimelong,memberid});
			if(baseList2!=null&&baseList2.size()!=0){
			
			}else{
				activecount.setActivetime(activetime);
				activecount.setMemberid(memberid);
				activecountService.addActivecount(activecount);
			}
			
			Integer type1 = Integer.valueOf(request.getParameter("type1"));
			Float allprice = Float.valueOf(request.getParameter("allprice"));
			String yuelilvStr = request.getParameter("yuelilv");
			Double yuelilv = null;
			if(yuelilvStr!=null&&!"".equals(yuelilvStr)){
				yuelilv = Double.valueOf(yuelilvStr);
			}
			String nianlilvStr = request.getParameter("nianlilv");
			Double nianlilv = null;
			if(nianlilvStr!=null&&!"".equals(nianlilvStr)){
				nianlilv = Double.valueOf(nianlilvStr);
			}
			String tiexiandateStr = request.getParameter("tiexiandate");
			Date tiexiandate = null;
//			if(tiexiandateStr!=null&&!"".equals(tiexiandateStr)){
				tiexiandate = new SimpleDateFormat("yyyy-MM-dd").parse(tiexiandateStr);
//			}
			String daoqidateStr = request.getParameter("daoqidate");
			Date daoqidate = null;
			if(daoqidateStr!=null&&!"".equals(daoqidateStr)){
				daoqidate = new SimpleDateFormat("yyyy-MM-dd").parse(daoqidateStr);
			}
			String tiaozhengStr = request.getParameter("tiaozheng");
			Integer tiaozheng = null;
			if(tiaozhengStr!=null&&!"".equals(tiaozhengStr)){
				tiaozheng = Integer.valueOf(tiaozhengStr);
			}
			String jixiStr = request.getParameter("jixi");
			Integer jixi = null;
			if(jixiStr!=null&&!"".equals(jixiStr)){
				jixi = Integer.valueOf(request.getParameter("jixi"));
			}
			Double tiexianlixi = Double.valueOf(request.getParameter("tiexianlixi"));
			Double tiexianjine = Double.valueOf(request.getParameter("tiexianjine"));
			String accountdesc = request.getParameter("accountdesc");
			
			Accountrecord accountrecord = new Accountrecord();
			accountrecord.setPublishtime(new Date());
			accountrecord.setAllprice(allprice);
			accountrecord.setDaoqidate(daoqidate);
			accountrecord.setJixi(jixi);
			accountrecord.setMemberid(memberid);
			accountrecord.setNianlilv(nianlilv);
			accountrecord.setPrice("0");
			accountrecord.setTiaozheng(tiaozheng);
			accountrecord.setTiexiandate(tiexiandate);
			accountrecord.setTiexianjine(tiexianjine);
			accountrecord.setTiexianlixi(tiexianlixi);
			accountrecord.setType1(DataMatchUtil.toDBFormPJ(type1));//@WKX 老的转新的
			accountrecord.setAccountdesc(accountdesc);
			accountrecord.setYuelilv(yuelilv);
			accountrecordService.saveAccountrecord(accountrecord);
			map.put("response", "success");
			map.put("msg", "添加成功");
			out.print(JSONArray.fromObject(map));
		}catch(Exception e){
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "添加失败");
			out.print(JSONArray.fromObject(map));
		}
	}	
	
	@RequestMapping("/app/zhangbudelete/")
	public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Integer accountrecordid = Integer.valueOf(request.getParameter("zhangbuid"));
			Accountrecord accountrecord = accountrecordService.getAccountrecord(accountrecordid);
			accountrecordService.delete(accountrecord);
			map.put("response", "success");
			map.put("msg", "删除成功");
			out.print(JSONArray.fromObject(map));
		}catch(Exception e){
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "获取信息失败");
			out.print(JSONArray.fromObject(map));
		}
	}
	
	@RequestMapping("/app/zhangbubupdate/")
	public void bupdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Integer accountrecordid = Integer.valueOf(request.getParameter("zhangbuid"));
			Accountrecord accountrecord = accountrecordService.getAccountrecord(accountrecordid);
			if(accountrecord!=null){				
				accountrecord.setDaoqidateStr(accountrecord.getDaoqidate()==null||"".equals(accountrecord.getDaoqidate())?"":new SimpleDateFormat("yyyy-MM-dd").format(accountrecord.getDaoqidate()));
				accountrecord.setTiexiandateStr(new SimpleDateFormat("yyyy-MM-dd").format(accountrecord.getTiexiandate()));
				accountrecord.setType1(DataMatchUtil.toAPPFormPJ(accountrecord.getType1()));//@WKX 新的转老的
				map.put("response", "success");
				map.put("msg", accountrecord);
				out.print(JSONArray.fromObject(map));
			}			
		}catch(Exception e){
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "获取信息失败");
			out.print(JSONArray.fromObject(map));
		}
	}
	
	@RequestMapping("/app/zhangbuupdate/")
	public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Integer accountrecordid = Integer.valueOf(request.getParameter("zhangbuid"));
			Integer memberid = 0;
			if (request.getParameter("memberid") != null) {
				memberid = Integer.valueOf(request.getParameter("memberid"));
			}				
			Integer type1 = Integer.valueOf(request.getParameter("type1"));
			Float allprice = Float.valueOf(request.getParameter("allprice"));
			String yuelilvStr = request.getParameter("yuelilv");
			Double yuelilv = null;
			if(yuelilvStr!=null&&!"".equals(yuelilvStr)){
				yuelilv = Double.valueOf(yuelilvStr);
			}
			String nianlilvStr = request.getParameter("nianlilv");
			Double nianlilv = null;
			if(nianlilvStr!=null&&!"".equals(nianlilvStr)){
				nianlilv = Double.valueOf(nianlilvStr);
			}
			String tiexiandateStr = request.getParameter("tiexiandate");
			Date tiexiandate = null;
//			if(tiexiandateStr!=null&&!"".equals(tiexiandateStr)){
				tiexiandate = new SimpleDateFormat("yyyy-MM-dd").parse(tiexiandateStr);
//			}
			String daoqidateStr = request.getParameter("daoqidate");
			Date daoqidate = null;
			if(daoqidateStr!=null&&!"".equals(daoqidateStr)){
				daoqidate = new SimpleDateFormat("yyyy-MM-dd").parse(daoqidateStr);
			}
			String tiaozhengStr = request.getParameter("tiaozheng");
			Integer tiaozheng = null;
			if(tiaozhengStr!=null&&!"".equals(tiaozhengStr)){
				tiaozheng = Integer.valueOf(tiaozhengStr);
			}
			String jixiStr = request.getParameter("jixi");
			Integer jixi = null;
			if(jixiStr!=null&&!"".equals(jixiStr)){
				jixi = Integer.valueOf(request.getParameter("jixi"));
			}
			Double tiexianlixi = Double.valueOf(request.getParameter("tiexianlixi"));
			Double tiexianjine = Double.valueOf(request.getParameter("tiexianjine"));
			String accountdesc = request.getParameter("accountdesc");
			Accountrecord accountrecord = accountrecordService.getAccountrecord(accountrecordid);
			if(accountrecord!=null){				
				accountrecord.setAllprice(allprice);
				accountrecord.setDaoqidate(daoqidate);
				accountrecord.setJixi(jixi);
				accountrecord.setMemberid(memberid);
				accountrecord.setNianlilv(nianlilv);
				accountrecord.setTiaozheng(tiaozheng);
				accountrecord.setTiexiandate(tiexiandate);
				accountrecord.setTiexianjine(tiexianjine);
				accountrecord.setTiexianlixi(tiexianlixi);
				accountrecord.setType1(DataMatchUtil.toDBFormPJ(type1));//@WKX 老的转新的
				accountrecord.setAccountdesc(accountdesc);
				accountrecord.setYuelilv(yuelilv);
				accountrecordService.update(accountrecord);
				map.put("response", "success");
				map.put("msg", "修改成功");
				out.print(JSONArray.fromObject(map));
			}
			
		}catch(Exception e){
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "修改失败");
			out.print(JSONArray.fromObject(map));
		}		
	}
	
	@RequestMapping("/app/zhangbulist/")
	public void list(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		
		try{
			Integer memberid = 0;
			if (request.getParameter("memberid") != null) {
				memberid = Integer.valueOf(request.getParameter("memberid"));
			}
			String day = request.getParameter("day");
			List<Accountrecord> baseEntityList = accountrecordService.getList(memberid, day);
			List<Accountrecord> accountrecordList = new ArrayList<Accountrecord>();
			if(baseEntityList!=null&&baseEntityList.size()!=0){
				for(Accountrecord baseEntity : baseEntityList){
					Accountrecord accountrecord = baseEntity;
					accountrecord.setTiexiandateStr(new SimpleDateFormat("yyyy-MM-dd").format(accountrecord.getTiexiandate()));
					accountrecord.setTiexiandateweek(new SimpleDateFormat("EEEE").format(accountrecord.getTiexiandate()));
					accountrecordList.add(accountrecord);
					accountrecord.setType1(DataMatchUtil.toAPPFormPJ(accountrecord.getType1()));//@WKX 新的转老的
				}
			}
			map.put("response", "success");
			map.put("msg", accountrecordList);
			out.print(JSONArray.fromObject(map));
		}catch(Exception e){
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "获取信息失败");
			out.print(JSONArray.fromObject(map));
		}
	}
	
}
