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
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ry.core.entity.Accountrecord;
import com.ry.core.entity.Historyprice;
import com.ry.core.entity.Member;
import com.ry.core.entity.Noticerecord;
import com.ry.core.form.AccountrecordForm;
import com.ry.core.service.AccountrecordService;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.HistorypriceService;
import com.ry.core.service.MemberService;
import com.ry.core.service.NoticerecordService;
import com.ry.core.service.OrgService;
import com.ry.core.util.Constant;
import com.ry.core.util.JsonUtil;
import com.ry.util.DataMatchUtil;
import com.ry.util.DateUtil;
import com.ry.util.EmailUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.jxlUtil;

import cn.jimmyshi.beanquery.BeanQuery;
import freemarker.template.Configuration;
import net.sf.json.JSONArray;

/**
 * @author GJJ
 * @date 2016年1月5日
 * 账簿管理类，负责查询已贴现或未贴现金额
 */
@Controller
public class GuanjiaController {

	@Resource
	private AccountrecordService accountrecordService;
	
	@Resource
	private MemberService memberService;
	
	@Resource
	private Configuration configuration;
	
	@Resource
	private HistorypriceService historypriceService;
	
	@Resource
	private DiscountrecordService discountrecordService;
	
	@Resource
	private NoticerecordService noticerecordService;
	
	@Resource
	OrgService orgService;
	
	private static final Logger logger = Logger.getLogger(GuanjiaController.class);
	
	/**
	 * 账簿首页交易总额查询
	 * @author GJJ
	 * @date 2016年1月8日
	 * @param response
	 * @param request
	 * @throws IOException
	 * @return void
	 * @throws ParseException 
	 * @time 2016年1月8日
	 * @EDIT WKX 2016-4-5 22:24 APP2.1
	 */
	@RequestMapping("/zhangbu/index/")
	public @ResponseBody Map<String, Object> countTiexian(Integer memberid,Integer belong) throws IOException, ParseException{
		if(belong==null)belong = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> result = accountrecordService.countPriceByDayAndMonth(memberid,belong);
		Date begin = com.ry.ryapp.util.DateUtil.getThisWeekMonday();
		Date end = DateUtil.addDays(begin, 6);
		List<Map<String,Object>> weekResult = accountrecordService.countPrice(memberid,belong,begin,end);
		for(Map<String, Object> resultMap : result){
			String tiexian = resultMap.get("tiexian")+"";
			if(StringUtils.isNotBlank(tiexian)){
				if(tiexian.equals("1")){
					String monthDiscounted = resultMap.get("monthNum")+"";
					String dayDiscounted = resultMap.get("dayNum")+"";
					map.put("monthdiscounted", monthDiscounted.toString());
					map.put("daydiscounted", dayDiscounted.toString());
				}else if(tiexian.equals("0")){
					String monthUndiscount = resultMap.get("monthNum")+"";
					String dayUndiscount = resultMap.get("dayNum")+"";
					map.put("monthundiscount", monthUndiscount);
					map.put("dayundiscount", dayUndiscount);
				}
			}else{
				map.put("failed", "没有查询结果");
			}
		}
		for(Map<String, Object> resultMap : weekResult){
			String tiexian = resultMap.get("tiexian")+"";
			if(tiexian != null){
				if(tiexian.equals("1")){
					String weekDiscounted = resultMap.get("weekNum")+"";
					map.put("weekdiscounted", weekDiscounted.toString());
				}else if(tiexian.equals("0")){
					String weekUndiscount = resultMap.get("weekNum")+"";
					map.put("weekundiscount", weekUndiscount);
				}
			}
		}
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		Date s = DateUtil.parser(year+"-01-01", DateUtil.FORMART3);
		Date e = DateUtil.parser((year+1)+"-01-01", DateUtil.FORMART3);
		List<Map<String,Object>> yearResult = accountrecordService.countPrice(memberid,belong,s,e);
		for(Map<String, Object> resultMap : yearResult){
			String tiexian = resultMap.get("tiexian")+"";
			if(tiexian != null){
				if(tiexian.equals("1")){
					String weekDiscounted = resultMap.get("weekNum")+"";
					map.put("yeardiscounted", weekDiscounted.toString());
				}else if(tiexian.equals("0")){
					String weekUndiscount = resultMap.get("weekNum")+"";
					map.put("yearundiscount", weekUndiscount);
				}
			}
		}
		return map;
	}
	
	/**
	 * @author GJJ
	 * @date 2016年1月8日
	 * @param type
	 * @param day
	 * @param response
	 * @throws IOException
	 * @return void
	 * @time 2016年1月8日
	 * @todo 查询用户是否有未读的消息
	 */
	@RequestMapping("/zhangbu/hasmessage/")
	@ResponseBody
	public Map<String, Object> hasMessage(String memberid){
		Map<String, Object> map = new HashMap<String, Object>();
		Integer sum = null;
		try {
			//根据用户memberid查询该用户是否有未读的消息
			sum = 1;
		} catch (Exception e) {
			map.put("message", "no");
			logger.info("获取用户未读消息数量失败---------", e);
		}
		if(sum > 0){
			map.put("message", "yes");
		}else{
			map.put("message", "no");
		}
		return map;
	}
	
