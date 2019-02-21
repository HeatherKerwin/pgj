package com.utiexian.website.controller;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.Enum.OrderState;
import com.ry.core.entity.Accountrecord;
import com.ry.core.entity.Comments;
import com.ry.core.entity.Discountrecord;
import com.ry.core.entity.DiscountrecordTask.Operator;
import com.ry.core.entity.DistributeOrder;
import com.ry.core.entity.Member;
import com.ry.core.entity.Org;
import com.ry.core.entity.RefundExamine;
import com.ry.core.entity.Systeminfo;
import com.ry.core.entity.Systeminfo.Type;
import com.ry.core.form.DiscountrecordForm;
import com.ry.core.service.AccountrecordService;
import com.ry.core.service.CommentsService;
import com.ry.core.service.DiscountrecordPlService;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.DiscountrecordSpService;
import com.ry.core.service.DistributeOrderService;
import com.ry.core.service.DistributeOrderSpService;
import com.ry.core.service.MemberService;
import com.ry.core.service.OrgInfoService;
import com.ry.core.service.OrgLimitService;
import com.ry.core.service.OrgService;
import com.ry.core.service.RefundExamineService;
import com.ry.core.service.RefundRecordService;
import com.ry.core.service.SysteminfoService;
import com.ry.core.util.Constant;
import com.ry.core.util.JPushUtil;
import com.ry.util.DataMatchUtil;
import com.ry.util.DateUtil;
import com.ry.util.DistanceUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.SendMessagesUtil;
import com.ry.util.page.PageResults;

import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;


/**
 * 银票订单列表
 * @author MH
 *
 */
@Controller
public class DiscountOrderController {
	
	private static Logger logger = Logger.getLogger(DiscountOrderController.class);
		
	@Resource
	DiscountrecordService discountrecordService;
	
	@Resource
	DiscountrecordSpService discountrecordSpService;
	
	@Resource
	DiscountrecordPlService discountrecordPlService;
	
	@Resource
	DistributeOrderService distributeOrderService;
	
	@Resource
	RefundExamineService refundExamineService;
	
	@Resource
	CommentsService commentsService;
	
	@Resource
	OrgLimitService orgLimitService;
	
	@Resource
	OrgService orgService;
	
	@Resource
	SysteminfoService systeminfoService;
	
	@Resource
	MemberService memberService;
	
	@Resource
	AccountrecordService accountrecordService;
	
	@Resource
	OrgInfoService orgInfoService;
	
	@Resource
	DistributeOrderSpService distributeOrderSpService;
	
	@Resource
	RefundRecordService refundRecordService;
	
	/**
	 * 创建线程池
	 * @author MH
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
	 * @author MH
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
	 * @author MH
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
	
	/**
	 * 票据贴现跳转
	 * 注：登录拦截器参照web.xml
	 * @author MH
	 * @param request
	 * @param ym 
	 */
	@RequestMapping("/discountorder/discount")
	public String discount(HttpServletRequest request,String ym){
		if(ym==null){
			ym="yp";
		} 
		if(ym.equals("yp")){
			return "discountorder/discountorder_yp1";
		}else if(ym.equals("sp")){
			return "discountorder/discountorder_sp1";
		}else if(ym.equals("pl")){
			return "discountorder/discountorder_pl1";
		}
		return "discountorder/discountorder_yp1";
	}
	
