package com.ry.ryapp.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.sql.rowset.serial.SerialException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ry.core.entity.Discountrecord;
import com.ry.core.entity.DiscountrecordTask;
import com.ry.core.entity.DiscountrecordTask.Operator;
import com.ry.core.entity.DiscountrecordTask.OperatorType;
import com.ry.core.entity.DistributeOrder;
import com.ry.core.entity.DistributeOrderTask;
import com.ry.core.entity.HongbaoDetail;
import com.ry.core.entity.Image;
import com.ry.core.entity.IntegraltradingDetail;
import com.ry.core.entity.Member;
import com.ry.core.entity.Org;
import com.ry.core.entity.OrgInfo;
import com.ry.core.entity.RefundExamine;
import com.ry.core.entity.Systeminfo;
import com.ry.core.entity.Systeminfo.Type;
import com.ry.core.form.DistributeOrderForm;
import com.ry.core.service.ActivityService;
import com.ry.core.service.CommentsService;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.DistributeOrderService;
import com.ry.core.service.HongbaoService;
import com.ry.core.service.InquiryReplyService;
import com.ry.core.service.MemberService;
import com.ry.core.service.OrgInfoService;
import com.ry.core.service.OrgService;
import com.ry.core.service.PaidanService;
import com.ry.core.service.PriceService;
import com.ry.core.service.RefundExamineService;
import com.ry.core.service.RefundRecordService;
import com.ry.core.service.SysteminfoService;
import com.ry.core.service.TiexianNoticeService;
import com.ry.core.util.Constant;
import com.ry.core.util.FileUtil;
import com.ry.core.util.JPushUtil;
import com.ry.util.AlipayUtil;
import com.ry.util.Bill99Util;
import com.ry.util.DateUtil;
import com.ry.util.DistanceUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.SendMessagesUtil;
import com.ry.util.page.PageResults;

import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;

/**
 * 机构订单
 * @author WKX
 */
@Controller
@RequestMapping("/app/distributeOrder/")
public class DistributeOrderController {
	
	private static Logger payLog = Logger.getLogger("payLog");
	
	@Resource
	DistributeOrderService distributeOrderService;
	
	@Resource
	DiscountrecordService discountrecordService;

	@Resource
	PriceService priceService;
	
	@Resource
	HongbaoService hongbaoService;
	
	@Resource
	OrgService orgService;
	
	@Resource
	InquiryReplyService inquiryReplyService;
	
	@Resource
	SysteminfoService systeminfoService;
	
	@Resource
	MemberService memberService;
	
	@Resource
	TiexianNoticeService tiexianNoticeService;
	
	@Resource
	OrgInfoService orgInfoService;
	
	@Resource
	PaidanService paidanService;
	
	@Resource
	CommentsService commentsService;
	
	@Resource
	RefundExamineService refundExamineService;
	
