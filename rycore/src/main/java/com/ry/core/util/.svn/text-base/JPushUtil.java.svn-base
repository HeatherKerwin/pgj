package com.ry.core.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.ry.core.entity.Systeminfo.Type;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * 消息推送工具类
 * @author WKX
 * 备注：详细方法注解见下方文档
 * https://github.com/jpush/jpush-api-java-client#maven-方式
 * 历史：
 * appKey ="f380f3b7bd161f6b8ab08aa2";
 * masterSecret = "004bb2c9fa908cc03aab7417";
 */
public class JPushUtil {
	
	private static Logger logger = Logger.getLogger(JPushUtil.class);
	
	private static final String appKey ="74749812632a52b5306e7b92";
	private static final String masterSecret = "ffc2e6f238da3206e47d75e3";
	
	public static final String TITLE = "Test from API example";
    public static final String ALERT = "这是一条信息";
    public static final String MSG_CONTENT = "Test from API Example - msgContent";
    
    /**
     * 推送开关（测试环境关闭）默认关闭
     */
    private static boolean pushSwitch = false;
    
    static{
    	String ps = Constant.properties.getProperty("PUSHSWITCH");
    	if("on".equals(ps)){
    		pushSwitch = true;
    	}
    }
    
    
    public static ExecutorService pool;
	public static synchronized ExecutorService initPool(){
		if(pool!=null){
			return pool;
		}else{
			pool = Executors.newFixedThreadPool(5);
			return pool;
		}
	}
    
	public static class PushJob implements Runnable {
		public String moblie;//推送的用户
		public Type type;//消息类型
		public String des;//消息内容
		public PushJob(String moblie,Type type,String des) {
	        this.moblie = moblie;
	        this.type = type;
	        this.des = des;
	    }
		public void run() {
			try {
				JPushUtil.pushToAlias(moblie, des,type);
			} catch (APIConnectionException e) {
				e.printStackTrace();
			} catch (APIRequestException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 消息推送线程
	 * @author WKX
	 * @param moblie 用户手机号
	 * @param type 消息类型
	 * @param des 内容
	 */
	public static void doPushJob(String moblie,Type type,String des){
		PushJob job = new PushJob(moblie,type,des);
		initPool().execute(job);
	}
    
	public static PushPayload buildPushObject_all_all_alert() {
        return PushPayload.alertAll(ALERT);
    }
	
	/**
	 * 全平台发送
	 * @author WKX
	 * @param alias 发送给谁
	 * @param alert
	 */
	public static PushPayload buildPushObject_all_alias_alert(String alias,String alert) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.alert(alert))
                .build();
    }
	
	public static PushPayload buildPushObject_android_tag_alertWithTitle() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.tag("tag1"))
                .setNotification(Notification.android(ALERT, TITLE, null))
                .build();
    }
	
	public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.tag_and("tag1", "tag_all"))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(ALERT)
                                .setBadge(5)
                                .setSound("happy")
                                .addExtra("from", "JPush")
                                .build())
                        .build())
                 .setMessage(Message.content(MSG_CONTENT))
                 .setOptions(Options.newBuilder()
                         .setApnsProduction(true)
                         .build())
                 .build();
    }
	
	public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
                        .addAudienceTarget(AudienceTarget.tag("tag1", "tag2"))
                        .addAudienceTarget(AudienceTarget.alias("alias1", "alias2"))
                        .build())
                .setMessage(Message.newBuilder()
                        .setMsgContent(MSG_CONTENT)
                        .addExtra("from", "JPush")
                        .build())
                .build();
    }
	
	/**
	 * 推送给某人
	 * @author WKX
	 * @param alias 推送目标[此处对应用户主键]
	 * @param alert 提示
	 * @param type 类型（DISCOUNTRECORD订单；PREFERENTIALINFO优惠消息）
	 * @throws APIConnectionException
	 * @throws APIRequestException
	 */
	public static PushResult pushToAlias(String alias,String alert,Type type) throws APIConnectionException, APIRequestException{
		logger.info("[消息推送-pushToAlias进入方法]：推送给："+alias+"，标题："+alert+"，类型："+type);
		try {
			JPushClient jpushClient = new JPushClient(masterSecret, appKey);
			
			PushPayload payload = PushPayload.newBuilder()
			.setPlatform(Platform.android_ios())
			.setAudience(Audience.alias(alias))
			.setNotification(Notification.newBuilder()
					.addPlatformNotification(AndroidNotification.newBuilder().setAlert(alert).addExtra("type", type.getValue()).build())
					.addPlatformNotification(IosNotification.newBuilder().autoBadge().setSound("happy").setAlert(alert).addExtra("type", type.getValue()).build())
					.build())
			.setOptions(Options.newBuilder()
			                .setApnsProduction(true)
			                .build())
			.build();
			
			if(pushSwitch){
				return jpushClient.sendPush(payload);
			}else{
				return null;
			}
		} catch (APIConnectionException e) {
			e.printStackTrace();
			logger.error("[消息推送-pushToAlias异常]：推送给："+alias+"，标题："+alert+"，类型："+type+"。异常："+e.getMessage());
			throw e;
		} catch (APIRequestException e) {
			e.printStackTrace();
			logger.error("[消息推送-pushToAlias异常]：推送给："+alias+"，标题："+alert+"，类型："+type+"。异常："+e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 推送所有用户
	 * @author WKX
	 * @param alert 提示
	 * @param title 标题
	 * @param message 内容
	 * @param type 类型（DISCOUNTRECORD订单；PREFERENTIALINFO优惠消息）
	 * @throws APIConnectionException
	 * @throws APIRequestException
	 */
	public static PushResult pushAllUser(String alert,Type type) throws APIConnectionException, APIRequestException{
		JPushClient jpushClient = new JPushClient(masterSecret, appKey);
		
		PushPayload payload = PushPayload.newBuilder()
        .setPlatform(Platform.all())
        .setAudience(Audience.all())
        .setNotification(Notification.newBuilder()
        		.addPlatformNotification(AndroidNotification.newBuilder().setAlert(alert).addExtra("type", type.getValue()).build())
        		.addPlatformNotification(IosNotification.newBuilder().setAlert(alert).autoBadge().setSound("happy").addExtra("type", type.getValue()).build())
        		.build())
        .setOptions(Options.newBuilder()
                        .setApnsProduction(true)
                        .build())
        .build();
		
		if(pushSwitch){
			return jpushClient.sendPush(payload);
		}else{
			return null;
		}
	}
	
	/**
	 * 本地测试
	 * @author WKX
	 * @param args
	 */
	public static void main(String[] args) {
		//3085 陈小静
		//987 卜文辉
		//1686 狄元吉
		//3149 胡少青
	    try {
	    	PushResult result = pushToAlias("13818994981","优惠消息",Type.PREFERENTIALINFO);
	    	//pushAllUser("优惠消息",Type.DISCOUNTRECORD);
	    	System.out.println(result);
	    } catch (APIConnectionException e) {
	    	System.err.println(e.getMessage());
	    	e.printStackTrace();
	    } catch (APIRequestException e) {
	    	System.err.println(e.getMessage());
	    	e.printStackTrace();
	    }
	}
}