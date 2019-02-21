package com.ry.rycms.task;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ry.core.entity.Accountrecord;
import com.ry.core.service.AccountrecordService;

public class OrderStateChangeTask{
	
	private static Logger logger = Logger.getLogger(OrderStateChangeTask.class);
	
	@Resource
	private AccountrecordService accountrecordService;
	
	public void execute(){
		logger.info("------------------定时任务执行------------------");
		List<Accountrecord> list = accountrecordService.findAccountrecordByDaoqidate();
		if(list != null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				Accountrecord a = list.get(i);
				a.setIsTiexian("1");
				a.setOrderStatue("0");//过期
				a.setTiexiandate(new Date());
				accountrecordService.update(a);
			}
		}
	}
	
}