	/**
	 * 根据条件跳转到详情页面
	 * @author MH
	 * @param no 订单号
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping("discountorder/optpage")
	public String optPage(Integer no,Integer orderflag,HttpServletRequest request){
		request.setAttribute("id", no);
		request.setAttribute("orderflag", orderflag);
		return "discountorder/discountorder";
	}
	
	/**
	 * 用户取消订单
	 * @param id 贴现订单
	 * @param cancel 取消原因
	 * @param reason 原因为本（文字、其他）
	 * @param currentState 当前状态
	 * @param model
	 * @author MH
	 */
	@RequestMapping("/discountorder/update")
	public String update(Integer id,Integer cancel,String reason,Integer currentState,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		String msg = "操作失败";
		String alert = "操作成功";
		String ok = "确定";
		try {
			DistributeOrder order = null;
			Map<String,Object> info = discountrecordService.getInfoAndOrderById(id);
			if(info!=null && info.get("orderflag")!=null && Integer.valueOf(info.get("orderflag").toString())!=currentState){
				msg = "数据已过期";
				throw new Exception(msg);
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
	 * 点击支付跳转到确认订单的页面
	 * @author MH
	 * @param no 订单号
	 * @param request
	 * @throws ParseException
	 */
	@RequestMapping("discountorder/payconfirmorder")
	public String payconfirmorder(HttpServletRequest request,String no) throws ParseException{
		Discountrecord discountrecord = discountrecordService.getByOrdernumber(no);
		Date begintime = discountrecord.getBegintime();
		Date endtime = discountrecord.getEndtime();
		Integer type1 = discountrecord.getType1();
		int shengDay = com.ry.util.DateUtil.daysBetween(begintime, endtime);//天数（对应几个月） 
		int tzts = discountrecordService.getTzts(type1, endtime);//调整天数（根据票据类型）
		int jxts = shengDay + tzts;//计息天数
		String start = DateUtil.formart(begintime,DateUtil.FORMART3);
		String end = DateUtil.formart(endtime,DateUtil.FORMART3);
		String switch_ = Constant.properties.getProperty("PAYSWITCH");//支付开关
		if("off".equals(switch_)){
			discountrecord.setDeposit(0F);
		}
		request.setAttribute("discountrecord",discountrecord);
		request.setAttribute("start",start);
		request.setAttribute("end",end);
		request.setAttribute("jxts", jxts);
		return "discountrecord/discount_yp2"; 
	}

	/**
	 * @author MH
	 * @param request
	 * @param form 表单元素
	 * @param xqid 详情页面的订单号
	 * @param state1 交易中为1
	 * @param state2 交易中为2
	 * @param type 类型
	 * @param start1 时间1
	 * @param end1 时间2
	 * @param pageIndex 
	 * @param pageSize
	 * @param model
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	@RequestMapping("/discountorder/list")
	public String listDiscountrecordByMemberId(HttpServletRequest request,DiscountrecordForm form,String xqid,Integer state1,Integer state2,Integer type,Integer[] state,String start1,String end1,Integer pageIndex,Integer pageSize,Model model) throws NumberFormatException, Exception{
		Map<String,Object> result = new HashMap<String, Object>();
		Member m = new Member();
		m =(Member) request.getSession().getAttribute("member");
		Integer  memberId = m.getId();
		logger.info("/discountorder/list memberId:"+memberId);
		form.setMemberid(memberId);
		if(state1!=null && state2!=null){
			Integer [] a = {state1,state2} ;
			form.setState(a);
		}
		if(start1!=null){
			Date start =DateUtil.parser(start1, DateUtil.FORMART3);
			form.setBegintime(start);
		}
		if(end1!=null){
			Date end =DateUtil.parser(end1, DateUtil.FORMART3);
			form.setEnd(end);
		}
		if(xqid!=null){
			form.setOrdernumber(xqid);
		}
		if(pageIndex==null)pageIndex = 0;
		if(pageSize==null)pageSize = 10;
		if(form!=null && form.getMemberid()!=null){
			PageResults<Map<String,Object>> page = discountrecordService.getPcPageList(pageIndex, pageSize, form);
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			if(page!=null && page.getResults()!=null){
				Map<String,Object> temp = null;
				DecimalFormat df = new DecimalFormat(".00");
				for (Map<String, Object>  map : page.getResults()) {
					temp = new HashMap<String, Object>();
					temp = map;
					Float lon_a = (Float) map.get("lon_a");
					Float lat_a = (Float) map.get("lat_a");
					Float lon_b = (Float) map.get("lon_b");
					Float lat_b =(Float) map.get("lat_b");
					if(lon_a != null && lat_a != null && lon_b!= null && lat_b != null){
						Double distance = DistanceUtil.getDistance(lat_a, lon_a, lat_b, lon_b);
						temp.put("distance", df.format(distance));
					}else{
						temp.put("distance", null);
					}
					
					Date sj1 =(Date)map.get("begintime");
					Date sj2 =(Date)map.get("endtime");
					Integer type1 =(Integer) map.get("type1");
					int shengDay = com.ry.util.DateUtil.daysBetween(sj1, sj2);//天数（对应几个月） 
					int tzts = discountrecordService.getTzts(type1, sj2);//调整天数（根据票据类型）
					int jxts = shengDay + tzts;//计息天数
					temp.put("jxts", jxts);
					Map<String, Object>  maps = null;
					
					if(map.get("org_id")!=null && map.get("org_id")!=""){
						maps = orgService.getComment(map, Integer.valueOf(map.get("org_id").toString()));
					}
					
					if(maps!=null){
						if(maps.get("_speed")!=null && maps.get("_speed")!="" && maps.get("_speed")!="--"){
							Double pjspeed =(Double) maps.get("_speed")*2;
							temp.put("pjspeed", df.format(pjspeed));
						}else{
							temp.put("pjspeed","--");
						}
						if(maps.get("_price")!=null && maps.get("_price")!="" && maps.get("_price")!="--"){
							Double pjprice = (Double)maps.get("_price")*2;
							temp.put("pjprice", df.format(pjprice));
						}else{
							temp.put("pjprice","--");
						}
						if(maps.get("_service")!=null && maps.get("_service")!="" && maps.get("_service")!="--"){
							Double pjservice = (Double)maps.get("_service")*2;
							temp.put("pjservice", df.format(pjservice));
						}else{
							temp.put("pjservice","--");
						}
						if(maps.get("_door")!=null && maps.get("_door")!="" && maps.get("_door")!="--"){
							Double pjdoor = (Double)maps.get("_door")*100;
							temp.put("pjdoor", df.format(pjdoor)+"%");
						}else{
							temp.put("pjdoor","--");
						}
					}
					list.add(temp);
				}
			}
			
			page.setResults(list);
			
			result.put("data",page);
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
	 * 评价（保存）
	 * @param comments 
	 * @param model
	 * @author MH
	 */
	@RequestMapping("/discountorder/comments/save")
	public String saveComment(HttpServletRequest request,Comments comments,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			if(comments==null || comments.getDcrdId()==null)throw new Exception();
			Member m = new Member();
			m =(Member) request.getSession().getAttribute("member");
			Integer  memberId = m.getId();
			comments.setOperatorId(memberId);
			List<Comments> list = commentsService.getByDcrdId(comments.getDcrdId(),comments.getType());
			if(list==null || list.size()==0){
				Map<String,Object> map = null;
				if(comments.getType()==null || comments.getType()==0){
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
	 * 确定收到款项 订单完成
	 * @param id
	 * @param model
	 * @author MH
	 */
	@RequestMapping("/discountorder/update/finish")
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
	 * 根据订单号获取对象显示详情
	 * @author MH
	 * @param id 订单号
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/discountorder/target")
	public String target(String id,HttpServletRequest request,Model model) throws Exception{
		Map<String,Object> result = new HashMap<String, Object>();
		try{
			if(id==null) throw new Exception();
			Discountrecord dis = discountrecordService.getByOrdernumber(id);
			if(dis!=null){
				result.put("data", dis);
				result.put("response", "success");
				result.put("msg", "操作成功");
			}
		}catch(Exception e){
			result.put("response", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));		
		return "ajax";
	}
}
