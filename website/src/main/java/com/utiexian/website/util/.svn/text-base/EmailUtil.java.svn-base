package com.utiexian.website.util;

import java.io.StringWriter;
import java.security.Security;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class EmailUtil {
	private static Logger log = Logger.getLogger(EmailUtil.class);
	private static final int PORT = 465;
	
	private static final String FROM = "睿银金融数据";//发送者,显示的发件人名字
	private static final String USER = "service@utiexian.com";//发送者邮箱地址
	private static final String SERVER = "smtp.utiexian.com";
	private static final String PASSWORD = "Ry170401";//密码
	private static final String ENCODING = "UTF-8";//邮件编码
	
	public static void sendEmail(List<String> targetPerson,String title,String html) {
        try{
        	 Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
             final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
            
        	 Properties props = new Properties();
             props.put("mail.smtp.host", SERVER);//SMTP服务器地址
             props.put("mail.smtp.auth", "true");//SMTP服务器是否需要用户认证，默认为false
             props.put("mail.stmp.timeout", "2000");
             
             props.setProperty("mail.smtp.port", "465");//邮箱发送服务器端口,这里设置为465端口
             props.setProperty("mail.smtp.socketFactory.port", "465");
             
             props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
             props.setProperty("mail.smtp.socketFactory.fallback", "false");
             
             Session session = Session.getInstance(props, new Authenticator() { 
             	 // 验账账户
                 public PasswordAuthentication getPasswordAuthentication() {
                     return new PasswordAuthentication(USER,PASSWORD);
                 }
             });
        	MimeMessage msg = new MimeMessage(session);
            msg.setSentDate(new Date());
	        msg.setFrom(new InternetAddress(USER,FROM,ENCODING));
            
            //这里可以添加多个目的用户
            for (String person : targetPerson) {
                msg.addRecipient(Message.RecipientType.TO,new InternetAddress(person,null,ENCODING));
			}
            msg.setSubject(title, ENCODING);   
            //设置邮件内容格式为混合内容  
            MimeMultipart msgMultipart = new MimeMultipart("mixed");  
            MimeBodyPart content = new MimeBodyPart();  
            //设置html内容  
            content.setContent(html,"text/html;charset=GBK");
            msgMultipart.addBodyPart(content); 
            msg.setContent(msgMultipart);
            Transport.send(msg);
        }catch(Exception e){
        	e.printStackTrace();
        }
	}
	
	/**
	 * @author GJJ
	 * @date 2016年1月13日
	 * @param targetPerson
	 * @param title
	 * @param html
	 * @param file
	 * @return void
	 * @time 2016年1月13日
	 * @todo 发送带附件的邮件
	 */
	public static void sendEmail(List<String> targetPerson,String title,String html,String fileName) {
        try{
        	 Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
             final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        	
        	 Properties props = new Properties();
             props.put("mail.smtp.host", SERVER);//SMTP服务器地址
             props.put("mail.smtp.auth", "true");//SMTP服务器是否需要用户认证，默认为false
             props.put("mail.stmp.timeout", "2000");
             
             props.setProperty("mail.smtp.port", String.valueOf(PORT));//邮箱发送服务器端口,这里设置为465端口
             props.setProperty("mail.smtp.socketFactory.port", String.valueOf(PORT));
             
             props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
             props.setProperty("mail.smtp.socketFactory.fallback", "false");
             Session session = Session.getInstance(props, new Authenticator() { 
             	 // 验账账户
                 public PasswordAuthentication getPasswordAuthentication() {
                     return new PasswordAuthentication(USER,PASSWORD);
                 }
             });
        	MimeMessage msg = new MimeMessage(session);
            msg.setSentDate(new Date());
	        msg.setFrom(new InternetAddress(USER,FROM,ENCODING));
            
            //这里可以添加多个目的用户
            for (String person : targetPerson) {
                msg.addRecipient(Message.RecipientType.TO,new InternetAddress(person,null,ENCODING));
			}
            msg.setSubject(title, ENCODING);   
            //设置邮件内容格式为混合内容  
            MimeMultipart msgMultipart = new MimeMultipart("mixed");  
            MimeBodyPart content = new MimeBodyPart();  
            //设置发送邮件的附件
            BodyPart attach = new MimeBodyPart();
            DataSource data = new FileDataSource(fileName);
            attach.setDataHandler(new DataHandler(data));
            attach.setFileName(getFileName(fileName));
            //设置html内容  
            content.setContent(html,"text/html;charset=GBK");
            msgMultipart.addBodyPart(content); 
            msgMultipart.addBodyPart(attach); 
            msg.setContent(msgMultipart);
            Transport.send(msg);
        }catch(Exception e){
        	e.printStackTrace();
        }
	}
	
	/**
	 * 得到网页邮件
	 * @param request
	 * @return
	 * @throws TemplateException
	 */
	public static String getHtml(Configuration cfg,String templatePath,Map<String, Object> m){
		// 设置FreeMarker的模版文件位置
		try {
			Template t = null;
			t = cfg.getTemplate(templatePath);
			java.io.StringWriter w = new StringWriter();
			t.process(m, w);
			// 字符串流输出到屏幕
			return w.toString();
		} catch (Exception e) {
			log.error("邮件发送错误", e);
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 截取文件格式
	 * @author WKX
	 * @param file
	 * @since 2016年9月21日 下午5:20:13
	 */
	private static String getFileName(String file){
		String result = file;
		if(StringUtils.isNotBlank(file)){
			String temp = file.replace("\\", "/");
			String[] word = temp.split("/");
			if(word!=null && word.length>0){
				result = word[word.length-1];
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		List<String> p = new ArrayList<String>();
		p.add("951133083@qq.com");
		sendEmail(p,"test","<p style='font-style:italic'>this is a test!</p>");
	}
}