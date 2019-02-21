package com.utiexian.rywap.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.Enum.OrderState;
import com.ry.core.entity.Discountrecord;
import com.ry.core.entity.DistributeOrder;
import com.ry.core.entity.RefundExamine;
import com.ry.core.form.DiscountrecordForm;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.DistributeOrderService;
import com.ry.core.service.RefundExamineService;
import com.ry.util.JacksonUtil;
import com.ry.util.page.PageResults;

import com.ry.core.entity.Activecount;
import com.ry.core.entity.Historyprice;
import com.ry.core.entity.HongbaoDetail;
import com.ry.core.entity.Member;
import com.ry.core.entity.Notice;
import com.ry.core.entity.NoticeAdd;
import com.ry.core.entity.Region;
import com.ry.core.form.ActivecountForm;
import com.ry.core.service.ActivecountService;
import com.ry.core.service.HistorypriceService;
import com.ry.core.service.HongbaoService;
import com.ry.core.service.MemberService;
import com.ry.core.service.NoticeAddService;
import com.ry.core.service.OrgInfoService;
import com.ry.core.service.RegionService;
import com.ry.core.service.TiexianNoticeService;
import com.ry.core.util.Constant;
import com.ry.core.util.FileUtil;
import com.ry.util.DateUtil;

/**
 * 银票贴现
 */
@Controller
@RequestMapping("/wap/discountrecord")
public class DiscountrecordController {
	
	@Resource
	DistributeOrderService distributeOrderService;
	
	@Resource
	RefundExamineService refundExamineService;
	
	@Resource
	RegionService regionService;
	
	@Resource
	HistorypriceService historypriceService;
	
	@Resource
	NoticeAddService noticeAddService;
	
	@Resource
	TiexianNoticeService tiexianNoticeService;
	
	@Resource
	MemberService memberService;
	
	@Resource
	ActivecountService activecountService;
	
	@Resource
	HongbaoService hongbaoService;
	
	@Resource
	DiscountrecordService discountrecordService;
	
	@Resource
	OrgInfoService orgInfoService;
	
	private static final String WAP = "WAP";//WAP端
	
	/**
	 * 初始化贴现页面
	 * @author WKX
	 * @param model
	 */
	@RequestMapping("")
	public String discountrecord(Model model){
		return "discountrecord";
	}
	
	/** 
	 * 跳转到银票订单列表页面
	 * @author MH
	 * @return
	 */
	@RequestMapping("/page")
	public String skiplist(){
		return "/my/discountrecord_list";
	}
	
