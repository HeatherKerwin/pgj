package com.ry.core.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ry.core.Enum.OrderState;
import com.ry.core.dao.DiscountrecordDao;
import com.ry.core.dao.DiscountrecordTaskDao;
import com.ry.core.dao.DistributeOrderDao;
import com.ry.core.dao.DistributeOrderTaskDao;
import com.ry.core.dao.PayRecordDao;
import com.ry.core.dao.TiexianNoticeDao;
import com.ry.core.entity.Dimension;
import com.ry.core.entity.Discountrecord;
import com.ry.core.entity.DiscountrecordTask.OperatorType;
import com.ry.core.entity.DistributeOrder;
import com.ry.core.entity.DistributeOrderTask;
import com.ry.core.entity.Historyprice;
import com.ry.core.entity.Org;
import com.ry.core.entity.OrgCity;
import com.ry.core.entity.OrgLimit;
import com.ry.core.entity.PayRecord;
import com.ry.core.entity.PriceType;
import com.ry.core.service.DimensionService;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.DistributeOrderService;
import com.ry.core.service.HistorypriceService;
import com.ry.core.service.MemberService;
import com.ry.core.service.OrgCityService;
import com.ry.core.service.OrgLimitService;
import com.ry.core.service.OrgService;
import com.ry.core.service.PaidanService;
import com.ry.core.service.PriceService;
import com.ry.util.DataMatchUtil;
import com.ry.util.DateUtil;
import com.ry.util.DistanceUtil;
import com.ry.util.PropertiesUtil;
import com.ry.util.SendMessagesUtil;
import com.ry.util.xml.XmlReader;

@Service
public class PaidanServiceImpl implements PaidanService{
	
	@Resource
	DiscountrecordDao discountrecordDao;
	
	@Resource
	DistributeOrderDao distributeOrderDao;
	
	@Resource
	TiexianNoticeDao tiexianNoticeDao;
	
	@Resource
	DistributeOrderTaskDao distributeOrderTaskDao;
	
	@Resource
	PayRecordDao payRecordDao;
	
	@Resource
	DiscountrecordService discountrecordService;
	
	@Resource
	DistributeOrderService distributeOrderService;
	
	@Resource
	HistorypriceService historypriceService;
	
	@Resource
	PriceService priceService;
	
	@Resource
	OrgService orgService;
	
	@Resource
	OrgCityService orgCityService;
	
	@Resource
	OrgLimitService orgLimitService;
	
	@Resource
	DimensionService dimensionService;
	
	@Resource
	MemberService memberService;
	
	@Resource
	DiscountrecordTaskDao discountrecordTaskDao;
	
	/**
	 * 机构报价（差额）
	 * @author WKX
	 * @param type1
	 * @param type2
	 * @param price
	 * @param historyprice
	 * @param differ
	 * @since 2016年5月24日 上午10:14:33
	 */
	private Float getChae(Integer type1,Integer type2,Map<String, Object> price,Float historyprice,Float differ){
		String key = DataMatchUtil.getNameByStateCDH(type2);
		if(!"--".equals(String.valueOf(price.get(key))) && String.valueOf(price.get(key)) != null){
			Float temp = Float.parseFloat(String.valueOf(price.get(key)));
			differ = temp - historyprice;//判断报价差额大小
			if(type1==2){//电票
				return differ*100;
			}else{//纸票
				return differ*12*10;
			}
		}else{
			return differ;
		}
	}
	
	/**
	 * 统计分数（汇总）
	 * @author WKX
	 * @param org
	 * @since 2016年5月25日 上午11:30:00
	 */
	private Map<String, Object> collect(Map<String, Object> org){
		Float amount = 0f;
		if(org!=null){
			if(org.get("scoreED")!=null)amount+=Float.parseFloat(String.valueOf(org.get("scoreED")));
			if(org.get("scoreCE")!=null)amount+=Float.parseFloat(String.valueOf(org.get("scoreCE")));
			if(org.get("scoreLL")!=null)amount+=Float.parseFloat(String.valueOf(org.get("scoreLL")));
			if(org.get("addED")!=null)amount+=Float.parseFloat(String.valueOf(org.get("addED")));
			if(org.get("addCITY")!=null)amount+=Float.parseFloat(String.valueOf(org.get("addCITY")));
			if(org.get("addPRICE")!=null)amount+=Float.parseFloat(String.valueOf(org.get("addPRICE")));
			if(org.get("dimensionFC")!=null)amount+=Float.parseFloat(String.valueOf(org.get("dimensionFC")));
			if(org.get("dimensionRC")!=null)amount+=Float.parseFloat(String.valueOf(org.get("dimensionRC")));
			if(org.get("dimensionFT")!=null)amount+=Float.parseFloat(String.valueOf(org.get("dimensionFT")));
			if(org.get("dimensionCS")!=null)amount+=Float.parseFloat(String.valueOf(org.get("dimensionCS")));
		}
		org.put("AMOUNT", amount);
		return org;
	}
	
	/**
	 * 读取参数
	 * @author WKX
	 * @since 2016年5月25日 下午2:15:16
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getConfigXML(){
		Map<String, Object> result = new HashMap<String, Object>();
		Object biliED = ((Map<String, Object>)XmlReader.getByType1("limit").get("limit")).get("weight");//额度
		Object biliCE = ((Map<String, Object>)XmlReader.getByType1("price").get("price")).get("weight");//报价差额
		Object biliLL = ((Map<String, Object>)XmlReader.getByType1("location").get("location")).get("weight");//交易城市
		Object biliAddED = ((Map<String, Object>)XmlReader.getByType1("newlimit").get("newlimit")).get("weight");//额度（新用户）
		Object biliAddCITY = ((Map<String, Object>)XmlReader.getByType1("newCity").get("newCity")).get("weight");//城市（新用户）
		Object biliAddPRICE = ((Map<String, Object>)XmlReader.getByType1("newPrice").get("newPrice")).get("weight");//报价（新用户）
		
		Object biliDimensionFC = ((Map<String, Object>)XmlReader.getByType1("orderComplate").get("orderComplate")).get("weight");//交易单数
		Object bilidimensionRC = ((Map<String, Object>)XmlReader.getByType1("orderRefused").get("orderRefused")).get("weight");//拒绝单数
		Object biliDimensionFT = ((Map<String, Object>)XmlReader.getByType1("completeHour").get("completeHour")).get("weight");//总共完成交易时间
		Object biliDimensionCS = ((Map<String, Object>)XmlReader.getByType1("remark").get("remark")).get("weight");//评价
		
		/** ---------------------额度配置参数------------------------  **/
		Map<String, Object> scoreedu1 = XmlReader.getByType2("limit", "above_1500");
		Object scoremin1 = scoreedu1.get("min");//1500
		Object score1_ = scoreedu1.get("score");//10
		
		Map<String, Object> scoreedu2 = XmlReader.getByType2("limit", "above_1200");
		Object scoremax2 = scoreedu2.get("max");//1500
		Object scoremin2 = scoreedu2.get("min");//1200
		Object score2_ = scoreedu2.get("score");//8
		
		Map<String, Object> scoreedu3 = XmlReader.getByType2("limit", "above_800");
		Object scoremax3 = scoreedu3.get("max");//1200
		Object scoremin3 = scoreedu3.get("min");//800
		Object score3_ = scoreedu3.get("score");//6
		
		Map<String, Object> scoreedu4 = XmlReader.getByType2("limit", "above_500");
		Object scoremax4 = scoreedu4.get("max");//800
		Object scoremin4 = scoreedu4.get("min");//500
		Object score4 = scoreedu4.get("score");//4
		
