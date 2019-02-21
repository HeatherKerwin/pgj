package com.ry.rycms.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Admin;
import com.ry.core.entity.Dayprice;
import com.ry.core.service.DaypriceService;
import com.ry.core.service.HistorypriceService;

/**
 *  
 * @author Ry-wk
 *
 */
@Controller
public class DaypriceController {
	@Resource
	DaypriceService daypriceService;
	
	@Resource
	HistorypriceService historypriceService;
	
	@RequestMapping("/admin/daypricesearch/")
	public String daypricesearch(String day,String time,String type4,HttpServletRequest request, HttpServletResponse response,Model mv) throws Exception{
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		if((!"admin".equals(admin.getUsername()))&&(!admin.getUsertype().contains("1"))){
			//response.sendRedirect(request.getContextPath()+"/login.jsp");
			return request.getContextPath()+"/login.jsp";
		}
		
		try{
			Integer type = 1;
			if(day == null){
				day = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			}
			if(time == null){
				time = "10:30";
			}
			if(type4 != null){
				type = Integer.valueOf(type4);
			}
			List<Dayprice> baseEntityList = daypriceService.getDaypriceList(day, type, time);			
			if(baseEntityList!=null&&baseEntityList.size()!=0){
				for(Dayprice baseEntity : baseEntityList){
					Dayprice dayprice = (Dayprice)baseEntity;
					if(dayprice.getType1()==1){//国股
						if(dayprice.getType2()==1){
							mv.addAttribute("hb1", dayprice);
						}
						if(dayprice.getType2()==2){
							mv.addAttribute("hs1", dayprice);
						}
					}
					if(dayprice.getType1()==2){//城商
						if(dayprice.getType2()==1){
							mv.addAttribute("hb2", dayprice);
						}
						if(dayprice.getType2()==2){
							mv.addAttribute("hs2", dayprice);
						}
					}
					if(dayprice.getType1()==3){//外资
						if(dayprice.getType2()==1){
							mv.addAttribute("hb3", dayprice);
						}
						if(dayprice.getType2()==2){
							mv.addAttribute("hs3", dayprice);
						}
					}
					if(dayprice.getType1()==4){//农商
						if(dayprice.getType2()==1){
							mv.addAttribute("hb4", dayprice);
						}
						if(dayprice.getType2()==2){
							mv.addAttribute("hs4", dayprice);
						}
					}
					if(dayprice.getType1()==5){//农合
						if(dayprice.getType2()==1){
							mv.addAttribute("hb5", dayprice);
						}
						if(dayprice.getType2()==2){
							mv.addAttribute("hs5", dayprice);
						}
					}
					if(dayprice.getType1()==6){//农信
						if(dayprice.getType2()==1){
							mv.addAttribute("hb6", dayprice);
						}
						if(dayprice.getType2()==2){
							mv.addAttribute("hs6", dayprice);
						}
					}
					if(dayprice.getType1()==7){//村镇
						if(dayprice.getType2()==1){
							mv.addAttribute("hb7", dayprice);
						}
						if(dayprice.getType2()==2){
							mv.addAttribute("hs7", dayprice);
						}
					}
					
				}
			}
			mv.addAttribute("day", day);
			mv.addAttribute("type4", type4);
			mv.addAttribute("time", time);
			//request.getRequestDispatcher("/manage/dayprice.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/price/dayprice";
		
	}
	
	@RequestMapping("/admin/updateprice/")
	public void updateprice(String day,Integer type4,String time,String b1,String b2,String b3,String b4,String b5,String b6,String b7,
			String s1,String s2,String s3,String s4,String s5,String s6,String s7,HttpServletRequest request, HttpServletResponse response) throws Exception{
		
			List<Dayprice> daypriceList = new ArrayList<Dayprice>();
			Dayprice hb1 = new Dayprice();
			hb1.setDay(day);
			hb1.setPrice(b1);
			hb1.setType1(1);
			hb1.setType2(1);
			hb1.setType4(type4);
			hb1.setTime(time);
			daypriceList.add(hb1);
	
			Dayprice hs1 = new Dayprice();
			hs1.setDay(day);
			hs1.setPrice(s1);
			hs1.setType1(1);
			hs1.setType2(2);
			hs1.setType4(type4);
			hs1.setTime(time);
			daypriceList.add(hs1);
			
			Dayprice hb2 = new Dayprice();
			hb2.setDay(day);
			hb2.setPrice(b2);
			hb2.setType1(2);
			hb2.setType2(1);
			hb2.setType4(type4);
			hb2.setTime(time);
			daypriceList.add(hb2);
	
			Dayprice hs2 = new Dayprice();
			hs2.setDay(day);
			hs2.setPrice(s2);
			hs2.setType1(2);
			hs2.setType2(2);
			hs2.setType4(type4);
			hs2.setTime(time);
			daypriceList.add(hs2);
			
			Dayprice hb3 = new Dayprice();
			hb3.setDay(day);
			hb3.setPrice(b3);
			hb3.setType1(3);
			hb3.setType2(1);
			hb3.setType4(type4);
			hb3.setTime(time);
			daypriceList.add(hb3);
	
			Dayprice hs3 = new Dayprice();
			hs3.setDay(day);
			hs3.setPrice(s3);
			hs3.setType1(3);
			hs3.setType2(2);
			hs3.setType4(type4);
			hs3.setTime(time);
			daypriceList.add(hs3);
			
			Dayprice hb4 = new Dayprice();
			hb4.setDay(day);
			hb4.setPrice(b4);
			hb4.setType1(4);
			hb4.setType2(1);
			hb4.setType4(type4);
			hb4.setTime(time);
			daypriceList.add(hb4);
	
			Dayprice hs4 = new Dayprice();
			hs4.setDay(day);
			hs4.setPrice(s4);
			hs4.setType1(4);
			hs4.setType2(2);
			hs4.setType4(type4);
			hs4.setTime(time);
			daypriceList.add(hs4);
			
			Dayprice hb5 = new Dayprice();
			hb5.setDay(day);
			hb5.setPrice(b5);
			hb5.setType1(5);
			hb5.setType2(1);
			hb5.setType4(type4);
			hb5.setTime(time);
			daypriceList.add(hb5);
	
			Dayprice hs5 = new Dayprice();
			hs5.setDay(day);
			hs5.setPrice(s5);
			hs5.setType1(5);
			hs5.setType2(2);
			hs5.setType4(type4);
			hs5.setTime(time);
			daypriceList.add(hs5);
			
			Dayprice hb6 = new Dayprice();
			hb6.setDay(day);
			hb6.setPrice(b6);
			hb6.setType1(6);
			hb6.setType2(1);
			hb6.setType4(type4);
			hb6.setTime(time);
			daypriceList.add(hb6);
	
			Dayprice hs6 = new Dayprice();
			hs6.setDay(day);
			hs6.setPrice(s6);
			hs6.setType1(6);
			hs6.setType2(2);
			hs6.setType4(type4);
			hs6.setTime(time);
			daypriceList.add(hs6);
			
			Dayprice hb7 = new Dayprice();
			hb7.setDay(day);
			hb7.setPrice(b7);
			hb7.setType1(7);
			hb7.setType2(1);
			hb7.setType4(type4);
			hb7.setTime(time);
			daypriceList.add(hb7);
	
			Dayprice hs7 = new Dayprice();
			hs7.setDay(day);
			hs7.setPrice(s7);
			hs7.setType1(7);
			hs7.setType2(2);
			hs7.setType4(type4);
			hs7.setTime(time);
			daypriceList.add(hs7);
			
			try{
				daypriceService.updateAllDayprice(daypriceList);			
			}catch(Exception e){
				e.printStackTrace();
			}
			//return "redirect:/admin/daypricesearch/";
			request.getRequestDispatcher("/admin/daypricesearch/").forward(request, response);
		
	}
}
