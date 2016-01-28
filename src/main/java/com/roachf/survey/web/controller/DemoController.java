package com.roachf.survey.web.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roachf.survey.pojo.entity.Demo;
import com.roachf.survey.service.DemoService;
import com.roachf.survey.utils.page.Page;

@Controller
@RequestMapping("demo")
public class DemoController {
	
	private DemoService demoService;
	
	private Logger logger = LoggerFactory.getLogger(DemoController.class);

	@Resource(name="demoService")
	public void setDemoService(DemoService demoService) {
		this.demoService = demoService;
	}
	
	public DemoService getDemoService() {
		return demoService;
	}

	/**
	 * 获取demo列表
	 * @param model
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String list(Model model, Page<Demo> page){
		Page<Demo> pages = demoService.getListByPage(page, null);
		model.addAttribute("page", pages);
		return "demo/demo";
	}
	
	/**
	 * 添加记录
	 * @param model
	 * @param demo
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String add(Model model, Demo demo){
		demoService.insert(demo);
		return "redirect:/demo";
	}
	
	/**
	 * 修改记录
	 * @param model
	 * @param demo
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public String update(Model model, Demo demo){
		demoService.update(demo);
		return "redirect:/demo";
	}
	
	/**
	 * 删除记录
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id")Integer id){
		demoService.delete(id);
		return "redirect:/demo";
	}

	/**
	 * 新增页面跳转
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/input", method = RequestMethod.GET)
	public String input(Model model){
		model.addAttribute("demo", new Demo());
		return "demo/input";
	}
	
	/**
	 * 修改页面跳转
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/input/{id}", method = RequestMethod.GET)
	public String input(@PathVariable("id")Integer id, Model model){
		Demo demo = demoService.getInfo(id);
		model.addAttribute("demo", demo);
		return "demo/input";
	}
	

}
