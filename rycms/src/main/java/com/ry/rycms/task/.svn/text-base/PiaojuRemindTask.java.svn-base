package com.ry.rycms.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ry.core.entity.Member;
import com.ry.core.entity.Systeminfo;
import com.ry.core.entity.Systeminfo.Type;
import com.ry.core.service.AccountrecordService;
import com.ry.core.service.MemberService;
import com.ry.core.service.SysteminfoService;
import com.ry.core.util.JPushUtil;

import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;

/**
 * 定时任务：每天执行一次,查询票据的到期时间(当前日期到三天后),copy到systeminfo中
 * 用于票据管家提醒
 * @author BKY
 */
public class PiaojuRemindTask {
	private static Logger logger = Logger.getLogger(PiaojuRemindTask.class);
	
	@Resource
	AccountrecordService accountrecordService;
	
	@Resource
	SysteminfoService systeminfoService;
	
	@Resource
	MemberService memberService;
	
	public void execute() {
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 2);	//当前日期的后天(第二天)
			date = calendar.getTime();
			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);	//当前日期的后天的明天(第三天)
			Date tiexiandate = calendar.getTime();
			String statue = "0";
			String tiexianType = "手动";
			String istiexian = "0";
			List<Map<String, Object>> list = accountrecordService.getForRemindExpire(date, tiexiandate, statue, tiexianType, istiexian);
			for(Map<String, Object> map : list) {
				Integer memberId = Integer.valueOf(map.get("memberid").toString());
				String allprice = map.get("allprice") != null ? map.get("allprice").toString() : "0";
				Date daoqishijian = (Date)map.get("daoqidate");
				String daoqidate = sdf.format(daoqishijian);
				String alert = "系统消息";
				String context = "票据管家提醒您，您的" + allprice + "万元的票据于" + daoqidate + "快到期，请前往贴现";
				doPushJob(memberId, Type.SYSTEM, alert, context);
			}
		} catch (Exception e) {
			logger.info("定时任务：票据提醒-提前三天查询三天后要到期的票据(accountrecord表到systeminfo表中)出错," + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建线程池
	 * @author WKX
	 */
	public static ExecutorService  pool;
	public static synchronized ExecutorService initPool(){
		if(pool!=null){
			return pool;
		}else{
			pool = Executors.newFixedThreadPool(5);
			return pool;
		}
	}
	
	/**
	 * 推送消息[并且生成消息]线程
	 * @author WKX
	 */
	public class PushJob implements Runnable {
		public Integer memberId;//用户主键
		public Integer operator;//操作[前台传值转枚举]
		public Integer discountrecordId;//订单主键[贴现]
		public Type type;//消息类型
		public String content;//消息内容
		public String alert;//推送通知[标题或内容]
		public PushJob(Integer memberId,Type type,String alert, String content) {
	        this.memberId = memberId;
	        this.alert = alert;
	        this.content = content;
	        this.type = type;
	    }
		public void run() {
			try {
				Systeminfo systeminfo = new Systeminfo();
				systeminfo.setMemberId(memberId);
				systeminfo.setType(type);
				systeminfo.setAlert(alert);
				systeminfo.setContent(content);
				systeminfo.setCreateTime(new Date());
				systeminfoService.addSysteminfo(systeminfo);
				
				Member member = memberService.getById(memberId);
				if(member!=null && StringUtils.isNotBlank(member.getMobile())){
					JPushUtil.pushToAlias(member.getMobile(), "【系统消息】",type);
				}
			} catch (APIConnectionException e) {
				logger.info("【异常】定时任务：票据提醒" + e.getMessage());
				e.printStackTrace();
			} catch (APIRequestException e) {
				e.printStackTrace();
				logger.info("【异常】定时任务：票据提醒" + e.getMessage());
			}
		}
	}
	
	/**
	 * 开启消息推送及保存线程[订单状态变更]
	 * @author WKX
	 * @param memberId 用户主键
	 * @param operator 操作标示
	 * @param discountrecordId 订单主键
	 * @param type 消息类型
	 * @param context 消息内容
	 * @param alert 消息标题
	 */
	private void doPushJob(Integer memberId,Type type,String alert, String context){
		PushJob job = new PushJob(memberId, type, alert, context);
		initPool().execute(job);
	}
}