		Map<String, Object> scoreedu5 = XmlReader.getByType2("limit", "above_0");
		Object scoremax5 = scoreedu5.get("max");//500
		Object scoremin5 = scoreedu5.get("min");//0
		Object score5 = scoreedu5.get("score");//2
		
		Map<String, Object> scoreedu6 = XmlReader.getByType2("limit", "equals_0");
		Object scoremax6 = scoreedu6.get("equals");//0
		Object score6 = scoreedu6.get("score");//0
		
		/** ---------------------报价差额配置参数------------------------  **/
		Map<String, Object> scoreMapno = XmlReader.getByType2("price", "noprice");
		Object scoreno = scoreMapno.get("score");//0
		
		Map<String, Object> scoreMapmax = XmlReader.getByType2("price", "above_max");
		Object minm = scoreMapmax.get("min");//3
		Object scorem = scoreMapmax.get("score");//1.8
		
		Map<String, Object> scoreMap3 = XmlReader.getByType2("price", "above_3.0");
		Object max3 = scoreMap3.get("max");//3.0
		Object min3 = scoreMap3.get("min");//2.5
		Object score3 = scoreMap3.get("score");//2.4
		
		Map<String, Object> scoreMap2 = XmlReader.getByType2("price", "above_2.5");
		Object max2 = scoreMap2.get("max");//2.5
		Object min2 = scoreMap2.get("min");//2.0
		Object score2 = scoreMap2.get("score");//3.0
		
		Map<String, Object> scoreMap20 = XmlReader.getByType2("price", "above_2.0");
		Object max20 = scoreMap20.get("max");//2.0
		Object min20 = scoreMap20.get("min");//1.5
		Object score20 = scoreMap20.get("score");//3.6
		
		Map<String, Object> scoreMap1 = XmlReader.getByType2("price", "above_1.5");
		Object max1 = scoreMap1.get("max");//1.5
		Object min1 = scoreMap1.get("min");//1.0
		Object score1 = scoreMap1.get("score");//4.0
		
		Map<String, Object> scoreMap10 = XmlReader.getByType2("price", "above_1.0");
		Object max10 = scoreMap10.get("max");//1.0
		Object min10 = scoreMap10.get("min");//0.5
		Object score10 = scoreMap10.get("score");//4.6
		
		Map<String, Object> scoreMap0 = XmlReader.getByType2("price", "above_0.5");
		Object max0 = scoreMap0.get("max");//0.5
		Object min0 = scoreMap0.get("min");//0
		Object score0 = scoreMap0.get("score");//5.2
		
		Map<String, Object> scoreMaps = XmlReader.getByType2("price", "standard");
		Object equals = scoreMaps.get("equals");//0
		Object scores = scoreMaps.get("score");//5.8
		
		Map<String, Object> scoreMapb0 = XmlReader.getByType2("price", "below_0.5");
		Object maxb0 = scoreMapb0.get("max");//0.5
		Object minb0 = scoreMapb0.get("min");//0
		Object scoreb0 = scoreMapb0.get("score");//6.4
		
		Map<String, Object> scoreMapb1 = XmlReader.getByType2("price", "below_1.0");
		Object maxb1 = scoreMapb1.get("max");//1
		Object minb1 = scoreMapb1.get("min");//0.5
		Object scoreb1 = scoreMapb1.get("score");//7
		
		Map<String, Object> scoreMapb15 = XmlReader.getByType2("price", "below_1.5");
		Object maxb15 = scoreMapb15.get("max");//1.5
		Object minb15 = scoreMapb15.get("min");//1
		Object scoreb15 = scoreMapb15.get("score");//7.6
		
		Map<String, Object> scoreMapb2 = XmlReader.getByType2("price", "below_2.0");
		Object maxb2 = scoreMapb2.get("max");//2
		Object minb2 = scoreMapb2.get("min");//1.5
		Object scoreb2 = scoreMapb2.get("score");//8.2
		
		Map<String, Object> scoreMapb25 = XmlReader.getByType2("price", "below_2.5");
		Object maxb25 = scoreMapb25.get("max");//2.5
		Object minb25 = scoreMapb25.get("min");//2
		Object scoreb25 = scoreMapb25.get("score");//8.8
		
		Map<String, Object> scoreMapb3 = XmlReader.getByType2("price", "below_3.0");
		Object maxb3 = scoreMapb3.get("max");//3
		Object minb3 = scoreMapb3.get("min");//2.5
		Object scoreb3 = scoreMapb3.get("score");//9.4
		
		Map<String, Object> scoreMapbmi = XmlReader.getByType2("price", "below_min");
		Object minbm = scoreMapbmi.get("min");//3
		Object scorebm = scoreMapbmi.get("score");//10
		
		/** --------------------------------地理位置-------------------------------------- **/
		Map<String, Object> loc_0 = XmlReader.getByType2("location", "distance_0");
		Object loc_0_s = loc_0.get("score");//0
		
		Map<String, Object> loc_1 = XmlReader.getByType2("location", "distance_1");
		Object loc_1_min = loc_1.get("min");//0
		Object loc_1_max = loc_1.get("max");//1
		Object loc_1_s = loc_1.get("score");//10.0
		
		Map<String, Object> loc_5 = XmlReader.getByType2("location", "distance_5");
		Object loc_5_min = loc_5.get("min");//1
		Object loc_5_max = loc_5.get("max");//5
		Object loc_5_s = loc_5.get("score");//8.6
		
		Map<String, Object> loc_9 = XmlReader.getByType2("location", "distance_9");
		Object loc_9_min = loc_9.get("min");//5
		Object loc_9_max = loc_9.get("max");//9
		Object loc_9_s = loc_9.get("score");//7.2
		
		Map<String, Object> loc_12 = XmlReader.getByType2("location", "distance_12");
		Object loc_12_min = loc_12.get("min");//9
		Object loc_12_max = loc_12.get("max");//12
		Object loc_12_s = loc_12.get("score");//5.8
		
		Map<String, Object> loc_15 = XmlReader.getByType2("location", "distance_15");
		Object loc_15_min = loc_15.get("min");//12
		Object loc_15_max = loc_15.get("max");//15
		Object loc_15_s = loc_15.get("score");//4.4
		
		Map<String, Object> loc_50 = XmlReader.getByType2("location", "distance_50");
		Object loc_50_min = loc_50.get("min");//15
		Object loc_50_max = loc_50.get("max");//50
		Object loc_50_s = loc_50.get("score");//3.0
		
		Map<String, Object> loc_max = XmlReader.getByType2("location", "distance_max");
		Object loc_max_min = loc_max.get("min");//1.6
		Object loc_max_s = loc_max.get("score");//1.6
		
		/** ---------------------新用户额度配置参数------------------------  **/
		Map<String, Object> newquota1 = XmlReader.getByType2("newlimit", "newabove_10");
		Object newquotamin1 = newquota1.get("min");//10000
		Object newquotascore1 = newquota1.get("score");//10
		
		Map<String, Object> newquota2 = XmlReader.getByType2("newlimit", "newabove_8");
		Object newquotamin2 = newquota2.get("min");//8000
		Object newquotamax2 = newquota2.get("max");//10000
		Object newquotascore2 = newquota2.get("score");//8
		
		Map<String, Object> newquota3 = XmlReader.getByType2("newlimit", "newabove_6");
		Object newquotamin3 = newquota3.get("min");//5000
		Object newquotamax3 = newquota3.get("max");//8000
		Object newquotascore3 = newquota3.get("score");//6
		
		Map<String, Object> newquota4 = XmlReader.getByType2("newlimit", "newabove_4");
		Object newquotamin4 = newquota4.get("min");//2000
		Object newquotamax4 = newquota4.get("max");//5000
		Object newquotascore4 = newquota4.get("score");//4
		
