package com.utiexian.third.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class MyProperties implements ApplicationListener<ContextRefreshedEvent> {

	@Value("${my.upload.path}")
	private String uploadPath;
	
	/**
	 * 文件上传路径
	 */
	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    	
    }
}