package com.roachf.ssm.module.demo.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roachf.ssm.module.base.controller.BaseController;
import com.roachf.ssm.module.demo.model.entity.Demo;
import com.roachf.ssm.module.demo.service.DemoService;

/**
 * 测试控制器
 * 
 * @author roach
 *
 */

@RestController
@RequestMapping(value = "/demo")
public class DemoController extends BaseController<Demo, Integer>{
	
	private Logger logger = Logger.getLogger(DemoController.class);
	
	private DemoService demoService;
	
	@Resource(name="demoService")
	public void setBaseService(DemoService demoService) {
		super.setBaseService(demoService);
		this.demoService = demoService;
	}

	public DemoService getDemoService() {
		return demoService;
	}

	public DemoController() {
		logger.info("demo controller init . . . ");
	}
}
