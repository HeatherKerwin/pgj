package com.ry.ryapp.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ry.core.entity.Activecount;
import com.ry.core.entity.Daylimit;
import com.ry.core.entity.Discountrecord;
import com.ry.core.entity.HongbaoDetail;
import com.ry.core.entity.IntegraltradingDetail;
import com.ry.core.entity.Member;
import com.ry.core.entity.Notice;
import com.ry.core.entity.Region;
import com.ry.core.entity.Saleprice;
import com.ry.core.entity.Systeminfo;
import com.ry.core.entity.Systeminfo.Type;
import com.ry.core.form.ActivecountForm;
import com.ry.core.service.AccountrecordService;
import com.ry.core.service.ActivecountService;
import com.ry.core.service.ActivityService;
import com.ry.core.service.DaylimitService;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.DistributeOrderService;
import com.ry.core.service.HongbaoService;
import com.ry.core.service.MemberService;
import com.ry.core.service.OrgInfoService;
import com.ry.core.service.PaidanService;
import com.ry.core.service.RegionService;
import com.ry.core.service.SalepriceService;
import com.ry.core.service.SysteminfoService;
import com.ry.core.service.TiexianNoticeService;
import com.ry.core.service.VariablesService;
import com.ry.core.util.Constant;
import com.ry.core.util.FileUtil;
import com.ry.core.util.JPushUtil;
import com.ry.core.util.JsonUtil;
import com.ry.ryapp.util.CacheUtil;
import com.ry.ryapp.util.unionpay.AcpService;
import com.ry.ryapp.util.unionpay.SDKConfig;
import com.ry.ryapp.util.unionpay.web.DemoBase;
import com.ry.util.AlipayUtil;
import com.ry.util.Bill99Util;
import com.ry.util.DataMatchUtil;
import com.ry.util.DateUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.SendMessagesUtil;

import net.sf.json.JSONArray;


@Controller
public class TiexianController {
	
	private static Logger logger = Logger.getLogger(TiexianController.class);
	
	private static Logger payLog = Logger.getLogger("payLog");
	
	@Resource
	DiscountrecordService discountrecordService;	
	
	@Resource
	AccountrecordService accountrecordService;
	
	@Resource
	MemberService memberService;
	
	@Resource
	ActivecountService activecountService;
	
	@Resource
	DaylimitService daylimitService;
	
	@Resource
	SalepriceService salepriceService;
	
	@Resource
	HongbaoService hongbaoService;
	
	@Resource
	TiexianNoticeService tiexianNoticeService;
	
	@Resource
	DistributeOrderService distributeOrderService;
	
	@Resource
	VariablesService variablesService;
	
	@Resource
	OrgInfoService orgInfoService;
	
	@Resource
	PaidanService paidanService;
	
	@Resource
	RegionService regionService;
	
	@Resource
	SysteminfoService systeminfoService;
	
	@Resource
	ActivityService activityService; 
	
