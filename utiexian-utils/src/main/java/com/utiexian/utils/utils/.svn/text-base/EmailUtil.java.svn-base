package com.utiexian.utils.utils;

import java.io.InputStream;
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
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.utiexian.utils.MyException;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class EmailUtil {
	private static Logger log = Logger.getLogger(EmailUtil.class);
	private static final String PORT = "465";
	
	private static final String FROM = "票据管家";//发送者,显示的发件人名字
	private static final String USER = "service@utiexian.com";//发送者邮箱地址
	private static final String SERVER = "smtp.utiexian.com";
	private static final String PASSWORD = "Ry170401";//密码
	private static final String ENCODING = "UTF-8";//邮件编码
	
	/**
	 * 得到网页邮件
	 * @param request
	 * @throws TemplateException
	 */
	public static String getHtml(Configuration cfg,String templatePath,Map<String, Object> m){
		try {//设置FreeMarker的模版文件位置
			Template t = null;
			t = cfg.getTemplate(templatePath);
			java.io.StringWriter w = new StringWriter();
			t.process(m, w);
			
			return w.toString();//字符串流输出到屏幕
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
	
	/**
	 * 发送邮件（附件）SSL
	 * @author WKX
	 * @param targetPerson 用户
	 * @param title 标题
	 * @param html 内容
	 * @param fileName 文件
	 */
	public static void sendEmail_SSL(List<String> targetPerson,String title,String html,String[] fileName) {
        try {
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
            
            Properties props = new Properties();//设置邮件会话参数
            props.setProperty("mail.smtp.host", SERVER);
            props.put("mail.smtp.auth", "true");
            props.put("mail.stmp.timeout", "2000");
            
            props.setProperty("mail.smtp.port", "465");//邮箱发送服务器端口,这里设置为465端口
            props.setProperty("mail.smtp.socketFactory.port", "465");
            
            props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            
            //获取到邮箱会话,利用匿名内部类的方式,将发送者邮箱用户名和密码授权给jvm
            Session session = Session.getDefaultInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                	return new PasswordAuthentication(USER,PASSWORD);
                }
            });
            MimeMessage msg = new MimeMessage(session);
            msg.setSentDate(new Date());
	        msg.setFrom(new InternetAddress(USER,FROM,ENCODING));
            
            for (String person : targetPerson) {//这里可以添加多个目的用户（to为收件人,cc为抄送,bcc为密送）
                msg.addRecipient(Message.RecipientType.TO,new InternetAddress(person,null,ENCODING));
			}
            msg.setSubject(title, ENCODING);   
            
            MimeMultipart msgMultipart = new MimeMultipart("mixed");//设置邮件内容格式为混合内容  
            MimeBodyPart content = new MimeBodyPart();  
            
            if(fileName!=null){//放文件
            	for(String file:fileName){
            		BodyPart attach = new MimeBodyPart();//设置发送邮件的附件
                    DataSource data = new FileDataSource(file);
                    attach.setDataHandler(new DataHandler(data));
                    attach.setFileName(getFileName(file));
                    msgMultipart.addBodyPart(attach);
            	}
            }
            content.setContent(html,"text/html;charset=GBK");//设置html内容  
            msgMultipart.addBodyPart(content); 
            msg.setContent(msgMultipart);
            Transport.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("邮件发送失败！请确认是否输入正确");
        }
    }
	
	/**
	 * 发送邮件（附件-Jar保内文件）SSL
	 * @author WKX
	 * @param targetPerson 用户
	 * @param title 标题
	 * @param html 内容
	 * @param is 文件（Jar内文件流数据）
	 * @param fileName 文件名称（类型）
	 */
	public static void sendEmail_SSL(List<String> targetPerson,String title,String html,InputStream[]is,String[] fileName) {
        try {
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
            
            Properties props = new Properties();//设置邮件会话参数
            props.setProperty("mail.smtp.host", SERVER);
            props.put("mail.smtp.auth", "true");
            props.put("mail.stmp.timeout", "2000");
            
            props.setProperty("mail.smtp.port", PORT);//邮箱发送服务器端口,这里设置为465端口
            props.setProperty("mail.smtp.socketFactory.port", PORT);
            
            props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            
            //获取到邮箱会话,利用匿名内部类的方式,将发送者邮箱用户名和密码授权给jvm
            Session session = Session.getDefaultInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                	return new PasswordAuthentication(USER,PASSWORD);
                }
            });
            MimeMessage msg = new MimeMessage(session);
            msg.setSentDate(new Date());
	        msg.setFrom(new InternetAddress(USER,FROM,ENCODING));
            
            for (String person : targetPerson) {//这里可以添加多个目的用户（to为收件人,cc为抄送,bcc为密送）
                msg.addRecipient(Message.RecipientType.TO,new InternetAddress(person,null,ENCODING));
			}
            msg.setSubject(title, ENCODING);   
            
            MimeMultipart msgMultipart = new MimeMultipart("mixed");//设置邮件内容格式为混合内容  
            MimeBodyPart content = new MimeBodyPart();  
            
            if(is!=null && fileName!=null && is.length==fileName.length){//放文件
            	for(int i=0;i<is.length;i++){
            		InputStream in = is[i];
            		BodyPart attach = new MimeBodyPart();//设置发送邮件的附件
            		DataSource data = new ByteArrayDataSource(in, "application/octet-stream");
                    attach.setDataHandler(new DataHandler(data));
                    attach.setFileName(getFileName(fileName[i]));
                    msgMultipart.addBodyPart(attach);
            	}
            }
            content.setContent(html,"text/html;charset=GBK");//设置html内容  
            msgMultipart.addBodyPart(content); 
            msg.setContent(msgMultipart);
            Transport.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("邮件发送失败！请确认是否输入正确");
        }
    }
	
	public static void main(String[] args) {
		List<String> p = new ArrayList<String>();
		p.add("1397009898@qq.com");
		
		String[]f= {"C:\\Users\\wangkaixuan\\Desktop\\1.jpg"};
		sendEmail_SSL(p,"test","<p style='font-style:italic'>this is a test!</p>",f);
	}
}