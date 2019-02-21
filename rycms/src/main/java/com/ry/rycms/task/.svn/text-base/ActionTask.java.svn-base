package com.ry.rycms.task;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ry.core.service.ActivityService;

public class ActionTask {
	
	private static Logger logger = Logger.getLogger(ActionTask.class);
	
	@Resource
	ActivityService activityService;
	
	public void execute(){
		logger.info("------------------定时任务：每天晚上统计积分添加开始------------------");
		String action [] = {"action_82","action_83","action2","action129","action_129","action41",
				"action6","action_6","action5","action_5","action4","action_4"};
		String title []={
		"银票报价","商票报价","询价","访问论坛","访问论坛","推荐好友","计算器","计算器","公催查询","公催查询","查联行号","查联行号"
		};
		Integer integral []={
				10,10,10,10,10,20,10,10,10,10,10,10
		};
		for (int i = 0; i < action.length; i++) {
			activityService.actionIntegral(integral[i],title[i],action[i] );
		}
		logger.info("------------------定时任务：统计积分添加结束------------------");
	}
}
