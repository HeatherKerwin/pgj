package com.ry.rymobile.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.form.MemberForm;


@Controller
public class TestController {
	
	
	@RequestMapping("/test/tongji")
	public void tongji(MemberForm memberForm, HttpServletRequest request, HttpServletResponse response) throws IOException{
		Enumeration enu=request.getHeaderNames();//取得全部头信息
		     while(enu.hasMoreElements()){//以此取出头信息
		       String headerName=(String)enu.nextElement();
		       String headerValue=request.getHeader(headerName);//取出头信息内容
		       System.out.println(headerName+" : "+headerValue);
		     }
		    // request.
	}
	

}
