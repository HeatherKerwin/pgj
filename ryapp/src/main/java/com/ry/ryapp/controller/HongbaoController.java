package com.ry.ryapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
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

import com.ry.core.entity.Discountrecord;
import com.ry.core.entity.DiscountrecordTask;
import com.ry.core.entity.DiscountrecordTask.Operator;
import com.ry.core.entity.HongbaoActivity;
import com.ry.core.entity.HongbaoDetail;
import com.ry.core.entity.HongbaoSend;
import com.ry.core.form.HongbaoDetailForm;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.DiscountrecordTaskService;
import com.ry.core.service.HongbaoService;
import com.ry.util.DateUtil;
import com.ry.util.PropertiesUtil;

import net.sf.json.JSONArray;

@Controller
public class HongbaoController {
	
	@Resource
	HongbaoService hongbaoService;

	@Resource 
	DiscountrecordService discountrecordService;
	
	@Resource 
	DiscountrecordTaskService discountrecordTaskService;
	
	@RequestMapping("/app/wdingdan/")
	public void hongbaohuodong(Integer memberid, HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException{
		PrintWriter out = response.getWriter();
		if (memberid == null || memberid <=0) {
			out.print(0);
			return;
		}
		try {
			//获取红包分类主键（已贴现完成）
			String tagId = PropertiesUtil.getConfigPropertiesValue("XIEXIANTAGID","101");
			HongbaoActivity hb = hongbaoService.getActivityByTagId(Integer.valueOf(tagId));
			List<Discountrecord> dlist = discountrecordService.getListFlag(null, memberid.toString(), 3);		
			if (hb != null && dlist != null && dlist.size() >0) {
				Discountrecord dis = dlist.get(0);
				DiscountrecordTask task = discountrecordTaskService.getByDiscountrecordIdAndOperator(dis.getId(),Operator.FINISH);
				if(hb!=null && task!=null){//判定红包活动有效期内，订单完成时间在红包活动期间内
					Date start = hb.getStartdate();
					Date end = hb.getEnddate();
					boolean in = DateUtil.daysBetween(task.getCreateTime(),end)>=0 && DateUtil.daysBetween(start,task.getCreateTime())>=0;
					if(in) {
						out.print(hb.getId());
						return;
					}
				}
			}
			out.print(0);
		} catch (Exception e) {
			e.printStackTrace();
			out.print(0);
		}
	}	
	
	@RequestMapping("/app/addHongbaoSend/")
	public void addHongbaoDetail(Integer memberid, Integer hid, String phone, HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException{
		HongbaoSend hongbaoSend = new HongbaoSend();
		hongbaoSend.setHid(hid);
		hongbaoSend.setMemberid(memberid);
		hongbaoSend.setStatus(0);
		hongbaoSend.setCreatetime(new Date());
		hongbaoSend.setUpdatetime(new Date());
		hongbaoSend.setPhone(phone);
		hongbaoService.addHongbaoSend(hongbaoSend);
	}
	
	@RequestMapping("/app/hongbaolist/")
	public void hongbaolist(Integer memberid, HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException{
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();		
		map.put("response", "success");
//		List<HongbaoDetail> hongbaolist1 = new ArrayList<HongbaoDetail>();
		List<HongbaoDetail> hongbaolist = hongbaoService.getList(memberid);
		List<HongbaoDetail> historylist = hongbaoService.getHistoryList(memberid);	
		List<HongbaoDetail> usedlist = hongbaoService.getUsedList(memberid);
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		if (hongbaolist != null && hongbaolist.size() > 0) {
			for (int i = 0; i < hongbaolist.size(); i++) {
				HongbaoDetail hongbaoDetail = hongbaolist.get(i);
				HongbaoActivity activity = new HongbaoActivity();
				activity.setId(hongbaoDetail.getHid());	
				activity = hongbaoService.getActivity(activity);
				//有效期
				Date endDate1 = DateUtil.addDays(hongbaoDetail.getUpdatetime(), activity.getUsedays()-1);
				String endDate = sf.format(endDate1);
				//天数
				Integer tianshu = DateUtil.daysBetween(new Date(), endDate1);
				hongbaoDetail.setEndDate(endDate);
				hongbaoDetail.setShengyutianshu(tianshu);
//				hongbaolist1.add(hongbaoDetail);
			}
					
		}		
		List<HongbaoDetailForm> list = new ArrayList<HongbaoDetailForm>();
		if (historylist != null && historylist.size() > 0) {
			for (int i = 0; i < historylist.size(); i++) {
				HongbaoActivity activity = new HongbaoActivity();
				activity.setId(historylist.get(i).getHid());		
				activity = hongbaoService.getActivity(activity);
				Date enddate = activity.getEnddate();
				Long limitprice = activity.getLimitprice();				
				HongbaoDetailForm hForm = new HongbaoDetailForm();
				HongbaoDetail detail = historylist.get(i);
				hForm.setId(detail.getId());
				hForm.setCreatetime(detail.getCreatetime());
				hForm.setEnddate(hForm.getEnddate());
				hForm.setFlag(detail.getFlag());
				hForm.setMemberid(detail.getMemberid());
				hForm.setPhone(detail.getPhone());
				hForm.setUpdatetime(detail.getUpdatetime());
				hForm.setPrice(detail.getPrice());
				
				hForm.setEnddate(DateUtil.formart(enddate, DateUtil.FORMART3));				
				hForm.setLimitprice(limitprice);
				list.add(hForm);
			}
		}
		List<HongbaoDetailForm> ulist = new ArrayList<HongbaoDetailForm>();
		if (usedlist != null && usedlist.size() > 0) {
			for (int i = 0; i < usedlist.size(); i++) {
				HongbaoActivity activity = new HongbaoActivity();
				activity.setId(usedlist.get(i).getHid());		
				activity = hongbaoService.getActivity(activity);
				Date enddate = activity.getEnddate();
				Long limitprice = activity.getLimitprice();				
				HongbaoDetailForm hForm = new HongbaoDetailForm();
				HongbaoDetail detail = usedlist.get(i);
				hForm.setId(detail.getId());
				hForm.setCreatetime(detail.getCreatetime());
				hForm.setEnddate(hForm.getEnddate());
				hForm.setFlag(detail.getFlag());
				hForm.setMemberid(detail.getMemberid());
				hForm.setPhone(detail.getPhone());
				hForm.setUpdatetime(detail.getUpdatetime());
				hForm.setPrice(detail.getPrice());
				
				hForm.setEnddate(DateUtil.formart(enddate, DateUtil.FORMART3));				
				hForm.setLimitprice(limitprice);
				ulist.add(hForm);
			}
		}
		
		map.put("hongbaolist", hongbaolist);
		map.put("historylist", list);
		map.put("usedlist", ulist);
		
		out.print(JSONArray.fromObject(map));
	}
	
	@RequestMapping("/app/historylist/")
	public void historylist(Integer memberid, HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();		
		map.put("response", "success");
		map.put("msg", hongbaoService.getList(memberid));
		out.print(JSONArray.fromObject(map));
	}
}