	/**
	 * 银票贴现下单
	 * @author WKX
	 * @param type1
	 * @param type2
	 * @param begintime
	 * @param endtime
	 * @param salepriceid
	 * @param place
	 * @param address
	 * @param allmoney
	 * @param membersex
	 * @param membername
	 * @param membermobile
	 * @param memberother
	 * @param memberid
	 * @param picpath
	 * @param hid
	 * @param bank
	 * @param acceptTime
	 * @param tradeModel
	 * @param deposit
	 * @param payWay
	 * @param longitude
	 * @param latitude
	 * @param cityId
	 * @param version
	 * @param endorse
	 * @param hasTicket
	 * @param flawTicket
	 * @param needTodoor
	 * @param model
	 * @throws IOException
	 */
	@RequestMapping("/save")
	public String save(Integer type1,Integer type2,String begintime,String endtime,Integer salepriceid,String place,String address,Float allmoney,
			Integer membersex,String membername,String membermobile,String memberother,Integer memberid,String picpath,String hid,String bank,Integer acceptTime,Integer tradeModel,
			Float deposit,Integer payWay,Float longitude,Float latitude,Integer cityId,Integer endorse,Integer hasTicket,Integer flawTicket,Integer needTodoor,
			Model model) throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		
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
						if (StringUtils.isNotBlank(picpaths[i])) {
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
			
			discountrecord.setSource(WAP);//微信订阅号

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
			if(member!=null){//日活
				discountrecord.setMembermobile(member.getMobile());
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
				if(cityId!=null){//交易城市要转换为城市主键（老版本默认上海）
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
				if(type1!=null && type1==2){//电票:承兑期限（0半年期、1一年期）
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
				discountrecord.setAddress(address);
				discountrecord.setLongitude(longitude);
				discountrecord.setLatitude(latitude);
				discountrecord.setPayWay(payWay);
				discountrecord.setBank(bank);
				if(deposit==null)deposit = (float)getDeposit(memberid, allmoney);//在App2.3中要在生成订单时获取保证金
				Map<String, Object> pay = toPay(3);//快钱
				result.put("pay", pay);
				discountrecord.setDeposit(deposit);//保证金金额
				if(pay!=null){
					discountrecord.setBnsNo(pay.get("bnsNo").toString());
					if(pay.get("jyh")!=null)discountrecord.setJyh(pay.get("jyh").toString());
				}
				discountrecordService.addDiscountrecord(discountrecord);
				if (StringUtils.isNotBlank(hid)) {
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
			}else{
				result.put("response", "failed");
				result.put("msg", "登录信息已过期请重新登录！");
			}
		}catch(Exception e){
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", eMsg);
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
	 * 生成商户订单号（相对APP端缺少银联预支付）
	 * @author WKX
	 * @param payWay 付款方式（0支付宝A、1微信W、2银联U、3块钱K、4宝付B）
	 * @param model
	 */
	private Map<String, Object> toPay(Integer payWay){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String prefix = payWay == null ? "N" : payWay == 0 ? "A" : payWay == 1 ? "W" : payWay == 2 ? "U":payWay == 3 ? "K":payWay == 4 ? "B":"N";//N代表无支付方式的
			String bnsNo = discountrecordService.randomBnsNo(prefix);
			result.put("bnsNo", bnsNo);
			result.put("payWay", payWay);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获取订单详情
	 * @author WKX
	 * @param id 主键
	 * @param model
	 */
	@RequestMapping("/get")
	public String get(Integer id,Model model){
		Map<String, Object> result = new HashMap<String, Object>();
		if(id!=null){
			result.put("data",discountrecordService.getById(id));//需要下单的主键
			result.put("response", "success");
		}else{
			result.put("response", "failed");
			result.put("msg", "数据异常");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 确认订单页面
	 * @author WKX
	 * @param id 主键
	 * @param model
	 */
	@RequestMapping("/confirm/page")
	public String confirmPage(Integer id,Model model){
		model.addAttribute("id", id);
		return "discountrecord-confirm";
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
	@RequestMapping("/excel/price")
	public String myExcel(Integer type1,Integer type2,Integer flag,Float allmoney,String start,String end,String cityName,Integer cityId,Model model,Integer limit){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			Date s = DateUtil.parser(start, DateUtil.FORMART3);
			Date e = DateUtil.parser(end, DateUtil.FORMART3);
			int shengDay = DateUtil.daysBetween(s, e);//天数（对应几个月）
			
			Historyprice query = new Historyprice();
			query.setType6(type1);//票据属性（1纸票、2电票）
			query.setType1(type2);//承兑行：1国股、2城商、3外资、4农商、5农合、6农信、7村镇
			query.setType3(1);//买断带票 1买断 2带票 （暂默认1）
			query.setType4(1);//地域 1长三角2珠三角3华中4环渤海5西南
			
			if(cityId!=null){
				query.setCityId(cityId);//城市id
			}else if(StringUtils.isNotBlank(cityName)){
				List<Region> regionL = regionService.getByNameAndType(cityName, "C");
				if(regionL!=null && regionL.size()>0){
					query.setCityId(regionL.get(0).getId());//城市id
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
			List<Historyprice> list = historypriceService.getList(allmoney,query);
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
	 * 根据节日获取调整天数
	 * @author WKX
	 * @param type1 票据类型
	 * @param end 到期日期
	 * @throws ParseException
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
	 * 预支付（保存贴现地址）
	 * @author WKX
	 * @param id 订单主键
	 * @param membername 用户名称
	 * @param membersex 性别
	 * @param membermobile 手机号码
	 * @param payWay 支付方式（暂时未传值）
	 * @param address 我的位置
	 * @param place 交易城市
	 * @param cityId 交易城市（主键）
	 * @param longitude 经度
	 * @param latitude 维度
	 * @param model
	 */
	@RequestMapping("/pay/before")
	public String payBefore(Integer id,String membername,Integer membersex,String membermobile,Integer payWay,
			String address,String place,Integer cityId,Float longitude,Float latitude,Model model){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Discountrecord discountrecord = discountrecordService.getModelById(id);
			if(discountrecord==null)throw new Exception("数据异常");
			
			if(payWay==null)payWay = 3;//块钱
			Map<String, Object> pay = toPay(payWay);//重新获取支付方式
			discountrecord.setBnsNo(pay.get("bnsNo").toString());
			discountrecord.setPayWay(payWay);
			discountrecord.setMembername(membername);
			discountrecord.setMembersex(membersex);
			discountrecord.setMembermobile(membermobile);
			discountrecord.setAddress(address);
			discountrecord.setPlace(place);
			discountrecord.setCityId(cityId);
			discountrecord.setLongitude(longitude);
			discountrecord.setLatitude(latitude);
			discountrecordService.updateModel(discountrecord);
			result.put("bnsNo", pay.get("bnsNo"));//订单号
			result.put("deposit", discountrecord.getDeposit());//保证金
			result.put("response", "success");
			result.put("msg", "保存成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 根据用户主键获取用户贴现订单[含查询]
	 * @author MH
	 * @param memberId 用户主键
	 * @param pageIndex 第几页
	 * @param model
	 */
	@RequestMapping("/list")
	public String listDiscountrecordByMemberId(Integer memberId,Integer pageNo,Integer pageSize,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		if(pageNo==null)pageNo = 1;
		if(pageSize==null)pageSize = 5;
		
		DiscountrecordForm form = new DiscountrecordForm();
		form.setMemberid(memberId);
		
		if(form!=null && form.getMemberid()!=null){
			PageResults<Map<String,Object>> page = discountrecordService.getPageList(pageNo, pageSize, form);
			result.put("data",page.getResults());
			result.put("response", "success");
			result.put("msg", "获取成功");
		}else{
			result.put("response", "failed");
			result.put("msg", "获取失败");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 跳转到取消订单的页面
	 * @author MH
	 * @param id	订单主键
	 * @param model
	 * @return
	 */
	@RequestMapping("/skip/cancel")
	public String skipcancel(Integer id,Model model){
		if(id == null) new Exception("出现异常，Id不能为空");
		Discountrecord dis = discountrecordService.getById(id);
		model.addAttribute("id",id);
		model.addAttribute("orderflag",dis.getOrderflag());
		return "/my/discountrecord_cancel";
	}
	
	/**
	 * 用户取消订单
	 * @param id 贴现订单
	 * @param cancel 取消原因
	 * @param reason 原因为本（文字、其他）
	 * @param currentState 当前状态
	 * @param version 版本
	 * @param model
	 * @author MH
	 */
	@RequestMapping("/cancel")
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
			Discountrecord dis = discountrecordService.getModelById(id);
			dis.setOrderflag(0);//无效订单
			dis.setVisitState(0);//企业贴现订单为无效订单时默认待回访状态
			dis.setCancel(cancel);
			if(reason != null && reason != "") dis.setCancelCause(reason);
			
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
	 * 根据主键获取对象（主要用来查看贴现地址保存情况）
	 * @param id 贴现主键
	 * @param model
	 * @throws IOException
	 * @author MH
	 */
	@RequestMapping("/get/info")
	public String getDisc(Integer id,Model model) throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			Discountrecord dis = discountrecordService.getById(id);
			if(dis==null)throw new Exception("订单获取异常");
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