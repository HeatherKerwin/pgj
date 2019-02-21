package com.utiexian.website.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.ry.core.entity.Actionlog;
import com.ry.core.entity.Member;
import com.ry.core.entity.Org;
import com.ry.core.entity.OrgLimit;
import com.ry.core.entity.Price;
import com.ry.core.entity.PriceType;
import com.ry.core.service.ActionlogService;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.OrgCityService;
import com.ry.core.service.OrgImageService;
import com.ry.core.service.OrgInfoService;
import com.ry.core.service.OrgLimitService;
import com.ry.core.service.OrgService;
import com.ry.core.service.PriceService;
import com.ry.core.service.PriceTypeService;
import com.ry.core.util.Constant;
import com.ry.util.DateUtil;
import com.ry.util.HttpUtil;
import com.ry.util.JacksonUtil;

/**
 * 机构报价
 * 
 * @author ZY
 */
@Controller
@RequestMapping("/price/")
public class PriceController {

	@Resource
	OrgService orgService;

	@Resource
	OrgInfoService orgInfoService;

	@Resource
	OrgImageService orgImageService;

	@Resource
	DiscountrecordService discountrecordService;

	@Resource
	PriceService priceService;

	@Resource
	PriceTypeService priceTypeService;

	@Resource
	OrgLimitService orgLimitService;

	@Resource
	OrgCityService orgCityService;

	@Resource
	private ActionlogService actionlogService;

