package com.utiexian.rywap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class AppController {
	
	@RequestMapping("/wPaint")
	public String wPaint(){
		return "/app/wPaint/wPaint";
	}
}
