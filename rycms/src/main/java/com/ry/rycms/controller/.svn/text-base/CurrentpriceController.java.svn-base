package com.ry.rycms.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Admin;
import com.ry.core.entity.Currentprice;
import com.ry.core.service.CurrentpriceService;

/**
 *  
 * @author Ry-wk
 *
 */
@Controller
public class CurrentpriceController {
	@Resource
	CurrentpriceService currentpriceService;
	
	@RequestMapping("/currentprice/list/")
	public String list(Integer type4,Integer type3,Integer type5,HttpServletRequest request, HttpServletResponse response, Model mv) throws Exception{
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		if((!"admin".equals(admin.getUsername()))&&(!admin.getUsertype().contains("1"))){
			return request.getContextPath()+"/login.jsp";
		}
		try{
			Currentprice c = new Currentprice();
			if(type3==null){
				c.setType3(1);
			} else {
				c.setType3(type3);
			}
			if(type4==null){
				c.setType4(1);
			} else {
				c.setType4(type4);
			}
			if(type5==null){
				c.setType5(1);
			} else {
				c.setType5(type5);
			}
			mv.addAttribute("cp", c);
			List<Currentprice> baseEntityList = currentpriceService.getList(c);
			if(baseEntityList!=null&&baseEntityList.size()!=0){
				for(Currentprice baseEntity : baseEntityList){
					Currentprice dayprice = (Currentprice)baseEntity;
					if(dayprice.getType1()==1){//国股
						if(dayprice.getType2()==1){
							mv.addAttribute("hb1", dayprice);
						}
						if(dayprice.getType2()==2){
							mv.addAttribute("hm1", dayprice);
						}
						if(dayprice.getType2()==3){
							mv.addAttribute("hs1", dayprice);
						}
						if(dayprice.getType2()==4){
							mv.addAttribute("hss1", dayprice);
						}
					}
					if(dayprice.getType1()==2){//城商
						if(dayprice.getType2()==1){
							mv.addAttribute("hb2", dayprice);
						}
						if(dayprice.getType2()==2){
							mv.addAttribute("hm2", dayprice);
						}
						if(dayprice.getType2()==3){
							mv.addAttribute("hs2", dayprice);
						}
						if(dayprice.getType2()==4){
							mv.addAttribute("hss2", dayprice);
						}
					}
					if(dayprice.getType1()==3){//外资
						if(dayprice.getType2()==1){
							mv.addAttribute("hb3", dayprice);
						}
						if(dayprice.getType2()==2){
							mv.addAttribute("hm3", dayprice);
						}
						if(dayprice.getType2()==3){
							mv.addAttribute("hs3", dayprice);
						}
						if(dayprice.getType2()==4){
							mv.addAttribute("hss3", dayprice);
						}
					}
					if(dayprice.getType1()==4){//农商
						if(dayprice.getType2()==1){
							mv.addAttribute("hb4", dayprice);
						}
						if(dayprice.getType2()==2){
							mv.addAttribute("hm4", dayprice);
						}
						if(dayprice.getType2()==3){
							mv.addAttribute("hs4", dayprice);
						}
						if(dayprice.getType2()==4){
							mv.addAttribute("hss4", dayprice);
						}
					}
					if(dayprice.getType1()==5){//农合
						if(dayprice.getType2()==1){
							mv.addAttribute("hb5", dayprice);
						}
						if(dayprice.getType2()==2){
							mv.addAttribute("hm5", dayprice);
						}
						if(dayprice.getType2()==3){
							mv.addAttribute("hs5", dayprice);
						}
						if(dayprice.getType2()==4){
							mv.addAttribute("hss5", dayprice);
						}
					}
					if(dayprice.getType1()==6){//农信
						if(dayprice.getType2()==1){
							mv.addAttribute("hb6", dayprice);
						}
						if(dayprice.getType2()==2){
							mv.addAttribute("hm6", dayprice);
						}
						if(dayprice.getType2()==3){
							mv.addAttribute("hs6", dayprice);
						}
						if(dayprice.getType2()==4){
							mv.addAttribute("hss6", dayprice);
						}
					}
					if(dayprice.getType1()==7){//村镇
						if(dayprice.getType2()==1){
							mv.addAttribute("hb7", dayprice);
						}
						if(dayprice.getType2()==2){
							mv.addAttribute("hm7", dayprice);
						}
						if(dayprice.getType2()==3){
							mv.addAttribute("hs7", dayprice);
						}
						if(dayprice.getType2()==4){
							mv.addAttribute("hss7", dayprice);
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		//	out.print("获取失败");
		}
		return "/price/currentpriceList";		
	}
	
	@RequestMapping("/currentprice/update/")
	public void update(Currentprice c,HttpServletRequest request, HttpServletResponse response) throws Exception{
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		if((!"admin".equals(admin.getUsername()))&&(!admin.getUsertype().contains("1"))){
			response.sendRedirect(request.getContextPath()+"/login.jsp");				
		}
		PrintWriter out = response.getWriter();
		try{
			if(c.getType3()==null){
				c.setType3(1);
			}
			if(c.getType4()==null){
				c.setType4(1);
			}
			if(c.getType5()==null){
				c.setType5(1);
			}
			Integer type3 = Integer.valueOf(c.getType3());
			Integer type4 = Integer.valueOf(c.getType4());
			Integer type5 = Integer.valueOf(c.getType5());
			String b1 = request.getParameter("b1");
			String b2 = request.getParameter("b2");
			String b3 = request.getParameter("b3");
			String b4 = request.getParameter("b4");
			String b5 = request.getParameter("b5");
			String b6 = request.getParameter("b6");
			String b7 = request.getParameter("b7");
			
			String s1 = request.getParameter("s1");
			String s2 = request.getParameter("s2");
			String s3 = request.getParameter("s3");
			String s4 = request.getParameter("s4");
			String s5 = request.getParameter("s5");
			String s6 = request.getParameter("s6");
			String s7 = request.getParameter("s7");
			
			String ss1 = request.getParameter("ss1");
			String ss2 = request.getParameter("ss2");
			String ss3 = request.getParameter("ss3");
			String ss4 = request.getParameter("ss4");
			String ss5 = request.getParameter("ss5");
			String ss6 = request.getParameter("ss6");
			String ss7 = request.getParameter("ss7");
			
			String m1 = request.getParameter("m1");
			String m2 = request.getParameter("m2");
			String m3 = request.getParameter("m3");
			String m4 = request.getParameter("m4");
			String m5 = request.getParameter("m5");
			String m6 = request.getParameter("m6");
			String m7 = request.getParameter("m7");
			List<Currentprice> currentpriceList = new ArrayList<Currentprice>();
			Currentprice currentprice1 = new Currentprice();
			currentprice1.setPrice(b1);
			currentprice1.setType1(1);
			currentprice1.setType2(1);
			currentprice1.setType3(type3);
			currentprice1.setType4(type4);
			currentprice1.setType5(type5);
			currentpriceList.add(currentprice1);
			
			Currentprice currentprice2 = new Currentprice();
			currentprice2.setPrice(b2);
			currentprice2.setType1(2);
			currentprice2.setType2(1);
			currentprice2.setType3(type3);
			currentprice2.setType4(type4);
			currentprice2.setType5(type5);
			currentpriceList.add(currentprice2);
			
			Currentprice currentprice3 = new Currentprice();
			currentprice3.setPrice(b3);
			currentprice3.setType1(3);
			currentprice3.setType2(1);
			currentprice3.setType3(type3);
			currentprice3.setType4(type4);
			currentprice3.setType5(type5);
			currentpriceList.add(currentprice3);
			
			Currentprice currentprice4 = new Currentprice();
			currentprice4.setPrice(b4);
			currentprice4.setType1(4);
			currentprice4.setType2(1);
			currentprice4.setType3(type3);
			currentprice4.setType4(type4);
			currentprice4.setType5(type5);
			currentpriceList.add(currentprice4);
			
			Currentprice currentprice5 = new Currentprice();
			currentprice5.setPrice(b5);
			currentprice5.setType1(5);
			currentprice5.setType2(1);
			currentprice5.setType3(type3);
			currentprice5.setType4(type4);
			currentprice5.setType5(type5);
			currentpriceList.add(currentprice5);
			
			Currentprice currentprice6 = new Currentprice();
			currentprice6.setPrice(b6);
			currentprice6.setType1(6);
			currentprice6.setType2(1);
			currentprice6.setType3(type3);
			currentprice6.setType4(type4);
			currentprice6.setType5(type5);
			currentpriceList.add(currentprice6);
			
			Currentprice currentprice7 = new Currentprice();
			currentprice7.setPrice(b7);
			currentprice7.setType1(7);
			currentprice7.setType2(1);
			currentprice7.setType3(type3);
			currentprice7.setType4(type4);
			currentprice7.setType5(type5);
			currentpriceList.add(currentprice7);
			
			
			Currentprice currentprice8 = new Currentprice();
			currentprice8.setPrice(m1);
			currentprice8.setType1(1);
			currentprice8.setType2(2);
			currentprice8.setType3(type3);
			currentprice8.setType4(type4);
			currentprice8.setType5(type5);
			currentpriceList.add(currentprice8);
			
			Currentprice currentprice9 = new Currentprice();
			currentprice9.setPrice(m2);
			currentprice9.setType1(2);
			currentprice9.setType2(2);
			currentprice9.setType3(type3);
			currentprice9.setType4(type4);
			currentprice9.setType5(type5);
			currentpriceList.add(currentprice9);
			
			Currentprice currentprice10 = new Currentprice();
			currentprice10.setPrice(m3);
			currentprice10.setType1(3);
			currentprice10.setType2(2);
			currentprice10.setType3(type3);
			currentprice10.setType4(type4);
			currentprice10.setType5(type5);
			currentpriceList.add(currentprice10);
			
			Currentprice currentprice11 = new Currentprice();
			currentprice11.setPrice(m4);
			currentprice11.setType1(4);
			currentprice11.setType2(2);
			currentprice11.setType3(type3);
			currentprice11.setType4(type4);
			currentprice11.setType5(type5);
			currentpriceList.add(currentprice11);
			
			Currentprice currentprice12 = new Currentprice();
			currentprice12.setPrice(m5);
			currentprice12.setType1(5);
			currentprice12.setType2(2);
			currentprice12.setType3(type3);
			currentprice12.setType4(type4);
			currentprice12.setType5(type5);
			currentpriceList.add(currentprice12);
			
			Currentprice currentprice13 = new Currentprice();
			currentprice13.setPrice(m6);
			currentprice13.setType1(6);
			currentprice13.setType2(2);
			currentprice13.setType3(type3);
			currentprice13.setType4(type4);
			currentprice13.setType5(type5);
			currentpriceList.add(currentprice13);
			
			Currentprice currentprice14 = new Currentprice();
			currentprice14.setPrice(m7);
			currentprice14.setType1(7);
			currentprice14.setType2(2);
			currentprice14.setType3(type3);
			currentprice14.setType4(type4);
			currentprice14.setType5(type5);
			currentpriceList.add(currentprice14);
			
			Currentprice currentprice15 = new Currentprice();
			currentprice15.setPrice(s1);
			currentprice15.setType1(1);
			currentprice15.setType2(3);
			currentprice15.setType3(type3);
			currentprice15.setType4(type4);
			currentprice15.setType5(type5);
			currentpriceList.add(currentprice15);
			
			Currentprice currentprice16 = new Currentprice();
			currentprice16.setPrice(s2);
			currentprice16.setType1(2);
			currentprice16.setType2(3);
			currentprice16.setType3(type3);
			currentprice16.setType4(type4);
			currentprice16.setType5(type5);
			currentpriceList.add(currentprice16);
			
			Currentprice currentprice17 = new Currentprice();
			currentprice17.setPrice(s3);
			currentprice17.setType1(3);
			currentprice17.setType2(3);
			currentprice17.setType3(type3);
			currentprice17.setType4(type4);
			currentprice17.setType5(type5);
			currentpriceList.add(currentprice17);
			
			Currentprice currentprice18 = new Currentprice();
			currentprice18.setPrice(s4);
			currentprice18.setType1(4);
			currentprice18.setType2(3);
			currentprice18.setType3(type3);
			currentprice18.setType4(type4);
			currentprice18.setType5(type5);
			currentpriceList.add(currentprice18);
			
			Currentprice currentprice19 = new Currentprice();
			currentprice19.setPrice(s5);
			currentprice19.setType1(5);
			currentprice19.setType2(3);
			currentprice19.setType3(type3);
			currentprice19.setType4(type4);
			currentprice19.setType5(type5);
			currentpriceList.add(currentprice19);
			
			Currentprice currentprice20 = new Currentprice();
			currentprice20.setPrice(s6);
			currentprice20.setType1(6);
			currentprice20.setType2(3);
			currentprice20.setType3(type3);
			currentprice20.setType4(type4);
			currentprice20.setType5(type5);
			currentpriceList.add(currentprice20);
			
			Currentprice currentprice21 = new Currentprice();
			currentprice21.setPrice(s7);
			currentprice21.setType1(7);
			currentprice21.setType2(3);
			currentprice21.setType3(type3);
			currentprice21.setType4(type4);
			currentprice21.setType5(type5);
			currentpriceList.add(currentprice21);
			
			Currentprice currentprice22 = new Currentprice();
			currentprice22.setPrice(ss1);
			currentprice22.setType1(1);
			currentprice22.setType2(4);
			currentprice22.setType3(type3);
			currentprice22.setType4(type4);
			currentprice22.setType5(type5);
			currentpriceList.add(currentprice22);
			
			Currentprice currentprice23 = new Currentprice();
			currentprice23.setPrice(ss2);
			currentprice23.setType1(2);
			currentprice23.setType2(4);
			currentprice23.setType3(type3);
			currentprice23.setType4(type4);
			currentprice23.setType5(type5);
			currentpriceList.add(currentprice23);
			
			Currentprice currentprice24 = new Currentprice();
			currentprice24.setPrice(ss3);
			currentprice24.setType1(3);
			currentprice24.setType2(4);
			currentprice24.setType3(type3);
			currentprice24.setType4(type4);
			currentprice24.setType5(type5);
			currentpriceList.add(currentprice24);
			
			Currentprice currentprice25 = new Currentprice();
			currentprice25.setPrice(ss4);
			currentprice25.setType1(4);
			currentprice25.setType2(4);
			currentprice25.setType3(type3);
			currentprice25.setType4(type4);
			currentprice25.setType5(type5);
			currentpriceList.add(currentprice25);
			
			Currentprice currentprice26 = new Currentprice();
			currentprice26.setPrice(ss5);
			currentprice26.setType1(5);
			currentprice26.setType2(4);
			currentprice26.setType3(type3);
			currentprice26.setType4(type4);
			currentprice26.setType5(type5);
			currentpriceList.add(currentprice26);
			
			Currentprice currentprice27 = new Currentprice();
			currentprice27.setPrice(ss6);
			currentprice27.setType1(6);
			currentprice27.setType2(4);
			currentprice27.setType3(type3);
			currentprice27.setType4(type4);
			currentprice27.setType5(type5);
			currentpriceList.add(currentprice27);
			
			Currentprice currentprice28 = new Currentprice();
			currentprice28.setPrice(ss7);
			currentprice28.setType1(7);
			currentprice28.setType2(4);
			currentprice28.setType3(type3);
			currentprice28.setType4(type4);
			currentprice28.setType5(type5);
			currentpriceList.add(currentprice28);
			
			currentpriceService.updateAllCurrentprice(currentpriceList);
			request.setAttribute("message", "修改成功");	
			out.print("修改成功");

		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "修改失败");
			out.print("修改失败");
		}			
		
		request.getRequestDispatcher("/currentprice/list/").forward(request, response);		
		
	}
}
