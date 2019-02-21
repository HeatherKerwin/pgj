package com.utiexian.website.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.DiscountrecordSp;
import com.ry.core.entity.Member;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.DiscountrecordSpService;
import com.ry.core.service.TiexianNoticeService;
import com.ry.core.util.Constant;
import com.ry.core.util.FileUtil;
import com.ry.util.DateUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.StringUtil;


/**
 * 商票贴现
 * @author MH
 *
 */
@Controller
public class DiscountrecordSpController {
	
	@Resource
	DiscountrecordSpService discountrecordSpService;
	
	@Resource
	TiexianNoticeService tiexianNoticeService;
	
	@Resource
	DiscountrecordService discountrecordService;
	
	/**
	 * 商票贴现下单
	 * @author MH
	 * @param model
	 * @throws Exception 
	 * @throws IOException
	 * @since 2016年8月15日 下午2:39:22
	 */
	@RequestMapping("discountrecordsp/tiexian")
	public String tieXian(HttpServletRequest request,Model model) throws Exception{
		
		DiscountrecordSp discountrecordSp = new DiscountrecordSp();
		
		String membermobile = request.getParameter("membermobile");
		discountrecordSp.setMemberMobile(membermobile);
		
		Integer type1 = Integer.valueOf(request.getParameter("type1"));
		discountrecordSp.setType1(type1);
		
		if(type1!=null && type1==2){
			Integer acceptTime =  Integer.valueOf(request.getParameter("acceptTime"));
			discountrecordSp.setAcceptTime(acceptTime);
			Integer tradeModel =  Integer.valueOf(request.getParameter("tradeModel"));
			discountrecordSp.setTradeModel(tradeModel);
		}
		
		String bank = request.getParameter("bank");
		discountrecordSp.setBank(bank);
		
		String allmoney = request.getParameter("allmoney");
		discountrecordSp.setAllmoney(Double.parseDouble(allmoney));
		
		String endorse = request.getParameter("endorse");
		discountrecordSp.setEndorse(Integer.valueOf(endorse));
		
		Integer  needTodoor = Integer.valueOf(request.getParameter("needTodoor"));
		discountrecordSp.setNeedTodoor(needTodoor);
		
		Integer hasTicket = Integer.valueOf(request.getParameter("hasTicket"));
		discountrecordSp.setHasTicket(hasTicket);
		
		String remarks = request.getParameter("remarks");
		discountrecordSp.setRemarks(remarks);
		
		String picpath = request.getParameter("picpath");
		List<String> tempstrs = new ArrayList<String>();
		//将临时目录文件转移到目录文件中
		if (org.apache.commons.lang.StringUtils.isNotBlank(picpath)) {
			String[] picpaths = picpath.split(",");
			String temp = Constant.properties.getProperty("uploadpath") + Constant.TEMP_PATH;//保存目录包名的绝对路径
			String uploadPath = Constant.properties.getProperty("uploadpath") + Constant.UPLOAD_SRC;//保存目录包名的绝对路径
			//检查目录
			File uploadDir = new File(uploadPath);
			if(!uploadDir.isDirectory()){
				uploadDir.mkdirs();
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			String extpath = df.format(new Date());
			for (int i = 0; i < picpaths.length; i++) {
				if (!"null".equalsIgnoreCase(picpaths[i])) {
					//临时文件夹 的文件 转到 保存目录中
					String pString = uploadPath + extpath;
					String tString = temp + "image" + File.separator + extpath;
					if (StringUtils.hasText(picpaths[i])) {
						tString += picpaths[i].substring(picpaths[i].lastIndexOf("/"), picpaths[i].length());
						FileUtil.moveFile(tString, pString);
					}
					String path = Constant.UPLOAD_SRC + extpath + picpaths[i].substring(picpaths[i].lastIndexOf("/"), picpaths[i].length());
					tempstrs.add(path);
				}
			}
		}
		
		picpath = "";
		if (tempstrs.size() > 0) {
			for (String tempstr : tempstrs) {
				picpath += tempstr ;
			}
		}								
		discountrecordSp.setPicpath(picpath);
		
		String first = request.getParameter("first");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		
		Date printtime = DateUtil.parser(first, DateUtil.FORMART3);	
		Date begintime = DateUtil.parser(start, DateUtil.FORMART3);	
		Date endtime = DateUtil.parser(end, DateUtil.FORMART3);
		
		discountrecordSp.setSource("PC");
		
		Member member =(Member) request.getSession().getAttribute("member");
		Integer  memberId = member.getId();
		discountrecordSp.setMemberId(memberId);
		
		discountrecordSp.setPrinttime(printtime);
		discountrecordSp.setBegintime(begintime);
		discountrecordSp.setEndtime(endtime);
		discountrecordSp.setNo(getNo());
		discountrecordSp.setOrderflag(1);//待确认
		discountrecordSp.setCreateTime(new Date());
		discountrecordSp.setHandleState(0);//默认待处理
		discountrecordSpService.saveModel(discountrecordSp);
		return  StringUtil.processRedirectUrl(Constant.properties.getProperty("baseUrl"),"/discountrecordsp/tiexian2?no="+discountrecordSp.getNo());
	}
	
	/**
	 *  添加完成后页面的选择贴现地址的展示
	 * @param request 
	 * @param no 订单号
	 * @return
	 * @throws ParseException 
	 * @author MH
	 */
	@RequestMapping("/discountrecordsp/tiexian2")
	public String tiexian2(HttpServletRequest request,String no) throws ParseException{
		DiscountrecordSp discountrecordSp=discountrecordSpService.getByOrdernumber(no);
		Integer num = DateUtil.daysBetween(discountrecordSp.getBegintime(),discountrecordSp.getEndtime());
		int tzts = 0;
		tzts = discountrecordService.getTzts(discountrecordSp.getType1(),discountrecordSp.getEndtime());//调整天数（根据票据类型）
		Integer jxts = tzts+num;
		String start = DateUtil.formart(discountrecordSp.getBegintime(),DateUtil.FORMART3);
		String end = DateUtil.formart(discountrecordSp.getEndtime(),DateUtil.FORMART3);
		String first = DateUtil.formart(discountrecordSp.getPrinttime(),DateUtil.FORMART3);
		
		request.setAttribute("discountrecordSp", discountrecordSp);
		request.setAttribute("first", first);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("jxts", jxts);
		
		return "discountrecord/discount_sp2";
	}
	
	/**
	 * 下单保存后保存（贴现地址信息）
	 * @author MH
	 * @param ordernumber 订单号
	 * @param ycname 用户名
	 * @param ycsex 性别
	 * @param ycphone 联系方式
	 * @param ycaddress 详细地址
	 * @param ycplace 交易城市
	 * @param yclon 经度
	 * @param yclat 纬度
	 * @param version
	 * @param model
	 * @since 
	 */
	@RequestMapping("/discountrecordsp/qrdd")
	public String savePayRecordBefore(String ordernumber,String ycname,String ycsex,
			String ycaddress,String ycplace,String yclon,String yclat ,String ycphone,Model model){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			DiscountrecordSp discountrecordSp = discountrecordSpService.getByOrdernumber(ordernumber);
			if(discountrecordSp==null)throw new Exception("数据异常");
			
			discountrecordSp.setMemberMobile(ycphone);
			discountrecordSp.setMemberName(ycname);
			discountrecordSp.setMemberSex(Integer.valueOf(ycsex));
			discountrecordSp.setAddress(ycaddress);
			discountrecordSp.setPlace(ycplace);
			discountrecordSp.setLongitude(Float.parseFloat(yclon));
			discountrecordSp.setLatitude(Float.parseFloat(yclat));
			discountrecordSp.setIsValid("T");
			discountrecordSpService.updateModel(discountrecordSp);
			result.put("response", "success");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "订单失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "discountrecord/discount_sp3";
	}
	
	/**
	 * 生成订单号
	 * @author MH
	 * @since 
	 */
	private String getNo(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String randomStr = String.valueOf(Math.random()*1000).split("\\.")[0];
		if(randomStr.length()==0){
			randomStr = "000";
		}
		if(randomStr.length()==1){
			randomStr = randomStr+"00";
		}
		if(randomStr.length()==2){
			randomStr = randomStr+"0";
		}
		String orderNumber = simpleDateFormat.format(new Date())+randomStr;
		return orderNumber;
	}
}
