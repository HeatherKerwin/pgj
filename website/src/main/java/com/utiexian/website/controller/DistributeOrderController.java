package com.utiexian.website.controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ry.core.entity.Discountrecord;
import com.ry.core.entity.DiscountrecordTask.Operator;
import com.ry.core.entity.DiscountrecordTask.OperatorType;
import com.ry.core.entity.DistributeOrder;
import com.ry.core.entity.DistributeOrderTask;
import com.ry.core.entity.Image;
import com.ry.core.entity.Member;
import com.ry.core.entity.Org;
import com.ry.core.entity.RefundExamine;
import com.ry.core.entity.Systeminfo;
import com.ry.core.entity.Systeminfo.Type;
import com.ry.core.form.DistributeOrderForm;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.DistributeOrderService;
import com.ry.core.service.MemberService;
import com.ry.core.service.OrgInfoService;
import com.ry.core.service.OrgService;
import com.ry.core.service.PaidanService;
import com.ry.core.service.PriceService;
import com.ry.core.service.RefundExamineService;
import com.ry.core.service.RefundRecordService;
import com.ry.core.service.SysteminfoService;
import com.ry.core.util.Constant;
import com.ry.core.util.FileUtil;
import com.ry.core.util.JPushUtil;
import com.ry.util.AlipayUtil;
import com.ry.util.Bill99Util;
import com.ry.util.DateUtil;
import com.ry.util.DistanceUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.SendMessagesUtil;
import com.ry.util.StringUtil;
import com.ry.util.page.PageResults;

import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;

/**
 * 机构订单
 * @author KHC
 */
@Controller
public class DistributeOrderController {
	
	private static Logger payLog = Logger.getLogger("payLog");
	
	@Resource
	RefundRecordService refundRecordService;
	
	@Resource
	DistributeOrderService distributeOrderService;
	
	@Resource
	DiscountrecordService discountrecordService;

	@Resource
	PriceService priceService;
	
	@Resource
	OrgService orgService;
	
	@Resource
	SysteminfoService systeminfoService;
	
	@Resource
	MemberService memberService;
	
	@Resource
	OrgInfoService orgInfoService;
	
	@Resource
	PaidanService paidanService;
	
	@Resource
	RefundExamineService refundExamineService;
	
	/**
	 * 创建线程池
	 * @author KHC
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
	 * @author KHC
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
	 * 推送消息、保存消息、发送短信
	 * @author KHC
	 * @param memberId 用户主键
	 * @param fkId 外键
	 * @param type 类型
	 * @param alert 标题
	 * @param des 描述内容
	 * @param smsCode 短信编码
	 * @since 2016年10月25日 上午9:22:08
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
	 * 订单信息详情
	 * @author MH
	 * @return
	 */
	@RequestMapping("/distributeorder/information/detail")
	public String informationdetail(){
		return "/hall/receiveOrder";
	}
	
	/**
	 * 机构银票列表
	 * @author KHC
	 * @param model
	 * @since 2016年12月12日 下午4:39:24
	 */
	@RequestMapping("/distributeorder/list")
	public String list(Model model){
		return "/orgorder/yp_list";
	}
	
	/**
	 * 机构银票详情
	 * @author KHC
	 * @param no
	 * @param model
	 * @since 2016年11月20日 下午7:13:46
	 */
	@RequestMapping("/distributeorder/detail")
	public String detail(Integer no,Model model){
		model.addAttribute("id",no);
		return "/hall/receiveOrder";
	}
	
	/**
	 * 机构接单银票详情
	 * @author KHC
	 * @param no
	 * @param model
	 * @since 2016年11月20日 下午7:13:46
	 */
	@RequestMapping("/distributeorder/detail/wait")
	public String getWait(String no,Model model){
		model.addAttribute("no",no);
		return "distributeorder/distributeorder";
	}
	
