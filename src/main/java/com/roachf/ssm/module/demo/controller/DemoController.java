package com.roachf.ssm.module.demo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.roachf.ssm.module.base.controller.BaseController;
import com.roachf.ssm.module.demo.model.entity.Demo;
import com.roachf.ssm.module.demo.service.DemoService;
import com.roachf.ssm.pojo.entity.Page;

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
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Demo> list(Page page) {
		return demoService.getList();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Integer insert(@RequestBody Demo demo){
		System.out.println("demo==" + demo);
		demoService.insert(demo);
		return demo.getId();
	}
}
