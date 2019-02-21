package com.ry.rycms.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ry.core.entity.Accountrecord;
import com.ry.core.entity.Member;
import com.ry.core.entity.Org;
import com.ry.core.entity.Systeminfo;
import com.ry.core.entity.Systeminfo.Type;
import com.ry.core.service.AccountrecordService;
import com.ry.core.service.MemberService;
import com.ry.core.service.NoticerecordService;
import com.ry.core.service.OrgService;
import com.ry.core.service.SysteminfoService;
import com.ry.core.util.JPushUtil;
import com.ry.util.DateUtil;
import com.ry.util.SendMessagesUtil;

import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;

/**
 * 定时任务:查询noticerecord表三天后要提醒的数据
 * @author BKY
 *
 */
public class NoticerecordRemindTask {
	private static Logger logger = Logger.getLogger(NoticerecordRemindTask.class);
	
	@Resource
	SysteminfoService systeminfoService;
	
	@Resource
	NoticerecordService noticerecordService;
	
	@Resource
	MemberService memberService;
	
	@Resource
	AccountrecordService accountrecordService;
	
	@Resource
	OrgService orgService;
	
	public void execute() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			Date noticetime1 = DateUtil.addDays(3);
			String end = DateUtil.formart(noticetime1, DateUtil.FORMART3);
			Date noticetime2 = DateUtil.parser(end+" 23:59:59", DateUtil.FORMART);
			System.err.println("---------------");
			List<Map<String, Object>> noticerecordList = noticerecordService.getForRemindExpire(noticetime1, noticetime2);
			for(Map<String, Object> map : noticerecordList) {
				Integer memberId = Integer.valueOf(map.get("memberid").toString());
				String allprice = map.get("allprice") != null ? map.get("allprice").toString() : "0";
				Date noticetime = (Date)map.get("noticetime");	//提醒日期
				String noticedesc = "无";
				if(map.get("noticedesc")!=null)noticedesc = map.get("noticedesc").toString();
				
				Object fkId = map.get("fkId");
				if(fkId!=null){
					Accountrecord acc = accountrecordService.getAccountrecord(Integer.valueOf(fkId.toString()));
					if(acc!=null){
						Member member = null;
						if(acc.getBelong()==0){//企业
							member = memberService.getById(acc.getMemberid());
						}else if(acc.getBelong()==1){//机构
							Org org = orgService.getById(acc.getMemberid());
							if(org!=null){
								member = memberService.getById(org.getMemberId());
							}
						}
						Map<String,String> param = new HashMap<String, String>();
						param.put("allprice",map.get("allprice")!=null?map.get("allprice").toString():"");
						param.put("expiredate","");
						if(map.get("expiredate")!=null){
							String time = map.get("expiredate").toString();
							if(time.length()>10){
								time = time.substring(0, 10);
							}
							param.put("expiredate",time);
						}
						if(member!=null && member.getMobile()!=null){
							SendMessagesUtil.sendMessage(member.getMobile(), "SMS_11035222", param);
						}
					}
				}
				
				String alert = "系统消息";
				String noticedate = sdf.format(noticetime);
				String content = "您的票据金额为" + allprice + "万元，备注：" + noticedesc + "，提醒日期" + noticedate + "，请知晓";
				doPushJob(memberId, Type.SYSTEM, content, alert);
			}
		} catch (Exception e) {
			logger.info("定时任务:查询noticerecord表三天后要提醒的数据操作失败，" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建线程池
	 * @author WKX
	 */
	public static ExecutorService pool;
	public static synchronized ExecutorService initPool(){
		if (pool != null) {
			return pool;
		} else {
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
		public Type type;//消息类型
		public String content;//消息内容
		public String alert;//推送通知[标题或内容]
		public PushJob(Integer memberId, Type type, String content, String alert) {
			this.memberId = memberId;
			this.type = type;
			this.content = content;
			this.alert = alert;
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
				logger.info("【异常】定时任务：票据提醒" + e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	private void doPushJob(Integer memberId, Type type, String content, String alert) {
		PushJob job = new PushJob(memberId, type, content, alert);
		initPool().execute(job);
	}
}
