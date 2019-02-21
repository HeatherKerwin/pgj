package com.ry.ryapp.controller;

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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ry.core.Enum.OrderState;
import com.ry.core.entity.Accountrecord;
import com.ry.core.entity.Comments;
import com.ry.core.entity.Currentprice;
import com.ry.core.entity.Discountrecord;
import com.ry.core.entity.DiscountrecordTask;
import com.ry.core.entity.DiscountrecordTask.Operator;
import com.ry.core.entity.DistributeOrder;
import com.ry.core.entity.Historyprice;
import com.ry.core.entity.HongbaoDetail;
import com.ry.core.entity.IntegraltradingDetail;
import com.ry.core.entity.Member;
import com.ry.core.entity.Notice;
import com.ry.core.entity.NoticeAdd;
import com.ry.core.entity.Org;
import com.ry.core.entity.OrgInfo;
import com.ry.core.entity.RefundExamine;
import com.ry.core.entity.Region;
import com.ry.core.entity.Systeminfo;
import com.ry.core.entity.Systeminfo.Type;
import com.ry.core.form.DiscountrecordForm;
import com.ry.core.service.AccountrecordService;
import com.ry.core.service.ActivityService;
import com.ry.core.service.CommentsService;
import com.ry.core.service.DimensionService;
import com.ry.core.service.DiscountrecordPlService;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.DiscountrecordSpService;
import com.ry.core.service.DiscountrecordTaskService;
import com.ry.core.service.DistributeOrderService;
import com.ry.core.service.HistorypriceService;
import com.ry.core.service.HongbaoService;
import com.ry.core.service.MemberService;
import com.ry.core.service.NoticeAddService;
import com.ry.core.service.OrgCityService;
import com.ry.core.service.OrgInfoService;
import com.ry.core.service.OrgLimitService;
import com.ry.core.service.OrgService;
import com.ry.core.service.PaidanService;
import com.ry.core.service.PriceService;
import com.ry.core.service.RefundExamineService;
import com.ry.core.service.RefundRecordService;
import com.ry.core.service.RegionService;
import com.ry.core.service.SysteminfoService;
import com.ry.core.service.TiexianNoticeService;
import com.ry.core.util.Constant;
import com.ry.core.util.JPushUtil;
import com.ry.util.DataMatchUtil;
import com.ry.util.DateUtil;
import com.ry.util.DistanceUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.SendMessagesUtil;
import com.ry.util.page.PageResults;

import cn.jimmyshi.beanquery.BeanQuery;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import net.sf.json.JSONArray;

@Controller
public class DiscountrecordController {
	
	@Resource
	DiscountrecordService discountrecordService;
	
	@Resource
	DiscountrecordSpService discountrecordSpService;
	
	@Resource
	DiscountrecordPlService discountrecordPlService;
	
	@Resource
	HongbaoService hongbaoService;
	
	@Resource
	DiscountrecordTaskService discountrecordTaskService;
	
	@Resource
	PriceService priceService;
	
	@Resource
	OrgService orgService;
	
	@Resource
	MemberService memberService;
	
	@Resource
	DistributeOrderService distributeOrderService;

	@Resource
	HistorypriceService historypriceService;
	
	@Resource
	AccountrecordService accountrecordService;
	
	@Resource
	SysteminfoService systeminfoService;
	
	@Resource
	TiexianNoticeService tiexianNoticeService;
	
	@Resource
	OrgInfoService orgInfoService;
	
	@Resource
	OrgLimitService orgLimitService;
	
	@Resource
	CommentsService commentsService;
	
	@Resource
	OrgCityService orgCityService;
	
	@Resource
	DimensionService dimensionService;
	
	@Resource
	PaidanService paidanService;
	
	@Resource
	RegionService regionService;
	
	@Resource
	RefundExamineService refundExamineService;
	
	@Resource
	RefundRecordService refundRecordService;
	
	@Resource
	NoticeAddService noticeAddService;
	
	@Resource
	ActivityService activityService;
	
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
		public Type type;//消息类型
		public String des;//消息内容
		public PushJob(Integer memberId,Integer operator,Integer discountrecordId,Type type,String des) {
	        this.memberId = memberId;
	        this.operator = operator;
	        this.discountrecordId = discountrecordId;
	        this.type = type;
	        this.des = des;
	    }
		public void run() {
			try {
				Operator o = Operator.valueOf(operator);
				if(org.apache.commons.lang.StringUtils.isBlank(des))des = o.getName();
				
				Systeminfo systeminfo = new Systeminfo();
				systeminfo.setMemberId(memberId);
				systeminfo.setType(type);
				systeminfo.setAlert(o.getName());
				systeminfo.setContent(des);
				systeminfo.setDiscountrecordId(discountrecordId);
				systeminfo.setCreateTime(new Date());
				systeminfoService.addSysteminfo(systeminfo);
				
				Member member = memberService.getById(memberId);
				if(member!=null && StringUtils.isNotBlank(member.getMobile())){
					JPushUtil.pushToAlias(member.getMobile(), "【订单消息】"+o.getName(),type);
				}
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
	 * @param type 消息类型
	 * @param des 消息内容
	 * @since 2016年4月6日 下午2:11:07
	 */
	private void doPushJob(Integer memberId,Integer operator,Integer discountrecordId,Type type,String des){
		PushJob job = new PushJob(memberId, operator, discountrecordId,type,des);
		initPool().execute(job);
	}
	
	@RequestMapping("/app/discountrecord/")
	public void discountrecord(Integer orderflag, Integer orderflag2, Integer memberid, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			DiscountrecordForm df = new DiscountrecordForm();
			df.setMemberid(memberid);
			
			if (orderflag == 1) {
				Integer[] state = {1,2,4};//老板APP订单显示进行中（暂时先查1待确认、2待验票、4待收款）
				df.setState(state);
			} else if (orderflag == 2) {
				Integer[] state = {3};//已完成
				df.setState(state);
			} else if (orderflag == 3) {
				Integer[] state = {0};//无效
				df.setState(state);
			}
			List<Discountrecord> baseEntityList = discountrecordService.getList(df);
			
			List<Discountrecord> discountrecordList = new ArrayList<Discountrecord>();
			if(baseEntityList!=null&&baseEntityList.size()!=0){
				for(Discountrecord baseEntity : baseEntityList){
					Discountrecord discountrecord = baseEntity;
					String type1show = "";
					String type2show = "";
					String orderflagshow = "";
					
					//@WKX 2-16-1-22 承兑行转换中文[替换掉的老代码见上次提交记录]
					type2show = DataMatchUtil.getCDHByTypeFromOld(discountrecord.getType2());
					
					if(discountrecord.getType1()==1){
						type1show = "纸票";
					}
					if(discountrecord.getType1()==2){
						type1show = "电票";				
					}
					
					//@WKX 订单状态转中文显示(新老接口差异不大-可用)
					orderflagshow = DataMatchUtil.getValueByOrderFlag(discountrecord.getOrderflag());
					
					discountrecord.setType1show(type1show);
					discountrecord.setType2show(type2show);
					discountrecord.setOrdertimeshow(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(discountrecord.getOrdertime()));
					discountrecord.setOrderflagshow(orderflagshow);
					HongbaoDetail hongbaoDetail = hongbaoService.getHongbaoDetail(discountrecord.getOrdernumber());
					if (hongbaoDetail == null) {
						discountrecord.setHongbaoprice(0);
					} else if ( hongbaoDetail.getPrice() == null && hongbaoDetail.getPrice() <=0) {
						discountrecord.setHongbaoprice(0);
					} else {
						discountrecord.setHongbaoprice(hongbaoDetail.getPrice());
					}
					
					discountrecordList.add(discountrecord);
				}
			}
			map.put("response", "success");
			map.put("msg", discountrecordList);
			out.print(JSONArray.fromObject(map));
		}catch(Exception e){
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "查询失败");
			out.print(JSONArray.fromObject(map));
		}
	}
	
