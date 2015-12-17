package com.roachf.ssm.module.demo.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.roachf.ssm.module.base.service.impl.BaseServiceImpl;
import com.roachf.ssm.module.demo.dao.DemoDao;
import com.roachf.ssm.module.demo.model.entity.Demo;
import com.roachf.ssm.module.demo.service.DemoService;
import com.roachf.ssm.pojo.entity.Page;

@Service("demoService")
public class DemoServiceImpl extends BaseServiceImpl<Demo, Integer> implements DemoService{
	
	private Logger logger = Logger.getLogger(DemoServiceImpl.class);

	private DemoDao demoDao;
	
	@Resource(name="demoDao")
	public void setDemoDao(DemoDao demoDao) {
		this.demoDao = demoDao;
	}
	
	public DemoDao getDemoDao() {
		return demoDao;
	}

	public DemoServiceImpl() {
		logger.info("demo service init . . . ");
	}

	@Override
	public Page<Demo> getListByPage(Page<Demo> page, Object parameters) {
		return demoDao.getListByPage(page, parameters);
	}
	
	@Override
//	@CacheEvict(value = "demolist", key = "'#demolist'")
	public boolean insert(Demo entity) {
		return demoDao.insert(entity);
	}
}
