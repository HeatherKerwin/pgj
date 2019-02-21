package com.utiexian.third.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.utiexian.utils.BaseController;

@Controller
@RequestMapping(value = "/news")
public class NewsController extends BaseController{

	@Autowired
    RestTemplate restTemplate;
	
	/**
     * 新闻
     * @author WKX
     */
	@RequestMapping("/index")
    public String main(Model model) {
        return "news";
    }
	
	/**
	 * 分页显示用户信息（DEMO）
	 * @author WKX
	 * @param pageIndex 第几页
	 * @param pageSize 一页个数
	 */
	@PostMapping(value="/page")
	public @ResponseBody String page(Integer pageIndex,Integer pageSize) {
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.set("pageIndex", pageIndex);
		params.set("pageSize", pageSize);
		String res = restTemplate.postForObject("http://utiexian-server/news/page",params, String.class);
		return res;
	}
}