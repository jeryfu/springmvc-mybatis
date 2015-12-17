package com.roachf.survey.web.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roachf.survey.pojo.entity.Demo;
import com.roachf.survey.pojo.entity.Page;
import com.roachf.survey.service.DemoService;

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

	@RequestMapping(method=RequestMethod.GET)
	public String list(){
		Page<Demo> pages = demoService.getListByPage(new Page<Demo>(), null);
		logger.info("pages==" + pages.toString());
		return "/demo/demo";
	}

}