	@Resource
	RefundRecordService refundRecordService;
	
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
				if(member!=null && org.apache.commons.lang.StringUtils.isNotBlank(member.getMobile())){
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
	
	/**
	 * 分页显示机构订单
	 * @author WKX
	 * @param form
	 * @param pageIndex
	 * @param pageSize
	 * @param belong APP2.2之后加的 角色（正好可以用来区分版本APP2.2之前不校验 保证金）
	 * @param model
	 * @throws IOException
	 * @since 2016年3月3日 下午5:00:34
	 */
	@RequestMapping("list")
	public String list(DistributeOrderForm form,Integer pageIndex,Integer pageSize,Float belong,Model model){
		if(belong!=null && form.getVersion()==null)form.setVersion(2.2F);//设置下版本
		
		Map<String,Object> result = new HashMap<String, Object>();
		if(pageIndex==null)pageIndex = 0;
		if(pageSize==null)pageSize = 10;
		if(form!=null && form.getOrgId()!=null){
			PageResults<Map<String,Object>> page = distributeOrderService.getPageList(pageIndex, pageSize, form);
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
	 * 显示机构当前未处理的订单（主页）
	 * @author WKX
	 * @param orgId 机构主键
	 * @param model
	 * @since 2016年3月3日 下午7:42:38
	 */
	@RequestMapping("index")
	public String index(Integer orgId,Float version,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		List<Map<String,Object>> list_0 = distributeOrderService.getUnReadByOrgIdAndState(orgId, 1);//待确认
		List<Map<String,Object>> list_1 = distributeOrderService.getUnReadByOrgIdAndState(orgId, 2);//验票中
		List<Map<String,Object>> list_2 = distributeOrderService.getUnReadByOrgIdAndState(orgId, 4);//代付款
		List<Map<String,Object>> list_3 = distributeOrderService.getAssignedByOrgId(orgId,version);//派单
		
		result.put("list_0", list_0==null?0:list_0.size());
		result.put("list_1", list_1==null?0:list_1.size());
		result.put("list_2", list_2==null?0:list_2.size());
		result.put("list_3", list_3==null?0:list_3.size());
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 显示机构当前未处理的订单（我的订单）
	 * @author WKX
	 * @param orgId 机构主键
	 * @param model
	 * @since 2016年3月3日 下午7:42:38
	 */
	@RequestMapping("inner")
	public String inner(Integer orgId,Float version,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		List<Map<String,Object>> list_ = distributeOrderService.getUnReadByOrgIdAndState(orgId, 1);//待确认
		
		List<Map<String,Object>> list_0 = distributeOrderService.getUnReadByOrgIdAndState(orgId, 2);//验票中
		List<Map<String,Object>> list_1 = distributeOrderService.getUnReadByOrgIdAndState(orgId, 3);//已完成
		List<Map<String,Object>> list_2 = distributeOrderService.getUnReadByOrgIdAndState(orgId, 4);//待付款
		List<Map<String,Object>> list_3 = distributeOrderService.getUnReadByOrgIdAndState(orgId, 5);//已付款
		
		if(version!=null && version>=2.2F){//新版本2.2后 待确认中可能存在待接单
			List<Map<String,Object>> temp = new ArrayList<Map<String,Object>>();
			if(list_!=null && list_.size()>0){
				for(Map<String,Object> m:list_){//已支付的才是待确认的
					if(m!=null && (m.get("deposit_state")==null||"1".equals(m.get("deposit_state").toString())))temp.add(m);
				}
			}
			result.put("list_", list_==null?0:temp.size());
		}else{
			result.put("list_", list_==null?0:list_.size());
		}
		
		result.put("list_0", list_0==null?0:list_0.size());
		result.put("list_1", list_1==null?0:list_1.size());
		result.put("list_2", list_2==null?0:list_2.size());
		result.put("list_3", list_3==null?0:list_3.size());
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 派单主键（机构订单主键）
	 * @author WKX
	 * @param dtboId
	 * @param model
	 * @since 2016年3月7日 下午3:56:20
	 */
	@RequestMapping("get")
	public String get(Integer dtboId,Float version,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			Map<String,Object> map = distributeOrderService.updateReadTaskAndGetInfoById(dtboId);
			if(map==null)throw new Exception("数据异常");
			
			Object ordertime = map.get("ordertime");
			if(ordertime!=null)map.put("ordertime", ordertime.toString());
			
			Object rate = map.get("price");
			Object rate1 = map.get("price1");
			Object rate2 = map.get("price2");
			
			map.put("rate", rate);//月利率
			map.put("rate1", rate1);//参数
			map.put("rate2", rate2);//10万贴现多少钱
			
			Object ordernumber = map.get("ordernumber");
			if(ordernumber!=null){//企业订单编号（查询使用的红包）
				HongbaoDetail hb = hongbaoService.getByOrdernumber(ordernumber.toString());
				if(hb!=null)map.put("hb",hb.getPrice());
			}
			
			Object lon_a = map.get("lon");
			Object lat_a = map.get("lat");
			Object lon_b = map.get("longitude");
			Object lat_b = map.get("latitude");
			if(lon_a!=null && lat_a!=null && lon_b!=null && lat_b!=null){//距离
				Double distance = DistanceUtil.getDistance(Float.valueOf(lat_a.toString()), Float.valueOf(lon_a.toString()),Float.valueOf(lat_b.toString()),Float.valueOf(lon_b.toString()));
				map.put("distance",distance);
			}
			Object time = map.get("create_time");
			if(time!=null){
				int timer = DateUtil.calSeconds(DateUtil.parser(time.toString(), DateUtil.FORMART), new Date());
				map.put("timer", 600-timer);
			}
			if(version!=null && version == 2.31F){//2.31版本保证金支付暂时关闭
				map.put("dtdeposit", 0);//保证金
			}
			result.put("data",map);
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
	 * 根据主键获取对象
	 * @author WKX
	 * @param dtboId
	 * @param model
	 * @since 2016年3月17日 上午10:15:59
	 */
	@RequestMapping("get/task")
	public String getTask(Integer dtboId,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		List<Map<String,Object>> list = distributeOrderService.getTaskAndInfoById(dtboId);
		result.put("data",list);
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 上传付款凭证
	 * @author WKX
	 * @param id
	 * @param imagePath 上传的付款凭证
	 * @param model
	 * @since 2016年3月16日 上午11:17:54
	 */ 
	@RequestMapping("update/img")
	public String update(Integer dtboId,String imagePath,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		String alert = "操作成功";
		String ok = "确定";
		try {
			DistributeOrder order = distributeOrderService.getById(dtboId);
			if(order==null)throw new SerialException("数据异常");
			
			//将临时目录文件转移到目录文件中			
			if (!"".equals(imagePath)) {
				String temp = Constant.properties.getProperty("uploadpath") + Constant.TEMP_PATH;//保存目录包名的绝对路径
				String uploadPath = Constant.properties.getProperty("uploadpath") + Constant.UPLOAD_SRC;//保存目录包名的绝对路径
				//检查目录
				File uploadDir = new File(uploadPath);
				if(!uploadDir.isDirectory()){
					uploadDir.mkdirs();
				}
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
				String extpath = df.format(new Date());
				
				if (!"null".equalsIgnoreCase(imagePath)) {
					//临时文件夹 的文件 转到 保存目录中	
					String pString = uploadPath + extpath;
					String tString = temp + "image" + File.separator + extpath;
					if (StringUtils.hasText(imagePath)) {
						tString += imagePath.substring(imagePath.lastIndexOf("/"), imagePath.length());
						FileUtil.moveFile(tString, pString);
					}
					imagePath = Constant.UPLOAD_SRC + extpath + imagePath.substring(imagePath.lastIndexOf("/"), imagePath.length());
				}
			}
			
			order.setImagePath(imagePath);
			order.setState(5);//已付款
			
			DistributeOrderTask task = new DistributeOrderTask();
			task.setDtboId(order.getId());
			task.setOperatorType(OperatorType.MEMBER);
			task.setRemarks("订单已付款，请等待客服确认");
			task.setCreateTime(new Date());
			task.setState(5);//已付款
			
			Discountrecord record = null;
			DiscountrecordTask recordTask = null;
			if(order.getDcrdId()!=null){
				record = discountrecordService.getById(order.getDcrdId());
				if(record!=null){
					record.setOrderflag(4);//代收款
					recordTask = new DiscountrecordTask();
					recordTask.setDiscountrecordId(record.getId());
					recordTask.setCreateTime(new Date());
					recordTask.setOperatorDesc("订单待收款中，请您确认");
					recordTask.setOperatorType(OperatorType.MEMBER);
					recordTask.setOperator(Operator.UNTRANSACTION);//代收款
				}
			}
			
			if(order.getOrgId()!=null){//存储操作人主键
				Map<String,Object> org = orgService.getInfoById(order.getOrgId());
				if(org!=null && org.get("member_id")!=null){
					Integer temp = Integer.valueOf(org.get("member_id").toString());
					task.setOperatorId(temp);
					if(recordTask!=null)recordTask.setOperatorId(temp);
				}
			}
			distributeOrderService.updateDisAndSaveTask(order,task,record,recordTask);
			
			String des = "您的这笔贴现订单，承兑方已上传付款凭证，请点击查看。";
			doPushJob(record.getMemberid(), 4, record.getId(), Type.DISCOUNTRECORD,des);//企业端推送（已上传付款凭证）
			
			result.put("response", "success");
			result.put("msg", "操作成功");
			alert = "亲，您上传的付款凭证持票企业可以查看哦！";
			ok = "好，我知道了";
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		result.put("alert", alert);
		result.put("ok", ok);
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 更新状态（机构订单）企业订单
	 * @param dtboId 订单主键
	 * @param state 变更的状态
	 * @param reason 验票失败（原因）
	 * @param model
	 * @since 2016年4月12日 下午8:37:14
	 * @EDIT WKX 2016年5月27日 匹配APP2.2 机构拒绝订单重新派单
	 */
	@RequestMapping("update/state")
	public String update(Integer dtboId,Integer state,String reason,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		boolean flag = true;
		String alert = "操作成功";
		String ok = "确定";
		try {
			DistributeOrder order = distributeOrderService.getById(dtboId);
			if(order==null || state==null)throw new SerialException("数据异常");

			if(order.getState()==1 && state==0){
				flag = false;//如果机构订单是待确认变为无效，企业订单是不需要变更的
				alert = "您已拒绝该笔订单，我们将不会再次派送该笔订单给您！";
				ok = "好，我知道了";
			}else if(order.getState()==2 && state==0){//验票失败
				alert = "亲，该笔订单验票失败，客服将核实订单状态并且我们将不会再次派送该笔订单给您！";
				ok = "好，我知道了";
			}
			if(state==0){
				state = -2;//匹配APP2.2后取消订单-主动拒绝
			}
			order.setState(state);
			
			DistributeOrderTask task = new DistributeOrderTask();
			task.setDtboId(order.getId());
			task.setOperatorType(OperatorType.MEMBER);
			String remarks = "";
			if(org.apache.commons.lang.StringUtils.isNotBlank(reason)){
				remarks = reason;
			}else if(0==state || -2==state){//无效订单
				remarks = "亲，您已取消该订单";
			}else if(1==state){//待确认
				remarks = "亲，我们派送的订单等待您的处理";
			}else if(2==state){//验票中
				remarks = "亲，您的订单正在验票中";
				alert = "亲，您准备去该持票企业进行验票，请认真核实订单信息呦！";
				ok = "好的";
			}else if(3==state){//已完成
				remarks = "亲，您的订单已完成交易";
			}else if(4==state){//待付款
				remarks = "亲，您的订单等待付款";
				alert = "您已验票成功，稍后去上传一下您的付款凭证吧！";
				ok = "好的";
			}else if(5==state){//已付款
				remarks = "亲，该订单您已付款";
			}
			task.setRemarks(remarks);
			task.setCreateTime(new Date());
			task.setState(state);
			
			Discountrecord record = null;
			DiscountrecordTask recordTask = null;
			if(order.getDcrdId()!=null && flag){
				record = discountrecordService.getById(order.getDcrdId());
				if(record!=null){
					if(state==-2){//APP2.2主动拒绝（企业订单继续变为待确认，再次派单）
						record.setOrderflag(1);
					}else{
						record.setOrderflag(state);//状态基本对应（除已付款：已付款已走其他接口）
					}
					recordTask = new DiscountrecordTask();
					recordTask.setDiscountrecordId(record.getId());
					recordTask.setCreateTime(new Date());
					String desc = "";
					if(org.apache.commons.lang.StringUtils.isNotBlank(reason)){
						desc = reason;
					}else if(0==state){//无效订单
						desc = "亲，您的订单已被取消";
					}else if(1==state){//待确认
						desc = "亲，您提交的订单已被客服确认";
					}else if(2==state){//验票中
						desc = "亲，您的订单正在验票中";
					}else if(3==state){//已完成
						desc = "亲，您的订单已完成交易";
					}else if(4==state){//待付款
						desc = "亲，您的订单待收款";
					}else if(-2==state){//对应APP2.2机构取消后 重新派单
						desc = "亲，您的订单已被取消，将尽快派给其他机构";
					}
					recordTask.setOperatorDesc(desc);
					recordTask.setOperatorType(OperatorType.MEMBER);
					if(-2==state){
						recordTask.setOperator(Operator.UNCONFIRM);//待确认APP2.2
					}else{
						recordTask.setOperator(Operator.valueOf(state));//状态
					}
				}
			}
			if(order.getOrgId()!=null){//存储操作人主键
				Org org = orgService.getById(order.getOrgId());
				if(org!=null){
					task.setOperatorId(org.getMemberId());
					if(recordTask!=null)recordTask.setOperatorId(org.getMemberId());
				}
			}
			distributeOrderService.updateDisAndSaveTask(order,task,record,recordTask);
			//if(-2==state)paidanService.updatePaidan(record.getId(), paidanService.getConfigXML());//APP2.2派单（机构主动取消）[暂时不系统派单]
			
			if(2==state){//待验票
				Org temp = orgService.getById(order.getOrgId());
				if(temp!=null && temp.getMemberId()!=null){
					doPushJob(temp.getMemberId(), state, dtboId, Type.DISTRIBUTEORDER,null);//机构
				}
				doPushJob(record.getMemberid(), state, record.getId(), Type.DISCOUNTRECORD,null);//企业
			}
			
			result.put("response", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		result.put("alert", alert);
		result.put("ok", ok);
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 有误拒绝（企业端用户拒绝）
	 * @author WKX
	 * @param id
	 * @param model
	 * @since 2016年4月6日 下午5:51:56
	 */
	@RequestMapping("noReceive/push")
	public String noReceive(Integer id,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			DistributeOrder order = distributeOrderService.getByDcrdId(id);
			if(order!=null && order.getOrgId()!=null){
				Org temp = orgService.getById(order.getOrgId());
				if(temp!=null && temp.getMemberId()!=null){
					String des = "您所上传的付款凭证被出票方拒绝，请重新上传。";
					doPushJob(temp.getMemberId(), 5, order.getId(), Type.DISTRIBUTEORDER,des);//机构
				}
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
	 * 分页显示机构订单（待结单）
	 * @author GXW
	 * @param form
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @throws IOException
	 */
	@RequestMapping("list/wait")
	public String listWait(DistributeOrderForm form, Integer pageIndex, Integer pageSize, Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (pageIndex == null)pageIndex = 0;
		if (pageSize == null)pageSize = 10;
		try {
			if (form != null && form.getOrgId() != null) {
				List<Map<String, Object>> orderList = distributeOrderService.getAssignedByOrgId(form.getOrgId(),null);
				Iterator<Map<String, Object>> i = orderList.iterator();
				
				while (i.hasNext()) {
					Map<String, Object> order = i.next();
					
					Object time = order.get("create_time");
					if(time!=null){
						int timer = DateUtil.calSeconds(DateUtil.parser(time.toString(), DateUtil.FORMART), new Date());
						order.put("timer", 600-timer);
					}
					order.put("myTime", 20);
					
					Float bail = 0f;//加大小票（大票：30；小票：10）
					if (order.get("allmoney") != null && Double.valueOf(order.get("allmoney").toString()) > 500) {
						bail += 30;
					} else {
						bail += 10;
					}
					bail += getDeposit(form.getOrgId());//认证、完成订单、拒绝订单（金额）
					order.put("bail", bail);
				}
				result.put("data", orderList);
				result.put("size", orderList.size());
				result.put("response", "success");
				result.put("msg", "获取成功");
			} else {
				result.put("response", "failed");
				result.put("msg", "获取失败");
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}

	/**
	 * 拒绝系统派单
	 * @author GXW
	 * @param form
	 * @param model
	 * @since 2016年5月19日
	 */
	@RequestMapping("reject")
	public String reject(DistributeOrderForm form, String reason, Integer memberId, Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			DistributeOrder order = distributeOrderService.getById(form.getId());
			Discountrecord dic = discountrecordService.getById(order.getDcrdId());
			order.setState(-2);//机构端拒绝
			DistributeOrderTask task = new DistributeOrderTask();
			task.setDtboId(order.getId());
			task.setCreateTime(new Date());
			task.setRemarks(reason);//拒绝理由
			task.setState(-2);
			task.setOperatorId(memberId);
			task.setOperatorType(OperatorType.MEMBER);
			Map<String, Object> res = paidanService.updatePaidanAndOrderState(order, task, dic);// 重新派单
			if("success".equals(res.get("response")))
				result=res;
			else {
				result.put("msg", "派单失败!"+res.get("msg"));
				result.put("response", "error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "failed");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}

	/**
	 * 接受系统派单
	 * @author GXW
	 * @param form
	 * @param model
	 * @since 2016年5月19日
	 * @EDIT WKX 2017-02-14 不支付保证金
	 */
	@RequestMapping("accept")
	public String accept(DistributeOrderForm form, Integer memberId, Integer payWay, Float bail, Float version, Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		String ps = Constant.properties.getProperty("VERSION_ALERT");//@WKX EDIT版本过期提示[因之前版本强制更新有问题，现做提示处理]
    	if("on".equals(ps) && version!=null && version<=2.32F){
    		result.put("response", "failed");
			result.put("msg", "当前版本已停用，请下载最新版本票据管家APP。");
			model.addAttribute("retValue",JacksonUtil.objToJson(result));
			return "ajax";
    	}
		try {
			DistributeOrder order = distributeOrderService.getById(form.getId());
			if(order==null || order.getState()!=1)throw new Exception("数据已过期");
			
			if(bail==null || bail==0)payWay = null;//当支付金额为0则无支付方式
			String prefix = payWay == null ? "N" : payWay == 0 ? "A" : payWay == 1 ? "W" : payWay == 2 ? "U":payWay == 3 ? "K":payWay == 4 ? "B":"N";//N代表无支付方式的
			String bnsNo = discountrecordService.randomBnsNo(prefix);//生成bnsNo
			
			order.setDepositState(0);//初始状态（待支付）
			order.setPayWay(payWay);
			order.setBnsNo(bnsNo);
			if(bail!=null)order.setDeposit(bail);
			
			if(form.getTodoorPrice()!=null)order.setTodoorPrice(form.getTodoorPrice());
			if(form.getTodoorTime()!=null)order.setTodoorTime(form.getTodoorTime());
			if(form.getLongitude()!=null)order.setLongitude(form.getLongitude());
			if(form.getLatitude()!=null)order.setLatitude(form.getLatitude());
			
			if(form.getWay()!=null)order.setWay(form.getWay());
			if(org.apache.commons.lang.StringUtils.isNotBlank(form.getPrice()))order.setPrice(form.getPrice());
			if(org.apache.commons.lang.StringUtils.isNotBlank(form.getPrice1()))order.setPrice1(form.getPrice1());
			if(org.apache.commons.lang.StringUtils.isNotBlank(form.getPrice2()))order.setPrice2(form.getPrice2());
			
			if(order.getDeposit()==null || order.getDeposit()==0){//确认订单（临时方案：无支付保证金）
				distributeOrderService.updateAndNoPayRecord(order);
			}else{
				distributeOrderService.updateDistributeOrder(order);
			}
			
			result.put("jyh", order.getJyh());
			result.put("bnsNo", order.getBnsNo());
			result.put("payWay", order.getPayWay());
			result.put("bail", order.getDeposit());
			result.put("response", "success");
			result.put("msg", "加载成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "加载失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 机构端保证金列表
	 * @author cx
	 * @throws Exception 
	 * @since 2016年4月6日 下午5:51:56
	 */
	@RequestMapping("jigoubaozhengjin")
	public String jigouBaoZhengJin(Integer memberId,Model model) throws Exception{
		Org org = orgService.getByMemberId(memberId);
		List<Map<String,Object>> list = distributeOrderService.getDeposit(org.getId());
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
	 * 更新订单（APP2.2取消订单）-2
	 * @author WKX
	 * @param dtboId 订单主键
	 * @param state 操作标示（设定为什么状态）
	 * @param reason 备注信息（取消的原因）
	 * @param currentState 当前页面订单状态（防止用户用老数据操作）
	 * @param model
	 * @since 2016年5月26日 下午9:33:16
	 */
	@RequestMapping("update/state/tominus2")
	public String updateTominus2(Integer dtboId,Integer state,String reason,Integer currentState,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		boolean flag = true;
		String alert = "操作成功";
		String ok = "确定";
		try {
			//step.1 数据时效性基本校验
			DistributeOrder order = distributeOrderService.getById(dtboId);
			if(order==null || state==null)throw new Exception("数据异常");
			if(currentState!=null && order.getState()!=currentState)throw new Exception("数据已过期");
			Discountrecord disc = discountrecordService.getById(order.getDcrdId());
			if(disc==null || disc.getOrderflag()==0)throw new Exception("数据已过期");

			if(order.getState()==1 && state==-2){//待确认（拒绝订单）
				alert = "您已拒绝该笔订单，我们将不会再次派送该笔订单给您！";
				ok = "好，我知道了";
			}else if(order.getState()==2 && state==-2){//验票中（拒绝订单）
				alert = "您已拒绝该笔订单，我们将不会再次派送该笔订单给您！";
				ok = "好，我知道了";
			}
			order.setState(state);
			
			DistributeOrderTask task = new DistributeOrderTask();
			task.setDtboId(order.getId());
			task.setOperatorType(OperatorType.MEMBER);
			String remarks = "";
			if(org.apache.commons.lang.StringUtils.isNotBlank(reason)){
				remarks = reason;
			}else{//无效订单
				remarks = "亲，您已取消该订单";
			}
			task.setRemarks(remarks);
			task.setCreateTime(new Date());
			task.setState(state);
			task.setType(0);//银票
			
			Discountrecord record = null;
			DiscountrecordTask recordTask = null;
			if(order.getDcrdId()!=null && flag){
				record = discountrecordService.getById(order.getDcrdId());
				if(record!=null){
					record.setOrderflag(1);//待确认
					recordTask = new DiscountrecordTask();
					recordTask.setDiscountrecordId(record.getId());
					recordTask.setCreateTime(new Date());
					String desc = "";
					if(org.apache.commons.lang.StringUtils.isNotBlank(reason)){
						desc = "亲，您的订单已被取消，将尽快派给其他机构【"+reason+"】";
					}else{//无效订单
						desc = "亲，您的订单已被取消，将尽快派给其他机构";
					}
					recordTask.setOperatorDesc(desc);
					recordTask.setOperatorType(OperatorType.MEMBER);
					recordTask.setOperator(Operator.UNCONFIRM);//待确认
				}
			}
			
			if(order.getOrgId()!=null){//存储操作人主键
				Org org = orgService.getById(order.getOrgId());
				if(org!=null){
					task.setOperatorId(org.getMemberId());
					if(recordTask!=null)recordTask.setOperatorId(org.getMemberId());
				}
			}
			distributeOrderService.updateDisAndSaveTask(order,task,record,recordTask);
			//paidanService.updatePaidan(record.getId(), paidanService.getConfigXML());//APP2.2派单（机构主动取消）[暂不系统派单]
			
			result.put("response", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		result.put("alert", alert);
		result.put("ok", ok);
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 更新订单（APP2.2准备验票）2
	 * @author WKX
	 * @param dtboId 订单主键
	 * @param state 操作标示（设定为什么状态）
	 * @param reason 备注信息（取消的原因）
	 * @param currentState 当前页面订单状态（防止用户用老数据操作）
	 * @param model
	 * @since 2016年5月27日 上午9:59:27
	 */
	@RequestMapping("update/state/to2")
	public String updateTo2(Integer dtboId,Integer state,String reason,Integer currentState,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		boolean flag = true;
		String alert = "操作成功";
		String ok = "确定";
		try {
			//step.1 数据时效性基本校验
			DistributeOrder order = distributeOrderService.getById(dtboId);
			if(order==null || state==null)throw new Exception("数据异常");
			if(currentState!=null && order.getState()!=currentState)throw new Exception("数据已过期");
			Discountrecord disc = discountrecordService.getById(order.getDcrdId());
			if(disc==null || disc.getOrderflag()==0)throw new Exception("数据已过期");

			order.setState(state);
			
			DistributeOrderTask task = new DistributeOrderTask();
			task.setDtboId(order.getId());
			task.setOperatorType(OperatorType.MEMBER);
			String remarks = "";
			if(org.apache.commons.lang.StringUtils.isNotBlank(reason)){
				remarks = reason;
			}else {//验票中
				remarks = "亲，您的订单正在验票中";
				alert = "亲，您准备去该持票企业进行验票，请认真核实订单信息呦！";
				ok = "好的";
			}
			task.setRemarks(remarks);
			task.setCreateTime(new Date());
			task.setState(state);
			
			Discountrecord record = null;
			DiscountrecordTask recordTask = null;
			if(order.getDcrdId()!=null && flag){
				record = discountrecordService.getById(order.getDcrdId());
				if(record!=null){
					record.setOrderflag(state);//验票中
					recordTask = new DiscountrecordTask();
					recordTask.setDiscountrecordId(record.getId());
					recordTask.setCreateTime(new Date());
					String desc = "";
					if(org.apache.commons.lang.StringUtils.isNotBlank(reason)){
						desc = reason;
					}else{//验票中
						desc = "亲，您的订单正在验票中";
					}
					recordTask.setOperatorDesc(desc);
					recordTask.setOperatorType(OperatorType.MEMBER);
					recordTask.setOperator(Operator.valueOf(state));//状态
				}
			}
			if(order.getOrgId()!=null){//存储操作人主键
				Org org = orgService.getById(order.getOrgId());
				if(org!=null){
					task.setOperatorId(org.getMemberId());
					if(recordTask!=null)recordTask.setOperatorId(org.getMemberId());
				}
			}
			distributeOrderService.updateDisAndSaveTask(order,task,record,recordTask);
			if(2==state){//待验票
				Org temp = orgService.getById(order.getOrgId());
				if(temp!=null && temp.getMemberId()!=null){
					doPushJob(temp.getMemberId(), state, dtboId, Type.DISTRIBUTEORDER,null);//机构
				}
				doPushJob(record.getMemberid(), state, record.getId(), Type.DISCOUNTRECORD,null);//企业
			}
			
			result.put("response", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		result.put("alert", alert);
		result.put("ok", ok);
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 更新订单（APP2.2待付款）4
	 * @author WKX
	 * @param dtboId 订单主键
	 * @param state 操作标示（设定为什么状态）
	 * @param reason 备注信息（取消的原因）
	 * @param currentState 当前页面订单状态（防止用户用老数据操作）
	 * @param model
	 * @since 2016年5月27日 上午9:59:27
	 */
	@RequestMapping("update/state/to4")
	public String updateTo4(Integer dtboId,Integer state,String reason,Integer currentState,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		boolean flag = true;
		String alert = "操作成功";
		String ok = "确定";
		try {
			//step.1 数据时效性基本校验
			DistributeOrder order = distributeOrderService.getById(dtboId);
			if(order==null || state==null)throw new Exception("数据异常");
			if(currentState!=null && order.getState()!=currentState)throw new Exception("数据已过期");
			Discountrecord disc = discountrecordService.getById(order.getDcrdId());
			if(disc==null || disc.getOrderflag()==0)throw new Exception("数据已过期");

			order.setState(state);
			
			DistributeOrderTask task = new DistributeOrderTask();
			task.setDtboId(order.getId());
			task.setOperatorType(OperatorType.MEMBER);
			String remarks = "";
			if(org.apache.commons.lang.StringUtils.isNotBlank(reason)){
				remarks = reason;
			}else {//验票中
				remarks = "亲，您的订单等待付款";
				alert = "您已验票成功，稍后去上传一下您的付款凭证吧！";
				ok = "好的";
			}
			task.setRemarks(remarks);
			task.setCreateTime(new Date());
			task.setState(state);
			
			Discountrecord record = null;
			DiscountrecordTask recordTask = null;
			if(order.getDcrdId()!=null && flag){
				record = discountrecordService.getById(order.getDcrdId());
				if(record!=null){
					record.setOrderflag(state);//验票中
					recordTask = new DiscountrecordTask();
					recordTask.setDiscountrecordId(record.getId());
					recordTask.setCreateTime(new Date());
					String desc = "";
					if(org.apache.commons.lang.StringUtils.isNotBlank(reason)){
						desc = reason;
					}else{//验票中
						desc = "亲，您的订单待收款";
					}
					recordTask.setOperatorDesc(desc);
					recordTask.setOperatorType(OperatorType.MEMBER);
					recordTask.setOperator(Operator.valueOf(state));//状态
				}
			}
			if(order.getOrgId()!=null){//存储操作人主键
				Org org = orgService.getById(order.getOrgId());
				if(org!=null){
					task.setOperatorId(org.getMemberId());
					if(recordTask!=null)recordTask.setOperatorId(org.getMemberId());
				}
			}
			distributeOrderService.updateDisAndSaveTask(order,task,record,recordTask);
			result.put("response", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		result.put("alert", alert);
		result.put("ok", ok);
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 更新订单（APP2.2待付款）4
	 * @author WKX
	 * @param dtboId 订单主键
	 * @param state 操作标示（设定为什么状态）
	 * @param reason 备注信息（取消的原因）
	 * @param currentState 当前页面订单状态（防止用户用老数据操作）
	 * @param model
	 * @since 2016年5月27日 上午9:59:27
	 */
	@RequestMapping("update/state/tominus1")
	public String updateTominus1(Integer dtboId,Integer state,String reason,Integer currentState,String[] images,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		String alert = "操作成功";
		String ok = "确定";
		try {
			//step.1 数据时效性基本校验
			DistributeOrder order = distributeOrderService.getById(dtboId);
			if(order==null || state==null)throw new Exception("数据异常");
			if(currentState!=null && order.getState()!=currentState)throw new Exception("数据已过期");
			Discountrecord disc = discountrecordService.getById(order.getDcrdId());
			if(disc==null || disc.getOrderflag()==0)throw new Exception("数据已过期");

			order.setState(state);
			DistributeOrderTask task = new DistributeOrderTask();
			task.setDtboId(order.getId());
			task.setOperatorType(OperatorType.MEMBER);
			String remarks = "";
			if(org.apache.commons.lang.StringUtils.isNotBlank(reason)){
				remarks = reason;
				order.setLostCause(reason);//验票失败原因
			}else {//验票中
				remarks = "亲，该笔订单验票失败";
				alert = "亲，该笔订单验票失败，客服将核实订单状态并且我们将不会再次派送该笔订单给您！";
				ok = "好，我知道了";
			}
			task.setRemarks(remarks);
			task.setCreateTime(new Date());
			task.setState(state);
			
			if(order.getOrgId()!=null){//存储操作人主键
				Org org = orgService.getById(order.getOrgId());
				if(org!=null){
					task.setOperatorId(org.getMemberId());
				}
			}
			List<Image> list = null;
			if(images!=null && images.length>0){//上传的图片可以保存
				String temp = Constant.properties.getProperty("uploadpath") + Constant.TEMP_PATH;//保存目录包名的绝对路径
				String uploadPath = Constant.properties.getProperty("uploadpath") + Constant.UPLOAD_SRC;//保存目录包名的绝对路径
				//检查目录
				File uploadDir = new File(uploadPath);
				if(!uploadDir.isDirectory()){
					uploadDir.mkdirs();
				}
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
				String extpath = df.format(new Date());
				list = new ArrayList<Image>();
				Image image = null;
				for (String img:images) {
					image = new Image();
					if (!"null".equalsIgnoreCase(img)) {
						String path = img;
						if(img.indexOf(Constant.TEMP_PATH)!=-1){//临时文件夹 的文件 转到 保存目录中(如果不是临时的图片则直接引用)
							String pString = uploadPath + extpath;
							String tString = temp + "image" + File.separator + extpath;
							if (StringUtils.hasText(img)) {
								tString += img.substring(img.lastIndexOf("/"), img.length());
								FileUtil.moveFile(tString, pString);
							}
							path = Constant.UPLOAD_SRC + extpath +img.substring(img.lastIndexOf("/"), img.length());
						}
						image.setImgPath(path);
						list.add(image);
					}
				}
			}
			distributeOrderService.updateDisAndImage(order, task, list,disc);//验票失败（不对企业订单做处理，只在审核后）并存凭证图片
			pushAndSend(order.getOrgId(), order.getId(), Type.DISTRIBUTEORDER, "无效订单", "我们收到了您出示的证明出票方的票据有问题的图片，我们将尽快审核并给您回复，请耐心等待。", null);
			result.put("response", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		result.put("alert", alert);
		result.put("ok", ok);
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 根据主键获取订单评价详情（APP2.2）
	 * @author WKX
	 * @param dtboId 机构订单主键
	 * @param model
	 * @since 2016年5月30日 上午10:18:01
	 */
	@RequestMapping("/comments/get")
	public String getCommentsByDtboId(Integer dtboId,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			List<Map<String,Object>> list = commentsService.getInfoByDtboId(dtboId);
			if(list!=null && list.size()>0){
				Map<String,Object> comments = list.get(0);
				Object operatorId = comments.get("operator_id");
				if(operatorId!=null){//获取机构信息
					Map<String,Object> company = orgInfoService.getByMemberIdAndType(Integer.valueOf(operatorId.toString()), 1);
					if(company!=null && company.get("company")!=null){
						comments.put("company", company.get("company"));
					}else{
						Member member = memberService.getById(Integer.valueOf(operatorId.toString()));
						if(member!=null && org.apache.commons.lang.StringUtils.isNotBlank(member.getUsername())){
							comments.put("company", member.getUsername());
						}else{
							comments.put("company", member.getMobile());
						}
					}
				}
				result.put("data", comments);
			}
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
	 * 机构端完成保证金支付
	 * @author GXW
	 * @param dtboId 机构订单主键
	 * @param bail 保证金金额
	 * @param payWay 支付方式
	 * @param model
	 * @since 2016年6月1日
	 */
	@RequestMapping("pay/completed")
	public String payCompleted(Integer dtboId,Integer state,Float bail,Integer payWay,String des,Float version,Model model) {
		payLog.info("接单支付：进入方法payCompleted...订单主键："+dtboId);
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			DistributeOrder order = distributeOrderService.getById(dtboId);
			if (order != null && order.getState() == 1) {
				if (0 == state){
					order.setDepositState(1);// 初始状态（已支付）
					try {
						Map<String,Object> cbPay = AlipayUtil.tradeQuery(order.getBnsNo());
						if(cbPay!=null){
							Object trade_no = cbPay.get("trade_no");
							Object buyer_email = cbPay.get("buyer_email");
							if(trade_no!=null)order.setJyh(trade_no.toString());//保存交易号
							if(buyer_email!=null)order.setCard(buyer_email.toString());//账号
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					pushAndSend(order.getOrgId(), order.getId(), Type.DISTRIBUTEORDER, "已支付", "亲，您的订单保证金已支付成功，还请赶快处理订单吧！", "SMS_10611169");
				}else{
					pushAndSend(order.getOrgId(), order.getId(), Type.DISTRIBUTEORDER, des, "亲，您的订单保证金未完成支付，快去我的订单完成支付吧！", "SMS_10637201");
				}
				if(bail!=null)order.setDeposit(bail);//保证金
				if(payWay!=null)order.setPayWay(payWay);//支付方式
				distributeOrderService.updateAndPayRecord(order, state, des,version);
				payLog.info("接单支付：执行完方法payCompleted...机构主键："+order.getOrgId()+"订单主键："+order.getId());
			}
			result.put("response", "success");
			result.put("msg", des);
		} catch (Exception e) {
			payLog.error("接单支付：更新支付状态异常savePayRecord...订单主键："+dtboId+"支付状态："+des);
			result.put("response", "failed");
			result.put("msg", "网络异常");
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 推送消息、保存消息、发送短信
	 * @author WKX
	 * @param memberId 用户主键
	 * @param fkId 外键
	 * @param type 类型
	 * @param alert 标题
	 * @param des 描述内容
	 * @param smsCode 短信编码
	 * @since 2016年6月15日 下午9:22:08
	 */
	private void pushAndSend(Integer orgId,Integer fkId,Type type,String alert,String des,String smsCode){
		try {
			Org org = orgService.getById(orgId);
			if(org!=null){
				Member member = memberService.getById(org.getMemberId());
				if(member!=null){
					Systeminfo systeminfo = new Systeminfo();
					systeminfo.setMemberId(member.getId());
					systeminfo.setType(type);
					systeminfo.setAlert(alert);
					systeminfo.setContent(des);
					systeminfo.setDiscountrecordId(fkId);//外键（对应类型）
					systeminfo.setCreateTime(new Date());
					systeminfoService.addSysteminfo(systeminfo);
					
					if(StringUtils.hasText(smsCode)){
						Map<String,String> param = new HashMap<String, String>();
						SendMessagesUtil.sendMessage(member.getMobile(), smsCode, param);
					}
					
					JPushUtil.doPushJob(member.getMobile(), type, des);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据倒计时 失效 订单
	 * @author WKX
	 * @param dtboId
	 * @param model
	 * @since 2016年6月16日 下午8:35:35
	 */
	@RequestMapping("/canelDist")
	public String cancelDist(Integer dtboId,Model model){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(dtboId!=null)throw new Exception();
			DistributeOrder dist = distributeOrderService.getById(dtboId);
			if(dist!=null && dist.getState()==1){//待支付状态
				dist.setState(0);
				distributeOrderService.saveModel(dist);
			}else{
				throw new Exception();
			}
			result.put("data", dtboId);
			result.put("response", "success");
		} catch (Exception e) {
			result.put("response", "failed");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 根据机构主键获取保证金额
	 * 【加是否认证金额（认证：0；未认证：30）】
	 * 【加上月是否已完成订单金额（有已完成：10；无已完成：30）】
	 * 【加上月是否拒绝订单金额（有：30；无：0）】
	 * @author WKX
	 * @param orgId 机构主键
	 * @throws ParseException
	 * @since 2016年8月26日 下午2:20:21
	 */
	private Integer getDeposit(Integer orgId) throws ParseException{
		int certificate = 0;// 认证
		int finish = 0;// 完成
		int reject = 0;// 拒绝
		// 认证过：0；未认证：30
		Map<String, Object> info = orgInfoService.getByOrgIdAndType(orgId, 1);
		if (info != null && info.get("state") != null) {
			if (2 != Integer.valueOf(info.get("state").toString())) {
				certificate += 30;
			}
		} else {
			certificate += 0;
		}
		// 上月有已完成订单：10；上月无已完成订单：30
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		String end = DateUtil.formart(calendar.getTime(), DateUtil.FORMART3);
		Date e = DateUtil.parser(end + " 23:59:59", DateUtil.FORMART);

		calendar.set(Calendar.DAY_OF_MONTH, 1);
		String start = DateUtil.formart(calendar.getTime(), DateUtil.FORMART3);
		Date s;
		s = DateUtil.parser(start + " 00:00:00", DateUtil.FORMART);

		Long amount = distributeOrderService.countbyOrdertime(orgId, s.getTime(), e.getTime(), 3);// 已完成
		if (amount != null && amount > 0) {
			finish += 10;
		} else {
			finish += 30;
		}
		// 上月有拒绝订单：30；上月无拒绝订单：0
		Long bmount = distributeOrderService.countbyOrdertime(orgId, s.getTime(), e.getTime(), -2);// 拒绝
		if (bmount != null && bmount > 0) {
			reject += 30;
		}
		Integer call = certificate + finish + reject;
		return call;
	}
	
	/**
	 * 查询银票详情（待接单详情）
	 * @param dtboId
	 * @param model
	 * @author WKX
	 */
	@RequestMapping("get/wait")
	public String getWaitDtbo(Integer dtboId,Float version,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			Map<String,Object> dtbo = distributeOrderService.getInfoById(dtboId);
			Object time = dtbo.get("create_time");
			Float deposit = 0f;//加大小票（大票：30；小票：10）
			
			if (dtbo.get("allmoney") != null && Double.valueOf(dtbo.get("allmoney").toString()) > 500) {
				deposit += 30;
			} else {
				deposit += 10;
			}
			Object orgId = dtbo.get("org_id");
			deposit += getDeposit(Integer.valueOf(orgId.toString()));//认证、完成订单、拒绝订单（金额）
			dtbo.put("deposit", deposit);//保证金
			if(time!=null){
				int timer = DateUtil.calSeconds(DateUtil.parser(time.toString(), DateUtil.FORMART), new Date());
				dtbo.put("timer", 600-timer);
			}
			if(version!=null && version == 2.31F){//2.31版本保证金支付暂时关闭
				dtbo.put("deposit", 0);//保证金
			}
			result.put("data", dtbo);
			result.put("response", "success");
			result.put("msg", "查询成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "查询失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 拒绝订单（验票失败）
	 * @author WKX
	 * @param dtboId 接单主键
	 * @param cancel1 拒绝理由
	 * @param cancel2 验票失败理由
	 * @param reason 拒绝验票失败
	 * @param cancelCause 取消原因
	 * @param currentState 当前订单状态
	 * @param images 图片
	 * @param model
	 * @since 2016年8月30日 上午9:29:15
	 */
	@RequestMapping("cancel")
	public String cancel(Integer dtboId,Integer cancel1,Integer cancel2,String reason,String lostCause,Integer currentState,String[]images,Integer memberId,Float version,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		
		String ps = Constant.properties.getProperty("VERSION_ALERT");//@WKX EDIT版本过期提示[因之前版本强制更新有问题，现做提示处理]
    	if("on".equals(ps) && version!=null && version<=2.32F){
    		result.put("response", "failed");
			result.put("msg", "当前版本已停用，请下载最新版本票据管家APP。");
			model.addAttribute("retValue",JacksonUtil.objToJson(result));
			return "ajax";
    	}
		
		String alert = "操作成功";
		String ok = "确定";
		try {
			DistributeOrder dist = distributeOrderService.getById(dtboId);
			if(dist==null || currentState==null)throw new Exception("数据异常");
			if(dist.getState()!=currentState)throw new Exception("数据已过期");
			Discountrecord disc = discountrecordService.getById(dist.getDcrdId());
			if(disc==null || disc.getOrderflag()==0)throw new Exception("数据已过期");
			
			if(org.apache.commons.lang.StringUtils.isBlank(lostCause))lostCause = reason;
			dist.setCancel1(cancel1);
			dist.setCancel2(cancel2);
			dist.setLostCause(lostCause);
			
			if(dist.getDepositState()!=null && dist.getDepositState()==1 && cancel1!=null && cancel1==6){//机构取消订单原因为其他时需要后台审核
				RefundExamine refundExamine = new RefundExamine();
				refundExamine.setDcrdId(disc.getId());
				refundExamine.setDtboId(dist.getId());
				refundExamine.setCancelRole(2);//机构端拒绝
				refundExamine.setCause(lostCause);//原因
				dist.setState(-2);//机构端拒绝
				disc.setOrderflag(0);//无效订单
				refundExamineService.saveModelAndUpdateDis(refundExamine, disc, dist);
			}else if(currentState==1 && dist.getDepositState()==0){//接单大厅拒绝订单
				dist.setState(-2);//机构端拒绝
				DistributeOrderTask task = new DistributeOrderTask();
				task.setDtboId(dist.getId());
				task.setCreateTime(new Date());
				task.setRemarks(reason);//拒绝理由
				task.setState(-2);
				task.setOperatorId(memberId);
				task.setOperatorType(OperatorType.MEMBER);
				task.setType(0);//银票
				paidanService.updatePaidanAndOrderState(dist, task, disc);// 重新派单
			}else{
				DistributeOrderTask task = new DistributeOrderTask();
				task.setDtboId(dist.getId());
				task.setOperatorType(OperatorType.MEMBER);
				String remarks = "";
				if(org.apache.commons.lang.StringUtils.isNotBlank(lostCause)){
					remarks = lostCause;
					dist.setLostCause(lostCause);//验票失败原因
				}else {//验票中
					remarks = "亲，该笔订单验票失败";
					alert = "亲，该笔订单验票失败，客服将核实订单状态并且我们将不会再次派送该笔订单给您！";
					ok = "好，我知道了";
				}
				task.setRemarks(remarks);
				task.setCreateTime(new Date());
				task.setState(-1);
				
				if(dist.getOrgId()!=null){//存储操作人主键
					Org org = orgService.getById(dist.getOrgId());
					if(org!=null){
						task.setOperatorId(org.getMemberId());
					}
				}
				List<Image> list = null;
				if(images!=null && images.length>0){//上传的图片可以保存
					String temp = Constant.properties.getProperty("uploadpath") + Constant.TEMP_PATH;//保存目录包名的绝对路径
					String uploadPath = Constant.properties.getProperty("uploadpath") + Constant.UPLOAD_SRC;//保存目录包名的绝对路径
					//检查目录
					File uploadDir = new File(uploadPath);
					if(!uploadDir.isDirectory()){
						uploadDir.mkdirs();
					}
					SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
					String extpath = df.format(new Date());
					list = new ArrayList<Image>();
					Image image = null;
					for (String img:images) {
						image = new Image();
						if (!"null".equalsIgnoreCase(img)) {
							String path = img;
							if(img.indexOf(Constant.TEMP_PATH)!=-1){//临时文件夹 的文件 转到 保存目录中(如果不是临时的图片则直接引用)
								String pString = uploadPath + extpath;
								String tString = temp + "image" + File.separator + extpath;
								if (StringUtils.hasText(img)) {
									tString += img.substring(img.lastIndexOf("/"), img.length());
									FileUtil.moveFile(tString, pString);
								}
								path = Constant.UPLOAD_SRC + extpath +img.substring(img.lastIndexOf("/"), img.length());
							}
							image.setImgPath(path);
							list.add(image);
						}
					}
				}
				if(3 == cancel1){
					dist.setState(-1);
					distributeOrderService.updateDisAndImage(dist, task, list,disc);//验票失败（不对企业订单做处理，只在审核后）并存凭证图片
					pushAndSend(dist.getOrgId(), dist.getId(), Type.DISTRIBUTEORDER, "无效订单", "我们收到了您出示的证明出票方的票据有问题，我们将尽快审核并给您回复，请耐心等待。", null);
				}else{
					disc.setOrderflag(1);//企业订单设置成交易中
					dist.setState(-2);//机构订单设置无效
					distributeOrderService.updateDisAndSaveTask(dist, task, disc, null);
				}
			}
			result.put("response", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		result.put("alert", alert);
		result.put("ok", ok);
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 银票接单支付
	 * @author WKX
	 * @param orderAmount 金额
	 * @param memberId 用户主键
	 * @param orderId 商户订单号
	 * @param model
	 * @throws Exception
	 * @since 2017年3月10日 下午3:51:09
	 */
	@RequestMapping("pay")
	public String toPay(String orderAmount,String memberId,String orderId,Model model) throws Exception{
		String path_cb = Constant.properties.getProperty("BILL99URL_CB");
		String path_page = Constant.properties.getProperty("BILL99URL_PAGE");
		Map<String, String> paras = new LinkedHashMap<String, String>();

		if(org.apache.commons.lang.StringUtils.isBlank(orderAmount))orderAmount = "30";//30元
		paras.put("orderId", orderId);//商户订单号
		paras.put("payerId", memberId);//用户标识
		paras.put("orderAmount", (int)(Double.valueOf(orderAmount)*100)+"");//订单金额（分）
//		paras.put("orderAmount", "100");//订单金额（分）
		paras.put("orderTime", new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()));//订单提交时间
		paras.put("productName", "接单保证金");//产品名称
		paras.put("productNum", "1");//数量
		paras.put("bgUrl", path_cb + "/app/distributeOrder/pay/cb");//后台回调
		paras.put("pageUrl", path_page + "/app/distributeOrder/pay/cb/page");//回调页面
		model.addAttribute("retValue",Bill99Util.buildRequestPayForWap(paras));
		return "ajax";
	}
	
	/**
	 * 接单支付回调后台（同支付宝一样，隔一段时间也会回调一次）
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
	@RequestMapping("pay/cb")
	public @ResponseBody String cbPay(String merchantAcctId,String bankId,String orderId,String orderTime,String orderAmount,String bindCard,String bindMobile,String dealId,String bankDealId,String fee,String payResult,Model model) throws Exception{
		String path_page = Constant.properties.getProperty("BILL99URL_PAGE");
		String page = path_page + "/app/distributeOrder/pay/cb/page";
		payLog.info("接单支付：进入方法cbPay...订单商户号："+orderId);
		Map<String, Object> result = new HashMap<String, Object>();
		String des = "10".equals(payResult) ? "支付成功" : "支付失败";//10支付成功、11支付失败
		try {
			DistributeOrder order = distributeOrderService.getByBnsno(orderId);
			if (order != null && order.getState() == 1) {
				if("10".equals(payResult)){
					Org activityorg = orgService.getById(order.getOrgId());
					List<IntegraltradingDetail> listactivity = activityService.getlistActivity("接单支付",DateUtil.formart(new Date(), DateUtil.FORMART3),activityorg.getMemberId());
					if(listactivity != null && listactivity.size()<5){
						activityService.timingIntegral(activityorg.getMemberId(), 20, "接单支付", null);
					}
					order.setDepositState(1);// 初始状态（已支付）
					order.setJyh(dealId);//保存交易号（快钱交易号）
					order.setCard(bindCard);//账号（部分卡号）
					pushAndSend(order.getOrgId(), order.getId(), Type.DISTRIBUTEORDER, "已支付", "亲，您的订单保证金已支付成功，还请赶快处理订单吧！", "SMS_10611169");
				}else{
					pushAndSend(order.getOrgId(), order.getId(), Type.DISTRIBUTEORDER, "支付失败", "亲，您的订单保证金未完成支付，快去我的订单完成支付吧！", "SMS_10637201");
				}
				order.setPayWay(3);//支付方式（快钱）
				distributeOrderService.updateAndPayRecord(order, Integer.valueOf(payResult), des,null);
				payLog.info("接单支付：执行完方法cbPay...机构主键："+order.getOrgId()+"订单主键："+order.getId());
			}
			result.put("response", "success");
			result.put("msg", des);
		} catch (Exception e) {
			payLog.error("接单支付：更新支付状态异常savePayRecord...订单商户号："+orderId+"支付状态："+des);
			result.put("response", "failed");
			result.put("msg", "网络异常");
			e.printStackTrace();
		}
		return "<result>1</result><redirecturl>" + page + "</redirecturl>";
	}
}