	/**
	 * 分页显示机构订单
	 * @author KHC
	 * @param form
	 * @param pageIndex
	 * @param pageSize
	 * @param request
	 * @param model
	 * @throws Exception
	 * @since 2016年11月14日 下午4:46:55
	 */
	@RequestMapping("/distributeorder/list/search")
	public String list(DistributeOrderForm form,Integer pageIndex,Integer pageSize,HttpServletRequest request,Model model) throws Exception{
		String switch_ = Constant.properties.getProperty("PAYSWITCH");//支付开关
		Member member = (Member) request.getSession().getAttribute("member");
		Integer memberId = member.getId();
		Map<String,Object> result = new HashMap<String, Object>();
		Map<String, Object> map = orgInfoService.getByMemberIdAndType(memberId, 1);//查询机构
		if(map!=null && map.get("org_id")!=null){
			form.setOrgId(Integer.valueOf(map.get("org_id").toString()));
		}
		if(pageIndex==null)pageIndex = 0;
		if(pageSize==null)pageSize = 10;
		if(form!=null && form.getOrgId()!=null){
			PageResults<Map<String,Object>> page = distributeOrderService.getPageListPC(pageIndex, pageSize, form);
			for(Map<String, Object> dtbo: page.getResults()){
				Object time = dtbo.get("create_time");
				if(time!=null){
					int timer = DateUtil.calSeconds(DateUtil.parser(time.toString(), DateUtil.FORMART), new Date());
					dtbo.put("timer", 600-timer);
				}
				if("off".equals(switch_)){//关闭保证金
					dtbo.put("deposit", 0);
				}
			}
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
	 * 拒绝订单（验票失败）
	 * @author KHC
	 * @param dtboId 接单主键
	 * @param cancel1 拒绝理由
	 * @param cancel2 验票失败理由
	 * @param reason 拒绝验票失败
	 * @param cancelCause 取消原因
	 * @param currentState 当前订单状态
	 * @param images 图片
	 * @param model
	 * @since 2016年10月30日 上午9:29:15
	 */
	@RequestMapping("/distributeorder/cancel")
	public String cancel(String no,String cancel1,String cancel2,String reason,String lostCause,String currentState,String[]images,HttpServletRequest request, Model model){
		Member member = (Member) request.getSession().getAttribute("member");
		Integer memberId = member.getId();
		Map<String,Object> result = new HashMap<String, Object>();
		String alert = "操作成功";
		String ok = "确定";
		try {
			if(no==null || no=="")throw new Exception("数据异常");
			DistributeOrder dist = distributeOrderService.getByNo(no);
			if(dist==null || currentState==null)throw new Exception("数据异常");
			if(dist.getState()!=Integer.valueOf(currentState))throw new Exception("数据已过期");
			Discountrecord disc = discountrecordService.getById(dist.getDcrdId());
			if(disc==null || disc.getOrderflag()==0)throw new Exception("数据已过期");
			if(org.apache.commons.lang.StringUtils.isBlank(lostCause))lostCause = reason;
			if(org.apache.commons.lang.StringUtils.isNumeric(cancel2)){
				dist.setCancel2(Integer.valueOf(cancel2));
			}
			dist.setCancel1(Integer.valueOf(cancel1));
			dist.setLostCause(lostCause);
						
			if(dist.getDepositState()!=null && dist.getDepositState()==1 && cancel1!=null && Integer.valueOf(cancel1)==5){//机构取消订单原因为其他时需要后台审核
				RefundExamine refundExamine = new RefundExamine();
				refundExamine.setDcrdId(disc.getId());
				refundExamine.setDtboId(dist.getId());
				refundExamine.setCancelRole(2);//机构端拒绝
				refundExamine.setCause(lostCause);//原因
				dist.setState(-2);//机构端拒绝
				disc.setOrderflag(0);//无效订单
				refundExamineService.saveModelAndUpdateDis(refundExamine, disc, dist);
			}else if(Integer.valueOf(currentState)==1 && dist.getDepositState()==0){//接单大厅拒绝订单
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
				if("2".equals(cancel1)){
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
	 * 接单失败时跳转接单大厅
	 * @author ZY
	 * 2016年10月25日下午4:10:12
	 */
	@RequestMapping("/distributeorder/hall")
	public String hall(){
		return "hall/receive";
	}
	
	/**
	 * 查询银票详情（待接单详情）
	 * @param no 订单号
	 * @param model
	 * @author ZY
	 */
	@RequestMapping("/distributeorder/get/wait")
	public String getWaitDtbo(String no,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		String switch_ = Constant.properties.getProperty("PAYSWITCH");//支付开关
		try {
			DistributeOrder dist = distributeOrderService.getByNo(no);
			Map<String,Object> dtbo = distributeOrderService.getInfoById(dist.getId());
			Object time = dtbo.get("create_time");
			Float deposit = 0f;//加大小票（大票：30；小票：10）
			
			if(!"off".equals(switch_)){//开启保证金（则计算保证金金额）
				if (dtbo.get("allmoney") != null && Double.valueOf(dtbo.get("allmoney").toString()) > 500) {
					deposit += 30;
				} else {
					deposit += 10;
				}
				Object orgId = dtbo.get("org_id");
				deposit += getDeposit(Integer.valueOf(orgId.toString()));//认证、完成订单、拒绝订单（金额）
			}
			dtbo.put("deposit", deposit);//保证金
			
			if(time!=null){
				int timer = DateUtil.calSeconds(DateUtil.parser(time.toString(), DateUtil.FORMART), new Date());
				dtbo.put("timer", 600-timer);
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
	 * 根据机构主键获取保证金额
	 * 【加是否认证金额（认证：0；未认证：30）】
	 * 【加上月是否已完成订单金额（有已完成：10；无已完成：30）】
	 * 【加上月是否拒绝订单金额（有：30；无：0）】
	 * @author KHC
	 * @param orgId 机构主键
	 * @throws ParseException
	 * @since 2016年10月26日 下午2:20:21
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
	 * 接受系统派单
	 * @author ZY
	 * @param form  订单信息
	 * @param payWay 支付方式
	 * @param bail 支付金额
	 * @param model
	 * 2016年12月12日下午2:04:16
	 */
	@RequestMapping("/distributeorder/accept")
	public String accept(DistributeOrderForm form, Integer payWay, Float bail, Model model) {
		String switch_ = Constant.properties.getProperty("PAYSWITCH");//支付开关
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			DistributeOrder order = distributeOrderService.getByNo(form.getNo());
			if(order==null || order.getState()!=1)throw new Exception("数据已过期");
			if("off".equals(switch_)){//当支付金额为0则无支付方式
				bail = 0F;
				payWay = null;
				order.setPayWay(null);
				order.setDeposit(bail);
			}
			String prefix = payWay == null ? "N" : payWay == 0 ? "A" : payWay == 1 ? "W" : payWay == 2 ? "U":payWay == 3 ? "K":payWay == 4 ? "B":"N";//N代表无支付方式的
			String bnsNo = discountrecordService.randomBnsNo(prefix);//生成bnsNo
			
			order.setDepositState(0);//初始状态（待支付）
			if(order.getPayWay()==null)order.setPayWay(payWay);
			if(order.getDeposit()==null)order.setDeposit(bail);
			if(org.apache.commons.lang.StringUtils.isBlank(order.getBnsNo()))order.setBnsNo(bnsNo);
			
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
			result.put("bnsNo", order.getBnsNo());
			result.put("bail", order.getDeposit());
			result.put("response", "success");
			result.put("msg", "加载成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", e.getMessage());
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 快钱付款
	 * @author MH
	 * @param out_trade_no 商户订单号
	 * @param deposit 付款金额
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping("/distributeorder/bill99pay")
	public String bill99pay(String deposit,String out_trade_no,HttpServletRequest request,Model model) throws Exception{
		String path = Constant.properties.getProperty("alipayUrl");
		DistributeOrder order = distributeOrderService.getByBnsno(out_trade_no);
		Integer deposit1 = (Float.valueOf(order.getDeposit())).intValue()*100;
		//指定付款人
		Member member = (Member) request.getSession().getAttribute("member");
		Integer memberId = member.getId();
		Map<String, String> paras = new HashMap<String, String>();
		paras.put("payerId", memberId.toString());//付款人标识
		paras.put("orderId", out_trade_no);//商户订单号
		paras.put("orderAmount", deposit1.toString());//订单金额
//		paras.put("orderAmount", "100");//订单金额
		paras.put("orderTime", new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()));//数量
		paras.put("productName", "银票接单保证金");//产品名称
		paras.put("productNum", "1");//数量
		paras.put("bgUrl", path+"/distributeorderpay/pay/bill99cb");//回调地址
		paras.put("pageUrl", path+"/distributeorderpay/pay/cb/page");//回调页面
		
		model.addAttribute("retValue",Bill99Util.buildRequestPay(paras));
		return "ajax";
	}
	

	/**
	 * 回调后台（同支付宝一样，隔一段时间也会回调一次）
	 * @author MH
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
	@RequestMapping("/distributeorderpay/pay/bill99cb")
	public @ResponseBody  String payBill99Cb(String merchantAcctId,String bankId,String orderId,String orderTime,String orderAmount,String bindCard,String bindMobile,String dealId,String bankDealId,String fee,String payResult,Model model){
		String path = Constant.properties.getProperty("alipayUrl");
		Map<String, Object> result = new HashMap<String, Object>();
		DistributeOrder dist = distributeOrderService.getByBnsno(orderId);
		String des;
		if( dist.getDepositState() == 0){
			des="未支付";
		}else{
			des="已支付";
			return null;
		}
		payLog.info("接单支付：进入保存支付状态方法savePayRecord...接单主键："+dist.getId()+"支付状态："+des);
		if(dist != null && dist.getState() == 1){
			if("10".equals(payResult)){
				dist.setDepositState(1);//初始状态（已支付）
				try {
					dist.setJyh(dealId);
					dist.setCard(bindCard);
				} catch (Exception e) {
					e.printStackTrace();
				}
				pushAndSend(dist.getOrgId(), dist.getId(), Type.DISTRIBUTEORDER, "已支付", "亲，您的订单保证金已支付成功，还请赶快处理订单吧！", "SMS_10611169");
			}else {
				pushAndSend(dist.getOrgId(), dist.getId(), Type.DISTRIBUTEORDER, des, "亲，您的订单保证金未完成支付，快去我的订单完成支付吧！", "SMS_10637201");
			}
			dist.setPayWay(3);//支付方式
			distributeOrderService.updateAndPayRecord(dist, 0, des,2.3F);
			payLog.info("接单支付：进入保存支付状态方法savePayRecord...接单主键："+dist.getOrgId()+"订单主键："+dist.getId());
			result.put("response", "success");
			result.put("msg", "操作成功");
		}else{
			result.put("response", "failed");
			result.put("msg", "网络异常");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "<result>1</result><redirecturl>"+ path +"/distributeorderpay/pay/cb/page</redirecturl>";
	}
	
	/**
	 * 快钱回调页面
	 * @author MH
	 * @throws Exception
	 * @since 2017年2月24日 上午10:39:43
	 */
	@RequestMapping("/distributeorderpay/pay/cb/page")
	public String cbPayPage(String merchantAcctId,String bankId,String orderId,String orderTime,String orderAmount,String bindCard,String bindMobile,String dealId,String bankDealId,String fee,String payResult,Model model) throws Exception{
		return StringUtil.processRedirectUrl(Constant.properties.getProperty("baseUrl"),"/distributepay/pay/success");
	}
	
	/**
	 * 快钱订单查询
	 * @author MH
	 * @param bnsno 商户订单号
	 * @param model
	 * @throws Exception
	 * @since 2017年3月2日 下午2:01:12
	 */
	@RequestMapping("/distributeorder/bill99query")
	public String query(String bnsno,Model model) throws Exception{
		Map<String, String> paras = new LinkedHashMap<String, String>();
		paras.put("orderId", bnsno);//商户订单号
		model.addAttribute("retValue",JacksonUtil.objToJson(Bill99Util.query(paras)));
		return "ajax";
	}
	
	/**
	 * 支付宝付款
	 * @author KHC
	 * @param out_trade_no 商户订单号
	 * @param deposit 付款金额
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping("/distributeorder/pay")
	public String list(String deposit,String out_trade_no,HttpServletRequest request,Model model) throws Exception{
		String path = Constant.properties.getProperty("alipayUrl");
		
		Map<String, String> paras = new HashMap<String, String>();
		paras.put("goods_type", "0");//商品类型:1表示实物类商品、0表示虚拟类商品
		paras.put("return_url", path+"/distributeorderpay/pay/callback");//此链接需要配置线上地址（并外网可访问）
		paras.put("notify_url", path+"/distributeorderpay/pay/cb?deposit="+deposit);//此链接需要配置线上地址（并外网可访问）
		
		paras.put("out_trade_no", out_trade_no);
		paras.put("subject", "银票机构支付保证金");//商品名称
		
		//Float money = Float.valueOf(deposit)/1000;
		paras.put("total_fee",deposit);
		paras.put("body", "银票机构支付保证金");//商品描述
		
		model.addAttribute("retValue",AlipayUtil.createDirectPay(paras));
		return "ajax";
	}
	
	/**
	 * 支付回调
	 * @param out_trade_no 商户订单号
	 * @param is_success 成功标识 T、F
	 * @param trade_no 交易号
	 * @param buyer_email 支付账号
	 * @param request
	 * @since 2016年10月25日 上午10:20:40
	 */
	@RequestMapping("/distributeorderpay/pay/callback")
	public String payCallback(String out_trade_no,String is_success,String trade_no,String buyer_email){
		return StringUtil.processRedirectUrl(Constant.properties.getProperty("baseUrl"),"/distributeorder/list");
	}
	
	/**
	 * 支付回调
	 * @param out_trade_no 商户订单号
	 * @param is_success 成功标识 T、F
	 * @param trade_no 交易号
	 * @param buyer_email 支付账号
	 * @param request
	 * @since 2016年10月25日 上午10:20:40
	 */
	@RequestMapping("/distributeorderpay/pay/cb")
	public String payCb(String out_trade_no,String trade_status,String trade_no,String buyer_email,String deposit,Model model){
		Map<String, Object> result = new HashMap<String, Object>();
		DistributeOrder dist = distributeOrderService.getByBnsno(out_trade_no);
		String des;
		if( dist.getDepositState() == 0){
			des="未支付";
		}else{
			des="已支付";
			return null;
		}
		payLog.info("接单支付：进入保存支付状态方法savePayRecord...接单主键："+dist.getId()+"支付状态："+des);
		if(dist != null && dist.getState() == 1){
			if(trade_status.equals("TRADE_SUCCESS")){
				dist.setDepositState(1);//初始状态（已支付）
				try {
					dist.setJyh(trade_no);
					dist.setCard(buyer_email);
				} catch (Exception e) {
					e.printStackTrace();
				}
				pushAndSend(dist.getOrgId(), dist.getId(), Type.DISTRIBUTEORDER, "已支付", "亲，您的订单保证金已支付成功，还请赶快处理订单吧！", "SMS_10611169");
			}else {
				pushAndSend(dist.getOrgId(), dist.getId(), Type.DISTRIBUTEORDER, des, "亲，您的订单保证金未完成支付，快去我的订单完成支付吧！", "SMS_10637201");
			}
			if(deposit!=null)dist.setDeposit(Float.valueOf(deposit));//保证金
			dist.setPayWay(0);//支付方式
			distributeOrderService.updateAndPayRecord(dist, 0, des,2.3F);
			payLog.info("接单支付：进入保存支付状态方法savePayRecord...接单主键："+dist.getOrgId()+"订单主键："+dist.getId());
			result.put("response", "success");
			result.put("msg", "操作成功");
		}else{
			result.put("response", "failed");
			result.put("msg", "网络异常");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
		
	/**
	 * 支付回调
	 * @param trade_no 交易号
	 * @param buyer_email 支付账号
	 * @param request
	 * @since 2016年10月25日 上午10:20:40
	 */
	@RequestMapping("/distributepay/pay/callback")
	public String payCallback(){
		return "distributeorder/success";
	}
	
	/**
	 * 跳转到支付成功的页面
	 */
	@RequestMapping("/distributepay/pay/success")
	public String paySuccess(){
		return "distributeorder/success";
	}
	
	/**
	 * 支付宝付款
	 * @author ZY
	 * @param out_trade_no 商户订单号
	 * @param subject 商品名称
	 * @param total_fee 付款金额
	 * @param body 商品描述
	 * @param model
	 * @throws Exception
	 * @since 2016年10月24日 下午5:56:04
	 */
	@RequestMapping("/distributeorder/paydo")
	public String toPay(String bnsNo,String deposit,HttpServletRequest request,Model model) throws Exception{
		//String ip = InetAddress.getLocalHost().getHostAddress();
		//int port = request.getLocalPort();
		//String cp = request.getContextPath();
		String path = Constant.properties.getProperty("alipayUrl");
		
		Map<String, String> paras = new HashMap<String, String>();
		paras.put("goods_type", "0");//商品类型:1表示实物类商品、0表示虚拟类商品
		paras.put("return_url", path+"/distributepay/pay/callback");//此链接需要配置线上地址（并外网可访问）
		paras.put("notify_url", path+"/distributepay/pay/cb");//此链接需要配置线上地址（并外网可访问）
		
		paras.put("out_trade_no", bnsNo);
		paras.put("subject", "银票保证金");
		//Float money = Float.valueOf(deposit)/1000;
		paras.put("total_fee", deposit);
		paras.put("body", "银票保证金");
		model.addAttribute("retValue",AlipayUtil.createDirectPay(paras));
		return "ajax";
	}
	
	/**
	 * 异步回调，根据支付状态更新订单
	 * @author WKX
	 * @param out_trade_no 商户订单号
	 * @param trade_no 交易号
	 * @param buyer_email 支付账号
	 * @param trade_status 【如下】
	 * 【WAIT_BUYER_PAY：交易创建，等待买家付款。】
	 * 【TRADE_CLOSED：在指定时间段内未支付时关闭的交易；在交易完成全额退款成功时关闭的交易。】
	 * 【TRADE_SUCCESS：交易成功，且可对该交易做操作，如：多级分润、退款等。】
	 * 【TRADE_PENDING：等待卖家收款（买家付款后，如果卖家账号被冻结）。】
	 * 【TRADE_FINISHED：交易成功且结束，即不可再做任何操作】
	 * @since 2016年10月28日 下午4:19:39
	 */
	@RequestMapping("/distributepay/pay/cb")
	public String cbPay(String out_trade_no,String trade_no,String buyer_email,String trade_status){
		Map<String, Object> result = new HashMap<String, Object>();
		Integer state=null;
		String des=null;
		try {
			DistributeOrder dist = distributeOrderService.getByBnsno(out_trade_no);
			if(dist != null && dist.getDepositState() == 1){
				return null ;
			}
			if (dist != null && dist.getState() == 1) {
				if (trade_status.equals("TRADE_SUCCESS")){
					state=0;des="交易成功";
					dist.setDepositState(1);// 初始状态（已支付）
					try {
						Map<String,Object> cbPay = AlipayUtil.tradeQuery(dist.getBnsNo());
						if(cbPay!=null){
							Object trade_no1 = cbPay.get("trade_no");
							Object buyer_email1 = cbPay.get("buyer_email");
							if(trade_no1!=null)dist.setJyh(trade_no1.toString());//保存交易号
							if(buyer_email1!=null)dist.setCard(buyer_email1.toString());//账号
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					dist.setPayWay(0);//支付方式
					distributeOrderService.updateAndPayRecord(dist, state, des,2.3F);
					// return "redirect:/test/pay/callback/redirect";
				}
				//else{
				//	state=2;des="交易失败";
				//	if(out_trade_no!=null)dist.setDeposit(Float.parseFloat(out_trade_no));//保证金
				//	dist.setPayWay(0);//支付方式
				//	distributeOrderService.updateAndPayRecord(dist, state, des,2.3F);
				//	return StringUtil.processRedirectUrl(Constant.properties.getProperty("baseUrl"), "/index.html");//最后要改成回到待支付详情里！！！
			//	}
			//	pushAndSend(dist.getOrgId(), dist.getId(), Type.DISTRIBUTEORDER, "已支付", "亲，您的订单保证金已支付成功，还请赶快处理订单吧！", "SMS_10611169");
			}else {
				pushAndSend(dist.getOrgId(), dist.getId(), Type.DISTRIBUTEORDER, des, "亲，您的订单保证金未完成支付，快去我的订单完成支付吧！", "SMS_10637201");
			}
			payLog.info("接单支付：进入保存支付状态方法savePayRecord...接单主键："+dist.getOrgId()+"订单主键："+dist.getId());
			result.put("response", "success");
			result.put("msg", "操作成功");
		}catch (Exception e){
			result.put("response", "failed");
			result.put("msg", "网络异常");
		} 
		
		return "ajax";
	}
	
	/**
	 * 订单查询（暂时没用到，后面优化可能会用）
	 * @author ZY
	 * @param out_trade_no
	 * @param model
	 * @throws Exception
	 * @since 2016年10月25日 上午10:42:22
	 */
	@RequestMapping("/distributeorder/test/query")
	public String payCallback(String out_trade_no,Model model) throws Exception{
		Map<String,Object> result = AlipayUtil.tradeQuery(out_trade_no);
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 根据订单号获取详情页
	 * @author KHC
	 * @param no
	 * @param model
	 * @throws Exception
	 * @since 2016年11月4日 下午2:48:26
	 */
	@RequestMapping("/distributeorder/get")
	public String getInfo(String no,Model model) throws Exception{
		if(no==null)throw new Exception("数据异常");
		String switch_ = Constant.properties.getProperty("PAYSWITCH");//支付开关
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			DistributeOrder dist = distributeOrderService.getByNo(no);
			if(dist==null)throw new Exception("数据异常");
			Map<String,Object> map = distributeOrderService.updateReadTaskAndGetInfoById(dist.id);
			if(map==null)throw new Exception("数据异常");
			
			Object ordertime = map.get("ordertime");
			if(ordertime!=null)map.put("ordertime", ordertime.toString());
			
			Object rate = map.get("price");
			Object rate1 = map.get("price1");
			Object rate2 = map.get("price2");
			
			map.put("rate", rate);//月利率
			map.put("rate1", rate1);//参数
			map.put("rate2", rate2);//10万贴现多少钱
			
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
			
			if("off".equals(switch_)){//关闭保证金
				map.put("dtdeposit", 0);
			}
			result.put("data",map);
			result.put("response", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "获取失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 加载状态角标
	 * @author KHC
	 * @param type
	 * @param orgId
	 * @param version
	 * @param model
	 * @since 2016年11月14日 下午2:25:35
	 */
	@RequestMapping("/distributeorder/badge")
	public String orgBadge(HttpServletRequest request,Model model){
		Member member = (Member) request.getSession().getAttribute("member");
		Integer memberId = member.getId();
		Map<String,Object> result = new HashMap<String, Object>();
		DistributeOrderForm form = new DistributeOrderForm();
		Map<String, Object> map = orgInfoService.getByMemberIdAndType(memberId, 1);//查询机构
		if(map!=null && map.get("org_id")!=null){
			try {
				form.setOrgId(Integer.valueOf(map.get("org_id").toString()));
				form.setState(1);
				form.setDepositState(0);
				PageResults<Map<String,Object>> page = distributeOrderService.getPageListPC(0, 10, form);
				result.put("list1",page.getTotalCount());
				form.setState(null);
				form.setDepositState(1);
				form.setStates(new Integer[]{1,2,4,5});
				PageResults<Map<String,Object>> page1 = distributeOrderService.getPageListPC(0, 10, form);
				result.put("list2",page1.getTotalCount());
				result.put("response", "success");
			} catch (Exception e) {
				e.printStackTrace();
				result.put("response", "failed");
				result.put("msg", "查询失败");
			}
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
}