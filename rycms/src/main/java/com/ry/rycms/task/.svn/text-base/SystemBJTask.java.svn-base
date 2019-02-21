package com.ry.rycms.task;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ry.core.entity.Dayprice;
import com.ry.core.entity.Historyprice;
import com.ry.core.entity.PriceType;
import com.ry.core.service.DaypriceService;
import com.ry.core.service.DistributeOrderService;
import com.ry.core.service.HistorypriceService;
import com.ry.core.service.PriceService;
import com.ry.core.service.PriceTypeService;
import com.ry.util.DateUtil;
import com.ry.util.xml.XmlReader;

/**
 * 系统报价定时任务
 * 
 * @author GXW
 */
public class SystemBJTask {

	@Resource
	private PriceService priceService;

	@Resource
	private DistributeOrderService distributeOrderService;

	@Resource
	private PriceTypeService priceTypeService;
	
	@Resource
	private DaypriceService daypriceService;
	
	@Resource
	private HistorypriceService historypriceService;

	private Map<Integer, Double> orgScore;

	private static Logger logger = Logger.getLogger(SystemBJTask.class);

	public void execute() {
		String FORMART = "HH";
		SimpleDateFormat simple = new SimpleDateFormat(FORMART);
		Integer hour = Integer.valueOf(simple.format(new Date()));// 当前小时
		try {
			if (hour > 16 || hour < 8)
				throw new Exception("not in 8-16");// 仅8~16点间执行
			logger.info("------------------定时任务:备份historyprice报价------------------");
			setDayPrice();
			logger.info("------------------定时任务:系统报价------------------");
			List<Integer> cityList = priceService.getCityIdListByDate(new Date());//获取本日报价cityId数组
			if (cityList==null || cityList.size()==0)throw new Exception("no price now");//无报价
			orgScore = getOrgScore();//初始化本日机构分数
			List<PriceType> ptList = priceTypeService.getPriceType();//所有priceType
			for(Integer cityId:cityList){//按cityId遍历
				Iterator<PriceType> iterator = ptList.iterator();//循环所有type
				while (iterator.hasNext())
					processByType(iterator.next(),cityId);//按照type cityId处理
			}
		} catch (Exception e) {
			logger.error("系统报价异常：" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 处理、计算、存储
	 * @param pt 票据类型
	 * @param cityId 城市Id
	 * @author GXW
	 * @date 2016年5月26日
	 */
	public void processByType(PriceType pt,Integer cityId) throws Exception{
		String date = DateUtil.formart(new Date(), DateUtil.FORMART3);
		List<Map<String, Object>> price = priceService.getAllPrice(date, pt,cityId);// 获取所有报价
		if (price.isEmpty())
			return;// 8类均暂时无报价，过
		Map<Integer, Double> guogu = new HashMap<Integer, Double>();// 国股 下同
		Map<Integer, Double> chengshang = new HashMap<Integer, Double>();
		Map<Integer, Double> dashang = new HashMap<Integer, Double>();
		Map<Integer, Double> waizi = new HashMap<Integer, Double>();
		Map<Integer, Double> nongshang = new HashMap<Integer, Double>();
		Map<Integer, Double> nonghe = new HashMap<Integer, Double>();
		Map<Integer, Double> nongxin = new HashMap<Integer, Double>();
		Map<Integer, Double> cunzhen = new HashMap<Integer, Double>();
		Iterator<Map<String, Object>> iterator = price.iterator();
		// 遍历 当前类型报价 将8类报价放入对应MAP
		while (iterator.hasNext()) {
			Map<String, Object> current = iterator.next();
			Integer orgId = Integer.valueOf(current.get("org_id").toString());
			Double guoguV = null;
			Double chengshangV = null;
			Double dashangV = null;
			Double waiziV = null;
			Double nongshangV = null;
			Double nongheV = null;
			Double nongxinV = null;
			Double cunzhenV = null;
			if (current.get("way") != null && "1".equals(current.get("way").toString())) {
				Double day = Double.valueOf(pt.getDay());
				guoguV = transferPrice(current.get("guogu2"));// 国股 下同
				if (guoguV != null)
					guoguV = guoguV * 30 * 1000 / 100000 / Double.valueOf(pt.getDay());// 转为对应的利率 下同
				chengshangV = transferPrice(current.get("chengshang2"));
				if (chengshangV != null)
					chengshangV = chengshangV * 30 * 1000 / 100000 / day;
				dashangV = transferPrice(current.get("dashang2"));
				if (dashangV != null)
					dashangV = dashangV * 30 * 1000 / 100000 / day;
				waiziV = transferPrice(current.get("waizi2"));
				if (waiziV != null)
					waiziV = waiziV * 30 * 1000 / 100000 / day;
				nongshangV = transferPrice(current.get("nongshang2"));
				if (nongshangV != null)
					nongshangV = nongshangV * 30 * 1000 / 100000 / day;
				nongheV = transferPrice(current.get("nonghe2"));
				if (nongheV != null)
					nongheV = nongheV * 30 * 1000 / 100000 / day;
				nongxinV = transferPrice(current.get("nongxin2"));
				if (nongxinV != null)
					nongxinV = nongxinV * 30 * 1000 / 100000 / day;
				cunzhenV = transferPrice(current.get("cunzhen2"));
				if (cunzhenV != null)
					cunzhenV = cunzhenV * 30 * 1000 / 100000 / day;
			} else {// 利率方式报价
				guoguV = transferPrice(current.get("guogu"));// 国股 下同
				chengshangV = transferPrice(current.get("chengshang"));
				dashangV = transferPrice(current.get("dashang"));
				waiziV = transferPrice(current.get("waizi"));
				nongshangV = transferPrice(current.get("nongshang"));
				nongheV = transferPrice(current.get("nonghe"));
				nongxinV = transferPrice(current.get("nongxin"));
				cunzhenV = transferPrice(current.get("cunzhen"));
			}
			if (guoguV != null)
				guogu.put(orgId, guoguV);// 去除无报价，有报价orgId、报价放入guogu 下同
			if (chengshangV != null)
				chengshang.put(orgId, chengshangV);
			if (dashangV != null)
				dashang.put(orgId, dashangV);
			if (waiziV != null)
				waizi.put(orgId, waiziV);
			if (nongshangV != null)
				nongshang.put(orgId, nongshangV);
			if (nongheV != null)
				nonghe.put(orgId, nongheV);
			if (nongxinV != null)
				nongxin.put(orgId, nongxinV);
			if (cunzhenV != null)
				cunzhen.put(orgId, cunzhenV);
		}
		// 取权重集合
		Set<Integer> guoguIts = intersectionSet(orgScore.keySet(), guogu.keySet());// 去除无权重机构（取交集）下同
		Set<Integer> chengshangIts = intersectionSet(orgScore.keySet(), chengshang.keySet());
		Set<Integer> dashangIts = intersectionSet(orgScore.keySet(), dashang.keySet());
		Set<Integer> waiziIts = intersectionSet(orgScore.keySet(), waizi.keySet());
		Set<Integer> nongshangIts = intersectionSet(orgScore.keySet(), nongshang.keySet());
		Set<Integer> nongheIts = intersectionSet(orgScore.keySet(), nonghe.keySet());
		Set<Integer> nongxinIts = intersectionSet(orgScore.keySet(), nongxin.keySet());
		Set<Integer> cunzhenIts = intersectionSet(orgScore.keySet(), cunzhen.keySet());
		// 计算并保存
		computeNSave(guogu, guoguIts, pt, 1, cityId);
		computeNSave(chengshang, chengshangIts, pt, 2, cityId);
		computeNSave(waizi, waiziIts, pt, 3, cityId);
		computeNSave(nongshang, nongshangIts, pt, 4, cityId);
		computeNSave(nonghe, nongheIts, pt, 5, cityId);
		computeNSave(nongxin, nongxinIts, pt, 6, cityId);
		computeNSave(cunzhen, cunzhenIts, pt, 7, cityId);
		computeNSave(dashang, dashangIts, pt, 8, cityId);
	}

	/**
	 * 工具，计算并存储报价
	 * @param map 机构报价
	 * @param set 有报价且有权重的orgId集合
	 * @param pt PriceType
	 * @param cityId 城市ID
	 * @author GXW
	 * @date 2016年5月26日
	 */
	public void computeNSave(Map<Integer, Double> map, Set<Integer> set, PriceType pt, Integer type1, Integer cityId) throws Exception{
		if (map.size() == 0)// 此类无报价 过
			return;
		Double price;
		if (set.size() == 0) {// 报价机构均无权重 首先判断是否有60%以上相同
			price = judgeSixtyPercent(map);
			if (price != null) {// 存在超过60%以上的相同报价(如果只有1家，必然存储这家的报价)
				savePrice(price, pt, type1, cityId);
				return;
			} else {// 不存在60%，price=0.618*(M-m)+m;
				price = noSixtyPercent(map);
				savePrice(price, pt, type1, cityId);
				return;
			}
		} else {// 有报价&有权重
			price = standardPrice(map, set);
			savePrice(price, pt, type1, cityId);
			return;
		}
	}

	/**
	 * 工具，将计算的系统报价保存入库
	 * @param map 机构报价
	 * @param set 有报价且有权重的orgId集合
	 * @param cityId 城市ID
	 * @return Double 标准系统报价
	 * @author GXW
	 * @date 2016年5月26日
	 */
	public void savePrice(Double price, PriceType pt, Integer type1,Integer cityId) throws Exception{
		Dayprice dayprice = new Dayprice();
		dayprice.setCityId(cityId);//设置城市ID
		dayprice.setType1(type1);// 承兑行
		if (pt.getType3() != null && pt.getType3() != 4)
			dayprice.setType2(4 - pt.getType3());// 金额
		else
			dayprice.setType2(1);
		dayprice.setType3(1);// 买断
		dayprice.setType4(1);// 长三角
		if (pt.getType1() == 1)// 大票不需要时间字段
			dayprice.setType5(pt.getType4() + 1);// 天数 三个月 六个月 六个月+
		dayprice.setType6(pt.getType2() + 1);// 纸票电票
		dayprice.setType7(pt.getType5());//半年期 一年期
		dayprice.setDay(DateUtil.formart(new Date(), DateUtil.FORMART3));// 年月日
		String FORMART = "HH:";
		SimpleDateFormat simple = new SimpleDateFormat(FORMART);
		dayprice.setTime(simple.format(new Date()) + "30");// 时间点
		dayprice.setPrice(price.toString());// price
		if (pt.getType1() == 1) {// 如果是小票 存入price2
			Double p = price / 30 / 1000 * 100000 * Double.valueOf(pt.getDay());
			BigDecimal price2 = new BigDecimal(p).setScale(0, BigDecimal.ROUND_HALF_UP);
			dayprice.setPrice2(price2.toString());// price2
		}
		
		Historyprice historyprice = new Historyprice();
		historyprice.setCityId(dayprice.getCityId());
		historyprice.setType1(dayprice.getType1());
		historyprice.setType2(dayprice.getType2());
		historyprice.setType3(dayprice.getType3());
		historyprice.setType4(dayprice.getType4());
		historyprice.setType5(dayprice.getType5());
		historyprice.setType6(dayprice.getType6());
		historyprice.setType7(dayprice.getType7());//半年一年
		historyprice.setPrice(dayprice.getPrice());
		historyprice.setPrice1(dayprice.getPrice1());
		historyprice.setPrice2(dayprice.getPrice2());
		historyprice.setDay(dayprice.getDay());
		List<Dayprice> dList = new ArrayList<Dayprice>();
		dList.add(dayprice);
		daypriceService.addAllDayprice(dList);
		historypriceService.saveOrUpdate(historyprice);
	}

	/**
	 * 工具，标准系统报价（Σ权重*报价）(假如是1家，适应)
	 * @param map 机构报价
	 * @param set 有报价且有权重的orgId集合
	 * @return Double 标准系统报价
	 * @author GXW
	 * @date 2016年5月26日
	 */
	public Double standardPrice(Map<Integer, Double> map, Set<Integer> set) {
		Double totalScore = 0.0;
		Double price = 0.0;
		Iterator<Integer> iterator = set.iterator();
		while (iterator.hasNext()) {// 先计算总分
			totalScore += orgScore.get(iterator.next());
		}
		iterator = set.iterator();
		while (iterator.hasNext()) {// 计算权重,同时计算 Σ weight*price
			Integer orgId = iterator.next();
			Double orgWeight = orgScore.get(orgId) / totalScore;// 机构对应权重
			price += orgWeight * map.get(orgId);
		}
		return price;
	}

	/**
	 * 工具，无60%情况下计算系统报价
	 * @param map 机构报价
	 * @return Double 系统报价
	 * @author GXW
	 * @date 2016年5月26日
	 */
	public Double noSixtyPercent(Map<Integer, Double> map) {
		Double maxPrice = 0.0;
		Double minPrice = 99999999.9;
		Set<Integer> keySet = map.keySet();
		Iterator<Integer> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			Integer key = iterator.next();
			Double curPrice = map.get(key);
			if (maxPrice < curPrice)
				maxPrice = curPrice;
			if (minPrice > curPrice)
				minPrice = curPrice;
		}
		return 0.618 * (maxPrice - minPrice) + minPrice;
	}

	/**
	 * 工具，判断某一报价是否有60%相同
	 * @param map 机构报价
	 * @return Double,有->返回该值;无->返回null
	 * @author GXW
	 * @date 2016年5月26日
	 */
	public Double judgeSixtyPercent(Map<Integer, Double> map) {
		Map<Double, Integer> count = new HashMap<Double, Integer>();// 用于统计报价次数
																	// （报价，报价次数）
		Set<Integer> orgSet = map.keySet();
		Iterator<Integer> iterator = orgSet.iterator();
		while (iterator.hasNext()) {
			Integer orgId = iterator.next();
			Double price = map.get(orgId);
			if (count.containsKey(price)) {// 包含，次数加1
				Integer times = count.get(price);
				count.put(price, times + 1);
			} else {// 不包含，次数为1
				count.put(price, 1);
			}
		}
		// 统计完成，找出频率最高 (key,value)
		Set<Double> priceSet = count.keySet();
		Double maxPrice = 0.0;
		Integer maxTimes = 0;
		Iterator<Double> i = priceSet.iterator();
		while (i.hasNext()) {// 找出最大报价次数
			Double curPrice = i.next();
			Integer curTimes = count.get(curPrice);
			if (curTimes > maxTimes) {// max设为较高者
				maxPrice = curPrice;
				maxTimes = curTimes;
			}
		}
		Double maxRatio = Double.valueOf(maxTimes) / Double.valueOf(map.size());// 最大频度
		if (maxRatio > 0.6)
			return maxPrice;
		return null;
	}

	/**
	 * 工具，把Object转换为报价
	 * @param o 待转换对象
	 * @return Double 报价值，无报价返回null
	 * @author GXW
	 * @date 2016年5月25日
	 */
	public Double transferPrice(Object o) {
		if (o == null)
			return null;
		String str = o.toString();
		if (str != null && !"--".equals(str) && !"".equals(str)) {
			return Double.valueOf(str);
		}
		return null;
	}

	/**
	 * 工具，获取两个SET的交集
	 * @param setA
	 * @param setB
	 * @return intersectionSet A，B交集
	 * @author GXW
	 * @date 2016年5月25日
	 */
	public Set<Integer> intersectionSet(Set<Integer> setA, Set<Integer> setB) {
		Set<Integer> intersectionSet = new HashSet<Integer>();
		Iterator<Integer> iterA = setA.iterator();
		while (iterA.hasNext()) {
			Integer tempInner = iterA.next();
			if (setB.contains(tempInner)) {
				intersectionSet.add(tempInner);
			}
		}
		return intersectionSet;
	}

	/**
	 * 获取所有上月得分机构Map,Map内容为（orgId，score） score=dingdanScore+pingjiaScore
	 */
	public Map<Integer, Double> getOrgScore() {
		Map<Integer, Double> allScore = new HashMap<Integer, Double>();// org_id,Score
																		// （dingdanScore+pingjiaScore）
		try {
			Date startDate = DateUtil.getPreviousMonthFirst();// 上月第一天
			String sd = DateUtil.formart(startDate, DateUtil.FORMART3) + " 00:00:00";
			startDate = DateUtil.parser(sd, DateUtil.FORMART);
			Date endDate = DateUtil.getPreviousMonthEnd();// 上月最后一天
			String ed = DateUtil.formart(endDate, DateUtil.FORMART3) + " 23:59:59";
			endDate = DateUtil.parser(ed, DateUtil.FORMART);
			Integer state = 3;// 已完成状态
			List<Map<String, Object>> list = distributeOrderService.getListIdAndTotal(state, startDate, endDate);// 获取某段时间内的机构的订单数
			if (list != null && list.size() > 0) {
				// 遍历计算每一家机构对应的分值(机构权重因素1[dingdanScore]和机构权重因素2[pingjiaScore])
				for (Map<String, Object> map : list) {
					getWeight1ByMap(map);
					getWeight2ByMap(map);
					Double Score = Double.valueOf(map.get("dingdanScore").toString())
							+ Double.valueOf(map.get("pingjiaScore").toString());
					allScore.put(Integer.valueOf(map.get("org_id").toString()), Score);
				}
			}
			return allScore;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 机构权重因素1（30%）：成交单数
	 */
	public Map<String, Object> getWeight1ByMap(Map<String, Object> map) {
		Map<String, Object> below_2 = XmlReader.getByType2("orderComplate", "below_2");
		Integer below_2_min = Integer.valueOf(below_2.get("min").toString()),
				below_2_max = Integer.valueOf(below_2.get("max").toString());
		Map<String, Object> below_4 = XmlReader.getByType2("orderComplate", "below_4");
		Integer below_4_min = Integer.valueOf(below_4.get("min").toString()),
				below_4_max = Integer.valueOf(below_4.get("max").toString());
		Map<String, Object> below_6 = XmlReader.getByType2("orderComplate", "below_6");
		Integer below_6_min = Integer.valueOf(below_6.get("min").toString()),
				below_6_max = Integer.valueOf(below_6.get("max").toString());
		Map<String, Object> below_8 = XmlReader.getByType2("orderComplate", "below_8");
		Integer below_8_min = Integer.valueOf(below_8.get("min").toString()),
				below_8_max = Integer.valueOf(below_8.get("max").toString());
		Map<String, Object> below_10 = XmlReader.getByType2("orderComplate", "below_10");
		Integer below_10_min = Integer.valueOf(below_10.get("min").toString());

		Double weight1 = 0d;// 机构权重因素1(30%)
		Integer dingdan = Integer.valueOf(map.get("dingdan").toString());
		Integer score = 0;// 分数
		if (dingdan > below_2_min && dingdan <= below_2_max) {
			score = Integer.valueOf(below_2.get("score").toString());
		} else if (dingdan > below_4_min && dingdan <= below_4_max) {
			score = Integer.valueOf(below_4.get("score").toString());
		} else if (dingdan > below_6_min && dingdan <= below_6_max) {
			score = Integer.valueOf(below_6.get("score").toString());
		} else if (dingdan > below_8_min && dingdan <= below_8_max) {
			score = Integer.valueOf(below_8.get("score").toString());
		} else if (dingdan > below_10_min) {
			score = Integer.valueOf(below_10.get("score").toString());
		}
		weight1 = 0.3 * score;
		map.put("dingdanScore", weight1);
		return map;
	}

	/**
	 * 机构权重因素2（70%）：上月每个评价用户的分数总和 （一个星星两分）/ 评价用户总数
	 */
	public Map<String, Object> getWeight2ByMap(Map<String, Object> map) {
		DecimalFormat df = new DecimalFormat("#0.00");
		Double weight2 = 0d;// 机构权重因素2(70%)
		Integer score = 0;// 分数
		if (map.get("price") != null && !"0".equals(map.get("pingjia").toString())) {
			Double price = Double.valueOf(map.get("price").toString());
			Integer pingjia = Integer.valueOf(map.get("pingjia").toString());
			Map<String, Object> below_2 = XmlReader.getByType2("pingjiaScore", "below_2");
			Integer below_2_min = Integer.valueOf(below_2.get("min").toString()),
					below_2_max = Integer.valueOf(below_2.get("max").toString());
			Map<String, Object> below_4 = XmlReader.getByType2("pingjiaScore", "below_4");
			Integer below_4_min = Integer.valueOf(below_4.get("min").toString()),
					below_4_max = Integer.valueOf(below_4.get("max").toString());
			Map<String, Object> below_6 = XmlReader.getByType2("pingjiaScore", "below_6");
			Integer below_6_min = Integer.valueOf(below_6.get("min").toString()),
					below_6_max = Integer.valueOf(below_6.get("max").toString());
			Map<String, Object> below_8 = XmlReader.getByType2("pingjiaScore", "below_8");
			Integer below_8_min = Integer.valueOf(below_8.get("min").toString()),
					below_8_max = Integer.valueOf(below_8.get("max").toString());
			Map<String, Object> below_10 = XmlReader.getByType2("pingjiaScore", "below_10");
			Integer below_10_min = Integer.valueOf(below_10.get("min").toString());

			Double dingdan = price / pingjia; // 此处dingdan为俩数相除
			if (dingdan > below_2_min && dingdan <= below_2_max) {
				score = Integer.valueOf(below_2.get("score").toString());
			} else if (dingdan > below_4_min && dingdan <= below_4_max) {
				score = Integer.valueOf(below_4.get("score").toString());
			} else if (dingdan > below_6_min && dingdan <= below_6_max) {
				score = Integer.valueOf(below_6.get("score").toString());
			} else if (dingdan > below_8_min && dingdan <= below_8_max) {
				score = Integer.valueOf(below_8.get("score").toString());
			} else if (dingdan > below_10_min) {
				score = Integer.valueOf(below_10.get("score").toString());
			}
		}
		weight2 = 0.7 * score;
		map.put("pingjiaScore", df.format(weight2));
		return map;
	}

	public void setDayPrice() {
		logger.info("------------------定时任务：每天10:30/14:30/16:30 三个时间点 对 historyprice的当日报价进行备份------------------");
		try {
			Date date = new Date();
			String day = DateUtil.formart(date, DateUtil.FORMART3);
			List<Historyprice> historypriceList = historypriceService.findbyDay(day);
			if (historypriceList != null && historypriceList.size() > 0) {
				String time = DateUtil.formart(date, DateUtil.FORMART2);
				Date d = DateUtil.parser(time, DateUtil.FORMART2);
				Calendar cal = Calendar.getInstance();
				cal.setTime(d);
				cal.set(Calendar.MINUTE, 30);
				time = DateUtil.formart(cal.getTime(), DateUtil.FORMART2);
				time = time.substring(11, time.length());
				List<Dayprice> daypriceList = new ArrayList<Dayprice>();
				for(Historyprice historyprice : historypriceList) {
					Dayprice dayprice = new Dayprice();
					dayprice.setType1(historyprice.getType1());
					dayprice.setType2(historyprice.getType2());
					dayprice.setType3(historyprice.getType3());
					dayprice.setType4(historyprice.getType4());
					dayprice.setType5(historyprice.getType5());
					dayprice.setType6(historyprice.getType6());
					dayprice.setType7(historyprice.getType7());
					dayprice.setPrice(historyprice.getPrice());
					dayprice.setPrice1(historyprice.getPrice1());
					dayprice.setPrice2(historyprice.getPrice2());
					dayprice.setDay(historyprice.getDay());
					dayprice.setCityId(historyprice.getCityId());
					dayprice.setTime(time);
					daypriceList.add(dayprice);
				}
				daypriceService.addAllDayprice(daypriceList);
			}
		} catch (Exception e) {
			logger.info("定时任务：每天10:30/14:30/16:30 三个时间点 对 historyprice的当日报价进行备份操作失败");
			e.printStackTrace();
		}
	}
}
