/**
* @Title: SiteServletContentListener.java
* @Package com.ry.rysite.listener
* @Description: TODO
* @author Ry-wk
* @date 2015年10月30日
* @version V1.0
*/
package com.ry.rycms.listener;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ry.core.entity.MessageUp;
import com.ry.core.service.MessageUpService;
import com.ry.core.util.Constant;
import com.ry.util.PropertiesUtil;
import com.taobao.api.internal.tmc.Message;
import com.taobao.api.internal.tmc.MessageHandler;
import com.taobao.api.internal.tmc.MessageStatus;
import com.taobao.api.internal.tmc.TmcClient;
import com.taobao.top.link.LinkException;

/**
 * @ClassName: SiteServletContentListener
 * @Description: TODO
 * @author Ry-wk
 * @date 2015年10月30日
 *
 */
public class SiteServletContentListener implements ServletContextListener{
	
	private static WebApplicationContext springContext;
	private static MessageUpService messageUpService;
	
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			//初始化存放项目真实路径
			PropertiesUtil.wwwroot = arg0.getServletContext().getRealPath("/");
			
			Constant.properties = PropertiesUtil.loadProperties(arg0.getServletContext().getRealPath("WEB-INF/classes/config.properties"));
//			Constant.UPLOAD_PATH = Constant.properties.getProperty("uploadpath")+Constant.UPLOAD_PATH ;
//			Constant.TEMP_PATH = Constant.properties.getProperty("uploadpath")+Constant.TEMP_PATH ;
			Constant.imgPath = Constant.properties.getProperty("preimgUrl");
			if("true".equals(Constant.properties.getProperty("meagess_up"))){
				SiteServletContentListener.messageUp(arg0);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 短信上行
	 * @throws LinkException
	 * @throws InterruptedException
	 */
	public static void messageUp(ServletContextEvent event) throws LinkException, InterruptedException{
		
		 springContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
         if(springContext != null){
         	messageUpService = (MessageUpService) springContext.getBean("messageUpService");
         }else{
        	 System.out.println("获取应用程序上下文失败!");
          return;
         }
		TmcClient client = new TmcClient("23282500", "215f4916892e24cf1af1b076fa371525", "default"); // 关于default参考消息分组说明
		client.setMessageHandler(new MessageHandler() {
		    public void onMessage(Message message, MessageStatus status) {
		        try {
		        	System.out.println(message.getContent());
		            System.out.println(message.getTopic());
		           
		            if(StringUtils.isNotBlank(message.getContent())){
			            JSONObject jsonObject = JSON.parseObject(message.getContent());
			    		MessageUp messageUp = new MessageUp();
			    		messageUp.setContent(jsonObject.getString("content").toString());
			    		messageUp.setSender(jsonObject.getString("sender").toString());
			    		messageUp.setDest_code(jsonObject.getString("dest_code").toString());
			    		messageUp.setSms_seq(jsonObject.getString("sms_seq").toString());
			    		messageUp.setSender_time(jsonObject.getString("sender_time").toString());
			    		((MessageUpService) messageUpService).saveModel(messageUp);
		            }	
		        } catch (Exception e) {
		            e.printStackTrace();
		            status.fail(); // 消息处理失败回滚，服务端需要重发
		          // 重试注意：不是所有的异常都需要系统重试。 
		          // 对于字段不全、主键冲突问题，导致写DB异常，不可重试，否则消息会一直重发
		          // 对于，由于网络问题，权限问题导致的失败，可重试。
		          // 重试时间 5分钟不等，不要滥用，否则会引起雪崩
		        }
		    }
		});
		client.connect("ws://mc.api.taobao.com"); // 消息环境地址：ws://mc.api.tbsandbox.com/
	}

}
