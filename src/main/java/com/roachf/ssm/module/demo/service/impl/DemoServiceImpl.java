package com.roachf.ssm.module.demo.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.roachf.ssm.module.base.service.impl.BaseServiceImpl;
import com.roachf.ssm.module.demo.dao.DemoDao;
import com.roachf.ssm.module.demo.model.entity.Demo;
import com.roachf.ssm.module.demo.service.DemoService;

@Service("demoService")
public class DemoServiceImpl extends BaseServiceImpl<Demo, Integer> implements DemoService{
	
	private Logger logger = Logger.getLogger(DemoServiceImpl.class);

	private DemoDao demoDao;
	
	@Resource(name="demoDao")
	public void setBaseDao(DemoDao demoDao) {
		super.setBaseDao(demoDao);
		this.demoDao = demoDao;
	}
	
	public DemoDao getDemoDao() {
		return demoDao;
	}

	public DemoServiceImpl() {
		logger.info("demo service init . . . ");
	}
}