	@RequestMapping("price")
	public String loginMember1(HttpServletRequest request) {
		Member member = (Member) request.getSession().getAttribute("member");
		Integer memberId = member.getId();
		boolean flag = false;
		Integer orgId = null;
		try {
			if (memberId == null)
				throw new Exception("数据异常");
			Map<String, Object> info = null;
			info = orgInfoService.getByMemberIdAndType(memberId, 1);
			if (info != null && info.get("state") != null) {
				request.getSession().setAttribute("orgId", info.get("org_id"));
				Object state = info.get("state");
				orgId = Integer.valueOf(info.get("org_id").toString());
				if (!"2".equals(state.toString())) {// 说明是：未认证、待审核、审核不通过
					flag = true;
				}
				if (!flag) {// 认证信息已经通过
					Map<String, Object> org_img = orgImageService.getByOrgId(orgId);
					if (org_img == null) {
						flag = true;
					}
				}
			} else {
				flag = true;
			}
			if (flag) {
				return "price/renzheng";
			} else {
				return "price/price";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}

	/**
	 * 跳转至商票报价
	 * 
	 * @author ZY
	 * @param request
	 *            2016年11月1日下午6:11:15
	 */
	@RequestMapping("change")
	public String tiaozhuan() {
		return "price/price_sp";
	}

	/**
	 * 获取额度
	 * 
	 * @author ZY
	 * @param request
	 * @param model
	 * @since 2016年10月28日 下午4:12:29
	 */
	@RequestMapping("get/orglimit")
	public String getOrgLimit(HttpServletRequest request, Model model) {
		Member member = (Member) request.getSession().getAttribute("member");
		Integer memberId = member.getId();
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Org org = orgService.getByMemberId(memberId);
			if (org == null)
				throw new Exception();
			OrgLimit limit = orgLimitService.getByOrgIdAndDay(org.getId(),
					DateUtil.formart(new Date(), DateUtil.FORMART3));
			if (limit != null) {
				result.put("data", limit);
				result.put("response", "success");
				result.put("msg", "加载成功");
			} else {
				result.put("response", "failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", "网络异常");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}

	/**
	 * 保存额度
	 * 
	 * @author ZY
	 * @param request
	 * @param price
	 * @param model
	 * @since 2016年10月28日 下午4:31:30
	 */
	@RequestMapping("save/orglimit")
	public String saveOrgLimit(HttpServletRequest request, Integer price, Model model,String remarkYp) {
		Member member = (Member) request.getSession().getAttribute("member");
		Integer memberId = member.getId();
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Org org = orgService.getByMemberId(memberId);
			if (org == null)
				throw new Exception();
			OrgLimit orgLimit = orgLimitService.getByOrgIdAndDay(org.getId(),
					DateUtil.formart(new Date(), DateUtil.FORMART3));
			if (orgLimit != null) {
				orgLimit.setPrice(price);
				orgLimit.setRemarkYp(remarkYp);
				orgLimit.setCreateTime(new Date());
			} else {
				orgLimit = new OrgLimit();
				Date date = new Date();
				orgLimit.setOrgId(org.getId());
				orgLimit.setPrice(price);
				orgLimit.setCreateTime(date);
				orgLimit.setRemarkYp(remarkYp);
				orgLimit.setDay(new SimpleDateFormat("yyyy-MM-dd").format(date));
			}
			orgLimitService.saveModel(orgLimit);
			result.put("response", "success");
			result.put("msg", "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "error");
			result.put("msg", "网络异常");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}

	/**
	 * 获取交易城市（含是否已报价cityId如果不是空则是报价了）
	 * 
	 * @author ZY
	 * @param request
	 * @param model
	 * @since 2016年10月29日 上午10:35:24
	 */
	@RequestMapping("get/orgcity")
	public String getOrgCity(HttpServletRequest request, Model model) {
		Member member = (Member) request.getSession().getAttribute("member");
		Integer memberId = member.getId();
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Org org = orgService.getByMemberId(memberId);
			if (org == null)
				throw new Exception();

			List<Map<String, Object>> data = orgCityService.getAndHasPriceByOrgId(org.getId(),
					DateUtil.formart(new Date(), DateUtil.FORMART3));
			result.put("data", data);
			result.put("response", "success");
			result.put("msg", "获取成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "error");
			result.put("msg", "网络异常");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}

	/**
	 * 获取有报价的城市
	 * 
	 * @author ZY
	 * @param priceType
	 *            报价条件
	 * @param request
	 * @param model
	 * @since 2016年10月30日 下午3:37:16
	 */
	@RequestMapping("get/haspricecity")
	public String hasPriceCity(PriceType priceType, HttpServletRequest request, Model model) {
		Member member = (Member) request.getSession().getAttribute("member");
		Integer memberId = member.getId();
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Org org = orgService.getByMemberId(memberId);
			List<Map<String, Object>> data = priceService.getByPriceTypeAndOrgIdGroupByCityId(priceType, org.getId());
			result.put("data", data);
			result.put("response", "success");
			result.put("msg", "获取成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", e.getMessage());
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}

	/**
	 * 保存报价（含多种混合报价）
	 * 
	 * @author ZY
	 * @param request
	 * @param price
	 * @param priceType
	 * @param model
	 * @since 2016年10月29日 下午2:03:04
	 */
	@RequestMapping("save/price")
	public String save(HttpServletRequest request, Integer[] cityIds, Price price, PriceType priceType, Model model) {
		Member member = (Member) request.getSession().getAttribute("member");
		Integer memberId = member.getId();
		Map<String, Object> result = new HashMap<String, Object>();
		List<Price> prices = new ArrayList<Price>();
		try {
			setEmpty(price);// 把空变为--
			price.setCreateTime(new Date());
			Org org = orgService.getByMemberId(memberId);
			if (org == null)
				throw new Exception();
			List<PriceType> types = priceTypeService.getByPriceType(priceType);
			List<Price> list = new ArrayList<Price>();
			if (types != null) {
				for(PriceType pt:types){
					price = getPricePager(price, pt);
					price.setOrgId(org.getId());
					price.setPriceTypeId(pt.getId());
					Price p = (Price) BeanUtils.cloneBean(price);
					list.add(p);
				}
			}
			if (cityIds.length == 0) {//如果没有报价城市传过来，则选择全国城市报价
				for (Price temp : list) {
					temp.setCityId(10000);
					Price p = (Price) BeanUtils.cloneBean(temp);
					prices.add(p);
				}
			} else {
				for (Integer cityId : cityIds) {
					for (Price temp : list) {
						temp.setCityId(cityId);
						Price p = (Price) BeanUtils.cloneBean(temp);
						prices.add(p);
					}
				}
			}
			for(Price p_:prices){//一小时内不能重复报价，判断是否到了可以报价的时间
				if(!isTime(result, p_, p_.getCityId()))throw new Exception(); 
			}
			priceService.saveByList(prices);
			result.put("response", "success");
			result.put("msg", "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "failed");
			if (result.get("msg") == null)
				result.put("msg", "保存失败");
		}
		priceActionlog(request, price.getOrgId());// 报价功能统计

		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}

	/**
	 * 根据主键获取报价
	 * 
	 * @author ZY
	 * @param priceId
	 *            报价主键
	 * @param model
	 * @since 2016年7月1日 上午11:10:35
	 */
	@RequestMapping("get/price/by/id")
	public String getPrice(Integer priceId, Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result.put("data", priceService.getById(priceId));
			result.put("response", "success");
			result.put("msg", "获取成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", "获取失败");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}

	/**
	 * 获取报价类型列表 （用于左侧菜单）
	 * 
	 * @author ZY
	 * @param request
	 *            获取用户主键
	 * @param cityId
	 *            城市ID
	 * @param model
	 * @since 2016年10月31日 下午3:38:29
	 */
	@RequestMapping("get/pricetypes")
	public String getPriceTypes(HttpServletRequest request, Integer cityId, Model model) {
		Member member = (Member) request.getSession().getAttribute("member");
		Integer memberId = member.getId();
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> priceTypes = new ArrayList<Map<String, Object>>();
		try {
			Org org = orgService.getByMemberId(memberId);
			if (org == null || org.getId() == null) {
				throw new Exception("请重新登录");// 找不到这个org
			} else {
				Integer orgId = org.getId();
				String createTime = DateUtil.formart(new Date(), DateUtil.FORMART3);
				List<Price> allPriceList = priceService.getByOrgId(orgId, createTime, null, null);// 获取本机构今天所有城市的报价
				if (allPriceList == null || allPriceList.size() == 0) {
					result.put("noPrice", true);// 放入一个参数 表示 此机构 当天所有城市均未报价
					throw new Exception("暂无报价");
				}
				List<Price> priceList = priceService.getByOrgId(orgId, createTime, null, cityId);// 获取指定城市、org在当天的所有报价（为了拿ptid）
				if (priceList == null || priceList.size() == 0) {
					throw new Exception("暂无报价");
				} else {
					Iterator<Price> iterator = priceList.iterator();
					while (iterator.hasNext()) {
						Price price = iterator.next();
						Integer ptId = price.getPriceTypeId();// priceType Id
						PriceType pt = priceTypeService.getById(ptId);
						String title;// priceType 标题
						Map<String, Object> typeMap = new HashMap<String, Object>();
						if (1 == pt.getId()) {// 大票纸票
							title = pt.getTitle1() + pt.getTitle2();
						} else if (2 == pt.getId() || 21 == pt.getId()) {// 大票电票
																			// 一年/半年
							title = pt.getTitle1() + pt.getTitle2() + "<br>" + pt.getTitle3();
						} else if(5 == pt.getId() || 8 == pt.getId() || 11 == pt.getId()){	//小纸票
							title = pt.getTitle1() + pt.getTitle2() + "<br>" + pt.getTitle4();
						} else {// 其他小票
							title = pt.getTitle1() + pt.getTitle2() + "<br>" + pt.getTitle3() + "<br>" + pt.getTitle4();
						}
						typeMap.put("ptId", ptId);// typeId
						typeMap.put("title", title);// 对应标题
						priceTypes.add(typeMap);// 加入结果
					}
					result.put("priceTypes", priceTypes);
					result.put("response", "success");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", e.getMessage());
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}

	/**
	 * 根据ptId获取天数
	 * 
	 * @author ZY
	 * @param ptId
	 * @param model
	 * @since 2016年10月31日
	 */
	@RequestMapping("get/daybyptid")
	public String getDayByPriceTypeId(Integer ptId, Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (ptId == null) {// 传值出错
			} else {
				PriceType pt = priceTypeService.getById(ptId);
				if (pt == null || pt.getDay() == null) {// ptId有错
				} else {
					result.put("day", Integer.valueOf(pt.getDay()));// 返回ptId对应的day
				}
			}
			result.put("response", "success");
			result.put("msg", "获取成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "error");
			result.put("msg", "网络异常");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}

	/**
	 * 获取报价信息
	 * 
	 * @author ZY
	 * @param ptId
	 * @param request
	 * @param cityId
	 * @param model
	 * @since 2016年10月31日 下午3:38:29
	 */
	@RequestMapping("get/price")
	public String getPrice(Integer ptId, HttpServletRequest request, Integer cityId, Model model) {
		Member member = (Member) request.getSession().getAttribute("member");
		Integer memberId = member.getId();
		// city->cityId
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Org org = orgService.getByMemberId(memberId);
			if (org == null || org.getId() == null) {
				throw new Exception("请重新登录");// 找不到org
			} else {
				Integer orgId = org.getId();
				String createTime = DateUtil.formart(new Date(), DateUtil.FORMART3);
				List<Price> priceList = priceService.getByOrgId(orgId, createTime, ptId, cityId);
				if (priceList == null || priceList.size() == 0) {
					throw new Exception("暂无报价");
				} else {
					Price price = priceList.get(0);
					result.put("response", "success");
					result.put("price", price);

					PriceType pt = priceTypeService.getById(ptId);
					result.put("priceType", pt);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", e.getMessage());
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}

	/**
	 * 修改报价信息
	 * 
	 * @author ZY
	 * @param request
	 * @param priceStr
	 * @param cityId
	 * @param model
	 * @since 2016年10月31日 下午3:38:29
	 */
	@RequestMapping("update/price")
	public String updatePrice(HttpServletRequest request, String priceStr, Integer cityId, Model model) {
		// city->cityId
		Price price = JSON.parseObject(priceStr, Price.class);
		setEmpty(price);// 把空变为--
		String baojiaWay = "CGBJ";// 更新一条数据一定是常规报价
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (cityId == null || "".equals(cityId)) {
				cityId = Constant.DEFAULT_CITY_ID;
			}
			boolean isTime = true;
			//isTime = isTime(result, price, cityId); 报价的时间是否满足时间条件
			if (isTime) {
				boolean saveOrNot = false;
				saveOrNot = savePrice(price, baojiaWay, cityId);// 常规报价
				if (saveOrNot) {
					result.put("msg", "保存成功");
					result.put("response", "success");
				} else {
					result.put("msg", "保存失败");
					result.put("response", "error");
				}
			} else {
				result.put("response", "error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", e.getMessage());
		}
		priceActionlog(request, price.getOrgId());// 报价功能统计

		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}

	/**
	 * 一小时内不能重复报价，判断是否到了可以报价的时间
	 * 
	 * @param result
	 *            返回的数据
	 * @param price
	 *            报价
	 * @return 是否是可以报价的时间
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
			Long minute = null; // 一小时内不能重复报价
			Date date = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);

			String createTime = sdf.format(date); // 当前日期
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar3 = Calendar.getInstance();
			Date date3 = sdf1.parse(createTime + " 08:30:00"); // 早上8.30
			calendar3.setTime(date3);

			Calendar calendar4 = Calendar.getInstance();
			Date date4 = sdf1.parse(createTime + " 17:30:00"); // 下午5.30
			calendar4.setTime(date4);
			// 只能在早上8.30-下午5.30之间报价
			if ((calendar.getTimeInMillis() - calendar3.getTimeInMillis()) < 0
					|| (calendar.getTimeInMillis() - calendar4.getTimeInMillis() > 0)) {
				result.put("msg", "每天只可以从8：30-17：30之间报价");
			} else {
				if (cityId != 0) {
					List<Price> list = priceService.getByOrgId(orgId, createTime, priceTypeId, cityId);
					if (list != null && list.size() > 0) {
						Boolean hasType = false;
						for (Price price1 : list) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isTime;
	}

	/**
	 * APP2.2 报价新增交易城市字段
	 * 
	 * @param price
	 *            报价
	 * @param baojiaWay
	 *            报价方式
	 * @param cityId
	 *            交易城市主键ID
	 */
	public boolean savePrice(Price price, String baojiaWay, Integer cityId) {
		boolean saveOrNot = true;
		try {
			price.setCreateTime(new Date());
			if (cityId != null && cityId != 0) {
				price.setCityId(Integer.valueOf(cityId));
			} else {
				cityId = Constant.DEFAULT_CITY_ID;
			}
			if (price.getWay() != null) { // 小票
				if ("KSBJ".equals(baojiaWay)) {
					List<Price> plist = getPagerList(price);
					if (plist != null && plist.size() > 0) {
						priceService.saveByList(plist);
					}
				} else if ("CGBJ".equals(baojiaWay)) {
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
	 * 根据传来的price，获取和它同一种type类型的price列表 快速报价-纸票
	 * 
	 * @author ZY
	 */
	public List<Price> getPagerList(Price price) throws Exception {
		PriceType pt = priceTypeService.getById(price.getPriceTypeId());// 当前提交的price的类型
		Integer type2 = pt.getType2();// 纸票or电票
		List<PriceType> ptList = priceTypeService.getByPriceType(pt);// 与提交的price类型相同的price列表
		List<Price> priceList = new ArrayList<Price>();
		if (ptList != null && ptList.size() > 0) {
			for (PriceType ptype : ptList) {
				price.setPriceTypeId(ptype.getId());
				if (type2 != null && type2 == 0) {// 纸票
					price = getPricePager(price, ptype);
				}
				Price p = (Price) BeanUtils.cloneBean(price);
				priceList.add(p);
			}
		}
		return priceList;
	}

	/**
	 * 根据报价和报价类型 推出报价
	 * 
	 * @param price
	 *            报价
	 * @param ptype
	 *            报价类型
	 * @return 计算后的报价
	 * @author ZY
	 */
	public Price getPricePager(Price price, PriceType ptype) {
		Integer day = Integer.valueOf(StringUtils.isNotBlank(ptype.getDay()) ? ptype.getDay() : "0");
		Integer way = price.getWay();
		DecimalFormat df = new DecimalFormat("#0.00");
		try {
			if (way != null && way == 0) { // 小票纸票A方式报价
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
			} else if (way != null && way == 1) { // 小票纸票B方式报价
				if ((!"--".equals(price.getGuogu2())) && (!"0".equals(price.getGuogu2()))
						&& ("--".equals(price.getGuogu()) || "".equals(price.getGuogu().trim()))) {
					double money = Double.parseDouble(price.getGuogu2());
					price.setGuogu(df.format((money * 30 * 1000) / 100000 / day));
				}
				if ((!"--".equals(price.getChengshang2())) && (!"0".equals(price.getChengshang2()))
						&& ("--".equals(price.getChengshang()) || "".equals(price.getChengshang().trim()))) {
					double money = Double.parseDouble(price.getChengshang2());
					price.setChengshang(df.format((money * 30 * 1000) / 100000 / day));
				}
				if ((!"--".equals(price.getWaizi2())) && (!"0".equals(price.getWaizi2()))
						&& ("--".equals(price.getWaizi()) || "".equals(price.getWaizi().trim()))) {
					double money = Double.parseDouble(price.getWaizi2());
					price.setWaizi(df.format((money * 30 * 1000) / 100000 / day));
				}
				if ((!"--".equals(price.getNonghe2())) && (!"0".equals(price.getNonghe2()))
						&& ("--".equals(price.getNonghe()) || "".equals(price.getNonghe().trim()))) {
					double money = Double.parseDouble(price.getNonghe2());
					price.setNonghe(df.format((money * 30 * 1000) / 100000 / day));
				}
				if ((!"--".equals(price.getNongxin2())) && (!"0".equals(price.getNongxin2()))
						&& ("--".equals(price.getNongxin()) || "".equals(price.getNongxin().trim()))) {
					double money = Double.parseDouble(price.getNongxin2());
					price.setNongxin(df.format((money * 30 * 1000) / 100000 / day));
				}
				if ((!"--".equals(price.getNongshang2())) && (!"0".equals(price.getNongshang2()))
						&& ("--".equals(price.getNongshang()) || "".equals(price.getNongshang().trim()))) {
					double money = Double.parseDouble(price.getNongshang2());
					price.setNongshang(df.format((money * 30 * 1000) / 100000 / day));
				}
				if ((!"--".equals(price.getCunzhen2())) && (!"0".equals(price.getCunzhen2()))
						&& ("--".equals(price.getCunzhen()) || "".equals(price.getCunzhen().trim()))) {
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
	 * 保存前置空为--
	 * 
	 * @author GXW
	 * @param price
	 * @since 2016年7月1日
	 */
	public void setEmpty(Price price) {
		if ("".equals(price.getGuogu()) || null == price.getGuogu())
			price.setGuogu("--");
		if ("".equals(price.getGuogu1()) || null == price.getGuogu1())
			price.setGuogu1("--");
		if ("".equals(price.getGuogu2()) || null == price.getGuogu2())
			price.setGuogu2("--");

		if ("".equals(price.getDashang()) || null == price.getDashang())
			price.setDashang("--");
		if ("".equals(price.getDashang1()) || null == price.getDashang1())
			price.setDashang1("--");
		if ("".equals(price.getDashang2()) || null == price.getDashang2())
			price.setDashang2("--");

		if ("".equals(price.getChengshang()) || null == price.getChengshang())
			price.setChengshang("--");
		if ("".equals(price.getChengshang1()) || null == price.getChengshang1())
			price.setChengshang1("--");
		if ("".equals(price.getChengshang2()) || null == price.getChengshang2())
			price.setChengshang2("--");

		if ("".equals(price.getWaizi()) || null == price.getWaizi())
			price.setWaizi("--");
		if ("".equals(price.getWaizi1()) || null == price.getWaizi1())
			price.setWaizi1("--");
		if ("".equals(price.getWaizi2()) || null == price.getWaizi2())
			price.setWaizi2("--");

		if ("".equals(price.getNongshang()) || null == price.getNongshang())
			price.setNongshang("--");
		if ("".equals(price.getNongshang1()) || null == price.getNongshang1())
			price.setNongshang1("--");
		if ("".equals(price.getNongshang2()) || null == price.getNongshang2())
			price.setNongshang2("--");

		if ("".equals(price.getNonghe()) || null == price.getNonghe())
			price.setNonghe("--");
		if ("".equals(price.getNonghe1()) || null == price.getNonghe1())
			price.setNonghe1("--");
		if ("".equals(price.getNonghe2()) || null == price.getNonghe2())
			price.setNonghe2("--");

		if ("".equals(price.getNongxin()) || null == price.getNongxin())
			price.setNongxin("--");
		if ("".equals(price.getNongxin1()) || null == price.getNongxin1())
			price.setNongxin1("--");
		if ("".equals(price.getNongxin2()) || null == price.getNongxin2())
			price.setNongxin2("--");

		if ("".equals(price.getCunzhen()) || null == price.getCunzhen())
			price.setCunzhen("--");
		if ("".equals(price.getCunzhen1()) || null == price.getCunzhen1())
			price.setCunzhen1("--");
		if ("".equals(price.getCunzhen2()) || null == price.getCunzhen2())
			price.setCunzhen2("--");
	}

	/**
	 * 报价统计（PC）
	 * 
	 * @author WKX
	 * @param request
	 * @param orgId
	 * @since 2016年7月4日 下午4:39:32
	 */
	public void priceActionlog(HttpServletRequest request, Integer orgId) {
		String ipadr = HttpUtil.getIpAddr(request);
		try {
			if (orgId == null)
				throw new Exception();
			Org org = orgService.getById(orgId);
			if (org != null) {
				Actionlog actionlog = new Actionlog();
				actionlog.setCode(Constant.BAOJIA);
				actionlog.setMemberId(org.getMemberId());
				actionlog.setFrom("PC");
				actionlog.setIpadr(ipadr);
				actionlog.setActiontime(new Date());

				// 报价功能统计一天只要记录一次就可以了
				List<Map<String, Object>> list = actionlogService.getByCodeAndMemberIdAndDayAndFrom(actionlog.getCode(),
						actionlog.getMemberId(), DateUtil.formart(new Date(), DateUtil.FORMART3), actionlog.getFrom());
				if (list == null || list.size() == 0) {
					actionlogService.addActionlog(actionlog);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 去开户
	 * @param request
	 * @return
	 */
	@RequestMapping("/toAccount")
	public String ToAccount(HttpServletRequest request) {
		return "price/renzheng";
	}
	
	/**
	 * 去报价页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/toPrice")
	public String ToPrice(HttpServletRequest request) {
		return "price/price";
	}
	
	
}