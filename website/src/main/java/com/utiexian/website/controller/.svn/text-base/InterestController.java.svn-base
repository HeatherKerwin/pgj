package com.utiexian.website.controller;

import java.text.ParseException;
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

import com.ry.core.entity.Historyprice;
import com.ry.core.entity.Notice;
import com.ry.core.entity.Region;
import com.ry.core.service.HistorypriceService;
import com.ry.core.service.RegionService;
import com.ry.core.service.TiexianNoticeService;
import com.ry.core.util.Constant;
import com.ry.util.DateUtil;
import com.ry.util.JacksonUtil;

@Controller
public class InterestController {
	
	@Resource
	TiexianNoticeService tiexianNoticeService;
	
	@Resource
	RegionService regionService;
	
	@Resource
	HistorypriceService historypriceService;
	
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
	public String myExcel(Integer type1,Integer type2,Integer flag,String allmoneys,String startDate,String endDate,String cityName,Integer cityId,Model model,Integer limit){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			Date s = DateUtil.parser(startDate, DateUtil.FORMART3);
			Date e = DateUtil.parser(endDate, DateUtil.FORMART3);
			int shengDay = DateUtil.daysBetween(s, e);//天数（对应几个月）
			
			Float allmoney = Float.parseFloat(allmoneys);
			
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
			
			int tzts = getTzts(type1,e,s);//调整天数（根据票据类型）
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
	 * @param type1
	 * @param end
	 * @throws ParseException 
	 * @since 2016年4月11日 下午7:49:50
	 */
	private int getTzts(Integer type1,Date end,Date start) throws ParseException{
		int init = 0;
		Notice notice = tiexianNoticeService.findFestivalByNowTime(end);//查询（当前日期在假期内）本年度提示
		if(notice!=null && notice.getEndDate()!=null){
			init = DateUtil.daysBetween(end, notice.getEndDate())+1;//天数（对应几个月）
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
		if(1==type1){
			//把Date类型的对象转换Calendar类型的对象  
			Calendar startCal = Calendar.getInstance();  
		    Calendar endCal = Calendar.getInstance();  
		  
		    startCal.setTime(start);  
		    endCal.setTime(end);  
		  
		    // 1.比较当前日期在年份中的周数是否相同  
		    if (startCal.get(Calendar.WEEK_OF_YEAR) == endCal.get(Calendar.WEEK_OF_YEAR)) {
	    		init += 0;
		    }else{
		    	init += 3;//纸票加3天
		    }
		}
		return init;
	}

}