	@RequestMapping("/app/discountrecordupdate/")
	public void discountrecordupdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			String memberid = request.getParameter("memberid");
			String discountrecordid = request.getParameter("discountrecordid");
			List<Discountrecord> baseEntityList = discountrecordService.getListbyMemberid(discountrecordid, memberid);
			if(baseEntityList!=null&&baseEntityList.size()!=0){
				Discountrecord discountrecord = (Discountrecord)baseEntityList.get(0);
				String picpath = request.getParameter("picpath");				
				discountrecord.setPicpath(discountrecord.getPicpath() + picpath);
				discountrecordService.updateDis(discountrecord);
				map.put("response", "success");
				map.put("msg", "上传成功");
				out.print(JSONArray.fromObject(map));
			}else{
				map.put("response", "failed");
				map.put("msg", "无效订单");
				out.print(JSONArray.fromObject(map));
			}
		}catch(Exception e){
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "上传失败");
			out.print(JSONArray.fromObject(map));
		}
	}
	
	@RequestMapping("/app/discountrecorddetail/")
	public void discountrecorddetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		
		try{
			String memberid = request.getParameter("memberid");
			String discountrecordid = request.getParameter("discountrecordid");
			List<Discountrecord> baseEntityList = discountrecordService.getListbyMemberid(discountrecordid, memberid);
			if(baseEntityList!=null&&baseEntityList.size()!=0){
				Discountrecord discountrecord = (Discountrecord)baseEntityList.get(0);
				String type1show = "";
				String type2show = "";
				String membersexshow = "";
				String orderflagshow = "";
				
				//@WKX 2-16-1-22 承兑行转换中文[替换掉的老代码见上次提交记录]
				type2show = DataMatchUtil.getCDHByTypeFromOld(discountrecord.getType2());
				
				if(discountrecord.getType1()==1){
					type1show = "纸票";
				}
				if(discountrecord.getType1()==2){
					type1show = "电票";				
				}
				if(discountrecord.getMembersex()==1){
					membersexshow = "男士";				
				}
				if(discountrecord.getMembersex()==2){
					membersexshow = "女士";
				}
				
				//@WKX 订单状态转中文显示(新老接口差异不大-可用)
				orderflagshow = DataMatchUtil.getValueByOrderFlag(discountrecord.getOrderflag());
				
				discountrecord.setType1show(type1show);
				discountrecord.setType2show(type2show);
				discountrecord.setOrdertimeshow(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(discountrecord.getOrdertime()));
				discountrecord.setOrderflagshow(orderflagshow);
				discountrecord.setMembersexshow(membersexshow);
				discountrecord.setBegintimeshow(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(discountrecord.getBegintime()));
				discountrecord.setEndtimeshow(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(discountrecord.getEndtime()));
				map.put("response", "success");
				map.put("msg", discountrecord);
				out.print(JSONArray.fromObject(map));
			}else{
				map.put("response", "failed");
				map.put("msg", "查询失败");
				out.print(JSONArray.fromObject(map));
			}
		}catch(Exception e){
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "查询失败");
			out.print(JSONArray.fromObject(map));
		}
	}
	
	/**
	 * 根据用户主键获取用户贴现订单[含查询]
	 * @author MH
	 * @param Id 订单主键
	 * @param model
	 * @since 2016年1月11日 下午1:33:03
	 */
	@RequestMapping("/app/discountrecord2/updatediscount")
	public String updatediscount(Integer id,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		String msg = "操作失败";
		String alert = "操作成功";
		String ok = "确定";
		try {
			Discountrecord dis = discountrecordService.getById(id);
			dis.setDepositState(1);
			float a = 0;
			dis.setDeposit(a);
			discountrecordService.updateModel(dis);
			result.put("response", "success");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", msg);
			e.printStackTrace();
		}
		result.put("alert", alert);
		result.put("ok", ok);
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	@RequestMapping("/app/cancelrecorddetail/")
	public void cancelrecorddetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		
		try{
			String memberid = request.getParameter("memberid");
			String discountrecordid = request.getParameter("discountrecordid");
			List<Discountrecord> baseEntityList = discountrecordService.getListbyMemberid(discountrecordid, memberid);
			if(baseEntityList!=null&&baseEntityList.size()!=0){
				Discountrecord discountrecord = (Discountrecord)baseEntityList.get(0);
				discountrecord.setOrderflag(Constant.ORDER_WUXIAO);
				discountrecordService.updateDis(discountrecord);
				map.put("response", "success");
				map.put("msg", "取消成功，请返回");
				out.print(JSONArray.fromObject(map));
			}else{
				map.put("response", "failed");
				map.put("msg", "查询失败");
				out.print(JSONArray.fromObject(map));
			}
		}catch(Exception e){
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "取消订单失败");
			out.print(JSONArray.fromObject(map));
		}
	}
	
	/**
	 * 根据用户主键获取用户贴现订单[含查询]
	 * @author WKX
	 * @param memberId 用户主键
	 * @param type 类型[1国股、2城商、3外资、4农商、5农合、6农信、7村镇]
	 * @param state 订单状态
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param pageIndex 第几页
	 * @param model
	 * @since 2016年1月11日 下午1:33:03
	 */
	@RequestMapping("/app/discountrecord/list")
	public String listDiscountrecordByMemberId(Integer memberId,Integer type,Integer[] state,String start,String end,Integer pageIndex,Integer pageSize,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			if(pageIndex==null)pageIndex = 1;
			if(pageSize==null)pageSize = 10;
			
			DiscountrecordForm df = new DiscountrecordForm();
			df.setMemberid(memberId);
			if(start!=null)df.setStart(DateUtil.parser(start, "yyyy-MM-dd"));
			if(end!=null)df.setEnd(DateUtil.parser(end, "yyyy-MM-dd"));
			if(type!=null && type>=0)df.setType(type);
			if(state!=null && state.length>0)df.setState(state);
			
			PageResults<Discountrecord> page = discountrecordService.getPageList(df, pageIndex, pageSize);
			
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			if(page!=null && page.getResults()!=null){
				Map<String,Object> temp = null;
				for(Discountrecord dis:page.getResults()){
					temp = new HashMap<String, Object>();
					temp.put("id",dis.getId());
					if(dis.getOrdertime()!=null)temp.put("createTime",com.ry.ryapp.util.DateUtil.formart(dis.getOrdertime(), DateUtil.FORMART3));
					temp.put("bank",DataMatchUtil.getCDHByTypeFromNew(dis.getType2()));
					temp.put("state",DataMatchUtil.getValueByOrderFlag(dis.getOrderflag()));
					temp.put("money",dis.getAllmoney());
					list.add(temp);
				}
			}
			result.put("state", "success");
			result.put("msg", "查询成功");
			result.put("data", list);
		} catch (Exception e) {
			result.put("state", "failed");
			result.put("msg", "查询失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 根据主键获取订单详情[含订单流程明细]
	 * @param discountrecordId
	 * @param model
	 * @throws IOException
	 * @author WKX
	 */
	@RequestMapping("/app/discountrecord/get")
	public String get(Integer discountrecordId,Model model) throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			Discountrecord dis = discountrecordService.getById(discountrecordId);
			if(dis==null)throw new Exception("订单获取异常");
				
			//查询订单详情
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("id",dis.getId());
			map.put("type1",dis.getType1()==1?"纸票":"电票");
			map.put("type2",DataMatchUtil.getCDHByTypeFromNew(dis.getType2()));
			if(dis.getBegintime()!=null)map.put("begintime",DateUtil.formart(dis.getBegintime(), "yyyy-MM-dd"));
			if(dis.getEndtime()!=null)map.put("endtime",DateUtil.formart(dis.getEndtime(), "yyyy-MM-dd"));
			map.put("place",dis.getPlace());
			map.put("allmoney",dis.getAllmoney());
			map.put("membername",StringUtils.isNotBlank(dis.getMembername())?dis.getMembername():dis.getMembermobile());
			if(dis.getOrdertime()!=null)map.put("ordertime",com.ry.ryapp.util.DateUtil.formart(dis.getOrdertime(), DateUtil.FORMART3));
			map.put("ordernumber",dis.getOrdernumber());
			map.put("orderflag",DataMatchUtil.getValueByOrderFlag(dis.getOrderflag()));
			
			//查询用过的红包
			HongbaoDetail hongbao = hongbaoService.getByOrdernumber(dis.getOrdernumber());
			if(hongbao!=null)map.put("hongbao", hongbao.getPrice());
			
			//查询订单流程记录
			List<DiscountrecordTask> tasks = discountrecordTaskService.getByDiscountrecordId(discountrecordId);
			List<Map<String,Object>> t_m = BeanQuery.select("id,operatorId,createTime,operator.value as oValue,operator.name as oName,operatorDesc").from(tasks).execute();
			for(Map<String,Object> t:t_m){
				if(t!=null && t.get("createTime")!=null)t.put("createTime", DateUtil.formart((Date)t.get("createTime"), "yyyy-MM-dd HH:mm"));
			}
			result.put("state", "success");
			result.put("msg", "查询成功");
			result.put("data", map);
			result.put("tasks", t_m);
		}catch(Exception e){
			e.printStackTrace();
			result.put("state", "failed");
			result.put("msg", "查询失败");
		}
		model.addAttribute("retValue", JSONArray.fromObject(result));
		return "ajax";
	}
	
	/* -------------------------------------新接口-新版APP2.1--------------------------------------- */
	
	/**
	 * 显示企业当前未处理的订单（我的订单）
	 * @author WKX
	 * @param memberId 用户主键
	 * @param model
	 * @since 2016年3月3日 下午7:42:38
	 */
	@RequestMapping("/app/discountrecord2/inner")
	public String inner(Integer memberId,Float version,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		List<Map<String,Object>> list_ = discountrecordService.getUnReadByMemberId(memberId, 1);//待确认
		List<Map<String,Object>> list_0 = discountrecordService.getUnReadByMemberId(memberId, 2);//验票中
		List<Map<String,Object>> list_1 = discountrecordService.getUnReadByMemberId(memberId, 4);//待收款
		List<Map<String,Object>> list_2 = discountrecordService.getUnReadByMemberId(memberId, 3);//已完成
		List<Map<String,Object>> list_3 = discountrecordService.getUnReadByMemberId(memberId, 0);//无效订单
		
		if(version!=null && version>=2.2F){//待付款（APP2.2）
			List<Map<String,Object>> _0 = new ArrayList<Map<String,Object>>();//待确认
			List<Map<String,Object>> _4 = new ArrayList<Map<String,Object>>();//待付款
			if(list_!=null && list_.size()>0){
				for(Map<String,Object> m:list_){
					if(m!=null && (m.get("deposit_state")==null||"1".equals(m.get("deposit_state").toString()))){
						_0.add(m);
					}else{
						_4.add(m);
					}
				}
			}
			result.put("list_", _0==null?0:_0.size());
			result.put("list_4", _4==null?0:_4.size());
		}else{
			result.put("list_", list_==null?0:list_.size());
		}
		
		if(version!=null && version>=2.2F){//待评价（新的APP则显示真实的待评价个数）
			list_2 = discountrecordService.getUnCommentsByMemberId(memberId);
		}
		
		result.put("list_0", list_0==null?0:list_0.size());
		result.put("list_1", list_1==null?0:list_1.size());
		result.put("list_2", list_2==null?0:list_2.size());
		result.put("list_3", list_3==null?0:list_3.size());
		
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 企业订单列表
	 * @param form
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @author WKX
	 */
	@RequestMapping("/app/discountrecord2/list")
	public String list(DiscountrecordForm form,Integer pageIndex,Integer pageSize,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		if(pageIndex==null)pageIndex = 0;
		if(pageSize==null)pageSize = 10;
		if(form!=null && form.getMemberid()!=null){
			PageResults<Map<String,Object>> page = discountrecordService.getPageList(pageIndex, pageSize, form);
			result.put("data",page.getResults());
			result.put("response", "success");
			result.put("msg", "获取成功");
		}else{
			result.put("response", "failed");
			result.put("msg", "获取失败");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 根据企业主键获取详情（含机构报价）
	 * @param id
	 * @param model
	 * @author WKX
	 */
	@RequestMapping("/app/discountrecord2/get")
	public String getInfo(Integer id,Float version , Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			if(id==null)throw new Exception("数据异常");
			Map<String,Object> info = discountrecordService.updateReadTaskAndGetInfoAndOrderById(id);
			if(info!=null){
				Object ordernumber = info.get("ordernumber");
				if(ordernumber!=null){//企业订单编号（查询使用的红包）
					HongbaoDetail hb = hongbaoService.getByOrdernumber(ordernumber.toString());
					if(hb!=null)info.put("hb",hb.getPrice());
				}
				Object orgId = info.get("org_id");
				if(orgId!=null){//获取机构相关信息
					Map<String,Object> org = orgService.getInfoById(Integer.valueOf(orgId.toString()));
					if(org!=null){
						info.put("company", org.get("company"));
						Object memberId = org.get("member_id");
						if(memberId!=null){
							Member member = memberService.getById(Integer.valueOf(memberId.toString()));
							info.put("mobile", member.getMobile());
							if(info.get("company")==null)info.put("company", member.getUsername());
							if(info.get("company")==null)info.put("company", member.getMobile());
						}
					}
				}
			}
			if(version != null && version == 2.31F){//判断是否是不支付的版本，如果是，将保证金改为0
				info.put("deposit", 0);
			}
			result.put("data",info);
			result.put("response", "success");
			result.put("msg", "获取成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "获取失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 根据主键获取对象
	 * @param id
	 * @param model
	 * @author WKX
	 */
	@RequestMapping("/app/discountrecord2/get/task")
	public String getTask(Integer id,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		List<Map<String,Object>> list = discountrecordService.getTaskAndInfoById(id);
		result.put("data",list);
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 获取机构上传付款凭证
	 * @param id
	 * @param model
	 * @author WKX
	 */
	@RequestMapping("/app/discountrecord2/get/img")
	public String getImg(Integer id,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		Map<String,Object> info = discountrecordService.getInfoAndImgById(id);
		result.put("data",info);
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 用户取消订单
	 * @param id 贴现订单
	 * @param cancel 取消原因
	 * @param reason 原因为本（文字、其他）
	 * @param currentState 当前状态
	 * @param version 版本
	 * @param model
	 * @author WKX
	 */
	@RequestMapping("/app/discountrecord2/update")
	public String update(Integer id,Integer cancel,String reason,Integer currentState,Float version,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		String msg = "操作失败";
		String alert = "操作成功";
		String ok = "确定";
		try {
			DistributeOrder order = null;
			Map<String,Object> info = discountrecordService.getInfoAndOrderById(id);
			if(version!=null && version>2.2F){//验证当前数据是否过期（APP2.2）
				if(info!=null && info.get("orderflag")!=null && Integer.valueOf(info.get("orderflag").toString())!=currentState){
					msg = "数据已过期";
					throw new Exception(msg);
				}
			}
			
			if(info!=null && info.get("dtboId")!=null){//机构订单（说明已派单）
				Integer dtboId = Integer.valueOf(info.get("dtboId").toString());
				order = distributeOrderService.getById(dtboId);
				if(order!=null)order.setState(OrderState.INVALID.getCode());//变为无效
			}
			Discountrecord dis = discountrecordService.getById(id);
			dis.setOrderflag(0);//无效订单
			dis.setVisitState(0);//企业贴现订单为无效订单时默认待回访状态
			dis.setCancel(cancel);
			dis.setCancelCause(reason);
			
			if(cancel!=null && order!=null && order.getDepositState()!=null && order.getDepositState()==1 && cancel==4){//当已经有机构接单（企业取消订单原因为其他时需要后台审核）
				RefundExamine refundExamine = new RefundExamine();
				refundExamine.setDcrdId(dis.getId());
				refundExamine.setDtboId(order.getId());
				refundExamine.setCancelRole(1);//企业端拒绝
				refundExamine.setCause(reason);//原因
				refundExamineService.saveModelAndUpdateDis(refundExamine, dis, order);
			}else{
				discountrecordService.updateAndTaskToInvalid(dis,order, dis.getMemberid(), reason);//针对APP2.2已含退款
			}
			
			result.put("response", "success");
			result.put("msg", "操作成功");
			alert = "亲，您已取消该笔订单，欢迎再次使用！";
			ok = "好的";
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", msg);
			e.printStackTrace();
		}
		result.put("alert", alert);
		result.put("ok", ok);
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 确定收到款项 订单完成
	 * @param id
	 * @param model
	 * @author WKX
	 */
	@RequestMapping("/app/discountrecord2/update/finish")
	public String finish(Integer id,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		String des = "订单已完成";
		try {
			DistributeOrder order = distributeOrderService.getByDcrdId(id);
			
			Discountrecord dis = discountrecordService.getById(id);
			dis.setOrderflag(OrderState.COMPLETE.getCode());//已完成
			dis.setVisitState(0);//订单完成后，后台备注待回访状态
			if(order!=null){
				order.setState(OrderState.COMPLETE.getCode());//已完成
			}
			discountrecordService.updateAndTaskToFinish(dis,order, dis.getMemberid(), des);
			
			
			Org temp = orgService.getById(order.getOrgId());
			if(temp!=null && temp.getMemberId()!=null){
				doPushJob(temp.getMemberId(), OrderState.COMPLETE.getCode(), order.getId(), Type.DISTRIBUTEORDER,null);//机构
			}
			doPushJob(dis.getMemberid(), OrderState.COMPLETE.getCode(), dis.getId(), Type.DISCOUNTRECORD,null);//企业
			
			//已完成（记入票据管理）
			Accountrecord acc = accountrecordService.getByDcrdIdAndBelong(dis.getId(),0);//所属人（0企业member、1机构org）
			if(acc == null){
				Accountrecord accountrecord = new Accountrecord();
				accountrecord.setAllprice(dis.getAllmoney());
				accountrecord.setTiexiandate(dis.getBegintime());
				accountrecord.setDaoqidate(dis.getEndtime());
				accountrecord.setMemberid(dis.getMemberid());
				accountrecord.setPublishtime(new Date());
				accountrecord.setCreateTime(new Date());
				accountrecord.setType1(dis.getType2());
				if(dis.getType1() == 1){
					accountrecord.setPiaojushuxing("纸票");
				}else{
					accountrecord.setPiaojushuxing("电票");
				}
				accountrecord.setAcceptTime(dis.getAcceptTime());
				accountrecord.setIsTiexian("1");
				accountrecord.setTiexianType("系统");
				accountrecord.setDiscountrecordId(dis.getId());//关联id
				accountrecord.setBelong(0);//0企业member
				accountrecordService.saveAccountrecord(accountrecord);
			}
			Accountrecord acc1 = accountrecordService.getByDcrdIdAndBelong(order.getId(),1);//所属人（0企业member、1机构org）
			if(acc1 == null){
				Accountrecord accountrecord = new Accountrecord();
				accountrecord.setAllprice(dis.getAllmoney());
				accountrecord.setTiexiandate(dis.getBegintime());
				accountrecord.setDaoqidate(dis.getEndtime());
				accountrecord.setMemberid(order.getOrgId());
				accountrecord.setPublishtime(new Date());
				accountrecord.setCreateTime(new Date());
				accountrecord.setType1(dis.getType2());
				if(dis.getType1() == 1){
					accountrecord.setPiaojushuxing("纸票");
				}else{
					accountrecord.setPiaojushuxing("电票");
				}
				accountrecord.setAcceptTime(dis.getAcceptTime());
				accountrecord.setIsTiexian("1");
				accountrecord.setTiexianType("系统");
				accountrecord.setDiscountrecordId(dis.getId());//关联id
				accountrecord.setBelong(1);//1机构org
				accountrecordService.saveAccountrecord(accountrecord);
			}
			
			List<IntegraltradingDetail> listactivity = activityService.getlistActivity("确认完成",DateUtil.formart(new Date(), DateUtil.FORMART3),dis.getMemberid());
			if(listactivity == null){
				activityService.timingIntegral(dis.getMemberid(), 30, "确认完成", null);
			}
			
			Member member1 = memberService.getById(dis.getMemberid());//   企业端      已完成订单    发送消息
			Map<String,String> param = new HashMap<String, String>();
			param.put("type1",dis.getType1()==1?"纸票":"电票");
			param.put("type2",DataMatchUtil.getCDHByTypeFromNew(dis.getType2()));
			param.put("allmoney", dis.getAllmoney()!=null?dis.getAllmoney().toString():"");
			param.put("endtime",DateUtil.formart(dis.getEndtime(), "yyyy-MM-dd"));
			if(member1!=null && member1.getMobile()!=null){
				SendMessagesUtil.sendMessage(member1.getMobile(), "SMS_7760021", param);
			}
			Map<String,Object> member = memberService.getInfoByOrgId(order.getOrgId());
			if(member!=null && member.get("mobile")!=null){
				SendMessagesUtil.sendMessage(member.get("mobile").toString(), "SMS_7800019", param);
			}
			
			result.put("response", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 获取计息天数和贴现利息
	 * @param type1 票据属性（1纸票、2电票）
	 * @param type2 承兑行：1国股、2城商、3外资、4农商、5农合、6农信、7村镇
	 * @param flag 电票（1半年、2一年）
	 * @param allmoney 票据金额
	 * @param start 贴现日期
	 * @param end 到期日期
	 * @param cityId 城市id
	 * @param cityName 城市名称
	 * @param limit 电票期限
	 * @param model
	 * @author WKX
	 */
	@RequestMapping("/app/excel/price")
	public String myExcel(Integer type1,Integer type2,Integer flag,Float allmoney,String start,String end,String cityName,Integer cityId,Model model,Integer limit,Float version){
		Map<String,Object> result = new HashMap<String, Object>();
		
		String ps = Constant.properties.getProperty("VERSION_ALERT");//@WKX EDIT版本过期提示[因之前版本强制更新有问题，现做提示处理]
    	if("on".equals(ps) && version!=null && version<=2.32F){
    		result.put("response", "failed");
			result.put("msg", "当前版本已停用，请下载最新版本票据管家APP。");
			model.addAttribute("retValue",JacksonUtil.objToJson(result));
			return "ajax";
    	}
		
		try {
			Date s = DateUtil.parser(start, DateUtil.FORMART3);
			Date e = DateUtil.parser(end, DateUtil.FORMART3);
			int shengDay = DateUtil.daysBetween(s, e);//天数（对应几个月）
			
			Historyprice query = new Historyprice();
			query.setType6(type1);//票据属性（1纸票、2电票）
			query.setType1(type2);//承兑行：1国股、2城商、3外资、4农商、5农合、6农信、7村镇
			query.setType3(1);//买断带票 1买断 2带票 （暂默认1）
			query.setType4(1);//地域 1长三角2珠三角3华中4环渤海5西南
			
			//APP2.2之后加入交易城市（老版本默认上海）
			if(cityId!=null){
				query.setCityId(cityId);//城市id
			}else if(StringUtils.isNotBlank(cityName)){
				List<Region> regionL = regionService.getByNameAndType(cityName, "C");
				if(regionL!=null && regionL.size()>0){
					query.setCityId(regionL.get(0).getId());//城市 id
				}else{
					query.setCityId(Constant.DEFAULT_CITY_ID);//城市id
				}
			}else{
				query.setCityId(Constant.DEFAULT_CITY_ID);//城市id
			}
			
			//票据金额 :1:500以上 2:100-500 3:50-100  4:<50
			if(500<=allmoney){ 
				query.setType2(1);
			}else if(100<=allmoney && allmoney<500){
				query.setType2(2);
			}else if(50<=allmoney && allmoney<100){
				query.setType2(3);
			}else if(allmoney<50){
				query.setType2(4);
			}
			
			String day = DateUtil.formart(new Date(), DateUtil.FORMART3);
			query.setDay(day);//查询当天报价
			
			int tzts = getTzts(type1,e);//调整天数（根据票据类型）
			int jxts = shengDay + tzts;//计息天数
			
			//小于等于90天0、91-178天1、大于等于179天2
			if(allmoney<500){//大票没有票据属性
				if(179<=jxts){
					query.setType5(3);
				}else if(91<=jxts && jxts<179){
					query.setType5(2);
				}else if(0<=jxts && jxts<91){
					query.setType5(1);
				}
            }
			if(type1 == 1){
				query.setType5(2);
			}
			//APP2.2 电票期限 
			if(limit != null){
				if(allmoney>=500){//大于500 设置type7清掉type2
					query.setType7(limit);
					query.setType2(null);
				}
			}else{
				if(query.getType6()==2){//如果选择了电票且limt为空 默认1年
					if(allmoney>=500){//大于500 设置type7清掉type2
						query.setType7(limit);
						query.setType2(null);
					}
				}
			}
			
			List<Historyprice> list = historypriceService.getList(query);
			if(list!=null && list.size()>0){
				Historyprice temp = list.get(0);
				result.put("data", temp);
				
				String rate = temp.getPrice();
				String rate1 = temp.getPrice1();
				String rate2 = temp.getPrice2();
				result.put("rate",rate);
				result.put("rate1",rate1);
				result.put("rate2",rate2);
				
				if(1==type1){//纸票
					if(500<=allmoney){//大票
						Float r = 0F;
						if(StringUtils.isNotBlank(rate) && !"--".equals(rate.trim())){
							r = (allmoney*10000)*jxts*((Float.valueOf(rate)/30)/1000);
						}
						result.put("txlx", r);
					}else{//小票
						Float r = 0F;
						if(StringUtils.isNotBlank(rate) && !"--".equals(rate.trim())){
							r = (allmoney*10000)*jxts*((Float.valueOf(rate)/30)/1000);
							if(StringUtils.isNotBlank(rate1) && !"--".equals(rate1.trim())){
								r += (allmoney/10)*Float.valueOf(rate1);
							}
						}else if(StringUtils.isNotBlank(rate2) && !"--".equals(rate2.trim())){
							Float r2 = Float.valueOf(rate2);
							r = (allmoney/10)*r2;
							if(StringUtils.isNotBlank(rate1) && !"--".equals(rate1.trim())){
								r2 -= Float.valueOf(rate1);
							}
							Float r_ = r2/jxts/100000*1000*30;
							result.put("rate",(float) (Math.round(r_ * 100)) / 100);
						}
						result.put("txlx", r);
					}
				}else{//电票
					Float r = 0F;
					if(StringUtils.isNotBlank(rate) && !"--".equals(rate.trim())){//年利率
						r = ((allmoney*10000)*jxts*(Float.valueOf(rate)/100))/360;
					}
					result.put("txlx", r);
				}
				result.put("allmoney", allmoney);
				result.put("response", "success");
				result.put("msg", "操作成功");
			}else{
				result.put("response", "failed");
				result.put("msg", "暂无数据，请尝试更改条件");
			}
			result.put("tzts", tzts);
			result.put("jxts", jxts);
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "网络异常");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 重新计算
	 * @param type1 票据属性（1纸票、2电票）
	 * @param type2 承兑行：1国股、2城商、3外资、4农商、5农合、6农信、7村镇
	 * @param flag 电票（1半年、2一年）
	 * @param allmoney 票据金额
	 * @param start 贴现日期
	 * @param end 到期日期
	 * @param rate 月（年）利率
	 * @param rate1 参数
	 * @param model
	 * @author WKX
	 */
	@RequestMapping("/app/reexcel/price")
	public String reExcel(Integer type1,Integer type2,Integer flag,Float allmoney,String start,String end,String rate,String rate1,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			Date s = DateUtil.parser(start, DateUtil.FORMART3);
			Date e = DateUtil.parser(end, DateUtil.FORMART3);
			int shengDay = DateUtil.daysBetween(s, e);//天数（对应几个月）
			
			Currentprice query = new Currentprice();
			query.setType6(type1);//票据属性（1纸票、2电票）
			query.setType1(type2);//承兑行：1国股、2城商、3外资、4农商、5农合、6农信、7村镇
			query.setType3(1);//买断带票 1买断 2带票 （暂默认1）
			query.setType4(1);//地域 1长三角2珠三角3华中4环渤海5西南
			
			//票据金额 :1:500以上 2:100-500 3:50-100  4:<50
			if(500<=allmoney){
				query.setType2(1);
			}else if(100<=allmoney && allmoney<500){
				query.setType2(2);
			}else if(50<=allmoney && allmoney<100){
				query.setType2(3);
			}else if(allmoney<50){
				query.setType2(4);
			}
			
			int tzts = getTzts(type1,e);//调整天数（根据票据类型）
			int jxts = shengDay + tzts;//计息天数
			
			//小于等于90天0、91-178天1、大于等于179天2
			if(allmoney<500){//大票没有票据属性
				if(179<=jxts){
					query.setType5(3);
				}else if(91<=jxts && jxts<179){
					query.setType5(2);
				}else if(0<=jxts && jxts<91){
					query.setType5(1);
				}
            }
			if(type1 == 1){
				query.setType5(2);
			}
			
			if(1==type1){//纸票
				if(500<=allmoney){//大票
					Float r = 0F;
					if(StringUtils.isNotBlank(rate) && !"--".equals(rate.trim())){
						r = (allmoney*10000)*jxts*((Float.valueOf(rate)/30)/1000);
						r = (float) (r/1.2);
					}
					result.put("txlx", r);
				}else{//小票
					Float r = 0F;
					if(StringUtils.isNotBlank(rate) && !"--".equals(rate.trim())){
						r = (allmoney*10000)*jxts*((Float.valueOf(rate)/30)/1000);
						if(StringUtils.isNotBlank(rate1) && !"--".equals(rate1.trim())){
							r += (allmoney/10)*Float.valueOf(rate1);
						}
					}
					result.put("txlx", r);
				}
			}else{//电票
				Float r = 0F;
				if(StringUtils.isNotBlank(rate) && !"--".equals(rate.trim())){//年利率
					r = ((allmoney*10000)*jxts*(Float.valueOf(rate)/100))/360;
				}
				result.put("txlx", r);
			}
			
			result.put("tzts", tzts);
			result.put("jxts", jxts);
			result.put("allmoney", allmoney);
			result.put("response", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "网络异常");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 根据节日获取调整天数
	 * @author WKX
	 * @param type1
	 * @param end
	 * @throws ParseException 
	 * @since 2016年4月11日 下午7:49:50
	 */
	private int getTzts(Integer type1,Date end) throws ParseException{
		int init = 0;
		Notice notice = tiexianNoticeService.findFestivalByNowTime(end);//查询（当前日期在假期内）本年度提示
		if(notice!=null && notice.getEndDate()!=null){
			init = DateUtil.daysBetween(end, notice.getEndDate())+1;//天数（对应几个月）
		}else{
			List<NoticeAdd> adds = null;
			Calendar cal = Calendar.getInstance();
			cal.setTime(end);
			int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
			if(6==w){
				Date temp = DateUtil.addDays(end, 1);
				adds = noticeAddService.getByNowTime(temp);
				if(adds!=null && adds.size()>0){//当前是周六，但是周日也补班
				}else{
					adds = noticeAddService.getByNowTime(end);
					if(adds!=null && adds.size()>0){//当前是周六，但是周六补班，周日放假
						init += 1;
					}else{
						init += 2;
					}
				}
			}else if(7==w){
				adds = noticeAddService.getByNowTime(end);
				if(adds!=null && adds.size()>0){//当前是周日，周日补班
				}else{
					init += 1;
				}
			}
		}
		if(1==type1)init += 3;//纸票加3天
		return init;
	}
	
	/**
	 * 根据用户主键获取未评论订单
	 * @param memberId
	 * @param model
	 * @author WKX
	 */
	@RequestMapping("/app/discountrecord/uncomments")
	public String getUnCommentsByMemberId(Integer memberId,Float version,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> list1 = new ArrayList<Map<String,Object>>();
			if(version!=null && version>=2.3F){
				for(int i=0;i<3;i++){
					list1=commentsService.getUnCommentsByMemberAndtype(memberId,i);
					for(Map<String,Object> m :list1){
					m.put("type", i);
					}
					list.addAll(list1);
				}
			}else{
				list = discountrecordService.getUnCommentsByMemberId(memberId);
			}
			result.put("data", list);
			result.put("response", "success");
			result.put("msg", "加载成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "网络异常");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 评价（保存）
	 * @param comments
	 * @param model
	 * @author WKX
	 */
	@RequestMapping("/app/discountrecord/comments/save")
	public String saveComment(Comments comments,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			if(comments==null || comments.getDcrdId()==null)throw new Exception();
			List<Comments> list = commentsService.getByDcrdId(comments.getDcrdId(),comments.getType());
			if(list==null || list.size()==0){
				Map<String,Object> map = null;
				if(comments.getType()==null || comments.getType()==0){
					List<IntegraltradingDetail> listactivity = activityService.getlistActivity("评论奖励",DateUtil.formart(new Date(), DateUtil.FORMART3),comments.getOperatorId());
					if(listactivity != null && listactivity.size()<5){
						activityService.timingIntegral(comments.getOperatorId(), 20, "评论奖励", null);
					}
					map = discountrecordService.getInfoAndOrderById(comments.getDcrdId());
				}else if(comments.getType()==1){
					map = discountrecordSpService.getInfoAndOrderById(comments.getDcrdId());
				}else if(comments.getType()==2){
					map = discountrecordPlService.getInfoAndOrderById(comments.getDcrdId());
				}
				if(map!=null && map.get("dtboId")!=null){
					comments.setDtboId(Integer.valueOf(map.get("dtboId").toString()));
				}
				comments.setCreateTime(new Date());
				commentsService.saveModel(comments);
			}
			result.put("response", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "网络异常");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 获取订单详情（评论显示）
	 * @param id
	 * @param type APP2.3根据Id和Type获取订单详情（评论显示）app2.3新增type，3个类型的公用一个评论页面，根据type区分
	 * @param model
	 * @author WKX
	 * @EDIT APP2.3 ZY 2016年8月19日 上午10:45:08
	 */
	@RequestMapping("/app/discountrecord/comments/show")
	public String getInfoAndOrderById(Integer id,String type,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			if(StringUtils.isBlank(type) || type.equals("0")){
				map = discountrecordService.getInfoAndOrderById(id);
			}
			else if(type.equals("1")){
				map = discountrecordSpService.getInfoAndOrderById(id);
			}
			else if(type.equals("2")){
				map = discountrecordPlService.getInfoAndOrderById(id);
			}
			if(map!=null && map.get("org_id")!=null){
				Object orgId = map.get("org_id");
				Map<String,Object> orgInfo = orgInfoService.getByOrgId(Integer.valueOf(orgId.toString()), 1);//机构
				
				if(orgInfo!=null && orgInfo.get("company")!=null){
					map.put("company", orgInfo.get("company"));
				}else if(orgInfo!=null && orgInfo.get("member_id")!=null){
					Object mid = orgInfo.get("member_id");
					Member m = memberService.getById(Integer.valueOf(mid.toString()));
					if(m!=null && StringUtils.isNotBlank(m.getUsername())){
						map.put("company", m.getUsername());
					}else{
						map.put("company", m.getMobile());
					}
				}
			}
			result.put("data", map);
			result.put("response", "success");
			result.put("msg", "加载成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "网络异常");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 保证金列表
	 * @author cx
	 * @param memberId
	 */
	@RequestMapping("/app/baojinlist")
	public String baoJinList(String memberId, HttpServletRequest request, HttpServletResponse response,Model model){
		List<Map<String,Object>> list = discountrecordService.getDeposit(memberId);
		List<Map<String,Object>> lists = new ArrayList<Map<String,Object>>();
		if(list.size() > 0){
			for(Map<String,Object> data : list){
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("orgid", data.get("org_id"));
				map.put("txlx", data.get("txlx"));
				map.put("no", data.get("no"));
				map.put("qydeposit", data.get("qydeposit"));
				map.put("jgdeposit", data.get("jgdeposit"));
				map.put("money", data.get("allmoney"));
				OrgInfo orgInfo = orgInfoService.getOrgInfoById(Integer.parseInt(data.get("org_id").toString()),null).get(0);
				map.put("company", orgInfo.getCompany());
				lists.add(map);
			}
		}else{
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("results", "null");
			lists.add(map);
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(lists));
		return "ajax";
	}
	
	/**
	 * 派单（系统派单测试）
	 * @author WKX
	 * @param id 贴现订单主键
	 * @since 2016年5月25日 下午3:10:09
	 */
	@RequestMapping("/app/paidan")
	public @ResponseBody Map<String,Object> paidan(Integer id){
		return paidanService.updatePaidan(id, paidanService.getConfigXML());
	}

	/**
	 * 显示接单机构的详情（评分等）
	 * @author WKX
	 * @param id
	 * @param model
	 * @since 2016年8月18日 下午3:40:32
	 */
	@RequestMapping("/app/discountrecord2/get/org")
	public String getOrgInfoBy(Integer id,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			if(id==null)throw new Exception("数据异常");
			Map<String,Object> org = new HashMap<String, Object>();
			DistributeOrder dist = distributeOrderService.getById(id);
			if(dist!=null){
				Map<String,Object> orgInfo = orgInfoService.getByOrgId(dist.getOrgId(), 1);
				org.put("company", orgInfo.get("company"));
				org.put("phone", orgInfo.get("phone"));
				org.put("txlx", dist.getTxlx());
				org.put("todoorPrice", dist.getTodoorPrice());
				org.put("todoorTime", dist.getTodoorTime());
				org = orgService.getComment(org, dist.getOrgId());
				
				Discountrecord disc = discountrecordService.getById(dist.getDcrdId());
				org.put("needTodoor", disc.getNeedTodoor());
				if(disc!=null){//距离
					Float lon_a = dist.getLongitude();
					Float lat_a = dist.getLatitude();
					Float lon_b = disc.getLongitude();
					Float lat_b = disc.getLatitude();
					if(lon_a!=null && lat_a!=null && lon_b!=null && lat_b!=null){
						Double distance = DistanceUtil.getDistance(lat_a, lon_a, lat_b, lon_b);
						org.put("distance",distance);
					}
				}
			}
			result.put("data", org);
			result.put("response", "success");
			result.put("msg", "加载成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "网络异常");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 根据主键获取对象（主要用来查看贴现地址保存情况）
	 * @param id 贴现主键
	 * @param model
	 * @throws IOException
	 * @author WKX
	 */
	@RequestMapping("/app/discountrecord/get/info")
	public String getDisc(Integer id,Float version,Model model) throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			Discountrecord dis = discountrecordService.getById(id);
			if(dis==null)throw new Exception("订单获取异常");
			if(version != null && version == 2.31F){//2.31版本不支付保证金
				float a = 0;
				dis.setDeposit(a);
			}
			result.put("response", "success");
			result.put("msg", "加载成功");
			result.put("data", dis);
		}catch(Exception e){
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", "加载失败");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
}