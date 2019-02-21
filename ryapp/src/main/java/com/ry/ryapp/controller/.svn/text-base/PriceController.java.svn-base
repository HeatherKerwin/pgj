package com.ry.ryapp.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Notice;
import com.ry.core.entity.OrgLimit;
import com.ry.core.entity.Price;
import com.ry.core.entity.PriceType;
import com.ry.core.service.DistributeOrderService;
import com.ry.core.service.OrgCityService;
import com.ry.core.service.OrgInfoService;
import com.ry.core.service.OrgLimitService;
import com.ry.core.service.PriceService;
import com.ry.core.service.PriceTypeService;
import com.ry.core.service.RegionService;
import com.ry.core.service.TiexianNoticeService;
import com.ry.core.util.Constant;
import com.ry.util.DateUtil;
import com.ry.util.JacksonUtil;

/**
 * 机构报价
 * @author WKX
 */
@Controller
@RequestMapping("/app/price/")
public class PriceController {
	
	@Resource
	PriceService priceService;
	
	@Resource
	PriceTypeService priceTypeService;
	
	@Resource
	OrgLimitService orgLimitService;
	
	@Resource
	TiexianNoticeService tiexianNoticeService;
	
	@Resource
	private OrgCityService orgCityService;
	
	@Resource
	private DistributeOrderService distributeOrderService;
	
	@Resource
	RegionService regionService;
	
	@Resource
	OrgInfoService orgInfoService;
	
	private static final Logger logger = Logger.getLogger(PriceController.class);
	