	/**
	 * @author GJJ
	 * @date 2016年1月8日
	 * @param type
	 * @param day
	 * @param response
	 * @throws IOException
	 * @return void
	 * @time 2016年1月8日
	 * @todo 查询一天的贴现和未贴现数据
	 */
	@RequestMapping("/zhangbu/daily")
	@ResponseBody
	public List<Map<String, Object>> daily(Integer memberid,String day){
		List<Accountrecord> list = null;
		List<Map<String, Object>> lists = null;
		try {
			list = (List<Accountrecord>) accountrecordService.findDiscountAday(memberid, day);
			lists = showTiexianData(list);
		}catch (Exception e) {
			logger.error("查询数据出错:", e);
		}
		return lists;
			
	}
	
	/**
	 * 用来在页面展示贴现信息时，加上实时价格和订单状态
	 * @param list
	 * @return
	 * @date 2016年1月21日
	 * @author lvyanqin
	 */
	public List<Map<String, Object>> showTiexianData(List<Accountrecord> list){
		List<Map<String, Object>> lists = null;
		if(list != null && list.size() > 0){
			lists = new ArrayList<Map<String,Object>>();
			Map<String, Object> map = null;
			for (Accountrecord accountrecord : list) {
				map = BeanQuery.select("id,memberid,type1,allprice,yuelilv,nianlilv,tiexiandate,daoqidate,jixi,tiexianlixi,tiexianjine,accountdesc,price,publishtime,tiexianType,piaojushuxing,orderStatue,discountrecordId").executeFrom(accountrecord);
				
				map.put("istiexian",accountrecord.getIsTiexian());
				String txType = (String) map.get("tiexianType");
				if(StringUtils.isNotBlank(txType)){
					if("手动".equals(txType.trim())&&"0".equals(accountrecord.getIsTiexian())){
						Historyprice his = new Historyprice();
						his.setType1(accountrecord.getType1());
						if(accountrecord.getAllprice() == null){
							accountrecord.setAllprice(0f);
						}
						if(accountrecord.getAllprice() > 500){
							his.setType2(1);
						}else if(accountrecord.getAllprice() > 100){
							his.setType2(2);
						}else if(accountrecord.getAllprice() > 50){
							his.setType2(3);
						}else{
							his.setType2(4);
						}
						his.setType3(1);//买断
						his.setType4(1);//长三角
						his.setType5(2);
						List<Historyprice> cs = historypriceService.getList(his);
						if(cs != null && cs.size() > 0){
							Historyprice temp = cs.get(0);
							Integer type1=accountrecord.getType1();
							Float allmoney=accountrecord.getAllprice();
							String rate=temp.getPrice();
							String rate1=temp.getPrice1();
							if(1==type1){//纸票
								if(500<=allmoney){//大票
									Float r = 0F;
									if(StringUtils.isNotBlank(rate) && !"--".equals(rate.trim())){
										r = (allmoney*10000)*183*((Float.valueOf(rate)/30)/1000);
									}
									map.put("txlx", r);
								}else{//小票
									Float r = 0F;
									if(StringUtils.isNotBlank(rate) && !"--".equals(rate.trim())){
										r = (allmoney*10000)*183*((Float.valueOf(rate)/30)/1000);
										if(StringUtils.isNotBlank(rate1) && !"--".equals(rate1.trim())){
											r += (allmoney/10)*Float.valueOf(rate1);
										}
									}
									map.put("txlx", r);
								}
							}else{//电票
								Float r = 0F;
								if(StringUtils.isNotBlank(rate) && !"--".equals(rate.trim())){//年利率
									r = ((allmoney*10000)*183*(Float.valueOf(rate)/100))/360;
								}
								map.put("txlx", r);
							}
						}
						
						String tempFlag = (String) map.get("orderStatue");
						if(StringUtils.isBlank(tempFlag)){
							map.put("orderStatue","未过期");
						}else if("0".equals(tempFlag.trim())){
							map.put("orderStatue","已过期");
						}else{
							map.put("orderStatue","未过期");
						}
					}
				}
				lists.add(map);
			}
		}
		
		return lists;
	}
	
	
	/**
	 * @author GJJ
	 * @date 2016年1月8日
	 * @param type
	 * @param start
	 * @param end
	 * @param response
	 * @throws IOException
	 * @return void
	 * @time 2016年1月8日
	 * @todo 查询一周的贴现和未贴现数据
	 */
	@RequestMapping("/zhangbu/week")
	public void week(String type,String start,String end,HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		List<Accountrecord> list = (List<Accountrecord>) accountrecordService.findDiscountByWeek(type, start, end);
		out.print(JSONArray.fromObject(list));
	}
	
