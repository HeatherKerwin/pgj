package com.ry.ryapp.util;

import java.util.List;

/**
 * 线程发送邮件
 * @author WKX
 */
public class SendEmailThread extends Thread{
	
	private List<String> person;
	private String title;
	private String html;
	private String path;
	
	public SendEmailThread(List<String> person,String title,String html,String path) {
		this.person=person;
		this.title=title;
		this.html=html;
		this.path=path;
	}
	
	@Override
    public void run() {
		EmailUtil.sendEmail(person,title, html,path);
    }

	public List<String> getPerson() {
		return person;
	}

	public void setPerson(List<String> person) {
		this.person = person;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}