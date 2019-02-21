package com.ry.rycms.task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ry.core.entity.Dayprice;
import com.ry.core.entity.Historyprice;
import com.ry.core.service.DaypriceService;
import com.ry.core.service.HistorypriceService;
import com.ry.util.DateUtil;

/**
 * 报价，每天去三个时间点 作为历史价格
 * 定时任务：每天10:30/14:30/16:30 三个时间点 对 historyprice的当日报价进行备份
 * @author BKY
 */
public class BackupHistorypriceTask {
	private static Logger logger = Logger.getLogger(BackupHistorypriceTask.class);

	@Resource
	DaypriceService daypriceService;
	@Resource
	HistorypriceService historypriceService;
	
	public void execute() {
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
