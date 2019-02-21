package com.ry.rycms.task;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ry.core.entity.KeyWordCount;
import com.ry.core.service.ClickCountService;
import com.ry.core.service.KeyWordCountService;
import com.ry.util.DateUtil;

/**
 * 定时任务：每天统计昨天百度搜索关键字统计
 * @author WKX
 */
public class KeyWordCountTask {
	
	private static Logger logger = Logger.getLogger(KeyWordCountTask.class);
	
	@Resource
	ClickCountService clickCountService;
	
	@Resource
	KeyWordCountService keyWordCountService;
	
	public void execute() {
		try {
			//查询昨天的搜索数据
			Date start = DateUtil.addDays(new Date(),-1);
			List<Map<String, Object>> list = clickCountService.getCountByCurrentDate(start, new Date());
			if(list!=null && list.size()>0){
				KeyWordCount temp = null;
				String day = DateUtil.formart(start, DateUtil.FORMART3);
				for(Map<String, Object> map:list){
					temp = new KeyWordCount();
					temp.setDay(day);
					if(map.get("keyword")!=null)temp.setKeyword(map.get("keyword").toString());
					if(map.get("s_amount")!=null)temp.setsAmount(Integer.valueOf(map.get("s_amount").toString()));
					if(map.get("a_amount")!=null)temp.setaAmount(Integer.valueOf(map.get("a_amount").toString()));
					keyWordCountService.saveModel(temp);
				}
			}
		} catch (Exception e) {
			logger.info("定时任务：每天统计昨天百度搜索关键字统计出错," + e.getMessage());
			e.printStackTrace();
		}
	}
}