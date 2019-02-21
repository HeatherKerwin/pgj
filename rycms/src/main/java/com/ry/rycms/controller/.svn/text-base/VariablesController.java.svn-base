package com.ry.rycms.controller;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Variables;
import com.ry.core.service.VariablesService;
import com.ry.util.page.PageResults;

@Controller
@RequestMapping("/variables")
public class VariablesController {
	@Resource
	private VariablesService variablesService;
	
	@RequestMapping("/index")
	public String list(PageResults<?> pr,Model model){
		if(pr.getCurrentPage()==null){
			pr.setCurrentPage(0);
		}
		pr = variablesService.getPageList(pr.getCurrentPage(), 10);	
		model.addAttribute("pageModel", pr);
		return "/variables/list";
	}
	
	@RequestMapping("/add")
	public String add(Model model){
		return "/variables/update";
	}
	
	@RequestMapping("/get")
	public String update(Integer id,Model model){
		Variables v=variablesService.get(id);
		model.addAttribute("variables", v);
		return "/variables/update";
	}
	
	@RequestMapping("/update")
	public String update(Variables v,Model model) throws Exception{
		System.out.println("111");
		variablesService.saveInfo(v);
		return "redirect:/variables/index";
	}
}