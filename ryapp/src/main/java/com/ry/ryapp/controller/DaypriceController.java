package com.ry.ryapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Dayprice;
import com.ry.core.entity.Historyprice;
import com.ry.core.service.AccountrecordService;
import com.ry.core.service.DaypriceService;
import com.ry.core.service.HistorypriceService;
import com.ry.util.DataMatchUtil;

import net.sf.json.JSONArray;


@Controller
public class DaypriceController {
	
	@Resource
	DaypriceService daypriceService;
	
	@Resource
	HistorypriceService historypriceService;
	
	@Resource
	AccountrecordService accountrecordService;
	
	@RequestMapping("/app/jiagezoushi/")
	public void jiagezoushi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();				
		String datestr = request.getParameter("datestr");
		
		try {
			List<Dayprice> guoguList = daypriceService.getList(null, datestr, 1, 1, 1);//国股
			List<Dayprice> nongshangList = daypriceService.getList(null, datestr, 4, 1, 1);//农商
			List<Dayprice> chengshangList = daypriceService.getList(null, datestr, 2, 1, 1);//城商
			List<Dayprice> type5List = daypriceService.getList(null, datestr, 5, 1, 1);//农合
			List<Dayprice> type6List = daypriceService.getList(null, datestr, 6, 1, 1);//农信
			List<Dayprice> type7List = daypriceService.getList(null, datestr, 7, 1, 1);//村镇
			
			List<Dayprice> guoguList1 = daypriceService.getList(null, datestr, 1, 2, 1);
			List<Dayprice> nongshangList1 = daypriceService.getList(null, datestr, 4, 2, 1);
			List<Dayprice> chengshangList1 = daypriceService.getList(null, datestr, 2, 2, 1);
			List<Dayprice> type5List1 = daypriceService.getList(null, datestr, 5, 2, 1);
			List<Dayprice> type6List1 = daypriceService.getList(null, datestr, 6, 2, 1);
			List<Dayprice> type7List1 = daypriceService.getList(null, datestr, 7, 2, 1);
			
			map.put("response", "success");
			map.put("guoguList", guoguList);
			map.put("nongshangList", nongshangList);
			map.put("chengshangList", chengshangList);
			map.put("type5List", type5List);
			map.put("type6List", type6List);
			map.put("type7List", type7List);
			map.put("guoguList1", guoguList1);
			map.put("nongshangList1", nongshangList1);
			map.put("chengshangList1", chengshangList1);
			map.put("type5List1", type5List1);
			map.put("type6List1", type6List1);
			map.put("type7List1", type7List1);
			out.print(JSONArray.fromObject(map));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 历史价格（之前查的表不对，老APPCAN存在NaN问题）
	 * @author EDIT WKX
	 * @param request
	 * @param response
	 * @throws IOException
	 * @since 2016年1月29日 上午9:28:02
	 */
	@RequestMapping("/app/kxiantu/")
	public void kxiantu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		String date = request.getParameter("date");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//1周
		if ("week".equals(date)) {
			try {
				Calendar c = Calendar.getInstance();
				c.add(Calendar.DATE, -7);
				String end = format.format(new Date());
				String start = format.format(c.getTime());
				
				List<Historyprice> guoguList = historypriceService.findPriceList(start,end, 1, 1, 1, 0, 7);
				List<Historyprice> nongshangList = historypriceService.findPriceList(start,end, 2, 1, 1, 0, 7);
				List<Historyprice> chengshangList = historypriceService.findPriceList(start,end, 4, 1, 1, 0, 7);
				List<Historyprice> type5List = historypriceService.findPriceList(start,end, 5, 1, 1, 0, 7);
				List<Historyprice> type6List = historypriceService.findPriceList(start,end, 6, 1, 1, 0, 7);
				List<Historyprice> type7List = historypriceService.findPriceList(start,end, 7, 1, 1, 0, 7);
				
				List<Historyprice> guoguList1 = historypriceService.findPriceList(start,end, 1, 2, 1, 0, 7);
				List<Historyprice> nongshangList1 = historypriceService.findPriceList(start,end, 2, 2, 1, 0, 7);
				List<Historyprice> chengshangList1 = historypriceService.findPriceList(start,end, 4, 2, 1, 0, 7);
				List<Historyprice> type5List1 = historypriceService.findPriceList(start,end, 5, 2, 1, 0, 7);
				List<Historyprice> type6List1 = historypriceService.findPriceList(start,end, 6, 2, 1, 0, 7);
				List<Historyprice> type7List1 = historypriceService.findPriceList(start,end, 7, 2, 1, 0, 7);
				
				Collections.reverse(guoguList);
				Collections.reverse(chengshangList);
				Collections.reverse(nongshangList);
				Collections.reverse(type5List);
				Collections.reverse(type6List);
				Collections.reverse(type7List);
				
				Collections.reverse(guoguList1);
				Collections.reverse(chengshangList1);
				Collections.reverse(nongshangList1);
				Collections.reverse(type5List1);
				Collections.reverse(type6List1);
				Collections.reverse(type7List1);
				
				map.put("response", "success");
				map.put("tickItv", 1);
				map.put("guoguList", guoguList);
				map.put("nongshangList", nongshangList);
				map.put("chengshangList", chengshangList);
				map.put("type5List", type5List);
				map.put("type6List", type6List);
				map.put("type7List", type7List);
				map.put("guoguList1", guoguList1);
				map.put("nongshangList1", nongshangList1);
				map.put("chengshangList1", chengshangList1);
				map.put("type5List1", type5List1);
				map.put("type6List1", type6List1);
				map.put("type7List1", type7List1);	
				out.print(JSONArray.fromObject(map));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//1个月		
		if ("month".equals(date)) {			
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);
			String end = format.format(new Date());
			String start = format.format(c.getTime());
			try {
				List<Historyprice> guoguList = historypriceService.findPriceList(start,end, 1, 1, 1, 0, 31);
				List<Historyprice> nongshangList = historypriceService.findPriceList(start,end, 2, 1, 1, 0, 31);
				List<Historyprice> chengshangList = historypriceService.findPriceList(start,end, 4, 1, 1, 0, 31);
				List<Historyprice> type5List = historypriceService.findPriceList(start,end, 5, 1, 1, 0, 31);
				List<Historyprice> type6List = historypriceService.findPriceList(start,end, 6, 1, 1, 0, 31);
				List<Historyprice> type7List = historypriceService.findPriceList(start,end, 7, 1, 1, 0, 31);
				
				List<Historyprice> guoguList1 = historypriceService.findPriceList(start,end, 1, 2, 1, 0, 31);
				List<Historyprice> nongshangList1 = historypriceService.findPriceList(start,end, 2, 2, 1, 0, 31);
				List<Historyprice> chengshangList1 = historypriceService.findPriceList(start,end, 4, 2, 1, 0, 31);
				List<Historyprice> type5List1 = historypriceService.findPriceList(start,end, 5, 2, 1, 0, 31);
				List<Historyprice> type6List1 = historypriceService.findPriceList(start,end, 6, 2, 1, 0, 31);
				List<Historyprice> type7List1 = historypriceService.findPriceList(start,end, 7, 2, 1, 0, 31);
				
				int tickItv = (int) (guoguList.size()*0.2); 
				
				Collections.reverse(guoguList);
				Collections.reverse(chengshangList);
				Collections.reverse(nongshangList);
				Collections.reverse(type5List);
				Collections.reverse(type6List);
				Collections.reverse(type7List);
				Collections.reverse(guoguList1);
				Collections.reverse(chengshangList1);
				Collections.reverse(nongshangList1);
				Collections.reverse(type5List1);
				Collections.reverse(type6List1);
				Collections.reverse(type7List1);
				
				int tickItv1 = (int) (guoguList1.size()*0.2); 
				
				map.put("response", "success");
				map.put("tickItv", tickItv);
				map.put("tickItv1", tickItv1);
				map.put("guoguList", guoguList);
				map.put("nongshangList", nongshangList);
				map.put("chengshangList", chengshangList);
				map.put("type5List", type5List);
				map.put("type6List", type6List);
				map.put("type7List", type7List);
				map.put("guoguList1", guoguList1);
				map.put("nongshangList1", nongshangList1);
				map.put("chengshangList1", chengshangList1);
				map.put("type5List1", type5List1);
				map.put("type6List1", type6List1);
				map.put("type7List1", type7List1);				
				out.print(JSONArray.fromObject(map));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//3个月		
		if ("tmonth".equals(date)) {
			try {
				Calendar c = Calendar.getInstance();
				c.add(Calendar.MONTH, -3);
				String end = format.format(new Date());
				String start = format.format(c.getTime());
				
				List<Historyprice> guoguList = historypriceService.findPriceList(start,end, 1, 1, 1, 0, 93);
				List<Historyprice> nongshangList = historypriceService.findPriceList(start,end, 2, 1, 1, 0, 93);
				List<Historyprice> chengshangList = historypriceService.findPriceList(start,end, 4, 1, 1, 0, 93);
				List<Historyprice> type5List = historypriceService.findPriceList(start,end, 5, 1, 1, 0, 93);
				List<Historyprice> type6List = historypriceService.findPriceList(start,end, 6, 1, 1, 0, 93);
				List<Historyprice> type7List = historypriceService.findPriceList(start,end, 7, 1, 1, 0, 93);
				
				List<Historyprice> guoguList1 = historypriceService.findPriceList(start,end, 1, 2, 1, 0, 93);
				List<Historyprice> nongshangList1 = historypriceService.findPriceList(start,end, 2, 2, 1, 0, 93);
				List<Historyprice> chengshangList1 = historypriceService.findPriceList(start,end, 4, 2, 1, 0, 93);
				List<Historyprice> type5List1 = historypriceService.findPriceList(start,end, 5, 2, 1, 0, 93);
				List<Historyprice> type6List1 = historypriceService.findPriceList(start,end, 6, 2, 1, 0, 93);
				List<Historyprice> type7List1 = historypriceService.findPriceList(start,end, 7, 2, 1, 0, 93);
				
				int tickItv = (int) (guoguList.size()*0.2); 
				
				Collections.reverse(guoguList);
				Collections.reverse(chengshangList);
				Collections.reverse(nongshangList);
				Collections.reverse(type5List);
				Collections.reverse(type6List);
				Collections.reverse(type7List);
				Collections.reverse(guoguList1);
				Collections.reverse(chengshangList1);
				Collections.reverse(nongshangList1);
				Collections.reverse(type5List1);
				Collections.reverse(type6List1);
				Collections.reverse(type7List1);
				
				int tickItv1 = (int) (guoguList1.size()*0.2); 
				
				map.put("response", "success");
				map.put("tickItv", tickItv);
				map.put("tickItv1", tickItv1);
				map.put("guoguList", guoguList);
				map.put("nongshangList", nongshangList);
				map.put("chengshangList", chengshangList);
				map.put("type5List", type5List);
				map.put("type6List", type6List);
				map.put("type7List", type7List);
				map.put("guoguList1", guoguList1);
				map.put("nongshangList1", nongshangList1);
				map.put("chengshangList1", chengshangList1);
				map.put("type5List1", type5List1);
				map.put("type6List1", type6List1);
				map.put("type7List1", type7List1);				
				out.print(JSONArray.fromObject(map));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//6个月
		if ("smonth".equals(date)) {
			try {
				Calendar c = Calendar.getInstance();
				c.add(Calendar.MONTH, -6);
				String end = format.format(new Date());
				String start = format.format(c.getTime());
				
				List<Historyprice> guoguList = historypriceService.findPriceList(start,end, 1, 1, 1, 0, 186);
				List<Historyprice> nongshangList = historypriceService.findPriceList(start,end, 2, 1, 1, 0, 186);
				List<Historyprice> chengshangList = historypriceService.findPriceList(start,end, 4, 1, 1, 0, 186);
				List<Historyprice> type5List = historypriceService.findPriceList(start,end, 5, 1, 1, 0, 186);
				List<Historyprice> type6List = historypriceService.findPriceList(start,end, 6, 1, 1, 0, 186);
				List<Historyprice> type7List = historypriceService.findPriceList(start,end, 7, 1, 1, 0, 186);
				
				List<Historyprice> guoguList1 = historypriceService.findPriceList(start,end, 1, 2, 1, 0, 186);
				List<Historyprice> nongshangList1 = historypriceService.findPriceList(start,end, 2, 2, 1, 0, 186);
				List<Historyprice> chengshangList1 = historypriceService.findPriceList(start,end, 4, 2, 1, 0, 186);
				List<Historyprice> type5List1 = historypriceService.findPriceList(start,end, 5, 2, 1, 0, 186);
				List<Historyprice> type6List1 = historypriceService.findPriceList(start,end, 6, 2, 1, 0, 186);
				List<Historyprice> type7List1 = historypriceService.findPriceList(start,end, 7, 2, 1, 0, 186);
				
				int tickItv = (int) (guoguList.size()*0.2);
				
				Collections.reverse(guoguList);
				Collections.reverse(chengshangList);
				Collections.reverse(nongshangList);
				Collections.reverse(type5List);
				Collections.reverse(type6List);
				Collections.reverse(type7List);
				Collections.reverse(guoguList1);
				Collections.reverse(chengshangList1);
				Collections.reverse(nongshangList1);
				Collections.reverse(type5List1);
				Collections.reverse(type6List1);
				Collections.reverse(type7List1);
				
				int tickItv1 = (int) (guoguList1.size()*0.2); 
				
				map.put("response", "success");
				map.put("tickItv", tickItv);
				map.put("tickItv1", tickItv1);
				map.put("guoguList", guoguList);
				map.put("nongshangList", nongshangList);
				map.put("chengshangList", chengshangList);
				map.put("type5List", type5List);
				map.put("type6List", type6List);
				map.put("type7List", type7List);
				map.put("guoguList1", guoguList1);
				map.put("nongshangList1", nongshangList1);
				map.put("chengshangList1", chengshangList1);
				map.put("type5List1", type5List1);
				map.put("type6List1", type6List1);
				map.put("type7List1", type7List1);				
				out.print(JSONArray.fromObject(map));
			} catch (Exception e) {
				e.printStackTrace();
			}					
		}
	}
	
	@RequestMapping("/app/bingtu/")
	public void bingtu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		String date = request.getParameter("date");
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		String memberid = request.getParameter("memberId");
		String date1str = "";
		int days = 0;
		//1周		
		if ("week".equals(date)) {	
			days = 7;	
		} else if ("month".equals(date)) {
			days = 30;	
		} else if ("month12".equals(date)) {
			days = 30;	
		}
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -days);
		Date date1 = c.getTime();	
		date1str = df1.format(date1);
		List<Double> list11 = accountrecordService.allprice(memberid, date1str);
		List<Double> list22 = accountrecordService.tiexianlixi(memberid, date1str);

		try {
			List<Object> list3 = new ArrayList<Object>();						
			
			if (list11.get(0) != null && list22.get(0) != null) {
				double num1 = Double.parseDouble(list11.get(0).toString());
				double num2 = Double.parseDouble(list22.get(0).toString());
				list3.add(num1);
				list3.add(num2);
			}
			
			List<Object> params1 = new ArrayList<Object>();
			List<Object> params2 = new ArrayList<Object>();
			List<Object> params3 = new ArrayList<Object>();
			List<Object> params4 = new ArrayList<Object>();
			List<Object> params5 = new ArrayList<Object>();
			List<Object> params6 = new ArrayList<Object>();
			
			//@WKX 老的饼图获取的 承兑行 要匹配新的数据
			params1.add(DataMatchUtil.toOLDAPPFormBT(1));
			params2.add(DataMatchUtil.toOLDAPPFormBT(2));
			params3.add(DataMatchUtil.toOLDAPPFormBT(3));
			params4.add(DataMatchUtil.toOLDAPPFormBT(4));
			params5.add(DataMatchUtil.toOLDAPPFormBT(5));
			
			params6.add(DataMatchUtil.toOLDAPPFormBT(1));
			params6.add(DataMatchUtil.toOLDAPPFormBT(2));
			params6.add(DataMatchUtil.toOLDAPPFormBT(3));
			params6.add(DataMatchUtil.toOLDAPPFormBT(4));
			params6.add(DataMatchUtil.toOLDAPPFormBT(5));
			Double t1 = accountrecordService.sallprice(memberid, date1str, params1);
			Double t2 = accountrecordService.sallprice(memberid, date1str, params2);
			Double t3 = accountrecordService.sallprice(memberid, date1str, params3);
			Double t4 = accountrecordService.sallprice(memberid, date1str, params4);
			Double t5 = accountrecordService.sallprice(memberid, date1str, params5);
			Double t6 = accountrecordService.sallprice(memberid, date1str, params6);
			 
			List<Object> list1 = new ArrayList<Object>();
			Double f1 = t1/t6;
			String result1 = String.format("%.2f", f1);
			Double f2 = t2/t6;
			String result2 = String.format("%.2f", f2);
			Double f3 = t3/t6;
			String result3 = String.format("%.2f", f3);
			Double f4 = t4/t6;
			String result4 = String.format("%.2f", f4);
			Double f5 = t5/t6;
			String result5 = String.format("%.2f", f5);
			
			list1.add(result1);
			list1.add(result2);
			list1.add(result3);
			list1.add(result4);
			list1.add(result5);
			
			map.put("response", "success");			
			map.put("binList", list3);
			map.put("pjsxList", list1);
			out.print(JSONArray.fromObject(map));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