	/**
	 * 根据机构订单主键获取派单时的报价及基础订单信息（先获取数据库保存）
	 * @author WKX
	 * @param dtboId
	 * @param model
	 * @since 2016年3月7日 下午3:56:20
	 */
	@RequestMapping("get/order/record")
	public String get(Integer dtboId,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			Map<String,Object> map = priceService.getDtboDcrdById(dtboId);
			if(map==null)throw new Exception("数据有误");
			
			if(map.get("tzts")==null || map.get("jxts")==null){
				Object start = map.get("begintime");//出票日期
				Object end = map.get("endtime");//到期日期
				Object type1 = map.get("type1");//票据类型
				
				Date s = DateUtil.parser(start.toString(), DateUtil.FORMART3);
				Date e = DateUtil.parser(end.toString(), DateUtil.FORMART3);
				
				int shengDay = DateUtil.daysBetween(s, e);//天数（对应几个月）
				int tzts = getTzts(Integer.valueOf(type1.toString()),e);//调整天数（根据票据类型）
				int jxts = shengDay + tzts;//计息天数
				map.put("tzts", tzts);
				map.put("day", jxts);
			}else{
				map.put("day", map.get("jxts"));
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
	 * 获取所有报价类型(用于报价展示)
	 * 根据机构ID获取机构的报价额度
	 * @author BKY
	 */
	@RequestMapping("get/priceTypeAndOrgLimit")
	public String getPriceType(Integer orgId, Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<PriceType> priceTypeList = priceTypeService.getPriceType();
			result.put("priceTypeList", priceTypeList);
			OrgLimit orgLimit = orgLimitService.getByOrgIdAndDay(orgId, "");
			result.put("orgLimit", orgLimit);
			result.put("response", "success");
		} catch (Exception e) {
			logger.info("获取所有报价类型时出错", e);
			e.printStackTrace();
			result.put("response", "error");
			result.put("msg", "获取所有报价类型时失败!");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 报价保存信息
	 * @author BKY
	 */
	@RequestMapping("save")
	public String save(Model model, Price price) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Integer orgId = price.getOrgId();
			Integer priceTypeId = price.getPriceTypeId();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Long minute = null;		//一小时内不能重复报价
			Date date = new Date();
			Calendar calendar = Calendar.getInstance();	
			calendar.setTime(date);
			
			String createTime = sdf.format(date);		//当前日期
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar3 = Calendar.getInstance();
			Date date3 = sdf1.parse(createTime + " 08:30:00");	//早上8.30
			calendar3.setTime(date3);
			
			Calendar calendar4 = Calendar.getInstance();
			Date date4 = sdf1.parse(createTime + " 17:30:00"); //下午5.30
			calendar4.setTime(date4);
			//只能在早上8.30-下午5.30之间报价
			if ((calendar.getTimeInMillis() - calendar3.getTimeInMillis()) < 0 || (calendar.getTimeInMillis() - calendar4.getTimeInMillis() > 0)) {
				result.put("msg", "每天只可以从8：30-17：30之间报价");
			} else {
				Integer cityId = Constant.DEFAULT_CITY_ID;
				List<Price> list = priceService.getByOrgId(orgId, createTime, priceTypeId, cityId);
				if (list != null && list.size() > 0) {
					Boolean hasType = false;
					for(Price price1 : list) {
						if (price1.getPriceTypeId() == priceTypeId) {
							hasType = true;
						}
					}
					if (hasType) {						
						Calendar calendar2 = Calendar.getInstance();
						Date create_time = list.get(0).getCreateTime();
						calendar2.setTime(create_time);
						minute = (calendar.getTimeInMillis() - calendar2.getTimeInMillis()) / (1000 * 60);
					}
				}
				if (minute != null && minute >= 0 && minute < 60) {				
					result.put("msg", "距离下次报价还剩" + (60 - minute) + "分钟");
				} else {
					price.setCreateTime(date);
					/*@APP2.1的版本bug  下个版本注意修改APP */
					int day = 93;
					int type = price.getPriceTypeId();
//					if (type == 4 || type == 7 || type == 10) {
//						day = 178;
//					} else if(type == 5 || type == 8 || type == 11) {
//						day = 187;
//					}
					PriceType pt = priceTypeService.getById(type);
					if (StringUtils.isNotBlank(pt.getDay())) {
						day = Integer.valueOf(pt.getDay());
					}
					
					if (price.getWay() != null && price.getWay() == 0) {	//A方式
						DecimalFormat df = new DecimalFormat("#0.00");
						if (!"--".equals(price.getGuogu())) {
							double money = Double.parseDouble(price.getGuogu());
							double money1 = Double.parseDouble(price.getGuogu1());
							price.setGuogu2(df.format((100000 * day * (money / 30) / 1000) + money1));
						} else {
							price.setGuogu2(price.getGuogu1());
						}
						if (!"--".equals(price.getChengshang())) {
							double money = Double.parseDouble(price.getChengshang());
							double money1 = Double.parseDouble(price.getChengshang1());
							price.setChengshang2(df.format((100000 * day * (money / 30) / 1000) + money1));
						} else {
							price.setChengshang2(price.getChengshang1());
						}
						if (!"--".equals(price.getWaizi())) {
							double money = Double.parseDouble(price.getWaizi());
							double money1 = Double.parseDouble(price.getWaizi1());
							price.setWaizi2(df.format((100000 * day * (money / 30) / 1000) + money1));
						} else {
							price.setWaizi2(price.getWaizi1());
						}
						if (!"--".equals(price.getNonghe())) {
							double money = Double.parseDouble(price.getNonghe());
							double money1 = Double.parseDouble(price.getNonghe1());
							price.setNonghe2(df.format((100000 * day * (money / 30) / 1000) + money1));
						} else {
							price.setNonghe2(price.getNonghe1());
						}
						if (!"--".equals(price.getNongxin())) {
							double money = Double.parseDouble(price.getNongxin());
							double money1 = Double.parseDouble(price.getNongxin1());
							price.setNongxin2(df.format((100000 * day * (money / 30) / 1000) + money1));
						} else {
							price.setNongxin2(price.getNongxin1());
						}
						if (!"--".equals(price.getNongshang())) {
							double money = Double.parseDouble(price.getNongshang());
							double money1 = Double.parseDouble(price.getNongshang1());
							price.setNongshang2(df.format((100000 * day * (money / 30) / 1000) + money1));
						} else {
							price.setNongshang2(price.getNongshang1());
						}
						if (!"--".equals(price.getCunzhen())) {
							double money = Double.parseDouble(price.getCunzhen());
							double money1 = Double.parseDouble(price.getCunzhen1());
							price.setCunzhen2(df.format((100000 * day * (money / 30) / 1000) + money1));
						} else {
							price.setCunzhen2(price.getCunzhen1());
						}
					} else if(price.getWay() != null && price.getWay() == 1) {	//B方式
						DecimalFormat df = new DecimalFormat("#0.000");
						if ((!"--".equals(price.getGuogu2())) && (!"0".equals(price.getGuogu2())) && ("--".equals(price.getGuogu()) || "".equals(price.getGuogu().trim()))) {
							double money = Double.parseDouble(price.getGuogu2());
							price.setGuogu(df.format((money * 30 * 1000) / 100000 / day));
						}
						if ((!"--".equals(price.getChengshang2())) && (!"0".equals(price.getChengshang2())) && ("--".equals(price.getChengshang()) || "".equals(price.getChengshang().trim()))) {
							double money = Double.parseDouble(price.getChengshang2());
							price.setChengshang(df.format((money * 30 * 1000) / 100000 / day));
						}
						if ((!"--".equals(price.getWaizi2())) && (!"0".equals(price.getWaizi2())) && ("--".equals(price.getWaizi()) || "".equals(price.getWaizi().trim()))) {
							double money = Double.parseDouble(price.getWaizi2());
							price.setWaizi(df.format((money * 30 * 1000) / 100000 / day));
						}
						if ((!"--".equals(price.getNonghe2())) && (!"0".equals(price.getNonghe2())) && ("--".equals(price.getNonghe()) || "".equals(price.getNonghe().trim()))) {
							double money = Double.parseDouble(price.getNonghe2());
							price.setNonghe2(df.format((money * 30 * 1000) / 100000 / day));
						}
						if ((!"--".equals(price.getNongxin2())) && (!"0".equals(price.getNongxin2())) && ("--".equals(price.getNongxin()) || "".equals(price.getNongxin().trim()))) {
							double money = Double.parseDouble(price.getNongxin2());
							price.setNongxin(df.format((money * 30 * 1000) / 100000 / day));
						}
						if ((!"--".equals(price.getNongshang2())) && (!"0".equals(price.getNongshang2())) && ("--".equals(price.getNongshang()) || "".equals(price.getNongshang().trim()))) {
							double money = Double.parseDouble(price.getNongshang2());
							price.setNongshang(df.format((money * 30 * 1000) / 100000 / day));
						}
						if ((!"--".equals(price.getCunzhen2())) && (!"0".equals(price.getCunzhen2())) && ("--".equals(price.getCunzhen()) || "".equals(price.getCunzhen().trim()))) {
							double money = Double.parseDouble(price.getCunzhen2());
							price.setCunzhen(df.format((money * 30 * 1000) / 100000 / day));
						}
					}
					/* 删除结束 */
					price.setCityId(Constant.DEFAULT_CITY_ID);//老版本报价设置默认城市
					priceService.saveEntity(price);
					result.put("msg", "保存成功");
				}
			}
			result.put("response", "success");
		} catch (Exception e) {
			logger.info("报价保存信息时出错", e);
			e.printStackTrace();
			result.put("response", "error");
			result.put("msg", "获取所有报价类型失败!");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * @APP2.2版本	报价保存信息
	 * @param price	报价
	 * @param baojiaWay	常规报价 or 快速报价
	 * @param baojiaStyle 电票 or 纸票
	 * @param timeLimit 期限(目前只在大票电票中有)： HALF-半年期，FULL-一年期
	 * @author BKY
	 */
	@RequestMapping("save2")
	public String save2(Model model, Price price, String baojiaWay,Integer cityId,Float version,String cityIds) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		String ps = Constant.properties.getProperty("VERSION_ALERT");//@WKX EDIT版本过期提示[因之前版本强制更新有问题，现做提示处理]
    	if("on".equals(ps) && (version==null || version<=2.32F)){
    		result.put("response", "failed");
			result.put("msg", "当前版本已停用，请下载最新版本票据管家APP。");
			model.addAttribute("retValue",JacksonUtil.objToJson(result));
			return "ajax";
    	}
    	
		try {
			if (cityId == null || "".equals(cityId)) {
				cityId = Constant.DEFAULT_CITY_ID;
			}
			if (price.getCreateTime() == null ) {
				price.setCreateTime(new Date());
			}
			boolean isTime = false;
			
			if(cityId == 0){//验证全部能否报价
				isTime = isTime(result, price, cityId);//报价的时间是否满足时间条件
			}else if(cityId == 10000){//选择全国，验证之前是否报价
				isTime = isTime(result, price, cityId);//报价的时间是否满足时间条件
			}
			if(cityIds != null && cityIds != "" && cityId != 0 && cityId != 10000){//选择 多个城市的验证
				String B[] = cityIds.split(",");
				for(int i=0;i<B.length;i++){
					cityId = Integer.valueOf(B[i]).intValue();
					isTime = isTime(result, price, cityId);//报价的时间是否满足时间条件
					if(!isTime) break;
				}
			}else if(cityId != 0){
				isTime = isTime(result, price, cityId);//报价的时间是否满足时间条件
			}
			
			if (isTime) {
				boolean saveOrNot = false;
				if(cityId == 10000 ){//全国报价，看填写的城市是否有不符合规矩的，如果有，不让报价
					saveOrNot = savePrice(price, baojiaWay, cityId);
				}else if(cityIds != null && cityIds != "" && cityId != 0){//多个城市报价
					String B[] = cityIds.split(",");
					for(int i=0;i<B.length;i++){
						cityId = Integer.valueOf(B[i]).intValue();
						saveOrNot = savePrice(price, baojiaWay, cityId);
					}
				}else if(cityId != 0){
					saveOrNot = savePrice(price, baojiaWay, cityId);
				}else{//全部城市报价
					Integer orgId = price.getOrgId();
					List<Map<String, Object>> orgCityList = orgCityService.getCityByOrgId(orgId);
					if (orgCityList != null && orgCityList.size() > 0) {
						for (Map<String, Object> orgCity : orgCityList) {
							String city_id = orgCity.get("cityid").toString();
							saveOrNot = savePrice(price, baojiaWay, Integer.valueOf(city_id));
						}
					}
				}
				if (saveOrNot) {
					result.put("msg", "保存成功");
					result.put("response", "success");
				}else {
					result.put("msg", "保存失败");
					result.put("response", "error");
				}
			} else {
				result.put("response", "error");
			}
		} catch (Exception e) {
			logger.info("报价保存信息时出错", e);
			e.printStackTrace();
			result.put("response", "error");
			result.put("msg", "获取所有报价类型失败!");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 获取所有报价类型priceType
	 * 根据机构ID获取该机构当天的报价price
	 * 获取该机构当天的额度orgLimit
	 * 获取该机构的机构名称
	 */
	@RequestMapping("get/priceAndType")
	public String priceAndType(Model model, Integer orgId, Integer priceTypeId, Integer cityId) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<PriceType> priceTypeList = priceTypeService.getPriceType();
			result.put("priceTypeList", priceTypeList);
			OrgLimit orgLimit = orgLimitService.getByOrgIdAndDay(orgId, "");
			result.put("orgLimit", orgLimit);
			String createTime = DateUtil.formart(new Date(), DateUtil.FORMART3);
			if (cityId == null) {
				cityId = Constant.DEFAULT_CITY_ID;
			}
			List<Price> priceList = priceService.getByOrgId(orgId, createTime,priceTypeId, cityId);
			Map<String,Object> orginfo = orgInfoService.getByOrgId(orgId, 1);
			if(orginfo != null){
				result.put("orginfo", orginfo);
			}
			if (priceList != null && priceList.size() > 0) {
				result.put("priceList", priceList);
			}
			result.put("response", "success");
		} catch (Exception e) {
			logger.info("获取报价类型和机构的报价信息时出错", e);
			e.printStackTrace();
			result.put("response", "error");
			result.put("msg", "获取报价类型和机构的报价信息时失败!");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 根据报价ID查询：获取报价的创建时间和所属报价类型的信息
	 * @param id 报价id
	 */
	@RequestMapping("get/priceTypeAndTimeById")
	public String priceTypeAndTimeById(Model model, Integer id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Map<String, Object> map = priceService.getPriceById(id);
//			List<Map<String, Object>> list = priceService.getById(id);
//			if (list != null && list.size() > 0) {
//				Map<String, Object> map = list.get(0);
			if (map != null) {
				String createTime = map.get("create_time").toString();
//				Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createTime);
				Date date = DateUtil.parser(createTime, DateUtil.FORMART);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				Calendar calendar2 = Calendar.getInstance();
				calendar2.setTime(new Date());
				Long minute = (calendar2.getTimeInMillis() - calendar.getTimeInMillis()) / (1000 * 60);
				if (minute != null && minute >= 0 && minute < 60) {				
					result.put("response", "success");
					result.put("msg", "距离下次报价还剩" + (60 - minute) + "分钟");
					result.put("state", "0");
				} else {
					result.put("typeAndTimeMap", map);
					result.put("state", "1");
				}
				result.put("response", "success");
			}
		} catch (Exception e) {
			logger.info("根据报价ID查询：获取报价的创建时间和所属报价类型的信息",e);
			e.printStackTrace();
			result.put("response", "error");
			result.put("msg", "根据报价ID查询：获取报价的创建时间和所属报价类型的信息时失败");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 根据机构ID和价格类型ID获取当天的报价信息
	 * @param orgId 机构id
	 * @param priceTypeId 报价类型id
	 */
	@RequestMapping("get/infoById")
	public String infoById(Model model, Integer orgId,Integer priceTypeId, Integer cityId) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (cityId == null) {
				cityId = Constant.DEFAULT_CITY_ID;
			}
			Price price = priceService.infoById(orgId, priceTypeId, cityId);
			result.put("price", price);
			result.put("response", "success");
			result.put("msg", "查询成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 根据机构id获取报价和报价类型信息
	 * @param orgId 机构id
	 * @author BKY
	 */
	@RequestMapping("getPriceAndTypeByOrgId")
	public String getPriceAndTypeByOrgId(Model model, Integer orgId, Integer cityId) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			OrgLimit orgLimit = orgLimitService.getByOrgIdAndDay(orgId, "");
			result.put("orgLimit", orgLimit);
			if (cityId == null) {
				cityId = Constant.DEFAULT_CITY_ID;
			}
			List<Map<String, Object>> list = priceService.getPriceAndTypeByOrgId(orgId, "", cityId,null);
			if (list != null && list.size() > 0) {
				result.put("list", list);
			}
			result.put("response", "success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 根据机构主键获取该机构已报价的priceTypeId
	 * @param orgId 机构主键
	 * @author BKY
	 */
	@RequestMapping("getPtidByOrgId")
	public String getPtidByOrgId(Model model, Integer orgId, Integer cityId) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (cityId == null) {
				cityId = Constant.DEFAULT_CITY_ID;
			}
			List<Map<String, Object>> list = priceService.getPtidAndDay(orgId, "", cityId);
			if (list != null && list.size() > 0) {
				result.put("list", list);
			}
			result.put("response", "success");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("根据机构主键获取该机构已报价的priceTypeId 出错！" + e);
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 根据机构主键、报价类型、报价期限 找到真正的报价类型id
	 * @param orgId	机构主键
	 * @param priceTypeId 报价类型id
	 * @param timeLimit 期限(半年期0 一年期1)
	 */
	@RequestMapping("getPtidByOrgIdAndTime")
	public String getPtidByOrgIdAndTime(Model model, Integer orgId, Integer priceTypeId, Integer timeLimit) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (timeLimit != null) {
				List<Map<String, Object>> ptList = priceTypeService.getByIdAndTimeLimit(priceTypeId, timeLimit);
				if (ptList != null && ptList.size() > 0) {
					result.put("ptList", ptList);
				}
			}
			result.put("response", "success");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("根据机构主键、报价类型、报价期限 找到真正的报价类型id 出错！" + e);
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
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
		Notice notice = tiexianNoticeService.findFestivalByNowTime(end);//查询本年度提示
		if(notice!=null && notice.getEndDate()!=null){
			init = DateUtil.daysBetween(end, notice.getEndDate());//天数（对应几个月）
		}else{
			Calendar cal = Calendar.getInstance();
			cal.setTime(end);
			int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
			if(6==w){
				init += 2;
			}else if(7==w){
				init += 1;
			}
		}
		if(1==type1)init += 3;//纸票加3天
		return init;
	}
	
	/**
	 * 一小时内不能重复报价，判断是否到了可以报价的时间
	 * @param result	返回的数据
	 * @param price		报价
	 * @return		是否是可以报价的时间
	 */
	public boolean isTime(Map<String, Object> result, Price price, Integer cityId) {
		boolean isTime = false;
		try {
			Integer orgId = price.getOrgId();
			Integer priceTypeId = price.getPriceTypeId();
			if (cityId == null || cityId == 0) {
				cityId = Constant.DEFAULT_CITY_ID;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Long minute = null;		//一小时内不能重复报价
			Date date = new Date();
			Calendar calendar = Calendar.getInstance();	
			calendar.setTime(date);
			
			String createTime = sdf.format(date);		//当前日期
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar3 = Calendar.getInstance();
			Date date3 = sdf1.parse(createTime + " 08:30:00");	//早上8.30
			calendar3.setTime(date3);
			
			Calendar calendar4 = Calendar.getInstance();
			Date date4 = sdf1.parse(createTime + " 17:30:00"); //下午5.30
			calendar4.setTime(date4);
			//只能在早上8.30-下午5.30之间报价
			if ((calendar.getTimeInMillis() - calendar3.getTimeInMillis()) < 0 || (calendar.getTimeInMillis() - calendar4.getTimeInMillis() > 0)) {
				result.put("msg", "每天只可以从8：30-17：30之间报价");
			} else {
				if (cityId != 0) {
					List<Price> list = priceService.getByOrgId(orgId, createTime, priceTypeId, cityId);
					if (list != null && list.size() > 0) {
						Boolean hasType = false;
						for(Price price1 : list) {
							if (price1.getPriceTypeId() == priceTypeId) {
								hasType = true;
							}
						}
						if (hasType) {						
							Calendar calendar2 = Calendar.getInstance();
							Date create_time = list.get(0).getCreateTime();
							calendar2.setTime(create_time);
							minute = (calendar.getTimeInMillis() - calendar2.getTimeInMillis()) / (1000 * 60);
						}
					}
					if (minute != null && minute >= 0 && minute < 60) {				
						result.put("msg", "距离下次报价还剩" + (60 - minute) + "分钟");
					} else {
						isTime = true;
					}
				} else {
					isTime = true;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return isTime;
	}

	/**
	 * 根据传来的price，获取和它同一种type类型的price列表
	 * 快速报价-纸票
	 * @author BKY
	 */
	public List<Price> getPagerList(Price price) throws Exception {
		PriceType pt = priceTypeService.getById(price.getPriceTypeId());//当前提交的price的类型
		pt.setType3(null);//快速查询去除类型：50万以下0、50-100万1、100万2...
		Integer type2 = pt.getType2();//纸票or电票
		List<PriceType> ptList = priceTypeService.getByPriceType(pt);//与提交的price类型相同的price列表
		List<Price> priceList = new ArrayList<Price>();
		if (ptList != null && ptList.size() > 0) {
			for (PriceType ptype : ptList) {
				price.setPriceTypeId(ptype.getId());
				if (type2 != null && type2 == 0) {//纸票
					price = getPricePager(price, ptype);
				}
				Price p = (Price) BeanUtils.cloneBean(price);
				priceList.add(p);
			}
		}
		return priceList;
	}
	
	/**
	 * APP2.2  报价新增交易城市字段
	 * @param price 报价
	 * @param baojiaWay 报价方式
	 * @param cityId 交易城市主键ID
	 */
	public boolean savePrice(Price price, String baojiaWay, Integer cityId) {
	//app2.3新增预警系统
		
		boolean saveOrNot = true;
		try {
			price.setCreateTime(new Date());
			if (cityId != null && cityId != 0) {
				price.setCityId(Integer.valueOf(cityId));
			} else {
				cityId = Constant.DEFAULT_CITY_ID;
			}
			if (price.getWay() != null) {	//小票
				if("KSBJ".equals(baojiaWay)) {
					List<Price> plist = getPagerList(price);
					if (plist != null && plist.size() > 0) {
						priceService.saveByList(plist);
					}
				}else if ("CGBJ".equals(baojiaWay)) {
					priceService.saveEntity(price);
				}
			} else {
				if ("KSBJ".equals(baojiaWay)) {
					List<Price> plist = getPagerList(price);
					if (plist != null && plist.size() > 0) {
						priceService.saveByList(plist);
					}
				} else {
					priceService.saveEntity(price);
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			saveOrNot = false;
		}
		return saveOrNot;
	}
	
	/**
	 * 根据报价和报价类型 推出报价
	 * @param price 报价
	 * @param ptype 报价类型
	 * @return 计算后的报价
	 * @author BKY
	 */
	public Price getPricePager(Price price, PriceType ptype) {
		Integer day = Integer.valueOf(StringUtils.isNotBlank(ptype.getDay()) ? ptype.getDay() : "0");
		Integer way = price.getWay();
		DecimalFormat df = new DecimalFormat("#0.00");
		try {
			if (way != null && way == 0) {			//小票纸票A方式报价
				if (!"--".equals(price.getGuogu())) {
					double money = Double.parseDouble(price.getGuogu());
					double money1 = Double.parseDouble(price.getGuogu1());
					price.setGuogu2(df.format((100000 * day * (money / 30) / 1000) + money1));
				} else {
					price.setGuogu2(price.getGuogu1());
				}
				if (!"--".equals(price.getChengshang())) {
					double money = Double.parseDouble(price.getChengshang());
					double money1 = Double.parseDouble(price.getChengshang1());
					price.setChengshang2(df.format((100000 * day * (money / 30) / 1000) + money1));
				} else {
					price.setChengshang2(price.getChengshang1());
				}
				if (!"--".equals(price.getWaizi())) {
					double money = Double.parseDouble(price.getWaizi());
					double money1 = Double.parseDouble(price.getWaizi1());
					price.setWaizi2(df.format((100000 * day * (money / 30) / 1000) + money1));
				} else {
					price.setWaizi2(price.getWaizi1());
				}
				if (!"--".equals(price.getNonghe())) {
					double money = Double.parseDouble(price.getNonghe());
					double money1 = Double.parseDouble(price.getNonghe1());
					price.setNonghe2(df.format((100000 * day * (money / 30) / 1000) + money1));
				} else {
					price.setNonghe2(price.getNonghe1());
				}
				if (!"--".equals(price.getNongxin())) {
					double money = Double.parseDouble(price.getNongxin());
					double money1 = Double.parseDouble(price.getNongxin1());
					price.setNongxin2(df.format((100000 * day * (money / 30) / 1000) + money1));
				} else {
					price.setNongxin2(price.getNongxin1());
				}
				if (!"--".equals(price.getNongshang())) {
					double money = Double.parseDouble(price.getNongshang());
					double money1 = Double.parseDouble(price.getNongshang1());
					price.setNongshang2(df.format((100000 * day * (money / 30) / 1000) + money1));
				} else {
					price.setNongshang2(price.getNongshang1());
				}
				if (!"--".equals(price.getCunzhen())) {
					double money = Double.parseDouble(price.getCunzhen());
					double money1 = Double.parseDouble(price.getCunzhen1());
					price.setCunzhen2(df.format((100000 * day * (money / 30) / 1000) + money1));
				} else {
					price.setCunzhen2(price.getCunzhen1());
				}
			} else if(way != null && way == 1) {	//小票纸票B方式报价
				if ((!"--".equals(price.getGuogu2())) && (!"0".equals(price.getGuogu2())) && ("--".equals(price.getGuogu()) || "".equals(price.getGuogu().trim()))) {
					double money = Double.parseDouble(price.getGuogu2());
					price.setGuogu(df.format((money * 30 * 1000) / 100000 / day));
				}
				if ((!"--".equals(price.getChengshang2())) && (!"0".equals(price.getChengshang2())) && ("--".equals(price.getChengshang()) || "".equals(price.getChengshang().trim()))) {
					double money = Double.parseDouble(price.getChengshang2());
					price.setChengshang(df.format((money * 30 * 1000) / 100000 / day));
				}
				if ((!"--".equals(price.getWaizi2())) && (!"0".equals(price.getWaizi2())) && ("--".equals(price.getWaizi()) || "".equals(price.getWaizi().trim()))) {
					double money = Double.parseDouble(price.getWaizi2());
					price.setWaizi(df.format((money * 30 * 1000) / 100000 / day));
				}
				if ((!"--".equals(price.getNonghe2())) && (!"0".equals(price.getNonghe2())) && ("--".equals(price.getNonghe()) || "".equals(price.getNonghe().trim()))) {
					double money = Double.parseDouble(price.getNonghe2());
					price.setNonghe2(df.format((money * 30 * 1000) / 100000 / day));
				}
				if ((!"--".equals(price.getNongxin2())) && (!"0".equals(price.getNongxin2())) && ("--".equals(price.getNongxin()) || "".equals(price.getNongxin().trim()))) {
					double money = Double.parseDouble(price.getNongxin2());
					price.setNongxin(df.format((money * 30 * 1000) / 100000 / day));
				}
				if ((!"--".equals(price.getNongshang2())) && (!"0".equals(price.getNongshang2())) && ("--".equals(price.getNongshang()) || "".equals(price.getNongshang().trim()))) {
					double money = Double.parseDouble(price.getNongshang2());
					price.setNongshang(df.format((money * 30 * 1000) / 100000 / day));
				}
				if ((!"--".equals(price.getCunzhen2())) && (!"0".equals(price.getCunzhen2())) && ("--".equals(price.getCunzhen()) || "".equals(price.getCunzhen().trim()))) {
					double money = Double.parseDouble(price.getCunzhen2());
					price.setCunzhen(df.format((money * 30 * 1000) / 100000 / day));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return price;
	}

	/**
	 * 查看是否有报价城市
	 * @author ZY
	 * @param orgId
	 * @param day
	 * @param model
	 * @throws IOException
	 * 2016年9月28日上午10:42:34
	 */
	@RequestMapping("cityprice")
	public String cityPrice(Integer orgId,String day,Model model) throws IOException{
		Map<String, Object> result = new HashMap<String, Object>();
		if(orgId!=null){
			List<Map<String,Object>> list=priceService.getCityIdByOrgIdAndDate(orgId, day);
			if(list!=null)result.put("response", "success");
			else result.put("response", "fail");
		}else{
			result.put("response", "fail");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
}