		Map<String, Object> newquota5 = XmlReader.getByType2("newlimit", "newabove_4");
		Object newquotamin5 = newquota5.get("min");//0
		Object newquotamax5 = newquota5.get("max");//2000
		Object newquotascore5 = newquota5.get("score");//2
		
		Map<String, Object> newquota6 = XmlReader.getByType2("newlimit", "newequals_0");
		Object newquotamin6 = newquota6.get("equals");//0
		Object newquotascore6 = newquota6.get("score");//0
		
		/** ---------------------新用交易城市数量配置参数------------------------  **/
		Map<String, Object> citynumber1 = XmlReader.getByType2("newCity", "cityabove_10");
		Object citynummin1 = citynumber1.get("min");//9
		Object citynumscore1 = citynumber1.get("score");//10
		
		Map<String, Object> citynumber2 = XmlReader.getByType2("newCity", "cityabove_8");
		Object citynummin2 = citynumber2.get("min");//6
		Object citynummax2 = citynumber2.get("max");//9
		Object citynumscore2 = citynumber2.get("score");//8
		
		Map<String, Object> citynumber3 = XmlReader.getByType2("newCity", "cityabove_6");
		Object citynummin3 = citynumber3.get("min");//4
		Object citynummax3 = citynumber3.get("max");//6
		Object citynumscore3 = citynumber3.get("score");//6
		
		Map<String, Object> citynumber4 = XmlReader.getByType2("newCity", "cityabove_4");
		Object citynummin4 = citynumber4.get("min");//2
		Object citynummax4 = citynumber4.get("max");//4
		Object citynumscore4 = citynumber4.get("score");//4
		
		Map<String, Object> citynumber5 = XmlReader.getByType2("newCity", "cityabove_2");
		Object citynummin5 = citynumber5.get("min");//0
		Object citynummax5 = citynumber5.get("max");//2
		Object citynumscore5 = citynumber5.get("score");//2
		
		Map<String, Object> citynumber6 = XmlReader.getByType2("newCity", "cityequals_0");
		Object cityequals = citynumber6.get("equals");//0
		Object citynumscore6 = citynumber6.get("score");//0
		
		/** ---------------------新用报价种类配置参数------------------------  **/
		Map<String, Object> price1 = XmlReader.getByType2("newPrice", "priceabove_10");
		Object pricemin1 = price1.get("min");//20
		Object pricescore1 = price1.get("score");//10
		
		Map<String, Object> price2 = XmlReader.getByType2("newPrice", "priceabove_8");
		Object pricemin2 = price2.get("min");//14
		Object pricemax2 = price2.get("max");//20
		Object pricescore2 = price2.get("score");//8
		
		Map<String, Object> price3 = XmlReader.getByType2("newPrice", "priceabove_6");
		Object pricemin3 = price3.get("min");//9
		Object pricemax3 = price3.get("max");//14
		Object pricescore3 = price3.get("score");//8
		
		Map<String, Object> price4 = XmlReader.getByType2("newPrice", "priceabove_4");
		Object pricemin4 = price4.get("min");//6
		Object pricemax4 = price4.get("max");//9
		Object pricescore4 = price4.get("score");//4
		
		Map<String, Object> price5 = XmlReader.getByType2("newPrice", "priceabove_2");
		Object pricemin5 = price5.get("min");//0
		Object pricemax5 = price5.get("max");//6
		Object pricescore5 = price5.get("score");//2
		
		Map<String, Object> price6 = XmlReader.getByType2("newPrice", "priceequals_0");
		Object pricemax6 = price6.get("equals");//0
		Object pricescore6 = price6.get("score");//0
		
		/** --------------------分数比例配置参数------------------------  **/
		result.put("biliED", biliED);
		result.put("biliCE", biliCE);
		result.put("biliLL", biliLL);
		result.put("biliAddED", biliAddED);
		result.put("biliAddCITY", biliAddCITY);
		result.put("biliAddPRICE", biliAddPRICE);
		
		result.put("biliDimensionFC", biliDimensionFC);
		result.put("bilidimensionRC", bilidimensionRC);
		result.put("biliDimensionFT", biliDimensionFT);
		result.put("biliDimensionCS", biliDimensionCS);
		
		/** ---------------------额度配置参数------------------------  **/
		result.put("scoremin1", scoremin1);
		result.put("score1_", score1_);
		
		result.put("scoremax2", scoremax2);
		result.put("scoremin2", scoremin2);
		result.put("score2_", score2_);
		
		result.put("scoremax3", scoremax3);
		result.put("scoremin3", scoremin3);
		result.put("score3_", score3_);
		
		result.put("scoremax4", scoremax4);
		result.put("scoremin4", scoremin4);
		result.put("score4", score4);
		
		result.put("scoremax5", scoremax5);
		result.put("scoremin5", scoremin5);
		result.put("score5", score5);
		
		result.put("scoremax6", scoremax6);
		result.put("score6", score6);
		
		/** ---------------------报价差额配置参数------------------------  **/
		result.put("scoreno", scoreno);
		
		result.put("minm", minm);
		result.put("scorem", scorem);
		
		result.put("max3", max3);
		result.put("min3", min3);
		result.put("score3", score3);
		
		result.put("max2", max2);
		result.put("min2", min2);
		result.put("score2", score2);
		
		result.put("max20", max20);
		result.put("min20", min20);
		result.put("score20", score20);
		
		result.put("max1", max1);
		result.put("min1", min1);
		result.put("score1", score1);
		
		result.put("max10", max10);
		result.put("min10", min10);
		result.put("score10", score10);
		
		result.put("max0", max0);
		result.put("min0", min0);
		result.put("score0", score0);
		
		result.put("equals", equals);
		result.put("scores", scores);
		
		result.put("maxb0", maxb0);
		result.put("minb0", minb0);
		result.put("scoreb0", scoreb0);
		
		result.put("maxb1", maxb1);
		result.put("minb1", minb1);
		result.put("scoreb1", scoreb1);
		
		result.put("maxb15", maxb15);
		result.put("minb15", minb15);
		result.put("scoreb15", scoreb15);
		
		result.put("maxb2", maxb2);
		result.put("minb2", minb2);
		result.put("scoreb2", scoreb2);
		
		result.put("maxb25", maxb25);
		result.put("minb25", minb25);
		result.put("scoreb25", scoreb25);
		
		result.put("maxb3", maxb3);
		result.put("minb3", minb3);
		result.put("scoreb3", scoreb3);
		
		result.put("minbm", minbm);
		result.put("scorebm", scorebm);
		
		/** --------------------------------地理位置-------------------------------------- **/
		result.put("loc_0_s", loc_0_s);
		
		result.put("loc_1_min", loc_1_min);
		result.put("loc_1_max", loc_1_max);
		result.put("loc_1_s", loc_1_s);
		
		result.put("loc_5_min", loc_5_min);
		result.put("loc_5_max", loc_5_max);
		result.put("loc_5_s", loc_5_s);
		
		result.put("loc_9_min", loc_9_min);
		result.put("loc_9_max", loc_9_max);
		result.put("loc_9_s", loc_9_s);
		
		result.put("loc_12_min", loc_12_min);
		result.put("loc_12_max", loc_12_max);
		result.put("loc_12_s", loc_12_s);
		
		result.put("loc_15_min", loc_15_min);
		result.put("loc_15_max", loc_15_max);
		result.put("loc_15_s", loc_15_s);
		
		result.put("loc_50_min", loc_50_min);
		result.put("loc_50_max", loc_50_max);
		result.put("loc_50_s", loc_50_s);
		
		result.put("loc_max_min", loc_max_min);
		result.put("loc_max_s", loc_max_s);
		
		/** ---------------------新用户额度配置参数------------------------  **/
		result.put("newquotamin1", newquotamin1);
		result.put("newquotascore1", newquotascore1);
		