	/**
	 * 查询一周的贴现和未贴现数据
	 * @param memberId
	 * @param type,区分已贴现，未贴现
	 * @param beginDate
	 * @date 2016年1月19日
	 * @author lvyanqin
	 * @throws IOException 
	 */
	@RequestMapping("/zhangbu/week1")
	@ResponseBody
	public List<Map<String, Object>> getWeekData(Integer memberId,String type,String begin,HttpServletResponse response) throws IOException{
		Date beginDate = null;
		List<Map<String, Object>> lists = null;
		try {
			beginDate = DateUtil.parser(begin, "yyyy-MM-dd");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String end = DateUtil.formart(DateUtil.addDays(beginDate, 6), "yyyy-MM-dd");
		List<Accountrecord> list = accountrecordService.getDiscount(memberId,type,begin,end);
		lists = showTiexianData(list);
		return lists;
		//out.print(JSONArray.fromObject(acountrecords));
	}
	
	/**
	 * @author GJJ
	 * @date 2016年1月8日
	 * @param type
	 * @param day
	 * @param response
	 * @throws IOException
	 * @return void
	 * @time 2016年1月8日
	 * @todo 查询一月的贴现和未贴现数据
	 */
	@RequestMapping("/zhangbu/month")
	@ResponseBody
	public List<Map<String, Object>> month(Integer memberid,String month,String type){
		/*String start = CalendarUtil.getFirstByMonth(month);
		String end = CalendarUtil.getLastByMonth(month);*/
		List<Map<String, Object>> lists = null;
		month += "-01";
		Date beginDate = null;
		try {
			beginDate = DateUtil.parser(month, "yyyy-MM-dd");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String end = DateUtil.formart(DateUtil.addDays(beginDate, 30), "yyyy-MM-dd");
		List<Accountrecord> list = new ArrayList<Accountrecord>();
		try {
			list = (List<Accountrecord>) accountrecordService.getDiscount(memberid, type, month, end);
			lists = showTiexianData(list);
		} catch (Exception e) {
			logger.info("获取月数据失败:",e);
		}
		return lists;
	}
	
	/**
	 * @author GJJ
	 * @date 2016年1月8日
	 * @param type
	 * @param end
	 * @param start
	 * @throws IOException
	 * @return void
	 * @time 2016年1月8日
	 * @todo 自定义日期查询
	 */
	@RequestMapping("/zhangbu/customizedate")
	@ResponseBody
	public List<Map<String, Object>> customizeDate(Integer memberId, String type,String end,String start) throws IOException{
		List<Map<String, Object>> lists = null;
		List<Accountrecord> list = (List<Accountrecord>) accountrecordService.getDiscount(memberId, type, start, end);
		lists = showTiexianData(list);
		return lists;
	}
	
	/**
	 * @author GJJ
	 * @date 2016年1月8日
	 * @param accountrecord
	 * @param response
	 * @param limit 票据期限
	 * @throws IOException
	 * @return void
	 * @throws ParseException 
	 * @time 2016年1月8日
	 * @todo 票据信息录入
	 */
	@RequestMapping("/zhangbu/addpiaoju/")
	@ResponseBody
	public Map<String,Object> savePiaoju(Accountrecord accountrecord,String begin,String end,String remindtime,Integer limit) throws ParseException {
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			accountrecord.setTiexiandate(DateUtil.parser(begin, DateUtil.FORMART3));
			accountrecord.setDaoqidate(DateUtil.parser(end, DateUtil.FORMART3));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		Integer status = null;
		try {
			//APP2.2 票据期限
			if("电票".equals(accountrecord.getPiaojushuxing())){//电票
				accountrecord.setAcceptTime(limit);//电票期限
			}
			accountrecord.setCreateTime(new Date());
			status = accountrecordService.saveAccountrecord(accountrecord);
			//APP2.2添加票据提醒
			if(StringUtils.isNotBlank(remindtime)){
				Noticerecord noticerecord = new Noticerecord();
				Integer s = (int) (accountrecord.getAllprice()/1);
				noticerecord.setAllprice(s);
				noticerecord.setMemberid(accountrecord.getMemberid());
				noticerecord.setExpiredate(accountrecord.getDaoqidate());
				noticerecord.setNoticetime(DateUtil.parser(remindtime,DateUtil.FORMART3));
				noticerecord.setNoticeaddtime(DateUtil.dataTurntoInt(remindtime,DateUtil.FORMART3));
				noticerecord.setFkId(accountrecord.getId());
				noticerecordService.addNoticerecord(noticerecord);
			}
		} catch (Exception e) {
			map.put("status", "fail");
			logger.info("插入数据出错:",e);
		}
		if(status!=null){
			map.put("status", "ok");
		}
		return map;
	}
	
	/**
	 * 查询从贴现页面进入编辑票据页面，进行初始化
	 * @param id
	 * @return
	 * @date 2016年1月20日
	 * @author lvyanqin
	 */
	@RequestMapping("/zhangbu/init/")
	@ResponseBody
	public Accountrecord showInitPJ(Integer id){
		Accountrecord list = accountrecordService.getAccountrecord(id);
		String json = JacksonUtil.obj2Str(list, DateUtil.FORMART3);
		list = JsonUtil.json2model(json, list);
		return list;
	}
	
	/**
	 * 删除票据,修改状态
	 * @param id
	 * @return
	 * @date 2016年1月22日
	 * @author lvyanqin
	 */
	@RequestMapping("/zhangbu/shanchu/")
	@ResponseBody
	public Map<String,Object> updatePiaojuStatue(Integer id){
		Map<String,Object> map = new HashMap<String, Object>();
		Accountrecord accountrecord =  new Accountrecord();
		accountrecord = accountrecordService.getAccountrecord(id);
		accountrecord.setStatue("0");//修改状态
		try {
			accountrecordService.updateAccountrecord(accountrecord);
			map.put("status", "ok");
		} catch (Exception e) {
			map.put("status", "fail");
			logger.info("更新数据出错:",e);
		}
		return map;
	}
	
	/**
	 * @author GJJ
	 * @date 2016年1月8日
	 * @param accountrecord
	 * @param response
	 * @throws IOException
	 * @return void
	 * @time 2016年1月8日
	 * @todo 编辑票据信息
	 */
	@RequestMapping("/zhangbu/editpiaoju/")
	@ResponseBody
	public Map<String,Object> editPiaoju(Accountrecord accountrecord,String begin,String end,String flag,String remindtime){
 		Map<String,Object> map = new HashMap<String, Object>();
		Accountrecord acc = new Accountrecord();
		acc = accountrecordService.getAccountrecord(accountrecord.getId());
		try {
			acc.setTiexiandate(DateUtil.parser(begin, DateUtil.FORMART3));
			acc.setDaoqidate(DateUtil.parser(end, DateUtil.FORMART3));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		try {
			if("1".equals(flag)){//带有月和周利率
				acc.setYuelilv(accountrecord.getYuelilv());
				acc.setNianlilv(accountrecord.getNianlilv());
			}
			acc.setAllprice(accountrecord.getAllprice());
			acc.setType1(accountrecord.getType1());
			acc.setTiexianlixi(accountrecord.getTiexianlixi());
			acc.setTiexianjine(accountrecord.getTiexianjine());
			acc.setAccountdesc(accountrecord.getAccountdesc());
			accountrecordService.updateAccountrecord(acc);
			if(StringUtils.isNotBlank(remindtime)){
				Noticerecord n = noticerecordService.getByFkId(acc.getId());
				if(n == null){
					Noticerecord noticerecord = new Noticerecord();
					Integer s = (int) (acc.getAllprice()/1);
					noticerecord.setAllprice(s);
					noticerecord.setMemberid(acc.getMemberid());
					noticerecord.setExpiredate(acc.getDaoqidate());
					noticerecord.setNoticetime(DateUtil.parser(remindtime,DateUtil.FORMART3));
					noticerecord.setNoticeaddtime(DateUtil.dataTurntoInt(remindtime,DateUtil.FORMART3));
					noticerecord.setFkId(acc.getId());
					noticerecordService.addNoticerecord(noticerecord);
				}else{
					n.setNoticetime(DateUtil.parser(remindtime,DateUtil.FORMART3));
					n.setNoticeaddtime(DateUtil.dataTurntoInt(remindtime,DateUtil.FORMART3));
					noticerecordService.updateNoticerecord(n);
				}
			}
			map.put("status", "ok");
		} catch (Exception e) {
			map.put("status", "fail");
			logger.info("更新数据出错:",e);
		}
		return map;
	}
	
	
	
	
	/**
	 * @author GJJ
	 * @date 2016年1月12日
	 * @param date
	 * @param type
	 * @return List<Map<String,Object>>
	 * @time 2016年1月12日
	 * @todo 根据票据类型和时间间隔查询,前台根据后台数据绘制图表进行展示
	 */
	@RequestMapping("/zhangbu/counttype/")
	public String countPiaojuType(String date,String type,String begin,String end,Integer memberid,Model model){
		List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();
		if(date.equals("1")){
			end = DateUtil.formart(new Date(), DateUtil.FORMART3);
			begin = DateUtil.formart(DateUtil.addDays(-30),DateUtil.FORMART3);
		}else if(date.equals("2")){
			end = DateUtil.formart(new Date(), DateUtil.FORMART3);
			begin = DateUtil.formart(DateUtil.addDays(-90),DateUtil.FORMART3);
		}else if(date.equals("3")){
			end = DateUtil.formart(new Date(), DateUtil.FORMART3);
			begin = DateUtil.formart(DateUtil.addDays(-180),DateUtil.FORMART3);
		}
		boolean flag = false;
		if(type.equals("总览")){
			type = null;
		}else if(type.equals("已贴现")){
			type = "1";
			flag = true;
		}else if(type.equals("未贴现")){
			type = "0";
		}
		try {
			if(flag){
				listMap = accountrecordService.sumLixi(begin,end,type,memberid);
			}else{
				listMap = accountrecordService.countPiaoju(begin,end,type,memberid);
			}
		} catch (Exception e) {
			logger.info("统计票据类型出错:",e);
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(listMap));
		return "ajax";
	}
	
	/**
	 * @author RY
	 * @date 2016年1月12日
	 * @param type
	 * @param date
	 * @param email
	 * @return void
	 * @throws Exception 
	 * @time 2016年1月12日
	 * @todo 导出excel文件并发送邮件
	 */
	@RequestMapping("/zhangbu/sendmail/")
	public String sendMail(String type,String start,String end,String email,Integer memberid,Integer belong,Model model){
		if(belong==null)belong = 0;
		Map<String, Object> call = new HashMap<String, Object>();
		try {
			AccountrecordForm form = new AccountrecordForm();
			
			form.setStart(start);
			form.setEnd(end);
			form.setEmail(email);
			form.setType(type);
			form.setBelong(belong);
			List<Accountrecord> list = accountrecordService.getByForm(form);
			
			Float all_1 = 0F;
			Float all_0 = 0F;
			List<Map<String,Object>>map_ = new ArrayList<Map<String,Object>>();
			Map<String,Object> m = null;
			String[] keys = new String[]{"type1","allprice","tiexianjine","yuelilv","nianlilv","tiexiandate","daoqidate","istiexian"};
			String[] title = new String[]{"#","承兑行","总金额","贴现金额","月利率","年利率","贴现日期","到期日期","类型"};
			if(list!=null && list.size()>0){
				for(Accountrecord a:list){
					m = new HashMap<String,Object>();
					m.put("type1",DataMatchUtil.getCDHByTypeFromNew(a.getType1()));
					m.put("allprice",a.getAllprice());
					m.put("tiexianjine",a.getTiexianjine());
					m.put("yuelilv",a.getYuelilv());
					m.put("nianlilv",a.getNianlilv());
					m.put("tiexiandate",DateUtil.formart(a.getTiexiandate(),"yyyy-MM-dd"));
					m.put("daoqidate",DateUtil.formart(a.getDaoqidate(),"yyyy-MM-dd"));
					m.put("istiexian","1".equals(a.getIsTiexian())?"已贴现":"未贴现");
					if("1".equals(a.getIsTiexian())){//已贴现
						Float temp = a.getAllprice();
						if(temp!=null)all_1 += temp;
					}else if("0".equals(a.getIsTiexian())){//未贴现
						Float temp = a.getAllprice();
						if(temp!=null)all_0 += temp;
					}
					map_.add(m);
				}
			}
			
			String path = Constant.properties.getProperty("uploadpath") + Constant.EXCEL_PATH;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String ymd = sdf.format(new Date());
			path += ymd + File.separator;
			File dirFile = new File(path);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			path += UUID.randomUUID()+".xls";
			
			jxlUtil.writeExcel(keys,title,map_, path, "票据数据导出");
			List<String> targetPerson = new ArrayList<String>();
			targetPerson.add(email);
			//获取freemarker模板
			Member member = memberService.getById(memberid);
			String userName = "";	
			if(member!=null){
				userName = member.getUsername()!=null?member.getUsername():member.getMobile();
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userName",userName);
			map.put("time",start+"~"+end);
			map.put("YI", all_1+"万");
			map.put("WEI", all_0+"万");
			String template = "/emailtemplate/email.ftl";
			String html = EmailUtil.getHtml(configuration,template,map);
			EmailUtil.sendEmail(targetPerson,"数据导出邮件", html,path);
			
			call.put("state", "success");
			call.put("msg", "发送成功，请注意查收");
		} catch (Exception e) {
			e.printStackTrace();
			call.put("state", "failed");
			call.put("msg", "发送失败");
		}
		model.addAttribute("retValue",JSONArray.fromObject(call));
		return "ajax";
	}
	
	/**
	 * 根据主键获取账簿
	 * @param id
	 * @date 2016年1月21日
	 * @author WKX
	 */
	@RequestMapping("/zhangbu/get")
	public @ResponseBody Map<String,Object> getById(Integer id){
		Map<String,Object> result = new HashMap<String, Object>();
		Accountrecord list = accountrecordService.getAccountrecord(id);
		String json = JacksonUtil.obj2Str(list, DateUtil.FORMART3);
		result = JsonUtil.json2model(json, result);
		return result;
	}
	
	/**
	 * 票据管理统计
	 * @param type 类型（1一个月、2三个月、3六个月、4自定义）
	 * @param start
	 * @param end
	 * @param memberId
	 * @param model
	 * @author WKX
	 */
	@RequestMapping("/app/zhangbu/tongji")
	public String tongji(String type,String start,String end,Integer memberId,Integer belong,Model model){
		if(belong==null)belong = 0;
		Map<String,Object> result = new HashMap<String,Object>();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		
		if("1".equals(type)){
			c.add(Calendar.MONTH, -1);
			start = format.format(c.getTime());
			end = format.format(new Date());
		}else if("2".equals(type)){
			c.add(Calendar.MONTH, -3);
			start = format.format(c.getTime());
			end = format.format(new Date());
		}else if("3".equals(type)){
			c.add(Calendar.MONTH, -6);
			start = format.format(c.getTime());
			end = format.format(new Date());
		}
		try {
			SimpleDateFormat fm = new SimpleDateFormat("yyyy年MM月dd日");
			result.put("start", fm.format(DateUtil.parser(start, DateUtil.FORMART3)));
			result.put("end", fm.format(DateUtil.parser(end, DateUtil.FORMART3)));
			
			List<Map<String,Object>> list = accountrecordService.getSumByMemberId(memberId,belong, start, end);
			if(list!=null && list.size()>0){
				Double tiexianlixi = 0D;
				Double allprice = 0D;
				for(Map<String,Object> map:list){
					if(map!=null && map.get("tiexianlixi")!=null){
						tiexianlixi += Double.valueOf(map.get("tiexianlixi").toString());
					}
					if(map!=null && map.get("allprice")!=null){
						allprice += Double.valueOf(map.get("allprice").toString());
					}
				}
				result.put("tiexianlixi", tiexianlixi);
				result.put("allprice", allprice);
			}
			result.put("response", "success");
			result.put("data", list);
			result.put("msg", "操作成功");
		} catch (Exception ex) {
			result.put("response", "failed");
			result.put("msg", "获取失败");
			logger.info("统计票据类型出错:",ex);
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * @author xiaoc
	 * @date 2016年3月29日
	 * @todo 查询一年的贴现和未贴现数据
	 */
	@RequestMapping("/zhangbu/year")
	@ResponseBody
	public List<Map<String, Object>> year(Integer memberid,String year,String type){
		List<Map<String, Object>> lists = null;
		year += "-01-01";
		Date beginDate = null;
		try {
			beginDate = DateUtil.parser(year, "yyyy-MM-dd");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String end = DateUtil.formart(DateUtil.addMonth(beginDate, 12), "yyyy-MM-dd");
		List<Accountrecord> list = new ArrayList<Accountrecord>();
		try {
			list = (List<Accountrecord>) accountrecordService.getDiscount(memberid, type, year, end);
			lists = showTiexianData(list);
		} catch (Exception e) {
			logger.info("获取月数据失败:",e);
		}
		return lists;
	}
	
	/**
	 * 根据istiexian查询所有
	 * @param memberid
	 * @param istiexian
	 * @date 2016年3月29日
	 * @author cx
	 */
	@RequestMapping("/zhangbu/alltiexian")
	@ResponseBody
	public List<Map<String, Object>> alltiexian(Integer memberid,String istiexian){
		List<Accountrecord> list = new ArrayList<Accountrecord>();
		list=(List<Accountrecord>) accountrecordService.alltiexian(memberid, istiexian);
		List<Map<String, Object>> lists = null;
		lists = showTiexianData(list);
		return lists;
	}
	
	/**
	 * 自定义日期查询2.1
	 * @author cx
	 * @date 2016年3月31日
	 * @param type
	 * @param end
	 * @param start
	 * @param chengduitype
	 * @param allprice
	 * @throws IOException
	 * @return void
	 */
	@RequestMapping("/zhangbu/customcount")
	@ResponseBody
	public List<Map<String, Object>> customcount(Integer memberId, String type,String end,String start,Float allprice,Integer type1) throws IOException{
		List<Map<String, Object>> lists = null;
		List<Accountrecord> list = (List<Accountrecord>) accountrecordService.customcount(memberId, type, end, start, allprice, type1);
		lists = showTiexianData(list);
		return lists;
	}
	
	/**
	 * 票据查询
	 * @author cx
	 * @param memberId  
	 * @param istiexian  是否已贴现 1：已贴现  0：未贴现
	 * @param type1  承兑行类型   
	 * @param start 起始时间  
	 * @param end   结束时间
	 * @param allprice 贴现金额
	 * @param size  查询原则  1：天  2：周  3：月   4：年  
	 * @param type  纸票 / 电票  （汉字）
	 * @param limit  电票期限 0半年 1一年  
	 * @throws Exception 
	 * @date 2016年3月31日
	 */
	@RequestMapping("/zhangbu/piaojulist")
	@ResponseBody
	public List<Map<String, Object>> piaojuGetList(Integer memberId, String istiexian,String day,String start,String end,Float allprice,Integer type1,String size,Integer belong,String type,Integer limit) throws Exception{
		if(belong==null)belong = 0;
		List<Map<String, Object>> lists = null;
		Date startdate = null;
		Date enddate = null;
		if("1".equals(size)){
			try {
				startdate = DateUtil.parser(day+" 00:00:00", DateUtil.FORMART);
				enddate = DateUtil.parser(day+" 23:59:59", DateUtil.FORMART);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else if("2".equals(size)){
			try {
				startdate = DateUtil.parser(day+" 00:00:00", DateUtil.FORMART);
				String endDate= DateUtil.formart(DateUtil.addDays(startdate, 6), DateUtil.FORMART);
				enddate = DateUtil.parser(endDate+" 23:59:59", DateUtil.FORMART);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}else if("3".equals(size)){
			try {
				day +="-01";
				startdate = DateUtil.parser(day+" 00:00:00", DateUtil.FORMART);
				String endDate= DateUtil.formart(DateUtil.addDays(startdate, 30), DateUtil.FORMART);
				enddate = DateUtil.parser(endDate+" 23:59:59", DateUtil.FORMART);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else if("4".equals(size)){
			try {
				day +="-01-01";
				startdate = DateUtil.parser(day+" 00:00:00", DateUtil.FORMART);
				String endDate = DateUtil.formart(DateUtil.addMonth(startdate, 12), DateUtil.FORMART);
				enddate = DateUtil.parser(endDate+" 23:59:59", DateUtil.FORMART);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else{
			if (start != null && end != null) {
				try {
					startdate = DateUtil.parser(start, "yyyy-MM-dd");
					enddate = DateUtil.parser(end, "yyyy-MM-dd");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		//APP2.2电票期限  老版本没给出期限 默认一年
		if("电票".equals(type)){
			if(limit==null){//未指定期限
				limit=1;//默认一年
			}
		}else{//纸票 期限置空
			limit=null;
		}
		
		lists=accountrecordService.noticeConnect(memberId, istiexian, enddate, startdate, allprice, type1,belong,type,limit);
		for(Map<String, Object> m:lists){
			//贴现金额       小数点后两位
			if(m.get("tiexianjine") != null){
				String tiexianjine = m.get("tiexianjine").toString();
				tiexianjine +="00";
				if(tiexianjine.contains(".")){
					tiexianjine = tiexianjine.substring(0, tiexianjine.indexOf(".") + 1 + 2);
				}
				m.put("txje",tiexianjine);
			}
			if("0".equals(m.get("istiexian"))&&"手动".equals(m.get("tiexianType"))){
					Historyprice his = new Historyprice();
					his.setType1((Integer) m.get("type1"));
					if(m.get("allprice") == null){
						m.put("allprice", 0f);
					}
					if( (Float)m.get("allprice") > 500){
						his.setType2(1);
					}else if((Float)m.get("allprice") > 100){
						his.setType2(2);
					}else if((Float)m.get("allprice") > 50){
						his.setType2(3);
					}else{
						his.setType2(4);
					}
					his.setType3(1);//买断
					his.setType4(1);//长三角
					his.setType5(2);
					List<Historyprice> cs = historypriceService.getList(his);
					if(cs != null && cs.size() > 0){
						Historyprice temp = cs.get(0);
						Integer type2=(Integer) m.get("type1");
						Float allmoney=(Float)m.get("allprice");
						String rate=temp.getPrice();
						String rate1=temp.getPrice1();
						if(1==type2){//纸票
							if(500<=allmoney){//大票
								Float r = 0F;
								if(StringUtils.isNotBlank(rate) && !"--".equals(rate.trim())){
									r = (allmoney*10000)*183*((Float.valueOf(rate)/30)/1000);
								}
								//r=r/10000;//票据管理中 改为元做单位
								m.put("txlx", r);
							}else{//小票
								Float r = 0F;
								if(StringUtils.isNotBlank(rate) && !"--".equals(rate.trim())){
									r = (allmoney*10000)*183*((Float.valueOf(rate)/30)/1000);
									if(StringUtils.isNotBlank(rate1) && !"--".equals(rate1.trim())){
										r += (allmoney/10)*Float.valueOf(rate1);
									}
								}
								//r=r/10000;//票据管理中 改为元做单位
								m.put("txlx", r);
							}
						}else{//电票
							Float r = 0F;
							if(StringUtils.isNotBlank(rate) && !"--".equals(rate.trim())){//年利率
								r = ((allmoney*10000)*183*(Float.valueOf(rate)/100))/360;
							}
							//r=r/10000;//票据管理中 改为元做单位
							m.put("txlx", r);
						}
					}else{
						m.put("txlx","--");
					}
					String tempFlag = (String) m.get("orderStatue");
					if(StringUtils.isBlank(tempFlag)){
						m.put("orderStatue","未过期");
					}else if("0".equals(tempFlag.trim())){
						m.put("orderStatue","已过期");
					}else{
						m.put("orderStatue","未过期");
					}
			}
		}
	    return lists;
	}

	/**
	 * 票据提醒
	 * @author cx
	 * @date 2016年5月19日
	 */
	@RequestMapping("/zhangbu/piaojuremind")
	public String piaoJuRemind(Integer memberid,Integer belong,Model model){
		SimpleDateFormat sf = new SimpleDateFormat(DateUtil.FORMART3);
		String date1 = sf.format(new Date());
		Date datetime1 = null;
		try {
			datetime1 = sf.parse(date1);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		Date datetime2 = DateUtil.addDays(datetime1, 3);
		String date2 = sf.format(datetime2);
		List<Map<String,Object>> list = accountrecordService.ifExistTiXin(memberid, belong,date1,date2);
		if(list.size() > 0){
			model.addAttribute("retValue",1);
		}else{
			model.addAttribute("retValue",0);
		}
		return "ajax";
	}
	
	/**
	 * 票据提醒
	 * @author cx
	 * @date 2016年5月19日
	 */
	@RequestMapping("/zhangbu/piaojuremindlist")
	public String piaoJuRemindList(Integer memberid,Integer belong,Model model){
		SimpleDateFormat sf = new SimpleDateFormat(DateUtil.FORMART3);
		String date1 = sf.format(new Date());
		Date datetime1 = null;
		try {
			datetime1 = sf.parse(date1);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		Date datetime2 = DateUtil.addDays(datetime1, 3);
		String date2 = sf.format(datetime2);
		List<Map<String,Object>> list = accountrecordService.ifExistTiXin(memberid, belong,date1,date2);
		if(list.size() == 0){
			Map<String,Object> map =new HashMap<String, Object>();
			map.put("data", null);
			list.add(map);
		}else{
			for(Map<String, Object> m:list){
				if("0".equals(m.get("istiexian"))&&"手动".equals(m.get("tiexianType"))){
						Historyprice his = new Historyprice();
						his.setType1((Integer) m.get("type1"));
						if(m.get("allprice") == null){
							m.put("allprice", 0f);
						}
						if( (Float)m.get("allprice") > 500){
							his.setType2(1);
						}else if((Float)m.get("allprice") > 100){
							his.setType2(2);
						}else if((Float)m.get("allprice") > 50){
							his.setType2(3);
						}else{
							his.setType2(4);
						}
						his.setType3(1);//买断
						his.setType4(1);//长三角
						his.setType5(2);
						List<Historyprice> cs = historypriceService.getList(his);
						if(cs != null && cs.size() > 0){
							Historyprice temp = cs.get(0);
							Integer type2=(Integer) m.get("type1");
							Float allmoney=(Float)m.get("allprice");
							String rate=temp.getPrice();
							String rate1=temp.getPrice1();
							if(1==type2){//纸票
								if(500<=allmoney){//大票
									Float r = 0F;
									if(StringUtils.isNotBlank(rate) && !"--".equals(rate.trim())){
										r = (allmoney*10000)*183*((Float.valueOf(rate)/30)/1000);
									}
									//r=r/10000;//票据管理中 改为元做单位
									m.put("txlx", r);
								}else{//小票
									Float r = 0F;
									if(StringUtils.isNotBlank(rate) && !"--".equals(rate.trim())){
										r = (allmoney*10000)*183*((Float.valueOf(rate)/30)/1000);
										if(StringUtils.isNotBlank(rate1) && !"--".equals(rate1.trim())){
											r += (allmoney/10)*Float.valueOf(rate1);
										}
									}
									//r=r/10000;//票据管理中 改为元做单位
									m.put("txlx", r);
								}
							}else{//电票
								Float r = 0F;
								if(StringUtils.isNotBlank(rate) && !"--".equals(rate.trim())){//年利率
									r = ((allmoney*10000)*183*(Float.valueOf(rate)/100))/360;
								}
								//r=r/10000;//票据管理中 改为元做单位
								m.put("txlx", r);
							}
						}else{
							m.put("txlx","--");
						}
						String tempFlag = (String) m.get("orderStatue");
						if(StringUtils.isBlank(tempFlag)){
							m.put("orderStatue","未过期");
						}else if("0".equals(tempFlag.trim())){
							m.put("orderStatue","已过期");
						}else{
							m.put("orderStatue","未过期");
						}
				}
				
				//tiexiandate转换为毫秒start
				String tiexiandate = m.get("tiexiandate").toString();
				if(StringUtils.isNotBlank(tiexiandate)){
					try {
						Date date = DateUtil.parser(tiexiandate, DateUtil.FORMART);
						Long mSeconds = date.getTime();
						m.put("tiexiandate", mSeconds);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				//end
			}
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(list));
	    return "ajax";
	}
}