	@RequestMapping("/app/tiexian/")
	public void gongcui(HttpServletRequest request, HttpServletResponse response) throws IOException {		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		try{			
			Integer salepriceid = Integer.valueOf(request.getParameter("salepriceid"));
			Integer type1 = Integer.valueOf(request.getParameter("type1"));
			Integer type2 = Integer.valueOf(request.getParameter("type2"));
			
			//@WKX 老APP承兑行转换
			type2 = DataMatchUtil.toDBFormTX(type2);
			
			Date begintime = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("begintime"));
			Date endtime = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("endtime"));
			String place = request.getParameter("place");
			float allmoney = Float.valueOf(request.getParameter("allmoney")); 
			Integer membersex = Integer.valueOf(request.getParameter("membersex"));
			String membername = request.getParameter("membername");
			String membermobile = request.getParameter("membermobile");
			String memberother = request.getParameter("memberother");
			Integer memberid = Integer.valueOf(request.getParameter("memberid"));
			String picpath = request.getParameter("picpath");
			List<String> tempstrs = new ArrayList<String>();
			//将临时目录文件转移到目录文件中			
			if (!"".equals(picpath)) {
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
									
			Discountrecord discountrecord = new Discountrecord();

			discountrecord.setAllmoney(allmoney);

			discountrecord.setBegintime(begintime);

			discountrecord.setEndtime(endtime);

			discountrecord.setMemberid(memberid);
			discountrecord.setMembermobile(membermobile);
			discountrecord.setMemberother(memberother);
			discountrecord.setMembersex(membersex);
			discountrecord.setMembername(membername);
			discountrecord.setOrderflag(Constant.ORDER_DAIQUEREN);
			discountrecord.setOrdertime(new Date());
			//修改数据库保存地址		
			picpath = "";
			if (tempstrs.size() > 0) {
				for (String tempstr : tempstrs) {
					picpath += tempstr + ",";
				}
			}								
			discountrecord.setPicpath(picpath);
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
			discountrecord.setOrdernumber(orderNumber);
			discountrecord.setPlace(place);
			discountrecord.setSalepriceid(salepriceid);
			discountrecord.setType1(type1);
			discountrecord.setType2(type2);
			List<Member> baseEntityList = memberService.getMemberList(memberid.toString());
			if(baseEntityList!=null&&baseEntityList.size()!=0){
				//日活
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
				if(baseList2!=null&&baseList2.size()!=0){
				
				}else{
					activecount.setActivetime(activetime);
					activecount.setMemberid(memberid);
					activecountService.addActivecount(activecount);
				}
				if(salepriceid==0){//非特价
					String day = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
					Date beginordertime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(day+" 00:00:00");
					Date endordertime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(day+" 23:59:59");
					List<Daylimit> baseList = daylimitService.getDaylimitList(day, null);
					//Float allmoneytotal = discountrecordService.findObjectCount("select sum(allmoney) from Discountrecord where ordertime <= ? and ordertime >= ? and orderflag!=-1", new Object[]{endordertime,beginordertime});
					Double allmoneytotal = discountrecordService.allmoney(beginordertime, endordertime);
					
					if(baseList!=null&&baseList.size()!=0){
						Daylimit daylimit = (Daylimit)baseList.get(0);
						if((allmoneytotal + allmoney) > daylimit.getLimitprice()){
							discountrecord.setOrderflag(Constant.ORDER_FAILED);
							discountrecordService.addDiscountrecord(discountrecord);
							map.put("response", "success");
							map.put("msg", "额度受限，稍后客服将与您联络");
							out.print(JSONArray.fromObject(map));
						}else{
							discountrecordService.addDiscountrecord(discountrecord);							
							String hid = request.getParameter("hid");
							if (StringUtils.hasText(hid)) {
								HongbaoDetail hongbaoDetail = new HongbaoDetail();
								hongbaoDetail = hongbaoService.getHongbaoDetail(Integer.parseInt(hid));
								if (hongbaoDetail.getFlag() == 0) {
									hongbaoDetail.setFlag(2);
									hongbaoDetail.setOrdernumber(orderNumber);
									hongbaoDetail.setUpdatetime(new Date());
									hongbaoService.updateHongbaoDetail(hongbaoDetail);
								}
							}	
							map.put("response", "success");
							map.put("msg", "下单成功");
							out.print(JSONArray.fromObject(map));
						}
					}else{
						discountrecord.setOrderflag(Constant.ORDER_FAILED);
						discountrecordService.addDiscountrecord(discountrecord);
						map.put("response", "success");
						map.put("msg", "额度受限，稍后客服将与您联络");
						out.print(JSONArray.fromObject(map));
					}					
				}else{//特价处理
					String s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
					//List<BaseEntity> baseEntityList2 = salepriceService.findObjectList("from Saleprice where id like ? and begintime < '"+s+"' and endtime > '"+s+"'", new Object[]{salepriceid});
					List<Saleprice> baseEntityList2 = salepriceService.getList(salepriceid, s, s); 
					if(baseEntityList2==null||baseEntityList2.size()==0){
						map.put("response", "failed");
						map.put("msg", "时间范围内没有活动");
						out.print(JSONArray.fromObject(map));
					}else{
						Saleprice saleprice = (Saleprice)baseEntityList2.get(0);
						//int allmoneysum = discountrecordService.findObjectCount("select sum(allmoney) from Discountrecord where salepriceid like ?",new Object[]{salepriceid});
						Double allmoneysum = discountrecordService.allmoney(salepriceid);
						if(allmoney<=saleprice.getAlllimit()-allmoneysum){
							discountrecordService.addDiscountrecord(discountrecord);							
							String hid = request.getParameter("hid");
							if (StringUtils.hasText(hid)) {
								HongbaoDetail hongbaoDetail = new HongbaoDetail();
								hongbaoDetail = hongbaoService.getHongbaoDetail(Integer.parseInt(hid));
								if (hongbaoDetail.getFlag() == 0) {
									hongbaoDetail.setFlag(2);
									hongbaoDetail.setOrdernumber(orderNumber);
									hongbaoDetail.setUpdatetime(new Date());
									hongbaoService.updateHongbaoDetail(hongbaoDetail);
								}
							}							
							map.put("response", "success");
							map.put("msg", "生成订单成功");
							out.print(JSONArray.fromObject(map));
						}else{
							map.put("response", "failed");
							map.put("msg", "贴现的金额超过限时特价总额");
							out.print(JSONArray.fromObject(map));
						}
					}
				}
			}else{
				map.put("response", "failed");
				map.put("msg", "用户不存在");
				out.print(JSONArray.fromObject(map));
			}
		}catch(Exception e){
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "操作失败,请稍后再试!");
			out.print(JSONArray.fromObject(map));
		}
	}
	
	/**
	 * 新的贴现
	 * @author WKX
	 * @param request
	 * @param response
	 * @throws IOException
	 * @since 2016年1月23日 下午3:17:33
	 */
	@RequestMapping("/app/tiexian/new")
	public void tieXian(HttpServletRequest request, HttpServletResponse response) throws IOException {		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		
		String error = "额度受限，稍后客服将与您联络";
		String msg = "下单成功";
		Notice notice = tiexianNoticeService.findByNowTime(DateUtil.formart(new Date(), "yyyy"));//查询本年度提示
		//如果在节假日定会额度受限,所以提醒非工作日的文字
		if(notice!=null && org.apache.commons.lang.StringUtils.isNotBlank(notice.getAlert())){
			error = notice.getAlert();
			msg = notice.getAlert();
		}else{
			notice = tiexianNoticeService.findByCode("ERROR");
			if(notice!=null && org.apache.commons.lang.StringUtils.isNotBlank(notice.getAlert()))error = notice.getAlert();
		}
		try{			
			Integer salepriceid = Integer.valueOf(request.getParameter("salepriceid"));
			Integer type1 = Integer.valueOf(request.getParameter("type1"));
			Integer type2 = Integer.valueOf(request.getParameter("type2"));
			Date begintime = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("begintime"));
			Date endtime = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("endtime"));
			String place = request.getParameter("place");
			float allmoney = Float.valueOf(request.getParameter("allmoney")); 
			Integer membersex = Integer.valueOf(request.getParameter("membersex"));
			String membername = request.getParameter("membername");
			String membermobile = request.getParameter("membermobile");
			String memberother = request.getParameter("memberother");
			Integer memberid = Integer.valueOf(request.getParameter("memberid"));
			String picpath = request.getParameter("picpath");
			List<String> tempstrs = new ArrayList<String>();
			//将临时目录文件转移到目录文件中			
			if (!"".equals(picpath)) {
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
									
			Discountrecord discountrecord = new Discountrecord();
			discountrecord.setAllmoney(allmoney);
			discountrecord.setBegintime(begintime);
			discountrecord.setEndtime(endtime);

			discountrecord.setMemberid(memberid);
			discountrecord.setMembermobile(membermobile);
			discountrecord.setMemberother(memberother);
			discountrecord.setMembersex(membersex);
			discountrecord.setMembername(membername);
			discountrecord.setOrderflag(Constant.ORDER_DAIQUEREN);
			discountrecord.setOrdertime(new Date());
			//修改数据库保存地址		
			picpath = "";
			if (tempstrs.size() > 0) {
				for (String tempstr : tempstrs) {
					picpath += tempstr + ",";
				}
			}								
			discountrecord.setPicpath(picpath);
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
			discountrecord.setOrdernumber(orderNumber);
			discountrecord.setPlace(place);
			discountrecord.setSalepriceid(salepriceid);
			discountrecord.setType1(type1);			discountrecord.setType2(type2);
			List<Member> baseEntityList = memberService.getMemberList(memberid.toString());
			if(baseEntityList!=null&&baseEntityList.size()!=0){
				//日活
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
				if(baseList2!=null&&baseList2.size()!=0){
				
				}else{
					activecount.setActivetime(activetime);
					activecount.setMemberid(memberid);
					activecountService.addActivecount(activecount);
				}
				if(salepriceid==0){//非特价
					String day = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
					Date beginordertime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(day+" 00:00:00");
					Date endordertime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(day+" 23:59:59");
					
//APP2.1中已经不需要维护额度（所以下面的额度判断暂定为6亿）
//					List<Daylimit> baseList = daylimitService.getDaylimitList(day, null);
					Double allmoneytotal = discountrecordService.allmoney(beginordertime, endordertime);
					
//					if(baseList!=null&&baseList.size()!=0){
//						Daylimit daylimit = (Daylimit)baseList.get(0);
					if((allmoneytotal + allmoney) > 60000){
						discountrecord.setOrderflag(Constant.ORDER_FAILED);
						discountrecordService.addDiscountrecord(discountrecord);
						map.put("response", "success");
						map.put("msg", error);
						out.print(JSONArray.fromObject(map));
					}else{
						discountrecordService.addDiscountrecord(discountrecord);							
						String hid = request.getParameter("hid");
						if (StringUtils.hasText(hid)) {
							HongbaoDetail hongbaoDetail = new HongbaoDetail();
							hongbaoDetail = hongbaoService.getHongbaoDetail(Integer.parseInt(hid));
							if (hongbaoDetail.getFlag() == 0) {
								hongbaoDetail.setFlag(2);
								hongbaoDetail.setOrdernumber(orderNumber);
								hongbaoDetail.setUpdatetime(new Date());
								hongbaoService.updateHongbaoDetail(hongbaoDetail);
							}
						}	
						map.put("response", "success");
						map.put("msg", msg);
						out.print(JSONArray.fromObject(map));
					}
//					}else{
//						discountrecord.setOrderflag(Constant.ORDER_FAILED);
//						discountrecordService.addDiscountrecord(discountrecord);
//						map.put("response", "success");
//						map.put("msg", error);
//						out.print(JSONArray.fromObject(map));
//					}					
				}else{//特价处理
					String s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
					List<Saleprice> baseEntityList2 = salepriceService.getList(salepriceid, s, s); 
					if(baseEntityList2==null||baseEntityList2.size()==0){
						map.put("response", "failed");
						map.put("msg", "时间范围内没有活动");
						out.print(JSONArray.fromObject(map));
					}else{
						Saleprice saleprice = (Saleprice)baseEntityList2.get(0);
						Double allmoneysum = discountrecordService.allmoney(salepriceid);
						if(allmoney<=saleprice.getAlllimit()-allmoneysum){
							discountrecordService.addDiscountrecord(discountrecord);							
							String hid = request.getParameter("hid");
							if (StringUtils.hasText(hid)) {
								HongbaoDetail hongbaoDetail = new HongbaoDetail();
								hongbaoDetail = hongbaoService.getHongbaoDetail(Integer.parseInt(hid));
								if (hongbaoDetail.getFlag() == 0) {
									hongbaoDetail.setFlag(2);
									hongbaoDetail.setOrdernumber(orderNumber);
									hongbaoDetail.setUpdatetime(new Date());
									hongbaoService.updateHongbaoDetail(hongbaoDetail);
								}
							}							
							map.put("response", "success");
							map.put("msg", "生成订单成功");
							out.print(JSONArray.fromObject(map));
						}else{
							map.put("response", "failed");
							map.put("msg", "贴现的金额超过限时特价总额");
							out.print(JSONArray.fromObject(map));
						}
					}
				}
			}else{
				map.put("response", "failed");
				map.put("msg", "用户不存在");
				out.print(JSONArray.fromObject(map));
			}
		}catch(Exception e){
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "操作失败,请稍后再试!");
			out.print(JSONArray.fromObject(map));
		}
	}
	
	/**
	 * 根据id查询订单信息
	 * @param id
	 * @return
	 * @date 2016年1月21日
	 * @author lvyanqin
	 */
	@RequestMapping("/app/tiexian/init/")
	@ResponseBody
	public Discountrecord showInit(Integer id){
		Discountrecord list = discountrecordService.getById(id);
		String json = JacksonUtil.obj2Str(list, DateUtil.FORMART3);
		list = JsonUtil.json2model(json, list);
		return list;
	}
	
	/**
	 * 贴现页面提示语查询
	 * @author WKX
	 * @since 2016年1月27日 下午1:46:38
	 */
	@RequestMapping("/app/topbar/")
	public @ResponseBody Notice topbar(){
		Notice notice = tiexianNoticeService.getFestivalByNowTime(new Date());//查询（今天是否是节假日）
		if(notice==null){
			return tiexianNoticeService.findByCode("OFFDAY");
		}
		return notice;
	}
	
	/**
	 * 初始化贴现提示语（和商票批量贴现功能开关）
	 * @author WKX
	 * @param model
	 * @since 2016年9月20日 上午9:28:25
	 */
	@RequestMapping("/app/sppl/switch")
	public String spplSwitch(String fun,Model model){
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String,Object> params = CacheUtil.getIndexParams("sppl");
		if(params!=null){
			result.put("sppl", params.get("sppl"));
		}else{
			String sppl = variablesService.getByCode("SPPLSWITCH", "ON");//商票批量贴现功能开关
			result.put("sppl", sppl);
			CacheUtil.setIndexParams(result);//存模拟缓存
		}
		result.put("fun", fun);
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/* ----------------------------------------APP2.1---------------------------------------------------------- */
	
	/**
	 * APP2.1贴现
	 * @param type1
	 * @param type2
	 * @param begintime
	 * @param endtime
	 * @param salepriceid
	 * @param place
	 * @param allmoney
	 * @param membersex
	 * @param membername
	 * @param membermobile
	 * @param memberother
	 * @param memberid
	 * @param picpath
	 * @param hid
	 * @param model
	 * @throws IOException
	 * @author WKX
	 */
	@RequestMapping("/app/tiexian2")
	public String tieXian(Integer type1,Integer type2,String begintime,String endtime,Integer salepriceid,String place,String address,Float allmoney,
			Integer membersex,String membername,String membermobile,String memberother,Integer memberid,String picpath,String hid,String bank,Integer acceptTime,Integer tradeModel,
			Float deposit,Integer payWay,Float longitude,Float latitude,Integer cityId,Float version,Integer endorse,Integer hasTicket,Integer flawTicket,Integer needTodoor,
			Model model) throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		
		String ps = Constant.properties.getProperty("VERSION_ALERT");//@WKX EDIT版本过期提示[因之前版本强制更新有问题，现做提示处理]
    	if("on".equals(ps) && version!=null && version<=2.32F){
    		result.put("response", "failed");
			result.put("msg", "当前版本已停用，请下载最新版本票据管家APP。");
			model.addAttribute("retValue",JacksonUtil.objToJson(result));
			return "ajax";
    	}
		
		String msg = "下单成功";
		String eMsg = "操作失败,请稍后再试!";
		
		Notice notice = tiexianNoticeService.getNoticeByNowTime();//获取节假日提示（法定节日、周末（非补班））用来贴现和查询查复的提醒
		if(notice!=null && org.apache.commons.lang.StringUtils.isNotBlank(notice.getAlert())){
			msg = notice.getAlert();
		}
		
		try{
			Date start = new SimpleDateFormat("yyyy-MM-dd").parse(begintime);
			Date end = new SimpleDateFormat("yyyy-MM-dd").parse(endtime);
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
			
			Discountrecord discountrecord = new Discountrecord();
			discountrecord.setAllmoney(allmoney);
			discountrecord.setBegintime(start);
			discountrecord.setEndtime(end);
			
			discountrecord.setSource("APP");

			discountrecord.setMemberid(memberid);
			discountrecord.setMembermobile(membermobile);
			discountrecord.setMemberother(memberother);
			discountrecord.setMembersex(membersex);
			discountrecord.setMembername(membername);
			discountrecord.setOrderflag(Constant.ORDER_DAIQUEREN);
			discountrecord.setOrdertime(new Date());
			
			discountrecord.setEndorse(endorse);
			discountrecord.setHasTicket(hasTicket);
			discountrecord.setFlawTicket(flawTicket);
			discountrecord.setNeedTodoor(needTodoor);
			discountrecord.setHandleState(0);//生成订单时，处理状态默认待处理
			//修改数据库保存地址		
			picpath = "";
			if (tempstrs.size() > 0) {
				for (String tempstr : tempstrs) {
					picpath += tempstr + ",";
				}
			}								
			discountrecord.setPicpath(picpath);
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
			discountrecord.setOrdernumber(orderNumber);
			discountrecord.setPlace(place);
			discountrecord.setSalepriceid(salepriceid);
			discountrecord.setType1(type1);			
			discountrecord.setType2(type2);
			
			Member member = memberService.getById(discountrecord.getMemberid());//企业端 待确认订单 发送消息
			if(member!=null){
				//日活
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
				if(baseList2!=null&&baseList2.size()!=0){
				
				}else{
					activecount.setActivetime(activetime);
					activecount.setMemberid(memberid);
					activecountService.addActivecount(activecount);
				}
				
				//APP2.2之后交易城市要转换为城市主键（老版本默认上海）
				if(cityId!=null){
					discountrecord.setCityId(cityId);//城市id
				}else if(org.apache.commons.lang.StringUtils.isNotBlank(place)){
					List<Region> regionL = regionService.getByNameAndType(place, "C");
					if(regionL!=null && regionL.size()>0){
						discountrecord.setCityId(regionL.get(0).getId());//城市id
					}else{
						discountrecord.setCityId(Constant.DEFAULT_CITY_ID);//城市id
					}
				}else{
					discountrecord.setCityId(Constant.DEFAULT_CITY_ID);//城市id
				}
				//APP2.2电票:承兑期限（0半年期、1一年期）
				if(type1!=null && type1==2){
					if(acceptTime!=null){
						discountrecord.setAcceptTime(acceptTime);
					}else{
						discountrecord.setAcceptTime(1);//默认电票一年期（适配老APP）
					}
					if(tradeModel!=null){
						discountrecord.setTradeModel(tradeModel);
					}else{
						discountrecord.setTradeModel(0);//交易模式（0先背书后打款、1先打款后背书）
					}
				}
				if(salepriceid==0){//非特价
					if(version!=null && version>=2.2F){//APP2.2加入支付保证金（派单）
						discountrecord.setAddress(address);
						discountrecord.setLongitude(longitude);
						discountrecord.setLatitude(latitude);
						discountrecord.setPayWay(payWay);
						discountrecord.setBank(bank);
						if(deposit==null)deposit = (float)getDeposit(memberid, allmoney);//在App2.3中要在生成订单时获取保证金
						if(version == 2.31F){//在APP 2.31 支付方式暂时改为不支付的方式
							deposit = (float) 0;
						}else if(version == 2.32F || version == 2.33F){
							payWay = 3;//版本2.32支付方式快钱（由于本次涉及老版本的订单号获取方式，暂使用该方式）
						}
						Map<String, Object> pay = toPay(deposit, payWay);//预支付（APP2.2）
						result.put("pay", pay);
						discountrecord.setDeposit(deposit);//保证金金额
						if(pay!=null){
							discountrecord.setBnsNo(pay.get("bnsNo").toString());
							if(pay.get("jyh")!=null)discountrecord.setJyh(pay.get("jyh").toString());
						}
					}
					//默认额度充足，系统派单（APP2.2之前版本）
					discountrecordService.addDiscountrecord(discountrecord);
					if (StringUtils.hasText(hid)) {
						HongbaoDetail hongbaoDetail = new HongbaoDetail();
						hongbaoDetail = hongbaoService.getHongbaoDetail(Integer.parseInt(hid));
						if (hongbaoDetail.getFlag() == 0) {
							hongbaoDetail.setFlag(2);
							hongbaoDetail.setOrdernumber(orderNumber);
							hongbaoDetail.setUpdatetime(new Date());
							hongbaoService.updateHongbaoDetail(hongbaoDetail);
						}
					}	
					
					result.put("data",discountrecord);//需要下单的主键
					result.put("response", "success");
					result.put("msg", msg);
					//if(version==null)paidanService.updatePaidan(discountrecord.getId(), paidanService.getConfigXML());//老版本系统派单[暂不系统派单]
				}else{//特价处理
					String s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
					List<Saleprice> baseEntityList2 = salepriceService.getList(salepriceid, s, s); 
					if(baseEntityList2==null||baseEntityList2.size()==0){
						result.put("response", "failed");
						result.put("msg", "时间范围内没有活动");
					}else{
						Saleprice saleprice = (Saleprice)baseEntityList2.get(0);
						Double allmoneysum = discountrecordService.allmoney(salepriceid);
						if(allmoney<=saleprice.getAlllimit()-allmoneysum){
							discountrecordService.addDiscountrecord(discountrecord);							
							if (StringUtils.hasText(hid)) {
								HongbaoDetail hongbaoDetail = new HongbaoDetail();
								hongbaoDetail = hongbaoService.getHongbaoDetail(Integer.parseInt(hid));
								if (hongbaoDetail.getFlag() == 0) {
									hongbaoDetail.setFlag(2);
									hongbaoDetail.setOrdernumber(orderNumber);
									hongbaoDetail.setUpdatetime(new Date());
									hongbaoService.updateHongbaoDetail(hongbaoDetail);
								}
							}							
							result.put("response", "success");
							result.put("msg", "生成订单成功");
						}else{
							result.put("response", "failed");
							result.put("msg", "贴现的金额超过限时特价总额");
						}
					}
				}
			}else{
				result.put("response", "failed");
				result.put("msg", "用户不存在");
			}
			payLog.info("银票下单：订单生成...用户主键:"+discountrecord.getMemberid()+"订单主键："+discountrecord.getId());
		}catch(Exception e){
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", eMsg);
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 初始化贴现保证金
	 * @author WKX
	 * @param memberId
	 * @param allmoney
	 * @param model
	 * @since 2016年5月17日 下午4:04:44
	 */
	@RequestMapping("/app/tiexian2/init")
	public String initDeposit(Integer memberId,Float allmoney,Model model){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result.put("data", getDeposit(memberId, allmoney));
			result.put("response", "success");
			result.put("msg", "初始化成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", "初始化异常");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 获取保证金金额（根据用户和贴现金额）
	 * @param memberId 用户主键
	 * @param allmoney 总金额
	 * @throws ParseException
	 * @author WKX
	 */
	private Integer getDeposit(Integer memberId,Float allmoney) throws ParseException{
		Integer money = 0;
		//step1 大小票（大票：20；小票：10）
		if(allmoney!=null && allmoney>500){
			money += 20;
		}else{
			money += 10;
		}
		//step2 认证过：0；未认证：20
		Map<String,Object> info = orgInfoService.getByMemberIdAndType(memberId,0);
		if(info!=null && info.get("state")!=null){
			if(2!=Integer.valueOf(info.get("state").toString())){
				money += 20;
			}
		}else{
			money += 20;
		}
		//step3 上月有已完成订单：10；上月无已完成订单：20
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1); 
		calendar.add(Calendar.DATE, -1);
		String end = DateUtil.formart(calendar.getTime(), DateUtil.FORMART3);
		Date e = DateUtil.parser(end+" 23:59:59", DateUtil.FORMART);
		
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		String start = DateUtil.formart(calendar.getTime(), DateUtil.FORMART3);
		Date s = DateUtil.parser(start+" 00:00:00", DateUtil.FORMART);
		Long amount = discountrecordService.countbyOrdertime(s, e, 3);//已完成
		if(amount!=null && amount>0){
			money += 10;
		}else{
			money += 20;
		}
		//step4 上月有拒绝订单：30；上月无拒绝订单：0
		Long bmount = discountrecordService.countbyOrdertime(s, e, 0);//拒绝
		if(bmount!=null && bmount>0){
			money += 30;
		}
		return money;
	}
	
	/**
	 * 支付保证金（银联）
	 * @param deposit 保证金（元）
	 * @param payWay 付款方式（0支付宝A、1微信W、2银联U）
	 * @param model
	 * @since 2016年5月18日 下午4:37:41
	 */
	@RequestMapping("/app/tiexian2.pay")
	public String pay(Integer deposit,Integer payWay,Model model){
		Map<String, Object> result = new HashMap<String, Object>();
		String prefix = "";
		try {
			prefix = payWay==0?"A":payWay==1?"W":"U";
			Map<String,Object> call = new HashMap<String, Object>();
			String orderId = discountrecordService.randomBnsNo(prefix);
			if(payWay==2){//银联
				String merId = "898320560120157";
				deposit = deposit*100;
				String txnAmt = String.valueOf(deposit);
				String txnTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
				
				Map<String, String> contentData = new HashMap<String, String>();
				
				/***银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改***/
				contentData.put("version", DemoBase.version);            //版本号 全渠道默认值
				contentData.put("encoding", DemoBase.encoding_UTF8);     //字符集编码 可以使用UTF-8,GBK两种方式
				contentData.put("signMethod", "01");           		 	//签名方法 目前只支持01：RSA方式证书加密
				contentData.put("txnType", "01");              		 	//交易类型 01:消费
				contentData.put("txnSubType", "01");           		 	//交易子类 01：消费
				contentData.put("bizType", "000201");          		 	//填写000201
				contentData.put("channelType", "08");          		 	//渠道类型 08手机

				/***商户接入参数***/
				contentData.put("merId", merId);   		 				//商户号码，请改成自己申请的商户号或者open上注册得来的777商户号测试
				contentData.put("accessType", "0");            		 	//接入类型，商户接入填0 ，不需修改（0：直连商户， 1： 收单机构 2：平台商户）
				contentData.put("orderId", orderId);        	 	    //商户订单号，8-40位数字字母，不能含“-”或“_”，可以自行定制规则	
				contentData.put("txnTime", txnTime);		 		    //订单发送时间，取系统时间，格式为YYYYMMDDhhmmss，必须取当前时间，否则会报txnTime无效
				contentData.put("accType", "01");					 	//账号类型 01：银行卡02：存折03：IC卡帐号类型(卡介质)
				contentData.put("txnAmt", txnAmt);						//交易金额 单位为分，不能带小数点
				contentData.put("currencyCode", "156");                 //境内商户固定 156 人民币
				
				contentData.put("backUrl", DemoBase.backUrl);
				
				/**对请求参数进行签名并发送http post请求，接收同步应答报文**/
				Map<String, String> reqData = AcpService.sign(contentData,DemoBase.encoding_UTF8);			 //报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。
				String requestAppUrl = SDKConfig.getConfig().getAppRequestUrl();								 //交易请求url从配置文件读取对应属性文件acp_sdk.properties中的 acpsdk.backTransUrl
				Map<String, String> rspData = AcpService.post(reqData,requestAppUrl,DemoBase.encoding_UTF8);  //发送请求报文并接受同步应答（默认连接超时时间30秒，读取返回结果超时时间30秒）;这里调用signData之后，调用submitUrl之前不能对submitFromData中的键值对做任何修改，如果修改会导致验签不通过
				
				if(rspData!=null)call.put("tn", rspData.get("tn"));
			}
			call.put("no", orderId);
			call.put("payWay", payWay);
			
			result.put("data", call);
			result.put("response", "success");
			result.put("msg", "支付成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", "支付失败");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 下单支付接口
	 * @author WKX
	 * @param deposit 保证金（元）
	 * @param payWay 付款方式（0支付宝A、1微信W、2银联U、3块钱K、4宝付B）
	 * @param model
	 * @since 2016年5月21日 下午7:13:07
	 */
	private Map<String, Object> toPay(Float deposit,Integer payWay){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String prefix = payWay == null ? "N" : payWay == 0 ? "A" : payWay == 1 ? "W" : payWay == 2 ? "U":payWay == 3 ? "K":payWay == 4 ? "B":"N";//N代表无支付方式的
			String bnsNo = discountrecordService.randomBnsNo(prefix);
			if(payWay!=null && payWay==2){//银联
				String merId = "898320560120157";
				deposit = deposit*100;
				String txnAmt = String.valueOf(deposit);
				String txnTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
				
				Map<String, String> contentData = new HashMap<String, String>();
				
				/***银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改***/
				contentData.put("version", DemoBase.version);            //版本号 全渠道默认值
				contentData.put("encoding", DemoBase.encoding_UTF8);     //字符集编码 可以使用UTF-8,GBK两种方式
				contentData.put("signMethod", "01");           		 	//签名方法 目前只支持01：RSA方式证书加密
				contentData.put("txnType", "01");              		 	//交易类型 01:消费
				contentData.put("txnSubType", "01");           		 	//交易子类 01：消费
				contentData.put("bizType", "000201");          		 	//填写000201
				contentData.put("channelType", "08");          		 	//渠道类型 08手机

				/***商户接入参数***/
				contentData.put("merId", merId);   		 				//商户号码，请改成自己申请的商户号或者open上注册得来的777商户号测试
				contentData.put("accessType", "0");            		 	//接入类型，商户接入填0 ，不需修改（0：直连商户， 1： 收单机构 2：平台商户）
				contentData.put("orderId", bnsNo);        	 	    //商户订单号，8-40位数字字母，不能含“-”或“_”，可以自行定制规则	
				contentData.put("txnTime", txnTime);		 		    //订单发送时间，取系统时间，格式为YYYYMMDDhhmmss，必须取当前时间，否则会报txnTime无效
				contentData.put("accType", "01");					 	//账号类型 01：银行卡02：存折03：IC卡帐号类型(卡介质)
				contentData.put("txnAmt", txnAmt);						//交易金额 单位为分，不能带小数点
				contentData.put("currencyCode", "156");                 //境内商户固定 156 人民币
				
				contentData.put("backUrl", DemoBase.backUrl);
				
				/**对请求参数进行签名并发送http post请求，接收同步应答报文**/
				Map<String, String> reqData = AcpService.sign(contentData,DemoBase.encoding_UTF8);			 //报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。
				String requestAppUrl = SDKConfig.getConfig().getAppRequestUrl();								 //交易请求url从配置文件读取对应属性文件acp_sdk.properties中的 acpsdk.backTransUrl
				Map<String, String> rspData = AcpService.post(reqData,requestAppUrl,DemoBase.encoding_UTF8);  //发送请求报文并接受同步应答（默认连接超时时间30秒，读取返回结果超时时间30秒）;这里调用signData之后，调用submitUrl之前不能对submitFromData中的键值对做任何修改，如果修改会导致验签不通过
				
				if(rspData!=null)result.put("jyh", rspData.get("tn"));
			}
			result.put("bnsNo", bnsNo);
			result.put("payWay", payWay);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 下单保存后保存（贴现地址信息）支付前
	 * @author WKX
	 * @param dcrdId
	 * @param membername 用户名
	 * @param membersex 性别
	 * @param membermobile 联系方式
	 * @param address 详细地址
	 * @param place 交易城市
	 * @param cityId 城市主键
	 * @param longitude 经度
	 * @param latitude 纬度
	 * @param version
	 * @param model
	 * @since 2016年9月6日 上午9:46:20
	 */
	@RequestMapping("/app/tiexian2/payrecord/save/before")
	public String savePayRecordBefore(Integer dcrdId,String membername,Integer membersex,String membermobile,Integer payWay,
			String address,String place,Integer cityId,Float longitude,Float latitude,Float version,Model model){
		Map<String, Object> result = new HashMap<String, Object>();
		
		String ps = Constant.properties.getProperty("VERSION_ALERT");//@WKX EDIT版本过期提示[因之前版本强制更新有问题，现做提示处理]
    	if("on".equals(ps) && version!=null && version<=2.32F){
    		result.put("response", "failed");
			result.put("msg", "当前版本已停用，请下载最新版本票据管家APP。");
			model.addAttribute("retValue",JacksonUtil.objToJson(result));
			return "ajax";
    	}
    	
		try {
			Discountrecord discountrecord = discountrecordService.getById(dcrdId);
			if(discountrecord==null)throw new Exception("数据异常");
			
			Map<String, Object> pay = toPay(null, payWay);//重新获取订单号
			discountrecord.setPayWay(payWay);
			discountrecord.setBnsNo(pay.get("bnsNo").toString());
			
			discountrecord.setMembername(membername);
			discountrecord.setMembersex(membersex);
			discountrecord.setMembermobile(membermobile);
			discountrecord.setAddress(address);
			discountrecord.setPlace(place);
			discountrecord.setCityId(cityId);
			discountrecord.setLongitude(longitude);
			discountrecord.setLatitude(latitude);
			discountrecordService.updateModel(discountrecord);
			result.put("response", "success");
			result.put("bnsNo", discountrecord.getBnsNo());//版本2.32开始添加的支付前获取最新的订单号码
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "支付失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 保存支付流水记录（APP2.2）
	 * @param dcrdId 订单编号
	 * @param state 状态
	 * @param des 描述
	 * @param model
	 * @author WKX
	 * @since 2016年5月25日 下午6:50:37
	 */
	@RequestMapping("/app/tiexian2/payrecord/save")
	public String savePayRecord(Integer dcrdId,Integer state,String des,Model model){
		Map<String, Object> result = new HashMap<String, Object>();
		payLog.info("下单支付：进入保存支付状态方法savePayRecord...订单主键："+dcrdId+"支付状态："+des);
		try {
			Discountrecord discountrecord = discountrecordService.getById(dcrdId);
			if(discountrecord!=null){
				if(0==state){
					discountrecord.setDepositState(1);//初始状态（已支付）
					try {
						Map<String,Object> cbPay = AlipayUtil.tradeQuery(discountrecord.getBnsNo());
						if(cbPay!=null){
							Object trade_no = cbPay.get("trade_no");
							Object buyer_email = cbPay.get("buyer_email");
							if(trade_no!=null)discountrecord.setJyh(trade_no.toString());//保存交易号
							if(buyer_email!=null)discountrecord.setCard(buyer_email.toString());//账号
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					pushAndSend(discountrecord.getMemberid(), discountrecord.getId(), Type.DISCOUNTRECORD, "已支付", "亲，您的订单保证金已支付成功，我们将立马处理您的订单！", "SMS_10621004");
				}else{
					pushAndSend(discountrecord.getMemberid(), discountrecord.getId(), Type.DISCOUNTRECORD, des, "亲，您的订单保证金未完成支付，快去我的订单完成支付吧！", "SMS_10637201");
				}
				paidanService.updateAndPayRecord(discountrecord, state, des);
				result.put("response", "success");
				result.put("msg", "操作成功");
				result.put("status", state);//返回支付时的返回值到前端
				result.put("des", des);//返回支付时的返回值到前端
				payLog.info("下单支付：更新支付状态完成savePayRecord...用户主键:"+discountrecord.getMemberid()+"订单主键："+discountrecord.getId());
			}else{
				result.put("response", "failed");
				result.put("msg", "网络异常");
			}
		} catch (Exception e) {
			payLog.error("下单支付：更新支付状态异常savePayRecord...订单主键："+dcrdId+"支付状态："+des);
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", "操作失败");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 推送消息、保存消息、发送短信
	 * @author WKX
	 * @param memberId
	 * @param fkId
	 * @param type
	 * @param alert
	 * @param des
	 * @param smsCode
	 * @since 2016年6月15日 下午9:22:08
	 */
	private void pushAndSend(Integer memberId,Integer fkId,Type type,String alert,String des,String smsCode){
		try {
			Member member = memberService.getById(memberId);
			if(member!=null){
				Systeminfo systeminfo = new Systeminfo();
				systeminfo.setMemberId(member.getId());
				systeminfo.setType(type);
				systeminfo.setAlert(alert);
				systeminfo.setContent(des);
				systeminfo.setDiscountrecordId(fkId);//外键（对应类型）
				systeminfo.setCreateTime(new Date());
				systeminfoService.addSysteminfo(systeminfo);
				
				Map<String,String> param = new HashMap<String, String>();
				SendMessagesUtil.sendMessage(member.getMobile(), smsCode, param);
				
				JPushUtil.doPushJob(member.getMobile(), type, des);
			}
		} catch (Exception e) {
			logger.error("订单流转消息发送异常："+e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 银票贴现支付
	 * @author WKX
	 * @param orderAmount 金额
	 * @param memberId 用户主键
	 * @param orderId 商户订单号
	 * @param model
	 * @throws Exception
	 * @since 2017年3月10日 下午3:51:09
	 */
	@RequestMapping("/app/discountrecord/pay")
	public String toPay(String orderAmount,String memberId,String orderId,Model model) throws Exception{
		String path_cb = Constant.properties.getProperty("BILL99URL_CB");
		String path_page = Constant.properties.getProperty("BILL99URL_PAGE");
		Map<String, String> paras = new LinkedHashMap<String, String>();

		if(org.apache.commons.lang.StringUtils.isBlank(orderAmount))orderAmount = "30";//30元
		paras.put("orderId", orderId);//商户订单号
		paras.put("payerId", memberId);//用户标识
		Discountrecord discountrecord = discountrecordService.getModelByBnsNoDiscount(orderId);
		paras.put("orderAmount", (int)(Double.valueOf(discountrecord.getDeposit())*100)+"");//订单金额（分）
//		paras.put("orderAmount", "100");//订单金额（分）
		paras.put("orderTime", new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()));//订单提交时间
		paras.put("productName", "贴现保证金");//产品名称
		paras.put("productNum", "1");//数量
		paras.put("bgUrl", path_cb + "/app/discountrecord/pay/cb");//后台回调
		paras.put("pageUrl", path_page + "/app/discountrecord/pay/cb/page");//回调页面
		model.addAttribute("retValue",Bill99Util.buildRequestPayForWap(paras));
		return "ajax";
	}
	
	/**
	 * 贴现支付回调后台（同支付宝一样，隔一段时间也会回调一次）
	 * @author WKX
	 * @param merchantAcctId 人民币账号
	 * @param bankId 银行代码
	 * @param orderId 商户订单号
	 * @param orderTime 订单交易时间
	 * @param orderAmount 商户订单金额
	 * @param bindCard 已绑短卡号
	 * @param bindMobile 已绑短手机尾号
	 * @param dealId 快钱交易号
	 * @param bankDealId 银行交易号
	 * @param fee 费用
	 * @param payResult 处理结果 (10：支付成功)
	 * @throws Exception
	 */
	@RequestMapping("/app/discountrecord/pay/cb")
	public @ResponseBody String cbPay(String merchantAcctId,String bankId,String orderId,String orderTime,String orderAmount,String bindCard,String bindMobile,String dealId,String bankDealId,String fee,String payResult,Model model) throws Exception{
		String path_page = Constant.properties.getProperty("BILL99URL_PAGE");
		String page = path_page + "/app/discountrecord/pay/cb/page";
		Discountrecord discountrecord = discountrecordService.getModelByBnsNoDiscount(orderId);
//		Discountrecord discountrecord = discountrecordService.getByBnsNoDiscount(orderId);
		if(discountrecord!=null){
			String des = "支付成功";
			if("10".equals(payResult)){
				List<IntegraltradingDetail> listactivity = activityService.getlistActivity("下单支付",DateUtil.formart(new Date(), DateUtil.FORMART3),discountrecord.getMemberid() );
				if(listactivity != null && listactivity.size()<5){
					activityService.timingIntegral(discountrecord.getMemberid(), 20, "下单支付", null);
				}
				
				discountrecord.setDepositState(1);//初始状态（已支付）
				discountrecord.setJyh(dealId);//保存交易号
				discountrecord.setCard(bindCard);//账号
				pushAndSend(discountrecord.getMemberid(), discountrecord.getId(), Type.DISCOUNTRECORD, "已支付", "亲，您的订单保证金已支付成功，我们将立马处理您的订单！", "SMS_10621004");
			}else{
				pushAndSend(discountrecord.getMemberid(), discountrecord.getId(), Type.DISCOUNTRECORD, des, "亲，您的订单保证金未完成支付，快去我的订单完成支付吧！", "SMS_10637201");
			}
			paidanService.updateAndPayRecord(discountrecord, Integer.valueOf(payResult), des);
			payLog.info("下单支付：更新支付状态完成cbPay...用户主键:"+discountrecord.getMemberid()+"订单主键："+discountrecord.getId());
		}
		return "<result>1</result><redirecturl>" + page + "</redirecturl>";
	}
	
	/**
	 * 订单查询（查询查复、下单、接单）快钱支付
	 * @author WKX
	 * @param orderId 商户订单号
	 * @param model
	 * @throws Exception
	 * @since 2017年3月2日 下午2:01:12
	 */
	@RequestMapping("/app/pay/query")
	public String query(String orderId,Model model) throws Exception{
		Map<String, String> paras = new LinkedHashMap<String, String>();
		paras.put("orderId", orderId);//商户订单号
		model.addAttribute("retValue",JacksonUtil.objToJson(Bill99Util.query(paras)));
		return "ajax";
	}
}