		result.put("newquotamin2", newquotamin2);
		result.put("newquotamax2", newquotamax2);
		result.put("newquotascore2", newquotascore2);
		
		result.put("newquotamin3", newquotamin3);
		result.put("newquotamax3", newquotamax3);
		result.put("newquotascore3", newquotascore3);
		
		result.put("newquotamin4", newquotamin4);
		result.put("newquotamax4", newquotamax4);
		result.put("newquotascore4", newquotascore4);
		
		result.put("newquotamin5", newquotamin5);
		result.put("newquotamax5", newquotamax5);
		result.put("newquotascore5", newquotascore5);
		
		result.put("newquotamin6", newquotamin6);
		result.put("newquotascore6", newquotascore6);
		
		/** ---------------------新用交易城市数量配置参数------------------------  **/
		result.put("citynummin1", citynummin1);
		result.put("citynumscore1", citynumscore1);
		
		result.put("citynummin2", citynummin2);
		result.put("citynummax2", citynummax2);
		result.put("citynumscore2", citynumscore2);
		
		result.put("citynummin3", citynummin3);
		result.put("citynummax3", citynummax3);
		result.put("citynumscore3", citynumscore3);
		
		result.put("citynummin4", citynummin4);
		result.put("citynummax4", citynummax4);
		result.put("citynumscore4", citynumscore4);
		
		result.put("citynummin5", citynummin5);
		result.put("citynummax5", citynummax5);
		result.put("citynumscore5", citynumscore5);
		
		result.put("cityequals", cityequals);
		result.put("citynumscore6", citynumscore6);
		
		/** ---------------------新用报价种类配置参数------------------------  **/
		result.put("pricemin1", pricemin1);
		result.put("pricescore1", pricescore1);
		
		result.put("pricemin2", pricemin2);
		result.put("pricemax2", pricemax2);
		result.put("pricescore2", pricescore2);
		
		result.put("pricemin3", pricemin3);
		result.put("pricemax3", pricemax3);
		result.put("pricescore3", pricescore3);
		
		result.put("pricemin4", pricemin4);
		result.put("pricemax4", pricemax4);
		result.put("pricescore4", pricescore4);
		
		result.put("pricemin5", pricemin5);
		result.put("pricemax5", pricemax5);
		result.put("pricescore5", pricescore5);
		
