package com.roachf.survey.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.roachf.survey.dao.DemoDao;
import com.roachf.survey.pojo.entity.Demo;
import com.roachf.survey.service.DemoService;

@Service("demoService")
public class DemoServiceImpl extends BaseServiceImpl<Demo, Integer> implements DemoService{

	private DemoDao demoDao;

	@Resource(name="demoDao")
	public void setDemoDao(DemoDao demoDao) {
		super.setBaseDao(demoDao);
		this.demoDao = demoDao;
	} 
	
	public DemoDao getDemoDao() {
		return demoDao;
	}
	

}