		result.put("pricemax6", pricemax6);
		result.put("pricescore6", pricescore6);
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.PaidanService#updatePaidan(java.lang.Integer, java.util.Map)
	 */
	public Map<String,Object> updatePaidan(Integer id,Map<String,Object> config){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			if(!canPaidan())throw new Exception("不在派单时段内...");
			
			List<Map<String,Object>> lilvList = new ArrayList<Map<String,Object>>();
			/** --------------------分数比例配置参数------------------------  **/
			Object biliED = config.get("biliED");//额度
			Object biliCE = config.get("biliCE");//报价差额
			Object biliLL = config.get("biliLL");//交易城市
			Object biliAddED = config.get("biliAddED");//额度（新用户）
			Object biliAddCITY = config.get("biliAddCITY");//城市（新用户）
			Object biliAddPRICE = config.get("biliAddPRICE");//报价（新用户）
			
			Object biliDimensionFC = config.get("biliDimensionFC");//交易单数
			Object bilidimensionRC = config.get("bilidimensionRC");//拒绝单数
			Object biliDimensionFT = config.get("biliDimensionFT");//总共完成交易时间
			Object biliDimensionCS = config.get("biliDimensionCS");//评价
			
			/** ---------------------额度配置参数------------------------  **/
			Object scoremin1 = config.get("scoremin1");//1500
			Object score1_ = config.get("score1_");//10
			
			Object scoremax2 = config.get("scoremax2");//1500
			Object scoremin2 = config.get("scoremin2");//1200
			Object score2_ = config.get("score2_");//8
			
			Object scoremax3 = config.get("scoremax3");//1200
			Object scoremin3 = config.get("scoremin3");//800
			Object score3_ = config.get("score3_");//6
			
			Object scoremax4 = config.get("scoremax4");//800
			Object scoremin4 = config.get("scoremin4");//500
			Object score4 = config.get("score4");//4
			
			Object scoremax5 = config.get("scoremax5");//500
			Object scoremin5 = config.get("scoremin5");//0
			Object score5 = config.get("score5");//2
			
			Object scoremax6 = config.get("scoremax6");//0
			Object score6 = config.get("score6");//0
			
			/** ---------------------报价差额配置参数------------------------  **/
			Object scoreno = config.get("scoreno");//0
			
			Object minm = config.get("minm");//3
			Object scorem = config.get("scorem");//1.8
			
			Object max3 = config.get("max3");//3.0
			Object min3 = config.get("min3");//2.5
			Object score3 = config.get("score3");//2.4
			
			Object max2 = config.get("max2");//2.5
			Object min2 = config.get("min2");//2.0
			Object score2 = config.get("score2");//3.0
			
			Object max20 = config.get("max20");//2.0
			Object min20 = config.get("min20");//1.5
			Object score20 = config.get("score20");//3.6
			
			Object max1 = config.get("max1");//1.5
			Object min1 = config.get("min1");//1.0
			Object score1 = config.get("score1");//4.0
			
			Object max10 = config.get("max10");//1.0
			Object min10 = config.get("min10");//0.5
			Object score10 = config.get("score10");//4.6
			
			Object max0 = config.get("max0");//0.5
			Object min0 = config.get("min0");//0
			Object score0 = config.get("score0");//5.2
			
			Object equals = config.get("equals");//0
			Object scores = config.get("scores");//5.8
			
			Object maxb0 = config.get("maxb0");//0.5
			Object minb0 = config.get("minb0");//0
			Object scoreb0 = config.get("scoreb0");//6.4
			
			Object maxb1 = config.get("maxb1");//1
			Object minb1 = config.get("minb1");//0.5
			Object scoreb1 = config.get("scoreb1");//7
			
			Object maxb15 = config.get("maxb15");//1.5
			Object minb15 = config.get("minb15");//1
			Object scoreb15 = config.get("scoreb15");//7.6
			
			Object maxb2 = config.get("maxb2");//2
			Object minb2 = config.get("minb2");//1.5
			Object scoreb2 = config.get("scoreb2");//8.2
			
			Object maxb25 = config.get("maxb25");//2.5
			Object minb25 = config.get("minb25");//2
			Object scoreb25 = config.get("scoreb25");//8.8
			
			Object maxb3 = config.get("maxb3");//3
			Object minb3 = config.get("minb3");//2.5
			Object scoreb3 = config.get("scoreb3");//9.4
			
			Object minbm = config.get("minbm");//3
			Object scorebm = config.get("scorebm");//10
			
			/** --------------------------------地理位置-------------------------------------- **/
			Object loc_0_s = config.get("loc_0_s");//0
			
			Object loc_1_min = config.get("loc_1_min");//0
			Object loc_1_max = config.get("loc_1_max");//1
			Object loc_1_s = config.get("loc_1_s");//10.0
			
			Object loc_5_min = config.get("loc_5_min");//1
			Object loc_5_max = config.get("loc_5_max");//5
			Object loc_5_s = config.get("loc_5_s");//8.6
			
			Object loc_9_min = config.get("loc_9_min");//5
			Object loc_9_max = config.get("loc_9_max");//9
			Object loc_9_s = config.get("loc_9_s");//7.2
			
			Object loc_12_min = config.get("loc_12_min");//9
			Object loc_12_max = config.get("loc_12_max");//12
			Object loc_12_s = config.get("loc_12_s");//5.8
			
			Object loc_15_min = config.get("loc_15_min");//12
			Object loc_15_max = config.get("loc_15_max");//15
			Object loc_15_s = config.get("loc_15_s");//4.4
			
			Object loc_50_min = config.get("loc_50_min");//15
			Object loc_50_max = config.get("loc_50_max");//50
			Object loc_50_s = config.get("loc_50_s");//3.0
			
			Object loc_max_min = config.get("loc_max_min");//1.6
			Object loc_max_s = config.get("loc_max_s");//1.6
			
			/** ---------------------新用户额度配置参数------------------------  **/
			Object newquotamin1 = config.get("newquotamin1");//10000
			Object newquotascore1 = config.get("newquotascore1");//10
			
			Object newquotamin2 = config.get("newquotamin2");//8000
			Object newquotamax2 = config.get("newquotamax2");//10000
			Object newquotascore2 = config.get("newquotascore2");//8
			
			Object newquotamin3 = config.get("newquotamin3");//5000
			Object newquotamax3 = config.get("newquotamax3");//8000
			Object newquotascore3 = config.get("newquotascore3");//6
			
			Object newquotamin4 = config.get("newquotamin4");//2000
			Object newquotamax4 = config.get("newquotamax4");//5000
			Object newquotascore4 = config.get("newquotascore4");//4
			
			Object newquotamin5 = config.get("newquotamin5");//0
			Object newquotamax5 = config.get("newquotamax5");//2000
			Object newquotascore5 = config.get("newquotascore5");//2
			
			Object newquotamin6 = config.get("newquotamin6");//0
			Object newquotascore6 = config.get("newquotascore6");//0
			
			/** ---------------------新用交易城市数量配置参数------------------------  **/
			Object citynummin1 = config.get("citynummin1");//9
			Object citynumscore1 = config.get("citynumscore1");//10
			
			Object citynummin2 = config.get("citynummin2");//6
			Object citynummax2 = config.get("citynummax2");//9
			Object citynumscore2 = config.get("citynumscore2");//8
			
			Object citynummin3 = config.get("citynummin3");//4
			Object citynummax3 = config.get("citynummax3");//6
			Object citynumscore3 = config.get("citynumscore3");//6
			
			Object citynummin4 = config.get("citynummin4");//2
			Object citynummax4 = config.get("citynummax4");//4
			Object citynumscore4 = config.get("citynumscore4");//4
			
			Object citynummin5 = config.get("citynummin5");//0
			Object citynummax5 = config.get("citynummax5");//2
			Object citynumscore5 = config.get("citynumscore5");//2
			
			Object cityequals = config.get("cityequals");//0
			Object citynumscore6 = config.get("citynumscore6");//0
			
			/** ---------------------新用报价种类配置参数------------------------  **/
			Object pricemin1 = config.get("pricemin1");//20
			Object pricescore1 = config.get("pricescore1");//10
			
			Object pricemin2 = config.get("pricemin2");//14
			Object pricemax2 = config.get("pricemax2");//20
			Object pricescore2 = config.get("pricescore2");//8
			
			Object pricemin3 = config.get("pricemin3");//9
			Object pricemax3 = config.get("pricemax3");//14
			Object pricescore3 = config.get("pricescore3");//8
			
			Object pricemin4 = config.get("pricemin4");//6
			Object pricemax4 = config.get("pricemax4");//9
			Object pricescore4 = config.get("pricescore4");//4
			
			Object pricemin5 = config.get("pricemin5");//0
			Object pricemax5 = config.get("pricemax5");//6
			Object pricescore5 = config.get("pricescore5");//2
			
			Object pricemax6 = config.get("pricemax6");//0
			Object pricescore6 = config.get("pricescore6");//0
			
			Discountrecord discountrecord = discountrecordService.getById(id);
			if(discountrecord.getDeposit()!=null && discountrecord.getDepositState()==0){
				throw new Exception("该订单未支付");
			}
			//查看该企业是否也是机构端，派单时则忽略
			Integer currentOrgId = null;
			Org currentOrg = orgService.getByMemberId(discountrecord.getMemberid());
			if(currentOrg!=null)currentOrgId = currentOrg.getId();
			
			Historyprice query = new Historyprice();
			Integer type1 = discountrecord.getType1();//纸票、电票
			Integer type2 = discountrecord.getType2();//承兑行
			Float allmoney = discountrecord.getAllmoney();//总金额
			Float lon = discountrecord.getLongitude();//经度
			Float lat = discountrecord.getLatitude();//纬度
			
			PriceType pirceType = new PriceType();
			if(type1==1){//纸票
				pirceType.setType2(0);
			}else if(type1==2){//电票
				pirceType.setType2(1);
				if(allmoney >= 500){
					query.setType7(discountrecord.getAcceptTime());//承兑期限（0半年期、1一年期）
					pirceType.setType5(discountrecord.getAcceptTime());//承兑期限（0半年期、1一年期）
				}else{
					int shengDay = DateUtil.daysBetween(discountrecord.getBegintime(), discountrecord.getEndtime());//天数（对应几个月）
					int tzts = discountrecordService.getTzts(type1,discountrecord.getEndtime());//调整天数（根据票据类型）
					int jxts = shengDay + tzts;//计息天数
					//小于等于90天0、91-178天1、大于等于179天2
					if(allmoney<500){//大票没有票据属性
						if(179<=jxts){
							query.setType5(3);
							pirceType.setType4(2);
						}else if(91<=jxts && jxts<179){
							query.setType5(2);
							pirceType.setType4(1);
						}else if(0<=jxts && jxts<91){
							query.setType5(1);
							pirceType.setType4(0);
						}
		            }
				}
			}
			//票据金额 :1:500以上 2:100-500 3:50-100  4:<50
			if(allmoney >= 500){
				query.setType2(1);
				pirceType.setType1(0);
			}else if(100 <= allmoney && allmoney < 500){
				query.setType2(2);
				pirceType.setType3(2);
				pirceType.setType1(1);
			}else if(50 <= allmoney && allmoney < 100){
				query.setType2(3);
				pirceType.setType3(1);
				pirceType.setType1(1);
			}else{
				query.setType2(4);
				pirceType.setType3(0);
				pirceType.setType1(1);
			}
			
			Integer cityId = discountrecord.getCityId();
			
			query.setType6(type1);
			query.setType1(type2);
			query.setDay(DateUtil.formart(new Date(), DateUtil.FORMART3));
			
			//获取票管家的报价（平台报价）
			List<Historyprice> historyList = null;
			Historyprice historyList1 = new Historyprice();
			if(type1==2 && discountrecord.getTradeModel()!=null && discountrecord.getTradeModel()==0){//电票-先背书后打款（不需要根据城市获取'平台'报价）
				historyList = historypriceService.getListOrderByPriceAsc(query);//不限制城市（获取报价最低的）
			}else{
				query.setCityId(cityId);//交易城市（根据城市查找报价）
				historyList = historypriceService.getList(allmoney,query);
			}
			if(historyList==null || historyList.size()==0)throw new Exception("平台无报价");
			historyList1 = historyList.get(0);
			
			Float historyprice = Float.parseFloat(String.valueOf(historyList1.getPrice()));//报价
			
			//获取机构端报价
			List<Map<String, Object>> priceList = null;
			if(type1==2 && discountrecord.getTradeModel()!=null && discountrecord.getTradeModel()==0){//电票-先背书后打款（不需要根据城市获取'机构'报价）
				priceList = priceService.getPriceOrderByTypeGroupByOrgId(pirceType,DataMatchUtil.getNameByStateCDH(type2));//根据承兑行选最小的报价
			}else{
				priceList = priceService.getPriceGroupByOrgId(pirceType,cityId);
			}
			if(priceList==null || priceList.size()==0)throw new Exception("机构无报价");
			
			Map<String, Object> org = null;
			List<OrgCity> citys = null;
			List<DistributeOrder> oldDist = null;
			String ym = DateUtil.formart(new Date(), DateUtil.FORMART4);//年月格式（yyyy-MM）
			for(Map<String, Object> data1 : priceList){//data1  机构报价值
				Object orgId = data1.get("org_id");
				if(orgId==null)continue;
				Org o = orgService.getById(Integer.valueOf(orgId.toString()));
				if(o==null)continue;
				if(currentOrgId!=null && currentOrgId==o.getId())continue;//不把订单派送给自己
				org = new HashMap<String, Object>();
				org.put("orgId", orgId);
				
				//step.1 机构订单额度
				OrgLimit orgLimit_ = orgLimitService.getByOrgIdAndDay(o.getId(), DateUtil.formart(new Date(), DateUtil.FORMART3));
				
				if(orgLimit_!=null){
					Float price = 0F;
					Object num = 0;
					if(orgLimit_.getPrice()!=null)price = Float.valueOf(orgLimit_.getPrice());
					if(orgLimit_.getUsedPrice()!=null)price -= orgLimit_.getUsedPrice();
					if(Float.parseFloat(String.valueOf(scoremax6)) == price){
						num = score6;
					}else if(Float.parseFloat(String.valueOf(scoremin5)) <= price && price < Float.parseFloat(String.valueOf(scoremax5))){
						num = score5;
					}else if(Float.parseFloat(String.valueOf(scoremin4)) <= price && price < Float.parseFloat(String.valueOf(scoremax4))){
						num = score4;
					}else if(Float.parseFloat(String.valueOf(scoremin3)) <= price && price < Float.parseFloat(String.valueOf(scoremax3))){
						num = score3_;
					}else if(Float.parseFloat(String.valueOf(scoremin2)) <= price && price < Float.parseFloat(String.valueOf(scoremax2))){
						num = score2_;
					}else if(Float.parseFloat(String.valueOf(scoremin1)) <= price){
						num = score1_;
					}
					org.put("scoreED", Float.parseFloat(String.valueOf(num))*Float.valueOf(biliED.toString()));
				}else{
					continue;
				}
				//step.2 获取差额（机构与平台比对）
				Float differ = null;
				differ = getChae(type1, type2, data1, historyprice, differ);
				Object scoreCE = null;//差额分数
				if(differ != null){
					if(differ > 0){//高于标准值
						if( Float.parseFloat(String.valueOf(minm)) < differ ){
							scoreCE = scorem;
						}else if( Float.parseFloat(String.valueOf(min3)) < differ && differ <= Float.parseFloat(String.valueOf(max3)) ){
							scoreCE = score3;
						}else if( Float.parseFloat(String.valueOf(min2)) < differ && differ <= Float.parseFloat(String.valueOf(max2)) ){
							scoreCE = score2;
						}else if( Float.parseFloat(String.valueOf(min20)) < differ && differ <= Float.parseFloat(String.valueOf(max20)) ){
							scoreCE = score20;
						}else if( Float.parseFloat(String.valueOf(min1)) < differ && differ <= Float.parseFloat(String.valueOf(max1)) ){
							scoreCE = score1;
						}else if( Float.parseFloat(String.valueOf(min10)) < differ && differ <= Float.parseFloat(String.valueOf(max10)) ){
							scoreCE = score10;
						}else if( Float.parseFloat(String.valueOf(min0)) < differ && differ <= Float.parseFloat(String.valueOf(max0)) ){
							scoreCE = score0;
						}
					}else if(differ == 0){
						if( Float.parseFloat(String.valueOf(equals)) == differ){
							scoreCE = scores;
						}
					}else if(differ < 0){
						if( Float.parseFloat(String.valueOf(minb0)) < -differ && -differ <= Float.parseFloat(String.valueOf(maxb0)) ){
							scoreCE = scoreb0;
						}else if( Float.parseFloat(String.valueOf(minb1)) < -differ && -differ <= Float.parseFloat(String.valueOf(maxb1)) ){
							scoreCE = scoreb1;
						}else if( Float.parseFloat(String.valueOf(minb15)) < -differ && -differ <= Float.parseFloat(String.valueOf(maxb15)) ){
							scoreCE = scoreb15;
						}else if( Float.parseFloat(String.valueOf(minb2)) < -differ && -differ <= Float.parseFloat(String.valueOf(maxb2)) ){
							scoreCE = scoreb2;
						}else if( Float.parseFloat(String.valueOf(minb25)) < -differ && -differ <= Float.parseFloat(String.valueOf(maxb25)) ){
							scoreCE = scoreb25;
						}else if( Float.parseFloat(String.valueOf(minb3)) < -differ && -differ <= Float.parseFloat(String.valueOf(maxb3)) ){
							scoreCE = scoreb3;
						}else if( Float.parseFloat(String.valueOf(minbm)) < -differ ){
							scoreCE = scorebm;
						}
					}
				}else{
					scoreCE = scoreno;
				}
				org.put("scoreCE",Float.valueOf(scoreCE.toString())*Float.valueOf(biliCE.toString()));
				//step.3交易城市 
				if(type1==2 && discountrecord.getTradeModel()!=null && discountrecord.getTradeModel()==0){//先背书后打款（不需要计算距离）
					
				}else{
					citys = orgCityService.getByOrgId(o.getId());
					if(citys!=null && citys.size()>0 && lon!=null && lat!=null){
						Double d = null;
						for(OrgCity city:citys){
							if(city.getLongitude()!=null && city.getLatitude()!=null){
								Double temp = DistanceUtil.getDistance(city.getLatitude(), city.getLongitude(), lat, lon);
								if(d==null || d>temp){
									d = temp;
								}
							}
						}
						if(Integer.parseInt(data1.get("city_id").toString()) == 10000){
							d=0.10;
						}
						if(d==null){
							
						}else{
							Object scoreLL = loc_0_s;
							if(d>Float.parseFloat(String.valueOf(loc_max_min))){//大于50公里
								scoreLL = loc_max_s;
							}else if(Float.parseFloat(String.valueOf(loc_50_max))>=d && d>Float.parseFloat(String.valueOf(loc_50_min))){//小于等于50公里（大于15）
								scoreLL = loc_50_s;
							}else if(Float.parseFloat(String.valueOf(loc_15_max))>=d && d>Float.parseFloat(String.valueOf(loc_15_min))){//小于等于50公里（大于15）
								scoreLL = loc_15_s;
							}else if(Float.parseFloat(String.valueOf(loc_12_max))>=d && d>Float.parseFloat(String.valueOf(loc_12_min))){//小于等于50公里（大于15）
								scoreLL = loc_12_s;
							}else if(Float.parseFloat(String.valueOf(loc_9_max))>=d && d>Float.parseFloat(String.valueOf(loc_9_min))){//小于等于50公里（大于15）
								scoreLL = loc_9_s;
							}else if(Float.parseFloat(String.valueOf(loc_5_max))>=d && d>Float.parseFloat(String.valueOf(loc_5_min))){//小于等于50公里（大于15）
								scoreLL = loc_5_s;
							}else if(Float.parseFloat(String.valueOf(loc_1_max))>=d && d>Float.parseFloat(String.valueOf(loc_1_min))){//小于等于50公里（大于15）
								scoreLL = loc_1_s;
							}
							org.put("scoreLL", Float.valueOf(scoreLL.toString())*Float.valueOf(biliLL.toString()));
						}
					}else{
						
					}
				}
				/** --------------------------------------附加属性--------------------------------------- **/
				String ymd = DateUtil.formart(DateUtil.getPreviousMonthFirst(), DateUtil.FORMART3);
				Date flag = DateUtil.parser(ymd+" 00:00:00", DateUtil.FORMART);
				if(flag.getTime() <= o.getCreateTime().getTime()){//新用户属性
					org.put("FLAG", 0);//新用户
					//step.1 新用户添加额度
					SimpleDateFormat sf2 = new SimpleDateFormat(DateUtil.FORMART3);
					String timedate = sf2.format(new Date());
					OrgLimit orgLimit = orgLimitService.getByOrgIdAndDay(o.getId(), timedate);//获取该机构当天的额度
					Integer price = orgLimit.getPrice();
					Object newedu = null;
					if( Integer.parseInt(newquotamin1.toString()) <= price ){//新用户当天额度值判断
						newedu = newquotascore1;
					}else if( Integer.parseInt(newquotamin2.toString()) <= price &&  price < Integer.parseInt(newquotamax2.toString()) ){
						newedu = newquotascore2;
					}else if( Integer.parseInt(newquotamin3.toString()) <= price &&  price < Integer.parseInt(newquotamax3.toString()) ){
						newedu = newquotascore3;
					}else if( Integer.parseInt(newquotamin4.toString()) <= price &&  price < Integer.parseInt(newquotamax4.toString()) ){
						newedu = newquotascore4;
					}else if( Integer.parseInt(newquotamin5.toString()) < price &&  price < Integer.parseInt(newquotamax5.toString()) ){
						newedu = newquotascore5;
					}else if( Integer.parseInt(newquotamin6.toString()) == price ){
						newedu = newquotascore6;
					}
					org.put("addED", Float.valueOf(newedu.toString())*Float.valueOf(biliAddED.toString()));
					
					//step.2 新用户添加交易城市数量
					List<OrgCity> list = orgCityService.getByOrgId(o.getId());
					Integer num = list.size();
					Object citynum = null;
					if( Integer.parseInt(citynummin1.toString()) <= num ){//新用户添加交易城市数量值判断
						citynum = citynumscore1;
					}else if( Integer.parseInt(citynummin2.toString()) <= num &&  num < Integer.parseInt(citynummax2.toString()) ){
						citynum = citynumscore2;
					}else if( Integer.parseInt(citynummin3.toString()) <= num &&  num < Integer.parseInt(citynummax3.toString()) ){
						citynum = citynumscore3;
					}else if( Integer.parseInt(citynummin4.toString()) <= num &&  num < Integer.parseInt(citynummax4.toString()) ){
						citynum = citynumscore4;
					}else if( Integer.parseInt(citynummin5.toString()) < num &&  num < Integer.parseInt(citynummax5.toString()) ){
						citynum = citynumscore5;
					}else if( Integer.parseInt(cityequals.toString()) == num ){
						citynum = citynumscore6;
					}
					org.put("addCITY", Float.valueOf(citynum.toString())*Float.valueOf(biliAddCITY.toString()));
					
					//step.3 新用户报价种类
					List<Map<String, Object>> list1 = priceService.getPriceAndTypeByOrgId(o.getId(),null,null,null);
					Integer newprice = list1.size();
					Object pricetype = null;
					if( Integer.parseInt(pricemin1.toString()) <= newprice ){//新用户报价种类值判断
						pricetype = pricescore1;
					}else if( Integer.parseInt(pricemin2.toString()) < newprice &&  newprice < Integer.parseInt(pricemax2.toString()) ){
						pricetype = pricescore2;
					}else if( Integer.parseInt(pricemin3.toString()) < newprice &&  newprice < Integer.parseInt(pricemax3.toString()) ){
						pricetype = pricescore3;
					}else if( Integer.parseInt(pricemin4.toString()) < newprice &&  newprice < Integer.parseInt(pricemax4.toString()) ){
						pricetype = pricescore4;
					}else if( Integer.parseInt(pricemin5.toString()) < newprice &&  newprice < Integer.parseInt(pricemax5.toString()) ){
						pricetype = pricescore5;
					}else if( Integer.parseInt(pricemax6.toString()) == newprice ){
						pricetype = pricescore6;
					}
					org.put("addPRICE", Float.valueOf(pricetype.toString())*Float.valueOf(biliAddPRICE.toString()));
				}else{//交易属性、增值属性
					org.put("FLAG", 1);//老用户
					Dimension dimension = dimensionService.getByOrgIdAndMonth(o.getId(), ym);
					Float fc = 0f;
					Float rc = 0f;
					Float ft = 0f;
					Float cs = 0f;
					if(dimension!=null){
						if(dimension.getFinishCount()!=null)fc = dimension.getFinishCount()*Float.valueOf(biliDimensionFC.toString());//交易单数（评分）
						if(dimension.getRefusedCount()!=null)rc = dimension.getRefusedCount()*Float.valueOf(bilidimensionRC.toString());//拒绝单数（评分）
						if(dimension.getFinishTime()!=null)ft = dimension.getFinishTime()*Float.valueOf(biliDimensionFT.toString());//总共完成交易时间（评分）
						if(dimension.getComments()!=null)cs = dimension.getComments()*Float.valueOf(biliDimensionCS.toString());//评价（评分）
					}
					org.put("dimensionFC",fc);
					org.put("dimensionRC",rc);
					org.put("dimensionFT",ft);
					org.put("dimensionCS",cs);
				}
				oldDist = distributeOrderService.getByDcrdIdAndOrgId(id, o.getId());
				if(oldDist==null || oldDist.size()==0){//曾经派过单的机构就不在重复派单了
					lilvList.add(collect(org));
				}
			}
			result.put("data", savePaidan(id, lilvList));//派单
			result.put("response", "success");
			result.put("msg", "派单成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获取测试账户（用户内容账号线上测试订单，防止系统派单到其他账号上去）
	 * @param mobile 下单用户号码
	 * @param orgId 派单结构
	 * @since 2016年6月24日 上午11:26:22
	 */
	private Boolean notInTestMobile(String mobile,Object orgId){
		List<String> result = new ArrayList<String>();
		String mob = null;
		String src =  PropertiesUtil.getRyUtilValue("TESTMOBILE", null);
		if(StringUtils.hasText(src)){
			String[]res = src.split(",");
			if(res!=null && res.length>0)Collections.addAll(result, res);
		}
		Map<String,Object> member = memberService.getInfoByOrgId(Integer.valueOf(orgId.toString()));
		if(member!=null && member.get("mobile")!=null)mob = member.get("mobile").toString();
		
		if(result!=null && result.size()>0){
			if(result.contains(mobile)){
				if(!result.contains(mob)){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 派单（根据机构的维度）
	 * @throws Exception 
	 */
	private Map<String,Object> savePaidan(Integer dcrdId,List<Map<String,Object>> orgs) throws Exception{
		Map<String,Object> result = new HashMap<String, Object>();
		Discountrecord dis = discountrecordService.getById(dcrdId);
		DistributeOrder temp = distributeOrderService.getByDcrdId(dcrdId);
		if(temp!=null)throw new Exception("该订单显示已派送");
		
		if(dis!=null && orgs!=null && orgs.size()>0){//待确认（则派单）
			Object orgId_ = null;
			Float score = 0f;
			for(Map<String,Object> org:orgs){
				if(org!=null && org.get("AMOUNT")!=null && score<Float.valueOf(org.get("AMOUNT").toString())){
					if(notInTestMobile(dis.getMembermobile(),org.get("orgId")))continue;
					
					score = Float.valueOf(org.get("AMOUNT").toString());
					orgId_ = org.get("orgId");
				}
			}
			if(orgId_!=null){
				Integer orgId = Integer.valueOf(orgId_.toString());
				DistributeOrder order = new DistributeOrder();
				order.setDcrdId(dcrdId);
				order.setOrgId(orgId);
				order.setState(OrderState.BECONFIRM.getCode());
				order.setNo(discountrecordService.createDistributeOrderNo());
				order.setCreateTime(new Date());
				
				Date start = dis.getBegintime();
				Date end = dis.getEndtime();
				Integer type1 = dis.getType1();
				Float allmoney = dis.getAllmoney();
				
				Integer shengDay = DateUtil.daysBetween(start, end);//天数（对应几个月）
				Integer tzts = discountrecordService.getTzts(type1,end);//调整天数（根据票据类型）
				Integer jxts = shengDay + tzts;//计息天数
				
				//保存当前报价数据
				Map<String,Object> price = discountrecordService.getPrice(dis, orgId);
				if(price!=null){
					Object rate_ = price.get("rate");
					Object rate1_ = price.get("rate1");
					Object rate2_ = price.get("rate2");
					
					String rate = "--";
					String rate1 = "--";
					String rate2 = "--";
					
					if(rate_!=null)rate = rate_.toString();
					if(rate1_!=null)rate1 = rate1_.toString();
					if(rate2_!=null)rate2 = rate2_.toString();
					
					order.setPrice(rate);
					order.setPrice1(rate1);
					order.setPrice2(rate2);
					
					Float txlx = 0F;
					Float txje = 0F;
					if(1==type1){//纸票
						if(500<=allmoney){//大票
							Float r = 0F;
							if(StringUtils.hasText(rate) && !"--".equals(rate.trim())){
								r = (allmoney*10000)*jxts*((Float.valueOf(rate)/30)/1000);
							}
							txlx = r;
						}else{//小票
							Float r = 0F;
							if(StringUtils.hasText(rate) && !"--".equals(rate.trim())){
								r = (allmoney*10000)*jxts*((Float.valueOf(rate)/30)/1000);
								if(StringUtils.hasText(rate1) && !"--".equals(rate1.trim())){
									r += (allmoney/10)*Float.valueOf(rate1);
								}
							}else if(StringUtils.hasText(rate2) && !"--".equals(rate2.trim())){
								r = (allmoney/10)*Float.valueOf(rate2);
							}
							txlx = r;
						}
					}else{//电票
						Float r = 0F;
						if(StringUtils.hasText(rate) && !"--".equals(rate.trim())){//年利率
							r = ((allmoney*10000)*jxts*(Float.valueOf(rate)/100))/360;
						}
						txlx = r;
					}
					if (allmoney != null && txlx != null) {//计算贴现金额保留2位小数
						txje = (allmoney * 10000 - txlx);
						txje = (float) (Math.round(txje * 100)) / 100;
					}
					String tzts_ = "--";
					String jxts_ = "--";
					String txlx_ = "--";
					String txje_ = "--";
					if(tzts!=null)tzts_ = tzts.toString();
					if(jxts!=null)jxts_ = jxts.toString();
					if(txlx!=null)txlx_ = txlx.toString();
					if(txje!=null)txje_ = txje.toString();
					order.setTzts(tzts_);//调整天数
					order.setJxts(jxts_);//计息天数
					order.setTxlx(txlx_);//贴现利息
					order.setTxje(txje_);//贴现金额
					if(price.get("way")!=null){
						order.setWay(Integer.valueOf(price.get("way").toString()));
					}
				}else{
					order.setPrice("--");
					order.setPrice1("--");
					order.setPrice2("--");
				}
				
				distributeOrderDao.saveDistributeOrder(order);
				saveDistributeOrderTask(null,OperatorType.SYSTEM,order.getId(), OrderState.BECONFIRM.getCode(), "系统已派单");//记录task（机构）
				
				Map<String,Object> member = memberService.getInfoByOrgId(orgId);//机构端发短信
				Map<String,String> param = new HashMap<String, String>();
				param.put("type1",dis.getType1()==1?"纸票":"电票");
				param.put("type2",DataMatchUtil.getCDHByTypeFromNew(dis.getType2()));
				param.put("allmoney", dis.getAllmoney()!=null?dis.getAllmoney().toString():"");
				param.put("endtime",DateUtil.formart(dis.getEndtime(), "yyyy-MM-dd"));
				if(member!=null && member.get("mobile")!=null){
					SendMessagesUtil.sendMessage(member.get("mobile").toString(), "SMS_7765032", param);
				}
				result.put("orgId", orgId_);//接单的机构
			}else{
				throw new Exception("暂未派送");
			}
		}else{
			throw new Exception("暂未派送");
		}
		return result;
	}
	
	/**
	 * 保存派单（机构订单）task
	 * @param operatorId 操作员（系统派单无操作员）
	 * @param operatorType 操作员类型（ADMIN、MEMBER、SYSTEM）
	 * @param dtboId 主表主键
	 * @param state 状态
	 * @param remarks 备注
	 * @throws Exception
	 * @author WKX
	 */
	private void saveDistributeOrderTask(Integer operatorId,OperatorType operatorType,Integer dtboId,Integer state,String remarks) throws Exception{
		DistributeOrderTask task = new DistributeOrderTask();
		task.setOperatorId(operatorId);
		task.setOperatorType(operatorType);
		task.setDtboId(dtboId);
		task.setState(state);
		task.setRemarks(remarks);
		task.setCreateTime(new Date());
		distributeOrderTaskDao.saveModel(task);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.PaidanService#updateAndPayRecord(com.ry.core.entity.Discountrecord, java.lang.Integer, java.lang.String)
	 */
	public void updateAndPayRecord(Discountrecord discountrecord,Integer state,String des){
		if(discountrecord!=null){
			PayRecord payRecord = new PayRecord();
			payRecord.setPkId(discountrecord.getId());
			payRecord.setPkType("2");//外键类型：1查询查复inquiry_reply、2下单discountrecord、3派单distribute_order
			payRecord.setPayMoney(new BigDecimal(discountrecord.getDeposit()));//支付金额
			payRecord.setPayWay(discountrecord.getPayWay());//支付方式
			payRecord.setState(state);//支付状态
			payRecord.setDescription(des);//描述
			payRecord.setJyh(discountrecord.getJyh());//交易号
			payRecord.setCard(discountrecord.getCard());
			payRecordDao.savePayRecord(payRecord);//保存流水
			discountrecordDao.updateDiscountrecord(discountrecord);//更新订单
			
			try {
				//updatePaidan(discountrecord.getId(), getConfigXML());//派单[暂不系统派单]
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.PaidanService#updatePaidanAndOrderState(com.ry.core.entity.DistributeOrder, com.ry.core.entity.DistributeOrderTask, com.ry.core.entity.Discountrecord)
	 */
	@Override
	public Map<String,Object> updatePaidanAndOrderState(DistributeOrder diso, DistributeOrderTask task, Discountrecord dic) {
		try {
			distributeOrderService.updateDisAndSaveTask(diso, task, dic, null);
			//return updatePaidan(dic.getId(), getConfigXML());[暂不系统派单]
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.PaidanService#getNeedPaidan()
	 */
	public List<Map<String,Object>> getNeedPaidan(){
		return discountrecordDao.getNeedPaidan();
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.PaidanService#getOverrunByCreateTime(java.util.Date)
	 */
	public List<DistributeOrder> getOverrunByCreateTime(Date date){
		return distributeOrderDao.getOverrunByCreateTime(date);
	}
	
	/**
	 * 验证派单时间
	 * @author WKX
	 * @since 2016年6月17日 下午4:15:14
	 */
	private Boolean canPaidan() {
		try {
			Date now = new Date();
			String flag = DateUtil.formart(now , DateUtil.FORMART3);
			Date s = DateUtil.parser(flag+" 09:00:00", DateUtil.FORMART);
			Date b = DateUtil.parser(flag+" 16:00:00", DateUtil.FORMART);
			if(now.getTime()>s.getTime() && now.getTime()<b.getTime()){
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
}